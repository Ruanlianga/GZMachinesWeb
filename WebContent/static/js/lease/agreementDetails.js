var taskId;
$(function() {
	taskId = localStorage.getItem("taskId");
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	document.getElementById('firstName').addEventListener('change',function(){
    	if(this.value == "-10"){
        	$("#firstName").html("");
        	$("#sums").html("");
        	$("#model").html("");
    	}else{
    		findModel(this.value);
    	}
    },false);
	
	document.getElementById('firstName').addEventListener('change',function(){
    	findModel(this.value);
    },false);

	document.getElementById('model').addEventListener('change',function(){
    	findSums(this.value);
    },false);
});

function add(){
	cleanForm();
	findType();
	JY.Model.edit("auDiv", "新增", function() {
		if (JY.Validate.form("auForm")) {
			var that = $(this);
			var taskId = localStorage.getItem("taskId");
			var machineNums = document.getElementById("machineNums").value;
			var sums = localStorage.getItem("sums");
			if(parseFloat(machineNums) > parseFloat(sums)){
				//墨绿深蓝风
				layer.alert('库存不够，请重新填写数量！', {
				  skin: 'layui-layer-molv' //样式类名
				  ,closeBtn: 0
				});
			}else{
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/agreementDetails/add', {taskId:taskId}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
			
		}
	});
}

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
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/agreementDetails/findByPage',{taskId:taskId},
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
									$("#addBtn").attr("disabled","disabled");
								}
								html += "<tr>";
								html += "<td style='vertical-align:middle;' class='center hidden-480'>"
										+ (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineType) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.model) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineNums) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.buyPrice) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leasePrice) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.payPrice) + "</td>";
//								if(l.batchStatus == '' || l.batchStatus == null){
//									html += "<td style='vertical-align:middle;' class='center'></td>";
//								}else if(l.batchStatus == 1 || l.batchStatus =='1'){
//									html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>待通知</span></td>";
//								}else if(l.batchStatus == 2 || l.batchStatus =='2'){
//									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待检验</span></td>";
//								}else if(l.batchStatus == 3 || l.batchStatus =='3'){
//									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待打印</span></td>";
//								}else if(l.batchStatus == 4 || l.batchStatus =='4'){
//									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待入库</span></td>";
//								}else if(l.batchStatus == 5 || l.batchStatus =='5'){
//									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>已入库</span></td>";
//								}else if(l.batchStatus == 6 || l.batchStatus =='6'){
//									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>已出库</span></td>";
//								}else {
//									html += "<td class='center'></td>";
//								}
								html += rowFunction(l.typeId,l.taskId,l.batchStatus);
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

function rowFunction(typeId,taskId) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
//	h += "<a href='#' title='查看' onclick='check(&apos;" + maTypeId + "&apos;,&apos;" + batchId + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='验收' onclick='edit(&apos;" + typeId + "&apos;,&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;" + typeId + "&apos;,&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
//	h += "<li><a href='#' title='查看' onclick='check(&apos;" + maTypeId + "&apos;,&apos;" + batchId + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h += "<li><a href='#' title='验收' onclick='edit(&apos;" + typeId + "&apos;,&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='删除' onclick='del(&apos;" + typeId + "&apos;,&apos;" + taskId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function check(typeId,taskId) {
	cleanAcceptForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/accept/find', {
		taskId : taskId,
		typeId:typeId
	}, function(data) {
		setAcceptForm(data);
		JY.Model.check("acceptDiv");
	});
}


function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='taskId']").val(l.taskId);
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


function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(typeIds,taskId) {
	cleanAcceptsForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/accept/find', {
		taskId : taskId,
		typeId:typeIds
	}, function(data) {
		setAcceptsForm(data);
		JY.Model.edit("auAcceptDiv", "修改", function() {
			if (JY.Validate.form("auAcceptForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auAcceptForm", bonuspath + '/backstage/accept/add', {taskId:taskId,typeId:typeId}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
}
var typeId;

function setAcceptsForm(data){
	var l = data.obj;
	if(l.isTest == "1"){
		$(".exteriorCheck").css("display","none");
	}else{
		$(".testCheck").css("display","none");
	}
	typeId = l.typeId;
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
	$("#auAcceptForm input[name$='typeId']").val("");
	$("#auAcceptForm input[name$='checkNum']").val("");
	$("#auAcceptForm input[name$='machineType']").val("");
	$("#auAcceptForm input[name$='model']").val("");
	$('#exteriorCheck1').prop('checked',true);
	$('#testCheck1').prop('checked',true);
	$("#checkConclusion").val("");
}

function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/new/delById', {
			taskId : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function findType(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machine/findType',{},function(data){
		$("#firstName").html("");
		var results = data.obj.list;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.typeId0+"'>"+l.type0+"</option>";
			}
			$("#firstName").append(html);
			findModel($("#firstName").eq(0).val());
		}else{
			html+="<option ></option>";
			$("#firstName").append(html);
		}
	});
}

function findModel(typeId0){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machine/findModel',{typeId0:typeId0},function(data){
		$("#model").html("");
		var results = data.obj.list;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.typeId+"'>"+l.type+"</option>";
			}
			$("#model").append(html);
			findSums($("#model").eq(0).val());
		}else{
			html+="<option ></option>";
			$("#model").append(html);
		}
	});
}

function findSums(type){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machine/findSums',{type:type},function(data){
		$("#sums").html("");
		var results = data.obj.list;
		var html="";
		if(results!=null&&results.length>0){
			var sums = results[0].sums;
			html+=sums;
			$("#sums").append(html);
			localStorage.setItem("sums",sums);
		}else{
			$("#sums").append(html);
		}
	});
}

