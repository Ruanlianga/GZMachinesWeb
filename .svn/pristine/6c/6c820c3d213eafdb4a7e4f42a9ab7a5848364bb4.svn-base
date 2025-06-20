<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.bonus.core.DateTimeHelper"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/plugins/jedate/skin/gray.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jedate/jedate.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<%
	String currentDate = DateTimeHelper.getNowDate();
%>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							<font>领料日期：</font> 
							<input class="datainp" id="pickTime" name="pickTime" readonly value="<%=currentDate%>" placeholder="只显示年月" type="text" style="height: 30px; margin: 0px"/>
							<input type="text" name="keyword" placeholder="这里输入关键词" class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)">
								<i class="icon-search bigger-110 icon-only"></i>
							</button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:6%"  class="center hidden-480">序号</th>
								<th style="width:16%" class="center">领料时间</th>
								<th style="width:16%" class="center">领料单位</th>
								<th style="width:16%" class="center">退料时间</th>
								<th style="width:14%" class="center">退料单位</th>
								<th style="width:10%" class="center">退料状态</th>
								<th style="width:8%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
						</div>
						<div class="col-sm-8">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<%@include file="back_machine_form.jsp" %>	
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/lease/back_machine.js?v=1"></script>		
</body>
</html>