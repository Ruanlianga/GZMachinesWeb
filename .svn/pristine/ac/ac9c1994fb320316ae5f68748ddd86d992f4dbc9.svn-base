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
	var batchId = localStorage.getItem("readPicId");
	var bonuspath = localStorage.getItem("bonuspath");
	
	findInvoiceUrl(batchId);
	function findInvoiceUrl(batchId){
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/inputDetails/find",
			data: {
				id : batchId
			},
			dataType: "json",
			success: function(data) {
				var l = data.obj;
				var machinesUrl = l.picUrl;
				$("#main").html("");
				var str = '';
				str+='<td style="height: 12px; padding-top: 0px; width: 100px">'+
				'<span style="font-size: 12px"><b>  </b></span> </td>'+
				'<td style="height: 12px; padding-top: 0px; width: 300px">'+
				'<img style="border: 0; margin-top: 0px;" src=\"'+bonuspath+'/machinesImg/'+ machinesUrl +'\" id="file2img" width="650" height="350" /></td>';
				$("#main").append(str);
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