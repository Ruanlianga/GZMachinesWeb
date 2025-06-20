<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%@include file="../webPortalCommonSet.jsp" %>

<title></title>

<style type="text/css">
	#baseInfo{
		/* border:1px solid; */
		text-align: center;
		width:96%;
		margin:0 2% 0 2%;
	}
	
	#baseInfo tr{
		height: 3em;
	}
	
	#baseInfo tr>input{
		width:96%;
	}
	#machineTable th{
		padding:5px;
		vertical-align:middle;
	}

	.inp{
		width:100%;
	}
	
	.inp2{
		width:68%;
	}
	
	#img{
		width:100%;
		height:150px;
	}
	
	#baseInfo td{
		border: solid 1px #d3d1d1;
	}
	
	.img{
		z-index:1;
		width:100px;
		height:100px;
		border:solid 1px black;
	}
	.img_div{
		display: inline-block;
		padding:10px;
	}
	.status{
	width:8%;
	position: absolute;
	z-index:999;
	top: 20%;
	left: 50%;
	opacity: 1;
}

.t{
	hidden:hidden;
}

.valid[readonly]{
		background-color: #ed9d9d !important;
		color: black !important;
	}
	
</style>
</head>
<body>
<div>
	<table  id="baseInfo" cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody class="a">
			
			<tr style="height:1.5em;">
				<td colspan="8"></td>
			</tr>
			<tr>
				<td width="10%">
					<a href="#" title="过滤" onclick="unitTree();" class="lrspace3 aBtnNoTD" data-toggle="modal">结算单位&nbsp;
						<i class='icon-zoom-in color-p bigger-140' style="color:blue"></i>
					</a>
				</td>
				<td  width="15%">
				<input type="hidden" id="unitId" value="0" >
				<input type="text"   id="unitName" value="" />
			
				</td>
				
				<td width="10%">
					<a href="#" title="过滤" onclick="projectTree();" class="lrspace3 aBtnNoTD" data-toggle="modal">结算工程&nbsp;
						<i class='icon-zoom-in color-p bigger-140' style="color:blue"></i>
					</a>
				</td>
				<td width="15%"  style="text-align: left;">
			 	<input type="hidden" id="projectId"  value="">
				<input type="text" id="projectName" value="" />  
				
					 
				</td>
				
				 <td width="10%"><span>协议号:</span></td>
				<td width="15%"  style="text-align: left;">
				<input type="hidden" id="agreementId"  value="">
					<input id="agreementCode" type="text" style="width:80%;" class="inp" disabled="disabled" value="">
				</td>
				
				<td width="10%"><span>截至日期:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="sltEndDate" class="inp" style="text-align: center;" class="t" jyValidate="required"
					  value="${fns:currentDateTimeAddOrSub('%y-%M-%d')}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:sFunc})"  type="text" readonly="readonly" >
				</td>
				
			</tr>
			<tr class="">
			    <td width="10%"><span>当前结算总金额:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="total" type="text" style="width:80%;" class="inp" disabled="disabled" value="0">:元
				</td>
				<td width="10%"><span>工器具租赁总金额:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="tm" type="text" style="width:80%;" class="inp subm" disabled="disabled" value="0">:元
				</td>
				<td width="10%"><span>设备租赁总金额:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="mm" type="text" style="width:80%;" class="inp subm" disabled="disabled" value="0">:元
				</td>
			<!-- 	<td width="10%"><span>赔偿总金额:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="cm" type="text" style="width:80%;" class="inp subm" disabled="disabled" value="0">:元
				</td> -->
				<td width="10%"><span>自定义总金额:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="cum" type="text" style="width:80%;" class="inp subm" disabled="disabled" value="0">:元
				</td>
			</tr>
			
			<tr>
				<td colspan="8"><span style="font-size: 2em;">自定义名目</span> <a  class="btn btn-success radius" title="点击添加" onclick="addExtraItem()" >添加</a></td>
			</tr>
			
			
			<tr class="as" hidden="hidden">
				<td width="10%">
					<select onchange="selectAddOrSub(this)">
						<option value="0">--增加项--</option>
						<option value="1">--减免项--</option>
					</select>
				</td>
				<td width="10%"><span>金额:</span></td>
				<td width="10%"><input type="number" class="cusM" style="width:80%;" value="0" onblur="validMoney(this)">:元</td>
				<td width="10%"><span>自定义备注:</span></td>
				<td  width="60%" colspan="4">
					<input type="text" style="width:80%;"> <a class="btn btn-success radius" title="点击删除该项" onclick="delExtraItem(this)" >删除</a>
				</td>
			</tr>
			
			
			<tr >
				<td width="10%"><span>结算人(租赁方:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="sltMan" type="text"  class="inp" >
				</td>
				<td width="10%"><span>结算人联系电话:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="sltManPhone" type="text"  class="inp" >
				</td>
				<td width="10%"><span>备注:</span></td>
				<td width="15%" colspan="3" style="text-align: left;">
					<textarea id="remarks" rows="2" cols="" class="inp"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="8" width="13%">
				<div class="cl pd-5 bg-1 bk-gray" style="padding-top:5px;">
					<span class="l" style="width: 60%;text-align: left;" id="sForm">
						<input type="text"  id="keyWord" name="keyWord" placeholder="请输入搜索关键词...">
						<a class="btn btn-success radius" data-title="点击查询" onclick="" href="javascript:;" >
							查询
						</a>
					</span>
					<span class="r" style="width: 38%;text-align: right;">
					</span>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
				<form id="baseForm" class="form-inline" method="POST">
					<table id="machineTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:4%" class="center hidden-480">序号</th>
							<th style="width:10%" class="center">机具名称</th>
							<th style="width:8%" class="center">机具规格</th>
							<th style="width:7%" class="center">数量(单位)</th>
							<th style="width:9%" class="center">设备编码</th>
							<th style="width:7%" class="center">租赁单价</th>
							<th style="width:8%" class="center">起租日期</th>
							<th style="width:4%" class='center'>状态</th>
							<th style="width:8%" class='center'>退租日期</th>
							<th style="width:10%" class="center">上次结算日期</th>
							<th style="width:6%" class='center'>结算日长</th>
							<th style="width:8%" class='center'>小计金额(元)</th>
						</tr>
					</thead>
					<tbody id="list">
						<tr><td colspan="14"  class="center" style='color:red;font-size:2em;' >请先选择需要结算的工程!!!</td></tr>
					</tbody>
					</table>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="col-sm-3">
	    <div class="dataTables_info customBtn" >
		   &emsp;<a href="#" title="导出" onclick="exportData();"   class="lrspace3" ><i class='icon-download color-blue bigger-220'></i></a> 
		</div>
	</div>
</div>


<script type="text/javascript">
var agreementId = $("#agreementId").val();

var machines ={};//机具容器

var p; //父页面对象,多层关闭时使用

/**
 * @author 
 * @date 2020-12-02
 * @function 提交申请审核结果
 * @returns
 */
function submitApply(){
	
	var agreementId = $("#agreementId").val();
	if(!JY.Object.notNull(agreementId)){
		var a = layer.confirm("<h4 style='color:red;'>请先选择结算工程!</h4>",{btn: ['关闭']},function(){
    		layer.close(a);
    	});
		return;
	}
	
	var obj = {};
	var arr = [];
	if(JY.Validate.newForm("baseInfo")){
		
		var s1 = $(".show_as").size();
		var size = $(".cb:checkbox:checked").size();
		if(s1 + size == 0){
			var idxMsg = layer.confirm("<h4 style='color:red;'>您没有选择任何结算的设备、工器具或自定义款项!</h4>",{btn: ['关闭']},function(){
	    		layer.close(idxMsg);
	    	});
			return;
		}
		
		$(".show_as").each(function(){
			var subtotal = $(this).find("input").eq(0).val();
			var remarks = $(this).find("input").eq(1).val();
			var stype = $(this).find("select").eq(0).val();
			
			var oo = {};
			oo.subtotal = parseFloat(subtotal);
			oo.remarks = remarks.trim();
			if(stype == 1 || stype == "1"){
				oo.costType = "1";
				oo.subtotal= -1 * oo.subtotal;
			}else{
				oo.costType = "0";
			}
			arr.push(oo);
		});
		
		
		
		var iii = layer.confirm("<h4 style='color:red;'>注意:本次结算截至日期为:" + $("#sltEndDate").val() + ",您确定生成此次的工程租赁结算清单列表吗?</h4>",{btn: ['确定','取消']},function(){
    		layer.close(iii);
			var agreementId = $("#agreementId").val();
			var tm = parseFloat($("#tm").val());
			var mm = parseFloat($("#mm").val());
			var baseMoney = tm+mm;
			var lastMoney = parseFloat($("#total").val());
			//var cpsMoney = parseFloat($("#cm").val());
			var addMoney = 0;
			var subMoney = 0;
			$(".cusM").each(function(){
				var v = $(this).parent().parent().find("select").eq(0).val();
				if(v == 1 || v == "1"){
					subMoney +=  parseFloat($(this).val());
					subMoney = checkIsExistDataDrift(subMoney);
				}else{
					addMoney +=  parseFloat($(this).val());
					addMoney = checkIsExistDataDrift(addMoney);
				}
			})
			obj.typeMoney = tm;
			obj.machineMoney = mm;
			obj.agreement = {id:agreementId};
			obj.baseMoney = baseMoney;
			obj.lastMoney = lastMoney;
			//obj.cpsMoney = cpsMoney;
			obj.addMoney = addMoney;
			obj.subMoney = subMoney;
			obj.status = "1";
			obj.sltMan = $("#sltMan").val();
			sltDate = $("#sltEndDate").val();
			obj.sltManPhone = $("#sltManPhone").val();
			obj.remarks = $("#remarks").val();
			
			$(".cb:checkbox:checked").each(function(){
				var o = {};
				o = paramConversionToObjOfForm2($(this).parent().parent().parent());
				o.startDate = o.zs;
				o.endDate = o.ze;
				delete o.lastSltDate;
				delete o.zs;
				delete o.ze;
				delete o.status;
				delete o.id;
				delete o.isCount;
				arr.push(o);
			});
			
			obj.items = arr;
			
			console.log(obj);
			
			var idx = layer.msg('正在提交保存,请稍等...', {
				  icon: 16
				  ,shade: 0.01
				  ,time:'-1'
			});
		 	$.ajax({        
		        type:"POST",  
		        url:bonuspath +'/backstage/projectSettlement/insertSlt', 
		        data: JSON.stringify(obj),
		        contentType:"application/json",
		        dataType:"json",
		        success:function(data){
		        	layer.close(idx);
		        	console.log(data);
		        	if(data.res == 1){
		        		var idxMsg = layer.confirm(data.resMsg,{btn: ['关闭']},function(){
		            		layer.close(idxMsg);
		            		parent.getbaseList();
		            		var i = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		        			parent.layer.close(i);
		            	});
					}else{
						var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
							layer.close(indexMsg);
			    		});
					}
				},
				error:function(data){
					layer.msg("数据加载失败!",{icon:2,time:2000})
					layer.close(idx);
				}
			}) 
		});
	}
}

