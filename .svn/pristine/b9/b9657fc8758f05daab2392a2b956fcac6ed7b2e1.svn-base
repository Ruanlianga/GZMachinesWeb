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
    <title>工程明细</title>
    <link href="${bonuspath}/static/js/index/layui.css" rel="stylesheet">
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
        .project-stats {
            display: flex;
            gap: 20px;
            margin-right: 30px;
        }
        .stat-item {
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .stat-label {
            color: #666;
            font-size: 14px;
        }
        .stat-value {
            font-size: 16px;
            font-weight: bold;
            color: #1E9FFF;
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
        .search-bar {
            padding: 10px 20px;
            background: #fafafa;
            border-bottom: 1px solid #f0f0f0;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .layui-form-label {
            width: 70px;
            padding: 9px 10px;
            color: #666;
            font-size: 13px;
        }
        .layui-input-inline {
            width: 180px;
        }
        .layui-inline {
            margin-right: 15px;
            display: flex;
            align-items: center;
        }
        .layui-form-item {
            margin-bottom: 0;
            display: flex;
            align-items: center;
        }
        .search-buttons {
            display: flex;
            gap: 8px;
        }
        .layui-input, .layui-select {
            height: 32px;
            line-height: 32px;
            border-radius: 2px;
            border-color: #e6e6e6;
        }
        .layui-btn {
            height: 32px;
            line-height: 32px;
            padding: 0 16px;
            font-size: 13px;
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
    </style>
</head>
<body>
    <div class="tab-header">
        <div class="device-info">
            <img src="${bonuspath}/static/img/u2609.svg" alt="设备图片" class="device-image">
            <div class="device-text">
                <div class="device-name" id="projectName"></div>
                <div class="device-model" id="companyName"></div>
            </div>
        </div>
        <div class="project-stats">
            <div class="stat-item">
                <span class="stat-label">领用量:</span>
                <span class="stat-value" id="useCount"></span>
            </div>
            <div class="stat-item">
                <span class="stat-label">退还量:</span>
                <span class="stat-value" id="backCount"></span>
            </div>
            <div class="stat-item">
                <span class="stat-label">差缺量:</span>
                <span class="stat-value" id="diffCount"></span>
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
                <!-- 在用工程表格 -->
                <div class="table-section">
                    <div class="table-title">在用工程</div>
                    <!-- 搜索条件栏 -->
                    <div class="search-bar">
                        <div class="layui-form">
                            <div class="layui-form-item" style="margin-bottom: 0;">
                                <div class="layui-inline">
                                    <label class="layui-form-label">关键字:</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="keyword" placeholder="搜索关键词" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">状态:</label>
                                    <div class="layui-input-inline">
                                        <select name="status">
                                            <option value="">退还筛选</option>
                                            <option value="1">未退还</option>
                                            <option value="2">已退还</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="search-buttons">
                                    <button class="layui-btn" id="searchBtn">查询</button>
                                    <button class="layui-btn layui-btn-primary" id="resetBtn">清空</button>
                                    <button class="layui-btn layui-btn-normal" id="exportBtn">导出</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table id="projectTable" lay-filter="projectTable"></table>
                </div>
            </div>
        </div>

        <!-- 操作记录区域 -->
        <div class="content-section" id="operation-record">
            <div class="operation-record-container">
                <div class="record-tabs">
                    <div class="record-tab active" data-type="all">全部</div>
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
        
        // 加载项目详细信息
        function loadProjectDetail() {
            var projectDetailData = JSON.parse(localStorage.getItem('projectDetailData') || '{}');
            
            // 更新页面显示
            $('#projectName').text(projectDetailData.projectName || '未知工程');
            $('#companyName').text(projectDetailData.companyName || '未知公司');
            $('#useCount').text(projectDetailData.useCount || 0);
            $('#backCount').text(projectDetailData.backCount || 0);
            $('#diffCount').text(projectDetailData.diffCount || 0);
        }
        
        // 获取当月的开始和结束日期
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var firstDay = year + '-' + (month < 10 ? '0' + month : month) + '-01';
        var lastDay = year + '-' + (month < 10 ? '0' + month : month) + '-' + new Date(year, month, 0).getDate();
        
        // 初始化加载数据
        $(function() {
            loadProjectDetail();
            // 延迟一下确保日期选择器已经初始化
            setTimeout(function() {
                loadOperationRecords('all', firstDay, lastDay);
            }, 500);
        });
        
        laydate.render({
            elem: '#dateRange',
            type: 'date',
            range: true,  // 开启日期范围选择
            //value: firstDay + ' - ' + lastDay,  // 设置默认值为当月
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

        initProjectTable();
        
        // 初始化在用工程表格
        function initProjectTable() {
            var searchParams = {
                keyword: $('input[name="keyword"]').val(),
                status: $('select[name="status"]').val()
            };

            var loadIndex = layer.load(1, {
                shade: [0.1, '#fff']
            });
            table.render({
                elem: '#projectTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getProjectMaDiff',
                method: 'post',
                where: {
                    projectId: localStorage.getItem('dataOverviewProjectId'),
                    ...searchParams
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
                    {title: '序号', type: 'numbers', width: '10%'},
                    {title: '设备类型', width: '20%', templet: function(d){
                        return d.isCount == 0 ? '设备' : '机具';
                    }},
                    {field: 'maType', title: '设备名称', width: '20%'},
                    {field: 'maName', title: '规格型号', width: '20%'},
                    {field: 'useCount', title: '领用量', width: '10%'},
                    {field: 'returnCount', title: '退还量', width: '10%'},
                    {field: 'diffCount', title: '差缺量', width: '10%'}
                ]],
                done: function() {
                    layer.close(loadIndex);
                }
            });
        }
        
        // 绑定搜索按钮事件
        $('#searchBtn').on('click', function() {
            initProjectTable();
        });

        // 绑定清空按钮事件
        $('#resetBtn').on('click', function() {
            $('input[name="keyword"]').val('');
            $('select[name="status"]').val('');
            initProjectTable();
        });

        // 绑定导出按钮事件
        $('#exportBtn').on('click', function() {
            var loadIndex = layer.load(1);
            // 使用table.exportFile导出
            table.exportFile(
                'projectTable', // 表格 ID
                table.cache.projectTable, // 表格当前页数据
                {
                    type: 'xlsx', // 导出类型
                    title: '工程明细', // 文件名
                    // 表头字段映射和样式
                    head: [
                        ['序号', '设备类型', '设备名称', '规格型号', '领用量', '退还量', '差缺量']
                    ],
                    // 自定义数据处理
                    filter: function(item, index) {
                        return [
                            index + 1,
                            item.isCount == 0 ? '设备' : '机具',
                            item.maType,
                            item.maName,
                            item.useCount,
                            item.returnCount,
                            item.diffCount
                        ];
                    }
                }
            );
            
            layer.close(loadIndex);
        });
        
        // 加载操作记录
        function loadOperationRecords(type = 'all', startDate = '', endDate = '') {
            var bonuspath = localStorage.getItem("bonuspath");
            var loadIndex = layer.load(1, {
                shade: [0.1, '#fff']
            });
            
            var requestData = {
                projectId: localStorage.getItem('equipmentViewProjectId')
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
                default:
                    apiUrl = '/backstage/indexHomeDetails/getProjectMaRecord';
            }

            $.ajax({
                type: 'post',
                url: '${bonuspath}/backstage/indexHomeDetails/getProjectMaRecord',
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