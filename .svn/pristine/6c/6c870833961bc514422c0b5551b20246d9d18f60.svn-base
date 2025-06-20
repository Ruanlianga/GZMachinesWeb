 
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
		laydate.render({
			elem: '#launchTime'
		});
		laydate.render({
			elem: '#finishTime'	
		});
	});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	$('#addBtn').on('click', function(e) {
			// 通知浏览器不要执行与事件关联的默认动作
			e.preventDefault();
			
			cleanForm();
			findReceiveName("0");
			JY.Model.edit("auDiv", "新增", function() {
				if (JY.Validate.form("auForm")) {
					var that = $(this);
					var receiveName = $("#receiveName ").val();
					var launchTime = $("#launchTime ").val();
					var finishTime = $("#finishTime ").val();
					var remark = $("#remark").val();
					if(launchTime > finishTime){
						layer.msg("采购时间不能大于到货时间");
						return false;
					}
					JY.Ajax.doRequest("auForm", bonuspath + '/backstage/new/add', 
						{receiveName:receiveName,
						launchTime:launchTime,
						finishTime:finishTime,
						remark:remark
						}, function(data) {
							that.dialog("close");
							JY.Model.info(data.resMsg, function() {
								search();
								getbaseList();
							}
						);
					});
				}
			});
		}
	);

});
function isFinish(){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/new/isFinish',null, function(data) {
		var l = data.obj.list;
		var str='<option value="0">请选择</option>';
			str+='<option value="0"></option>';
		for(var i=0;i<l.length;i++){
			str+='<option value='+l[i].taskId+'>'+l[i].status+'</option>';
		}
		$("#status").append(str);
	});
}
function getbaseList(init) {
	var isSure = $("#status").val();
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest( "baseForm", bonuspath + '/backstage/new/findByPage', 
			{
				isSure: isSure
			},
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
					for (var i = 0; i < results.length; i++) {
						var l = results[i];
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.launchName) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.launchTime) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.finishTime) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maType) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.receiveName) + "</td>";
						if(l.taskStatus == 1 || l.taskStatus == "1"){
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>待添加采购明细</span></td>";
						}else if(l.taskStatus == 2 || l.taskStatus =='2'){
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-error'>待采购验收</span></td>";
						}else if(l.taskStatus == 3 || l.taskStatus =='3') {
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待上传验收附件</span></td>";
						}else if(l.taskStatus >= 4) {
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>已采购完成</span></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'></td>";
						}
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
						html += rowFunction(l.taskId,l.launchTime,l.finishTime,l.taskStatus,l.isFinish,l.isExamine);
						html += "</tr>";
					}
					$("#baseTable tbody").append(html);
					JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
				} else {
					html += "<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
					$("#baseTable tbody").append(html);
					$("#pageing ul").empty();// 清空分页
				}
				JY.Model.loadingClose();
			}
	);
}

function rowFunction(id,launchTime,finishTime,status,isFinish,isExamine) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
 	//h += "<a href='#' title='验收单' onclick='checkList(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a>";
    if(status == 1){
		h += "<a href='#' title='批次详情' onclick='details(&apos;" + id + "&apos;,&apos;"+ launchTime +"&apos;,&apos;"+ finishTime +"&apos;,&apos;"+ isFinish +"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	    h += "<a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
    	
	}else{
		h += "<a href='#' title='批次详情' onclick='details(&apos;" + id + "&apos;,&apos;"+ launchTime +"&apos;,&apos;"+ finishTime +"&apos;,&apos;"+ isFinish +"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	    h += "<a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
		h += "<a href='#' title='采购单' onclick='order(&apos;" + id + "&apos;,&apos;" + launchTime + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a>";
	}
    if(isExamine != "1"){
    	 h += "<a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign color-p bigger-140'></i></a>";
    }
  /*  h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='批次详情' onclick='details(&apos;" + id + "&apos;,&apos;"+ launchTime +"&apos;,&apos;"+ finishTime +"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	//h += "<a href='#' title='打印' onclick='print(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a>";
	//h += "<li><a href='#' title='验收单' onclick='checkList(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a></li>";
    if(status == 1){
	    h += "<li><a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
		h += "<li><a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
    }
	h += "</ul></div></div>";*/
	h += "</td>";
	return h;
}


