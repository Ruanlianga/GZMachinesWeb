<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
	<form id="auForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input id="batchId" type="hidden" name="batchId" ></td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>规格型号：</td>
					<td class="DataTD">&nbsp;
						<select id="firstName" name="firstName"></select>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"></td>
					<td class="DataTD">&nbsp;
						<select id="secondName" name="secondName"></select>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"></td>
					<td class="DataTD">&nbsp;
						<select id="parentName" name="parentName"></select>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"></td>
					<td class="DataTD">&nbsp;
						<select id="model" name="model"></select>
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>采购公司：</td>
					<td class="DataTD">&nbsp;
						<div class="layui-input-inline">
				        	<select id="company" name="company">
				        		<option value="W">送电分公司</option>
				        		<option value="H">宏源分公司</option>
				        	</select>
				    	</div>
				    </td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>生产厂家：</td>
					<td class="DataTD">&nbsp;
						<select id="vender" name="vender"></select>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>机具数量：</td>
					<td class="DataTD">&nbsp;
					<input type="number" jyValidate="required" name="machineNums" class="FormElement ui-widget-content ui-corner-all"></td>
				</tr>
				
			</tbody>
		</table>
	</form>
</div>

<div id="acceptDiv" class="hide">
	<form id="acceptForm" method="POST" onsubmit="return false;" >
		<table id="dd" cellpadding="0" border="0" cellspacing="0" style="width:99%;">
			<tr>
				<td colspan="6" style="text-align: center; font-size: 1.3em;font-weight: bold;font-family:'宋体'">
					机具设备到货验收单
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding: 20px 0px 10px 0px;font-family:'宋体'">验收日期：</td>
				<td colspan="2" style="padding: 20px 0px 10px 0px;font-family:'宋体'">编号：</td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">机具名称</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span id="machineTypes" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">规格型号</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;"><span id="models" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span class="machinesNums" style="text-align: center;"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">生产厂家</td>
				<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span id="venders" style="text-align: center;"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">出厂编号</td>
				<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">验收项目</td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span class="machinesNums" style="text-align: center;"></span></td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">外观检查</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">试验检验</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">验收结论</td>
				<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">相关配套资料：</td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;border-top: 1px dashed #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td colspan="6" style="height:30px;border-top: 1px dashed #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td rowspan="7" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:'宋体'">验收签章</td>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">租赁科</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">采购员</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">库管员</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">部门负责人</td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px dashed #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">修试科</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">修试人员</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">部门负责人</td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px dashed #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">安全技术科</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">检验员</td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">部门负责人</td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px dashed #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"></td>
				<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:'宋体'">分公司领导</td>
				<td colspan="4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000;font-family:'宋体'"></td>
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