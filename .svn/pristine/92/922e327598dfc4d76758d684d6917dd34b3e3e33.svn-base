package com.bonus.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.scrap.beans.ScrapDetailsBean;
import com.bonus.scrap.service.ScrapDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/app/scrap/")
public class AppScrapController extends BaseController<Object> {

	@Autowired
	private ScrapDetailsService service;
	//报废任务
	@RequestMapping(value = "findScrapTask", method = RequestMethod.POST)
	@ResponseBody
	public List<ScrapDetailsBean> findScrapTask(ScrapDetailsBean o) {
		List<ScrapDetailsBean> list = new ArrayList<ScrapDetailsBean>();
		try {
			list = service.findScrapTask(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	//报废明细
	@RequestMapping(value = "findScrapTaskDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<ScrapDetailsBean> findScrapTaskDetails(ScrapDetailsBean o) {
		List<ScrapDetailsBean> list = new ArrayList<ScrapDetailsBean>();
		try {
			list = service.findScrapTaskDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	//查询具体机具
	@RequestMapping(value = "findScrapDevice", method = RequestMethod.POST)
	@ResponseBody
	public List<ScrapDetailsBean> findScrapDevice(ScrapDetailsBean o) {
		List<ScrapDetailsBean> list = new ArrayList<ScrapDetailsBean>();
		try {
			list = service.findScrapDevice(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	//填写报废原因
	@RequestMapping(value = "scrapReason", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes scrapReason(ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.scrapReason(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	
	//报废任务列表
	@RequestMapping(value = "getScrapIndexList", method = RequestMethod.POST)
	@ResponseBody
	public List<ScrapDetailsBean> getScrapIndexList(ScrapDetailsBean o) {
		
		List<ScrapDetailsBean> list = new ArrayList<ScrapDetailsBean>();
		try {
			
			
			list = service.getScrapIndexList(o);
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	//报废设备列表
	@RequestMapping(value = "findScrapCodeList", method = RequestMethod.POST)
	@ResponseBody
	public List<ScrapDetailsBean> findScrapCodeList(ScrapDetailsBean o) {
		
		List<ScrapDetailsBean> list = new ArrayList<ScrapDetailsBean>();
		try {
			
			
			list = service.findScrapCodeList(o);
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	
	
	
	@RequestMapping(value = "submitCodeScrap", method = RequestMethod.POST)
	@ResponseBody
	public int submitCodeScrap(HttpServletRequest request,@RequestBody ScrapDetailsBean o) {
		int res = 0;
		String image = o.getScrapUrl();
		System.out.println(image);
		if(image != null && image != "null") { //如果上传了图片，则更新图片路径
			try {
				String projectPath = request.getSession().getServletContext().getRealPath("/");
				String imgFilePath = "/upload/images/";
				File uploadPathFile = new File(projectPath + imgFilePath);

				// 创建父类文件
				if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
					uploadPathFile.mkdirs();
				}
				String picName = new Date().getTime() + ".jpg";
				//picName = request.getParameter("imageUrl");
				File imgeFile = new File(projectPath + imgFilePath, picName);
				if (!imgeFile.exists()) {
					imgeFile.createNewFile();
				}
				// 对base64进行解码
				byte[] result = Encodes.decodeBase64(image);
				// 使用Apache提供的工具类将图片写到指定路径下
				FileUtils.writeByteArrayToFile(imgeFile, result);
				o.setScrapUrl(imgFilePath + picName);
			} catch(Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
		}
		
		try {
			
			res = service.submitCodeScrap(o);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = -1;
		}
		return res;	
	}
	
	//查询具体机具
		@RequestMapping(value = "submitNumScrap", method = RequestMethod.POST)
		@ResponseBody
		public int submitNumScrap(HttpServletRequest request, ScrapDetailsBean o) {
			int res = 0;
			String image = request.getParameter("imgBase64Data");
			System.out.println(image);
			if(image != null && image != "null") { //如果上传了图片，则更新图片路径
				try {
					String projectPath = request.getSession().getServletContext().getRealPath("/");
					String imgFilePath = "/upload/images/";
					File uploadPathFile = new File(projectPath + imgFilePath);

					// 创建父类文件
					if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
						uploadPathFile.mkdirs();
					}
					String picName = new Date().getTime() + ".jpg";
					//picName = request.getParameter("imageUrl");
					File imgeFile = new File(projectPath + imgFilePath, picName);
					if (!imgeFile.exists()) {
						imgeFile.createNewFile();
					}
					// 对base64进行解码
					byte[] result = Encodes.decodeBase64(image);
					// 使用Apache提供的工具类将图片写到指定路径下
					FileUtils.writeByteArrayToFile(imgeFile, result);
					o.setScrapUrl(imgFilePath + picName);
				} catch(Exception e) {
					logger.error(e.toString(), e);
					res = -1;
				}
			}
			try {
				service.submitNumScrap(o);
				res = 1;
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
			return res;	
		}
		
		//报废设备列表
		@RequestMapping(value = "findScrapCodeListFinish", method = RequestMethod.POST)
		@ResponseBody
		public List<ScrapDetailsBean> findScrapCodeListFinish(ScrapDetailsBean o) {
			
			List<ScrapDetailsBean> list = new ArrayList<ScrapDetailsBean>();
			try {
				
				
				list = service.findScrapCodeListFinish(o);
				
				
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return list;
		}
		
		
	
}
