<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
<style type="text/css">
	.orgTree {
		width:100% !important;
		height:500px !important;
		background: none repeat scroll 0 0 #fff !important;
		border:1px solid #ddd !important
	}
</style>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							&emsp;&nbsp;<input type="text" name="keyWord" id="keyWord" placeholder="这里输入关键词"   class="input-large" maxlength="30">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button class="btn btn-sm" title="重置" type="button" onclick="resetSearch()">重置</button>
						    <button class="btn btn-success btn-sm" id="addBtn" title="需求计划申请" type="button" onclick="addForm()">需求计划申请</button>
						    <button class="btn btn-danger btn-sm" title="导出" type="button" onclick="exportData()">导出</button>
						    <button class="btn btn-sm" title="查看流程" type="button" onclick="showProcess()">查看流程</button>
						</div>			
					</div>
					<input type='hidden' class='pageNum'  name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:10%" class="center">计划编号</th>
								<th style="width:20%" class="center">工程名称</th>
								<th style="width:10%" class="center">需用日期</th>
								<th style="width:10%" class="center">申请人</th>
								<th style="width:10%" class="center">申请时间</th>
								<th style="width:15%" class="center">备注</th>
								<th style="width:10%" class="center">审核状态</th>
								<th style="width:10%" class="center hidden-480">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
							<!-- <div class="dataTables_info customBtn" >
								&emsp;<a href="#" title="增加" id="addBtn" class="lrspace3" ><i class='icon-plus-sign color bigger-220'></i></a>
							</div> -->
						</div>
						<div class="col-sm-8">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #addorUpdateFrom -->
			<%-- <%@include file="./child/apply_plan_form.jsp" %> --%>
			<!-- #dialog-confirm -->
			<%-- <%@include file="../dialog.jsp" %> --%>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/demandPlan/apply_plan_list.js?v=1"></script>		
</body>
</html>