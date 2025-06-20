$(function() {
	var name = localStorage.getItem("checkTreeName");
	localStorage.setItem("checkTreeName","");
	// 初始化树
	getOrgTreeData(name);
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
	callback : {
		onClick: onTreeClick,
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
var zNodes1 = [{ id:0, pId:-1,open:true,name:"检验人员列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData(name) {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/user/findCheckPerson',
		data : {
			name:name,
			postId:4
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj);
			console.info(zNodes1);
			$.fn.zTree.init($("#checkTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("checkTree");
}

function onTreeClick(event, treeId, treeNode, clickFlag){
	localStorage.setItem("checkId",treeNode.id);
	localStorage.setItem("checkName",treeNode.name);
	window.parent.setCheckForm();
	var index=parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function search(){
	getbaseList();
}
