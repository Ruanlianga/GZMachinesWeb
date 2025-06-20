<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<meta name="renderer" content="webkit">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
	String nowTime = DateTimeHelper.getNowDate();
%>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
</head>

<body>
<div class="page-content" >
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							&emsp;&nbsp;绑定日期：
							<input type="text" readonly name="startTime" value="<%=nowTime %>" id="startTime">
							<input type="text" readonly name="endTime" value="<%=nowTime %>" id="endTime">
							<input type="hidden" name="maModelId" id="maModelId" placeholder="请输入关键字" class="input-large">
							<input type="text" name="userName" placeholder="请输入关键字"  class="input-large">
							<button id='searchBtn' style="height: 30px; margin: 0px" class="btn btn-warning btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:8%" class="center hidden-480">序号</th>
								<th style="width:20%" class="center">机具类型</th>
								<th style="width:20%" class="center">规格型号</th>
								<th style="width:20%" class="center">机具编号</th>
								<th style="width:20%" class="center">二维码编号</th>
								<th style="width:12%" class="center">绑定人</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-3">
						</div>
						<div class="col-sm-9">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/repair/ma_input_details.js?v=1"></script>		
</body>
</html>