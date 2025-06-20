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

import com.bonus.bm.service.AuditLogsService;
import com.bonus.exp.POIOutputHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.ma.service.MachineRfidService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/machinerfid/")
public class MachineRfidController extends BaseController<MachineRfidBean> {

	@Autowired
	private MachineRfidService service;


	@Autowired
	private AuditLogsService aservice;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/machine_rfid_list";
	}



	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<MachineRfidBean> page, MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setCompanyId(companyId);
			Page<MachineRfidBean> result = service.findByPage(o, page);
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
	 * 机具设备导出
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param o
	 */
	@RequestMapping("export")
	public void expExcel(HttpServletRequest request, HttpServletResponse response, MachineRfidBean o) {
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setCompanyId(companyId);
			List<MachineRfidBean> list = service.getBaseList(o);
		
			expOutExcel(response, list, "RFID绑定记录");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	private void expOutExcel(HttpServletResponse response, List<MachineRfidBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MachineRfidBean bean = list.get(i);
				Map<String, Object> maps = outWarehousingReportBeanToMap(i, bean);
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

	private Map<String, Object> outWarehousingReportBeanToMap(int i, MachineRfidBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("comName",o.getCompanyName() ); // 
		maps.put("devName",o.getDeviceName() ); // 
		maps.put("devModel",o.getDeviceModel() ); // 
		maps.put("devUnit",o.getDeviceUnit() ); // 
		maps.put("devCode",o.getDeviceCode() ); // 
		maps.put("epcCode",o.getEpcCode() ); // 
		maps.put("creator",o.getCreator() ); // 
		maps.put("createTime",o.getCreateTime()); // 

	
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("分公司");
		list.add("机具名称");
		list.add("规格型号");
		list.add("单位");
		list.add("设备编码");
		list.add("EPC编码");
		list.add("绑定人");
		list.add("绑定时间");
		return list;
	}
	
}