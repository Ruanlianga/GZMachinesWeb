<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<c:set var="bonuspath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script src="${bonuspath}/static/js/ace/select2.full.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>变更警告</title>
    <style>
        .timeline-view, .list-view {
            padding: 20px;
            max-width: 95% !important;
        }
        
        .timeline-view {
            position: relative;
            margin-left: 120px;
            padding-left: 50px;
        }
        
        /* 修改主时间线样式 */
        .timeline-view::before {
            content: '';
            position: absolute;
            left: 0;  /* 调整到最左侧 */
            top: 0;
            bottom: 0;
            width: 2px;
            background: #e9ecef;
            z-index: 1;
        }
        
        .timeline-item {
            position: relative;
            margin-bottom: 40px;
            background: #f8f9fa;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            transition: all 0.3s ease;
            border: none;
            padding: 20px;
        }
        
        .timeline-item:hover {
            background: #fff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        
        /* 修改时间线圆点样式 */
        .timeline-item::before {
            content: '';
            position: absolute;
            left: -54px;  /* 调整圆点位置到时间线上 */
            top: 43px;
            width: 14px;
            height: 14px;
            border-radius: 50%;
            background: #007bff;
            border: 2px solid #fff;
            z-index: 2;
        }
        
        /* 添加连接线 */
        .timeline-item::after {
            content: '';
            position: absolute;
            left: -40px;  /* 位置在圆点和内容之间 */
            top: 50px;
            width: 40px;
            height: 2px;
            background: #007bff;
            transition: opacity 0.3s ease;
        }
        
        /* 添加左侧时间标签 */
        .timeline-date {
            position: absolute;
            top: 12px;
            right: 20px;
            text-align: right;
            color: #6c757d;
            font-size: 13px;
        }
        
        .list-item {
            padding: 20px;
            border: 1px solid #e9ecef;
            margin-bottom: 15px;
            border-radius: 8px;
            background: #f8f9fa;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            transition: all 0.3s ease;
        }
        
        .list-item:hover {
            background: #fff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        
        /* 视图切换按钮组样式 */
        .view-toggle {
            margin: 20px;
        }
        
        .view-toggle .btn-group {
            background: #fff;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            overflow: hidden;
        }
        
        .view-toggle .btn {
            border: none;
            padding: 8px 20px;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        
        .view-toggle .btn-primary {
            background: #409eff;
        }
        
        .view-toggle .btn-secondary {
            background: #f8f9fa;
            color: #606266;
        }
        
        /* 搜索区域容器 */
        .search-container {
            margin: 20px;
            margin-top: 0;
        }
        
        /* 搜索区域样式 */
        .search-area {
            display: flex;
            align-items: center;
            gap: 15px;
            padding: 15px;
            background: #fff;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
        }
        
        .search-area .form-group {
            margin: 0;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        
        .search-area label {
            margin: 0;
            color: #606266;
            font-weight: normal;
        }
        
        .search-area input {
            width: 200px;
        }
        
        .search-area .btn-search {
            background: #409eff;
            color: #fff;
            border: none;
            padding: 7px 15px;
            border-radius: 4px;
            cursor: pointer;
            transition: all 0.3s;
        }
        
        .search-area .btn-search:hover {
            background: #66b1ff;
        }
        
        .search-area .btn-reset {
            background: #f4f4f5;
            color: #606266;
            border: none;
            padding: 7px 15px;
            border-radius: 4px;
            cursor: pointer;
            transition: all 0.3s;
        }
        
        .search-area .btn-reset:hover {
            background: #e9e9eb;
        }
        
        /* 美化内部文字样式 */
        .timeline-item h4, .list-item h4 {
            color: #2c3e50;
            margin-bottom: 15px;
        }

        .timeline-item p, .list-item p {
            color: #5a6268;
            margin-bottom: 8px;
        }
        
        /* 用户信息样式 */
        .user-info {
            position: absolute;
            top: 0;
            left: 0;
            display: flex;
            align-items: center;
            gap: 10px;
            max-width: 300px;
        }
        
        .user-avatar {
            width: 24px;
            height: 24px;
            border-radius: 50%;
            object-fit: cover;
        }
        
        .user-name {
            font-size: 16px;
            color: #111;
            font-weight: 500;
        }

        .role-name {
            font-size: 12px;
            color: #666;
            font-weight: 400;
        }
        
        /* 复制按钮样式 */
        .copy-btn {
            position: absolute;
            right: 20px;
            bottom: 20px;
            padding: 4px 8px;
            background: #409eff;
            border: 1px solid #ffffff;
            border-radius: 4px;
            cursor: pointer;
            display: none;  /* 默认隐藏 */
            color: #ffffff;
            font-size: 12px;
            transition: all 0.2s;
        }
        
        .timeline-item:hover .copy-btn {
            display: block;  /* 悬浮时显示 */
        }
        
        /* 添加日历样式 */
        .timeline-calendar {
            position: absolute;
            left: -120px;
            top: 20px;
            width: 60px;
            height: 60px;
            background: #fff;
            border-radius: 6px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
            overflow: hidden;
            transition: opacity 0.3s ease;
        }
        
        .calendar-month {
            background: #409eff;
            color: #fff;
            font-size: 12px;
            padding: 2px 0;
            text-transform: uppercase;
        }
        
        .calendar-day {
            font-size: 24px;
            font-weight: bold;
            color: #2c3e50;
            line-height: 38px;
        }
        
        /* 内容区域样式 */
        .timeline-content {
            position: relative;
            padding-top: 45px;
            min-height: 120px;
        }
        
        /* 调整标题和内容的间距 */
        .timeline-item h4 {
            margin-bottom: 15px;
            color: #2c3e50;
            padding-right: 100px;
        }
        
        .timeline-item p {
            margin-bottom: 10px;
            color: #5a6268;
        }

        /* 当日历隐藏时，也隐藏连接线 */
        .timeline-item:has(.timeline-calendar[style*="display: none"]) ::after {
            opacity: 0;
        }

        .no-data {
            text-align: center;
            padding: 20px;
            color: #999;
            font-size: 14px;
        }

        /* 表格样式 */
        .warning-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: #fff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
        }
        
        .warning-table th {
            background: #f8f9fa;
            padding: 12px 15px;
            text-align: left;
            font-weight: 500;
            color: #2c3e50;
            border-bottom: 2px solid #dee2e6;
        }
        
        .warning-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #dee2e6;
            color: #5a6268;
        }
        
        .warning-table tbody tr:hover {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <div class="container" style="max-width: 95%;width: 95%;">
        <div class="view-toggle">
            <div class="btn-group">
                <button class="btn btn-primary" onclick="toggleView('timeline')">时间轴视图</button>
                <button class="btn btn-secondary" onclick="toggleView('list')">列表视图</button>
            </div>
        </div>
        
        <!-- 搜索区域移到按钮下方 -->
        <div class="search-container">
            <div class="search-area" id="timelineSearch">
                <div class="form-group">
                    <label>时间范围：</label>
                    <input type="text" class="form-control" id="dateRange" readonly>
                </div>
                <div class="form-group">
                    <label>关键字：</label>
                    <input type="text" class="form-control" id="searchKeyword" placeholder="请输入搜索关键字">
                </div>
                <button class="btn-search" onclick="searchData()">
                    <i class="fa fa-search"></i> 搜索
                </button>
                <button class="btn-reset" onclick="resetSearch()">
                    <i class="fa fa-refresh"></i> 重置
                </button>
            </div>
        </div>

        <!-- 时间轴视图 -->
        <div id="timelineView" class="timeline-view">
            <div class="timeline-item">
                <div class="timeline-calendar">
                    <div class="calendar-month">三月</div>
                    <div class="calendar-day">20</div>
                </div>
                <div class="timeline-content">
                    <div class="user-info">
                        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" class="user-avatar" alt="用户头像">
                        <span class="user-name">张三</span>
                        <span class="role-name">库管员</span>
                    </div>
                    <div class="timeline-date">2024-03-20 10:30</div>
                    <h4>变更警告 #1</h4>
                    <p>设备编号: DEV-001</p>
                    <p>警告内容: 设备参数异常变更</p>
                    <button class="copy-btn" onclick="copyTitle(this)">
                        <i class="fa fa-copy"></i> 复制
                    </button>
                </div>
            </div>
            
            <div class="timeline-item">
                <div class="timeline-calendar">
                    <div class="calendar-month">三月</div>
                    <div class="calendar-day">20</div>
                </div>
                <div class="timeline-content">
                    <div class="user-info">
                        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" class="user-avatar" alt="用户头像">
                        <span class="user-name">李四</span>
                        <span class="role-name">维修员</span>
                    </div>
                    <div class="timeline-date">2024-03-20 11:45</div>
                    <h4>变更警告 #2</h4>
                    <p>设备编号: DEV-001</p>
                    <p>警告内容: 设备参数异常变更FFFFFF</p>
                    <button class="copy-btn" onclick="copyTitle(this)">
                        <i class="fa fa-copy"></i> 复制
                    </button>
                </div>
            </div>

            <div class="timeline-item">
                <div class="timeline-calendar">
                    <div class="calendar-month">三月</div>
                    <div class="calendar-day">19</div>
                </div>
                <div class="timeline-content">
                    <div class="user-info">
                        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" class="user-avatar" alt="用户头像">
                        <span class="user-name">王五</span>
                        <span class="role-name">检验员</span>
                    </div>
                    <div class="timeline-date">2024-03-19<br>14:20</div>
                    <h4>变更警告 #3</h4>
                    <p>设备编号: DEV-001</p>
                    <p>警告内容: 设备参数异常变更xx</p>
                    <button class="copy-btn" onclick="copyTitle(this)">
                        <i class="fa fa-copy"></i> 复制
                    </button>
                </div>
            </div>

            <div class="timeline-item">
                <div class="timeline-calendar">
                    <div class="calendar-month">三月</div>
                    <div class="calendar-day">19</div>
                </div>
                <div class="timeline-content">
                    <div class="user-info">
                        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" class="user-avatar" alt="用户头像">
                        <span class="user-name">孙六</span>
                        <span class="role-name">检验员</span>
                    </div>
                    <div class="timeline-date">2024-03-19<br>14:20</div>
                    <h4>变更警告 #4</h4>
                    <p>设备编号: DEV-004</p>
                    <p>警告内容: 设备参数异常变更xx</p>
                    <button class="copy-btn" onclick="copyTitle(this)">
                        <i class="fa fa-copy"></i> 复制
                    </button>
                </div>
            </div>

            <div class="timeline-item">
                <div class="timeline-calendar">
                    <div class="calendar-month">三月</div>
                    <div class="calendar-day">18</div>
                </div>
                <div class="timeline-content">
                    <div class="user-info">
                        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" class="user-avatar" alt="用户头像">
                        <span class="user-name">刘七</span>
                        <span class="role-name">试验员</span>
                    </div>
                    <div class="timeline-date">2024-03-18<br>15:20</div>
                    <h4>变更警告 #5</h4>
                    <p>设备编号: DEV-005</p>
                    <p>警告内容: 设备参数异常变更xx</p>
                    <button class="copy-btn" onclick="copyTitle(this)">
                        <i class="fa fa-copy"></i> 复制
                    </button>
                </div>
            </div>
        </div>

        <!-- 列表视图 -->
        <div id="listView" class="list-view" style="display:none;">
            <table class="warning-table">
                <thead>
                    <tr>
                        <th>时间</th>
                        <th>标题</th>
                        <th>来源</th>
                        <th>保有量变化</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function toggleView(viewType) {
            if (viewType === 'timeline') {
                $('#timelineView').show();
                $('#listView').hide();
                $('#timelineSearch').show();
                $('.view-toggle button:first-child').addClass('btn-primary').removeClass('btn-secondary');
                $('.view-toggle button:last-child').addClass('btn-secondary').removeClass('btn-primary');
            } else {
                $('#timelineView').hide();
                $('#listView').show();
                $('#timelineSearch').hide();
                $('.view-toggle button:first-child').addClass('btn-secondary').removeClass('btn-primary');
                $('.view-toggle button:last-child').addClass('btn-primary').removeClass('btn-secondary');
            }
        }

        // 加载警告数据
        function loadWarningData() {
            $.ajax({
                url: bonuspath + '/backstage/maTotalChangeWarn/getChangeWarnList',
                type: 'POST',
                dataType: "json",
                success: function(response) {
                    // 检查返回的数据结构
                    if (!response.obj || !Array.isArray(response.obj)) {
                        console.error('Invalid data format received');
                        $('#timelineView').html('<div class="no-data">暂无数据</div>');
                        return;
                    }
                    // 过滤掉无效的数据项
                    const warnings = response.obj.filter(item => item && typeof item === 'object');
                    renderTimelineView(warnings);
                    renderListView(warnings);
                },
                error: function(xhr, status, error) {
                    layer.msg('加载数据失败：' + error, {
                        icon: 2,
                        time: 2000
                    });
                }
            });
        }

        // 渲染时间轴视图
        function renderTimelineView(warnings) {
            console.log('Starting renderTimelineView with:', warnings);
            if (!Array.isArray(warnings) || warnings.length === 0) {
                console.log('No warnings to display');
                $('#timelineView').html('<div class="no-data">暂无数据</div>');
                return;
            }

            let timelineHtml = '';
            warnings.forEach(function(warning) {
                // 检查每个必需字段
                if (!warning.createTime) {
                    console.error('Warning missing createTime:', warning);
                    return;
                }

                // 确保日期是正确的
                let date;
                try {
                    // 处理日期字符串
                    date = new Date(warning.createTime.replace(/-/g, '/'));
                    if (isNaN(date.getTime())) {
                        console.error('Invalid date:', warning.createTime);
                        date = new Date();
                    }
                } catch (e) {
                    console.error('Date parsing error:', e);
                    date = new Date();
                }

                const month = date.getMonth() + 1;
                const day = date.getDate();
                const timeStr = warning.createTime.substring(0, 16); // 只显示到分钟

                // 使用空字符串作为默认值，而不是显示 'false'
                const avatar = warning.userAvatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png';
                const userName = warning.userName || '未知用户';
                const userRole = warning.userRole || '未知角色';
                const title = warning.title || '';
                const deviceCode = warning.deviceCode || '';
                const taskCode = warning.taskCode || '';
                const warningContent = warning.warningContent || '';

                // 打印变量值
                console.log('Variables:', {
                    month, day, timeStr, avatar, userName, userRole, title, deviceCode, warningContent
                });

                // 使用字符串拼接方式
                timelineHtml += 
                    '<div class="timeline-item">' +
                    '<div class="timeline-calendar">' +
                    '<div class="calendar-month">' + month + '月</div>' +
                    '<div class="calendar-day">' + day + '</div>' +
                    '</div>' +
                    '<div class="timeline-content">' +
                    '<div class="user-info">' +
                    '<img src="' + avatar + '" class="user-avatar" alt="用户头像">' +
                    '<span class="user-name">' + userName + '</span>' +
                    '<span class="role-name">' + userRole + '</span>' +
                    '</div>' +
                    '<div class="timeline-date">' + timeStr + '</div>' +
                    '<h4>' + title + '</h4>' +
                    '<p><span style="color: #ff4747;">保有量变化:</span> ' + warningContent + '</p>' +
                    '<p>来源: ' + taskCode + '</p>' +
                    '<button class="copy-btn" onclick="copyTitle(this)">' +
                    '<i class="fa fa-copy"></i> 复制' +
                    '</button>' +
                    '</div>' +
                    '</div>';

                // 打印当前HTML片段
                console.log('Current HTML fragment:', timelineHtml);
            });
            
            $('#timelineView').html(timelineHtml);
            handleCalendarDisplay();
        }

        // 渲染列表视图
        function renderListView(warnings) {
            if (!Array.isArray(warnings) || warnings.length === 0) {
                $('#listView').html('<div class="no-data">暂无数据</div>');
                return;
            }

            let tableHtml = '<table class="warning-table"><thead><tr>' +
                '<th>序号</th>' +
                '<th>设备类型</th>' +
                '<th>设备名称</th>' +
                '<th>规格型号</th>' +
                '<th>总保有量</th>' +
                '<th>来源</th>' +
                '<th>保有量变化</th>' +
                '<th>时间</th>' +
                '</tr></thead><tbody>';

            warnings.forEach(function(warning, index) {
                const timeStr = warning.createTime.substring(0, 16); // 只显示到分钟
                const title = warning.title || '';
                const taskCode = warning.taskCode || '';
                const warningContent = warning.warningContent || '';
                const deviceType = warning.deviceType || '';
                const deviceName = warning.deviceName || '';
                const deviceModel = warning.typeName || '';
                const num = warning.num || '';
                

                tableHtml += '<tr>' +
                    '<td>' + (index + 1) + '</td>' +
                    '<td>' + deviceType + '</td>' +
                    '<td>' + deviceName + '</td>' +
                    '<td>' + deviceModel + '</td>' +
                    '<td>' + num + '</td>' +
                    '<td>' + taskCode + '</td>' +
                    '<td>' + warningContent + '</td>' +
                    '<td>' + warning.createTime + '</td>' +
                    '</tr>';
            });
            
            tableHtml += '</tbody></table>';
            $('#listView').html(tableHtml);
        }

        // 处理日历显示
        function handleCalendarDisplay() {
            let lastMonth = '';
            let lastDay = '';
            
            $('.timeline-item').each(function(index) {
                const calendar = $(this).find('.timeline-calendar');
                const month = calendar.find('.calendar-month').text();
                const day = calendar.find('.calendar-day').text();
                
                if (month === lastMonth && day === lastDay) {
                    calendar.hide();
                } else {
                    lastMonth = month;
                    lastDay = day;
                }
            });
        }

        $(document).ready(function() {
            loadWarningData();
        });

        function copyTitle(btn) {
            const title = $(btn).siblings('p').text();
            
            // 创建临时textarea元素
            const textarea = document.createElement('textarea');
            textarea.value = title;
            document.body.appendChild(textarea);
            
            // 选择文本并复制
            textarea.select();
            document.execCommand('copy');
            
            // 移除临时元素
            document.body.removeChild(textarea);
            
            // 显示复制成功提示
            layer.msg('复制成功', {
                icon: 1,
                time: 1000
            });
        }

        // 初始化日期范围选择器
        layui.use('laydate', function(){
            var laydate = layui.laydate;
            laydate.render({
                elem: '#dateRange',
                type: 'datetime',
                range: true,
                format: 'yyyy-MM-dd HH:mm:ss'
            });
        });
        
        // 搜索功能
        function searchData() {
            const dateRange = $('#dateRange').val();
            const keyword = $('#searchKeyword').val();
            let [startTime, endTime] = ['', ''];
            
            if (dateRange) {
                [startTime, endTime] = dateRange.split(' - ');
            }
            
            $.ajax({
                url: bonuspath + '/backstage/maTotalChangeWarn/getChangeWarnList',
                type: 'POST',
                data: {
                    startTime: startTime,
                    endTime: endTime,
                    keyword: keyword
                },
                success: function(response) {
                    if (!response.obj || !Array.isArray(response.obj)) {
                        layer.msg('无符合条件数据', {
                            icon: 2,
                            time: 2000
                        });
                        return;
                    }
                    const warnings = response.obj.filter(item => item && typeof item === 'object');
                    renderTimelineView(warnings);
                    renderListView(warnings);
                },
                error: function(xhr, status, error) {
                    layer.msg('搜索失败：' + error, {
                        icon: 2,
                        time: 2000
                    });
                }
            });
        }
        
        // 重置搜索条件
        function resetSearch() {
            $('#dateRange').val('');
            $('#searchKeyword').val('');
            loadWarningData();
        }
    </script>
</body>
</html>
