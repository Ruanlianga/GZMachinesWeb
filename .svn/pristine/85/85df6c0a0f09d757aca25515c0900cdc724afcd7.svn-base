package com.bonus.ma.controller;

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

import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.VenderBean;
import com.bonus.ma.service.VenderService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;

@Controller
@RequestMapping("/backstage/vender/")
public class VenderController extends BaseController<VenderBean> {

	@Autowired
	private VenderService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/venderlist";
	}
	
	@RequestMapping("maVenderTree")
	public String maModelTree(Model model) {
		return "/ma/maVenderTree";
	}
	
	// 库管员
	@RequestMapping("makeeper")
	public String makeeper(Model model) {
		return "/ma/maKeeper";
	}

	
	// 机具图片上传
	@RequestMapping("machinesPic")
	public String machinesPic(Model model) {
		return "/ma/updVenderPic";
	}

	// 机具图片查看
	@RequestMapping("readMachinesPic")
	public String readMachinesPic(Model model) {
		return "/ma/readVenderPic";
	}
	
	
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<VenderBean> page,VenderBean o){
		AjaxRes ar = getAjaxRes();
			try {
				Page<VenderBean> result = service.findByPage(o, page);
				Map<String,Object> p = new HashMap<String,Object>();
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(VenderBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<VenderBean> list = service.find(o);
			VenderBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(VenderBean o){
		AjaxRes ar=getAjaxRes();
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value="updateVend", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateVend(VenderBean o){
		AjaxRes ar=getAjaxRes();
		try {
			service.updateVend(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}
	
	
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(VenderBean o){
		AjaxRes ar=getAjaxRes();
		try {
			int res = service.insertBean(o);
			if(res==1)ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else ar.setFailMsg("登录名已存在");	
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(VenderBean o){
		AjaxRes ar=getAjaxRes();
		try {
			service.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks){
		AjaxRes ar=getAjaxRes();
		try {
			service.deleteBatch(chks);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="findVender",method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findVender(VenderBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<VenderBean> list = service.findVender(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "maVenderTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes maModelTree(VenderBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = service.maVenderTree(o);
			Map<String, Object> p  = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//库管员
	@RequestMapping(value = "makeeper", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes makeeper(VenderBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = service.makeeper(o);
			Map<String, Object> p  = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 厂家图片上传
	 */
	@ResponseBody
	@RequestMapping(value = "uploadVender", method = RequestMethod.POST)
	public Map<String,Object> uploadVender(@RequestParam("file")MultipartFile file,HttpServletRequest request, VenderBean o) {
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
                String saveDirectory = request.getSession().getServletContext().getRealPath("/venderzImg");
               // String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
                
                File files = new File(saveDirectory);
    		     if (!files.exists()) {
    		      files.mkdirs();
    		    }
    		     
    		     File dest=new File(files,fileName);
                 file.transferTo(dest);
               
                
                //保存文件名文件路径
                o.setPicName(fileName);
                o.setPicUrl(saveDirectory);
                
            	service.updateVend(o);
                
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

}
