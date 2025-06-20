<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp"%>
<%@include file="../systemset.jsp"%>
<link rel="stylesheet"
	href="${bonuspath}/static/plugins/jedate/skin/gray.css" />
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
</head>
<body>
	<div class="page-content">
		
		<div class="row-fluid">
			<form id="baseForm" method="POST" onsubmit="return false;">
				<div class="row">
					<div class="widget-main">	
						&emsp;&nbsp;<input type="text" style="height: 30px;margin: 0px" name="keyWord" placeholder="请输入关键词" class="input-large">			
						<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)">
							<i class="icon-search bigger-110 icon-only"></i>
						</button>
					</div>
				</div>
				<input type='hidden' class='pageNum' name='pageNum' value='1'/>
				<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
			</form>
			<table id="bmTable"	class="table table-striped table-bordered table-hover" style="margin-top: 20px;">
				<thead>
					<tr>
						<th style="width: 5%" class="center hidden-480">序号</th>
						<th style="width: 8%" class="center hidden-480">设备编号</th>
						<th style="width: 8%" class="center hidden-480">GPS编号</th>
						<th style="width: 8%" class="center hidden-480">告警名称</th>
						<th style="width: 8%" class="center hidden-480">告警时间</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			<div class="row">
			<div class="col-sm-2">
				<div class="dataTables_info customBtn" ></div>
			</div>
			<div class="col-sm-10">
				<div id="pageing" class="dataTables_paginate paging_bootstrap">
					<ul class="pagination"></ul>
				</div>
			</div>
	       </div>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp"%>
		</div>
	</div>
	<div>
		</div>
	<script src="${bonuspath}/static/js/warning/tip.js"></script>
</body>
</html>