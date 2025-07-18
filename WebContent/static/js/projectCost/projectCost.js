/**
 * 项目结算模块
 */
layui.define(['table', 'form', 'layer'], function(exports){
    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        layer = layui.layer;
    
    var baseUrl = window.bonuspath || './';
    
    // 设置Layui全局配置，解决乱码问题
    layui.config({
        lang: 'zh-CN'
    });
    
    // 添加CSS修复乱码问题
    (function fixLayuiCss() {
        // 基本 CSS 样式
        var style = document.createElement('style');
        style.type = 'text/css';
        var css = '.layui-laypage-skip em{font-style:normal !important;} ' +
                 '.layui-laydate-content td, .layui-laydate-content th{font-family:"Microsoft YaHei", sans-serif !important;} ' +
                 '.layui-laydate-footer span{font-family:"Microsoft YaHei", sans-serif !important;}';
        
        if(style.styleSheet) {
            style.styleSheet.cssText = css;
        } else {
            style.appendChild(document.createTextNode(css));
        }
        
        document.head.appendChild(style);
        
        // 添加直接修复日期选择器显示的函数
        window.fixDatepicker = function() {
            setTimeout(function() {
                try {
                    // 修复星期标题
                    $('.layui-laydate-content th').eq(0).text('日');
                    $('.layui-laydate-content th').eq(1).text('一');
                    $('.layui-laydate-content th').eq(2).text('二');
                    $('.layui-laydate-content th').eq(3).text('三');
                    $('.layui-laydate-content th').eq(4).text('四');
                    $('.layui-laydate-content th').eq(5).text('五');
                    $('.layui-laydate-content th').eq(6).text('六');
                    
                    // 修复按钮提示文字
                    $('.laydate-prev-m').attr('title', '上个月');
                    $('.laydate-next-m').attr('title', '下个月');
                    $('.laydate-prev-y').attr('title', '上一年');
                    $('.laydate-next-y').attr('title', '下一年');
                    
                    // 修复底部按钮文字
                    $('.laydate-btns-now').text('今天');
                    $('.laydate-btns-clear').text('清空');
                    $('.laydate-btns-confirm').text('确定');
                    
                    // 修复年月选择器中的文字
                    $('.laydate-set-ym span').each(function() {
                        var text = $(this).text();
                        text = text.replace('骞', '年').replace('鏈', '月');
                        $(this).text(text);
                    });
                    
                    console.log('日期选择器文字已修复');
                } catch(e) {
                    console.warn('修复日期选择器文字时出错:', e);
                }
            }, 50);
        };
        
        // 在日期选择器打开时自动修复
        $(document).on('click', 'input[lay-key]', function() {
            window.fixDatepicker();
        });
        
        console.log('添加CSS样式修复和日期选择器监听');
    })();
    
    // 初始化Layui组件，确保使用中文
    (function initLayuiLanguage() {
        try {
            // 修复可能的编码问题
            if(layui.device('ie')) {
                try {
                    // 解决IE浏览器下的乱码问题
                    document.charset = 'UTF-8';
                } catch(e) {
                    console.warn('无法设置文档编码:', e);
                }
            }
            
            // 修复分页组件的乱码问题
            if(layui.laypage && layui.laypage.config) {
                try {
                    // 尝试直接修改laypage的默认文本
                    layui.laypage.config({
                        lang: {
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
                    });
                } catch(e) {
                    console.warn('无法设置laypage配置:', e);
                }
            }
            
            // 不再尝试全局配置laydate，改为在每次创建时设置
            
            console.log('Layui组件语言初始化完成');
        } catch(e) {
            console.error('初始化Layui组件时出错:', e);
        }
    })();
    
    // 创建日期选择器配置函数，确保所有日期选择器使用中文
    function createDatePicker(elem, options) {
        // 检查页面编码，若非 UTF-8 则尝试设置
        var metaCharset = document.querySelector('meta[charset]');
        if(metaCharset && metaCharset.getAttribute('charset').toLowerCase() !== 'utf-8') {
            try {
                metaCharset.setAttribute('charset', 'UTF-8');
                console.log("页面编码已设置为 UTF-8");
            } catch(e) {
                console.warn("无法设置页面编码:", e);
            }
        }
        
        var defaultOptions = {
            elem: elem,
            lang: 'cn', // 设置中文
            format: 'yyyy-MM-dd', // 设置日期格式
            theme: 'molv', // 使用墨绿主题，更美观
            // 添加事件回调，在每次打开日期选择器时修复文字
            ready: function() {
                if(window.fixDatepicker) {
                    window.fixDatepicker();
                }
            },
            change: function() {
                if(window.fixDatepicker) {
                    window.fixDatepicker();
                }
            }
        };
        
        // 合并配置
        var finalOptions = $.extend({}, defaultOptions, options || {});
        
        try {
            // 渲染日期选择器
            var dateInstance = layui.laydate.render(finalOptions);
            
            // 在渲染后尝试修复日期文本编码问题
            setTimeout(function() {
                if(window.fixDatepicker) {
                    window.fixDatepicker();
                }
            }, 100); // 延迟100ms执行，确保元素已渲染
            
            return dateInstance;
        } catch (e) {
            console.warn('创建日期选择器失败，尝试简化配置:', e);
            
            // 如果失败，尝试使用最基本的配置
            return layui.laydate.render({
                elem: elem
            });
        }
    }
    
    var projectCost = {
        // 初始化列表页
        init: function() {
            // 初始化日期选择器
            createDatePicker('#startDate');
            createDatePicker('#endDate');
            
            // 初始化表格 - 改为手动加载数据
            var tableIns = table.render({
                elem: '#LAY-project-cost-list',
                // 不使用url自动请求
                cols: [[
                    {field: 'id', title: 'ID', hide: true, width: 80},
                    {field: 'projectName', title: '工程名称', minWidth: 400},
                    {field: 'startTime', title: '开始日期', width: 120},
                    {field: 'endTime', title: '结束日期', width: 120},
                    {field: 'totalAmount', title: '总金额(元)', width: 120, templet: function(d) {
                        return d.totalAmount ? d.totalAmount.toFixed(2) : '0.00';
                    }},
                    {field: 'createTime', title: '创建时间', minWidth: 160},
                    {field: 'createUser', title: '创建人', width: 120},
                    {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-project-cost-list'}
                ]],
                data: [], // 初始为空数组
                page: false,
                limit: 1000,
                height: 'full-220',
                even: true, // 开启隔行背景
                size: 'lg', // 大尺寸
                autoSort: false, // 关闭自动排序
                cellMinWidth: 80, // 最小单元格宽度
                text: {
                    none: '暂无数据'
                },
                // 添加语言配置，修复乱码问题
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
            
            // 手动加载数据函数
            function loadTableData(params) {
                // 显示加载指示器
                layer.load(2);
                
                // 默认参数
                var defaultParams = {
                    page: 1,
                    limit: 1000
                };
                
                // 合并参数
                params = $.extend({}, defaultParams, params || {});
                
                $.ajax({
                    url: baseUrl + '/backstage/projectCost/queryCalculationList',
                    type: 'post',
                    dataType: 'json', 
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(params),
                    success: function(res) {
                        // 关闭加载指示器
                        layer.closeAll('loading');
                        
                        console.log("原始响应:", res);
                        
                        // 处理可能是字符串的响应
                        if (typeof res === 'string') {
                            try {
                                res = JSON.parse(res);
                                console.log("解析字符串响应为对象:", res);
                            } catch(e) {
                                console.error("无法解析JSON:", e);
                                
                                // 尝试处理编码问题
                                try {
                                    var decodedString = decodeURIComponent(escape(res));
                                    res = JSON.parse(decodedString);
                                    console.log("编码转换后成功解析:", res);
                                } catch(e2) {
                                    console.error("编码转换尝试失败:", e2);
                                    layer.msg('获取数据失败，请检查网络连接');
                                    return;
                                }
                            }
                        }
                        
                        // 更新表格数据
                        if (res && res.res === 1 && res.obj) {
                            // 渲染表格
                            tableIns.reload({
                                data: res.obj.data || []
                                // 移除分页配置，因为我们已取消分页功能
                            });
                            console.log("表格数据已更新, 总数:", res.obj.count);
                        } else {
                            // 显示错误消息
                            var errorMsg = (res && res.resMsg) ? res.resMsg : '数据接口请求异常';
                            
                            // 尝试修复可能的编码问题
                            try {
                                errorMsg = decodeURIComponent(escape(errorMsg));
                            } catch(e) {
                                console.error("修复错误消息编码失败:", e);
                            }
                            
                            layer.msg(errorMsg);
                            console.error("请求失败:", res);
                            
                            // 清空表格
                            tableIns.reload({
                                data: []
                                // 移除分页配置，因为我们已取消分页功能
                            });
                        }
                    },
                    error: function(xhr, status, error) {
                        // 关闭加载指示器
                        layer.closeAll('loading');
                        
                        // 显示错误信息
                        layer.msg('网络请求失败');
                        console.error("请求失败:", status, error);
                        console.log("XHR对象:", xhr);
                        
                        // 清空表格
                        tableIns.reload({
                            data: []
                            // 移除分页配置，因为我们已取消分页功能
                        });
                    }
                });
            }
            
            // 初始加载数据
            loadTableData();
            
            // 监听工具条事件
            table.on('tool(LAY-project-cost-list)', function(obj){
                var data = obj.data;
                if(obj.event === 'detail'){
                    // 打开详情页
                    layer.open({
                        type: 2,
                        title: '计算结果详情',
                        content: baseUrl + '/backstage/projectCost/calculation_detail?id=' + data.id,
                        area: ['90%', '90%'],
                        maxmin: true
                    });
                } else if(obj.event === 'delete'){
                    // 删除操作
                    layer.confirm('确定删除该计算结果吗？', function(index){
                        $.ajax({
                            url: baseUrl + '/backstage/projectCost/deleteCalculation',
                            type: 'post',
                            data: {id: data.id},
                            success: function(res){
                                if (typeof res === 'string') {
                                    try {
                                        res = JSON.parse(res);
                                    } catch(e) {
                                        console.error("无法解析JSON:", e);
                                        layer.msg('删除失败，请检查网络连接');
                                        return;
                                    }
                                }
                                if(res.res === 1){
                                    layer.msg('删除成功');
                                    // 删除当前行并重新加载数据，确保表格状态正确
                                    loadTableData();
                                } else {
                                    layer.msg('删除失败');
                                }
                            },
                            error: function(){
                                layer.msg('网络错误，删除失败');
                            }
                        });
                        layer.close(index);
                    });
                }
            });
            
            // 监听搜索
            form.on('submit(LAY-project-cost-search)', function(data){
                var field = data.field;
                
                // 处理中文编码问题不再需要，因为我们使用JSON提交
                console.log("搜索参数:", field);
                
                // 使用自定义加载函数
                loadTableData({
                    projectName: field.projectName,
                    startDate: field.startDate,
                    endDate: field.endDate
                });
                
                return false;
            });
            
            // 注释掉这个事件绑定，因为在list.jsp中已经有同样的代码
            // 添加按钮点击事件
            /*
            $('.layui-btn[data-type="add"]').on('click', function(){
                layer.open({
                    type: 2,
                    title: '新增结算计算',
                    content: baseUrl + '/backstage/projectCost/calculation_form',
                    area: ['90%', '90%'],
                    maxmin: true
                });
            });
            */
        },
        
        // 加载项目下拉框数据
        loadProjects: function() {
            console.log('开始加载工程列表...');
            $.ajax({
                url: baseUrl + '/backstage/projectCost/getProjects',
                type: 'get',
                dataType: 'json',
                success: function(res) {
                    //console.log('工程列表API响应:', res);
                    
                    // 检查响应是否存在且不为空
                    if(!res) {
                        console.error('响应为空');
                        layer.msg('加载工程列表失败: 响应为空');
                        return;
                    }
                    
                    try {
                        var projects = [];
                        
                        // 检查不同可能的数据结构
                        // 注意：AjaxRes使用res=1表示成功，而不是success=true
                        if(res.res === 1 || res.success === true) {
                            console.log('响应成功标志正确 - res:' + res.res);
                            if(Array.isArray(res.data)) {
                                projects = res.data;
                                console.log('从res.data获取数据，数量:', projects.length);
                            } else if(res.obj && Array.isArray(res.obj)) {
                                projects = res.obj;
                                console.log('从res.obj获取数据，数量:', projects.length);
                            } else if(res.obj && res.obj.list && Array.isArray(res.obj.list)) {
                                projects = res.obj.list;
                                console.log('从res.obj.list获取数据，数量:', projects.length);
                            } else {
                                console.warn('响应中未找到有效数组数据');
                            }
                        } else {
                            // 请求不成功，但尝试从响应中提取数据
                            if(Array.isArray(res)) {
                                projects = res;
                                console.log('响应本身是数组，数量:', projects.length);
                            } else {
                                console.error('请求不成功且未找到数据:', res.resMsg || res.msg || '未知错误');
                                layer.msg('加载工程列表失败: ' + (res.resMsg || res.msg || '服务器返回未知错误'));
                            }
                        }
                        
                        if(projects.length === 0) {
                            console.warn('未获取到工程数据或数据为空');
                        }
                        
                        // 生成HTML，即使没有数据也会创建下拉框
                        var html = '<option value="">请选择工程</option>';
                        for(var i = 0; i < projects.length; i++) {
                            // 兼容不同返回结构
                            var id = projects[i].id || projects[i].ID || '';
                            var name = projects[i].name || projects[i].NAME || projects[i].projectName || '';
                            console.log('工程数据:', i, id, name);
                            html += '<option value="' + id + '">' + name + '</option>';
                        }
                        console.log('生成的HTML:', html);
                        
                        // 更新下拉框并重新渲染
                        $('select[name="projectId"]').html(html);
                        if(typeof form !== 'undefined' && form.render) {
                            form.render('select'); // 重新渲染select
                            console.log('下拉框渲染完成');
                        } else {
                            console.error('form对象不存在或不包含render方法');
                        }
                        
                        // 如果没有数据，显示提示
                        if(projects.length === 0) {
                            layer.msg('未获取到工程数据，请检查系统配置');
                        }
                    } catch(error) {
                        console.error('处理工程数据时出错:', error);
                        layer.msg('加载工程列表失败: 数据处理错误');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('请求工程列表失败:', status, error);
                    console.log('XHR对象:', xhr);
                    layer.msg('网络错误，无法加载工程列表');
                }
            });
        },
        
        // 生成结算数据
        generateSettlement: function(data) {
            if(!data.projectId) {
                layer.msg('请选择工程');
                return;
            }
            if(!data.startTime || !data.endTime) {
                layer.msg('请选择统计时间范围');
                return;
            }
            
            $('#data-loading').show();
            $('#data-container').hide();
            
            // 清除旧的表格容器内容，防止多次渲染
            if($('#calculationResultSection').length > 0) {
                $('#calculationResultSection').remove();
            }
            
            $.ajax({
                url: baseUrl + '/backstage/projectCost/calculateSettlement',
                type: 'post',
                data: data,
                success: function(res) {
                    $('#data-loading').hide();
                    
                    // 打印响应以进行问题排查
                    console.log("API响应:", res);
                    console.log("响应类型:", typeof res);
                    
                    // 处理可能是字符串的响应
                    if (typeof res === 'string') {
                        try {
                            res = JSON.parse(res);
                            console.log("已将字符串响应解析为对象:", res);
                        } catch(e) {
                            console.error("响应无法解析为JSON:", e);
                        }
                    }
                    
                    // 检查响应是否成功，兼容不同的返回格式
                    var isSuccess = false;
                    
                    if (res) {
                        if(res.success === true) {
                            isSuccess = true;
                            console.log("通过res.success判断成功");
                        } else if(res.res === 1) {
                            isSuccess = true;
                            console.log("通过res.res判断成功");
                        } else if(res.code === 200 || res.code === 0 || res.code === '0') {
                            isSuccess = true;
                            console.log("通过res.code判断成功");
                        } else if(res.status === 'success' || res.status === 200 || res.status === 0) {
                            isSuccess = true;
                            console.log("通过res.status判断成功");
                        }
                    }
                    
                    console.log("判断是否成功: ", isSuccess, "响应结构:", JSON.stringify(res).substring(0, 100) + "...");
                    
                    if(isSuccess) {
                        // 清空并重建容器结构
                        $('#data-container').empty().show();
                        
                        // 获取正确的数据，兼容不同的数据结构
                        let responseData = {};

                        if(res.data) {
                            responseData = res.data;
                            console.log("从res.data获取数据");
                        } else if(res.obj) {
                            responseData = res.obj;
                            console.log("从res.obj获取数据");
                        } else {
                            // 尝试直接使用响应本身
                            responseData = res;
                            console.log("直接使用整个响应作为数据");
                        }
                        
                        console.log("提取的响应数据:", responseData);
                        
                        // 从详情数据中提取领料和退料明细
                        let leaseData = [];
                        let returnData = [];

                        // 检查返回的数据结构
                        if(responseData.leaseMaterials) {
                            // 使用原来的数据结构
                            leaseData = responseData.leaseMaterials || [];
                            returnData = responseData.returnMaterials || [];
                        } else if(responseData.details) {
                            // 使用后端返回的新数据结构
                            var details = responseData.details || [];
                            console.log("发现details数组，长度:", details.length);
                            
                            // 尝试预览第一条数据
                            if(details.length > 0) {
                                console.log("第一条数据样例:", details[0]);
                                
                                // 打印所有字段名，便于排查
                                console.log("数据字段列表:", Object.keys(details[0]).join(", "));
                            }
                            
                            // 按类型分离明细（假设operateType=1为领料，operateType=2为退料）
                            leaseData = details.filter(function(item) {
                                return item.operateType === 1 || item.operateType === '1';
                            });
                            
                            returnData = details.filter(function(item) {
                                return item.operateType === 2 || item.operateType === '2';
                            });
                            
                            if(leaseData.length === 0 && returnData.length === 0 && details.length > 0) {
                                // 如果没有按类型分离出数据，可能是后端没有设置operateType字段，则尝试直接使用details
                                console.log("未能根据operateType分离数据，显示所有明细作为领料");
                                leaseData = details;
                            }
                        } else {
                            // 尝试寻找其他可能的数据结构
                            console.warn("未找到预期的数据结构，尝试深度查找数据");
                            
                            // 处理res对象中可能的嵌套数据
                            var foundData = false;
                            
                            // 检查是否有任何直接可用的数组
                            for(var key in responseData) {
                                if(Array.isArray(responseData[key])) {
                                    console.log("发现数组数据在字段:", key, "长度:", responseData[key].length);
                                    if(responseData[key].length > 0) {
                                        leaseData = responseData[key];
                                        foundData = true;
                                        break;
                                    }
                                }
                            }
                            
                            // 如果是res.res===1格式的API，特殊处理
                            if(!foundData && res.res === 1) {
                                console.log("检测到res.res===1格式的API，尝试从res.obj中提取数据");
                                
                                // 如果responseData已经是res.obj，查找里面的数组
                                if(res.obj === responseData) {
                                    // 已经在上面处理过
                                } 
                                // 尝试直接将整个res.obj作为数据源（如果是数组）
                                else if(Array.isArray(res.obj)) {
                                    console.log("res.obj是数组，直接用作数据源");
                                    leaseData = res.obj;
                                    foundData = true;
                                }
                            }
                            
                            // 最后尝试使用整个响应作为数据源
                            if(!foundData) {
                                if(Array.isArray(responseData)) {
                                    console.log("responseData本身是数组，直接用作数据源");
                                    leaseData = responseData;
                                } else if(Array.isArray(res)) {
                                    console.log("整个res是数组，直接用作数据源");
                                    leaseData = res;
                                } else {
                                    console.warn("未找到可用的数组数据");
                                }
                            }
                        }
                        
                        console.log("处理后的领料数据:", leaseData);
                        console.log("处理后的退料数据:", returnData);
                        
                        // 保存当前的领料和退料数据，以便在明细视图中使用
                        window.currentLeaseData = leaseData;
                        window.currentReturnData = returnData;
                        
                        // 获取计算结果数据
                        let calculationResults = responseData.calculationResults || [];
                        let totalAmount = responseData.totalAmount || 0;
                        
                        console.log("计算结果数据:", calculationResults);
                        console.log("总金额:", totalAmount);
                        
                        // 保存到全局变量，供保存方法使用
                        window.calculationResults = calculationResults;
                        window.totalAmount = totalAmount;
                        
                        // 创建新的容器结构，使用更明确的方式分隔表格
                        $('#data-container').html(
                            '<div class="layui-row">' +
                            '<div class="layui-col-md12">' +
                            '<div style="text-align: right; padding: 10px; font-weight: bold; color: #FF5722; font-size: 16px;">' +
                            '总金额: <span id="totalAmountDisplay">' + totalAmount.toFixed(2) + '</span> 元' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            
                            // 租赁费用计算及领退差缺表格
                            '<div id="resultTableContainer" style="margin-bottom: 40px; border: 1px solid #e6e6e6; padding: 15px; background-color: #fff; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">' +
                            '<h3 style="font-size: 16px; font-weight: bold; margin-bottom: 15px;">费用计算及领退差缺表</h3>' +
                            '<table class="layui-table" id="calculationTable" lay-filter="calculationTable"></table>' +
                            '</div>' +
                            
                            // 物资领料明细表格
                            '<div id="leaseTableContainer" style="margin-bottom: 40px; border: 1px solid #e6e6e6; padding: 15px; background-color: #fff; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">' +
                            '<h3 style="font-size: 16px; font-weight: bold; margin-bottom: 15px;">物资领料明细</h3>' +
                            '<table class="layui-table" id="leaseTable" lay-filter="leaseTable"></table>' +
                            '</div>' +
                            
                            // 物资退料明细表格
                            '<div id="returnTableContainer" style="margin-bottom: 40px; border: 1px solid #e6e6e6; padding: 15px; background-color: #fff; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">' +
                            '<h3 style="font-size: 16px; font-weight: bold; margin-bottom: 15px;">物资退料明细</h3>' +
                            '<table class="layui-table" id="returnTable" lay-filter="returnTable"></table>' +
                            '</div>'
                        );
                        
                        // 计算合适的表格高度 - 根据数据量调整
                        var calcTableHeight = function(dataLength) {
                            // 根据数据行数决定表格高度
                            if (dataLength <= 3) return 220;  // 少量数据用更小高度
                            if (dataLength <= 6) return 240;  // 中等数据用中等高度
                            return 300;  // 大量数据用更大高度
                        };
                        
                        // 滚动事件处理 - 确保表格可见
                        $(window).on('scroll', function() {
                            var windowHeight = $(window).height();
                            var scrollTop = $(window).scrollTop();
                            
                            // 检查每个表格容器是否在视图中
                            $('#resultTableContainer, #leaseTableContainer, #returnTableContainer').each(function() {
                                var $container = $(this);
                                var containerTop = $container.offset().top;
                                var containerHeight = $container.height();
                                
                                // 如果容器底部超出视图，添加一个可视指示器
                                if (containerTop + containerHeight > scrollTop + windowHeight) {
                                    if ($container.find('.scroll-indicator').length === 0) {
                                        $container.append('<div class="scroll-indicator" style="text-align: center; padding: 5px; color: #999;">↓ 向下滚动查看更多 ↓</div>');
                                    }
                                } else {
                                    $container.find('.scroll-indicator').remove();
                                }
                            });
                        });
                        
                        // 渲染计算结果表格
                        if(calculationResults.length > 0) {
                            var calcHeight = calcTableHeight(calculationResults.length);
                            
                            table.render({
                                elem: '#calculationTable',
                                data: calculationResults,
                                cols: [[
                                    {field: 'machineTypeId', title: '物资ID', width: 100, hide: true},
                                    {field: 'machineTypeName', title: '物资名称', width: 160},
                                    {field: 'machineModel', title: '规格型号', width: 120},
                                    {field: 'machineUnit', title: '单位', width: 80},
                                    {field: 'currentCount', title: '未退还数量', width: 120, templet: function(d){
                                        return d.currentCount || 0;
                                    }},
                                    {field: 'price', title: '单价(元/天)', width: 120, templet: function(d){
                                        return d.price ? d.price.toFixed(2) : '0.00';
                                    }},
                                    {field: 'amount', title: '金额(元)', width: 120, style: 'color: #FF5722; font-weight: bold;', templet: function(d){
                                        return d.amount ? d.amount.toFixed(2) : '0.00';
                                    }},
                                    {title: '操作', width: 100, templet: function(d){
                                        return '<a class="layui-btn layui-btn-xs" lay-event="viewDetail">查看明细</a>';
                                    }}
                                ]],
                                page: false,
                                height: calcHeight,  // 使用计算的高度
                                limit: 1000,
                                // 添加语言配置，修复乱码问题
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
                            
                            // 监听计算结果表格工具条事件
                            table.on('tool(calculationTable)', function(obj){
                                let i;
                                const data = obj.data;
                                if(obj.event === 'viewDetail'){
                                    // 显示物资使用明细
                                    let detailHtml = '<div style="padding: 15px;">';

                                    // 添加时间段计算明细表格
                                    detailHtml += '<div class="layui-tab layui-tab-brief" lay-filter="detailTab">';
                                    detailHtml += '<ul class="layui-tab-title">';
                                    detailHtml += '<li class="layui-this">使用时间段</li>';
                                    detailHtml += '<li>物资领退记录</li>';
                                    detailHtml += '</ul>';
                                    detailHtml += '<div class="layui-tab-content">';
                                    
                                    // 时段计算内容
                                    detailHtml += '<div class="layui-tab-item layui-show">';
                                    detailHtml += '<table class="layui-table">';
                                    detailHtml += '<thead><tr><th>开始时间</th><th>结束时间</th><th>使用天数</th><th>使用数量</th><th>金额(元)</th></tr></thead>';
                                    detailHtml += '<tbody>';

                                    const segments = data.segments || [];

                                    // 打印调试信息
                                    console.log("时段计算数据:", segments);
                                    
                                    // 检查是否有数据
                                    if (segments.length === 0) {
                                        // 如果没有时段数据，尝试从计算结果构建一个简单的时段
                                        detailHtml += '<tr><td colspan="5" style="text-align: center;">没有详细的时段计算数据，显示汇总信息</td></tr>';
                                        
                                        // 添加一个汇总行
                                        var amount = data.amount || 0;
                                        var count = data.currentCount || 0;
                                        detailHtml += '<tr style="font-weight: bold;">';
                                        detailHtml += '<td>--</td>';
                                        detailHtml += '<td>--</td>';
                                        detailHtml += '<td>--</td>';
                                        detailHtml += '<td>' + count + '</td>';
                                        detailHtml += '<td>' + (amount ? amount.toFixed(2) : '0.00') + '</td>';
                                        detailHtml += '</tr>';
                                    } else {
                                        var totalDays = 0;
                                        var totalAmount = 0;
                                        
                                        for(i = 0; i < segments.length; i++){
                                            var segment = segments[i];
                                            var startTime = formatDateTime(segment.startTime);
                                            var endTime = formatDateTime(segment.endTime);
                                            var days = segment.days || 0;
                                            var count = segment.count || 0;
                                            var amount = segment.amount || 0;
                                            
                                            totalDays += days;
                                            totalAmount += amount;
                                            
                                            detailHtml += '<tr>';
                                            detailHtml += '<td>' + startTime + '</td>';
                                            detailHtml += '<td>' + endTime + '</td>';
                                            detailHtml += '<td>' + days + '</td>';
                                            detailHtml += '<td>' + count + '</td>';
                                            detailHtml += '<td>' + amount.toFixed(2) + '</td>';
                                            detailHtml += '</tr>';
                                        }
                                        
                                        // 添加汇总行
                                        detailHtml += '<tr style="font-weight: bold; background-color: #f2f2f2;">';
                                        detailHtml += '<td colspan="2">合计</td>';
                                        detailHtml += '<td>' + totalDays + '</td>';
                                        detailHtml += '<td>-</td>';
                                        detailHtml += '<td>' + totalAmount.toFixed(2) + '</td>';
                                        detailHtml += '</tr>';
                                    }
                                    
                                    detailHtml += '</tbody></table>';
                                    detailHtml += '</div>';
                                    
                                    // 操作记录内容
                                    detailHtml += '<div class="layui-tab-item">';
                                    
                                    // 添加颜色图例说明
                                    detailHtml += '<div style="margin-bottom: 10px; text-align: right;">';
                                    detailHtml += '<span style="display: inline-block; padding: 2px 8px; margin-right: 10px; background-color: #e8f5e9; color: #388e3c; border-radius: 2px;">领料</span>';
                                    detailHtml += '<span style="display: inline-block; padding: 2px 8px; background-color: #ffebee; color: #d32f2f; border-radius: 2px;">退料</span>';
                                    detailHtml += '</div>';
                                    
                                    detailHtml += '<table class="layui-table">';
                                    detailHtml += '<thead><tr style="background-color: #f2f2f2; font-weight: bold;"><th>操作时间</th><th>操作类型</th><th>数量</th><th>现场剩余数量</th></tr></thead>';
                                    detailHtml += '<tbody>';
                                    
                                    var details = data.details || [];
                                    var timeline = data.timeline || {};
                                    
                                    // 打印调试信息
                                    console.log("明细详情数据:", details);
                                    console.log("时间线数据:", timeline);
                                    
                                    // 按操作时间排序
                                    details.sort(function(a, b){
                                        var timeA = a.operateTime ? new Date(a.operateTime) : new Date(0);
                                        var timeB = b.operateTime ? new Date(b.operateTime) : new Date(0);
                                        return timeA - timeB;
                                    });
                                    
                                    // 检查是否有数据
                                    if (details.length === 0) {
                                        detailHtml += '<tr><td colspan="4" style="text-align: center;">暂无操作记录数据</td></tr>';
                                    } else {
                                        var totalLeaseQuantity = 0;
                                        var totalReturnQuantity = 0;
                                        var finalCount = 0;
                                        
                                        for(i = 0; i < details.length; i++){
                                            var item = details[i];
                                            var operateTimeStr = item.operateTime || '';
                                            var operateTime = formatDateTime(operateTimeStr);
                                            
                                            // 确定操作类型
                                            let operateType = '未知';
                                            if (item.operateType === 1 || item.operateType === '1') {
                                                operateType = '领料';
                                            } else if (item.operateType === 2 || item.operateType === '2') {
                                                operateType = '退料';
                                            } else {
                                                operateType = String(item.operateType || '');
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
                                            
                                            // 如果在timeline找不到对应的值，尝试使用不同格式的时间字符串
                                            if (currentCount === 0 && operateTimeStr) {
                                                // 遍历timeline对象的所有键
                                                for (var timeKey in timeline) {
                                                    // 检查是否包含相同的日期部分（忽略精确的时间）
                                                    if (timeKey && operateTimeStr.substring(0, 10) === timeKey.substring(0, 10)) {
                                                        currentCount = timeline[timeKey];
                                                        console.log("找到匹配的时间线数据:", timeKey, currentCount);
                                                        break;
                                                    }
                                                }
                                            }
                                            
                                            // 更新最终数量
                                            finalCount = currentCount;
                                            
                                            // 根据操作类型设置不同的行样式
                                            var rowStyle = '';
                                            if (operateType === '领料') {
                                                rowStyle = 'background-color: #e8f5e9; color: #388e3c;';  // 绿色背景，深绿色文字
                                            } else if (operateType === '退料') {
                                                rowStyle = 'background-color: #ffebee; color: #d32f2f;';  // 红色背景，深红色文字
                                            }
                                            
                                            detailHtml += '<tr style="' + rowStyle + '">';
                                            detailHtml += '<td>' + operateTime + '</td>';
                                            detailHtml += '<td>' + operateType + '</td>';
                                            detailHtml += '<td>' + quantity + '</td>';
                                            detailHtml += '<td>' + currentCount + '</td>';
                                            detailHtml += '</tr>';
                                        }
                                        
                                        // 添加汇总行
                                        detailHtml += '<tr style="font-weight: bold; background-color: #f2f2f2;">';
                                        detailHtml += '<td colspan="2">汇总</td>';
                                        detailHtml += '<td><span style="color: #388e3c;">领料: ' + totalLeaseQuantity + '</span><br><span style="color: #d32f2f;">退料: ' + totalReturnQuantity + '</span></td>';
                                        detailHtml += '<td>' + finalCount + '</td>';
                                        detailHtml += '</tr>';
                                    }
                                    
                                    detailHtml += '</tbody></table>';
                                    detailHtml += '</div>';
                                    
                                    detailHtml += '</div>'; // 结束 tab-content
                                    detailHtml += '</div>'; // 结束 tab
                                    detailHtml += '</div>'; // 结束外层div
                                    
                                    // 打开弹窗
                                    const layerIndex = layer.open({
                                        type: 1,
                                        title: data.machineTypeName + ' 使用明细',
                                        content: detailHtml,
                                        area: ['800px', '500px'],
                                        shadeClose: true,
                                        success: function () {
                                            // 初始化Tab
                                            layui.element.render('tab');
                                        }
                                    });
                                }
                            });
                        } else {
                            $('#resultTableContainer').html('<div style="padding: 20px; text-align: center;">暂无租赁费用计算结果</div>');
                        }
                        
                        // 渲染领料表格
                        if (leaseData && leaseData.length > 0) {
                            var leaseHeight = calcTableHeight(leaseData.length);
                            
                            table.render({
                                elem: '#leaseTable',
                                data: leaseData,
                                cols: [[
                                    {field: 'machineTypeName', title: '物资名称', width: 160, templet: function(d){
                                        return d.machineTypeName || d.machineName || d.materialName || '';
                                    }},
                                    {field: 'machineModel', title: '规格型号', width: 120, templet: function(d){
                                        return d.machineModel || d.specification || '';
                                    }},
                                    {field: 'machineUnit', title: '单位', width: 80, templet: function(d){
                                        return d.machineUnit || d.unit || '';
                                    }},
                                    {field: 'leaseNum', title: '数量', width: 80, templet: function(d){
                                        return d.leaseNum || d.quantity || d.count || d.num || 0;
                                    }},
                                    {field: 'price', title: '单价(元)', width: 100, templet: function(d){
                                        // 如果没有价格字段，暂时显示0
                                        return d.price || d.unitPrice || '0.00';
                                    }},
                                    {field: 'amount', title: '金额(元)', width: 100, templet: function(d){
                                        // 尝试使用已有金额，或者根据数量和单价计算
                                        var amount = d.amount || d.totalPrice || d.money || 0;
                                        // 如果没有金额，但有数量，尝试计算
                                        if(amount === 0 && d.leaseNum) {
                                            var price = d.price || d.unitPrice || 0;
                                            if(price > 0) {
                                                amount = (parseFloat(d.leaseNum) * parseFloat(price)).toFixed(2);
                                            }
                                        }
                                        return amount || '0.00';
                                    }},
                                    {field: 'leaseUnit', title: '使用单位', width: 120, templet: function(d){
                                        return d.leaseUnit || '';
                                    }},
                                    {field: 'operatePersonName', title: '操作人', width: 100, templet: function(d){
                                        return d.operatePersonName || '';
                                    }},
                                    {field: 'taskCode', title: '任务编号', width: 120, templet: function(d){
                                        return d.taskCode || '';
                                    }},
                                    {field: 'projectName', title: '工程名称', width: 200, templet: function(d){
                                        return d.projectName || '';
                                    }},
                                    {field: 'operateType', title: '操作类型', width: 100, templet: function(d){
                                            return d.operateType === 1 ? '<span style="color: #2f952d">领料</span>' : (d.operateType === 2 ? '<span style="color: #bc412b">退料</span>' : d.operateType || '');
                                    }},
                                    {field: 'remark', title: '备注', width: 120, templet: function(d){
                                        return d.remark || '';
                                    }}
                                ]],
                                page: false,
                                height: leaseHeight,  // 使用计算的高度
                                limit: 1000,
                                // 添加语言配置，修复乱码问题
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
                        } else {
                            $('#leaseTableContainer').html('<div style="padding: 20px; text-align: center;">暂无物资领料明细</div>');
                        }
                        
                        // 渲染退料表格
                        if (returnData && returnData.length > 0) {
                            var returnHeight = calcTableHeight(returnData.length);
                            
                            table.render({
                                elem: '#returnTable',
                                data: returnData,
                                cols: [[
                                    {field: 'machineTypeName', title: '物资名称', width: 160, templet: function(d){
                                        return d.machineTypeName || d.machineName || d.materialName || '';
                                    }},
                                    {field: 'machineModel', title: '规格型号', width: 120, templet: function(d){
                                        return d.machineModel || d.specification || '';
                                    }},
                                    {field: 'machineUnit', title: '单位', width: 80, templet: function(d){
                                        return d.machineUnit || d.unit || '';
                                    }},
                                    {field: 'returnNum', title: '数量', width: 80, templet: function(d){
                                        return d.returnNum || d.quantity || d.count || d.num || 0;
                                    }},
                                    {field: 'price', title: '单价(元)', width: 100, templet: function(d){
                                        // 如果没有价格字段，暂时显示0
                                        return d.price || d.unitPrice || '0.00';
                                    }},
                                    {field: 'amount', title: '金额(元)', width: 100, templet: function(d){
                                        // 尝试使用已有金额，或者根据数量和单价计算
                                        var amount = d.amount || d.totalPrice || d.money || 0;
                                        // 如果没有金额，但有数量，尝试计算
                                        if(amount === 0 && d.returnNum) {
                                            var price = d.price || d.unitPrice || 0;
                                            if(price > 0) {
                                                amount = (parseFloat(d.returnNum) * parseFloat(price)).toFixed(2);
                                            }
                                        }
                                        return amount || '0.00';
                                    }},
                                    {field: 'leaseUnit', title: '使用单位', width: 120, templet: function(d){
                                        return d.leaseUnit || '';
                                    }},
                                    {field: 'operatePersonName', title: '操作人', width: 100, templet: function(d){
                                        return d.operatePersonName || '';
                                    }},
                                    {field: 'taskCode', title: '任务编号', width: 120, templet: function(d){
                                        return d.taskCode || '';
                                    }},
                                    {field: 'projectName', title: '工程名称', width: 200, templet: function(d){
                                        return d.projectName || '';
                                    }},
                                    {field: 'operateType', title: '操作类型', width: 100, templet: function(d){
                                            return d.operateType === 1 ? '<span style="color: #2f952d">领料</span>' : (d.operateType === 2 ? '<span style="color: #bc412b">退料</span>' : d.operateType || '');
                                    }},
                                    {field: 'remark', title: '备注', width: 120, templet: function(d){
                                        return d.remark || '';
                                    }}
                                ]],
                                page: false,
                                height: returnHeight,  // 使用计算的高度
                                limit: 1000,
                                // 添加语言配置，修复乱码问题
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
                        } else {
                            $('#returnTableContainer').html('<div style="padding: 20px; text-align: center;">暂无物资退料明细</div>');
                        }
                        
                        // 滚动到容器顶部确保用户可以看到结果
                        $('html, body').animate({
                            scrollTop: $('#data-container').offset().top - 20
                        }, 500);
                        
                    } else {
                        layer.msg(res.msg || res.resMsg || '数据加载失败');
                    }
                },
                error: function(xhr, status, error) {
                    $('#data-loading').hide();
                    console.error('请求失败:', status, error);
                    console.log('XHR对象:', xhr);
                    layer.msg('网络错误，请重试');
                }
            });
        },
        
        // 保存结算数据
        saveSettlement: function(data) {
            if(!data.projectId) {
                layer.msg('请选择工程');
                return;
            }
            if(!data.startTime || !data.endTime) {
                layer.msg('请选择统计时间范围');
                return;
            }
            
            layer.confirm('确定保存此结算数据吗？', function(index) {
                $.ajax({
                    url: baseUrl + '/backstage/projectCost/saveSettlement',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function(res) {
                        // 处理可能是字符串的响应
                        if (typeof res === 'string') {
                            try {
                                res = JSON.parse(res);
                                console.log("已将字符串响应解析为对象:", res);
                            } catch(e) {
                                console.error("响应无法解析为JSON:", e);
                            }
                        }

                        if(res.resMsg === '保存成功' || res.res === 1) {
                            layer.msg('保存成功');
                            // 可选：关闭当前页并刷新列表
                            const index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.layui.table.reload('LAY-project-cost-list');
                        } else {
                            layer.msg(res.msg || '保存失败');
                        }
                    },
                    error: function() {
                        layer.msg('网络错误，请重试');
                    }
                });
                
                layer.close(index);
            });
        },
        
        // 保存计算结果数据
        saveCalculation: function(data) {
            if(!data.projectId) {
                layer.msg('请选择工程');
                return;
            }
            if(!data.startTime || !data.endTime) {
                layer.msg('请选择统计时间范围');
                return;
            }
            
            // 获取当前项目名称
            const projectName = $('select[name="projectId"] option:selected').text();

            // 检查是否有计算结果数据
            if (!window.calculationResults || !window.totalAmount) {
                layer.msg('请先生成计算结果再保存');
                return;
            }
            
            // 准备保存的数据
            const saveData = {
                projectId: data.projectId,
                projectName: projectName,
                startTime: data.startTime,
                endTime: data.endTime,
                calculationResults: window.calculationResults || [],
                totalAmount: window.totalAmount || 0
            };

            layer.confirm('确定保存此计算结果吗？', function(index) {
                $.ajax({
                    url: baseUrl + '/backstage/projectCost/saveCalculation',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(saveData),
                    success: function(res) {
                        if(res.success || res.res === 1) {
                            layer.msg('保存成功');
                            // 可选：关闭当前页并刷新列表
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.layui.table.reload('LAY-project-cost-list');
                        } else {
                            layer.msg(res.msg || '保存失败');
                        }
                    },
                    error: function() {
                        layer.msg('网络错误，请重试');
                    }
                });
                
                layer.close(index);
            });
        },
        
        // 加载详情数据
        loadDetail: function(id) {
            $.ajax({
                url: baseUrl + '/backstage/projectCost/getSettlementDetail',
                type: 'get',
                data: {id: id},
                success: function(res) {
                    if(res.success) {
                        var data = res.data;
                        if(data) {
                            // 填充表单数据
                            form.val('detail-form', {
                                'projectName': data.projectName,
                                'settlementTime': data.createTime,
                                'timeRange': data.startTime + ' 至 ' + data.endTime
                            });
                            
                            // 渲染领料明细表格
                            table.render({
                                elem: '#leaseDetails-table',
                                url: baseUrl + '/backstage/projectCost/getSettlementLeaseDetails',
                                where: {settlementId: id},
                                cols: [[
                                    {field: 'materialName', title: '物资名称', width: 160},
                                    {field: 'specification', title: '规格型号', width: 120},
                                    {field: 'unit', title: '单位', width: 80},
                                    {field: 'quantity', title: '数量', width: 100},
                                    {field: 'price', title: '单价(元)', width: 100},
                                    {field: 'amount', title: '金额(元)', width: 120}
                                ]],
                                page: false,
                                height: 'full-500',
                                // 添加语言配置，修复乱码问题
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
                            
                            // 渲染退料明细表格
                            table.render({
                                elem: '#returnDetails-table',
                                url: baseUrl + '/backstage/projectCost/getSettlementReturnDetails',
                                where: {settlementId: id},
                                cols: [[
                                    {field: 'materialName', title: '物资名称', width: 160},
                                    {field: 'specification', title: '规格型号', width: 120},
                                    {field: 'unit', title: '单位', width: 80},
                                    {field: 'quantity', title: '数量', width: 100},
                                    {field: 'price', title: '单价(元)', width: 100},
                                    {field: 'amount', title: '金额(元)', width: 120}
                                ]],
                                page: false,
                                height: 'full-500',
                                // 添加语言配置，修复乱码问题
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
                        }
                    } else {
                        layer.msg(res.msg || '数据加载失败');
                    }
                },
                error: function() {
                    layer.msg('网络错误，请重试');
                }
            });
        },
        
        // 打印详情
        printDetail: function(id) {
            window.open(baseUrl + '/backstage/projectCost/printDetail?id=' + id);
        }
    };
    
    // 输出接口
    exports('projectCost', projectCost);
});

// 添加日期格式化函数
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
