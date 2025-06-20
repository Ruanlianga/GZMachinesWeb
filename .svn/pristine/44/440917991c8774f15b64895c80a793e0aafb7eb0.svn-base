var ids = '';
$(function() {
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
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
	getbaseList(1);
});

function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/receiveDetails/expExcel');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}

function checkTree(){
	localStorage.setItem("checkId","");
	localStorage.setItem("checkName","");
	localStorage.setItem("checkTreeName",$("#checkName").val());
	layer.open({
		type: 2,
		title:['检验人员','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/user/checkTree'
	});
}
function setCheckForm(){
	var checkId = localStorage.getItem("checkId");
	var checkName = localStorage.getItem("checkName");
	$("#checkId").val(checkId);
	$("#checkName").val(checkName);
}

function backShowChenkStatus(){
	$('input[type=checkbox]:checked').each(function(){
		var id = $(this).val();	
		console.log("id=",id);
		ids += id+","; 
		console.log("ids=",ids);	
	})
}
function deleteTask() {
	backShowChenkStatus();	
	var idsStr = ids;
	if(idsStr == "" ){
		layer.msg("请选择想要删除的信息!",{icon:7,time:2000});
		return;
	}
	
	JY.Model.confirm("确认删除吗？", function() {
		console.log("idsStr=",idsStr);
		var idx = layer.msg('删除申请提交中,请稍等...', {
			  icon: 16
			  ,shade: 0.01
			  ,time:'-1'
		});
		var data = {
			ids:idsStr	
		}
		$.ajax({
			type:'POST',
			url:bonuspath+'/backstage/rm/task/deleteTask',
			async:true,
			data:data,
			success:function(data) {
				data = JSON.parse(data);
				layer.msg("删除成功!",{icon:1,time:2000},function(){
					getbaseList(1);
					ids = {};
	            });
				layer.close(idx);
			},
			error:function(data){
				layer.msg("删除失败!",{icon:2,time:2000});
				layer.close(idx);
			}	
		});
	});
}

function getbaseList(init) {
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/rm/task/findByPage',null,function(data) {
		$("#baseTable tbody").empty();
		var obj = data.obj;
		var list = obj.list;
		var results = list.results;
		var permitBtn = obj.permitBtn;
		var pageNum = list.pageNum, 
			pageSize = list.pageSize, 
			totalRecord = list.totalRecord;
		var html = "";
		if (results != null && results.length > 0) {
			var leng = (pageNum - 1) * pageSize;
			for (var i = 0; i < results.length; i++) {
				var l = results[i];
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center'><input type='checkbox' id='inp' value='" + l.id + "'></td>";
				html += "<td style='vertical-align:middle;' class='center hidden-480'>"+ (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.returnMaterialTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unitName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.workName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.subcontractors) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.agreementCode) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.number) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.userName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.phone) + "</td>";
				var checker = l.checker;
				if(checker == "" || checker == null){
					checker = "尚未指派";
				}
				html += "<td style='vertical-align:middle;cursor:pointer;' class='center hidden-480'><a href='#' onclick='chooseChecker("+ l.id +")'>" + checker + "</a></td>";
				var isFinish = l.isFinish;
				if(l.isFinish == 0){
					isFinish = "待退料";
				}else{
					isFinish = "已退料";
				}
				html += "<td style='vertical-align:middle;' class='center'>"+ isFinish + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
				html += rowFunction(l.id,l.number,l.isFinish);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='14' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}

function rowFunction(id,number,isfinish) {
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(isfinish != 0){
	h+="<a href='#' title='查看' onclick='rmDoc(&apos;"+id+"&apos;,&apos;"+number+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	}
	h+="</div>";
	h+="<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(isfinish != 0){
	h+="<li><a href='#' title='查看' onclick='rmDoc(&apos;"+id+"&apos;,&apos;"+number+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	}
	h+="</ul></div></div>";		
	h+="</td>";
	return h;
}

function chooseChecker(id) {
	cleanForm();
	JY.Model.edit("auDiv", "指派客服代表", function() {
		var that = $(this);
		if (JY.Validate.form("auForm")) {
			var checkId = $("#checkId").val();
			JY.Ajax.doRequest(null, bonuspath+'/backstage/rm/task/updateChecker', 
				{
				id:id,
				checkerId:checkId,
				}, 
				function(data) {
			JY.Model.info(data.resMsg, function() {
				that.dialog("close");
				$("#serviceId").val('');
				getbaseList(1);
			});
			});
		}
	});
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
//	hideRole();
}

