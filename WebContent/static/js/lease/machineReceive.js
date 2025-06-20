$(function() {
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
		laydate.render({
			elem: '#receiveTime'
		});
		laydate.render({
			elem: '#startTime',
			done:function (data) {
	            var endTime=$("#endTime").val();
	            if(data>endTime && endTime!=''){
	                layer.msg("开始时间不能大于结束时间");
	                $("#startTime").val('');
	            }
	        }
		});
		laydate.render({
			elem: '#endTime',
			done:function (data) {
	            var startTime=$("#startTime").val();
	            if(data<startTime && startTime!=''){
	                layer.msg("开始时间不能大于结束时间");
	                $("#endTime").val('');
	            }
	        }
		});
	});
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
});

function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/receiveDetails/expExcel');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/machineReceive/findByPage',null,function(data) {
		$("#baseTable tbody").empty();
		var obj = data.obj;
		var list = obj.list;
		var results = list.results;
		var permitBtn = obj.permitBtn;
		var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
		var html = "";
		if (results != null && results.length > 0) {
			var leng = (pageNum - 1) * pageSize;
			for (var i = 0; i < results.length; i++) {
				var l = results[i];
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.applyTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leaseCompany) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leaseProject) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.applyNumber) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.agreementCode) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leasePerson) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.phone) + "</td>";
				var shInfo = '';
			
				var wsh = l.wsh;
			
				if(	wsh != null){
					wsh = "未审核:" + wsh ;
					var shtg = l.shtg;
					shtg = "<br/>审核通过:" + shtg;
					var shbh = l.shbh;
					shbh = "<br/>审核驳回:" + shbh ;
					shInfo = wsh+shtg+shbh;
					
					var pzInfo = '';
					var wpz = l.wpz;
					wpz = "未批准:" + wpz + "";
					var pztg = l.pztg;
					pztg = "<br/>批准通过:" + pztg ;
					var pzbh = l.pzbh;
					pzbh = "<br/>批准驳回:" + pzbh;
					pzInfo = wpz+pztg+pzbh;
					
				//	html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(shInfo) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(pzInfo) + "</td>";
				}else{
					//html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
				}
			
		
				html += rowFunction(l.taskId, l.applyTime, l.leaseProjectId, l.planOutId);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='13' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}

function rowFunction(taskId, applyTime, leaseProjectId, planOutId) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='机具领取' onclick='details(&apos;" + taskId + "&apos;,&apos;" + applyTime + "&apos;,&apos;" + leaseProjectId + "&apos;,&apos;" + planOutId + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in col bigger-140'></i></a>";
	h += "<a href='#' title='领料单' onclick='receive(&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	//h += "<a href='#' title='出库检验表' onclick='check(&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-cogs col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='机具领取' onclick='details(&apos;" + taskId + "&apos;,&apos;" + applyTime + "&apos;,&apos;" + leaseProjectId + "&apos;,&apos;" + planOutId + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in col bigger-140'></i></a></li>";
	h += "<li><a href='#' title='领料单' onclick='receive(&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a></li>";
	h += "<li><a href='#' title='删除' onclick='del(&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	//h += "<li><a href='#' title='出库检验表' onclick='check(&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-cogs col bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function receive(taskId){
	saveTaskId(taskId);
	cleanForm(taskId);
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/findSheet', {
		taskId : taskId
	}, function(data) {
		//alert("data="+JSON.stringify(data));
		console.log("data=",data);
		if(data.obj.list.length != 0){
			setForm(data);
			JY.Model.check("auDiv");
		}else {
			var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
			    layer.close(indexMsg);
			});
		}
	});
}

