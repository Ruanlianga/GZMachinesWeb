<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<meta name="renderer" content="webkit">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
</head>
<body>
<div class="page-content" >
		<div class="row-fluid">	
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							<font>通知内容：</font><textarea style="width: 80%;" rows="1" id="noticeContent"></textarea>
							<button type="button" style="height: 30px; margin: 0px" class="btn btn-sm btn-success" onclick="notice();">通知</button>
						</div>
					</div>
				</form>
				<table id="personTable" class="table table-striped table-bordered table-hover" style="margin-top: 2%;" >
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label>
									<input type="checkbox" class="ace">
									<span class="lbl"></span>
								</label>
							</th>
							<th style="width:3%"  class="center hidden-480">序号</th>
							<th style="width:5%" class="center">部门</th>
							<th style="width:5%" class="center">姓名</th>
							<th style="width:5%" class="center">职责</th>
							<th style="width:5%" class="center">联系电话</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/newInput/selectPerson.js"></script>		
</body>
</html>