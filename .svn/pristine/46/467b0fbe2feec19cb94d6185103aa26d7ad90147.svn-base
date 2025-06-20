$(function() {
//	var parentId = localStorage.getItem("repairId");
//	findBatch(parentId);
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	getbaseList();

	$('#addBtn').on('click',
			function(e) {
				// 通知浏览器不要执行与事件关联的默认动作
				e.preventDefault();
				cleanForm();
				findMachines();
				findTypeModel();
				findRepairDevice();
				var batchNum = localStorage.getItem("batchNum");
				JY.Model.edit("auDiv", "新增", function() {
					if (JY.Validate.form("auForm")) {
						var that = $(this);
						JY.Ajax.doRequest("auForm", bonuspath
								+ '/backstage/repairInput/addDetails', {parentId:parentId,batchNum:batchNum,batchStatus:2}, function(data) {
							that.dialog("close");
							JY.Model.info(data.resMsg, function() {
								var machinesId = data.obj.machinesName;
								var model = data.obj.model;
								var machinesNums = data.obj.machinesNum;
								var machinesNum = parseInt(machinesNums);
								search();
								//idToName(machinesId,model,machinesNum);
							});
						});
					}
				});
		});
});

/**
 * 查询已维修完毕的机具
 */
function findRepairDevice(){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/repair/findRepairDevice', {
		status : 3
	}, function(data) {
		alert("data="+JSON.stringify(data));
		var res = data.obj;
		var deviceNum;
		alert(res.list.length);
		if(res != null || res.list.length > 0){
			for(var i = 0;i<res.list.length;i++){
				deviceNum = res.list[i].deviceNum;
			}
		}
		
	});
}

function findBatch(repairId){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/find', {
		repairId : repairId
	}, function(data) {
		var batchNum = data.obj.batchNum;
		localStorage.setItem("batchNum",batchNum);
		getbaseList();
	});
}

function idToName(machinesId,model,machinesNum){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machines/idToName',
			{
				model : model
			},function(data){
			var modelType = '';
			var results = data.obj;
			if(results!=null&&results.list.length>0){
				modelType = data.obj.list[0].model;
			}
			toMachinesName(machinesId,modelType,machinesNum);
		});
}

function toMachinesName(machinesId,modelType,machinesNum){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machines/toMachinesName',
			{
				machinesId : machinesId
			},function(data){
			var machinesName = '';
			var res = data.obj;
			if(res!= null && res.list.length > 0){
				machinesName = res.list[0].machinesName;
			}
			findByModel(machinesName,modelType,machinesNum)
		});
}

function findByModel(machinesName,modelType,machinesNum){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machines/findByModel',
		{
			machinesName : machinesName,
			model : modelType,
			batchStatus : 5,
			pickNum : machinesNum
		},function(data){
			var res = data.obj;
			var machinesId = '';
			if(res!= null && res.list.length > 0){
				for(var i = 0;i<=res.list.length;i++){
					var l = res.list[i];
					machinesId = l.machinesId;
					outMachines(machinesId);
				}
			}else{
				layer.alert('库存不够', {
				    skin: 'layui-layer-lan'
				    ,closeBtn: 0
				    ,anim: 4 //动画类型
				  });
			}		
	});
}

function outMachines(machinesId){
	alert("machinesId="+machinesId);
//	JY.Ajax.doRequest(null,bonuspath +'/backstage/machines/repairInput',
//			{
//				machinesId : machinesId,
//				batchStatus : 2
//			},function(data){
//			layer.alert('待入库', {
//			    skin: 'layui-layer-lan'
//			    ,closeBtn: 0
//			    ,anim: 4 //动画类型
//			  });
//		});
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

function getbaseList(init) {
	var batchNum = localStorage.getItem("batchNum");
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax
			.doRequest(
					"baseForm",
					bonuspath + '/backstage/repairInput/findByOutNums',
					{batchNum:batchNum},
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
								html += "<td class='center'><label> <input type='checkbox' name='ids' value='"
										+ l.deviceNum
										+ "' class='ace' /> <span class='lbl'></span></label></td>";
								html += "<td class='center hidden-480'>"
										+ (i + leng + 1) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.batchNum) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.batchTime) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.machinesName) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.model) + "</td>";
								html += "<td class='center'>个</td>";
								html += "<td class='center'>1</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.invoiceNum) + "</td>"
