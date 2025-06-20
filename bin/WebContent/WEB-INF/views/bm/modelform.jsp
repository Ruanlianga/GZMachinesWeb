<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD">机具类型：</td>
						<td class="DataTD">&nbsp;
							<input id="types" type="text" jyValidate="required" readonly value="" class="FormElement ui-widget-content ui-corner-all" onclick="showRole(); return false;"/>
							<input type="hidden" name="type" value="0" >
							<a href="#" title="清空" onclick="emptyRole(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
							<div id='typeContent' class="menuContent ztreeMC" style="display: none; position: absolute;">
								<ul id="orgTree" class="ztree accountOrgTree"></ul>
							</div>
						</td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"></td>
						<td class="DataTD">&nbsp;
						</td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"></td>
						<td class="DataTD">&nbsp;
						</td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>规格型号：</td>
						<td class="DataTD">&nbsp;
							<input type="text" jyValidate="required"  maxlength="16" name="name" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
					
				</tbody>
			</table>
		</form>
</div>