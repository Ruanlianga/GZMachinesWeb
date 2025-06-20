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
							&emsp;&nbsp;<input type="text" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button id="addBtn" class="btn btn-success btn-sm" title="新增" type="button">新增</button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%" class="center">
									<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
								</th>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:10%" class="center">证书编号</th>
								<th style="width:10%" class="center">单位名称</th>
								<th style="width:10%" class="center">器具名称</th>
								<th style="width:10%" class="center">型号规格</th>
								<th style="width:10%" class="center">出厂编号</th>
								<th style="width:10%" class="center">制造厂商</th>
								<th style="width:10%" class="center">检验日期</th>		
								<th style="width:10%" class="center">检验单位</th>
								<th style="width:8%" class="center">二维码查看</th>							
								<th style="width:8%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
								&emsp;<!-- <a href="#" title="增加" id="addBtn" class="lrspace3" ><i class='icon-plus-sign color bigger-220'></i></a> -->
								<a href="#" title="批量删除" id="delBatchBtn" class="lrspace3" ><i class='icon-trash color-red bigger-220'></i></a>
							</div>
						</div>
						<div class="col-sm-8">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #addorUpdateFrom -->
			<%@include file="checkQrCodeForm.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/checkQrCode.js?v=1"></script>		
</body>
</html>