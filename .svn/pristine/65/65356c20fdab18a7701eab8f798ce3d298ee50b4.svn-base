var init = 1;
var keyWord = "";
var type0 = "";
var type = "";
var startTime = "";
var endTime = "";
var batchStatus = "0";
$(function() {

	getbaseList();
	getNewDataTable(null);
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});

	
	$('#resetBtn').on('click', function(e) {
		$("#keyWord").val("");
		$("#type0").val("");
		$("#type").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#batchStatus").val("0");
		$("#batchStatus option[value='0']").attr("selected","true");
		init = 1;
		getbaseList(1);
	});
	

});


function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/machine/export');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}


function emptyRole() {
	$("#orgName").prop("value", "");
	$("#auForm input[name$='orgId']").prop("value", "0");
}

var preisShow = false;// 窗口是否显示
function showRole() {
	if (preisShow) {
		hideRole();
	} else {
		var obj = $("#orgName");
		var offpos = $("#orgName").position();
		$("#orgContent").css({
			left : offpos.left + "px",
			top : offpos.top + obj.heith + "px"
		}).slideDown("fast");
		preisShow = true;
	}
}
var hideRole = function() {
	$("#orgContent").fadeOut("fast");
	preisShow = false;
}
function clickRole(e, treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (check) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree"), nodes = zTree
				.getSelectedNodes(), v = "", n = "", o = "", p = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";// 获取name值
			n += nodes[i].id + ",";// 获取id值
			o += nodes[i].other + ",";// 获取自定义值
			var pathNodes = nodes[i].getPath();
			for (var y = 0; y < pathNodes.length; y++) {
				p += pathNodes[y].name + "/";// 获取path/name值
			}
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (n.length > 0)
			n = n.substring(0, n.length - 1);
		if (o.length > 0)
			o = o.substring(0, o.length - 1);
		if (p.length > 0)
			p = p.substring(0, p.length - 1);
		$("#orgName").val(p);
		$("#auForm input[name$='orgId']").prop("value", n);
		hideRole();
	}
}

