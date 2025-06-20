package com.bonus.newInput.service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.ma.service.MachineVersionService;
import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.beans.NewInputBean;
import com.bonus.newInput.beans.NewInputCheckBean;
import com.bonus.newInput.dao.InputDetailsDao;
import com.bonus.newInput.dao.NewInputCheckDao;
import com.bonus.newInput.dao.NewInputDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.StringHelper;
import com.bonus.sys.UserShiroHelper;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service("inputDetails")
public class InputDetailsServiceImp extends BaseServiceImp<InputDetailsBean> implements InputDetailsService {

	@Autowired
	private InputDetailsDao dao;

	@Autowired
	private NewInputDao nDao;

	@Autowired
	private TaskRecordDao trDao;

	@Autowired
	private NewInputCheckDao ncDao;

	@Autowired
	private MachineDao mDao;

	@Autowired
	private MachineTypeDao mtDao;
	
	@Autowired
	private MachineVersionService mvService;
	

	/***
	 * 添加新购明细 1.机具表 2.新购明细表 3.明细和机具关系表
	 */
	@Override
	public int insertBean(InputDetailsBean o) {
		o.setCheckStatus("1");
		boolean isExist = devIsExist(o);
		if (isExist) {
			dao.insertBean(o);
			updateStasus(o);
			addMachineDetails(o);
			return 1;
		} else {
			return 0;
		}

	}
	
	/**
	 * 判断是否已存在
	 */
    private boolean devIsExist(InputDetailsBean o){
    	List<InputDetailsBean> list = dao.find(o);
    	if (list != null && list.size() > 0) {
			return false;
		}else{
			return true;
		}
    }
	
	/**
	 * 插入机具信息表 判断是否计数
	 */
	private void addMachineDetails(InputDetailsBean o) {
		String nums = o.getArrivalNum();
		int num = Integer.parseInt(nums);
		 String typeId = o.getMaModelId();
		MachineBean bean = new MachineBean();
		bean.setType(typeId);
		bean.setBatchStatus("1");//待通知人员
		bean.setVerderId(o.getMaVenderId());
		bean.setBuyTime(DateTimeHelper.getNowTime());
		boolean tf = isCount(o);
		if (!tf) {
			for (int i = 0; i < num; i++) {
				
	            String deviceType = "";
				
				deviceType =  mvService.getDeviceType(typeId);
				
				bean.setDeviceType(deviceType);
				
				mDao.insert(bean);
				o.setMaId(bean.getId());
				dao.insertMaNewInput(o);
			}
		} else {
			return;
		}
	}

	/**
	 * 查询机具是否计数
	 * 
	 * @param o
	 * @return
	 */
	public boolean isCount(InputDetailsBean o) {
		MachineTypeBean bean = new MachineTypeBean();
		bean.setMaModelId(o.getMaModelId());
		MachineTypeBean mtBean = mtDao.findByModelId(bean);
		if ("1".equals(mtBean.getIsCount())) {
			return true;
		}
		return false;
	}

	/**
	 * 指定人员
	 */
	@Override
	public int updatePerson(InputDetailsBean o) {
		dao.update(o);
		boolean tf = isAppoint(o);
		if (!tf) {
			o.setCheckStatus("2");
			dao.updateCheckStatusById(o);
		}
		// 修改机具状态
		List<InputDetailsBean> list = dao.find(o);
		for (InputDetailsBean bean : list) {
			MachineBean mBean = new MachineBean();
			mBean.setId(bean.getId());
			mBean.setBatchStatus("2");//待检验
			mDao.update(mBean);
		}
		return 1;
	}

	/**
	 * 是否指定
	 * 
	 * @param o
	 * @return
	 */
	private boolean isAppoint(InputDetailsBean o) {
		InputDetailsBean bean = dao.findPersonInfo(o);
		if (StringHelper.isEmpty(bean.getChecker()) || StringHelper.isEmpty(bean.getCustomerRep())) {
			return true;
		}
		return false;
	}

	/***
	 * 修改新购任务状态
	 * 
	 * @param o
	 */
	public void updateStasus(InputDetailsBean o) {
		NewInputBean bean = new NewInputBean();
		bean.setTaskId(o.getTaskId());
		bean.setTaskStatus("2");
		nDao.updateStatus(bean);
	}

	/**
	 * 1.修改状态 2.建立检验任务 3.判断该验收任务是否结束（）
	 */
	@Override
	public int isSure(InputDetailsBean o) {
		o.setCheckStatus("3");
		dao.isSure(o);
		buildCheckTask(o);
		return 1;
	}

