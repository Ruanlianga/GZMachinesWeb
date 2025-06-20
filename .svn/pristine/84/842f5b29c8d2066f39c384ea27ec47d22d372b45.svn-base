/**
 * 
 */
var nodeArray = [];
var preArray = [];
var nodeObj = {};
//var excludeArr = [];
$(function() {
	var name = localStorage.getItem("unitTreeName");
	var isOpen = localStorage.getItem("isOpen");
	//excludeArr = localStorage.getItem("excludeArr");
	//excludeArr = "dw1,dw2,dw3";
	//console.log(excludeArr);
	
	localStorage.setItem("unitTreeName","");
	localStorage.setItem("isOpen","");
	$("#key").focus();
	// 初始化树
	getOrgTreeData(name,isOpen);
	
	$("#key").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			$("#unitTree_4_a").trigger("click")
		}
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
		//	alert(JSON.stringify(result))
			initData(result.obj.list);
			var nodes = zNodes1.concat(result.obj.list);
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
		/*layer.alert('请重新选择', {
		  skin: 'layui-layer-molv', //样式类名
		  closeBtn: 0
		});*/
	}else{
		localStorage.setItem("unitId",treeNode.id);
		localStorage.setItem("unitName",treeNode.name);
		window.parent.setUnitForm();
		var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	}
}


/**
 * @author 无畏
 * @date 2019-05-08
 * @function 将数组里面对象装进 id为键 值为数组中对象的 json对象  以便快速定位到该值
 * @returns 
 */
function initData(arr){
	nodeArray = arr;
	preArray = arr;
	var size = nodeArray.length;
	var node ={};
	var key ="";
	for(var i = 0;i<size;i++){
		node = nodeArray[i];
		key=node.id;
		nodeObj[key]=node;
	}
}

/**
 * @author 无畏
 * @date 2019-05-08
 * @function 排除指定id的node
 * @returns 数组对象
 */
function excludeUnitId(arr){
	initData(arr);
	
	if(excludeArr.indexOf(",") == -1){
		delete nodeObj[excludeArr];
	}else{
		var a = excludeArr.split(",");
		/*var size = a.length;
		for(var i = 0;i<size;i++){
			delete nodeObj[a[i]];
		}*/
		deleteExclode(a);
	}
	nodeArray = JsonToArray(nodeObj,1)
	return nodeArray;
}

function deleteExclode(arr){ 
	var size = arr.length;
	var node ={};
	for(var i = 0; i < size ; i++){
		
		node = nodeObj[arr[i]];
		
		if(node.id.indexOf("lx") == -1 && node.id.indexOf("dw") == -1){
			
			delete nodeObj[node.id];
			
		}else if(node.id.indexOf("lx") != -1){
			delete nodeObj[node.id];
			for (var key in nodeObj){
		    	if(nodeObj[key].pId  == node.id){
		    		delete nodeObj[key]
		    	}
		    }
		}else if(node.id.indexOf("dw") != -1){
			var b = node;
			delete nodeObj[node.id];
			
			for (var key in nodeObj){
		    	if(nodeObj[key].pId  == b.id){
		    		var a = nodeObj[key];
		    		delete nodeObj[key];
		    		for(var keyy in nodeObj){
		    			if(nodeObj[keyy].pId == a.id){
		    				delete nodeObj[keyy];;
		    			}
		    		}
		    	}
		    	
		    }
		}
	}
}


/**
 * @author 无畏
 * @date 2019-05-08
 * @function 将传入的json对值全部封装进数组
 * @returns 数组对象
 */
function JsonToArray(obj,flag){
	var arr = [];
    for (var key in obj){
    	arr.push(obj[key]);
    }
    var size = arr.length;
    if(flag != 1){
    	for(var i = 0;i<size;i++){
        	arr[i].open = true;
        }
    }
    return arr;
}

/**
 * @author 无畏
 * @date 2019-05-08
 * @function 将数组里面对象装进 id为键 值为数组中对象的 json对象  以便快速定位到该值
 * @returns 
 */
function keyChange(){
	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	var keyWord = $("#key").val().trim();
	if(!reg.test(keyWord)){
		return;
	}
	var keyObj = {};
	var size = nodeArray.length;
	var name="";   
	var node = {};
	var p = {};
	var pp = {};
	for(var i = 0; i < size ; i++){
		node = nodeArray[i];
		name = node.name;
		if(name.indexOf(keyWord) != -1){
			keyObj[node.id] = node;
			if(node.id.indexOf("lx") == -1 && node.id.indexOf("dw") == -1){
					p =nodeObj[node.pId];
					keyObj[p.id] = p;
					pp = nodeObj[p.pId];
					keyObj[pp.id] = pp;
			}else if(node.id.indexOf("lx") != -1){
				p =nodeObj[node.pId];
				keyObj[p.id] = p;
				for (var key in nodeObj){
			    	if(nodeObj[key].pId  == node.id){
			    		keyObj[nodeObj[key].id] = nodeObj[key];
			    	}
			    }
			}else if(node.id.indexOf("dw") != -1){
				for (var key in nodeObj){
			    	if(nodeObj[key].pId  == node.id){
			    		keyObj[nodeObj[key].id] = nodeObj[key];
			    		for(var keyy in nodeObj){
			    			if(nodeObj[keyy].pId == nodeObj[key].id){
			    				keyObj[nodeObj[keyy].id] = nodeObj[keyy];
			    			}
			    		}
			    	}
			    	
			    }
			}		
		}
	}
	
	var nodes = zNodes1.concat(JsonToArray(keyObj));
	$.fn.zTree.init($("#unitTree"), setting1, nodes);
}


function search(){
	getbaseList();
}
