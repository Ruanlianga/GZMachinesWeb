<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp"%>
<%@include file="../systemset.jsp"%>
<script type="text/javascript" src="${bonuspath}/static/js/Print.js"></script>
<link rel="stylesheet"
	href="${bonuspath}/static/js/layui-v2.9.18/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui-v2.9.18/layui/layui.js"></script>
<title></title>
<style type="text/css">
.layout {
	display: flex;
	justify-content: center;
	align-items: center;
}

#main-box {
	width: 100%;
	height: 100%;
	padding: 0 2% 0 2%;
	box-sizing: border-box;
}

#title {
	width: 100%;
	height: 40px;
	justify-content: flex-start;
	color: red;
	font-size: 18px;
}

#search-form {
	width: 100%;
	height: 60px;
}

#table-box {
	width: 100%;
	height: calc(100% - 130px);
}
</style>
</head>
<body id="body">
	<div id="main-box">
		<div id="title" class="layout">
			<p id="nowDate"></p>
		</div>
		<div id="search-form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 100px;">工程名称</label>
					<div class="layui-input-inline" style="width: 300px;">
						<input type="text" name="proName" id="proName" autocomplete="off"
							class="layui-input" lay-affix="clear" placeholder="请输入工程名称"
							maxlength="30">
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn layui-bg-blue" onclick="queryTable(1)">
						<i class="layui-icon"></i> 查 询
					</button>
					<button class="layui-btn layui-btn-primary" onclick="queryTable(2)">
						<i class="layui-icon layui-icon-refresh"></i> 重 置
					</button>
					<button class="layui-btn layui-bg-blue" onclick="openDeteil({})">
						 全 部 数 据
					</button>
				</div>
			</div>
		</div>
		<div class="table-box" table-responsive style="z-index: 1;">
			<table class="layui-hide" id="currentTableId"
				lay-filter="currentTableId2"></table>
		</div>
	</div>
</body>
<script src="${bonuspath}/static/js/index/calendarDetail.js?v=1"></script>
</html>