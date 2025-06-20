<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
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
						&nbsp;&nbsp;操作记录类型:<select id="optType" name="optType">
								<option value="-1">--操作类型--</option>
								<option value="2">领料</option>
								<option value="3">机具入库</option>
								<option value="4">机具退料</option>
								<option value="6">维修</option>
								<option value="7">报废</option>
								<option value="8">维修—检验</option>
							</select>	
							 &nbsp;&nbsp;项目名称:<input type="text" name="proName" id="proName"  class="input-medium">	
							 &nbsp;&nbsp;单位名称:<input type="text" name="unitName" id="unitName"  class="input-large">
							 &nbsp;&nbsp;操作人:<input type="text" name="name" id="name"  class="input-large">
							<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList()"><i class="icon-search bigger-110 icon-only"></i></button>
							
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th style="width:6%"  class="center hidden-480">序号</th>
								<!-- <th style="width:7%" class="center">分公司名称</th> -->
								<th style="width:8%" class="center">操作类型</th>
								<th style="width:22%" class="center">项目名称</th>
								<th style="width:22%" class="center">单位名称</th>
								<th style="width:15%" class="center">操作人</th>
								<th style="width:15%" class="center">操作时间</th>
								<th style="width:12%" class="center">备注</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-9">
							<!--设置分页位置-->
							<div class="dataTables_info customBtn" >
								 &emsp;<a href="#" title="导出" id="export" class="lrspace3" ><i class='icon-download color bigger-220'></i></a> 
							</div>
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/lifehistory.js?v=1"></script>		
</body>
</html>