function details(taskId){
	var unitId = localStorage.setItem("TaskId",taskId);
	layer.open({
		type: 2,
		title:['退料详情','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['1000px', '550px'],
		content: bonuspath+'/backstage/lease/back/returnDetails'
	});
}

function del(id){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/lease/back/del',{taskId:id},function(data){
			JY.Model.info(data.resMsg,function(){getbaseList(1);});
		});
	});
}

function add(){
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/rm/task/findNumber',
		data: {},
		dataType: "json",
		success: function(data) {
			var number = data.number;
			var subcontractorsList = data.subcontractorsList;//分包商集合
			if(number != null && number != ''){
				cleanAddForm();	
				$("#number").val(number);
				var number = $("#number").val();
				if(subcontractorsList.length > 0){//判断分包商不为空
					var html;
					html += '<option value="-1">请选择</option>';
					for(var i = 0; i < subcontractorsList.length; i++){
						html += '<option value="'+subcontractorsList[i].id+'">'+subcontractorsList[i].name+'</option>';
					}
					$("#subcontractors").empty();
					$("#subcontractors").append(html);
				}
				
				JY.Model.edit("auAddDiv","新增",function(){
					var unitName = $("#unitName").val();
					var projectName = $("#projectName").val();
					if(unitName == ""){
						layer.msg("请选择退料单位");
						return false;
					}
					if(projectName == ""){
						layer.msg("请选择退料工程");
						return false;
					}
					 if(JY.Validate.form("auAddForm")){
						 var that =$(this);
						 JY.Ajax.doRequest("auAddForm",bonuspath +'/backstage/rm/task/add',null,function(data){
						     that.dialog("close");      
						     JY.Model.info(data.resMsg,function(){getbaseList(1);});
						 });
					 }	 
				});
			}else{
				alert("请联系相关人员！");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}
	});
}

function getChildrenId(array,type){
	var ids = '';
	if(type==0){
		for(var i=0;i<array.length;i++){
			if (!array[i].isParent){
				ids+=array[i].id+',';
		    }
		}
	}else{
		for(var i=0;i<array.length;i++){
			if (!array[i].isParent){
				ids+=array[i].name+'/';
		    }
		}
	}
     return ids;
}

