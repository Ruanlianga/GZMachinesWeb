var taskId = localStorage.getItem("taskId");
$(function() {
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
	getbaseList(1);
});



function getbaseList(init) {
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/rm/taskAcc/findByPageTwo',{taskId:taskId},function(data) {
		$("#baseTable tbody").empty();
		var obj = data.obj;
		var list = obj.list;
		var results = list.results;
		var permitBtn = obj.permitBtn;
		var pageNum = list.pageNum, 
			pageSize = list.pageSize, 
			totalRecord = list.totalRecord;
		var html = "";
		if (results != null && results.length > 0) {
			var leng = (pageNum - 1) * pageSize;
			for (var i = 0; i < results.length; i++) {
				var l = results[i];
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center hidden-480'>"+ (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceModel) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceUnit) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.returnNum) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.codeRemark) + "</td>";
				if(l.isExamine==0 || l.isExamine=='0'){
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>未审核</span></td>";
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'></span></td>";
				}else if(l.isExamine==1 || l.isExamine=='1'){
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'>已通过</span></td>";
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'></span></td>";
				}else{
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:red;'>未通过</span></td>";
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>"+ JY.Object.notEmpty(l.auditRemark) + "</span></td>";
				}
				if(l.isApproval==0 || l.isApproval=='0'){
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>未批准</span></td>";
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'></span></td>";
				}else if(l.isApproval==1 || l.isApproval=='1'){
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'>已通过</span></td>";
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:green;'></span></td>";
				}else{
					html += "<td style='vertical-align:middle;' class='center'><span style='clolr:red;'>未通过</span></td>";
					html += "<td style='vertical-align:middle;' class='center'><span style='color: red'>"+ JY.Object.notEmpty(l.approvalRemark) + "</span></td>";
				}
			
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




function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
//	hideRole();
}





