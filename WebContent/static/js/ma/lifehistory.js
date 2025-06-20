var deviceCode = localStorage.getItem("deviceCode");
var bonuspath = localStorage.getItem("bonuspath");
$(function() {
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
});

$('#export').on('click',function(e) {
	var proName = $("#proName").val();
	var optType=$("#optType").val();
	var unitName=$("#unitName").val();
	var name=$("#name").val();
	// 通知浏览器不要执行与事件关联的默认动作
	e.preventDefault();
	window.location.href=bonuspath+"/backstage/machine/exporthistory?proName="+proName+"&unitName="+unitName+"&name="+name+"&optType="+optType+"&deviceCode="+deviceCode;  
});

function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/machine/exporthistory');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}


function getbaseList(index) {
	var proName = $("#proName").val();
	var unitName = $("#unitName").val();
	var name = $("name").val();
	var optType = $("optType").val();
	var init = $(".pageNum").val();
	$(".pageNum").val(init);
	if (index == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/machine/machinehistory',{deviceCode:deviceCode},
		function(data) {
			$("#baseTable tbody").empty();
			var msg = data.resMsg;
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
					html += "<td style='vertical-align:middle;' class='center hidden-480'>"
							+ (i + leng + 1) + "</td>";
					if(l.type == '' || l.type == null){
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}else if(l.type == 2 || l.type =='2'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-default'>领料</span></td>";
					}else if(l.type == 3|| l.type =='3'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-primary'>机具入库</span></td>";
					}else if(l.type == 4 || l.type =='4'){
//						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick='toQRCode(&apos;"+ l.id +"&apos;);'>打印</a></td>";
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>机具退料</span></td>";
					}else if(l.type == 5 || l.type =='5'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>新购</span></td>";
					}else if(l.type == 6 || l.type =='6'){
					    html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>维修</span></td>";
					}else if(l.type == 7 || l.type =='6'){
					    html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>报废</span></td>";
					}else if(l.type == 8 || l.type =='8'){
					    html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>维修—检验</span></td>";
					}
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.proName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unitName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.name) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.outTime) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remarks) + "</td>";
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
