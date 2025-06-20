<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../baseset.jsp"%>
<%@include file="../../systemset.jsp"%>
<link rel="stylesheet"
	href="${bonuspath}/static/js/layui-v2.9.18/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui-v2.9.18/layui/layui.js"></script>
<link rel="stylesheet"
	href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script
	src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<title></title>

<style type="text/css">
.btn-box {
	width: 100%;
	height: 80px;
	box-sizing: border-box;
	display: flex;
	justify-content: end;
	align-items: center;
	padding-right: 2%;
}

#baseInfo tr {
	height: 2.5em;
}

#baseInfo tr>input {
	width: 96%;
}

.inp {
	width: 80%;
}

.a {
	text-align: left;
}

.tree-div {
	padding: 0px 5px;
	background: #cae6e8;
	border: 1px solid #cae6e8;
	width: 300px;
	height: 30px;
	position: absolute;
	top: 24%;
	border-radius: 5px;
	z-index: 999;
	overflow-x: hidden;
	cursor: pointer;
}

.treeTitle {
	font-size: 1.2em;
	text-align: center;
	width: 190px;
}

.required_icon, th span {
	font-size: 16px;
	color: red;
	margin: 0 5px 0 5px
}

.layui-form-label {
	width: 120px;
}

.layui-form-item .layui-input-inline {
	width: 350px;
}

#baseInfo tr th {
	vertical-align: middle;
}

.customTable {
	width: 96%;
	margin: 0 2% 0 2%;
}
</style>
</head>
<body>
	<div id="main-box">
		<form class="layui-form" onsubmit="return false;"
			onclick="return false;">
			<div class="layui-form-item" style="margin-top: 1%;">
				<div class="layui-inline">
					<label class="layui-form-label"><span class="required_icon">*</span>申请工程</label>
					<div class="layui-input-inline layui-input-wrap"
						style="background-color: #fff !important;">
						<select name="projectId" id="projectId" lay-verify="required" lay-search
							class="layui-select">
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"><span class="required_icon">*</span>需用日期</label>
					<div class="layui-input-inline layui-input-wrap">
						<input type="text" name="needTime" id="needTime"
							lay-verify="required" lay-affix="clear" class="layui-input"
							placeholder="请选择需用日期" readonly="readonly"
							style="cursor: pointer;">
					</div>
				</div>
			</div>
			<div class="layui-form-item">

				<div class="layui-inline">
					<label class="layui-form-label"><span class="required_icon">*</span>项目部分</label>
					<div class="layui-input-inline layui-input-wrap">
						<input type="text" name="projectPart" id="projectPart"
							lay-verify="required" autocomplete="off" lay-affix="clear"
							class="layui-input" maxlength="50" placeholder="请输入项目部分">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"><span class="required_icon">*</span>施工地点</label>
					<div class="layui-input-inline layui-input-wrap">
						<input type="text" name="projectContent" id="projectContent"
							lay-verify="required" autocomplete="off" lay-affix="clear"
							class="layui-input" maxlength="50" placeholder="请输入施工地点">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><span class="required_icon">*</span>计划说明</label>
				<div class="layui-input-inline" style="width: 844px;">
					<textarea placeholder="请输入计划说明" lay-verify="required" id="remark"
						name="remark" class="layui-textarea" maxLength="500"></textarea>
				</div>
			</div>
			<button type="submit" id="formSubmit" class="layui-btn" lay-submit=""
				lay-filter="formData" style="display: none;"></button>
		</form>

		<table id="baseInfo" cellspacing="0" cellpadding="0" border="0"
			class="customTable">
			<tbody class="a">
				<input type="hidden" name="taskId" value="${taskId}" />
				<tr class="out_source">
					<td colspan="6">
						<table>
							<tr>
								<td width="90%">
									<div style="right: 10%; position: fixed; bottom: auto;"
										title="点击拖拽" id="typeTree" class="tree-div">
										<div class="treeTitle">
											选择机具<a onclick="unfoldOrPickUpTypeTree(this)"
												style="font-size: 0.8em; margin-left: 1em; cursor: pointer;"
												title="点击开展或收起">展开</a>
										</div>
										<input style="margin-left: 10px; margin-top: 5px;" type="text"
											id="key" placeholder="Search..." value="${keyWord}"
											onblur="keyChange(1)" oninput="keyChange(2)" />
										<div>
											<ul id="machineTypeTree" class="ztree"></ul>
										</div>
									</div>
									<div id="b" onclick="t()"></div>
								</td>

							</tr>
						</table>

					</td>


				</tr>


				<tr>
					<td colspan="6">
						<table id="machineTable"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th style="width: 5%" class="center hidden-480">序号</th>
									<th style="width: 15%" class="center">物机类型</th>
									<th style="width: 15%" class="center">物机名称</th>
									<th style="width: 10%" class="center">规格</th>
									<th style="width: 10%" class="center">单位</th>
									<th style="width: 10%" class="center"><span>*</span>需用量</th>
									<th style="width: 10%" class="center"><span>*</span>需用天数</th>
									<th style="width: 15%" class="center">备注</th>
									<th style="width: 10%" class="center">操作</th>
								</tr>
							</thead>
							<tbody id="list">
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-box">
			<button class="layui-btn layui-btn-normal save" onclick="saveData2()">提交申请</button>
			<button class="layui-btn layui-btn-primary cancel"
				onclick="closePage()">返回</button>
		</div>
	</div>


	<script type="text/javascript">

