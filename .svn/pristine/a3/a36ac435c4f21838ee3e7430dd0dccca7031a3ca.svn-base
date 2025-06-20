$(function() {
	// 初始化树
	getRoleTreeData();
	getResouleTreeData();
	$('#saveBtn').on(
			'click',
			function(e) {
				e.preventDefault();
				var chks = [];
				
				var treeObj = $.fn.zTree.getZTreeObj("resTree");
				var nodes = treeObj.getCheckedNodes(true);
				for(var i = 0;i < nodes.length; i++) {
					chks.push(nodes[i].id);
				}
				JY.Ajax.doRequest(null, bonuspath+ '/backstage/res/updateResouces', {roleId : $("#roleId").val(),chks : chks.toString()}, function(data) {
					JY.Model.info(data.resMsg);
				});
			});
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
	callback : {
		onClick : onTreeClick,
		// 点击前
		beforeClick : beforeClick,
		// 不允许拖拽
		beforeDrag : beforeDrag
	}
};

var setting2 = {
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true
		}
	}
};

function beforeClick(treeId, treeNode) {
	if (treeNode.id == 0) {
		return false;
	}
	return true;
}

// 不允许拖拽
function beforeDrag(treeId, treeNodes) {
	return false;
}

// 定义树节点初始数据
var zNodes1 = [{
	id : 0,
	pId : -1,
	open : true,
	name : "角色列表",
	icon : bonuspath + "/static/css/sys/images/home.gif"
}];

//获取数据初始化树
function getRoleTreeData() {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/role/findAll',
		data : null,
		success : function(result) {
			var nodes = zNodes1.concat(result.obj);
			$.fn.zTree.init($("#roleTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}
var zNodes2 = [];
//获取数据初始化树
function getResouleTreeData() {
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/res/findAll',null,function(result){
		var nodes = zNodes2.concat(result.obj);
		$.fn.zTree.init($("#resTree"), setting2, nodes);
	});
}

function onTreeClick(event, treeId, treeNode, clickFlag) {
	$("#roleVisName").html(treeNode.name);
	$("#roleId").val(treeNode.id);
	getResouleTreeData();
}
