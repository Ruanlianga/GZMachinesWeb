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

import org.apache.catalina.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.exp.POIOutputHelper;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.service.OutStorageService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/outquery/")
public class OutStorageQueryController extends BaseController<OutStorageBean> {

	@Autowired
	private OutStorageService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/sq/outstoragelist";
	}
	

	@RequestMapping(value = "outStorageQuery", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes outStorageQuery(Page<OutStorageBean> page, OutStorageBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId); 
			Page<OutStorageBean> result = service.outStorageQuery(o, page);
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
	public void export(Page<OutStorageBean> page, HttpServletRequest request, HttpServletResponse response,
			OutStorageBean o) {
		try {
			page.setPageSize(10000);
			 String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId); 
			String startTime = o.getStartTime();
			String endTime = o.getEndTime();
			String fileName = startTime + "-"+ endTime + "出库详细表";
			Page<OutStorageBean> results = service.outStorageQuery(o, page);
			List<OutStorageBean> list = results.getResults();
			expOutExcel(response, list,fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<OutStorageBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				OutStorageBean bean = list.get(i);
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

	private Map<String, Object> outCheckToMap(int i, OutStorageBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("leaseCompany", o.getLeaseCompany()); 
		maps.put("leaseProject", o.getLeaseProject()); 
		maps.put("maType", o.getMaType()); 
		maps.put("maModel", o.getMaModel()); 
		maps.put("deviceCode", o.getDeviceCode()); 
		maps.put("thisOutNum", o.getThisOutNum()); 
		maps.put("outPerson", o.getOutPerson()); 
		maps.put("outTime", o.getOutTime()); 
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
		list.add("出库数量");
		list.add("客服代表");
		list.add("出库时间");
		list.add("是否计数");
		return list;
	}
	

}
