<%@ page contentType="text/html;charset=UTF-8"%>
<div id="auDiv" class="hide auDiv">
	<form id="auForm" method="POST" onsubmit="return false;">
		<table id="paidSettlement" cellpadding="0" border="0" cellspacing="0" style="width:98%;">
				<tr>
					<td colspan="6" style="text-align: center; font-size: 1.3em;font-weight: bold;font-family:SimSun;padding-top: 30px;">
						机具设备有偿使用费结算协议书
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding: 20px 0px 10px 0px;font-family:SimSun"><span id="checkTimes"></span></td>
					<td colspan="2" style="padding: 20px 0px 10px 0px;font-family:SimSun">编号：<span id="checkCodes"></span></td>
				</tr>
				<tr>
					<td style="height:70px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">工程名称：</td>
					<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"><span id="workName" style="text-align: center;"></span></td>
				</tr>

				<tr>
					<td style="height:70px;text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">承租单位：</td>
					<td colspan="3" style="text-align:center;width: 45%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"><span id="companyName" style="text-align: center;"></span></td>
					<td style="text-align:center;width: 10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">日期：</td>
					<td style="text-align:center;width: 20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;"><span id="settlementDate" style="text-align: center;"></span></td>
				</tr>
				<tr>
					<td colspan="6" style="height:70px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">结算项目及金额(元)</td>
				</tr>

				<tr>
					<td colspan="4" style="height:70px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;一、施工机具有偿使用费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="leaseTotal"></span></td>
				</tr>

				<!-- <tr>
					<td colspan="4" style="height:70px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;二、施工机具维修费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="repairTotal"></span></td>
				</tr> -->
				<tr>
					<td colspan="4" style="height:70px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;二、施工机具丢失费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="loseTotal"></span></td>
				</tr>

				<tr>
					<td colspan="4" style="height:70px;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">&emsp;三、施工机具损坏赔偿费：</td>
					<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;border-right: 1px solid #000000;font-family:SimSun"><span class="scrapTotal"></span></td>
				</tr>

				<tr>
					<td colspan="2" style="height:70px;width:25%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">费用合计金额(大写) ;</td>
					<td colspan="2" style="text-align:center;width:35%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="dxTotal"></span></td>
					<td colspan="2" style="text-align:center;width:30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="total"></span></td>
				</tr>
				<tr>
					<td style="height:70px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">说明</td>
					<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">本协议一式三份，甲方一份，乙方一份，经双方签字后生效。</td>
				</tr>
			   <tr>
					<td style="height:70px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:SimSun">备注</td>
				    <td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:SimSun"></td> 
				</tr> 
				<tr>
					<td colspan="2" style="padding-top: 30px;">部门负责人： </td>
					<td colspan="2" style="padding-top: 30px;">承租负责人： </td>
					<td colspan="2" style="padding-top: 30px;">核算员： </td>
				</tr>
			</table>
			<button  onClick="jqprinting()">打印</button>
	</form>
	<script type="text/javascript">
		function jqprinting() {
			$("#paidSettlement").jqprint({ operaSupport: false });
		}
	</script>
</div>
