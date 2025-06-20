var ids = '';
$(function() {
//	agreementInfo();
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
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});

});



//返回签订协议页面
function backToAgreen(){
	var url = bonuspath + '/backstage/agreement/backToAgreen';  
    window.location.href = url;
}

function loadOrgTree(){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/project/findWorkTree',null,function(data){
		$.fn.zTree.init($("#workTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
	});
}

function emptyRole() {
	$("#workNames").prop("value", "");
	$("#auForm input[name$='projectName']").prop("value", "0");
}

var preisShow = false;// 窗口是否显示
function showRole() {
	if (preisShow) {
		hideRole();
	} else {
		var obj = $("#workNames");
		var offpos = $("#workNames").position();
		$("#workContent").css({
			left : offpos.left + "px",
			top : offpos.top + obj.heith + "px"
		}).slideDown("fast");
		preisShow = true;
	}
}
var hideRole = function() {
	$("#workContent").fadeOut("fast");
	preisShow = false;
}
function clickRole(e, treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (check) {
		var zTree = $.fn.zTree.getZTreeObj("workTree"), nodes = zTree
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
		$("#workNames").val(p);
		$("#auForm input[name$='projectName']").prop("value", n);
		hideRole();
	}
}

function agreementInfo(){
	//iframe层
	layer.open({
	  type: 2,
	  title: '协议签订',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['610px', '95%'],
	  content: bonuspath + '/backstage/agreement/context'
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
function setServiceForm(){
	var serviceId = localStorage.getItem("serviceId");
	var serviceName = localStorage.getItem("serviceName");
	$("#serviceId").val(serviceId);
	$("#serviceName").val(serviceName);
	
}



function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/putAudit/findByPage',
					null,
					function(data) {
		              //alert("data="+JSON.stringify(data));
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
								html += "<tr ondblclick='selectData(this)'>";
								html += "<td style='vertical-align:middle;' class='center'><label>" +
											"<input type='checkbox' name='checkId' class='ids' id='inp' value="+l.id+">" +
											"<input type='hidden' value="+l.taskId+">" +
											"<input type='hidden' value="+l.maModelId+">" +
											"<input type='hidden' value="+l.isCount+" ></label></td>";
								html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leaseName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.projectName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineModel) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.alPutNum) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.definitionName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operationTime) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.putPerson) + "</td>";
								html += rowFunction(l.id,l.taskId,l.maModelId,l.isCount,l.isExamine);
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='13' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}

function rowFunction(id,taskId,maModelId,isCount,isExamine) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(isExamine==0 || isExamine=='0'){
		h += "<a href='#' title='审核' onclick='isAudit(&apos;" + id + "&apos;,&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;,&apos;" + isCount + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(isExamine==0 || isExamine=='0'){
		h += "<li><a href='#' title='审核' onclick='isAudit(&apos;" + id + "&apos;,&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;,&apos;" + isCount + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a></li>";
	}
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function isAudit(id,taskId,maModelId,isCount) {
		JY.Model.confirm("确认审核吗？", function() {
			JY.Ajax.doRequest(null, bonuspath + '/backstage/putAudit/isAudit', {
				id:id,
				taskId:taskId,
				maModelId:maModelId,
				isCount:isCount
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					search();
					getbaseList(1);
				});
			});
		});
}

function backShowChenkStatus(){
	$('input[type=checkbox]:checked').each(function(){
		var id = $(this).val();
		var taskId = $(this).next().val();
		var maModelId = $(this).next().next().val();
		var isCount = $(this).next().next().next().val();
		var allId = id+","+taskId+","+maModelId+","+isCount;
		console.log("allId=",allId);
		ids += allId+"-"; 
		console.log("ids=",ids);
		
		
	})
}

/**
 * @author 守约
 * @date 2020-10-12
 * @function 生成字符串数组
 * @returns 
 */
function getIdsString(){
	var idsArr = [];
	var idsStr = "";
	for ( var property in ids){
		if(idsStr == ""){
			idsStr = property;
		} else {
			idsStr += ","+property; 
		}
		idsArr.push(idsStr);
	}
	return idsArr;
}

