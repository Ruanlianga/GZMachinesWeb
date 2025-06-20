var proName = localStorage.getItem("projectTreeName");
var proUnitId = localStorage.getItem("unitId");
var balanceEnd = localStorage.getItem("isBalanceEnd");
	
$(function() {
	var name = localStorage.getItem("projectTreeName");
	var unitId = localStorage.getItem("unitId");
	var isBalanceEnd = localStorage.getItem("isBalanceEnd");
	
	localStorage.setItem("projectTreeName","");
	localStorage.setItem("unitId","");
	localStorage.setItem("isBalanceEnd","");
	
	// 初始化树
	getOrgTreeData(name,unitId,isBalanceEnd);
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
var zNodes1 = [{ id:0, pId:-1,open:true,name:"工程名称列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData(name,unitId,isBalanceEnd) {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/project/projectTree',
		data : {
			name:name,
			unitId:unitId,
			isBalanceEnd:isBalanceEnd
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj.list);
			console.info(zNodes1);
			$.fn.zTree.init($("#projectTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

//重新加载数据初始化树
function reloadTree() {
	var keyWord = $("#keyWord").val();
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/project/projectTree',
		data : {
			name:proName,
			unitId:proUnitId,
			isBalanceEnd:balanceEnd,
			keyWord:keyWord
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj.list);
			console.info(zNodes1);
			$.fn.zTree.init($("#projectTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("projectTree");
}

function onTreeClick(event, treeId, treeNode, clickFlag){
	if(treeNode.id.indexOf("gs") != -1){
		//墨绿深蓝风
		layer.alert('请重新选择', {
		  skin: 'layui-layer-molv', //样式类名
		  closeBtn: 0
		});
	}else{
		localStorage.setItem("projectId",treeNode.id);
		localStorage.setItem("projectName",treeNode.name);
		window.parent.setProjectForm();
		var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	}
}

function search(){
	getbaseList();
}
