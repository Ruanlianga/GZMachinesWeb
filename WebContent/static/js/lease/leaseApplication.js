var ids = '';
var subcontractorsList;
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
	
	$('#addBtn').on('click',function(e) {
	
	   

		$.ajax({
			type: "post",
			url: bonuspath + '/backstage/lease/application/findApplyNumber',
			data: {},
			dataType: "json",
			success: function(data) {
				$("#subcontractors").empty();
				var applyNumber = data.applyNumber;//申请单号
				subcontractorsList = data.subcontractorsList;//分包商集合
				if(applyNumber != null && applyNumber != ''){
					// 通知浏览器不要执行与事件关联的默认动作
					e.preventDefault();
					cleanForm();
					//申请单号赋值
					$("#applyNumber").val(applyNumber);
					if(subcontractorsList.length > 0){//判断分包商不为空
						var html;
						html += '<option value="-1">请选择</option>';
						for(var i = 0; i < subcontractorsList.length; i++){
							html += '<option value="'+subcontractorsList[i].id+'">'+subcontractorsList[i].name+'</option>';
						}
						$("#subcontractors").empty();
						$("#subcontractors").append(html);
					}
					JY.Model.edit("addDiv", "新增", function() {
						if (JY.Validate.form("addForm")) {
							
							var obj = document.getElementsByClassName("btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only");
							obj[0].classList.add("pe");
							
							var that = $(this);
					     	that.attr("disabled", "disabled");
							JY.Ajax.doRequest("addForm", bonuspath
									+ '/backstage/lease/application/addLeaseApply', null, function(data) {
								that.dialog("close");
								JY.Model.info(data.resMsg, function() {
									search();
								});
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
	});
});
function backShowChenkStatus(){
	$('input[type=checkbox]:checked').each(function(){
		var id = $(this).val();	
		console.log("id=",id);
		ids += id+"-"; 
		console.log("ids=",ids);	
	})
}
function deleteApply() {
	backShowChenkStatus();	
	var idsStr = ids;
	if(idsStr == "" ){
		layer.msg("请选择想要删除的信息!",{icon:7,time:2000});
		return;
	}
	
	JY.Model.confirm("确认删除吗？",function(){	
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
			url:bonuspath+'/backstage/lease/application/deleteApply',
			async:true,
			data:data,
			success:function(data) {
				data = JSON.parse(data);
				console.log("data=",data.resMsg);	
				if(data.resMsg == "无法删除" ){
					layer.msg("该领料单已有领料机具，无法删除!",{icon:2,time:2000},function(){
						getbaseList(1);
						ids = {};
					});
				}else{
					layer.msg("删除成功!",{icon:1,time:2000},function(){
						getbaseList(1);
						ids = {};
		            });
				}
				
				layer.close(idx);
			},
			error:function(data){
				layer.msg("删除失败!",{icon:2,time:2000});
				layer.close(idx);
			}	
		});
	});
}

function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/lease/application/expExcel');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/lease/application/findByPage',null,function(data) {
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
				html += "<td style='vertical-align:middle;' class='center'><input type='checkbox' id='inp' value='" + l.id + "'></td>";
				html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.applyNumber) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.subcontractors) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.proposer) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.phone) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.applyTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unitName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.workName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.agreementCode) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operator) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operationTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
				html += rowFunction(l.id,l.isSure,l.taskId,l.projectType);

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

function rowFunction(id,isSure,taskId,projectType) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(isSure == 0){
		h += "<a href='#' title='确认' onclick='isSure(&apos;" + id + "&apos;,&apos;" + taskId + "&apos;,&apos;" + projectType + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-blue bigger-140'></i></a>";
	}else{
		h += "<span>已确认</span>";
	}
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(isSure == 0){
		h += "<li><a href='#' title='确认' onclick='isSure(&apos;" + id + "&apos;,&apos;" + taskId + "&apos;,&apos;" + projectType + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-blue bigger-140'></i></a></li>";
	}else{
		h += "<span>已确认</span>";
	}
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function isSure(id,taskId,projectType){
	var ids = parseInt(id);
	var that =$(this);
	/*$(".aBtnNoTD").attr("disabled", "disabled");*/
	JY.Ajax.doRequest(null,bonuspath +'/backstage/lease/application/update',{id:ids,isSure:1},function(data){
	    JY.Model.info(data.resMsg,function(){
	    	$("#searchBtn").trigger("click");
	    	buildLeaseTask(ids,taskId,projectType);
	    });	
	});
}

//创建领料任务
function buildLeaseTask(id,taskId,projectType){
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/lease/application/buildLeaseTask',
		data: {
			id:id,
			taskId:taskId,
			projectType:projectType
		},
		dataType: "json",
		success: function(data) {
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function cleanForm(){
	$("#addForm input[id$='applyNumber']").val("");
	$("#addForm input[id$='proposer']").val("");
	$("#addForm input[id$='phone']").val("");
	$("#addForm input[id$='applyTime']").val("");
	$("#addForm input[id$='agreementId']").val("");
	$("#addForm input[id$='unitName']").val("");
	$("#addForm input[id$='projectName']").val("");
	$("#addForm input[id$='agreementCode']").val("");
	$("#addForm input[id$='operator']").val("");
	$("#addForm input[id$='operationTime']").val("");
	$("#addForm input[id$='remark']").val("");
}

function edit(taskId){
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/application/find', {
		taskId : taskId
	}, function(data) {
		setAddForm(data);
		JY.Model.edit("addDiv","修改",function(){
	    	if(JY.Validate.form("addForm")){
				var that =$(this);
				JY.Ajax.doRequest("addForm",bonuspath +'/backstage/lease/application/update',null,function(data){
				    that.dialog("close");
				    JY.Model.info(data.resMsg,function(){search();});	
				});
			}	
	    });
	});
}

function del(taskId,agreementCode){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/lease/application/del',{
			taskId : taskId,
			agreementCode : agreementCode
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

function findDeviceNumDetils(taskId,modelId){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/findDeviceNumDetils', {
		taskId : taskId,
		modelId:modelId
	}, function(data) {
		setDetilsForm(data);
	});
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

function addCollar(){
	var applyNumber = $("#applyNumber").val();
	var leaseMan = $("#leaseMan ").val();
	var phone = $("#phone ").val(); 
	var remark = $("#remark").val();
	var agreementCode = $("#agreementCode").val();
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/lease/application/addLeaseApply',
		data: {
			applyNumber : applyNumber,
			proposer : leaseMan,
			phone : phone,
			agreementCode : agreementCode,
			remark : remark
		},
		dataType: "json",
		success: function(data) {},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function getAgreementNum(){
	$(".leaseMan").val("");
	var unitId = $("#unitId").val();
	var projectId = $("#projectId").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/application/findAgreeCode',
			{leaseCompany:unitId,projectName:projectId}, function(data) {
				var l = data.obj.code;
				var agreeId = data.obj.id;
				if(l == null){
					$("#agreementCode").val("尚未签订协议");
				}else{
					$("#agreementCode").val(l);
					getSubInfo(agreeId);
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
			
			for(var i = 0; i < subcontractorsList.length; i++){
				
			var sId = subcontractorsList[i].id;
				if(sId == id){
					html += '<option checked value="'+id+'">'+subcontractors+'</option>';
				}else{
					html += '<option value="'+subcontractorsList[i].id+'">'+subcontractorsList[i].name+'</option>';
				}
				
			}
			
			
			
			$("#subcontractors").empty();
			$("#subcontractors").append(html);
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