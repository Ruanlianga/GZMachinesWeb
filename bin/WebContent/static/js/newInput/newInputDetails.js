var batchsId;
var buyTime;
$(function() {
	batchsId = localStorage.getItem("batchId");
	buyTime = localStorage.getItem("buyTime");
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	document.getElementById('firstName').addEventListener('change',function(){
    	findSecondName(this.value);
    },false);
    
    document.getElementById('secondName').addEventListener('change',function(){
    	findParentName(this.value);
    },false);
    
    document.getElementById('parentName').addEventListener('change',function(){
    	findModel(this.value);
    },false);

	$('#addBtn').on('click',
			function(e) {
				// 通知浏览器不要执行与事件关联的默认动作
				e.preventDefault();
				cleanForm();
				findFirstName();
				findVender();
				JY.Model.edit("auDiv", "新增", function() {
					if (JY.Validate.form("auForm")) {
						var that = $(this);
						$("#batchId").val(batchsId);
						var company = $("#company").val();
						JY.Ajax.doRequest("auForm", bonuspath
								+ '/backstage/inputDetails/add', {batchStatus:1}, function(data) {
							that.dialog("close");
							JY.Model.info(data.resMsg, function() {
								var batchId = data.obj.batchId;
								var type = data.obj.model;
								var machinesNum = data.obj.machineNums;
								var batchStatus = data.obj.batchStatus;
								var vender = data.obj.vender;
								search();
//								var type = data.obj.parentId;
								for(var i = 1; i <= parseInt(machinesNum);i++){
									var isFixedAssets;
									var isTrack;
									var isCheck;
									var cycleNum;
									var remark;
									batchAddMachines(company,batchId,type,machinesNum,vender,batchStatus,
											isFixedAssets,isTrack,isCheck,buyTime,cycleNum,remark);
								}
							});
						});
					}
				});
		});
});

function selectPerson(){
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['完善通知信息','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['800px', '430px'],
		content: bonuspath+'/backstage/user/select'
	});
}

function batchAddMachines(company,batchId,type,machinesNum,vender,batchStatus,isFixedAssets,isTrack,isCheck,buyTime,cycleNum,remark){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machine/add',
		{
			taskId : batchId,
			type : type,
			machinesNum :machinesNum,
			venderName : vender,
			batchStatus : batchStatus,
			isFixedAssets : 0,
			isTrack : 1,		//跟踪
			isCheck : 1,		//需要检验
			buyTime : buyTime,
			equipmentState : 1,
			outInNum : 0,
			remarks : '无',
			deviceNum:company
		},function(data){
	});
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
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/inputDetails/findByPage',{batchId:batchsId},
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
					if(l.batchStatus >= 2){
						$("#selectPersonBtn").attr("disabled","disabled");
					}
					html += "<tr>";
					html += "<td class='center'><label> <input type='checkbox' name='ids' value='"
							+ l.batchId
							+ "' class='ace' /> <span class='lbl'></span></label></td>";
					html += "<td class='center hidden-480'>"
							+ (i + leng + 1) + "</td>";
					html += "<td class='center'>"+ JY.Object.notEmpty(l.machineType) + "</td>";
					html += "<td class='center'>"+ JY.Object.notEmpty(l.model) + "</td>";
					html += "<td class='center'>"+ JY.Object.notEmpty(l.machineNums) + "</td>";
					if(l.picUrl == '' || l.picUrl == null){
						html += "<td class='center'><a onclick='updMachinesPic(&apos;"+ l.id + "" + l.batchId +"&apos;);'>上传图片</a></td>";
					}else{
						html += "<td class='center'><a onclick='readMachinesPic(&apos;"+ l.batchId +"&apos;);'>查看图片</a></td>";
					}
					/*if(l.isFixedAssets == '' || l.isFixedAssets == null){
						html += "<td class='center'><a onclick='toFixedAssets(&apos;"+ l.batchId +"&apos;);'>转固定资产</a></td>";
					}else if(l.isFixedAssets == '1' || l.isFixedAssets == 1){
						html += "<td class='center'>是</td>";
					}else if(l.isFixedAssets == '0' || l.isFixedAssets == 0){
						html += "<td class='center'><a onclick='toFixedAssets(&apos;"+ l.batchId +"&apos;);'>固定资产</a></td>";
					}else{
						html += "<td class='center'><a onclick='toFixedAssets(&apos;"+ l.batchId +"&apos;);'>固定资产</a></td>";
					}*/
					if(l.batchStatus == '' || l.batchStatus == null){
						html += "<td class='center'></td>";
					}else if(l.batchStatus == 1 || l.batchStatus =='1'){
						html += "<td class='center'><span class='label label-danger'>待通知</span></td>";
					}else if(l.batchStatus == 2 || l.batchStatus =='2'){
						html += "<td class='center'><span class='label label-warning'>待检验</span></td>";
					}else if(l.batchStatus == 3 || l.batchStatus =='3'){
						html += "<td class='center'><span class='label label-info'>待打印</span></td>";
					}else if(l.batchStatus == 4 || l.batchStatus =='4'){
						html += "<td class='center'><span class='label label-info'>待入库</span></td>";
					}else if(l.batchStatus == 5 || l.batchStatus =='5'){
						html += "<td class='center'><span class='label label-info'>已入库</span></td>";
					}else{
						html += "<td class='center'></td>";
					}
					console.info("maTypeId="+l.maTypeId);
					html += rowFunction(l.batchId,l.maTypeId);
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
		}
	);
}

