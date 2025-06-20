package com.bonus.newInput.controller;

import java.util.ArrayList;
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

import com.bonus.newInput.beans.NewInputBean;
import com.bonus.newInput.service.NewInputService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.service.UserService;

@Controller
@RequestMapping("/backstage/new/")
public class NewInputController extends BaseController<NewInputBean> {

	@Autowired
	private NewInputService service;

	@Autowired
	private UserService uService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/newInput/newInputBatchlist";
	}
	
	@RequestMapping("isExamine")
	public String isExamine(Model model) {
		return "/newInput/newInputAuditinglist";
	}

	@RequestMapping("isApproval")
	public String isApproval(Model model) {
		return "/newInput/newInputApprovallist";
	}
	// 批次机具详情
	@RequestMapping("details")
	public String details(Model model) {
		return "/newInput/detailslist";
	}
	
	
	
	/***
	 * 批量退料审核
	 * @param o
	 * @return
	 */
	@RequestMapping("batchAudit")
	@ResponseBody
	public AjaxRes batchAuditExamine(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String allId = o.getIds();
			String[] ids = allId.split("-");
			for(String id : ids) {
				o.setId(id);
				service.isExamine(o);
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			}	
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}

	/*// 机具发票上传
	@RequestMapping("updInvoicePic")
	public String updInvoicePic(Model model) {
		return "/newInput/updInvoicePic";
	}
*/
	// 机具发票查看
	@RequestMapping("readInvoicePic")
	public String readInvoicePic(Model model) {
		return "/newInput/readInvoicePic";
	}

	// 机具图片上传
	@RequestMapping("machinesPic")
	public String machinesPic(Model model) {
		return "/newInput/updMachinesPic";
	}

	// 机具图片查看
	@RequestMapping("readMachinesPic")
	public String readMachinesPic(Model model) {
		return "/pm/readMachinesPic";
	}

	// 转固定资产
	@RequestMapping("toFixedAssets")
	public String toFixedAssets(Model model) {
		return "/pm/toFixedAssets";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<NewInputBean> page, NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			page.setCompanyId(companyId);
			Page<NewInputBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "newPurchaseReceipt", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes newPurchaseReceipt(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<NewInputBean> result = service.newPurchaseReceipt(o);
			//MachineReceiveBean m = result.get(0);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findIsExamine", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findIsExamine(Page<NewInputBean> page, NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			page.setCompanyId(companyId);
			Page<NewInputBean> result = service.findIsExamine(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findIsApproval", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findIsApproval(Page<NewInputBean> page, NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			page.setCompanyId(companyId);
			Page<NewInputBean> result = service.findIsApproval(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<NewInputBean> list = service.find(o);
			NewInputBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			service.add(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	@RequestMapping(value = "isExamine", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isExamine(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.isExamine(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	@RequestMapping(value = "isApproval", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isApproval(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.isApproval(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.deleteQrcode(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	/**
	 * 图片文件上传
	 */
	@ResponseBody
	@RequestMapping(value = "uploadPhoto", method = RequestMethod.POST)
	public AjaxRes uploadPhoto(HttpServletRequest request, NewInputBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.uploadPhoto(request, o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED); 
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findAllUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAllUserInfo(UserBean o) {
		AjaxRes ar = getAjaxRes();
		List<UserBean> list = new ArrayList<>();
		try {
			list = uService.findAllUserInfo(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
}
