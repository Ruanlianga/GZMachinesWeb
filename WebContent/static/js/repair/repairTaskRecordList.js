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
	$("#baseForm").attr("action",bonuspath +'/backstage/repairRecord/export');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();*/
	let params = {
		"startTime":$("#startTime").val(),
		"endTime":$("#endTime").val(),
		"keyWord":$("#keyWord").val()
	}
	exportCommon(bonuspath + '/backstage/repairRecord/export', 'POST', params,`${params.startTime}-${params.endTime}维修记录报表`);
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
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/repairRecord/findByPage',
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
								html += "<tr ondblclick='selectData(this)'>";
								html += "<td style='vertical-align:middle;' class='center'><label>" +
								"<input type='checkbox' name='checkId' class='ids' id='inp' value="+l.id+"></td>";
								html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.modelName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.alRepairNum) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.codeRemark) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.repairPerson) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operationTime) + "</td>";
								
								if(l.repairUrl == null || l.repairUrl == ''||l.repairUrl == 'null'){ 
									html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadImg("+l.id+")'>上传</a></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadImg("+l.id+")'>上传</a><a href='javascript:void(0)' onclick = 'queryImg(\""+l.repairUrl+"\")'>|查看</a></td>";
								}
								
								if(l.fileUrl == null || l.fileUrl == ''||l.fileUrl == 'null'){ 
									html += "<td style='vertical-align:middle;' class='center'>暂无</td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'><a  <a href='javascript:void(0)' onclick = 'openImg(\""+l.fileUrl+"\") '>|查看</a></td>";
								}
								
								var rmStatus = l.rmStatus;
								if(rmStatus == "5"||rmStatus == "8"){
									rmStatus = "维修合格";
								}else{
									rmStatus = "维修申请报废";
								}
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(rmStatus) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
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



function print(){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var keyWord = $("#keyWord").val();
	localStorage.setItem("startTime",startTime);
	localStorage.setItem("endTime",endTime);
	localStorage.setItem("keyWord",keyWord);
	layer.open({
	  type: 2,
	  title:['维修单打印','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['80%', '80%'],
	  content:bonuspath + '/backstage/repairRecord/print'
	});

}

function uploadImg(id){ 
	localStorage.setItem("id",id);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['维修附件上传','background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/repairRecord/fileUpload'
	});
}

function queryImg(repairUrl){
	window.open(bonuspath +"/filePath" +repairUrl);
}

function openImg(repairUrl){
	window.open(bonuspath +repairUrl);
}



function upload(){
	if($(".ids:checkbox:checked").size() == 0){
		var indexMsg = layer.confirm("<h4 style='color:red;'>请选择要操作的数据!</h4>", {btn: ['关闭']},function(){
			layer.close(indexMsg);
		});
		return false;
	}
	var vals = checkValues();
	localStorage.setItem("id",vals);
	layer.open({
	    type: 2,
	    title:['批量上传','background-color: #27A3D9;color:#fff'],
	    shadeClose:true,
	    shade:false,
	    maxmin: true,
	    area: ['610px', '400px'],
	    content:bonuspath + '/backstage/repairRecord/upload'
	});
	
	
}

function checkValues(){
	var vals = '';
	$('input[type=checkbox]:checked').each(function(){
		var val = $(this).val();	
		console.log("val=",val);
		vals += val+","; 
		console.log("vals=",vals);	
	})
	return vals;
}