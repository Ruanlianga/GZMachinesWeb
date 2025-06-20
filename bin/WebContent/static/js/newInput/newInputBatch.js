$(function() {
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
		laydate.render({
			elem: '#time'
		});
		laydate.render({
			elem: '#buyTime',
			type: 'datetime'
		});
		laydate.render({
			elem: '#acceptTime',
			type: 'datetime'	
		});
	});
	
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
	$('#addBtn').on('click',
			function(e) {
				// 通知浏览器不要执行与事件关联的默认动作
				e.preventDefault();
				cleanForm();
				findAcceptor();
				JY.Model.edit("auDiv", "新增", function() {
					if (JY.Validate.form("auForm")) {
						var that = $(this);
						JY.Ajax.doRequest("auForm", bonuspath
								+ '/backstage/task/add', 
							{ 
								name:'新购入库',
								type:'1',
								status:'1'
							}, function(data) {
								var id = data.obj.id;
								var creator = data.obj.id;
								var createTime = data.obj.createTime;
								var remark = data.obj.remark;
								addShop(id,creator,createTime,remark);
							that.dialog("close");
							JY.Model.info(data.resMsg, function() {
								search();
							});
						});
					}
				});
		});

});

function loadOrgTree(){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/orgTree',null,function(data){
		$.fn.zTree.init($("#orgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
	});
}
function emptyRole(){
	$("#acceptors").prop("value","");
	$("#auForm input[name$='acceptor']").prop("value","0");
}
var preisShow=false;//窗口是否显示
function showRole() {
	if(preisShow){
		hideRole();
	}else{
		var obj = $("#acceptors");
		var offpos = $("#acceptors").position();
		$("#orgContent").css({left:offpos.left+"px",top:offpos.top+obj.heigth+"px"}).slideDown("fast");	
		preisShow=true;
	}
}
function clickRole(e, treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if(check){
		var zTree = $.fn.zTree.getZTreeObj("orgTree"),
		nodes = zTree.getSelectedNodes(),v ="",n ="",o="",p="";	
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";//获取name值
			n += nodes[i].id + ",";//获取id值
			o += nodes[i].other + ",";//获取自定义值
			var pathNodes=nodes[i].getPath();
			for(var y=0;y<pathNodes.length;y++){
				p+=pathNodes[y].name+"/";//获取path/name值
			}
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);	
		if (n.length > 0 ) n = n.substring(0, n.length-1);
		if (o.length > 0 ) o = o.substring(0, o.length-1);
		if (p.length > 0 ) p = p.substring(0, p.length-1);
		
		$("#acceptors").val(p);
		$("#auForm input[name$='acceptor']").prop("value",n);
		hideRole();
	}
}
function hideRole(){
	$("#orgContent").fadeOut("fast");
	preisShow=false;
}

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax
			.doRequest(
					"baseForm",
					bonuspath + '/backstage/new/findByPage',
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
								html += "<td class='center'>"+ JY.Object.notEmpty(l.buyTime) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.buyer) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.acceptTime) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.acceptor) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.caretor) + "</td>";
//								html += "<td class='center'>"+ JY.Object.notEmpty(l.careteTime) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
								
								html += rowFunction(l.id,l.buyTime);
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

function rowFunction(id,buyTime) {
	var h = "";
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='批次详情' onclick='details(&apos;" + id + "&apos;,&apos;"+ buyTime +"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='批次详情' onclick='details(&apos;" + id + "&apos;,&apos;"+ buyTime +"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h += "<li><a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
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
			JY.Ajax.doRequest(null, bonuspath + '/backstage/new/delBatch', {
				chks : chks.toString()
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		});
	}
});

function addShop(id,creator,createTime,remark){
	var acceptor = $("#acceptor ").val();
	var buyTime = $("#buyTime ").val();
	var acceptTime = $("#acceptTime ").val();
	cleanForm();
	$.ajax({
		type: "post",
		url: bonuspath + '/backstage/new/add',
		data: {
			id : id,
			creator : creator,
			createTime : createTime,
			remark : remark,
			acceptor : acceptor,
			buyTime : buyTime,
			acceptTime : acceptTime
		},
		dataType: "json",
		success: function(data) {
			search();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}

	});
}

function check(id) {
	cleanForm();
	findAcceptor();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/new/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function details(id,buyTime){
	localStorage.setItem("batchId",id);
	localStorage.setItem("buyTime",buyTime);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具增加','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1000px', '600px'],
	  content:bonuspath + '/backstage/inputDetails/list'
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='buyTime']").val(JY.Object.notEmpty(l.buyTime));
	$("#auForm input[name$='buyer']").val(JY.Object.notEmpty(l.buyer));
	$("#auForm input[name$='acceptTime']").val(JY.Object.notEmpty(l.acceptTime));
	$("#auForm input[name$='acceptor']").val(JY.Object.notEmpty(l.acceptor));
	$("#auForm input[name$='caretor']").val(JY.Object.notEmpty(l.caretor));
	$("#auForm input[name$='careteTime']").val(JY.Object.notEmpty(l.careteTime));
	$("#auForm input[name$='remark']").val(JY.Object.notEmpty(l.remark));

}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='buyTime']").val('');
	$("#auForm input[name$='buyer']").val('');
	$("#auForm input[name$='acceptTime']").val('');
	$("#auForm input[name$='acceptor']").val('');
	$("#auForm input[name$='caretor']").val('');
	$("#auForm input[name$='careteTime']").val('');
	$("#auForm input[name$='remark']").val('');
	hideRole();
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(id) {
	cleanForm();
	findAcceptor();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/new/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/new/update', null, function(data) {
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
		JY.Ajax.doRequest(null, bonuspath + '/backstage/new/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function findAcceptor(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/user/findByPage',{},function(data){
		$("#acceptor").html("");
		var results = data.obj.list.results;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#acceptor").append(html);
		}else{
			html+="<option ></option>";;
			$("#acceptor").append(html);
		}
//		$('#acceptor').select2();  
//		$("#acceptor").select2({  //可以调$("#XXX")
//			'width':'200px',
//			separator: ",", // 分隔符  
//			tags: true,  //true可以手动添加数据
//			placeholder: "默认提示语", //默认提示语
//			maximumSelectionLength: 1  //最多能够选择的个数
//		});
	});
}

