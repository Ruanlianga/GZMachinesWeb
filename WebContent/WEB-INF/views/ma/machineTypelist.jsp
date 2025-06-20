<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />

<link href="${bonuspath}/static/js/index/layui.css" rel="stylesheet">
<script src="${bonuspath}/static/js/index/layui.min.js"></script>

<style type="text/css">
	.orgTree {
		width:100% !important;
		height:600px !important;
		background: none repeat scroll 0 0 #fff !important;
		border:1px solid #ddd !important
	}
</style>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
<script src="${bonuspath}/static/js/sys/main.js"></script>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<div class="row">
						<div class="col-sm-4">
								<select id="typeNameId" style="width: 140px;" name="typeNameId" ></select>
								<select id="nameId" style="width: 140px;" name="nameId"></select>
							<!-- <input type="text" id="typeName" style="width: 115px;"  class="input-large" placeholder="输入机具类型">
							<input type="text" id="name" style="width: 115px;" class="input-large" placeholder="输入机具名称"> -->
							<input type="text" id="model" style="width: 140px;" class="input-large" placeholder="物资型号">
							<button class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="searchType()">
								<i class="icon-search bigger-110 icon-only"></i>
							</button>
							
							
							<ul id="orgTree" class="ztree orgTree"></ul>
						</div>
						<div class="col-sm-8">
							<div class="row page-header">
								<h1>&nbsp;机具类型&nbsp;<small><i class="icon-double-angle-right"></i><span id='roleVisName'>请选择机具类型</span></small></h1>
							</div>
							<form id="baseForm" class="form-inline" method="POST">
								<div class="row">
									<div class="widget-main">	
										&emsp;&nbsp;<input type="text" style="height: 30px;margin: 0px" id="keyWord" name="keyWord" placeholder="这里输入机具类型" class="input-large">			
										<input style="display: none;" type="text" value="0" id="pId" name="parentId"  class="input-large">	
										<input style="display: none;" type="text" value="0" id="level" name="level"  class="input-large">		
										<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)">
											<i class="icon-search bigger-110 icon-only"></i>
										</button>
										<!-- <button title="重置" class="btn btn-sm btn-error" onclick="getbaseList(2)">重置</button> -->
										<button title="新增" class="btn btn-sm btn-success" id = "addBtn">新增</button>
										<button title="库存预警值设置" class="btn btn-sm btn-success" id = "upWarnValue">库存预警值设置值</button>
										 <!-- <button title="测试" class="btn btn-sm btn-success" id = "testBtn">测试</button> -->
										 <!-- <button title="测试" class="btn btn-sm btn-success" id = "testBtn1">测试</button>  --> 
									   <button type="button" class="btn btn-sm" onclick="exportData() ;">导出数据</button>
									</div>
								</div>
								<input type='hidden' class='pageNum' name='pageNum' value='1'/>
								<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
								<!-- <input type='hidden' id="parentId" name='parentId' value=''/> -->
								<input type='hidden' id="companyId" name='companyId' value='0'/>
								<iframe name="downloadFrame" style="display: none;" frameborder="0"></iframe>
							</form>
							<table id="baseTable" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th style="width:5%"  class="center hidden-480">序号</th>
										<th style="width:15%"  class="center ">机具类型</th>
										<th style="width:15%"  class="center ">设备标签</th>
										<th style="width:15%"  class="center ">操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
							<div class="row">
								<div class="col-sm-2" style="visibility: hidden">
									<div class="dataTables_info customBtn" >
										&emsp;<a href="#" title="导出数据" id="export" class="lrspace3" onclick="exportData();" ><i class='icon-download color bigger-220'></i></a> 
									</div>
								</div>
								<div class="col-sm-10">
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