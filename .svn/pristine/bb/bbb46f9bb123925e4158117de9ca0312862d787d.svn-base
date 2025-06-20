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
								<h1>&nbsp;角色&nbsp;<small><i class="icon-double-angle-right"></i><span id='roleVisName'>请选择角色</span></small></h1>
							</div>
							<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
								<div class="row">
									<div class="widget-main">	
										<input type="text" name="keyWord" placeholder="这里输入关键词"   class="input-large">			
										&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
									</div>
								</div>
								<input type='hidden' id="roleId" name='roleId' value='0'/>
								<a href="#" title="保存" id="saveBtn" class="lrspace3" ><i class='icon-check color-red bigger-220'></i></a>
							</form>
							<table id="baseTable" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th style="width:3%" class="center">
											<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
										</th>
										<th style="width:5%"  class="center hidden-480">序号</th>
										<th style="width:10%" class="center">登录名</th>
										<th style="width:10%" class="center hidden-480">用户名</th>
										<th style="width:15%"  class="center ">单位部门</th>
										<th style="width:15%"  class="center ">电话号码</th>
										<th style="width:15%" class="center hidden-480"><i class="icon-envelope-alt  bigger-110 hidden-480"></i>电子邮箱</th>
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
<script src="${bonuspath}/static/js/sys/role.js?v=1"></script>		
</body>
</html>