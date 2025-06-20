$(function() {
	parentId = localStorage.getItem("parentId");
	parentName = localStorage.getItem("parentName");
	getbaseList(1);
	$('#addBtn').on('click',function(e) {
		e.preventDefault();
		cleanForm();
		$("#pName").val(parentName);
		JY.Model.edit("auDiv", "新增", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath + '/backstage/machineType/insert', {parentId:parentId}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						getbaseList(1);
					});
				});
			}
		});
	});
});

var parentId;
var parentName;

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	$("#parentId").val(parentId);
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/machineType/findDetails',null,
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
					html += "<td class='center hidden-480'>" + (i + leng + 1) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.parentName) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.name) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.weight) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.unit) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.leasePrice) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.payPrice) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.buyPrice) + "</td>";
					html += "<td class='center'>" + JY.Object.notEmpty(l.keeper) + "</td>";
					html += rowFunction(l.id);
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
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='修改' onclick='check(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function check(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/findModel', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[id$='pName']").val(JY.Object.notEmpty(l.parentName));
	$("#auForm input[id$='name']").val(JY.Object.notEmpty(l.name));
	$("#auForm input[id$='weight']").val(JY.Object.notEmpty(l.weight));
	$("#auForm input[id$='unit']").val(JY.Object.notEmpty(l.unit));
	$("#auForm input[id$='leasePrice']").val(JY.Object.notEmpty(l.leasePrice));
	$("#auForm input[id$='payPrice']").val(JY.Object.notEmpty(l.payPrice));
	$("#auForm input[id$='buyPrice']").val(JY.Object.notEmpty(l.buyPrice));
	if(l.isTest == "1") {
		$('#isTest1').prop('checked',true);
	} 
	if(l.isTest == "0") {
		$('#isTest0').prop('checked',true);
	}
	$("#auForm input[id$='keeper']").val(JY.Object.notEmpty(l.keeper));
}

function cleanForm() {
	$("#auForm input[name$='id']").val("");
	$("#auForm input[id$='pName']").val("");
	$("#auForm input[id$='name']").val("");
	$("#auForm input[id$='weight']").val("");
	$("#auForm input[id$='unit']").val("");
	$("#auForm input[id$='leasePrice']").val("");
	$("#auForm input[id$='payPrice']").val("");
	$("#auForm input[id$='buyPrice']").val("");
	$('#isTest1').prop('checked',true);
	$("#auForm input[id$='keepper']").val("");
}

function edit(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/findModel', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath + '/backstage/machineType/updateModel', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						getbaseList(1);
					});
				});
			}
		});
	});
}
