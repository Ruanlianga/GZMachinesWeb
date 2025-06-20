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

import com.bonus.newInput.controller.InputDetailsControllerTest;
import com.bonus.sys.beans.UserBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.StringHelper;
import com.bonus.exp.POIOutputHelper;
import com.bonus.sq.beans.MachineStatusQueryBean;
import com.bonus.sq.service.MachineStatusQueryService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/machinequery/")
public class MachineStatusQueryController extends BaseController<MachineStatusQueryBean> {

	@Autowired
	private MachineStatusQueryService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/sq/machinestatuslist";
	}

	@RequestMapping("collectList")
	public String collectList(Model model) {
		return "/sq/machinestatuscollectlist";
	}

	@RequestMapping("detailList")
	public String detailList(Model model) {
		return "/sq/machinedetail";
	}

	@RequestMapping(value = "machineStatusQuery", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes machineStatusQuery(MachineStatusQueryBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			Integer userId = UserShiroHelper.getCurrentUser().getId();
			o.setCompanyId(companyId);
			o.setUserId(userId);
			List<MachineStatusQueryBean> result = service.machineStatusQuery(o);
			float storageNum = 0;
			float leaseNum = 0;
			float scrapNum = 0;
			float repairNum = 0;
			float checkNum = 0;
			float loseNum = 0;
			float waitInputNum = 0;
			float totalStorageNum = 0;
			for (MachineStatusQueryBean bean : result) {
				storageNum += Float.parseFloat(bean.getStorageNum());
				leaseNum +=  Float.parseFloat(bean.getLeaseNum());
				repairNum +=  Float.parseFloat(bean.getRepairNum());
				checkNum +=  Float.parseFloat(bean.getCheckNum());
				scrapNum +=  Float.parseFloat(bean.getScrapNum());
				loseNum +=  Float.parseFloat(bean.getLoseNum());
				waitInputNum +=  Float.parseFloat(bean.getWaitInputNum());
				totalStorageNum +=  Float.parseFloat(bean.getTotalStorageNum());
			}
			MachineStatusQueryBean maBean = new MachineStatusQueryBean(storageNum + "", leaseNum + "",  repairNum + "", checkNum + "",scrapNum + "",
					loseNum + "",waitInputNum + "",  totalStorageNum + "");
			Map<String, Object> p = new HashMap<String, Object>();
			result.add(maBean);
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	/**
	 * 添加或移除收藏
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "addOrRemoveCollect", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addOrRemoveCollect(MachineStatusQueryBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Integer userId = UserShiroHelper.getCurrentUser().getId();
			o.setUserId(userId);
			int result = service.addOrRemoveCollect(o);
			ar.setSucceed(result);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "export", method = RequestMethod.POST)
	public void export( HttpServletRequest request, HttpServletResponse response,
			MachineStatusQueryBean o) {
		try {
			Integer userId = UserShiroHelper.getRealCurrentUser().getId();
			logger.debug("userId=" + userId);
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			o.setUserId(userId);
			List<MachineStatusQueryBean> list = service.machineStatusQuery(o);
			float storageNum = 0;
			float leaseNum = 0;
			float scrapNum = 0;
			float repairNum = 0;
			float checkNum = 0;
			float loseNum = 0;
			float waitInputNum = 0;
			float totalStorageNum = 0;
			for (MachineStatusQueryBean bean : list) {
				storageNum += Float.parseFloat(bean.getStorageNum());
				leaseNum += Float.parseFloat(bean.getLeaseNum());
				repairNum += Float.parseFloat(bean.getRepairNum());
				scrapNum += Float.parseFloat(bean.getScrapNum());
				checkNum += Float.parseFloat(bean.getCheckNum());
				loseNum += Float.parseFloat(bean.getLoseNum());
				waitInputNum += Float.parseFloat(bean.getWaitInputNum());
				totalStorageNum += Float.parseFloat(bean.getTotalStorageNum());
			}
			MachineStatusQueryBean maBean = new MachineStatusQueryBean(storageNum + "", leaseNum + "",  repairNum + "",checkNum + "",scrapNum + "",
					loseNum + "",waitInputNum + "", totalStorageNum + "");
			maBean.setMachineModel("合计");
			list.add(0, maBean);
			expOutExcel(response, list, "机具库存分布表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<MachineStatusQueryBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MachineStatusQueryBean bean = list.get(i);
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

	private Map<String, Object> outCheckToMap(int i, MachineStatusQueryBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("machineName", o.getMachineName()); 
		maps.put("machineModel", o.getMachineModel()); 
		maps.put("storageNum", o.getStorageNum()); 
		maps.put("leaseNum", o.getLeaseNum()); 
		maps.put("repairNum", o.getRepairNum()); 
		maps.put("checkNum", o.getCheckNum()); 
		maps.put("waitNum", o.getWaitInputNum()); 
		maps.put("scrapNum", o.getScrapNum()); 
		maps.put("loseNum", o.getLoseNum()); 
		maps.put("totalStorageNum", o.getTotalStorageNum()); 
		String isCount = o.getIsCount();
		if ("0".equals(isCount)) {
			isCount = "是";
		} else {
			isCount = "否";
		}
		maps.put("isCount", isCount); 
		float StorageNum = Float.parseFloat(o.getStorageNum());
		float TotalStorageNum = Float.parseFloat(o.getTotalStorageNum());
		float num;
		if(StorageNum==0){
			num=0;
		}else{
		 num = StorageNum/TotalStorageNum;
		 num =(float)(Math.round(num*100))/100;
		}
		maps.put("num", num);
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具名称");
		list.add("机具规格");
		list.add("在库数量");
		list.add("在用数量");
		list.add("在修数量");
		list.add("在检数量");
		list.add("待入库量");
		list.add("报废数量");
		list.add("丢失数量");
		list.add("总保有量");
		list.add("是否固资");
		list.add("库存预警值");
		return list;
	}
}
