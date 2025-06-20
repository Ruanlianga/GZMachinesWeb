<%@ page contentType="text/html;charset=UTF-8"%>
<div id="auDiv" class="hide auDiv">
	<form id="auForm" method="POST" onsubmit="return false;">
		<table id="dd" cellpadding="0" border="0" cellspacing="0" style="width:100%;">
			<tr>
				<td colspan="7" style="text-align: center; font-size: 1.4em;font-weight: bold;">
					退&emsp;料&emsp;单
				</td>
			</tr>
			<tr>
				<td colspan="7" style="height:30px;width: 20%;font-weight: bold;">工程名称：<span id="pickProject"></span></td>
			</tr>
			<tr>
				<td colspan="3" style="height:30px;font-weight: bold;">退料单位：<span id="pickCompany"></span></td>
				<td colspan="2" style="text-align:center;font-weight: bold;"><span class="year"></span>年<span class="month"></span>月<span class="day"></span>日</td>
				<td colspan="2" style="font-weight: bold;">编号：<span id="outNum"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">编号</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">名称</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">规格型号</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">单位</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">数量</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">重量</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;">备注</td>
			</tr>
			<tr>
				<td id="id0" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="machinesName0" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="model0" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="unit0" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="pickNum0" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="weight0" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="remark0" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;"></td>
			</tr>
			<tr>
				<td id="id1" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="machinesName1" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="model1" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="unit1" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="pickNum1" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="weight1" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="remark1" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;"></td>
			</tr>
			<tr>
				<td id="id2" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="machinesName2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="model2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="unit2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="pickNum2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="weight2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="remark2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;"></td>
			</tr>
			<tr>
				<td id="id3" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="machinesName3" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="model3" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="unit3" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="pickNum3" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="weight3" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="remark3" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;"></td>
			</tr>
			<tr id="five">
				<td id="id4" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="machinesName4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="model4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="unit4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="pickNum4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="weight4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>
				<td id="remark4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;"></td>
			</tr>
			<tr>
				<td colspan="2" style="height:30px;font-weight: bold;border-top: 1px solid #000000;">审核：</td>
				<td colspan="4" style="border-top: 1px solid #000000;"></td>
				<td style="font-weight: bold;border-top: 1px solid #000000;">开票：</td>
			</tr>
		</table>
	</form>
	<button style="bottom: 30px;" onClick="jqprinting()">打印</button>
	<script type="text/javascript">
		function jqprinting() {
			$("#dd").jqprint({ operaSupport: false });
		}
	</script>
</div>
