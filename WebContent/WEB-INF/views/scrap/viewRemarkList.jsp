<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
</head>
<body>

 <div>
          <h1 class="center">不通过原因</h1>
</div>
<div id="ulDiv" >
          <textarea style="width: 100% ;font-size:30px;height:150px;margin-top:20px" id="remark"></textarea>
 </div>

<script src="${bonuspath}/static/js/scrap/viewRemark.js?v=1"></script>		
</body>
</html>