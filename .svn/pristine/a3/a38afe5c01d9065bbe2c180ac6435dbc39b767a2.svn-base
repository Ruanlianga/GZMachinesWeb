
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
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/out/findBackApproval',
					null,
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
								//大于2天不可操作退回
								if(l.dateNum=="1"){
									html += "<td style='vertical-align:middle;' class='center'></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><label>" +
									"<input type='checkbox' name='checkId' class='ids' id='inp' value="+l.taskId+">" +
									"<input type='hidden' name='nextId' id='nextId' value="+l.maModelId+" >" +
									"<input type='hidden' name='nextId' id='nextId' value="+l.outNum+" >" +
									"<input type='hidden' name='nextId' id='nextId' value="+l.storageNum+" >" +
									"<input type='hidden' name='nextId' id='nextId' value="+l.maType+" >" +
									"<input type='hidden' name='nextnextId' id='nextnextId' value="+l.maModel+"></label></td>";
								}
								
								
								html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.applyNumber) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operationTime) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leaseCompany) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.leaseProject) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maType) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModel) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.outNum) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.customerSrep) + "</td>";
								
								html += rowFunction(l.taskId,l.maModelId,l.isApproval,l.outNum,l.mTaskId,l.storageNum,l.dateNum,l.operationTime);
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='11' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}

function rowFunction(taskId,maModelId,isApproval,outNum,mTaskId,storageNum,dateNum,operationTime) {
	var h = "";
	if(dateNum=="1"){
		h += "<td style='vertical-align:middle;' class='center'>";
		h += "</td>";
		return h;
	}else{
		h += "<td style='vertical-align:middle;' class='center'>";
		h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
		h += "<a href='#' title='退回' onclick='backData(&apos;" + taskId + "&apos;,&apos;" + maModelId + "&apos;,&apos;" + outNum + "&apos;,&apos;" + mTaskId + "&apos;,&apos;" + storageNum + "&apos;,&apos;" + operationTime + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
		h += "</ul></div></div>";
		h += "</td>";
		return h;
	}
	
}

function backData(taskId,maModelId,outNum,mTaskId,storageNum,operationTime) {
	JY.Model.confirm("确认领料出库退回吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/out/backData', {
			taskId:taskId,
			maModelId:maModelId,
			mTaskId:mTaskId,
			outNum:outNum,
			storageNum:storageNum,
			operationTime:operationTime
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				getbaseList(1);
			});
		});
	});
}


function batchBack() {
	var idsStr = backShowChenkStatus();
	if(idsStr == "" ){
		layer.msg("请选择想要批准的信息!",{icon:7,time:2000});
		return;
	} else {
		JY.Model.confirm("确认领料出库退回吗？", function() {
		console.log("idsStr=", idsStr);
		var idx = layer.msg('退回申请提交中,请稍等...', {
			icon: 16
			, shade: 0.01
			, time: '-1'
		});
		var data = {
			ids: idsStr
		}

		$.ajax({
			type: 'POST',
			url: bonuspath + '/backstage/out/batchBackAuditApproval',
			async: true,
			data: data,
			success: function (data) {
				data = JSON.parse(data);
				layer.msg("退回提交成功!", {icon: 1, time: 2000}, function () {
					getbaseList(1);
					ids = {};
				});
				layer.close(idx);
			},
			error: function (data) {
				layer.msg("退回提交失败!", {icon: 2, time: 2000});
				layer.close(idx);
			}
		});
		});
	}
}


function backShowChenkStatus(){
	var ids = '';
	$('input[type=checkbox]:checked').each(function(){
		var taskId = $(this).val();
		var maModelId = $(this).next().val();
		var outNum = $(this).next().next().val();
		var storageNum = $(this).next().next().next().val();
		var maType = $(this).next().next().next().next().val();
		var maModel = $(this).next().next().next().next().next().val();
		console.log("taskId=",taskId);
		console.log("maModelId=",maModelId);
		console.log("outNum=",outNum);
		console.log("storageNum=",storageNum);
		
		var allId = taskId+","+maModelId+","+outNum;
		console.log("allId=",allId);
		ids += allId+"-"; 
		if(taskId === "on"){
			ids = "";
		}
		console.log("ids=",ids);
	})
	return ids;
}


