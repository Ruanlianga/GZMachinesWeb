<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<style type="text/css">
	.orgTree {
		width:100% !important;
		height:500px !important;
		background: none repeat scroll 0 0 #fff !important;
		border:1px solid #ddd !important
	}
</style>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<div class="row">
						<div class="col-sm-3">
							<ul id="orgTree" class="ztree orgTree"></ul>
						</div>
						<div class="col-sm-9">
							<div class="row page-header">
								<h1>&nbsp;库管员&nbsp;<small><i class="icon-double-angle-right"></i><span id='roleVisName'>请选择库管员</span></small></h1>
							</div>
							<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
								<div class="row">
									<div class="widget-main">	
										<font>&emsp;&nbsp;</font><input type="text" style="height: 30px;margin: 0px" name="keyWord" placeholder="这里输入关键词" class="input-large">			
										<button id='searchBtn' style="height: 30px;margin: 0px" class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
										<button type="button" style="height: 30px;margin: 0px" class="btn btn-sm btn-success" onclick="save();">保存</button>
									</div>
								</div>
								<input type='hidden' id="roleId" name='roleId' value='0'/>
							</form>
							<table id="baseTable" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th style="width:5%" class="center">
											<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
										</th>
										<th style="width:10%"  class="center hidden-480">序号</th>
										<th style="width:20%" class="center">大类</th>
										<th style="width:20%" class="center hidden-480">中类</th>
										<th style="width:20%" class="center">小类</th>
										<th style="width:20%" class="center hidden-480">规格型号</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/keeperType.js?v=1"></script>		
</body>
</html>