function rowFunction(id,maTypeId) {
	var h = "";
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;" + id +"&apos;,&apos;"+ maTypeId + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='验收' onclick='edit(&apos;" + id +"&apos;,&apos;"+ maTypeId  + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;" + id +"&apos;,&apos;"+ maTypeId  + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='查看' onclick='check(&apos;" + id +"&apos;,&apos;"+ maTypeId  + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h += "<li><a href='#' title='验收' onclick='edit(&apos;" + id +"&apos;,&apos;"+ maTypeId  + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='删除' onclick='del(&apos;" + id +"&apos;,&apos;"+ maTypeId  + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function check(id,maTypeIds) {
	cleanAcceptForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/inputDetails/find', {
		batchId : id,
		maTypeId:maTypeIds
	}, function(data) {
		setAcceptForm(data);
		JY.Model.check("acceptDiv");
	});
}


function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='batchId']").val(l.batchId);
	$("#auForm input[name$='model']").val(JY.Object.notEmpty(l.model));
	$("#auForm input[name$='machinesNum']").val(JY.Object.notEmpty(l.machinesNum));
	$("#auForm input[name$='outNum']").val(JY.Object.notEmpty(l.outNum));
	$("#auForm input[name$='invoiceNum']").val(JY.Object.notEmpty(l.invoiceNum));
	$("#auForm input[name$='invoiceUrl']").val(JY.Object.notEmpty(l.invoiceUrl));
	$("#auForm input[name$='machinesUrl']").val(JY.Object.notEmpty(l.machinesUrl));
	$("#auForm input[name$='batchStatus']").val(JY.Object.notEmpty(l.batchStatus));
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='model']").val('');
	$("#auForm input[name$='machinesNum']").val('');
	$("#auForm input[name$='outNum']").val('');
	$("#auForm input[name$='invoiceNum']").val('');
	$("#auForm input[name$='invoiceUrl']").val('');
	$("#auForm input[name$='machinesUrl']").val('');
	$("#auForm input[name$='batchStatus']").val('');
	hideRole();
}

function setAcceptForm(data) {
	var l = data.obj;
	$("#models").html(JY.Object.notEmpty(l.model));
	$("#machineTypes").html(JY.Object.notEmpty(l.machineType));
	$(".machinesNums").html(JY.Object.notEmpty(l.machineNums));
	$("#venders").html(JY.Object.notEmpty(l.vender));
}

