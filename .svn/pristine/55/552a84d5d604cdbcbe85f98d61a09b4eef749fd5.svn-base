var ids = '';
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
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/rm/taskAcc/findByPage',null,function(data) {
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
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.returnMaterialTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unitName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.workName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.subcontractors) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.number) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.userName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.phone) + "</td>";
				var checker = l.checker;
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(checker) + "</td>";
				var wsh = l.wsh;
				
				if(	wsh != null){
					wsh = "未审核:" + wsh ;
					var shtg = l.shtg;
					shtg = "<br/>审核通过:" + shtg;
					var shbh = l.shbh;
					shbh = "<br/>审核驳回:" + shbh ;
					shInfo = wsh+shtg+shbh;
					
					var pzInfo = '';
					var wpz = l.wpz;
					wpz = "未批准:" + wpz + "";
					var pztg = l.pztg;
					pztg = "<br/>批准通过:" + pztg ;
					var pzbh = l.pzbh;
					pzbh = "<br/>批准驳回:" + pzbh;
					pzInfo = wpz+pztg+pzbh;
					
//					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(shInfo) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(pzInfo) + "</td>";
				}else{
//					html += "<td style='vertical-align:middle;' class='center'></td>";
					html += "<td style='vertical-align:middle;' class='center'></td>";
				}
				
		
			
				html += rowFunction(l.id,l.number,l.isFinish);
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

function rowFunction(id,number,isfinish) {
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(isfinish != 0){
	h+="<a href='#' title='查看' onclick='details(&apos;"+id+"&apos;,&apos;"+number+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	}
	h+="</div>";
	h+="<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(isfinish != 0){
	h+="<li><a href='#' title='查看' onclick='details(&apos;"+id+"&apos;,&apos;"+number+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	}
	h+="</ul></div></div>";		
	h+="</td>";
	return h;
}



function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
//	hideRole();
}

function details(taskId){
	var taskId = localStorage.setItem("taskId",taskId);
	layer.open({
		type: 2,
		title:['退料详情','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['1000px', '550px'],
		content: bonuspath+'/backstage/rm/taskAcc/taskAccDetails'
	});
}





