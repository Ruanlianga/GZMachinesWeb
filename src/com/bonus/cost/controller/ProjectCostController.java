package com.bonus.cost.controller;

import com.bonus.cost.beans.ProjectCostCalculation;
import com.bonus.cost.beans.ProjectLeaseCostDetail;
import com.bonus.cost.beans.ProjectSettlement;
import com.bonus.cost.service.ProjectCostService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.UserShiroHelper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 阮世耀
 * @version : 1.0
 * @PackagePath: com.bonus.cost.controller
 * @CreateTime: 2025-05-14  15:09
 * @Description: 工程费用结算控制器
 */
@Controller
@RequestMapping("/backstage/projectCost/")
public class ProjectCostController extends BaseController<T> {

    @Resource
    private ProjectCostService projectCostService;

    @RequestMapping("list")
    public String index() {
        return "/projectCost/list";
    }
    
    @RequestMapping("detail")
    public String detail() {
        return "/projectCost/detail";
    }
    
    /**
     * 前往计算结果详情页面
     */
    @RequestMapping("calculation_detail")
    public String calculationDetail() {
        return "/projectCost/calculation_detail";
    }

    /**
     * 前往结算计算表单页面
     */
    @RequestMapping("calculation_form")
    public String calculationForm() {
        return "/projectCost/calculation_form";
    }

