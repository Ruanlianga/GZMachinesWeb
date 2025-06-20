package com.bonus.bm.controller;

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

import com.bonus.bm.beans.CompanyBean;
import com.bonus.bm.service.CompanyService;
import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.ZNode;

@Controller
@RequestMapping("/backstage/company/")
public class CompanyController extends BaseController<CompanyBean> {
	@Autowired
	private CompanyService comService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/company_list";
	}

	@RequestMapping("unitTree")
	public String unitTree(Model model) {
		return "/bm/unitTree";
	}
	
	@RequestMapping("unitTreePlus")
	public String unitTreePlus(Model model) {
		return "/bm/unitTreePlus";
	}
	
	@RequestMapping("unitTree2")
	public String unitTree2(Model model) {
		return "/bm/unitTree2";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<CompanyBean> page, CompanyBean o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(GlobalConst.RESOURCES_TYPE_MENU, "/backstage/company/list"))) {
			try {
				if (o != null && !"".equals(o.getEndTime())) {
					String time = DateTimeHelper.getAddNumDay(o.getEndTime(), 1);
					o.setEndTime(time);
				}
				Page<CompanyBean> station = comService.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(CompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			comService.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(CompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			comService.update(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(CompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = comService.find(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCompany() {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = comService.getCompany();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getCompanyType", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCompanyType() {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = comService.getCompanyType();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insert(CompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			CompanyBean bean = comService.findByNameAndCompanyId(o);
			if (bean == null) {
				String typeId = o.getTypeId1();
				//String companyId = o.getCompanyId1();
				if("0".equals(typeId)){
					ar.setSucceedMsg("请选择单位类型");
				}else{
					comService.insert(o);
					ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
				}
			} else {
				ar.setSucceedMsg("往来单位已存在");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getUnit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getUnit(CompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = comService.getUnit(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "unitTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes unitTree(CompanyBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			String isOpen = req.getParameter("isOpen");
			List<ZNode> list = comService.unitTree(o);
			if (list != null && list.size() > 0) {
				if (StringHelper.isNotEmpty(isOpen)) {
					for (ZNode zNode : list) {
						zNode.setOpen(true);
					}
				}
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getUnitName", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getUnitName(CompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = comService.getUnitName(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

}
