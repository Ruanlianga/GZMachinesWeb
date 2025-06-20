<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
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
							<input type="text" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							分公司<select id="companyId" name="companyId"></select>
							工程类别<select id="typeId" name="typeId"></select>
							电压等级<select id="volId" name="volId"></select>
							是否已结算<select id="isBal" name="isBal">
									<option selected="selected" value="1">否</option>
									<option value="0">是</option>
								 </select>
							&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						</div>			
						
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%" class="center">
									<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
								</th>
									<th style="width:5%"  class="center hidden-480">序号</th>
									<th style="width:10%" class="center">工程名称</th>
									<th style="width:15%" class="center">所属分公司</th>
									<th style="width:10%" class="center">工程类型</th>
									<th style="width:10%" class="center">电压等级</th>
									<th style="width:10%" class="center hidden-480">项目经理</th>
									<th style="width:15%"  class="center ">联系电话</th>
									<th style="width:10%"  class="center ">手机</th>
									<th style="width:15%" class="center hidden-480">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
								<a href="#" title="增加" id="addBtn" class="lrspace3" ><i class='icon-plus-sign color bigger-220'></i></a>
								<!-- <a href="#" title="批量删除" id="delBatchBtn" class="lrspace3" ><i class='icon-trash color-red bigger-220'></i></a> -->
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
			<%@include file="./projectManage_form.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>>
<script src="${bonuspath}/static/js/bm/projectManage.js?v=1"></script>		
</body>
</html>