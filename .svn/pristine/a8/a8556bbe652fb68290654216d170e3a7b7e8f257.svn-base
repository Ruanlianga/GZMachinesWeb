var  maTypeId;
$(function() {
	var name = localStorage.getItem("maModelTreeName");
	 maTypeId = localStorage.getItem("maTypeTreeId");
	localStorage.setItem("maModelTreeName","");
	localStorage.setItem("maTypeTreeId","");
	// 初始化树
	getOrgTreeData(name,maTypeId);
//	query();
});


function query(){
	var name=$("#search").val();
	getOrgTreeData(name,maTypeId);
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
var zNodes1 = [{ id:0, pId:-1,open:true,name:"机具类型列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData(name,maTypeId) {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/machineType/maModelTree',
		data : {
			name:name,
			parentId:maTypeId
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj.list);
			console.info(zNodes1);
			$.fn.zTree.init($("#maModelTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("maModelTree");
}

function onTreeClick(event, treeId, treeNode, clickFlag){
	console.info("name"+treeNode.name+",id:"+treeNode.id);
	if(treeNode.id.indexOf("lx") != -1){
		//墨绿深蓝风
		layer.alert('请重新选择', {
		  skin: 'layui-layer-molv', //样式类名
		  closeBtn: 0
		});
	}else{
		localStorage.setItem("maModelId",treeNode.id);
		localStorage.setItem("maModelName",treeNode.name);
		window.parent.setMaModelForm();
		var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	}
}

function search(){
	getbaseList();
}
