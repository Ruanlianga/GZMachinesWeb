<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>项目结算计算</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/admin.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/common.css" media="all">
    <style>
        .layui-table-body layui-table-main {
            height: 300px;
        }
        .layui-form-item .layui-input-inline {
            width: 180px;
        }
        .filter-row {
            display: flex;
            align-items: center;
            padding: 10px 10px 0 10px;
            margin-bottom: 15px;
            flex-wrap: wrap;
        }
        .filter-item {
            display: flex;
            align-items: center;
            margin-right: 10px;
            margin-bottom: 10px;
        }
        .filter-label {
            width: auto;
            padding-right: 8px;
            flex-shrink: 0;
            font-weight: bold;
        }
        .filter-action {
            margin-left: auto;
        }
        .table-container {
            padding: 0 10px;
            height: calc(100vh - 150px);
        }
        .table-section {
            height: calc(50% - 20px);
            margin-bottom: 20px;
        }
        .table-section:last-child {
            margin-bottom: 0;
        }
        .table-header {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 10px;
            padding-left: 10px;
            border-left: 5px solid #1E9FFF;
        }
    </style>
</head>
<body>
    <div class="layui-form" lay-filter="calculation-form" id="calculation-form">
        <!-- 筛选框和按钮行 -->
        <div class="filter-row">
            <div class="filter-item">
                <div class="filter-label">工程名称</div>
                <div class="layui-input-inline">
                    <select name="projectId" lay-verify="required" lay-filter="projectSelect">
                        <option value="">请选择工程</option>
                    </select>
                </div>
            </div>
            <div class="filter-item">
                <div class="filter-label">统计时间</div>
                <div class="layui-input-inline" style="width: 150px;">
                    <input type="text" name="startTime" id="startTime" lay-verify="required" placeholder="开始日期" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 150px;">
                    <input type="text" name="endTime" id="endTime" lay-verify="required" placeholder="结束日期" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="filter-action">
                <button class="layui-btn" lay-submit lay-filter="calculation-generate">生成</button>
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="calculation-save">保存</button>
            </div>

        </div>

        <!-- 加载中提示 -->
        <div id="data-loading" style="display:none; text-align: center; padding: 30px 0;">
            <i class="layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop" style="font-size: 30px;"></i>
            <p>数据加载中，请稍候...</p>
        </div>

        <!-- 数据表格容器 -->
        <div id="data-container" class="table-container" style="display:none;">
            <div class="table-section" style="margin-bottom: 150px;">
                <div class="table-header">物资领料明细</div>
                <table class="layui-table" id="leaseTable" lay-filter="leaseTable"></table>
            </div>
            <div class="table-section">
                <div class="table-header">物资退料明细</div>
                <table class="layui-table" id="returnTable" lay-filter="returnTable"></table>
            </div>
        </div>
    </div>
    <script src="${bonuspath}/static/js/layui/layui.js"></script>
    <script>
        // 先配置模块路径
        layui.config({
            base: '${bonuspath}/static/' //静态资源所在路径
        }).extend({
            index: 'js/index', //主入口模块
            projectCost: 'js/projectCost/projectCost'
        });
        
        // 然后按顺序加载模块
        layui.use(['layer', 'element', 'form', 'table', 'laydate', 'index'], function(){
            var $ = layui.$,
                layer = layui.layer,
                form = layui.form,
                table = layui.table,
                laydate = layui.laydate;
            
            // 初始化日期选择器
            laydate.render({
                elem: '#startTime'
            });
            
            laydate.render({
                elem: '#endTime'
            });
            
            // 加载projectCost模块
            layui.use('projectCost', function(){
                var projectCost = layui.projectCost;
                
                // 初始化项目下拉框数据
                projectCost.loadProjects();
                
                // 生成结算数据
                form.on('submit(calculation-generate)', function(data){
                    projectCost.generateSettlement(data.field);
                    return false;
                });
                
                // 保存结算数据
                form.on('submit(calculation-save)', function(data){
                    projectCost.saveCalculation(data.field);
                    return false;
                });
            });
        });
    </script>
</body>
</html> 