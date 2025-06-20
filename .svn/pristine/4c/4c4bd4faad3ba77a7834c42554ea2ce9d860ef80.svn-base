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

import com.bonus.core.PushtoSingle;
import com.bonus.data.beans.FenceBean;
import com.bonus.data.service.FenceService;
import com.bonus.exp.POIOutputHelper;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/fence/")
public class FenceController extends BaseController<FenceBean>{

	@Autowired 
	private FenceService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/fence";
	}

	private PushtoSingle ps = new PushtoSingle();
	

	
	@RequestMapping("findNoPage")
	@ResponseBody
	public AjaxRes findNoPage(FenceBean o){
		AjaxRes ar = getAjaxRes();
		try {
			/*if("1".equals(o.getIsActive())){
				page.setPageSize(1000);
			}*/
			List<FenceBean> result = service.findNoPage(o);
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
	public AjaxRes delete(FenceBean o) {
		AjaxRes ar = getAjaxRes();
		try {
 			service.delete(o);
 			//ps.push("1", "设备信息", "您有一条设备告警信息，请注意查看");
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(FenceBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<FenceBean> list = service.find(o);
			FenceBean bean = list.get(0);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findFence", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findFence(FenceBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<FenceBean> list = service.findFence(o);
			//FenceBean bean = list.get(0);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
 

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(FenceBean o) {
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
	public AjaxRes add(FenceBean o, HttpServletRequest req) {
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
	public void export(Page<FenceBean> page, HttpServletRequest request, HttpServletResponse response,
			FenceBean o) {
		try {
			page.setPageSize(500000);
			Page<FenceBean> results = service.findByPage(o, page);
			DecimalFormat df = new DecimalFormat("#0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
			List<FenceBean> list = results.getResults();
			
			expOutExcel(response, list, "围栏管理" );
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	private void expOutExcel(HttpServletResponse response, List<FenceBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				FenceBean bean = list.get(i);
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
	
	
	private Map<String, Object> outMaLeaseBeanToMap(int i, FenceBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("name", o.getName());
		maps.put("radius", o.getRadius());
		maps.put("lon", o.getLon());
		maps.put("lat", o.getLon());
		maps.put("isOpen", o.getIsOpen());
		return maps;
	}


	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("库房名称");
		list.add("经度");
		list.add("纬度");
		list.add("围栏半径");
		list.add("状态");
		
		return list;
	}


}
