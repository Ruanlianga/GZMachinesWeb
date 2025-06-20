<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.bonus.core.DateTimeHelper"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<link rel="stylesheet" href="${bonuspath}/static/plugins/bootstrap/css/bootstrap-select.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script src="${bonuspath}/static/plugins/bootstrap/js/bootstrap-select.js"></script>
</head>
<body>
<div id="auDiv" >
	<form id="auForm" method="POST" onsubmit="return false;">
		<table id="dd" cellpadding="0" border="0" cellspacing="0" style="width:98%;">
			<tr>
				<td colspan="10" style="text-align: center; font-size: 1.4em;font-weight: bold;font-family:'宋体'">
					维修合格入库单
				</td>
			</tr>
			
			<tr>
			    <td colspan="5" style="height:30px;font-family:'宋体'"><span style="font-weight: bold;" >打印时间：</span><span class="applyTime"></span></td>
			</tr>
			
			<tr id="header">
				<td style="width:3%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">序号</td>
				<td style="width:12%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">机具类型</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">机具规格</td>
				<td style="width:12%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">维修数量</td>
				<td style="width:7%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">设备编号</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">维修时间</td>
				<td style="width:7%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">维修结果</td>
				<td style="width:14%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom:  1px solid #000000;border-right:1px solid #000000;font-weight: bold;font-family:'宋体'">备注</td>
			</tr>
			<tr>
				<td colspan="4" style="height:30px;font-weight: bold;border-top: 1px solid #000000;font-family:'宋体'">维修：</td>
				<td colspan="3" style="height:30px;font-weight: bold;border-top: 1px solid #000000;font-family:'宋体'">检验：</td>
				<td colspan="3" style="height:30px;font-weight: bold;border-top: 1px solid #000000;font-family:'宋体'">库管：</td>
			</tr>
			
			
		</table>
	</form>
	<button style="bottom: 30px;" onClick="jqprinting1()">打印</button>
	<button style="bottom: 30px;" onClick="save1()">保存</button>
</div>
<script  src="${bonuspath}/static/js/repair/printRepairTask.js?v=1"></script>	
</body>

