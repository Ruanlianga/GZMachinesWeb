var taskId;
var applyTime;
var today;
var applyDate;


$(function() {
	taskId = localStorage.getItem("taskId");
	applyTime = localStorage.getItem("applyTime");
	today = new Date().toISOString().split('T')[0]; // 获取今天的日期（格式：YYYY-MM-DD）
	applyDate = new Date(applyTime).toISOString().split('T')[0]; // 转换applyTime为日期（格式：YYYY-MM-DD）
	getbaseList(1);
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	$('#addBtn').on('click',function(e) {
		if (applyDate !== today) {
			layer.alert('领料时间不是今天，不允许进行新增操作', {
				skin: 'layui-layer-molv', // 样式类名
				closeBtn: 0
			});
			return; // 退出函数，阻止新增操作
		}
		$("#maTypeName").attr("readOnly",false);
		$("#maModelName").attr("readOnly",false);
		$(".maTree").css("display","");
		cleanForm();
		JY.Model.edit("auDiv", "新增", function() {
			var that = $(this);
			if (JY.Validate.form("auForm")) {
				var taskId = localStorage.getItem("taskId");
				const leasePlanOutId = localStorage.getItem("leasePlanOutId");
				var maTypeId =$("#maTypeId").val();  //机具类型ID
				var maModelId = $("#maModelId").val();//机具型号ID
				var serviceId = $("#serviceId").val();
				var checkId = $("#checkId").val();
				var machineNum = $("#machineNums").val();//填入需要机具数量
				if(maModelId == 0){
					layer.alert('请重新选择机具规格', {
						  skin: 'layui-layer-molv', //样式类名
						  closeBtn: 0
					});
				}else{
					if( parseInt(machineNum) <= 0 || String(machineNum).split('.')[1] != undefined){
						layer.alert('请输入正整数', {
							  skin: 'layui-layer-molv', //样式类名
							  closeBtn: 0
						});
					}else{
						JY.Ajax.doRequest(null, bonuspath+'/backstage/receiveDetails/add', 
								{
							taskId:taskId,
							leasePlanOutId:leasePlanOutId,
							maModelId:maModelId,
							machinesNum:machineNum,
							customerSrep:serviceId,
							checker:checkId
							}, 
							function(data) {
						JY.Model.info(data.resMsg, function() {
							that.dialog("close");
							$("#maModelId").val('');//机具型号ID
							document.getElementById('maModelId').value = "";
							$("#machineNums").val('');//填入需要机具数量
							$("#serviceId").val('');
							$("#checkId").val('');
							search();
						});
						});
					}
				}
			}
		});
	});
});
var machineTrue='';
function getMachineNum(maModelId){
	JY.Ajax.doRequest(null, bonuspath+ '/backstage/receiveDetails/getMachinesNum', {maModelId:maModelId}, function(data) {
		machineTrue=data.obj.list.machinesNum;
		if(machineTrue == "" || machineTrue == null){
			machineTrue = 0;
		}
		$("#sums").html(machineTrue);
	});
	JY.Ajax.doRequest(null, bonuspath+ '/backstage/receiveDetails/getPreMachinesNum', {maModelId:maModelId}, function(data) {
		$("#preSums").html(data.obj.list.preCollerNum);
	});
}

function machineTree(){
	localStorage.setItem("machId","");
	localStorage.setItem("machName","");
	localStorage.setItem("machTreeName",$("#machinesName").val());
	layer.open({
		type: 2,
		title:['设备名称','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/receiveDetails/machineTree'
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
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/receiveDetails/findByPage',{taskId:taskId},
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
				if(l.isSure==0 || l.isSure=='0'){
					var val = l.taskId + "," + l.maModelId + "," + l.preCollerNum + "," + l.customerSrepId;
					html += "<td style='vertical-align:middle;' class='center'><input type='checkbox' id='inp' value='" + val + "'></td>";
				}else if(l.isSure == 1 || l.isSure == "1"){
					html += "<td style='vertical-align:middle;' class='center'></td>";
				}
				html += "<td style='vertical-align:middle;' class='center hidden-480'>"
						+ (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maType) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModel) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maUnit) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leasePrice) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.preCollerNum) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.alreadyCollerNum) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.customerSrep) + "</td>";
				if(l.isSure==0 || l.isSure=='0'){
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>未确认</span></td>";
				}else{
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'>已确认</span></td>";
				}
			/*	if( l.isExamine == 0 || l.isExamine == '0'){
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>未审核</span></td>";
				}else if(l.isExamine==1 || l.isExamine=='1'){
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'>已通过</span></td>";
				}else{
					
					html += "<td style='vertical-align:middle;' class='center'> <a href='#' onclick='viewAudit(&apos;" + l.auditRemark + "&apos; )'><span style='clolr:red;'>未通过</span></a></td>";
				}*/
				
				if( l.isApproval == 0 || l.isApproval == '0'){
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>未批准</span></td>";
				}else if(l.isApproval == 1 || l.isApproval == '1'){
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'>已通过</span></td>";
				}else{
					html += "<td style='vertical-align:middle;' class='center'> <a href='#' onclick='viewApproval( &apos;" + l.approvalRemark + "&apos;)'><span style='clolr:red;'>未通过</span></a></td>";
				}
				
				//原检验人员Id改为客服代表Id
				html += rowFunction(l.taskId,l.maModelId,l.isSure,l.customerSrepId,l.preCollerNum);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
