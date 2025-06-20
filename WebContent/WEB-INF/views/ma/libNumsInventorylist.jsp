<%@page import="com.bonus.core.DateTimeHelper"%> <!-- lsun -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<meta name="renderer" content="webkit"> <!-- lsun -->
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
	String nowTime = DateTimeHelper.getNowDate(); 
%> <!-- lsun -->
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" /> <!-- lsun -->
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script><!-- lsun -->
<script src="${bonuspath}/static/js/layer.js"></script><!-- lsun -->
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" /><!-- lsun -->
<script src="${bonuspath}/static/js/layui/layui.js"></script><!-- lsun -->
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
						
						<span style="cursor: pointer;" onclick="maTypeTree();">
							机具名称
							<a href="#" title="机具名称" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
						</span>
						 <input type="hidden" id="maTypeId" name="firstName" value="0" >
						 <input type="text" id="maTypeName" name="maTypeName" />
						 
					 	 <span style="cursor: pointer;" onclick="maModelTree();">
					 	 	规格型号
					 	 	<a href="#" title="规格型号" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>	
					 	 </span>
					 	 <input type="hidden" id="maModelId" name="model" value="0" class="FormElement ui-widget-content ui-corner-all">
						 <input type="text" id="maModelName" name="maModelName" class="lrspace3 aBtnNoTD" data-toggle="modal"/>  
						 
						<button id="addBtn" class="btn btn-success btn-sm" title="新增" type="button">新增</button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10000'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:10%" class="center">物资名称</th>
								<th style="width:10%" class="center">物资类型</th>
								<th style="width:10%" class="center">物资单位</th>
								<th style="width:10%" class="center">分公司名称</th>
								<th style="width:10%" class="center">库存数量</th>
								<th style="width:10%" class="center">盘盈/盘亏</th>
								<th style="width:10%" class="center">盘点数量</th>
								<th style="width:10%" class="center">备注</th>							
								<th style="width:8%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
						</div>
						<div class="col-sm-8">
							<!--设置分页位置-->
							<!-- <div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div> -->
						</div>
					</div>
			<!-- #addorUpdateFrom -->
			<%@include file="venderform.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/libNumsInventory.js?v=1"></script>		
</body>
</html>