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
	
});
function exportData(){
	/*$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/scrapRecord/export');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();*/
	let params = {
		"startTime":$("#startTime").val(),
		"endTime":$("#endTime").val(),
		"keyWord":$("#keyWord").val()
	}
	exportCommon(bonuspath + '/backstage/scrapRecord/export', 'POST', params,`${params.startTime}-${params.endTime}报废记录报表`);
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

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/scrapRecord/findByPage',
					null,
					function(data) {
		             //   alert("data="+JSON.stringify(data));
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
								html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.modelName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.number) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.scrapTime) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.alScrapNum) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.scrapReson) + "</td>";
								if(l.picUrl == null || l.picUrl == '' || l.picUrl == '无'){
									html += "<td style='vertical-align:middle;' class='center'><a onclick='uploadImg(&apos;"+ l.modelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>上传</a></td>";
								} else {
									html += "<td style='vertical-align:middle;' class='center'><a onclick='readImg(&apos;"+ l.modelId +"&apos;,&apos;"+ l.taskId +"&apos;);'>查看</a></td>";
								} 
								//html += rowFunction(l.scrapPerson);
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

function rowFunction(isApproval) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if (isApproval == "" || isApproval == null) {
	}else{
		h += "<a href='#' title='通知' onclick='check(&apos;" + isApproval + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if (isApproval == 1 || isApproval == '1') {
	}else{
		h += "<a href='#' title='通知' onclick='check(&apos;" + isApproval + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function user(id) {
	cleanForm();
	JY.Model.edit("userDiv", "指派检验员代表", function() {
		var that = $(this);
		if (JY.Validate.form("userForm")) {
			var userId = $("#userId").val();
			JY.Ajax.doRequest(null, bonuspath+'/backstage/scrap/update', 
				{
				id:id,
				scrapPerson:userId
				}, 
				function(data) {
			JY.Model.info(data.resMsg, function() {
				that.dialog("close");
				$("#userId").val('');
				search();
			});
			});
		}
	});
}
function userTree(){
	localStorage.setItem("userId","");
	localStorage.setItem("userName","");
	localStorage.setItem("userTreeName",$("#userName").val());
	layer.open({
		type: 2,
		title:['检验人员','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/user/userTree'
	});
}

function setUserForm(){
	var userId = localStorage.getItem("userIds");
	var userName = localStorage.getItem("userName");
	$("#userId").val(userId);
	$("#userName").val(userName);
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

function check(isApproval) {
	JY.Model.confirm("确认通知吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/scrap/isApproval', {
			scrapPerson:isApproval
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
				getbaseList(1);
			});
		});
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='typeName']").val(JY.Object.notEmpty(l.typeName));
	$("#auForm input[name$='modelName']").val(JY.Object.notEmpty(l.modelName));
	$("#auForm input[name$='scrapNum']").val(JY.Object.notEmpty(l.scrapNum));
	$("#auForm input[name$='scrapPerson']").val(JY.Object.notEmpty(l.scrapPerson));
	$("#auForm input[name$='scrapChecker']").val(JY.Object.notEmpty(l.scrapChecker));
	$("#auForm input[name$='operationTime']").val(JY.Object.notEmpty(l.operationTime));
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='typeName']").val('');
	$("#auForm input[name$='modelName']").val('');
	$("#auForm input[name$='scrapNum']").val('');
	$("#auForm input[name$='scrapPerson']").val('');
	$("#auForm input[name$='scrapChecker']").val('');
	$("#auForm input[name$='operationTime']").val('');
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(id) {
	console.info("id="+id);
	cleanForm();
	console.info("id="+id);
	JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/agreement/update', null, function(data) {
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



//报废资料上传
function uploadImg(maModelId,taskId){  
	
	localStorage.setItem("maModelId",maModelId);
	localStorage.setItem("taskId",taskId);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['报废资料上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '400px'],
	  content: bonuspath+'/backstage/scrapRecord/maImg'
	});
}
//报废资料查看
function readImg(modelId,taskId){
	localStorage.setItem("modelId",modelId);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['报废资料查看','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/scrapRecord/queryMaImg?modelId='+modelId +'&taskId='+taskId
	});
}



/*
//报废资料上传
function updMachinesPic(id,taskId){
	localStorage.setItem("updPicId",id);
	localStorage.setItem("taskId",taskId);
	localStorage.setItem("bonuspath",bonuspath);
	layer.open({
	  type: 2,
	  title:['报废资料上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '430px'],
	  content: bonuspath+'/backstage/scrapRecord/machinesPic',
	  end: function(){
		 location.reload(); 
	  }
	});
}

//报废资料查看
function readMachinesPic(picUrl) {
	localStorage.setItem("bonuspath", bonuspath);
	localStorage.setItem("picUrl", picUrl);
	var machinesUrl = picUrl;
	console.log("picUrl=", picUrl);
	if(machinesUrl.indexOf("pdf") != -1) { //如果是pdf文件
		var path = bonuspath+'/scrapImg/'+ machinesUrl;
		window.open(path);
	} else { //是图片
			layer.open({
			type: 2,
			title:['报废图片查看','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['800px', '430px'],
			content: bonuspath+'/backstage/scrapRecord/readMachinesPic'
			});
	}
}
*/





