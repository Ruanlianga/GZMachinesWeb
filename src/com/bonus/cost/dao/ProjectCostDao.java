package com.bonus.cost.dao;

import com.bonus.core.BonusBatis;
import com.bonus.cost.beans.ProjectCostCalculation;
import com.bonus.cost.beans.ProjectCostCalculationDetail;
import com.bonus.cost.beans.ProjectCostCalculationSegment;
import com.bonus.cost.beans.ProjectLeaseCostDetail;
import com.bonus.cost.beans.ProjectSettlement;
import com.bonus.sys.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : 阮世耀
 * @version : 1.0
 * @PackagePath: com.bonus.cost.dao
 * @CreateTime: 2025-05-14  14:19
 * @Description: 描述
 */
@BonusBatis
public interface ProjectCostDao extends BaseDao<ProjectLeaseCostDetail> {

    List<ProjectLeaseCostDetail> queryProjectLeaseDetails(@Param("param") ProjectLeaseCostDetail o);

    List<ProjectLeaseCostDetail> queryProjectReturnDetails(@Param("param") ProjectLeaseCostDetail o);
    
    /**
     * 保存结算记录
     * @param settlement 结算记录
     * @return 影响行数
     */
    int saveSettlement(ProjectSettlement settlement);
    
    /**
     * 保存结算明细
     * @param settlementId 结算ID
     * @param detailList 明细列表
     * @param type 类型（1-领料，2-退料）
     * @return 影响行数
     */
    int saveSettlementDetails(Integer settlementId, List<ProjectLeaseCostDetail> detailList, Byte type);

    
    /**
     * 保存计算结果
     * @param calculation 计算结果
     * @return 影响行数
     */
    int saveCalculation(ProjectCostCalculation calculation);
    
    /**
     * 保存计算结果明细
     * @param detail 明细
     * @return 影响行数
     */
    int saveCalculationDetail(ProjectCostCalculationDetail detail);
    
    /**
     * 批量保存计算结果时间段
     * @param segments 时间段列表
     * @return 影响行数
     */
    int saveCalculationSegments(List<ProjectCostCalculationSegment> segments);
    
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
    ProjectCostCalculation getCalculationById(Integer id);
    
    /**
     * 查询计算结果明细
     * @param calculationId 计算结果ID
     * @return 明细列表
     */
    List<ProjectCostCalculationDetail> queryCalculationDetails(Integer calculationId);
    
    /**
     * 查询计算结果时间段
     * @param detailId 明细ID
     * @return 时间段列表
     */
    List<ProjectCostCalculationSegment> queryCalculationSegments(Integer detailId);
    
    /**
     * 删除计算结果（逻辑删除）
     * @param id 计算结果ID
     * @return 影响行数
     */
    int deleteCalculation(Integer id);
}
