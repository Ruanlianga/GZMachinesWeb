var init = 1;
var keyWord = "";
var type0 = "";
var type = "";
var startTime = "";
var endTime = "";
var batchStatus = "0";
var clickState = 0; //初始化点击状态
$(function() {
/*	document.getElementById('maModelName').value = "";
	document.getElementById('maModelId').value = "";*/
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
});

function getbaseList(init) {
	keyWord = $("#keyWord").val();
	type0 = $("#type0").val();
	type = $("type").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	batchStatus = $("#batchStatus").val();
	init = $(".pageNum").val();
	$(".pageNum").val(init);
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/inventoryRecord/findByPage',null,
		function(data) {
			$("#baseTable tbody").empty();
			var msg = data.resMsg;
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
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maTypeName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModelName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unit) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
 					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.inventoryPerson) + "</td>";
 					 html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.inventoryNum) + "</td>"; 
 					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.inventoryTime) + "</td>";
 					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.inventoryType) + "</td>";
					/*if(l.inventoryType == 1){
						html += "<td style='vertical-align:middle;' class='center'>盘盈</td>";
					}else if(l.inventoryType == 0){
						html += "<td style='vertical-align:middle;' class='center'>盘亏</td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}*/
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
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

function inputOnBlur(id){
	var inNumsName = "inNums" + id;
	var inNums = $('#'+inNumsName).val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/inventory/update', 
			{id:id,inNums:inNums}, function(data) {
//		JY.Model.info('增加设备数量完成', function() {
			search();
//		});
	});
}

function delTask(id){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/inventory/find',{id:id},function(data){
	var res = data.obj;
	var isProfit = res.isProfit;
	if(isProfit != null){
		JY.Model.confirm("权限不够，无法删除！");
	}else{
		JY.Model.confirm("确认删除吗？",function(){	
			JY.Ajax.doRequest(null,bonuspath +'/backstage/inventory/del',{id:id},function(data){
				JY.Model.info(data.resMsg,function(){search();});
			});
		});
	}
	});
}

function search() {
	document.getElementById('maModelName').value = "";
	document.getElementById('maModelId').value = "";
	getbaseList(1);
	clickState = 0;
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

