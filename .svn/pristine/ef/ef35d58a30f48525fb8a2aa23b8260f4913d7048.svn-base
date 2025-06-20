<%@page import="javax.sound.midi.SysexMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="bonuspath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${bonuspath}/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/js/jquery/jquery-form.js"></script>
<%-- <script type="text/javascript" src="${bonuspath}/static/js/layui/layui.js"></script>
<script type="text/javascript" src="${bonuspath}/static/js/pm/machines.js"></script> --%>
<script type="text/javascript" src="${bonuspath}/static/js/ace/jy.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
<title>Insert title here</title>

</head>
<style>
html,body{
	width:100%;
	height:100%;
	margin: 0;
	padding: 0;
}
.head {
	width: 100%;
	line-height: 150%;
	background-color: red;
	text-align: center;
	padding: 5px 0;
}

.title {
	color: white;
	vertical-align: middle;
}
span{
	font-size:16px;
	color: red;
}

</style>
<body onclick="warningPage()">
<script type="text/javascript">
	var bonuspath = localStorage.getItem("bonuspath");
	
	findWarnModel();
	function findWarnModel(){
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machineType/findWarnModel",
			data: {
				keyWord : null
			},
			dataType: "json",
			success: function(data) {
				$("#main").html("");
				var res = data.obj.list.results;
				var warnSize = data.obj.list.totalRecord;
				var html = "";
				html += "发现<span>"+ warnSize +"</span>种机具库存不足，分别是：</br>";
				for(var i=0;i<res.length;i++){
					var l = res[i];
					if(i==res.length-1){
						html += "<span>" + l.parentName + "</span>(<span>" + l.name + "</span>)。";
					}else{
						html += "<span>" + l.parentName + "</span>(<span>" + l.name + "</span>)、";
					}
				}
				html+="</br>点击可查看详情"
				$("#main").append(html);
			}
		});
	}
	
	function warningPage(){
		var index=parent.layer.getFrameIndex(window.name);//获取当前弹出层的层级
	    window.parent.layer.open({
	    	type: 2,
			title:['库存预警明细','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['900px', '600px'],
			content: bonuspath+'/backstage/machineType/warnPage'
	    });
	}
</script>
	<div class="head">
		<span class="title">库存预警</span>
	</div>
	<table>
		<tr id="main">
		</tr>
	</table>
</body>
</html>