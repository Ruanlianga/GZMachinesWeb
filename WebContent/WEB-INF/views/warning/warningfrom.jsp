<%@ page contentType="text/html;charset=UTF-8"%>
<div id="auDiv" class="hide">
	<form id="auAddForm" method="POST" onsubmit="return false;">
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
	<!-- 		<tr class="FormData" style="display: none;">
					<td class="DataTD">
						<input id="id" name="id" type="text" class="FormElement ui-widget-content ui-corner-all" >
					</td>
				</tr> -->
				
 			
				<!--  <tr class="FormData">
					<td class="CaptionTD">设备名称:</td>
					<td class="DataTD">&nbsp; 
						<input id="name" name="name" type="text"  class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				
				 <tr class="FormData">
					<td class="CaptionTD">设备编号:</td>
					<td class="DataTD">&nbsp; 
						<input id="code" name="code" type="text"  class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				
			
				</tr> -->
 
					
	            <tr class="FormData">
					<td class="CaptionTD">设备周期:</td>
					<td class="DataTD">&nbsp; 
						<input id="uploadCycle"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="uploadCycle" type="text"  class="FormElement ui-widget-content ui-corner-all">
					</td>
				</tr>
				
				</tr>
 
			</tbody>
		</table>
	</form>
</div>
</html>