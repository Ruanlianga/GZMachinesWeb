$(function() {
	// 初始化树
	getOrgTreeData();
});

// 树的设置
var setting1 = {
	view : {
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	edit : {
		enable : false,
	},
	check : {
		enable : true
	},
	callback : {
		//点击前
		beforeClick:beforeClick,
		// 不允许拖拽
		beforeDrag : beforeDrag
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

// 不允许拖拽
function beforeDrag(treeId, treeNodes) {
	return false;
}

// 定义树节点初始数据
var zNodes1 = [{ id:0, pId:-1,open:true,name:"库管员列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData() {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/user/findPerson',
		data : {
			postId:2
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj);
			console.info(zNodes1);
			$.fn.zTree.init($("#keeperTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("keeperTree");
}

function save(){
	var keeperIds="";
	var keeperNames="";
	var treeObj = $.fn.zTree.getZTreeObj("keeperTree");
	var nodes = treeObj.getCheckedNodes(true);
	for(var i = 1;i < nodes.length; i++) {
		keeperIds += nodes[i].id + ",";
		keeperNames += nodes[i].name + ",";
	}
	keeperIds = keeperIds.substring(0,keeperIds.length-1);
	keeperNames = keeperNames.substring(0,keeperNames.length-1);
	localStorage.setItem("keeperIds",keeperIds);
	localStorage.setItem("keeperNames",keeperNames);
	window.parent.setKeeperForm();
	var index=parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function search(){
	getbaseList();
}