	/**
	 * 1.判断当前新购任务下是否已有检验任务 2.没有则去建立检验任务并添加明细，有只添加明细
	 * 
	 * @param o
	 */
	public void buildCheckTask(InputDetailsBean o) {
		TaskRecordBean bean = new TaskRecordBean();
		bean.setTaskId(o.getTaskId());
		bean.setDefinitionId("14");
		List<TaskRecordBean> list = trDao.findTaskInfo(bean);
		if (list != null && list.size() > 0) {
			o.setTaskId(list.get(0).getTaskId());
			addCheckDetails(o);
		} else {
			NewInputBean nBean = new NewInputBean();
			nBean.setId(o.getTaskId());
			NewInputBean niBean = nDao.findTaskById(nBean);
			o.setTaskId(niBean.getTaskId());
			addCheckTask(o);
		}

	}

	/**
	 * 创建检验任务
	 * 
	 * @param o
	 */
	public void addCheckTask(InputDetailsBean o) {
		String userId = UserShiroHelper.getRealCurrentUser().getId() + "";
		TaskRecordBean bean = new TaskRecordBean();
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(userId);
		bean.setDefinitionId("14");
		bean.setProcessId("7");
		bean.setIsFinish("0");
		bean.setTaskId(o.getTaskId());
		trDao.insert(bean);
		o.setTaskId(bean.getId());
		addCheckDetails(o);
	}

	/**
	 * 添加检验明细
	 * 
	 * @param o
	 */
	public void addCheckDetails(InputDetailsBean o) {
		NewInputCheckBean bean = new NewInputCheckBean();
		bean.setTaskId(o.getTaskId());
		bean.setCheckNum(o.getCheckNum());
		bean.setCheckTime(DateTimeHelper.getNowTime());
		bean.setMaModelId(o.getMaModelId());
		ncDao.insert(bean);
	}

	@Override
	public void updateCheckStatus(InputDetailsBean idBean) {
		dao.updateCheckStatus(idBean);
	}
/**
 * 上传机具图片
 */
	@Override
	public Object uploadMachines(HttpServletRequest request, InputDetailsBean o) {
		String fileName = "";
		// 文件上传采用cos组件上传，可更换为commons-fileupload上传，文件上传后，保存在upload文件夹
		// 获取文件上传路径
		String saveDirectory = request.getSession().getServletContext().getRealPath("/machinesImg");
		File file = new File(saveDirectory);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 打印上传路径信息
		System.out.println("文件保存路径=" + saveDirectory);
		// 每个文件最大50m
		int maxPostSize = 200 * 1024 * 1024;
		// 采用cos缺省的命名策略，重名后加1,2,3...如果不加dfp重名将覆盖
		// DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
		// response的编码为"UTF-8",同时采用缺省的文件名冲突解决策略,实现上传,如果不加dfp重名将覆盖
		// MultipartRequest multi = new MultipartRequest(request, saveDirectory,
		// maxPostSize,"UTF-8",dfp);
		MultipartRequest multi = null;
		DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
		// long tiem =(new java.util.Date()).getTime();
		try {
			multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", dfp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 输出反馈信息
		Enumeration<?> files = multi.getFileNames();
		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();
			File f = multi.getFile(name);
			System.err.println("f=" + f);
			if (f != null) {
				String maTypeId = multi.getParameter("maTypeId");
				String taskId = multi.getParameter("taskId");

				String newName = DateTimeHelper.getNowTimeFomart();
				fileName = multi.getFilesystemName(name);
				int begin = fileName.indexOf(".");
				int last = fileName.length();
				String suffix = fileName.substring(begin, last);
				// 更换文件名称
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
						o.setMaModelId(maTypeId);
						o.setTaskId(taskId);
						o.setPicUrl(newName + suffix);
						dao.updatePhotoUrl(o);
						MachineBean bean = new MachineBean();
						bean.setType(maTypeId);
						bean.setPicUrl(newName + suffix);
						mDao.insertMachinesUrl(bean);
					}
				} else {
					logger.error("新文件名和旧文件名相同...");
				}
			}
			return null;
		}
		return null;
	}
