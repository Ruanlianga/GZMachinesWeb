package com.bonus.rm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.rm.service.PutInStorageTaskService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/puttask/")
public class PutInStorageTaskController extends BaseController<PutInStorageTaskBean> {

	@Autowired
	private PutInStorageTaskService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/rm/puttasklist";
	}
	@RequestMapping("backcodelist")
	public String backcodelist(Model model) {
		return "/rm/backcodelist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<PutInStorageTaskBean> page, PutInStorageTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<PutInStorageTaskBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findBackCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findBackCode(Page<PutInStorageTaskBean> page, PutInStorageTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
		
			Page<PutInStorageTaskBean> result = service.findByPageTwo(o, page);
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
	 * 指派客服代表
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "updatePutPerson", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updatePutPerson(PutInStorageTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			 service.updatePutPerson(o);
			ar.setSucceedMsg("指派成功");
		} catch (Exception e) {
			ar.setFailMsg("指派失败");
			logger.error(e.toString(), e);
		}
		return ar;
	}
	/***
	 * 指派客服代表
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "updatePutPersons", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updatePutPersons(PutInStorageTaskBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			String[] values = o.getValue().split("-");
			for(int i = 0;i < values.length;i++){
				String[] vals = values[i].split(",");
				if(!"on".equals(vals[0])){
					o.setId(vals[0]);
					o.setTaskId(vals[1]);
					o.setIsSure(vals[2]);
					o.setMaModelId(vals[3]);
					o.setPrePutNum(vals[4]);
					
					res = service.updatePutPerson(o);
				}
			}
			ar.setSucceedMsg(res);
		} catch (Exception e) {
			ar.setFailMsg("指派失败");
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	/***
	 * 发布
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "isRelease", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isRelease(PutInStorageTaskBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.isRelease(o);
			if (res == "1") {
				ar.setSucceedMsg("发布成功");
			} else {
				ar.setFailMsg("发布失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	/***
	 * 删除
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "deleteReturn", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes deleteReturn(PutInStorageTaskBean o) {
		int res = 0;
		AjaxRes ar = getAjaxRes();
		try {
			res = service.deleteReturn(o);
			if (res > 0) {
				ar.setSucceedMsg("删除成功");
			} else {
				ar.setFailMsg("删除失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
}
