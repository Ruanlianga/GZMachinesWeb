package com.bonus.ma.controller;

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
import com.bonus.ma.beans.pickDetailsBean;
import com.bonus.ma.service.pickDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/pickDetails/")
public class pickDetailsController extends BaseController<pickDetailsBean> {
 
	@Autowired
	private pickDetailsService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/pickDetailsList";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<pickDetailsBean> page, pickDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<pickDetailsBean> result = service.findByPage(o, page);
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
	public void export(Page<pickDetailsBean> page, HttpServletRequest request, HttpServletResponse response,
			pickDetailsBean o) {
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
 			page.setPageSize(100000);
			Page<pickDetailsBean> results = service.findByPage(o, page);
			List<pickDetailsBean> list = results.getResults();
			expOutExcel(response, list, "领料明细导出");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<pickDetailsBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				pickDetailsBean bean = list.get(i);
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

	private Map<String, Object> outCheckToMap(int i, pickDetailsBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("servicer", o.getServicer());
		maps.put("maTypeName", o.getMaTypeName()); 
		maps.put("maModelName", o.getMaModelName()); 
		maps.put("deviceCode", o.getDeviceCode()); 
		maps.put("remark", o.getRemark()); 
		maps.put("pickCode", o.getPickCode()); 
		maps.put("companyName", o.getCompanyName()); 
		maps.put("number", o.getNumber()); 
		maps.put("bsName", o.getBsName()); 
		maps.put("pickNum", o.getPickNum()); 
		maps.put("leaseName", o.getLeaseName()); 
		maps.put("operationTime", o.getOperationTime().split(" ")[0]); 
		maps.put("pickingUnit", o.getPickingUnit()); 
		maps.put("projectName", o.getProjectName()); 
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("客服代表");
		list.add("机具名称");
		list.add("机具规格");
		list.add("设备编号");
		list.add("备注");
		list.add("协议号");
		list.add("分公司名称");
		list.add("单号");
		list.add("领料方");
		list.add("领料数量");
		list.add("领料人");
		list.add("领料日期");
		list.add("领料单位");
		list.add("领料工程");
		return list;
	}
}
