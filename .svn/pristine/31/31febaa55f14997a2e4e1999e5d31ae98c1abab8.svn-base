<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../baseset.jsp" %>
    <%@include file="../systemset.jsp" %>
    <meta charset="UTF-8">
    <title>机具类别明细</title>
    <!-- Layui CSS -->
    <link href="${bonuspath}/static/js/index/layui.css" rel="stylesheet">
    <script src="${bonuspath}/static/js/index/crypto-js.min.js"></script>
    <style>
        .equipment-types-container {
            padding: 15px;
        }
        .filter-section {
            margin-bottom: 15px;
            padding: 15px;
            background-color: #f8f8f8;
            border-radius: 4px;
        }
        .layui-table-view {
            overflow-x: auto !important;
        }
        /* 添加 Layui 字体图标路径 */
        @font-face {
            font-family: 'layui-icon';
            src: url('${bonuspath}/static/js/index/font/iconfont.eot');
            src: url('${bonuspath}/static/js/index/font/iconfont.eot?#iefix') format('embedded-opentype'),
                 url('${bonuspath}/static/js/index/font/iconfont.woff2') format('woff2'),
                 url('${bonuspath}/static/js/index/font/iconfont.woff') format('woff'),
                 url('${bonuspath}/static/js/index/font/iconfont.ttf') format('truetype'),
                 url('${bonuspath}/static/js/index/font/iconfont.svg#layui-icon') format('svg');
        }
    </style>
