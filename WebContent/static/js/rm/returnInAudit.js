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
	var keyWord = $("#keyword").val();
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/audit/findPutInAudit',
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
								html += "<tr ondblclick='selectData(this)'>";
								html += "<td style='vertical-align:middle;' class='center'><label>" +
											"<input type='checkbox' name='checkId' class='ids' id='inp' value="+l.taskId+">" +
													"<input type='hidden' name='nextId' id='nextId' value="+l.modelId+" >" +
															"<input type='hidden' name='nextnextId' id='nextnextId' value="+l.maNum+"></label></td>";
								html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.agreeCode) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unitName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.projectName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.modelName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maNum) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.customerSrep) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operator) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operationTime) + "</td>";
								if(l.isExamine==0 || l.isExamine=='0'){
									html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>未审核</span></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'>已审核</span></td>";
								}
								html += rowFunction(l.taskId,l.modelId,l.isExamine,l.maNum);
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

function rowFunction(taskId,modelId,isExamine,maNum) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(isExamine == 1 || isExamine == '1'){
	}else {
		h += "<a href='#' title='审核' onclick='check(&apos;" + taskId + "&apos;,&apos;" + modelId + "&apos;,&apos;" + maNum + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
//	h += "<a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(isExamine == 1 || isExamine == '1'){
		
	}else {
		h += "<a href='#' title='审核' onclick='check(&apos;" + taskId + "&apos;,&apos;" + modelId + "&apos;,&apos;" + maNum + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
//	h += "<li><a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}
function backShowChenkStatus(){
	$('input[type=checkbox]:checked').each(function(){
		var taskId = $(this).val();
		var modelId = $(this).next().val();
		var maNum = $(this).next().next().val();
		console.log("taskId=",taskId);
		console.log("modelId=",modelId);
		console.log("maNum=",maNum);
		var allId = taskId+","+modelId+","+maNum;
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
		url:bonuspath+'/backstage/audit/batchAuditExamine',
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
function check(taskId,modelId,maNum) {
	JY.Model.confirm("确认审核吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/audit/putInExamine', {
			taskId:taskId,
			modelId:modelId,
			maNum:maNum
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