function cleanAddForm(){
	JY.Tags.cleanForm("auAddForm");
	$("#backCompanyId").html("");
	$("#backProjectId").html("");
	$("#auAddForm input[name$='userName']").val("");//上级资源
	$("#auAddForm input[name$='phone']").val("");
	$("#auAddForm input[name$='number']").val("");
	$("#auAddForm input[name$='remark']").val("");
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

function rmDoc(id,backOddNumbers){
	saveTaskId(id);
	JY.Ajax.doRequest(null, bonuspath + '/backstage/rm/taskRecord/findSheet', {
		id : id
	}, function(data) {
		setForm(data,backOddNumbers);
		JY.Model.check("auDocDiv");
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
	var taskRemark0 = $("#taskRemark0 input").val();
	var remarkMachine = $(".remarkMachine");
	
	var remarkMachineString = "";
	
	var isCounts = new Array();
	var isCountString = "";
	
	var wmaIds = new Array();
	var wmaIdString = "";
	for(i = 0; i < remarkMachine.length; i++){
		var remark = $(remarkMachine[i]).val();
		console.log(remark);
		if(remark == null || remark == ""){
			remark = " ";
		}
		remarkMachineString += remark + ",";
		$(remarkMachine[i]).attr("value",remark);
		if(i == remarkMachine.length - 1){
			$("#taskRemark0 input").attr("value",taskRemark0);
			$.ajax({
				type: "POST",
				url: bonuspath + '/backstage/rm/taskRecord/saveMaterialRequisition',
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
	}
}
function setForm(data,backOddNumbers) {
	var results = data.obj.list;
	var html = "";
	var rmStatus;
	console.log("results",results);
	if(results.length > 0){
		var date = results[0].returnMaterialTime;
		var e = new Array();
		var d = new Array();
		e = date.split(" ");
		d = e[0].split("-");
		$(".year").html(d[0]);
		$(".month").html(d[1]);
		$(".day").html(d[2]);
		$("#backProjectName").html(results[0].leaseName);
		$("#backCompanyName").html(results[0].projectName);
		$("#backOddNumbers").html(backOddNumbers);
		$("#subcontractorName").html(results[0].subcontractorName);
		var taskRemark;
		if(results[0].taskRemark == "" || results[0].taskRemark == null){
			taskRemark = "";
		}else{
			taskRemark = results[0].taskRemark;
		}
		$("#taskRemark0").html('备注：<input type="text" value="'+taskRemark+'" style="border: 0;width: 90%;" class="projectName basic FormElement ui-widget-content ui-corner-all">');
	}
	$(".basic").html("");
	$(".add").remove();
	console.log("results[0]",results[0]);
	if(results.length > 0){
		if(results[0].thisBackNum != 0){
			for(var i = 0;i < results.length;i++){
				var remarkMachine;
				if(i < 5){
					$("#id"+i).html(i+1);
					$("#typeName"+i).html(results[i].maType);
					$("#modelName"+i).html(results[i].maModel);
					$("#unit"+i).html(results[i].unit);
					$("#backNum"+i).html(results[i].thisBackNum);
					$("#code"+i).html(results[i].deviceCode);
					if(results[i].rmStatus == 1 ){
						rmStatus = "合格入库";
					}else if(results[i].rmStatus == 4 || results[i].rmStatus == 3){
						rmStatus = "待报废 ";
					}else if(results[i].rmStatus == 7 || results[i].rmStatus == 2 || results[i].rmStatus == 5){
						rmStatus = "待修 ";
					}
					$("#weight"+i).html(rmStatus);
					var modelId = results[i].modelId;
					
					
					if(results[i].remark != null && results[i].remark != "" && results[i].remark != "null"){
						$("#remarkMachine"+i).html(results[i].remark);
					}else{
						$("#remarkMachine"+i).html("");
					}
					
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
					if(results[i].rmStatus == 1 ){
						rmStatus = "合格入库";
					}else if(results[i].rmStatus == 4 || results[i].rmStatus == 3){
						rmStatus = "待报废 ";
					}else if(results[i].rmStatus == 7 || results[i].rmStatus == 2 || results[i].rmStatus == 5){
						rmStatus = "待修 ";
					}
					//1:合格2:维修3:待报废4:确认待报废5:维修合格6:维修申请报废7:退料已维修(2改为7)8:维修检验通过10:维修检验报废通过11:维修检验完成
					if(results[i].remarkMachine != null && results[i].remarkMachine != ""){
						remarkMachine = results[i].remarkMachine;
					}else{
						remarkMachine = "";
					}
					html += '<tr class="add">';
					html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+(i+1)+'</td>';
					html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+results[i].maType+'</td>';
					html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+results[i].maModel+'</td>';
					html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+results[i].unit+'</td>';
					html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+results[i].thisBackNum+'</td>';
					if(results[i].deviceCode  ==null || results[4].deviceCode =="" || results[i].deviceCode == "null") {
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>';	
					}else{
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+results[i].deviceCode+'</td>';	
					}
					html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+ rmStatus +'</td>';
					
					if(results[i].remark != null && results[i].remark != "" && results[i].remark != "null"){
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+ results[i].remark +'</td>';
					}else{
						html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;"></td>';
					}
					html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;"><input type="text" class="remarkMachine" name="remarkMachine" value="'+remarkMachine+'" style="border: 0;width:50px;" class="projectName basic FormElement ui-widget-content ui-corner-all"></td>';
					html += '</tr>';
					
				}
			}
			$("#examineUser").html("");
			$("#approvalUser").html("");
			if(results[0].examineUser != null && results[0].examineUser != ""){
				var html1 = "";
				var picUrl = results[0].examineUser.replaceAll(/\\/g,"@");
				var s = picUrl.replaceAll("@","/");
			    var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
			    var path = l;
				html1 += '<img class="img" style="width: 80px;height: 50px;" src="' + path + '" >';
				$("#examineUser").append(html1);
			}
			if(results[0].approvalUser != null && results[0].approvalUser != ""){
				var html2 = "";
				var picUrl = results[0].approvalUser.replaceAll(/\\/g,"@");
				var s = picUrl.replaceAll("@","/");
			    var l =bonuspath + '/backstage/user/downFile?headerUrl='+s;
			    var path = l;
				 html2 += '<img class="img" style="width: 80px;height: 50px;" src="' + path + '" >';
				 $("#approvalUser").append(html2);
			}
		}
	}
	$("#five").after(html);
}

//浮点数加法运算
function floatAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    return (arg1*m+arg2*m)/m;
}

function setDetailsForm(data){
	var list = data.obj.list;
	var html="";
	$(".addDetails").remove();
	for(var i = 0;i < list.length;i++){
		html+='<tr class="addDetails">';
		html+='<td style="height:30px;text-align:center;width: 10%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-family:SimSun">'+(i+1)+'</td>';
		html+='<td style="text-align:center;width: 30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-family:SimSun">'+list[i].typeName+'</td>';
		html+='<td style="text-align:center;width: 30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-family:SimSun">'+list[i].modelName
		html+='<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun">'+list[i].deviceNum+'</td>';
		var status = list[i].batchStatus;
		switch (status) {
		case "5":
			status = "在库";
			break;
		case "6":
			status = "在用";
			break;
		case "7":
			status = "在修";
			break;
		case "8":
			status = "在试";
			break;
		case "10":
			status = "待报废";
			break;
		case "11":
			status = "已报废";
			break;
		case "12":
			status = "报废封存";
			break;
		case "9":
			status = "修试后待入库";
			break;
		case "15":
			status = "已报废移交";
			break;
		default:
			break;
		}
		html+='<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun">'+status+'</td>';
		html+='</tr>';
	}
	$("#headerRepair").after(html);
}

function findDeviceCodeDetils(id,modelId){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/back/findDeviceCodeDetils', {
		taskId : id,
		modelId:modelId
	}, function(data) {
		setDetilsForm(data);
		JY.Model.check("auDetailDiv");
	});
}

function findDeviceCodeDetilsGroup(backer,backDay,agreementId,backStatus,modelId){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/back/findDeviceCodeDetilsGroup', {
		backer : backer,
		backDay:backDay,
		agreementId:agreementId,
		backStatus:backStatus,
		modelId:modelId
	}, function(data) {
		setDetilsForm(data);
		JY.Model.check("auDetailDiv");
	});
}

function setDetilsForm(data) {
	var list = data.obj.list;
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
}

function edit(id,batchStatus) {
	cleanAddForm();	
	$('.selectpicker').selectpicker();  
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/back/find', {id:id}, function(data) {
		setAddForm(data);
		JY.Model.edit("auAddDiv", "修改", function() {
			if (JY.Validate.form("auAddForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auAddForm", bonuspath + '/backstage/lease/back/update', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						getbaseList(1);
					});
				});
			}
		});
	});
}

