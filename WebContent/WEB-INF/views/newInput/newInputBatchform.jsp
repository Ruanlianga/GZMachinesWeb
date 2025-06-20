<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
	<form id="auForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>新购机具联络员：</td>
					<td class="DataTD">&nbsp;
						<select id="receiveName" name="receiveName" style="width:45%;"></select>
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>采购时间：</td>
					<td class="DataTD">&nbsp;
				        <input type="text" jyValidate="required" readonly style="width:45%;" class="FormElement ui-widget-content ui-corner-all" id="launchTime" name="launchTime" >
				    </td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>到货时间：</td>
					<td class="DataTD">&nbsp;
				        <input type="text" jyValidate="required" readonly style="width:45%;" class="FormElement ui-widget-content ui-corner-all" id="finishTime" name="finishTime" >
				    </td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD">备注：</td>
					<td class="DataTD">&nbsp;
				    	<input type="text" style="width:45%;" id="remark" name="remark" class="FormElement ui-widget-content ui-corner-all">
				    </td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<div id="acceptDiv" class="hide">
	<form id="acceptForm" method="POST" onsubmit="return false;" >
		<table class="accept" id="accepts" cellpadding="0" border="0" cellspacing="0" style="width:98%;page-break-before: always;">
			<tr>
				<td colspan="6" style="text-align: center; font-size: 1.3em;font-weight: bold;font-family:'宋体'">
					机 具 设 备 到 货 验 收 单
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding: 20px 0px 10px 0px;font-family:'宋体'">验收日期：<span id="checkTimes"></span></td>
				<td colspan="2" style="padding: 20px 0px 10px 0px;font-family:'宋体'">编号：<span id="checkCodes"></span></td>
			</tr>
			<tr style="width: 100%">
				<td style="height:120px;text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">机具名称</td>
				<td style="text-align:left;width: 25%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span id="machineTypes" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">规格型号</td>
				<td style="text-align:left;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;"><span id="models" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td style="text-align:left;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span id="machinesNums" class="basic" style="text-align: center;"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">生产厂家</td>
				<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span id="venders" class="basic" style="text-align: center;"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">出厂编号</td>
				<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">验收项目</td>
			</tr>
			<tr>
				<td style="height:80px;width: 15%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td style="text-align:center;width: 25%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span id="checkNums" class="basic" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">外观检查</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span id="exteriorChecks" class="basic" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">实验检验</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span id="testChecks" class="basic" style="text-align: center;"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">验收结论</td>
				<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span id="checkConclusions" class="basic" style="text-align: center;"></span></td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">相关配套资料：<span id="aboutFile"></span></td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;border-top: 1px dashed #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;border-top: 1px dashed #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td rowspan="7" style="text-align:center;width:15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:'宋体'">验收签章</td>
				<td rowspan="2" style="height:80px;width:15%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">租赁科</td>
				<td style="text-align:center;width:15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">采购员</td>
				<td style="text-align:center;width:15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">库管员</td>
				<td colspan="2" style="text-align:center;width:30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">部门负责人</td>
			</tr>
			<tr>
				<td style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td rowspan="2" style="height:80px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">修试科</td>
				<td colspan="2" style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">修试人员</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">部门负责人</td>
			</tr>
			<tr>
				<td colspan="2" style="height:40px;width: 15%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td rowspan="2" style="height:80px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">安全技术科</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">检验员</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">部门负责人</td>
			</tr>
			<tr>
				<td colspan="2" style="height:50px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="height:50px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td style="height:70px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:'宋体'">分公司领导</td>
				<td colspan="4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
		</table>
	</form>
	<button style="bottom: 40px;" onClick="jqprinting1()">打印</button>
	<script type="text/javascript">
		function jqprinting1() {
			$(".accept").jqprint({ operaSupport: false });
		}
	</script>
</div>