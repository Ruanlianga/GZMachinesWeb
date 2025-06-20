package com.bonus.rm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.rm.beans.PutInStorageAuditBean;
import com.bonus.rm.service.PutInStorageAuditService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/putAudit/")
public class PutInStorageAuditController extends BaseController<PutInStorageAuditBean> {

	@Autowired
	private PutInStorageAuditService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/rm/putAuditlist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<PutInStorageAuditBean> page, PutInStorageAuditBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setCompanyId(companyId);
			Page<PutInStorageAuditBean> result = service.findByPage(o, page);
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
	 * 审核
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "isAudit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isAudit(PutInStorageAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.isAudit(o);
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
	 * 批量退料审核
	 * @param o
	 * @return
	 */
	@RequestMapping("batchAudit")
	@ResponseBody
	public AjaxRes batchAudit(PutInStorageAuditBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			String allId = o.getIds();
			String[] ids = allId.split("-");
			for(String id : ids) {
				String[] idss = id.split(",");
				o.setId(idss[0]);
				o.setTaskId(idss[1]);
				o.setMaModelId(idss[2]);
				o.setIsCount(idss[3]);
				res = service.isAudit(o);
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
}