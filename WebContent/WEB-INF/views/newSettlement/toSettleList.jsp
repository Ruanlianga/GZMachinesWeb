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
							<%-- <input type="text" readonly="readonly" value="<%=startTime%>" class="input-large" id="startTime" name="startTime" >
							<input type="text" readonly="readonly" value="<%=endTime%>" class="input-large" id="endTime" name="endTime" >  
							<input type="text" id="maType" name="maType" placeholder="这里输入机具名称"   class="input-large">
							<input type="text" id="maModel" name="maModel" placeholder="这里输入规格型号"   class="input-large">	 --%>
							<input type="text" id="keyWord" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%" class="center">
									<label>
										<input type="checkbox" id="checkAll">
									</label>
								</th>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:20%" class="center">结算工程</th>
								<th style="width:20%" class="center">结算单位</th>
								<th style="width:15%" class="center">工程协议</th>
								<th style="width:15%" class="center">结算状态</th>
								<th style="width:20%" class="center">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
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
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script type="text/javascript">

$(function(){
	getbaseList(1);
	$("#keyWord").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
});

/**
 * @author 守约
 * @date 2020-10-16
 * @function 条件查询
 * @returns 
 */
function search(){
	$("#search").trigger("click")
}

/**
 * @author 守约
 * @date 2020-10-16
 * @function 初始化页面ajax请求 返回html页面
 * @returns  
 */
function getbaseList(init){
	var data = {pageNum:init};
	data = {pageNum:init,pageSize:10};
	var keyWord = $("#keyWord").val();
	console.log("keyw=",keyWord);
	if(JY.Object.notNull(keyWord)){
		data.keyWord = keyWord;
	}
	data = Object.assign(data,obj);
	JY.Model.loading();
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/projectSettlement/findSettleContent', 
        data: JSON.stringify(data),      
        dataType:"html",
        contentType:"application/json",
        success:function(data){
        	$("#baseTable tbody").html(data);
        	JY.Model.loadingClose();
		},
		error:function(data){
			console.log("ajax请求错误!");
		}
	})
}
</script>
</body>
</html>