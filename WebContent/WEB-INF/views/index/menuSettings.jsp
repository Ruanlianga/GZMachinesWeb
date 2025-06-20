<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../baseset.jsp"%>
    <!-- 预加载关键资源 -->
<!-- <link rel="preload" href="https://cdn.bootcdn.net/ajax/libs/echarts/5.4.3/echarts.min.js" as="script"> -->
<script src="${bonuspath}/static/js/index/echarts.min.js"></script>
<!-- <link rel="preload" href="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js" as="script"> -->
<script src="${bonuspath}/static/js/index/jquery.min.js"></script>
<!-- <link rel="preload" href="https://cdn.bootcdn.net/ajax/libs/layer/3.5.1/layer.min.js" as="script"> -->
<script src="${bonuspath}/static/js/index/layer.min.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/css/index/indexHome.css" />
<!-- 样式表引入 -->
<!-- Bootstrap CSS -->
<!-- <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="${bonuspath}/static/js/index/bootstrap.min.css" rel="stylesheet">
<!-- Layui CSS -->
<!-- <link href="https://www.layuicdn.com/layui-v2.6.8/css/layui.css" rel="stylesheet"> -->
<link href="${bonuspath}/static/js/index/layui.css" rel="stylesheet">
<script src="${bonuspath}/static/js/layui/layui.js"></script>

<!-- 在head中添加 -->
    <style>
        .menu-settings-container {
            padding: 15px;
        }
        
        .menu-settings-list {
            max-height: 500px;
            overflow-y: auto;
            padding-right: 5px;
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }
        
        .menu-item {
            display: flex;
            align-items: center;
            padding: 8px 5px;
            border: 1px solid #eee;
            border-radius: 4px;
            width: calc(33.33% - 7px);  /* 一行显示3个，减去间距 */
            background: #fff;
            transition: all 0.3s;
        }
        
        .menu-item:hover {
            background: #f5f7fa;
            border-color: #e6e6e6;
        }
        
        .menu-item label {
            margin: 0;
            padding-left: 5px;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 5px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            flex: 1;
        }
        
        .menu-item i {
            font-size: 14px;
            color: #666;
            flex-shrink: 0;
        }
        
        .menu-item input[type="checkbox"] {
            margin: 0;
            cursor: pointer;
            flex-shrink: 0;
        }
        
        /* 自定义滚动条样式 */
        .menu-settings-list::-webkit-scrollbar {
            width: 6px;
        }
        
        .menu-settings-list::-webkit-scrollbar-track {
            background: #f1f1f1;
        }
        
        .menu-settings-list::-webkit-scrollbar-thumb {
            background: #888;
            border-radius: 3px;
        }
        
        .menu-settings-list::-webkit-scrollbar-thumb:hover {
            background: #555;
        }
    </style>
</head>
<body>
    <div class="menu-settings-container">
        <div class="menu-settings-list" id="menuSettingsList"></div>
    </div>

    <script>
        layui.use(['table'], function(){
            var $ = layui.$;
            $(function() {
                console.log("加载xxxxxxxx哈哈哈哈哈哈哈哈哈哈哈哈哈");
                loadMenuSettings();
            });
        });

        // 加载菜单设置
        function loadMenuSettings() {
            console.log('开始加载菜单设置');
            
            $.ajax({
                url: bonuspath + '/backstage/indexHome/getResource',
                type: 'POST',
                success: function(response) {
                    console.log('获取到的原始响应:', response);
                    
                    try {
                        const data = JSON.parse(response);
                        console.log('解析后的数据:', data);
                        
                        if (data.res <= 0) {
                            layer.msg('加载失败：' + data.message, {icon: 2});
                            return;
                        }

                        const menuList = $('#menuSettingsList');
                        menuList.empty();

                        // 渲染菜单项
                        data.obj.forEach(menu => {
                            console.log('处理菜单项:', menu);
                            const menuItem = $('<div>').addClass('menu-item');
                            
                            const checkbox = $('<input>', {
                                type: 'checkbox',
                                id: 'menu_' + menu.id,
                                'data-id': menu.id,
                                checked: menu.isCheck == 1
                            });
                            
                            const label = $('<label>', {
                                for: 'menu_' + menu.id
                            });
                            
                            const icon = $('<i>').addClass(menu.rsIcon || 'layui-icon layui-icon-app');
                            const text = document.createTextNode(menu.rsName);
                            
                            label.append(icon).append(text);
                            menuItem.append(checkbox).append(label);
                            menuList.append(menuItem);
                        });

                    } catch (error) {
                        console.error('解析数据失败:', error);
                        layer.msg('数据格式错误', {icon: 2});
                    }
                },
                error: function(xhr, status, error) {
                    console.error('请求失败:', {
                        status: status,
                        error: error,
                        response: xhr.responseText
                    });
                    layer.msg('加载失败', {icon: 2});
                }
            });
        }

        // 保存设置
        function saveSettings() {
            console.log('开始保存设置');
            
            // 获取所有选中的菜单ID
            let checkedMenus = [];  // 初始化为空数组
            $('#menuSettingsList input[type="checkbox"]:checked').each(function() {
                let id = $(this).data('id');
                console.log('选中的菜单ID:', id);
                checkedMenus.push(
                    id
                );
            });
            
            console.log('选中的菜单:', checkedMenus);
            
            $.ajax({
                url: bonuspath + '/backstage/indexHome/saveResourse',
                type: 'POST',
                data: {
                    list: JSON.stringify(checkedMenus).replace(/[\[\]]/g, '')  // 移除方括号
                },
                success: function(response) {
                    console.log('保存响应:', response);
                    layer.msg('保存成功', {icon: 1});
                    // 刷新父页面的常用功能
                    window.parent.location.reload();
                },
                error: function(xhr, status, error) {
                    console.error('保存请求失败:', {
                        status: status,
                        error: error,
                        response: xhr.responseText
                    });
                    layer.msg('保存失败', {icon: 2});
                }
            });
        }
    </script>
</body>
</html> 