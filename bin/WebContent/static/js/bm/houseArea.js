$(function() {
	getbaseList();
	loadOrgTree();
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
				//findAreaType();
				findHouse();
				JY.Model.edit("auDiv", "新增", function() {
					if (JY.Validate.form("auForm")) {
						var that = $(this);
						JY.Ajax.doRequest("auForm", bonuspath
								+ '/backstage/houseArea/add', null, function(data) {
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
					bonuspath + '/backstage/houseArea/findByPage',
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
function  area(){
	var area=l.length*l.width;
	return area;
	
}
								
								
								html += "<td class='center'>"+ JY.Object.notEmpty(l.length) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.width) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(area()) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.lon) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.lat) + "</td>";
								html += "<td class='center'>"+JY.Object.notEmpty(l.stand) +"/"+ JY.Object.notEmpty(l.areaType) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.houseName) + "</td>";
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
			JY.Ajax.doRequest(null, bonuspath + '/backstage/houseArea/delBatch', {
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
//	findAreaType();
	findHouse();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/houseArea/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='length']").val(JY.Object.notEmpty(l.length));
	$("#auForm input[name$='width']").val(JY.Object.notEmpty(l.width));
	$("#auForm input[name$='area']").val(JY.Object.notEmpty(l.area));
	$("#auForm input[name$='lon']").val(JY.Object.notEmpty(l.lon));
	$("#auForm input[name$='lat']").val(JY.Object.notEmpty(l.lat));
	$("#auForm input[name$='orgName']").val(JY.Object.notEmpty(l.areaType));
	$("#auForm input[name$='orgId']").val(JY.Object.notEmpty(l.orgId));
	$("#auForm input[name$='houseName']").val(JY.Object.notEmpty(l.houseName));

}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='length']").val('');
	$("#auForm input[name$='width']").val('');
	$("#auForm input[name$='area']").val('');
	$("#auForm input[name$='lon']").val('');
	$("#auForm input[name$='lat']").val('');
	$("#auForm input[name$='areaType']").val('');
	$("#auForm input[name$='houseName']").val('');
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
	//findAreaType();
	findHouse();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/houseArea/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/houseArea/update', null, function(data) {
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
		JY.Ajax.doRequest(null, bonuspath + '/backstage/houseArea/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

/*function findAreaType(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/areaType/findma',{},function(data){
	//JY.Ajax.doRequest("auForm",bonuspath +'/backstage/areaType/findAreaType',{},function(data){
		$("#areaType").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];

				html+="<option value='"+l.maid+"'>"+l.mtname+"</option>";
			}
			$("#areaType").append(html);
		}else{
			html+="<option ></option>";
			$("#areaType").append(html);
		}
	});

}*/
function findHouse(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/house/findHouse',{},function(data){
		$("#houseName").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				
			
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#houseName").append(html);
		}else{
			html+="<option ></option>";
			$("#houseName").append(html);
		}
	});

}



/*function change(value){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/areaType/findmt',{mtid:value},function(data){
		//JY.Ajax.doRequest("auForm",bonuspath +'/backstage/areaType/findAreaType',{},function(data){
			$("#classone").html("");
			var results = data.obj;
			var htmla="";
			if(results!=null&&results.length>0){
				for(var i=0;i<results.length;i++){
					var l= results[i];

					htmla+="<option value='"+l.maname+"'>"+l.maname+"</option>";
				}
				$("#classone").append(html);
			}else{
				html+="<option ></option>";
				$("#classone").append(html);
			}
		});
	
	
}
*/

function loadOrgTree(){

	JY.Ajax.doRequest("",bonuspath +'/backstage/areaType/orgTree',null,function(data){
	
		$.fn.zTree.init($("#orgTree"),{
			view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},
			data:{simpleData:{enable: true}},
			callback:{onClick:clickOrgTree}},
			data.obj
		);
	});
}
var orgTreeShow=false;//窗口是否显示

function showOrgTree() {
	if(orgTreeShow){
		hideOrgTree();
	}else{
		var obj = $("#orgName");
		var offpos = $("#orgName").position();
		$("#orgContent").css({left:offpos.left+"px",top:offpos.top+obj.heigth+"px"}).slideDown("fast");	
		orgTreeShow=true;
	}
}
function clickOrgTree(e, treeId, treeNode) {
	var check = (treeNode);
	if(check){
		var zTree = $.fn.zTree.getZTreeObj("orgTree"),
		nodes = zTree.getSelectedNodes(),v ="",n ="",o="",p="",q="";	
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";//获取name值
			n += nodes[i].id + ",";//获取id值
			o += nodes[i].other + ",";//获取自定义值
			q += nodes[i].distance;
			var pathNodes=nodes[i].getPath();
			for(var y=0;y<pathNodes.length;y++){
				p+=pathNodes[y].name+"/";//获取path/name值
			}
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);	
		if (n.length > 0 ) n = n.substring(0, n.length-1);
		if (o.length > 0 ) o = o.substring(0, o.length-1);
		if (p.length > 0 ) p = p.substring(0, p.length-1);
	
		$("#orgName").val(p);
		if(treeNode.isParent){
			$("#parentId").val(n);
			$("#orgId").val(null);
		}else{
			$("#orgId").val(n);
			$("#parentId").val(null);
		}
		console.info("isParent:" + treeNode.isParent + ",parentId:" + $("#parentId").val() + ",orgId:" + $("#orgId").val());
		hideOrgTree();
	}
}

function hideOrgTree(){
	$("#orgContent").fadeOut("fast");
	orgTreeShow=false;
}

//数结构结束




















