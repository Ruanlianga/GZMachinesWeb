<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp"%>
<%@include file="../systemset.jsp"%>
<link rel="stylesheet"
	href="${bonuspath}/static/js/layui-v2.9.18/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui-v2.9.18/layui/layui.js"></script>
<style type="text/css">
.layui-tab-brief>.layui-tab-title .layui-this {
	color: #3399CC;
}
.layui-tab-brief>.layui-tab-more li.layui-this:after, .layui-tab-brief>.layui-tab-title .layui-this:after {
    border-bottom: 2px solid #3399CC;
}
a {
    color: #428bca;
    text-decoration: none;
}
a:hover, a:focus {
    color: #2a6496;
    text-decoration: underline;
}
</style>
</head>
<body>
	<div class="layui-tab layui-tab-brief" style="padding: 0 28px;" lay-filter="demo-filter-tab">
		<ul class="layui-tab-title">
			<li value="" class="layui-this">全部</li>
			<li value="1">待审核</li>
			<li value="2">已通过</li>
			<li value="3">已驳回</li>
		</ul>
	</div>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST"
					onsubmit="return false;">
					<div class="row">
						<div class="widget-main">
							&emsp;&nbsp;<input type="text" name="keyWord" id="keyWord"
								placeholder="这里输入关键词" class="input-large" maxlength="30">
								<input type="text" value="" id="auditStatus" name="auditStatus" hidden>
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤"
								type="button" onclick="getbaseList(1)">
								<i class="icon-search bigger-110 icon-only"></i>
							</button>
							<button class="btn btn-sm" title="重置" type="button"
								onclick="resetSearch()">重置</button>
							<button class="btn btn-danger btn-sm" title="导出" type="button"
								onclick="exportData()">导出</button>
						</div>
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1' /> <input
						type='hidden' class='pageSize' name='pageSize' value='10' />
				</form>
				<table id="baseTable"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 5%" class="center hidden-480">序号</th>
							<th style="width: 10%" class="center">计划单号</th>
							<th style="width: 20%" class="center">工程名称</th>
							<th style="width: 10%" class="center">项目部分</th>
							<th style="width: 10%" class="center">审核状态</th>
							<th style="width: 10%" class="center">提交人</th>
							<th style="width: 15%" class="center">提交时间</th>
							<th style="width: 10%" class="center">备注</th>
							<th style="width: 10%" class="center hidden-480">操作</th>
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
	<script src="${bonuspath}/static/js/demandPlan/plan_check_list.js?v=1"></script>
</body>
</html>