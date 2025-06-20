<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%@include file="../webPortalCommonSet.jsp" %>
<link href="${bonuspath}/static/plugins/select2/css/select2.min.css" rel="stylesheet">
<script  src="${bonuspath}/static/plugins/select2/js/select2.min.js"></script>
<script  src="${bonuspath}/static/plugins/select2/js/i18n/zh-CN.js"></script>
<title></title>

<style type="text/css">
	#baseInfo{
		text-align: center;
		width:96%;
		margin:0 2% 0 2%;
	}
	#baseInfo tr{
		height: 2.5em;
	}
	#baseInfo tr>input{
		width:96%;
	}
	.inp{
		width:80%;
	}
	.a{
		text-align: left;
	}
</style>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
			<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
				<div class="widget-header widget-header-large" style="padding:10px;">
					<div class="widget-main customBtn" >	
						<!-- <span>字典类型名称：</span> -->
						 <input id ="picId" type="hidden"  value=""/>
							<span>关键词:</span>
						<input id="keyWord" type="text" name="keyWord" placeholder="请输入关键词..." />
							&nbsp;&nbsp;物资名称:			    
						    <select id="parentId" name="parentId" onchange="loadINameByFname(this)" style="width: 12%"   class="js-example-basic-single form-control inp  ">
		                       	<option value="">--请选择物资名称--</option>
		                        <c:forEach items="${machineType}" var="machineType">
		                        	<option value="${machineType.id}" >${machineType.name}</option>
		                        </c:forEach>
		                   	</select>
		                   	&nbsp;&nbsp;规格型号：
						    <select id="typeId" name="typeId"    style="width: 12%"  disabled="disabled" class="js-example-basic-single form-control inp  ">
		                       	<option value="">--请先选择物资名称--</option>
		                   	</select>
		                   	 	&nbsp;&nbsp;筛选是否选中：
						    <select id="isCheck" name="isCheck"    style="width: 8%"  class=" inp  ">
		                       	<option value="0">--未勾选--</option>
		                       	<option value="1">--已勾选--</option>
		                   	</select>
					<button class="btn btn-success btn-sm" onclick="getbaseList(1)" id="search" title="查询"  style="width: 6%"  type="button">查询</button>
					<button class="btn btn-gray btn-sm" onclick="clearS()" id="search" title="清空"  style="width: 6%"  type="button">清空</button>
					<!-- <button class="btn btn-danger btn-sm" onclick="uploadPic()" id="search" title="上传"  style="width: 6%"  type="button">上传</button>
					 --><br>
					<span>备注:</span>	<input   class="clear "  id="remarks"  name="remarks"  type="text"  value="">
					</div>
				</div>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:5%" class="center">
								<button id="all_click_btn" class="btn btn-success btn-sm">全选</button>
							</th>
							<th style="width:5%" class='center'>序号</th>
							<th style="width:5%" class='center'>物资名称</th>
							<th style="width:10%" class='center'>规格型号</th>
							<th style="width:5%" class='center'>购置价格</th>
							<th style="width:5%" class='center'>固定资产</th>
							<th style="width:5%" class='center'>固定资产编号</th>
							<th style="width:5%" class='center'>原编码</th>
							<th style="width:5%" class='center'>设备编码</th>
							<th style="width:10%" class="center">设备状态</th>
							<th style="width:5%" class="center">报废数</th>
							<th style="width:5%" class="center">资产属性</th>
							<th style="width:10%" class="center">备注</th>
							<th style="width:10%" class="center">编码备注</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

<script type="text/javascript">

var initBatchObj = {};
var initBatchObj2 = {};
var initRamObj = {};
var initRamObj2 = {};

function uploadPic(){
	var id = "20210531";
	 $("#picId").val(id);
	//var id=$("#userId").find("option:selected").val();
	var index = layer.open({
		  type: 2,
		  title: '图片上传',
		  shade: [0],
		  btn: ['关闭'], //按钮组
		  area: ['600px','300px'],
		  anim: 2,
		  yes: function(index,layero){//layer.msg('yes');    //点击确定回调
			  	var obj = $(layero).find("iframe")[0].contentWindow;
		       // alert(obj)
		        var imgId =  localStorage.getItem("imgId");
		        //alert(imgId)
		       
			    layer.close(index);
		  },
		  content: [bonuspath +'/backstage/fileUpload/toImgsUpload?type.id=76&folderName=storageScrap&modelFlag=storageScrap&ownerId='+id]
	});
}



function clearS(){
	$("#typeParentId").val("").select2();
	$("#parentId").val("").select2();
	$("#typeId").val("").select2();
	$("#keyWord").val("");
	$("#remarks").val("");
	$("#isCheck").val(0);
	
	
}
/**
 * 
 * 初始化集合
 * 
 */
var checkedSet = []


// 为全选按钮添加点击事件监听器
$('#all_click_btn').on('click', function() {
	let allChecked = true;
	// 检查所有复选框是否都被选中
	$('.cb:checkbox').each(function() {
		if (!$(this).is(':checked')) {
			allChecked = false;
			return false; // 退出循环
		}
	});

	// 如果所有复选框都被选中，则取消所有复选框的选择
	if (allChecked) {
		$('.cb:checkbox').prop('checked', false);
	} else {
		// 否则，选中所有复选框
		$('.cb:checkbox').prop('checked', true);
	}

	// 更新 checkedSet 数组
	updateCheckedSet();
});

// 更新 checkedSet 数组的函数
function updateCheckedSet() {
	checkedSet = [];
	$('.cb:checkbox:checked').each(function() {
		var id = $(this).val();
		addCheckSet(id);
	});
}


function onClickHander(obj){
	 var id = $(obj).val();
	
	//alert(id)
    if(obj.checked){
    	addCheckSet(id);
        console.log("selected");
    }else{
    	removeCheckSet(id);
        console.log("unselected");
    }
    
}

