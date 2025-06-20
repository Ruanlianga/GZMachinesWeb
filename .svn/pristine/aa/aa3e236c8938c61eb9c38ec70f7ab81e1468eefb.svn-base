$(function() {
	// 初始化树
	getOrgTreeData();
	getbaseList();
	
	$('#saveBtn').on('click', function(e) {
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){
			chks.push($(this).val());
		});
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!");
		}else{
			JY.Ajax.doRequest(null,bonuspath +'/backstage/user/updateUsers',{roleId:$("#roleId").val(),chks:chks.toString()},function(data){
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
	edit : {
		enable : true,
		renameTitle : "修改",
		removeTitle : "删除",
		showRemoveBtn: showRenameBtn,
		showRenameBtn: showRenameBtn
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
		// 删除前确认
		beforeRemove : beforeRemove
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

// 修改完时处理 不进行后台数据处理
function beforeRename(treeId, treeNode, newName) {
	if (newName.length == 1) {
		JY.Model.info("角色名称不能为空.");
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
	if(treeNode.id == 1){
		JY.Model.info("管理员角色不能删除！");
		return false;
	}
	
	var zTree = getTree();
	//选中该节点
	zTree.selectNode(treeNode);
	
	if(treeNode.id){
		JY.Model.confirm("确认删除 节点 -- " + treeNode.name + " 吗？",function(){
			//此处进行ajax后台数据处理
			$.post(bonuspath +'/backstage/role/del',
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
	$.post(bonuspath +'/backstage/role/add',		//数据提交的地址
		   {"name" : treeNode.name},	//提交的数据
		   function(data){//回调函数
				JY.Model.info(data.resMsg,function(){refreshTree();});
		   },"json");//预期返回的数据类型
}

//修改节点名称
function updateNode(treeNode){
	//此处进行ajax后台数据处理
	$.post(bonuspath +'/backstage/role/update',		//数据提交的地址
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

function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.id).unbind().remove();
};

// 添加新增按钮
function addHoverDom(treeId, treeNode) {
	if (treeNode.id != 0 || treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0)
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
var zNodes1 = [{ id:0, pId:-1,open:true,name:"角色列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData() {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/role/findAll',
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

function onTreeClick(event, treeId, treeNode, clickFlag){
	$("#roleVisName").html(treeNode.name);
	$("#roleId").val(treeNode.id);
	getbaseList();
}

function getbaseList(role){
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/user/findAllByRole',null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
        	 var results=obj.list;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 if(l.isActive == 1){
            			 html+="<td class='center'><label> <input type='checkbox' checked='true' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 } else {
            			 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 }
            		 html+="<td class='center hidden-480'>"+(i+1)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.loginName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center '>"+JY.Object.notEmpty(l.orgName)+"</td>";
            		 html+="<td class='center '>"+JY.Object.notEmpty(l.telphone)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.mail)+"</td>";
            		 html+="</tr>";
            	 } 
        		 $("#baseTable tbody").append(html);
        	 }else{
        		html+="<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }
    	 JY.Model.loadingClose();
	 });
}





