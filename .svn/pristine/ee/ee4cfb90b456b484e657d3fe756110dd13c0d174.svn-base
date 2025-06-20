<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
<style type="text/css">
	.orgTree {
		width:100% !important;
		height:500px !important;
		background: none repeat scroll 0 0 #fff !important;
		border:1px solid #ddd !important
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
						&emsp;&nbsp;<font>任务状态：</font>
							<select id="isFinish" name="isFinishs">
									<option  selected="selected" value="0">待办</option>
									<option  value="1">已办</option>
							</select>
							<input type="text" id="keyWord" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
							<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						</div>			
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<!-- <th style="width:10%" class="center">单位名称</th>
								<th style="width:15%" class="center">工程名称</th>
								<th style="width:10%" class="center">任务单号</th> -->
								<th style="width:15%" class="center">任务名称</th>
								<th style="width:10%" class="center">创建时间</th>
								<th style="width:10%" class="center">任务单号</th>
								<th style="width:10%" class="center">创建人</th>
								<th style="width:10%" class="center">任务情况</th>
								<!-- <th style="width:10%" class="center">操作</th> -->
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
						</div>
						<div class="col-sm-8">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap" >
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="unFinishWorkListDetails.jsp" %>
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script type="text/javascript">
function edit(id,definitionId){
	cleanForm();
    $.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/rm/taskRecord/findUnFinishContentDetails',
		data : {
			id:id,
			definitionId: definitionId
		},
		success : function(data) {
			JY.Model.check("auDiv");
			var list = data[0];
			console.log("##########");
			console.log(list);
			
			console.log("##########");
			assignment(list);
		},
		error : function(e) {
		},
		dataType : 'json'
	});
}
$(function() {
	getbaseList(1);
	$("#keyWord").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
});

function search(){
	$("#search").trigger("click");
}

function getbaseList(init) {
	var isFinish =$("#isFinish").val();
	var data = {pageNum:init};
	data = {pageNum:init,pageSize:10 };
	var keyWord = $("#keyWord").val();
	console.log("keyw=",keyWord);
	if(JY.Object.notNull(keyWord)){
		data.keyWord = keyWord;
	}
	data = Object.assign(data,obj);
	JY.Model.loading();
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/rm/taskRecord/findUnFinishContent?isFinish='+isFinish, 
        data: JSON.stringify(data ),      
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
function cleanForm() {
	$("#auForm input[id$='unitName']").val("");
	$("#auForm input[id$='projectName']").val("");
	$("#auForm input[id$='taskName']").val("");
	$("#auForm input[id$='agreementCode']").val("");

	$("#auForm input[id$='personName']").val("");
	$("#auForm input[id$='createTime']").val("");
	$("#auForm input[id$='testLoad']").val("");
	$("#auForm input[id$='holdingTime']").val("");
	$("#auForm input[id$='operaTionName']").val("");
}
function assignment(data){
	$("#auForm input[id$='unitName']").val(data.unitName);
	$("#auForm input[id$='projectName']").val(data.projectName);
	$("#auForm input[id$='taskName']").val(data.taskName);
	$("#auForm input[id$='agreementCode']").val(data.agreementCode);

	$("#auForm input[id$='personName']").val(data.personName);
	$("#auForm input[id$='createTime']").val(data.createTime);
	$("#auForm input[id$='testLoad']").val(data.testLoad);
	$("#auForm input[id$='holdingTime']").val(data.holdingTime);
	$("#auForm input[id$='operaTionName']").val(data.operaTionName);
}
</script>		
</body>
</html>