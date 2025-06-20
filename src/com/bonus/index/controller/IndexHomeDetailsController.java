package com.bonus.index.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.index.beans.IndexCheckWarnBean;
import com.bonus.index.beans.IndexHomeCalendarBean;
import com.bonus.index.beans.IndexHomeDetailsBean;
import com.bonus.index.beans.IndexHomeTaskBean;
import com.bonus.index.beans.IndexInuseWarnBean;
import com.bonus.index.beans.IndexProjectBean;
import com.bonus.index.beans.IndexStorageWarnBean;
import com.bonus.index.beans.IndexTodoWarnBean;
import com.bonus.index.beans.IndexTotalWarnBean;
import com.bonus.index.service.IndexHomeDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/indexHomeDetails/")
public class IndexHomeDetailsController extends BaseController<IndexHomeDetailsBean>{

	@Autowired 
	private IndexHomeDetailsService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/index/indexHome";
	}
	
	@RequestMapping(value = "getMaTypeDetails",method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaTypeDetails(IndexHomeDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeDetailsBean> list = service.getMaTypeDetails(o);
			if (o != null) {
				// 过滤物资类型
				if (o.getIsCount() != null && !o.getIsCount().trim().isEmpty()) {
					list.removeIf(item -> item == null || item.getIsCount() == null);
					if ("1".equals(o.getIsCount())) {
						list.removeIf(item -> "0".equals(item.getIsCount()));
					} else if ("0".equals(o.getIsCount())) {
						list.removeIf(item -> "1".equals(item.getIsCount()));
					}
				}

				// 过滤数量
				if (o.getQuantityFilter() != null && o.getQuantityFilter() > 0) {
					switch (o.getQuantityFilter()) {
						case 1:
							list.removeIf(item -> item.getMaTotal() == null || item.getMaTotal() < 1);
							break;
						case 2:
							list.removeIf(item -> item.getStorageNum() == null || item.getStorageNum() < 1);
							break;
						case 3:
							list.removeIf(item -> item.getInuseNum() == null || item.getInuseNum() < 1);
							break;
						case 4:
							list.removeIf(item -> item.getScrapNum() == null || item.getScrapNum() < 1);
							break;
					}
				}
			}
			ar.setSucceed("code", "0");
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getMaChangeInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaChangeDetails(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaChangeInfo(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getMaUseInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaUseInfo(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaUseInfo(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getMaChangeTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaChangeTask(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaChangeTask(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	// 领料出库
	@RequestMapping(value = "getMaOutTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaOutTask(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaOutTask(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getMaBackTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaBackTask(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaBackTask(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getMaScrapTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaScrapTask(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaScrapTask(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getMaPdTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaPdTask(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaPdTask(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getMaNewTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaNewTask(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaNewTask(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
    
	@RequestMapping(value = "getMaInTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMaInTask(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getMaInTask(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	// 需求计划到期预警
	@RequestMapping(value = "getAboutExpireWarn", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPlanDetailsAboutExpireList(IndexInuseWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexInuseWarnBean> aboutExpireList = service.getPlanDetailsAboutExpireList(o);
			ar.setSucceed(aboutExpireList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
    
	//库存不足
	@RequestMapping(value = "getStorageWarn", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getStorageWarn(IndexStorageWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexStorageWarnBean> list = service.getStorageWarn(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//检验周期
	@RequestMapping(value = "getCheckWarn", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCheckWarn(IndexCheckWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexCheckWarnBean> list = service.getCheckWarn(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
		
	//长期占用
	@RequestMapping(value = "getInUseWarn", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getInUseWarn(IndexInuseWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexInuseWarnBean> list = service.getInUseWarn(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
				
	//保有量变化
	@RequestMapping(value = "getTotalWarn", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getTotalWarn(IndexTotalWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexTotalWarnBean> list = service.getTotalChangeWarn(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
				
	//差缺工程详细
	@RequestMapping(value = "getProjectDiff", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getProjectDiff(IndexProjectBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexProjectBean> list = service.getProjectDiff(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
				

	//差缺工程分公司
	@RequestMapping(value = "getProjectCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getProjectCompany(IndexProjectBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexProjectBean> list = service.getProjectCompany(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	//差缺工程明细 -- 传入projectId
	@RequestMapping(value = "getProjectMaDiff", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getProjectMaDiff(IndexProjectBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexProjectBean> list = service.getProjectMaDiff(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//差缺工程机具记录-- 传入projectId
	@RequestMapping(value = "getProjectMaRecord", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getProjectMaRecord(IndexHomeTaskBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeTaskBean> list = service.getProjectMaRecord(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	

	//待办--全部
	@RequestMapping(value = "getTodoList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getTodoList(IndexTodoWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexTodoWarnBean> list = service.getTodoList(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getToOutList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getToOutList(IndexTodoWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexTodoWarnBean> list = service.getToOutList(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getToBackList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getToBackList(IndexTodoWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexTodoWarnBean> list = service.getToBackList(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getToNewList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getToNewList(IndexTodoWarnBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexTodoWarnBean> list = service.getToNewList(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	//日历领-传点击年月日
	@RequestMapping(value = "getCalendarOut", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCalendarOut(IndexHomeCalendarBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeCalendarBean> list = service.getCalendarOut(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	//日历退-传点击年月日
	@RequestMapping(value = "getCalendarBack", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCalendarBack(IndexHomeCalendarBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeCalendarBean> list = service.getCalendarBack(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	//日历维修-传点击年月日
	@RequestMapping(value = "getCalendarRepair", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCalendarRepair(IndexHomeCalendarBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeCalendarBean> list = service.getCalendarRepair(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	//日历报废-传点击年月日
	@RequestMapping(value = "getCalendarScrap", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCalendarScrap(IndexHomeCalendarBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeCalendarBean> list = service.getCalendarScrap(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	//日历修试-传点击年月日
	@RequestMapping(value = "getCalendarRepairInput", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCalendarRepairInput(IndexHomeCalendarBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeCalendarBean> list = service.getCalendarRepairInput(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	//日历新购-传点击年月日
	@RequestMapping(value = "getCalendarNewInput", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCalendarNewInput(IndexHomeCalendarBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeCalendarBean> list = service.getCalendarNewInput(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
		
	//日历盘点-传点击年月日
	@RequestMapping(value = "getCalendarPd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCalendarPd(IndexHomeCalendarBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeCalendarBean> list = service.getCalendarPd(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}


}
