<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auAcceptDiv" class="hide">
	<form id="auAcceptForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>机具类型：</td>
					<td class="DataTD">&nbsp;
					<input type="text" readonly id="machineType" class="FormElement ui-widget-content ui-corner-all"></td>
					<td class="CaptionTD"><font color="red">*</font>规格型号：</td>
					<td class="DataTD">&nbsp;
					<input type="text" readonly id="modelType" class="FormElement ui-widget-content ui-corner-all"></td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>验收数量：</td>
					<td class="DataTD">&nbsp;
						<input type="number" jyValidate="required" name="checkNum" class="FormElement ui-widget-content ui-corner-all">
					</td>
					<td class="CaptionTD exteriorCheck">外观检查：</td>
					<td class="DataTD exteriorCheck">&nbsp;
						<label class="radio-inline">
 								<input type="radio" name="exteriorCheck" id="exteriorCheck1" value="1"> 合格
 							</label>
						<label class="radio-inline">
							<input type="radio" name="exteriorCheck" id="exteriorCheck0" value="0"> 不合格
						</label>
					</td>
					<td class="CaptionTD testCheck">试验检验：</td>
					<td class="DataTD testCheck">&nbsp;
						<label class="radio-inline">
 								<input type="radio" name="testCheck" id="testCheck1" value="1"> 合格
 							</label>
						<label class="radio-inline">
							<input type="radio" name="testCheck" id="testCheck0" value="0"> 不合格
						</label>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>验收结论：</td>
					<td colspan="3" class="DataTD">&nbsp;
						<textarea rows="2" style="width:93%;" jyValidate="required" id="checkConclusion" name="checkConclusion" class="FormElement ui-widget-content ui-corner-all"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
