<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
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
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							&emsp;&nbsp;<input type="text" id="deviceName" name="deviceName" placeholder="这里输入机具名称"   class="input-large">
							<input type="text" id="deviceModel" name="deviceModel" placeholder="这里输入规格型号"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button type="button" class="btn btn-sm btn-danger" onclick="exportData();">导出数据</button>
						</div>	 		
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:7%"  class="center hidden-480">序号</th>
								<th style="width:13%" class="center">机具名称</th>
								<th style="width:13%" class="center">规格型号</th>
								<th style="width:5%" class="center">单位</th>
								<th style="width:8%" class="center">在库数量</th>
								<th style="width:8%" class="center">在用数量</th>
								<th style="width:8%" class="center">在修数量</th>
							    <th style="width:8%" class="center">待入库数量</th>
								<th style="width:8%" class="center">报废数量</th>
								<th style="width:8%" class="center">总保有量</th>
								<th style="width:7%" class="center">是否固资</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-3">
							<!-- <div class="dataTables_info customBtn" >
								 &emsp;<a href="#" title="导出" id="export" class="lrspace3" ><i class='icon-download color bigger-220'></i></a> 
							</div> -->
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
<script src="${bonuspath}/static/js/ma/maStock.js?v=1"></script>		
</body>
</html>