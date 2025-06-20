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

</style>
<body>
<script type="text/javascript">
	var id = localStorage.getItem("QRCodeId");
	var bonuspath = localStorage.getItem("path");
	findMachinesUrl(id);
	function findMachinesUrl(id){
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machine/find",
			data: {
				id : id
			},
			dataType: "json",
			success: function(data) {
				var l = data.obj;
				var qrcode = l.qrcode;
				var deviceNum = l.deviceCode;
				$("#main").html("");
				if(qrcode == "" || qrcode == null || qrcode == "null"){
					$("#main").append("该设备尚未绑定");
				}else{
					var str = '';
					str+='<td style="height: 12px; padding-top: 0px; width: 100px">'+
					'<span style="font-size: 12px"><b>  </b></span> </td>'+
					'<td style="height: 12px; padding-top: 0px; width: 300px;">'+
					'<img style="border: 0; margin: 0px 60px;" src=\"'+bonuspath+'/images/'+ qrcode + ".jpg" +'\" id="file2img"  /><br />'+
					'<span style="color:#27A3D9;font-size:16px;margin-left:20%;">设备编号：</span>'+
					'<span style="font-size:16px;">'+ deviceNum +'</span>'+
					'</td>';
					$("#main").append(str);
				}
			}
		});
	}
</script>
	<table>
		<tr id="main">
			
		</tr>
	</table>
</body>
</html>