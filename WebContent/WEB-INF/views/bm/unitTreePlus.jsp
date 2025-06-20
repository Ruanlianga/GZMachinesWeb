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
<input style="margin-left:10px;margin-top:5px;" type="text" id="key" placeholder="Search..." oninput="keyChange()" />
<div class="page-content">
		<div class="row-fluid">	
			<ul id="unitTree" class="ztree orgTree"></ul>
		</div>
	</div>
<script src="${bonuspath}/static/js/bm/unitTreePlus.js"></script>		
</body>
</html>