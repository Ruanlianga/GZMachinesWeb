var init = 1;
var keyWord = "";
var type0 = "";
var type = "";
var startTime = "";
var endTime = "";
var batchStatus = "0";
var nameList=new Array();
$(function() {

	getbaseList();
	getNewDataTable(null);
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});

	
	$('#resetBtn').on('click', function(e) {
		$("#keyWord").val("");
		$("#type0").val("");
		$("#type").val("");
		$("#batchStatus").val("0");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#batchStatus option[value='0']").attr("selected","true");
		init = 1;
		getbaseList(1);
	//	getNewDataTable(null);
		//results = new Array();
	});
//	getSelectType(3,null);
});


function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/machine/export');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}


function emptyRole() {
	$("#orgName").prop("value", "");
	$("#auForm input[name$='orgId']").prop("value", "0");
}

var preisShow = false;// 窗口是否显示
function showRole() {
	if (preisShow) {
		hideRole();
	} else {
		var obj = $("#orgName");
		var offpos = $("#orgName").position();
		$("#orgContent").css({
			left : offpos.left + "px",
			top : offpos.top + obj.heith + "px"
		}).slideDown("fast");
		preisShow = true;
	}
}
var hideRole = function() {
	$("#orgContent").fadeOut("fast");
	preisShow = false;
}
function clickRole(e, treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (check) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree"), nodes = zTree
				.getSelectedNodes(), v = "", n = "", o = "", p = "";
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";// 获取name值
			n += nodes[i].id + ",";// 获取id值
			o += nodes[i].other + ",";// 获取自定义值
			var pathNodes = nodes[i].getPath();
			for (var y = 0; y < pathNodes.length; y++) {
				p += pathNodes[y].name + "/";// 获取path/name值
			}
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (n.length > 0)
			n = n.substring(0, n.length - 1);
		if (o.length > 0)
			o = o.substring(0, o.length - 1);
		if (p.length > 0)
			p = p.substring(0, p.length - 1);
		$("#orgName").val(p);
		$("#auForm input[name$='orgId']").prop("value", n);
		hideRole();
	}
}

