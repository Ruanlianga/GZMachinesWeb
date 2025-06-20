package com.bonus.repair.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.ExcelUtilsPlus;
import com.bonus.exp.POIOutputHelper;
import com.bonus.lease.beans.MachineReceiveBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.repair.beans.RepairTaskRecordBean;
import com.bonus.repair.service.RepairTaskRecordService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/repairRecord/")
public class RepairTaskRecordController extends BaseController<RepairTaskRecordBean> {

	@Autowired
	private RepairTaskRecordService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/repair/repairTaskRecordList";
	}
	
	@RequestMapping("print")
	public String print(Model model) {
		return "/repair/printRepairTask";
	}
	
	@RequestMapping("upload")
	public String upload(Model model) {
		return "/repair/uploadRepairFile";
	}
	
	@RequestMapping("fileUpload")
	public String uploadExcel(Model model, HttpServletRequest req) {
		return "/repair/fileUpload";
	}


	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<RepairTaskRecordBean> page, RepairTaskRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			Page<RepairTaskRecordBean> result = service.findByPage(o, page);
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
	public void export(Page<RepairTaskRecordBean> page, HttpServletRequest request, HttpServletResponse response,
			RepairTaskRecordBean o) {
		try {
			String startTime = o.getStartTime();
			String endTime = o.getEndTime();
			String fileName = startTime + "-"+ endTime + "维修记录报表";
			page.setPageSize(10000);
			Page<RepairTaskRecordBean> results = service.findByPage(o, page);
			List<RepairTaskRecordBean> list = results.getResults();
			expOutExcel(response, list, fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<RepairTaskRecordBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				RepairTaskRecordBean bean = list.get(i);
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
	
	@RequestMapping(value = "getPrintDetails", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPrintDetails(RepairTaskRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			List<RepairTaskRecordBean> result = service.getPrintDetails(o);
			//MachineReceiveBean m = result.get(0);
			if (result == null && result.size() <= 0) {
				ar.setFailMsg("暂无领料单！");
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "saveRemark", method = RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> saveRemark(RepairTaskRecordBean o) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MachineReceiveBean> list = new ArrayList<MachineReceiveBean>();
		try {
			String[] remarkString = o.getRemarkString().split(",");
			String[] taskIdString = o.getTaskIdString().split(",");
			
			boolean flag = true;
			for(int i = 0; i < remarkString.length; i++){
				o.setRemark(remarkString[i]);
				o.setId(taskIdString[i]);
				int materialRequisition = service.saveRemark(o);
				if(materialRequisition != -1){
					flag = true;
				}else{
					flag = false;
				}
			}
			
			if(flag){
				map.put("res", "修改成功");
			}else{
				map.put("res", "修改失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
}
	
	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes importExcel(@RequestParam("file")MultipartFile file,HttpServletRequest request,RepairTaskRecordBean o) {
		AjaxRes ar = new AjaxRes();
		try {
			 if(file!=null){
                 String fileName = file.getOriginalFilename();
                 String fileNewName = UUID.randomUUID().toString();
                 String mkdirsName = "repairFile"; 
         		 String saveDirectory = "/data/gzimt/" + mkdirsName + "/"; // linux 系统路径
                 String os = System.getProperty("os.name");
                 if (os.toLowerCase().startsWith("win")) {
                 	saveDirectory = "e://GZMachinesWeb/" + mkdirsName + "/";
                 }
                 File files = new File(saveDirectory);
      		     if (!files.exists()) {
      		    	files.mkdirs();
      		     }
      		     int begin = fileName.indexOf(".");
      		     int last = fileName.length();
      		     String suffix = fileName.substring(begin,last);
      		     
      		     fileNewName =fileNewName+suffix;
      		   
      		     String filePath ="/repairFile" +"/"+fileNewName;
      		     File dest=new File(files,fileNewName);
      		     file.transferTo(dest);
      		     RepairTaskRecordBean repair = new RepairTaskRecordBean();
	     		 repair.setRepairUrl(filePath);
      		     int result = 0;
      		     if (o.getId().indexOf(",") != -1) {
					String[] ids = o.getId().split(",");
					for (int i = 0; i < ids.length; i++) {
						repair.setId(ids[i]);
						result = service.updateUrl(repair);
					}
				}else {
					o.setRepairUrl(filePath);
					result = service.updateUrl(o);
				}
      		     
      		     if(result ==1 ){
      		    	ar.setSucceedMsg("信息录入成功!");
      		     }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			ar.setFailMsg("信息录入失败!");
		}
		return ar;
	}
	

	private Map<String, Object> outCheckToMap(int i, RepairTaskRecordBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("typeName", o.getTypeName()); 
		maps.put("modelName", o.getModelName()); 
		maps.put("alrepairNum", o.getAlRepairNum());
		maps.put("deviceCode", o.getDeviceCode()); 
		maps.put("optTime", o.getOperationTime()); 
		
		String rmStatus = o.getRmStatus();
		if("5".equals(rmStatus) || "8".equals(rmStatus)){
			rmStatus = "维修合格";
		}else{
			rmStatus = "维修申请报废";
		}
		
		
		maps.put("rmStatus", rmStatus); 
		maps.put("remark", o.getRemark()); 
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具类型");
		list.add("机具规格");
		list.add("维修数量");
		list.add("设备编号");
		list.add("维修时间");
		list.add("维修结果");
		list.add("备注");
	
		return list;
	}
	
}
