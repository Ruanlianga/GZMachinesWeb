<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
					</tr>
					<tr class="FormData" >
						<td class="CaptionTD">性别：</td>
						<td class="DataTD">&nbsp;
							<label class="radio-inline">
  								<input type="radio" name="sex" id="sex1" value="1"> 男
  							</label>
							<label class="radio-inline">
								<input type="radio" name="sex" id="sex0" value="0"> 女
							</label>
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>登录名：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jyValidate="required"   name="loginName" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>单位部门：</td>
						<td class="DataTD">&nbsp;
							<input id="orgName" type="text" jyValidate="required" readonly value="" class="FormElement ui-widget-content ui-corner-all" onclick="showRole(); return false;"/>
							<input type="hidden" name="orgId" value="0" >
							<a href="#" title="清空" onclick="emptyRole(); return false;" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
							<div id='orgContent' class="menuContent ztreeMC" style="display: none; position: absolute;">
								<ul id="orgTree" class="ztree accountOrgTree"></ul>
							</div>
						</td>
					</tr>	
						<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>所属分公司</td>
						<td class="DataTD">&nbsp;
							<input id="companyName" type="text" jyValidate="required" readonly value="" class="FormElement ui-widget-content ui-corner-all" />
							<input type="hidden" id="companyId" name="companyId" value="0" >
						
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">用户名：</td>
						<td class="DataTD">&nbsp;
						<input type="text"  name="name" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>	
					<tr class="FormData">
						<td class="CaptionTD">电子邮箱：</td>
						<td class="DataTD">&nbsp;
						<input type="text" jyValidate="email"   name="mail" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>	
					<tr class="FormData">
						<td class="CaptionTD">电话号码：</td>
						<td class="DataTD">&nbsp;
						<input type="text"   name="telphone" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr> 
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>岗位：</td>
						<td class="DataTD">&nbsp;
							<input type="text" name="postName"  jyValidate="required" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr> 
					<tr class="FormData">
						<td class="CaptionTD">办公地址：</td>
						<td class="DataTD">&nbsp;
						<input type="text"   name="officeAddress" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
</div>
<div id="resetPwdDiv" class="hide">
	<form id="resetPwdFrom" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
				<tr style="display:none">
					<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
				</tr>
				<tr class="FormData">
					<td class="CaptionTD">重置密码：</td>
					<td class="DataTD">&nbsp;
						<input type="password" jyValidate="required"   name="pwd" class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr> 
			</tbody>
		</table>
	</form>		
</div>