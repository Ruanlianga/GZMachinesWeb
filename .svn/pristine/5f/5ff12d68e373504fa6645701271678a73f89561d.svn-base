<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />

<style type="text/css">
	.roleTree {
		width:100% !important;
		height:500px !important;
		background: none repeat scroll 0 0 #fff !important;
		border:1px solid #ddd !important
	}
	
	.resTree {
		width:100% !important;
		height:400px !important;
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
							<ul id="roleTree" class="ztree roleTree"></ul>
						</div>
						<div class="col-sm-9">
							<div class="row page-header">
								<h1>&nbsp;角色&nbsp;<small><i class="icon-double-angle-right"></i><span id='roleVisName'>请选择角色</span></small></h1>
							</div>
							<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
								<input type='hidden' id="roleId" name='roleId' value='0'/>
								<button id="saveBtn" class="btn btn-primary  btn-sm" title="保存" type="button">保存</button>
								<!-- <a href="#" title="保存" id="saveBtn" class="lrspace3" ><i class='icon-check color-red bigger-220'></i></a> -->
							</form>
							<ul id="resTree" class="ztree resTree"></ul>
						</div>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/sys/roleres.js?v=1"></script>		
</body>
</html>