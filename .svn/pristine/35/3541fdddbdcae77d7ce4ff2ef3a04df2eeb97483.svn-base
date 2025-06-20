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
								+ '/backstage/machine/add', null, function(data) {
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
					bonuspath + '/backstage/machine/findByPage',
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
								html += "<td class='center'>"+ JY.Object.notEmpty(l.type0) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.type) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.weight) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.unit) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.leasePrice) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.isFixedAssets) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.houseName) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.position) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.buyPrice) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.isTrack) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.payPrice) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.picUrl) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.isCheck) + "</td>";
								if(l.deviceNum.length>1){
									html += "<td class='center'>"+ JY.Object.notEmpty(l.deviceNum) + "</td>";
								}else{
									html += "<td class='center'>暂无</td>";
								}
								if(l.isFixedAssets == 0 || l.isFixedAssets == '0' || l.isFixedAssets == ''){
									html += "<td class='center'><a onclick='toFixedAssets(&apos;"+ l.id +"&apos;);'>固定资产</a></td>";
								}else{
									html += "<td class='center'>是</td>";
								}
								html += "<td class='center'>"+ JY.Object.notEmpty(l.venderName) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.outFactortNum) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.buyTime) + "</td>";
								if(l.qrcodeUrl == '' || l.qrcodeUrl == null){
									html += "<td class='center'><a onclick='toQRCode(&apos;"+ l.id +"&apos;);'>打印</a></td>";
								}else{
									html += "<td class='center'><a onclick='readQRCode(&apos;"+ l.id +"&apos;);'>查看</a></td>";
								}
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
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	/*x`*/
	h += "</div>";
	h += "</td>";
	return h;
}

function toQRCode(id){
	$.ajax({
		type: "post",
		url: bonuspath + "/backstage/machine/ToQRCode",
		data: {
			id : id
		},
		success: function(data) {
			search();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！"+textStatus);
		}
	});
}

function readQRCode(id){
	localStorage.setItem("QRCodeId",id);
	localStorage.setItem("path",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['二维码查看','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machine/readQRCode'
	});
//	findBatchStatus(id);
}

//转固定资产
function toFixedAssets(id){
	localStorage.setItem("fixedId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['资料填写','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['400px', '260px'],
	  content: bonuspath+ '/backstage/machine/toFixedAssets'
	});
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
			JY.Ajax.doRequest(null, bonuspath + '/backstage/machine/delBatch', {
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
		localStorage.setItem("machinesId",id);
		localStorage.setItem("bonuspath",bonuspath);
		//iframe层-父子操作
		layer.open({
		  type: 2,
		  title:['信息查看','background-color: #27A3D9;color:#fff'],
		  shadeClose:true,
		  shade:false,
		  maxmin: true,
		  area: ['1000px', '550px'],
		  content: bonuspath+'/backstage/machine/openFrom'
		});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));

}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='name']").val('');
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
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machine/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/machine/update', null, function(data) {
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
		JY.Ajax.doRequest(null, bonuspath + '/backstage/machine/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