var arrIds = [];
/**
 * 将勾选的元素添加到集合
 * 
 */
 function addCheckSet(id){
	arrIds.push(id)
    checkedSet = arrIds   ;
	console.log("添加后集合:",checkedSet)
}
 function isInArray(arr,value){
	    for(var i = 0; i < arr.length; i++){
	        if(value === arr[i]){
	            return true;
	        }
	    }
	    return false;

	}
 
 /**
  * 将集合中取消勾选的元素删除
  * 
  */
 function removeCheckSet(id){
	 
	if(isInArray(arrIds,id)){
		removeArray(arrIds, id)
	}
	console.log("删除后集合:",checkedSet)
 }
 
 function removeArray(arr, val) {
	   for(var i = 0; i < arr.length; i++) {
	    if(arr[i] == val) {
	     arr.splice(i, 1);
	     break;
	    }
	   }
	  }




//根据名称查规格（搜索）
function loadINameByFname(that){
	var mtId = $(that).val();
	if(JY.Object.notNull(mtId)){
		//$("#addMachineBtn").removeAttr("hidden");
		var idx = layer.msg('正在加载,请稍等...', {
			  icon: 16
			  ,shade: 0.01
			  ,time:'-1'
		});

		$.ajax({        
	        type:"POST",  
	        url:bonuspath +'/backstage/apply/fandNameByFIdSeletc', 
	        data: {id:mtId},
	        dataType:"json",
	        success:function(data){
	        	var mt = data.obj;
	        	if(mt != null && mt.length > 0){
	        		$("#typeId").removeAttr("disabled");
	        		
	        		var length = mt.length;
	        		var html = "<option value=''>--请选规格--</option>";
	        		for(var i = 0; i < length; i++){
	        			var o = mt[i];
	        			html += "<option value='"+o.id+"'>"+o.name+"</option>"
	        		}
	        		$("#typeId").html(html);
	        	}else{
	        		$("#typeId").html("<option value=''>--暂无规格,请先新增--</option>");
	        		$("#typeId").attr("disabled","disabled");
	        		
	        	}
	        	layer.close(idx);
			},
			error:function(data){
				layer.msg("数据加载失败!",{icon:2,time:2000})
				layer.close(idx);
			}
		})
	}else{
		$("#typeId").html("<option value=''>--请先名称--</option>");
		$("#typeId").attr("disabled","disabled");
	}
}

$(function () {
	$.fn.modal.Constructor.prototype.enforceFocus = function(){};
	$(".js-example-basic-single").select2({
        "language": "zh-CN"
    });
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	//getbaseList(1);
});

function search(){
	$("#search").trigger("click")
}

function getbaseList(init){
	data = {};
	
	//data = getObjParam("baseForm");
	var parentId = $("select[id=parentId]").val();
	var typeId = $("select[id=typeId]").val();
	var isCheck = $("select[id=isCheck]").val();
	var keyWord =$("#keyWord").val();
	data.checkedSet = checkedSet;
	data.parentId = parentId;
	data.typeId = typeId;
	data.keyWord = keyWord;
	data.isCheck = isCheck;
	console.info("data:",data)
	var idx = layer.msg('正在加载数据,请稍等...', {
		  icon: 16
		  ,shade: 0.01
		  ,time:'-1'
	});
	
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/apply/findMaCodeList', 
        data: JSON.stringify(data),      
        dataType:"html",
        contentType:"application/json",
        success:function(data){
        	$("#baseTable tbody").html(data);
        	layer.close(idx);
		}
	})
}



//盘点报废保存
function saveInventoryScrap(){
		var sfb = [];
		var o={};
		
		if(JY.Validate.newForm("baseTable")){
			var remarks = $("#remarks").val();
			//alert(picId)
			var obj = paramConversionToObjOfForm("baseTable");
			var size = $(".cb:checkbox:checked").size();
			if(size == 0){
				showMsg("您还没有选择任何需求,请先选择物资") ;
				return;
			}
			
			$(".cb:checkbox:checked").each(function(){
			var that = $(this).parent().parent().parent(); 
				o = paramConversionToObjOfForm2(that);
				sfb.push(o);
			});
			obj.sfb = sfb;
			obj.applyRemark = remarks;
			
			var idx = layer.msg('正在提交保存,请稍等...', {
				  icon: 16
				  ,shade: 0.01
				  ,time:'-1'
			});
			
			
			console.log(JSON.stringify(obj));
		   	$.ajax({        
		        type:"POST",  
		        url:bonuspath +'/backstage/apply/saveInventoryScrap', 
		        data: JSON.stringify(obj),
		        contentType:"application/json",
		        dataType:"json",
		        success:function(data){
		        	//console.log(JSON.stringify(data))
		        	 layer.close(idx);
			        	if(data.res == 1){
							var i = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(i); 
							parent.showMsgAndReload(data.resMsg);
						}else{
							var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
								layer.close(indexMsg);
							});
						}
				},
				error:function(data){
					console.log(JSON.stringify(data))
					layer.msg("数据加载失败!",{icon:2,time:2000})
					layer.close(idx);
				}
			})    
		}
	} 



/**
 * @author 
 * @date 
 * @function 启动执行
 * @returns  
 */
$(function () {
	$.fn.modal.Constructor.prototype.enforceFocus = function(){};
	$(".js-example-basic-single").select2({
        "language": "zh-CN"
    });
    
	
});

function vilad(storageNum,that){
	var scrapNum=$(that).val();

	if(storageNum<scrapNum){
		$(that).val(storageNum);
		showMsg("报废数量不能超过库存数量！") ;
		
	}
	
}






</script>

</body>
</html>