//在页面保存taskId
function saveTaskId(taskId){
	$("#taskId0").val("");
	$("#taskId0").val(taskId);
}
//保存领料单修改的数据
function save1(){
	var taskId = $("#taskId0").val();
	var taskRemark0 = $(".taskRemark0 input").val();
	var remarkMachine = $(".remarkMachine");
	var wmaId = $(".wmaId");
	
	var remarkMachines = new Array();
	var remarkMachineString = "";
	
	var isCounts = new Array();
	var isCountString = "";
	
	var wmaIds = new Array();
	var wmaIdString = "";
	
	for(i = 0; i < remarkMachine.length; i++){
		remarkMachines[i] = $(remarkMachine[i]).val();
		remarkMachineString += remarkMachines[i] + ",";
		$(remarkMachine[i]).attr("value",remarkMachines[i]);
		wmaIds[i] = $(wmaId[i]).val();
		wmaIdString += wmaIds[i] + ",";
	}
	
	$(".taskRemark0 input").attr("value",taskRemark0);
	
	$.ajax({
		type: "POST",
		url: bonuspath + '/backstage/machineReceive/saveMaterialRequisition',
		data: {
			taskId : taskId,
			taskRemark : taskRemark0,
			remarkMachine : remarkMachineString,
			isCount : isCountString,
			id : wmaIdString
		},
		dataType: 'json',
		success: function(data) {
			JY.Model.info(data.res,function(){
				search();
			});
		},
		error: function(msg) {

		}
	});
}

 

function check(taskId){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/findCheckSheet', {
		taskId : taskId
	}, function(data) {
		console.log("data=",data);
		setCheckForm(data);
		JY.Model.check("auCheckDiv");
	});
}

function cleanForm(){
	$("#addForm input[id$='taskId']").val("");
	$("#addForm input[id$='unitName']").val("");
	$("#addForm input[id$='unitId']").val("");
	$("#addForm input[id$='projectName']").val("");
	$("#addForm input[id$='projectId']").val("");
	$("#addForm input[id$='agreementCode']").val("");
	$("#addForm input[id$='applyNumber']").val("");
	$("#addForm input[id$='leaseMan']").val("");
	$("#addForm input[id$='phone']").val("");
	$("#addForm input[id$='remark']").val("");
}

function edit(taskId){
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/find', {
		taskId : taskId
	}, function(data) {
		setAddForm(data);
		JY.Model.edit("addDiv","修改",function(){
	    	if(JY.Validate.form("addForm")){
				var that =$(this);
				JY.Ajax.doRequest("addForm",bonuspath +'/backstage/machineReceive/update',null,function(data){
				    that.dialog("close");
				    JY.Model.info(data.resMsg,function(){search();});	
				});
			}	
	    });
	});
}

function del(taskId){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/machineReceive/del',{
			taskId : taskId
		},function(data){
			JY.Model.info(data.resMsg,function(){search();});
		});
	});
}