var machines ={};//机具容器
var p; //父页面对象,多层关闭时使用

var form,laydate,layer;
layui.use(['form', 'laydate'], function(){
	   form = layui.form;
	   layer = layui.layer;
	   laydate = layui.laydate;
	   laydate.render({
		   elem: '#needTime'
		  });
	   form.verify();
	   form.on('submit(formData)', function (data) {
		   submitApply(data);
       });
	   getProSelect();
	form.render();
	
});

function saveData2() {
    $('#formSubmit').trigger('click')
}

// 工程下拉选
function getProSelect() {
    $.ajax({
    	url : bonuspath + '/backstage/planApplication/getProList',
        type: 'post',
        data: {},
        dataType:"JSON",
        success: function (result) {
            if (result.res === 1) {
            	setProList(result.obj);
            }
        }
    });
}

function setProList(list) {
    let html = '<option value="">请选择</option>';
    if(list && list.length > 0){
    	$.each(list, function (index, item) {
            html += '<option value="' + item.id + '">' + item.name + '</option>';
        })
    }
    $('#projectId').empty().append(html);
   layui.form.render();
}




$(function () {
	
	
	$("#typeTree").mousedown(function(ev){
		var div1 = document.getElementById("typeTree");
　　		var oevent = ev || event;
　　		var dx = oevent.clientX;
    　　	var distanceX = div1.offsetWidth;

 　　　　document.onmousemove = function(ev){
 　　　　　   var oevent = ev || event;
 			var diff = oevent.clientX - dx;
			div1.style.width = (distanceX - diff) + 'px';
 　　
 　　　　};
 
　　　　	document.onmouseup = function(){
　　　　　　document.onmousemove = null;
　　　　　　document.onmouseup = null;
　　　　};

});
	
getMachineTypeTreeData();
	
		
});


var leaseCompany = localStorage.getItem("leaseCompany");
var projectName = localStorage.getItem("projectName");
$("#leaseCompany").val(leaseCompany);
$("#projectName").val(projectName);

function t(){
	
	$("#typeTree").css("height", "30px");
	$("#typeTree").css("overflow-y",null);
	$("#typeTree>a").text("展开");
	
}

/**
 * 打开或收起类型树
 */
function unfoldOrPickUpTypeTree(that){
	if($("#typeTree").outerHeight(true) < 40){
		$("#typeTree").css("height","400px");
		$("#typeTree").css("overflow-y","auto");
		$(that).text("收起");
	}else{
		$("#typeTree").css("height", "30px");
		$("#typeTree").css("overflow-y",null);
		$(that).text("展开");
		
	}
}

var nodeArray = [];
var preArray = [];
var nodeObj = {};

//定义树节点初始数据
var zNodes1 = [];