/**
 * @author 
 * @date 2020-06-22
 * @function 条件查询
 * @returns 
 */
function search(){
	/* alert(1)
	 window.location.reload(); */
	$("#search").trigger("click")
}

/**
 * @author 
 * @date 2020-05-24
 * @function 启动执行
 * @returns  
 */
$(function () {
	$.fn.modal.Constructor.prototype.enforceFocus = function(){};

	$("#keyWord").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	p = window.parent;
});
function validNum(that,price){

	var newPrice = $(that).val();
   
	if(newPrice == '' || isNaN(newPrice)){
		
		$(that).val(price);
	}else{
		if(parseFloat(newPrice) < 0){
			$(that).val("0");
		}else{
		
			$(that).val(newPrice);
		
		}
	}
	countMoney()
	
}
function validMoney(that){
	var num = $(that).val();
	if(!JY.Object.notNull(num)){
		num = 0;	
	}else if(isNaN(num)){
		num = 0;
	}else if(parseFloat(num) < 0){
		num = 0;
	}
	$(that).val(num);
	
	var total = 0;
	$(".cusM").each(function(){
		var type = $(this).parent().parent().find("select").eq(0).val();
		if(type == 1 || type == "1"){
			total-=parseFloat($(this).val());
			total = checkIsExistDataDrift(total);
		}else{
			total+=parseFloat($(this).val());
			total = checkIsExistDataDrift(total);
		}
	})
	
	$("#cum").val(total);
	countTotalMoney();
}


