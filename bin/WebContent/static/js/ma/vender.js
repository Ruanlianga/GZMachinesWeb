$(function() {
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
								+ '/backstage/vender/add', null, function(data) {
							that.dialog("close");
							JY.Model.info(data.resMsg, function() {
								search();
							});
						});
					}
				});
		});
});


function emptyRole() {
	$("#orgName").prop("value", "");
	$("#auForm input[name$='orgId']").prop("value", "0");
}

var preisShow = false;// 窗口是否显示
function showRole() {
	if (preisShow) {
		hideRole();
	} else {
		var obj = $("#orgName");
		var offpos = $("#orgName").position();
		$("#orgContent").css({
			left : offpos.left + "px",
			top : offpos.top + obj.heith + "px"
		}).slideDown("fast");
		preisShow = true;
	}
}
var hideRole = function() {
	$("#orgContent").fadeOut("fast");
	preisShow = false;
}
function clickRole(e, treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (check) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree"), nodes = zTree
				.getSelectedNodes(), v = "", n = "", o = "", p = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";// 获取name值
			n += nodes[i].id + ",";// 获取id值
			o += nodes[i].other + ",";// 获取自定义值
			var pathNodes = nodes[i].getPath();
			for (var y = 0; y < pathNodes.length; y++) {
				p += pathNodes[y].name + "/";// 获取path/name值
			}
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (n.length > 0)
			n = n.substring(0, n.length - 1);
		if (o.length > 0)
			o = o.substring(0, o.length - 1);
		if (p.length > 0)
			p = p.substring(0, p.length - 1);
		$("#orgName").val(p);
		$("#auForm input[name$='orgId']").prop("value", n);
		hideRole();
	}
}

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax
			.doRequest(
					"baseForm",
					bonuspath + '/backstage/vender/findByPage',
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
								html += "<td class='center'><label> <input type='checkbox' name='ids' value='"
										+ l.id
										+ "' class='ace' /> <span class='lbl'></span></label></td>";
								html += "<td class='center hidden-480'>"
										+ (i + leng + 1) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.name) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.address) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.companyMan) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.mainPerson) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.phone) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.scopeBusiness) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.notes) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.picUrl) + "</td>";
								html += rowFunction(l.id);
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

function rowFunction(id) {
	var h = "";
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;"
			+ id
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
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
	h += "<li><a href='#' title='查看' onclick='check(&apos;"
			+ id
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
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

// 批量删除
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
			JY.Ajax.doRequest(null, bonuspath + '/backstage/vender/delBatch', {
				chks : chks.toString()
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		});
	}
});

function check(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/vender/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
	$("#auForm input[name$='address']").val(JY.Object.notEmpty(l.address));
	$("#auForm input[name$='companyMan']").val(JY.Object.notEmpty(l.companyMan));
	$("#auForm input[name$='mainPerson']").val(JY.Object.notEmpty(l.mainPerson));
	$("#auForm input[name$='phone']").val(JY.Object.notEmpty(l.phone));
	$("#auForm input[name$='scopeBusiness']").val(JY.Object.notEmpty(l.scopeBusiness));
	$("#auForm input[name$='notes']").val(JY.Object.notEmpty(l.notes));
	$("#auForm input[name$='picUrl']").val(JY.Object.notEmpty(l.picUrl));

}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='name']").val('');
	$("#auForm input[name$='address']").val('');
	$("#auForm input[name$='companyMan']").val('');
	$("#auForm input[name$='mainPerson']").val('');
	$("#auForm input[name$='phone']").val('');
	$("#auForm input[name$='scopeBusiness']").val('');
	$("#auForm input[name$='notes']").val('');
	$("#auForm input[name$='picUrl']").val('');
	hideRole();
}

function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/vender/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/vender/update', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
}


function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/vender/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

