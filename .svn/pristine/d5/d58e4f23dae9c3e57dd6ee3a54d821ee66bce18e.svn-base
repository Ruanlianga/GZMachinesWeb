<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<meta name="renderer" content="webkit">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
 String endTime = DateTimeHelper.getNowDate();
 String startTime = DateTimeHelper.getPreMonthDate();
%>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
</head>

<body>
<div class="page-content" >
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							<input type="text" readonly="readonly" value="<%=startTime%>" class="input-large" id="startTime" name="startTime" >
							<input type="text" readonly="readonly" value="<%=endTime%>" class="input-large" id="endTime" name="endTime" >  
							<input type="text" id="machineName" name="machineName" placeholder="这里输入机具名称"   class="input-large">
							<input type="text" id="machineModel" name="machineModel" placeholder="这里输入规格型号"   class="input-large">	
							<input type="text" id="keyWord" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button id='resetBtn' class="btn btn-error btn-sm" title="重置" type="button">重置</button>	
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:12%" class="center">单位名称</th>
								<th style="width:12%" class="center">工程名称</th>
								<th style="width:12%" class="center">分公司名称</th>
								<th style="width:10%" class="center">机具名称</th>
								<th style="width:10%" class="center">机具规格</th>
								<th style="width:10%" class="center">设备编号</th>
								<th style="width:10%" class="center">备注</th>
								<th style="width:8%" class="center">入库数量</th>
								<th style="width:8%" class="center">客服代表</th>
								<th style="width:10%" class="center">入库时间</th>
								<th style="width:8%" class="center">是否计数</th>
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
			<!-- #addorUpdateFrom -->
		<%-- 	<%@include file="outstorageinspectionlist.jsp" %> --%>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/sq/putinstorage.js?v=1"></script>		
</body>
</html>