function selectAddOrSub(){
	var total = 0;
	$(".cusM").each(function(){
		var type = $(this).parent().parent().find("select").eq(0).val();
		if(type == 1 || type == "1"){
			total-=parseFloat($(this).val());
			total = checkIsExistDataDrift(total);
		}else{
			total+=parseFloat($(this).val());
			total = checkIsExistDataDrift(total);
		}
	})
	
	$("#cum").val(total);
	countTotalMoney();
}

function checkStatusChange(that,type){
	
	var total = 0;
	if(type == 1 || type == '1'){
		total = $("#tm").val();
	}else{
		total = $("#mm").val();
	}
	var t = 0;
  st = $(that).parent().parent().parent().find(".subtotal").eq(0).text();
	if($(that).is(':checked')){
		t =	parseFloat(total)+parseFloat(st);
		t = checkIsExistDataDrift(t);
	}else{
		t =	parseFloat(total)-parseFloat(st);	
		t = checkIsExistDataDrift(t);
	}
	if(type == 1 || type == '1'){
		$("#tm").val(t);
	}else{
		$("#mm").val(t);
	}
	countTotalMoney();
}

function sFunc(event){
	var prevDate = event.cal.oldValue;
	var currDate = $("#sltEndDate").val();
	if(prevDate != currDate){
		countMoney();
	}
}


