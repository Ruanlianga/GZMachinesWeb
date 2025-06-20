<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>计算结果详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/admin.css" media="all">
    <link rel="stylesheet" href="${bonuspath}/static/css/common.css" media="all">
    <style>
        .layui-card-header {
            font-weight: bold;
            font-size: 16px;
        }
        .detail-header {
            margin-bottom: 20px;
        }
        .detail-info {
            margin-bottom: 15px;
        }
        .detail-info .layui-inline {
            margin-right: 20px;
        }
        .detail-info .label {
            font-weight: bold;
            display: inline-block;
            width: 100px;
            text-align: right;
            margin-right: 10px;
        }
        .detail-info .value {
            display: inline-block;
        }
        .total-amount {
            font-size: 18px;
            color: #FF5722;
            font-weight: bold;
            text-align: right;
            padding: 10px 20px;
        }
        .layui-tab-content {
            padding: 15px 0;
        }
        .layui-table-view {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">计算结果详情</div>
            <div class="layui-card-body">
                <div class="detail-header">
                    <div class="detail-info" style="font-size: 13px;">
                        <div class="layui-inline">
                            <span class="label" style="background-color: #1E9FFF !important;">工程名称：</span>
                            <span class="value" id="projectName"></span>
                        </div>
                        <div class="layui-inline">
                            <span class="label" style="background-color: #1E9FFF !important;">统计区间：</span>
                            <span class="value" id="timeRange"></span>
                        </div>
                        <div class="layui-inline">
                            <span class="label" style="background-color: #1E9FFF !important;">创建时间：</span>
                            <span class="value" id="createTime"></span>
                        </div>
                        <div class="layui-inline">
                            <span class="label" style="background-color: #1E9FFF !important;">创建人：</span>
                            <span class="value" id="createUser"></span>
                        </div>
                    </div>
                    <div class="total-amount">
                        总金额：<span id="totalAmount">0.00</span> 元
                    </div>
                </div>
                
                <div class="layui-tab layui-tab-brief" lay-filter="detail-tab">
                    <ul class="layui-tab-title">
                        <li class="layui-this">费用计算结果</li>
                    </ul>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <table id="calculation-table" lay-filter="calculation-table"></table>
                        </div>
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-primary" id="back-btn">返回</button>
                        <button class="layui-btn" id="print-btn">打印</button>
                        <button class="layui-btn layui-btn-normal" id="export-cost-btn"><i class="layui-icon layui-icon-export"></i> 导出费用计算表</button>
                        <button class="layui-btn layui-btn-normal" id="export-segment-btn"><i class="layui-icon layui-icon-export"></i> 导出物资区间费用表</button>
                        <button class="layui-btn layui-btn-normal" id="export-record-btn"><i class="layui-icon layui-icon-export"></i> 导出领退记录表</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 明细查看弹窗模板 -->
    <script type="text/html" id="detail-dialog-template">
        <div style="padding: 15px;">
            <div class="layui-tab layui-tab-brief" lay-filter="detailTab">
                <ul class="layui-tab-title">
                    <li class="layui-this">使用时间段</li>
                    <li>物资领退记录</li>
                </ul>
                <div class="layui-tab-content">
                    <!-- 时段计算内容 -->
                    <div class="layui-tab-item layui-show">
                        <table class="layui-table" id="segments-table">
                            <thead>
                                <tr>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>使用天数</th>
                                    <th>使用数量</th>
                                    <th>金额(元)</th>
                                </tr>
                            </thead>
                            <tbody id="segments-body">
                                <!-- 动态填充 -->
                            </tbody>
                        </table>
                    </div>
                    
                    <!-- 操作记录内容 -->
                    <div class="layui-tab-item">
                        <div style="margin-bottom: 10px; text-align: right;">
                            <span style="display: inline-block; padding: 2px 8px; margin-right: 10px; background-color: #e8f5e9; color: #388e3c; border-radius: 2px;">领料</span>
                            <span style="display: inline-block; padding: 2px 8px; background-color: #ffebee; color: #d32f2f; border-radius: 2px;">退料</span>
                        </div>
                        <table class="layui-table" id="operations-table">
                            <thead>
                                <tr style="background-color: #f2f2f2; font-weight: bold;">
                                    <th>操作时间</th>
                                    <th>操作类型</th>
                                    <th>数量</th>
                                    <th>现场剩余数量</th>
                                </tr>
                            </thead>
                            <tbody id="operations-body">
                                <!-- 动态填充 -->
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </script>
    
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
            
            // 获取详情数据
            var id = location.search.match(/id=([^&]*)/);
            id = id ? id[1] : '';
            
            if(id) {
                // 加载详情数据
                $.ajax({
                    url: '${bonuspath}/backstage/projectCost/getCalculationDetail',
                    type: 'get',
                    data: {id: id},
                    success: function(res) {
                        if(typeof res === 'string'){
                            res = JSON.parse(res);
                        }
                        if(res && res.res === 1 && res.obj) {
                            var data = res.obj;
                            
                            // 填充基本信息
                            $('#projectName').text(data.projectName || '');
                            $('#timeRange').text((data.startTime || '') + ' 至 ' + (data.endTime || ''));
                            $('#createTime').text(data.createTime || '');
                            $('#createUser').text(data.createUser || '');
                            $('#totalAmount').text((data.totalAmount || 0).toFixed(2));
                            
                            // 渲染计算结果表格
                            var details = data.details || [];
                            table.render({
                                elem: '#calculation-table',
                                data: details,
                                cols: [[
                                    {field: 'machineTypeName', title: '物资名称', minWidth: 160},
                                    {field: 'machineModel', title: '规格型号', minWidth: 120},
                                    {field: 'machineUnit', title: '单位', width: 80},
                                    {field: 'currentCount', title: '未退还数量', width: 100},
                                    {field: 'price', title: '单价(元/天)', width: 100, templet: function(d){
                                        return d.price ? d.price.toFixed(2) : '0.00';
                                    }},
                                    {field: 'firstLeaseTime', title: '首次领料时间', minWidth: 160, templet: function(d){
                                        return d.firstLeaseTime || '--';
                                    }},
                                    {field: 'lastReturnTime', title: '最后退料时间', minWidth: 160, templet: function(d){
                                        return d.lastReturnTime || '--';
                                    }},
                                    {field: 'amount', title: '金额(元)', width: 100, style: 'color: #FF5722; font-weight: bold;', templet: function(d){
                                        return d.amount ? d.amount.toFixed(2) : '0.00';
                                    }},
                                    {title: '操作', width: 90, templet: function(d){
                                        return '<a class="layui-btn layui-btn-xs" lay-event="viewDetail">查看明细</a>';
                                    }}
                                ]],
                                page: false,
                                limit: 1000,
                                height: 'full-350',
                                even: true,
                                size: 'lg',
                                autoSort: false,
                                cellMinWidth: 80,
                                lang: 'zh-CN',
                                i18n: {
                                    'zh-CN': {
                                        page: {
                                            first: '首页',
                                            last: '尾页',
                                            prev: '上一页',
                                            next: '下一页',
                                            skip: '跳至',
                                            total: '共',
                                            item: '条',
                                            pagesize: '条/页',
                                            jump: '确定'
                                        }
                                    }
                                }
                            });
                            
                            // 监听表格工具条事件
                            table.on('tool(calculation-table)', function(obj){
                                var data = obj.data;
                                if(obj.event === 'viewDetail'){
                                    // 打开明细弹窗
                                    var layerIndex = layer.open({
                                        type: 1,
                                        title: data.machineTypeName + ' 使用明细',
                                        content: $('#detail-dialog-template').html(),
                                        area: ['800px', '500px'],
                                        success: function(layero, index){
                                            // 初始化Tab
                                            element.render('tab');
                                            
                                            // 填充时段数据
                                            var segments = data.segments || [];
                                            var segmentsHtml = '';
                                            var totalDays = 0;
                                            var totalAmount = 0;
                                            
                                            if(segments.length === 0){
                                                segmentsHtml = '<tr><td colspan="5" style="text-align: center;">没有详细的时段计算数据，显示汇总信息</td></tr>';
                                                segmentsHtml += '<tr style="font-weight: bold;"><td>--</td><td>--</td><td>--</td><td>' + 
                                                    (data.currentCount || 0) + '</td><td>' + 
                                                    (data.amount ? data.amount.toFixed(2) : '0.00') + '</td></tr>';
                                            } else {
                                                for(var i = 0; i < segments.length; i++){
                                                    var segment = segments[i];
                                                    var startTime = formatDateTime(segment.startTime);
                                                    var endTime = formatDateTime(segment.endTime);
                                                    var days = segment.days || 0;
                                                    var count = segment.count || 0;
                                                    var amount = segment.amount || 0;
                                                    
                                                    totalDays += days;
                                                    totalAmount += amount;
                                                    
                                                    segmentsHtml += '<tr><td>' + startTime + '</td><td>' + endTime + '</td><td>' + 
                                                        days + '</td><td>' + count + '</td><td>' + amount.toFixed(2) + '</td></tr>';
                                                }
                                                
                                                // 添加汇总行
                                                segmentsHtml += '<tr style="font-weight: bold; background-color: #f2f2f2;"><td colspan="2">合计</td><td>' + 
                                                    totalDays + '</td><td>-</td><td>' + totalAmount.toFixed(2) + '</td></tr>';
                                            }
                                            
                                            $('#segments-body').html(segmentsHtml);
                                            
                                            // 填充操作记录数据
                                            var details = data.details || [];
                                            var operationsHtml = '';
                                            var totalLeaseQuantity = 0;
                                            var totalReturnQuantity = 0;
                                            var finalCount = 0;
                                            var timeline = data.timeline || {};
                                            
                                            if(details.length === 0){
                                                operationsHtml = '<tr><td colspan="4" style="text-align: center;">暂无操作记录数据</td></tr>';
                                            } else {
                                                // 按操作时间排序
                                                details.sort(function(a, b){
                                                    var timeA = a.operateTime ? new Date(a.operateTime) : new Date(0);
                                                    var timeB = b.operateTime ? new Date(b.operateTime) : new Date(0);
                                                    return timeA - timeB;
                                                });
                                                
                                                for(var i = 0; i < details.length; i++){
                                                    var item = details[i];
                                                    var operateTimeStr = item.operateTime || '';
                                                    var operateTime = formatDateTime(operateTimeStr);
                                                    
                                                    // 确定操作类型
                                                    var operateType = '未知';
                                                    if (item.operateType === 1 || item.operateType === '1') {
                                                        operateType = '领料';
                                                    } else if (item.operateType === 2 || item.operateType === '2') {
                                                        operateType = '退料';
                                                    }
                                                    
                                                    // 确定数量
                                                    var quantity = 0;
                                                    if (operateType === '领料') {
                                                        quantity = item.leaseNum || item.quantity || item.count || item.num || 0;
                                                        totalLeaseQuantity += parseFloat(quantity);
                                                    } else if (operateType === '退料') {
                                                        quantity = item.returnNum || item.quantity || item.count || item.num || 0;
                                                        totalReturnQuantity += parseFloat(quantity);
                                                    }
                                                    
                                                    // 查找当前在用数量
                                                    var currentCount = timeline[operateTimeStr] || 0;
                                                    finalCount = currentCount;
                                                    
                                                    // 根据操作类型设置不同的行样式
                                                    var rowStyle = '';
                                                    if (operateType === '领料') {
                                                        rowStyle = 'background-color: #e8f5e9; color: #388e3c;';
                                                    } else if (operateType === '退料') {
                                                        rowStyle = 'background-color: #ffebee; color: #d32f2f;';
                                                    }
                                                    
                                                    operationsHtml += '<tr style="' + rowStyle + '"><td>' + operateTime + '</td><td>' + 
                                                        operateType + '</td><td>' + quantity + '</td><td>' + currentCount + '</td></tr>';
                                                }
                                                
                                                // 添加汇总行
                                                operationsHtml += '<tr style="font-weight: bold; background-color: #f2f2f2;"><td colspan="2">汇总</td><td>' +
                                                    '<span style="color: #388e3c;">领料: ' + totalLeaseQuantity + '</span><br>' +
                                                    '<span style="color: #d32f2f;">退料: ' + totalReturnQuantity + '</span></td><td>' + 
                                                    finalCount + '</td></tr>';
                                            }
                                            
                                            $('#operations-body').html(operationsHtml);
                                        }
                                    });
                                }
                            });
                        } else {
                            layer.msg(res.resMsg || '数据加载失败');
                        }
                    },
                    error: function() {
                        layer.msg('网络错误，请重试');
                    }
                });
            }
            
            // 返回按钮
            $('#back-btn').on('click', function(){
                location.href = '${bonuspath}/backstage/projectCost/list';
            });
            
            // 打印按钮
            $('#print-btn').on('click', function(){
                window.print();
            });
            
            // 导出费用计算表按钮
            $('#export-cost-btn').on('click', function(){
                if (!id) {
                    layer.msg('缺少必要的参数');
                    return;
                }
                // 使用表单提交下载文件
                var form = $('<form method="get" action="${bonuspath}/backstage/projectCost/exportCostCalculation"></form>');
                form.append($('<input type="hidden" name="id" value="' + id + '">'));
                form.appendTo('body').submit().remove();
            });
            
            // 导出物资区间费用表按钮
            $('#export-segment-btn').on('click', function(){
                if (!id) {
                    layer.msg('缺少必要的参数');
                    return;
                }
                // 使用表单提交下载文件
                var form = $('<form method="get" action="${bonuspath}/backstage/projectCost/exportSegmentCalculation"></form>');
                form.append($('<input type="hidden" name="id" value="' + id + '">'));
                form.appendTo('body').submit().remove();
            });
            
            // 导出领退记录表按钮
            $('#export-record-btn').on('click', function(){
                if (!id) {
                    layer.msg('缺少必要的参数');
                    return;
                }
                // 使用表单提交下载文件
                var form = $('<form method="get" action="${bonuspath}/backstage/projectCost/exportOperationRecords"></form>');
                form.append($('<input type="hidden" name="id" value="' + id + '">'));
                form.appendTo('body').submit().remove();
            });
            
            // 格式化日期时间
            function formatDateTime(dateTimeStr) {
                if(!dateTimeStr) return '';
                
                try {
                    var date = new Date(dateTimeStr);
                    if(isNaN(date.getTime())) return dateTimeStr;
                    
                    return date.getFullYear() + '-' + 
                           padZero(date.getMonth() + 1) + '-' + 
                           padZero(date.getDate()) + ' ' + 
                           padZero(date.getHours()) + ':' + 
                           padZero(date.getMinutes());
                } catch(e) {
                    return dateTimeStr;
                }
            }
            
            function padZero(num) {
                return (num < 10 ? '0' : '') + num;
            }
        });
    </script>
</body>
</html> 