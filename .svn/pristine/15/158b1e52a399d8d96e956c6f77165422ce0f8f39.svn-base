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
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	$('#export').on('click',function(e) {
		e.preventDefault();
		/*$("#baseForm").attr("onsubmit","return true;");
		$("#baseForm").attr("action",bonuspath +'/backstage/maLease/export');
		$("#baseForm").attr("target","downloadFrame");//iframe的名字
		$("#baseForm").submit();*/
		var params = {
			keyWord: $("#keyWord").val(),
			bsName: $("#bsName").val(),
			projectName: $("#projectName").val(),
			deviceName: $("#deviceName").val(),
			deviceModel: $("#deviceModel").val()
		}
		var  url = bonuspath + '/backstage/maLease/export'
		exportCommon(url,'POST', params,'工程机具使用情况')
	});
});

function resetList(){
	 $("#keyWord").val('');
	 $("#bsName").val('');
	 $("#projectName").val('');
	 $("#deviceName").val('');
	 $("#deviceModel").val('');
	 getbaseList();
}

function getbaseList(init) {
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/maLease/findByPage',null,
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
					html += "<td style='vertical-align:middle;' class='center hidden-480'>"
							+ (i + leng + 1) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.agreementCode) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.bsName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unitName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.projectName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceModel) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceUnit) + "</td>";
					var num=l.deviceUnit;
					var leaseNum;
					var returnNum;
					var usingNum;
					
					if(c[num]==undefined){
						leaseNum=parseInt(l.leaseNum);
						returnNum=parseInt(l.returnNum);
						usingNum=parseInt(l.usingNum);
						if (usingNum < 0){
							returnNum = leaseNum;
							usingNum = 0;
						}
						
					}else{
						leaseNum =parseInt(l.leaseNum);
						returnNum =parseInt(l.returnNum);
						usingNum =parseInt(l.usingNum);
						if(usingNum <= 0){
							returnNum = leaseNum;
							usingNum = 0;
						}
					}
					html += "<td style='vertical-align:middle;' class='center'><a href='#' onclick='viewLeaseCode("+ l.agreementId +"," + l.modelId +")'>" + leaseNum + "</a></td>";
					html += "<td style='vertical-align:middle;' class='center'><a href='#' onclick='viewBackCode("+ l.agreementId +"," + l.modelId +")'>" + returnNum + "</a></td>";
					html += "<td style='vertical-align:middle;' class='center'><a href='#' onclick='viewUseCode("+ l.agreementId +"," + l.modelId +")'>" + usingNum + "</a></td>";
					/*html += "<td style='vertical-align:middle;' class='center'>"+ leaseNum + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ returnNum + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ usingNum + "</td>";*/
					//html += rowFunction(l.agreementId);
					html += "</tr>";
				}
				$("#baseTable tbody").append(html);
				JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
			} else {
				html += "<tr><td colspan='12' class='center'>没有相关数据</td></tr>";
				$("#baseTable tbody").append(html);
				$("#pageing ul").empty();// 清空分页
			}
			JY.Model.loadingClose();
		}
	);
}

function rowFunction(agreementId) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;" + agreementId + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "</div>";
	h += "</td>";
	return h;
}


function viewLeaseCode(agreementId,modelId){
	localStorage.setItem("agreementId",agreementId);
	localStorage.setItem("modelId",modelId);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['领料编号明细','background-color: #27A3D9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['850px', '500px'],
		content:bonuspath + '/backstage/maLease/leasecodelist'
	});
	
}

function viewBackCode(agreementId,modelId){
	localStorage.setItem("agreementId",agreementId);
	localStorage.setItem("modelId",modelId);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['退料编号明细','background-color: #27A3D9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['850px', '500px'],
		content:bonuspath + '/backstage/maLease/backcodelist'
	});
	
}

function viewUseCode(agreementId,modelId){
	localStorage.setItem("agreementId",agreementId);
	localStorage.setItem("modelId",modelId);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['在用编号明细','background-color: #27A3D9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['850px', '500px'],
		content:bonuspath + '/backstage/maLease/usecodelist'
	});
	
}



function check(agreementId) {
	localStorage.setItem("agreementId",agreementId);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['协议领料明细','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['850px', '500px'],
	  content:bonuspath + '/backstage/maLease/details'
	});
}

function search() {
	$("#searchBtn").trigger("click");
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
}

function projectTree(){
	var unitId = $("#unitId").val();
//	if(unitId == 0){
//		JY.Model.info("请选择单位");
//	}else{
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
//	}
}

function unitTree(){
	//localStorage.setItem("isOpen","0");
	localStorage.setItem("unitId","");
	localStorage.setItem("unitName","");
	localStorage.setItem("unitTreeName",$("#unitName").val());
	layer.open({
		type: 2,
		title:['结算单位','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/company/unitTreePlus'
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

function setMaModelForm(){
	var maModelId = localStorage.getItem("maModelId");
	var maModelName = localStorage.getItem("maModelName");
	$("#maModelId").val(maModelId);
	$("#maModelName").val(maModelName);
}

function maTypeTree(){
	localStorage.setItem("maTypeId","");
	localStorage.setItem("maTypeName","");
	localStorage.setItem("maTypeTreeName",$("#maTypeName").val());
	layer.open({
		type: 2,
		title:['物资名称','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/machineType/maTypeTree'
	});
}

function maModelTree(){
	var maTypeId = $("#maTypeId").val();
//	if(maTypeId == "0"){
//		JY.Model.info("请选择物资名称！");
//	}else{
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
//	}
}

