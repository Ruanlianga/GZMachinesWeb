<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
 String reportCode = (String)request.getSession().getAttribute("reportCode");
String id = (String)request.getSession().getAttribute("id");
%>
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" /> <!-- lsun -->
<script src="${bonuspath}/static/js/layer.js"></script><!-- lsun -->
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" /><!-- lsun -->
<script src="${bonuspath}/static/js/layui/layui.js"></script><!-- lsun -->
</head>


<body>
<input style="display: none" type="text" id="reportCode" value="<%=reportCode%>" />
<input style="display: none" type="text" id="id" value="<%=id%>" />
<table id="auForm" cellspacing="0" cellpadding="0" border="0" style="width: 100%;height: 500px;margin-top: 10%">
	<tbody>
		<tr class="FormData">
			<td class="CaptionTD">证书编号：</td>
			<td class="DataTD">&nbsp;
				<span id="reportCode2" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
		
		<tr class="FormData">
			<td class="CaptionTD">单位名称：</td>
			<td class="DataTD">&nbsp;
				<span id="unitName" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
		
		<tr class="FormData">
			<td class="CaptionTD">器具名称：</td>
			<td class="DataTD">&nbsp;
				<span id="maType" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
		
		<tr class="FormData">
			<td class="CaptionTD">规格型号：</td>
			<td class="DataTD">&nbsp;
				<span id="maModel" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
		
		<tr class="FormData">
			<td class="CaptionTD">出厂编号：</td>
			<td class="DataTD">&nbsp;
				<span id="outCode" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
		
		<tr class="FormData">
			<td class="CaptionTD">制造厂商：</td>
			<td class="DataTD">&nbsp;
				<span id="vender" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
		
		<tr class="FormData">
			<td class="CaptionTD">检验日期：</td>
			<td class="DataTD">&nbsp;
				<span id="checkTime" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
		<tr class="FormData">
			<td class="CaptionTD">检验单位：</td>
			<td class="DataTD">&nbsp;
				<span id="checkUnit" class="FormElement ui-widget-content ui-corner-all"></span>
			</td>
		</tr>
	</tbody>
</table>
<script src="${bonuspath}/static/js/ma/showQRCodePage.js?v=1"></script>		
</body>
</html>