<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />

<style type="text/css">
	.orgTree {
		width:100% !important;
		height:500px !important;
		background: none repeat scroll 0 0 #fff !important;
		border:1px solid #ddd !important
	}
</style>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<div class="row">
						<div class="col-sm-3">
							<ul id="orgTree" class="ztree orgTree"></ul>
						</div>
						<div class="col-sm-9">
							<div class="row page-header">
								<h1>&nbsp;机具类型&nbsp;<small><i class="icon-double-angle-right"></i><span id='roleVisName'>请选择机具类型</span></small></h1>
							</div>
							<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
								<div class="row">
									<div class="widget-main">	
										&emsp;&emsp;<input type="text" name="keyWord" placeholder="请输入机具类型" class="input-large">
										<input style="display: none;" type="text" value="-2" id="pId" name="parentId"  class="input-large">			
										<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList()"><i class="icon-search bigger-110 icon-only"></i></button>
									</div>
								</div>
								<input type='hidden' class='pageNum' name='pageNum' value='1'/>
								<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
								<input type='hidden' id="parentId" name='parentId' value='0'/>
								<input type='hidden' id="roleId" name='roleId' value='0'/>
							</form>
							<table id="baseTable" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th style="width:5%"  class="center hidden-480">序号</th>
										<th style="width:15%"  class="center ">机具类型</th>
										<th style="width:15%"  class="center ">操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
							<div class="row">
								<div class="col-sm-4">
									<div class="dataTables_info customBtn" >
										<a href="#" title="增加" id="addBtn" class="lrspace3" ><i class='icon-plus-sign color bigger-220'></i></a>
									</div>
								</div>
								<div class="col-sm-8">
									<!--设置分页位置-->
									<div id="pageing" class="dataTables_paginate paging_bootstrap">
										<ul class="pagination"></ul>
									</div>
								</div>
						   </div>
						</div>
					</div>
			<%@include file="./machineTypeform.jsp" %>	
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/machineType.js?v=1"></script>		
</body>
</html>