/**
 * 上传验收图片
 */
	@Override
	public InputDetailsBean uploadAccept(HttpServletRequest request, InputDetailsBean o) {
		String fileName = "";
		// 文件上传采用cos组件上传，可更换为commons-fileupload上传，文件上传后，保存在upload文件夹
		// 获取文件上传路径
		String saveDirectory = request.getSession().getServletContext().getRealPath("/acceptImg");
		File file = new File(saveDirectory);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 打印上传路径信息
		System.out.println("文件保存路径=" + saveDirectory);
		// 每个文件最大50m
		int maxPostSize = 200 * 1024 * 1024;
		// 采用cos缺省的命名策略，重名后加1,2,3...如果不加dfp重名将覆盖
		// DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
		// response的编码为"UTF-8",同时采用缺省的文件名冲突解决策略,实现上传,如果不加dfp重名将覆盖
		// MultipartRequest multi = new MultipartRequest(request, saveDirectory,
		// maxPostSize,"UTF-8",dfp);
		MultipartRequest multi = null;
		DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
		// long tiem =(new java.util.Date()).getTime();
		try {
			multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", dfp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 输出反馈信息
		Enumeration<?> files = multi.getFileNames();
		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();
			File f = multi.getFile(name);
			System.err.println("f=" + f);
			if (f != null) {
				String batchId = multi.getParameter("maTypeId");
				String taskId = multi.getParameter("taskId");

				String newName = DateTimeHelper.getNowTimeFomart();
				fileName = multi.getFilesystemName(name);
				int begin = fileName.indexOf(".");
				int last = fileName.length();
				String suffix = fileName.substring(begin, last);
				// 更换文件名称
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
						o.setMaModelId(batchId);
						o.setTaskId(taskId);
						o.setCheckUrl(newName + suffix);
						dao.updateCheckUrl(o);
						//上传完图片建立新购二维码打印任务
					}
				} else {
					logger.error("新文件名和旧文件名相同...");
				}
			}
			return o;
		}
		return o;
	}
	
/**
 * 新购检验
 */
	@Override
	public int check(InputDetailsBean o) {
		dao.updateCheckNum(o);
		o.setCheckStatus("3");
		o.setCheckTime(DateTimeHelper.getNowTime());
		dao.isSure(o);
		boolean tf = isChecked(o);
		if (tf) {
			NewInputBean bean = new NewInputBean();
			bean.setTaskStatus("3");
			bean.setTaskId(o.getTaskId());
			nDao.updateStatus(bean);
		}
		// 修改机具状态
		List<InputDetailsBean> list = dao.find(o);
		for (InputDetailsBean bean : list) {
			MachineBean mBean = new MachineBean();
			mBean.setId(bean.getId());
			mBean.setBatchStatus("3");
			mDao.update(mBean);
		}
		return 1;
	}
/***
 * 判断是否全部检验完成
 * @param o
 * @return
 */
	public boolean isChecked(InputDetailsBean o) {
		List<InputDetailsBean> list = dao.findByTaskId(o);
		for (InputDetailsBean idBean : list) {
			if ("3".equals(idBean.getCheckStatus())) {
				continue;
			}else {
				return false;
			}
		}
		return true;
	}
/**
 * 上传验收照片
 */
	@Override
	public void checkUpload(InputDetailsBean bean) {
		bean.setCheckStatus("4");
		dao.isSure(bean);
		boolean tf = isUpload(bean);
		if (tf) {
			NewInputBean nBean = new NewInputBean();
			nBean.setTaskStatus("4");
			nBean.setTaskId(bean.getTaskId());
			nDao.updateStatus(nBean);
		}
	}
	/***
	 * 判断是否全部上传验收图片
	 * @param o
	 * @return
	 */
		public boolean isUpload(InputDetailsBean o) {
			List<InputDetailsBean> list = dao.findByTaskId(o);
			for (InputDetailsBean idBean : list) {
				if ("4".equals(idBean.getCheckStatus())) {
					continue;
				}else {
					return false;
				}
			}
			return true;
		}
		
	@Override
	public String findPicUrl(InputDetailsBean bean) {
		return dao.findPicUrl(bean);
	}

	@Override
	public int updateInvoiceUrl(InputDetailsBean bean) {
		// TODO Auto-generated method stub
		return dao.updateInvoiceUrl(bean);
	}

	@Override
	public List<InputDetailsBean> findInvoiceUrl(InputDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.findInvoiceUrl(o);
	}

	@Override
	public List<InputDetailsBean> findManchinesUrl(InputDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.findManchinesUrl(o);
	}

	@Override
	public List<InputDetailsBean> findcheckUrl(InputDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.findcheckUrl(o);
	}

	@Override
	public int updatePhotoUrl(InputDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.updatePhotoUrl(o);
	}

	@Override
	public int updateCheckUrl(InputDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.updateCheckUrl(o);
	}
}