function getbaseList(index) {
	keyWord = $("#keyWord").val();
	typeName = $("#type0").val();
	type = $("type").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	batchStatus = $("#batchStatus").val();
	init = $(".pageNum").val();
	$(".pageNum").val(init);
	if (index == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/disassembly/findByPage',null,
		function(data) {
			$("#baseTable tbody").empty();
			var msg = data.resMsg;
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
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.type) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
					if(l.batchStatus == '' || l.batchStatus == null){
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}else if(l.batchStatus == 1 || l.batchStatus =='1'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-default'>待通知</span></td>";
					}else if(l.batchStatus == 2 || l.batchStatus =='2'){
						html += "<td style='vertical-align:middle;' class='center'></td>";
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-primary'>待采购检验</span></td>";
					}else if(l.batchStatus == 3 || l.batchStatus =='3'){
//						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick='toQRCode(&apos;"+ l.id +"&apos;);'>打印</a></td>";
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待打印</span></td>";
					}else if(l.batchStatus == 4 || l.batchStatus =='4'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待入库</span></td>";
					}else if(l.batchStatus == 5 || l.batchStatus =='5'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>在库</span></td>";
					}else if(l.batchStatus == 6 || l.batchStatus =='6'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>在用</span></td>";	
					}else if(l.batchStatus == 7 || l.batchStatus =='7'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在修</span></td>";
					}else if(l.batchStatus == 8 || l.batchStatus =='8'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>已检验</span></td>";
					}else if(l.batchStatus == 9 || l.batchStatus =='9'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>修试后待入库</span></td>";
					}else if(l.batchStatus == 10 || l.batchStatus =='10'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待报废</span></td>";	
					}else if(l.batchStatus == 11 || l.batchStatus =='11'){
						if(l.isScrap == 1 || l.isScrap == '1'){
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>已报废</span></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>待报废入库</span></td>";
						}
					}else if(l.batchStatus == 12 || l.batchStatus =='12'){
						if(l.isScrap == 1 || l.isScrap =='1'){
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已封存</span></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待封存入库</span></td>";
						}
					}else if(l.batchStatus == 13 || l.batchStatus =='13'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在检</span></td>";
					}else if(l.batchStatus == 14 || l.batchStatus =='14'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>出库待审</span></td>";
					}else if(l.batchStatus == 16 || l.batchStatus =='16'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待批准</span></td>";
					}else if(l.batchStatus == 17 || l.batchStatus =='17'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>退料检验</span></td>";
					}else if(l.batchStatus == 18 || l.batchStatus =='18'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>入库待审</span></td>";
					}else if(l.batchStatus == 18 || l.batchStatus =='18'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>入库待审</span></td>";
					}else if(l.batchStatus == 19 || l.batchStatus =='19'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>维修合格</span></td>";
					}else if(l.batchStatus == 20 || l.batchStatus =='20'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已拆分</span></td>";
					}else if(l.batchStatus == 21 || l.batchStatus =='21'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已组装</span></td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}
					if(l.batchStatus == 5 || l.batchStatus =='5'){
					html+= "<td style='vertical-align:middle;' class='center'>";
					html += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
					html += "<a href='#' title='添加' onclick='addNewTable(&apos;" + l.id + "&apos;,&apos;" + 2 + "&apos;)' class='aBtnNoTD' ><i class='icon-plus color-p bigger-140'></i></a>";
					html += "</div>";
					html += "</td>";
					}else{
						html+= "<td style='vertical-align:middle;' class='center'>";
						html += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
						html += "</div>";
						html += "</td>";
					}
					//操作按钮
					html += "</tr>";
				}
				$("#baseTable tbody").append(html);
				JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
			} else {
				html += "<tr><td colspan='14' class='center'>没有相关数据</td></tr>";
				$("#baseTable tbody").append(html);
				$("#pageing ul").empty();// 清空分页
			}
			JY.Model.loadingClose();
		});
}
var i=1;
var idd='';
var results = new Array();
function addNewTable(id,dd){
	JY.Ajax.doRequest(null, bonuspath + '/disassembly/getListDataById', {
		id:id,
		
	}, function(data) {
		 var da=data.obj;
		 var id=da.id+","; 
		 idd=idd+","+id+",";
		 results.push(da);
		 var len=results.length-1;
		 results[len].ids=i;
		 results[len].typeName=null;
		 results[len].typeNameId=null;
		 results[len].typeId=null;
		 results[len].type=null;
		 results[len].type=null;
		 results[len].originNum=null;
		 i=i+1;
		 //第二次添加
		 console.log(results);
		 getNewDataTable(results);
	});
	
}


