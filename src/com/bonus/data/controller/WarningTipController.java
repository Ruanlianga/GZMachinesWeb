package com.bonus.data.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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

import com.bonus.data.beans.WarningTipBean;
import com.bonus.data.service.WarningTipService;
import com.bonus.exp.POIOutputHelper;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/warningTip/")
public class WarningTipController extends BaseController<WarningTipBean>{

	@Autowired 
	private WarningTipService service;
	
	@RequestMapping("tip2")
	public String index(Model model) {
		return "/warning/warninglist";
	}
	

	@RequestMapping("tip")
	public String index2(Model model) {
		return "/warning/tip";
	}
	
	@RequestMapping("findByPage")
	@ResponseBody
	public AjaxRes findByPage(Page<WarningTipBean> page,WarningTipBean o){
		AjaxRes ar = getAjaxRes();
			try {
				Page<WarningTipBean> result = service.findByPage(o, page);
				Map<String,Object> p = new HashMap<String,Object>();
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value = "findByPageTwo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findTip(Page<WarningTipBean> page,WarningTipBean o){
		AjaxRes ar = getAjaxRes();
			try {
				Page<WarningTipBean> result = service.findByPageTwo(o, page);
				Map<String,Object> p = new HashMap<String,Object>();
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value = "findWranTip", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findWranTip(WarningTipBean o){
		AjaxRes ar = getAjaxRes();
		try {
			List<WarningTipBean> result = service.findWranTip(o);
			Map<String,Object> p = new HashMap<String,Object>();
			p.put("list",result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public void export(Page<WarningTipBean> page, HttpServletRequest request, HttpServletResponse response,
			WarningTipBean o) {
		try {
			page.setPageSize(500000);
			Page<WarningTipBean> results = service.findByPage(o, page);
			List<WarningTipBean> list = results.getResults();
			
			expOutExcel(response, list, "告警提示" );
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	private void expOutExcel(HttpServletResponse response, List<WarningTipBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				WarningTipBean bean = list.get(i);
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
	private Map<String, Object> outMaLeaseBeanToMap(int i, WarningTipBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("typeName", o.getTypeName());
		maps.put("code", o.getCode());
		maps.put("warnName", o.getWarnName());
		maps.put("lat", o.getLat());
		maps.put("lon", o.getLon());
		maps.put("time", o.getTime());
		return maps;
	}


	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("类型名称"); 
		list.add("设备编号");
		list.add("告警名称");
		list.add("设备经度");
		list.add("设备纬度");
		list.add("告警时间");
		return list;
	}

	
	//告警管理-告警提示导出
	@RequestMapping(value = "tipExport", method = RequestMethod.POST)
	public void tipExport(Page<WarningTipBean> page, HttpServletRequest request, HttpServletResponse response,
			WarningTipBean o) {
		try {
			page.setPageSize(500000);
			Page<WarningTipBean> results = service.findByPage(o, page);
			List<WarningTipBean> list = results.getResults();
			if(list != null){
				expOutExcel2(response, list, "告警提示" );
			}else{
				
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	private void expOutExcel2(HttpServletResponse response, List<WarningTipBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			
			for (int i = 0; i < size; i++) {
				WarningTipBean bean = list.get(i);
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
	private Map<String, Object> outMaLeaseBeanToMap2(int i, WarningTipBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("code", o.getCode());
		maps.put("gpsCode", o.getGpsCode());
		maps.put("time", o.getTime());
		maps.put("warnName", o.getWarnName());
		return maps;
	}


	private List<String> reportHeader2() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("设备编号"); 
		list.add("GPS编号");
		list.add("告警时间");
		list.add("告警名称");
		return list;
	}

	 
	
}
