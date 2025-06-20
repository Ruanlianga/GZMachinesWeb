$(function() {
    // 获取URL参数中的typeId
    var typeId = getUrlParam("typeId");
    // 初始加载数据
    loadDetailList(typeId, 1);
});

// 获取URL参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}

// 加载详情列表
function loadDetailList(typeId, pageNum) {
    console.log("typeId="+typeId);
    var params = {
        typeId: typeId,
        pageNum: 1,
        pageSize: 10000
    };
    
    JY.Ajax.doRequest(null, bonuspath + '/backstage/maLease/findByPage', params, function(data) {
        try {
            $("#detailTable tbody").empty();
            
            // 检查返回数据格式
            if (!data || !data.obj || !data.obj.list) {
                console.error("Invalid response data:", data);
                $("#detailTable tbody").append("<tr><td colspan='6' class='center'>数据格式错误</td></tr>");
                return;
            }
            
            var obj = data.obj;
            var list = obj.list;
            var results = list.results;
            
            var html = "";
            if (results != null && results.length > 0) {
                for (var i = 0; i < results.length; i++) {
                    var item = results[i];
                    html += "<tr>";
                    html += "<td class='center'>" + (i + 1) + "</td>";
                    html += "<td class='center'>" + JY.Object.notEmpty(item.projectName) + "</td>";
                    html += "<td class='center'>" + JY.Object.notEmpty(item.usingNum) + "</td>";
                    html += "<td class='center'>" + JY.Object.notEmpty(item.dayDiff) + "</td>";
                    html += "<td class='center'>" + JY.Object.notEmpty(item.agreementCode) + "</td>";
                    html += "<td class='center'>" + JY.Object.notEmpty(item.startTime) + "</td>";
                    html += "</tr>";
                }
                $("#detailTable tbody").append(html);
            } else {
                html += "<tr><td colspan='6' class='center'>没有相关数据</td></tr>";
                $("#detailTable tbody").append(html);
            }
        } catch (e) {
            console.error("Error processing response:", e);
            $("#detailTable tbody").empty().append("<tr><td colspan='6' class='center'>数据处理错误</td></tr>");
        }
    }, function(error) {
        // 添加错误回调
        console.error("Ajax request failed:", error);
        $("#detailTable tbody").empty().append("<tr><td colspan='6' class='center'>加载失败</td></tr>");
    });
}
