<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<div class="row">
						<ul id="orgTree" class="ztree"></ul>
					</div>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/sys/org.js?v=1"></script>		
</body>
</html>