//								if(l.invoiceUrl == '' || l.invoiceUrl == null){
//									html += "<td class='center'><a onclick='uploadInvoice(&apos;"+ l.repairId +"&apos;);'>上传发票</a></td>";
//								}else{
//									html += "<td class='center'><a onclick='readInvoice(&apos;"+ l.repairId +"&apos;);'>查看发票</a></td>";
//								}		
								if(l.machinesUrl == '' || l.machinesUrl == null){
									html += "<td class='center'><a onclick='updMachinesPic(&apos;"+ l.repairId +"&apos;);'>上传图片</a></td>";
								}else{
									html += "<td class='center'><a onclick='readMachinesPic(&apos;"+ l.repairId +"&apos;);'>查看图片</a></td>";
								}
//								if(l.batchStatus == '' || l.batchStatus == null){
//									html += "<td class='center'></td>";
//								}else if(l.batchStatus == 1 || l.batchStatus =='1'){
//									html += "<td class='center'><span class='label label-danger'>待打印</span></td>";
//								}else if(l.batchStatus == 2 || l.batchStatus =='2'){
//									html += "<td class='center'><span class='label label-warning'>待入库</span></td>";
//								}else if(l.batchStatus == 3 || l.batchStatus =='3'){
//									html += "<td class='center'><span class='label label-info'>已入库</span></td>";
//								}else if(l.batchStatus == 4 || l.batchStatus =='4'){
//									html += "<td class='center'><span class='label label-info'>待出库</span></td>";
//								}else if(l.batchStatus == 5 || l.batchStatus =='5'){
//									html += "<td class='center'><span class='label label-info'>已出库</span></td>";
//								}
								html += rowFunction(l.deviceNum);
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

function rowFunction(deviceNum) {
	var h = "";
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
//	h += "<a href='#' title='修改' onclick='edit(&apos;"
//			+ id
//			+ "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
//	h += "<a href='#' title='删除' onclick='del(&apos;"
//			+ id
//			+ "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "<a href='#' title='入库' onclick='qrCode(&apos;"
		+ deviceNum 
		+ "&apos;)' class='aBtnNoTD' >入库</a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
//	h += "<li><a href='#' title='修改' onclick='edit(&apos;"
//			+ id
//			+ "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
//	h += "<li><a href='#' title='删除' onclick='del(&apos;"
//			+ id
//			+ "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	h += "<li><a href='#' title='入库' onclick='qrCode(&apos;"
		+ deviceNum
		+ "&apos;)' class='aBtnNoTD' >入库</a></li>";
	h += "</ul></div></div>";
	h += "</td>";

	return h;
}

// 批量删除
$('#delBatchBtn').on('click', function(e) {
	// 通知浏览器不要执行与事件关联的默认动作
	e.preventDefault();
	var chks = [];
	$('#baseTable input[name="ids"]:checked').each(function() {
		chks.push($(this).val());
	});
	if (chks.length == 0) {
		JY.Model.info("您没有选择任何内容!");
	} else {
		JY.Model.confirm("确认要删除选中的数据吗?", function() {
			JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/delDetails', {
				chks : chks.toString()
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		});
	}
});

/**
 * 生成二维码出库
 * @param id
 * @returns
 */

function qrCode(deviceNum){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machines/findQRCodeByDeviceNum', {
		deviceNum : deviceNum
	}, function(data) {
		var res = data.obj;
		var qrcodeUrl;
		if(res != null){
			qrcodeUrl = res.qrcodeUrl;
		}else{
			qrcodeUrl = '暂无二维码';
		}
		findIdByDeviceNum(deviceNum);
	});
}