function setAddForm(data){	
	var l = data.obj.list;
	$("#addForm input[id$='taskId']").val(JY.Object.notEmpty(l.taskId));
	$("#addForm input[id$='unitName']").val(JY.Object.notEmpty(l.leaseCompany));
	$("#addForm input[id$='unitId']").val(JY.Object.notEmpty(l.leaseCompanyId));
	$("#addForm input[id$='projectName']").val(JY.Object.notEmpty(l.projectName));
	$("#addForm input[id$='projectId']").val(JY.Object.notEmpty(l.projectId));
	$("#addForm input[id$='agreementCode']").val(JY.Object.notEmpty(l.agreementCode));
	$("#addForm input[id$='applyNumber']").val(JY.Object.notEmpty(l.applyNumber));
	$("#addForm input[id$='leaseMan']").val(JY.Object.notEmpty(l.leaseMan));
	$("#addForm input[id$='phone']").val(JY.Object.notEmpty(l.phone));
	$("#addForm input[id$='remark']").val(JY.Object.notEmpty(l.remark));
}
var results={}
function setForm(data) {
	results = data.obj.list;
	var html = "";
	$(".basic").html("");
	$(".add").remove();
	if(results.length != 0){
		if(results[0].typeId != null){
			$("#dd").show();
			$("#morePage").hide();
				for(var i = 0;i < results.length;i++){
					var remarkMachine;
					if(i < 5){
						var wmaId = $(".wmaId");
						if(results[i].id != null && results[i].id != ""){
							$(wmaId[i]).attr("value",results[i].id);
						}else{
							$(wmaId[i]).attr("value","-1");
						}
						$("#number"+i).html(i+1);
						$("#id"+i).html(results[i].deviceCode);
						$("#machinesType"+i).html(results[i].machineType);
						$("#machinesModel"+i).html(results[i].machineName);
						$("#unit"+i).html(results[i].machineModel);
						$("#isFixedAssets"+i).html(results[i].isFixedAssets);
						$("#machinesNum"+i).html(results[i].machineUnit);
						$("#weight"+i).html(results[i].thisOutNum);
						if(results[i].remark != "" && results[i].remark != "null" && results[i].remark != null){
							$("#remarkMachine"+i).html(results[i].remark);
						}else{
							$("#remarkMachine"+i).html("");
						}
						
						var taskId = results[i].taskId;
						var modelId = results[i].typeId;
						if(results[i].remarkMachine != null && results[i].remarkMachine != ""){
							remarkMachine = results[i].remarkMachine;
						}else{
							remarkMachine = "";
						}
						
						if(results[i].isCount == 0 || results[i].isCount == '0'){
							$("#remark"+i).html('<input type="text" class="remarkMachine" name="remarkMachine" value="'+remarkMachine+'" style="border: 0;width:50px;" class="projectName basic FormElement ui-widget-content ui-corner-all">');
						}else{
							$("#remark"+i).html('<input type="text" class="remarkMachine" name="remarkMachine" value="'+remarkMachine+'" style="border: 0;width:50px;" class="projectName basic FormElement ui-widget-content ui-corner-all">');
						}
					}else{
						if(results[i].remarkMachine != null && results[i].remarkMachine != ""){
							remarkMachine = results[i].remarkMachine;
						}else{
							remarkMachine = "";
						}
						var wmaId;
						if(results[i].id != null && results[i].id != ""){
							wmaId = results[i].id;
						}else{
							wmaId =  "-1";
						}
						var temp = "";
						html += '<tr class="add">';
						html += '<input type="hidden" class="wmaId" value="'+wmaId+'">';
						html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+(i+1)+'</td>';
						if(results[i].deviceCode != null) {
							html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].deviceCode+'</td>';
						} else {
							html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+temp+'</td>';
						}
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineType+'</td>';
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineName+'</td>';
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineModel+'</td>';
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].isFixedAssets+'</td>';
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineUnit+'</td>';
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].thisOutNum+'</td>';
						if(results[i].remark != "" && results[i].remark != "null" && results[i].remark != null){
							html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].remark+'</td>';
						}else{
							html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"></td>';
						}
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family:SimSun"><input type="text" class="remarkMachine" name="remarkMachine" value="'+remarkMachine+'" style="border: 0;width:50px;" class="projectName basic FormElement ui-widget-content ui-corner-all"></td>';
						html += '</tr>';
					}
				}
				$("#five").after(html);
		}
	}
	if(results.length > 0){
		debugger;
		$(".subcontractors").html(results[0].subcontractors);//分包商
		$(".leaseCompany").html(results[0].leaseCompany);
		$(".projectName").html(results[0].leaseProject);
		$(".applyTime").html(results[0].applyTime);
		$(".applyNumber").html(results[0].applyNumber);
		if (results[0].isConfirm != null && results[0].isConfirm === 1) {
			const confirmButtonDom = '<td colspan="3" style="text-align: right; "><span style="color: #2f952d">已确认出库</span></td>';
			$("#leaseFormTrDom").html(confirmButtonDom);
		} else {
			const confirmButtonDom = '<td colspan="3" style="text-align: right; "><button class="layui-btn" onclick="onclickConfirm()">请确认发货</button></td>';
			$("#leaseFormTrDom").html(confirmButtonDom);
		}

		var taskRemark;
		if(results[0].taskRemark == "" || results[0].taskRemark == null){
			taskRemark = "";
		}else{
			taskRemark = results[0].taskRemark;
		}
		$(".taskRemark0").html('备注：<input type="text" value="'+taskRemark+'" style="border: 0;width: 90%;" class="projectName basic FormElement ui-widget-content ui-corner-all">');
		$(".examineUrl").html("");
		$(".approvalUrl").html("");
		$(".opeatorUrl").html("");
		if(results[0].examineUrl != null && results[0].examineUrl != ""){
			var html1 = "";
			var picUrl = results[0].examineUrl.replaceAll(/\\/g,"@");
			var s = picUrl.replaceAll("@","/");
			var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
			var path = l;
			html1 += '<img class="img" style="width: 100px;height: 90px;" src="' + path + '" >';
			
		}
		if(results[0].approvalUrl != null && results[0].approvalUrl != ""){
			var html2 = "";
			var picUrl = results[0].approvalUrl.replaceAll(/\\/g,"@");
			var s = picUrl.replaceAll("@","/");
			var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
			var path = l;
			html2 += '<img class="img" style="width: 110px;height: 90px;" src="' + path + '" >';
		
		}
		if(results[0].opeatorUrl != null && results[0].opeatorUrl != ""){
			var html3 = "";
			var html1 = "";
			var picUrl = results[0].opeatorUrl.replaceAll(/\\/g,"@");
			var s = picUrl.replaceAll("@","/");
			var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
			var path = l;
			html3 += '<img class="img" style="width: 80px;height: 50px;" src="' + path + '" >';
		
		}
	}
}

