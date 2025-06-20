package com.bonus.rm.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.exp.POIOutputHelper;
import com.bonus.rm.beans.ReturnMaterialTaskRecordBean;
import com.bonus.rm.service.ReturnMaterialTaskRecordService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.wf.beans.TaskRecordBean;

@Controller
@RequestMapping("/backstage/rm/taskRecord/")
public class ReturnMaterialTaskRecordController extends BaseController<ReturnMaterialTaskRecordBean> {

	@Autowired
	private ReturnMaterialTaskRecordService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/rm/taskRecordList";
	}
	
	@RequestMapping("show")
	public String show(Model model) {
		return "/rm/unFinishWorkList";
	}
	@RequestMapping("detail")
	public String openFrom(Model model) {
		return "/rm/unFinishWorkListDetails";
	}
	@RequestMapping(value = "findUnFinishContent", method = RequestMethod.POST)
    public String findUnFinishContent(@RequestBody Page<TaskRecordBean> page,HttpServletRequest request,TaskRecordBean o,Model model, HttpSession sess) {
		try {
			String a = request.getParameter("isFinish");
//			UserBean user = UserShiroHelper.getRealCurrentUser();
//			page.setUser(user);
			page.setIsFinish(a);
			page = service.findUnFinishContent(page,o);
			model.addAttribute("page", page);
		}catch(Exception e) {
		    e.printStackTrace();
		    logger.error(e.toString(), e);
		}
		return "/rm/UnFinishContentList";
    }

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ReturnMaterialTaskRecordBean> page, ReturnMaterialTaskRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			Page<ReturnMaterialTaskRecordBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findSheet", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findSheet(ReturnMaterialTaskRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ReturnMaterialTaskRecordBean> result = service.findRMSheet(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//退租单备注修改
	@RequestMapping(value = "saveMaterialRequisition", method = RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> saveMaterialRequisition(ReturnMaterialTaskRecordBean o) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ReturnMaterialTaskRecordBean> beanList = new ArrayList<ReturnMaterialTaskRecordBean>();
			String[] remarkMachines = o.getRemarkMachine().split(",");
			int res = service.updateRemarkbyTaskId(o); //更新WF_TASK_RECORD 的总备注
			beanList = service.findIdByTaskId(o); 
			
			boolean flag = true;
			for(int i = 0;i < beanList.size();i++){
				o.setId(beanList.get(i).getId());
				if(remarkMachines.length > 0){
					o.setRemarkMachine(remarkMachines[i]);
				}
				int res1 = service.updateRemarkMachinebyId(o);
				if(res1 != -1){
					flag = true;
				}else{
					flag = false;
				}
			}
			
			if(flag && res != -1){
				map.put("res", "修改成功");
			}else{
				map.put("res", "修改失败");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}
	
	//待办详情
	@RequestMapping(value = "findUnFinishContentDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<ReturnMaterialTaskRecordBean> findUnFinishContentDetails(ReturnMaterialTaskRecordBean o) {
		List<ReturnMaterialTaskRecordBean> list = new ArrayList<ReturnMaterialTaskRecordBean>();
		try {
			list = service.findUnFinishContentDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public void export(Page<ReturnMaterialTaskRecordBean> page, HttpServletRequest request, HttpServletResponse response,
			ReturnMaterialTaskRecordBean o) {
		try {
			String startTime = o.getStartTime();
			String endTime = o.getEndTime();
			String fileName = startTime + "-"+ endTime + "退料记录报表";
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
		    o.setCompanyId(companyId); 
			page.setPageSize(10000);
			Page<ReturnMaterialTaskRecordBean> results = service.findByPage(o, page);
			List<ReturnMaterialTaskRecordBean> list = results.getResults();
			expOutExcel(response, list, fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<ReturnMaterialTaskRecordBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				ReturnMaterialTaskRecordBean bean = list.get(i);
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

	private Map<String, Object> outCheckToMap(int i, ReturnMaterialTaskRecordBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("leaseName", o.getLeaseName()); 
		maps.put("projectName", o.getProjectName()); 
		maps.put("companyName", o.getCompanyName()); 
		maps.put("bsName", o.getBsName()); 
//		maps.put("agreementCode", o.getAgreementCode());
		maps.put("number", o.getNumber());
		maps.put("checker", o.getChecker());
		maps.put("maType", o.getMaType()); 
		maps.put("maModel", o.getMaModel()); 
		maps.put("deviceCode", o.getDeviceCode()); 
		maps.put("thisBackNum", o.getThisBackNum()); 
		maps.put("returnMaterialTime", o.getReturnMaterialTime()); 
		String rmStatus = o.getRmStatus();
		if("1".equals(rmStatus)   ){
			rmStatus = "合格入库";
		}else if("4".equals(rmStatus)  ||"3".equals(rmStatus) ){
			rmStatus = "待报废 ";
		}else if("7".equals(rmStatus) || "2".equals(rmStatus)  || "5".equals(rmStatus) ){
			rmStatus = "待修 ";
		}
		
		maps.put("rmStatus",rmStatus ); 
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("单位名称");
		list.add("工程名称");
		list.add("公司名称");
		list.add("领用方");
//		list.add("协议号");
		list.add("单号");
		list.add("检验员");
		list.add("机具名称");
		list.add("机具规格");
		list.add("设备编码");
		list.add("退料数量");
		list.add("退料时间");
		list.add("退料状态");
		return list;
	}
	
	
	
}
