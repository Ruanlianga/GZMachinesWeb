package com.bonus.newSettlement.service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.newSettlement.beans.ProjectSettlementDetailsBean;
import com.bonus.newSettlement.beans.ProjectSettlementInfoBean;
import com.bonus.newSettlement.dao.MaTypeProjectStorageDao;
import com.bonus.newSettlement.dao.ProjectSettlementDetailsDao;
import com.bonus.newSettlement.dao.ProjectSettlementInfoDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.StringHelper;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service("ProjectSettlementInfoService")
public class ProjectSettlementInfoServiceImp extends BaseServiceImp<ProjectSettlementInfoBean> implements ProjectSettlementInfoService {


	@Autowired
	private ProjectSettlementInfoDao infoDao;

	@Autowired
	private ProjectSettlementDetailsDao detailsDao;
	
	@Autowired
	private MaTypeProjectStorageDao storageDao;
	
	@Override
	@Transactional
	public int insertSlt(ProjectSettlementInfoBean o) {
		Integer result = 0;
		try {
			
			String time = DateTimeHelper.getNowDateFomart();
			String nowYear = DateTimeHelper.getNowYear();
			String code = genNextCode("project_settlement_info","JS"+nowYear,"JS"+time);
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String orgId = user.getCompanyId();
			o.setOrgId(orgId);
			o.setCode(code);
			o.setCreator(UserShiroHelper.getRealCurrentUser());
			o.setCreateTime(DateTimeHelper.currentDateTime());
			result = infoDao.insertBean(o);
			if(result == 0){
				throw new ZeroAffectRowsException("结算生成失败,结算基本信息插入操作错误!");
			}
			
			ProjectSettlementDetailsBean[] items = o.getItems();
			
			MaTypeProjectStorageBean storage = null;
			if(items != null && items.length > 0){
				for(ProjectSettlementDetailsBean bean : items){
					bean.setSltInfo(o);
					String backDate = bean.getBackDate();
					bean.setBackDate(backDate);
					result = detailsDao.insertBean(bean);
					if(result == 0){
						throw new ZeroAffectRowsException("结算生成失败,结算详情信息插入操作错误!");
					}
					
					if("1".equals(bean.getCostType()) ){
						storage = bean.getStorage();
						if(StringHelper.isNotEmpty(bean.getEndDate())){
							storage.setLastSltDate(bean.getEndDate());
							String endDate = bean.getEndDate();
							
							if(backDate != null){
								if(endDate.compareTo(backDate) >= 0){
									storage.setIsSlt(1);
								}
							}
							result = storageDao.updateBean(storage);
							if(result == 0){
								throw new ZeroAffectRowsException("结算生成失败,工程库存信息修改操作错误!");
							}
						}
				
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public ProjectSettlementInfoBean findSettlementInfoById(ProjectSettlementInfoBean o) {
		return infoDao.findSettlementInfoById(o);
	}

	@Override
	public List<ProjectSettlementInfoBean> findBaseInfoById(ProjectSettlementInfoBean o) {
		return infoDao.findBaseInfoById(o);
	}

	@Override
	public List<ProjectSettlementInfoBean> findMaInfoById(ProjectSettlementInfoBean o) {
		return infoDao.findMaInfoById(o);
	}

	@Override
	public Page<ProjectSettlementInfoBean> findSettleContent(Page<ProjectSettlementInfoBean> page,
			ProjectSettlementInfoBean o) {
		page.setResults(infoDao.findSettleContent(page,o));
		return page;
	}

	@Override
	public Integer updateSettleSta(ProjectSettlementInfoBean o) {
		Integer res = 0;
		res = infoDao.updateSettleSta(o);
		return res;
	}


	@Override
	public Object uploadFile(HttpServletRequest request,ProjectSettlementInfoBean o) {
		// 文件上传采用cos组件上传，可更换为commons-fileupload上传，文件上传后，保存在upload文件夹
		// 获取文件上传路径
		String saveDirectory = request.getSession().getServletContext().getRealPath("/ProjectSettlementInfoFile");
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
//		long tiem =(new java.util.Date()).getTime();
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
			if (f != null) {
				String ids = multi.getParameter("id");
			    Integer id =Integer.valueOf(ids).intValue();
				o.setId(id);
				String fileUrl = multi.getFilesystemName(name);
				System.err.println("fileUrl=="+fileUrl);
				String newName = DateTimeHelper.getNowTimeFomart();
				int begin = fileUrl.indexOf(".");
				int last = fileUrl.length();
				String suffix = fileUrl.substring(begin,last);
				//更换文件名称
				if (!fileUrl.equals(newName)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
					File oldfile = new File(saveDirectory + "/" + fileUrl);
					File newfile = new File(saveDirectory + "/" + newName + suffix);
					if (!oldfile.exists()) {
						logger.error("需要重命名的文件不存在");
					}
					if (newfile.exists()) {// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
						logger.error(newName + "已经存在！");
					} else {
						oldfile.renameTo(newfile);
						o.setFileUrl(newName+suffix);
						o.setOldFileUrl(fileUrl);
						 infoDao.update(o);
					}
				} else {
					logger.error("新文件名和旧文件名相同...");
				}
			}
			return null;
		}
		return null;
	}

	@Override
	public ProjectSettlementInfoBean findSettlementFileUrlById(ProjectSettlementInfoBean o) {
		// TODO Auto-generated method stub
		return infoDao.findSettlementFileUrlById(o);
	}
}
