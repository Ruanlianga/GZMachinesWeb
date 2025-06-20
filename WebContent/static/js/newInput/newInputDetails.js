var taskId;
var buyTime;
var acceptTime;
var noticeContent;
var isFinish;
$(function() {
	taskId = localStorage.getItem("taskId");
	launchTime = localStorage.getItem("launchTime");
	finishTime = localStorage.getItem("finishTime");
	isFinish = localStorage.getItem("isFinish");
	if(isFinish == '1'){
		$("#addBtn").css("display","none");
	}else if(isFinish == '0'){
		$("#addBtn").css("display","");
	}
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
});

function add(){
	
	
	JY.Model.edit("auDiv", "新增", function() {
		
		var actualPrice = $("#actualPrice").val();
		var machineNums = $("#machineNums").val();
		var checkPlace = $("#checkPlace").val();
		if(actualPrice == '' || actualPrice == null){
			layer.msg('购置价格不能为空');
			return false;
		}
		if(machineNums == '' || machineNums == null){
			layer.msg('机具数量不能为空');
			return false;
		}
		if(checkPlace == '' || checkPlace == null){
			layer.msg('验收地点不能为空');
			return false;
		}
		
		var that = $(this);
			if (JY.Validate.form("auForm")) {
				$("#taskId").val(taskId);
				var maVenderId = $("#maVenderId").val();
				var maTypeId =$("#maTypeId").val();  //机具类型ID
				var maModelId = $("#maModelId").val();//机具型号ID
				var actualPrice = $("#actualPrice").val();
				var machineNums = $("#machineNums").val();
				var checkPlace = $("#checkPlace").val();
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/inputDetails/add', 
						{
					taskId:taskId,
					maModelId:maModelId,
					actualPrice:actualPrice,
					arrivalNum:machineNums,
					maVenderId:maVenderId,
					checkPlace:checkPlace
						},
				function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
		}
	});
			
}

function selectPerson(){
	localStorage.setItem("noticeContent",noticeContent);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['完善通知信息','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['850px', '450px'],
		content: bonuspath+'/backstage/user/select'
	});
}

//新购只记数量的机具
function addCountsMachines(taskId,type,machinesNum){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machineType/addCountsMachines',
			{
				id : type,
				nums :machinesNum
			},function(data){
		});
}

function batchAddMachines(company,taskId,type,machinesNum,chackStatus,isFixedAssets,isTrack,isCheck,buyTime,cycleNum,remark,acceptTime){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machine/addMachine',
		{
			taskId : taskId,
			type : type,
			machinesNum :machinesNum,
			chackStatus : chackStatus,
			isFixedAssets : 0,
			isTrack : 1,		//跟踪
			isCheck : 1,		//需要检验
			buyTime : buyTime,
			equipmentState : 1,
			outInNum : 0,
			remarks : '无',
			deviceNum:company,
			tOverhaulTime: acceptTime
		},function(data){
	});
}

