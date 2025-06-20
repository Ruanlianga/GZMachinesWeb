<%@ page contentType="text/html;charset=UTF-8" %>



<div id="auDiv" class="hide">
	<form id="auForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input id="batchId" type="hidden" name="batchId" ></td>
					
					
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="maTypeTree();"><font color="red">*</font>物资名称：</td>
					<td class="DataTD">&nbsp;
						<input id="maTypeName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="maTypeId" name="maTypeId" value="0" >
						<a href="#" title="过滤" class="maTree" onclick="maTypeTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="maModelTree();"><font color="red">*</font>规格型号：</td>
					<td class="DataTD">&nbsp;
						<input id="maModelName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="maModelId" name="maModelId" value="0" >
						<a href="#" title="过滤" class="maTree" onclick="maModelTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD" style="cursor: pointer;" onclick="serviceTree();"><font color="red">*</font>客服代表：</td>
					<td class="DataTD">&nbsp;
						<input id="serviceName" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all"/>
						<input type="hidden" id="serviceId" name="serviceId" value="0" >
						<a href="#" title="过滤" onclick="serviceTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
					</td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD"><font color="red">*</font>机具数量：</td>
					<td class="DataTD">&nbsp;
						<input type="number" jyValidate="required" id="machineNums" name="machineNums" class="FormElement ui-widget-content ui-corner-all"></td>
				<td>库存数量：<span id="sums"></span><br/>
					预出库数量：<span id="preSums"></span></td>
				</tr>
				
			</tbody>
		</table>
	</form>
</div>


<div id="remarkDiv" class="hide">
	<form id="remarkForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
			
				
				<tr class="FormData">
				<td class="CaptionTD" style="cursor: pointer;"><font color="red">*</font>备注：</td>
					<td class="DataTD">&nbsp;
						<input id="remarks" name="remarks" jyValidate="required" type="text" class="FormElement ui-widget-content ui-corner-all"/>
					</td>
				</tr>
			
				
			</tbody>
		</table>
	</form>
</div>

</div>