function jqprinting1() {
	var pageRows = 20;
	var page = 1;
	var pages = Math.ceil(results.length/pageRows)
	console.log(pages)
	console.log('results+++',results)
	var html = "";
	// if(pages==1){
	// 	$("#dd").show();
	// 	$("#morePage").hide();
	// 	$("#morePage").empty();
	// }else{
		$("#dd").hide();
		$("#morePage").show();
		$("#morePage").empty();
		for(var i = 0;i < results.length;i++){
			if(results[i].remarkMachine != null && results[i].remarkMachine != ""){
				remarkMachine = results[i].remarkMachine;
			}else{
				remarkMachine = "";
			}
			var wmaId;
			if(results[i].id != null && results[i].id != ""){
				wmaId = results[i].id;
			}else{
				wmaId =  "-1";
			}
			var temp = "";
			html += '<tr class="add">';
			html += '<input type="hidden" class="wmaId" value="'+wmaId+'">';
			html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+(i+1)+'</td>';
			if(results[i].deviceCode != null) {
				html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].deviceCode+'</td>';
			} else {
				html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+temp+'</td>';
			}
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineType+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineName+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineModel+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].isFixedAssets+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineUnit+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].thisOutNum+'</td>';
			if(results[i].remark != "" && results[i].remark != "null" && results[i].remark != null){
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].remark+'</td>';
			}else{
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"></td>';
			}
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family:SimSun"><input type="text" class="remarkMachine" name="remarkMachine" value="'+remarkMachine+'" style="border: 0;width:50px;" class="projectName basic FormElement ui-widget-content ui-corner-all"></td>';
			html += '</tr>';
			if(i==(page*pageRows-1)||i==(results.length-1)){

				let HTML;
				if (results[0].isConfirm != null && results[0].isConfirm === 1) {
					HTML = `
					<tr>
						<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
							贵州送变电有限责任公司
						</td>
					</tr>
					<tr>
						<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
							领　料　单
						</td>
						<td colspan="3" style="text-align: right; "><span style="color: #2f952d">已确认出库</span></td>
					</tr>
					`;
				} else {
					HTML = `
					<tr>
						<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
							贵州送变电有限责任公司
						</td>
					</tr>
					<tr>
						<td colspan="9" style="text-align: center; font-size: 1.4em;font-weight:bold;">
							领　料　单
						</td>
					</tr>
					`;
				}



				HTML = HTML + `
					
					<tr>
						<td colspan="5"><span style="font-weight: bold;font-family:'宋体'">单据编号：</span><span class="applyNumber"></span></td>
						<td colspan="5" style="height:30px;font-family:'宋体'"><span style="font-weight: bold;" >制单日期：</span><span class="applyTime"></span></td>
					</tr>
					
					<tr>
						<td colspan="3" style="height:30px;width:20%;font-family:'宋体';border-top: 1px solid #000000;border-left: 1px solid #000000;">工程名称：<span class="projectName"></span></td>
						<td colspan="3" style="height:30px;width:20%;font-family:'宋体';border-top: 1px solid #000000;border-left: 1px solid #000000;">租赁单位：<span class="leaseCompany"></span></td>
						<td colspan="4" style="border-top: 1px solid #000000;width:20%;font-family:'宋体';border-left: 1px solid #000000;border-right:1px solid #000000;">领料方：<span class="subcontractors"></span></td>
					</tr>
					<tr>
						<td colspan="10" class="taskRemark0" style="height:30px;font-family:'宋体';border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;">&emsp;备注：
						</td>
					</tr>
					<tr>
						<td style="width:3%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">序号</td>
						<td style="width:10%;height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">资产编号</td>
						<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">资产类别</td>
						<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">设备名称</td>
						<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">规格型号</td>
						<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">资产</td>
						<td style="width:3%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">单位</td>
						<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">数量</td>
						<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:'宋体'">设备编号</td>
						<td style="width:10%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;font-family:'宋体'">备注</td>
					</tr>
						`+html+`
					<tr style="height: 50px">
						<td colspan="10" style="height:30px;font-weight: bold;border-top: 1px solid #000000;font-family:'宋体'"><span style="color:red">备注：以上货物完好无损，质量无缺陷，确认无误后由领用人签字。</span></td>
					</tr>
					
					<tr style="height: 50px">
						<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'"><span >制单：</span><span class="opeatorUrl"></td>
						<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'">领用人：</td>
					</tr>
					
					<tr style="height: 50px">
						<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'">库工：</td>
						<td colspan="5" style="height:30px;font-weight: bold;font-family:'宋体'">出库确认：</td>
					</tr>
					
				
				`;
				page = page+1;
				$("#morePage").append(HTML);
				HTML = ''
				html = ''
			// }
		}
		}
		if(results.length > 0){
			$(".subcontractors").html(results[0].subcontractors);//分包商
			$(".leaseCompany").html(results[0].leaseCompany);
			$(".projectName").html(results[0].leaseProject);
			$(".applyTime").html(results[0].applyTime);
			$(".applyNumber").html(results[0].applyNumber);
			var taskRemark;
			if(results[0].taskRemark == "" || results[0].taskRemark == null){
				taskRemark = "";
			}else{
				taskRemark = results[0].taskRemark;
			}
			$(".taskRemark0").html('备注：<input type="text" value="'+taskRemark+'" style="border: 0;width: 90%;" class="projectName basic FormElement ui-widget-content ui-corner-all">');
			$(".examineUrl").html("");
			$(".approvalUrl").html("");
			$(".opeatorUrl").html("");
			if(results[0].examineUrl != null && results[0].examineUrl != ""){
				var html1 = "";
				var picUrl = results[0].examineUrl.replaceAll(/\\/g,"@");
				var s = picUrl.replaceAll("@","/");
				var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
				var path = l;
				html1 += '<img class="img" style="width: 100px;height: 90px;" src="' + path + '" >';
				
			}
			if(results[0].approvalUrl != null && results[0].approvalUrl != ""){
				var html2 = "";
				var picUrl = results[0].approvalUrl.replaceAll(/\\/g,"@");
				var s = picUrl.replaceAll("@","/");
				var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
				var path = l;
				html2 += '<img class="img" style="width: 110px;height: 90px;" src="' + path + '" >';
			
			}
			if(results[0].opeatorUrl != null && results[0].opeatorUrl != ""){
				var html3 = "";
				var html1 = "";
				var picUrl = results[0].opeatorUrl.replaceAll(/\\/g,"@");
				var s = picUrl.replaceAll("@","/");
				var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
				var path = l;
				html3 += '<img class="img" style="width: 80px;height: 50px;" src="' + path + '" >';
			
			}
		}
	setTimeout(()=>{
		$("#dd").show();
		$("#morePage").hide();
	},200)
	$("#morePage").jqprint({ operaSupport: false });

}

