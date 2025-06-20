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
<style>
	#upload{
		width: 15%;
	    height: 33px;
	    background-color: #009688;
	    border: 0;
	    color: #fff;
	    margin-left: 41%;
    }
</style>
</head>
<body>
    <!--************这里是上传图片的代码***************-->
    <!--************这里添加的隐藏的输入框，用来传递images的参数***************-->
    <input type="hidden" name="images" class="image">
    <div class="layui-upload">
        <button type="button" class="layui-btn" id="test1">附件上传(PDF/ZIP)</button>
        <div class="layui-upload-list">
            <div style="width: 100%;height: 300px;text-align:center;font-size: 30px;line-height: 200px;" id="demo1"></div>
            <p id="demoText"></p>
        </div>
    </div>
    <!--************上面里是上传图片的代码***************-->
<button  id="upload">上传</button>
<script src="${bonuspath}/static/js/scrap/fileUpload.js?v=1"></script>		
</body>
</html>