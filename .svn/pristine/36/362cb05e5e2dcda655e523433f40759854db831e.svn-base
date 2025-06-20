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

import com.bonus.data.UUIDUtil;
import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repair.service.RepairDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/app/repair/")
public class AppRepairController extends BaseController<Object> {

	@Autowired
	private RepairDetailsService service;
	
	
	//维修任务
	@RequestMapping(value = "findRepairTask", method = RequestMethod.POST)
	@ResponseBody
	public List<RepairDetailsBean> findRepairTask(RepairDetailsBean o) {
		
		List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
		try {
			list = service.findRepairTask(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	
	
		
	
	//维修明细
	@RequestMapping(value = "findRepairTaskDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<RepairDetailsBean> findRepairTaskDetails(RepairDetailsBean o) {
		List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
		try {
			list = service.findRepairTaskDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	//查询具体机具
	@RequestMapping(value = "findRepairDevice", method = RequestMethod.POST)
	@ResponseBody
	public List<RepairDetailsBean> findRepairDevice(RepairDetailsBean o) {
		List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
		try {
			list = service.findRepairDevice(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	//查询机具类型
	@RequestMapping(value = "findTypeName", method = RequestMethod.POST)
	@ResponseBody
		public List<RepairDetailsBean> findTypeName(RepairDetailsBean o) {
		
		List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
		try {
			list = service.findTypeName(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		return list;
	}
	
	//查询机具规格
		@RequestMapping(value = "findModelName", method = RequestMethod.POST)
		@ResponseBody
			public List<RepairDetailsBean> findModelName(RepairDetailsBean o) {
			List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
			try {
				list = service.findModelName(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			System.out.println(list);
			return list;
		}
	
	
	//查询具体机具
	@RequestMapping(value = "repairOperation", method = RequestMethod.POST)
	@ResponseBody
	public int repairOperation(HttpServletRequest request, RepairDetailsBean o) {
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
			service.repairOperation(o);
			res = 1;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = -1;
		}
		return res;	
	}
	
	
	

	//維修拆分
		@RequestMapping(value = "repairSplit", method = RequestMethod.POST)
		@ResponseBody
		public String repairSplit(HttpServletRequest request, RepairDetailsBean o) {
			String res = "";
			try {
				res = service.repairSplit(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = "拆分出错";
			}
			return res;	
		}
		
		//结束维修
		@RequestMapping(value = "finishRepair",method = RequestMethod.POST)
		@ResponseBody
		public String finishRepair(HttpServletRequest request,RepairDetailsBean o){
			String res = "";
			try{
				res = service.finishRepair(o);
			}catch (Exception e) {
				// TODO: handle exception
				res = "结束维修使出现错误了！";
			}
			
			return res;
		}
		
		//维修任务列表
				@RequestMapping(value = "getRepairIndexList", method = RequestMethod.POST)
				@ResponseBody
				public List<RepairDetailsBean> getRepairIndexList(RepairDetailsBean o) {
					
					List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
					try {
						
						
						list = service.getRepairIndexList(o);
						
						
					} catch (Exception e) {
						logger.error(e.toString(), e);
					}
					return list;
				}
				
				//维修任务列表
				@RequestMapping(value = "getRepairedNumList", method = RequestMethod.POST)
				@ResponseBody
				public List<RepairDetailsBean> getRepairedNumList(RepairDetailsBean o) {
					
					List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
					try {
						
						
						list = service.getRepairedNumList(o);
						
						
					} catch (Exception e) {
						logger.error(e.toString(), e);
					}
					return list;
				}
				
	
		@RequestMapping(value = "submitNumRepair", method = RequestMethod.POST)
		@ResponseBody
		public int submitNumRepair(HttpServletRequest request,@RequestBody RepairDetailsBean o) {
			int res = 0;
			String[] images = o.getRepairUrls();
			if(images != null) { //如果上传了图片，则更新图片路径\
				if(images.length > 0) {
					for(int i = 0;i<images.length;i++){
						String image = images[i];
						try {
							String projectPath = request.getSession().getServletContext().getRealPath("/");
							String imgFilePath = "/upload/images/";
							File uploadPathFile = new File(projectPath + imgFilePath);

							// 创建父类文件
							if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
								uploadPathFile.mkdirs();
							}
							String uuid = UUIDUtil.getUUID();
							String picName = uuid + ".jpg";
							//picName = request.getParameter("imageUrl");
							File imgeFile = new File(projectPath + imgFilePath, picName);
							if (!imgeFile.exists()) {
								imgeFile.createNewFile();
							}
							// 对base64进行解码
							byte[] result = Encodes.decodeBase64(image);
							// 使用Apache提供的工具类将图片写到指定路径下
							FileUtils.writeByteArrayToFile(imgeFile, result);
							images[i] = imgFilePath + picName;
							
						} catch(Exception e) {
							logger.error(e.toString(), e);
							res = -1;
						}
					}
				}
			}
			try {
				res = service.submitNumRepair(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
			return res;	
		}
		
		
		//查询具体维修机具
		@RequestMapping(value = "findRepairCodeList", method = RequestMethod.POST)
		@ResponseBody
		public List<RepairDetailsBean> findRepairCodeList(RepairDetailsBean o) {
			List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
			try {
				list = service.findRepairCodeList(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return list;
		}
		
		@RequestMapping(value = "submitCodeRepair", method = RequestMethod.POST)
		@ResponseBody
		public int submitCodeRepair(HttpServletRequest request,@RequestBody RepairDetailsBean o) {
			int res = 0;
			String[] images = o.getRepairUrls();
			if(images != null) { //如果上传了图片，则更新图片路径
				if(images.length > 0) {
					for(int i = 0;i<images.length;i++){
						String image = images[i];
						try {
							String projectPath = request.getSession().getServletContext().getRealPath("/");
							String imgFilePath = "/upload/images/";
							File uploadPathFile = new File(projectPath + imgFilePath);

							// 创建父类文件
							if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
								uploadPathFile.mkdirs();
							}
							String uuid = UUIDUtil.getUUID();
							String picName = uuid + ".jpg";
							//picName = request.getParameter("imageUrl");
							File imgeFile = new File(projectPath + imgFilePath, picName);
							if (!imgeFile.exists()) {
								imgeFile.createNewFile();
							}
							// 对base64进行解码
							byte[] result = Encodes.decodeBase64(image);
							// 使用Apache提供的工具类将图片写到指定路径下
							FileUtils.writeByteArrayToFile(imgeFile, result);
							images[i] = imgFilePath + picName;
							
						} catch(Exception e) {
							logger.error(e.toString(), e);
							res = -1;
						}
					}
				}
			}	
			
			try {
				o.setRepairUrls(images);
				res = service.submitCodeRepair(o);
				
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
			return res;	
		}
		
		@RequestMapping(value = "splitCodeRepair", method = RequestMethod.POST)
		@ResponseBody
		public int splitCodeRepair(HttpServletRequest request, RepairDetailsBean o) {
			int res = 0;
			try {
				
				res = service.splitCodeRepair(o);
				
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
			return res;	
		}
		
		
		//查询具体机具
		@RequestMapping(value = "submitNumScrap", method = RequestMethod.POST)
		@ResponseBody
		public int submitNumScrap(HttpServletRequest request, RepairDetailsBean o) {
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

		
		@RequestMapping(value = "submitCodeScrap", method = RequestMethod.POST)
		@ResponseBody
		public int submitCodeScrap(HttpServletRequest request,@RequestBody RepairDetailsBean o) {
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
		
		//查询具体维修机具
		@RequestMapping(value = "findRepairCodeListFinish", method = RequestMethod.POST)
		@ResponseBody
		public List<RepairDetailsBean> findRepairCodeListFinish(RepairDetailsBean o) {
			List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
			try {
				list = service.findRepairCodeListFinish(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return list;
		}
		
		@RequestMapping(value = "getRepairDetails", method = RequestMethod.POST)
		@ResponseBody
		public AjaxRes getRfidNMachineStatus(RepairDetailsBean o) throws Exception {
			AjaxRes ar = getAjaxRes();
			try {
				List<RepairDetailsBean> list = service.getRepairDetails(o);
				if(list !=null && list.size()>0){
					ar.setRes(1);
					ar.setResMsg("success");
					ar.setSucceed(list);
				}else{
					ar.setRes(0);
					ar.setFailMsg("无相关信息");
					ar.setSucceed("失败");
				
				}
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
			return ar;
		}
		
		//查询具体维修机具
		@RequestMapping(value = "getRepairMan", method = RequestMethod.POST)
		@ResponseBody
		public List<RepairDetailsBean> getRepairMan(RepairDetailsBean o) {
			List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
			try {
				list = service.getRepairMan(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return list;
		}

}
