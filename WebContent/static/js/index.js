/**
 * layui的主入口模块
 */
layui.define(['layer', 'form', 'element', 'table'], function(exports){
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        element = layui.element,
        table = layui.table;
    
    // 定义全局变量
    var global = {
        // 公共事件监听
        eventListener: function() {
            // 返回按钮事件监听
            $(document).on('click', '.js-back', function() {
                history.back(-1);
            });
        },
        
        // 初始化方法
        init: function() {
            this.eventListener();
        }
    };
    
    // 对外输出全局对象
    exports('index', global);
});
