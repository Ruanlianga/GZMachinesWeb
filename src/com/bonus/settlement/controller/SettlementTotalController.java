package com.bonus.settlement.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.exp.POIOutputHelper;
import com.bonus.settlement.beans.SettlementTotalBean;
import com.bonus.settlement.service.SettlementTotalService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/settlementTotal/")
public class SettlementTotalController extends BaseController<SettlementTotalBean> {

	@Autowired
	private SettlementTotalService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/settlement/settlementTotal_list";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<SettlementTotalBean> page, SettlementTotalBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<SettlementTotalBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public void export(Page<SettlementTotalBean> page, HttpServletRequest request, HttpServletResponse response,
			SettlementTotalBean o) {
		try {
			String startTime = o.getStartTime();
			String endTime = o.getEndTime();
			String fileName = startTime + "-"+ endTime + "结算报表";
			page.setPageSize(10000);
			Page<SettlementTotalBean> results = service.findByPage(o, page);
			List<SettlementTotalBean> list = results.getResults();
			expOutExcel(response, list, fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<SettlementTotalBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				SettlementTotalBean bean = list.get(i);
				Map<String, Object> maps = outCheckToMap(i, bean);
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
		}else{
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
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

	private Map<String, Object> outCheckToMap(int i, SettlementTotalBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("agreementCode", o.getAgreementCode()); 
		maps.put("projectName", o.getProjectName()); 
		maps.put("leaseName", o.getLeaseName()); 
		maps.put("settlementDate", o.getSettlementDate()); 
		maps.put("leaseMoney", o.getLeaseMoney()); 
		maps.put("loseMoney", o.getLoseMoney()); 
		maps.put("deductionMoney", o.getDeductionMoney()); 
		maps.put("totalMoney", o.getTotalMoney()); 
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("协议号");
		list.add("工程名称");
		list.add("单位名称");
		list.add("结算日期");
		list.add("租赁费用（元）");
		list.add("丢失费用（元）");
		list.add("扣减费用（元）");
		list.add("总金额（元）");
		return list;
	}
}
