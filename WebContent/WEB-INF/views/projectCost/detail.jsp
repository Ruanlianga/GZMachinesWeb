<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>项目结算详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/admin.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/common.css" media="all">
</head>
<body>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">项目结算详情</div>
            <div class="layui-card-body">
                <div class="layui-form" lay-filter="detail-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">工程名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="projectName" class="layui-input" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结算时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="settlementTime" class="layui-input" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">统计区间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="timeRange" class="layui-input" readonly>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="layui-tab layui-tab-brief" lay-filter="detail-tab">
                    <ul class="layui-tab-title">
                        <li class="layui-this">物资领料明细</li>
                        <li>物资退料明细</li>
                    </ul>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <table id="leaseDetails-table" lay-filter="leaseDetails-table"></table>
                        </div>
                        <div class="layui-tab-item">
                            <table id="returnDetails-table" lay-filter="returnDetails-table"></table>
                        </div>
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-primary" id="back-btn">返回</button>
                        <button class="layui-btn" id="print-btn">打印</button>
                    </div>
                </div>
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
        layui.use(['layer', 'element', 'form', 'table', 'index'], function(){
            var $ = layui.$,
                layer = layui.layer,
                form = layui.form,
                table = layui.table,
                element = layui.element;
            
            // 加载projectCost模块
            layui.use('projectCost', function(){
                var projectCost = layui.projectCost;
                
                // 获取详情数据
                var id = location.search.match(/id=([^&]*)/);
                id = id ? id[1] : '';
                
                if(id) {
                    projectCost.loadDetail(id);
                }
                
                // 返回按钮
                $('#back-btn').on('click', function(){
                    location.href = '${bonuspath}/backstage/projectCost/list';
                });
                
                // 打印按钮
                $('#print-btn').on('click', function(){
                    projectCost.printDetail(id);
                });
            });
        });
    </script>
</body>
</html> 