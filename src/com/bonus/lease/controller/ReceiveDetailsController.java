package com.bonus.lease.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.beans.ReceiveDetailsBean;
import com.bonus.lease.service.OutStorageService;
import com.bonus.lease.service.ReceiveDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/receiveDetails/")
public class ReceiveDetailsController extends BaseController<ReceiveDetailsBean> {

	@Autowired
	private OutStorageService oservice;
	
	@Autowired
	private ReceiveDetailsService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/lease/receiveDetailslist";
	}

	@RequestMapping("readScarpPic")
	public String readScarpPic(Model model) {
		return "/lease/readScarpPic";
	}

	@RequestMapping("readTestPic")
	public String readTestPic(Model model) {
		return "/lease/readTestPic";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ReceiveDetailsBean> page, ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		page.setPageSize(1000);
		try {
			Page<ReceiveDetailsBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		List<ReceiveDetailsBean> list = service.findBean(o);
		try {
			if(list.size() != 0) {
				//有重复记录
				if ("1".equals(list.get(0).getIsSure())) {
					//已确认
					ar.setFailMsg(GlobalConst.COLLAR_FAIL);
				} else {
					//未确认
					service.updateBean(o);
					ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
				}
			} else {
				int res = service.insertBean(o);
				if (res == 1) {
					ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
				} else {
					ar.setFailMsg(GlobalConst.SAVE_FAIL);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}

	@RequestMapping(value = "isSure", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isSure(ReceiveDetailsBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			res = service.isSure(o);
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

	@RequestMapping(value = "isSures", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isSures(HttpSession session,HttpServletRequest req,ReceiveDetailsBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			
			//获取表单中的token值
        	String token = o.getToken();
        	//获取session中的token值
        	String sessionToken = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
        	if (token.equals(sessionToken)) {
				//说明令牌相同
        		//session中的token容易为空,因为session中的token是需要被销毁的
        		req.getSession().removeAttribute("TOKEN_IN_SESSION");
        		
    			String[] values = o.getValue().split("-");
    			for(int i = 0;i < values.length;i++){
    				String[] vals = values[i].split(",");
    				if(!"on".equals(vals[0])){
    					o.setTaskId(vals[0]);
    					o.setMaModelId(vals[1]);
    					o.setPreCollerNum(vals[2]);
    					o.setCheckerId(vals[3]);
    					res = service.isSure(o);
    				}
    			}
    			if (res == "1") {
    				ar.setSucceedMsg("发布成功");
    			} else {
    				ar.setFailMsg("发布失败");
    			}
    			
        		
        	}else {
        		 ar.setFailMsg("重复提交！！");
        	}
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "batchDeletion", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes batchDeletion(HttpSession session,HttpServletRequest req,ReceiveDetailsBean o) {
		String res = "";
		AjaxRes ar = getAjaxRes();
		try {
			
			//获取表单中的token值
        	String token = o.getToken();
        	//获取session中的token值
        	String sessionToken = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
        	if (token.equals(sessionToken)) {//说明令牌相同
        		
        		//session中的token容易为空,因为session中的token是需要被销毁的
        		req.getSession().removeAttribute("TOKEN_IN_SESSION");
        		
    			String[] values = o.getValue().split("-");
    			for(int i = 0;i < values.length;i++){
    				String[] vals = values[i].split(",");
    				if(!"on".equals(vals[0])){
    					o.setTaskId(vals[0]);
    					o.setMaModelId(vals[1]);
    					o.setPreCollerNum(vals[2]);
    					o.setCheckerId(vals[3]);
    					res = service.batchDeletion(o);
    				}
    			}
    			if (res == "1") {
    				ar.setSucceedMsg("删除成功");
    			} else {
    				ar.setFailMsg("删除失败");
    			}
    			
        		
        	}else {
        		 ar.setFailMsg("重复提交！！");
        	}
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}

	@RequestMapping(value = "allSure", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes allSure(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.allSure(o);
			ar.setSucceedMsg("发布成功");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg("发布失败");
		}
		return ar;
	}
	/**
	 * 领料明细删除功能 1.未确认之前只需删除领料明细，确认后因为生成了出库任务，故要删除出库任务
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		List<ReceiveDetailsBean> list = service.findBean(o);
		try {
			if (list.get(0).getIsExamine().equals("0") && o.getTaskId() != null && o.getMaModelId() != null) {
				if (list.get(0).getIsSure().equals("1")) {//删除出库任务
					//查询出库任务Id
					List<ReceiveDetailsBean> rdList = service.findTaskId(o);
					if (list.size() > 0 && list != null) {
						ReceiveDetailsBean bean = new ReceiveDetailsBean();
						bean.setTaskId(rdList.get(0).getId());
						bean.setMaModelId(o.getMaModelId());
						service.delOutTask(bean);
					}
				}
				service.delete(o);
				ar.setSucceedMsg("删除成功");
			} else {
				ar.setFailMsg("无法删除");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg("删除失败");
		}
		ar.setSucceed(o);
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		List<ReceiveDetailsBean> list = new ArrayList<ReceiveDetailsBean>();
		try {
			list = service.find(o);
			ar.setSucceed("查询成功");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg("查询失败");
		}
		ar.setSucceed(list);
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		List<ReceiveDetailsBean> list = service.findBean(o);
		if(list.size() == 0) { 
			ar.setFailMsg("修改失败");
		} else {
			try {
				if(list.get(0).getIsApproval().equals("1")) { //已完成出库，不能修改
					ar.setFailMsg("已出库批准，不可修改");
				}
				else {
					//1、比对预修改数量machineNums与预出库数量preMachineNum大小---待定
					service.update(o);
					updateOutNums(o);
					ar.setSucceed("修改成功");
				}
			} catch(Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("修改失败");
			}
		}
		return ar;
	}
	/**
	 * 修改预出库数量
	 * @param bean
	 */
	public void updateOutNums(ReceiveDetailsBean bean){
		//查询出库任务id,根据出库任务修改数量
		List<ReceiveDetailsBean> list = service.findTaskId(bean);
		OutStorageBean osbean = new OutStorageBean();
		if(list.size() > 0){
			osbean.setTaskId(list.get(0).getId());
			osbean.setPreOutNum(bean.getMachinesNum());
			osbean.setOutPersonId(bean.getServiceId());
			oservice.updateOutNum(osbean);
		}
	}
	
	/*
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			if (o.getTaskId() != null && o.getMaModelId() != null) {
				service.update(o);
				ar.setSucceed("修改成功");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg("修改失败");
		}
		return ar;
	}
	*/

	@RequestMapping(value = "getMachinesNum", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMachinesNum(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			ReceiveDetailsBean bean = service.getMachinesNum(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", bean);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getPreMachinesNum", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPreMachinesNum(ReceiveDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			ReceiveDetailsBean bean = service.getPreMachinesNum(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", bean);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
//
//	/**
//	 * 机具领料表导出
//	 * 
//	 * @param request
//	 * @param response
//	 * @param page
//	 * @param o
//	 */
//	@RequestMapping("expExcel")
//	public void expExcel(HttpServletRequest request, HttpServletResponse response, ReceiveDetailsBean o) {
//		try {
//			String fileName = "机具领料详细";
//			List<ReceiveDetailsBean> list = service.findMaReceiveMsg(o);
//			String startTime = o.getStartTime();
//			String endTime = o.getEndTime();
//			String sb = "";
//			if (startTime != null && startTime.equals(endTime)) {
//				sb = startTime;
//			} else {
//				sb = startTime + "~" + endTime;
//			}
//			String keyWord = o.getKeyWord();
//			if (StringHelper.isNotEmpty(keyWord)) {
//				keyWord = "包含（" + keyWord + "）";
//			} else {
//				keyWord = "";
//			}
//			expOutExcel(response, list, sb + fileName + keyWord);
//		} catch (Exception e) {
//			logger.error(e.toString(), e);
//		}
//	}
//
//	private void expOutExcel(HttpServletResponse response, List<ReceiveDetailsBean> list, String filename)
//			throws Exception {
//		if (list != null) {
//			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
//			int size = list.size();
//			for (int i = 0; i < size; i++) {
//				ReceiveDetailsBean bean = list.get(i);
//				Map<String, Object> maps = outReceiveDetailsBeanToMap(i, bean);
//				results.add(maps);
//			}
//			List<String> headers = receiveDetailsHeader();
//			HSSFWorkbook workbook = POIOutputHelper.excel(results, headers, filename);
//			OutputStream out = null;
//			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//			response.addHeader("Content-Disposition",
//					"attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".xls");
//			response.setHeader("Pragma", "No-cache");
//			out = response.getOutputStream();
//			workbook.write(out);
//			out.flush();
//			out.close();
//		}
//	}
//
//	private Map<String, Object> outReceiveDetailsBeanToMap(int i, ReceiveDetailsBean bean) {
//		Map<String, Object> maps = new LinkedHashMap<String, Object>();
//		maps.put("id", i + 1);
//		maps.put("createTime", bean.getCreateTime());
//		maps.put("applyMan", bean.getApplyMan());
//		maps.put("leaseCompany", bean.getLeaseCompany());
//		maps.put("projectName", bean.getProjectName());
//		maps.put("agreementCode", bean.getAgreementCode());
//		maps.put("applyNumber", bean.getApplyNumber());
//		maps.put("leaseMan", bean.getLeaseMan());
//		maps.put("phone", bean.getPhone());
//		maps.put("remark", bean.getRemark());
//		maps.put("machinesType", bean.getMachinesType());
//		maps.put("machinesModel", bean.getMachinesModel());
//		maps.put("machinesNum", bean.getMachinesNum());
//		maps.put("actualNum", bean.getActualNum());
//		maps.put("isSure", bean.getIsSure());
//		return maps;
//	}
//
//	private List<String> receiveDetailsHeader() {
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("序号");
//		list.add("申请时间");
//		list.add("申请人");
//		list.add("租赁单位");
//		list.add("工程名称");
//		list.add("协议号");
//		list.add("租赁申请单号");
//		list.add("领料人");
//		list.add("联系方式");
//		list.add("备注");
//		list.add("机具类型");
//		list.add("机具型号");
//		list.add("机具数量");
//		list.add("已领数量");
//		list.add("是否确认");
//		return list;
//	}
}
