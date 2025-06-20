<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.bonus.core.DateTimeHelper"%>
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
<%
	String nowTime = DateTimeHelper.getNowDate();
%>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							&emsp;&nbsp;二维码生成日期 ：
							<input type="text" readonly name="startTime" value="<%=nowTime %>" id="startTime">
							<input type="text" readonly name="endTime" value="<%=nowTime %>" id="endTime">
							<input type="text" name="keyWord" id="keyWord" placeholder="请输入关键字">
							绑定状态：
							<select id="isBind" name="isBind">
								<option value="-1">全部</option>
								<option value="0">未绑定</option>
								<option value="1">已绑定</option>
							</select>
							<button class="btn btn-warning btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button class="btn btn-success btn-sm" title="新增" type="button" onclick="add()">新增</button>
							<!-- <button class="btn btn-info btn-sm" title="生成" type="button" onclick="produce()">生成</button> -->
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:5%"  class="center hidden-480">序号</th>
							<th style="width:10%" class="center">二维码编码</th>
							<th style="width:8%" class="center">设备名称</th>
							<th style="width:8%" class="center">设备型号</th>
							<th style="width:10%" class="center">设备编码</th>
							<th style="width:10%" class="center">生产厂家</th>
							<th style="width:10%" class="center">分公司名称</th>
							<th style="width:10%" class="center">绑定日期</th>
							<th style="width:10%" class="center">备注</th>
							<th style="width:5%" class="center">绑定状态</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-2">
						<div class="dataTables_info customBtn" >
							&emsp;<a href="#" title="下载"  class="lrspace3" id="download" ><i class='icon-download color bigger-220'></i></a> 
						</div>					
						<!-- <a href="#" title="保存" id="saveBtn" class="lrspace3" ><i class='icon-check color-red bigger-220'></i></a> -->
					
						<!-- <button class="btn btn-info btn-sm" title="下载" type="button" onclick="download()">下载</button> -->
					</div>
					<div class="col-sm-10">
						<!--设置分页位置-->
						<div id="pageing" class="dataTables_paginate paging_bootstrap">
							<ul class="pagination"></ul>
						</div>
					</div>
				</div>
				<%@include file="qrcodeform.jsp" %>
				<!-- #dialog-confirm -->
				<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/qrcode.js?v=1"></script>		
</body>
</html>