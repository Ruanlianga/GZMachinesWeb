package com.bonus.index.controller;

import java.io.IOException;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.exp.POIOutputHelper;
import com.bonus.index.beans.PositionBean;
import com.bonus.index.service.PositionService;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.beans.ZNode;

@Controller
@RequestMapping("/backstage/position/")
public class PositionController extends BaseController<PositionBean>{

	@Autowired 
	private PositionService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/position";
	}
	
	@RequestMapping("deviceDetail")
	public String deviceDetail(Model model) {
		return "/sys/deviceDetail";
	}
	@RequestMapping("historyGps")
	public String historyGps(Model model) {
		return "/index/historyGps";
	}
	
	@RequestMapping("findOldGpsData")
	@ResponseBody
	public AjaxRes findOldGpsData(Page<PositionBean> page,PositionBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<PositionBean> station = service.findOldGpsDatas(o,page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", station);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping("findByPage")
	@ResponseBody
	public AjaxRes findByPage(PositionBean o){
		AjaxRes ar = getAjaxRes();
		try {
			List<PositionBean> result = service.findNoPage(o);
			Map<String,Object> p = new HashMap<String,Object>();
			p.put("list",result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping("findBindPosition")
	@ResponseBody
	public AjaxRes findBindPosition(PositionBean o){
		AjaxRes ar = getAjaxRes();
		try {
			List<PositionBean> list = service.findBindPosition(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping("findDeviceDetail")
	@ResponseBody
	public AjaxRes findDeviceDetail(Page<PositionBean> page,PositionBean o){
		AjaxRes ar = getAjaxRes();
		String status = o.getStatus();
		Page<PositionBean> result = null;
			try {
				if("-1".equals(status)){
					result = service.findDeviceDetail(o, page);
				}else if("0".equals(status)){
					o.setDeviceType("0");
					result = service.findInLibDevice(o, page);
				}else if("1".equals(status)){
					o.setDeviceType("1");
					result = service.findInLibDevice(o, page);
				}else if("2".equals(status)){
					o.setDeviceType("7");
					result = service.findInLibDevice(o, page);
				}else{
					
				}
				Map<String,Object> p = new HashMap<String,Object>();
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delete(PositionBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "gpsCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes gpsCode(PositionBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			 PositionBean gps = new PositionBean();
			 gps.setNewgpsCode(o.getNewgpsCode());
		     String list=service.gpsCode(gps);
				System.err.println(list);
				if(list==null || list=="") {
					System.out.println("GPS不存在");
				}else {
					ar.setSucceed(list);
				}
				
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "gpsCodeBind", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes gpsCodeBind(PositionBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			 PositionBean gps = new PositionBean();
			 gps.setGpsCode(o.getGpsCode());
		     String list=service.gpsCodeBind(gps);
				System.err.println(list);
				if(list==null || list=="") {
					ar.setFailMsg("编号不存在");
				}else {
					ar.setSucceed(list);
				}
				
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(PositionBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<PositionBean> list = service.find(o);
			PositionBean bean = list.get(0);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(PositionBean o) {
		AjaxRes ar = getAjaxRes();
 
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(PositionBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		try {
			service.insert(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	

	@RequestMapping(value = "export", method = RequestMethod.POST)
	public void export(Page<PositionBean> page, HttpServletRequest request, HttpServletResponse response,
			PositionBean o) {
		try {
			page.setPageSize(500000);
			List<PositionBean> list = service.findNoPage(o);
			DecimalFormat df = new DecimalFormat("#0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
			/*List<PositionBean> list = results.getResults();*/
			
			expOutExcel(response, list, "定位装置管理" );
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	private void expOutExcel(HttpServletResponse response, List<PositionBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				PositionBean bean = list.get(i);
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
	
	private Map<String, Object> outMaLeaseBeanToMap(int i, PositionBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("code", o.getCode());
		if(o.getStatus().equals("1")  ){
			maps.put("status","已绑定" );
		}else{
			maps.put("status","未绑定" );
		}
		return maps;
	}


	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("GPS编号");
		list.add("GPS状态");
		return list;
	}
	
	@RequestMapping(value = "getMainTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMainTree(PositionBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = service.getMainTree(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "getMainSpecialTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMainSpecialTree(PositionBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = service.getMainSpecialTree(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
    
    @RequestMapping(value = "exportOldGPS", method = RequestMethod.POST)
	public void exportOldGPS(Page<PositionBean> page, HttpServletRequest request, HttpServletResponse response,String codes,
			PositionBean o) {
		try {
			System.out.print(codes);
//			o.setCode(codes);
			String startTime = o.getStartTime();
			String endTime = o.getEndTime();
			String fileName = startTime +"-"+endTime + "历史定位记录";
			page.setPageSize(10000);
			Page<PositionBean> results = service.findOldGpsDatas(o, page);
			List<PositionBean> list = results.getResults();
			expOutExcelOldGPS(response, list, fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

    private void expOutExcelOldGPS(HttpServletResponse response, List<PositionBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				PositionBean bean = list.get(i);
				Map<String, Object> maps = outCheckToMap(i, bean);
				results.add(maps);
			}
			List<String> headers = reportHeaderGps();
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

    private Map<String, Object> outCheckToMap(int i, PositionBean o) {
    	String posTypeInfo = null;
    	if("lbs".equals(o.getPosType()) || "LBS".equals(o.getPosType())) {
			posTypeInfo = "基站定位";
		}else if("GPS".equals(o.getPosType()) || "gps".equals(o.getPosType())) {
			posTypeInfo = "卫星定位";
		}else if("WIFI".equals(o.getPosType())|| "wifi".equals(o.getPosType())) {
			posTypeInfo = "WIFI定位";
		}else if("BEACON".equals(o.getPosType()) || "beacon".equals(o.getPosType())) {
			posTypeInfo = "蓝牙定位";
		}else{
			posTypeInfo = "";
		}
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("code", o.getCode()); 
		maps.put("typeName", o.getTypeName()); 
		maps.put("deviceModel", o.getDeviceModel()); 
		maps.put("deviceCode", o.getDeviceCode());
		maps.put("lon", o.getLon()); 
		maps.put("lat", o.getLat()); 
		maps.put("upTime", o.getUpTime()); 
		maps.put("posTypeInfo", posTypeInfo); 
		maps.put("address", o.getAddress()); 
		maps.put("useUnit", o.getUseUnit()); 
		maps.put("useTime", o.getUseTime()); 
		maps.put("proName", o.getProName()); 
		return maps;
	}
    

	private List<String> reportHeaderGps() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("GPS编号");
		list.add("设备类型");
		list.add("规格型号");
		list.add("设备编码");
		list.add("经度");
		list.add("纬度");
		list.add("上传时间");
		list.add("定位类型");
		list.add("地址");
		list.add("领用单位");
		list.add("领用时间");
		list.add("当前所在项目");
		return list;
	}
	
    
    @RequestMapping(value = "getTypeName", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getTypeName() {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = service.getTypeName();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

    @RequestMapping(value = "getDeviceModel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getDeviceModel() {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = service.getDeviceModel();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

    @RequestMapping(value = "getDeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getDeviceCode() {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = service.getDeviceCode();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
    
    @RequestMapping(value = "getCodes", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCode() {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyBean> list = service.getCode();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

    
}