//获取数据初始化树
function getMachineTypeTreeData(name) {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/planApplication/getDevTreeList',
		data : {
			name:name
		
		},
		success : function(result) {
			console.log(result);
		 /* 	var nodes = zNodes1.concat(result.obj.list);
			nodes.push({id: "0", pId: null, name: "物资类型", open: true,code:""});
			$.fn.zTree.init($("#machineTypeTree"), setting1, nodes);  */
			 initData(result.obj);
			var nodes = zNodes1.concat(result.obj);
			
			$.fn.zTree.init($("#machineTypeTree"), setting1, nodes);
			var treeObj = $.fn.zTree.init($("#machineTypeTree"), setting1, nodes);
			fillter(treeObj); 
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
	
	
}


function fillter(treeObj) {
	//获得树形图对象
	var nodeList = treeObj.getNodes();//展开第一个根节点
	for(var i = 0; i < nodeList.length; i++) { //设置节点展开第二级节点
		treeObj.expandNode(nodeList[i], false, false, true);
		var nodespan = nodeList[i].children;
		if(nodespan && nodespan.length > 0){
			for(var j = 0; j < nodespan.length; j++) { //设置节点展开第三级节点
				treeObj.expandNode(nodespan[j], false, false, true);
				var nodespans = nodespan[j].children;
				
				if(nodespans!=null){
					for(var k = 0; k < nodespans.length; k++) { //设置节点展开第三级节点
						treeObj.expandNode(nodespans[k], false, false, true);
					}	
				}else{
					
				}
				
			}
		}
	}
}




/**
 * @author zp
 * @date 
 * @function 将数组里面对象装进 id为键 值为数组中对象的 json对象  以便快速定位到该值
 * @returns 
 */
function keyChange(flag){
	if(flag == 1){
		var keyWord = $("#key").val().trim();
		if(!JY.Object.notNull(keyWord)){
			var nodes = zNodes1.concat(JsonToArray(nodeObj));
			$.fn.zTree.init($("#machineTypeTree"), setting1, nodes);
		}
		
	}else{
		
		var keyWord = $("#key").val().trim();
	
		var keyObj = {};
		if(nodeArray!=null){
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
					if(node.level == 3){
						p =nodeObj[node.pId];
						keyObj[p.id] = p;
						pp = nodeObj[p.pId];
						keyObj[pp.id] = pp;
						for (var key in nodeObj){
					    	if(nodeObj[key].pId  == node.id){
					    		keyObj[nodeObj[key].id] = nodeObj[key];
					    	}
					    }
					}else if(node.level == 2){
						p =nodeObj[node.pId];
						keyObj[p.id] = p;
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
					}else if(node.level == 1){
						for (var key in nodeObj){
					    	if(nodeObj[key].pId  == node.id){
					    		keyObj[nodeObj[key].id] = nodeObj[key];
					    		for(var keyy in nodeObj){
					    			if(nodeObj[keyy].pId == nodeObj[key].id){
					    				keyObj[nodeObj[keyy].id] = nodeObj[keyy];
					    				for(var keyyy in nodeObj){
							    			if(nodeObj[keyyy].pId == nodeObj[keyy].id){
							    				keyObj[nodeObj[keyyy].id] = nodeObj[keyyy];
							    			}
							    		}
					    			}
					    		}
					    	}
					    	
					    }
					}		
				}
			}
		}else{
			
		}
		
		
		var nodes = zNodes1.concat(JsonToArray(keyObj));
		$.fn.zTree.init($("#machineTypeTree"), setting1, nodes);
	}
}





/**
 * @author 
 * @date 
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
 * @author zp
 * @date 
 * @function 排除指定id的node
 * @returns 数组对象
 */
function excludeUnitId(arr){
	initData(arr);
	if(excludeArr.indexOf(",") == -1){
		delete nodeObj[excludeArr];
	}else{
		var a = excludeArr.split(",");
		deleteExclode(a);
	}
	nodeArray = JsonToArray(nodeObj,1)
	return nodeArray;
}

/**
 * @author zp
 * @date 
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









//树的设置
var setting1 = {
	view : {
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true
		},
		showTitle:true,
		 key: {
	         title: "name" //设置title提示信息对应的属性名称 也就是节点相关的某个属性 这样设置显示的是属性 title的内容
	     }
	},
	edit : {
		enable : false
	},
	callback : {
		// 不允许拖拽
		onClick : chooseNode,
		beforeDrag : beforeDrag,
	}
};

//不允许拖拽
function beforeDrag(treeId, treeNodes) {
	return false;
}
var oob={};
/**
 * 单击节点事件
 * @returns
 */
