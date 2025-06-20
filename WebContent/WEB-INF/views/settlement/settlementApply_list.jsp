<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
 String endTime = DateTimeHelper.getNowDate();
%>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
</head>
<style>
.center {
font-size: 14px;
color: black;
font-style: normal;
}
.title {
text-align:center;
font-size: 16px;
color: black;
font-style: normal;
}
</style>

<body>


<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
						    &nbsp;&nbsp;
						    <input type="text" id="keyWord" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
					    	  &nbsp;选择批次：<select id="batch" name="batch"></select>
					    	<input type="text" id="stopDays" name="stopDays" placeholder="请输入停用天数"   class="input-large">
					     	<button type="button" class="btn btn-info btn-sm" onclick="editStopDays();">提交</button> 
			<!-- 		     	<button style="float: right;margin-right:10px;"type="button" class="btn btn-info btn-sm" onclick="exportLease();">租赁明细导出</button> -->
						</div>				
					</div>
				</form>
				<hr>
			
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
						
					<thead>
						<!-- <tr><th  colspan="10" style="font-weight:800" class="center">租赁费用明细</th></tr> -->
						<tr>
							<th style="width:11%" class="center">设备名称</th>
							<th style="width:11%" class="center">设备规格</th>
							<th style="width:5%" class="center">单位</th>
							<th style="width:8%" class="center">计划单价（元）</th>
							<th style="width:8%" class="center">租赁单价（元）</th>
							<th style="width:8%" class="center">租赁数量</th>
							<th style="width:8%" class="center">租赁日期</th>
							<th style="width:8%" class="center">归还数量</th>
							<th style="width:8%" class="center">归还日期</th>
							<th style="width:8%" class="center">停用天数（天）</th>
							<th style="width:8%" class="center">租赁天数（天）</th>
							<th style="width:9%" class="center">租赁费用（元）</th>
						</tr>
					</thead>
					<tbody></tbody>
				
				</table>
			
		     	<%@include file="leasePrice_form.jsp" %>
				<%--<%@include file="lease_price_form.jsp" %>
				<%@include file="back_machine_form.jsp" %> --%>
				<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>

	
	<script src="${bonuspath}/static/js/settlement/settlementApply.js?v=1"></script>		
<br>

</body>
</html>

