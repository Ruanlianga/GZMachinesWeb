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
    <title>待办事项</title>
    <link href="https://www.layuicdn.com/layui-v2.6.8/css/layui.css" rel="stylesheet">

    <style>
        .todo-container {
            padding: 20px;
        }
        .todo-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            border-bottom: 1px solid #e8e8e8;
            background: #fff;
            padding-bottom: 10px;
        }
        .todo-tabs {
            flex: 1;
            display: flex;
            align-items: center;
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
        .todo-tab {
            padding: 0 25px;
            height: 40px;
            line-height: 40px;
            cursor: pointer;
            position: relative;
            color: #666;
            transition: all 0.3s;
        }
        .todo-tab:hover {
            color: #1E9FFF;
        }
        .todo-tab.active {
            color: #1E9FFF;
            font-weight: bold;
        }
        .todo-tab.active:after {
            content: '';
            position: absolute;
            bottom: -1px;
            left: 0;
            width: 100%;
            height: 2px;
            background-color: #1E9FFF;
        }
        .table-container {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="todo-container">
        <div class="todo-header">
            <div class="todo-tabs">
                <div class="todo-tab active" data-type="all">全部待办</div>
                <div class="todo-tab" data-type="purchase">新购机具</div>
                <div class="todo-tab" data-type="out">领料出库</div>
                <div class="todo-tab" data-type="return">退料申请</div>
            </div>
            <button class="layui-btn layui-btn-primary" id="exportBtn">导出</button>
        </div>

        <div class="table-container">
            <table id="todoTable" lay-filter="todoTable"></table>
        </div>
    </div>
    
    <script src="${bonuspath}/static/js/index/layui.min.js"></script>
    <script>
    layui.use(['table', 'layer'], function(){
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.$;
        var currentType = 'all'; // 用于记录当前选中的类型

        // 加载表格数据
        function loadTableData(type) {
            currentType = type; // 更新当前类型
            // 根据类型选择不同的接口
            var apiUrl;
            switch(type) {
                case 'purchase':
                    apiUrl = '/backstage/indexHomeDetails/getToNewList';
                    break;
                case 'out':
                    apiUrl = '/backstage/indexHomeDetails/getToOutList';
                    break;
                case 'return':
                    apiUrl = '/backstage/indexHomeDetails/getToBackList';
                    break;
                default:
                    apiUrl = '/backstage/indexHomeDetails/getTodoList';
            }

            table.render({
                elem: '#todoTable',
                url: '${bonuspath}' + apiUrl,
                method: 'post',
                page: false,
                height: 'full-100',
                cols: [[
                    {type: 'numbers', title: '序号', width: '5%', fixed: 'left'},
                    {field: 'taskType', title: '任务类型', width: '10%'},
                    {field: 'taskName', title: '任务名称', width: '15%'},
                    {field: 'taskCode', title: '任务单号', width: '30%'},
                    {field: 'userName', title: '创建人', width: '10%'},
                    {field: 'time', title: '创建时间', width: '15%'},
                    {field: 'operation', title: '操作', width: '15%', fixed: 'right', templet: function(d){
                        return '<a class="layui-btn layui-btn-xs" lay-event="handle" data-url="'+ d.linkUrl +'">去处理</a>';
                    }}
                ]],
                response: {
                    statusCode: 200
                },
                parseData: function(res) {
                    return {
                        "code": 200,
                        "msg": res.resMsg,
                        "count": res.obj.length,
                        "data": res.obj
                    };
                }
            });
        }

        // 绑定选项卡点击事件
        $('.todo-tab').on('click', function() {
            $('.todo-tab').removeClass('active');
            $(this).addClass('active');
            var type = $(this).data('type');
            loadTableData(type);
        });

        // 监听表格工具条事件
        table.on('tool(todoTable)', function(obj){
            var data = obj.data;
            // 打印详细日志
            console.log('待办事项数据:', data);
            var menuId = "menu9" + getRandomInt(1, 100);  // 使用固定的菜单ID
            var taskType = data.taskType || '待办任务';
            var url = bonuspath + data.linkUrl;
            
            console.log('准备打开菜单:', {
                menuId: menuId,
                taskType: taskType,
                url: url
            });
            
            try {
                console.log("进来了");
                if(window.parent && window.parent.parent) {
                    window.parent.parent.openMenu("1", menuId, "menu7", taskType, data.linkUrl);
                } else if(window.top) {
                    window.top.openMenu("1", menuId, "menu7", taskType, data.linkUrl);
                } else {
                    console.error('未找到TabControlAppend方法');
                    layer.msg('页面跳转失败，请刷新重试', {icon: 2});
                }
            } catch(e) {
                console.error('跳转异常:', e);
                layer.msg('页面跳转异常，请刷新重试', {icon: 2});
            }
        });

        function getRandomInt(min, max) {
            min = Math.ceil(min);
            max = Math.floor(max);
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }

        // 绑定导出按钮事件
        $('#exportBtn').on('click', function() {
            var loadIndex = layer.msg('正在导出数据，请稍候...', {
                icon: 16,
                shade: 0.3,
                time: 0
            });
            try {
                if (!table.cache.todoTable || table.cache.todoTable.length === 0) {
                    layer.msg('暂无数据可导出', {icon: 2});
                    return;
                }
                
                // 根据当前类型设置文件名
                var typeNames = {
                    'all': '全部待办',
                    'purchase': '新购机具',
                    'out': '领料出库',
                    'return': '退料申请'
                };
                
                setTimeout(function() {
                    table.exportFile(
                        'todoTable',
                        table.cache.todoTable,
                        {
                            type: 'xlsx',
                            title: typeNames[currentType] + '_' + new Date().toLocaleDateString().replace(/\//g, '-'),
                            head: [
                                ['序号', '任务类型', '任务名称', '任务单号', '创建人', '创建时间']
                            ],
                            filter: function(item, index) {
                                return [
                                    index + 1,
                                    item.taskType,
                                    item.taskName,
                                    item.taskCode,
                                    item.userName,
                                    item.time
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

        // 初始加载全部待办数据
        loadTableData('all');
    });
    </script>
</body>
</html> 