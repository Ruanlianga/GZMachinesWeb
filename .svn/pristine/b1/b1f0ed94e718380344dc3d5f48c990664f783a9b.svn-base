package com.bonus.sq.controller;

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
import com.bonus.lease.beans.OutStorageCheckBean;
import com.bonus.lease.service.OutStorageCheckService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/outstoragequery/")
public class OutStorageCheckQueryController extends BaseController<OutStorageCheckBean> {

	@Autowired
	private OutStorageCheckService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/sq/outstoragechecklist";
	}

	@RequestMapping(value = "outStorageCheckQuery", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPageOne(Page<OutStorageCheckBean> page, OutStorageCheckBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<OutStorageCheckBean> result = service.outStorageCheckQuery(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public void export(Page<OutStorageCheckBean> page, HttpServletRequest request, HttpServletResponse response,
			OutStorageCheckBean o) {
		try {
			String startTime = o.getStartTime();
			String endTime = o.getEndTime();
			String fileName = startTime + "-"+ endTime + "出库检验详细表";
			page.setPageSize(10000);
			Page<OutStorageCheckBean> results = service.outStorageCheckQuery(o, page);
			List<OutStorageCheckBean> list = results.getResults();
			expOutExcel(response, list, fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<OutStorageCheckBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				OutStorageCheckBean bean = list.get(i);
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

	private Map<String, Object> outCheckToMap(int i, OutStorageCheckBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("leaseName", o.getLeaseName()); 
		maps.put("projectName", o.getProjectName()); 
		maps.put("machineName", o.getMachineName()); 
		maps.put("machineModel", o.getMachineModel()); 
		maps.put("deviceCode", o.getDeviceCode()); 
		maps.put("thisCheckNum", o.getThisCheckNum()); 
		maps.put("checker", o.getChecker()); 
		maps.put("checkTime", o.getCheckTime()); 
		String isCount = o.getIsCount();
		if ("1".equals(isCount)) {
			isCount = "是";
		} else {
			isCount = "否";
		}
		maps.put("isCount", isCount); 
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("单位名称");
		list.add("工程名称");
		list.add("机具名称");
		list.add("机具规格");
		list.add("设备编码");
		list.add("检验数量");
		list.add("检验人");
		list.add("检验时间");
		list.add("是否计数");
		return list;
	}
}
