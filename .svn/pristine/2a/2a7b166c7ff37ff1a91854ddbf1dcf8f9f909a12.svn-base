package com.bonus.plan.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.data.beans.WarningTipBean;
import com.bonus.exp.POIOutputHelper;
import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.plan.beans.PlanApplyBean;
import com.bonus.plan.beans.PlanDataDetailBean;
import com.bonus.plan.beans.PlanDevBean;
import com.bonus.plan.service.PlanApplicationService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

/**
 * 计划申请 控制层
 * @author xj
 */
@Controller
@RequestMapping("/backstage/planApplication/")
public class PlanApplicationController  extends BaseController<PlanApplyBean> {
	
	@Autowired
	private PlanApplicationService service;
	
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/demandPlan/apply_plan_list";
	}
	
	@RequestMapping("showProcess")
	public String showProcess(Model model) {
		return "/demandPlan/child/showProcess";
	}
	
	@RequestMapping("addForm")
	public String addForm(Model model) {
		return "/demandPlan/child/apply_plan_form";
	}
	
	@RequestMapping("detail")
	public String detail(Model model) {
		return "/demandPlan/child/apply_plan_detail";
	}
	

	
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<PlanApplyBean> page, PlanApplyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<PlanApplyBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 获取设备树 集合
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "getDevTreeList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getDevTreeList( PlanDevBean o) {
			return service.getDevTreeList(o);
	}
	/**
	 * 获取设备树 集合
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "getProList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getProList(PlanDevBean o) {
			return service.getProList(o);
	}
	
	/**
	 * 新增计划
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "addPlan", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addPlan(@RequestBody PlanApplyBean o) {
		return service.addPlan(o);
	}
	
	
	
	/**
	 * 修改计划
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "updatePlan", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updatePlan(@RequestBody PlanApplyBean o) {
		return service.updatePlan(o);
	}
	
	
	
	/**
	 * 查看详情详情
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "getPlanDetails", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPlanDetails( PlanApplyBean o) {
		return service.getPlanDetails(o);
	}
	/**
	 * 导出详情
	 * @param o
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "exportDetail", method = RequestMethod.POST)
	public void exportDetail(PlanApplyBean o, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<PlanDataDetailBean> results = service.getDetailsList(o);
			exportDetails(response, results, "机具明细" );
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	
	
	/**
	 * 导出列表
	 * @param page
	 * @param request
	 * @param response
	 * @param o
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public void export(Page<PlanApplyBean> page, HttpServletRequest request, HttpServletResponse response,
			PlanApplyBean o) {
		try {
			page.setPageSize(500000);
			Page<PlanApplyBean> results = service.findByPage(o, page);
			List<PlanApplyBean> list = results.getResults();
			
			expOutExcel(response, list, "需求计划申请" );
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	
	
	
	private void exportDetails(HttpServletResponse response, List<PlanDataDetailBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				PlanDataDetailBean bean = list.get(i);
				Map<String, Object> maps = outMaLeaseBeanToMap2(i, bean);
				results.add(maps);
			}

			List<String> headers = reportHeader2();
			HSSFWorkbook workbook = POIOutputHelper.excel(results, headers, filename);
			OutputStream out = null;
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".xls");
			response.setHeader("Pragma", "No-cache");
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		}
	}
	
	private void expOutExcel(HttpServletResponse response, List<PlanApplyBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				PlanApplyBean bean = list.get(i);
				Map<String, Object> maps = outMaLeaseBeanToMap(i, bean);
				results.add(maps);
			}

			List<String> headers = reportHeader();
			HSSFWorkbook workbook = POIOutputHelper.excel(results, headers, filename);
			OutputStream out = null;
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".xls");
			response.setHeader("Pragma", "No-cache");
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		}
	}
	
	
	private Map<String, Object> outMaLeaseBeanToMap2(int i, PlanDataDetailBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("type", o.getType());
		maps.put("name", o.getTypeName());
		maps.put("module", o.getModule());
		maps.put("unit", o.getUnit());
		maps.put("needNum", o.getNeedNum());
		maps.put("needDay", o.getTimes());
		maps.put("remarks", o.getRemarks());
		return maps;
	}
	
	
	private Map<String, Object> outMaLeaseBeanToMap(int i, PlanApplyBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("code", o.getCode());
		maps.put("proName", o.getProName());
		maps.put("needTime", o.getNeedTime());
		maps.put("creater", o.getCreator());
		maps.put("createTime", o.getCreateTime());
		maps.put("projectPart", o.getProjectPart());
		maps.put("projectContent", o.getProjectContent());
		maps.put("remarks", o.getRemark());
		maps.put("ststus",getStatus(o.getStatusType(),o.getStatus()));
		return maps;
	}

		/**
		 * 获取审核状态
		 * @param statusType
		 * @param status
		 * @return
		 */
		private String  getStatus(String statusType,String status) {
			try {
				String company ="";
				if("1".equals(statusType)) {
					 return "审核通过";
				}else if("2".equals(statusType)) {
					company="分公司";
				}else if("3".equals(statusType)) {
					company="项目管理中心";
				}else if("4".equals(statusType)) {
					company="机具公司";
				}
				if("1".equals(status)) {
					return "待"+company+"审核";
					
				}else if("2".equals(status)) {
					return "审核通过";
					
				}else if("3".equals(status)) {
					return company+"审核驳回";
				}
				}catch (Exception e) {
					logger.error(e.toString(), e);
				}
			 return "待审核";
			
		}
	
		private List<String> reportHeader2() {
			ArrayList<String> list = new ArrayList<String>();
			list.add("序号");
			list.add("物机类型"); 
			list.add("物机名称");
			list.add("规格型号");
			list.add("单位");
			list.add("需用量");
			list.add("需用天数");
			list.add("备注");
			return list;
		}
	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("计划编号"); 
		list.add("工程名称");
		list.add("需用日期");
		list.add("申请人");
		list.add("申请时间");
		list.add("项目部分");
		list.add("施工地点");
		list.add("备注");
		list.add("审核状态");
		return list;
	}

}