//							JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='15' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}


function rowFunction(taskId,maModelId,isSure,checkerId,preCollerNum) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(isSure==0 || isSure=='0'){
		h += "<a href='#' title='确认' onclick='check(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;,&apos;" + preCollerNum + "&apos;,&apos;" + checkerId + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
		h += "<a href='#' title='修改' onclick='edit(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
		h += "<a href='#' title='删除' onclick='del(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	}else if(isSure == 1 || isSure == "1"){
		h += "<a href='#' title='修改' onclick='edit(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
		h += "<a href='#' title='删除' onclick='del(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	}
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(isSure==0 || isSure=='0'){
		h += "<li><a href='#' title='确认' onclick='check(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;,&apos;" + preCollerNum + "&apos;,&apos;" + checkerId + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a></li>";
		h += "<li><a href='#' title='修改' onclick='edit(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
		h += "<li><a href='#' title='删除' onclick='del(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	}else if(isSure == 1 || isSure == "1"){
		h += "<li><a href='#' title='修改' onclick='edit(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
		h += "<li><a href='#' title='删除' onclick='del(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	}
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}


function viewAudit(auditRemark){

	$("#remarkForm input[name$='remarks']").val(auditRemark);
    JY.Model.check("remarkDiv");
}

function viewApproval(approvalRemark){
	
	$("#remarkForm input[name$='remarks']").val(approvalRemark);
	JY.Model.check("remarkDiv");
}



function noEdit(){
	layer.alert('机具领料任务已发布，不予修改！', {
		  skin: 'layui-layer-molv' //样式类名
		  ,closeBtn: 0
		});
}

function check(taskId,maModelId,preCollerNum,checkerId) {
	console.log("id="+taskId+","+maModelId+","+preCollerNum+","+checkerId);
	JY.Model.confirm("确认发布吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/receiveDetails/isSure', {
			taskId:taskId,
			maModelId:maModelId,
			preCollerNum:preCollerNum,
			checkerId:checkerId
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
				getbaseList(1);
			});
		});
	});
}
function del(taskId,maModelId) {
	if (applyDate !== today) {
		layer.alert('领料时间不是今天，不允许进行删除操作', {
			skin: 'layui-layer-molv', // 样式类名
			closeBtn: 0
		});
		return; // 退出函数
	}
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/receiveDetails/del', {   
			taskId : taskId,
			maModelId: maModelId
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
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
	JY.Tags.cleanForm("auForm");
	$("#auForm input[id$='batchId']").val("");// 上级资源
	$("#auForm input[id$='maTypeId']").val(0);
	$("#auForm input[id$='maTypeName']").val('');
	$("#auForm input[id$='maModelName']").val('');
	$("#auForm input[id$='maModelId']").val(0);
	$("#auForm input[id$='machineNums']").val('');
	$("#sums").html("0");
	$("#preSums").html("0");
}


