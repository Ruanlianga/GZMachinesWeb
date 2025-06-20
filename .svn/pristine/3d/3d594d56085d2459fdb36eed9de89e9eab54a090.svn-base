<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>

<%-- <link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script> --%>
<%-- <script src="${bonuspath}/static/plugins/jedate/jedate.js"></script> --%>



<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<style type="text/css">
	.bigger-140{
		font-size:180%;
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
				<span >关键字:</span>
				<input type="text"  id="keyWord" name="keyWord" /> 
				<span style="cursor: pointer;" onclick="unitTree();">施工单位：</span>
							<input type="hidden" id="unitId" name="unitId" value="0" >
							<input type="text" id="unitName" name="unitName" />
						 	<span style="cursor: pointer;" onclick="projectTree();">工程名称：</span>
						 	<input type="hidden" id="projectId" name="projectId" value="0" class="FormElement ui-widget-content ui-corner-all">
							<input type="text" id="projectName" name="projectName" class="lrspace3 aBtnNoTD" data-toggle="modal"/>  
				设备名称 <input type="text"  id="maName" name="maName" /> 
				设备规格 <input type="text"  id="maType" name="maType" /> 
				
			 	是否在用：<select id="scope" name="scope" >
			 							<option value="">--请选择--</option>
										<option value="1">---是---</option>
										<option value="2">---否---</option>
				           </select>		 
				&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="submit" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
				<button class="btn btn-danger btn-sm" onclick="alter()" >一键修改</button>
			</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%" class="center">序号</th>
								<th style="width:10%" class="center">协议号</th>
								<th style="width:15%" class="center">工程名称</th>
								<th style="width:10%" class="center">施工单位</th>
								<th style="width:10%" class="center">设备状态</th>
								<th style="width:10%" class="center">设备名称</th>
								<th style="width:10%" class="center">设备规格</th>
								<th style="width:10%" class="center">设备编码</th>
								<th style="width:10%" class="center">下次检验时间</th>
								<th style="width:10%" class="center">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table> 
					<div class="row">
						<div class="col-sm-3">
							<div class="dataTables_info customBtn" >
							</div>
						</div>
						<div class="col-sm-9">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>
			<%@include file ="./alterdetail.jsp" %>
			<%@include file ="./inform.jsp" %>
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/warningManage/checkWarning.js?v=1"></script>		
</body>
</html>