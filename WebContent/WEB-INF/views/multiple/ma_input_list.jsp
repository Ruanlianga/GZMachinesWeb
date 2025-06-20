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
					<form id="baseForm" class="form-inline" method="POST">
					<div class="row">
						<div class="widget-main">	
							&emsp;&nbsp;绑定日期：
							<input type="text" readonly name="startTime" value="<%=nowTime %>" id="startTime">
							<input type="text" readonly name="endTime" value="<%=nowTime %>" id="endTime">
							<input type="text" name="maType" placeholder="请输入机具名称"  class="input-large">
							<input type="text" name="maModel" placeholder="请输入规格型号"  class="input-large">
							<input type="hidden" name="type" id="type" class="input-large">						
							<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					<iframe name="downloadFrame" style="display: none;" frameborder="0"></iframe>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:10%" class="center hidden-480">序号</th>
								<th style="width:30%" class="center">机具类型</th>
								<th style="width:30%" class="center">规格型号</th>
								<th style="width:20%" class="center">绑定数量</th>
								<th style="width:10%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-2">
						<!-- <button class="btn btn-info btn-sm" title="导出规格" type="button" onclick="exportData(1)">导出规格</button>
							<button class="btn btn-danger btn-sm" title="导出明细" type="button" onclick="exportData(2)">导出明细</button> -->
						<div class="dataTables_info customBtn" >
							&emsp;<a href="#" title="导出数据"  class="lrspace3" onclick="exportData(1)" ><i class='icon-download color bigger-220'></i></a> 
						</div>
						<div class="dataTables_info customBtn" >
							&emsp;<a href="#" title="导出明细"  class="lrspace3" onclick="exportData(2)" ><i class='icon-download color bigger-220'></i></a> 
						</div>
						</div>
						<div class="col-sm-10">
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
<script src="${bonuspath}/static/js/repair/ma_input_list.js?v=1"></script>		
</body>
</html>