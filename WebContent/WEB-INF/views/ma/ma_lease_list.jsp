<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
String endTime = DateTimeHelper.getNowDate();
String startTime = DateTimeHelper.getPreMonthDate();
%>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script src="${bonuspath}/static/js/ace/select2.full.js"></script>
<style type="text/css">
	
</style>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST">
					<div class="row">
					<div class="widget-main">	
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="keyWord" id="keyWord" placeholder="这里输入关键词"   class="input-large">	 
						 &nbsp;<input type="text" name="bsName" id="bsName" placeholder="这里输入领用方"   class="input-large">	 
						 &nbsp;<input type="text" name="projectName" id="projectName" placeholder="这里输入工程"   class="input-large">	 
						 &nbsp;<input type="text" name="deviceName" id="deviceName" placeholder="这里输入机具名称"   class="input-large">	 
						 &nbsp;<input type="text" name="deviceModel" id="deviceModel" placeholder="这里输入机具规格"   class="input-large">	 
						&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
					
						<button id='resetBtn' class="btn btn-error btn-sm" title="重置" type="button" onclick="resetList()" >重置</button>
					
					</div>		
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					<iframe name="downloadFrame" style="display: none;" frameborder="0"></iframe>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:8%" class="center">协议号</th>
								<th style="width:6%" class="center">领用方</th>
								<th style="width:10%" class="center">往来单位</th>
								<th style="width:12%" class="center">工程名称</th>
								<th style="width:8%" class="center">机具名称</th>
								<th style="width:8%" class="center">规格名称</th>
								<th style="width:8%" class="center">单位</th>
								<th style="width:8%" class="center">租赁数量</th>
								<th style="width:8%" class="center">归还数量</th>
								<th style="width:8%" class="center">在用数量</th>
							<!-- 	<th style="width:6%" class="center">操作</th> -->
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-2">
							<div class="dataTables_info customBtn" >
								 &emsp;<a href="#" title="导出" id="export" class="lrspace3" ><i class='icon-download color bigger-220'></i></a> 
							</div>
						</div>
						<div class="col-sm-10">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<%@include file ="./codeform.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/ma_lease_list.js?v=1"></script>		
</body>
</html>