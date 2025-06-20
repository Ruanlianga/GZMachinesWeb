<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../systemset.jsp" %>
    <%@include file="../baseset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
    <script src="${bonuspath}/static/js/layer.js"></script>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
    <script src="${bonuspath}/static/js/layui/layui.js"></script>
    <title>工程详情</title>
    <style>
        .filter-container {
            padding: 20px;
            background: #fff;
        }
        .filter-row {
            margin-bottom: 15px;
            border-bottom: 1px solid #f0f0f0;
            padding-bottom: 15px;
            display: flex;
            align-items: flex-start;
        }
        .filter-row:last-child {
            border-bottom: none;
        }
        .filter-label {
            display: inline-block;
            width: 80px;
            color: #666;
            font-weight: bold;
            margin-right: 10px;
            flex-shrink: 0;
        }
        .filter-options {
            display: inline-flex;
            flex-wrap: wrap;
            gap: 10px;
            flex: 1;
        }
        .filter-option {
            padding: 5px 15px;
            border-radius: 3px;
            cursor: pointer;
            transition: all 0.3s;
            color: #666;
        }
        .filter-option:hover {
            color: #1E9FFF;
        }
        .filter-option.active {
            background: #1E9FFF;
            color: #fff;
        }
        .table-container {
            padding: 0 20px;
            margin-top: 20px;
        }
        .selected-filters {
            padding: 10px 20px;
            background: #f8f8f8;
            margin: 0 20px;
            border-radius: 4px;
        }
        .selected-filter {
            display: inline-block;
            margin-right: 10px;
            padding: 2px 8px;
            background: #e6f7ff;
            border: 1px solid #91d5ff;
            border-radius: 2px;
            color: #1890ff;
            font-size: 12px;
        }
        .project-link {
            color: #1E9FFF;
            text-decoration: none;
        }
        .project-link:hover {
            color: #0d8aff;
            text-decoration: underline;
        }
        .filter-result-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background: #f8f8f8;
            margin: 0 20px;
            border-radius: 4px;
        }
        
        .selected-filters {
            flex: 1;
        }
        
        #exportBtn {
            height: 32px;
            line-height: 30px;
            padding: 0 15px;
            font-size: 13px;
            border: 1px solid #dcdfe6;
            margin-left: 15px;
        }
        
        #exportBtn:hover {
            border-color: #c6e2ff;
            color: #409eff;
            background-color: #ecf5ff;
        }
        
        // 添加搜索框样式
        .search-input {
            width: 200px;
            height: 32px;
            padding: 0 10px;
            border: 1px solid #e6e6e6;
            border-radius: 2px;
            margin-right: 10px;
        }
        
        .search-btn {
            height: 32px;
            line-height: 32px;
            padding: 0 15px;
        }
    </style>
