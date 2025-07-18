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
							&emsp;&nbsp;<input type="text" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button class="btn btn-success btn-sm" id="addBtn" title="新增" type="button">新增</button>
							<img src="${bonuspath}/static/img/tips.png" style="width: 40px;height: 40px;float: right;margin-right: 100px;" onclick="tips()">
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
								<th style="width:10%" class="center">登录名</th>
								<th style="width:10%" class="center hidden-480">用户名</th>
								<th style="width:15%"  class="center">单位部门 </th>
								<th style="width:12%"  class="center hidden-480">岗位</th>
								<th style="width:12%"  class="center ">电话号码</th>
								<th style="width:5%"  class="center hidden-480">性别</th>
								<th style="width:5%"  class="center hidden-480">电子签名</th>
								<th style="width:15%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
								<!-- &emsp;<a href="#" title="增加" id="addBtn" class="lrspace3" ><i class='icon-plus-sign color bigger-220'></i></a> -->
								&emsp;<a href="#" title="批量删除" id="delBatchBtn" class="lrspace3" ><i class='icon-trash color-red bigger-220'></i></a>
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
			<%@include file="userform.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/sys/user.js?v=1"></script>		
</body>
</html>