</head>
<body>
    <div class="equipment-types-container">
        <!-- 筛选区域 -->
        <div class="filter-section">
            <form class="layui-form" lay-filter="equipmentTypesFilter">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <select name="isCount" lay-filter="isCount">
                                <option value="">机具类型</option>
                                <option value="0">设备</option>
                                <option value="1">机具</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <select name="quantityFilter" lay-filter="quantityFilter">
                                <option value="">数量筛选</option>
                                <option value="1">只看保有量>0</option>
                                <option value="2">只看库存量>0</option>
                                <option value="3">只看在用量>0</option>
                                <option value="4">只看报废量>0</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="maName" class="layui-input" placeholder="请输入机具名称">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="maType" class="layui-input" placeholder="请输入规格型号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button type="button" class="layui-btn" lay-submit lay-filter="equipmentTypesSearch">搜索</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        <button type="button" class="layui-btn layui-btn-primary" lay-submit lay-filter="equipmentTypesExport">导出</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- 表格区域 -->
        <table id="equipmentTypesTable" lay-filter="equipmentTypesTable"></table>
    </div>

    <script src="${bonuspath}/static/js/index/layui.min.js"></script>
    
    <!-- 操作列模板 -->
    <script type="text/html" id="operationBar">
        <a class="layui-btn layui-btn-xs" lay-event="detail">明细</a>
    </script>
    <script>
    layui.use(['table', 'form'], function(){
        var table = layui.table;
        var form = layui.form;
        
        // 渲染表单
        form.render('select');
        
        // 监听数量筛选下拉框
        // form.on('select(quantityFilter)', function(data){
        //     table.reload('equipmentTypesTable', {
        //         where: {
        //             quantityFilter: data.value
        //         }
        //     });
        // });
        
        // 监听机具类型下拉框
        // form.on('select(deviceType)', function(data){
        //     table.reload('equipmentTypesTable', {
        //         where: {
        //             deviceType: data.value
        //         }
        //     });
        // });
        
        // 初始化表格
        table.render({
            elem: '#equipmentTypesTable',
            url: '${bonuspath}/backstage/indexHomeDetails/getMaTypeDetails',
            method: 'post',  // 设置请求方法为 POST
            page: false,
            width: 1460,  // 设置表格总宽度
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            dataType: 'text', // 将响应类型设置为text而不是json
            parseData: function(res) {
                console.log('开始解析数据');
                try {
                    console.log('收到的原始响应数据:', res);
                    
                    if (!res) {
                        throw new Error('响应数据为空');
                    }
                    
                    try {
                        return {
                            "code": 0,
                            "msg": res.resMsg || '',
                            "count": res.obj ? res.obj.length : 0,
                            "data": res.obj || []
                        };
                    } catch (decryptError) {
                        console.error('转换失败:', decryptError);
                        throw new Error('转换失败');
                    }
                    
                } catch (e) {
                    console.error('数据处理失败:', e);
                    console.error('原始数据:', res);
                    layer.msg('数据处理失败，请检查网络连接或联系管理员', {icon: 2});
                    return {
                        "code": -1,
                        "msg": "数据处理失败: " + e.message,
                        "count": 0,
                        "data": []
                    };
                }
            },
            request: {
                pageName: 'page',
                limitName: 'limit'
            },
            response: {
                statusName: 'code',
                statusCode: 0,
                msgName: 'msg',
                countName: 'count',
                dataName: 'data'
            },
            cols: [[
                {type: 'numbers', title: '序号', width: 50},
                {field: 'isCount', title: '机具类别', width: 90, templet: function(d) {
                    return d.isCount === '0' ? '设备' : '机具';
                }},
                {field: 'maType', title: '机具名称', width: 100},
                {field: 'maName', title: '规格型号', width: 160},
                {field: 'maUnit', title: '单位', width: 90},
                {field: 'maTotal', title: '保有量', width: 90},
                {field: 'storageNum', title: '库存量', width: 80},
                {field: 'inuseNum', title: '在用量', width: 80},
                {field: 'repairNum', title: '在修在检量', width: 90},
                {field: 'scrapNum', title: '报废量', width: 80},
                {field: 'projectNum', title: '在用工程数量', width: 120},
                {field: 'pdContent', title: '盘点记录', width: 150, templet: function(d) {
                    return '盘盈:' + d.pyNum + '盘亏:' + d.pkNum;
                }},
                {field: 'inuseCount', title: '累计领用量', width: 110},
                {field: 'backCount', title: '累计退还量', width: 110},
                {title: '操作', width: 60, fixed: 'right', toolbar: '#operationBar'}
            ]],
            done: function(res, curr, count) {
                console.log('表格渲染完成，结果:', res);
                if (res.code === -1) {
                    layer.msg(res.msg);
                }
            }
        });

        // 监听工具条事件
        table.on('tool(equipmentTypesTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                localStorage.setItem('equipmentViewTypeId', data.typeId);
                // 打开明细弹窗
                layer.open({
                    zIndex: 20001014,
                    type: 2,
                    title: '机具类别详细信息',
                    area: ['80%', '95%'],
                    content: '${bonuspath}/backstage/indexHome/dataOverview/equipment/detail',
                    success: function(layero, index){
                        // 可以在这里对弹出的iframe进行一些操作
                    }
                });
            }
        });

        // 监听搜索表单提交
        form.on('submit(equipmentTypesSearch)', function(data){
            console.log(data.field);
            table.reload('equipmentTypesTable', {
                where: data.field
            });
            return false;
        });

     
        // 导出--事件监听
        form.on('submit(equipmentTypesExport)', function(data){
            var loadIndex = layer.msg('正在导出数据，请稍候...', {
                icon: 16,
                shade: 0.3,
                time: 0
            });
            try {
                if (!table.cache.equipmentTypesTable || table.cache.equipmentTypesTable.length === 0) {
                    layer.msg('暂无数据可导出', {icon: 2});
                    return false;
                }
                
                setTimeout(function() {
                    table.exportFile(
                        'equipmentTypesTable',
                        table.cache.equipmentTypesTable,
                        {
                            type: 'xlsx',
                            title: '机具类别明细_' + new Date().toLocaleDateString().replace(/\//g, '-'),
                            head: [
                                ['机具类别', '机具名称', '规格型号', '单位', '保有量', '库存量', '在用量', 
                                 '在修在检量', '报废量', '在用工程数量', '盘点记录', '累计领用量', '累计退还量']
                            ],
                            filter: function(item, index) {
                                return [
                                    item.isCount === '0' ? '设备' : '机具',
                                    item.maType,
                                    item.maName,
                                    item.maUnit,
                                    item.maTotal,
                                    item.storageNum,
                                    item.inuseNum,
                                    item.repairNum,
                                    item.scrapNum,
                                    item.projectNum,
                                    '盘盈:' + item.pyNum + ' 盘亏:' + item.pkNum,
                                    item.inuseCount,
                                    item.backCount
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
            return false;
        });
    });
    </script>
</body>
</html> 