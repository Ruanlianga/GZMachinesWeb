<%@ page contentType="text/html;charset=UTF-8" %>
<div id="serviceDiv" class="hide">
	<form id="serviceForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input id="batchId" type="hidden" name="batchId" ></td>
				</tr>
				
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="serviceTree();"><font color="red">*</font>客服代表：</td>
					<td class="DataTD">&nbsp;
						<input id="serviceName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="serviceId" name="serviceId" value="0" >
						<a href="#" title="过滤" onclick="serviceTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
			
				
			</tbody>
		</table>
	</form>
</div>

</div>