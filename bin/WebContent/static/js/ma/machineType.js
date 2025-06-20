$(function() {
	// 初始化树
	getOrgTreeData();
	getbaseList();
	$('#addBtn').on('click',function(e) {
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		$("#name").val("");
		JY.Model.edit("auDiv", "新增", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				var parentId = $("#parentIds").val();
				var name = $("#names").val();
				JY.Ajax.doRequest(null, bonuspath+'/backstage/machineType/insert',{parentId:parentId,name:name}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
	
	$('#saveBtn').on('click', function(e) {
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){
			chks.push($(this).val());
		});
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!");
		}else{
			JY.Ajax.doRequest(null,bonuspath +'/backstage/machineType/updateType',{roleId:$("#roleId").val(),chks:chks.toString()},function(data){
				JY.Model.info(data.resMsg);
			});	
		}
	});

});

// 树的设置
var setting1 = {
	view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick: onTreeClick,
		//点击前
		beforeClick:beforeClick,
		// 不允许拖拽
		beforeDrag : beforeDrag,
		// 修改前确认
		beforeEditName : beforeEditName,
		// 修改完时的处理
		beforeRename : beforeRename,
		// 修改成功后处理
		onRename : onRename,
	}
};

function beforeClick(treeId, treeNode){
	if(treeNode.id == 0){
		return false;
	}
	return true;
}

function showRenameBtn(treeId, treeNode){
	if (treeNode.id == 0){
		return false;
	}
	return true;
}

function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.id).unbind().remove();
};

// 修改完时处理 不进行后台数据处理
function beforeRename(treeId, treeNode, newName) {
	if (newName.length == 1) {
		JY.Model.info("类型名称不能为空.");
		var zTree = getTree();
		setTimeout(function() {
			zTree.editName(treeNode);
		}, 10);
		return false;
	}
	return true;
}


// 修改成功后处理
function onRename(event, treeId, treeNode) {
	if (treeNode.existed) {
		updateNode(treeNode);
	} else {
		addNode(treeNode);
	}
}

//添加节点
function addNode(treeNode){
	//此处进行ajax后台数据处理
	$.post(bonuspath +'/backstage/machineType/treeInsert',		//数据提交的地址
		   {"name" : treeNode.name,"parentId":treeNode.parentId},	//提交的数据
		   function(data){//回调函数
				JY.Model.info(data.resMsg,function(){refreshTree();});
		   },"json");//预期返回的数据类型
}

//修改节点名称
function updateNode(treeNode){
	//此处进行ajax后台数据处理
	$.post(bonuspath +'/backstage/machineType/treeUpdate',		//数据提交的地址
		   {"id" : treeNode.id,					//提交的数据
			"name" : treeNode.name},				
		   function(data){
				JY.Model.info(data.resMsg,function(){refreshTree();});
		   },"json");//预期返回的数据类型
}

// 不允许拖拽
function beforeDrag(treeId, treeNodes) {
	return false;
}

// 确认是否进入编辑状态
function beforeEditName(treeId, treeNode) {
	if (treeNode.id == 0) {
		JY.Model.info("根节点不能修改！");
		return false;
	}
	return true;
}

// 添加新增按钮
function addHoverDom(treeId, treeNode) {
	console.info("editNameFlag:"+treeNode.editNameFlag+",length："+$("#addBtn_" + treeNode.id).length+"，pId："+treeNode.pId);
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0 || treeNode.pId != 0){
		return;
	}else if(treeNode.pId != "null"){
		return;
	}

	var sObj = $("#" + treeNode.tId + "_span");
	var addStr = "<span class='button add' id='addBtn_" + treeNode.id
			+ "' title='添加' onfocus='this.blur();'></span>";
	sObj.append(addStr);

	var btn = $("#addBtn_" + treeNode.id);
	if (btn) {
		btn.bind("click", function() {
			var zTree = getTree();
			var newNode;
			newNode = zTree.addNodes(treeNode, {
				parentId : treeNode.id,
				name : "新增",
				icon : bonuspath + "/static/css/sys/images/user_group.gif"
			});
			if (newNode) {
				zTree.editName(newNode[0]);
			}
			return false;
		});
	}
};

// 定义树节点初始数据
var zNodes1 = [{ id:0, pId:-1,open:true,name:"机具类型列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData() {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/machineType/getMainTree',
		data : null,
		success : function(result) {
			var nodes = zNodes1.concat(result.obj.list);
			$.fn.zTree.init($("#orgTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

function refreshTree(){
	getOrgTreeData();
}

// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("orgTree");
}

function onTreeClick(event, treeId, treeNode, clickFlag){
	$("#roleVisName").html(treeNode.name);
	$("#pId").val(treeNode.id);
	$("#parentIds").val(treeNode.id);
	getbaseList(treeNode.id);
}

function search(){
	getbaseList();
}

function getbaseList(){
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/machineType/findByPage',null,function(data){
		 $("#baseTable tbody").empty();
		 var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum,
				pageSize = list.pageSize,
				totalRecord = list.totalRecord;
        	 var html = "";
    		 if(results != null && results.length > 0){
    			 var leng = (pageNum - 1) * pageSize;
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center hidden-480'>"+(i + leng + 1)+"</td>";
            		 html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.name)+"</td>";
            		 html+=rowFunction(l.id,l.name);
            		 html+="</tr>";
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
        	 }else{
        		html+="<tr><td colspan='4' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }
    	 JY.Model.loadingClose();
	 });
}

function cleanForm(){
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(''));
}

function setForm(data){
	//alert(JSON.stringify(data));
	var l=data.obj;
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
}

function rowFunction(id,name){
	var h="";
	h+="<td class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;,&apos;"+name+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="<a href='#' title='修改' onclick='edit(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
//	h+="<a href='#' title='删除' onclick='del(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}
function del(accountId){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/machineType/treeDelete',{id:accountId},function(data){
			JY.Model.info(data.resMsg,function(){search();});
		});
	});
}
function edit(id){
	cleanForm();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machineType/find',{id:id},function(data){
		alert(JSON.stringify(data));
	    setForm(data);   
	    JY.Model.edit("auDiv","修改",function(){
	    	if(JY.Validate.form("auForm")){
				var that =$(this);
				JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machineType/update',{id:id},function(data){
				    that.dialog("close");
				    JY.Model.info(data.resMsg,function(){search();});	
				});
			}	
	    });
	});
}

function check(id,name){
	//iframe层-父子操作
	localStorage.setItem("parentId",id);
	localStorage.setItem("parentName",name);
	layer.open({
		type: 2,
		title:['机具型号详情','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['850px', '530px'],
		content: bonuspath+'/backstage/machineType/details'
	});
}