function findIdByDeviceNum(deviceNum){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machines/findIdByDeviceNum', {
		deviceNum : deviceNum
	}, function(data) {
		var machinesId = data.obj.machinesId;
		readQRCode(machinesId);
	});
}

function readQRCode(id){
	localStorage.setItem("QRCodeId",id);
	localStorage.setItem("path",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['二维码查看','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machines/readQRCode'
	});
	findBatchStatus(id);
}

function check(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/findByBatch', {
		repairId : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='repairId']").val(l.repairId);
	$("#auForm input[name$='batchNum']").val(JY.Object.notEmpty(l.batchNum));
	$("#auForm input[name$='machinesName']").val(JY.Object.notEmpty(l.machinesName));
	$("#auForm input[name$='model']").val(JY.Object.notEmpty(l.model));
	$("#auForm input[name$='machinesNum']").val(JY.Object.notEmpty(l.machinesNum));
	$("#auForm input[name$='machinesUnit']").val(JY.Object.notEmpty(l.machinesUnit));
	$("#auForm input[name$='machinesWeight']").val(JY.Object.notEmpty(l.machinesWeight));
//	$("#auForm input[name$='invoiceNum']").val(JY.Object.notEmpty(l.invoiceNum));
//	$("#auForm input[name$='invoiceUrl']").val(JY.Object.notEmpty(l.invoiceUrl));
	$("#auForm input[name$='machinesUrl']").val(JY.Object.notEmpty(l.machinesUrl));
	$("#auForm input[name$='batchStatus']").val(JY.Object.notEmpty(l.batchStatus));
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='repairId']").val('0');// 上级资源
	$("#auForm input[name$='batchNum']").val('');
	$("#auForm input[name$='machinesName']").val('');
	$("#auForm input[name$='model']").val('');
	$("#auForm input[name$='machinesNum']").val('');
	$("#auForm input[name$='machinesUnit']").val('');
	$("#auForm input[name$='machinesWeight']").val('');
//	$("#auForm input[name$='invoiceNum']").val('');
//	$("#auForm input[name$='invoiceUrl']").val('');
	$("#auForm input[name$='machinesUrl']").val('');
	$("#auForm input[name$='batchStatus']").val('');
	hideRole();
}

function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function search() {
	$("#searchBtn").trigger("click");
}

function uploadInvoice(id){
	localStorage.setItem("updDocId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['发票上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/repairInput/Pic'
	});
}

function readInvoice(id){
	localStorage.setItem("InvoiceId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['发票查看','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/readInvoice.jsp'
	});
}

function updMachinesPic(id){
	localStorage.setItem("updPicId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具图片上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/inPut/machinesPic'
	});
}

function readMachinesPic(id){
	localStorage.setItem("readPicId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['机具图片查看','background-color: #27A3D9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['800px', '430px'],
		content: bonuspath+'/backstage/inPut/readMachinesPic'
	});
}

function edit(id) {
	cleanForm();
	findTypeModel();
	findMachines();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/findByBatch', {
		repairId : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/repairInput/updateDetails', null, function(data) {
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
		JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/delDetails', {
			repairId : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function findMachines(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machines/findMachines',{},function(data){
		 $("#machinesName").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.machinesId+"'>"+l.machinesName+"</option>";
			}
			 $("#machinesName").append(html);
		}else{
			html+="<option ></option>";;
    		$("#machinesName").append(html);
		}
	});

}
function findTypeModel(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machines/findModel',{},function(data){
		$("#model").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.list.length>0){
			for(var i=0;i<results.list.length;i++){
				var l= results.list[i];
				html+="<option value='"+l.machinesId+"'>"+l.model+"</option>";
			}
			$("#model").append(html);
		}else{
			html+="<option ></option>";;
			$("#model").append(html);
		}
	});
	
}


