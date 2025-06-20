<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.bonus.core.DateTimeHelper"%>
<%
	String currentDate = DateTimeHelper.getNowDate();
%>
<!-- <style>
.FormData1{
	 display:none;
}
</style> -->
 
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" autofocus="off">
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red"></font>当前检修的时间：</td>
						<td class="DataTD">&nbsp;
						<input type="text"   id="nowchecktime" value="<%=currentDate %>"  name="nowchecktime" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red"></font>下次检修的时间：</td>
						<td class="DataTD">&nbsp;
						<input type="text"    id="nextchecktime" value="<%=currentDate %>"  name="nextchecktime" class="FormElement ui-widget-content ui-corner-all"></td>
					</tr>
						<!-- <tr class="FormData1">
							<td class="CaptionTD"><font color="red"></font>内容：</td>
							<td class="DataTD">&nbsp;
							<textarea style="width: 75%;" rows="4" name="content" id="content"></textarea><br>
							</td>
						</tr>
						<tr class="FormData1">
							<td class="CaptionTD"><font color="red"></font>电话：</td>
							<td class="DataTD">&nbsp;
							<input type="text" id="telepment" value="" name="telepment" class="FormElement ui-widget-content ui-corner-all"></td>
							<input type="text" id="telepment" value="" jyValidate="required,phone" name="telepment" class="FormElement ui-widget-content ui-corner-all"></td>
						</tr> -->
					
				</tbody>
			</table>
		</form>
</div>