function getbaseList(init) {
	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/inputDetails/findByPage',{taskId:taskId},
					function(data) {
						$("#baseTable tbody").empty();
						var obj = data.obj;
						var list = obj.list;
						var results = list.results;
						var permitBtn = obj.permitBtn;
						var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
						var html = "";
						if (results != null && results.length > 0) {
							var leng = (pageNum - 1) * pageSize;
//							noticeContent = "各位同事您好，请于" + $("#tomorrowDate").val() +"在机具2号库，进行机具验收。验收内容如下：";
							for (var i = 0; i < results.length; i++) {
								var l = results[i];
								if(l.chackStatus >= 2){
//									$("#selectPersonBtn").attr("disabled","disabled");
									$("#addBtn").attr("disabled","disabled");
								}
								html += "<tr>";
								html += "<td style='vertical-align:middle;' class='center hidden-480'>"
										+ (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineType) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineModel) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.actualPrice) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.arrivalNum) + "</td>";
								/*
								if(l.picUrl == '' || l.picUrl == null){
									html += "<td style='vertical-align:middle;' class='center'><a onclick='updMachinesPic(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>上传图片</a></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><a onclick='readMachinesPic(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>查看图片</a></td>";
								}
								*/
								if(l.picUrl == null || l.picUrl == ''){
									html += "<td style='vertical-align:middle;' class='center'><a onclick='uploadManchines(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>上传</a></td>";
								} else {
									html += "<td style='vertical-align:middle;' class='center'><a onclick='readManchines(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>查看</a></td>";
								} 
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.venderName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.checkPlace) + "</td>";
								if(l.checker == "" || l.checker == null){
									l.checker = "尚未指派";
									html += "<td style='vertical-align:middle;cursor:pointer;' class='center hidden-480'><a href='#' onclick='checker("+ l.id +")'>" + l.checker + "</a></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.checker) + "</td>";
								}
								if(l.customerRep == "" || l.customerRep == null){
									l.customerRep = "尚未指派";
									html += "<td style='vertical-align:middle;cursor:pointer;' class='center hidden-480'><a href='#' onclick='service("+ l.id +")'>" + l.customerRep + "</a></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.customerRep) + "</td>";
								}
								
								if(l.checkStatus == 1 || l.checkStatus =='1'){
									html += "<td style='vertical-align:middle;' class='center'></td>";
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待指定人员</span></td>";
								}else if(l.checkStatus == 2 || l.checkStatus =='2'){
									html += "<td style='vertical-align:middle;' class='center'></td>";
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-error'>待验收</span></td>";
								}else if(l.checkStatus == 3 || l.checkStatus =='3'){
									html += "<td style='vertical-align:middle;' class='center'><a onclick='uploadCheckUrl(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;,&apos;"+ l.maTypeId +"&apos;);'>上传图片</a></td>";
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待上传验收图片</span></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><a onclick='readCheckUrl(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;,&apos;"+ l.maTypeId +"&apos;);'>查看图片</a></td>";
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>已完成</span></td>";
								}
								if(l.invoiceUrl == '' || l.invoiceUrl == null){
									html += "<td style='vertical-align:middle;' class='center'><a onclick='uploadImg(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>上传图片</a></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><a onclick='readInvoice(&apos;"+ l.maModelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>查看图片</a></td>";
								}
//							noticeContent += "机具类型："+ l.machineType + "，规格型号："+l.model + "，数量：" + l.machineNums + "；";
							html += rowFunction(l.maModelId,l.taskId,l.checkStatus,l.arrivalNum,l.checkerId);
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='15' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}

function rowFunction(maModelId,taskId,checkStatus,machineNums,checkerId) {
	var h = "";
//	var status = parseInt(checkStatus);
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(checkStatus >= 2){
//		h += "<a href='#' title='验收单' onclick='checkList(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;,&apos;" + checkStatus + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
//		h += "<a href='#' title='入库单' onclick='inputList(&apos;" + maTypeId + "&apos;,&apos;" + batchId + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a>";
	}
	if(checkStatus == 2){
		h += "<a href='#' title='验收' onclick='edit(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;,&apos;" + machineNums + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	}else if(checkStatus == 1){
		h += "<a href='#' title='修改' onclick='editDetails(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;,&apos;" + machineNums + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
		h += "<a href='#' title='删除' onclick='del(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign color-blue bigger-140'></i></a>";
	}
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(checkStatus >= 2){
//		h += "<li><a href='#' title='验收单' onclick='checkList(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;,&apos;" + checkStatus + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
//		h += "<li><a href='#' title='入库单' onclick='inputList(&apos;" + maTypeId + "&apos;,&apos;" + batchId + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a></li>";
	}
	if(checkStatus == 2){
		h += "<li><a href='#' title='验收' onclick='edit(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;,&apos;" + machineNums + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	}else if(checkStatus == 1){
		h += "<li><a href='#' title='修改' onclick='editDetails(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;,&apos;" + machineNums + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
		h += "<li><a href='#' title='删除' onclick='del(&apos;" + maModelId + "&apos;,&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign color-p bigger-140'></i></a></li>";
	}
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function check(taskId,maModelId,machineNums,checkerId) {
	JY.Model.confirm("确认发布吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/inputDetails/isSure', {
			taskId:taskId,
			maModelId:maModelId,
			checkNum:machineNums,
			checkerId:checkerId
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
				getbaseList(1);
			});
		});
	});
}

