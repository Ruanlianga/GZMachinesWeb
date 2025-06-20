<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="baseset.jsp" %>
<%@include file="systemset.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>变压器</title>
<style type="text/css">
</style>
<script src="${bonuspath}/static/plugins/echarts/2.2.7/echarts.js"></script>
<script src="${bonuspath}/static/plugins/echarts/2.2.7/echarts-all.js"></script>
</head>
<body>
	<div class="page-content" id="trains" style="width: 850px;height:530px;">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							<!-- <input type="text" name="keyWord" id="indate" placeholder="这里输入关键词"   class="input-large">			
							&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button> -->
						</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					<input type='hidden' name='dataType' id="dataType" />
					<input type='hidden' name='isOverLoad' id="isOverLoad" />
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%" class="center">
									<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
								</th>
								<th style="width:5%"  class="center hidden-480">序号</th>
								<th style="width:10%" class="center">名称</th>
								<th style="width:10%" class="center hidden-480">类型</th>
								<th style="width:5%"  class="center">状态</th>
								<th style="width:10%"  class="center">过载类型</th>
								<th style="width:12%"  class="center ">过载程度</th>
								<th style="width:14%" class="center hidden-480">允许时间</th>
								<th style="width:12%" class="center hidden-480">开始时间</th>
								<th style="width:12%"  class="center hidden-480">持续时间</th>
								<!-- <th style="width:10%" class="center">操作</th> -->
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
								<!-- <a href="#" title="增加" id="addBtn" class="lrspace3" ><i class='icon-plus-sign color bigger-220'></i></a>
								<a href="#" title="批量删除" id="delBatchBtn" class="lrspace3" ><i class='icon-trash color-red bigger-220'></i></a> -->
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
		 <%-- 	<%@include file="overloadtran_form.jsp" %> --%>
			<!-- #dialog-confirm -->
			<%@include file="dialog.jsp" %>	
			</div>
		</div>
	</div>
</body>
<script src="openDetails.js"></script>
<script type="text/javascript">

</script>
</html>