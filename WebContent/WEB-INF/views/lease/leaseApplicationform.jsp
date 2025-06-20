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
					
					<td class="CaptionTD">领用方：</td>
					<td class="DataTD">&nbsp;
						<select id="subcontractors" name="subcontractors">
							
						</select>	
					</td>
					
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="projectTree();">工程名称：</td>
					<td class="DataTD">&nbsp;
						<input id="projectName" type="text" value="" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="projectId" name="projectType" value="0" >
						<a href="#" title="过滤" onclick="projectTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				
					<td class="CaptionTD" style="cursor: pointer;" onclick="findAgreeCode();"><font color="red">*</font>协议号：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" id="agreementCode" name="agreementCode" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				<tr class="FormData">
					<!-- <td class="CaptionTD"><font color="red">*</font>申请单号：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" readonly="readonly" id="applyNumber" name="applyNumber" class="FormElement ui-widget-content ui-corner-all">
					</td>
				 -->
				 
				 <td class="CaptionTD">联系方式：</td>
					<td class="DataTD">&nbsp;
						<input type="text" id="phone" name="phone" class="FormElement ui-widget-content ui-corner-all">
					</td>
					<td class="CaptionTD"><font color="red">*</font>领料人：</td>
					<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required" id="proposer" name="proposer" class="FormElement ui-widget-content ui-corner-all">
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