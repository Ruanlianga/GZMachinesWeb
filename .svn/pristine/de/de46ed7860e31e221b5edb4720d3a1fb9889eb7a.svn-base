<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
    <script src="${bonuspath}/static/js/layer.js"></script>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
    <script src="${bonuspath}/static/js/layui/layui.js"></script>
    <meta charset="UTF-8">
    <title>预警信息</title>
<%--    <link href="https://www.layuicdn.com/layui-v2.6.8/css/layui.css" rel="stylesheet">--%>

    <style>
        .warning-container {
            padding: 10px;
        }
        .warning-tabs {
            border-bottom: 1px solid #e6e6e6;
            margin-bottom: 15px;
        }
        .warning-tab {
            display: inline-block;
            padding: 0 25px;
            height: 40px;
            line-height: 40px;
            cursor: pointer;
            position: relative;
            color: #666;
        }
        .warning-tab.active {
            color: #1E9FFF;
            font-weight: bold;
        }
        .warning-tab.active:after {
            content: '';
            position: absolute;
            bottom: -1px;
            left: 0;
            width: 100%;
            height: 2px;
            background-color: #1E9FFF;
        }
        .warning-content {
            display: none;
        }
        .warning-content.active {
            display: block;
        }
        /* 添加搜索框样式 */
        .search-box {
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 4px;
        }
        .search-box .layui-inline {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="warning-container">
        <div class="warning-tabs">
            <div class="warning-tab active" data-target="expire">计划到期</div>
            <div class="warning-tab" data-target="stock">库存不足</div>
            <div class="warning-tab" data-target="check">检验周期</div>
            <div class="warning-tab" data-target="occupy">长期占用</div>
            <div class="warning-tab" data-target="change">保有量变化</div>
        </div>

        <!-- 计划到期预警 -->
        <div class="warning-content active" id="expire">
            <div class="search-box">
                <div class="layui-form">
                    <div class="layui-inline">
                        <input type="text" name="projectName" placeholder="工程名称" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <input type="text" name="maName" placeholder="设备名称" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn" lay-submit lay-filter="expireSearch">搜索</button>
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="expireReset">重置</button>
                    </div>
                </div>
            </div>
            <table id="expireTable" lay-filter="expireTable"></table>
        </div>

        <!-- 库存不足 -->
        <div class="warning-content active" id="stock">
            <div class="search-box">
                <div class="layui-form">
                    <div class="layui-inline">
                        <input type="text" name="maName" placeholder="请输入机具名称" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <input type="text" name="maType" placeholder="请输入规格型号" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn" lay-submit lay-filter="stockSearch">搜索</button>
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="stockReset">重置</button>
                    </div>
                </div>
            </div>
            <table id="stockTable" lay-filter="stockTable"></table>
        </div>
        
        <!-- 检验周期 -->
        <div class="warning-content" id="check">
            <div class="search-box">
                <div class="layui-form">
                    <div class="layui-inline">
                        <input type="text" name="maName" placeholder="请输入机具名称" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <input type="text" name="maType" placeholder="请输入规格型号" autocomplete="off" class="layui-input">
                    </div>
                    
                    <div class="layui-inline">
                        <button class="layui-btn" lay-submit lay-filter="checkSearch">搜索</button>
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="checkReset">重置</button>
                    </div>
                </div>
            </div>
            <table id="checkTable" lay-filter="checkTable"></table>
        </div>
        
        <!-- 长期占用 -->
        <div class="warning-content" id="occupy">
            <div class="search-box">
                <div class="layui-form">
                    <div class="layui-inline">
                        <input type="text" name="maName" placeholder="请输入机具名称" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <input type="text" name="maType" placeholder="请输入规格型号" autocomplete="off" class="layui-input">
                    </div>
                    
                    <div class="layui-inline">
                        <button class="layui-btn" lay-submit lay-filter="occupySearch">搜索</button>
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="occupyReset">重置</button>
                    </div>
                </div>
            </div>
            <table id="occupyTable" lay-filter="occupyTable"></table>
        </div>
        
        <!-- 保有量变化 -->
        <div class="warning-content" id="change">
            <div class="search-box">
                <div class="layui-form">
                    <div class="layui-inline">
                        <input type="text" name="maName" placeholder="请输入机具名称" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <input type="text" name="maType" placeholder="请输入规格型号" autocomplete="off" class="layui-input">
                    </div>
                    
                    <div class="layui-inline">
                        <button class="layui-btn" lay-submit lay-filter="changeSearch">搜索</button>
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="changeReset">重置</button>
                    </div>
                </div>
            </div>
            <table id="changeTable" lay-filter="changeTable"></table>
        </div>
    </div>

    <script src="${bonuspath}/static/js/index/layui.min.js"></script>
    <script>
    layui.use(['table', 'form'], function(){
        var table = layui.table;
        var form = layui.form;
        
        // 切换标签页
        $('.warning-tab').click(function(){
            $('.warning-tab').removeClass('active');
            $(this).addClass('active');
            
            var targetId = $(this).data('target');
            $('.warning-content').removeClass('active');
            $('#' + targetId).addClass('active');
            
            // 初始化对应的表格
            switch(targetId) {
                case 'expire':
                    initExpireTable();
                    break;
                case 'stock':
                    initStockTable();
                    break;
                case 'check':
                    initCheckTable();
                    break;
                case 'occupy':
                    initOccupyTable();
                    break;
                case 'change':
                    initChangeTable();
                    break;
            }
        });

        // 初始化预警超期领用表格
        function initExpireTable() {
            table.render({
                elem: '#expireTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getAboutExpireWarn',
                height: 'full-100',
                method: 'post',
                page: true,
                limit: 50,  // 每页显示的条数
                limits: [10, 20, 50, 100],  // 每页条数的选择项
                loading: true,  // 显示加载条
                cols: [[
                    {type: 'numbers', title: '序号', width: '5%'},
                    {field: 'projectName', title: '工程名称', width: '18%'},
                    {field: 'type', title: '所属需求计划', width: '10%'},
                    {field: 'needNum', title: '领用数量', width: '8%'},
                    {field: 'backDate', title: '计划归还时间', width: '10%'},
                    { title: '状态', width: '10%',templet:function(d) {
                        if (d.planStatus == null) {
                            return '';
                        }
                       if (d.planStatus.indexOf("已过期") !== -1) {
                           return '<span style="color: red;">' + d.planStatus + '</span>';
                       }
                        if (d.planStatus === "今天到期") {
                            return '<span style="color: #dea54c;">' + d.planStatus + '</span>';
                        }
                       return d.planStatus;
                    }},
                    {field: 'maType', title: '设备类型', width: '10%'},
                    {field: 'maName', title: '设备名称', width: '10%'},
                    {field: 'maModel', title: '规格型号', width: '12%'},
                    {field: 'remark', title: '备注', width: '7%'}
                ]],
                response: {
                    statusCode: 200
                },
                parseData: function(res) {
                    var data = [];
                    if(res.obj && Array.isArray(res.obj)) {
                        // 根据当前页码和每页条数计算起始索引
                        var curr = this.page.curr || 1;
                        var limit = this.page.limit || 50;
                        var start = (curr - 1) * limit;
                        var end = start + limit;
                        // 截取对应页的数据
                        data = res.obj.slice(start, end);
                    }
                    return {
                        "code": 200,
                        "msg": res.resMsg,
                        "count": res.obj ? res.obj.length : 0,  // 总数据条数
                        "data": data  // 当前页数据
                    };
                }
            });
        }

        // 初始化库存不足表格
        function initStockTable() {
            table.render({
                elem: '#stockTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getStorageWarn',
                height: 'full-100',
                method: 'post',
                page: true,
                limit: 20,  // 每页显示的条数
                limits: [10, 20, 30, 50],  // 每页条数的选择项
                loading: true,  // 显示加载条
                cols: [[
                    {type: 'numbers', title: '序号', width: '10%'},
                    { title: '机具类别', width: '20%',templet:function(d) {
                        return d.isCount === '0' ? '设备' : '机具';
                    }},
                    {field: 'maType', title: '机具名称', width: '20%'},
                    {field: 'maName', title: '规格型号', width: '25%'},
                    {field: 'storageNum', title: '库存量', width: '10%'},
                    {field: 'maUnit', title: '单位', width: '15%'}
                ]],
                response: {
                    statusCode: 200
                },
                parseData: function(res) {
                    var data = [];
                    if(res.obj && Array.isArray(res.obj)) {
                        // 根据当前页码和每页条数计算起始索引
                        var curr = this.page.curr || 1;
                        var limit = this.page.limit || 50;
                        var start = (curr - 1) * limit;
                        var end = start + limit;
                        // 截取对应页的数据
                        data = res.obj.slice(start, end);
                    }
                    return {
                        "code": 200,
                        "msg": res.resMsg,
                        "count": res.obj ? res.obj.length : 0,  // 总数据条数
                        "data": data  // 当前页数据
                    };
                }
            });
        }
        
        // 初始化检验周期表格
        function initCheckTable() {
            table.render({
                elem: '#checkTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getCheckWarn',
                height: 'full-100',
                method: 'post',
                page: true,
                limit: 20,
                limits: [10, 20, 30, 50],
                loading: true,
                cols: [[
                    {type: 'numbers', title: '序号', width: '5%'},  // 序号
                    {field: 'deviceType', title: '机具类别', width: '5%',templet:function(d) {
                        return d.isCount === '0' ? '设备' : '机具';
                    }},
                    {field: 'maType', title: '机具名称', width: '10%'},
                    {field: 'maName', title: '规格型号', width: '10%'},
                    {field: 'maCode', title: '设备编码', width: '10%'},
                    {field: 'thisCheckTime', title: '下次检验时间', width: '10%'},
                    {field: 'nextCheckTime', title: '距下次检验时长', width: '10%'},
                    {field: 'maStatus', title: '状态', width: '10%',templet:function(d) {
                        switch(d.maStatus) {
                            case '1': return '待通知';
                            case '2': return '待检验';
                            case '3': return '待打印';
                            case '4': return '待入库';
                            case '5': return '在库';
                            case '6': return '在用';
                            case '7': return '在修';
                            case '8': return '在检';
                            case '9': return '修饰后待入库';
                            case '10': return '待报废';
                            case '11': return '已报废';
                            case '12': return '报废封存';
                            case '13': return '在检';
                            case '14': return '在审';
                            case '15': return '出库审核通过';
                            case '16': return '待报废检验';
                            case '17': return '待封存检验';
                            case '19': return '维修合格';
                            default: return '未知';
                        }
                    }},
                    {field: 'projectName', title: '在用工程', width: '10%'},
                    {field: 'agreementCode', title: '协议号', width: '10%'},
                    {field: 'companyName', title: '施工单位', width: '10%'}
                ]],
                response: {
                    statusCode: 200
                },
                parseData: function(res) {
                    var data = [];
                    if(res.obj && Array.isArray(res.obj)) {
                        var curr = this.page.curr || 1;
                        var limit = this.page.limit || 50;
                        var start = (curr - 1) * limit;
                        var end = start + limit;
                        data = res.obj.slice(start, end);
                    }
                    return {
                        "code": 200,
                        "msg": res.resMsg,
                        "count": res.obj ? res.obj.length : 0,
                        "data": data
                    };
                }
            });
        }
        
        // 初始化长期占用表格
        function initOccupyTable() {
            table.render({
                elem: '#occupyTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getInUseWarn',
                height: 'full-100',
                method: 'post',
                page: true,
                limit: 20,
                limits: [10, 20, 30, 50],
                loading: true,
                cols: [[
                    {type: 'numbers', title: '序号', width: '7%'},  // 序号
                    {field: 'deviceType', title: '机具类别', width: '8%',templet:function(d) {
                        return d.isCount === '0' ? '设备' : '机具';
                    }},
                    {field: 'maType', title: '机具名称', width: '10%'},
                    {field: 'maName', title: '规格型号', width: '20%'},
                    {field: 'inuseNum', title: '长期占用量', width: '10%'},
                    {field: 'maUnit', title: '单位', width: '10%'},
                    {field: 'outTime', title: '领用日期', width: '15%'},
                    {field: 'projectName', title: '领用工程', width: '20%'}
                ]],
                response: {
                    statusCode: 200
                },
                parseData: function(res) {
                    var data = [];
                    if(res.obj && Array.isArray(res.obj)) {
                        var curr = this.page.curr || 1;
                        var limit = this.page.limit || 50;
                        var start = (curr - 1) * limit;
                        var end = start + limit;
                        data = res.obj.slice(start, end);
                    }
                    return {
                        "code": 200,
                        "msg": res.resMsg,
                        "count": res.obj ? res.obj.length : 0,
                        "data": data
                    };
                }
            });
        }
        
        // 初始化保有量变化表格
        function initChangeTable() {
            table.render({
                elem: '#changeTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getTotalWarn',
                method: 'post',
                height: 'full-100',
                page: true,
                limit: 20,
                limits: [10, 20, 30, 50],
                loading: true,
                cols: [[
                    {type: 'numbers', title: '序号', width: '5%'},
                    {title: '机具类别', width: '6%',templet:function(d) {
                        return d.isCount === '0' ? '设备' : '机具';
                    }},
                    {field: 'maType', title: '机具名称', width: '14%'},
                    {field: 'maName', title: '规格型号', width: '15%'},
                    {field: 'total', title: '总保有量', width: '6%'},
                    {title: '在库/在用/在修', width: '10%',templet:function(d) {
                        return (d.storageNum | 0) + '/' + (d.inuseNum | 0) + '/' + (d.repairNum | 0);
                    }},
                    {field: 'time', title: '最后更新时间', width: '12%'},
                    {field: 'content', title: '保有量变化概述', width: '22%'},
                    {field: 'operation', title: '操作', width: '10%', templet: function(d){
                        return '<a class="layui-btn layui-btn-xs" lay-event="detail">查看来源</a>';
                    }}
                ]],
                response: {
                    statusCode: 200
                },
                parseData: function(res) {
                    var data = [];
                    if(res.obj && Array.isArray(res.obj)) {
                        var curr = this.page.curr || 1;
                        var limit = this.page.limit || 50;
                        var start = (curr - 1) * limit;
                        var end = start + limit;
                        data = res.obj.slice(start, end);
                    }
                    return {
                        "code": 200,
                        "msg": res.resMsg,
                        "count": res.obj ? res.obj.length : 0,
                        "data": data
                    };
                }
            });
        }

        // 监听保有量变化工具条事件
        table.on('tool(changeTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                localStorage.setItem('equipmentViewTypeId', data.id);
                layer.open({
                    type: 2,
                    title: '操作记录',
                    area: ['96%', '96%'],
                    content: '${bonuspath}/backstage/indexHome/dataOverview/equipment/detail',
                    maxmin: false
                });
            }
        });
        
        // 监听检验周期表格工具条
        table.on('tool(checkTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.open({
                    type: 2,
                    title: '检验详情',
                    area: ['90%', '90%'],
                    content: '${bonuspath}/backstage/indexHomeDetails/getCheckDetail?maId=' + data.maId,
                    maxmin: false
                });
            }
        });
        
        // 监听长期占用表格工具条
        table.on('tool(occupyTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.open({
                    type: 2,
                    title: '占用详情',
                    area: ['90%', '90%'],
                    content: '${bonuspath}/backstage/indexHomeDetails/getOccupyDetail?maId=' + data.maId,
                    maxmin: false
                });
            }
        });
        
        // 监听库存不足表格工具条
        table.on('tool(stockTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.open({
                    type: 2,
                    title: '库存详情',
                    area: ['90%', '90%'],
                    content: '${bonuspath}/backstage/indexHomeDetails/getStockDetail?maId=' + data.maId,
                    maxmin: false
                });
            }
        });

        // 添加搜索功能
        form.on('submit(expireSearch)', function(data){
            table.reload('expireTable', {
                where: data.field
            });
            return false;
        });

        form.on('submit(expireReset)', function(){
            $('input[name="maType"]').val('');
            $('input[name="maName"]').val('');
            document.querySelector('#stock .layui-form').reset();
            table.reload('expireTable', {
                where: {}
            });
            return false;
        });
        
        // 添加搜索功能
        form.on('submit(stockSearch)', function(data){
            table.reload('stockTable', {
                where: data.field
            });
            return false;
        });
        
        form.on('submit(stockReset)', function(){
            $('input[name="maType"]').val('');
            $('input[name="maName"]').val('');
            document.querySelector('#stock .layui-form').reset();
            table.reload('stockTable', {
                where: {}
            });
            return false;
        });
        
        form.on('submit(checkSearch)', function(data){
            table.reload('checkTable', {
                where: data.field
            });
            return false;
        });
        
        form.on('submit(checkReset)', function(){
            $('input[name="maType"]').val('');
            $('input[name="maName"]').val('');
            document.querySelector('#check .layui-form').reset();
            table.reload('checkTable', {
                where: {}
            });
            return false;
        });
        
        // 添加长期占用搜索功能
        form.on('submit(occupySearch)', function(data){
            table.reload('occupyTable', {
                where: data.field
            });
            return false;
        });
        
        form.on('submit(occupyReset)', function(){
            $('input[name="maType"]').val('');
            $('input[name="maName"]').val('');
            document.querySelector('#occupy .layui-form').reset();
            table.reload('occupyTable', {
                where: {}
            });
            return false;
        });
        
        // 添加保有量变化搜索功能
        form.on('submit(changeSearch)', function(data){
            table.reload('changeTable', {
                where: data.field
            });
            return false;
        });
        
        form.on('submit(changeReset)', function(){
            $('input[name="maType"]').val('');
            $('input[name="maName"]').val('');
            document.querySelector('#change .layui-form').reset();
            table.reload('changeTable', {
                where: {}
            });
            return false;
        });
        
        // 默认加载超期领用预警表格
        initExpireTable();
    });
    </script>
</body>
</html> 