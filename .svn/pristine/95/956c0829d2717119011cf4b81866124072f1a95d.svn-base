package com.bonus.index.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.index.beans.IndexDetailVo;
import com.bonus.index.beans.IndexHomeBean;
import com.bonus.index.beans.IndexHomeResourseBean;
import com.bonus.index.beans.IndexProAndNum;
import com.bonus.index.beans.PartFiveBean;
import com.bonus.index.beans.PartSixBean;
import com.bonus.index.beans.PartThreeBean;
import com.bonus.index.service.IndexHomeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.UserShiroHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author bonus
 */
@Controller
@RequestMapping("/backstage/indexHome/")
public class IndexHomeController extends BaseController<IndexHomeBean> {

	@Autowired
	private IndexHomeService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/index/indexHome";
	}

	@RequestMapping("dataOverview/equipment/index")
	public String dataOverviewDetails(Model model) {
		return "/equipment/equipmentTypesDetail";
	}

	@RequestMapping("dataOverview/equipment/detail")
	public String showEquipmentTypeInfo(Model model) {
		return "/equipment/equipmentTypesInfo";
	}

	@RequestMapping("warning/info")
	public String warningInfo(Model model) {
		return "/warning/warningInfo";
	}

	@RequestMapping("menuSettings/index")
	public String menuSettingsIndex(Model model) {
		return "/index/menuSettings";
	}

	@RequestMapping("calendar/index")
	public String calendarIndex(Model model) {
		return "/equipment/calendarDetail";
	}

	@RequestMapping("todo/index")
	public String todoIndex(Model model) {
		return "/todo/todoList";
	}

	@RequestMapping("dataOverview/project/index")
	public String dataOverviewDetailsProject(Model model) {
		return "/equipment/projectDetail";
	}

	@RequestMapping("dataOverview/project/detail")
	public String dataOverviewDetailsProjectDetail(Model model) {
		return "/equipment/projectInfo";
	}
	
	@RequestMapping("calendarDetail")
	public String calendarDetail(Model model) {
		return "/index/calendarDetail";
	}

	@RequestMapping(value = "getPartOneData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPartOneData(IndexHomeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeBean> list = service.getPartOneData(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getPartTwoData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPartTwoData(IndexHomeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeBean> list = service.getPartTwoData(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getPartThreeData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPartThreeData(IndexHomeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<PartThreeBean> list = service.getPartThreeData(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getPartFourData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPartFourData(IndexHomeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<IndexHomeBean> list = service.getPartFourData(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getPartFiveData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPartFiveData(IndexHomeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<PartFiveBean> list = service.getPartFiveData(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "getPartSixData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPartSixData(IndexHomeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<PartSixBean> list;
			if (o == null) {
				o = new IndexHomeBean();
				o.setTime(DateTimeHelper.getNowMonth());
			}
			list = service.getPartSixData(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	// 获取首页常用菜单
	@RequestMapping(value = "getHomeResource", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getHomeResource(IndexHomeResourseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Integer userId = UserShiroHelper.getRealCurrentUser().getId();
			o.setUserId(userId + "");
			List<IndexHomeResourseBean> list = service.getHomeResource(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	// 首页常用资源
	@RequestMapping(value = "getResource", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getResource(IndexHomeResourseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Integer userId = UserShiroHelper.getRealCurrentUser().getId();
			o.setUserId(userId + "");
			List<IndexHomeResourseBean> list = service.getResource(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	// 保存首页常用资源
	@RequestMapping(value = "saveResourse", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes saveResourse(IndexHomeResourseBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Integer userId = UserShiroHelper.getRealCurrentUser().getId();
			o.setUserId(userId + "");
			int res = service.saveResourse(o);
			if (res > 0) {
				ar.setSucceed("保存成功！");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	/**
	 * 查询工程领料、退料、维修检验、机具报废、修试后入库、新购入库、库存盘点数量等数据
	 * 
	 * @param o
	 * @return
	 */

	@SuppressWarnings("null")
	@RequestMapping(value = "getProAndNum", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<IndexDetailVo> getProAndNum(IndexHomeBean o) {
		return service.getProAndNum(o);
	}

}
