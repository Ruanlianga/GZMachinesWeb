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
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							&emsp;&nbsp;<input type="text" name="keyWord" id="keyWord" placeholder="这里输入关键词"   class="input-medium">	
							<input type="text" name="type0" id="type0" placeholder="请输入机具名称"  class="input-large">
							<input type="text" name="type" id="type" placeholder="请输入规格型号"  class="input-large">
							<input type="text" name="remarks" id="remarks" placeholder="这里输入备注检索"   class="input-large">	
							<select id="batchStatus" name="batchStatus">
								<option value="-1">--机具状态选择--</option>
								<option value="0">移交</option>
								<option value="5">在库</option>
								<option value="6">在用</option>
								<option value="7">在修</option>
								<option value="8">在检</option>
								<option value="9">修试待入库</option>
								<option value="10">待报废</option>
								<option value="11">报废</option>
								<option value="20">已拆分</option>
								<option value="21">已组装</option>
							</select>	
							<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button id='resetBtn' class="btn btn-error btn-sm" title="重置" type="button">重置</button>
						    <button type="button" class="btn btn-success btn-sm" onclick="insert();">盘点入库</button>
						    <button type="button" class="btn btn-success btn-sm" onclick="upload();">操作手册上传</button>
						     <button type="button" class="btn btn-success btn-sm" onclick="downFile();">模板下载</button>
						     <button type="button" class="btn btn-success btn-sm" onclick="importExcel();">excel上传</button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='50'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%"  class="center hidden-480">序号</th>
								<!-- <th style="width:7%" class="center">分公司名称</th> -->
								<th style="width:7%" class="center">机具名称</th>
								<th style="width:7%" class="center">规格型号</th>
								<th style="width:7%" class="center">购置价格</th>
								<th style="width:5%" class="center">固定资产</th>
								<th style="width:5%" class="center">固定编号</th>
								<th style="width:5%" class="center">原编号</th>
								<th style="width:5%" class="center">设备编码</th>
								<th style="width:8%" class="center">资产属性</th>
								<th style="width:8%" class="center">合格证</th>
								<th style="width:8%" class="center">技术材料</th>
								<th style="width:6%" class="center">改造手续</th>
								<th style="width:6%" class="center">操作手册</th>
								<th style="width:6%" class="center">二维码</th>
								<th style="width:6%" class="center">机具状态</th>
								<th style="width:6%" class="center">备注</th>
								<th style="width:10%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-3">
							<div class="dataTables_info customBtn" >
								 &emsp;<a href="#" title="导出" id="export" class="lrspace3" ><i class='icon-download color bigger-220'></i></a> 
							</div>
						</div>
						<div class="col-sm-9">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<%@include file ="./codeform.jsp" %>
			<%@include file ="./machineOperation.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/machine.js?v=1"></script>		
</body>
</html>