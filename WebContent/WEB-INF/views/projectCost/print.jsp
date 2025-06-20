<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>项目结算单打印</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/admin.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/common.css" media="all">
    <style>
        body {
            margin: 20px;
            font-family: SimSun, "宋体", serif;
        }
        .print-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            background: #fff;
        }
        .print-header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 2px solid #000;
        }
        .print-header h1 {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .project-info {
            margin: 15px 0;
            line-height: 1.8;
        }
        .project-info p {
            margin: 5px 0;
        }
        .materials-table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
        }
        .materials-table th, .materials-table td {
            border: 1px solid #000;
            padding: 8px;
            text-align: center;
        }
        .materials-table th {
            background-color: #f0f0f0;
        }
        .summary {
            margin: 20px 0;
            line-height: 1.8;
        }
        .signatures {
            display: flex;
            justify-content: space-between;
            margin-top: 50px;
        }
        .signature-item {
            flex: 1;
            text-align: center;
        }
        .signature-line {
            display: inline-block;
            width: 150px;
            border-bottom: 1px solid #000;
            margin-bottom: 5px;
        }
        @media print {
            .no-print {
                display: none;
            }
            body {
                margin: 0;
            }
            .print-container {
                box-shadow: none;
                padding: 0;
            }
        }
    </style>
</head>
<body>
    <div class="no-print" style="margin-bottom: 20px;">
        <button class="layui-btn" onclick="window.print()">打印结算单</button>
        <button class="layui-btn layui-btn-primary" onclick="window.history.back()">返回</button>
    </div>
    
    <div class="print-container">
        <div class="print-header">
            <h1>项目结算单</h1>
            <p>结算编号：${settlement.settlementNo}</p>
        </div>
        
        <div class="project-info">
            <p><strong>项目名称：</strong>${settlement.projectName}</p>
            <p><strong>结算周期：</strong><fmt:formatDate value="${settlement.startTime}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${settlement.endTime}" pattern="yyyy-MM-dd"/></p>
            <p><strong>结算日期：</strong><fmt:formatDate value="${settlement.createTime}" pattern="yyyy-MM-dd"/></p>
        </div>
        
        <h3>物资领料明细</h3>
        <table class="materials-table">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>物资名称</th>
                    <th>规格型号</th>
                    <th>单位</th>
                    <th>数量</th>
                    <th>单价(元)</th>
                    <th>金额(元)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${leaseMaterials}" var="item" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${item.materialName}</td>
                        <td>${item.specification}</td>
                        <td>${item.unit}</td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber value="${item.price}" pattern="#,##0.00"/></td>
                        <td><fmt:formatNumber value="${item.amount}" pattern="#,##0.00"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6" style="text-align: right;"><strong>领料合计：</strong></td>
                    <td><strong><fmt:formatNumber value="${settlement.leaseTotal}" pattern="#,##0.00"/></strong></td>
                </tr>
            </tbody>
        </table>
        
        <h3>物资退料明细</h3>
        <table class="materials-table">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>物资名称</th>
                    <th>规格型号</th>
                    <th>单位</th>
                    <th>数量</th>
                    <th>单价(元)</th>
                    <th>金额(元)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${returnMaterials}" var="item" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${item.materialName}</td>
                        <td>${item.specification}</td>
                        <td>${item.unit}</td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber value="${item.price}" pattern="#,##0.00"/></td>
                        <td><fmt:formatNumber value="${item.amount}" pattern="#,##0.00"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6" style="text-align: right;"><strong>退料合计：</strong></td>
                    <td><strong><fmt:formatNumber value="${settlement.returnTotal}" pattern="#,##0.00"/></strong></td>
                </tr>
            </tbody>
        </table>
        
        <div class="summary">
            <p><strong>结算金额：</strong><fmt:formatNumber value="${settlement.settlementAmount}" pattern="#,##0.00"/> 元</p>
            <p><strong>备注：</strong>${settlement.remark}</p>
        </div>
        
        <div class="signatures">
            <div class="signature-item">
                <p>结算人：</p>
                <span class="signature-line"></span>
                <p>日期：<fmt:formatDate value="${settlement.createTime}" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="signature-item">
                <p>项目负责人：</p>
                <span class="signature-line"></span>
                <p>日期：</p>
            </div>
            <div class="signature-item">
                <p>审核人：</p>
                <span class="signature-line"></span>
                <p>日期：</p>
            </div>
        </div>
    </div>
    
    <script src="${bonuspath}/static/js/layui/layui.js"></script>
    <script>
        // 先配置模块路径
        layui.config({
            base: '${bonuspath}/static/' //静态资源所在路径
        }).extend({
            index: 'js/index' //主入口模块
        });
        
        // 然后按顺序加载模块
        layui.use(['layer', 'jquery'], function(){
            var $ = layui.$,
                layer = layui.layer;
                
            // 页面功能初始化
            $(document).ready(function(){
                // 打印按钮已经通过内联的onclick处理
            });
        });
    </script>
</body>
</html> 