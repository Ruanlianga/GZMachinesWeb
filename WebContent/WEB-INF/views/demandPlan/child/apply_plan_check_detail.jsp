<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../baseset.jsp"%>
<%@include file="../../systemset.jsp"%>
<script type="text/javascript" src="${bonuspath}/static/js/Print.js"></script>
<link rel="stylesheet"
	href="${bonuspath}/static/js/layui-v2.9.18/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui-v2.9.18/layui/layui.js"></script>
<title>机具需求计划审核详情</title>
<style type="text/css">
.layout {
	display: flex;
	justify-content: center;
	align-items: center;
}

#main-box {
	width: 100%;
	height: 100%;
	padding: 0 2% 0 2%;
	box-sizing: border-box;
}

#plan-detail-box {
	width: 100%;
	height: 80px;
	flex-direction: column;
	align-items: baseline;
	justify-content: space-evenly;
}

#plan-detail-box>p, #plan-basic-box>p, #oper-record-box p,
	#implement-box p {
	font-size: 20px;
	font-weight: bold;
}

#plan-basic-box {
	width: 100%;
	height: 250px;
	flex-direction: column;
	align-items: baseline;
	justify-content: space-evenly;
}

.classTable {
	width: 100%;
	table-layout: fixed;
	text-align: center;
	border-collapse: collapse;
	border-spacing: 1px;
	box-sizing: border-box;
}

.classTable tr td, .classTable tr th {
	height: 50px;
	box-sizing: border-box;
	border: 1px solid #ddd;
	padding: 10px 20px;
	text-align: left;
}

.classTable tr td, .classTable tr th {
	width: 20%
}

.classTable tr td:nth-child(1), .classTable tr th:nth-child(1) {
	width: 40%
}

.classTable tr:nth-child(odd) {
	background-color: #f2f2f2;
}

#implement-box {
	width: 100%;
	flex-direction: column;
	align-items: baseline;
	justify-content: space-evenly;
}

#oper-record-box {
	width: 100%;
	flex-direction: column;
	align-items: baseline;
	justify-content: space-evenly;
}

.layui-timeline {
	margin-top: 10px;
	width: 100%;
}

.oper-info {
	height: 80px;
}

.oper-info>div:nth-child(1) {
	width: 7%;
	height: 100%
}

.oper-info>div:nth-child(2) {
	width: 73%;
	height: 100%
}

.oper-info>div:nth-child(3) {
	width: 20%;
	height: 100%
}

.user-oper {
	flex-direction: column;
}

.page-content {
	width: 100%;
}
</style>
</head>
<body id="body">
	<div id="main-box">
		<div id="plan-detail-box" class="layout">
			<div class="layout" style="width: 100%;justify-content: space-between;">
				<p style="font-size: 20px;font-weight: bold;">机具需求计划</p>
				<div style="width:15%;">
					<button type="button" class="layui-btn" onclick="print()">打印</button>
					<button type="button" id="auditCheck" class="layui-btn layui-bg-orange" onclick="auditData()">审核</button>
				</div>
			</div>
			<div class="layout">
				<p id="code"></p>
				<div>
					<span id="checkStatus"></span>
				</div>
			</div>
		</div>
		<!--基本信息-->
		<div id="plan-basic-box" class="layout">
			<p>基本信息</p>
			<div id="plan-basic-table">
				<table class="classTable">
					<tr>
						<th>项目名称</th>
						<th>项目部分</th>
						<th>工程内容</th>
						<th>需用日期</th>
					</tr>
					<tr>
						<td id="proName"></td>
						<td id="projectPart"></td>
						<td id="projectContent"></td>
						<td id="needTime"></td>
					</tr>
					<tr>
						<th colspan="4">计划说明</th>
					</tr>
					<tr>
						<td colspan="4" id="remark"></td>
					</tr>
				</table>
			</div>
		</div>
		<!--机具明细-->
		<div id="implement-box" class="layout">
			<p>机具明细</p>
			<div class="page-content">
				<div class="row-fluid">
					<div class="col-xs-12">
						<form id="baseForm" class="form-inline" method="POST"
							onsubmit="return false;">
							<div class="row">
								<div class="widget-main">
									&emsp;&nbsp;<input type="text" name="keyWord" id="keyWord"
										placeholder="输入物机名称" class="input-large" maxlength="30">
									<input type="text" name="keyWord2" id="keyWord2"
										placeholder="输入规格" class="input-large" maxlength="30">
									<button id='searchBtn' class="btn btn-warning  btn-sm"
										title="过滤" type="button" onclick="search()">
										<i class="icon-search bigger-110 icon-only"></i>
									</button>
									<button class="btn btn-sm" title="重置" type="button"
										onclick="resetSearch()">重置</button>
									<button class="btn btn-danger btn-sm" title="下载" type="button"
										onclick="exportData()">下载</button>
								</div>
							</div>
							<input type='hidden' class='pageNum' name='pageNum' value='1' />
							<input type='hidden' class='pageSize' name='pageSize' value='10' />
						</form>
						<table id="baseTable"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th style="width: 10%" class="center hidden-480">序号</th>
									<th style="width: 15%" class="center">物机类型</th>
									<th style="width: 20%" class="center">物机名称</th>
									<th style="width: 10%" class="center">规格</th>
									<th style="width: 10%" class="center">单位</th>
									<th style="width: 10%" class="center">需用量</th>
									<th style="width: 10%" class="center">需用天数</th>
									<th style="width: 15%" class="center">备注</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
						<!-- <div class="row">
							<div class="col-sm-4"></div>
							<div class="col-sm-8">
								设置分页位置
								<div id="pageing" class="dataTables_paginate paging_bootstrap">
									<ul class="pagination"></ul>
								</div>
							</div>
						</div> -->
					</div>
				</div>
			</div>
		</div>
		<!-- 操作记录 -->
		<div id="oper-record-box" class="layout">
			<p>操作记录</p>
			<div class="layui-timeline">
				<div class="layui-timeline-item">
					<i class="layui-icon layui-timeline-axis"></i>
					<div class="layui-timeline-content layui-text">
						<h3 class="layui-timeline-title">8月18日</h3>
						<div class="layui-panel">
							<div class="oper-info layout">
								<div class="layout">
									<img src="${bonuspath}/static/img/user_head_icon.png">
								</div>
								<div class="user-oper layout">
									<div style="width: 100%">
										<span>王天峰</span><span>（13787220576）</span><span>项目管理中心</span>
									</div>
									<div style="width: 100%">
										<span>完结-审核确认通过，共耗时：21小时16分钟 原因备注：无</span>
									</div>
								</div>
								<div class="layout">
									<img src="${bonuspath}/static/img/time_icon.png"> <span
										style="margin: 0 5px 0 5px;">2020-12-23 22:31</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="${bonuspath}/static/js/demandPlan/child/apply_plan_check_detail.js?v=1"></script>
</html>