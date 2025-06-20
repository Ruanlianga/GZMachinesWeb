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
String endTime = DateTimeHelper.getNowDate();
String startTime = DateTimeHelper.getPreMonthDate();
%>
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
						&emsp;&nbsp;<font>选择日期：</font> 
								<input class="datainp" id="startTime" name="startTime" value="<%=startTime %>" readonly placeholder="只显示年月" type="text"/>
								<input class="datainp" id="endTime" name="endTime" value="<%=endTime %>" readonly placeholder="只显示年月" type="text"/>
						&emsp;&nbsp;<input type="text" id="keyWord" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
						
						 &nbsp;<input type="text" name="maTypeName" id="maTypeName" placeholder="这里输入机具名称"   class="input-large">	 
						 &nbsp;<input type="text" name="maModelName" id="maModelName" placeholder="这里输入机具规格"   class="input-large">	 
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						
						<button id='resetBtn' class="btn btn-error btn-sm" title="重置" type="button">重置</button>		
						</div>	
							
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:5%" class="center">客服代表</th>
								<th style="width:5%" class="center">机具名称</th>
								<th style="width:5%" class="center">机具规格</th>
								<th style="width:5%" class="center">设备编码</th>
								<th style="width:5%" class="center">备注</th>
								<th style="width:5%" class="center">协议号</th>
								<th style="width:5%" class="center">公司名称</th>
								<th style="width:5%" class="center">单号</th>
								<th style="width:5%" class="center">领料方</th>
								 <th style="width:5%" class="center">领料数量</th>  
								<th style="width:5%" class="center">领料人</th>
								<th style="width:5%" class="center">领料日期</th>
								<th style="width:5%" class="center">领料单位</th>
								<th style="width:10%" class="center">领料工程</th>
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
						<div class="col-sm-8">
							<!--设置分页位置-->
							 <div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>  
						</div>
					</div>
			<!-- #addorUpdateFrom -->
			<%-- <%@include file="venderform.jsp" %> --%>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/pickDetailsList.js?v=1"></script>		
</body>
</html>