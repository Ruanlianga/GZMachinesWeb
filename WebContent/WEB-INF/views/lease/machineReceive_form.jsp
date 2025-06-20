<%@ page contentType="text/html;charset=UTF-8"%>
<div id="addDiv" class="hide">
	<form id="addForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="4" class="ui-state-error">
						<input id="taskId" type="hidden" name="taskId" >
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="unitTree();">租赁单位：</td>
					<td class="DataTD">&nbsp;
						<input id="unitName" type="text" value="" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="unitId" name="branchOffice" value="0" >
						<a href="#" title="过滤" onclick="unitTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
					<td class="CaptionTD" style="cursor: pointer;" onclick="projectTree();">工程名称：</td>
					<td class="DataTD">&nbsp;
						<input id="projectName" type="text" value="" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="projectId" name="projectType" value="0" >
						<a href="#" title="过滤" onclick="projectTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="findAgreeCode();"><font color="red">*</font>协议号：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" id="agreementCode" name="agreementCode" class="FormElement ui-widget-content ui-corner-all">
					</td>
					<td class="CaptionTD"><font color="red">*</font>申请单号：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" readonly="readonly" id="applyNumber" name="applyNumber" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>领料人：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" id="leaseMan" name="leaseMan" class="FormElement ui-widget-content ui-corner-all">
					</td>
					<td class="CaptionTD">联系方式：</td>
					<td class="DataTD">&nbsp;
						<input type="text" id="phone" name="phone" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">备注：</td>
					<td class="DataTD">&nbsp;
						<input type="text" id="remark" name="remark" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<div id="auDiv" style="width: 1600px" class="hide auDiv">
	<input type="hidden" id="taskId0" name="taskId0" value=""/>
	<form id="auForm" method="POST" onsubmit="return false;">
		<table id="dd" cellpadding="0" border="0" cellspacing="0" style="width:98%;">
			<tr>
				<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
					贵州送变电有限责任公司
				</td>
			</tr>
			<tr>
				<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
					领　料　单
				</td>
				<td id="leaseFormTrDom" colspan="4" style="text-align: right; font-size: 1.4em;font-weight:bold;">
				</td>
			</tr>
			
			<tr>
			    <td colspan="5"><span style="font-weight: bold;font-family:'宋体'">单据编号：</span><span class="applyNumber"></span></td>
			    <td colspan="5" style="height:30px;font-family:'宋体'"><span style="font-weight: bold;" >制单日期：</span><span class="applyTime"></span></td>
			</tr>
			
			<tr>
				<td colspan="3" style="height:30px;width:20%;font-family:'宋体';border-top: 1px solid #000000;border-left: 1px solid #000000;">工程名称：<span class="projectName"></span></td>
				<td colspan="3" style="height:30px;width:20%;font-family:'宋体';border-top: 1px solid #000000;border-left: 1px solid #000000;">租赁单位：<span class="leaseCompany"></span></td>
	           	<td colspan="4" style="border-top: 1px solid #000000;width:20%;font-family:'宋体';border-left: 1px solid #000000;border-right:1px solid #000000;">领料方：<span class="subcontractors"></span></td>
			</tr>
		    <tr>
				<td colspan="10" class="taskRemark0" style="height:30px;font-family:'宋体';border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;">&emsp;备注：
				</td>
			</tr>
			<tr>
				<td style="width:3%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">序号</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">资产编号</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">资产类别</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">设备名称</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">规格型号</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">资产</td>
				<td style="width:3%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">单位</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">设备编号</td>
				<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;font-family:'宋体'">备注</td>
			</tr>
			<tr>
				<input type="hidden" class="wmaId" value="">
				<td id="number0" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="id0" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesType0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesModel0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="unit0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="isFixedAssets0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesNum0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="weight0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remarkMachine0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remark0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family:'宋体'">
				</td>
			</tr>
			<tr>
				<input type="hidden" class="wmaId" value="">
				<td id="number1" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="id1" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesType1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesModel1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="unit1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="isFixedAssets1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesNum1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="weight1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remarkMachine1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remark1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family:'宋体'">
				</td>
			</tr>
			<tr>
				<input type="hidden" class="wmaId" value="">
				<td id="number2" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="id2" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesType2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesModel2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="unit2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="isFixedAssets2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesNum2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="weight2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remarkMachine2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remark2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;">
				</td>
			</tr>
			<tr>
				<input type="hidden" class="wmaId" value="">
				<td id="number3" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="id3" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体' "></td>
				<td id="machinesType3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体' "></td>
				<td id="machinesModel3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体' "></td>
				<td id="unit3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体' "></td>
				<td id="isFixedAssets3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesNum3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体' "></td>
				<td id="weight3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体' "></td>
				<td id="remarkMachine3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remark3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family:'宋体'">
				</td>
			</tr>
			<tr id="five">
				<input type="hidden" class="wmaId" value="">
				<td id="number4" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="id4" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesType4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesModel4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="unit4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="isFixedAssets4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="machinesNum4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="weight4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remarkMachine4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td id="remark4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;    border-bottom: 1px solid #000000;font-family:'宋体'">
				</td>
			</tr>
			
			<tr style="height: 50px">
				<td colspan="10" style="height:30px;font-weight: bold;border-top: 1px solid #000000;font-family:'宋体'"><span style="color:red">备注：以上货物完好无损，质量无缺陷，确认无误后由领用人签字。</span></td>
			</tr>
			
			<tr style="height: 50px">
				<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'"><span >制单：</span><span class="opeatorUrl"></span></td>
				<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'">领用人：</td>
			</tr>
			
			<tr style="height: 50px">
				<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'">库工：</td>
				<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'">出库确认：</td>
			</tr>
			
			
		</table>
		<table id="morePage" cellpadding="0" border="0" cellspacing="0" style="width:98%;z-index: -1;">
 
		</table>
		<!-- <table id="morePage" class="hide"></div> -->
	</form>
	<button style="margin-top: 20px;width: 55px;height: 30px;" onClick="jqprinting1()">打印</button>
	<button style="margin-top: 20px;width: 55px;height: 30px;margin-left: 1%;" onClick="save1()">保存</button>
	<script type="text/javascript">
		// function jqprinting1() {
		// 	$("#dd").jqprint({ operaSupport: false });
		// }
	</script>