function chooseNode(event,treeId,treeNode){
	//console.log(JSON.stringify(treeNode))
	if(!treeNode.isParent ){
		

		oob = treeNode;
		saveMaInfo(oob);
	}

}


function saveMaInfo(oob){
	
	if(JSON.stringify(oob) == JSON.stringify({})){
		var indexMsg = layer.confirm("<h5 style='color:red;'>请选择物资!</h5>", {btn: ['关闭']},function(){
			layer.close(indexMsg);
		});
	}else{
		if(validPartIsSelected(oob)){
			var indexMsg = layer.confirm("<h5 style='color:red;'>已经选择了该物资!</h5>", {btn: ['关闭']},function(){
				layer.close(indexMsg);
			});
		}else{
			invokeSubPageFunction(oob);
		}
		
	}
}






/**
 * @author 
 * @date 2020-04-22
 * @function 条件查询
 * @returns 
 */
function search(){
	$("#search").trigger("click")
}

/**
 * @author 
 * @date 2020-04-24
 * @function 启动执行
 * @returns  
 */
$(function () {

	$("#keyWord").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	p = window.parent;
});

/**
 * @author 
 * @date 2020-04-29
 * @function 提交申请审核结果
 * @returns
 */
function submitApply(data){
	var machines = [];
	var size = $(".pa").size();
	if(size == 0){
		var indexMsg = layer.confirm("<h5 style='color:red'>您还没有选择任何需求,请先选择设备！</h5>", {btn: ['确认']},function(){
			layer.close(indexMsg);})
		return;
	}
	$(".pa").each(function(){
		o = paramConversionToObjOfForm2(this);
		machines.push(o);
	});
	data.field.jsonData = JSON.stringify(machines);
	let loadingMsg = layer.msg('正在提交保存,请稍等...', {
		  icon: 16
		  ,shade: 0.01
		  ,time:'0'
	});
	console.log(JSON.stringify(data.field));
    $.ajax({
        url: bonuspath +'/backstage/planApplication/addPlan',
        type: 'POST',
        data: JSON.stringify(data.field),
        dataType: 'json',
        contentType:"application/json",
        beforeSend: function () {
            $('.save').addClass("layui-btn-disabled").attr("disabled", true);
            $('.cancel').addClass("layui-btn-disabled").attr("disabled", true);
        },
        success: function (result) {
            layer.close(loadingMsg); // 关闭提示层
            if (result.res === 1) {
            	parent.layer.msg(result.msg, { icon: 1 });
                closePage(1);
            } else{
            	var indexMsg = layer.confirm(result.resMsg, {btn: ['关闭']},function(){
					layer.close(indexMsg);
				});
                $('.save').removeClass("layui-btn-disabled").attr("disabled", false);
                $('.cancel').removeClass("layui-btn-disabled").attr("disabled", false);
            } 
        },
        error: function (result) {
            layer.close(loadingMsg); // 关闭提示层
            layer.msg('服务异常，请稍后重试', { icon: 16, scrollbar: false, time: 2000 });
            $('.save').removeClass("layui-btn-disabled").attr("disabled", false);
            $('.cancel').removeClass("layui-btn-disabled").attr("disabled", false);
        }
    });
}

//解析表格数据
function paramConversionToObjOfForm2(that){
	let obj = {
			 'moduleId' : $(that).find('th').eq(0).html(),
			 'type' : $(that).find('th').eq(2).html(),
			 'typeName': $(that).find('th').eq(3).html(),
			 'module' : $(that).find('th').eq(4).html(),
			 'unit' : $(that).find('th').eq(5).html(),
			 'needNum' : $(that).find('th').eq(6).children('input').val(),
			 'times' : $(that).find('th').eq(7).children('input').val(),
			 'remarks' : $(that).find('th').eq(8).children('input').val()
	};
	return obj;
}

/**
 * @author 
 * @date 2020-04-29
 * @function 回调子页面函数
 * @returns
 */
function invokeSubPageFunction(obj){
	$("#list").append(renderData(obj));
	machines[obj.id] = obj;
	/* var id = obj.id;
	var isCount = obj.isCount;
	 
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/machineType/getNumAndPick',
		data : {
			id:id,
			isCount:isCount
		},
		dataType : 'json',
		success : function(data) {
			//alert(JSON.stringify(data))
			
			machines[obj.id] = obj;
			obj.storageNum = data.obj.nums;
			obj.preOutNum = data.obj.allNums;
			
			var size = Object.getOwnPropertyNames(machines).length	
			$("#list").append(renderData(obj,size)); 
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		
	}); */
	
	
}

