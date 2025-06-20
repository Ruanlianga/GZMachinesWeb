package com.bonus.lease.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.lease.beans.AgreementBean;
import com.bonus.lease.beans.MachineReceiveBean;
import com.bonus.lease.beans.ReceiveDetailsBean;
import com.bonus.lease.service.AgreementService;
import com.bonus.lease.service.MachineReceiveService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/machineReceive/")
public class MachineReceiveController extends BaseController<MachineReceiveBean> {

	@Autowired
	private MachineReceiveService service;

	@Autowired
	private AgreementService aservice;

	@RequestMapping("list")
	public String index(Model model) {
		return "/lease/machineReceive_list";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<MachineReceiveBean> page, MachineReceiveBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			Page<MachineReceiveBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findSheet", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findSheet(MachineReceiveBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineReceiveBean> result = service.findLeaseSheet(o);
			//MachineReceiveBean m = result.get(0);
			if (result != null && !result.isEmpty()) {
				for (MachineReceiveBean bean : result) {
					UserBean user = UserShiroHelper.getRealCurrentUser();
					String applyTime = bean.getApplyTime();
					//bean.setTestTime(applyTime);
					String nextTestTime = DateTimeHelper.getNextYearDate(applyTime);
					//bean.setNextTestTime(nextTestTime);
					bean.setMakeOrderPerson(user.getName());
				}
			}else {
				ar.setFailMsg("暂无领料单！");
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "updateConfirmStatus", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateConfirmStatus(@RequestParam("taskId") String taskId) {
		AjaxRes ar = getAjaxRes();
		int updated = service.updateConfirmStatus(taskId);
		if (updated > 0) {
			ar.setSucceedMsg("操作成功");
		} else {
			ar.setFailMsg("操作失败");
		}
		return ar;
	}
	
	@RequestMapping(value = "findAgreementCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAgreementCode(AgreementBean o) {
		AjaxRes ar = getAjaxRes();
		String agreeCode = aservice.findAgreementCode(o);
		ar.setSucceedMsg(agreeCode);
		return ar;
	}
	
	/**
	 * 保存修改的领料单数据
	 * @param bean
	 * @return map
	 */
	@RequestMapping(value = "saveMaterialRequisition", method = RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> saveMaterialRequisition(MachineReceiveBean o) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MachineReceiveBean> list = new ArrayList<MachineReceiveBean>();
		try {
			String[] remarkMachineString = o.getRemarkMachine().split(",");
			String[] isCountString = o.getIsCount().split(",");
			String[] wmaIdString = o.getId().split(",");
			list = service.findLeaseSheet(o);
			o.setId(list.get(0).getTaskId());
			o.setTaskId(list.get(0).getTaskId1());
			o.setModelId(list.get(0).getModelId());
			int mainTask = service.saveMainTask(o);
			
			boolean flag = true;
			for(int i = 0; i < remarkMachineString.length; i++){
				o.setRemarkMachine(remarkMachineString[i]);
				o.setId(wmaIdString[i]);
				int materialRequisition = service.saveMaterialRequisition(o);
				if(materialRequisition != -1){
					flag = true;
				}else{
					flag = false;
				}
			}
			
			if(flag && mainTask != -1){
				map.put("res", "修改成功");
			}else{
				map.put("res", "修改失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(MachineReceiveBean o) {
		AjaxRes ar = getAjaxRes();
		
		try {
			List<MachineReceiveBean> list = service.findDetails(o);
			if (list.size() > 0 && list != null) {
				ar.setFailMsg("该领料单已分配机具，请先删除相关机具！");
			}else {
				int result = service.del(o);
				if (result > 0) {
					ar.setSucceedMsg("删除成功");
				}else {
					ar.setFailMsg("删除失败");
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg("删除失败");
		}
		ar.setSucceed(o);
		return ar;
	}
	
}
