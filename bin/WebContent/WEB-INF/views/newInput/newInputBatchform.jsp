<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>接收人：</td>
						<td class="DataTD">&nbsp;
							<select id="acceptor" name="acceptor"></select>
						</td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>采购时间：</td>
						<td class="DataTD">&nbsp;
							<div class="layui-input-inline">
					        	<input type="text" class="FormElement ui-widget-content ui-corner-all" id="buyTime" name="buyTime" >
					    	</div>
					    </td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>到货时间：</td>
						<td class="DataTD">&nbsp;
							<div class="layui-input-inline">
					        	<input type="text" class="FormElement ui-widget-content ui-corner-all" id="acceptTime" name="acceptTime" >
					    	</div>
					    </td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>备注：</td>
						<td class="DataTD">&nbsp;
					    	<input type="text" maxlength="16" id= "remark"name="remark" class="FormElement ui-widget-content ui-corner-all">
					    </td>
					</tr>
				</tbody>
			</table>
		</form>
</div>