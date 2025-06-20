package com.bonus.cost.service;

import com.bonus.cost.beans.ProjectCostCalculation;
import com.bonus.cost.beans.ProjectLeaseCostDetail;
import com.bonus.cost.beans.ProjectSettlement;

import java.util.List;
import java.util.Map;

/**
 * @author : 阮世耀
 * @version : 1.0
 * @PackagePath: com.bonus.cost.service
 * @CreateTime: 2025-05-14  15:07
 * @Description: 描述
 */
public interface ProjectCostService {

    List<ProjectLeaseCostDetail> queryProjectLeaseDetails(ProjectLeaseCostDetail o);

    List<ProjectLeaseCostDetail> queryProjectReturnDetails(ProjectLeaseCostDetail o);
    
    /**
     * 查询工程领料和退料明细，合并后按操作时间排序
     * @param o 查询参数
     * @return 合并后的领料和退料明细列表
     */
    Map<String, Object> queryProjectLeaseAndReturnDetails(ProjectLeaseCostDetail o);
    
    /**
     * 保存结算记录
     * @param settlementData 结算数据
     * @param userId 用户ID
     * @return 结算ID
     */
    Integer saveSettlement(Map<String, Object> settlementData, String userId);

    /**
     * 计算结算信息
     * @param o 查询参数
     * @return 结算结果
     */
    Map<String, Object> calculateSettlement(ProjectLeaseCostDetail o);

    /**
     * 获取所有工程
     * @return 所有工程列表
     */
    List<Map<String, Object>> getAllProjects();

    List<ProjectLeaseCostDetail> getSettlementLeaseDetails(Integer settlementId);

    List<ProjectLeaseCostDetail> getSettlementReturnDetails(Integer settlementId);
    
    /**
     * 保存计算结果
     * @param calculationData 计算结果数据
     * @param userId 用户ID
     * @return 计算结果ID
     */
    Integer saveCalculation(Map<String, Object> calculationData, String userId);
    
    /**
     * 查询计算结果列表
     * @param calculation 查询条件
     * @return 计算结果列表
     */
    List<ProjectCostCalculation> queryCalculationList(ProjectCostCalculation calculation);
    
    /**
     * 获取计算结果详情
     * @param id 计算结果ID
     * @return 计算结果
     */
    ProjectCostCalculation getCalculationDetail(Integer id);
    
    /**
     * 删除计算结果
     * @param id 计算结果ID
     * @return 是否成功
     */
    boolean deleteCalculation(Integer id);
    
    /**
     * 导出费用计算表
     * @param id 计算结果ID
     * @param response HTTP响应对象
     * @throws Exception 导出异常
     */
    void exportCostCalculation(Integer id, javax.servlet.http.HttpServletResponse response) throws Exception;
    
    /**
     * 导出物资区间费用表
     * @param id 计算结果ID
     * @param response HTTP响应对象
     * @throws Exception 导出异常
     */
    void exportSegmentCalculation(Integer id, javax.servlet.http.HttpServletResponse response) throws Exception;
    
    /**
     * 导出领退记录表
     * @param id 计算结果ID
     * @param response HTTP响应对象
     * @throws Exception 导出异常
     */
    void exportOperationRecords(Integer id, javax.servlet.http.HttpServletResponse response) throws Exception;
}
