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
		<input type="hidden" id ="totalId" value="${bean.lastMoney}">
		
			<!-- <input type="hidden" name="leaseType.id" value="37">
			 -->
			<%-- <tr style="height:2em;">
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
			</tr> --%>
			 	<thead style="display:table-header-group;font-weight:bold"></thead>
					<tbody id="aa"></tbody> 
					
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
							<th style="width:15%" class="center">租赁单位</th>
							<th style="width:15%" class='center'>工程名称</th>
							<th style="width:10%" class="center">机具名称</th>
							<th style="width:10%" class="center">规格型号</th>
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
						<th colspan="2" class='center'>合计：</th>
						<th id="dxMoney" colspan="9" class='center'></th>
						<th id="money" colspan="1" class='center'>${bean.lastMoney}</th>
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
function jqprinting1() {
	$("#printTable").jqprint({ operaSupport: false });
}


$(function () {
	var money=$("#totalId").val();
	var dxMoney = DX(money)
	$("#dxMoney").html(dxMoney)

});

function DX(n) {
//	alert(1);
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
        return "数据非法";
    var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
        n += "00";
    var p = n.indexOf('.');
    if (p >= 0)
        n = n.substring(0, p) + n.substr(p+1, 2);
        unit = unit.substr(unit.length - n.length);
    for (var i=0; i < n.length; i++)
        str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    return str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
}
</script>
</body>
</html>