</div>

<div id="auDetailDiv" class="hide auDetailDiv">
	<form id="auDetailForm" method="POST" onsubmit="return false;">
		<table id="detail" cellpadding="0" border="0" cellspacing="0" style="width:99%;">
			<tr>
				<td colspan="4" style="padding-bottom:20px;text-align: center; font-size: 1.3em;font-weight: bold;font-family:'宋体'">
					领料单编号明细
				</td>
			</tr>
			<tr id="header">
				<td style="height:30px;text-align:center;width: 10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">编号</td>
				<td style="text-align:center;width: 30%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">名称</td>
				<td style="text-align:center;width:30%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">规格型号</td>
				<td style="text-align:center;width: 30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">设备编号</td>
			</tr>
		</table>
	</form>
	<button style="bottom: 30px;" onClick="jqprinting2()">打印</button>
	<script type="text/javascript">
		function jqprinting2() {
			$("#detail").jqprint({ operaSupport: false });
		}
	</script>
</div>

<div id="auCheckDiv" class="hide auDetailDiv">
	<form id="auCheckForm" method="POST" onsubmit="return false;">
		<table id="check" cellpadding="0" border="0" cellspacing="0" style="width:98%;">
			<tr>
				<td colspan="12" style="text-align: center; font-size: 2em;font-weight: bold;font-family: simsun;">
					施  工  机  具  设  备  出  库  检  验  记  录  表
				</td>
			</tr>
			<tr>
				<td colspan="7" style="padding-top:20px;padding-bottom:20px;height:30px; width:70%;font-weight: bold;font-family: simsun;font-size: 1.2em">&emsp;领用工程：<span id="leaseProject"></span></td>
				<td colspan="5" style="padding-top:20px;padding-bottom:20px;font-weight: bold; width:30%;font-family: simsun;font-size: 1.2em">&emsp;使用单位：<span id="leaseCompanyName"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">机具名称</td>
				<td style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">规格型号</td>
				<td style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">单位</td>
				<td style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">数量</td>
				<td style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">设备编码</td>
				<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">额定载荷KN</td>
				<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">试验载荷KN</td>
				<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">持荷时间min</td>
				<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">试验日期</td>
				<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">下次试验日期</td>
				<td style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">检验结论</td>
				<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;font-family: simsun;font-size: 1.1em">备注</td>
			</tr>
			<tr>
				<td class="typeName0 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName0 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit0 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum0 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode0 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad0 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad0 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime0 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime0 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime0 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults0 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName1 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName1 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit1 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum1 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode1 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad1 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad1 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime1 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime1 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime1 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults1 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName2 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName2 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit2 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum2 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode2 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad2 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad2 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime2 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime2 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime2 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults2 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName3 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName3 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit3 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum3 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode3 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad3 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad3 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime3 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime3 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime3 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults3 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName4 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName4 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit4 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum4 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode4 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad4 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad4 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime4 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime4 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime4 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults4 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName5 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName5 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit5 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum5 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode5 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad5 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad5 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime5 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime5 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime5 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults5 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName6 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName6 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit6 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum6 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode6 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad6 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad6 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime6 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime6 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime6 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults6 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName7 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName7 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit7 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum7 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode7 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad7 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad7 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime7 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime7 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime7 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults7 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td class="typeName8 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName8 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit8 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum8 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode8 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad8 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad8 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime8 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime8 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime8 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults8 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr class="ten">
				<td class="typeName9 basic" style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="modelName9 basic" style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="unit9 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="machinesNum9 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="deviceCode9 basic" style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="ratedLoad9 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testLoad9 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="holdingTime9 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="testTime9 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="nextTestTime9 basic" style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td class="checkResults9 basic" style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;"></td>
				<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>
			</tr>
			<tr>
				<td colspan="9" style="height:30px; width:80%;font-weight: bold;border-top: 1px solid #000000;"></td>
				<td colspan="3" style="height:75px;font-weight: bold; width:20%;border-top: 1px solid #000000;font-family: simsun;">
					<div style="margin-top:38px">
						<span style="font-size: 1.2em">&emsp;检验单位：</span>
						<canvas id="canvas" style="float:right;margin-top:-75px;"width="150" height="150"></canvas>
						<img id="img" style="float:right;margin-top:-75px;" src=""/>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<button style="bottom: 30px;" onClick="jqprinting3()">打印</button>
	<script type="text/javascript">
		var canvas = document.getElementById("canvas");
		if(canvas.getContext){
			var ctx = canvas.getContext("2d");
	        ctx.beginPath();
	        ctx.moveTo(100,250);
	        ctx.lineTo(900,250);
	        ctx.stroke();
	       	ctx.beginPath();
	        ctx.moveTo(500,0);
	        ctx.lineTo(1000,1000);
	        ctx.stroke();
	    }
		function jqprinting3() {
			var image = document.getElementById("img");
	        image.src = canvas.toDataURL("image/png");
	        $("#img").css("display","block");
			$("#check").print();
			$("#img").css("display","none");
		}
	</script>
</div>
