<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script src="${bonuspath}/static/js/ace/select2.full.js"></script>
</head>
<style>
html,body{
	width:100%;
	height:100%;
	margin: 0;
	padding: 0;
}

</style>
<body>
	<div id="ulDiv">
        <ul id="dowebok">
        </ul>
    </div>
    <script type="text/javascript">
    var photos = [];
    var photoPath = localStorage.getItem("urlPathView");
    $(function () {
        if (photoPath != null && photoPath != "") {
            getHidePhotoList(photoPath);
        } else {
            getHidePhotoList(null);
        }
    });

    // 加载接地图片
    function getHidePhotoList(data) {
        $("#dowebok").empty();
        var results = data;
        if (results != null && results.length > 0) {
            var html = "";
            var s = results.replaceAll("@","/");
          /*   var l =bonuspath + '/backstage/machine/downFile?headerUrl='+s; */
            var l = bonuspath + "/" + "filePath" + data;
            var path = l;
            html += "<li class='disPhotoLi'>";
            html += '<img data-original="' + path + '" src="' + path + '"  class="img" />';
            html += " </li>";
            $("#dowebok").append(html);
            /*var viewer = new Viewer(document.getElementById('dowebok'), {
                url: 'data-original',
                show: function () {
                    viewer.update();
                }
            });*/
        } else {
            var html = "<li style='color:black;float: left;border: none; font-size: 20px;height: 20%;position: absolute;\n" + "left: 315px;\n" + "top: 170px;'>没有相关数据</li>";
            $("#dowebok").append(html);
        }
    }
	</script>
</body>
</html>