function order(id,launchTime){
	cleanForm(id);
	JY.Ajax.doRequest(null, bonuspath + '/backstage/new/newPurchaseReceipt', {
		id : id
	}, function(data) {
		if(data.obj.list.length != 0){
			setForms(data,launchTime);
			JY.Model.check("auDivss");
		}else {
			var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
			    layer.close(indexMsg);
			});
		}
	});
}


function setForms(data,launchTime) {
	var results = data.obj.list;
//	alert(JSON.stringify(results))
	var html = "";
	$(".launchTime").text(launchTime);
	$(".basic").html("");
	$(".add").remove();
	if(results.length != 0){
		for(var i = 0;i < results.length;i++){
			if(i < 5){
 				$("#id"+i).html(i+1);
				$("#machineType"+i).html(results[i].machineType);
				$("#machineModel"+i).html(results[i].machineModel);
				$("#unit"+i).html(results[i].unit);
				$("#arrivalNum"+i).html(results[i].arrivalNum);
				$("#buyPrice"+i).html(results[i].buyPrice);
				$("#venderName"+i).html(results[i].venderName);
			}else{
				html += '<tr class="add">';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+(i+1)+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineType+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].machineModel+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].unit+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].arrivalNum+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].buyPrice+'</td>';
				html += '<td style="text-align:center;border-bottom: 1px solid #000000;border-right: 1px solid #000000;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+results[i].venderName+'</td>';
 				html += '</tr>';
			}
		}
	}
	$("#five").after(html);
}




function addShop(id,creator,createTime,remark){
	var receiveName = $("#receiveName ").val();
	var launchTime = $("#launchTime ").val();
	var finishTime = $("#finishTime ").val();
	cleanForm();
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/new/add',
		data: {
			id : id,
			creator : creator,
			createTime : createTime,
			remark : remark,
			receiveName : receiveName,
			launchTime : launchTime,
			finishTime : finishTime
		},
		dataType: "json",
		success: function(data) {
			search();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function check(id) {
	cleanForm();
	findReceiveName("0");
	JY.Ajax.doRequest(null, bonuspath + '/backstage/new/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function details(id,launchTime,finishTime,isFinish){
	localStorage.setItem("taskId",id);
	localStorage.setItem("launchTime",launchTime);
	localStorage.setItem("finishTime",finishTime);
	localStorage.setItem("isFinish",isFinish);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具增加','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1200px', '600px'],
	  content:bonuspath + '/backstage/inputDetails/list'
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='receiveName']").val(JY.Object.notEmpty(l.receiveName));
	$("#auForm input[name$='launchTime']").val(JY.Object.notEmpty(l.launchTime));
	$("#auForm input[name$='finishTime']").val(JY.Object.notEmpty(l.finishTime));
	$("#auForm input[name$='remark']").val(JY.Object.notEmpty(l.remark));
	findReceiveName(l.receiveId);
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='receiveName']").val('');
	$("#auForm input[name$='launchTime']").val('');
	$("#auForm input[name$='finishTime']").val('');
	$("#auForm input[name$='remark']").val('');
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/new/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var receiveName = $("#receiveName").val();
				var launchTime = $("#launchTime").val();
				var finishTime = $("#finishTime").val();
				var remark = $("#remark").val();
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath + '/backstage/new/update', {id:id,receiveName:receiveName,launchTime:launchTime,finishTime:finishTime,remark:remark}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
}


function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/new/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function findReceiveName(receiveId){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/new/findAllUserInfo',{},function(data){
		$("#receiveName").html("");
		var results = data.obj.list
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#receiveName").append(html);
			$("#receiveName option[value='"+receiveId+"']").attr("selected","selected");
		}else{
			html+="<option ></option>";;
			$("#receiveName").append(html);
		}
	});
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

function checkList(id) {
	cleanAcceptForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/new/findSheet', {
		id : id
	}, function(data) {
		setAcceptForm(data);
		JY.Model.check("acceptDiv");
	});
}