function checkList(maTypeId,taskId,chackStatus) {
	cleanAcceptForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/accept/find', {
		taskId : taskId,
		maTypeId:maTypeId
	}, function(data) {
		setAcceptForm(data,chackStatus);
		JY.Model.check("acceptDiv");
	});
}

function inputList(maTypeId,taskId) {
	cleanInputForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/accept/findInput', {
		taskId : taskId,
		maTypeId: maTypeId
	}, function(data) {
		setInputForm(data);
		JY.Model.check("inputDiv");
	});
}

function service(id) {
	cleanForm();
	JY.Model.edit("serviceDiv", "指派客服代表", function() {
		var that = $(this);
		if (JY.Validate.form("serviceForm")) {
			var serviceId = $("#serviceId").val();
			JY.Ajax.doRequest(null, bonuspath+'/backstage/inputDetails/update', 
				{
				id:id,
				customerRep:serviceId
				}, 
				function(data) {
			JY.Model.info(data.resMsg, function() {
				that.dialog("close");
				$("#serviceId").val('');
				search();
			});
			});
		}
	});
}

function checker(id) {
	cleanForm();
	JY.Model.edit("checkerDiv", "指派检验员代表", function() {
		var that = $(this);
		if (JY.Validate.form("checkerForm")) {
			var checkId = $("#checkId").val();
			JY.Ajax.doRequest(null, bonuspath+'/backstage/inputDetails/update', 
				{
				id:id,
				checker:checkId
				}, 
				function(data) {
			JY.Model.info(data.resMsg, function() {
				that.dialog("close");
				$("#checkId").val('');
				search();
			});
			});
		}
	});
}

function serviceTree(){
	localStorage.setItem("serviceId","");
	localStorage.setItem("serviceName","");
	localStorage.setItem("serviceTreeName",$("#serviceName").val());
	layer.open({
		type: 2,
		title:['客服代表','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/user/serviceTree'
	});
}
function setServiceForm(){
	var serviceId = localStorage.getItem("serviceId");
	var serviceName = localStorage.getItem("serviceName");
	$("#serviceId").val(serviceId);
	$("#serviceName").val(serviceName);
	
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
function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='taskId']").val(l.taskId);
	$("#auForm input[name$='model']").val(JY.Object.notEmpty(l.model));
	$("#auForm input[name$='machinesNum']").val(JY.Object.notEmpty(l.machinesNum));
	$("#auForm input[name$='outNum']").val(JY.Object.notEmpty(l.outNum));
	$("#auForm input[name$='invoiceNum']").val(JY.Object.notEmpty(l.invoiceNum));
	$("#auForm input[name$='invoiceUrl']").val(JY.Object.notEmpty(l.invoiceUrl));
	$("#auForm input[name$='machinesUrl']").val(JY.Object.notEmpty(l.machinesUrl));
	$("#auForm input[name$='chackStatus']").val(JY.Object.notEmpty(l.chackStatus));
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[id$='taskId']").val("");
	$("#auForm input[id$='maTypeName']").val("");// 上级资源
	$("#auForm input[id$='maTypeId']").val("0");
	$("#auForm input[id$='maModelName']").val('');
	
	$("#auForm input[id$='maModelId']").val("0");
	$("#auForm input[name$='machineNums']").val("");
	$("#auForm input[name$='actualPrice']").val("");
	$("#auForm input[name$='vender']").val("");
	$("#auForm input[id$='maVenderId']").val("");
}

