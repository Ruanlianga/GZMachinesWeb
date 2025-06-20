<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>项目计算结果列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/admin.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/common.css" media="all">
    <style>
        /* 优化筛选区域和按钮布局 */
        .filter-area {
            padding: 10px 15px;
            display: flex;
            align-items: center;
            flex-wrap: wrap;
            background-color: #fff;
        }
        .filter-item {
            margin-right: 10px;
            display: flex;
            align-items: center;
        }
        .filter-label {
            width: auto;
            padding-right: 8px;
            font-weight: 500;
            white-space: nowrap;
        }
        .filter-buttons {
            margin-left: auto;
            white-space: nowrap;
        }
        .layui-input-inline-auto {
            width: auto;
            min-width: 120px;
        }
        .layui-input-inline-date {
            width: 120px;
        }
        @media screen and (max-width: 768px) {
            .filter-area {
                flex-direction: column;
                align-items: flex-start;
            }
            .filter-item {
                margin-bottom: 10px;
                width: 100%;
            }
            .filter-buttons {
                margin-left: 0;
                margin-top: 10px;
                width: 100%;
                display: flex;
                justify-content: space-between;
            }
        }
    </style>
</head>
<body>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-form filter-area">
                <div class="filter-item">
                    <div class="filter-label">工程名称</div>
                    <div class="layui-input-inline layui-input-inline-auto">
                        <input type="text" name="projectName" placeholder="请输入工程名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="filter-item">
                    <div class="filter-label">计算日期</div>
                    <div class="layui-input-inline layui-input-inline-date">
                        <input type="text" name="startDate" id="startDate" placeholder="开始日期" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline layui-input-inline-date">
                        <input type="text" name="endDate" id="endDate" placeholder="结束日期" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="filter-buttons">
                    <button class="layui-btn layui-btn-sm" lay-submit lay-filter="LAY-project-cost-search">
                        <i class="layui-icon layui-icon-search"></i> 搜索
                    </button>
                    <button class="layui-btn layui-btn-sm layui-btn-normal" data-type="add">
                        <i class="layui-icon layui-icon-add-1"></i> 新增结算单
                    </button>
                </div>
            </div>

            <div class="layui-card-body">
                <table id="LAY-project-cost-list" lay-filter="LAY-project-cost-list"></table>
                <script type="text/html" id="table-project-cost-list">
                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail"><i class="layui-icon layui-icon-search"></i>查看</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
                </script>
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
                laydate = layui.laydate;
            
            // 初始化日期选择器
            laydate.render({
                elem: '#startDate'
            });
            
            laydate.render({
                elem: '#endDate'
            });
            
            // 加载projectCost模块
            layui.use('projectCost', function(){
                var projectCost = layui.projectCost;
                // 初始化页面
                projectCost.init();
                
                // 添加按钮点击事件
                $('.layui-btn[data-type="add"]').on('click', function(){
                    layer.open({
                        type: 2,
                        title: '新增计算结果',
                        content: '${bonuspath}/backstage/projectCost/calculation_form',
                        area: ['90%', '90%'],
                        maxmin: false
                    });
                });
            });
        });
    </script>
</body>
</html> 