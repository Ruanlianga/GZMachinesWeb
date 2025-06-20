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
	String nowTime = DateTimeHelper.getNowDate();
%>

<%
      //创建令牌
      String token = java.util.UUID.randomUUID().toString();
      //存在session中一份,以后做判断
      session.setAttribute("TOKEN_IN_SESSION", token);
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
						<input type="hidden" id="token" name="token" value="<%=token%>"/>	
							<input type="text" name="keyWord" placeholder="这里输入物资名称"   class="input-large">			
							&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button id="addBtn" class="btn btn-success btn-sm" title="新增" type="button">新增</button>
							<button class="btn btn-fail btn-sm" title="批量确认" type="button" onclick="batchConfirmation()">批量确认</button>
							<button class="btn btn-sm btn-danger" title="批量删除" type="button" onclick="batchDeletion()">批量删除</button>
						
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%" class="center">
									<input type="checkbox">
								</th>
								<th style="width:3%"  class="center hidden-480">序号</th>
								<th style="width:5%" class="center">物资名称</th>
								<th style="width:5%" class="center">规格型号</th>
								<th style="width:5%" class="center">单位</th>
								<th style="width:5%" class="center">租赁单价（元）</th>
								<th style="width:5%" class="center">机具数量</th>
								<th style="width:5%" class="center">已领数量</th>
								<th style="width:5%" class="center">客服代表</th>
								<th style="width:5%" class="center">是否确认</th>
								<!-- <th style="width:5%" class="center">审核状态</th> -->
								<th style="width:5%" class="center">批准状态</th>
								<th style="width:5%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
								<!-- <a href="#" title="批量确认" id="delBatchBtn" class="lrspace3" ><i class='icon-ok color-p bigger-220'></i></a> -->
							</div>
						</div>
						<div class="col-sm-8">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="./receiveDetailsform.jsp" %>
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/lease/receiveDetails.js?v=1"></script>		
</body>
</html>