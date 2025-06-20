var startTime = localStorage.getItem("startTime");
var endTime = localStorage.getItem("endTime");
var keyWord = localStorage.getItem("keyWord");
$(function() {
	layui.use('laydate', function(){
	});
	getPrintDetails();
});

function getPrintDetails(){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/repairRecord/getPrintDetails', {
		startTime:startTime,endTime:endTime,keyWord:keyWord
	}, function(data) {
		console.log(data);
		if(data.obj.list.length != 0){
			setForm(data);
		}else {
			var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
			    layer.close(indexMsg);
			});
		}
	});
}

function setForm(data) {
	var results = data.obj.list;
	var html = "";
	if(results.length != 0){
		for(var i = 0;i < results.length;i++){
				if(results[i].remark != null && results[i].remark != ""){
					remark = results[i].remark;
				}else{
					remark = "";
				}
				var taskId;
				if(results[i].id != null && results[i].id != ""){
					taskId = results[i].id;
				}else{
					taskId =  "-1";
				}
				html += '<tr>';
				html += '<input type="hidden" class="taskId" value="'+taskId+'">';
				html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;">'+(i+1)+'</td>';
				html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+JY.Object.notEmpty(results[i].typeName)+'</td>';
				html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+JY.Object.notEmpty(results[i].modelName)+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+JY.Object.notEmpty(results[i].alRepairNum)+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+JY.Object.notEmpty(results[i].deviceCode)+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+JY.Object.notEmpty(results[i].operationTime)+'</td>';
				var rmStatus = results[i].rmStatus;
				if(rmStatus == "5"||rmStatus == "8"){
					rmStatus = "维修合格";
				}else{
					rmStatus = "维修申请报废";
				}
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-family:SimSun">'+rmStatus+'</td>';
				html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;border-bottom:1px solid #000000;font-family:SimSun"><input type="text" class="remark" name="remark" value="'+remark+'" style="border: 0;width:50px;"></td>';
				html += '</tr>';
			}
	}
	$("#header").after(html);
}

function save1() {
	var remark = $(".remark");
	var taskId = $(".taskId");
	var remarks = new Array();
	var remarkString = "";
	var taskIds = new Array();
	var taskIdString = "";
	for(i = 0; i < remark.length; i++){
		remarks[i] = $(remark[i]).val();
		remarkString += remarks[i] + ",";
		taskIds[i] = $(taskId[i]).val();
		taskIdString += taskIds[i] + ",";
	}
	
	$.ajax({
		type: "POST",
		url: bonuspath + '/backstage/repairRecord/saveRemark',
		data: {
			remarkString : remarkString,
			taskIdString : taskIdString,
		},
		dataType: 'json',
		success: function(data) {
			layer.alert(data.res, function () {
                var index = parent.layer.getFrameIndex(window.name); //先得到当前 iframe层的索引
                parent.layer.close(index); //再执行关闭
                parent.example.ajax.reload();
            });
		},
		error: function(msg) {

		}
	});
	
}


function jqprinting1() {
	$("#dd").jqprint({ operaSupport: false });
}