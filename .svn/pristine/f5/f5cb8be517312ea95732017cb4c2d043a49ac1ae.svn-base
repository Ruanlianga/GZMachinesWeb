package com.bonus.scrap.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.lease.beans.AgreementBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.scrap.beans.ScrapApplyBean;
import com.bonus.scrap.beans.ScrapApplyFileBean;
import com.bonus.scrap.service.ScrapApplyService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/apply/")
public class ScrapApplyController extends BaseController<ScrapApplyBean> {

	@Autowired
	private ScrapApplyService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/scrap/scrapInventoryScrapFrom";
	}
	@RequestMapping("viewRemark")
	public String viewRemark(Model model) {
		return "/scrap/viewRemarkList";
	}
	
	@RequestMapping("addApply")
	public String addApply(Model model) {
		return "/scrap/scrapAddApply";
	}
	
	@RequestMapping("fileUpload")
	public String fileUpload(Model model) {
		return "/scrap/fileUpload";
	}
	
//	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
//	@ResponseBody
//	public AjaxRes findByPage(Page<ScrapApplyBean> page, ScrapApplyBean o) {
//		AjaxRes ar = getAjaxRes();
//		try {
//			Page<ScrapApplyBean> result = service.findByPage(o, page);
//			Map<String, Object> p = new HashMap<String, Object>();
//			p.put("list", result);
//			ar.setSucceed(p);
//		} catch (Exception e) {
//			logger.error(e.toString(), e);
//			ar.setFailMsg(GlobalConst.DATA_FAIL);
//		}
//		return ar;
//	}
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	public String findByPage (@RequestBody Page<ScrapApplyBean> page,ScrapApplyBean o,Model model) {
		try {
		o =page.getObj();
		page = service.findByPage(o, page);
		model.addAttribute("page", page);
		}catch (Exception e) {
			System.out.println("盘点报废单列表获取失败!");
			e.printStackTrace();
		}
		return "/scrap/scrapInventoryScrapList";
	}
	
	@RequestMapping("addInventoryCodeScrap")
	public String addInventoryCodeScrap(Model model) {
		ScrapApplyBean mt = new ScrapApplyBean();
		List<ScrapApplyBean> list = service.findParentTypeList(mt);
		model.addAttribute("machineType", list);
		return "/scrap/addInventoryCodeScrap";
	}

	@RequestMapping(value = "fandNameByFIdSeletc", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes fandNameByFIdSeletc( ScrapApplyBean o, HttpServletRequest req) {
    	AjaxRes ar = getAjaxRes();
    	try {
    		List<ScrapApplyBean> list = service.findTypeList(o);
    		ar.setSucceed(list);
    	} catch (Exception e) {
    		logger.error(e.toString(), e);
    		ar.setFailMsg(GlobalConst.DATA_FAIL);
    	}
    	return ar;
    }
	@RequestMapping(value = "findRemark", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findRemark( ScrapApplyBean o, HttpServletRequest req) {
    	AjaxRes ar = getAjaxRes();
    	try {
    		ScrapApplyBean bean = service.findRemark(o);
    		ar.setSucceed(bean);
    	} catch (Exception e) {
    		logger.error(e.toString(), e);
    		ar.setFailMsg(GlobalConst.DATA_FAIL);
    	}
    	return ar;
    }
	
	@RequestMapping(value = "findMaCodeList", method = RequestMethod.POST)
	public String findMaCodeList (@RequestBody ScrapApplyBean o,Model model) {
		try {
			
			Integer[] checkedSet = o.getCheckedSet();
			List<ScrapApplyBean> list = service.findMaCodeList(o);
			int len = checkedSet.length;
			if(len > 0){
				for(ScrapApplyBean da : list){
					Integer dId =Integer.parseInt(da.getMaId());
				    for(int i=0;i < len; i++ ){
				    	Integer checkId = checkedSet[i];
						if(checkId.equals(dId)){
							da.setChecked("checked");
							break;
						}
					}
				}
			}
			
			model.addAttribute("list", list);
		}catch (Exception e) {
			System.out.println("盘点报废单列表获取失败!");
			e.printStackTrace();
		}
		return "/scrap/addInventoryCodeScrapList";
	}
	
	
	@RequestMapping(value = "saveInventoryScrap", method = RequestMethod.POST)
	@ResponseBody
	public  AjaxRes saveInventoryScrap(HttpServletRequest req,@RequestBody ScrapApplyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			//获取表单中的token值
	    	String token = o.getToken();
	    	//获取session中的token值
	    	String sessionToken = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
	    	//session中的token容易为空,因为session中的token是需要被销毁的
	    	if (token.equals(sessionToken)) {
	    		//说明令牌相同
	    		req.getSession().removeAttribute("TOKEN_IN_SESSION");
	    		ar = service.saveInventoryScrap(o);
	    	}else {
	    		 ar.setFailMsg("重复提交！！");
	    	}
		
		}catch(Exception e) {
			ar.setFailMsg("盘点报废失败!");
			e.printStackTrace();
		}
		return ar;
	 }
	
	@RequestMapping("viewInventoryScrap")
	public String viewInventoryScrap(ScrapApplyBean o,Model model) {
		try {
			List<ScrapApplyBean> list= service.findInventoryScrapDetailsById(o);
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/scrap/viewInventoryScrap";
	}
	
	
	
	@RequestMapping(value = "findFileList")
    public String findFileList( ScrapApplyFileBean o, Model model) {
    	try {
    		List<ScrapApplyFileBean> list = service.findFileList(o);
			model.addAttribute("list", list);
    	} catch (Exception e) {
    		logger.error(e.toString(), e);
    	}
    	return "/scrap/scrapShowFiles";
    }
	
	
	/**
	 * 列表附件上传
	 */
	@ResponseBody
	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public Map<String,Object> fileUpload(@RequestParam("file")MultipartFile file,HttpServletRequest request, ScrapApplyFileBean o) {
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
                String filepath = request.getSession().getServletContext().getRealPath("/scrapApply");
               // String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
                filepath+="/"+fileName;
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.exists()){
                    files.mkdirs();
                }
                file.transferTo(files);
                
                //保存文件名文件路径
                o.setFileName(fileName);
                o.setFileUrl("/scrapApply/"+fileName);
            	int res = service.fileUpload(o);
                
                Map<String,Object> map2=new HashMap<>();
                map2.put("src","/images/"+ dateStr+"/"+fileName);
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                
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