function setAcceptForm(data,chackStatus) {
	var l = data.obj;
	var machineNums = l.machineNums;
	maNums = '0' + machineNums.substring(machineNums.length-4);
	if(parseFloat(maNums) < parseFloat(0.001)){
		maNums = machineNums.substring(0,machineNums.length-4);
	}else{
		maNums = machineNums;
	}
	$("#checkTimes").html(JY.Object.notEmpty(l.checkTime));
	$("#checkCodes").html(JY.Object.notEmpty(l.checkCode));
	$("#models").html(JY.Object.notEmpty(l.model));
	$("#venders").html(JY.Object.notEmpty(l.vender));
	$("#machineTypes").html(JY.Object.notEmpty(l.machineType));
	$("#outNums").html(JY.Object.notEmpty(l.outNum));
	$("#machinesNums").html(JY.Object.notEmpty(maNums));
	
	if(chackStatus > 2){
		var exteriorCheck = l.exteriorCheck;
		if(exteriorCheck == "1"){
			exteriorCheck = "合格";
		}else if(exteriorCheck == "0"){
			exteriorCheck = "不合格";
		}else{
			exteriorCheck = "合格";
		}
		var testCheck = l.testCheck;
		if(testCheck == "1"){
			testCheck = "合格";
		}else if(testCheck == "0"){
			testCheck = "不合格";
		}else{
			testCheck = "合格";
		}
		$("#exteriorChecks").html(exteriorCheck);
		$("#testChecks").html(testCheck);
	}
	var checkNum = l.checkNum;
	$("#checkNums").html(checkNum);
	var checkConclusion = l.checkConclusion;
	$("#checkConclusions").html(checkConclusion);
	$("#aboutFile").html(l.aboutFile);
}

function setInputForm(data) {
	$(".basic").html("");
	$(".add").remove();
	var list = data.obj.list;
	var html="";
	if(list.length>0){
		var date = list[0].buyTime;
		var d = new Array();
		d = date.split("-");
		$(".year").html(d[0]);
		$(".month").html(d[1]);
		$(".day").html(d[2]);
//		$(".vender").html(list[0].vender);
		for(var i=0;i<list.length;i++){
			var deviceNum = list[i].deviceNum;
			var outFacTime = list[i].outFacTime;
			if(deviceNum.length == 1){
				deviceNum = "暂无";
			}
			if(outFacTime == null || outFacTime == "null"){
				outFacTime = "暂无";
			}
			html+='<tr class="add">';
			html+='<td style="height:30px;text-align:center;width: 9%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="id">'+(i+1)+'</span></td>';
			html+='<td colspan="2" style="text-align:center;width: 18%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="machineType">'+list[i].machineType+'</span></td>';
			html+='<td style="text-align:center;width: 9%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="model">'+list[i].model+'</span></td>';
			html+='<td style="text-align:center;width: 9%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="unit">'+list[i].unit+'</span></td>';
			html+='<td style="text-align:center;width: 9%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="buyPrice">'+list[i].buyPrice+'</span></td>';
			html+='<td style="text-align:center;width: 9%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="machineNums">1</span></td>';
			html+='<td colspan="2" style="text-align:center;width: 18%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="deviceNum">'+deviceNum+'</span></td>';
			html+='<td colspan="2" style="text-align:center;width: 18%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun"><span class="acceptDate">'+outFacTime+'</span></td>';
			html+='</tr>';
		}
		$("#header").after(html);
	}
}

function cleanAcceptForm() {
	$("#models").html('');
	$("#machineTypes").html('');
	$("#outNums").html('');
	$("#machinesNums").html('');
	$("#venders").html('');
	$("#checkNums").html('');
	$("#exteriorChecks").html('');
	$("#testChecks").html('');
	$("#checkConclusions").html('');
}

function cleanInputForm() {
//	$("#vender").html('');
	$("#acceptDate").html('');
}

function search() {
	$("#searchBtn").trigger("click");
}

