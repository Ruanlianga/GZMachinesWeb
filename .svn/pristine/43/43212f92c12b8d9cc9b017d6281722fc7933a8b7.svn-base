var porjectName =localStorage.getItem("projectName");
$(function() {
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
		laydate.render({
			elem: '#endTime'
		});
	});
	var endTime = $("#endTime").val();
	localStorage.setItem("endTime", "endTime");
	var agreementId;
	//getLeaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});
	title();

});
function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/royaltyDetails/export?agreementId='+agreementId);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}


function title(){
	/*var title = JSON.stringify(porjectName);
	alert(title);
	$("#projectName").html(title+"结算明细");*/
	agreementId = localStorage.getItem("agreementId");
	JY.Model.loading();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlementDetails/findTitle', {agreementId : agreementId}, function(data) {
		JY.Model.loadingClose();
		var results = data.obj;
		var projectName=results.projectName;
		var unitName=results.unitName;
		$("#projectName").html(projectName+"结算明细");
		$("#unitName").html(unitName);
	});
	selectBatch();
}
function findSettlementApply(){	
	    getLeaseList(0);
	    agreementId = localStorage.getItem("agreementId");
		localStorage.setItem("agreementId",agreementId);
		layer.open({
			type: 2,
			title:['详细信息','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['1100px', '500px'],
			content: bonuspath+'/backstage/settlementDetails/settlementApply'
		});
	}
function exportLease(){
	agreementId = localStorage.getItem("agreementId");
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/settlementDetails/getLeaseExcel?agreementId='+agreementId);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}
function getLeaseList(init) {
	agreementId = localStorage.getItem("agreementId");
	endTime = localStorage.getItem("endTime");
	var batch = $("#batch").val();
	//alert("init="+init);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/settlementDetails/getLeaseList',
		{agreementId : agreementId,endTime:endTime,flag:init,batch:batch},function(data) {
		$("#leaseDetails").empty();
//		alert("data="+JSON.stringify(data));
			var obj = data.obj;
			var results = obj.list;
			var html = "";
			if (results != null && results.length > 0) {
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceModel) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceUnit) + "</td>";
					//alert(l.planLeasePrice);
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.planLeasePrice) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.leaseNum) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.leaseDate) + "</td>";
					if(l.returnNum == 0 || l.returnNum == '0'){
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.returnNum) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.returnDate) + "</td>";
					}
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.stopDays) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.leaseDays) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.leaseMoney) + "</td>";
//					html += leaseFunction2(agreementId);
					html += "</tr>";
					if(i == results.length - 1){
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center'>小计</td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.leaseTotal) + "</td>";
					
						html += "</tr>";
						$("#notYetDetails").empty();
						getLoseList(init);
					}
				}
				$("#leaseDetails").append(html);
			}else{
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center'>小计</td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
//				html += "<td style='vertical-align:middle;' class='center'><a href='#' onclick='exportLease(&apos;" + l.agreementId + "&apos;)'>"+ JY.Object.notEmpty(l.leaseTotal) + "</a></td>";
				html += "<td style='vertical-align:middle;' class='center'>0.00</td>";
			
				html += "</tr>";
				$("#leaseDetails").append(html);
			}
			JY.Model.loadingClose();
		});
}

function check(agreementId) {
	cleanForm();
	$("#agreementId").val(agreementId);
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/find', {
		agreementId : agreementId
	}, function(data) {
		setForm(data);
		JY.Model.check("lease");
	});
}

function setForm(data) {
	var l = data.obj;
	$("#lease input[name$='id']").val(l.id);
	$("#lease input[name$='leasePrice']").val(
			JY.Object.notEmpty(l.leasePrice));


}

function cleanForm() {
	JY.Tags.isValid("lease", "1");
	JY.Tags.cleanForm("lease");
	$("#lease input[name$='id']").val('0');// 上级资源
	$("#lease input[name$='leasePrice']").val('');
	hideRole();
}

function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function getLoseList(init) {
	agreementId = localStorage.getItem("agreementId");
	var batch = $("#batch").val();
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/settlementDetails/getLoseList',{agreementId : agreementId,flag:init,batch:batch},
		function(data) {
//		alert("data="+JSON.stringify(data));
			$("#notYetDetails").empty();
			var obj = data.obj;
			var results = obj.list;
			var html = "";
			if (results != null && results.length > 0) {
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceModel) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceUnit) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.loseNum) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.loseMoney) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "</tr>";
					if(i == results.length - 1){
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center'>小计</td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.loseTotal) + "</td>";
//						html += notYetFunction(agreementId);
						html += "</tr>";
					}
				}
				$("#notYetDetails").append(html);
			}else{
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center'>小计</td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'>0.00</td>";
//				html += notYetFunction(agreementId);
				html += "</tr>";
				$("#notYetDetails").append(html);
			}
			getDeductionList();
			JY.Model.loadingClose();
		});
}

