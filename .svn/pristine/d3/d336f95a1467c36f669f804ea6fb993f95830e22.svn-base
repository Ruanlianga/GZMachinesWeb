$(function() {
	var name = localStorage.getItem("maVenderTreeName");
	localStorage.setItem("maVenderTreeName","");
	// 初始化树
	getOrgTreeData(name);
});

function query(){
	var name=$("#search").val();
	getOrgTreeData(name);
}

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
var zNodes1 = [{ id:0, pId:-1,open:true,name:"机具厂家列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData(name) {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/vender/maVenderTree',
		data : {
			name:name
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj.list);
			console.info(zNodes1);
			$.fn.zTree.init($("#maVenderTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("maVenderTree");
}

function onTreeClick(event, treeId, treeNode, clickFlag){
	console.info("name"+treeNode.name+",id:"+treeNode.id);
	localStorage.setItem("maVenderId",treeNode.id);
	localStorage.setItem("maVenderName",treeNode.name);
	window.parent.setMaVenderForm();
	var index=parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function search(){
	getbaseList();
}
