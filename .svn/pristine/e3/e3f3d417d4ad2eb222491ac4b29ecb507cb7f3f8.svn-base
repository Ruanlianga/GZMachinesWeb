package com.bonus.ma.controller;

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

import com.bonus.core.StringHelper;
import com.bonus.exp.POIOutputHelper;
import com.bonus.ma.beans.MaLeaseBean;
import com.bonus.ma.service.MaLeaseService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/maLease/")
public class MaLeaseController extends BaseController<MaLeaseBean> {

	@Autowired
	private MaLeaseService service;

	@RequestMapping("list")
	public String list(Model model) {
		return "/ma/ma_lease_list";
	}

	@RequestMapping("leasecodelist")
	public String leasecodelist(Model model) {
		return "/ma/leasecodelist";
	}
	@RequestMapping("backcodelist")
	public String backcodelist(Model model) {
		return "/ma/backcodelist";
	}
	@RequestMapping("usecodelist")
	public String usecodelist(Model model) {
		return "/ma/usecodelist";
	}
	
	
	@RequestMapping("details")
	public String details(Model model) {
		return "/ma/ma_lease_details";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<MaLeaseBean> page, MaLeaseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			Page<MaLeaseBean> results = service.findByPage(o, page);
		
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findleaseCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findleaseCode(Page<MaLeaseBean> page, MaLeaseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
		
			Page<MaLeaseBean> results = service.findleaseCode(o, page);
			
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "finduseCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes finduseCode(Page<MaLeaseBean> page, MaLeaseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			
			Page<MaLeaseBean> results = service.finduseCode(o, page);
			
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findbackCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findbackCode(Page<MaLeaseBean> page, MaLeaseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			
			Page<MaLeaseBean> results = service.findbackCode(o, page);
			
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByPageOne", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage2(Page<MaLeaseBean> page, MaLeaseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MaLeaseBean> results = service.findByPageTwo(o, page);
			DecimalFormat df = new DecimalFormat("#0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
			List<MaLeaseBean> list = results.getResults();
			if (list != null && list.size() > 0) {
				for (MaLeaseBean bean : list) {
					float leaseNum = StringHelper.getFloat(bean.getLeaseNum());
					bean.setLeaseNum(df.format(leaseNum));
					float returnNum = StringHelper.getFloat(bean.getReturnNum());
					bean.setReturnNum(df.format(returnNum));
					float buyPrice = StringHelper.getFloat(bean.getBuyPrice());
					bean.setBuyPrice(df.format(buyPrice));
				}
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "export", method = RequestMethod.POST)
	public void export(Page<MaLeaseBean> page, HttpServletRequest request, HttpServletResponse response,
			MaLeaseBean o) {
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			page.setPageSize(200000);
			Page<MaLeaseBean> results = service.findByPage(o, page);
			DecimalFormat df = new DecimalFormat("#0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
			List<MaLeaseBean> list = results.getResults();
			if (list != null && list.size() > 0) {
				for (MaLeaseBean bean : list) {
					float leaseNum = StringHelper.getFloat(bean.getLeaseNum());
					float returnNum = StringHelper.getFloat(bean.getReturnNum());
					float buyPrice = StringHelper.getFloat(bean.getBuyPrice());
					float usingNum = StringHelper.getFloat(bean.getUsingNum());
					bean.setTotal(df.format(buyPrice));
					bean.setLeaseNum(df.format(leaseNum));
					if (usingNum < 0) {
						returnNum = leaseNum;
						usingNum =0; 
						bean.setUsingNum(usingNum+"");
						bean.setReturnNum(returnNum+"");
						bean.setUseMoney("0");
					}
					bean.setUsingNum(df.format(usingNum));
				}
			}
			String fileName = "";
			if (StringHelper.isNotEmpty(o.getKeyWord())) {
				fileName += "包含（" + o.getKeyWord() + "）";
			}
			expOutExcel(response, list, "工程机具使用情况" + fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<MaLeaseBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MaLeaseBean bean = list.get(i);
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

	private Map<String, Object> outMaLeaseBeanToMap(int i, MaLeaseBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("agreementCode", o.getAgreementCode());
		maps.put("bsName", o.getBsName());
		maps.put("unitName", o.getUnitName());
		maps.put("projectName", o.getProjectName());
		maps.put("deviceName", o.getDeviceName());
		maps.put("deviceModel", o.getDeviceModel());
		maps.put("deviceUnit", o.getDeviceUnit());
		maps.put("leaseNum", o.getLeaseNum());
		maps.put("returnNum", o.getReturnNum());
		maps.put("usingNum", o.getUsingNum());
//		maps.put("useMoney", o.getUseMoney());
//		maps.put("buyPrice", o.getBuyPrice());
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("协议号");
		list.add("领用方");
		list.add("往来单位");
		list.add("工程名称");
		list.add("机具名称");
		list.add("规格名称");
		list.add("单位");
		list.add("租赁数量");
		list.add("归还数量");
		list.add("在用数量");
//		list.add("在用总价值（元）");
//		list.add("投入金额（元）");
		return list;
	}

	@RequestMapping(value = "exportDetails", method = RequestMethod.POST)
	public void exportDetails(Page<MaLeaseBean> page, HttpServletRequest request, HttpServletResponse response,
			MaLeaseBean o) {
		try {
			page.setPageSize(20000);
			Page<MaLeaseBean> results = service.findByPageTwo(o, page);
			DecimalFormat df = new DecimalFormat("#0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
			List<MaLeaseBean> list = results.getResults();
			if (list != null && list.size() > 0) {
				for (MaLeaseBean bean : list) {
					float leaseNum = StringHelper.getFloat(bean.getLeaseNum());
					bean.setLeaseNum(df.format(leaseNum));
					float returnNum = StringHelper.getFloat(bean.getReturnNum());
					bean.setReturnNum(df.format(returnNum));
					float buyPrice = StringHelper.getFloat(bean.getBuyPrice());
					bean.setBuyPrice(df.format(buyPrice));
				}
			}
			String fileName = "";
			if (StringHelper.isNotEmpty(o.getKeyWord())) {
				fileName += "包含（" + o.getKeyWord() + "）";
			}
			expOutDetailsExcel(response, list, "工程机具使用明细" + fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutDetailsExcel(HttpServletResponse response, List<MaLeaseBean> list, String filename)
			throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MaLeaseBean bean = list.get(i);
				Map<String, Object> maps = outMaLeaseBeanDetailsToMap(i, bean);
				results.add(maps);
			}

			List<String> headers = reportDetailsHeader();
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

	private Map<String, Object> outMaLeaseBeanDetailsToMap(int i, MaLeaseBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("agreementCode", o.getAgreementCode());
		maps.put("unitName", o.getUnitName());
		maps.put("projectName", o.getProjectName());
		maps.put("typeName", o.getTypeName());
		maps.put("deviceName", o.getDeviceName());
		maps.put("deviceModel", o.getDeviceModel());
		maps.put("deviceUnit", o.getDeviceUnit());
		maps.put("leaseNum", o.getLeaseNum());
		maps.put("returnNum", o.getReturnNum());
		maps.put("buyPrice", o.getBuyPrice());
		return maps;
	}

	private List<String> reportDetailsHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("协议号");
		list.add("往来单位");
		list.add("工程名称");
		list.add("设备分类");
		list.add("物资名称");
		list.add("规格型号");
		list.add("计量单位");
		list.add("租赁数量");
		list.add("归还数量");
		list.add("投入金额（元）");
		return list;
	}

}
