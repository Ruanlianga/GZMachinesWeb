<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
						<div class="row">
							<div class="widget-main">	
								<input type="text" name="keyWord" placeholder="请输入规格型号" class="input-large">			
								<input type="text" style="display: none;" id="parentId" name="parentId" />	
								<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)">
									<i class="icon-search bigger-110 icon-only"></i>
								</button>
							</div>				
						</div>
						<input type='hidden' class='pageNum' name='pageNum' value='1'/>
						<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:10%" class="center">机具类型</th>
								<th style="width:10%" class="center">规格型号</th>
								<th style="width:10%" class="center">重量</th>
								<th style="width:10%" class="center">计量单位</th>
								<th style="width:10%" class="center">租赁价格</th>
								<th style="width:10%" class="center">丢失赔偿价格</th>
								<th style="width:10%" class="center">购置价格</th>
								<th style="width:10%" class="center">库管员</th>
								<th style="width:8%" class="center">操作</th>
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
			<!-- #addorUpdateFrom -->
			<%@include file="typeDetailsform.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/typeDetails.js?v=1"></script>		
</body>
</html>