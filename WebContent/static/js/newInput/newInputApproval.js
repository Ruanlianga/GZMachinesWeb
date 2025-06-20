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
	var keyWord = $("#keyWord").val();
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/new/findIsApproval',
					{
						keyWord: keyWord
					},
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
								html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maType) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModel) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.checkNum) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.nums) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.checker) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.customerRep) + "</td>";
								if(l.isApproval==0 || l.isApproval=='0'){
									html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>未批准</span></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'>已批准</span></td>";
								}
								html += rowFunction(l.taskId,l.maModelId,l.isApproval,l.nums,l.id);
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

function rowFunction(taskId,maModelId,isApproval,nums,id) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if (isApproval == 1 || isApproval == '1') {
	}else{
		h += "<a href='#' title='批准' onclick='check(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;,&apos;" + nums + "&apos;,&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
//	h += "<a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if (isApproval == 1 || isApproval == '1') {
	}else{
		h += "<a href='#' title='批准' onclick='check(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;,&apos;" + nums + "&apos;,&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
//	h += "<li><a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
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

function check(taskId,maModelId,nums,id) {
	JY.Model.confirm("确认批准吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/new/isApproval', {
			taskId:taskId,
			maModelId:maModelId,
			nums:nums,
			id:id
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


