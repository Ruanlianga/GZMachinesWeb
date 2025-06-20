package com.bonus.newInput.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.service.InputDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/inputDetails/")
public class InputDetailsController extends BaseController<InputDetailsBean> {

	@Autowired
	private InputDetailsService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/newInput/newInputDetailslist";
	}
	/* zwc
	 * 发票上传
	 */
	@RequestMapping("imgInvoice")
	public String imgLoad(Model model, HttpServletRequest req) {
		return "/newInput/imgInvoice";
	}
	
	/* zwc
	 * 发票查看
	 */
	@RequestMapping("queryImgPage")
	public String queryImg(Model model, HttpServletRequest req,InputDetailsBean o) {
		String taskId= req.getParameter("taskId");
		String maModelId= req.getParameter("maModelId");
		o.setTaskId(taskId);
		o.setMaModelId(maModelId);
		List<InputDetailsBean> str = service.findInvoiceUrl(o);
		req.setAttribute("str", str.get(0).getInvoiceUrl());
		return "/newInput/nvoicePhoto";
	}
	
	
	/* zwc
	 * 机具信息上传
	 */
	@RequestMapping("imgManchines")
	public String imgManchines(Model model, HttpServletRequest req) {
		return "/newInput/imgManchines";
	}
	
	/* zwc
	 * 机具信息查看
	 */
	@RequestMapping("queryImgManchines")
	public String queryImgManchines(Model model, HttpServletRequest req,InputDetailsBean o) {
		String taskId= req.getParameter("taskId");
		String maModelId= req.getParameter("maModelId");
		o.setTaskId(taskId);
		o.setMaModelId(maModelId);
		List<InputDetailsBean> str = service.findManchinesUrl(o);
		req.setAttribute("str", str.get(0).getPicUrl());
		return "/newInput/queryImgManchines";
	}
	
	
	
	/* zwc
	 * 检查图片
	 */
	@RequestMapping("imgCheck")
	public String imgCheck(Model model, HttpServletRequest req) {
		return "/newInput/imgCheck";
	}
	
	/* zwc
	 * 检查图片查看
	 */
	@RequestMapping("queryImgCheck")
	public String queryImgCheck(Model model, HttpServletRequest req,InputDetailsBean o) {
		String taskId= req.getParameter("taskId");
		String maModelId= req.getParameter("maModelId");
		o.setTaskId(taskId);
		o.setMaModelId(maModelId);
		List<InputDetailsBean> str = service.findcheckUrl(o);
		req.setAttribute("str", str.get(0).getCheckUrl());
		return "/newInput/queryImgCheck";
	}
	
	
