package com.bonus.rm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.rm.beans.ReturnAuditBean;
import com.bonus.rm.service.ReturnAuditService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/audit/")
public class ReturnAuditController extends BaseController<ReturnAuditBean> {

	@Autowired
	private ReturnAuditService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/rm/returnApproval";
	}
	
	@RequestMapping("aduitList")
	public String aduitList(Model model) {
		return "/rm/returnAudit";
	}
	
	@RequestMapping("putInList")
	public String putInList(Model model) {
		return "/rm/returnInAudit";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ReturnAuditBean> page, ReturnAuditBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<ReturnAuditBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findAuditByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAuditByPage(Page<ReturnAuditBean> page, ReturnAuditBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<ReturnAuditBean> result = service.findAuditByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findPutInAudit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPutInAudit(Page<ReturnAuditBean> page, ReturnAuditBean o) {
		AjaxRes ar = getAjaxRes();
	
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<ReturnAuditBean> result = service.findPutInAudit(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/***
	 * 批量退料审核
	 * @param o
	 * @return
	 */
	@RequestMapping("batchAuditExamine")
	@ResponseBody
	public AjaxRes batchAuditExamine(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			String allId = o.getIds();
			String[] ids = allId.split("-");
			for(String id : ids) {
				String[] idss = id.split(",");
				o.setTaskId(idss[0]);
				o.setModelId(idss[1]);
				o.setMaNum(idss[2]);
				res = service.putInExamine(o);
				if (res == "1") {
					ar.setSucceedMsg("审核成功");
				} else {
					ar.setFailMsg("审核失败");
				}
			}	
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	/***
	 * 批量退料审核
	 * @param o
	 * @return
	 */
	@RequestMapping("batchAudit")
	@ResponseBody
	public AjaxRes batchAudit(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			String allId = o.getIds();
			String[] ids = allId.split("-");
			for(String id : ids) {
				String[] idss = id.split(",");
				o.setId(idss[0]);
				o.setTaskId(idss[1]);
				o.setModelId(idss[2]);
				o.setCheckerId(idss[3]);
				res = service.isExamine(o);
			//res = service.batchAudit(o);
			if (res == "1") {
				ar.setSucceedMsg("审核成功");
			} else {
				ar.setFailMsg("审核失败");
			}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	/***
	 * 批量退料批准
	 * @param o
	 * @return
	 */
	@RequestMapping("batchApproval")
	@ResponseBody
	public AjaxRes batchApproval(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			String allId = o.getIds();
			String[] ids = allId.split("-");
			for(String id : ids) {
				String[] idss = id.split(",");
				o.setId(idss[0]);
				o.setTaskId(idss[1]);
				o.setModelId(idss[2]);
				o.setCheckerId(idss[3]);
				res = service.isApproval(o);
			//res = service.batchAudit(o);
			if (res == "1") {
				ar.setSucceedMsg("批准成功");
			} else {
				ar.setFailMsg("批准失败");
			}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	/***
	 * 退料审核
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "isExamine", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isExamine(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.isExamine(o);
			if (res == "1") {
				ar.setSucceedMsg("审核成功");
			} else {
				ar.setFailMsg("审核失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	/***
	 * 退料审核
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "isApproval", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isApproval(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.isApproval(o);
			if (res == "1") {
				ar.setSucceedMsg("批准成功");
			} else {
				ar.setFailMsg("批准失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	/***
	 * 入库审核
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "putInExamine", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes putInExamine(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.putInExamine(o);
			if (res == "1") {
				ar.setSucceedMsg("审核成功");
			} else {
				ar.setFailMsg("审核失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	/***
	 * 审核驳回
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "putCancelExamine", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes putCancelExamine(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.putCancelExamine(o);
			if (res == "1") {
				ar.setSucceedMsg("审核驳回成功");
			} else {
				ar.setFailMsg("审核驳回失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	/***
	 * 批准审核驳回
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "putCancelApproval", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes putCancelApproval(ReturnAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.putCancelApproval(o);
			if (res == "1") {
				ar.setSucceedMsg("批准驳回成功");
			} else {
				ar.setFailMsg("批准驳回失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}

}