function setCheckForm(data) {
	var results = data.obj.list;
	var html = "";
	$(".basic").html("");
	$(".add").remove();
	if(results.length > 0){
		$("#leaseCompanyName").html(results[0].leaseCompany);
		$("#leaseProject").html(results[0].projectName);
		if(results[0].machinesType != null){
			createSeal('canvas','安徽送变电工程有限公司机具设备分公司','检验专用章');
			for(var i = 0;i < results.length;i++){
				if(i < 10){
					$(".typeName"+i).html(results[i].machinesType);
					$(".modelName"+i).html(results[i].machinesModel);
					$(".unit"+i).html(results[i].unit);
					$(".machinesNum"+i).html(results[i].actualNum);
					$(".deviceCode"+i).html(results[i].deviceCode);
					$(".ratedLoad"+i).html(results[i].ratedLoad);
					$(".testLoad"+i).html(results[i].testLoad);
					$(".holdingTime"+i).html(results[i].holdingTime);
					$(".testTime"+i).html(results[i].testTime);
					$(".nextTestTime"+i).html(results[i].nextTestTime);
					$(".checkResults"+i).html("合格");
				}else{
					html += '<tr class="add">';
					html += '<td style="height:30px;text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].machinesType+'</td>';
					html += '<td style="text-align:center; width:16%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].machinesModel+'</td>';
					html += '<td style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].unit+'</td>';
					html += '<td style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].actualNum+'</td>';
					html += '<td style="text-align:center; width:4%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].deviceCode+'</td>';
					html += '<td style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].ratedLoad+'</td>';
					html += '<td style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].testLoad+'</td>';
					html += '<td style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].holdingTime+'</td>';
					html += '<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].testTime+'</td>';
					html += '<td style="text-align:center; width:10%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">'+results[i].nextTestTime+'</td>';
					html += '<td style="text-align:center; width:5%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family: simsun;">合格</td>';
					html += '<td style="text-align:center; width:20%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-family: simsun;"></td>';
					html += '</tr>';
				}
			}
		}
		$(".ten").after(html);
	}
}

