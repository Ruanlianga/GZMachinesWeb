package com.bonus.ma.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.beans.LogBean;
import com.bonus.bm.dao.LogDao;
import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.LibNumsInventoryBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.service.LibNumsInventoryService;
import com.bonus.ma.service.MachineTypeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/backstage/inventory/")
public class LibNumsInventoryController extends BaseController<LibNumsInventoryBean> {

	@Autowired
	private LibNumsInventoryService service;

	@Autowired
	private MachineTypeService mtservice;
	
	@Autowired
	LogDao logDao;
	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/libNumsInventorylist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<LibNumsInventoryBean> page, LibNumsInventoryBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<LibNumsInventoryBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(LibNumsInventoryBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<LibNumsInventoryBean> list = service.find(o);
			LibNumsInventoryBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(LibNumsInventoryBean o) {
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

	@RequestMapping(value = "addLibs", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addLibs(LibNumsInventoryBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			String num = "";
			String isCount = "";
			String inNums = o.getInNums();
			String maModelId = o.getMaModelId();
			MachineTypeBean mt = new MachineTypeBean();
			mt.setId(maModelId);
			List<MachineTypeBean> list = mtservice.find(mt);
			if (list.size() > 0) {
				String num1 = list.get(0).getNums();
				if(num1 == null){
					num = "0";
				}else{
					num = num1;
				}
				isCount = list.get(0).getIsCount();
			}
			if ("0".equals(isCount)) {
				ar.setFailMsg("该设备不可计数盘点入库！");
			} else {
				//添加日志
				LogBean logBean= new LogBean();
				logBean.setModel("盘点库存-盘赢");
				logBean.setFun("addLibs");
				logBean.setTask(o.getId());
				logBean.setTypeId(maModelId);
				LogBean inBean=logDao.findInNum(logBean);
				String description="在库数:"+inBean.getInNum()+";盘赢数:"+inNums;
				logBean.setDescription(description);
				String time = DateTimeHelper.currentDateTime();
				logBean.setTime(time);
				String userId = UserShiroHelper.getRealCurrentUser().getId()+"";
				logBean.setCreator(userId);
				logDao.insertLog(logBean);
				
				LogBean total = logDao.findTotalNum(logBean);
				float num1 = Float.parseFloat(total.getTotal());
				float num2 = Float.parseFloat(inNums);
				float num3 = num1+num2;
				String description2 =total.getTotal()+"=>"+num3;
				total.setTask(o.getId());
				total.setTaskCode("盘点库存-盘赢");
				total.setCreator(userId);
				total.setTime(time);
				total.setDescription(description2);
				logDao.insertTotalLog(total);
				
				
				addLibsNums(inNums, num, maModelId, request);
				String createTime = DateTimeHelper.getNowTime();
				o.setCreateTime(createTime);
				service.update(o);
				ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	private synchronized void addLibsNums(String inNums, String num, String maModelId,
			HttpServletRequest request) {
		BigDecimal bd1 = new BigDecimal(num);
		BigDecimal bd2 = new BigDecimal(inNums);
		Float lib = bd1.add(bd2).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		MachineTypeBean bean = new MachineTypeBean();
		bean.setId(maModelId);
		bean.setNums(lib + "");
		mtservice.update(bean);
	}

	@RequestMapping(value = "minusLibs", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes minusLibs(LibNumsInventoryBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String num = "";
			String isCount = "";
			String inNums = o.getInNums();
			String maModelId = o.getMaModelId();
			MachineTypeBean mt = new MachineTypeBean();
			mt.setId(maModelId);
			List<MachineTypeBean> list = mtservice.find(mt);
			if (list.size() > 0) {
				num = list.get(0).getNums();
				isCount = list.get(0).getIsCount();
			}
			if ("0".equals(isCount)) {
				ar.setFailMsg("该设备不可计数盘点入库！");
			} else {
				//添加日志
				LogBean logBean= new LogBean();
				logBean.setModel("盘点库存-盘亏");
				logBean.setFun("minusLibs");
				logBean.setTask(o.getId());
				logBean.setTypeId(maModelId);
				LogBean inBean=logDao.findInNum(logBean);
				String description="在库数:"+inBean.getInNum()+";盘亏数:"+inNums;
				logBean.setDescription(description);
				String time = DateTimeHelper.currentDateTime();
				logBean.setTime(time);
				String userId = UserShiroHelper.getRealCurrentUser().getId()+"";
				logBean.setCreator(userId);
				logDao.insertLog(logBean);
				
				LogBean total = logDao.findTotalNum(logBean);
				float num1 = Float.parseFloat(total.getTotal());
				float num2 = Float.parseFloat(inNums);
				float num3 = num1-num2;
				String description2 =total.getTotal()+"=>"+num3;
				total.setTask(o.getId());
				total.setTaskCode("盘点库存-盘亏");
				total.setCreator(userId);
				total.setTime(time);
				total.setDescription(description2);
				logDao.insertTotalLog(total);
				
				addMinusNums(inNums, num, maModelId);
				String createTime = DateTimeHelper.getNowTime();
				o.setCreateTime(createTime);
				service.update(o);
				ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	private synchronized void addMinusNums(String inNums, String num, String maModelId) {
		Float lib = Float.parseFloat(num) - Float.parseFloat(inNums);
		MachineTypeBean bean = new MachineTypeBean();
		bean.setId(maModelId);
		bean.setNums(lib + "");
		mtservice.update(bean);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(LibNumsInventoryBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String maModelId = o.getMaModelName();
			o.setMaModelId(maModelId);
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			String isExist = service.findByModelId(o);
			if (isExist == null) {
				service.insert(o);
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			} else {
				ar.setSucceedMsg("该类型机具已存在，请勿重复添加！");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(LibNumsInventoryBean o) {
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
	
	@RequestMapping(value = "/readDoc")
	 @ResponseBody
	 public String previewResources1(HttpServletRequest request, HttpServletResponse response,String path) {
	   String dataString=null;
	  try {
		  
		  File file = new File(request.getSession().getServletContext().getRealPath("/")+path);
	   
		  FileInputStream fis = new FileInputStream(file);
	      BufferedInputStream br = new BufferedInputStream(fis);
	      response.setHeader("Content-Disposition",
	              "inline; filename=" + URLEncoder.encode("aaa", "UTF-8"));
	      OutputStream out = response.getOutputStream();
	      byte[] buf = new byte[1024];
	      int len = 0;
	      while ((len = br.read(buf)) != -1)
	      out.write(buf, 0, len);
	      
	      dataString= getImageString(buf);
	      br.close();
	      out.close();
	  } catch (Exception e) {
	   logger.error(e.toString(), e);
	  }
	  return dataString;
	 }
	
	public static String getImageString(byte[] data) throws IOException {
		  BASE64Encoder encoder = new BASE64Encoder();
		   return data != null ? encoder.encode(data) : "";
	}
}
