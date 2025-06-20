var agreementId = localStorage.getItem("agreementId");
var modelId = localStorage.getItem("modelId");

$(function() {
//	agreementInfo();

	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	

});



function getbaseList(init) {
	
	var keyWord = $("#keyWord").val();


	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/maLease/finduseCode',
					{
						agreementId: agreementId,
						modelId: modelId,
						keyWord: keyWord
					},
					function(data) {
		              //alert("data="+JSON.stringify(data));
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
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceModel) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
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



