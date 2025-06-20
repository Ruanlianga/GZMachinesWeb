<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>区域长度（米）：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required"  maxlength="16" name="length" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>区域宽度（米）：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required"  maxlength="16" name="width" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					
				<!-- 	<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>区域面积（平米）：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required"  maxlength="16" name="area" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr> -->
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>仓库长度为x轴：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required"  maxlength="16" name="lon" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>仓库宽度为y轴：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required"  maxlength="16" name="lat" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>区域类型：</td>
						<td class="DataTD">&nbsp;
<!-- 							<select id="areaType"    name="areaType"   onchange="change(this.value)"></select> -->
                      	<input id="orgName"    name="orgName"  type="text" placeholder="请选择存放机具类型"  readonly value="" class="FormElement ui-widget-content ui-corner-all" onclick="showOrgTree(); return false;" />
							<input type="hidden" name="orgId" id="orgId"   >
							<a href="#" title="清空" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
							<div id='orgContent' class="menuContent ztreeMC" style="display: none; position: absolute;">
								<ul id="orgTree" class="ztree accountOrgTree"></ul>
							</div>

						</td>
							
						
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>所属仓库：</td>
						<td class="DataTD">&nbsp;
							<select id="houseName"   name="houseName"></select>
						</td>
					</tr>
					
				</tbody>
			</table>
		</form>
</div>