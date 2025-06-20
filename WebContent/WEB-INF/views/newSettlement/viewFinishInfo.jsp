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
				<td align="center" style="font-size: 1.5em;">工程完结清单</td>
			</tr>
			<tr>
				<td>
					<h6>${bean.sltDate}</h6>
				</td>
			</tr>
		
		
			<tr style="height:2em;">
				<td colspan="8" style="font-size: 1.5em;">${bean.unitName}</td>
			</tr>
			
			<tr style="height:2em;">
				<td colspan="8"   style="font-size: 1.5em;">${bean.projectName}</td>
			</tr>
			
			<tr>
				<td colspan="8">
					<table id="machineTable" border="1"  >
					<thead>
						<tr class="ti">
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:10%" class="center">机具名称</th>
							<th style="width:10%" class="center">规格型号</th>
							<th style="width:5%" class="center">单位</th>
							<th style="width:5%" class="center">数量</th>
							<th style="width:5%" class="center">设备编号</th>
							<th style="width:10%" class="center">领料日期</th>
							<th style="width:10%" class="center">退料日期</th>
							<th style="width:10%" class='center'>备注</th>
						</tr>
					</thead>
					<tbody id="list">
					<c:forEach items="${bean.mas}" var="l" varStatus="idx">
							<tr>
								<td  class="center"  >${idx.index+1}</td>
								<td  class="center"  >${l.deviceName}</td>
								<td  class="center"  >${l.deviceModel}</td>
								<td class="center"   >${l.deviceUnit}</td>
								<td  class="center"  >${l.num}</td>
								<td  class="center"  >${l.deviceCode}</td>
								<td  class="center"  >${l.startDate}</td>
								<td  class="center"  >${l.backDate}</td>
								<td  class="center"  >${l.remarks1}</td>
								
							</tr>
					
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