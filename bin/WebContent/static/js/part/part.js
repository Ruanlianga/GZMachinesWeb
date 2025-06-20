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
				find();
				JY.Model.edit("auDiv", "新增", function() {
					if (JY.Validate.form("auForm")) {
						var that = $(this);
						JY.Ajax.doRequest("auForm", bonuspath
								+ '/backstage/partone/add', null, function(data){
						     	that.dialog("close");
							JY.Model.info(data.resMsg, function(){
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
					bonuspath + '/backstage/partone/findByPage',
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
							//	alert(JSON.stringify(l));
								html += "<tr>";
							/*	html += "<td class='center'><label> <input type='checkbox' name='ids' value='"
										+ l.partsTypeId
										+ "' class='ace' /> <span class='lbl'></span></label></td>";*/
								function consumable(){
									if(l.isConsumables!=null&& l.isConsumables!=''){
										if(l.isConsumables=='1'){
											return "是";
										}else{
										return "否";
										}
									}else{
										return " ";
									}
									
								}
								//l.isConsumables
							//	alert(l.isConsumables);
								html += "<td class='center hidden-480'>"
										+ (i + leng + 1) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.partNum) + "</td>";
							//	html += "<td class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.factory) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.model) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.price) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.outTime) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.remarks) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.num) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(consumable(l.isConsumables)) + "</td>";
								html += rowFunction(l);
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
					});
  }


function rowFunction(l) {
	
var 	id=l.id;
	var h = "";
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;"
			+ JSON.stringify(l)
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='修改' onclick='edit(&apos;"
			+JSON.stringify(l)
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;"
			+ id
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='查看' onclick='check(&apos;"
			+ JSON.stringify(l)
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h += "<li><a href='#' title='修改' onclick='edit(&apos;"
			+JSON.stringify(l)
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
			JY.Ajax.doRequest(null, bonuspath + '/backstage/partsType/delBatch', {
				chks : chks.toString()
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		});
	}
});

function check(l) {
  var l=JSON.parse(l);
  find(l);
  JY.Model.check("auDiv");
}

function setForm(l) {
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='partNum']").val(l.partNum);
	$("#auForm input[name$='remarks']").val(JY.Object.notEmpty(l.remarks));
	$("#auForm input[name$='isConsumables']").val(l.isConsumables);
	$("#auForm input[name$='price']").val(l.price);
	$("#typeId option[value='"+l.typeId+"']").attr("selected","selected");  
	$("#factory option[value='"+l.venderId+"']").attr("selected","selected"); 
	$("#auForm input[name$='outTime']").val(l.outTime);
	$("#auForm input[name$='num']").val(l.num);
	$("#auForm input[name$='model']").val(l.model);
}

function cleanForm() {
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='typeNum']").val('');// 上级资源
	$("#auForm input[name$='remarks']").val('');
	$("#auForm input[name$='remarks']").val('');
	$("#auForm input[name$='isConsumables']").val('');
	$("#auForm option[name$='factory']").empty();
	$("#auForm input[name$='outTime']").val('');
	$("#auForm input[name$='num']").val('');
	$("#auForm input[name$='model']").val('');
	$('#typeId option[value="-10"]').attr("selected","selected"); 
	$('#factory option[value="-10"]').attr("selected","selected"); 
}

function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(l) {
	var l=JSON.parse(l);
	find(l);
	
	JY.Model.edit("auDiv", "修改", function() {
		if (JY.Validate.form("auForm")) { 
			var that = $(this);
			JY.Ajax.doRequest("auForm", bonuspath
					+ '/backstage/partone/update', null, function(data) {
				that.dialog("close");
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		}
	});
}

function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/partone/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}
//公司名称
function find(l) {
	var  html;
	$("#factory").empty();
	html+='<option value="-10">请选择厂家</option>';	  
	JY.Ajax.doRequest(null, bonuspath + '/backstage/partone/find', {}, function(data) {
		var  obj=  data.obj;
		var list= obj.length;
		for(var i=0; i<list;i++ ){
    	   html+="<option value='"+obj[i].venderId+"'>" + obj[i].factory+"</option>";	  
		}
		$("#factory").append(html);
		cleanForm();
		findone(l);
	});
}
//获得配件类型
function  findone(l) {
	  var  htmla;
		$("#typeId").empty();
		htmla+="<option value='-10'>请选择配件类型</option>";	  
		JY.Ajax.doRequest(null, bonuspath + '/backstage/partone/findone', {
		}, function(data) {
          var  obj=  data.obj;
           var list= obj.length;
               for(var i=0; i<list;i++ ){
               htmla+="<option  value='"+obj[i].typeId+"' >"+obj[i].typeName+"</option>";
                       }
               $("#typeId").append(htmla);
               setForm(l);
	});
}
















