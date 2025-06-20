<%@page import="javax.sound.midi.SysexMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="bonuspath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${bonuspath}/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript"
	src="${bonuspath}/static/js/jquery/jquery-form.js"></script>
<script type="text/javascript" src="${bonuspath}/static/js/ace/jy.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet"
	href="${bonuspath}/static/js/theme/default/layer.css" />
<title>Insert title here</title>

</head>
<style>
html, body {
	width: 100%;
	height: 50%;
	margin: 0;
	padding: 0;
}

#myFormId {
	margin-left: 20%;
	margin-top: 10%;
}
</style>
<body>

	<form id="myFormId" method="post" action="${bonuspath}/backstage/projectSettlement/uploadFile" enctype="multipart/form-data">
		选择文件:<input type="file" id="fileUrl" name="fileUrl">
		<input style="display: none;" name="id" id="id" type="text" /> 
		<input type="submit" value="上传"/> 
		<input type="button" value="取消" onclick="closeUpload()" />
	</form>

	<script type="text/javascript">
		var id = localStorage.getItem("id");
		
		var bonuspath = localStorage.getItem("bonuspath");
		$("#id").val(id);
		$(function() {
			$("#myFormId").ajaxForm(function(data) {
				var res = JSON.parse(data);
				layer.open({
				    content: res.resMsg,
				    btn: '我知道了',
				    yes: function(index){
				    	var indexs = parent.layer.getFrameIndex(window.name);
						parent.layer.close(indexs); //疯狂模式，关闭所有层
						window.parent.location.reload(); //刷新父页面
				    }
				});
			});
		});

		function closeUpload() {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index); //疯狂模式，关闭所有层
		}
	</script>
</body>
</html>