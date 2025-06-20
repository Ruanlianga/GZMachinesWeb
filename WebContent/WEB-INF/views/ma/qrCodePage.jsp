<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String qrcode = (String)request.getSession().getAttribute("qrcode");
%>
<c:set var="bonuspath" value="${pageContext.request.contextPath}"/>
<script>
    var bonuspath = '${bonuspath}';
</script>
<script src="../../static/js/jquery/jquery-2.0.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机具信息</title>
<style type="text/css">
	body,html{
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0;
	}
	.dataDiv{
		width: 100%;
		height: 100%;
		overflow: auto;
	}
	table{
		width: 100%;
		height: 20%;
		font-size: 30px;
	}
	.td_title{
		width: 20%;
	}
	.td_data{
		width: 80%;
	}
	td
{
    border: 1px solid black;
}
	p{font-size: 45px;text-align: center;}
</style>
</head>
<body>
	<input style="display: none" type="text" id="qrcode" value="<%=qrcode%>" />
	<%-- <div id="qrPage"></div> --%>
	<div class="dataDiv">
		<table>
			<tr>
				<td class="td_title">固资类型：</td>
				<td class="td_data" id="type0"></td>
			</tr>
			<tr>
				<td class="td_title">规格型号：</td>
				<td class="td_data" id="type"></td>
			</tr>
			<tr>
				<td class="td_title">设备编码：</td>
				<td class="td_data" id="deviceCode"></td>
			</tr>
			<tr>
				<td class="td_title">二维码编码：</td>
				<td class="td_data" id="qrCode"></td>
			</tr>
			<tr>
				<td class="td_title">备注：</td>
				<td class="td_data" id="remarks"></td>
			</tr>
			<tr>
				<td class="td_title">设备状态：</td>
				<td class="td_data" id="batchStatus"></td>
			</tr>
			<tr>
				<td class="td_title">购买日期：</td>
				<td class="td_data" id="buyTime"></td>
			</tr>
			<tr>
				<td class="td_title">设备厂家：</td>
				<td class="td_data" id="venderName"></td>
			</tr>
			<tr>
				<td class="td_title">资产属性：</td>
				<td class="td_data" id="buyCompany"></td>
			</tr>
			<tr>
				<td class="td_title">维修人：</td>
				<td class="td_data" id="infoName"></td>
			</tr>
			<tr>
				<td class="td_title">维修时间：</td>
				<td class="td_data" id="time"></td>
			</tr>
			<tr>
				<td class="td_title">维修照片：</td>
				<td class="td_data"></td>
			</tr>
			<tr>
				<td colspan="2" height="500px" style="text-align: center;" id="infoFile">
					<!-- <img src="" style="width: 30.5%;height: 48%;margin: 1%">
					<img src="" style="width: 30.5%;height: 48%;margin: 1%">
					<img src="" style="width: 30.5%;height: 48%;margin: 1%">
					<img src="" style="width: 30.5%;height: 48%;margin: 1%">
					<img src="" style="width: 30.5%;height: 48%;margin: 1%">
					<img src="" style="width: 30.5%;height: 48%;margin: 1%">  -->
					<!-- 暂无图片-->
				</td>
			</tr>
			<tr>
				<td class="td_title">合格证照片：</td>
				<td class="td_data"></td>
			</tr>
			<tr>
				<td colspan="2" height="500px" style="text-align: center;" id="filePath">
					<!-- <img src="" style="width: 98%;height: 98%;margin: 1%"> -->
					<!-- 暂无图片-->
				</td>
			</tr>
			<tr>
				<td class="td_title">操作手册：</td>
				<td class="td_data" id="optName"></td>
			</tr>
		</table>
	</div>
</body>
<script src="../../static/js/ma/qrCodePage.js"></script>
</html>