function setAcceptForm(data,batchStatus) {
	var s = 5;
	var l = data.obj;
	var typeName = l.typeName;
	var typeNames = new Array();
	typeNames = typeName.split(";,;");
	var modelName = l.modelName
	var modelNames = new Array();
	modelNames = modelName.split(";,;");
	var machinesNum = l.machinesNum
	var machinesNums = new Array();
	machinesNums = machinesNum.split(";,;");
	var checkNum = l.checkNum;
	var checkNums = new Array();
	checkNums = checkNum.split(";,;");
	var unit = l.unit;
	var units = new Array();
	units = unit.split(";,;");
	var size = modelNames.length;
	var typeNameHtml = ""; 
	var modelNameHtml = "";
	var machinesNumHtml = "";
	var checkNumHtml = "";
	var applyNumber = l.applyNumber;
	var venderName = l.venderName;
	var maNums = "";
	$(".add").remove();
	if(parseInt(size) > s){
		groupAcceptForm(s,size,typeNames,modelNames,machinesNums,checkNums,units,applyNumber,venderName);
		size = s;
	}
	var tf = false;
	for(var i = 0; i < size; i++){
		maNums = '0' + machinesNums[i].substring(machinesNums[i].length-4);
		if(parseFloat(maNums) < parseFloat(0.001)){
			maNums = machinesNums[i].substring(0,machinesNums[i].length-4);
		}else{
			maNums = machinesNums[i];
		}
		typeNameHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 1em;border:1px solid #000000;">'+(i+1)+'</span>、';
		typeNameHtml += typeNames[i] + " </span>";
		modelNameHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 0.9em;border:1px solid #000000;">'+(i+1)+'</span>、';
		modelNameHtml += modelNames[i] + " </span>";
		machinesNumHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 1.1em;border:1px solid #000000;">'+(i+1)+'</span>、';
		machinesNumHtml += maNums + units[i] + " </span>";
		checkNumHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 1em;border:1px solid #000000;">'+(i+1)+'</span>、';
		checkNumHtml += checkNums[i] + units[i] + " </span>";
		if(checkNums[i] > 0){
			tf = true;
		}
	}
	if(!tf){
		checkNumHtml = "";
	}
	$("#checkCodes").html(JY.Object.notEmpty(applyNumber));
	$("#machineTypes").html(typeNameHtml);
	$("#models").html(JY.Object.notEmpty(modelNameHtml));
	$("#machinesNums").html(JY.Object.notEmpty(machinesNumHtml));
	$("#venders").html(JY.Object.notEmpty(venderName));
	$("#checkNums").html(checkNumHtml);
}

function groupAcceptForm(s,size,typeNames,modelNames,machinesNums,checkNums,units,applyNumber,venderName){
	size -= s;
	var len = parseInt(parseInt(size) / s);
	if(len > 0){
		var ys = parseInt(size) % s;
		if(ys == 0){
			len += 1;
		}
		for(var i = 1; i < len; i++){
			initAcceptForm(s,i,s,typeNames,modelNames,machinesNums,checkNums,units,applyNumber,venderName);
		}
		if(ys > 0){
			initAcceptForm(s,len,ys,typeNames,modelNames,machinesNums,checkNums,units,applyNumber,venderName);
		}
	} else {
		len = 1;
		var ys = parseInt(size) % s;
		if(ys > 0){
			initAcceptForm(s,len,ys,typeNames,modelNames,machinesNums,checkNums,units,applyNumber,venderName);
		}
	}
}

