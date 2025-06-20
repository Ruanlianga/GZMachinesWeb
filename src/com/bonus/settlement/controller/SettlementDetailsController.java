package com.bonus.settlement.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.exp.POIOutputHelper;
import com.bonus.settlement.beans.SettlementDetailsBean;
import com.bonus.settlement.beans.SettlementMoneyBean;
import com.bonus.settlement.calc.BalanceAgreementCalc;
import com.bonus.settlement.calc.BalanceAgreementModelResultBean;
import com.bonus.settlement.service.SettlementDetailsService;
import com.bonus.settlement.service.SettlementMoneyService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/settlementDetails/")
public class SettlementDetailsController extends BaseController<SettlementDetailsBean> {

	@Autowired
	private SettlementDetailsService service;

	@Autowired
	private SettlementMoneyService smService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/settlement/settlementDetails_list";
	}
	
	@RequestMapping("settlementApply")
	public String settlementApply(Model model) {
		return "/settlement/settlementApply_list";
	}

	@RequestMapping(value = "getSettlementApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getSettlementApply( SettlementDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<SettlementDetailsBean> list = new ArrayList<>();
			List<SettlementDetailsBean> result = service.getSettlementApply(o);
			for(SettlementDetailsBean bean : result){
				String leaseNum = bean.getLeaseNum();
				String planLeasePrice = bean.getPlanLeasePrice();
				String leasePrice = bean.getLeasePrice();
				String leaseDays = bean.getLeaseDays();
				String stopDays = bean.getStopDays();
				int leaseDay = Integer.parseInt(leaseDays);
				if(leasePrice != null){
					planLeasePrice = leasePrice;
					bean.setPlanLeasePrice(planLeasePrice);
				}
				if(stopDays != null){
				 int stopDay = Integer.parseInt(stopDays);
				 leaseDay = Integer.parseInt(leaseDays);
				 if(stopDay <= leaseDay){
					 leaseDay = leaseDay -stopDay;
				 }
				}
				float leaseMoney = Float.parseFloat(leaseNum)*Float.parseFloat(planLeasePrice)*leaseDay;
				String leaseMoneys = StringHelper.formatData(leaseMoney);
				bean.setLeaseMoney(leaseMoneys);
				list.add(bean);
				//service.update(bean);
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
	
	@RequestMapping(value = "editStopDays", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes editStopDays(SettlementDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			SettlementDetailsBean sdBean = service.findSettlementInfo(o);
			if(sdBean != null){
				String batch = sdBean.getBatch();
				o.setBatch(batch);
			}
		    service.editStopDays(o);
		 	ar.setSucceedMsg("提交成功");
		} catch (Exception e) {
			ar.setFailMsg("提交失败");
			logger.error(e.toString(), e);
		}
		return ar;
	}
	@RequestMapping(value = "editLeasePrice", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes editLeasePrice(SettlementDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
		    service.editLeasePrice(o);
		 	ar.setSucceedMsg("修改成功");
		} catch (Exception e) {
			ar.setFailMsg("修改失败");
			logger.error(e.toString(), e);
		}
		return ar;
	}
	@RequestMapping(value = "editDeduction", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes editDeduction(SettlementDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
		    service.editDeduction(o);
		 	ar.setSucceedMsg("填写成功");
		} catch (Exception e) {
			ar.setFailMsg("填写失败");
			logger.error(e.toString(), e);
		}
		return ar;
	}
	@RequestMapping(value = "getLeaseList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getLeaseList(SettlementDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			List<BalanceAgreementModelResultBean> ba = new ArrayList<>();
			String settlementDate = o.getSettlementTime();
			float leaseMoney = 0;
			float leaseTotal = 0;
			List<SettlementDetailsBean> collerList = service.getLeaseList(o);
			List<SettlementDetailsBean> backList = service.getBackList(o);
			if (StringHelper.isEmpty(settlementDate)) {
				settlementDate = DateTimeHelper.getNowDate();
			}
			BalanceAgreementCalc calc = new BalanceAgreementCalc(settlementDate);
			if(collerList.size() > 0 && collerList!=null){
				calc.calc(collerList, backList);
				List<BalanceAgreementModelResultBean> rs = calc.getResults();
				ba = groupByResult(rs);
				List<SettlementDetailsBean> lmList = leaseMoneyDetailsResult(ba, o);//结算租赁费
			}
			
			//list.addAll(lmList);
			List<SettlementDetailsBean> list = service.findSettlementApply(o);
			if(list.size() > 0 && list !=null){
				for(int i = 0;i < list.size(); i++){
					String planLeasePrice = list.get(i).getPlanLeasePrice();
					String leasePrice = list.get(i).getLeasePrice();
					String leaseDays = list.get(i).getLeaseDays();
					String stopDays = list.get(i).getStopDays();
					String leaseNum = list.get(i).getLeaseNum();
					int leaseDay = Integer.parseInt(leaseDays);
					if(leasePrice!=null){
						planLeasePrice = leasePrice;
						list.get(i).setPlanLeasePrice(planLeasePrice);
					}
					if(stopDays!=null){
						int stopDay = Integer.parseInt(stopDays);
						 if(stopDay <= leaseDay){
							 leaseDay = leaseDay -stopDay;
						 }
					}
				    leaseMoney = Float.parseFloat(leaseNum)*Float.parseFloat(planLeasePrice)*leaseDay;
				    leaseTotal = leaseTotal + leaseMoney;
					String leaseMoneys = StringHelper.formatData(leaseMoney);
				    list.get(i).setLeaseMoney(leaseMoneys);
				}
				String lmTotal = StringHelper.formatData(leaseTotal);
				list.get(list.size() - 1).setLeaseTotal(lmTotal);
				String flag = request.getParameter("flag");// js请求参数flag
				if ("2".equals(flag)) {
					if (collerList == null || collerList.isEmpty()) {
						logger.warn("未找到租赁信息");
					} else {
						SettlementMoneyBean smBean = new SettlementMoneyBean();
						// clear(agreementId);
						int index = 0;
						for (SettlementDetailsBean bean : list) {
							if (index == 0) {
								index++;
								smBean.setAgreementId(bean.getAgreementId());
								smBean.setType("1");
								smBean.setBatch(o.getBatch());
								smService.delete(smBean);
							}
							smBean.setTypeId(bean.getTypeId());
							smBean.setNum(bean.getLeaseNum() + "");
							smBean.setStartTime(bean.getLeaseDate());
							smBean.setNums(bean.getReturnNum() + "");
							smBean.setEndTime(bean.getReturnDate());
							smBean.setMoney(bean.getLeaseMoney() + "");
							smBean.setBatch(o.getBatch());
							smBean.setIsSure("1");//结算申请
							smBean.setIsSettlement("1");//未结算
							smService.insert(smBean);// 确认结算往数据库insert租赁费
						}
					}
				}
				if ("3".equals(flag)) {
					if (collerList == null || collerList.isEmpty()) {
						logger.warn("未找到租赁信息");
					} else {
						SettlementMoneyBean smBean = new SettlementMoneyBean();
						// clear(agreementId);
						int index = 0;
						for (SettlementDetailsBean bean : list) {
							if (index == 0) {
								index++;
								smBean.setAgreementId(bean.getAgreementId());
								smBean.setType("1");
								smBean.setBatch(o.getBatch());
								smService.delete(smBean);
							}
							smBean.setTypeId(bean.getTypeId());
							smBean.setNum(bean.getLeaseNum() + "");
							smBean.setStartTime(bean.getLeaseDate());
							smBean.setNums(bean.getReturnNum() + "");
							smBean.setEndTime(bean.getReturnDate());
							smBean.setMoney(bean.getLeaseMoney() + "");
							smBean.setBatch(o.getBatch());
							smBean.setIsSure("1");
							smBean.setIsSettlement("1");//未结算
							smService.insert(smBean);// 确认结算往数据库insert租赁费
						}
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
	
	private List<SettlementDetailsBean> leaseMoneyDetailsResult(List<BalanceAgreementModelResultBean> rs,
			SettlementDetailsBean o) throws ParseException {
		List<SettlementDetailsBean> lmList = new ArrayList<SettlementDetailsBean>();
		float leaseMoney = 0;
		String startTime = null;
		String batch = null;
		int batchs = 0;
		SettlementDetailsBean sdBean = service.findSettlementInfo(o);
		if(sdBean != null){
			String settlementTime = sdBean.getSettlementTime();
			 batch = sdBean.getBatch();
			if(batch.isEmpty()||batch==null){
				batch = "1";
			}
		    batchs = Integer.parseInt(batch);
			batchs = batchs + 1;
			if(settlementTime !=null){
				startTime = settlementTime;
			}
		}else{
			logger.warn("未申请结算");
		}
		for (int j = 0; j < rs.size(); j++) {
			SettlementDetailsBean bean = new SettlementDetailsBean();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化为年月
			String agreementId = rs.get(j).getAgreementCode();
			String deviceName = rs.get(j).getDeviceName();
			String deviceModel = rs.get(j).getDeviceModel();
			String backDate = rs.get(j).getReturnDate();
			String leaseDate = rs.get(j).getCollar().getLeaseDate();
			String settlementDate = rs.get(j).getSettlementDate();
			String typeId = rs.get(j).getTypeId();
			float leaseNum = rs.get(j).getLeaseNum();
			float backNum = rs.get(j).getReturnNum();
			Date beginTime = sdf.parse(leaseDate);
			Date closeTime = sdf.parse(backDate);
			Date switchTime = sdf.parse(settlementDate);
			if( startTime != null ){
				beginTime = sdf.parse(startTime);
				leaseDate = startTime;
			}else{
				startTime = leaseDate;
			}
			if(beginTime.before(closeTime)){
				if (isEffectiveDate(switchTime, beginTime, closeTime)) {
					int days = DateTimeHelper.calcDate(startTime, settlementDate)+1;
					leaseMoney = leaseNum * days * rs.get(j).getLeasePrice();
					//System.out.println(leaseMoney);
					if(leaseMoney < 0){
						leaseMoney = 0 ;
					}
					leaseMoney = StringHelper.format2Data(leaseMoney);
					bean.setAgreementId(agreementId);
					bean.setDeviceName(deviceName);
					bean.setDeviceModel(deviceModel);
					bean.setTypeId(typeId);
					bean.setLeaseNum(leaseNum+"");
					bean.setLeaseDate(leaseDate);
					bean.setReturnNum(backNum+"");
					bean.setReturnDate(backDate);
					bean.setLeaseDays(days+"");
					bean.setSettlementTime(settlementDate);
					bean.setLeaseMoney(leaseMoney+"");
					if(batch != null && batchs > 0){
						bean.setBatch(batchs+"");
					}else{
						bean.setBatch("1");
					}
					lmList.add(bean);
					service.insert(bean);
			}
			}
	
		}
		return lmList;
	}

	private List<BalanceAgreementModelResultBean> groupByResult(List<BalanceAgreementModelResultBean> rs) throws ParseException {
		Map<String, BalanceAgreementModelResultBean> maps = new HashMap<String, BalanceAgreementModelResultBean>();
		for (BalanceAgreementModelResultBean bean : rs) {
			String returnDate = bean.getReturnDate();
			String settlementDate = bean.getSettlementDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化为年月
			Date backTime = sdf.parse(returnDate);
			Date tempTime = sdf.parse(settlementDate);
			if (backTime.before(tempTime)) {
				settlementDate = returnDate;
				bean.setSettlementDate(returnDate);
			}
			if (bean.getLeaseMoney() > 0) {
				String key = bean.getTypeId() + ":" + bean.getLeaseNum() + bean.getCollar().getLeaseDate()
						+ bean.getReturnDate() + bean.getSettlementDate();
				maps.put(key, bean);
			}
		}
		Set<String> keys = maps.keySet();
		List<BalanceAgreementModelResultBean> list = new ArrayList<BalanceAgreementModelResultBean>();
		for (String key : keys) {
			BalanceAgreementModelResultBean tmps = maps.get(key);
			list.add(tmps);
		}
		return list;
	}
	
	@RequestMapping(value = "getLoseList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getLoseList( SettlementDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			List<SettlementDetailsBean> list = service.getLoseList(o);
            if(list != null && list.size() > 0){
            	float loseTotal = 0;
    			for(SettlementDetailsBean bean : list){
    				String loseMoney = bean.getLoseMoney();
    				loseTotal = loseTotal +Float.parseFloat(loseMoney);
    				
    				String flag = request.getParameter("flag");
    				if ("3".equals(flag)) {
    					SettlementMoneyBean smBean = new SettlementMoneyBean();
    					int index = 0;
    					for (SettlementDetailsBean sdBean : list) {
    						if (index == 0) {
    							index++;
    							smBean.setAgreementId(bean.getAgreementId());
    							smBean.setType("2");
    							smBean.setBatch(o.getBatch());
    							smService.delete(smBean);
    						}
    						smBean.setTypeId(sdBean.getTypeId());
    						smBean.setNum(sdBean.getLoseNum());
    						smBean.setMoney(sdBean.getLoseMoney());
    						smBean.setBatch(o.getBatch());
    						smBean.setIsSure("1");
    						smBean.setIsSettlement("1");//未结算
    						smService.insert(smBean);
    					}
    				}
    			}
    			String lsTotal = StringHelper.formatData(loseTotal);
    			list.get(list.size() - 1).setLoseTotal(lsTotal);
    		
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
	/*@RequestMapping(value = "getRepairList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getRepairList( SettlementDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			List<SettlementDetailsBean> list = service.getRepairList(o);
			if(list != null && list.size() > 0){
				float repairTotal = 0; 
				for(SettlementDetailsBean bean : list){
					String repairMoney = bean.getRepairMoney();
					repairTotal = repairTotal +Float.parseFloat(repairMoney);
					String flag = request.getParameter("flag");
					if ("2".equals(flag)) {
						SettlementMoneyBean smBean = new SettlementMoneyBean();
						int index = 0;
						for (SettlementDetailsBean sdBean : list) {
							if (index == 0) {
								index++;
								smBean.setAgreementId(sdBean.getAgreementId());
								smBean.setType("3");
								smService.delete(smBean);
							}
							smBean.setTypeId(bean.getTypeId());
							String repairNum = sdBean.getRepairNum();
							String repMoney = sdBean.getRepairMoney();
							smBean.setNum(repairNum);
							smBean.setMoney(repMoney);
							smService.insert(smBean);
						}
					}
				}
				String rpTotal = StringHelper.formatData(repairTotal);
				list.get(list.size() - 1).setLoseTotal(rpTotal);
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}*/
	@RequestMapping(value = "getScrapList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getScrapList( SettlementDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			List<SettlementDetailsBean> list = service.getScrapList(o);
            if(list != null && list.size() > 0){
            	float scrapTotal = 0;
    			for(SettlementDetailsBean bean : list){
    				String scrapMoney = bean.getScrapMoney();
    				scrapTotal = scrapTotal + Float.parseFloat(scrapMoney);
    				String flag = request.getParameter("flag");
					if ("3".equals(flag)) {
						SettlementMoneyBean smBean = new SettlementMoneyBean();
						int index = 0;
						for (SettlementDetailsBean sdBean : list) {
							if (index == 0) {
								index++;
								smBean.setAgreementId(sdBean.getAgreementId());
								smBean.setBatch(o.getBatch());
								smBean.setType("4");
								smService.delete(smBean);
							}
							smBean.setTypeId(sdBean.getTypeId());
							smBean.setNum(sdBean.getScrapNum());
							smBean.setMoney(sdBean.getScrapMoney());
							smBean.setBatch(o.getBatch());
							smBean.setIsSure("1");
							smBean.setIsSettlement("1");//未结算
							smService.insert(smBean);
						}
					}
    			}
    			String spTotal = StringHelper.formatData(scrapTotal);
    			list.get(list.size() - 1).setScrapTotal(spTotal);
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
	@RequestMapping(value = "findTitle", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findTittle(SettlementDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			SettlementDetailsBean bean = service.findTitle(o);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "selectBatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes selectBatch(SettlementDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<SettlementDetailsBean> list = service.selectBatch(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "getDeductionList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getDeductionList(SettlementDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<SettlementDetailsBean> list = service.getDeductionList(o);
	        if(list != null && list.size() > 0){
            	float deductionTotal = 0;
    			for(SettlementDetailsBean bean : list){
    				String deductionMoney = bean.getDeductionMoney();
    				deductionTotal = deductionTotal + Float.parseFloat(deductionMoney);
    			}
    			String ddTotal = StringHelper.formatData(deductionTotal);
    			list.get(list.size() - 1).setDeductionTotal(ddTotal);
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
	/**
	 * 结算明细导出
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param o
	 */

	@RequestMapping("getLeaseExcel")
	public void expExcel(HttpServletRequest request, HttpServletResponse response, SettlementDetailsBean o) {
		try {
			SettlementDetailsBean bean = service.findTitle(o);
			String unitName = bean.getLeaseName();
			String projectName = bean.getProjectName();
			String downLoadName = projectName + "-" + unitName;
			String fileName = downLoadName + "租赁费用结算明细";
			List<SettlementDetailsBean> list = service.findSettlementApply(o);
			for(int i = 0;i < list.size(); i++){
				String planLeasePrice = list.get(i).getPlanLeasePrice();
				String leasePrice = list.get(i).getLeasePrice();
				String leaseDays = list.get(i).getLeaseDays();
				String stopDays = list.get(i).getStopDays();
				int leaseDay = Integer.parseInt(leaseDays);
				if(leasePrice!=null){
					planLeasePrice = leasePrice;
					list.get(i).setPlanLeasePrice(planLeasePrice);
				}
				if(stopDays!=null){
					int stopDay = Integer.parseInt(stopDays);
					 if(stopDay <= leaseDay){
						 leaseDay = leaseDay -stopDay;
					 }
				}
			}
			expOutExcel(response, list, fileName);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutExcel(HttpServletResponse response, List<SettlementDetailsBean> rs, String filename)
			throws Exception {
		if (rs != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = rs.size();
			for (int i = 0; i < size; i++) {
				SettlementDetailsBean bean = rs.get(i);
				Map<String, Object> maps = outSettlementDetailsBeanToMap(i, bean);
				results.add(maps);
			}
			List<String> headers = settlementDetailsHeader();
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

	private Map<String, Object> outSettlementDetailsBeanToMap(int i, SettlementDetailsBean bean) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("deviceName", bean.getDeviceName());
		maps.put("deviceModel", bean.getDeviceModel());
		maps.put("deviceUnit", bean.getDeviceUnit());
		maps.put("planLeasePrice", bean.getPlanLeasePrice());
		maps.put("leaseNum", bean.getLeaseNum());
		maps.put("leaseDate", bean.getLeaseDate());
		maps.put("returnNum", bean.getReturnNum());
		maps.put("returnDate", bean.getReturnDate());
		maps.put("stopDays", bean.getStopDays());
		maps.put("leaseDays", bean.getLeaseDays());
		maps.put("leaseMoney", bean.getLeaseMoney());
		maps.put("batch", bean.getBatch());
		return maps;
	}

	private List<String> settlementDetailsHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("设备名称");
		list.add("设备规格");
		list.add("单位");
		list.add("租赁单价（元）");
		list.add("租赁数量");
		list.add("租赁日期");
		list.add("归还数量");
		list.add("归还日期");
		list.add("停用天数（天）");
		list.add("租赁天数（天）");
		list.add("租赁费用（元）");
		list.add("结算批次（次）");
		return list;
	}
	/**
	 * 结算明细导出
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param o
	 */
	@RequestMapping("getLoseExcel")
	public void getNotYetExcel(HttpServletRequest request, HttpServletResponse response, SettlementDetailsBean o) {
		try {
			SettlementDetailsBean bean = service.findTitle(o);
			String unitName = bean.getLeaseName();
			String projectName = bean.getProjectName();
			String downLoadName = projectName + "-" + unitName;
			String fileName = downLoadName + "结算明细";
			List<SettlementDetailsBean> list = service.getLoseList(o);
			String keyWord = "丢失费用";
			if (StringHelper.isNotEmpty(keyWord)) {
				keyWord = "包含（" + keyWord + "）";
			} else {
				keyWord = "";
			}
			expOutNotYetExcel(response, list, fileName + keyWord);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expOutNotYetExcel(HttpServletResponse response, List<SettlementDetailsBean> list, String filename)
			throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				SettlementDetailsBean bean = list.get(i);
				Map<String, Object> maps = outNotYetBeanToMap(i, bean);
				results.add(maps);
			}

			List<String> headers = notYetHeader();
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

	private Map<String, Object> outNotYetBeanToMap(int i, SettlementDetailsBean bean) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("deviceName", bean.getDeviceName());
		maps.put("deviceModel", bean.getDeviceModel());
		maps.put("deviceUnit", bean.getDeviceUnit());
		maps.put("loseNum", bean.getLoseNum());
		maps.put("loseMoney", bean.getLoseMoney());
		return maps;
	}

	private List<String> notYetHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("设备名称");
		list.add("规格名称");
		list.add("单位");
		list.add("丢失数量");
		list.add("丢失费用（元）");
		return list;
	}
	
	/*@RequestMapping("getScrapExcel")
	public void getScarpExcel(HttpServletRequest request, HttpServletResponse response, SettlementDetailsBean o) {
		try {
			SettlementDetailsBean bean = service.findTitle(o);
			String unitName = bean.getLeaseName();
			String projectName = bean.getProjectName();
			String downLoadName = projectName + "-" + unitName;
			String fileName = downLoadName + "结算明细";
			List<SettlementDetailsBean> list = service.getScrapList(o);
			String keyWord = "报废费用";
			if (StringHelper.isNotEmpty(keyWord)) {
				keyWord = "包含（" + keyWord + "）";
			} else {
				keyWord = "";
			}
			expScarpExcel(response, list, fileName + keyWord);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	private void expScarpExcel(HttpServletResponse response, List<SettlementDetailsBean> list, String filename)
			throws Exception {
		if (list != null) {
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				SettlementDetailsBean bean = list.get(i);
				Map<String, Object> maps = outScarpBeanToMap(i, bean);
				results.add(maps);
			}

			List<String> headers = scarpHeader();
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

	private Map<String, Object> outScarpBeanToMap(int i, SettlementDetailsBean bean) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		maps.put("id", i + 1);
		maps.put("deviceName", bean.getDeviceName());
		maps.put("deviceModel", bean.getDeviceModel());
		maps.put("deviceUnit", bean.getDeviceUnit());
		maps.put("scrapNum", bean.getScrapNum());
		maps.put("scrapMoney", bean.getScrapMoney());
		String damage = bean.getDamage();
		if ("1".equals(damage)) {
			damage = "人为损坏";
		} else {
			damage = "自然损坏";
		}
		maps.put("damage", damage); 
		return maps;
	}

	private List<String> scarpHeader() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("设备名称");
		list.add("规格名称");
		list.add("单位");
		list.add("报废数量");
		list.add("报废费用（元）");
		list.add("备注");
		return list;
	}*/

	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * 
	 * @param nowTime
	 *            当前时间
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
}
