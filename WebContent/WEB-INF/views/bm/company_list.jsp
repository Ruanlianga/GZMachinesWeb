<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.bonus.core.DateTimeHelper"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%
	String nowTime = DateTimeHelper.getNowDate();
%>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script src="${bonuspath}/static/js/ace/select2.full.js"></script>
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
							&emsp;&nbsp;<input type="text" name="keyWord" placeholder="输入单位名称、人员关键字"   class="input-large">		
							<input type="text" name="startTime" id="startTime" readonly placeholder="开始时间"  class="">
							<input type="text" name="endTime" id="endTime" readonly placeholder="结束时间"    class="">	
							<font></font><select id="companyId" name="companyId"></select>
							<font></font><select id="typeId" name="typeId"></select>
							<!-- <font>结算：</font>
							<select id="isDis" name="isDis">
								<option selected="selected" value="0">否</option>
								<option value="1">是</option>
							</select> -->
							<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button class="btn btn-success btn-sm" title="新增" type="button" onclick="add()">新增</button>
						</div>			
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:15%" class="center">单位名称</th>
								<th style="width:10%" class="center">单位类型</th>
								<th style="width:10%" class="center">创建时间</th>
								<th style="width:10%" class="center hidden-480">材料员</th>
								<th style="width:10%"  class="center ">项目经理</th>
								<th style="width:11%"  class="center ">联系方式</th>
								<th style="width:12%" class="center hidden-480">操作</th>
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
			<%@include file="./company_form.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/bm/company.js?v=1"></script>		
</body>
</html>