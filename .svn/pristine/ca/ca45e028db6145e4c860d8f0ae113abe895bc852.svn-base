var id;
$(function() {
	id = localStorage.getItem("id");
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});
	$('#addBtn').on('click',
			function(e) {
				// 通知浏览器不要执行与事件关联的默认动作
				e.preventDefault();
				cleanForm();
				JY.Model.edit("auDiv", "新增", function() {
					if (JY.Validate.form("auForm")) {
						var that = $(this);
						JY.Ajax.doRequest("auForm", bonuspath
								+ '/backstage/checkQrCode/add', null, function(data) {
							that.dialog("close");
							JY.Model.info(data.resMsg, function() {
								search();
							});
						});
					}
				});
		});
});

function search() {
	$("#searchBtn").trigger("click");
}

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest(
					"baseForm",
					bonuspath + '/backstage/checkQrCode/findByPage',
					null,
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
								html += "<td style='vertical-align:middle;' class='center'><label> <input type='checkbox' name='ids' value='"
										+ l.id
										+ "' class='ace' /> <span class='lbl'></span></label></td>";
								html += "<td style='vertical-align:middle;' class='center hidden-480'>"
										+ (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.reportCode) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unitName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maType) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModel) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.outCode) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.vender) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.checkTime) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.checkUnit) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'><a onclick='checkShowQrCode(&apos;"+l.reportCode+"&apos;,&apos;"+l.id+"&apos;)'>二维码查看</a></td>";
								html += rowFunction(l.id);
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='11' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}

function rowFunction(id) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='修改' onclick='edit(&apos;"
			+ id
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;"
			+ id
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='修改' onclick='edit(&apos;"
			+ id
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='删除' onclick='del(&apos;"
			+ id
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";

	return h;
}

function edit(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/checkQrCode/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/checkQrCode/update', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='reportCode']").val('');
	$("#auForm input[name$='unitName']").val('');
	$("#auForm input[name$='maType']").val('');
	$("#auForm input[name$='maModel']").val('');
	$("#auForm input[name$='outCode']").val('');
	$("#auForm input[name$='vender']").val('');
	$("#auForm input[name$='checkTime']").val('');
	$("#auForm input[name$='checkUnit']").val('');

}
function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='reportCode']").val(JY.Object.notEmpty(l.reportCode));
	$("#auForm input[name$='unitName']").val(JY.Object.notEmpty(l.unitName));
	$("#auForm input[name$='maType']").val(JY.Object.notEmpty(l.maType));
	$("#auForm input[name$='maModel']").val(JY.Object.notEmpty(l.maModel));
	$("#auForm input[name$='outCode']").val(JY.Object.notEmpty(l.outCode));
	$("#auForm input[name$='vender']").val(JY.Object.notEmpty(l.vender));
	$("#auForm input[name$='checkTime']").val(JY.Object.notEmpty(l.checkTime));
	$("#auForm input[name$='checkUnit']").val(JY.Object.notEmpty(l.checkUnit));
}


function del(id){
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/checkQrCode/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

//批量删除
$('#delBatchBtn').on('click', function(e) {
	
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		var chks = [];
		$('#baseTable input[name="ids"]:checked').each(function() {
			chks.push($(this).val());
		});
		if (chks.length == 0) {
			JY.Model.info("您没有选择任何内容!");
		} else {
			JY.Model.confirm("确认要删除选中的数据吗?", function() {
				JY.Ajax.doRequest(null, bonuspath + '/backstage/checkQrCode/delBatch', {
					chks : chks.toString()
				}, function(data) {
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			});
		}
	
});



function checkShowQrCode(reportCode,id){
	localStorage.setItem("id",id);
	localStorage.setItem("reportCode",reportCode);
	layer.open({
		type: 2,
		title:['二维码查看','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['800px', '430px'],
		content: bonuspath+'/backstage/checkQrCode/checkShowQrCode'
	});
}
