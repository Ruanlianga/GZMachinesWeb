package com.bonus.ma.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
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

import com.bonus.core.StringHelper;
import com.bonus.exp.POIOutputHelper;
import com.bonus.ma.beans.MaStockBean;
import com.bonus.ma.service.MaStockService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/maStock/")
public class MaStockController extends BaseController<MaStockBean> {

	@Autowired
	private MaStockService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/maStock";
	}

	@RequestMapping("details")
	public String stock(Model model) {
		return "/ma/maStockDetails";
	}

	@RequestMapping("stockLoss")
	public String stockLoss(Model model) {
		return "/ma/maStockLoss";
	}

	@RequestMapping("stockDetails")
	public String stockDetails(Model model) {
		return "/ma/ma_stock_details";
	}

	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<MaStockBean> page, MaStockBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			List<MaStockBean> results = service.findByPage(o);
			float stockNum = 0;
			float leaseNum = 0;
			float scrapNum = 0;
			float repairNum = 0;//再修数量
			float repairedNum = 0;//修饰后的数量
			float lossNum = 0;
			float stockNums = 0;
			float stockMoney = 0;
			for (MaStockBean bean : results) {
				BigDecimal bd1 = new BigDecimal(stockNum);
				BigDecimal bd2 = new BigDecimal(StringHelper.getFloat(bean.getStockNum()));
				BigDecimal bd3 = new BigDecimal(leaseNum);
				BigDecimal bd4 = new BigDecimal(StringHelper.getFloat(bean.getLeaseNum()));
				BigDecimal bd5 = new BigDecimal(scrapNum);
				BigDecimal bd6 = new BigDecimal(StringHelper.getFloat(bean.getScrapNum()));
				BigDecimal bd7 = new BigDecimal(repairNum);
				BigDecimal bd8 = new BigDecimal(StringHelper.getFloat(bean.getRepairNum()));
				BigDecimal bd15 = new BigDecimal(repairedNum);
				BigDecimal bd16 = new BigDecimal(StringHelper.getFloat(bean.getRepairedNum()));
				BigDecimal bd9 = new BigDecimal(lossNum);
				BigDecimal bd10 = new BigDecimal(StringHelper.getFloat(bean.getLossNum()));
				BigDecimal bd11 = new BigDecimal(stockNums);
				BigDecimal bd12 = new BigDecimal(StringHelper.getFloat(bean.getStockNums()));
				BigDecimal bd13 = new BigDecimal(stockMoney);
				BigDecimal bd14 = new BigDecimal(StringHelper.getFloat(bean.getStockMoney()));
				stockNum = bd1.add(bd2).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
				leaseNum = bd3.add(bd4).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
				scrapNum = bd5.add(bd6).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
				repairNum = bd7.add(bd8).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
				repairedNum = bd15.add(bd16).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
				lossNum = bd9.add(bd10).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
				stockNums = bd11.add(bd12).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
				stockMoney = bd13.add(bd14).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
			}
			MaStockBean maBean = new MaStockBean(stockNum + "", leaseNum + "", scrapNum + "", repairNum + "",repairedNum + "",
					lossNum + "", stockNums + "", stockMoney + "");
			Map<String, Object> p = new HashMap<String, Object>();
			results.add(maBean);
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByPageTwo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPageTwo(Page<MaStockBean> page, MaStockBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MaStockBean> results = service.findByPageTwo(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findStockDetails", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes stockDetails(Page<MaStockBean> page, MaStockBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MaStockBean> results = service.findStockDetails(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findStockLoss", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findStockLoss(Page<MaStockBean> page, MaStockBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MaStockBean> results = service.findStockLoss(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", results);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping("export")
	// @RequestMapping(value = "export", method = RequestMethod.GET)
	public void export(Page<MaStockBean> page, HttpServletRequest request, HttpServletResponse response,
			MaStockBean o) {
		try {
			page.setPageSize(20000);
			Page<MaStockBean> results = service.findByPage(o, page);
			List<MaStockBean> list = results.getResults();
			float stockNum = 0;
			float leaseNum = 0;
			float scrapNum = 0;
			float repairNum = 0;
			float repairedNum = 0;
			float lossNum = 0;
			float stockNums = 0;
			float stockMoney = 0;
			for (MaStockBean bean : list) {
				stockNum += StringHelper.getFloat(bean.getStockNum());
				leaseNum += StringHelper.getFloat(bean.getLeaseNum());
				scrapNum += StringHelper.getFloat(bean.getScrapNum());
				repairNum += StringHelper.getFloat(bean.getRepairNum());
				repairedNum += StringHelper.getFloat(bean.getRepairedNum());
				lossNum += StringHelper.getFloat(bean.getLossNum());
				stockNums += StringHelper.getFloat(bean.getStockNums());
				stockMoney += StringHelper.getFloat(bean.getStockMoney());
			}
			MaStockBean maBean = new MaStockBean(stockNum + "", leaseNum + "", scrapNum + "", repairNum + "", repairedNum + "",
					lossNum + "", stockNums + "", stockMoney + "");
			maBean.setDeviceModel("合计");
			list.add(0, maBean);
			expOutExcel(response, list, "机具仓储状态表");

		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<MaStockBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MaStockBean bean = list.get(i);
				Map<String, Object> maps = outMaStockBeanToMap(i, bean);
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

	private Map<String, Object> outMaStockBeanToMap(int i, MaStockBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("deviceName", o.getDeviceName());
		maps.put("deviceModel", o.getDeviceModel());
		maps.put("stockNum", o.getStockNum());
		maps.put("leaseNum", o.getLeaseNum());
		maps.put("repairNum", o.getRepairNum());
		maps.put("repairedNum", o.getRepairedNum());
		maps.put("scrapNum", o.getScrapNum());
//		maps.put("lossNum", o.getLossNum());
		maps.put("stockNums", o.getStockNums());
//		maps.put("stockMoney", o.getStockMoney());
		String isCount = o.getIsCount();
		if ("1".equals(isCount)) {
			isCount = "是";
		} else if ("0".equals(isCount)) {
			isCount = "否";
		} else {
			isCount = "";
		}
		maps.put("isCount", isCount);
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具名称");
		list.add("规格型号");
		list.add("库存数量");
		list.add("在用数量");
		list.add("在修数量");
		list.add("待入库数量");
		list.add("报废数量");
//		list.add("丢失数量");
		list.add("总保有量");
//		list.add("总保有量资产（万元）");
		list.add("是否计数");
		return list;
	}

	@RequestMapping(value = "exportStockDetails", method = RequestMethod.POST)
	public void exportStockDetails(Page<MaStockBean> page, HttpServletRequest request, HttpServletResponse response,
			MaStockBean o) {
		try {
			page.setPageSize(100000);
			Page<MaStockBean> results = service.findStockDetails(o, page);
			List<MaStockBean> list = results.getResults();
			if (list != null) {
				for (MaStockBean bean : list) {
					String status = bean.getStatus();
					switch (status) {
					case "1":
						bean.setStatus("待通知");
						break;
					case "2":
						bean.setStatus("待检验");
						break;
					case "3":
						bean.setStatus("待打印");
						break;
					case "4":
						bean.setStatus("待入库");
						break;
					case "5":
						bean.setStatus("在库");
						break;
					case "6":
						bean.setStatus("在用");
						break;
					case "7":
						bean.setStatus("在修");
						break;
					case "8":
						bean.setStatus("在检");
						break;
					case "9":
						bean.setStatus("修饰后待入库");
						break;
					case "10":
						bean.setStatus("待报废");
						break;
					case "11":
						bean.setStatus("已报废");
						break;
					case "12":
						bean.setStatus("报废封存");
						break;
					case "13":
						bean.setStatus("在检");
						break;
					case "14":
						bean.setStatus("在审");
						break;
					case "16":
						bean.setStatus("待报废检验");
						break;
					case "17":
						bean.setStatus("待封存检验");
						break;
					default:
						bean.setStatus("无");
						break;
					}
				}
			}
			// String modelName = o.getDeviceModel();
			// String fileName = o.getDeviceName() + "—" +
			// modelName.replaceAll("/", "—");
			String status = o.getStatus();
			if ("5".equals(status)) {
				status = "在库";
			} else if ("6".equals(status)) {
				status = "在用";
			} else if ("7".equals(status)) {
				status = "修试";
			} else if ("11".equals(status)) {
				status = "报废";
			}
			// fileName += status;

			String fileName = "总保有量";

			expStockDetailsOutExcel(response, list, fileName + "机具仓储状态明细表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expStockDetailsOutExcel(HttpServletResponse response, List<MaStockBean> list, String filename)
			throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MaStockBean bean = list.get(i);
				Map<String, Object> maps = outDetailsStockBeanToMap(i, bean);
				results.add(maps);
			}

			List<String> headers = reportStockDetailsHeader();
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

	private Map<String, Object> outDetailsStockBeanToMap(int i, MaStockBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("deviceName", o.getDeviceName()); //
		maps.put("deviceModel", o.getDeviceModel()); //
		maps.put("buyPrice", o.getBuyPrice()); //
		maps.put("deviceCode", o.getDeviceCode()); //
		maps.put("qrCode", o.getQrCode()); //
		maps.put("status", o.getStatus()); //
		return maps;
	}

	private List<String> reportStockDetailsHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具名称");
		list.add("规格型号");
		list.add("原值（元）");
		list.add("设备编码");
		list.add("二维码编码");
		list.add("设备状态");
		return list;
	}

	@RequestMapping(value = "exportDetails", method = RequestMethod.POST)
	public void exportDetails(Page<MaStockBean> page, HttpServletRequest request, HttpServletResponse response,
			MaStockBean o) {
		try {
			page.setPageSize(100000);
			Page<MaStockBean> results = service.findByPageTwo(o, page);
			List<MaStockBean> list = results.getResults();
			if (list != null) {
				for (MaStockBean bean : list) {
					String status = bean.getStatus();
					switch (status) {
					case "1":
						bean.setStatus("待通知");
						break;
					case "2":
						bean.setStatus("待检验");
						break;
					case "3":
						bean.setStatus("待打印");
						break;
					case "4":
						bean.setStatus("待入库");
						break;
					case "5":
						bean.setStatus("在库");
						break;
					case "6":
						bean.setStatus("在用");
						break;
					case "7":
						bean.setStatus("在修");
						break;
					case "8":
						bean.setStatus("在检");
						break;
					case "9":
						bean.setStatus("修饰后待入库");
						break;
					case "10":
						bean.setStatus("待报废");
						break;
					case "11":
						bean.setStatus("已报废");
						break;
					case "12":
						bean.setStatus("报废封存");
						break;
					case "13":
						bean.setStatus("在检");
						break;
					case "14":
						bean.setStatus("在审");
						break;
					case "16":
						bean.setStatus("待报废检验");
						break;
					case "17":
						bean.setStatus("待封存检验");
						break;
					default:
						bean.setStatus("无");
						break;
					}
				}
			}
			String modelName = o.getDeviceModel();
			String fileName = o.getDeviceName() + "—" + modelName.replaceAll("/", "—");
			String status = o.getStatus();
			if ("5".equals(status)) {
				status = "在库";
			} else if ("6".equals(status)) {
				status = "在用";
			} else if ("7".equals(status)) {
				status = "修试";
			} else if ("11".equals(status)) {
				status = "报废";
			}
			fileName += status;
			if (StringHelper.isNotEmpty(o.getKeyWord())) {
				fileName += "（包含" + o.getKeyWord() + "）";
			}
			expDetailsOutExcel(response, list, fileName + "机具仓储状态明细表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expDetailsOutExcel(HttpServletResponse response, List<MaStockBean> list, String filename)
			throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MaStockBean bean = list.get(i);
				Map<String, Object> maps = outDetailsMaStockBeanToMap(i, bean);
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

	private Map<String, Object> outDetailsMaStockBeanToMap(int i, MaStockBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("deviceName", o.getDeviceName()); //
		maps.put("deviceModel", o.getDeviceModel()); //
		maps.put("buyPrice", o.getBuyPrice()); //
		maps.put("deviceCode", o.getDeviceCode()); //
		maps.put("qrCode", o.getQrCode()); //
		maps.put("status", o.getStatus()); //
		return maps;
	}

	private List<String> reportDetailsHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具名称");
		list.add("规格型号");
		list.add("原值（元）");
		list.add("设备编码");
		list.add("二维码编码");
		list.add("设备状态");
		return list;
	}

	@RequestMapping(value = "exportLoss", method = RequestMethod.POST)
	public void exportLoss(Page<MaStockBean> page, HttpServletRequest request, HttpServletResponse response,
			MaStockBean o) {
		try {
			page.setPageSize(100000);
			Page<MaStockBean> results = service.findStockLoss(o, page);
			List<MaStockBean> list = results.getResults();

			String modelName = o.getDeviceModel();
			String fileName = o.getDeviceName() + "—" + modelName.replaceAll("/", "—");
			String status = "丢失";

			fileName += status;
			if (StringHelper.isNotEmpty(o.getKeyWord())) {
				fileName += "（包含" + o.getKeyWord() + "）";
			}
			expLossExcel(response, list, fileName + "机具仓储状态明细表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expLossExcel(HttpServletResponse response, List<MaStockBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MaStockBean bean = list.get(i);
				Map<String, Object> maps = outLossMaStockBeanToMap(i, bean);
				results.add(maps);
			}

			List<String> headers = reportLossHeader();
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

	private Map<String, Object> outLossMaStockBeanToMap(int i, MaStockBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		o.setStatus("丢失");
		maps.put("id", i + 1);
		maps.put("deviceName", o.getDeviceName()); //
		maps.put("deviceModel", o.getDeviceModel()); //
		maps.put("buyPrice", o.getBuyPrice()); //
		maps.put("deviceCode", o.getDeviceCode()); //
		maps.put("qrCode", o.getQrCode()); //
		maps.put("status", o.getStatus()); //
		return maps;
	}

	private List<String> reportLossHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具名称");
		list.add("规格型号");
		list.add("原值（元）");
		list.add("设备编码");
		list.add("二维码编码");
		list.add("设备状态");
		return list;
	}

}