</head>
<body>
    <div class="filter-container">
        <!-- 搜索框 -->
        <div class="filter-row">
            <span class="filter-label">工程名称：</span>
            <div class="filter-options">
                <input type="text" id="projectNameSearch" class="layui-input" style="width: 200px;" placeholder="请输入工程名称">
                <button class="layui-btn layui-btn-primary" id="searchBtn">搜索</button>
            </div>
        </div>
        
        <!-- 分公司筛选 -->
        <div class="filter-row">
            <span class="filter-label">分公司：</span>
            <div class="filter-options" id="companyFilter">
                <div class="filter-option active" data-value="all">全部</div>
                <!-- 分公司选项将通过JavaScript动态添加 -->
            </div>
        </div>
        
        <!-- 年份筛选 -->
        <div class="filter-row">
            <span class="filter-label">年份：</span>
            <div class="filter-options" id="yearFilter">
                <!-- 年份选项将通过JavaScript动态添加 -->
            </div>
        </div>
        
        <!-- 月份筛选 -->
        <div class="filter-row">
            <span class="filter-label">月份：</span>
            <div class="filter-options" id="monthFilter">
                <div class="filter-option active" data-value="all">不限</div>
                <div class="filter-option" data-value="1">1月</div>
                <div class="filter-option" data-value="2">2月</div>
                <div class="filter-option" data-value="3">3月</div>
                <div class="filter-option" data-value="4">4月</div>
                <div class="filter-option" data-value="5">5月</div>
                <div class="filter-option" data-value="6">6月</div>
                <div class="filter-option" data-value="7">7月</div>
                <div class="filter-option" data-value="8">8月</div>
                <div class="filter-option" data-value="9">9月</div>
                <div class="filter-option" data-value="10">10月</div>
                <div class="filter-option" data-value="11">11月</div>
                <div class="filter-option" data-value="12">12月</div>
            </div>
        </div>
    </div>

    <!-- 已选择的筛选条件 -->
    <div class="filter-result-container">
        <div class="selected-filters" id="selectedFilters">
            <span class="filter-option">已选择条件:</span>
            <!-- 已选择的条件将通过JavaScript动态添加 -->
        </div>
        <button class="layui-btn layui-btn-primary" id="exportBtn">导出</button>
    </div>

    <!-- 表格容器 -->
    <div class="table-container">
        <table id="projectTable" lay-filter="projectTable"></table>
    </div>

    <script src="${bonuspath}/static/js/index/layui.min.js"></script>
    <script>
    layui.use(['table', 'layer'], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.$;

        // 初始化年份选项
        function initYearOptions() {
            var currentYear = new Date().getFullYear();
            var html = '<div class="filter-option active" data-value="all">不限</div>';
            for(var i = 7; i >= 0; i--) {
                var year = currentYear - i;
                html += '<div class="filter-option" data-value="' + year + '">' + year + '年</div>';
            }
            $('#yearFilter').html(html);
            $('#yearFilter .filter-option').first().addClass('active');
        }

        // 初始化分公司选项
        function loadCompanyOptions() {
            $.ajax({
                url: '${bonuspath}/backstage/indexHomeDetails/getProjectCompany',
                type: 'post',
                dataType: "json",
                success: function(res) {
                    if(res.obj && res.obj.length > 0) {
                        var html = '<div class="filter-option active" data-value="all">全部</div>';
                        res.obj.forEach(function(company) {
                            html += '<div class="filter-option" data-value="' + company.companyId + '">' + 
                                   company.companyName + '</div>';
                        });
                        $('#companyFilter').html(html);
                    }
                }
            });
        }

        // 更新已选择的筛选条件
        function updateSelectedFilters() {
            var html = '<span class="filter-option">已选择条件:</span>';
            var company = $('#companyFilter .active').text();
            var year = $('#yearFilter .active').text();
            var month = $('#monthFilter .active').text();

            if(company) html += '<span class="selected-filter">' + company + '</span>';
            if(year) html += '<span class="selected-filter">' + year + '</span>';
            if(month) html += '<span class="selected-filter">' + month + '</span>';

            $('#selectedFilters').html(html);
        }

        // 加载表格数据
        function loadTableData() {
            var company = $('#companyFilter .active').data('value');
            var year = $('#yearFilter .active').data('value');
            var month = $('#monthFilter .active').data('value');
            var projectName = $('#projectNameSearch').val().trim();
            console.log(company, year, month);
            if(isNaN(company)){
                company = '';
            }

            table.render({
                elem: '#projectTable',
                url: '${bonuspath}/backstage/indexHomeDetails/getProjectDiff',
                method: 'post',
                where: {
                    companyId: company,
                    year: year,
                    month: month,
                    projectName: projectName
                },
                page: true,
                limit: 50,
                limits: [10, 20, 50, 100, 1000, 10000],
                height: 'full-100',
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
                },
                cols: [[
                    {title: '序号', type: 'numbers', width: '5%'},
                    {field: 'projectName', title: '工程名称', width: '34%', templet: function(d) {
                        return '<a class="project-link" href="javascript:;" lay-event="projectDetail">' + d.projectName + '</a>';
                    }},
                    {field: 'useCount', title: '领用量', width: '6%'},
                    {field: 'returnCount', title: '退还量', width: '6%'},
                    {field: 'diffCount', title: '差缺量', width: '6%'},
                    {field: 'firstTime', title: '首次领料日期', width: '11%'},
                    {field: 'lastTime', title: '最新领料日期', width: '11%'},
                    {field: 'backTime', title: '最新退料日期', width: '11%'},
                    {field: 'companyName', title: '所属分公司', width: '10%'}
                ]],
                response: {
                    statusCode: 200
                }
            });
        }

        // 添加表格行工具事件
        table.on('tool(projectTable)', function(obj){
            var data = obj.data;
            console.log(data);
            if(obj.event === 'projectDetail'){
                localStorage.setItem('dataOverviewProjectId', data.projectId);
                localStorage.setItem('projectDetailData', JSON.stringify({
                    useCount: data.useCount || 0,
                    backCount: data.backCount || 0,
                    diffCount: data.diffCount || 0,
                    projectName: data.projectName,
                    companyName: data.companyName
                }));

                layer.open({
                    type: 2,
                    title: '工程详细信息',
                    area: ['95%', '95%'],
                    content: '${bonuspath}/backstage/indexHome/dataOverview/project/detail',
                    success: function(layero, index) {
                        // 可以在这里对打开的iframe进行一些操作
                    }
                });
            }
        });

        // 绑定筛选项点击事件
        $('.filter-options').on('click', '.filter-option', function() {
            $(this).siblings().removeClass('active');
            $(this).addClass('active');
            updateSelectedFilters();
            loadTableData();
        });

        // 绑定导出按钮事件
        $('#exportBtn').on('click', function() {
            var loadIndex = layer.msg('正在导出数据，请稍候...', {
                icon: 16,
                shade: 0.3,
                time: 0
            });
            try {
                if (!table.cache.projectTable || table.cache.projectTable.length === 0) {
                    layer.msg('暂无数据可导出', {icon: 2});
                    return;
                }
                
                setTimeout(function() {
                    table.exportFile(
                        'projectTable',
                        table.cache.projectTable,
                        {
                            type: 'xlsx',
                            title: '工程明细_' + new Date().toLocaleDateString().replace(/\//g, '-'),
                            head: [
                                ['序号', '工程名称', '领用量', '退还量', '差缺量', '首次领料日期', '最新领料日期', '最新退料日期', '所属分公司']
                            ],
                            filter: function(item, index) {
                                return [
                                    index + 1,
                                    item.projectName,
                                    item.useCount,
                                    item.backCount,
                                    item.diffCount,
                                    item.firstTime,
                                    item.lastTime,
                                    item.backTime,
                                    item.companyName
                                ];
                            }
                        }
                    );
                    layer.close(loadIndex);
                    layer.msg('导出成功', {icon: 1});
                }, 100);
            } catch (e) {
                console.error('导出失败:', e);
                layer.msg('导出失败，请稍后重试', {icon: 2});
                layer.close(loadIndex);
            }
        });

        // 绑定搜索按钮点击事件
        $('#searchBtn').on('click', function() {
            loadTableData();
        });

        // 绑定回车键搜索
        $('#projectNameSearch').on('keypress', function(e) {
            if(e.which === 13) {
                loadTableData();
            }
        });

        // 初始化
        initYearOptions();
        loadCompanyOptions();
        loadTableData();
    });
    </script>
</body>
</html> 