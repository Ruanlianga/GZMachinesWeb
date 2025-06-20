package com.bonus.cost.service;

import com.bonus.core.ExcelUtils;
import com.bonus.cost.beans.ProjectCostCalculation;
import com.bonus.cost.beans.ProjectCostCalculationDetail;
import com.bonus.cost.beans.ProjectCostCalculationSegment;
import com.bonus.cost.beans.ProjectLeaseCostDetail;
import com.bonus.cost.beans.ProjectSettlement;
import com.bonus.cost.dao.ProjectCostDao;
import com.bonus.plan.beans.PlanDevBean;
import com.bonus.plan.beans.PlanProBean;
import com.bonus.plan.dao.PlanApplicationDao;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : 阮世耀
 * @version : 1.0
 * @PackagePath: com.bonus.cost.service
 * @CreateTime: 2025-05-14  15:08
 */
@Service("projectCost")
public class ProjectCostServiceImpl implements ProjectCostService{

    @Autowired
    private ProjectCostDao projectCostDao;

    @Autowired
    private PlanApplicationDao planApplicationDao;

    /**
     * 安全转换为Double类型
     * @param obj 要转换的对象
     * @param defaultValue 默认值
     * @return 转换后的Double值
     */
    private Double safeToDouble(Object obj, Double defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        
        if (obj instanceof Double) {
            return (Double) obj;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        } else if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        } else if (obj instanceof String) {
            try {
                return Double.parseDouble((String) obj);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        
        // 其他类型尝试转换为字符串再解析
        try {
            return Double.parseDouble(String.valueOf(obj));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * 安全转换为Integer类型
     * @param obj 要转换的对象
     * @param defaultValue 默认值
     * @return 转换后的Integer值
     */
    private Integer safeToInteger(Object obj, Integer defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        
        if (obj instanceof Integer) {
            return (Integer) obj;
        } else if (obj instanceof Double) {
            return ((Double) obj).intValue();
        } else if (obj instanceof Number) {
            return ((Number) obj).intValue();
        } else if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        
        // 其他类型尝试转换为字符串再解析
        try {
            return Integer.parseInt(String.valueOf(obj));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * 安全转换为String类型
     * @param obj 要转换的对象
     * @param defaultValue 默认值
     * @return 转换后的String值
     */
    private String safeToString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        
        if (obj instanceof String) {
            return (String) obj;
        }
        
        return String.valueOf(obj);
    }

    @Override
    public List<ProjectLeaseCostDetail> queryProjectLeaseDetails(ProjectLeaseCostDetail o) {
        return projectCostDao.queryProjectLeaseDetails(o);
    }

    @Override
    public List<ProjectLeaseCostDetail> queryProjectReturnDetails(ProjectLeaseCostDetail o) {
        return projectCostDao.queryProjectReturnDetails(o);
    }

    @Override
    public Map<String, Object> queryProjectLeaseAndReturnDetails(ProjectLeaseCostDetail o) {
        // 获取领料明细
        List<ProjectLeaseCostDetail> leaseDetails = queryProjectLeaseDetails(o);
        // 获取退料明细
        List<ProjectLeaseCostDetail> returnDetails = queryProjectReturnDetails(o);

        // 创建一个新的列表来存储合并后的结果
        List<ProjectLeaseCostDetail> mergedList = new ArrayList<>();
        if (leaseDetails != null) {
            mergedList.addAll(leaseDetails);
        }
        if (returnDetails != null) {
            mergedList.addAll(returnDetails);
        }

        // 按操作时间排序，并添加空值检查
        Map<String, Object> result = new HashMap<>();
        result.put("details", mergedList.stream()
                // 过滤掉 null 元素
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(
                        ProjectLeaseCostDetail::getOperateTime,
                        // 处理 getOperateTime() 为 null 的情况
                        Comparator.nullsFirst(Comparator.naturalOrder())
                ))
                .collect(Collectors.toList()));
        return result;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveSettlement(Map<String, Object> settlementData, String userId) {
        // 获取当前用户信息
        UserBean user = UserShiroHelper.getRealCurrentUser();
        String currentUser = user != null ? user.getLoginName() : userId;
        
        // 处理结算数据
        String projectId = (String) settlementData.get("projectId");
        String projectName = (String) settlementData.get("projectName");
        String startTime = (String) settlementData.get("startTime");
        String endTime = (String) settlementData.get("endTime");
        
        @SuppressWarnings("unchecked")
        List<ProjectLeaseCostDetail> leaseData = (List<ProjectLeaseCostDetail>) settlementData.get("leaseData");
        
        @SuppressWarnings("unchecked")
        List<ProjectLeaseCostDetail> returnData = (List<ProjectLeaseCostDetail>) settlementData.get("returnData");
        
        // 创建结算记录
        ProjectSettlement settlement = new ProjectSettlement();
        settlement.setProjectId(projectId);
        settlement.setProjectName(projectName);
        settlement.setStartTime(startTime);
        settlement.setEndTime(endTime);
        settlement.setLeaseCount(leaseData != null ? leaseData.size() : 0);
        settlement.setReturnCount(returnData != null ? returnData.size() : 0);
        settlement.setCreateTime(LocalDateTime.now());
        settlement.setCreateUser(currentUser);
        // 0-正常
        settlement.setDelFlag((byte) 0);
        
        // 保存结算记录
        projectCostDao.saveSettlement(settlement);
        Integer settlementId = settlement.getId();
        
        // 保存领料明细
        if (leaseData != null && !leaseData.isEmpty()) {
            projectCostDao.saveSettlementDetails(settlementId, leaseData, (byte) 1);
        }
        
        // 保存退料明细
        if (returnData != null && !returnData.isEmpty()) {
            projectCostDao.saveSettlementDetails(settlementId, returnData, (byte) 2);
        }
        
        return settlementId;
    }

    @Override
    public Map<String, Object> calculateSettlement(ProjectLeaseCostDetail o) {
        // 获取领料明细
        List<ProjectLeaseCostDetail> leaseDetails = queryProjectLeaseDetails(o);
        // 获取退料明细
        List<ProjectLeaseCostDetail> returnDetails = queryProjectReturnDetails(o);

        // 创建一个新的列表来存储合并后的结果
        List<ProjectLeaseCostDetail> mergedList = new ArrayList<>();
        if (leaseDetails != null) {
            mergedList.addAll(leaseDetails);
        }
        if (returnDetails != null) {
            mergedList.addAll(returnDetails);
        }

        // 按照machineTypeId分组进行计算
        Map<String, List<ProjectLeaseCostDetail>> groupedByMachineType = mergedList.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getMachineTypeId() != null)
                .collect(Collectors.groupingBy(
                        item -> String.valueOf(item.getMachineTypeId())
                ));
        
        // 存储计算结果
        List<Map<String, Object>> calculationResults = new ArrayList<>();
        double totalAmount = 0.0;
        
        // 获取统计开始和结束日期
        String startTimeStr = o.getStartTime(); 
        String endTimeStr = o.getEndTime();
        
        // 转换为日期对象，如果为空则使用默认值
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        
        try {
            if (startTimeStr != null && !startTimeStr.isEmpty()) {
                // 尝试不同格式解析日期
                try {
                    // 尝试标准ISO格式 (yyyy-MM-ddTHH:mm:ss)
                    startDate = LocalDateTime.parse(startTimeStr);
                } catch (Exception e1) {
                    try {
                        // 尝试日期格式 (yyyy-MM-dd)
                        startDate = LocalDateTime.parse(startTimeStr + "T00:00:00");
                    } catch (Exception e2) {
                        // 使用默认值
                        System.out.println("无法解析开始日期: " + startTimeStr + ", 使用默认值");
                        startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
                    }
                }
            } else {
                // 默认使用当月第一天
                startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
            }
            
            if (endTimeStr != null && !endTimeStr.isEmpty()) {
                // 尝试不同格式解析日期
                try {
                    // 尝试标准ISO格式 (yyyy-MM-ddTHH:mm:ss)
                    endDate = LocalDateTime.parse(endTimeStr);
                } catch (Exception e1) {
                    try {
                        // 尝试日期格式 (yyyy-MM-dd)
                        endDate = LocalDateTime.parse(endTimeStr + "T23:59:59");
                    } catch (Exception e2) {
                        // 使用默认值
                        System.out.println("无法解析结束日期: " + endTimeStr + ", 使用默认值");
                        endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
                    }
                }
            } else {
                // 默认使用当天
                endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
            }
        } catch (Exception e) {
            // 解析日期失败，使用默认值
            System.out.println("解析日期出错: " + e.getMessage() + ", 使用默认值");
            startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
            endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        }
        
        // 对每种物资类型进行计算
        for (Map.Entry<String, List<ProjectLeaseCostDetail>> entry : groupedByMachineType.entrySet()) {
            String machineTypeId = entry.getKey();
            List<ProjectLeaseCostDetail> items = entry.getValue();
            
            // 按操作时间排序
            items.sort(Comparator.comparing(
                    ProjectLeaseCostDetail::getOperateTime,
                    Comparator.nullsFirst(Comparator.naturalOrder())
            ));
            
            // 获取该物资类型的第一条记录，用于提取物资名称等信息
            ProjectLeaseCostDetail firstItem = items.get(0);
            double unitPrice = firstItem.getPrice() != null ? firstItem.getPrice() : 0;
            
            // 计算租赁数量和金额
            int currentCount = 0;
            double totalItemAmount = 0.0;
            Map<String, Object> timeLineData = new HashMap<>(); // 存储时间线上的数量变化
            List<Map<String, Object>> segments = new ArrayList<>(); // 存储各个时间段
            
            LocalDateTime previousTime = startDate;
            
            // 计算第一次领料时间和最后一次退料时间
            LocalDateTime firstLeaseTime = null;
            LocalDateTime lastReturnTime = null;
            
            for (ProjectLeaseCostDetail item : items) {
                if (item == null || item.getOperateTime() == null) {continue;}

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                LocalDateTime operateTime = LocalDateTime.parse(item.getOperateTime(), formatter);

                // 如果操作时间超出统计范围，跳过
                if (operateTime.isBefore(startDate) || operateTime.isAfter(endDate)) {
                    continue;
                }
                
                String operateTimeStr = operateTime.toString();
                
                // 判断操作类型：1-领料，2-退料
                boolean isLease = (item.getOperateType() == null || item.getOperateType() == 1);
                int quantity = 0;
                
                // 计算上一个时间点到当前操作时间的租赁费用
                if (currentCount > 0 && !previousTime.equals(operateTime)) {
                    // 计算两个时间点之间的天数
                    long daysBetween = java.time.Duration.between(previousTime, operateTime).toDays();
                    // 不足一天按一天计算
                    if (daysBetween < 1) {daysBetween = 1;}
                    
                    // 计算该时间段的租赁费用
                    double segmentAmount = currentCount * unitPrice * daysBetween;
                    totalItemAmount += segmentAmount;
                    
                    // 记录该时间段
                    Map<String, Object> segment = new HashMap<>();
                    segment.put("startTime", previousTime.toString());
                    segment.put("endTime", operateTime.toString());
                    segment.put("days", daysBetween);
                    segment.put("count", currentCount);
                    segment.put("amount", segmentAmount);
                    segments.add(segment);
                    
                    // 添加调试信息
                    System.out.println("物资ID: " + machineTypeId + 
                            ", 时段: " + previousTime + " 至 " + operateTime + 
                            ", 天数: " + daysBetween +
                            ", 数量: " + currentCount + 
                            ", 单价: " + unitPrice + 
                            ", 段金额: " + segmentAmount);
                }
                
                // 更新当前在用数量
                if (isLease) {
                    quantity = item.getLeaseNum() != null ? item.getLeaseNum() : 0;
                    currentCount += quantity;
                    // 记录第一次领料时间
                    if (firstLeaseTime == null || operateTime.isBefore(firstLeaseTime)) {
                        firstLeaseTime = operateTime;
                    }
                } else {
                    quantity = item.getReturnNum() != null ? item.getReturnNum() : 0;
                    currentCount -= quantity;
                    // 记录最后一次退料时间
                    if (lastReturnTime == null || operateTime.isAfter(lastReturnTime)) {
                        lastReturnTime = operateTime;
                    }
                }
                
                // 更新前一个时间点
                previousTime = operateTime;
                
                // 记录时间线上的数量变化
                timeLineData.put(operateTimeStr, currentCount);
            }
            
            // 计算最后一个操作时间点到统计结束时间的租赁费用
            if (currentCount > 0 && !previousTime.equals(endDate)) {
                // 计算两个时间点之间的天数
                long daysBetween = java.time.Duration.between(previousTime, endDate).toDays();
                // 不足一天按一天计算
                if (daysBetween < 1) {daysBetween = 1;}
                
                // 计算该时间段的租赁费用
                double segmentAmount = currentCount * unitPrice * daysBetween;
                totalItemAmount += segmentAmount;
                
                // 记录该时间段
                Map<String, Object> segment = new HashMap<>();
                segment.put("startTime", previousTime.toString());
                segment.put("endTime", endDate.toString());
                segment.put("days", daysBetween);
                segment.put("count", currentCount);
                segment.put("amount", segmentAmount);
                segments.add(segment);
                
                // 添加调试信息
                System.out.println("物资ID: " + machineTypeId +
                        ", 时段: " + previousTime + " 至 " + endDate +
                        ", 天数: " + daysBetween +
                        ", 数量: " + currentCount + 
                        ", 单价: " + unitPrice + 
                        ", 段金额: " + segmentAmount);
            }
            
            // 更新总金额
            totalAmount += totalItemAmount;
            
            // 创建计算结果对象
            Map<String, Object> calculationResult = new HashMap<>();
            calculationResult.put("machineTypeId", machineTypeId);
            calculationResult.put("machineTypeName", firstItem.getMachineTypeName());
            calculationResult.put("machineModel", firstItem.getMachineModel());
            calculationResult.put("machineUnit", firstItem.getMachineUnit());
            calculationResult.put("price", unitPrice);
            calculationResult.put("currentCount", currentCount);
            calculationResult.put("amount", totalItemAmount);
            calculationResult.put("timeline", timeLineData);
            calculationResult.put("segments", segments);
            
            // 添加第一次领料时间和最后一次退料时间
            DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            calculationResult.put("firstLeaseTime", firstLeaseTime != null ? firstLeaseTime.format(displayFormatter) : "");
            calculationResult.put("lastReturnTime", lastReturnTime != null ? lastReturnTime.format(displayFormatter) : "");
            
            // 确保details数据中包含领料和退料记录
            if (!items.isEmpty()) {
                // 打印一下查看数据结构
                System.out.println("第一条明细数据: " + items.get(0));
                
                // 确保操作类型字段存在
                for (ProjectLeaseCostDetail detail : items) {
                    // 确保operateType字段有值
                    if (detail.getOperateType() == null) {
                        if (detail.getLeaseNum() != null && detail.getLeaseNum() > 0 && (detail.getReturnNum() == null || detail.getReturnNum() == 0)) {
                            // 如果leaseNum有值但returnNum没值，则为领料
                            detail.setOperateType((byte) 1);
                        } else if (detail.getReturnNum() != null && detail.getReturnNum() > 0 && (detail.getLeaseNum() == null || detail.getLeaseNum() == 0)) {
                            // 如果returnNum有值但leaseNum没值，则为退料
                            detail.setOperateType((byte) 2);
                        } else {
                            // 默认为领料
                            detail.setOperateType((byte) 1);
                        }
                    }
                }
            }
            
            calculationResult.put("details", items);
            
            calculationResults.add(calculationResult);
        }
        
        // 将原始明细按操作时间排序
        List<ProjectLeaseCostDetail> sortedDetails = mergedList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(
                        ProjectLeaseCostDetail::getOperateTime,
                        Comparator.nullsFirst(Comparator.naturalOrder())
                ))
                .collect(Collectors.toList());

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("details", sortedDetails);  // 原始按时间排序的明细
        result.put("calculationResults", calculationResults); // 计算结果
        result.put("totalAmount", totalAmount); // 总金额
        return result;
    }

    @Override
    public List<Map<String, Object>> getAllProjects() {
        List<PlanProBean> proList = planApplicationDao.getProList(new PlanDevBean());
        return proList.stream().map(bean -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", bean.getId());
            map.put("name", bean.getName());
            // 可根据需要添加其他字段
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProjectLeaseCostDetail> getSettlementLeaseDetails(Integer settlementId) {
        return Collections.emptyList();
    }

    @Override
    public List<ProjectLeaseCostDetail> getSettlementReturnDetails(Integer settlementId) {
        return Collections.emptyList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveCalculation(Map<String, Object> calculationData, String userId) {
        // 获取当前用户信息
        UserBean user = UserShiroHelper.getRealCurrentUser();
        String currentUser = user != null ? user.getLoginName() : userId;
        
        // 处理计算结果数据
        String projectId = safeToString(calculationData.get("projectId"), "");
        String projectName = safeToString(calculationData.get("projectName"), "");
        String startTime = safeToString(calculationData.get("startTime"), "");
        String endTime = safeToString(calculationData.get("endTime"), "");
        Double totalAmount = safeToDouble(calculationData.get("totalAmount"), 0.0);
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> calculationResults = (List<Map<String, Object>>) calculationData.get("calculationResults");
        
        // 创建计算结果记录
        ProjectCostCalculation calculation = new ProjectCostCalculation();
        calculation.setProjectId(projectId);
        calculation.setProjectName(projectName);
        calculation.setStartTime(startTime);
        calculation.setEndTime(endTime);
        calculation.setTotalAmount(totalAmount);
        calculation.setCreateTime(String.valueOf(LocalDateTime.now()));
        calculation.setCreateUser(currentUser);
        calculation.setDelFlag((byte) 0);
        
        // 保存计算结果记录
        projectCostDao.saveCalculation(calculation);
        Integer calculationId = calculation.getId();
        
        // 保存计算结果明细和时间段
        if (calculationResults != null && !calculationResults.isEmpty()) {
            for (Map<String, Object> resultItem : calculationResults) {
                // 创建明细记录
                ProjectCostCalculationDetail detail = new ProjectCostCalculationDetail();
                detail.setCalculationId(calculationId);
                detail.setMachineTypeId(safeToInteger(resultItem.get("machineTypeId"), 0));
                detail.setMachineTypeName(safeToString(resultItem.get("machineTypeName"), ""));
                detail.setMachineModel(safeToString(resultItem.get("machineModel"), ""));
                detail.setMachineUnit(safeToString(resultItem.get("machineUnit"), ""));
                detail.setPrice(safeToDouble(resultItem.get("price"), 0.0));
                detail.setCurrentCount(safeToInteger(resultItem.get("currentCount"), 0));
                detail.setAmount(safeToDouble(resultItem.get("amount"), 0.0));
                detail.setFirstLeaseTime(safeToString(resultItem.get("firstLeaseTime"), ""));
                detail.setLastReturnTime(safeToString(resultItem.get("lastReturnTime"), ""));
                
                // 保存明细记录
                projectCostDao.saveCalculationDetail(detail);
                Integer detailId = detail.getId();
                
                // 保存时间段记录
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> segments = (List<Map<String, Object>>) resultItem.get("segments");
                if (segments != null && !segments.isEmpty()) {
                    List<ProjectCostCalculationSegment> segmentList = new ArrayList<>();
                    for (Map<String, Object> segmentItem : segments) {
                        ProjectCostCalculationSegment segment = new ProjectCostCalculationSegment();
                        segment.setCalculationDetailId(detailId);
                        segment.setStartTime(safeToString(segmentItem.get("startTime"), ""));
                        segment.setEndTime(safeToString(segmentItem.get("endTime"), ""));
                        segment.setDays(safeToInteger(segmentItem.get("days"), 0));
                        segment.setCount(safeToInteger(segmentItem.get("count"), 0));
                        segment.setAmount(safeToDouble(segmentItem.get("amount"), 0.0));
                        
                        segmentList.add(segment);
                    }
                    projectCostDao.saveCalculationSegments(segmentList);
                }
            }
        }
        
        return calculationId;
    }
    
    @Override
    public List<ProjectCostCalculation> queryCalculationList(ProjectCostCalculation calculation) {
        return projectCostDao.queryCalculationList(calculation);
    }
    
    @Override
    public ProjectCostCalculation getCalculationDetail(Integer id) {
        ProjectCostCalculation calculation = projectCostDao.getCalculationById(id);
        if (calculation != null) {
            // 查询明细
            List<ProjectCostCalculationDetail> details = projectCostDao.queryCalculationDetails(id);
            calculation.setDetails(details);
            
            // 查询每个明细的时间段
            if (details != null && !details.isEmpty()) {
                for (ProjectCostCalculationDetail detail : details) {
                    List<ProjectCostCalculationSegment> segments = projectCostDao.queryCalculationSegments(detail.getId());
                    detail.setSegments(segments);
                    
                    // 获取领料和退料记录
                    try {
                        // 使用machineTypeId查询领料和退料记录
                        if (detail.getMachineTypeId() != null) {
                            ProjectLeaseCostDetail queryParam = new ProjectLeaseCostDetail();
                            queryParam.setMachineTypeId(detail.getMachineTypeId());
                            queryParam.setProjectId(calculation.getProjectId().toString());
                            queryParam.setStartTime(calculation.getStartTime());
                            queryParam.setEndTime(calculation.getEndTime());
                            
                            // 合并领料和退料记录
                            List<ProjectLeaseCostDetail> leaseDetails = queryProjectLeaseDetails(queryParam);
                            List<ProjectLeaseCostDetail> returnDetails = queryProjectReturnDetails(queryParam);
                            
                            List<ProjectLeaseCostDetail> allDetails = new ArrayList<>();
                            if (leaseDetails != null) {
                                allDetails.addAll(leaseDetails);
                            }
                            if (returnDetails != null) {
                                allDetails.addAll(returnDetails);
                            }
                            
                            // 确保操作类型字段存在
                            for (ProjectLeaseCostDetail operDetail : allDetails) {
                                if (operDetail.getOperateType() == null) {
                                    if (operDetail.getLeaseNum() != null && operDetail.getLeaseNum() > 0 && 
                                        (operDetail.getReturnNum() == null || operDetail.getReturnNum() == 0)) {
                                        // 如果leaseNum有值但returnNum没值，则为领料
                                        operDetail.setOperateType((byte) 1);
                                    } else if (operDetail.getReturnNum() != null && operDetail.getReturnNum() > 0 && 
                                               (operDetail.getLeaseNum() == null || operDetail.getLeaseNum() == 0)) {
                                        // 如果returnNum有值但leaseNum没值，则为退料
                                        operDetail.setOperateType((byte) 2);
                                    } else {
                                        // 默认为领料
                                        operDetail.setOperateType((byte) 1);
                                    }
                                }
                            }
                            
                            // 计算第一次领料时间和最后一次退料时间
                            LocalDateTime firstLeaseTime = null;
                            LocalDateTime lastReturnTime = null;
                            
                            if (!allDetails.isEmpty()) {
                                // 按操作时间排序
                                allDetails.sort(Comparator.comparing(
                                        ProjectLeaseCostDetail::getOperateTime,
                                        Comparator.nullsFirst(Comparator.naturalOrder())
                                ));
                                
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                
                                for (ProjectLeaseCostDetail operDetail : allDetails) {
                                    if (operDetail.getOperateTime() == null) continue;
                                    
                                    LocalDateTime operateTime = LocalDateTime.parse(operDetail.getOperateTime(), formatter);
                                    
                                    // 判断操作类型并记录时间
                                    boolean isLease = (operDetail.getOperateType() == null || operDetail.getOperateType() == 1);
                                    if (isLease) {
                                        // 记录第一次领料时间
                                        if (firstLeaseTime == null || operateTime.isBefore(firstLeaseTime)) {
                                            firstLeaseTime = operateTime;
                                        }
                                    } else {
                                        // 记录最后一次退料时间
                                        if (lastReturnTime == null || operateTime.isAfter(lastReturnTime)) {
                                            lastReturnTime = operateTime;
                                        }
                                    }
                                }
                                
                                // 设置时间属性
                                DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                detail.setFirstLeaseTime(firstLeaseTime != null ? firstLeaseTime.format(displayFormatter) : "");
                                detail.setLastReturnTime(lastReturnTime != null ? lastReturnTime.format(displayFormatter) : "");
                            }
                            
                            // 将记录放入detail对象中用于前端显示
                            detail.setDetails(allDetails);
                        }
                    } catch (Exception e) {
                        // 记录错误但不影响其他数据的返回
                        e.printStackTrace();
                        System.err.println("Error fetching operation records: " + e.getMessage());
                    }
                }
            }
        }
        return calculation;
    }
    
    @Override
    public boolean deleteCalculation(Integer id) {
        return projectCostDao.deleteCalculation(id) > 0;
    }
    
    /**
     * 封装导出Excel的方法，适用于只有HttpServletResponse的情况
     * 
     * @param fileName 文件名
     * @param sheetName sheet名称
     * @param headers 表头
     * @param widths 列宽
     * @param formats 数据格式
     * @param dataList 数据列表
     * @param response HttpServletResponse对象
     * @throws Exception 导出异常
     */
    private void exportExcel(String fileName, String sheetName, String[] headers, int[] widths, int[] formats,
            List<Map<String, Object>> dataList, javax.servlet.http.HttpServletResponse response) throws Exception {
        try {
            // 设置文件名
            String filename = "";
            try {
                filename = URLEncoder.encode(fileName, "UTF-8");
            } catch (Exception e) {
                filename = fileName;
            }
            
            // 设置响应头
            response.setHeader("Content-disposition", "attachment;filename="+filename);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Pragma", "No-cache");
            
            // 创建一个工作簿
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建一个sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            
            // 创建表头
            int headerrow = 0;
            if (headers != null) {
                HSSFRow row = sheet.createRow(headerrow);
                // 设置表头行高 - 调高表头高度
                row.setHeight((short) 500); // 设置为原高度的约2倍
                
                // 表头样式
                HSSFCellStyle style = wb.createCellStyle();
                HSSFFont font = wb.createFont();
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font.setFontName("微软雅黑");
                font.setFontHeightInPoints((short) 11);
                style.setFont(font);
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
                style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                
                // 设置淡黄色背景
                style.setFillForegroundColor((short) 0x2B); // 修改为更明确的淡黄色，0x2B是淡黄色(light yellow)
                style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                
                for (int i = 0; i < headers.length; i++) {
                    sheet.setColumnWidth((short) i, (short) widths[i]);
                    HSSFCell cell = row.createCell(i);
                    cell.setCellValue(headers[i]);
                    cell.setCellStyle(style);
                }
                headerrow++;
            }
            
            // 表格主体
            if (dataList != null) {
                List<HSSFCellStyle> styleList = new ArrayList<HSSFCellStyle>();

                if (headers != null) {
                    for (int i = 0; i < headers.length; i++) { // 列数
                        HSSFCellStyle style = wb.createCellStyle();
                        HSSFFont font = wb.createFont();
                        font.setFontName("微软雅黑");
                        font.setFontHeightInPoints((short) 10);
                        style.setFont(font);
                        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                        // 添加垂直居中对齐
                        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                        
                        if (formats[i] == 1) {
                            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                        } else if (formats[i] == 2) {
                            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        } else if (formats[i] == 3) {
                            style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        } else if (formats[i] == 4) {
                            style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            // int类型
                            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
                        } else if (formats[i] == 5) {
                            // float类型
                            style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                        } else if (formats[i] == 6) {
                            // 百分比类型
                            style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
                        }
                        styleList.add(style);
                    }
                }

                for (Map<String, Object> stringObjectMap : dataList) { // 行数
                    HSSFRow row = sheet.createRow(headerrow);
                    int j = 0;
                    for (String key : headers) { // 列数
                        HSSFCell cell = row.createCell(j);
                        Object o = stringObjectMap.get(key);
                        if (o == null || "".equals(o)) {
                            cell.setCellValue("");
                        } else if (formats[j] == 4) {
                            // int
                            try {
                                cell.setCellValue(Long.parseLong(String.valueOf(o)));
                            } catch (Exception e) {
                                cell.setCellValue(0);
                            }
                        } else if (formats[j] == 5 || formats[j] == 6) {
                            // float
                            try {
                                cell.setCellValue(Double.parseDouble(String.valueOf(o)));
                            } catch (Exception e) {
                                cell.setCellValue(0.0);
                            }
                        } else {
                            cell.setCellValue(String.valueOf(o));
                        }

                        cell.setCellStyle(styleList.get(j));
                        j++;
                    }
                    headerrow++;
                }
            }
            
            // 输出文件
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            throw new Exception("导出Excel异常", e);
        }
    }
    
    @Override
    public void exportCostCalculation(Integer id, javax.servlet.http.HttpServletResponse response) throws Exception {
        ProjectCostCalculation calculation = getCalculationDetail(id);
        if (calculation == null) {
            throw new RuntimeException("计算结果不存在");
        }
        
        // 准备Excel数据
        String fileName = "费用计算表_" + calculation.getProjectName() + "_" + calculation.getId() + ".xls";
        String sheetName = "费用计算表";
        
        // 表头 - 增加规格型号字段
        String[] headers = {"序号", "物资类型", "规格型号", "单价", "总数量", "使用天数", "首次领料时间", "最后退料时间", "金额"};
        // 列宽 - 添加规格型号列宽
        int[] widths = {256*5, 256*15, 256*15, 256*10, 256*10, 256*10, 256*18, 256*18, 256*12};
        
        // 数据格式（1:String left; 2:String center; 3:String right; 4:int right; 5:float right）
        int[] formats = {4, 2, 2, 5, 4, 4, 2, 2, 5};
        
        // 准备数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<ProjectCostCalculationDetail> details = calculation.getDetails();
        
        if (details != null && !details.isEmpty()) {
            int index = 1;
            for (ProjectCostCalculationDetail detail : details) {
                Map<String, Object> row = new HashMap<>();
                row.put("序号", index++);
                row.put("物资类型", detail.getMachineTypeName());
                row.put("规格型号", detail.getMachineModel()); // 添加规格型号字段
                row.put("单价", detail.getPrice());
                row.put("总数量", detail.getCurrentCount());
                row.put("使用天数", calculateTotalDays(detail));
                row.put("首次领料时间", detail.getFirstLeaseTime());
                row.put("最后退料时间", detail.getLastReturnTime());
                row.put("金额", detail.getAmount());
                dataList.add(row);
            }
        }
        
        // 调用导出方法
        exportExcel(fileName, sheetName, headers, widths, formats, dataList, response);
    }
    
    @Override
    public void exportSegmentCalculation(Integer id, javax.servlet.http.HttpServletResponse response) throws Exception {
        ProjectCostCalculation calculation = getCalculationDetail(id);
        if (calculation == null) {
            throw new RuntimeException("计算结果不存在");
        }
        
        // 准备Excel数据
        String fileName = "物资区间费用表_" + calculation.getProjectName() + "_" + calculation.getId() + ".xls";
        String sheetName = "物资区间费用表";
        
        // 表头 - 增加工程名称、规格型号、故障停用天数、扣减费用字段
        String[] headers = {"序号", "工程名称", "物资类型", "规格型号", "起始时间", "截止时间", "租赁天数（天）", "数量", "金额(元)", "因故障造成的停用天数", "扣减费用(元)"};
        // 列宽 - 调整并新增字段宽度
        int[] widths = {256*6, 256*60, 256*20, 256*16, 256*22, 256*22, 256*16, 256*10, 256*12, 256*22, 256*16};
        
        // 数据格式（1:String left; 2:String center; 3:String right; 4:int right; 5:float right）
        int[] formats = {4, 2, 2, 2, 2, 2, 4, 4, 5, 4, 5};
        
        // 准备数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<ProjectCostCalculationDetail> details = calculation.getDetails();
        
        if (details != null && !details.isEmpty()) {
            int index = 1;
            for (ProjectCostCalculationDetail detail : details) {
                List<ProjectCostCalculationSegment> segments = detail.getSegments();
                if (segments != null && !segments.isEmpty()) {
                    for (ProjectCostCalculationSegment segment : segments) {
                        Map<String, Object> row = new HashMap<>();
                        row.put("序号", index++);
                        row.put("工程名称", calculation.getProjectName()); // 添加工程名称字段
                        row.put("物资类型", detail.getMachineTypeName());
                        row.put("规格型号", detail.getMachineModel()); // 添加规格型号字段
                        row.put("起始时间", segment.getStartTime()); 
                        row.put("截止时间", segment.getEndTime()); 
                        row.put("租赁天数（天）", segment.getDays()); 
                        row.put("数量", segment.getCount());
                        row.put("金额(元)", segment.getAmount()); 
                        row.put("因故障造成的停用天数", 0); // 默认为0天
                        row.put("扣减费用(元)", 0.00); // 默认为0.00元
                        dataList.add(row);
                    }
                }
            }
        }
        
        // 调用导出方法
        exportExcel(fileName, sheetName, headers, widths, formats, dataList, response);
    }
    
    @Override
    public void exportOperationRecords(Integer id, javax.servlet.http.HttpServletResponse response) throws Exception {
        ProjectCostCalculation calculation = getCalculationDetail(id);
        if (calculation == null) {
            throw new RuntimeException("计算结果不存在");
        }
        
        // 准备Excel数据
        String fileName = "领退记录表_" + calculation.getProjectName() + "_" + calculation.getId() + ".xls";
        String sheetName = "领退记录表";
        
        // 表头 - 增加规格型号字段
        String[] headers = {"序号", "物资类型", "规格型号", "操作类型", "操作时间", "数量", "单位", "操作人", "任务编号"};
        // 列宽 - 添加规格型号列宽
        int[] widths = {256*5, 256*15, 256*15, 256*10, 256*18, 256*10, 256*10, 256*10, 256*15};
        
        // 数据格式（1:String left; 2:String center; 3:String right; 4:int right; 5:float right）
        int[] formats = {4, 2, 2, 2, 2, 4, 2, 2, 2};
        
        // 准备数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        
        // 获取所有物资的领退记录
        List<ProjectCostCalculationDetail> details = calculation.getDetails();
        if (details != null && !details.isEmpty()) {
            int index = 1;
            for (ProjectCostCalculationDetail detail : details) {
                List<ProjectLeaseCostDetail> operRecords = detail.getDetails();
                if (operRecords != null && !operRecords.isEmpty()) {
                    for (ProjectLeaseCostDetail record : operRecords) {
                        Map<String, Object> row = new HashMap<>();
                        row.put("序号", index++);
                        row.put("物资类型", record.getMachineTypeName());
                        row.put("规格型号", record.getMachineModel()); // 添加规格型号字段
                        row.put("操作类型", record.getOperateType() == 1 ? "领料" : "退料");
                        row.put("操作时间", record.getOperateTime());
                        row.put("数量", record.getOperateType() == 1 ? record.getLeaseNum() : record.getReturnNum());
                        row.put("单位", record.getMachineUnit());
                        row.put("操作人", record.getOperatePersonName());
                        row.put("任务编号", record.getTaskCode());
                        dataList.add(row);
                    }
                }
            }
        }
        
        // 调用导出方法
        exportExcel(fileName, sheetName, headers, widths, formats, dataList, response);
    }
    
    /**
     * 计算物资使用的总天数
     */
    private int calculateTotalDays(ProjectCostCalculationDetail detail) {
        int totalDays = 0;
        if (detail != null && detail.getSegments() != null) {
            for (ProjectCostCalculationSegment segment : detail.getSegments()) {
                totalDays += segment.getDays() != null ? segment.getDays() : 0;
            }
        }
        return totalDays;
    }
}