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
	String nowTime = DateTimeHelper.getNowDate(); 
%> <!-- lsun -->
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
					
		<div class="row-fluid" >	
					
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main" >	
						&emsp;&nbsp;<input type="text" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr >
								<td style="width:10%"  class="center hidden-480">序号</td>
								<td style="width:10%" class="center">物资名称</td>
								<td style="width:10%" class="center">物资类型</td>
								<td style="width:10%" class="center">物资单位</td>
								<td style="width:10%" class="center">分公司名称</td>
								<td style="width:10%" class="center">盘点人</td>
								<td style="width:10%" class="center">盘点数量</td>  
								<td style="width:10%" class="center">盘点时间</td>
								<td style="width:10%" class="center">盘盈/盘亏</td>
								<td style="width:10%" class="center">备注</td>							
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
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
<script src="${bonuspath}/static/js/ma/inventoryRecordlist.js?v=1"></script>		
</body>
</html>