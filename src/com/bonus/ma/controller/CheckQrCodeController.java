package com.bonus.ma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.ma.beans.CheckQrCodeBean;
import com.bonus.ma.service.CheckQrCodeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/checkQrCode/")
public class CheckQrCodeController extends BaseController<CheckQrCodeBean> {

	@Autowired
	private CheckQrCodeService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/checkQrCodeList";
	}
	
	

	@RequestMapping("checkShowQrCode")
	public String checkShowQrCode(Model model) {
		return "/ma/checkShowQrCode";
	}
	
	@RequestMapping("showQRCodePage")
	public String showQRCodePage(Model model, HttpServletRequest req) {
		String reportCode = req.getParameter("reportCode");
		req.getSession().setAttribute("reportCode", reportCode);
		
		String id = req.getParameter("id");
		req.getSession().setAttribute("id", id);
		return "/ma/showQRCodePage";
	}
	
	
	 
	 
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<CheckQrCodeBean> page,CheckQrCodeBean o){
		AjaxRes ar = getAjaxRes();
			try {
				Page<CheckQrCodeBean> result = service.findByPage(o, page);
				Map<String,Object> p = new HashMap<String,Object>();
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(CheckQrCodeBean o){
		AjaxRes ar=getAjaxRes();
		try {
			int count=service.findCount(o);
			if(count>0){
				ar.setFailMsg("保存失败,证书编号已存在");	
			}else{
				UserBean user = UserShiroHelper.getRealCurrentUser();
			    String creator = user.getLoginName();
			    o.setCreator(creator);
				service.insertBean(o);
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			}
			
			
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(CheckQrCodeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<CheckQrCodeBean> list = service.find(o);
			CheckQrCodeBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(CheckQrCodeBean o){
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
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(CheckQrCodeBean o){
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
	
	
	

}