/*	 zwc
	 * 机具图片上传
	 
	@RequestMapping("machinesPic")
	public String machinesPic(Model model) {
		return "/newInput/updMachinesPic";
	}

	// 机具图片查看
	@RequestMapping("readMachinesPic")
	public String readMachinesPic(Model model) {
		return "/newInput/readMachinesPic";
	}*/
	
	

	/*// 机具发票上传
	@RequestMapping("updInvoicePic")
	public String updInvoicePic(Model model) {
		return "/newInput/updInvoicePic";
	}

	// 机具发票查看
	@RequestMapping("readInvoicePic")
	public String readInvoicePic(Model model) {
		return "/newInput/readInvoicePic";
	}*/

	// 机具图片上传
	@RequestMapping("machinesPic")
	public String machinesPic(Model model) {
		return "/newInput/updMachinesPic";
	}

	// 机具图片查看
	@RequestMapping("readMachinesPic")
	public String readMachinesPic(Model model) {
		return "/newInput/readMachinesPic";
	}

	// 验收图片上传
	@RequestMapping("acceptPic")
	public String acceptPic(Model model) {
		return "/newInput/updAcceptPic";
	}

	// 验收图片查看
	@RequestMapping("readAcceptPic")
	public String readAcceptPic(Model model) {
		return "/newInput/readAcceptPic";
	}
	
	// 相关配套资料上传
	@RequestMapping("updAboutFile")
	public String updAboutFile(Model model) {
		return "/newInput/updAboutFile";
	}

	// 固定资产
	@RequestMapping("toFixedAssets")
	public String toFixedAssets(Model model) {
		return "/pm/toFixedAssets";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<InputDetailsBean> page, InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			page.setCompanyId(companyId);
			Page<InputDetailsBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	/**
	 * 删除 
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(HttpServletRequest request, InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String savePath = request.getSession().getServletContext().getRealPath("/machinesImg");
			System.out.println(savePath);
			String path = savePath + "/" +  service.findPicUrl(o);
			File file = new File(path);
			if(file.isFile() && file.exists()) 
				file.delete(); //删除文件
			service.delete(o); //删除数据库记录
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<InputDetailsBean> list = service.find(o);
			InputDetailsBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = service.insertBean(o);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else
				ar.setFailMsg(GlobalConst.DEV_ISEXIST);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	/**
	 * 未用到
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "isSure", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isSure(InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = service.isSure(o);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else
				ar.setFailMsg(GlobalConst.SAVE_FAIL);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	@RequestMapping(value = "check", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes check(InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = service.check(o);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else
				ar.setFailMsg(GlobalConst.SAVE_FAIL);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = service.updatePerson(o);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else
				ar.setFailMsg(GlobalConst.SAVE_FAIL);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	@RequestMapping(value = "uploadImg")
	@ResponseBody
    public Map<String,Object> upload(@RequestParam("file")MultipartFile file,InputDetailsBean o,HttpServletRequest request){
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String fileName = file.getOriginalFilename();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//                String saveDirectory = request.getSession().getServletContext().getRealPath("/lossImg");
                String mkdirsName = "lossImg"; // 机具管理-机具类型管理
        		String saveDirectory = "/data/gzimt/" + mkdirsName + "/"; // linux 系统路径
                String os = System.getProperty("os.name");
                if (os.toLowerCase().startsWith("win")) {
                	saveDirectory = "e://GZMachinesWeb/" + mkdirsName + "/";
                }
                 File files = new File(saveDirectory);
      		     if (!files.exists()) {
      		      files.mkdirs();
      		    }
                File dest=new File(files,fileName);
                 file.transferTo(dest);
                //保存文件名文件路径
                o.setInvoiceUrl(fileName);
                List<InputDetailsBean> list = new ArrayList<InputDetailsBean>();
                int res = service.updateInvoiceUrl(o);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+fileName);
                return map;
            }

        }catch (Exception e){
        	logger.error(e.toString(),e);
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }
	
	
	
	//机具信息图片上传
	@RequestMapping(value = "uploadManchines")
	@ResponseBody
    public Map<String,Object> uploadManchines(@RequestParam("file")MultipartFile file,InputDetailsBean o,HttpServletRequest request){
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String fileName = file.getOriginalFilename();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//                String saveDirectory = request.getSession().getServletContext().getRealPath("/machinesImg");
                String mkdirsName = "machineImg"; // 机具管理-机具类型管理
        		String saveDirectory = "/data/gzimt/" + mkdirsName + "/"; // linux 系统路径
                String os = System.getProperty("os.name");
                if (os.toLowerCase().startsWith("win")) {
                	saveDirectory = "e://GZMachinesWeb/" + mkdirsName + "/";
                }
                 File files = new File(saveDirectory);
      		     if (!files.exists()) {
      		      files.mkdirs();
      		    }
                File dest=new File(files,fileName);
                 file.transferTo(dest);
                //保存文件名文件路径
                o.setPicUrl(fileName);
                List<InputDetailsBean> list = new ArrayList<InputDetailsBean>();
                int res = service.updatePhotoUrl(o);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+fileName);
                return map;
            }

        }catch (Exception e){
        	logger.error(e.toString(),e);
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }
	
	
	//检查图片上传
	@RequestMapping(value = "uploadCheckUrl")
	@ResponseBody
    public Map<String,Object> uploadCheckUrl(@RequestParam("file")MultipartFile file,InputDetailsBean o,HttpServletRequest request){
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String fileName = file.getOriginalFilename();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//                String saveDirectory = request.getSession().getServletContext().getRealPath("/acceptImg");
                String mkdirsName = "acceptImg"; // 机具管理-机具类型管理
        		String saveDirectory = "/data/gzimt/" + mkdirsName + "/"; // linux 系统路径
                String os = System.getProperty("os.name");
                if (os.toLowerCase().startsWith("win")) {
                	saveDirectory = "e://GZMachinesWeb/" + mkdirsName + "/";
                }
                 File files = new File(saveDirectory);
      		     if (!files.exists()) {
      		      files.mkdirs();
      		    }
                File dest=new File(files,fileName);
                 file.transferTo(dest);
                 String taskId = request.getParameter("taskId");
                 String maTypeId = request.getParameter("maTypeId");
     			String maModelId =  request.getParameter("maModelId");
     			o.setTaskId(taskId);
     			o.setMaTypeId(maTypeId);
     			o.setMaModelId(maModelId);
     			o.setCheckStatus("4");
     			service.checkUpload(o);
                //保存文件名文件路径
                o.setCheckUrl(fileName);
                List<InputDetailsBean> list = new ArrayList<InputDetailsBean>();
                int res = service.updateCheckUrl(o);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+fileName);
                return map;
            }

        }catch (Exception e){
        	logger.error(e.toString(),e);
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }
	
	
	 
/*
	*//**
	 * 图片文件上传
	 *//*
	@ResponseBody
	@RequestMapping(value = "uploadPhoto", method = RequestMethod.POST)
	public AjaxRes uploadPhoto(HttpServletRequest request, InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}*/

	/***
	 * 机具图片上传
	 */
	@ResponseBody
	@RequestMapping(value = "uploadMachines", method = RequestMethod.POST)
	public AjaxRes uploadMachines(HttpServletRequest request, InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.uploadMachines(request, o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	/**
	 * 验收图片上传
	 */
	@ResponseBody
	@RequestMapping(value = "uploadAccept", method = RequestMethod.POST)
	public AjaxRes uploadAccept(HttpServletRequest request, InputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			InputDetailsBean idBean = service.uploadAccept(request, o);
			String taskId = idBean.getTaskId();
			String maTypeId = idBean.getMaTypeId();
			InputDetailsBean bean = new InputDetailsBean();
			bean.setMaModelId(maTypeId);
			bean.setTaskId(taskId);
			o.setCheckStatus("4");
			service.checkUpload(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

}
