$(function() {
	// 初始化树
	getOrgTreeData();

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
	edit : {
		enable : true,
		renameTitle : "修改",
		removeTitle : "删除",
		showRemoveBtn: showRenameBtn,
		showRenameBtn: showRenameBtn
	},
	callback : {
		// 不允许拖拽
		beforeDrag : beforeDrag,
		// 修改前确认
		beforeEditName : beforeEditName,
		// 修改完时的处理
		beforeRename : beforeRename,
		// 修改成功后处理
		onRename : onRename,
		// 删除前确认
		beforeRemove : beforeRemove
	}
};

function showRenameBtn(treeId, treeNode){
//	if (treeNode.id == 1){
//		return false;
//	}
	return true;
}

// 修改完时处理 不进行后台数据处理
function beforeRename(treeId, treeNode, newName) {
	if (newName.length == 1) {
		JY.Model.info("节点名称不能为空.");
		var zTree = getTree();
		setTimeout(function() {
			zTree.editName(treeNode);
		}, 10);
		return false;
	}
	return true;
}

//确认是否删除+删除处理
function beforeRemove(treeId, treeNode) {
	if(treeNode.id==1){
		JY.Model.info("根节点不能删除！");
		return false;
	}
	
	var zTree = getTree();
	//选中该节点
	zTree.selectNode(treeNode);
	
	if(treeNode.isParent)
	{
		JY.Model.info("此节点存在子节点，不能删除。");
		return false;
	}
	if(treeNode.id){
		JY.Model.confirm("确认删除 节点 -- " + treeNode.name + " 吗？",function(){
			//此处进行ajax后台数据处理
			$.post(bonuspath +'/backstage/org/del',
				   {"id" : treeNode.id},
				   function(data){
					   JY.Model.info(data.resMsg,function(){refreshTree();});
				   },"json");
		});
	} else {
		  zTree.removeNode(treeNode);
	}
	return false;
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
	$.post(bonuspath +'/backstage/org/add',		//数据提交的地址
		   {"name" : treeNode.name,
			"parentId" : treeNode.getParentNode().id},	//提交的数据
		   function(data){//回调函数
				JY.Model.info(data.resMsg,function(){refreshTree();});
		   },"json");//预期返回的数据类型
}

//修改节点名称
function updateNode(treeNode){
	//此处进行ajax后台数据处理
	$.post(bonuspath +'/backstage/org/update',		//数据提交的地址
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
	if (treeNode.id == 1) {
		JY.Model.info("根节点不能修改！");
		return false;
	}
	return true;
}

function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.id).unbind().remove();
};

// 添加新增按钮
function addHoverDom(treeId, treeNode) {
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0)
		return;

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
var zNodes1 = [];

// 获取数据初始化树
function getOrgTreeData() {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/org/findAll',
		data : null,
		success : function(result) {
			var nodes = zNodes1.concat(result.obj);
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
