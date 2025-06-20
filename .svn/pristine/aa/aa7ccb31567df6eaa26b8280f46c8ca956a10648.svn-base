var batchId;
$(function() {
	getbaseList();
	batchId = localStorage.getItem("batchId");
});

function getbaseList() {
	JY.Model.loading();
	JY.Ajax.doRequest(null,bonuspath + '/backstage/user/findAllUser',null,function(data) {
		$("#personTable tbody").empty();
		var obj = data.obj;
		var list = obj.list;
		var html = "";
		if (list != null && list.length > 0) {
			for (var i = 0; i < list.length; i++) {
				var l = list[i];
				html += "<tr>";
				html += "<td class='center'>" +
						"	<label>" +
						"		<input type='checkbox' name='telphone' value='"+ l.telphone + "' class='ace' /> " +
						"		<span class='lbl'></span>" +
						"	</label>" +
						"</td>";
				html += "<td class='center hidden-480'>" + (i + 1) + "</td>";
				html += "<td class='center'>"+ JY.Object.notEmpty(l.orgName) + "</td>";
				html += "<td class='center'>"+ JY.Object.notEmpty(l.name) + "</td>";
				html += "<td class='center'>"+ JY.Object.notEmpty(l.postName) + "</td>";
				html += "<td class='center'>"+ JY.Object.notEmpty(l.telphone) + "</td>";
				html += "</tr>";
			}
			$("#personTable tbody").append(html);
		} else {
			html += "<tr><td colspan='6' class='center'>没有相关数据</td></tr>";
			$("#personTable tbody").append(html);
		}
		JY.Model.loadingClose();
	});
}

function notice(){
	var chks = [];
	var noticeContent = $("#noticeContent").val();
	$('#personTable input[name="telphone"]:checked').each(function() {
		chks.push($(this).val());
	});
	if (chks.length == 0) {
		JY.Model.info("您没有选择通知人员！");
	}else if(noticeContent == ""){
		JY.Model.info("您还没有填写通知内容！");
	}else {
		JY.Model.confirm("您确定要通知选择的人员吗？", function() {
			JY.Ajax.doRequest(null, bonuspath + '/backstage/new/sendNotice', {
				batchId : batchId,
				chks : chks.toString(),
				noticeContent : noticeContent
			}, function(data) {
				JY.Model.info(data.resMsg,function() {
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index); //疯狂模式，关闭所有层
					window.parent.location.reload(); //刷新父页面
				},function(){
					var index=parent.layer.getFrameIndex(window.name);
	                parent.layer.close(index);
	                window.parent.location.reload(); //刷新父页面
				});
			});
		});
	}
}

