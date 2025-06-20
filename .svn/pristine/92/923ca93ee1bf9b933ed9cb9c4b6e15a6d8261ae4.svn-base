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
		<input onchange="query()" oninput="query()"  type="text" id="search" >
		<button id='searchBtn' class="btn btn-warning btn-sm" title="过滤" type="button" onclick="query()"><i class="icon-search bigger-110 icon-only"></i></button>
		<div class="row-fluid">	
			<ul id="maTypeTree" class="ztree orgTree"></ul>
		</div>
	</div>
<script src="${bonuspath}/static/js/ma/maTypeTree2.js"></script>		
</body>
</html>