//添加表数据信息
function getNewDataTable(results){
	$("#newBaseTable tbody").empty();
	var html = "";
	if (results != null && results.length > 0) {
		
		for (var i = 0; i < results.length; i++) {
			var l = results[i];
			html += "<tr>";
			html += "<td style='vertical-align:middle;' class='center hidden-480'>"
					+ (i + 1) + "</td>";
		//
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
			var typeName= l.typeName;
			var typeNameId=l.typeNameId;
			if(typeName==null){
				typeName='';
			}
			if(typeNameId==null){
				typeNameId='';
			}
			html += "<td style='vertical-align:middle;' class='center'>" +
					"<input value='"+ typeName +"'  id='typeName"+l.ids+"' readonly='readonly' onclick='typeNameChange(&apos;"+l.ids+"&apos;)'  type='text' style='width:90px;' />"
					+"<input   id='typeNameId"+l.ids+"' value='"+ typeNameId +"'  style='display:none'   type='text' style='width:90px;' />"
					+"</td>";
			var type= l.type;
			if(type==null){
				type='';
			}
			var typeId= l.typeId;
			if(typeId==null){
				typeId='';
			}
			html += "<td style='vertical-align:middle;' class='center'>" +
					"<input  value='"+ type +"'  id='type"+l.ids+"' readonly='readonly' onclick='typeChange(&apos;"+l.ids+"&apos;)'  type='text' style='width:90px;' />" +
					"<input  value='"+ typeId +"'  id='typeId"+l.ids+"' style='display:none' readonly='readonly'   type='text' style='width:90px;' />" +
							
					"</td>";

	
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
			var originNum= l.originNum;
			if(originNum==null){
				originNum='';
			}
			html += "<td style='vertical-align:middle;' class='center'><input value='"+ originNum +"'  id='inNums"+l.ids+"' onblur='inputOnBlur(&apos;"+l.ids+"&apos;);' type='text' style='width:90px;' /></td>";
	
			html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>在库</span></td>";
			
			
			html+= "<td style='vertical-align:middle;' class='center'>";
			html += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
			html += "<a href='#' title='删除' onclick='deleteData("+l.ids+")' class='aBtnNoTD' ><i class='icon-remove-sign color-p bigger-140'></i></a>";
			html += "</div>";
			html += "</td>";
			html += "</tr>";
		}
		$("#newBaseTable tbody").append(html);
	} else {
		html += "<tr><td colspan='14' class='center'>没有相关数据</td></tr>";
		$("#newBaseTable tbody").append(html);
	}
	
}
function deleteData(ids){
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var idd=l.ids;
		if(ids==idd){
			results.splice(i,1);
			break;
		}
	}
	 getNewDataTable(results);
}
//shuju
function inputOnBlur(ids){
	var  typeName1=$("#typeName"+ids).val();
	var type1=$("#type"+ids).val();
	 if(typeName1==null || typeName1=='' ||type1==null || type1=='' ){
		 $("#inNums"+ids).val('');
		 JY.Model.info("请先选择机具设备及规格");
		return false;
	 }
	var deviceCode=$("#inNums"+ids).val();
	var b=false;
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var typeName=l.typeName;
		var type=l.type;
		var idd=l.ids;
		var originNum=l.originNum;
		
		if(ids==idd){
			//设备编码校验  唯一性
			$.ajax({
				url: bonuspath + '/disassembly/getListDataByDeviceCode',
				data:{
					type : type,
					deviceCode:deviceCode,
					typeName:typeName
				},
				type:'post',
				dataType:'json',
				async:false,
				success:function(data){
					if(data.obj==1){
						 $("#inNums"+ids).val('');
						b=true;
					}else{
						l.originNum=deviceCode;//新数据赋值
					}
				}
			})
		}else{
			if(typeName1==typeName && type==type1 && ids!=idd && originNum==deviceCode){
				 $("#inNums"+ids).val('');
				b=true;
			}
		}
		if(b){
			break;
		}
		
	}
	
	if(b){
		JY.Model.info(deviceCode+"设备编码重复!");
		return;
	}
}
//数据校验
function saveNewData(){
	
	var isreturn=false;
	if(results.length<1){
		return;
	}
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var originNum=l.originNum;
		if(originNum=='' || originNum==null){
			isreturn=true;
			
			break;
		}	
	}
	if(isreturn){
		JY.Model.info("请填写设备编码或删除无用数据");
		return;
	}
	//数据校验重复性
	var isall=false;
	var originNum;
	var newDeviceCode;
	var  isFalse=false;
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var typeName=l.typeName;
		var type=l.type;
		var idd=l.ids;
		originNum=l.originNum;
		$.ajax({
			url: bonuspath + '/disassembly/getListDataByDeviceCode',
			data:{
				type : type,
				deviceCode:originNum,
				typeName:typeName
			},
			type:'post',
			dataType:'json',
			async:false,
			success:function(data){
				if(data.obj==1){
					newDeviceCode=originNum;
					isall=true;
				}
			}
		})
		if(isall){
			break;
		}
		
		for (var j = 0; j < results.length; j++) {
			var ll = results[j];
			var typeName1=ll.typeName;
			var  deviceCode=ll.originNum;
			newDeviceCode=originNum;
			var type1=ll.type;
			var ids=ll.ids;
			if(typeName1==typeName && type==type1 && ids!=idd && originNum==deviceCode){
				newDeviceCode=deviceCode;
				isFalse=true;
				
				break;
			}
		}
	}
	if(isFalse){
		JY.Model.info(newDeviceCode+"设备编码重复!");
		return ;
	}
	if(isall){
		JY.Model.info(newDeviceCode+"设备编码重复!");
		return ;
	}
	var array=new Array();
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var id=l.id;
		var originNum=l.originNum;
		var typeId=l.typeId;
		var  ids=id+"_"+originNum+"_"+typeId;
		
		array.push(ids);
	}
	var da=array.join("@");
	JY.Model.loading();
	//数据后台处理
	JY.Ajax.doRequest(null, bonuspath + '/disassembly/insertDatasplit', {
		id : da,
	}, function(data) {
		JY.Model.info(data.obj);
		getbaseList();
		getNewDataTable(null);
		results = new Array();
		JY.Model.loadingClose();
	});
	
}
//树
var isOpen=false;
var isOpen2=false;
var isOpen3=false;
//规格
function typeChange(ids){
		if(isOpen){
			return false;
		}
		localStorage.setItem("disIds",ids);
		var maTypeId = $("#typeNameId"+ids).val();
		if(maTypeId=='' || maTypeId==null ){
			JY.Model.info("请选择机具类型！");
		}else{
			localStorage.setItem("maModelId","");
			localStorage.setItem("maModelName","");
			localStorage.setItem("maModelTreeName",$("#maModelName").val());
			localStorage.setItem("maTypeTreeId",maTypeId);
			isOpen=true;
			layer.open({
				type: 2,
				title:['规格型号','background-color: #438EB9;color:#fff'],
				shadeClose:true,
				shade:false,
				maxmin: true,
				area: ['400px', '400px'],
				content: bonuspath+'/backstage/machineType/maModelTree2',
				cancel: function(){
					// 右上角关闭事件的逻辑
					isOpen=false;
				}
			});
		}
}
//记录查询
function history(){
	if(isOpen3){
		return false;
	}
	localStorage.setItem("flowType","1");
	layer.open({
		type: 2,
		title:['拆分记录','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['1200px', '750px'],
		content: bonuspath+'/disassembly/historyFlow',
		cancel: function(){
			// 右上角关闭事件的逻辑
			isOpen3=false;
		}
	});
	
}

//名称
function typeNameChange(ids){
	if(isOpen2){
		return false;
	}
	localStorage.setItem("disIds",ids);
		localStorage.setItem("maTypeId","");
		localStorage.setItem("maTypeName","");
		isOpen2=true;
		layer.open({
			type: 2,
			title:['机具类型','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['400px', '400px'],
			content: bonuspath+'/backstage/machineType/maTypeTree2',
			cancel: function(){
				// 右上角关闭事件的逻辑
				isOpen2=false;
			}

		});
}
//机具名称
function setMaTypeForm(id,name){
	var ids=localStorage.getItem("disIds");
	$("#typeName"+ids).val(name);
	$("#typeNameId"+ids).val(id);
	$("#type"+ids).val('');
	$("#typeId"+ids).val('');
	isOpen2=false;
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var idd=l.ids;
		if(ids==idd){
			l.typeName=name;//新数据赋值
			l.typeNameId=id;
			l.type=null;//新数据赋值
			l.typeId=null;
		}
	}
}
//规格
function setMaModelForm(id,name){
	var ids=localStorage.getItem("disIds");
	$("#type"+ids).val(name);
	$("#typeId"+ids).val(id);
	isOpen=false;
	for (var i = 0; i < results.length; i++) {
		var l = results[i];
		var idd=l.ids;
		if(ids==idd){
			l.type=name;//新数据赋值
			l.typeId=id;
		}
	}
}








