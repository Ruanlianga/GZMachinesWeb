<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
	<form id="auForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input id="batchId" type="hidden" name="batchId" ></td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>机具类型：</td>
					<td class="DataTD">&nbsp;
						<select id="firstName" name="firstName"></select>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>规格型号：</td>
					<td class="DataTD">&nbsp;
						<select id="model" name="model"></select>
					</td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>机具数量：</td>
					<td class="DataTD">&nbsp;
					<input type="number" jyValidate="required" id="machineNums" name="machineNums" class="FormElement ui-widget-content ui-corner-all"></td>
					<td>库存数量：<span id="sums"></span></td>
				</tr>
				
			</tbody>
		</table>
	</form>
</div>
