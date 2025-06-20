<%@ page contentType="text/html;charset=UTF-8" %>
<div id="checkerDiv" class="hide">
	<form id="checkerForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input id="batchId" type="hidden" name="batchId" ></td>
				</tr>
				
				<tr class="FormData">
				<td class="CaptionTD" style="cursor: pointer;" onclick="checkTree();"><font color="red">*</font>检验员：</td>
					<td class="DataTD">&nbsp;
						<input id="checkName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="checkId" name="checkId" value="0" >
						<a href="#" title="过滤" onclick="checkTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
			
				
			</tbody>
		</table>
	</form>
</div>

</div>