    /**
     * 查询工程领料明细
     */
    @RequestMapping("queryProjectLeaseDetails")
    @ResponseBody
    public AjaxRes queryProjectLeaseDetails(ProjectLeaseCostDetail o){
        AjaxRes ar = getAjaxRes();
        try {
            List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(o);
            Map<String,Object> p = new HashMap<>();
            p.put("list",result);
            ar.setSucceed(p);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }

    /**
     * 查询工程退料明细
     */
    @RequestMapping("queryProjectReturnDetails")
    @ResponseBody
    public AjaxRes queryProjectReturnDetails(ProjectLeaseCostDetail o){
        AjaxRes ar = getAjaxRes();
        try {
            List<ProjectLeaseCostDetail> result = projectCostService.queryProjectReturnDetails(o);
            Map<String,Object> p = new HashMap<>();
            p.put("list",result);
            ar.setSucceed(p);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }

    /**
     * 查询工程领料和退料明细（合并后按操作时间排序）
     */
    @RequestMapping("queryProjectLeaseAndReturnDetails")
    @ResponseBody
    public AjaxRes queryProjectLeaseAndReturnDetails(ProjectLeaseCostDetail o){
        AjaxRes ar = getAjaxRes();
        try {
            Map<String, Object> result = projectCostService.queryProjectLeaseAndReturnDetails(o);
            Map<String,Object> p = new HashMap<>();
            p.put("list",result);
            ar.setSucceed(p);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 保存结算记录
     */
    @RequestMapping("saveSettlement")
    @ResponseBody
    public AjaxRes saveSettlement(@RequestBody Map<String, Object> settlementData) {
        AjaxRes ar = getAjaxRes();
        try {
            Integer id = projectCostService.saveSettlement(settlementData, "1");
            ar.setSucceed(id);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 打印结算详情
     */
    @RequestMapping("printDetail")
    public String printDetail() {
        return "/projectCost/print";
    }

    /**
     * 计算结算数据
     */
    @RequestMapping("calculateSettlement")
    @ResponseBody
    public AjaxRes calculateSettlement(ProjectLeaseCostDetail o) {
        AjaxRes ar = getAjaxRes();
        try {
            Map<String, Object> result = projectCostService.calculateSettlement(o);
            ar.setSucceed(result);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }

    /**
     * 获取工程列表
     */
    @RequestMapping("getProjects")
    @ResponseBody
    public AjaxRes getProjects() {
        AjaxRes ar = getAjaxRes();
        try {
            List<Map<String, Object>> projects = projectCostService.getAllProjects();
            ar.setSucceed(projects);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 获取结算明细-领料
     */
    @RequestMapping("getSettlementLeaseDetails")
    @ResponseBody
    public AjaxRes getSettlementLeaseDetails(@RequestParam(value = "settlementId") Integer settlementId) {
        AjaxRes ar = getAjaxRes();
        try {
            List<ProjectLeaseCostDetail> details = projectCostService.getSettlementLeaseDetails(settlementId);
            Map<String, Object> result = new HashMap<>();
            result.put("data", details);
            result.put("count", details.size());
            ar.setSucceed(result);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 获取结算明细-退料
     */
    @RequestMapping("getSettlementReturnDetails")
    @ResponseBody
    public AjaxRes getSettlementReturnDetails(@RequestParam(value = "settlementId") Integer settlementId) {
        AjaxRes ar = getAjaxRes();
        try {
            List<ProjectLeaseCostDetail> details = projectCostService.getSettlementReturnDetails(settlementId);
            Map<String, Object> result = new HashMap<>();
            result.put("data", details);
            result.put("count", details.size());
            ar.setSucceed(result);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }

    /**
     * 保存计算结果
     */
    @RequestMapping("saveCalculation")
    @ResponseBody
    public AjaxRes saveCalculation(@RequestBody Map<String, Object> calculationData) {
        AjaxRes ar = getAjaxRes();
        try {
            Integer id = projectCostService.saveCalculation(calculationData, UserShiroHelper.getCurrentUser().getcId());
            ar.setSucceed(id,  "保存成功");
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 查询计算结果列表
     */
    @RequestMapping("queryCalculationList")
    @ResponseBody
    public AjaxRes queryCalculationList(ProjectCostCalculation calculation) {
        AjaxRes ar = getAjaxRes();
        try {
            List<ProjectCostCalculation> list = projectCostService.queryCalculationList(calculation);
            int count = list != null ? list.size() : 0;
            Map<String, Object> result = new HashMap<>();
            result.put("data", list);
            result.put("count", count);
            ar.setSucceed(result);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 获取计算结果详情
     */
    @RequestMapping("getCalculationDetail")
    @ResponseBody
    public AjaxRes getCalculationDetail(@RequestParam(value = "id") Integer id) {
        AjaxRes ar = getAjaxRes();
        try {
            ProjectCostCalculation calculation = projectCostService.getCalculationDetail(id);
            ar.setSucceed(calculation);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 删除计算结果
     */
    @RequestMapping("deleteCalculation")
    @ResponseBody
    public AjaxRes deleteCalculation(@RequestParam(value = "id") Integer id) {
        AjaxRes ar = getAjaxRes();
        try {
            boolean success = projectCostService.deleteCalculation(id);
            if (success) {
                ar.setSucceed("删除成功");
            } else {
                ar.setFailMsg("删除失败");
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(GlobalConst.DATA_FAIL);
        }
        return ar;
    }
    
    /**
     * 打印计算结果详情
     */
    @RequestMapping("printCalculationDetail")
    public String printCalculationDetail() {
        return "/projectCost/print_calculation";
    }
    
    /**
     * 导出费用计算表
     */
    @RequestMapping("exportCostCalculation")
    public void exportCostCalculation(@RequestParam(value = "id") Integer id, HttpServletResponse response) {
        try {
            projectCostService.exportCostCalculation(id, response);
        } catch (Exception e) {
            logger.error("导出费用计算表失败", e);
        }
    }

    /**
     * 导出物资区间费用表
     */
    @RequestMapping("exportSegmentCalculation")
    public void exportSegmentCalculation(@RequestParam(value = "id") Integer id, HttpServletResponse response) {
        try {
            projectCostService.exportSegmentCalculation(id, response);
        } catch (Exception e) {
            logger.error("导出物资区间费用表失败", e);
        }
    }

    /**
     * 导出领退记录表
     */
    @RequestMapping("exportOperationRecords")
    public void exportOperationRecords(@RequestParam(value = "id") Integer id, HttpServletResponse response) {
        try {
            projectCostService.exportOperationRecords(id, response);
        } catch (Exception e) {
            logger.error("导出领退记录表失败", e);
        }
    }
}