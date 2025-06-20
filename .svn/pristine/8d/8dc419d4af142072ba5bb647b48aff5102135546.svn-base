<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script src="${bonuspath}/static/js/ace/select2.full.js"></script>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12" style="width: 55%;">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							&emsp;&nbsp;<input type="text" name="keyWord" id="keyWord" placeholder="这里输入关键词"   class="input-large">	
							<input type="text" name="type0" id="type0"   placeholder="请输入机具名称"  class="input-large">
							<input type="text" name="type" id="type" placeholder="请输入规格型号"  class="input-large">
							 <select id="batchStatus" name="batchStatus">
								<option value="0">--机具状态选择--</option>
								<option value="5">在库</option>
								<option value="6">在用</option>
								<option value="7">修试</option>
								<option value="11">报废</option>
								<option value="20">已拆分</option>
							</select>	
							<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button id='resetBtn' class="btn btn-error btn-sm" title="重置" type="button">重置</button>
						  
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%"  class="center hidden-480">序号</th>
								<th style="width:7%" class="center">分公司名称</th>
								<th style="width:7%" class="center">机具名称</th>
								<th style="width:9%" class="center">规格型号</th>
								<th style="width:7%" class="center">设备编码</th>
								<th style="width:5%" class="center">机具状态</th>
								<th style="width:5%" class="center">操作</th>
							
							
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-9" style="width:83%">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap" tyle="width:100%">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
				<%@include file ="./codeform.jsp" %>
				<%@include file ="./machineOperation.jsp" %>
				<!-- #dialog-confirm -->
				<%@include file="../dialog.jsp" %>	
			</div>
			<div class="col-xs-12" style="width: 45%;" >
					<form id="" class="form-inline" method="POST" onsubmit="return false;"  style=" margin-left: 85%;">
					<div class="row">
						<div class="widget-main">	
						<button id='searchBtn'  class="btn btn-warning btn-sm" title="保存" type="button" onclick="history()">记录</button>
						<button id='saveBtn' class="btn btn-warning btn-sm" title="保存" type="button" onclick="saveNewData()">保存</button>
						</div>
						</div>
					
					</form>
					<table id="newBaseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%"  class="center hidden-480">序号</th>
								<th style="width:7%" class="center">分公司名称</th>
								<th style="width:7%" class="center">机具名称</th>
								<th style="width:9%" class="center">规格型号</th>
								<th style="width:7%" class="center">设备原编码</th>
								<th style="width:7%" class="center">设备编码</th>
								<th style="width:5%" class="center">机具状态</th>
								<th style="width:5%" class="center">操作</th>
							
							</tr>
						</thead>
						<tbody></tbody>
					</table>
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/disassembly.js?v=1"></script>		
</body>
</html>