function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(taskId,maModelId) {
	if (applyDate !== today) {
		layer.alert('领料时间不是今天，不允许进行修改操作', {
			skin: 'layui-layer-molv', // 样式类名
			closeBtn: 0
		});
		return; // 退出函数
	}
	$("#maTypeName").attr("readOnly",true);
	$("#maModelName").attr("readOnly",true);
	$(".maTree").css("display","none");
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/receiveDetails/find', {
		taskId : taskId,
		maModelId:maModelId
	}, function(data) {
		setDetaFrom(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				var maModelId = $("#maModelId").val();//机具型号ID
				var machineNums = $("#machineNums").val();//填入需要机具数量
				var serviceId = $("#serviceId").val(); //客服代表id
					if(parseFloat(machineNums)>parseFloat(machineTrue)){
						layer.alert('库存不够，请重新填写数量！', {
							  skin: 'layui-layer-molv' //样式类名
							  ,closeBtn: 0
							});
					}else if( parseInt(machineNums) <= 0 || String(machineNums).split('.')[1] != undefined){
						layer.alert('请输入正整数', {
							  skin: 'layui-layer-molv', //样式类名
							  closeBtn: 0
						});
					}else{								//machinesType原来的ID machinesModel修改后的ID
						JY.Ajax.doRequest(null, bonuspath+'/backstage/receiveDetails/update',
								{taskId:taskId, maModelId:maModelId, machinesNum:machineNums,serviceId:serviceId}, 
								function(data) {
							JY.Model.info(data.obj, function() {
								that.dialog("close");
								search();
							});
						});
					}
			}
		});
	});
}
var typeId;

function setDetaFrom(data){
	var l = data.obj[0];
	console.info(JSON.stringify(data))
	console.info(l.maType)
	
	$("#maTypeName").val(l.maType);//机具类型名称
	//$("#maTypeId").val(l.machinesId);//机具类型ID
	$("#maModelName").val(l.maModel);//规格型号名称
	$("#maModelId").val(l.maModelId);//规格型号ID
	$("#machineNums").val(l.preCollerNum);//机具数量
	$("#serviceName").val(l.customerSrep);
	$("#serviceId").val(l.customerSrepId);
	$("#checkName").val(l.checker);
getMachineNum(l.maModelId);
}

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

function setMaTypeForm(){
	var maTypeId = localStorage.getItem("maTypeId");
	var maTypeName = localStorage.getItem("maTypeName");
	$("#maTypeId").val(maTypeId);
	$("#maTypeName").val(maTypeName);
	$("#maModelId").val(0);
	$("#maModelName").val("");
}
function setCheckForm(){
	var checkId = localStorage.getItem("checkId");
	var checkName = localStorage.getItem("checkName");
	$("#checkId").val(checkId);
	$("#checkName").val(checkName);
}
function setServiceForm(){
	var serviceId = localStorage.getItem("serviceId");
	var serviceName = localStorage.getItem("serviceName");
	$("#serviceId").val(serviceId);
	$("#serviceName").val(serviceName);
}

function setMaModelForm(){
	var maModelId = localStorage.getItem("maModelId");
	var maModelName = localStorage.getItem("maModelName");
	$("#maModelId").val(maModelId);
	$("#maModelName").val(maModelName);
getMachineNum(maModelId);
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
	if(maTypeId == "0"){
		JY.Model.info("请选择物资名称！");
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

$('#delBatchBtn').on(
		'click',
		function(e) {
			var taskId = localStorage.getItem("taskId");
			JY.Model.confirm("确认发布吗？", function() {
				JY.Ajax.doRequest(null, bonuspath
						+ '/backstage/receiveDetails/allSure', {
					taskId : taskId,
				}, function(data) {
					JY.Model.info(data.resMsg, function() {
						getbaseList(1);
					});
				});
			});

		});
/**
 * 批量确认
 * @returns
 */
function batchConfirmation(){
	var vals = backShowChenkStatus();
	var token = $("#token").val();
	JY.Model.confirm("确认发布吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/receiveDetails/isSures', {
			value:vals,
			token:token
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
				getbaseList(1);
				vals = "";
			});
		});
	});
}

/**
 * 批量删除
 * @returns
 */
function batchDeletion(){
	if (applyDate !== today) {
		layer.alert('领料时间不是今天，不允许进行批量删除操作', {
			skin: 'layui-layer-molv', // 样式类名
			closeBtn: 0
		});
		return; // 退出函数
	}
	var vals = backShowChenkStatus();
	var token = $("#token").val();
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/receiveDetails/batchDeletion', {
			value:vals,
			token:token
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
				getbaseList(1);
				vals = "";
			});
		});
	});
}

function backShowChenkStatus(){
	var vals = '';
	$('input[type=checkbox]:checked').each(function(){
		var val = $(this).val();	
		console.log("val=",val);
		vals += val+"-"; 
		console.log("vals=",vals);	
	})
	return vals;
}
