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
	String tomorrowDate = DateTimeHelper.getTomorrowDate(nowTime);
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
							<input type="text" name="machinesName" placeholder="这里输入关键词"   class="input-large">			
							&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button type="button" id="addBtn" class="btn btn-sm btn-success" onclick="add();">新增</button>
							<!-- <button type="button" id="selectPersonBtn" class="btn btn-sm btn-info" onclick="selectPerson();">通知人员</button> -->
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					<input type='hidden' id='tomorrowDate' value='<%=nowTime%>'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:4%"  class="center hidden-480">序号</th>
								<th style="width:6%" class="center">机具名称</th>
								<th style="width:6%" class="center">规格型号</th>
								<th style="width:5%" class="center">采购单价(元)</th>
								<th style="width:8%" class="center">采购数量</th>
								<th style="width:8%" class="center">机具信息</th>
								<th style="width:8%" class="center">机具厂家</th>
								<th style="width:8%" class="center">验收地点</th>
								<th style="width:8%" class="center">检验员</th>
								<th style="width:8%" class="center">客服代表</th>
								<th style="width:8%" class="center">验收图片</th>
								<th style="width:8%" class="center">验收状态</th>
								<th style="width:8%" class="center">机具发票</th>
								<th style="width:5%" class="center">操作</th>
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
			<!-- #addorUpdateFrom -->
			<%@include file="newInputDetailsform.jsp" %>
			<%@include file="checkerform.jsp" %>
			<%@include file="serviceform.jsp" %>
			<%@include file="machinesAcceptform.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/newInput/newInputDetails.js?v=1"></script>		
</body>
</html>