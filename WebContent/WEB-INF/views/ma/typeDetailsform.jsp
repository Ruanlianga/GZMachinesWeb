<%@ page contentType="text/html;charset=UTF-8" %>
<div id="auDiv" class="hide">
		<form id="auForm" method="POST" onsubmit="return false;" >
			<table cellspacing="0" cellpadding="0" border="0" class="customTable">
				<tbody>
					<tr style="display:none">
						<td colspan="4" class="ui-state-error">
							<input type="hidden" name="id" >
						</td>
					</tr>
					
					<tr class="FormData">
						<td class="CaptionTD">机具类型：</td>
						<td class="DataTD">&nbsp;
							<input type="text" readonly id="pName" class="FormElement ui-widget-content ui-corner-all">
						</td>
						<td class="CaptionTD"><font color="red">*</font>规格型号：</td>
						<td class="DataTD">&nbsp;
							<input type="text" jyValidate="required" id="name" name="name" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>数量：</td>
						<td class="DataTD">&nbsp;
							<input type="number" min="0"  readonly jyValidate="required" id="nums" name="nums" class="FormElement ui-widget-content ui-corner-all">
						</td>
							<td class="CaptionTD"><font color="red">*</font>购置价格：</td>
						<td class="DataTD">&nbsp;
							<input type="number" min="0" jyValidate="required" id="buyPrice" name="buyPrice" class="FormElement ui-widget-content ui-corner-all">
						</td>
					<!-- 	<td class="CaptionTD"><font color="red">*</font>总保有量：</td>
						<td class="DataTD">&nbsp;
							<input type="number" jyValidate="required" id="allNums" name="allNums" class="FormElement ui-widget-content ui-corner-all">
						</td> -->
						
					</tr>

				<!-- 	<tr class="FormData">
						<td class="CaptionTD">核定载荷：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="ratedLoad" name="ratedLoad" class="FormElement ui-widget-content ui-corner-all">
						</td>
						<td class="CaptionTD">试验载荷：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="testLoad" name="testLoad" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr> -->
					
				<!-- 	<tr class="FormData">
						<td class="CaptionTD">持荷时间（分）：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="holdingTime" name="holdingTime" class="FormElement ui-widget-content ui-corner-all">
						</td> 
					
					</tr>-->
					<tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>租赁价格：</td>
						<td class="DataTD">&nbsp;
							<input type="number" min="0" jyValidate="required" id="leasePrice" name="leasePrice" class="FormElement ui-widget-content ui-corner-all">
						</td>
						<td class="CaptionTD"><font color="red">*</font>丢失赔偿价格：</td>
						<td class="DataTD">&nbsp;
							<input type="number" min="0" jyValidate="required" id="payPrice" name="payPrice" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
					<tr class="FormData">
						<!-- <td class="CaptionTD">是否需要实验：</td>
						<td class="DataTD">&nbsp;
							<label class="radio-inline">
  								<input type="radio" name="isTest" id="isTest1" value="1"> 是
  							</label>
							<label class="radio-inline">
								<input type="radio" name="isTest" id="isTest0" value="0"> 否
							</label>
						</td> -->
						<td class="CaptionTD">计量单位：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="unit" name="unit" class="FormElement ui-widget-content ui-corner-all">
						</td>
						<td class="CaptionTD">是否只计数：</td>
						<td class="DataTD">&nbsp;
							<label class="radio-inline">
  								<input type="radio" name="isCount" id="isCount1" value="1"> 是
  							</label>
							<label class="radio-inline">
								<input type="radio" name="isCount" id="isCount0" value="0"> 否
							</label>
						</td>
					</tr>
					<tr class="FormData">
						<td class="CaptionTD">RFID功率：</td>
						<td class="DataTD">&nbsp;
							<select id="rfidPower" name="rfidPower" style="width: 163px;">
								<option value="">请选择</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
							</select>
						</td>
						<td class="CaptionTD"></td>
						<td class="DataTD"></td>
					</tr>
					<!-- <tr class="FormData">
						<td class="CaptionTD"><font color="red">*</font>抽检比例：</td>
						<td class="DataTD">&nbsp;
							<input type="number" jyValidate="required" id="sampingRatio" name="sampingRatio" class="FormElement ui-widget-content ui-corner-all">%
						</td>
						<td class="CaptionTD">库管员：</td>
						<td class="DataTD">&nbsp;
							<input type="text" id="keeper" readonly class="FormElement ui-widget-content ui-corner-all">
							<input type="hidden" id="keeperId" name="keeperId">
							<a href="#" title="库管员" onclick="keeperTree();" class="lrspace3 aBtnNoTD" data-toggle="modal"><i class='icon-zoom-in color-p bigger-140'></i></a>
						</td>
					</tr> -->
					
						<!--<tr class="FormData">
						
						 <td class="CaptionTD"><font color="red">*</font>预警数量：</td>
						<td class="DataTD">&nbsp;
							<input type="number" jyValidate="required" id="warnNum" name="warnNum" class="FormElement ui-widget-content ui-corner-all">
						</td>
					</tr>
				</tbody> -->
			</table>
		</form>
</div>