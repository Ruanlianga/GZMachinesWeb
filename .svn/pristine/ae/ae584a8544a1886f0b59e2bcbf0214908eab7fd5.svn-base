package com.bonus.newInput.service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.LogBean;
import com.bonus.bm.dao.LogDao;
import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.newInput.beans.NewInputBean;
import com.bonus.newInput.dao.InputDetailsDao;
import com.bonus.newInput.dao.NewInputDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service("new")
public class NewInputServiceImp extends BaseServiceImp<NewInputBean> implements NewInputService{

	@Autowired 
	private NewInputDao dao;
	
	@Autowired 
	private TaskRecordDao trDao;
	
	@Autowired 
	private InputDetailsDao idDao;
	
	@Autowired
	MachineDao maDao;

	@Autowired
	MachineTypeDao mtDao;
	
	@Autowired
	LogDao logDao;
	@Override
	public void add(NewInputBean o) {
		addTaskRecord(o);
	}
	/**
	 * 新购任务建立
	 * @param o
	 */
	private void addTaskRecord(NewInputBean o){
		String userId = UserShiroHelper.getRealCurrentUser().getId()+"";
		TaskRecordBean bean = new TaskRecordBean();
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(userId);
		bean.setDefinitionId("12");
		bean.setProcessId("7");
		bean.setIsFinish("0");
		bean.setCompanyId(o.getCompanyId());
		trDao.insert(bean);
		o.setTaskId(bean.getId());
		o.setLaunchId(userId);
		dao.add(o);
	}
	@Override
	public Object uploadPhoto(HttpServletRequest request,NewInputBean o) {
		 String fileName = "";
		  String newName = "";
		  String suffix = "";
		  // 文件上传采用cos组件上传，可更换为commons-fileupload上传，文件上传后，保存在upload文件夹
		  // 获取文件上传路径
		  String saveDirectory = request.getSession().getServletContext().getRealPath("/lossImg");
		  File file = new File(saveDirectory);
		  if (!file.exists()) {
		   file.mkdirs();
		  }
		  // 打印上传路径信息
		  System.out.println("文件保存路径=" + saveDirectory);
		  // 每个文件最大50m
		  int maxPostSize = 200 * 1024 * 1024;
		  
		  MultipartRequest multi = null;
		  DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
		  try {
		   multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8",dfp);
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  // 输出反馈信息
		  Enumeration<?> files = multi.getFileNames();
		  while (files.hasMoreElements()) {
		   String name = (String) files.nextElement();
		   File f = multi.getFile(name);
		   System.err.println("f=" + f);
		   if (f != null) {
		    fileName = multi.getFilesystemName(name);
		    
		    newName = DateTimeHelper.getNowTimeFomart();
		    int begin = fileName.indexOf(".");
		    int last = fileName.length();
		    suffix = fileName.substring(begin,last);
		    //更换文件名称
		    if (!fileName.equals(newName)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
		     File oldfile = new File(saveDirectory + "/" + fileName);
		     File newfile = new File(saveDirectory + "/" + newName + suffix);
		     if (!oldfile.exists()) {
		      logger.error("需要重命名的文件不存在");
		     }
		     if (newfile.exists()) {// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
		      logger.error(newName + "已经存在！");
		     } else {
		      oldfile.renameTo(newfile);
		     }
		    } else {
		     logger.error("新文件名和旧文件名相同...");
		    }
		   }
		   return newName + suffix;
		  }
		  return null;
		 }
	@Override
	public void isExamine(NewInputBean o) {
		o.setIsExamine("1");
		dao.isExamine(o);
		
	}
	/**
	 * 批准 1.修改机具状态，增加库存 2.修改任务状态3.修改批准状态
	 */
	@Override
	public void isApproval(NewInputBean o) {
		//添加日志
		LogBean logBean= new LogBean();
		logBean.setModel("新购批准");
		logBean.setFun("/backstage/new/isApproval");
		logBean.setTask(o.getTaskId());
		logBean.setTypeId(o.getMaModelId());
		LogBean inBean=logDao.findInNum(logBean);
		String description="在库数:"+inBean.getInNum()+";批准数:"+o.getNums();
		logBean.setDescription(description);
		String time = DateTimeHelper.currentDateTime();
		logBean.setTime(time);
		String userId = UserShiroHelper.getRealCurrentUser().getId()+"";
		logBean.setCreator(userId);
		logDao.insertLog(logBean);
		
		LogBean total = logDao.findTotalNum(logBean);
		float num1 = Float.parseFloat(total.getTotal());
		float num2 = Float.parseFloat(o.getNums());
		float num3 = num1+num2;
		String description2 =total.getTotal()+"=>:"+num3;
		total.setTask(o.getTaskId());
		total.setTaskCode("新购批准入库");
		total.setCreator(userId);
		total.setTime(time);
		total.setDescription(description2);
		logDao.insertTotalLog(total);
		//修改批准状态
		updateIsApproval(o);
		//判断任务是否满足完成 满足改为已完成
		boolean tf = updateTaskStatus(o);
		TaskRecordBean bean = new TaskRecordBean();
		if (tf) {
			bean.setIsFinish("1");
			bean.setId(o.getTaskId());
			trDao.update(bean);
		}
		//修改机具状态 和库存
		updateDevStatus(o);
		
	}
	public void updateIsApproval(NewInputBean o){
		o.setIsApproval("1");
		dao.isApproval(o);
	}
	public boolean updateTaskStatus(NewInputBean o){
		List<NewInputBean> list = dao.findDetailsByTask(o);
		for (NewInputBean bean : list) {
			if ("0".equals(bean.getIsApproval()) || "0".equals(bean.getIsExamine())) {
				return false;
			}
		}
		return true;
	}
	//修改机具状态，改库存
	public void updateDevStatus(NewInputBean o) {
		MachineTypeBean mmtBean = new MachineTypeBean();
		mmtBean.setMaModelId(o.getMaModelId());
		MachineTypeBean mmBean = mtDao.findByModelId(mmtBean);
		if (mmBean.getIsCount().equals("0")) {
			List<NewInputBean> list = dao.findCodeByModel(o);
			MachineBean maBean = new MachineBean();
			for (NewInputBean bean : list) {
				maBean.setTypeId(o.getMaModelId());
				maBean.setDeviceCode(bean.getDeviceCode());
				maBean.setBatchStatus("5");
				// 改状态
				maDao.updateMachineStatus(maBean);
			}
			updateMaNum(o);
		}else{
			updateMaNum(o);
		}
	}

	public void updateMaNum(NewInputBean o) {
		MachineBean maBean = new MachineBean();
		maBean.setTypeId(o.getMaModelId());
		MachineBean mBean = maDao.findMachineNum(maBean);
		// 修改库存
		float sums = Float.parseFloat(mBean.getSums());
		float nums = Float.parseFloat(o.getNums());
		float maNum = sums + nums;
		DecimalFormat mFormat = new DecimalFormat(".000");
		String formatNum = mFormat.format(maNum);
		MachineTypeBean mtBean = new MachineTypeBean();
		mtBean.setId(o.getMaModelId());
		mtBean.setNums(formatNum);
		// 修改库存
		mtDao.update(mtBean);
	}
	@Override
	public Page<NewInputBean> findIsExamine(NewInputBean o, Page<NewInputBean> page) {
		page.setResults(dao.findIsExamine(o, page));
		return page;
	}
	@Override
	public Page<NewInputBean> findIsApproval(NewInputBean o, Page<NewInputBean> page) {
		page.setResults(dao.findIsApproval(o, page));
		return page;
	}
	@Override
	public List<NewInputBean> newPurchaseReceipt(NewInputBean o) {
		// TODO Auto-generated method stub
		return dao.newPurchaseReceipt(o);
	}
	
	public int deleteQrcode(NewInputBean o) {
		int res = 0;
		List<NewInputBean> list = dao.findMaId(o);
		if (list != null) {
			for(NewInputBean input : list){
				res = res + dao.deleteMachine(input);
			}
		}
		dao.delete(o);
		return res;
	}
	
}
