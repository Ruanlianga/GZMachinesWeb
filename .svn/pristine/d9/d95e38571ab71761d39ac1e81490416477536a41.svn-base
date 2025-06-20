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
<style type="text/css">
	/* .table{
		table-layout: fixed;
	   	word-break: break-all;
	   	width:150%;
	}
	.row-fluid{
		width:150% !important;
	} */
</style>
</head>

<body>
<div class="page-content" >
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							<input type="text" id="machineName" name="machineName" placeholder="这里输入机具名称"   class="input-large">
							<input type="text" id="machineModel" name="machineModel" placeholder="这里输入规格型号"   class="input-large">	
							<input type="text" id="keyWord" name="keyWord" placeholder="这里输入关键词"   class="input-large">
							<select id="isCollectOption" name="isCollectOption" class="input-large">
								<option value="">全部</option>
								<option value="1">我的关注</option>
							</select>				
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button id='resetBtn' class="btn btn-error btn-sm" title="重置" type="button">重置</button>	
							<button type="button" class="btn btn-sm btn-danger" onclick="exportData();">导出数据</button>
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:100px" class="center">机具类型</th>
								<th style="width:100px" class="center">机具名称</th>
								<th style="width:100px" class="center">规格规格</th>
								<th style="width:80px" class="center">在库数量</th>
								<th style="width:80px" class="center">在用数量</th>
								<th style="width:80px" class="center">在修数量</th>
								<th style="width:80px" class="center">在检数量</th>
								<th style="width:80px" class="center">待入库量</th>
								<th style="width:80px" class="center">报废数量</th>
								<th style="width:80px" class="center">丢失数量</th>
								<th style="width:80px" class="center">总保有量</th>
								<th style="width:4%" class="center">是否固资</th>
								<th style="width:160px" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-3">
							<div class="dataTables_info customBtn" >
						<!-- 		 &emsp;<a href="#" title="导出" id="export" class="lrspace3" ><i class='icon-download color bigger-220'></i></a>  -->
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
<script src="${bonuspath}/static/js/sq/machinestatuscollectquery.js?v=1"></script>
<script>
function collectAddMachine(machineId) {
    $.ajax({
        url: bonuspath + "/backstage/machinequery/addOrRemoveCollect",
        type: "POST",
        data: {
            typeId: machineId,
			isCollect: 0
        },
        success: function(response) {
			console.log("response="+JSON.stringify(response));
			layer.msg("收藏成功");
			setTimeout(function(){
				getbaseList();
			},1000);
        },
        error: function() {
            layer.msg("操作失败，请稍后重试");
        }
    });
}
function collectRemoveMachine(machineId) {
    $.ajax({
        url: bonuspath + "/backstage/machinequery/addOrRemoveCollect",
        type: "POST",
        data: {
            typeId: machineId,
			isCollect: 1
        },
        success: function(response) {
			console.log("response="+JSON.stringify(response));
			layer.msg("取消成功");
			setTimeout(function(){
				getbaseList();
			},1000);
        },
        error: function() {
            layer.msg("操作失败，请稍后重试");
        }
    });
}
</script>
</body>
</html>