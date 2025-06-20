<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%@include file="../webPortalCommonSet.jsp" %>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jQuery.print.js"></script>>
<title></title>

<style type="text/css">
	#baseInfo{
		/* border:1px solid; */
		text-align: center;
		width:96%;
		margin:0 2% 0 2%;
	}
	
	#baseInfo tr{
		height: 3em;
	}
	
	#baseInfo tr>input{
		width:96%;
	}
	#machineTable th{
		padding:5px;
		vertical-align:middle;
	}

	#bigTable td{
		border: solid 1px black;
	}
	
	#machineTable th{
		border: solid 1px black;
	}

	#machineTable td{
		border: solid 1px black;
	}

	.inp{
		width:100%;
	}
	
	.inp2{
		width:68%;
	}
	
	.ti th{
		font-size: 1.2em;
		font-weight: 1000;
	}
	
	.ti td{
		font-size: 1.2em;
		font-weight: 1000;
	}
	
	#img{
		width:100%;
		height:150px;
	}
	.img{
		z-index:1;
		width:100px;
		height:100px;
		border:solid 1px black;
	}
	.img_div{
		display: inline-block;
		padding:10px;
	}
	.status{
		width:8%;
		position: absolute;
		z-index:999;
		top: 20%;
		left: 50%;
		opacity: 1;
	}

.t{
	hidden:hidden;
}

.valid[readonly]{
		background-color: #ed9d9d !important;
		color: black !important;
	}
	
</style>
</head>
<body>
<div id = "printTable">
	<table  id="baseInfo" cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody class="a">
		
		
			<!-- <input type="hidden" name="leaseType.id" value="37">
			<input type="hidden" name="status.id" value="46"> -->
			<tr style="height:2em;">
				<td colspan="8" style="font-size: 1.5em;"></td>
			</tr>
			<tr style="height:2em;">
				<td align="center" style="font-size: 1.5em;">工程租赁结算清单</td>
			</tr>
			<tr>
				<td>
					<h6>${bean.sltDate}</h6>
				</td>
			</tr>
			<tr style="height:2em;">
				<td colspan="8" style="font-size: 1em;" align="right"></td>
			</tr>
			<tr style="height:2em;">
				<td colspan="8">
					<table id="bigTable"  border="1" >
						<tr class="ti">
							<td style="width:5%" class="center">序号</td>
							<td style="width:20%" class="center">项目</td>
							<td style="width:20%" class="center">工器具租赁费用</td>
							<td style="width:20%" class="center">机械设备租赁费用</td>
							<td style="width:20%" class="center">共计租赁费用</td>
							<td style="width:15%" class="center">备注</td>
						</tr>
					
							<c:forEach items="${baseList}" var="l" varStatus="idx">
						<tr>
							<td style="width:5%" class="center">${idx.index+1}</td>
							<td style="width:20%" class="center">${l.project.name}</td>
							<td style="width:20%" class="center">${l.typeMoney}</td>
							<td style="width:20%" class="center">${l.machineMoney}</td>
							<td style="width:20%" class="center">${l.lastMoney}</td>
							<td style="width:15%" class="center"></td>
						</tr>
							</c:forEach>
					
			
						<tr>
							<td style="width:5%" class="center"></td>
							<td style="width:20%" class="center">共计</td>
							<td style="width:20%" class="center">${bean.typeMoney}</td>
							<td style="width:20%" class="center">${bean.machineMoney}</td>
							<td style="width:20%" class="center">${bean.lastMoney}</td>
							<td style="width:15%" class="center"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="height:2em;">
				<td colspan="8" style="font-size: 1.5em;"></td>
			</tr>
			<tr style="height:2em;">
				<td colspan="8"  align="center" style="font-size: 1.5em;">工器具、设备结算明细清单</td>
			</tr>
			<tr>
				<td colspan="8">
					<table id="machineTable" border="1"  >
					<thead>
						<tr class="ti">
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:12%" class="center">租赁单位</th>
							<th style="width:12%" class='center'>工程名称</th>
							<th style="width:10%" class="center">机具名称</th>
							<th style="width:10%" class="center">规格型号</th>
							<th style="width:5%" class="center">资产</th>
							<th style="width:5%" class="center">单位</th>
							<th style="width:5%" class="center">数量</th>
							<th style="width:10%" class="center">起租日期</th>
							<th style="width:10%" class="center">退还日期</th>
							<th style="width:10%" class="center">上次结算日期</th>
							<th style="width:5%" class="center">天数</th>
							<th style="width:10%" class='center'>金额</th>
							
							
						</tr>
					</thead>
					<tbody id="list">
					<c:forEach items="${maList}" var="l" varStatus="idx">
						<c:forEach items="${l.mas}" var="ma" varStatus="idx">
							<tr>
								<td  class="center"  >${idx.index+1}</td>
								<td  class="center"  >${l.company.name}</td>
								<td  class="center"  >${l.project.name}</td>
								<td  class="center"  >${ma.type.parentName}</td>
								<td  class="center"  >${ma.type.name}</td>
								<td class="center"   >${ma.type.isFixedAssets}</td>
								<td  class="center"  >${ma.type.unit}</td>
								<td  class="center"  >${ma.num}</td>
								<td  class="center"  >${ma.startDate}</td>
								<td  class="center"  >${ma.backDate}</td>
								<td  class="center"  >${ma.endDate}</td>
								<td  class="center"  >${ma.dayLen}</td>
								<td  class="center"  >${ma.subtotal}</td>
								
							</tr>
						</c:forEach>
						</c:forEach>
					</tbody>
					</table>
				</td>
			</tr>
			<tr style="height:2em;">
				<td colspan="8" style="font-size: 1.5em;"></td>
			</tr>
			<tr style="height:2em;">
				<td colspan="8" style="font-size: 1.5em;"></td>
			</tr>
		</tbody>
	</table>
</div>


<script type="text/javascript">

</script>
</body>
</html>