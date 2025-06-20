<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%@include file="../baseset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
    <meta charset="UTF-8">
    <title>机具类别详细信息</title>
    <link href="https://www.layuicdn.com/layui-v2.6.8/css/layui.css" rel="stylesheet">
    <style>
        .detail-container {
            padding: 20px;
        }
        .content-section {
            display: none;
        }
        .content-section.active {
            display: block;
        }
        .tab-header {
            padding: 10px 20px;
            border-bottom: 1px solid #eee;
            background-color: #f8f8f8;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .device-info {
            display: flex;
            align-items: center;
            gap: 15px;
        }
        .device-image {
            width: 40px;
            height: 40px;
            border-radius: 4px;
            object-fit: cover;
        }
        .device-text {
            display: flex;
            flex-direction: column;
            gap: 4px;
        }
        .device-name {
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }
        .device-model {
            font-size: 14px;
            color: #666;
        }
        .tab-buttons {
            display: flex;
        }
        .tab-buttons .layui-btn {
            height: 40px;
            line-height: 40px;
            padding: 0 25px;
            background: none;
            border: none;
            border-radius: 0;
            color: #666;
            font-size: 14px;
            position: relative;
        }
        .tab-buttons .layui-btn.active {
            color: #1E9FFF;
            font-weight: bold;
        }
        .tab-buttons .layui-btn.active:after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 0;
            width: 100%;
            height: 2px;
            background-color: #1E9FFF;
        }
        .tab-buttons .layui-btn:hover {
            color: #1E9FFF;
        }
        .tables-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .table-section {
            background: #fff;
            border-radius: 4px;
        }
        .table-title {
            padding: 12px 15px;
            font-size: 15px;
            font-weight: bold;
            border-bottom: 1px solid #eee;
            background-color: #fafafa;
        }
        .layui-table-view {
            margin: 0;
        }
        .table-section .layui-table-box {
            margin: 0 1px;
        }
        /* 时间线样式 */
        .operation-record-container {
            height: calc(100vh - 150px);  /* 减去头部和选项卡的高度 */
            display: flex;
            flex-direction: column;
        }
        .record-tabs {
            display: flex;
            border-bottom: 1px solid #e8e8e8;
            white-space: nowrap;
            background: #fff;
            position: sticky;
            top: 0;
            z-index: 1;
        }
        .record-tab {
            padding: 0 20px;
            height: 40px;
            line-height: 40px;
            cursor: pointer;
            position: relative;
            color: #666;
            transition: all 0.3s;
        }
        .record-tab:hover {
            color: #1E9FFF;
        }
        .record-tab.active {
            color: #1E9FFF;
            font-weight: bold;
        }
        .record-tab.active:after {
            content: '';
            position: absolute;
            bottom: -1px;
            left: 0;
            width: 100%;
            height: 2px;
            background-color: #1E9FFF;
        }
        .timeline {
            position: relative;
            padding-left: 120px;
        }
        .timeline::before {
            content: '';
            position: absolute;
            left: 90px;
            top: 0;
            bottom: 0;
            width: 2px;
            background: #e8e8e8;
        }
        .timeline-item {
            position: relative;
            margin-bottom: 30px;
        }
        .timeline-item:last-child {
            margin-bottom: 0;
        }
        .timeline-dot {
            position: absolute;
            left: -54px;
            top: 5px;
            width: 12px;
            height: 12px;
            background: #1E9FFF;
            border-radius: 50%;
        }
        .timeline-date {
            position: absolute;
            left: -120px;
            top: 0;
            width: 60px;
            text-align: center;
            background: #fff;
            border: 1px solid #e8e8e8;
            border-radius: 4px;
            overflow: hidden;
        }
        .timeline-date .date-month {
            background: #1E9FFF;
            color: #fff;
            font-size: 12px;
            padding: 2px 0;
        }
        .timeline-date .date-day {
            font-size: 20px;
            color: #333;
            padding: 5px 0;
            font-weight: bold;
        }
        .timeline-date .date-year {
            font-size: 12px;
            color: #999;
            padding-bottom: 2px;
        }
        .timeline-content {
            background: #f8f8f8;
            padding: 15px;
            border-radius: 4px;
            /* margin-left: 20px; */
            position: relative;
        }
        .timeline-time {
            position: absolute;
            top: 15px;
            right: 15px;
            font-size: 12px;
            color: #999;
        }
        .timeline-header {
            display: flex;
            align-items: center;
            margin-right: 80px;  /* 为右上角时间留出空间 */
        }
        .timeline-user {
            display: flex;
            align-items: center;
        }
        .timeline-avatar {
            width: 24px;
            height: 24px;
            border-radius: 50%;
            margin-right: 8px;
        }
        .timeline-title {
            font-size: 14px;
            color: #333;
            flex: 1;
        }
        .timeline-desc {
            font-size: 14px;
            color: #666;
            line-height: 1.6;
            margin: 8px 0;
            display: flex;
            align-items: flex-start;
            gap: 10px;
        }
        .timeline-desc-text {
            flex: 1;
        }
        .copy-btn {
            padding: 2px 8px;
            font-size: 12px;
            color: #1E9FFF;
            border: 1px solid #1E9FFF;
            border-radius: 3px;
            cursor: pointer;
            background: none;
            transition: all 0.3s;
        }
        .copy-btn:hover {
            background: #1E9FFF;
            color: #fff;
        }
        .timeline-footer {
            font-size: 13px;
            color: #1e9fff;
            margin-top: 8px;
            padding-top: 8px;
            border-top: 1px solid #eee;
        }
        .timeline-wrapper {
            flex: 1;
            overflow-y: auto;
            padding: 20px;
        }
        .filter-bar {
            padding: 15px 20px;
            background: #fff;
            border-bottom: 1px solid #e8e8e8;
            display: flex;
            align-items: center;
        }
        .filter-label {
            margin-right: 10px;
            color: #666;
        }
        .date-range {
            display: flex;
            align-items: center;
            margin-right: 15px;
        }
        .search-btn {
            padding: 0 15px;
            height: 32px;
            line-height: 32px;
            background: #1E9FFF;
            color: #fff;
            border: none;
            border-radius: 2px;
            cursor: pointer;
            transition: all 0.3s;
        }
        .search-btn:hover {
            opacity: 0.8;
        }
        .layui-input {
            width: 240px;
        }
    </style>
