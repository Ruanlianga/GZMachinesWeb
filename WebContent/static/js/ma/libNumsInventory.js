var count = 1;
var clickState = 0; //初始化点击状态
$(function() {
	document.getElementById('maModelName').value = "";
	document.getElementById('maModelId').value = "";
	getbaseList(1);
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
//	$('#export').on('click',function(e) {
//		e.preventDefault();
//		$("#baseForm").attr("action",bonuspath +'/backstage/maLease/export');
//		$("#baseForm").attr("target","downloadFrame");//iframe的名字
//		$("#baseForm").submit();
//	});
	$('#addBtn').on('click',function(e){
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
			var maModelName = $('#maModelId').val();
				var that = $(this);
				if(maModelName == '' || maModelName == 0){
					JY.Model.info("请选择设备！", function() {
					});
				}else{
            	JY.Ajax.doRequest(null, bonuspath
						+ '/backstage/inventory/add', {maModelName:maModelName}, function(data) {
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
					
				}
	});
	
});

function getbaseList(init) {
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/inventory/findByPage',null,
		function(data) {
			$("#baseTable tbody").empty();
			var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
			var html = "";
			if (results != null && results.length > 0) {
				var leng = (pageNum - 1) * pageSize;
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center hidden-480'>"
							+ (i + leng + 1) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maTypeName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModelName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unit) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
					var nu =l.unit;
					var num;
					if(c[nu] == undefined){
						num =parseInt(l.num);
					}else{
						num = parseFloat(l.num).toFixed(3);
					}
					if(isNaN(num)){
						num = 0;
					}
					html += "<td style='vertical-align:middle;' class='center'>"+ num + "</td>";
//					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.nums) + "</td>";
					if(l.isProfit == 1){
						html += "<td style='vertical-align:middle;' class='center'>盘盈</td>";
					}else if(l.isProfit == 0){
						html += "<td style='vertical-align:middle;' class='center'>盘亏</td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}
					html += "<td style='vertical-align:middle;' class='center'><input value='"+ l.inNums +"' id='inNums"+l.id+"' onblur='inputOnBlur("+l.id+");' type='text' style='width:90px;' /></td>";
					if(l.remark != null){
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'><input id='remark"+l.id+"' type='text' style='width:90px;' /></td>";
					}
					
					html += rowFunction(l.id,l.maModelId);
					html += "</tr>";
				}
				$("#baseTable tbody").append(html);
				JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
			} else {
				html += "<tr><td colspan='12' class='center'>没有相关数据</td></tr>";
				$("#baseTable tbody").append(html);
				$("#pageing ul").empty();// 清空分页
			}
			JY.Model.loadingClose();
		}
	);
}

function rowFunction(id,maModelId) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='盘盈' onclick='addLibs(&apos;" + id + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-plus color-p bigger-140'></i></a>";
	h += "<a href='#' title='盘亏' onclick='delLibs(&apos;" + id + "&apos;,&apos;" + maModelId + "&apos;)' class='aBtnNoTD' ><i class='icon-minus color-p bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='delTask(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign color-p bigger-140'></i></a>";
	h += "</div>";
	h += "</td>";
	return h;
}

function addLibs(id,maModelId){
	if( clickState == 1){
		JY.Model.confirm("请勿重复操作，避免数据错乱！");
    }else{
    	
    	JY.Model.confirm("你确认提交吗?", function() {
    		clickState = 1;
        	var inNumsName = "inNums" + id;
        	var remarkName = "remark" + id;
        	var remark = $('#'+remarkName).val();
        	var inNums = $('#'+inNumsName).val();
        	JY.Ajax.doRequest(null, bonuspath + '/backstage/inventory/addLibs', 
        			{id:id,
        		     isProfit : 1,
        		     remark : remark,
        		     inNums:inNums,
        		      maModelId : maModelId
        		      }, 
        			function(data) {
        		JY.Model.info('盘点完成', function() {
        			search();
        			addLibsRecord(remark,maModelId,inNums);
        		});
        	},function(data){
        		JY.Model.info('盘点失败', function() {
        			search();
        		});
        	});
		});
    	
    	
    }
}
//盘盈insert 
function addLibsRecord( remark,modelId,inNums) {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/inventoryRecord/addLibsRecord', {
	 		inventoryType:'盘盈',
			remark:remark,
			modelId:modelId,
			inventoryNum:inNums
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		}); 
}

