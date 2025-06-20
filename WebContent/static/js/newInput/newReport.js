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
	$("#baseForm").attr("action",bonuspath +'/backstage/news/export');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();*/
	var url = bonuspath + '/backstage/news/export';
	let params = {
		"startTime":$("#startTime").val(),
		"endTime":$("#endTime").val(),
		"keyWord":$("#keyWord").val()
	}
	exportCommon(url, 'POST', params,`${params.startTime}-${params.endTime}新购入库报表`)
}

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest( "baseForm", bonuspath + '/backstage/news/findByPage', null,
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
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maTypeName) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModelName) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unit) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.num) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.actualPrice) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.manufactorName) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.purchaseTime) + "</td>";
						html += "</tr>";
					}
					$("#baseTable tbody").append(html);
					JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
				} else {
					html += "<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
					$("#baseTable tbody").append(html);
					$("#pageing ul").empty();// 清空分页
				}
				JY.Model.loadingClose();
			}
	);
}