function findDeviceNumDetils(taskId,modelId){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/findDeviceNumDetils', {
		taskId : taskId,
		modelId:modelId
	}, function(data) {
		setDetilsForm(data);
	});
}

function setDetilsForm(data) {
	var list = data.obj.list;
	if(list.length > 0){
		JY.Model.check("auDetailDiv");
		var html="";
		$(".addDetails").remove();
		for(var i = 0;i < list.length;i++){
			html+='<tr class="addDetails">';
			html+='<td style="height:30px;text-align:center;width: 10%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-family:SimSun">'+(i+1)+'</td>';
			html+='<td style="text-align:center;width: 30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-family:SimSun">'+list[i].type+'</td>';
			html+='<td style="text-align:center;width: 30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-family:SimSun">'+list[i].model+'</td>';
			html+='<td style="text-align:center;width: 30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun">'+list[i].deviceCode+'</td>';
			html+='</tr>';
		}
		$("#header").after(html);
	}else{
		//墨绿深蓝风
		layer.alert('目前机具尚未领取', {
		  skin: 'layui-layer-molv' //样式类名
		  ,closeBtn: 0
		});
	}
	
}

function search() {
	$("#searchBtn").trigger("click");
}

function codeEnable(){
	var agreementCode = $("#agreementCode ").val();
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/agreement/findAgreeCodeId',
		data: {
			code : agreementCode
		},
		dataType: "json",
		success: function(data) {
			var agreementCodeId = data.obj.list.id;
			if(agreementCodeId != null || agreementCodeId != ''){
				insert();
			}else{
				//墨绿深蓝风
				layer.alert('协议号不存在!', {
				  skin: 'layui-layer-molv' //样式类名
				  ,closeBtn: 0
				});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function insert(){
	JY.Model.edit("addDiv", "新增", function() {
		if (JY.Validate.form("addForm")) {
			var that = $(this);
			var agreementCode = $("#agreementCode ").val();
			$.ajax({
				type: "post",
				url: bonuspath + '/backstage/agreement/findAgreeCodeId',
				data: {
					code : agreementCode
				},
				dataType: "json",
				success: function(data) {
					var res = data.obj;
					if(res != null && res != "null"){
						var remark = $("#remark").val();
						JY.Ajax.doRequest("addForm", bonuspath + '/backstage/task/add',
								{ 
									name:'领料申请',
									type:'2',
									status:'1',
									remark : remark
								}, function(data) {
									var id = data.obj.id;
									var creator = data.obj.creator;
									var createTime = data.obj.createTime;
									var remark = data.obj.remark;
									findAgreeCodeId(id,creator,createTime,remark);
									that.dialog("close");
									JY.Model.info(data.resMsg, function() {
										 search();
									});
							});
					}else{
						//墨绿深蓝风
						layer.alert('协议号不存在!', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("未连接到服务器，请检查网络！");
				}

			});
			
		}
	});
}

function findAgreeCodeId(id,creator,createTime,remark){
	var agreementCode = $("#agreementCode").val();
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/agreement/findAgreeCodeId',
		data: {
			code : agreementCode
		},
		dataType: "json",
		success: function(data) {
			var agreementCodeId = data.obj.list.id;
			addAgreementTask(id,creator,createTime,remark,agreementCodeId);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function addAgreementTask(id,creator,createTime,remark,agreementCodeId){
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/agreeTask/add',
		data: {
			taskId : id,
			agreementId : agreementCodeId
		},
		dataType: "json",
		success: function(data) {
			addCollar(id,creator,createTime,remark);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

// 确认发货
function onclickConfirm(){
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/machineReceive/updateConfirmStatus',
		data: {
			taskId : $("#taskId0").val()
		},
		dataType: "json",
		success: function(data) {
			alert(data.resMsg  + ",请重新打开后查看");
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function addCollar(id,creator,createTime,remark){
	var applyNumber = $("#applyNumber").val();
	var leaseMan = $("#leaseMan ").val();
	var phone = $("#phone ").val(); 
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/machineReceive/add',
		data: {
			taskId : id,
			applyMan : creator,
			createTime : createTime,
			remark : remark,
			applyNumber : applyNumber,
			leaseMan : leaseMan,
			phone : phone,
			remark : remark
		},
		dataType: "json",
		success: function(data) {},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function findApplyNumber(){
	$("#baseForm").attr("onsubmit","return false;");
	$("#unitId").val('');
	$("#projectId").val('');
	$("#unitName").val('');
	$("#projectName").val('');
	$("#agreementCode").val('');
	$("#leaseMan").val('');
	$("#phone").val('');
	$("#remark").val('');
	localStorage.setItem("unitId","");
	localStorage.setItem("unitName","");
	localStorage.setItem("projectId","");
	localStorage.setItem("projectName","");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machineReceive/findApplyNumber',{},function(data){
		var applyNumber = data.resMsg;
		if(applyNumber != null && applyNumber != ''){
			$("#applyNumber").val(applyNumber);
			insert();
		}else{
			alert("请联系相关人员！");
		}
	});
}

function details(taskId, applyTime, leaseProjectId, planOutId){
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("applyTime",applyTime);
	localStorage.setItem("leaseProjectId",leaseProjectId);
	localStorage.setItem("leasePlanOutId",planOutId);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['领取机具','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1000px', '600px'],
	  content:bonuspath + '/backstage/receiveDetails/list'
	});

}
function getAgreementNum(){
	$(".leaseMan").val("");
	var unitId = $("#unitId").val();
	var projectId = $("#projectId").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/findAgreeCode',
			{leaseCompany:unitId,projectName:projectId}, function(data) {
				var l = data.resMsg;
				if(l == null){
					$("#agreementCode").val("尚未签订协议");
				}else{
					$("#agreementCode").val(l);
				}
			}
	);
}

function setUnitForm(){
	var unitId = localStorage.getItem("unitId");
	var unitName = localStorage.getItem("unitName");
	$("#unitId").val(unitId);
	$("#unitName").val(unitName);
}

function setProjectForm(){
	var projectId = localStorage.getItem("projectId");
	var projectName = localStorage.getItem("projectName");
	$("#projectId").val(projectId);
	$("#projectName").val(projectName);
	getAgreementNum();
}

function unitTree(){
	localStorage.setItem("unitId","");
	localStorage.setItem("unitName","");
	localStorage.setItem("unitTreeName",$("#unitName").val());
	layer.open({
		type: 2,
		title:['租赁单位','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/company/unitTree'
	});
}

function projectTree(){
	var unitId = $("#unitId").val();
	if(unitId == 0){
		JY.Model.info("请选择租赁单位");
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

function createSeal(id, company, name) {
	$("#"+id).html("");
	$("#img").css("display","none");
	var canvas = document.getElementById(id);
	var context = canvas.getContext('2d');
	canvas.height=canvas.height;
	// 绘制印章边框   
	var width = canvas.width / 2;
	var height = canvas.height / 2;
	context.lineWidth = 3;
	context.strokeStyle = "#f00";
	context.beginPath();
	context.arc(width, height, 70, 0, Math.PI * 2);
	context.stroke();

	//画五角星
	create5star(context, width, height, 20, "#f00", 0);

	// 绘制印章名称   
	context.font = '18px Helvetica';
	context.textBaseline = 'middle'; //设置文本的垂直对齐方式
	context.textAlign = 'center'; //设置文本的水平对对齐方式
	context.lineWidth = 1;
	context.fillStyle = '#f00';
	context.fillText(name, width, height + 45);

	// 绘制印章单位   
	context.translate(width, height); // 平移到此位置,
	context.font = '15px Helvetica'
	var count = company.length; // 字数   
	var angle = 4 * Math.PI / (3 * (count - 1)); // 字间角度   
	var chars = company.split("");
	var c;
	for(var i = 0; i < count; i++) {
		c = chars[i]; // 需要绘制的字符   
		if(i == 0)
			context.rotate(5 * Math.PI / 6);
		else
			context.rotate(angle);
		context.save();
		context.translate(60, 0); // 平移到此位置,此时字和x轴垂直   
		context.rotate(Math.PI / 2); // 旋转90度,让字平行于x轴   
		context.fillText(c, 0, 5); // 此点为字的中心点   
		context.restore();
	}

	//绘制五角星  
	/** 
	 * 创建一个五角星形状. 该五角星的中心坐标为(sx,sy),中心到顶点的距离为radius,rotate=0时一个顶点在对称轴上 
	 * rotate:绕对称轴旋转rotate弧度 
	 */
	function create5star(context, sx, sy, radius, color, rotato) {
		context.save();
		context.fillStyle = color;
		context.translate(sx, sy); //移动坐标原点  
		context.rotate(Math.PI + rotato); //旋转  
		context.beginPath(); //创建路径  
		var x = Math.sin(0);
		var y = Math.cos(0);
		var dig = Math.PI / 5 * 4;
		for(var i = 0; i < 5; i++) { //画五角星的五条边  
			var x = Math.sin(i * dig);
			var y = Math.cos(i * dig);
			context.lineTo(x * radius, y * radius);
		}
		context.closePath();
		context.stroke();
		context.fill();
		context.restore();
	}
}
