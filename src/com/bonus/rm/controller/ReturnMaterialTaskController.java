package com.bonus.rm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.service.subcontractorsService;
import com.bonus.lease.beans.AgreementBean;
import com.bonus.rm.beans.ReturnMaterialTaskBean;
import com.bonus.rm.service.ReturnMaterialTaskService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/rm/task/")
public class ReturnMaterialTaskController extends BaseController<ReturnMaterialTaskBean> {

	@Autowired
	private ReturnMaterialTaskService service;
	
	@Autowired
	private subcontractorsService subService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/rm/tasklist";
	}
	
	/***
	 * 批量删除
	 * @param o
	 * @return
	 */
	@RequestMapping("deleteTask")
	@ResponseBody
	public AjaxRes deleteTask(ReturnMaterialTaskBean o) {
		Integer res = 0;
		AjaxRes ar = getAjaxRes();
		try {
			String allId = o.getIds();
			String[] ids = allId.split(",");
			for(String id : ids) {
				o.setId(id);
				res = service.deleteTask(o);
				if (res == 1) {
					ar.setSucceedMsg("删除成功");
				} else {
					ar.setFailMsg("删除失败");
				}
			}	
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ReturnMaterialTaskBean> page, ReturnMaterialTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		      String companyId = user.getCompanyId();
		      o.setOrgId(companyId);
			Page<ReturnMaterialTaskBean> result = service.findByPage(o, page);
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
	public AjaxRes find(ReturnMaterialTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ReturnMaterialTaskBean> list = service.find(o);
			ReturnMaterialTaskBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//查询协议号
	@RequestMapping(value = "findAgreeCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAgreeCode(AgreementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<AgreementBean> list = service.findAgreeCode(o);
			AgreementBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//查询申请单号
	@RequestMapping(value = "findNumber", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMaterialTaskBean findNumber(ReturnMaterialTaskBean o) {
		try {
			o.setNumber(service.findNumber(o));
			o.setSubcontractorsList(subService.findSubcontractors());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		
		return o;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(ReturnMaterialTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			
			service.addTask(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(ReturnMaterialTaskBean o) {
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
	
	@RequestMapping(value = "updateChecker", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateChecker(ReturnMaterialTaskBean o) {
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
	public AjaxRes del(ReturnMaterialTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "delBatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks) {
		AjaxRes ar = getAjaxRes();
		try {
//			service.deleteBatch(chks);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

}