function updPrice(maTypeId,taskId){
	layer.prompt({
		title: ['价格增加','background-color: #438EB9;color:#fff']
	},function(val, index){
	  title:'价格修改';
		JY.Ajax.doRequest(null, bonuspath + '/backstage/inputDetails/update', {taskId:taskId,maTypeId:maTypeId,actualPrice:val}, function(data) {
			layer.close(index);
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

/*function uploadInvoice(maModelId,taskId){
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['发票上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/updInvoice.jsp'
	});
}
*/


function uploadImg(maModelId,taskId){  
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['发票上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '400px'],
	  content: bonuspath+'/backstage/inputDetails/imgInvoice'
	});
}

function readInvoice(maModelId,taskId){
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['发票查看','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/inputDetails/queryImgPage?maModelId='+maModelId +'&taskId='+taskId
	});
}

function uploadManchines(maModelId,taskId){  
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具图片上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '400px'],
	  content: bonuspath+'/backstage/inputDetails/imgManchines'
	});
}

function readManchines(maModelId,taskId){
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具图片查看','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/inputDetails/queryImgManchines?maModelId='+maModelId +'&taskId='+taskId
	});
}





function uploadCheckUrl(maModelId,taskId,maTypeId){  
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("maTypeId",maTypeId);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具图片上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '400px'],
	  content: bonuspath+'/backstage/inputDetails/imgCheck'
	});
}

function readCheckUrl(maModelId,taskId){
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具图片查看','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/inputDetails/queryImgCheck?maModelId='+maModelId +'&taskId='+taskId
	});
}


function edit(maModelId,taskId,machineNums) {
	cleanAcceptsForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/accept/find', {
		taskId : taskId,
		maModelId: maModelId
	}, function(data) {
		setAcceptsForm(data);
		JY.Model.edit("auAcceptDiv", "验收", function() {
			
			var checkNum = $("#checkNum").val();
			var checkConclusion = $("#checkConclusion").val();
			if(parseFloat(checkNum) > parseFloat(machineNums) || parseFloat(checkNum) < 0){
				JY.Model.info("请正确填写机具验收数量");
			}else if(checkConclusion == "" || checkConclusion == null){
				JY.Model.info("请输入验收结论");
			}else{
				if (JY.Validate.form("auAcceptForm")) {
					var that = $(this);
					JY.Ajax.doRequest("auAcceptForm", bonuspath + '/backstage/inputDetails/check', {taskId:taskId,maModelId:maModelId,qualifiedNum:checkNum,checkConclusion:checkConclusion}, function(data) {
						that.dialog("close");
						JY.Model.info(data.resMsg, function() {
							search();
						});
					});
				}
			}
		});
	});
}

function del(maTypeIds,taskId){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/inputDetails/del',{
			taskId : taskId,
			maModelId:maTypeIds
		},function(data){
			JY.Model.info(data.resMsg,function(){search();});
		});
	});
}

function editDetails(maTypeIds,taskId,machineNums) {
	maTypeId = maTypeIds;
	cleanEditForm();
	setEditForm(machineNums);
	JY.Model.edit("auEditDiv", "修改", function() {
		var editNum = $("#editNum").val();
		var machineNum = machineNums;
		if(parseFloat(editNum) < 1){
			JY.Model.info("请正确填写修改后数量");
		}else{
			console.log("maTypeIds="+maTypeIds+"+taskId="+taskId+"+machineNum="+machineNum+"+editNum="+editNum);
			if (JY.Validate.form("auEditForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auEditForm", bonuspath + '/backstage/accept/edit', {taskId:taskId,maTypeId:maTypeId,editNum:editNum,machineNum:machineNum}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		}
	});
}

var maTypeId;

function setEditForm(machineNums){
	$("#machineNum").val(machineNums);
}
function cleanEditForm(){
	$("#editNum").val("");
}

function setAcceptsForm(data){
	var l = data.obj;
	maTypeId = l.maTypeId;
	$("#machineType").val(JY.Object.notEmpty(l.machineType));
	$("#modelType").val(JY.Object.notEmpty(l.machineModel));
	$("#auAcceptForm input[name$='checkNum']").val(JY.Object.notEmpty(l.checkNum));
	$("#checkConclusion").val(JY.Object.notEmpty(l.checkConclusion));
}

function cleanAcceptsForm(){
	$("#auAcceptForm input[name$='taskId']").val("");
	$("#auAcceptForm input[name$='maTypeId']").val("");
	$("#auAcceptForm input[name$='checkNum']").val("");
	$("#auAcceptForm input[name$='machineType']").val("");
	$("#auAcceptForm input[name$='machineModel']").val("");
	$("#checkConclusion").val("");
}

function updMachinesPic(id,taskId){
	localStorage.setItem("updPicId",id);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具信息上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '430px'],
	  content: bonuspath+'/backstage/inputDetails/machinesPic'
	});
}

