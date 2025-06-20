package com.bonus.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class BaseServiceImp<T> implements BaseService<T> {

	protected Logger logger = Logger.getLogger(BaseServiceImp.class);
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public void insert(T o) {
		baseDao.insert(o);
	}

	@Override
	public void delete(T o) {
		baseDao.delete(o);
	}

	public AjaxRes getAjaxRes() {
		return new AjaxRes();
	}
	
	@Override
	public void deleteBatch(List<T> os) {
		baseDao.deleteBatch(os);
	}

	@Override
	public void update(T o) {
		baseDao.update(o);
	}

	@Override
	public List<T> find(T o) {
		return baseDao.find(o);
	}

	@Override
	public Page<T> findByPage(T o, Page<T> page) {
		page.setResults(baseDao.findByPage(o, page));
		return page;
	}
	@Override
	public Page<T> fileViewFindByPage(T o, Page<T> page) {
		page.setResults(baseDao.fileViewFindByPage(o, page));
		return page;
	}
	
	public HashMap<String, Object> uploadFileSpring(HttpServletRequest request) {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<MultipartFile> tmps = new ArrayList<MultipartFile>();
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next());
				tmps.add(file);
			}
		}
		map.put("filePath", tmps);
		return map;
	}

	public HashMap<String, Object> uploadFile(HttpServletRequest request) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<FileItem> tmps = new ArrayList<FileItem>();
			List<FileItem> items = upload.parseRequest(request);
			if (null != items) {
				Iterator<FileItem> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
						map.put(item.getFieldName(), item.getString("UTF-8"));
					} else {
						tmps.add(item);
					}
				}
			}
			map.put("filePath", tmps);
		} catch (Exception e) {
			logger.error("dowith request upload file failed!", e);
			return null;
		}
		return map;
	}

	@Override
	public Page<T> findByPageOne(T o, Page<T> page) {
		page.setResults(baseDao.findByPage(o, page));
		return page;
	}

	@Override
	public Page<T> findByPageTwo(T o, Page<T> page) {
		page.setResults(baseDao.findByPageTwo(o, page));
		return page;
	}
	@Override
	public Page<T> findByPageThree(T o, Page<T> page) {
		page.setResults(baseDao.findByPageThree(o, page));
		return page;
	}
	
	public List<Integer> getIntList(String ids) {
		List<Integer> idList = new ArrayList<Integer>();
		String[] arr = ids.split(",");
		Integer size = arr.length; 
		for(int i=0;i<size;i++) {
		    idList.add(Integer.parseInt(arr[i]));
		}
		return idList;
	}
	public String genNextCode(String tableName,String condition,String codePart){
		String code = null;
		String nextCode = null;
		
		String sql = " select `code` from "+ tableName +" where `code` like '"+condition+"%' order by `code` desc  limit 1 ";
		System.out.println(sql);
		
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		if(queryForList!=null && queryForList.size()>0){
		 code = (String)queryForList.get(0).get("code");
			String nums = "1";
			try {
				nums = code.substring(code.lastIndexOf("-")+1);
			} catch (Exception e) {
				nums = "1";
			}
			
			int num = Integer.parseInt(nums)+1;
			
			if(num >= 1 && num <= 9){
				
				nextCode = codePart +"-000"+num;
				
			}else if(num >= 10 && num <= 99){
				
				nextCode = codePart +"-00"+num;
				
			}else if(num >= 100 && num <= 999){
				
				nextCode = codePart +"-0"+num;
				
			}else{
				
				nextCode = codePart +"-"+num;
				
			}
			
		}else{
			nextCode = codePart + "-0001";
		}
		return nextCode;
	}
}