var dls=[];
var sts=[];
var dl="";
var st="";
var iddl;
function countMoney(){
	var currDate = $("#sltEndDate").val();
	var maArr=[];
	$(".mas").each(function(){
		maArr.push(paramConversionToObjOfForm2(this));
	})
	var len = maArr.length;
	var total = 0;
	var tm = 0;
	var mm = 0;
	for(var i = 0;i < len;i++){
		var countStartDate = "";
		var countEndDate = "";
		if(maArr[i].hasOwnProperty('lastSltDate')){
			countStartDate = maArr[i].lastSltDate;
		}else{
			countStartDate = maArr[i].startDate;
		}
		
		if(maArr[i].hasOwnProperty('backDate')){
			if(maArr[i].backDate >= currDate ){
				countEndDate = currDate;
			}else{
				countEndDate = maArr[i].backDate;
			}
		}else{
			countEndDate = currDate;
		}
		var id = maArr[i].id;
		 dl = DateMinus(countStartDate,countEndDate)+1;
		if(dl > 0){
			$("#dl"+maArr[i].id).text(dl);
		    st = parseFloat(maArr[i].price)*dl*parseFloat(maArr[i].num);
			
			st = checkIsExistDataDrift(st)
		 	
			if(maArr[i].isCount == 1 || maArr[i].isCount == '1'){
				tm+=st;
				tm = checkIsExistDataDrift(tm);
			}else{
				mm+=st;
				mm = checkIsExistDataDrift(mm);
			}
			
			$("#zs"+maArr[i].id).val(countStartDate);
			$("#ze"+maArr[i].id).val(countEndDate);
			
			$("#st"+maArr[i].id).text(st);
			$("#inp"+maArr[i].id).removeAttr("disabled");
			$("#inp"+maArr[i].id).prop("checked",true);
		}else{
			$("#inp"+maArr[i].id).removeAttr("checked");
			$("#inp"+maArr[i].id).attr("disabled","disabled");
			$("#dl"+maArr[i].id).text('---');
			$("#st"+maArr[i].id).text('---');
		}
		/* dls[i]=dl;
		sts[i]=st; */
		if (iddl != undefined || iddl != 'undefined') {
			iddl = id + "," + dl+","+st+";" + iddl;
		} 
	}
	$("#tm").val(tm);
	$("#mm").val(mm);

	countTotalMoney();
}

/**
 * 检查是否存在数据飘逸
 */
function checkIsExistDataDrift(num){
	var strNum = num.toString();
	var endStr = strNum.substring(strNum.indexOf(".")+1);
	var startStr = strNum.substring(0,strNum.indexOf("."));
	if(endStr.indexOf("999999") >= 0){
		console.log("出现99999:",num);
		var start = endStr.indexOf("999999");
		if(start == 0){
			num = parseFloat(startStr)+1
		}else{
			endStr = endStr.substring(0,start);
			endStr = (parseFloat(endStr)+1).toString();
			num = parseFloat(startStr+"."+endStr);
		}
	}else if(endStr.indexOf("000000") >= 0 ){
		console.log("出现000000:",num);
		var start = endStr.indexOf("000000");
		if(start == 0){
			num = parseFloat(startStr);
		}else{
			num = parseFloat(strNum.substring(0,strNum.indexOf("000000")));
		}
	}
	return num;
}

