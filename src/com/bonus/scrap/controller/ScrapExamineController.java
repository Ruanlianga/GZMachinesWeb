package com.bonus.scrap.controller;

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
import com.bonus.ma.beans.MachineBean;
import com.bonus.scrap.beans.ScrapExamineBean;
import com.bonus.scrap.beans.ScrapTaskRecordBean;
import com.bonus.scrap.service.ScrapExamineService;
//import com.bonus.scrap.service.ScrapExamineService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/scrapExamine/")
public class ScrapExamineController extends BaseController<ScrapExamineBean> {

	@Autowired
	private ScrapExamineService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/scrap/scrapExamineList";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ScrapExamineBean> page, ScrapExamineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<ScrapExamineBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "export" )
	public void export(Page<ScrapExamineBean> page, HttpServletRequest request, HttpServletResponse response,
			ScrapExamineBean o) {
		try {
			page.setPageSize(10000);
			Page<ScrapExamineBean> results = service.findByPage(o, page);
			List<ScrapExamineBean> list = results.getResults();
			expOutExcel(response, list, "报废记录明细报表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<ScrapExamineBean> list, String filename) throws Exception {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			if (list != null) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				ScrapExamineBean bean = list.get(i);
				Map<String, Object> maps = outScrapBeantoMap(i, bean);
				results.add(maps);
				
			}
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
	private Map<String, Object> outScrapBeantoMap(int i, ScrapExamineBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("ScrapCode", o.getScrapCode()); // 类型名称
		maps.put("type", o.getType()); // 规格名称
		maps.put("Machine", o.getMachine()); // 采购价格
		maps.put("MachineCode", o.getMachineCode()); // 固资编号
		maps.put("ScrapNum", o.getScrapNum());//产权部门
		maps.put("Creator",o.getCreator());//使用部门
		maps.put("CreateTime", o.getCreateTime());//使用年限
		maps.put("Auditor", o.getAuditor());//制单日期
		maps.put("AuditTime", o.getAuditTime());//制单日期
		/*
		 * maps.put("typeName", o.getTypeName()); maps.put("counts", o.getCounts());
		 * maps.put("buyMoney", o.getBuyMoney()); maps.put("buyTime", o.getBuyTime());
		 * maps.put("acceptTime", o.getAcceptTime());
		 */
		return maps;
	}
		
	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("单号");
		list.add("设备名称");
		list.add("规格型号");
		list.add("设备编号");
		list.add("报废数量");
		list.add("申请人");
		list.add("申请时间");
		list.add("审核人");
		list.add("审核时间");
		
		return list;
	}	
	

	}	
	
	
	
	
	
	
	
	















