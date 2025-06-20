package com.bonus.ma.controller;

import java.io.OutputStream;
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

import com.bonus.bm.beans.CompanyBean;
import com.bonus.bm.service.AuditLogsService;
import com.bonus.core.StringHelper;
import com.bonus.exp.POIOutputHelper;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.service.MachineTypeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.beans.ZNode;

@Controller
@RequestMapping("/backstage/machineType/")
public class MachineTypeController extends BaseController<MachineTypeBean> {

	@Autowired
	private MachineTypeService service;

	@Autowired
	private AuditLogsService aservice;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/machineTypelist";
	}

	@RequestMapping("details")
	public String details(Model model) {
		return "/ma/typeDetailslist";
	}

	@RequestMapping("maTypeTree")
	public String maTypeTree(Model model) {
		return "/ma/maTypeTree";
	}
	@RequestMapping("maTypeTree2")
	public String maTypeTree2(Model model) {
		return "/ma/maTypeTree2";
	}

	@RequestMapping("maModelTree")
	public String maModelTree(Model model) {
		return "/ma/maModelTree";
	}
	
	@RequestMapping("maModelTree2")
	public String maModelTree2(Model model) {
		return "/ma/maModelTree2";
	}

	@RequestMapping("uploadFileUrl")
	public String uploadFile(Model model) {
		return "/ma/updMaTypeFile";
	}

	@RequestMapping("warnNotice")
	public String warnNotice(Model model) {
		return "/sys/warnNotice";
	}

	@RequestMapping("warnPage")
	public String warnPage(Model model) {
		return "/sys/warnPage";
	}

	@RequestMapping(value = "findStore", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findStore(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineTypeBean> result = service.findStore(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<MachineTypeBean> page, MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setCompanyId(companyId);
			Page<MachineTypeBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAll() {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = service.getRoleBeans();
			if (list != null) {
				for (ZNode bean : list) {
					bean.setIcon(getRequest().getContextPath() + "/static/css/sys/images/user_group.gif");
				}
			}
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineTypeBean> list = service.find(o);
			MachineTypeBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(MachineTypeBean o) {
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
	
	@RequestMapping(value = "updateWarnValue", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateWarnValue(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.updateWarnValue(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(MachineTypeBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		try {
			MachineTypeBean bean = service.findLastId();
			o.setId(Integer.parseInt(bean.getId()) + 1 + "");
			service.insert(o);
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setCompanyId(companyId);
			service.insertOrgRelation(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "testData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes testData(MachineTypeBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineTypeBean> list = service.findTestData();
			for(MachineTypeBean ma : list){
				MachineTypeBean type = new MachineTypeBean();
				type = service.getIdByThreeName(ma);
				if(type!=null){
					String id = ma.getId();
					type.setId(id);
					service.updateTestDataById(type);
				}
				
			}
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "zulinTest", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes zulinTest(MachineTypeBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		int a = 4;
		String res = "";
		try {
			List<MachineTypeBean> list = service.findZulinTest();
			for(MachineTypeBean ma : list){
				String num = ma.getFirstName().substring(3);
				String gzNum = "SQ" + num;
				for (int i = 0; i < a; i++) {
					if (i == 0) {
						MachineTypeBean type = new MachineTypeBean();
						type.setFirstName(gzNum);
						service.insetOne(type);
					}else if(i == 1){
						MachineTypeBean type1 = new MachineTypeBean();
						type1.setFirstName(ma.getFirstName());
						service.insetTwo(type1);
						res = type1.getId();
					}else if(i == 2){
						o.setId(res);
						service.insetThree(o);
					}else{
						o.setId(res);
						service.insetThree(o);
					}
					
				}
			}
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	// 新购只记数量的机具
	@RequestMapping(value = "addCountsMachines", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addCountsMachines(MachineTypeBean o) {
		// UserBean user = UserShiroHelper.getRealCurrentUser();
		AjaxRes ar = getAjaxRes();
		try {
			service.updateNums(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}

	@RequestMapping(value = "getMainTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMainTree(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setCompanyId(companyId);
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

	@RequestMapping(value = "treeInsert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes treeInsert(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			MachineTypeBean bean = new MachineTypeBean();
			bean = service.findLastId();
			o.setId(Integer.parseInt(bean.getId()) + 1 + "");
			service.treeInsert(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "treeUpdate", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes treeUpdate(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int i = service.treeUpdate(o);
			if (i > 0) {
				ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
			} else {
				ar.setSucceedMsg(GlobalConst.UPDATE_FAIL);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "treeDelete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes treeDelete(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int i = service.treeDelete(o);
			if (i > 0) {
				ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
			} else {
				ar.setSucceedMsg(GlobalConst.DEL_FAIL);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findFirstName", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findFirstName(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineTypeBean> list = service.findFirstName(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findStoreDetails", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findStoreDetails(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineTypeBean> result = service.findStoreDetails(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findDetails", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findDetails(Page<MachineTypeBean> page, MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setCompanyId(companyId);
			Page<MachineTypeBean> result = service.findDetails(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findModel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findModel(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			List<MachineTypeBean> list = service.findModel(o);
			MachineTypeBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "updateModel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateModel(MachineTypeBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
	//	String userId = UserShiroHelper.getCurrentUser().getId() + "";
		try {
	//		String typeId = o.getId();
			service.updateModel(o);
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			service.updateOrgModel(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
	//		MachineTypeBean bean = service.findTopClass(o);
			/*aservice.addAuditLogs(request, userId, "/backstage/machineType/update", "机具类型管理", "修改", "修改机具 '"
					+ bean.getParentName() + "-" + bean.getName() + "'后,库存为" + bean.getNums() + "," + o.getNums()+",总保有量为"+bean.getAllNums()+","+o.getAllNums(), "1");*/
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
	//		MachineTypeBean bean = service.findTopClass(o);
			/*aservice.addAuditLogs(request, userId, "/backstage/machineType/update", "机具类型管理", "修改",
					"修改机具 '" + bean.getParentName() + "-" + bean.getName() + "'后,库存为" + o.getNums(), "-1");*/
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineTypeBean> list = service.findChilds(o);
			if (list != null && list.size() > 0) {
				ar.setFailMsg(GlobalConst.DEL_PARENT_FAIL);
				return ar;
			}
			service.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delete(MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			switch (o.getLevel()) {
			case "1":
			case "2":
				List<MachineTypeBean> maList = service.findChilds(o);
				if (maList != null && maList.size() > 0) {
					ar.setFailMsg(GlobalConst.DEL_PARENT_FAIL);
					return ar;
				} else {
					service.delete(o);
				}
				break;
			case "3":
				service.delete(o);
			
				List<MachineTypeBean> masList = service.findChilds(o);
				if (masList != null && masList.size() > 0) {
					for (MachineTypeBean maBean : masList) {
						service.delete(maBean);
					}
				}
				break;
			case "4":
				List<MachineTypeBean> lists = service.findChilds(o);
				if (lists != null && lists.size() > 0) {
					ar.setFailMsg(GlobalConst.DEL_PARENT_FAIL);
					return ar;
				}
				service.delete(o);
				String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
				o.setCompanyId(companyId);
				service.deleteOrgRelation(o);
				break;
			default:
				service.delete(o);
				break;
			}
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "maTypeTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes unitTree(MachineTypeBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		try {
			String isOpen = req.getParameter("isOpen");
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			List<ZNode> list = service.maTypeTree(o);
			if (list != null && list.size() > 0) {
				if (StringHelper.isNotEmpty(isOpen)) {
					for (ZNode zNode : list) {
						zNode.setOpen(true);
					}
				}
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//查询一级下拉选
	@RequestMapping(value = "getTypeNameList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getTypeNameList() {
		AjaxRes ar = getAjaxRes();
		MachineTypeBean bean = new MachineTypeBean();
		String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
		bean.setCompanyId(companyId);
		try {
			List<MachineTypeBean> list = service.typeNameList(bean);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//查询二级下拉选
	@RequestMapping(value = "getNameList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getNameList(MachineTypeBean bean) {
		AjaxRes ar = getAjaxRes();
		String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
		bean.setCompanyId(companyId);
		try {
			List<MachineTypeBean> list = service.nameList(bean);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "maModelTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes maModelTree(MachineTypeBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		try {
			String isOpen = req.getParameter("isOpen");
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setCompanyId(companyId);
			if("0".equals(o.getParentId())){
				o.setParentId("");
			} 
			List<ZNode> list = service.maModelTree(o);
			if (list != null && list.size() > 0) {
				if (StringHelper.isNotEmpty(isOpen)) {
					for (ZNode zNode : list) {
						zNode.setOpen(true);
					}
				}
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes uploadFile(HttpServletRequest request, MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.uploadFile(request, o);
			ar.setSucceedMsg(GlobalConst.UPLOAD_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPLOAD_FAIL);
		}
		return ar;
	}

	// 预警设备查询
	@RequestMapping(value = "findWarnModel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findWarnModel(Page<MachineTypeBean> page, MachineTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MachineTypeBean> result = service.findWarnModel(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	/**
	 * 机具类型表导出
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param o
	 */
	@RequestMapping("expExcel")
	public void expExcel(HttpServletRequest request, HttpServletResponse response, MachineTypeBean o) {
		try {
			String keyWord = request.getParameter("keyWord");
			o.setKeyWord(keyWord);
			List<MachineTypeBean> list = service.findMaTypeMsg(o);
		/*	String name = "全部";
			if (StringHelper.isNotEmpty(o.getKeyWord())) {
				name = "包含" + o.getKeyWord();
			}*/
			expOutExcel(response, list, "机具类型详细表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<MachineTypeBean> list, String filename)
			throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MachineTypeBean bean = list.get(i);
				Map<String, Object> maps = outMachineTypeBeanToMap(i, bean);
				results.add(maps);
			}

			List<String> headers = machineTypeHeader();
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

	private Map<String, Object> outMachineTypeBeanToMap(int i, MachineTypeBean bean) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("firstName", bean.getFirstName());
		maps.put("secondName", bean.getSecondName());
		maps.put("parentName", bean.getParentName());
		maps.put("name", bean.getName());
		maps.put("weight", bean.getWeight());
		maps.put("unit", bean.getUnit());
		maps.put("nums", bean.getNums());
		//maps.put("allNums", bean.getAllNums());
		maps.put("buyPrice", bean.getBuyPrice());
		maps.put("leasePrice", bean.getLeasePrice());
		maps.put("payPrice", bean.getPayPrice());
		maps.put("isTest", bean.getIsTest());
		maps.put("keeper", bean.getKeeper());
		return maps;
	}

	private List<String> machineTypeHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("施工机具类型");
		list.add("设备分类");
		list.add("机具类型");//物资名称
		list.add("规格型号");
		list.add("重量");
		list.add("计量单位");
		list.add("数量");//库存数
		/*list.add("总保有量");*/
		list.add("原值(元)");
		list.add("租赁价格(元)");
		list.add("丢失赔偿价格(元)");
		list.add("是否只计数");//是否需要实验
		list.add("库管员");
		return list;
	}

}
