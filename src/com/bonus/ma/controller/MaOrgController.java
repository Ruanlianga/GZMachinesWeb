package com.bonus.ma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.service.AuditLogsService;
import com.bonus.ma.beans.MaOrgBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.service.MaOrgService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.beans.ZNode;
import com.bonus.sys.service.OrgService;
import com.bonus.sys.service.UserService;

@Controller
@RequestMapping("/backstage/maOrg/")
public class MaOrgController extends BaseController<MaOrgBean> {

	@Autowired
	private MaOrgService service;

	@Autowired
	private UserService userService;

	@Autowired
	private AuditLogsService aservice;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/machine_org_list";
	}

	@RequestMapping(value = "getOrgTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgTree(MaOrgBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = new ArrayList<>();
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setOrgId(companyId);
			list = service.getOrgTree(o);
			for (ZNode zNode : list) {
				
				zNode.setOpen(true);
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
	
	@RequestMapping(value = "getMaListByOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAllByKeeper(MaOrgBean o) {
		AjaxRes ar = getAjaxRes();
	
			try {
				List<MaOrgBean> list = service.getMaListByOrg(o);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", list);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
	
		return ar;
	}
	
	@RequestMapping(value="updateOrgRelation", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateOrgRelation(String orgId,String chks){
		AjaxRes ar=getAjaxRes();
		try {
			int res = service.updateOrgRelation(orgId,chks);
			if(res >0 ){
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			}else{
				ar.setFailMsg(GlobalConst.SAVE_FAIL);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(MaOrgBean o) {
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
}
