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
		font-size:140%;
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
				&nbsp;&nbsp;
			    <font color="red">*</font><span style="cursor: pointer;" onclick="unitTree();">选择单位：</span>
				<input type="hidden" id="unitId" name="leaseCompany" value="0" >
				<input type="text" jyValidate="required" id="unitName" name="unitName" />
			 	
			 	<font color="red">*</font><span style="cursor: pointer;" onclick="projectTree();">工程名称：</span>
			 	<input type="hidden" id="projectId" name="projectName" value="0" class="FormElement ui-widget-content ui-corner-all">
				<input type="text" id="projectName" name="projectName" class="lrspace3 aBtnNoTD" data-toggle="modal"/>  
				
				<font color="red">*</font><span style="cursor: pointer;" onclick="findAgreeCode();">协议号：</span>
				<input type="text" jyValidate="required"  id="agreementCode" name="agreementCode">
			 	是否已结算：<select name="isSettlement">
						<option value="1">未结算</option>
						<option value="0">已结算</option>
				</select>	
				<button title="重置" class="btn btn-sm btn-error" onclick="getbaseList(2)">重置</button>	 
				<button id='searchBtn' class="btn btn-warning  btn-sm" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
			</div>				
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:6%" class="center">序号</th>
								<th style="width:20%" class="center">协议号</th>
								<th style="width:20%" class="center">工程名称</th>
								<th style="width:20%" class="center">承租单位</th>
								<th style="width:15%" class="center">是否已结算</th>
								<th style="width:10%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<hr />
			<!-- 	<table id="paidSettlement1" class="table ">
			
				<tr>
					<td colspan="6" style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">本工程清算项目及金额（元）</td>
				</tr>

				<tr>
					<td colspan="4" style="height:40px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;一、施工机具有偿使用费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="leaseTotal"></span></td>
				</tr>

				<tr>
					<td colspan="4" style="height:40px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;二、施工机具维修费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="repairTotal"></span></td>
				</tr>
				<tr>
					<td colspan="4" style="height:40px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;三、施工机具丢失费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="loseTotal"></span></td>
				</tr>

				<tr>
					<td colspan="4" style="height:40px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;四、施工机具损坏赔偿费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="scarpTotal"></span></td>
				</tr>

				<tr>
					<td colspan="2" style="height:40px;width:25%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">费用合计金额(大写) ;</td>
					<td colspan="2" style="text-align:center;width:35%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="dxTotal"></span></td>
					<td colspan="2" style="text-align:center;width:30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="total"></span></td>
				</tr>
			
				<tr>
					<td colspan="2" style="height:45px;width:25%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">工程投入总额(大写) ;</td>
					<td colspan="2" style="text-align:center;width:35%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span id="dxTotal"></span></td>
					<td colspan="2" style="text-align:center;width:30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="total"></span></td>
				</tr>
			
				<tr>
					<td style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">说明</td>
					<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">本协议一式三份，甲方一份，乙方一份，经双方签字后生效。</td>
				</tr>
				<tr>
					<td style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:SimSun">备注</td>
					<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:SimSun">此费用仅为在机具设备分公司发生费用，未计从项目部领用机具费用。</td>
				</tr>
				
			</table> -->
					
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
			<%@include file="settlement_form.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
<script src="${bonuspath}/static/js/settlement/settlement.js?v=1"></script>		
</body>
</html>