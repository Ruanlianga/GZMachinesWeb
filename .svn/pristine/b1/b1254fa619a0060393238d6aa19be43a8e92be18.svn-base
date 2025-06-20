package com.bonus.scrap.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.exp.POIOutputHelper;
import com.bonus.scrap.beans.ScrapTaskRecordBean;
import com.bonus.scrap.service.ScrapTaskRecordService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/backstage/scrapRecord/")
public class ScrapTaskRecordController extends BaseController<ScrapTaskRecordBean> {

	@Autowired
	private ScrapTaskRecordService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/scrap/scrapTaskList";
	}
/*	
	//报废资料上传
	@RequestMapping("machinesPic")
	public String machinesPic(Model model) {
		return "/scrap/updMachinesPic";
	}
	*/
	
	

	/* zwc
	 * 报废资料上传
	 */
	@RequestMapping("maImg")
	public String imgCheck(Model model, HttpServletRequest req) {
		return "/scrap/maImg";
	}
	
	/* zwc
	 * 报废资料查看
	 */
	@RequestMapping("queryMaImg")
	public String queryImgCheck(Model model, HttpServletRequest req,ScrapTaskRecordBean o) {
		String taskId= req.getParameter("taskId");
		String modelId= req.getParameter("modelId");
		o.setTaskId(taskId);
		o.setModelId(modelId);
		List<ScrapTaskRecordBean> str = service.findMaPhotoUrl(o);
		req.setAttribute("str", str.get(0).getPicUrl());
		return "/scrap/queryMaImg";
	}
	
	
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ScrapTaskRecordBean> page, ScrapTaskRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			Page<ScrapTaskRecordBean> result = service.findByPage(o, page);
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
	public void export(Page<ScrapTaskRecordBean> page, HttpServletRequest request, HttpServletResponse response,
			ScrapTaskRecordBean o) {
		try {
			String startTime = o.getStartTime();
			String endTime = o.getEndTime();
			String fileName = startTime + "-"+ endTime + "报废记录报表";
			page.setPageSize(10000);
			Page<ScrapTaskRecordBean> results = service.findByPage(o, page);
			List<ScrapTaskRecordBean> list = results.getResults();
			expOutExcel(response, list, fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<ScrapTaskRecordBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				ScrapTaskRecordBean bean = list.get(i);
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

	private Map<String, Object> outCheckToMap(int i, ScrapTaskRecordBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("typeName", o.getTypeName()); 
		maps.put("modelName", o.getModelName()); 
		maps.put("assetsNum", o.getAssetsNum());
		maps.put("deviceCode", o.getDeviceCode()); 
		maps.put("scrapTime", o.getScrapTime()); 
		maps.put("alScrapNum", o.getAlScrapNum()); 
		maps.put("remark", o.getRemark()); 
		maps.put("scrapReson", o.getScrapReson()); 
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具类型");
		list.add("机具规格");
		list.add("资产编号");
		list.add("设备编号");
		list.add("报废时间");
		list.add("报废数量");
		list.add("备注");
		list.add("报废原因");
		return list;
	}
	
	
	/***
	 * 报废资料上传
	 */
	@ResponseBody
	@RequestMapping(value = "uploadMachines", method = RequestMethod.POST)
	public AjaxRes uploadMachines(HttpServletRequest request, ScrapTaskRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.uploadMachines(request, o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}
	
	// 报废图片查看
	@RequestMapping("readMachinesPic")
	public String readMachinesPic(Model model) {
		return "/scrap/readMachinesPic";
	}
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(ScrapTaskRecordBean bean) {
		AjaxRes ar = getAjaxRes();
		ar.setSucceedMsg("success");
		return ar;
	}
	

	//机具信息图片上传
	@RequestMapping(value = "uploadMaIng")
	@ResponseBody
    public Map<String,Object> uploadManchines(@RequestParam("file")MultipartFile file,ScrapTaskRecordBean o,HttpServletRequest request){
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String fileName = file.getOriginalFilename();
                
            /*    BASE64Encoder encoder = new BASE64Encoder();  
				// 通过base64来转化图片
				String data = encoder.encode(file.getBytes());*/
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
                String saveDirectory = request.getSession().getServletContext().getRealPath("/scrapImg");
                
                
                
                 File files = new File(saveDirectory);
      		     if (!files.exists()) {
      		      files.mkdirs();
      		    }
                File dest=new File(files,fileName);
                 file.transferTo(dest);
                 String taskId = request.getParameter("taskId");
                 String maTypeId = request.getParameter("maTypeId");
     			String maModelId =  request.getParameter("maModelId");
     			o.setTaskId(taskId);
      			o.setModelId(maModelId);
                //保存文件名文件路径
                o.setPicUrl(fileName);
                List<ScrapTaskRecordBean> list = new ArrayList<ScrapTaskRecordBean>();
                int res = service.updatePhotoUrl(o);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+fileName);
                return map;
            }

        }catch (Exception e){
        	logger.error(e.toString(),e);
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }
	
}
