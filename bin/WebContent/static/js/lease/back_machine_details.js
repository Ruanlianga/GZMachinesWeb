$(function() {
	getbaseList(1);
	$("#backMachines").click(function(){
		if(backStatus != "5" || backStatus != 5){
			JY.Model.info("已退料成功！");
		}else{
			JY.Model.confirm("确认退料吗？", function() {
				JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/back/update',{outNum:backOutNum},function(data) {
					JY.Model.info(data.resMsg, function() {
						getbaseList(1);
					});
				});
			});
		}
	});
});
var backOutNum = "";
var backStatus = "";
function getbaseList(init) {
	backOutNum = localStorage.getItem("backOutNum");
	backStatus = localStorage.getItem("backStatus");
	if (init == 1)
	$("#baseForms.pageNum").val(1);
	JY.Model.loading();
	$(".backOutNum").val(backOutNum);
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/lease/back/findDetails',null,function(data) {
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
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.outNum) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machinesName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.model) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.pickNum) + "</td>";
				if(l.batchStatus == 5 || l.batchStatus =='5'){
					html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>未退料</span></td>";
				}else{
					html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已退料</span></td>";
				}
				html += rowFunction(l.id);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='5' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}

function rowFunction(id) {
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='退料' onclick='edit(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-trash color-blue bigger-140'></i></a>";
	h+="</div>";
	h+="<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h+="<li><a href='#' title='退料' onclick='edit(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-trash color-blue bigger-140'></i></a></li>";
	h+="</ul></div></div>";		
	h+="</td>";
	return h;
}

function edit(id){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/lease/back/trashMachines', {
		id : id
	}, function(data) {
		window.location.href="";
	});
}
