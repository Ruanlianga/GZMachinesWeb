$(function() {
	var name = localStorage.getItem("unitTreeName");
	var isOpen = localStorage.getItem("isOpen");
	localStorage.setItem("unitTreeName","");
	localStorage.setItem("isOpen","");
	// 初始化树
	getOrgTreeData(name,isOpen);
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
var zNodes1 = [{ id:0, pId:-1,open:true,name:"往来单位列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData(name,isOpen) {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/company/unitTree',
		data : {
			name : name,
			isOpen : isOpen
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj.list);
			console.info(zNodes1);
			$.fn.zTree.init($("#unitTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("unitTree");
}

function onTreeClick(event, treeId, treeNode, clickFlag){
	console.info("name"+treeNode.name+",id:"+treeNode.id);
	if(treeNode.id.indexOf("lx") != -1 || treeNode.id.indexOf("dw") != -1){
		//墨绿深蓝风
		layer.alert('请重新选择', {
		  skin: 'layui-layer-molv', //样式类名
		  closeBtn: 0
		});
	}else{
		localStorage.setItem("unitId",treeNode.id);
		localStorage.setItem("unitName",treeNode.name);
		window.parent.setUnitForm();
		var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	}
}

function search(){
	getbaseList();
}