function batchAudit() {
	backShowChenkStatus();
	//var idsStr = getIdsString();
	var idsStr = ids;
	if(idsStr == "" ){
		layer.msg("请选择想要审核的信息!",{icon:7,time:2000});
		return;
	}
	console.log("idsStr=",idsStr);
	var idx = layer.msg('审核申请提交中,请稍等...', {
		  icon: 16
		  ,shade: 0.01
		  ,time:'-1'
	});
	var data = {
		ids:idsStr	
	}
	
	$.ajax({
		type:'POST',
		url:bonuspath+'/backstage/putAudit/batchAudit',
		async:true,
		data:data,
		success:function(data) {
			data = JSON.parse(data);
			layer.msg("审核提交成功!",{icon:1,time:2000},function(){
				getbaseList(1);
				ids = {};
            });
			layer.close(idx);
		},
		error:function(data){
			layer.msg("审核提交失败!",{icon:2,time:2000});
			layer.close(idx);
		}	
	});
	
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
			JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/delBatch', {
				chks : chks.toString()
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		});
	}
});

function check(id) {
	cleanForm();
	findBranchOffice();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='contractNumber']").val(JY.Object.notEmpty(l.contractNumber));
	$("#auForm input[name$='startTime']").val(JY.Object.notEmpty(l.startTime));
	$("#auForm input[name$='leaseTerm']").val(JY.Object.notEmpty(l.leaseTerm));
	$("#auForm input[name$='advanceCharge']").val(JY.Object.notEmpty(l.advanceCharge));
	$("#auForm input[name$='authorizingPerson']").val(JY.Object.notEmpty(l.authorizingPerson));
	$("#auForm input[name$='authorizingPhone']").val(JY.Object.notEmpty(l.authorizingPhone));
	$("#auForm input[name$='remark']").val(JY.Object.notEmpty(l.remark));

}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='projectName']").val('');
	$("#auForm input[name$='startTime']").val('');
	$("#auForm input[name$='leaseTerm']").val('');
	$("#auForm input[name$='advanceCharge']").val('');
	$("#auForm input[name$='authorizingPerson']").val('');
	$("#auForm input[name$='authorizingPhone']").val('');
	$("#auForm input[name$='remark']").val('');
	hideRole();
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(id,taskId,isSure,maModelId) {
	//alert(taskId);
	cleanForm();
	JY.Model.edit("auDiv", "指派客服代表", function() {
		var that = $(this);
		if (JY.Validate.form("auForm")) {
			var serviceId = $("#serviceId").val();
			JY.Ajax.doRequest(null, bonuspath+'/backstage/puttask/updatePutPerson', 
				{
				id:id,
				putPersonId:serviceId,
				taskId:taskId,
				isSure:isSure,
				maModelId:maModelId
				}, 
				function(data) {
			JY.Model.info(data.resMsg, function() {
				that.dialog("close");
				$("#serviceId").val('');
				search();
			});
			});
		}
	});
	
/*	
	JY.Model.edit("auDiv", "修改", function() {
		if (JY.Validate.form("auForm")) {
			var that = $(this);
			JY.Ajax.doRequest("auForm", bonuspath
					+ '/backstage/puttask/updatePutPerson',{id:id}, function(data) {
				that.dialog("close");
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		}
	});*/
}

function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function details(id){
	localStorage.setItem("taskId",id);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['协议明细','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1000px', '600px'],
	  content:bonuspath + '/backstage/agreementDetails/list'
	});

}

function findBranchOffice(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/company/getCompany',{},function(data){
		$("#branchOffice").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#branchOffice").append(html);
		}else{
			html+="<option ></option>";;
			$("#branchOffice").append(html);
		}
	});
}

function findUnit(companyId){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/company/getUnit',{companyId:companyId},function(data){
		$("#leaseCompany").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#leaseCompany").append(html);
		}else{
			html+="<option ></option>";;
			$("#leaseCompany").append(html);
		}
	});
	
}

function findProjectType(companyId){
	localStorage.setItem("companyId",companyId);
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/project/getProjectType',{},function(data){
		$("#projectType").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#projectType").append(html);
		}else{
			html+="<option ></option>";;
			$("#projectType").append(html);
		}
	});
	
}

function findProjectName(typeId){
	var companyId = localStorage.getItem("companyId");
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/project/getProjectName',{companyId : companyId,typeId:typeId},function(data){
		$("#projectName").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#projectName").append(html);
		}else{
			html+="<option ></option>";;
			$("#projectName").append(html);
		}
	});
	
}

//附件图片上传、查看
function updAgreeListPic(id){
	localStorage.setItem("updAgreeId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['附件图片上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/agreement/updAgreeListPic'
	});
}

function readAgreePic(id){
	localStorage.setItem("readAgreeId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['附件图片查看','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['800px', '430px'],
		content: bonuspath+'/backstage/agreement/readAgreePic'
	});
}