function delLibs(id,maModelId,inNums){
	if( clickState == 1){
		JY.Model.confirm("请勿重复操作，避免数据错乱！");
    }else{
    	JY.Model.confirm("你确认提交吗?", function() {
    		clickState = 1;
        	var inNumsName = "inNums" + id;
        	var remarkName = "remark" + id;
        	var remark = $('#'+remarkName).val();
        	var inNums = $('#'+inNumsName).val();
        	JY.Ajax.doRequest(null, bonuspath + '/backstage/inventory/minusLibs', 
        			{id:id,isProfit : 0,remark : remark,inNums:inNums,maModelId : maModelId}, function(data) {
        		JY.Model.info('盘点完成', function() {
        			search();
        			reduceLibsRecord(remark,maModelId,inNums);
        		});
        	},function(data){
        		JY.Model.info('盘点失败', function() {
        			search();
        		});
        	});
    	});
   	 
    
    }
	
}

//盘亏insert
function reduceLibsRecord( remark,modelId,inNums) {
 	JY.Ajax.doRequest(null, bonuspath + '/backstage/inventoryRecord/reduceLibsRecord', {
 		inventoryType:'盘亏',
		remark:remark,
		modelId:modelId,
		inventoryNum:inNums
	}, function(data) {
		JY.Model.info(data.resMsg, function() {
			search();
		});
	}); 
}

function inputOnBlur(id){
	var inNumsName = "inNums" + id;
	var inNums = $('#'+inNumsName).val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/inventory/update', 
			{id:id,inNums:inNums}, function(data) {
//		JY.Model.info('增加设备数量完成', function() {
			search();
//		});
	});
}

function delTask(id){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/inventory/find',{id:id},function(data){
	var res = data.obj;
	var isProfit = res.isProfit;
	if(isProfit != null){
		JY.Model.confirm("权限不够，无法删除！");
	}else{
		JY.Model.confirm("确认删除吗？",function(){	
			JY.Ajax.doRequest(null,bonuspath +'/backstage/inventory/del',{id:id},function(data){
				JY.Model.info(data.resMsg,function(){search();});
			});
		});
	}
	});
}

function search() {
	document.getElementById('maModelName').value = "";
	document.getElementById('maModelId').value = "";
	getbaseList(1);
	clickState = 0;
}

function setMaTypeForm(){
	var maTypeId = localStorage.getItem("maTypeId");
	var maTypeName = localStorage.getItem("maTypeName");
	$("#maTypeId").val(maTypeId);
	$("#maTypeName").val(maTypeName);
	$("#maModelId").val(0);
	$("#maModelName").val("");
}

function setMaModelForm(){
	var maModelId = localStorage.getItem("maModelId");
	var maModelName = localStorage.getItem("maModelName");
	$("#maModelId").val(maModelId);
	$("#maModelName").val(maModelName);
}

function maTypeTree(){
	localStorage.setItem("maTypeId","");
	localStorage.setItem("maTypeName","");
	localStorage.setItem("maTypeTreeName",$("#maTypeName").val());
	layer.open({
		type: 2,
		title:['物资名称','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/machineType/maTypeTree'
	});
}

function maModelTree(){
	var maTypeId = $("#maTypeId").val();
//	if(maTypeId == "0"){
//		JY.Model.info("请选择物资名称！");
//	}else{
		localStorage.setItem("maModelId","");
		localStorage.setItem("maModelName","");
		localStorage.setItem("maModelTreeName",$("#maModelName").val());
		localStorage.setItem("maTypeTreeId",maTypeId);
		layer.open({
			type: 2,
			title:['规格型号','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['400px', '400px'],
			content: bonuspath+'/backstage/machineType/maModelTree'
		});
//	}
}

