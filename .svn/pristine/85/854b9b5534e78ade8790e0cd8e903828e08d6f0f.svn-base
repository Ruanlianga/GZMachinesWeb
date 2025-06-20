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
		laydate.render({
			elem: '#buyTime'
		});
		laydate.render({
			elem: '#acceptTime'	
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

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest( "baseForm", bonuspath + '/backstage/inputQrcode/findByPage', null,
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
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.launchName) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.launchTime) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.finishTime) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maType) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.receiveName) + "</td>";
						if(l.checkStatus == 4 || l.checkStatus == '4'){
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>待生成二维码</span></td>";
						}else if(l.checkStatus == 5 || l.checkStatus =='5') {
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待下载二维码</span></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>已完成</span></td>";
						}
						html += rowFunction(l.taskId);
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

function rowFunction(id) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='打印机具列表' onclick='details(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='打印机具列表' onclick='details(&apos;" + id +"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function details(id){
	localStorage.setItem("taskId",id);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['打印机具类型列表','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['850px', '500px'],
	  content:bonuspath + '/backstage/inputQrcode/details'
	});
}