function setAddForm(data){
	var l = data.obj.list;
	$("#auAddForm input[name$='id']").val(l.id);//上级资源
	$("#backProjectId option[value='"+backProjectId+"']").attr("selected","selected");
	$("#auAddForm input[name$='userName']").val(l.userName);//上级资源
	$("#auAddForm input[name$='phone']").val(l.phone);
	$("#auAddForm input[name$='backTime']").val(l.backTime);
	$("#auAddForm input[name$='remark']").val(l.remark);
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

function getAgreementNum(){
	$(".backer").val("");
	var unitId = $("#unitId").val();
	var projectId = $("#projectId").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/rm/task/findAgreeCode',
			{leaseCompany:unitId,projectName:projectId}, function(data) {
				var l = data.obj.code;
				var agreementId = data.obj.id;
				if(l == null){
					$("#agreementCode").val("尚未签订协议，无法退料");
				}else{
					$("#agreementCode").val(l);
					getSubInfo(agreementId);
				}
			}
	);
}

//根据协议获取最近一次领用方
function getSubInfo(agreementId){
	
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/application/getSubInfo',
			{agreementId:agreementId}, function(data) {
				
			var id = data.obj.id;
			var subcontractors = data.obj.subcontractors;
			var html;
			html += '<option value="'+id+'">'+subcontractors+'</option>';
			
			$("#subcontractors").empty();
			$("#subcontractors").append(html);
			}
	);
}

var personId='';
var persinName='';
function hideOrgTree() {
	personId='';
	persinName='';
	$("#receivePerson").val();
	$("#receivePerId").val();
	$("#personContent").fadeOut("fast");
	var zTree = $.fn.zTree.getZTreeObj("personTree");
	personId = getChildrenId(zTree.getCheckedNodes(),0);
	persinName =getChildrenId(zTree.getCheckedNodes(),1);
	$("#receivePerson").val(persinName);
	$("#receivePerId").val(personId);
	orgShow = false;
}
