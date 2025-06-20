<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../baseset.jsp"%>
<%@include file="../../systemset.jsp"%>
<link rel="stylesheet"
	href="${bonuspath}/static/js/layui-v2.9.18/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui-v2.9.18/layui/layui.js"></script>

<title>审核</title>

<style type="text/css">
#main-box {
	width: 100%;
	height: 100%;
}

.layui-form-label {
	width: 120px;
}

.layui-form-item .layui-input-inline {
	width: 250px;
}

.required_icon, th span {
	font-size: 16px;
	color: red;
	margin: 0 5px 0 5px
}

.btn-box {
	width: 100%;
	height: 80px;
	box-sizing: border-box;
	display: flex;
	justify-content: end;
	align-items: center;
	padding-right: 4%;
}
</style>
</head>
<body>
	<div id="main-box">
		<form class="layui-form" onsubmit="return false;"
			onclick="return false;">
			<div class="layui-form-item" style="margin-top: 1%;">
				<label class="layui-form-label"><span class="required_icon">*</span>审核结果</label>
				<div class="layui-input-block">
					<input type="radio" lay-filter="auditStatus" name="auditStatus" value="2" title="通过" checked>
					<input type="radio" lay-filter="auditStatus" name="auditStatus" value="3" title="不通过">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" id="auditRemarksLabel">审核意见</label>
				<div class="layui-input-inline" style="width: 250px;">
					<textarea placeholder="不通过必须填写审核意见"
						id="auditRemarks" name="auditRemarks" class="layui-textarea"
						maxLength="500"></textarea>
				</div>
			</div>
			<button type="submit" id="formSubmit" class="layui-btn" lay-submit=""
				lay-filter="formData" style="display: none;"></button>
		</form>
		<div class="btn-box">

			<button class="layui-btn layui-btn-primary cancel"
				onclick="closePage()">取消</button>
			<button class="layui-btn layui-btn-normal save" onclick="saveData2()">确定</button>
		</div>
	</div>
</body>
<script src="${bonuspath}/static/js/demandPlan/child/audit_form.js?v=1"></script>
</html>