function getbaseList(index) {
	keyWord = $("#keyWord").val();
	typeName = $("#type0").val();
	type = $("type").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	batchStatus = $("#batchStatus").val();
	init = $(".pageNum").val();
	$(".pageNum").val(init);
	if (index == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/disassembly/findByPage',null,
		function(data) {
			$("#baseTable tbody").empty();
			var msg = data.resMsg;
			var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
			var html = "";
			console.log(results);
			if (results != null && results.length > 0) {
				var leng = (pageNum - 1) * pageSize;
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center hidden-480'>"
							+ (i + leng + 1) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.type) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
					if(l.batchStatus == '' || l.batchStatus == null){
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}else if(l.batchStatus == 1 || l.batchStatus =='1'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-default'>待通知</span></td>";
					}else if(l.batchStatus == 2 || l.batchStatus =='2'){
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-primary'>待采购检验</span></td>";
					}else if(l.batchStatus == 3 || l.batchStatus =='3'){
//						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick='toQRCode(&apos;"+ l.id +"&apos;);'>打印</a></td>";
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待打印</span></td>";
					}else if(l.batchStatus == 4 || l.batchStatus =='4'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待入库</span></td>";
					}else if(l.batchStatus == 5 || l.batchStatus =='5'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>在库</span></td>";
					}else if(l.batchStatus == 6 || l.batchStatus =='6'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>在用</span></td>";	
					}else if(l.batchStatus == 7 || l.batchStatus =='7'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在修</span></td>";
					}else if(l.batchStatus == 8 || l.batchStatus =='8'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>已检验</span></td>";
					}else if(l.batchStatus == 9 || l.batchStatus =='9'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>修试后待入库</span></td>";
					}else if(l.batchStatus == 10 || l.batchStatus =='10'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待报废</span></td>";	
					}else if(l.batchStatus == 11 || l.batchStatus =='11'){
						if(l.isScrap == 1 || l.isScrap == '1'){
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>已报废</span></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>待报废入库</span></td>";
						}
					}else if(l.batchStatus == 12 || l.batchStatus =='12'){
						if(l.isScrap == 1 || l.isScrap =='1'){
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已封存</span></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待封存入库</span></td>";
						}
					}else if(l.batchStatus == 13 || l.batchStatus =='13'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在检</span></td>";
					}else if(l.batchStatus == 14 || l.batchStatus =='14'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>出库待审</span></td>";
					}else if(l.batchStatus == 16 || l.batchStatus =='16'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待批准</span></td>";
					}else if(l.batchStatus == 17 || l.batchStatus =='17'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>退料检验</span></td>";
					}else if(l.batchStatus == 18 || l.batchStatus =='18'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>入库待审</span></td>";
					}else if(l.batchStatus == 19 || l.batchStatus =='19'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>维修合格</span></td>";
					}else if(l.batchStatus == 20 || l.batchStatus =='20'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已拆分</span></td>";
					}else if(l.batchStatus == 21 || l.batchStatus =='21'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已组装</span></td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}
					if(l.batchStatus == 5 || l.batchStatus =='5'){
					html+= "<td style='vertical-align:middle;' class='center'>";
					html += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
					html += "<a href='#' title='添加' onclick='addNewTable(&apos;" + l.id + "&apos;,&apos;" +l.typeName + "&apos;)' class='aBtnNoTD' ><i class='icon-plus color-p bigger-140'></i></a>";
					html += "</div>";
					html += "</td>";
					}else{
						html+= "<td style='vertical-align:middle;' class='center'>";
						html += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
						html += "</div>";
						html += "</td>";
					}
					//操作按钮
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
var i=1;
var idd='';
var results = new Array();
function addNewTable(id,typeName){
	JY.Ajax.doRequest(null, bonuspath + '/disassembly/getListDataById', {
		id:id,
		
	}, function(data) {
		 var da=data.obj;
		 var id=da.id;
		 if(results.length>0){
			 var isAdd=false;
			 var  isType=false;
			 for ( var i in results) {
					var datsId=results[i].id;
					var typeNames=results[i].typeName;
					if(typeName!=typeNames){
						isType=true;
						break;
					}
					if(id==datsId){
						isAdd=true;
						break;
						
					}
				}
			 if(isAdd){
					JY.Model.info("该设备已添加");
					return;
				}
			 if(isType){
					JY.Model.info("机具类型不一致!");
					return;
				}
		 }
		 results.push(da);
		 getNewDataTable(results);
	});
	
}


//添加表数据信息
function getNewDataTable(results){
	$("#newBaseTable tbody").empty();
	var html = "";
	console.log(results);
	if (results != null && results.length > 0) {
		
		for (var i = 0; i < results.length; i++) {
			var l = results[i];
			html += "<tr>";
			html += "<td style='vertical-align:middle;' class='center hidden-480'>"
					+ (i + 1) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.type) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
			
			if(l.batchStatus == '' || l.batchStatus == null){
				html += "<td style='vertical-align:middle;' class='center'></td>";
			}else if(l.batchStatus == 1 || l.batchStatus =='1'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-default'>待通知</span></td>";
			}else if(l.batchStatus == 2 || l.batchStatus =='2'){
				html += "<td style='vertical-align:middle;' class='center'></td>";
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-primary'>待采购检验</span></td>";
			}else if(l.batchStatus == 3 || l.batchStatus =='3'){
//				html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick='toQRCode(&apos;"+ l.id +"&apos;);'>打印</a></td>";
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待打印</span></td>";
			}else if(l.batchStatus == 4 || l.batchStatus =='4'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待入库</span></td>";
			}else if(l.batchStatus == 5 || l.batchStatus =='5'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>在库</span></td>";
			}else if(l.batchStatus == 6 || l.batchStatus =='6'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>在用</span></td>";	
			}else if(l.batchStatus == 7 || l.batchStatus =='7'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在修</span></td>";
			}else if(l.batchStatus == 8 || l.batchStatus =='8'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>已检验</span></td>";
			}else if(l.batchStatus == 9 || l.batchStatus =='9'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>修试后待入库</span></td>";
			}else if(l.batchStatus == 10 || l.batchStatus =='10'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待报废</span></td>";	
			}else if(l.batchStatus == 11 || l.batchStatus =='11'){
				if(l.isScrap == 1 || l.isScrap == '1'){
					html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>已报废</span></td>";
				}else{
					html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>待报废入库</span></td>";
				}
			}else if(l.batchStatus == 12 || l.batchStatus =='12'){
				if(l.isScrap == 1 || l.isScrap =='1'){
					html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已封存</span></td>";
				}else{
					html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待封存入库</span></td>";
				}
			}else if(l.batchStatus == 13 || l.batchStatus =='13'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在检</span></td>";
			}else if(l.batchStatus == 14 || l.batchStatus =='14'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>出库待审</span></td>";
			}else if(l.batchStatus == 16 || l.batchStatus =='16'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待批准</span></td>";
			}else if(l.batchStatus == 17 || l.batchStatus =='17'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>退料检验</span></td>";
			}else if(l.batchStatus == 18 || l.batchStatus =='18'){
				html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>入库待审</span></td>";
			}else{
				html += "<td style='vertical-align:middle;' class='center'></td>";
			}
			
			
			html+= "<td style='vertical-align:middle;' class='center'>";
			html += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
			html += "<a href='#' title='删除' onclick='deleteData("+l.ids+")' class='aBtnNoTD' ><i class='icon-remove-sign color-p bigger-140'></i></a>";
			html += "</div>";
			html += "</td>";
			html += "</tr>";
		}
		$("#newBaseTable tbody").append(html);
	} else {
		html += "<tr><td colspan='14' class='center'>没有相关数据</td></tr>";
		$("#newBaseTable tbody").append(html);
	}
	
}
function deleteData(ids){
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var idd=l.ids;
		if(ids==idd){
			results.splice(i,1);
			break;
		}
	}
	 getNewDataTable(results);
}

//数据校验
function saveNewData(){
	$("#maTypeId").val("");
	$("#maTypeName").val("");
	$("#maModelId").val("");
	$("#maModelName").val("");
	$("#deviceCodes").val("");
	 if(results.length<0){
		 JY.Model.info("请先添加设备!");
			return;
	 }
	 var  type=results[0].type;
	 var  typeName=results[0].typeName;
		JY.Model.edit("auDiv", "新增", function() {
			var that = $(this);
			var deviceCode=$("#deviceCodes").val();
			var maTypeName= $("#maTypeName").val();
			var maModelName=$("#maModelName").val();
			var maModelId=$("#maModelId").val();
			if(deviceCode==null || deviceCode==''){
				JY.Model.info("请填写设备编码");
				return false;
			}
			if(maTypeName==null || maTypeName==''){
				JY.Model.info("请选择机具设备");
				return false;
			}
			if(maModelName==null || maModelName==''){
				JY.Model.info("强选择规格型号");
				return false;
			}
			var  isOnly=onlyDeviceCode(maModelId,maTypeName,deviceCode);
			if(isOnly){
				JY.Model.info("设备编码重复!");
				return false;
			}
//检验正常入库
		var array=new Array();
		var array2=new Array();
		for (var i = 0; i < results.length; i++) {
			array2.push(results[i].deviceCode);
			array.push(results[i].id);
		}
		var da=array.join("@");
		var oldCode=array2.join(",");
		JY.Model.loading();
		//数据后台处理
		JY.Ajax.doRequest(null, bonuspath + '/disassembly/addDataSynthesis', {
			id : da,
			deviceCode:deviceCode,
			originNum:oldCode,
			type:maModelId,
		}, function(data) {
			that.dialog("close");
			console.log(data);
			JY.Model.info(data.obj);
			getbaseList();
			getNewDataTable(null);
			results = new Array();
			JY.Model.loadingClose();
		});	
			
		
});
	
}
var isOpen3=false;
function  onlyDeviceCode(type,typeName,deviceCode){
	var returnData=false;
	$.ajax({
		url: bonuspath + '/disassembly/getListDataByDeviceCode',
		data:{
			type : type,
			deviceCode:deviceCode,
			typeName:typeName
		},
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			console.log(data);
			if(data.obj==1){
				returnData=true;
			}else{
				returnData=false;
			}
		}
	})
	return returnData;
}

function maTypeTree(){
	localStorage.setItem("maTypeId","");
	localStorage.setItem("maTypeName","");
	localStorage.setItem("maTypeTreeName","");
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

function setMaTypeForm(){
	var maTypeId = localStorage.getItem("maTypeId");
	var maTypeName = localStorage.getItem("maTypeName");
	$("#maTypeId").val(maTypeId);
	$("#maTypeName").val(maTypeName);
	$("#maModelId").val("");
	$("#maModelName").val("");
}
function setMaModelForm(){
	var maModelId = localStorage.getItem("maModelId");
	var maModelName = localStorage.getItem("maModelName");
	$("#maModelId").val(maModelId);
	$("#maModelName").val(maModelName);
}
//记录查询
function history(){
	if(isOpen3){
		return false;
	}
	localStorage.setItem("flowType","2");
	layer.open({
		type: 2,
		title:['合成记录','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['1200px', '750px'],
		content: bonuspath+'/disassembly/historyFlow',
		cancel: function(){
			// 右上角关闭事件的逻辑
			isOpen3=false;
		}
	});
	
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