var user;

/**
 * @author 
 * @date 2020-04-29
 * @function 校验是否已经选择
 * @returns
 */
function validPartIsSelected(obj){
	if(machines[obj.id]){
		return true;
	}
	return false;
}

/**
 * @author 
 * @date 2020-04-29
 * @function 渲染数据
 * @returns
 */
function renderData(obj){
	console.info(obj)
	var index = $('.pa').size() + 1;
		var html = "";	
		html += "<tr class='pa'>";
		html += "<th  class='center' style='display:none'>"+obj.id+"</th>";
		html += "<th  class='center'>"+index+"</th>";
		html += 	"<th  class='center'>"+setNullValue(obj.ppName)+"</th>";
		html += 	"<th  class='center'>"+setNullValue(obj.pName)+"</th>";
		html += 	"<th  class='center'>"+setNullValue(obj.name)+"</th>";
		html += 	"<th  class='center'>"+setNullValue(obj.unit)+"</th>";
		html += 	"<th  class='center'><input style='width:90%;height:2.5em;border: 1px solid #d5d5d5 !important;padding: 0 5px 0 5px;' onblur='validNum(this,1)' maxLength='8' value='1'></th>";
		html += 	"<th  class='center'><input style='width:90%;height:2.5em;border: 1px solid #d5d5d5 !important;padding: 0 5px 0 5px;'onblur='validNum(this,2)' maxLength='4' value='1'></th>";
		/* if(JY.Object.notNull(obj.unitName)){
			html += "<th  class='center'>"+obj.unitName+"</th>";
		}else{
			html += "<th  class='center'>暂无</th>";
		} */
		/* if(JY.Object.notNull(obj.keepName)){
			html += "<th  class='center'>"+obj.keepName+"</th>";
		}else{
			html += "<th  class='center'>暂无</th>";
		} */
		html += 	"<th  class='center'><input onblur='checkText(this)' maxLength='255'  filed='remarks,val' type='text' style='width:100%;height:2.5em;'></th>";
		html += "<th  class='center'><a style='color:#4491d3;cursor: pointer;' onclick='delPartData("+obj.id+",this)'>删除</a></th></tr>";
		
	return html;
}

// 设置空值
function setNullValue(value){
	if(!value){
		return '/';
	}
	return value;
}

/**
 * @author 
 * @date 2019-06-19
 * @function 
 * @returns  
 */
function validNum(that,type){
	var num = $(that).val();
	if(type === 1){ // 需用量 最少为1 最大不能超过十位数
		const regex = /^(?!0\d)\d{1,8}$/;
		if(!regex.test(num)){
			var indexMsg = layer.confirm("<h5 style='color:red'>需用量最少为1，最大输入6位数，且为正整数！</h5>", {btn: ['确认']},function(){
				layer.close(indexMsg);})
			$(that).val(1);
		}
	}else if(type === 2){ // 需用天数
		const regex = /^(?!0\d)\d{1,4}$/;
		if(!regex.test(num)){
			var indexMsg = layer.confirm("<h5 style='color:red'>需用天数最少为1，最大输入4位数，且为正整数！</h5>", {btn: ['确认']},function(){
				layer.close(indexMsg);})
			$(that).val(1);
		}
	}
}

/**
 * @author 
 * @date 2019-08-27
 * @function 
 * @returns  
 */
function delPartData(id,that){
	var indexMsg = layer.confirm("<h5 style='color:red'>您确定删除此条数据吗?</h5>", {btn: ['确定','取消']},function(){
		layer.close(indexMsg);
		$(that).parent().parent().remove();
		delete machines[id];
		if($('.pa').size() > 0){
			$("#list .pa").each(function(index,item){
				console.log(index);
				 $(this).find('th').eq(1).html(index + 1)
			});
		}
	});
}

// 关闭页面
function closePage(type) {
    let index = parent.layer.getFrameIndex(window.name); //先得到当前 iframe层的索引
    if (type == 1) {
    	window.parent.reloadData();
    }
    parent.layer.close(index); //再执行关闭
}


</script>
</body>
</html>