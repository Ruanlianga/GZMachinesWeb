package com.bonus.ma.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.InventoryRecordBean;
import com.bonus.ma.service.InventoryRecordService;
import com.bonus.ma.service.MachineTypeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/inventoryRecord/")
public class InventoryRecordController extends BaseController<InventoryRecordBean> {

	/*@Autowired
	private LibNumsInventoryService service;*/
	
	@Autowired
	private InventoryRecordService service;

	@Autowired
	private MachineTypeService mtservice;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/InventoryRecordlist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<InventoryRecordBean> page, InventoryRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<InventoryRecordBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	/*
	 * 盘盈插入wf_inventory_record
	 */
	@RequestMapping(value = "addLibsRecord", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addLibsRecord(InventoryRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			String inventoryTime = DateTimeHelper.getNowTime();
			o.setInventoryTime(inventoryTime);
		 	o.setInventoryPerson(user.getName());
			service.insert(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	/*
	 * 盘盈插入wf_inventory_record
	 */
	@RequestMapping(value = "reduceLibsRecord", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes reduceLibsRecord(InventoryRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			 String companyId = user.getCompanyId();
			  o.setOrgId(companyId);
			String inventoryTime = DateTimeHelper.getNowTime();
			o.setInventoryTime(inventoryTime);
		 	o.setInventoryPerson(user.getName());
			service.insert(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	/*@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(InventoryRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<InventoryRecordBean> list = service.find(o);
			InventoryRecordBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(InventoryRecordBean o) {
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
	public AjaxRes addLibs(InventoryRecordBean o, HttpServletRequest request) {
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
	public AjaxRes minusLibs(InventoryRecordBean o) {
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
	public AjaxRes add(InventoryRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String maModelId = o.getMaModelName();
			o.setMaModelId(maModelId);
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
	public AjaxRes del(InventoryRecordBean o) {
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
	}*/
}
