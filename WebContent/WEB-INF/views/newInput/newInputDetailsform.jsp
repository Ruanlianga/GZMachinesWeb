<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
	<form id="auForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input id="taskId" type="hidden" name="taskId" ></td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="maTypeTree();"><font color="red">*</font>机具类型：</td>
					<td class="DataTD">&nbsp;
						<input id="maTypeName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all" style="width:50%;"/>
						<input type="hidden" id="maTypeId" name="firstName" value="0" >
						<a href="#" title="过滤" onclick="maTypeTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="maModelTree();"><font color="red">*</font>规格型号：</td>
					<td class="DataTD">&nbsp;
						<input id="maModelName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all" style="width:50%;"/>
						<input type="hidden" id="maModelId" name="model" value="0" >
						<a href="#" title="过滤" onclick="maModelTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="maVenderTree();"><font color="red">*</font>生产厂家：</td>
					<td class="DataTD">&nbsp;
						<input id="maVenderName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all" style="width:50%;"/>
						<input type="hidden" id="maVenderId" name="vender" value="0" >
						<a href="#" title="过滤" onclick="maVenderTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>购置价格：</td>
					<td class="DataTD">&nbsp;
						<input type="number" min="0" id="actualPrice" name="actualPrice" class="FormElement ui-widget-content ui-corner-all" style="width:50%;">
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>机具数量：</td>
					<td class="DataTD">&nbsp;
						<input type="number" min="0" id="machineNums" name="machineNums" class="FormElement ui-widget-content ui-corner-all" style="width:50%;">
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>验收地点</td>
					<td class="DataTD">&nbsp;
						<input type="text" id="checkPlace" name="checkPlace" class="FormElement ui-widget-content ui-corner-all" style="width:50%;">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<div id="auEditDiv" class="hide">
	<form id="auEditForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr class="FormData">
					<td class="CaptionTD">原机具数量：</td>
					<td class="DataTD">&nbsp;
						<input id="machineNum" readonly name="machineNum" type="number" class="FormElement ui-widget-content ui-corner-all"/>
					</td>
					<td class="CaptionTD"><font color="red">*</font>修改后数量：</td>
					<td class="DataTD">&nbsp;
						<input id="editNum"  jyValidate="required"name="editNum" type="number" class="FormElement ui-widget-content ui-corner-all"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<div id="acceptDiv" class="hide">
	<form id="acceptForm" method="POST" onsubmit="return false;" >
		<table id="accept" cellpadding="0" border="0" cellspacing="0" style="width:99%;">
			<tr>
				<td colspan="6" style="text-align: center; font-size: 1.3em;font-weight: bold;font-family:'宋体'">
					机具设备到货验收单
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding: 20px 0px 10px 0px;font-family:'宋体'">验收日期：<span id="checkTimes"></span></td>
				<td colspan="2" style="padding: 20px 0px 10px 0px;font-family:'宋体'">编号：<span id="checkCodes"></span></td>
			</tr>
			<tr>
				<td style="height:30px;text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">机具名称</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span id="machineTypes" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">规格型号</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;"><span id="models" style="text-align: center;"></span></td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span id="machinesNums" class="basic" style="text-align: center;"></span></td>
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
				<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span id="checkNums" class="basic" style="text-align: center;"></span></td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">外观检查</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:'宋体'"><span id="exteriorChecks" class="basic" style="text-align: center;"></span></td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">试验检验</td>
				<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:'宋体'"><span id="testChecks" class="basic" style="text-align: center;"></span></td>
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
	<button style="bottom: 30px;" onClick="jqprinting1()">打印</button>
	<script type="text/javascript">
		function jqprinting1() {
			$("#accept").jqprint({ operaSupport: false });
		}
	</script>
</div>

<div id="inputDiv" class="hide">
	<form id="inputForm" method="POST" onsubmit="return false;" >
		<table id="input" cellpadding="0" border="0" cellspacing="0" style="width:99%;">
			<tr>
				<td colspan="11" style="text-align: center; font-size: 1.3em;font-weight: bold;font-family:'宋体'">
					入　  库　  单
				</td>
			</tr>
			<tr>
				<td colspan="11" style="padding: 20px 0px 10px 0px;font-family:'宋体'">供应单位：<span class="vender"></span></td>
			</tr>
			<tr>
				<td colspan="4" style="padding: 5px 0px 10px 0px;font-family:'宋体'">入库类别：新购/其它</td>
				<td colspan="5" style="padding: 5px 0px 10px 0px;font-family:'宋体'"><span class="year"></span>年<span class="month"></span>月<span class="day"></span>日</td>
				<td colspan="2" style="padding: 5px 0px 10px 0px;font-family:'宋体'"></td>
			</tr>
			<tr id="header">
				<td style="height:30px;text-align:center;width: 6%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">编号</td>
				<td colspan="2" style="text-align:center;width: 20%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">名称</td>
				<td style="text-align:center;width: 9%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">规格型号</td>
				<td style="text-align:center;width: 6%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">单位</td>
				<td style="text-align:center;width: 9%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">单价（元）</td>
				<td style="text-align:center;width: 6%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
				<td colspan="2" style="text-align:center;width: 20%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">编号</td>
				<td colspan="2" style="text-align:center;width: 23%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:'宋体'">出厂日期</td>
			</tr>
			<tr>
				<td colspan="3" style="height:30px;width: 9%;border-top: 1px solid #000000;font-weight: bold;font-family:'宋体'">审核：</td>
				<td colspan="2" style="width: 18%;border-top: 1px solid #000000;font-weight: bold;font-family:'宋体'">检验：</td>
				<td colspan="3" style="width: 9%;border-top: 1px solid #000000;font-weight: bold;font-family:'宋体'">仓库：</td>
				<td colspan="3" style="width: 18%;border-top: 1px solid #000000;font-weight: bold;font-family:'宋体'">操作人：</td>
			</tr>
		</table>
	</form>
	<button style="bottom: 30px;" onClick="jqprinting2()">打印</button>
	<script type="text/javascript">
		function jqprinting2() {
			$("#input").jqprint({ operaSupport: false });
		}
	</script>
</div>