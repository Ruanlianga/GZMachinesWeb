package com.bonus.ma.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
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

import com.bonus.bm.service.AuditLogsService;
import com.bonus.core.DateTimeHelper;
import com.bonus.core.ExcelUtilsPlus;
//import com.bonus.core.ExcelUtils1;
import com.bonus.core.StringHelper;
import com.bonus.exp.POIOutputHelper;
import com.bonus.exp.POIOutputHelper1;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.beans.QRCodeBean;
import com.bonus.ma.service.MachineService;
import com.bonus.ma.service.MachineTypeService;
import com.bonus.ma.service.QRCodeService;
import com.bonus.multiple.beans.MachineInputDetailsBean;
import com.bonus.multiple.service.MachineInputDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

@Controller
@RequestMapping("/backstage/machine/")
public class MachineController extends BaseController<MachineBean> {

	@Autowired
	private MachineService service;

	@Autowired
	private QRCodeService qrService;

	@Autowired
	private MachineTypeService mtservice;

	@Autowired
	private MachineInputDetailsService miService;

	@Autowired
	private AuditLogsService aservice;




	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/machinelist";
	}


	@RequestMapping("listSpecial")
	public String listSpecial(Model model) {
		return "/ma/machineSpecialList";
	}


	@RequestMapping("flowlist")
	public String flowlist(Model model) {
		return "/ma/machineFlowlist";
	}
	@RequestMapping("openFrom")
	public String openFrom(Model model) {
		return "/ma/machineform";
	}

	@RequestMapping("openflowForm")
	public String openflowFrom(Model model) {
		return "/ma/machineFlowform";
	}

	@RequestMapping("fileOptView")
	public String fileOptView(Model model) {
		return "/ma/fileOptView";
	}
	@RequestMapping("machineLifeCycle")
	public String machineLifeCycle(Model model) {
		return "/ma/machineLifeCycle";
	}
	@RequestMapping("machineLifeCycleFlow")
	public String machineLifeCycleFlow(Model model) {
		return "/ma/machineLifeCycleFlow";
	}
	// 二维码查看
	@RequestMapping("readQRCode")
	public String readQRCode(Model model) {
		return "/ma/readQRCode";
	}
	@RequestMapping("findhistory")
	public String findhistory(Model model) {
		return "/ma/lifehistory";
	}
	// 固资编号
	@RequestMapping("toFixedAssets")
	public String toFixedAssets(Model model) {
		return "/ma/toFixedAssets";
	}

	// 资产属性
	@RequestMapping("buyCompany")
	public String buyCompany(Model model) {
		return "/ma/buyCompany";
	}

	@RequestMapping("qrCodePage")
	public String getQRCodePage(Model model, HttpServletRequest req) {
		String qrcode = req.getParameter("qrcode");
		req.getSession().setAttribute("qrcode", qrcode);
		return "/ma/qrCodePage";
	}

	/*
	 * 合格证上传
	 */
	@RequestMapping("imgLoadPage")
	public String imgLoad(Model model, HttpServletRequest req) {
		return "/ma/imgLoad";
	}
	@RequestMapping("uploadExcel")
	public String uploadExcel(Model model, HttpServletRequest req) {
		return "/ma/excelUpload";
	}

	@RequestMapping("submitLoad")
	public String submitLoad(Model model, HttpServletRequest req) {
		return "/ma/submitLoad";
	}
	/*
	 * 技术材料上传
	 */
	@RequestMapping("materialsLoadPage")
	public String materialsLoad(Model model, HttpServletRequest req) {
		return "/ma/materialsLoad";
	}
	/*
	 * 改造手续
	 */
	@RequestMapping("proceduresLoadPage")
	public String proceduresLoad(Model model, HttpServletRequest req) {
		return "/ma/proceduresLoad";
	}
	@RequestMapping("OpmanualLoadPage")
	public String OpmanualLoadPage(Model model,HttpServletRequest req){
		return "/ma/opmanualLoad";
	}
	/*
	 * 合格证照片查看
	 */
	@RequestMapping("queryImgPage")
	public String queryImg(Model model, HttpServletRequest req) {
		return "/ma/queryImg";
	}
	@RequestMapping("queryOptImgPage")
	public String queryOptImgPage(Model model, HttpServletRequest req) {
		return "/ma/queryOptImgPage";
	}
	/*
	 * 技术材料
	 */
	@RequestMapping("fileView")
	public String fileView(Model model, HttpServletRequest req) {
		return "/ma/fileView";
	}

	/*
	 * 文件查看
	 */
	@RequestMapping("queryFilePage")
	public String queryFile(Model model, HttpServletRequest req) {
		return "/ma/queryFile";
	}


	@RequestMapping(value = "getMachineStatus", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMachineStatus() {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.getMachineStatus();
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
	public AjaxRes findByPage(Page<MachineBean> page, MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();//获取分公司
			o.setOrgId(companyId);//分公司id
			System.out.println(companyId);
			Page<MachineBean> result = service.findByPage(o, page);

			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping("exportModelExcel")
	public void exportModelExcel(HttpServletRequest request, HttpServletResponse response, MachineBean o) {
		try {
			String filename = "设备入库模板";
			List<String> headers = reportHeader1();
			HSSFWorkbook workbook = POIOutputHelper.excel(null, headers, filename);
			OutputStream out = null;
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".xls");
			response.setHeader("Pragma", "No-cache");
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		}catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private List<String> reportHeader1() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具名称");
		list.add("规格型号");
		list.add("产权部门");
		list.add("使用部门");
		list.add("设备编号");
		list.add("机具状态");
		list.add("备注");
		list.add("固资编号");
		return list;
	}



	@RequestMapping(value = "fileViewFindByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes fileViewFindByPage(Page<MachineBean> page, MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MachineBean> result = service.fileViewFindByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findPhotoList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPhotoList(MachineBean  o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.findPhotoList(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findOptPhotoList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findOptPhotoList(MachineBean  o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.findOptPhotoList(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}


	@RequestMapping(value = "findFileList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findFileList(MachineBean  o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.findFileList(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}


	@RequestMapping(value="findMachineType", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findMachineType(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> list = service.findMachineType(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value="findMachineTypeId", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findMachineTypeId(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> list = service.findMachineTypeId(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByPageTwo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPageTwo(Page<MachineBean> page, MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();//获取分公司
			o.setOrgId(companyId);//分公司id
			Page<MachineBean> result = service.findByPageTwo(o, page);
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
	 * 二维码绑定机具
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "machineBinding", method = RequestMethod.POST)
	@ResponseBody
	public List<MachineBean> repairBinding(MachineBean o, HttpServletRequest request) {
		List<MachineBean> list = new ArrayList<>();
		try {
			String deviceCode = o.getDeviceCode().toUpperCase();
			o.setDeviceCode(deviceCode);
			list = service.findByCode(o);
			if (list != null && list.size() ==1) {
				//ar.setSucceed(list, "2");

				QRCodeBean qrBean = new QRCodeBean();
				qrBean.setCode(o.getQrcode());
				qrBean.setMaModelId(o.getModelId());
				List<QRCodeBean> qrList = qrService.findQRCodeInfo(qrBean);
				qrBean = qrList.get(0);
				o.setType(qrBean.getMaModelId());
				if (StringHelper.isEmpty(o.getVerderId())) {
					o.setVerderId(qrBean.getVenderId());
				}
				o.setDeviceNum(qrBean.getBuyCompany());
				o.setIsFixedAssets("0");
				o.setClerk(o.getClerk());
				o.settOverhaulPersion(o.gettOverhaulPersion());
				String thisTime = DateTimeHelper.getNowDate();
				o.settOverhaulTime(thisTime);
				o.setnOverhaulTime(DateTimeHelper.getNextYearDate(thisTime));
				o.setOutInNum("0");
				//o.setBatchStatus("1");
				o.setBindTime(DateTimeHelper.getNowDate());
				//service.insert(o);
				service.machineBinding(o);
				qrBean.setCode(o.getQrcode());
				qrBean.setBindTime(DateTimeHelper.getNowTime());
				qrBean.setIsBind("1");
				qrService.update(qrBean);

				String actualNum = null;

				try {
					actualNum = Float.parseFloat(actualNum) + 1 + "";
				} catch (Exception e) {
					actualNum = "1";
				}


				logger.warn("绑定成功！");

			}
			else if(list.size() > 1){
				return list;
			}else{
				return list;
			}


		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	@RequestMapping(value = "addBuyTime", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addBuyTime(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.addBuyTime(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> list = service.find(o);
			List<MachineBean> list1 = service.findFile(o);
			MachineBean station = list.get(0);
			if(list1.size()>0){
				station.setFilePath(list1.get(0).getFilePath());
			}
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "machineLifeCycle", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> machineLifeCycle(MachineBean o) {
		Map<String, Object> map = new HashMap<String, Object>();
		AjaxRes ar1 = getAjaxRes();
		AjaxRes ar2 = getAjaxRes();
		AjaxRes ar3 = getAjaxRes();
		AjaxRes ar4 = getAjaxRes();
		AjaxRes ar5 = getAjaxRes();
		AjaxRes ar6 = getAjaxRes();
		MachineBean station1 = new MachineBean();
		MachineBean station2 = new MachineBean();
		MachineBean station3 = new MachineBean();
		MachineBean station4 = new MachineBean();
		MachineBean station5 = new MachineBean();
		MachineBean station6 = new MachineBean();
		try {
			List<MachineBean> listPicking = service.machinePicking(o);
			if(listPicking != null && !listPicking.isEmpty()){
				station1 = listPicking.get(0);
			}
			ar1.setSucceed(station1);

			List<MachineBean> listMaterialReturn = service.machineMaterialReturn(o);
			if(listMaterialReturn != null && !listMaterialReturn.isEmpty()){
				station2 = listMaterialReturn.get(0);
			}
			ar2.setSucceed(station2);

			List<MachineBean> listRepair = service.machineRepair(o);
			if(listRepair != null && !listRepair.isEmpty()){
				station3 = listRepair.get(0);
			}
			ar3.setSucceed(station3);

			List<MachineBean> listOverhaul = service.machineOverhaul(o);
			if(listOverhaul != null && !listOverhaul.isEmpty()){
				station4 = listOverhaul.get(0);
			}
			ar4.setSucceed(station4);

			List<MachineBean> listWarehousing = service.machineWarehousing(o);
			if(listWarehousing != null && !listWarehousing.isEmpty()){
				station5 = listWarehousing.get(0);
			}
			ar5.setSucceed(station5);

			//报废信息
			List<MachineBean> listScrap = service.machineScrap(o);
			if(listScrap != null && !listScrap.isEmpty()){
				station6 = listScrap.get(0);
			}
			ar6.setSucceed(station6);


			map.put("listPicking", ar1);
			map.put("listMaterialReturn", ar2);
			map.put("listRepair", ar3);
			map.put("listOverhaul", ar4);
			map.put("listWarehousing", ar5);
			map.put("listScrap", ar6);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar1.setFailMsg(GlobalConst.DATA_FAIL);
			ar2.setFailMsg(GlobalConst.DATA_FAIL);
			ar3.setFailMsg(GlobalConst.DATA_FAIL);
			ar4.setFailMsg(GlobalConst.DATA_FAIL);
			ar5.setFailMsg(GlobalConst.DATA_FAIL);
			ar6.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return map;
	}


	@RequestMapping(value = "findCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findCode(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> list = service.findCode(o);
			MachineBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByQrcode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByQrcode(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			MachineBean bean = service.findByQrcode(o);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "machinehistory", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes machinehistory(Page<MachineBean> page, MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MachineBean> result = service.findByPageThree(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "exporthistory" )
	public void exporthistory(Page<MachineBean> page, HttpServletRequest request, HttpServletResponse response,
							  MachineBean o) {
		try {
			page.setPageSize(10000);
			Page<MachineBean> results = service.findByPageThree(o, page);
			List<MachineBean> list = results.getResults();
			if (list != null) {
				for (MachineBean bean : list) {
					String type = bean.getType();
					switch (type) {
						case "2":
							bean.setType("领料");
							break;
						case "3":
							bean.setType("机具入库");
							break;
						case "4":
							bean.setType("机具退料");
							break;
						case "5":
							bean.setType("新购");
							break;
						case "6":
							bean.setType("维修");
							break;
						case "7":
							bean.setType("报废");
							break;
						case "8":
							bean.setType("维修—检验");
							break;
					}
				}
			}

			String machineName =list.get(0).getTypeName()+"  "+list.get(0).getMachineName();
			String typeName = "";
			if(list.get(0).getAssetsNum()==null || list.get(0).getAssetsNum()==" "){
				typeName = "(固资编号："+"/"+"；设备编码:"+list.get(0).getCode()+")";
			}else{
				typeName = "(固资编号："+list.get(0).getAssetsNum()+"；设备编码:"+list.get(0).getCode()+")";
			}
			expOutExcel1(response, list, "机具历史记录",machineName,typeName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	private void expOutExcel1(HttpServletResponse response, List<MachineBean> list, String filename, String machineName,String typeName) throws Exception {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		if(list !=null){
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MachineBean bean = list.get(i);
				Map<String, Object> maps = outmachinehistory(i, bean);
				results.add(maps);
			}
		}
		List<String> headers = historyreportHeader();

		HSSFWorkbook workbook = POIOutputHelper1.excel(results, headers, filename,machineName,typeName);
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
	private Map<String, Object> outmachinehistory(int i, MachineBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("optType", o.getType()); // 类型名称
		maps.put("unitName", o.getUnitName()); // 规格名称
		maps.put("proName", o.getProName()); // 采购价格
		maps.put("name", o.getName()); // 固资编号
		maps.put("outTime", o.getOutTime());//产权部门
		maps.put("remark", o.getRemark()); // 备注
		/*
		 * maps.put("typeName", o.getTypeName()); maps.put("counts", o.getCounts());
		 * maps.put("buyMoney", o.getBuyMoney()); maps.put("buyTime", o.getBuyTime());
		 * maps.put("acceptTime", o.getAcceptTime());
		 */
		return maps;
	}
	private List<String> historyreportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("操作类型");
		list.add("项目名称");
		list.add("单位名称");
		list.add("操作人");
		//list.add("出厂编号");
		list.add("操作时间");
		list.add("备注");
		return list;
	}

	@RequestMapping(value = "findByQrcodePage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByQrcodePage(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			MachineBean bean = service.findByQrcodePage(o);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findInfoData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findInfoData(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> list = service.findInfoData(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByDeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByDeviceCode(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			MachineBean bean = service.findByDeviceCode(o);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByLikeCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByLikeCode(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			MachineBean bean = service.findByLikeCode(o);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getAlOutStorageInfoList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getAlOutStorageInfoList(OutStorageBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<OutStorageBean> bean = service.getAlOutStorageInfoList(o);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findByDevQrcode", method = RequestMethod.POST)
	@ResponseBody
	public String findByDevQrcode(MachineBean o, HttpServletRequest request) {
		String results = "";
		String deviceCode = request.getParameter("deviceCode");
		o.setDeviceCode(deviceCode);
		try {
			List<MachineBean> list = service.findByCode(o);
			if (list != null && list.size() > 0) {
				for (MachineBean bean : list) {
					results += bean.toString() + ";";
				}
				results = results.substring(0, results.length() - 1);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return results;
	}

	@RequestMapping(value = "findByCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByCode(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> list = service.findByCode(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findType", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findType(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.findType(o);
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
	public AjaxRes findModel(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.findModel(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findSums", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findSums(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.findSums(o);
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
	 * 资产属性
	 *
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "getBuyCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getBuyCompany() {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineBean> result = service.getBuyCompany();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}


	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(MachineBean o) {
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		String res = "";
		try {
			if (StringHelper.isNotEmpty(o.getOutFactortNum())) {
				List<MachineBean> list = service.findByOutFactortNum(o);
				if (list != null && list.size() > 0) {
					res += "<span style='color: red'>出厂编号</span>已存在，修改失败<br/>";
					flag1 = true;
				}
			}
			if (StringHelper.isNotEmpty(o.getDeviceCode())) {
				List<MachineBean> list = service.findByCode(o);
				if (list != null && list.size() > 0) {
					res += "<span style='color: red'>设备编码</span>已存在，修改失败<br/>";
					flag2 = true;
				}
			}
			if (StringHelper.isNotEmpty(o.getAssetNum())) {
				List<MachineBean> list = service.findByAssetNum(o);
				if (list != null && list.size() > 0) {
					res += "<span style='color: red'>固资编号</span>已存在，修改失败<br/>";
					flag3 = true;
				}
			}
			if(flag1){
				o.setOutFactortNum("");
			}
			if(flag2){
				o.setDeviceCode("");
			}
			if(flag3){
				o.setAssetNum("");
			}
			if(flag1 || flag2 || flag3){
				res += "其余已修改成功";
			}
			service.update(o);
			if (StringHelper.isNotEmpty(o.getBuyPrice())) {//购置价格，购置价格在其他的数据表中所以单独写
				List<MachineBean> list = service.findByType(o);
				o.setId(list.get(0).getType());
				service.updateType(o);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		if(flag1 || flag2 || flag3){
			return res;
		}else{
			return "修改成功";
		}
	}

	@RequestMapping(value = "updateCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateCode(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String gpsRes = "";
			String epcRes = "";
			List<MachineBean> gps = service.findGps(o);
			List<MachineBean> dev = service.findDev(o);
			String rfidEpc = o.getRfidEpc();
			if (gps.isEmpty()) {
				o.setRfidEpc(null);
				service.update(o);
				gpsRes = "GPS配置成功";
			} else {
				gpsRes = "GPS配置失败，该编号已存在";
			}

			if (dev.isEmpty()) {
				o.setGpsCode(null);
				o.setRfidEpc(rfidEpc);
				service.update(o);
				epcRes = "RFID配置成功";
			} else {
				epcRes = "REID配置失败，该编号已存在";
			}
			ar.setSucceedMsg(gpsRes + "、" + epcRes);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(MachineBean o) {
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

	@RequestMapping(value = "delBatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks) {
		AjaxRes ar = getAjaxRes();
		try {
			service.deleteBatch(chks);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

	// 二维码打印
	@RequestMapping(value = "ToQRCode", method = RequestMethod.POST)
	@ResponseBody
	public void ToQRCode(MachineBean o, HttpServletRequest request) throws Exception {
		List<MachineBean> list = service.find(o);
		String company = list.get(0).getDeviceNum();
		String stockNums = findStockNums(o);
		int num = Integer.parseInt(stockNums) + 1;
		String month = DateTimeHelper.getNowMonth().replace("-", "");
		stockNums = String.format("%5d", num).replace(" ", "0");
		InetAddress inet = InetAddress.getLocalHost();
		String mid = o.getId();
		String ip = inet.getHostAddress();
		String Port = request.getLocalPort() + ""; // 取得本地端口
		String proName = request.getContextPath();
		String url = "http://" + ip + ":" + Port + proName + "/backstage/machine/qrCodePage?qrcode=" + mid;
		System.out.println("url=" + url);
		String text = "暂无数据";
		// 主要参数
		String deviceNum = company + month + "-" + stockNums;
		String assetNum = deviceNum;
		text = url;
		int width = 300;
		int height = 300;
		// 二维码的图片格式
		String format = "png";
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		// 生成二维码
		String realPath = request.getSession().getServletContext().getRealPath("/images");
		File files = new File(realPath);
		if (!files.exists()) {
			files.mkdirs();
		}
		File outputFile = new File(realPath + File.separator + deviceNum + ".png");
		String fileName = deviceNum + ".png";
		o.setId(mid);
		o.setQrcodeUrl(fileName);
		o.setDeviceNum(deviceNum);
		o.setAssetNum(assetNum);
		service.insertQRCode(o);
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	}

	public Object nullToEmpty(Object o) {
		if (null == o) {
			o = "";
		}
		return o;
	}

	// 查询库存量
	public String findStockNums(MachineBean o) {
		o.setBuyTime(DateTimeHelper.getNowYear());
		String stockNums = service.findStockNums(o);
		return stockNums;
	}

	// 改变状态为待入库
	@RequestMapping(value = "inPuting", method = RequestMethod.POST)
	@ResponseBody
	public String inPuting(MachineBean o) {
		service.update(o);
		return "1";
	}

	@RequestMapping(value = "updateCodeByQrcode", method = RequestMethod.POST)
	@ResponseBody
	public String updateCodeByQrcode(MachineBean o) {
		String res = "绑定成功";
		try {
			o.setQrcode(o.getQrcode().toUpperCase());
			service.updateCodeByQrcode(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "绑定失败";
		}
		return res;
	}

	@RequestMapping(value = "binding", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes binding(MachineBean o, HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			String userId = request.getParameter("userId");
			String inputType = request.getParameter("inputType");
			String deviceCode = o.getDeviceCode().toUpperCase();
			o.setDeviceCode(deviceCode);
			List<MachineBean> list = service.findByCode(o);
			if (list != null && list.size() > 0) {
				ar.setSucceed("2");
			} else {
				QRCodeBean qrBean = new QRCodeBean();
				qrBean.setCode(o.getQrcode());
				List<QRCodeBean> qrList = qrService.findQRCodeInfo(qrBean);
				qrBean = qrList.get(0);
				o.setType(qrBean.getMaModelId());
				o.setVerderId(qrBean.getVenderId());
				o.setDeviceNum(qrBean.getBuyCompany());
				o.setIsFixedAssets("0");// 是否是固定资产
				o.setClerk(o.getClerk());// 检验员 --》 登录人姓名
				o.settOverhaulPersion(o.gettOverhaulPersion());// 本次检修人员 --》 登录人姓名
				String thisTime = DateTimeHelper.getNowDate();
				o.settOverhaulTime(thisTime);// 本次检修时间
				o.setnOverhaulTime(DateTimeHelper.getNextYearDate(thisTime));// 下次检修时间
				o.setOutInNum("0");
				service.insert(o);
				qrBean.setCode(o.getQrcode());
				qrBean.setIsBind("1");
				qrService.update(qrBean);
				MachineBean bean = new MachineBean();
				bean.setId(o.getId());
				bean.setBatchStatus("5");// 在库
				service.update(bean);
				MachineTypeBean mt = new MachineTypeBean();
				mt.setId(qrBean.getMaModelId());
				bindingNums(mt, request, userId);
				// TODO
				MachineInputDetailsBean miBean = new MachineInputDetailsBean(bean.getId(), userId,
						DateTimeHelper.getNowTime(), o.getQrcode(), "0", "1", inputType, "");
				miService.insert(miBean);
				ar.setSucceed("1");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}

	public synchronized AjaxRes bindingNums(MachineTypeBean mt, HttpServletRequest request, String userId) {
		AjaxRes ar = new AjaxRes();
		try {
			List<MachineTypeBean> mtlist = mtservice.find(mt);
			String libNums = null;
			if (mtlist.size() > 0) {
				libNums = mtlist.get(0).getNums();// 库存数
			} else {
				libNums = "0";// 不存在机具规格 
			}
			String num = libNums;
			libNums = (Float.parseFloat(libNums) + 1) + "";
			mt.setNums(libNums);
			mtservice.update(mt);
			MachineTypeBean bean = mtservice.findTopClass(mt);
			aservice.addAuditLogs(request, userId, "/backstage/machine/binding", "盘点入库", "绑定入库",
					"修改机具 '" + bean.getParentName() + "-" + bean.getName() + "'后,库存为" + num + "" + mt.getNums(), "1");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setSucceed("0");
			MachineTypeBean bean = mtservice.findTopClass(mt);
			aservice.addAuditLogs(request, userId, "/backstage/machine/binding", "盘点入库", "绑定入库",
					"修改机具 '" + bean.getParentName() + "-" + bean.getName() + "'后,库存为" + mt.getNums(), "-1");
		}

		return ar;
	}

	@RequestMapping(value = "newListBinding", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes newListBinding(MachineBean o) {
		AjaxRes ar = new AjaxRes();
		try {
			String deviceCode = o.getDeviceCode().toUpperCase();
			o.setDeviceCode(deviceCode);
			List<MachineBean> list = service.findByCode(o);
			if (list != null && list.size() > 0) {
				ar.setSucceed("2");
			} else {
				QRCodeBean qrBean = new QRCodeBean();
				qrBean.setCode(o.getQrcode());
				List<QRCodeBean> qrList = qrService.findQRCodeInfo(qrBean);
				qrBean = qrList.get(0);
				o.setClerk(o.getClerk());
				o.settOverhaulPersion(o.gettOverhaulPersion());
				String thisTime = DateTimeHelper.getNowDate();
				o.settOverhaulTime(thisTime);
				o.setnOverhaulTime(DateTimeHelper.getNextYearDate(thisTime));
				o.setOutInNum("0");
				o.setBatchStatus("4");
				service.update(o);
				qrBean.setCode(o.getQrcode());
				qrBean.setIsBind("1");
				qrService.update(qrBean);

				ar.setSucceed("1");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return ar;
	}

	@RequestMapping(value = "bindingSure", method = RequestMethod.POST)
	@ResponseBody
	public String bindingSure(MachineBean o) {
		String res = "绑定成功";
		try {
			String deviceCode = o.getDeviceCode().toUpperCase();
			o.setDeviceCode(deviceCode);
			QRCodeBean qrBean = new QRCodeBean();
			qrBean.setCode(o.getQrcode());
			String maModelId = "";
			String typeId = "";
			List<QRCodeBean> qrList = qrService.findQRCodeInfo(qrBean);
			List<MachineBean> maList = service.findByCode(o);
			if(qrList != null){
				maModelId = qrList.get(0).getMaModelId();
			}
			if(maList != null){
				typeId = maList.get(0).getTypeId();
			}
			if(Integer.parseInt(maModelId) != Integer.parseInt(typeId)){
				res = "-1";
			}else{
				qrBean = qrList.get(0);
				o.setType(qrBean.getMaModelId());
				o.setVerderId(qrBean.getVenderId());
				o.setDeviceNum(qrBean.getBuyCompany());
				o.setIsFixedAssets("0");
				o.setClerk(o.getClerk());
				o.settOverhaulPersion(o.gettOverhaulPersion());
				String thisTime = DateTimeHelper.getNowDate();
				o.settOverhaulTime(thisTime);
				o.setnOverhaulTime(DateTimeHelper.getNextYearDate(thisTime));
				o.setOutInNum("0");
				service.insert(o);
				qrBean.setCode(o.getQrcode());
				qrBean.setIsBind("1");
				qrService.update(qrBean);
				MachineBean bean = new MachineBean();
				bean.setId(o.getId());
				bean.setBatchStatus("5");
				service.update(bean);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "绑定失败";
		}
		return res;
	}

	@RequestMapping(value = "bindingDetail", method = RequestMethod.POST)
	@ResponseBody
	public String bindingDetail(MachineBean o) {
		String res = "绑定成功";
		try {
			o.setQrcode(o.getQrcode().toUpperCase());
			String deviceNum = o.getDeviceNum();
			MachineBean maBean = service.findByQrcode(o);
			if (maBean == null) {
				List<MachineBean> list = service.findByCode(o);
				if (list.isEmpty()) {
					String stockNums = findStockNums();
					int numss = Integer.parseInt(stockNums) + 1;
					String month = DateTimeHelper.getNowMonth().replace("-", "");
					stockNums = String.format("%5d", numss).replace(" ", "0");
					deviceNum = deviceNum + month + "-" + stockNums;
					o.setDeviceNum(deviceNum);
					service.insert(o);
					QRCodeBean qrBean = new QRCodeBean();
					qrBean.setCode(o.getQrcode());
					qrBean.setIsBind("1");
					qrService.update(qrBean);
				} else {
					res = "绑定失败，该设备已绑定";
				}
			} else {
				res = "绑定失败，该二维码已绑定";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "绑定失败";
		}
		return res;
	}

	@RequestMapping(value = "unbinding", method = RequestMethod.POST)
	@ResponseBody
	public String unbinding(MachineBean o) {
		String res = "解绑成功";
		try {
			o.setQrcode(o.getQrcode().toUpperCase());
			List<MachineBean> list = service.findByCode(o);
			if (list != null && list.size() > 0) {
				MachineBean bean = list.get(0);
				service.unbinding(o);
				QRCodeBean qrBean = new QRCodeBean();
				qrBean.setCode(o.getQrcode());
				qrBean.setIsBind("0");
				qrService.update(qrBean);
				bean.setId(list.get(0).getId());
				bean.setBatchStatus("4");
			} else {
				res = "解绑失败，该设备编号不存在";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	// 查询库存量
	public String findStockNums() {
		MachineBean bean = new MachineBean();
		bean.setBuyTime(DateTimeHelper.getNowYear());
		String stockNums = service.findStockNums(bean);
		return stockNums;
	}

	/**
	 * 机具设备导出
	 *
	 * @param request
	 * @param response
	 * @param page
	 * @param o
	 */
	@RequestMapping("export")
	public void expExcel(HttpServletRequest request, HttpServletResponse response, MachineBean o) {
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setOrgId(companyId);
			List<MachineBean> list = service.export(o);
			if (list != null) {
				for (MachineBean bean : list) {
					String status = bean.getBatchStatus();
					switch (status) {
						case "1":
							bean.setBatchStatus("待通知");
							break;
						case "2":
							bean.setBatchStatus("待检验");
							break;
						case "3":
							bean.setBatchStatus("待打印");
							break;
						case "4":
							bean.setBatchStatus("待入库");
							break;
						case "5":
							bean.setBatchStatus("在库");
							break;
						case "6":
							bean.setBatchStatus("在用");
							break;
						case "7":
							bean.setBatchStatus("在修");
							break;
						case "8":
							bean.setBatchStatus("在检");
							break;
						case "9":
							bean.setBatchStatus("修饰后待入库");
							break;
						case "10":
							bean.setBatchStatus("待报废");
							break;
						case "11":
							bean.setBatchStatus("已报废");
							break;
						case "12":
							bean.setBatchStatus("报废封存");
							break;
						case "13":
							bean.setBatchStatus("在检");
							break;
						case "14":
							bean.setBatchStatus("在审");
							break;
						case "15":
							bean.setBatchStatus("出库待批准");
							break;
						case "16":
							bean.setBatchStatus("待报废检验");
							break;
						case "17":
							bean.setBatchStatus("待封存检验");
							break;
						default:
							bean.setBatchStatus("其它");
							break;
					}
				}
			}
			expOutExcel(response, list, "机具设备报表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	/*@RequestMapping(value = "export", method = RequestMethod.GET)
	public void export(Page<MachineBean> page, HttpServletRequest request, HttpServletResponse response,
			MachineBean o) {
		try {
			page.setPageSize(10000);
			Page<MachineBean> results = service.findByPage(o, page);
			List<MachineBean> list = results.getResults();
			expOutExcel(response, list, "机具设备报表");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}*/

	private void expOutExcel(HttpServletResponse response, List<MachineBean> list, String filename) throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				MachineBean bean = list.get(i);
				Map<String, Object> maps = outWarehousingReportBeanToMap(i, bean);
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

	private Map<String, Object> outWarehousingReportBeanToMap(int i, MachineBean o) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("type0", o.getType0()); // 类型名称
		maps.put("type", o.getType()); // 规格名称
		maps.put("buyPrice", o.getBuyPrice()); // 采购价格
		maps.put("assetNum", o.getAssetNum()); // 固资编号
		maps.put("propertyDepartment", o.getPropertyDepartment());//产权部门
		maps.put("useDepartment",o.getUseDepartment());//使用部门
		maps.put("serviceLife", o.getServiceLife());//使用年限
		maps.put("makeOrderDate", o.getMakeOrderDate());//制单日期
		maps.put("invoiceDate",o.getInvoiceDate());//发票日期
		maps.put("venderName", o.getVenderName()); // 厂家
		maps.put("deviceCode", o.getDeviceCode()); // 设备编码
		maps.put("qrcode", o.getQrcode()); // 二维码编号
		maps.put("batchStatus", o.getBatchStatus()); // 机具状态
		maps.put("buyTime", o.getBuyTime()); // 出厂时间
		//maps.put("outFactortNum", o.getOutFactortNum()); // 出厂编号
		maps.put("tOverhaulPersion", o.gettOverhaulPersion()); // 本次检修人员
		maps.put("tOverhaulTime", o.gettOverhaulTime()); // 本次检修时间
		maps.put("nOverhaulTime", o.getnOverhaulTime()); // 下次检修时间
		maps.put("subcontractors", o.getSubcontractors()); // 领料方
		maps.put("leaseName", o.getLeaseName()); //
		maps.put("projectName", o.getProjectName()); // 
		maps.put("remarks", o.getRemarks()); // 备注

		/*
		 * maps.put("typeName", o.getTypeName()); maps.put("counts", o.getCounts());
		 * maps.put("buyMoney", o.getBuyMoney()); maps.put("buyTime", o.getBuyTime());
		 * maps.put("acceptTime", o.getAcceptTime());
		 */
		return maps;
	}

	private List<String> reportHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("机具名称");
		list.add("规格型号");
		list.add("购置价格");
		list.add("固资编号");
		list.add("产权部门");
		list.add("使用部门");
		list.add("使用年限");
		list.add("制单日期");
		list.add("发票日期");
		list.add("厂家");
		list.add("设备编码");
		list.add("二维码编号");
		list.add("机具状态");
		list.add("出厂时间");
		//list.add("出厂编号");
		list.add("本次检修人员");
		list.add("本次检修时间");
		list.add("下次检修时间");
		list.add("领料方");
		list.add("领料单位");
		list.add("领料工程");
		list.add("备注");
		return list;
	}

	@RequestMapping(value = "isFixedAssets", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isFixedAssets(MachineBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.isFixedAssets(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findInByAccountName", method = RequestMethod.POST)
	@ResponseBody
	public String findInByAccountName(MachineBean o) {
		String results = "";
		MachineBean maUser = service.findByAccountName(o);
		String userId = maUser.getUserId();
		try {
			results = userId;
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return results;
	}

	@RequestMapping(value="addMachine", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(MachineBean o){
		AjaxRes ar=getAjaxRes();
		try {

			String deviceCodeFlag = getCode(o);

			if("1".equals(deviceCodeFlag)){

				String typeId = o.getModel();

				o.setType(typeId);
				o.setBatchStatus("5");

				service.insert(o);
				MachineTypeBean mt = new MachineTypeBean();
				mt.setId(o.getModel());
				List<MachineTypeBean> mtlist = mtservice.find(mt);
				String libNums = null;
				if (mtlist.size() > 0) {
					libNums = mtlist.get(0).getNums();// 库存数
				} else {
					libNums = "0";// 不存在机具规格
				}
				//String num = libNums;
				libNums = (Float.parseFloat(libNums) + 1) + "";
				mt.setNums(libNums);
				mtservice.update(mt);
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			}else if("0".equals(deviceCodeFlag)){
				ar.setFailMsg("设备编码重复");
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "uploadProcedures")
	@ResponseBody
	public Map<String,Object> procedures(@RequestParam("file")MultipartFile file,MachineBean o,HttpServletRequest request){
		String prefix="";
		String dateStr="";
		//保存上传
		OutputStream out = null;
		InputStream fileInput=null;
		try{
//        	UUID uuid = UUID.randomUUID();
			if(file!=null){
				String fileName = file.getOriginalFilename();
				String fileNewName = UUID.randomUUID().toString();
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateStr = simpleDateFormat.format(date);
				String uuid = UUID.randomUUID().toString().replace("-", "");
//                String saveDirectory = request.getSession().getServletContext().getRealPath("/procedures");
				String mkdirsName = "procedures"; // 机具管理-机具类型管理
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

				String filePath = "/procedures" +"/"+fileNewName;
				File dest=new File(files,fileNewName);
				file.transferTo(dest);
				o.setFileName(fileName);
				o.setFilePath(filePath);
				o.setFileNewName(fileNewName);
				int res = service.insProceduresById(o);


				Map<String,Object> map2=new HashMap<>();
				Map<String,Object> map=new HashMap<>();
				map.put("code",0);
				map.put("msg","");
				map.put("data",map2);
				map2.put("src","/file/"+ filePath);
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
	@RequestMapping(value = "uploadOpmanual")
	@ResponseBody
	public Map<String,Object> opmanual(@RequestParam("file")MultipartFile file,MachineBean o,HttpServletRequest request){
		String prefix="";
		String dateStr="";
		//保存上传
		OutputStream out = null;
		InputStream fileInput=null;
		try{
//        	UUID uuid = UUID.randomUUID();
			if(file!=null){
				String fileName = file.getOriginalFilename();
				String fileNewName = UUID.randomUUID().toString();
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateStr = simpleDateFormat.format(date);
				String uuid = UUID.randomUUID().toString().replace("-", "");
//                String saveDirectory = request.getSession().getServletContext().getRealPath("/optInfo");
				String mkdirsName = "optInfo"; // 机具管理-机具类型管理
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

				String filePath = "/optInfo" +"/"+fileNewName;
				File dest=new File(files,fileNewName);
				file.transferTo(dest);
				o.setFileName(fileName);
				o.setFilePath(filePath);
				o.setFileNewName(fileNewName);
				int res = service.updateOpmanual(o);

				Map<String,Object> map2=new HashMap<>();
				Map<String,Object> map=new HashMap<>();
				map.put("code",0);
				map.put("msg","");
				map.put("data",map2);
				map2.put("src","/file/"+ filePath);
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

	@RequestMapping(value = "submitUpload")
	@ResponseBody
	public Map<String,Object> submitUpload(@RequestParam("file")MultipartFile file,MachineBean o,HttpServletRequest request){

		String prefix="";
		String dateStr="";
		//保存上传
		OutputStream out = null;
		InputStream fileInput=null;

		try{
//        	UUID uuid = UUID.randomUUID();
			if(file!=null){

				String fileNewName = UUID.randomUUID().toString();
				String fileName = file.getOriginalFilename();
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateStr = simpleDateFormat.format(date);
				//String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//                String saveDirectory = request.getSession().getServletContext().getRealPath("/optInfo");
				String mkdirsName = "optInfo"; // 机具管理-机具类型管理
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

				String filePath = "/optInfo" +"/"+fileNewName;
				File dest=new File(files,fileNewName);
				file.transferTo(dest);
				o.setFileName(fileName);
				o.setFilePath(filePath);
				o.setFileNewName(fileNewName);
				int res = service.updateOptInfo(o);

				Map<String,Object> map2=new HashMap<>();
				Map<String,Object> map=new HashMap<>();
				map.put("code",0);
				map.put("msg","");
				map.put("data",map2);
				map2.put("src","/file/"+ dateStr+fileNewName);
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
	@RequestMapping(value = "uploadMaterials")
	@ResponseBody
	public Map<String,Object> materials(@RequestParam("file")MultipartFile file,MachineBean o,HttpServletRequest request){
		String prefix="";
		String dateStr="";
		//保存上传
		OutputStream out = null;
		InputStream fileInput=null;
		try{
//        	UUID uuid = UUID.randomUUID();
			if(file!=null){
//            	String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")); // 前缀
//                String fileName = DateTimeHelper.getNowDate().replace("-", "") + "_" + uuid + suffix;
////                String fileName = UUID.randomUUID().toString().replace("-", "") + suffix; // 文件名称
//                String mkdirsName = "materials"; // X光检测报告
//                String testReportFiles = "/data/gz/" + mkdirsName + "/"; // linux 系统路径
//                String os = System.getProperty("os.name");
//                if (os.toLowerCase().startsWith("win")) {
//                    testReportFiles = "e://GZMachinesWeb/" + mkdirsName + "/";
//                }
//                String path = testReportFiles + "/" + DateTimeHelper.getYear(new Date()) + "/" + DateTimeHelper.getMonth(new Date()) + "/" + fileName;
//                File newFile = new File(path);
//                
//                //生成文件夹
//                if (!newFile.getParentFile().exists()) {
//                    newFile.getParentFile().mkdirs();
//                }
//                file.transferTo(newFile);
//                String filePath = mkdirsName+"/"+DateTimeHelper.getYear(new Date())+"/"+DateTimeHelper.getMonth(new Date())+"/"+fileName;
				String fileName = file.getOriginalFilename();
				String fileNewName = UUID.randomUUID().toString();
                 /*Date date = new Date();
                 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 dateStr = simpleDateFormat.format(date);*/
				String uuid = UUID.randomUUID().toString().replace("-", "");
//            	 String saveDirectory = request.getSession().getServletContext().getRealPath("/Materials");
				String mkdirsName = "Materials"; // 机具管理-机具类型管理
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

				String filePath = "/Materials" +"/"+fileNewName;
				File dest=new File(files,fileNewName);
				file.transferTo(dest);
				o.setFileName(fileName);
				o.setFilePath(filePath);
				o.setFileNewName(fileNewName);
				int res = service.insMaterialsById(o);


				Map<String,Object> map2=new HashMap<>();
				Map<String,Object> map=new HashMap<>();
				map.put("code",0);
				map.put("msg","");
				map.put("data",map2);
				map2.put("src","/file/"+ filePath);
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
	@RequestMapping(value = "uploadImg")
	@ResponseBody
	public Map<String,Object> upload(@RequestParam(""
			+ "")MultipartFile file,MachineBean o,HttpServletRequest request){
		String prefix="";
		String dateStr="";
		//保存上传
		OutputStream out = null;
		InputStream fileInput=null;
		try{
			if(file!=null){

				String fileNewName = UUID.randomUUID().toString();
				String fileName = file.getOriginalFilename();
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateStr = simpleDateFormat.format(date);
				//String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//                   String saveDirectory = request.getSession().getServletContext().getRealPath("/machineImg");
				String mkdirsName = "machineImg"; // 机具管理-机具类型管理
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

				String filePath ="/machineImg" +"/"+fileNewName;
				File dest=new File(files,fileNewName);
				file.transferTo(dest);
				o.setFileName(fileName);
				o.setFilePath(filePath);
				o.setFileNewName(fileNewName);

//                String fileName = file.getOriginalFilename();
//                Date date = new Date();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                dateStr = simpleDateFormat.format(date);
//                String filepath = request.getSession().getServletContext().getRealPath("/machineImg");
//               // String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//
//                File files=new File(filepath);
//                //打印查看上传路径
//                System.out.println(filepath);
//                if(!files.getParentFile().exists()){
//                    files.getParentFile().mkdirs();
//                }
//                file.transferTo(files);

				//保存文件名文件路径
//                String uuid = UUID.randomUUID().toString();
//                o.setFileName(uuid);
//                o.setFilePath(filepath);
//                

				int res = service.insCertificateBuId(o);

				Map<String,Object> map2=new HashMap<>();
				Map<String,Object> map=new HashMap<>();
				map.put("code",0);
				map.put("msg","");
				map.put("data",map2);

				map2.put("src","/file/"+ filePath );

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

	@RequestMapping(value = "uploadNewImg")
	@ResponseBody
	public Map<String,Object> uploadNewImg(@RequestParam(""
			+ "")MultipartFile file,MachineBean o,HttpServletRequest request){
		String prefix="";
		String dateStr="";
		//保存上传
		OutputStream out = null;
		InputStream fileInput=null;
		try{
			if(file!=null){

				String fileNewName = UUID.randomUUID().toString();
				String fileName = file.getOriginalFilename();
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateStr = simpleDateFormat.format(date);
				//String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//                   String saveDirectory = request.getSession().getServletContext().getRealPath("/machineImg");
				String mkdirsName = "newMachineImg"; // 机具管理-机具类型管理
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

				String filePath ="/newMachineImg" +"/"+fileNewName;
				File dest=new File(files,fileNewName);
				file.transferTo(dest);
				o.setPicUrl(filePath);

				int res = service.updateMachinesUrl(o);

				Map<String,Object> map2=new HashMap<>();
				Map<String,Object> map=new HashMap<>();
				map.put("code",0);
				map.put("msg","");
				map.put("data",map2);

				map2.put("src","/file/"+ filePath );

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

	@RequestMapping(value = "downFile")
	public void ShowImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("headerUrl") String url)
			throws IOException {
		FileInputStream fileIs = null;
		try {
//			String paths = request.getSession().getServletContext().getRealPath("");  
//			url = paths+url;
			String paths = "/data/gzimt";
			String path = paths + url;
			fileIs = new FileInputStream(path);
		} catch (Exception e) {
			return;
		}
		int i = fileIs.available();
		// 得到文件大小
		byte data[] = new byte[i];
		fileIs.read(data);
		// 读数据
		response.setContentType("image/*");
		// 设置返回的文件类型
		OutputStream outStream = response.getOutputStream();
		// 得到向客户端输出二进制数据的对象
		outStream.write(data);
		// 输出数据
		outStream.flush();
		outStream.close();
		fileIs.close();
	}
	@RequestMapping(value = "downFile1")
	public void showFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("headerUrl") String url)
			throws IOException {
		FileInputStream fileIs = null;
		try {
			fileIs = new FileInputStream(url);
		} catch (Exception e) {
			return;
		}
		int i = fileIs.available();
		// 得到文件大小
		byte data[] = new byte[i];
		fileIs.read(data);
		// 读数据
		response.setContentType("file/*");
		// 设置返回的文件类型
		OutputStream outStream = response.getOutputStream();
		// 得到向客户端输出二进制数据的对象
		outStream.write(data);
		// 输出数据
		outStream.flush();
		outStream.close();
		fileIs.close();
	}

	public String getCode(MachineBean o){
		List<MachineBean> list = new ArrayList<MachineBean>();
		String msg = "1";
		try {
			list = service.getCode(o);
			if(list.size() > 0){
				msg = "0";
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return msg;
	}


	@RequestMapping(value = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes importExcel(@RequestParam("file")MultipartFile file,HttpServletRequest request,MachineBean o) {
		AjaxRes ar = new AjaxRes();
		try {
			if(file!=null){
				String fileName = file.getOriginalFilename();
				String filePath = request.getSession().getServletContext().getRealPath("/excelUpload");
				File files = new File(filePath);
				if (!files.exists()) {
					files.mkdirs();
				}
				File dest=new File(files,fileName);
				file.transferTo(dest);
				String fileNewPath = filePath + "/" + fileName;
				List<List<String>> info = ExcelUtilsPlus.extractExcelFileInfo(fileNewPath);
				Integer result = service.updateInfo(info,o);
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




}