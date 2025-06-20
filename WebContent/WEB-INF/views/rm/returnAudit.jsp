<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.bonus.core.DateTimeHelper"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<link rel="stylesheet" href="${bonuspath}/static/plugins/bootstrap/css/bootstrap-select.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script src="${bonuspath}/static/plugins/bootstrap/js/bootstrap-select.js"></script>
<%
	String currentDate = DateTimeHelper.getNowDate();
%>
<style type="text/css">
	.filter-option{
		color:grey;
	}
	.btn-group>.btn, .btn-group+.btn{
		border-width:1px;
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
								&emsp;&nbsp;<font>退料日期：</font> 
								<input class="datainp" id="startTime" name="startTime" value="<%=currentDate %>" readonly placeholder="只显示年月" type="text"/>
								<input class="datainp" id="endTime" name="endTime" value="<%=currentDate %>" readonly placeholder="只显示年月" type="text"/>
								<input type="text" id="keyword" name="keyword" placeholder="这里输入关键词" class="input-large" style="height: 30px; margin: 0px">			
								<select name="isExamine" id="isExamine">
								<option value="0">--审核情况选择--</option>
								<option value="0">未审核</option>
								<option value="1">已通过</option>
								<option value="2">未通过</option>
							</select>	
								
								
								<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)">
									<i class="icon-search bigger-110 icon-only"></i>
								</button>
								<button id='checkBtn' class="btn btn-warning btn-sm" title="批量审核" type="button" onclick="batchAudit()">批量审核
								</button>
							</div>				
						</div>
						<input type='hidden' class='pageNum' name='pageNum' value='1'/>
						<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
						<iframe name="downloadFrame" style="display: none;" frameborder="0"></iframe>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:4%"  class="center">
									<label>
										<input type="checkbox" style="">
									</label>
								</th>
								<th style="width:4%"  class="center hidden-480">序号</th>
								<th style="width:8%" class="center">协议编号</th>
								<th style="width:12%" class="center">租赁单位</th>
								<th style="width:12%" class="center">工程名称</th>
								<th style="width:10%" class="center">分公司名称</th>
								<th style="width:8%" class="center">退料单号</th>
								<th style="width:6%" class="center">机具类型</th>
								<th style="width:5%" class="center">机具规格</th>
								<th style="width:5%" class="center">机具数量</th>
								<th style="width:5%" class="center">退料人</th>
								<th style="width:5%" class="center">检验人</th>
								<th style="width:9%" class="center">申请时间</th>
								<th style="width:8%" class="center">是否审核</th>
								<th style="width:8%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-3">
						</div>
						<div class="col-sm-9">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<%@include file="taskform.jsp" %>	
			<%@include file="remarkForm.jsp" %> 
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
	<script src="${bonuspath}/static/js/rm/returnAudit.js?v=1"></script>	
</html>