<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>

<style type="text/css">
	.bigger-140{
		font-size:180%;
	}
</style>

</head>
<body>
<div class="page-content">

		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">
			    <span style="cursor: pointer;" onclick="unitTree();">选择单位
			    	<i class='icon-zoom-in color-p bigger-120'></i>
			    </span>
				<input type="hidden" id="unitId" name="unitId" value="0" >
				<input type="text" id="unitName" name="unitName" />
			 	
			 	<span style="cursor: pointer;" onclick="projectTree();">工程名称
			 		<i class='icon-zoom-in color-p bigger-120'></i>
			 	</span>
			 	<input type="hidden" id="projectId" name="projectId" value="0" class="FormElement ui-widget-content ui-corner-all">
				<input type="text" id="projectName" name="projectName" class="lrspace3 aBtnNoTD" data-toggle="modal"/>  
				
				<span style="cursor: pointer;" onclick="findAgreeCode();">协议号：</span>
				<input type="text"  id="agreementCode" name="agreementCode"> 
				
			 	是否已审核：<select name="isSettlement">
										<option value="1">未审核</option>
										<option value="0">已审核</option>
				</select>		 
				&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
			</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:5%" class="center">序号</th>
								<th style="width:10%" class="center">协议号</th>
								<th style="width:10%" class="center">工程名称</th>
								<th style="width:10%" class="center">承租单位</th>
								<th style="width:10%" class="center">是否已审核</th>
								<th style="width:10%" class="center">审核时间</th>
								<th style="width:10%" class="center">申请批次</th>
								<th style="width:10%" class="center">结算费用(元)</th>
								<th style="width:10%" class="center">扣减费用(元)</th>
								<th style="width:10%" class="center">备注</th>
								<th style="width:5%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-3">
							<div class="dataTables_info customBtn" >
							</div>
						</div>
						<div class="col-sm-9">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #addorUpdateFrom -->
		<%-- 	<%@include file="paidSettlementReview_form.jsp" %> --%>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/settlement/settlementAudit.js?v=1"></script>		
</body>
</html>