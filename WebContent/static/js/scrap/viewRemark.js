var id = localStorage.getItem("id");
var status = localStorage.getItem("status");
$(function () {
	findRemark(id);
});
	function  findRemark(id){
		$.ajax({
			type:"post",
			url:bonuspath + "/backstage/apply/findRemark",
			data:{
				id:id
			},
			dataType:"Json",
			success: function(data){
				var obj = data.obj;
				var remark =obj.auditRemark;
				$("#remark").val(remark);
				
			},
			error: function(XMLHttpRequest,textStatus, errorThrown){
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		});
	}
