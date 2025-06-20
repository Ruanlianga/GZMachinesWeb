package com.bonus.scrap.service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.scrap.beans.ScrapTaskRecordBean;
import com.bonus.scrap.dao.ScrapTaskRecordDao;
import com.bonus.sys.BaseServiceImp;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service("scrapRecord")
public class ScrapTaskRecordServiceImp extends BaseServiceImp<ScrapTaskRecordBean> implements ScrapTaskRecordService {
		
		@Autowired
		private ScrapTaskRecordDao dao;

		/**
		 * 上传报废资料
		 */
		@Override
		public Object uploadMachines(HttpServletRequest request, ScrapTaskRecordBean o) {
			String fileName = "";
			String saveDirectory = request.getSession().getServletContext().getRealPath("/scrapImg");
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
				multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", dfp);
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
							o.setModelId(maTypeId);
							o.setTaskId(taskId);
							o.setPicUrl(newName + suffix);
							dao.updatePhotoUrl(o);
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
		public List<ScrapTaskRecordBean> findMaPhotoUrl(ScrapTaskRecordBean o) {
			// TODO Auto-generated method stub
			return dao.findMaPhotoUrl(o);
		}

		@Override
		public int updatePhotoUrl(ScrapTaskRecordBean o) {
			// TODO Auto-generated method stub
			return dao.updatePhotoUrl(o);
		}

}