function cleanAcceptForm() {
	$("#models").html('');
	$("#machineTypes").html('');
	$(".machinesNums").html('');
	$("#venders").html('');
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
	  title:['发票上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/updInvoice.jsp'
	});
}

function readInvoice(id){
	localStorage.setItem("InvoiceId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['发票查看','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/readInvoice.jsp'
	});
}

function updMachinesPic(id,batchId){
	localStorage.setItem("updPicId",id);
	localStorage.setItem("taskId",batchId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具图片上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/inputDetails/machinesPic'
	});
}

function readMachinesPic(id,batchId){
	localStorage.setItem("readPicId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
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

function edit(id,maTypeIds) {
	cleanAcceptsForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/accept/find', {
		batchId : id,
		maTypeId:maTypeIds
	}, function(data) {
		setAcceptsForm(data);
		JY.Model.edit("auAcceptDiv", "修改", function() {
			if (JY.Validate.form("auAcceptForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auAcceptForm", bonuspath + '/backstage/accept/add', {taskId:id,maTypeId:maTypeId}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
}
var maTypeId;
function setAcceptsForm(data){
	alert(JSON.stringify(data));
	var l = data.obj;
	if(l.isTest == "1"){
		$(".exteriorCheck").css("display","none");
	}else{
		$(".testCheck").css("display","none");
	}
	maTypeId = l.maTypeId;
	$("#machineType").val(JY.Object.notEmpty(l.machineType));
	$("#modelType").val(JY.Object.notEmpty(l.model));
	$("#auAcceptForm input[name$='checkNum']").val(JY.Object.notEmpty(l.checkNum));
	if(l.exteriorCheck == "1") {
		$('#exteriorCheck1').prop('checked',true);
	} 
	if(l.exteriorCheck == "0") {
		$('#exteriorCheck0').prop('checked',true);
	}
	if(l.setestCheckx == "1") {
		$('#testCheck1').prop('checked',true);
	} 
	if(l.testCheck == "0") {
		$('#testCheck0').prop('checked',true);
	}
	$("#checkConclusion").val(JY.Object.notEmpty(l.checkConclusion));
}

function cleanAcceptsForm(){
	$("#auAcceptForm input[name$='taskId']").val("");
	$("#auAcceptForm input[name$='maTypeId']").val("");
	$("#auAcceptForm input[name$='checkNum']").val("");
	$("#auAcceptForm input[name$='machineType']").val("");
	$("#auAcceptForm input[name$='model']").val("");
	$('#exteriorCheck1').prop('checked',true);
	$('#testCheck1').prop('checked',true);
	$("#auAcceptForm input[name$='checkConclusion']").val("");
}

function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/new/delById', {
			batchId : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function findFirstName(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machineType/findFirstName',{parentId:0},function(data){
		$("#firstName").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#firstName").append(html);
		}else{
			html+="<option ></option>";;
			$("#firstName").append(html);
		}
	});
}

function findSecondName(parentId){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machineType/findFirstName',{parentId:parentId},function(data){
		$("#secondName").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#secondName").append(html);
		}else{
			html+="<option ></option>";;
			$("#secondName").append(html);
		}
	});
}

function findParentName(parentId){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machineType/findFirstName',{parentId:parentId},function(data){
		$("#parentName").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#parentName").append(html);
		}else{
			html+="<option ></option>";;
			$("#parentName").append(html);
		}
	});
}

function findModel(parentId){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machineType/findFirstName',{parentId:parentId},function(data){
		$("#model").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#model").append(html);
		}else{
			html+="<option ></option>";;
			$("#model").append(html);
		}
	});
}

function findVender(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/vender/findVender',{},function(data){
		$("#vender").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#vender").append(html);
		}else{
			html+="<option ></option>";;
			$("#vender").append(html);
		}
	});
}

