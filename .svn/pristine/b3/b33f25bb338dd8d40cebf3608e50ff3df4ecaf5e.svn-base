var typeParentId = "";
var typetId = ""; 
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

$('#export').on('click',function(e) {
	var keyWord = $("#keyWord").val();
	var typetId=$("#typetId").val();
	var typeParentId=$("#typeParentId").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	// 通知浏览器不要执行与事件关联的默认动作
	e.preventDefault();
	window.location.href=bonuspath+"/backstage/scrapExamine/export?typeParentId="+typeParentId+"&typetId="+typetId+"&keyWord="+keyWord+"&startTime="+startTime+"&endTime="+endTime;
});



function exportData(){
	/*$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/scrapExamine/export');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();*/
	var url = bonuspath +'/backstage/scrapExamine/export';
	let params = {
		"startTime":$("#startTime").val(),
		"endTime":$("#endTime").val(),
		"keyWord":$("#keyWord").val(),
		"typeParentId":$("#typeParentId").val(),
		"typetId":$("#typetId").val()
	}
	exportCommon(url, 'POST', params,`报废记录明细报表`)
}

$(function(){
	findMachineType();
})

function findMachineType(){
	$.ajax({
		type:"post",
		url:bonuspath + "/backstage/machine/findMachineType",
		data:{},
		dataType:"json",
		success: function(data){
			var obj = data.obj;
			var list = obj.list;
			var html = '<option value="">--请先选名称--</option>';
			if(list.length>0){
				for(var i = 0;i<list.length;i++){
					html += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
			}
			$("#typetId").append(html);
		},
		error: function(XMLHttpRequest,textStatus, errorThrown){
			alert("未连接到服务器，请检查网络！"+textStatus);
		}
	});
}


function findMachineTypeId(typeId){
	$("#typeParentId").empty();
	$.ajax({
		type:"post",
		url:bonuspath + "/backstage/machine/findMachineTypeId",
		data:{id:typeId},
		dataType:"json",
		success: function(data){
			var obj = data.obj;
			var list = obj.list;
			var html = '<option value="">--请先选名称--</option>';
			if(list.length>0){
				for(var i = 0;i<list.length;i++){
					html += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
			}
			$("#typeParentId").append(html);
		},
		error: function(XMLHttpRequest,textStatus, errorThrown){
			alert("未连接到服务器，请检查网络！"+textStatus);
		}
	});
}

function loadNameById(){
	var typeId = $("#typetId").val();
	findMachineTypeId(typeId);
}

function getbaseList(init) {
	var typetId = $("#typetId").val();
	var typeParentId = $("#typeParentId");
	var keyWord =  $("#keyWord");
	var startTime =$("#startTime");
	var endTime = $("#endTime");
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/scrapExamine/findByPage',
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
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.scrapCode) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.type) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machine) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineCode) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.scrapNum) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.creator) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.createTime) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.auditor) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.auditTime) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'><a onclick='uploadScrapFile(&apos;"+ l.id +"&apos;);'>下载</a></td>";
								
								
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

function uploadScrapFile(id) {
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/apply/findFileList', 
        data: {id:id},      
        dataType:"json",
        success:function(data){
        	var obj=data.obj;
        	if(obj.length>0){
        		var list=obj;
            	if(list.length>0){
            		var urls=[];
            		var names=[];
            		for(var i=0;i<list.length;i++){
            			urls.push(list[i].fileUrl);
            			names.push(list[i].fileName);
            		}
            		window.open(bonuspath +'/backstage/scrapAudit/exportZip?urls='+urls+"&fileNames="+names+"&bonusPath="+bonuspath);
        	}else{
        		layer.msg('尚未上传附件!');
        	}
        	
        	}else{
        		layer.msg('尚未上传附件!');
        	}
		}
	})
}	
	
	
	

	
	
	
	
	











