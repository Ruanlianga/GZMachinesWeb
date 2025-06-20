<%@ page contentType="text/html;charset=UTF-8"%>

<div id="auAddDiv" class="hide auDiv">
	<form id="auAddForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				
				<tr style="display:none;">
					<td colspan="2" class="ui-state-error">
						<input type="hidden" name="id" style="width:50%;">
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD" onclick="unitTree();" style="cursor:pointer"><font color="red">*</font>退料单位：</td>
					<td class="DataTD">&nbsp;
						<input id="unitName" type="text"  value="" style="width:50%;" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="unitId" name="backCompanyId" value="0" >
						<a href="#" title="过滤" onclick="unitTree(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD" onclick="projectTree();" style="cursor:pointer"><font color="red">*</font>退料工程：</td>
					<td class="DataTD">&nbsp;
						<input id="projectName" type="text"  value="" style="width:50%;" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="projectId" name="backProjectId" value="0" >
						<a href="#" title="过滤" onclick="projectTree(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD">领用方：</td>
					<td class="DataTD">&nbsp;
						<select id="subcontractors" name="subcontractors">
						
						</select>	
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>协议编号：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" readonly="readonly" name="agreementCode" id="agreementCode" style="width:50%;" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>退料单号：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" readonly="readonly" name="number" id="number" style="width:50%;" class="FormElement ui-widget-content ui-corner-all">
						
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>退料人：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" name="userName" style="width:50%;" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>联系方式：</td>
					<td class="DataTD">&nbsp;
						<input class="datainp" type="text" jyValidate="required" name="phone" style="width:50%;" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD">备注：</td>
					<td class="DataTD">&nbsp;
						<input type="text" name="remark" style="width:50%;" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<div id="auDocDiv" class="hide auDocDiv">
	<input type="hidden" id="taskId0" name="taskId0" value=""/>
	<form id="auDocForm" method="POST" onsubmit="return false;">
		<table id="dd" cellpadding="0" border="0" cellspacing="0" style="width:98%;">
			<tr>
				<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
					贵州送变电有限责任公司
				</td>
			</tr>
			<tr>
				<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
					退　料　单
				</td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;border-top: 1px solid #000000;border-left: 1px solid #000000;">工程名称：<span id="backProjectName"></span></td>
				<td colspan="4" style="border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;">制单日期：<span class="year"></span>年<span class="month"></span>月<span class="day"></span>日</td>
			</tr>
			<tr>
				<td colspan="5" style="height:30px;border-top: 1px solid #000000;border-left: 1px solid #000000;">退料单位：<span id="backCompanyName"></span></td>
				<td colspan="2" style="border-top: 1px solid #000000;border-left: 1px solid #000000;">单号：<span id="backOddNumbers"></span></td>
				<td colspan="2" style="border-top: 1px solid #000000;border-right: 1px solid #000000;">领料方：<span id="subcontractorName"></span></td>
			</tr>
			<tr>
				<td colspan="9" id="taskRemark0" style="height:30px;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;">备注：</td>
			</tr>
			<tr>
				<td style="width:3%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">序号</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">设备名称</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">规格型号</td>
				<td style="width:3%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">单位</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">数量</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">内部编号</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">状态</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">设备编号</td>
				<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-right: 1px solid #000000;">备注</td>
			</tr>
			<tr>
				<td id="id0" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="typeName0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="modelName0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="unit0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="backNum0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="code0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="weight0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remarkMachine0" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remark0" class="basic" style="text-align:center;border: 1px solid #000000;"></td>
			</tr>
			<tr>
				<td id="id1" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="typeName1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="modelName1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="unit1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="backNum1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="code1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="weight1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remarkMachine1" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remark1" class="basic" style="text-align:center;border: 1px solid #000000;"></td>
			</tr>
			<tr>
				<td id="id2" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="typeName2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="modelName2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="unit2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="backNum2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="code2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="weight2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remarkMachine2" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remark2" class="basic" style="text-align:center;border: 1px solid #000000;"></td>
			</tr>
			<tr>
				<td id="id3" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="typeName3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="modelName3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="unit3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="backNum3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="code3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="weight3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remarkMachine3" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remark3" class="basic" style="text-align:center;border: 1px solid #000000;"></td>
			</tr>
			<tr id="five">
				<td id="id4" class="basic" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="typeName4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="modelName4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="unit4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="backNum4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="code4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="weight4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remarkMachine4" class="basic" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>
				<td id="remark4" class="basic" style="text-align:center;border: 1px solid #000000;"></td>
			</tr>
			<tr style="height: 50px">
				<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体';border-top: 1px solid #000000;">库工：</td>
				<td colspan="4" style="height:30px;font-weight: bold;font-family:'宋体';border-top: 1px solid #000000;">退料人：</td>
			</tr>
			<tr style="height: 50px">
				<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'">检验：</td>
				<td colspan="4" style="height:30px;font-weight: bold;font-family:'宋体'">入库确认：</td>
			</tr>
			<tr style="height: 50px">
			<!-- 	<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'"><span >退料入库审核：</span><span id="examineUser"></span></td> -->

			</tr>
		</table>
	</form>
	<button style="margin-top: 20px;width: 55px;height: 30px;" onClick="jqprinting1()">打印</button>
	<button style="margin-top: 20px;width: 55px;height: 30px;margin-left: 1%;" onClick="save1()">保存</button>
	<script type="text/javascript">
		function jqprinting1() {
			$("#dd").jqprint({ operaSupport: false });
		}
	</script>
</div>
