$(function() {
	jeDate({
		dateCell : "#outTime",// isinitVal:true,
		format : "YYYY-MM-DD",
		isTime : false
	});
	getbaseList(1);
});

function getbaseList(init) {
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/lease/back/findByPage',null,function(data) {
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
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.pickTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.pickCompanyName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.backTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.backCompanyName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.backStatus) + "</td>";
				html += rowFunction(l.taskId,l.backStatus);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='8' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}

function rowFunction(taskId,backStatus) {
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='查看' onclick='check(&apos;"+taskId+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="<a href='#' title='退料' onclick='edit(&apos;"+taskId+"&apos;,&apos;"+backStatus+"&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h+="</div>";
	h+="<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h+="<li><a href='#' title='查看' onclick='check(&apos;"+taskId+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h+="<li><a href='#' title='退料' onclick='edit(&apos;"+taskId+"&apos;,&apos;"+backStatus+"&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h+="</ul></div></div>";		
	h+="</td>";
	return h;
}

function check(taskId){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/back/find', {
		taskId : taskId
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function setForm(data) {
	var list = data.obj.list;
	var results = list.results;
	var html = "";
	var date = $("#outTime").val()+"";
	var d = new Array();
	d = date.split("-");
	$(".year").html(d[0]);
	$(".month").html(d[1]);
	$(".day").html(d[2]);
	$("#pickProject").html(results[0].pickProject);
	$("#pickCompany").html(results[0].pickCompany);
	$("#outNum").html(results[0].outNum);
	for(var i = 0;i < results.length;i++){
		if(i < 5){
			$("#id"+i).html(i+1);
			$("#machinesName"+i).html(results[i].machinesName);
			$("#model"+i).html(results[i].model);
			$("#unit"+i).html("");
			$("#pickNum"+i).html(results[i].pickNum);
			$("#weight"+i).html("");
			$("#remark"+i).html("编号详见明细");
		}else{
			html += '<tr>';
			html += '<td style="height:30px;text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">'+(i++)+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">'+results[i].machinesName+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">'+results[i].model+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;">'+results[i].pickNum+'</td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;font-weight: bold;"></td>';
			html += '<td style="text-align:center;border-top: 1px solid #000000;border-left: 1px solid #000000;border-right:1px solid #000000;font-weight: bold;">编号详见明细</td>';
			html += '</tr>';
		}
	}
	$("#five").after(html);
}

function edit(outNum,batchStatus) {
	localStorage.setItem("backOutNum",outNum);
	localStorage.setItem("backStatus",batchStatus);
	layer.open({
	  type: 2,
	  title:['机具退料','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['900px', '450px'],
	  content:bonuspath + '/backstage/lease/back/details'
	});
}
