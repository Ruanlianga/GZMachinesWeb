<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp"%>
<%@include file="../systemset.jsp"%>
<%
	String nowTime = DateTimeHelper.getNowDate();
%>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/plugins/bootstrap/css/bootstrap-select.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<style type="text/css">
	.filter-option {
		color: grey;
	}
	
	.btn-group>.btn, .btn-group+.btn {
		border-width: 1px;
	}
	
	.inputText{
		width: 220px;
		height: 34px;
	}
	
	.inputBtn{
		width: 100px;
		height: 35px;
		border:none;
		border-radius:3px;
		background-color: #4CD964;
		color: white;
	}
</style>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">
							&emsp;&nbsp;<button id="hisAgreement" class="btn btn-danger  btn-sm" title="已签协议" type="button" onclick="getHisAgreen()">已签协议查看</button>
							&emsp;&nbsp;<hr>
						</div>
					</div>
				</form>
				<font color="red">*</font><span>协议编号：</span>
				<input class="inputText" jyValidate="required" id="code" name="code" type="text" readonly="readonly"><br /><br />
				
				<font color="red">*</font><span style="cursor: pointer;" onclick="unitTree();">租赁单位：</span>
				<input type="text" jyValidate="required" id="unitName" style="width: 36%;" class="FormElement ui-widget-content ui-corner-all"/>
				<input type="hidden" id="unitId" name="leaseCompany" value="0" >
				<a href="#" title="过滤" onclick="unitTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a><br /><br />
				
				<font color="red">*</font><span style="cursor: pointer;" onclick="projectTree(); return false;">工程名称：</span><input type="text" jyValidate="required" id="projectName" style="width: 36%;" class="FormElement ui-widget-content ui-corner-all"/>
				<input type="hidden" id="projectId" name="projectName" value="0" >
				<a href="#" title="过滤" onclick="projectTree(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a><br /><br />

				<font color="red">*</font><span>开始日期：</span><div class="layui-input-inline"><input class="inputText" jyValidate="required" type="text" jyValidate="required" readonly class="FormElement ui-widget-content ui-corner-all" id="startTime" name="startTime" > </div>&ensp;&ensp;

				<font color="red">*</font><span>租赁期限：</span><input class="inputText" min = 0 type="number" id="leaseTerm" jyValidate="required" name="leaseTerm">&nbsp;天<br /><br />
				
				<font color="red">*</font><span>授&ensp;权&ensp;人：</span><input class="inputText" type="text" jyValidate="required" id="authorizingPerson" name="authorizingPerson">&ensp;&ensp;
				
				<font color="red">*</font><span>联系方式：</span><input class="inputText" type="text" jyValidate="required" id="authorizingPhone" name="authorizingPhone"><br /><br />
				
				<font color="red">*</font><span>合同编号：</span><input class="inputText" jyValidate="required" type="text" id="contractNumber" name="contractNumber" style="width:15%">
				<a href="#" title="附件上传" onclick="updAgreePic();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-upload color-p bigger-140'></i></a> &ensp;&ensp;
				
				&nbsp;<span>备&emsp;&emsp;注：</span><input class="inputText" type="text" id="remark" name="remark"><br /><br />
				
				<!-- <input class="btn btn-sm btn-success" type="button" value="确定" onclick="checkAgreement()" > -->
				<button type="button" class="btn btn-sm btn-primary" onclick="checkAgreement()">保存</button>
					
				<%@include file="../dialog.jsp"%>
			</div>
		</div>
	</div>
	<script src="${bonuspath}/static/js/lease/agreeIndex.js?v=1"></script>
</body>
</html>