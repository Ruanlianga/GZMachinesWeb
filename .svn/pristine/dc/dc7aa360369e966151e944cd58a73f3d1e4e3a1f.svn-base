$(function() {
	// 初始化树
	getOrgTreeData();
	getbaseList();
});

function save(){
	var chks =[];    
	$('#baseTable input[name="ids"]:checked').each(function(){
		chks.push($(this).val());
	});
	if(chks.length==0) {
		JY.Model.info("您没有选择任何内容!");
	}else{
		JY.Ajax.doRequest(null,bonuspath +'/backstage/ma/keeperType/updateKeeper',{userId:$("#roleId").val(),chks:chks.toString()},function(data){
			JY.Model.info(data.resMsg);
		});	
	}
}

// 树的设置
var setting1 = {
	view : {
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
		JY.Model.info("库管员名称不能为空.");
		var zTree = getTree();
		setTimeout(function() {
			zTree.editName(treeNode);
		}, 10);
		return false;
	}
	return true;
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

// 定义树节点初始数据
var zNodes1 = [{ id:0, pId:-1,open:true,name:"库管员列表",icon:bonuspath + "/static/css/sys/images/home.gif"}];

// 获取数据初始化树
function getOrgTreeData() {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/ma/keeperType/findAll',
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
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/ma/keeperType/findAllByKeeper',null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
        	 var results=obj.list;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 console.info("l.isActive:"+l.isActive);
            		 if(l.isActive == 1){
            			 html+="<td class='center'><label> <input type='checkbox' checked='true' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 } else {
            			 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 }
            		 html+="<td class='center hidden-480'>"+(i+1)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.firstName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.secondName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.parentName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.name)+"</td>";
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





