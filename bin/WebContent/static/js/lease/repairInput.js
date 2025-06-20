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
				findCheck();
				findRepair();
				JY.Model.edit("auDiv", "新增", function() {
					if (JY.Validate.form("auForm")) {
						var that = $(this);
						JY.Ajax.doRequest("auForm", bonuspath
								+ '/backstage/repairInput/add', null, function(data) {
							that.dialog("close");
							JY.Model.info(data.resMsg, function() {
								search();
							});
						});
					}
				});
		});
});

/**
 * 审核人
 * @returns
 */
function loadOrgTree(){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/orgTree',null,function(data){
		$.fn.zTree.init($("#orgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
	});
}

function emptyRole(){
	$("#checkPersons").prop("value","");
	$("#auForm input[name$='checkPerson']").prop("value","0");
}

var preisShow=false;//窗口是否显示
function showRole() {
	if(preisShow){
		hideRole();
	}else{
		var obj = $("#checkPersons");
		var offpos = $("#checkPersons").position();
		$("#orgContent").css({left:offpos.left+"px",top:offpos.top+obj.heith+"px"}).slideDown("fast");	
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
		$("#checkPersons").val(p);
		$("#auForm input[name$='checkPerson']").prop("value",n);
		hideRole();
	}
}
function hideRole(){
	$("#orgContent").fadeOut("fast");
	preisShow=false;
}

/**
 * 检修人
 * @returns
 */
function loadCompanyTree(){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/orgTree',null,function(data){
		$.fn.zTree.init($("#companyTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickCompany}},data.obj);
	});
}

function emptyCompany(){
	$("#repairPersons").prop("value","");
	$("#auForm input[name$='repairPerson']").prop("value","0");
}

var preisShows=false;//窗口是否显示
function showCompany() {
	if(preisShows){
		hideCompany();
	}else{
		var obj = $("#repairPersons");
		var offpos = $("#repairPersons").position();
		$("#orgCompany").css({left:offpos.left+"px",top:offpos.top+obj.heith+"px"}).slideDown("fast");	
		preisShows=true;
	}
}
function clickCompany(e, treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if(check){
		var zTree = $.fn.zTree.getZTreeObj("companyTree"),
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
		$("#repairPersons").val(p);
		$("#auForm input[name$='repairPerson']").prop("value",n);
		hideCompany();
	}
}
function hideCompany(){
	$("#orgCompany").fadeOut("fast");
	preisShows=false;
}

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax
			.doRequest(
					"baseForm",
					bonuspath + '/backstage/repairInput/findByPage',
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
										+ l.batchNum
										+ "' class='ace' /> <span class='lbl'></span></label></td>";
								html += "<td class='center hidden-480'>"
										+ (i + leng + 1) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.batchNum) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.batchTime) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.checkPerson) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.repairPerson) + "</td>";
								html += "<td class='center'>"+ JY.Object.notEmpty(l.notes) + "</td>";
								html += rowFunction(l.batchNum);
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='14' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}

function rowFunction(batchNum) {
	var h = "";
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='入库详情' onclick='repairDetails(&apos;"
			+ batchNum
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='入库' onclick='input(&apos;"
			+ batchNum 
			+ "&apos;)' class='aBtnNoTD' >|入库</a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='入库详情' onclick='repairDetails(&apos;"
			+ batchNum
			+ "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h += "<li><a href='#' title='入库' onclick='input(&apos;"
			+ batchNum 
			+ "&apos;)' class='aBtnNoTD' >|入库</a></li>";
	h += "</ul></div></div>";
	h += "</td>";

	return h;
}

/**
 * 修饰后入库，改变状态
 */
function input(batchNum){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/findByOutNums', {
		batchNum : batchNum
	}, function(data) {
		var res = data.obj.list.results;
		var deviceNum;
		if(res != null && res.length >0){
			for(var i = 0;i<res.length;i++){
				deviceNum = res[i].deviceNum;
				changeStatus(deviceNum);
//				findQRCodeByDeviceNum(deviceNum);
			}
		}
		
	});
}

function findQRCodeByDeviceNum(deviceNum){
	alert("deviceNum="+deviceNum)
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machines/findQRCodeByDeviceNum', {
		deviceNum : deviceNum
	}, function(data) {
		var res = data.obj;
		alert("data="+JSON.stringify(data));
	});
}

function changeStatus(deviceNum){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machines/updByDeviceNum', {
		deviceNum : deviceNum,
		batchStatus : 3
	}, function(data) {
		layer.alert('已入库', {
		    skin: 'layui-layer-lan'
		    ,closeBtn: 0
		    ,anim: 4 //动画类型
		  });
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
			JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/delBatch', {
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
	findCheck();
	findRepair();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/find', {
		outId : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function repairDetails(batchNum){
	localStorage.setItem("batchNum",batchNum);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具入库','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1000px', '600px'],
	  content:bonuspath + '/backstage/repairInput/details'
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='repairId']").val(l.repairId);
	$("#auForm input[name$='batchNums']").val(JY.Object.notEmpty(l.batchNums));
	$("#auForm input[name$='batchTime']").val(JY.Object.notEmpty(l.batchTime));
	$("#auForm input[name$='checkPerson']").val(JY.Object.notEmpty(l.checkPerson));
	$("#auForm input[name$='repairPerson']").val(JY.Object.notEmpty(l.repairPerson));
	$("#auForm input[name$='notes']").val(JY.Object.notEmpty(l.notes));
//	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
//	var nodes = treeObj.getNodesByParam("id",l.checkPerson);
//	if(nodes.length>0){
//		treeObj.selectNode(nodes[0]);
//		clickRole(null,null,nodes[0]);
//	}

}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='repairId']").val('0');// 上级资源
	$("#auForm input[name$='batchNums']").val('');
	$("#auForm input[name$='batchTime']").val('');
	$("#auForm input[name$='checkPerson']").val('0');
	$("#auForm input[name$='repairPerson']").val('');
	$("#auForm input[name$='notes']").val('');
	hideRole();
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(id) {
	cleanForm();
	findCheck();
	findRepair();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/find', {
		repairId : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/repairInput/update', null, function(data) {
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
		JY.Ajax.doRequest(null, bonuspath + '/backstage/repairInput/del', {
			repairId : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function findCheck(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/user/findByPage',{},function(data){
		$("#checkPerson").html("");
		var results = data.obj.list.results;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#checkPerson").append(html);
		}else{
			html+="<option ></option>";;
			$("#checkPerson").append(html);
		}
	});
	
}

function findRepair(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/user/findByPage',{},function(data){
		$("#repairPerson").html("");
		var results = data.obj.list.results;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#repairPerson").append(html);
		}else{
			html+="<option ></option>";;
			$("#repairPerson").append(html);
		}
	});
	
}


