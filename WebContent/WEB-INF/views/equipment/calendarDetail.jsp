<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
    <script src="${bonuspath}/static/js/layui/layui.js"></script>
    <title>机具日历详情</title>
    <style>
        .calendar-detail-container {
            padding: 20px;
            padding-top: 80px;
            height: 100%;
            overflow-y: auto;
        }
        .overview-header {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            background: #f5f5f5;
            z-index: 1000;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            padding: 15px 20px;
        }
        .header-content {
            display: flex;
            align-items: center;
            gap: 20px;
            width: 100%;
        }
        .header-left {
            display: flex;
            align-items: center;
            gap: 15px;
            min-width: 200px;
        }
        .calendar-icon {
            width: 24px;
            height: 24px;
        }
        .current-date {
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }
        .task-overview {
            display: flex;
            align-items: center;
            gap: 10px;
            flex: 1;
        }
        .task-item {
            background: #fff;
            padding: 8px 15px;
            border-radius: 4px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            min-width: 110px;
            display: flex;
            flex-direction: row;
            align-items: center;
            gap: 10px;
            transition: all 0.3s ease;
            cursor: pointer;
        }
        .task-item:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .task-label {
            color: #666;
            font-size: 14px;
            white-space: nowrap;  /* 防止文字换行 */
        }
        .task-value {
            font-size: 18px;  /* 减小字号 */
            font-weight: bold;
            color: #1E9FFF;
            min-width: 30px;  /* 保证数字对齐 */
            text-align: right;  /* 数字右对齐 */
        }
        .table-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
            position: relative;
            z-index: 1;
            padding-bottom: 40px;
        }
        .table-section {
            background: #fff;
            border-radius: 4px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.05);
            scroll-margin-top: 100px; /* 确保定位时不被顶部固定区域遮挡 */
        }
        .table-title {
            padding: 15px 20px;
            border-bottom: 1px solid #eee;
            color: #333;
            font-size: 15px;
        }
        .table-content {
            padding: 15px;
        }
        .header-right {
            display: flex;
            gap: 10px;
            min-width: 180px;
            justify-content: flex-end;
        }
        .header-btn {
            padding: 6px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 13px;
            display: flex;
            align-items: center;
            gap: 5px;
        }
        .header-btn.export {
            background: #1E9FFF;
            color: #fff;
        }
        .header-btn.download {
            background: #fff;
            color: #666;
            border: 1px solid #ddd;
        }
        .header-btn i {
            font-size: 14px;
        }
        /* 领料出库 - 蓝色系 */
        .task-item[data-type="leaseOut"] {
            background: linear-gradient(135deg, #E3F2FD 0%, #BBDEFB 100%);
        }
        /* 退料接收 - 绿色系 */
        .task-item[data-type="return"] {
            background: linear-gradient(135deg, #E8F5E9 0%, #C8E6C9 100%);
        }
        /* 维修检验 - 橙色系 */
        .task-item[data-type="repair"] {
            background: linear-gradient(135deg, #FFF3E0 0%, #FFE0B2 100%);
        }
        /* 机具报废 - 红色系 */
        .task-item[data-type="scrap"] {
            background: linear-gradient(135deg, #FFEBEE 0%, #FFCDD2 100%);
        }
        /* 修试入库 - 紫色系 */
        .task-item[data-type="repairIn"] {
            background: linear-gradient(135deg, #F3E5F5 0%, #E1BEE7 100%);
        }
        /* 新购入库 - 青色系 */
        .task-item[data-type="purchase"] {
            background: linear-gradient(135deg, #E0F7FA 0%, #B2EBF2 100%);
        }
        /* 库存盘点 - 棕色系 */
        .task-item[data-type="inventory"] {
            background: linear-gradient(135deg, #EFEBE9 0%, #D7CCC8 100%);
        }
    </style>
</head>
<body>
    <div class="calendar-detail-container">
        <!-- 顶部固定区域 -->
        <div class="overview-header">
            <div class="header-content">
                <div class="header-left">
                    <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt="日历" class="calendar-icon">
                    <span class="current-date" id="currentDate">2024-01-01 ~ 2025-01-01</span>
                </div>
                <div class="task-overview">
                    <div class="task-item" data-type="leaseOut" onclick="scrollToTable('leaseOut')">
                        <span class="task-label">领料出库</span>
                        <span class="task-value" id="leaseOutCount">0</span>
                    </div>
                    <div class="task-item" data-type="return" onclick="scrollToTable('return')">
                        <span class="task-label">退料接收</span>
                        <span class="task-value" id="returnCount">0</span>
                    </div>
                    <div class="task-item" data-type="repair" onclick="scrollToTable('repair')">
                        <span class="task-label">维修检验</span>
                        <span class="task-value" id="repairCount">0</span>
                    </div>
                    <div class="task-item" data-type="scrap" onclick="scrollToTable('scrap')">
                        <span class="task-label">机具报废</span>
                        <span class="task-value" id="scrapCount">0</span>
                    </div>
                    <div class="task-item" data-type="repairIn" onclick="scrollToTable('repairIn')">
                        <span class="task-label">修饰入库</span>
                        <span class="task-value" id="repairInCount">0</span>
                    </div>
                    <div class="task-item" data-type="purchase" onclick="scrollToTable('purchase')">
                        <span class="task-label">新购入库</span>
                        <span class="task-value" id="purchaseCount">0</span>
                    </div>
                    <div class="task-item" data-type="inventory" onclick="scrollToTable('inventory')">
                        <span class="task-label">库存盘点</span>
                        <span class="task-value" id="inventoryCount">0</span>
                    </div>
                </div>
                <div class="header-right">
                    <button class="header-btn export" onclick="exportData()">
                        <i class="layui-icon layui-icon-export"></i>导出
                    </button>
                    <button class="header-btn download" onclick="downloadTemplate()">
                        <i class="layui-icon layui-icon-download-circle"></i>下载模板
                    </button>
                </div>
            </div>
        </div>

        <!-- 表格区域 -->
        <div class="table-container">
            <div class="table-section" id="leaseOut">
                <div class="table-title">领料出库</div>
                <div class="table-content">
                    <table id="leaseOutTable" lay-filter="leaseOutTable"></table>
                </div>
            </div>
            <div class="table-section" id="return">
                <div class="table-title">退料接收</div>
                <div class="table-content">
                    <table id="returnTable" lay-filter="returnTable"></table>
                </div>
            </div>
            <div class="table-section" id="repair">
                <div class="table-title">维修检验</div>
                <div class="table-content">
                    <table id="repairTable" lay-filter="repairTable"></table>
                </div>
            </div>
            <div class="table-section" id="scrap">
                <div class="table-title">机具报废</div>
                <div class="table-content">
                    <table id="scrapTable" lay-filter="scrapTable"></table>
                </div>
            </div>
            <div class="table-section" id="repairIn">
                <div class="table-title">修试入库</div>
                <div class="table-content">
                    <table id="repairInTable" lay-filter="repairInTable"></table>
                </div>
            </div>
            <div class="table-section" id="purchase">
                <div class="table-title">新购入库</div>
                <div class="table-content">
                    <table id="purchaseTable" lay-filter="purchaseTable"></table>
                </div>
            </div>
            <div class="table-section" id="inventory">
                <div class="table-title">库存盘点</div>
                <div class="table-content">
                    <table id="inventoryTable" lay-filter="inventoryTable"></table>
                </div>
            </div>
        </div>
    </div>

    <script src="${bonuspath}/static/js/index/layui.min.js"></script>
    <script>
    // 滚动到指定表格
    function scrollToTable(tableId) {
        layui.use(['jquery'], function(){
            var $ = layui.$;
            var $target = $('#' + tableId);
            if ($target.length) {
                var headerHeight = $('.overview-header').outerHeight();
                var targetTop = $target.offset().top - headerHeight - 20; // 20px额外空间
                $('html, body').animate({
                    scrollTop: targetTop
                }, 500);
            }
        });
    }
    
    layui.use(['table'], function(){
        var table = layui.table;
        var $ = layui.$;
        
        // 获取URL参数
        var date = localStorage.getItem('calendarDate') || '';
        var proId = localStorage.getItem('calendar_pro_id') || ''; // 工程ID
        
        // 格式化并显示日期
        // function formatDate(dateStr) {
        //     if (!dateStr) return '';
        //     var date = new Date(dateStr);
        //     var year = date.getFullYear();
        //     var month = (date.getMonth() + 1).toString().padStart(2, '0');
        //     var day = date.getDate().toString().padStart(2, '0');
        //     return `${year}年${month}月${day}日`;
        // }
        
        // 设置当前日期
        $('#currentDate').text(date);
        
        // 加载概览数据
        loadOverviewData();
        
        // 加载所有表格
        loadAllTables();
        
        // 加载概览数据
        function loadOverviewData() {
            let calendarDayData = JSON.parse(localStorage.getItem('calendarDayData'));
            console.log('hhhhh' + calendarDayData);
           
            if(calendarDayData) {
                $('#leaseOutCount').text(calendarDayData.leaseNum || 0);
                $('#returnCount').text(calendarDayData.backNum || 0);
                $('#repairCount').text(calendarDayData.checkNum || 0);
                $('#scrapCount').text(calendarDayData.scrapNum || 0);
                $('#repairInCount').text(calendarDayData.inputNum || 0);
                $('#purchaseCount').text(calendarDayData.newNum || 0);
                $('#inventoryCount').text(calendarDayData.bdNum || 0);
            }
        }
        
        // 加载所有表格
        function loadAllTables() {
            var tables = {
                leaseOut: {
                    elem: '#leaseOutTable',
                    url: '${bonuspath}/backstage/indexHomeDetails/getCalendarOut',
                    cols: [[
                        {field: 'maType', title: '机具名称', width: '15%'},
                        {field: 'maName', title: '规格型号', width: '15%'},
                        {field: 'maCode', title: '设备编码', width: '12%'},
                        {field: 'num', title: '领料量', width: '8%'},
                        {field: 'taskCode', title: '单号', width: '10%'},
                        {field: 'projectName', title: '领料工程', width: '12%'},
                        {field: 'companyName', title: '分公司', width: '12%'},
                        {field: 'userName', title: '出库人员', width: '8%'},
                        {field: 'currentTime', title: '领料时间', width: '8%'}
                    ]]
                },
                return: {
                    elem: '#returnTable',
                    url: '${bonuspath}/backstage/indexHomeDetails/getCalendarBack',
                    cols: [[
                        {field: 'maType', title: '机具名称', width: '15%'},
                        {field: 'maName', title: '规格型号', width: '15%'},
                        {field: 'maCode', title: '设备编码', width: '12%'},
                        {field: 'num', title: '退料量', width: '8%'},
                        {field: 'taskCode', title: '单号', width: '10%'},
                        {field: 'projectName', title: '退料工程', width: '12%'},
                        {field: 'companyName', title: '分公司', width: '12%'},
                        {field: 'userName', title: '接收检验人员', width: '8%'},
                        {field: 'currentTime', title: '退料时间', width: '8%'}
                    ]]
                },
                repair: {
                    elem: '#repairTable',
                    url: '${bonuspath}/backstage/indexHomeDetails/getCalendarRepair',
                    cols: [[
                        {field: 'maType', title: '机具名称', width: '15%'},
                        {field: 'maName', title: '规格型号', width: '15%'},
                        {field: 'maCode', title: '设备编码', width: '12%'},
                        {field: 'num', title: '维修量', width: '10%'},
                        {field: 'remark', title: '编号备注', width: '18%'},
                        {field: 'fileUrl', title: '维修附件', width: '12%'},
                        {field: 'userName', title: '维修人员', width: '8%'},
                        {field: 'currentTime', title: '维修时间', width: '10%'}
                    ]]
                },
                scrap: {
                    elem: '#scrapTable',
                    url: '${bonuspath}/backstage/indexHomeDetails/getCalendarScrap',
                    cols: [[
                        {field: 'maType', title: '机具名称', width: '15%'},
                        {field: 'maName', title: '规格型号', width: '15%'},
                        {field: 'maCode', title: '设备编码', width: '12%'},
                        {field: 'num', title: '报废量', width: '10%'},
                        {field: 'remark', title: '报废原因', width: '18%'},
                        {field: 'fileUrl', title: '报废资料', width: '12%'},
                        {field: 'userName', title: '维修人员', width: '8%'},
                        {field: 'currentTime', title: '维修时间', width: '10%'}
                    ]]
                },
                repairIn: {
                    elem: '#repairInTable',
                    url: '${bonuspath}/backstage/indexHomeDetails/getCalendarRepairInput',
                    cols: [[
                        {field: 'maType', title: '机具名称', width: '18%'},
                        {field: 'maName', title: '规格型号', width: '18%'},
                        {field: 'maCode', title: '设备编码', width: '15%'},
                        {field: 'num', title: '入库量', width: '12%'},
                        {field: 'taskCode', title: '单号', width: '15%'},
                        {field: 'userName', title: '入库人员', width: '10%'},
                        {field: 'currentTime', title: '入库时间', width: '12%'}
                    ]]
                },
                purchase: {
                    elem: '#purchaseTable',
                    url: '${bonuspath}/backstage/indexHomeDetails/getCalendarNewInput',
                    cols: [[
                        {field: 'maType', title: '机具名称', width: '18%'},
                        {field: 'maName', title: '规格', width: '18%'},
                        {field: 'num', title: '新购量', width: '12%'},
                        {field: 'price', title: '采购价格', width: '12%'},
                        {field: 'companyName', title: '厂家', width: '15%'},
                        {field: 'startTime', title: '采购时间', width: '12%'},
                        {field: 'endTime', title: '到货时间', width: '13%'}
                    ]]
                },
                inventory: {
                    elem: '#inventoryTable',
                    url: '${bonuspath}/backstage/indexHomeDetails/getCalendarPd',
                    cols: [[
                        {field: 'maType', title: '机具名称', width: '20%'},
                        {field: 'maName', title: '规格', width: '20%'},
                        {field: 'num', title: '盘点结果', width: '15%'},
                        {field: 'remark', title: '备注', width: '20%'},
                        {field: 'userName', title: '盘点人员', width: '12%'},
                        {field: 'currentTime', title: '盘点时间', width: '13%'}
                    ]]
                }
            };
            
            // 遍历初始化所有表格
            Object.keys(tables).forEach(function(key) {
                var config = tables[key];
                table.render({
                    elem: config.elem,
                    url: config.url,
                    method: 'post',
                    where: { currentTime: date,proId:proId },
                    height: 300,  // 固定高度
                    page: true,
                    limit: 10,
                    cols: config.cols,
                    parseData: function(res) {
                        return {
                            "code": 0,
                            "msg": res.resMsg,
                            "count": res.obj ? res.obj.length : 0,
                            "data": res.obj || []
                        };
                    }
                });
            });
        }
        
        // 获取URL参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURIComponent(r[2]);
            return null;
        }
    });
    
    // 导出数据
    function exportData() {
        var loadIndex = layer.msg('正在导出数据，请稍候...', {
            icon: 16,
            shade: 0.3,
            time: 0
        });
        
        try {
            var date = localStorage.getItem('calendarDate') || '';
            var allData = [];
            var tableIds = ['leaseOutTable', 'returnTable', 'repairTable', 'scrapTable', 
                           'repairInTable', 'purchaseTable', 'inventoryTable'];
            
            // 检查是否有数据可导出
            var hasData = tableIds.some(function(tableId) {
                return layui.table.cache[tableId] && layui.table.cache[tableId].length > 0;
            });
            
            if (!hasData) {
                layer.msg('暂无数据可导出', {icon: 2});
                layer.close(loadIndex);
                return;
            }
            
            setTimeout(function() {
                // 领料出库数据
                if (layui.table.cache.leaseOutTable && layui.table.cache.leaseOutTable.length > 0) {
                    layui.table.exportFile(
                        'leaseOutTable',
                        layui.table.cache.leaseOutTable,
                        {
                            type: 'xlsx',
                            title: '领料出库_' + date,
                            head: [['机具名称', '规格型号', '设备编码', '领料量', '单号', 
                                  '领料工程', '分公司', '出库人员', '领料时间']],
                            filter: function(item) {
                                return [
                                    item.maType,
                                    item.maName,
                                    item.maCode,
                                    item.num,
                                    item.taskCode,
                                    item.projectName,
                                    item.companyName,
                                    item.userName,
                                    item.currentTime
                                ];
                            }
                        }
                    );
                }
                
                // 退料接收数据
                if (layui.table.cache.returnTable && layui.table.cache.returnTable.length > 0) {
                    layui.table.exportFile(
                        'returnTable',
                        layui.table.cache.returnTable,
                        {
                            type: 'xlsx',
                            title: '退料接收_' + date,
                            head: [['机具名称', '规格型号', '设备编码', '退料量', '单号', 
                                  '退料工程', '分公司', '接收检验人员', '退料时间']],
                            filter: function(item) {
                                return [
                                    item.maType,
                                    item.maName,
                                    item.maCode,
                                    item.num,
                                    item.taskCode,
                                    item.projectName,
                                    item.companyName,
                                    item.userName,
                                    item.currentTime
                                ];
                            }
                        }
                    );
                }
                
                // 维修检验数据
                if (layui.table.cache.repairTable && layui.table.cache.repairTable.length > 0) {
                    layui.table.exportFile(
                        'repairTable',
                        layui.table.cache.repairTable,
                        {
                            type: 'xlsx',
                            title: '维修检验_' + date,
                            head: [['机具名称', '规格型号', '设备编码', '维修量', '编号备注', 
                                  '维修附件', '维修人员', '维修时间']],
                            filter: function(item) {
                                return [
                                    item.maType,
                                    item.maName,
                                    item.maCode,
                                    item.num,
                                    item.remark,
                                    item.fileUrl,
                                    item.userName,
                                    item.currentTime
                                ];
                            }
                        }
                    );
                }
                
                // 机具报废数据
                if (layui.table.cache.scrapTable && layui.table.cache.scrapTable.length > 0) {
                    layui.table.exportFile(
                        'scrapTable',
                        layui.table.cache.scrapTable,
                        {
                            type: 'xlsx',
                            title: '机具报废_' + date,
                            head: [['机具名称', '规格型号', '设备编码', '报废量', '报废原因', 
                                  '报废资料', '维修人员', '维修时间']],
                            filter: function(item) {
                                return [
                                    item.maType,
                                    item.maName,
                                    item.maCode,
                                    item.num,
                                    item.remark,
                                    item.fileUrl,
                                    item.userName,
                                    item.currentTime
                                ];
                            }
                        }
                    );
                }
                
                // 修试入库数据
                if (layui.table.cache.repairInTable && layui.table.cache.repairInTable.length > 0) {
                    layui.table.exportFile(
                        'repairInTable',
                        layui.table.cache.repairInTable,
                        {
                            type: 'xlsx',
                            title: '修试入库_' + date,
                            head: [['机具名称', '规格型号', '设备编码', '入库量', '单号', 
                                  '入库人员', '入库时间']],
                            filter: function(item) {
                                return [
                                    item.maType,
                                    item.maName,
                                    item.maCode,
                                    item.num,
                                    item.taskCode,
                                    item.userName,
                                    item.currentTime
                                ];
                            }
                        }
                    );
                }
                
                // 新购入库数据
                if (layui.table.cache.purchaseTable && layui.table.cache.purchaseTable.length > 0) {
                    layui.table.exportFile(
                        'purchaseTable',
                        layui.table.cache.purchaseTable,
                        {
                            type: 'xlsx',
                            title: '新购入库_' + date,
                            head: [['机具名称', '规格', '新购量', '采购价格', '厂家', 
                                  '采购时间', '到货时间']],
                            filter: function(item) {
                                return [
                                    item.maType,
                                    item.maName,
                                    item.num,
                                    item.price,
                                    item.companyName,
                                    item.startTime,
                                    item.endTime
                                ];
                            }
                        }
                    );
                }
                
                // 库存盘点数据
                if (layui.table.cache.inventoryTable && layui.table.cache.inventoryTable.length > 0) {
                    layui.table.exportFile(
                        'inventoryTable',
                        layui.table.cache.inventoryTable,
                        {
                            type: 'xlsx',
                            title: '库存盘点_' + date,
                            head: [['机具名称', '规格', '盘点结果', '备注', '盘点人员', '盘点时间']],
                            filter: function(item) {
                                return [
                                    item.maType,
                                    item.maName,
                                    item.num,
                                    item.remark,
                                    item.userName,
                                    item.currentTime
                                ];
                            }
                        }
                    );
                }
                
                layer.close(loadIndex);
                layer.msg('导出成功', {icon: 1});
            }, 100);
        } catch (e) {
            console.error('导出失败:', e);
            layer.msg('导出失败，请稍后重试', {icon: 2});
            layer.close(loadIndex);
        }
    }
    
    // 下载模板
    function downloadTemplate() {
        window.location.href = '${bonuspath}/backstage/indexHomeDetails/downloadTemplate';
    }
    </script>
</body>
</html> 