function countTotalMoney(){
	var total = 0;
	$(".subm").each(function(){
		total += parseFloat($(this).val());
		total = checkIsExistDataDrift(total);
	})
	$("#total").val(total);
}

function addExtraItem(){
	$(".as:last").after($(".as").eq(0).prop("outerHTML"));
	$(".as:last").addClass("show_as");
	$(".show_as").find("input").attr("jyValidate","required");
	$(".show_as").find("input:even").attr("jyValidate","over0");
	$(".as:last").show();
}

function delExtraItem(that){
	$(that).parent().parent().remove();
	selectAddOrSub()
}

function DateMinus(date1,date2){
   var sdate = new Date(date1); 
   var now = new Date(date2); 
   var days = now.getTime() - sdate.getTime(); 
  var day = parseInt(days / (1000 * 60 * 60 * 24)); 
  return day; 
}

/**
 * @author 
 * @date 2020-12-3
 * @function 工程选择事件
 * @returns  
 */
function selectProject(){
	var agreementId = $("#agreementId").val();
	if(JY.Object.notNull(agreementId)){
		//alert(agreementId)
		findUnSltMaTypeList(agreementId);
	}else{
		$("#total").val(0);
		$("#list").html("<tr><td colspan='14'  class='center' style='color:red;font-size:2em;' >请先选择需要结算的工程!!!</td></tr>");
	}
}

/**
 * @author 
 * @date 2020-05-24
 * @function 初始化页面ajax请求 返回html页面
 * @returns  
 */
function findUnSltMaTypeList(agreementId){
	var abc = {};
	abc = getObjParam("sForm");
	abc.agreement = {id:agreementId};
	var idx = layer.msg('正在加载数据,请稍等...', {
		  icon: 16
		  ,shade: 0.01
		  ,time:'-1'
	});
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/projectSettlement/findUnSltMaTypeList', 
        data: JSON.stringify(abc),      
        dataType:"html",
        contentType:"application/json",
        success:function(data){
        	console.log(data);
        	$("#list").html(data);
        	countMoney();
        	layer.close(idx);
		}
	})
}





function setUnitForm(){
	var unitId = localStorage.getItem("unitId");
	var unitName = localStorage.getItem("unitName");
	$("#unitId").val(unitId);
	$("#unitName").val(unitName);
	
}

function unitTree(){
	localStorage.setItem("isOpen","1");
	localStorage.setItem("unitId","");
	localStorage.setItem("unitName","");
	localStorage.setItem("unitTreeName",$("#unitName").val());
	layer.open({
		type: 2,
		title:['选择结算单位','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/company/unitTreePlus'
	});
}

function setProjectForm(){
	var projectId = localStorage.getItem("projectId");
	var projectName = localStorage.getItem("projectName");
	$("#projectId").val(projectId);
	$("#projectName").val(projectName);
	getAgreementNum();

}

function projectTree(){
	var unitId = $("#unitId").val();
	if(unitId == 0){
		JY.Model.info("请选择单位");
	}else{
		localStorage.setItem("unitId",unitId);
		localStorage.setItem("projectId","");
		localStorage.setItem("projectName","");
		localStorage.setItem("projectTreeName",$("#projectName").val());
		layer.open({
			type: 2,
			title:['工程名称','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['400px', '400px'],
			content: bonuspath+'/backstage/project/projectTree'
		});
	}
}

function getAgreementNum(){
 
	var unitId = $("#unitId").val();
	var projectId = $("#projectId").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/getAgreementCode',
			{companyId:unitId,projectId:projectId}, function(data) {
				//alert(JSON.stringify(data));
				var l = data.obj;
				if(l == null){
					$("#agreementCode").val("尚未签订协议");
				}else{
					$("#agreementCode").val(l.code);
					$("#agreementId").val(l.id);
					selectProject()
				}
			}
	);
}

function exportData(){
	var id = $("#agreementId").val();
	$("#baseForm").attr("action",bonuspath +'/backstage/projectSettlement/export?id='+id+"&iddl="+iddl);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
	/* window.location.href=bonuspath+"/backstage/projectSettlement/export?agreementId"+agreementId;   */
}

</script>
</body>
</html>