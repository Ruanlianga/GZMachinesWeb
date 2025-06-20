$(function() {
	getbaseList(1);
});

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/machineType/findWarnModel',null,
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
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.firstName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.secondName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.parentName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.name) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.nums) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.allNums) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.keeper) + "</td>";
//					html += rowFunction(l.id);
					html += "</tr>";
				}
				$("#baseTable tbody").append(html);
				JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
			} else {
				html += "<tr><td colspan='12' class='center'>没有相关数据</td></tr>";
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
	h += "<a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
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
	$("#auForm input[id$='sampingRatio']").val(JY.Object.notEmpty(l.sampingRatio));
	$("#auForm input[id$='weight']").val(JY.Object.notEmpty(l.weight));
	$("#auForm input[id$='unit']").val(JY.Object.notEmpty(l.unit));
	$("#auForm input[id$='ratedLoad']").val(JY.Object.notEmpty(l.ratedLoad));
	$("#auForm input[id$='testLoad']").val(JY.Object.notEmpty(l.testLoad));
	$("#auForm input[id$='holdingTime']").val(JY.Object.notEmpty(l.holdingTime));
	$("#auForm input[id$='leasePrice']").val(JY.Object.notEmpty(l.leasePrice));
	$("#auForm input[id$='payPrice']").val(JY.Object.notEmpty(l.payPrice));
	$("#auForm input[id$='buyPrice']").val(JY.Object.notEmpty(l.buyPrice));
	if(l.isTest == "1") {
		$('#isTest1').prop('checked',true);
	} 
	if(l.isTest == "0") {
		$('#isTest0').prop('checked',true);
	}
	if(l.isCount == "1") {
		$('#isCount1').prop('checked',true);
	} 
	if(l.isTest == "0") {
		$('#isCount0').prop('checked',true);
	}
	$("#auForm input[id$='keeper']").val(JY.Object.notEmpty(l.keeper));
	$("#auForm input[id$='warnNum']").val(JY.Object.notEmpty(l.warnNum));
}

function cleanForm() {
	$("#auForm input[name$='id']").val("");
	$("#auForm input[id$='pName']").val("");
	$("#auForm input[id$='name']").val("");
	$("#auForm input[id$='sampingRatio']").val("100");
	$("#auForm input[id$='weight']").val("");
	$("#auForm input[id$='unit']").val("");
	$("#auForm input[id$='ratedLoad']").val("");
	$("#auForm input[id$='testLoad']").val("");
	$("#auForm input[id$='holdingTime']").val("");
	$("#auForm input[id$='leasePrice']").val("");
	$("#auForm input[id$='payPrice']").val("");
	$("#auForm input[id$='buyPrice']").val("");
	$('#isTest1').prop('checked',true);
	$('#isCount0').prop('checked',true);
	$("#auForm input[id$='keepper']").val("");
	$("#auForm input[id$='warnNum']").val("");
}

function edit(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/findModel', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			var sampingRatio = $("#sampingRatio").val();
			if(sampingRatio >= 0 && sampingRatio <= 100){
				if (JY.Validate.form("auForm")) {
					var that = $(this);
					JY.Ajax.doRequest("auForm", bonuspath + '/backstage/machineType/updateModel', null, function(data) {
						that.dialog("close");
						JY.Model.info(data.resMsg, function() {
							getbaseList(1);
						});
					});
				}
			}else{
				JY.Model.info("请正确填写抽检比例！（0~100）");
			}
		});
	});
}

function del(id) {
	JY.Model.confirm("请联系相关人员", function(){
		JY.Model.confirm("确认删除吗？", function() {
			JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/delete', {
				id : id,
				level : 4
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					getbaseList(1);
				});
			});
		});
	});
}