function exportNotYet(){
	agreementId = localStorage.getItem("agreementId");
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/settlementDetails/getLoseExcel?agreementId='+agreementId);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}

/*
function getRepairList(init) {
	agreementId = localStorage.getItem("agreementId");
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/settlementDetails/getRepairList',
			{
		     agreementId : agreementId,
		     flag : init
		   },
		function(data) {
//			   alert("data="+JSON.stringify(data));
				
			$("#repairDetails").empty();
			var obj = data.obj;
			var results = obj.list;
			var html = "";
			if (results != null && results.length > 0) {
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceModel) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deviceUnit) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.repairNum) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.repairMoney) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
//					html += repairFunction2(agreementId);
					html += "</tr>";
					if(i == results.length - 1){
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center'>小计</td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.repairTotal) + "</td>";
//						html += repairFunction(agreementId,l.backId);
						html += "</tr>";
					}
				}
				$("#repairDetails").append(html);
			}else{
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center'>小计</td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'>0.00</td>";
				html += "</tr>";
				$("#repairDetails").append(html);
			}
			getScrapList(init);
			JY.Model.loadingClose();
		});
}

function exportRepair(){
	agreementId = localStorage.getItem("agreementId");
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/royaltyDetails/getRepairExcel?agreementId='+agreementId);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}*/

/*function getScrapList(init) {
	agreementId = localStorage.getItem("agreementId");
	var batch = $("#batch").val();
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/settlementDetails/getScrapList',{agreementId : agreementId,flag :init,batch:batch},function(data) {
			$("#scarpDetails").empty();
//			alert("data="+JSON.stringify(data));
			var obj = data.obj;
			var results = obj.list;
			var html = "";
			if (results != null && results.length > 0) {
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center'>" +l.deviceName + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + l.deviceModel + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + l.deviceUnit + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + l.scrapNum+ "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.scrapMoney) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "</tr>";
					if(i == results.length - 1){
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center'>小计</td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.scrapTotal) + "</td>";
						html += "</tr>";
					}
				}
				$("#scarpDetails").append(html);
			}else{
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center'>小计</td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'>0.00</td>";
				html += "</tr>";
				$("#scarpDetails").append(html);
			}
			getDeductionList();
			JY.Model.loadingClose();
		});
}*/
function getDeductionList(init) {
	agreementId = localStorage.getItem("agreementId");
	var batch = $("#batch").val();
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/settlementDetails/getDeductionList',{agreementId : agreementId,flag :init,batch:batch},function(data) {
			$("#deductionDetails").empty();
//			alert("data="+JSON.stringify(data));
			var obj = data.obj;
			var results = obj.list;
			var html = "";
			if (results != null && results.length > 0) {
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center'>" +l.deductionMoney + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + l.remark + "</td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "</tr>";
					if(i == results.length - 1){
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center'>小计</td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.deductionTotal) + "</td>";
						html += "</tr>";
					}
				}
				$("#deductionDetails").append(html);
			}else{
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center'>小计</td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'>0.00</td>";
				html += "</tr>";
				$("#deductionDetails").append(html);
			}
			//getFourMoneyList();
			JY.Model.loadingClose();
		});
}
/*
function getFourMoneyList() {
	agreementId = localStorage.getItem("agreementId");
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/royaltyDetails/fourMoneySum',null,function(data) {
			$("#fourDetails").empty();
//			alert("data="+JSON.stringify(data));
			var obj = data.obj;
			var results = obj.list;
			var html = "";
			if (results != null && results.length > 0) {
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center'>合计</td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.fourMoneySum) + "</td>";
					html += "</tr>";
					
				}
				$("#fourDetails").append(html);
			}
			
			JY.Model.loadingClose();
		});
}*/
function exportScarp(){
	agreementId = localStorage.getItem("agreementId");
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/royaltyDetails/getScarpExcel?agreementId='+agreementId);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}
function search() {
	$("#searchBtn").trigger("click");
}

function backToAgreen(){
	var type = localStorage.getItem("type");
	if(type == 1){
		var url = bonuspath+'/backstage/settlement/list';  
	    window.location.href = url;
	}else{
		var url = bonuspath+'/backstage/settlementAudit/list';  
	    window.location.href = url;
	}
}
function editDeduction() {
	agreementId = localStorage.getItem("agreementId");
	 var batch = $("#batch").val();
	JY.Model.edit("auDiv", "填写", function() {
		if (JY.Validate.form("auForm")) {
			var that = $(this);
			JY.Ajax.doRequest("auForm", bonuspath
					+ '/backstage/settlementDetails/editDeduction',{agreementId:agreementId,batch:batch}, function(data) {
				that.dialog("close");
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		}
	});
}
function settlementBatch(){
	getLeaseList(2);
	}

function settlementFun(){
	getLeaseList(3);
}
function selectBatch(){
	agreementId = localStorage.getItem("agreementId");
	$("#batch").html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/settlementDetails/selectBatch',{agreementId:agreementId},
	function(data){
	//	alert("data="+JSON.stringify(data));
		var l = data.obj.list;
		var str='<option value="0">选择结算批次</option>';
		for(var i=0;i<l.length;i++){
			str+='<option value='+l[i].batch+'>'+l[i].batch+'</option>';
		}
		$("#batch").append(str);
	});	
	$("#batch").change(function(){
		// batch = $(this).val();
		 var batch = $("#batch").val();
		 getLeaseList(1);
	});
}