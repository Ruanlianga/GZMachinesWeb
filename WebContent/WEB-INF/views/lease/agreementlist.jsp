<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
	String nowDate = DateTimeHelper.getNowDate();
%>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST">
					<div class="row">
						<div class="widget-main">	
							<div class="layui-input-inline">
					       		&emsp;&nbsp;
					       		合同开始日期：<input type="text" value="<%=nowDate%>" readonly class="input-large" id="startTime" name="startTime" >
					       		<input type="text" value="<%=nowDate%>" readonly class="input-large" id="endTime" name="endTime" >
					    	</div>
							<input type="text" name="keyWord" placeholder="这里输入关键词"   class="input-large">
							<select id="isBalance" name="isBalance">
								<option value="1">未结算</option>
								<option value="0">已结算</option>
							</select>			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							
							<button style="float: right;" id="hisAgreement"class="btn btn-danger  btn-sm" title="已签协议" type="button" onclick="backToAgreen()">协议签订</button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					<iframe name="downloadFrame" style="display: none;" frameborder="0"></iframe>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:3%"  class="center hidden-480">序号</th>
							<th style="width:6%" class="center">协议编号</th>
							<th style="width:6%" class="center">合同编号</th>
							<th style="width:6%" class="center">附件图片</th>
							<th style="width:6%" class="center">签订日期</th>
							<th style="width:6%" class="center">租赁单位</th>
							<th style="width:6%" class="center">工程名称</th>
							<th style="width:6%" class="center">开始日期</th>
							<th style="width:5%" class="center">租赁期限(天)</th>
							<th style="width:5%" class="center">授权人</th>
							<th style="width:6%" class="center">授权人电话</th>
							<th style="width:6%" class="center">备注</th>
							<th style="width:5%" class="center">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-2">
						<div class="dataTables_info customBtn" >
							&emsp;<a href="#" title="导出数据"  class="lrspace3" onclick="exportData();" ><i class='icon-download color bigger-220'></i></a> 
						</div>						
						<!-- <button class="btn btn-sm btn-success" type="button" onclick="exportData()">导出数据</button> -->
					</div>
					<div class="col-sm-10">
						<div id="pageing" class="dataTables_paginate paging_bootstrap">
							<ul class="pagination"></ul>
						</div>
					</div>
				</div>
				<%@include file="agreementform.jsp" %>
				<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/lease/agreement.js?v=1"></script>		
</body>
</html>