function updAboutFile(id,taskId){
	localStorage.setItem("typeId",id);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['配套资料上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/inputDetails/updAboutFile'
	});
}

function readMachinesPic(id,taskId){
	localStorage.setItem("readPicId",id);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	$.ajax({
		type: "post",
		url: bonuspath + "/backstage/inputDetails/find",
		data: {
			taskId : taskId,
			maModelId : id
		},
		dataType: "json",
		success: function(data) {
			var l = data.obj;
			var machinesUrl = l.picUrl;
			if(l.picUrl.indexOf("pdf") != -1) { //如果是pdf文件
				var path = bonuspath+'/machinesImg/'+ machinesUrl;
				window.open(path);
			} else { //是图片
				layer.open({
				type: 2,
				title:['机具图片查看','background-color: #438EB9;color:#fff'],
				shadeClose:true,
				shade:false,
				maxmin: true,
				area: ['800px', '430px'],
				content: bonuspath+'/backstage/inputDetails/readMachinesPic'
				});
			}
		}
	});
	
}

//验收图片上传、查看
function updAcceptPic(id,taskId){
	localStorage.setItem("updAccId",id);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['验收图片上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/inputDetails/acceptPic'
	});
}

function readAcceptPic(id,taskId){
	localStorage.setItem("readAccId",id);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['验收图片查看','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['550px', '430px'],
		content: bonuspath+'/backstage/inputDetails/readAcceptPic'
	});
}

function setMaTypeForm(){
	var maTypeId = localStorage.getItem("maTypeId");
	var maTypeName = localStorage.getItem("maTypeName");
	$("#maTypeId").val(maTypeId);
	$("#maTypeName").val(maTypeName);
	$("#maModelId").val(0);
	$("#maModelName").val("");
}

function maTypeTree(){
	localStorage.setItem("maTypeId","");
	localStorage.setItem("maTypeName","");
	localStorage.setItem("maTypeTreeName",$("#maTypeName").val());
	layer.open({
		type: 2,
		title:['机具类型','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/machineType/maTypeTree'
	});
}

function setMaModelForm(){
	var maModelId = localStorage.getItem("maModelId");
	var maModelName = localStorage.getItem("maModelName");
	$("#maModelId").val(maModelId);
	$("#maModelName").val(maModelName);
}

function maModelTree(){
	var maTypeId = $("#maTypeId").val();
	if(maTypeId == "0"){
		JY.Model.info("请选择机具类型！");
	}else{
		localStorage.setItem("maModelId","");
		localStorage.setItem("maModelName","");
		localStorage.setItem("maModelTreeName",$("#maModelName").val());
		localStorage.setItem("maTypeTreeId",maTypeId);
		layer.open({
			type: 2,
			title:['规格型号','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['400px', '400px'],
			content: bonuspath+'/backstage/machineType/maModelTree'
		});
	}
}

function setMaVenderForm(){
	var maVenderId = localStorage.getItem("maVenderId");
	var maVenderName = localStorage.getItem("maVenderName");
	$("#maVenderId").val(maVenderId);
	$("#maVenderName").val(maVenderName);
}

function maVenderTree(){
	localStorage.setItem("maVenderId","");
	localStorage.setItem("maVenderName","");
	localStorage.setItem("maVenderTreeName",$("#maVenderName").val());
	layer.open({
		type: 2,
		title:['机具厂家','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/vender/maVenderTree'
	});
}
