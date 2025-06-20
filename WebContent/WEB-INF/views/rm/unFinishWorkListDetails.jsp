<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="4" class="ui-state-error">
							<input type="hidden" name="id" >
						</td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD">单位名称：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="unitName" name="unitName" class="FormElement ui-widget-content ui-corner-all">
						</td>
						<td class="CaptionTD">工程名称：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="projectName" name="projectName" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">任务名称：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="taskName" name="taskName" class="FormElement ui-widget-content ui-corner-all">
						</td>
							<td class="CaptionTD">协议号：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="agreementCode" name="agreementCode" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">负责人：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="personName" name="personName" class="FormElement ui-widget-content ui-corner-all">
						</td>
						<td class="CaptionTD">创建时间：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="createTime" name="createTime" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">发起人：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="operaTionName" name="operaTionName" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
					
				</tbody>
			</table>
		</form>
</div>