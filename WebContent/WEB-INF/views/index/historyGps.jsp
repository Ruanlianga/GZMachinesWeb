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
	String lastMonth = DateTimeHelper.getPreMonthDate();
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
						    <font></font><select id="typeNameId" name="typeNameId"></select>
							<font></font><select id="deviceModel" name="deviceModel"></select>
							<font></font><select id="deviceCode" name="deviceCode"></select>
							<font></font><select id="code" name="code"></select>
							<input type="text" readonly name="startTime" value="<%=lastMonth %>" id="startTime">
							<input type="text" readonly name="endTime" value="<%=nowTime %>" id="endTime">
							<input type="text" id="keyWord" name="keyWord" placeholder="请输入关键字"   class="input-large">		
							<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button id="resetBtn" onclick="reseData('<%=lastMonth %>','<%=nowTime %>')" class="btn btn-error btn-sm" title="重置" type="button">重置</button>
							<button type="button" class="btn btn-sm btn-danger" onclick="exportOldData()">导出数据</button>
						</div>	
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					<iframe name="downloadFrame" style="display: none;" frameborder="0"></iframe>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%" class="center hidden-480">序号</th>
								<th style="width:10%" class="center">GPS编码</th>
								<th style="width:6%" class="center">设备类型</th>
								<th style="width:6%" class="center">规格型号</th>
								<th style="width:9%" class="center">设备编码</th>
								<th style="width:9%" class="center">经度</th>
								<th style="width:9%" class="center">纬度</th>
								<th style="width:6%" class="center">上传时间</th>
								<th style="width:6%" class="center">定位类型</th>
								<th style="width:9%" class="center">地址</th>
								<th style="width:9%" class="center">领用单位</th>
								<th style="width:6%" class="center">领用时间</th>
								<th style="width:10%" class="center">当前所在项目</th>
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
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/baiduMap/historyGps.js?v=1"></script>		
</body>
</html>