function initAcceptForm(s,i,ys,typeNames,modelNames,machinesNums,checkNums,units,applyNumber,venderName){
	var typeNameHtml = ""; 
	var modelNameHtml = "";
	var machinesNumHtml = "";
	var checkNumHtml = "";
	var size = s * i + ys;
	var tf = false;
	for(var j = s * i; j < size; j++){
		maNums = '0' + machinesNums[j].substring(machinesNums[j].length-4);
		typeNameHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 1em;border:1px solid #000000;">'+(j+1)+'</span>、';
		typeNameHtml += typeNames[j] + " </span>";
		modelNameHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 0.9em;border:1px solid #000000;">'+(j+1)+'</span>、';
		modelNameHtml += modelNames[j] + " </span>";
		machinesNumHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 1.1em;border:1px solid #000000;">'+(j+1)+'</span>、';
		machinesNumHtml += machinesNums[j] + units[j] + " </span>";
		checkNumHtml += '<span style="padding:5px 0px;"><span style="width: 24px;height: 24px;padding:1px; -webkit-border-radius: 50%;font-size: 1em;border:1px solid #000000;">'+(i+1)+'</span>、';
		checkNumHtml += checkNums[i] + units[j] + " </span>";
		if(checkNums[i] > 0){
			tf = true;
		}
	}
	if(!tf){
		checkNumHtml = "";
	}
	
	var html = '';
	html += '<table class="accept add" cellpadding="0" border="0" cellspacing="0" style="width:99%;page-break-before: always;">';
	html += '<tr>';
	html += '<td colspan="6" style="text-align: center; font-size: 1.3em;font-weight: bold;font-family:SimSun">';
	html += '机 具 设 备 到 货 验 收 单';
	html += '</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td colspan="4" style="padding: 20px 0px 10px 0px;font-family:SimSun">验收日期：<span id="checkTimes"></span></td>';
	html += '<td colspan="2" style="padding: 20px 0px 10px 0px;font-family:SimSun">编号：<span id="checkCodes">'+applyNumber+'</span></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="height:120px;text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">机具名称</td>';
	html += '<td style="text-align:left;width: 25%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+typeNameHtml+'</td>';
	html += '<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">规格型号</td>';
	html += '<td style="text-align:left;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+modelNameHtml+'</td>';
	html += '<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">数量</td>';
	html += '<td style="text-align:left;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun">'+machinesNumHtml+'</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">生产厂家</td>';
	html += '<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"><span class="basic" style="text-align: center;">'+venderName+'</span></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">出厂编号</td>';
	html += '<td colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td colspan="6" style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">验收项目</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="height:80px;width: 15%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">数量</td>';
	html += '<td style="text-align:center;width: 25%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"><span class="basic" style="text-align: center;">'+checkNumHtml+'</span></td>';
	html += '<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">外观检查</td>';
	html += '<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"><span class="basic" style="text-align: center;"></span></td>';
	html += '<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">试验检验</td>';
	html += '<td style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"><span id="testChecks" class="basic" style="text-align: center;"></span></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">验收结论</td>';
	html += '<td rowspan="5" colspan="5" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"><span class="basic" style="text-align: center;"></span></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td colspan="6" style="height:30px;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">相关配套资料：<span></span></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td colspan="6" style="height:30px;border-top: 1px dashed #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td colspan="6" style="height:30px;border-top: 1px dashed #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<tr>';
	html += '<td rowspan="7" style="text-align:center;width:15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:SimSun">验收签章</td>';
	html += '<td rowspan="2" style="height:80px;width:15%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">租赁科</td>';
	html += '<td style="text-align:center;width:15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">采购员</td>';
	html += '<td style="text-align:center;width:15%;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">库管员</td>';
	html += '<td colspan="2" style="text-align:center;width:30%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">部门负责人</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"></td>';
	html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"></td>';
	html += '<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td rowspan="2" style="height:80px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">修试科</td>';
	html += '<td colspan="2" style="height:40px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">修试人员</td>';
	html += '<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">部门负责人</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td colspan="2" style="height:40px;width: 15%;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"></td>';
	html += '<td colspan="2" style="text-align:center;width: 15%;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td rowspan="2" style="height:80px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">安全技术科</td>';
	html += '<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;font-family:SimSun">检验员</td>';
	html += '<td colspan="2" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-weight: bold;font-family:SimSun">部门负责人</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td colspan="2" style="height:50px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun"></td>';
	html += '<td colspan="2" style="height:50px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;font-family:SimSun"></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="height:70px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-bottom: 1px solid #000000;font-weight: bold;font-family:SimSun">分公司领导</td>';
	html += '<td colspan="4" style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right: 1px solid #000000;border-bottom: 1px solid #000000;font-family:SimSun"></td>';
	html += '</tr>';
	html += '</table>';
	$("#acceptForm").append(html);
}