</head>
<body>
    <div class="tab-header">
        <div class="device-info">
            <img src="https://t8.baidu.com/it/u=2970788442,2168143594&fm=193" alt="设备图片" class="device-image">
            <div class="device-text">
                <div class="device-name">机动角磨</div>
                <div class="device-model">5T（单筒汽油绞磨）</div>
            </div>
        </div>
        <div class="tab-buttons">
            <button class="layui-btn active" data-target="change-history">变化明细</button>
            <button class="layui-btn" data-target="operation-record">操作记录</button>
        </div>
    </div>

    
    <div class="detail-container">
        <!-- 变化明细区域 -->
        <div class="content-section active" id="change-history">
            <div class="tables-container">
                <!-- 基本信息表格 -->
                <div class="table-section">
                    <div class="table-title">基本信息</div>
                    <table id="basicInfoTable" lay-filter="basicInfoTable" class="layui-table">
                        <thead>
                            <tr>
                                <td>操作类型</td>
                                <td>结果</td>
                                <td>最新操作</td>
                                <td>操作人</td>
                                <td>日期</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>保有量</td>
                                <td id="totalNum">632</td>
                                <td>新购入库:32</td>
                                <td>孙雪峰</td>
                                <td>2024-01-13</td>
                            </tr>
                            <tr>
                                <td>库存量</td>
                                <td id="storageNum"></td>
                                <td id="storageContent"></td>
                                <td id="storageUser"></td>
                                <td id="storageTime"></td>
                            </tr>
                            <tr>
                                <td>在用量</td>
                                <td id="inuseNum"></td>
                                <td id="inuseContent"></td>
                                <td id="inuseUser"></td>
                                <td id="inuseTime"></td>
                            </tr>
                            <tr>
                                <td>在修在检量</td>
                                <td id="repairNum">632</td>
                                <td id="repairContent"></td>
                                <td id="repairUser"></td>
                                <td id="repairTime"></td>
                            </tr>
                            <tr>
                                <td>报废量</td>
                                <td id="scrapNum">632</td>
                                <td id="scrapContent"></td>
                                <td id="scrapUser"></td>
                                <td id="scrapTime"></td>

                            </tr>
                            <tr>
                                <td>盘点</td>
                                <td id="pdNum"></td>
                                <td id="pdContent"></td>
                                <td id="pdUser"></td>
                                <td id="pdTime"></td>
                            </tr>
                        </tbody>

                    </table>
                </div>
                <!-- 在用工程表格 -->
                <div class="table-section">
                    <div class="table-title">在用工程</div>
                    <table id="projectTable" lay-filter="projectTable"></table>
                </div>
            </div>
        </div>

        <!-- 操作记录区域 -->
        <div class="content-section" id="operation-record">
            <div class="operation-record-container">
                <div class="record-tabs">
                    <div class="record-tab active" data-type="all">全部</div>
                    <div class="record-tab" data-type="leaseOut">领料出库</div>
                    <div class="record-tab" data-type="return">退料申请</div>
                    <div class="record-tab" data-type="scrap">机具报废</div>
                    <div class="record-tab" data-type="storePd">库存盘点</div>
                    <div class="record-tab" data-type="purchase">新购入库</div>
                    <div class="record-tab" data-type="store">修饰入库</div>
                </div>
                <div class="filter-bar">
                    <span class="filter-label">时间范围：</span>
                    <div class="date-range">
                        <input type="text" class="layui-input" id="dateRange" placeholder="请选择日期范围">
                    </div>
                    <button class="search-btn" id="searchBtn">查询</button>
                </div>
                <div class="timeline-wrapper">
                    <div class="timeline" id="timelineList">
                        <!-- 时间线内容将通过JavaScript动态生成 -->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${bonuspath}/static/js/index/layui.min.js"></script>
    <script>
    layui.use(['table', 'laydate'], function(){
        var table = layui.table;
        var $ = layui.$;
        var laydate = layui.laydate;
        
        // 获取当月的开始和结束日期
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var firstDay = year + '-' + (month < 10 ? '0' + month : month) + '-01';
        var lastDay = year + '-' + (month < 10 ? '0' + month : month) + '-' + new Date(year, month, 0).getDate();
        
        // 初始化加载数据
        $(function() {
            // 延迟一下确保日期选择器已经初始化
            setTimeout(function() {
                loadOperationRecords('all', firstDay, lastDay);
            }, 500);
        });
        
        laydate.render({
            elem: '#dateRange',
            type: 'date',
            range: true,  // 开启日期范围选择
            value: firstDay + ' - ' + lastDay,  // 设置默认值为当月
        });
        
        // 绑定查询按钮点击事件
        $('#searchBtn').on('click', function() {
            var dateRange = $('#dateRange').val();
            if (dateRange) {
                var dates = dateRange.split(' - ');
                loadOperationRecords($('.record-tab.active').data('type'), dates[0], dates[1]);
            } else {
                loadOperationRecords($('.record-tab.active').data('type'));
            }
        });
        
        // 切换按钮点击事件
        $('.tab-header .layui-btn').click(function(){
            // 更新按钮状态
            $('.tab-header .layui-btn').removeClass('active');
            $(this).addClass('active');
            
            // 切换内容区域
            var targetId = $(this).data('target');
            $('.content-section').removeClass('active');
            $('#' + targetId).addClass('active');
            
            // 如果是切换到表格，需要重新渲染表格
            if(targetId === 'operation-record' && !window.operationRecordTableInited) {
                loadOperationRecords();
                window.operationRecordTableInited = true;
            }
        });

        initBasicInfoTable();
        initProjectTable();
        
        // 初始化基本信息表格
        function initBasicInfoTable() {
            var loadIndex = layer.load(1, {
                shade: [0.1, '#fff']
            });
            $.ajax({
                url: '${bonuspath}/backstage/indexHomeDetails/getMaChangeInfo',
                type: 'POST',
                dataType: 'json',
                async: false,
                data: {
                    typeId: localStorage.getItem('equipmentViewTypeId')
                },
                success: function(data) {
                    console.log(data);
                    const obj = data.obj;
                    for(let index = 0; index < obj.length; index++) {
                        const element = obj[index];
                        if(element == null) {continue;}
                        if(element.taskType == '新购入库'){
                            $('#storageNum').text(1111);
                            $('#storageContent').text(element.taskType + ':' + element.content);
                            $('#storageUser').text(element.userName);
                            $('#storageTime').text(element.currentDate);
                        }
                        if(element.taskType == '领料出库'){
                            $('#inuseNum').text(633);
                            $('#inuseContent').text(element.taskType + ':' + element.content);
                            $('#inuseUser').text(element.userName);
                            $('#inuseTime').text(element.currentDate);
                        }
                        if(element.taskType == '退料申请'){
                            $('#repairNum').text(200);
                            $('#repairContent').text(element.taskType + ':' + element.content);
                            $('#repairUser').text(element.userName);
                            $('#repairTime').text(element.currentDate);
                        }
                        if(element.taskType == '机具报废'){
                            $('#scrapNum').text(20);
                            $('#scrapContent').text(element.taskType + ':' + element.content);
                            $('#scrapUser').text(element.userName);
                            $('#scrapTime').text(element.currentDate);
                        }
                        if(element.taskType == '盘亏'){
                            $('#pdContent').text(13);
                            $('#pdContent').text(element.taskType + ':' + element.content);
                            $('#pdUser').text(element.userName);
                            $('#pdTime').text(element.currentDate);
                        }
                    }
                },
                error: function() {
                    layer.msg('加载基本信息失败', {icon: 2});
                },
                complete: function() {
                    layer.close(loadIndex);
                }
            });
        }
        
        // 初始化在用工程表格
        function initProjectTable() {
            var loadIndex = layer.load(1, {
                shade: [0.1, '#fff']
            });
            table.render({
                elem: '#projectTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getMaUseInfo',
                method: 'post',
                where: {
                    typeId: localStorage.getItem('equipmentViewTypeId')
                },
                page: false,
                height: 280,
                cellMinWidth: 100,
                parseData: function(res) {
                    const obj = res.obj;
                    return {
                        "code": 0,  // 解析接口状态
                        "msg": res.resMsg,            // 解析提示文本
                        "count": obj.length,       // 解析数据长度
                        "data": obj         // 解析数据列表
                    };
                },
                cols: [[
                    {field: 'projectName', title: '工程名称', width: '40%'},
                    {field: 'inuseNum', title: '在用数量', width: '20%'},
                    {field: 'userName', title: '操作人', width: '20%'},
                    {field: 'currentDate', title: '日期', width: '20%'}
                ]],
                done: function() {
                    layer.close(loadIndex);
                }
            });
        }
        
        // 加载操作记录
        function loadOperationRecords(type = 'all', startDate = '', endDate = '') {
            var bonuspath = localStorage.getItem("bonuspath");
            var loadIndex = layer.load(1, {
                shade: [0.1, '#fff']
            });
            
            var requestData = {
                typeId: localStorage.getItem('equipmentViewTypeId')
            };

            // 获取日期范围
            var dateRange = $('#dateRange').val();
            if (dateRange) {
                var dates = dateRange.split(' - ');
                startDate = dates[0];
                endDate = dates[1];
            }
            
            // 添加日期筛选参数
            if (startDate) {
                requestData.startTime = startDate;
            }
            if (endDate) {
                requestData.endTime = endDate;
            }
            
            // 根据类型选择不同的接口
            var apiUrl;
            switch(type) {
                case 'leaseOut':
                    apiUrl = '/backstage/indexHomeDetails/getMaOutTask';
                    break;
                case 'return':
                    apiUrl = '/backstage/indexHomeDetails/getMaBackTask';
                    break;
                case 'scrap':
                    apiUrl = '/backstage/indexHomeDetails/getMaScrapTask';
                    break;
                case 'storePd':
                    apiUrl = '/backstage/indexHomeDetails/getMaPdTask';
                    break;
                case 'purchase':
                    apiUrl = '/backstage/indexHomeDetails/getMaNewTask';
                    break;
                case 'store':
                    apiUrl = '/backstage/indexHomeDetails/getMaInTask';
                    break;
                default:
                    apiUrl = '/backstage/indexHomeDetails/getMaChangeTask';
            }

            $.ajax({
                type: 'post',
                url: bonuspath + apiUrl,
                dataType: "json",
                data: requestData,
                success: function(res) {
                    console.log('操作记录数据:', res);
                    if(res.obj && res.obj.length > 0) {
                        renderTimeline(res.obj);
                    } else {
                        $('#timelineList').html('<div class="timeline-empty">暂无操作记录</div>');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('加载操作记录失败:', {
                        status: status,
                        error: error,
                        response: xhr.responseText
                    });
                    layer.msg('加载操作记录失败，请稍后重试', {icon: 2});
                },
                complete: function() {
                    layer.close(loadIndex);
                    console.log('typeId:', localStorage.getItem('equipmentViewTypeId'));
                }
            });
        }
        
        // 绑定选项卡点击事件
        $('.record-tab').on('click', function() {
            $('.record-tab').removeClass('active');
            $(this).addClass('active');
            
            var type = $(this).data('type');
            loadOperationRecords(type);
        });
        
        // 渲染时间线
        function renderTimeline(data) {
            if (!Array.isArray(data)) {
                console.error('时间线数据格式错误:', data);
                return;
            }

            var html = '';
            data.forEach(function(item) {
                console.log(item);
                if (!item) return;
                // 解析日期
                var date = new Date(item.currentDate);
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                
                // 获取月份名称
                var monthNames = ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'];
                var monthName = monthNames[date.getMonth()];
                
                html += '<div class="timeline-item">' +
                    '<div class="timeline-dot"></div>' +
                    '<div class="timeline-date">' +
                    '<div class="date-month">' + monthName + '</div>' +
                    '<div class="date-day">' + day + '</div>' +
                    '<div class="date-year">' + year + '</div>' +
                    '</div>' +
                    '<div class="timeline-content">' +
                    '<div class="timeline-time">' + (item.currentTime || '') + '</div>' +
                    '<div class="timeline-header">' +
                        '<div class="timeline-user">' +
                            '<img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" class="timeline-avatar" alt="用户头像">' +
                            '<span>' + (item.userName || '未知用户') + '</span>' +
                        '</div>' +
                    '</div>' +
                    '<div class="timeline-desc">' +
                        '<div class="timeline-desc-text">' + (item.content || '内容为空') + '</div>' +
                        '<button class="copy-btn" data-content="' + (item.content || '') + '">复制</button>' +
                    '</div>' +
                    '<div class="timeline-footer">来源：' + (item.taskCode || '单号为空') + '</div>' +
                    '</div>' +
                    '</div>';
            });
            if (html) {
                $('#timelineList').html(html);
                // 绑定复制按钮点击事件
                bindCopyButtons();
            } else {
                $('#timelineList').html('<div class="timeline-empty">暂无操作记录</div>');
            }
        }
        
        // 绑定复制按钮事件
        function bindCopyButtons() {
            $('.copy-btn').on('click', function() {
                var content = $(this).data('content');
                copyToClipboard(content);
                layer.msg('复制成功', {icon: 1});
            });
        }
        
        // 复制到剪贴板函数
        function copyToClipboard(text) {
            // 创建临时textarea
            var textarea = document.createElement('textarea');
            textarea.value = text;
            document.body.appendChild(textarea);
            
            // 选择文本
            textarea.select();
            textarea.setSelectionRange(0, 99999);
            
            // 执行复制
            document.execCommand('copy');
            
            // 移除临时元素
            document.body.removeChild(textarea);
        }

        // 添加空数据样式
        $('<style>')
            .text(`
                .timeline-empty {
                    text-align: center;
                    padding: 30px 0;
                    color: #999;
                }
            `)
            .appendTo('head');
    });
    </script>
</body>
</html> 