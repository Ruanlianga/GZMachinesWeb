var init = 1;
var keyWord = "";
var type0 = "";
var type = "";
var startTime = "";
var endTime = "";
var batchStatus = "0";
var deviceType = "1";
$(function() {
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
		laydate.render({
			elem: '#startTime',
			done:function (data) {
	            var endTime=$("#endTime").val();
	            if(data>endTime && endTime!=''){
	                layer.msg("开始时间不能大于结束时间");
	                $("#startTime").val('');
	            }
	        }
		});
		laydate.render({
			elem: '#endTime',
			done:function (data) {
	            var startTime=$("#startTime").val();
	            if(data<startTime && startTime!=''){
	                layer.msg("开始时间不能大于结束时间");
	                $("#endTime").val('');
	            }
	        }
		});
	});
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});
	$('#addBtn').on('click', function(e) {
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		cleanForm();
		JY.Model.edit("auDiv", "新增", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/machine/add', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
	
	$('#resetBtn').on('click', function(e) {
		$("#keyWord").val("");
		$("#type0").val("");
		$("#type").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#remarks").val("");
		$("#batchStatus option[value='-1']").attr("selected","true");
		init = 1;
		getbaseList(1);
	});
	
	$('#export').on('click',function(e) {
		var keyWord = $("#keyWord").val();
		var type=$("#type").val();
		var type0=$("#type0").val();
		var batchStatus=$("#batchStatus").val();
		
		var remarks=$("#remarks").val();
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		//window.location.href=bonuspath+"/backstage/machine/export?type="+type+"&type0="+type0+"&keyWord="+keyWord+"&batchStatus="+batchStatus+"&remarks="+remarks+"&deviceType="+deviceType;
		var url = bonuspath +'/backstage/machine/export';
		var params = {
			keyWord,
			type,
			type0,
			batchStatus,
			remarks
		};
		exportCommon(url, 'GET', params, '机具设备报表')
	});
});

function downFile(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/machine/exportModelExcel');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}




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
	type0 = $("#type0").val();
	type = $("type").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	batchStatus = $("#batchStatus").val();
	init = $(".pageNum").val();
	$(".pageNum").val(init);
	if (index == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/machine/findByPage',{deviceType:deviceType},
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
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.type0) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.type) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.buyPrice) + "</td>";
					if(l.isFixedAssets == 0 || l.isFixedAssets =='0'){
						html += "<td style='vertical-align:middle;' class='center'><a href='#' onclick='isFixedAssets(&apos;"+ l.id +"&apos;);'>否</td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'>是</td>";
					}
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.assetNum) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.originNum) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
					if(l.fileName == null || l.fileName == 'null' || l.fileName == ''){
						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadImg("+l.id+",1)'>上传</a></td>";
						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadMaterials("+l.id+",2)'>上传</a></td>";
						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadProcedures("+l.id+",3)'>上传</a></td>";
					
					}else{
						var filePath = l.filePath.replaceAll(/\\/g,"@");
						if(l.fileType.indexOf("1") == -1){ //没有合格证文件
							html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadImg("+l.id+",1)'>上传</a></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'queryImg(\""+l.id+"\",1)'>查看</a><a href='javascript:void(0)' onclick = 'downImg(\""+filePath+"\",\""+l.fileName+"\",\""+l.fileType+"\")'>|下载</a><a href='javascript:void(0)' onclick = 'uploadImg("+l.id+",1)'>|上传</a></td>";
						}
						if(l.fileType.indexOf("2") == -1){ //没有技术材料文件
							html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadMaterials("+l.id+",2)'>上传</a></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadMaterials("+l.id+",2)'>上传</a><a href='javascript:void(0)' onclick = 'filePreview(\""+l.id+"\",2)'>|查看</a></td>";
						}
						if(l.fileType.indexOf("3") == -1){ //没有改造手续文件
							html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadProcedures("+l.id+",3)'>上传</a></td>";
						}else{
							html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadProcedures("+l.id+",3)'>上传</a><a href='javascript:void(0)' onclick = 'filePreview(\""+l.id+"\",3)'>|查看</a></td>";
						}
						var fileNames = l.fileName.split(",");
						var filePaths = l.filePath.split(",");
						var fileTypes = l.fileType.split(",");
					}
					if(l.optUrl == null || l.optUrl == ''||l.optUrl == 'null'){ //没有操作手册文件
						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadOpmanual("+l.id+",\""+l.optUrl+"\")'>上传</a></td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadOpmanual("+l.id+",\""+l.optUrl+"\")'>上传</a><a href='javascript:void(0)' onclick = 'fileView(\""+l.id+"\",\""+l.optUrl+"\",\""+l.optName+"\")'>|查看</a></td>";
					}
					if(l.qrcode == null || l.qrcode == 'null'){
						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)';'>未绑定</a></td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick='readQRCode(&apos;"+ l.id +"&apos;);'>查看</a></td>";
					}
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
					}else if(l.batchStatus == 15 || l.batchStatus =='15'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>出库待批准</span></td>";
					}else if(l.batchStatus == 16 || l.batchStatus =='16'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待批准</span></td>";
					}else if(l.batchStatus == 17 || l.batchStatus =='17'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>退料检验</span></td>";
					}else if(l.batchStatus == 18 || l.batchStatus =='18'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>入库待审</span></td>";
					}else if(l.batchStatus == 19 || l.batchStatus =='19'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>维修合格</span></td>";
					}else if(l.batchStatus == 20 || l.batchStatus =='20'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已拆分</span></td>";
					}else if(l.batchStatus == 21 || l.batchStatus =='21'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>组装</span></td>";
					}else if(l.batchStatus == 22 || l.batchStatus =='22'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>检验报废</span></td>";
					}else if(l.batchStatus == 23 || l.batchStatus =='23'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>检验未通过</span></td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remarks) + "</td>";
					html += rowFunction(l.id,l.deviceCode,l.isFixedAssets,l.typeId);
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

function rowFunction(id,deviceCode,isFixedAssets,typeId) {
	var h = "";
	h += "<td style='vertical-align:middle;width: 50px;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;,&apos;"+typeId+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='机具生命周期' onclick='receive(&apos;" + deviceCode + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-p bigger-140'></i></a></li>";
//	h += "<a href='#' title='修改' onclick='edit(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "</div>";
	h += "</td>";
	return h;
}

function toQRCode(id){
	$.ajax({
		type: "post",
		url: bonuspath + "/backstage/machine/ToQRCode",
		data: {
			id : id
		},
		success: function(data) {
			setLoad();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！"+textStatus);
		}
	});
}

function isFixedAssets(id){
	JY.Model.confirm("确认变更吗？", function() {
		$.ajax({
			type:"post",
			url:bonuspath + "/backstage/machine/isFixedAssets",
			data:{
				id:id
			},
			success: function(data){
				setLoad();
			},
			error: function(XMLHttpRequest,textStatus, errorThrown){
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		});
	});
	
}

function readQRCode(id){
	localStorage.setItem("QRCodeId",id);
	localStorage.setItem("path",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['二维码查看','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machine/readQRCode'
	});
//	findBatchStatus(id);
}


//转固定资产
function toFixedAssets(id,assetNum,erpNum){
	localStorage.setItem("fixedId",id);
	localStorage.setItem("bonuspath",bonuspath);
	localStorage.setItem("assetNum",assetNum);
	localStorage.setItem("erpNum",erpNum);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['资料填写','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['400px', '260px'],
	  content: bonuspath+ '/backstage/machine/toFixedAssets'
	});
}
//
function buyCompany(id){
	localStorage.setItem("buyCompanyId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['资料填写','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['400px', '260px'],
	  content: bonuspath+ '/backstage/machine/buyCompany'
	});

}
// 批量删除
$('#delBatchBtn').on('click', function(e) {
	// 通知浏览器不要执行与事件关联的默认动作
	e.preventDefault();
	var chks = [];
	$('#baseTable input[name="ids"]:checked').each(function() {
		chks.push($(this).val());
	});
	if (chks.length == 0) {
		JY.Model.info("您没有选择任何内容!");
	} else {
		JY.Model.confirm("确认要删除选中的数据吗?", function() {
			JY.Ajax.doRequest(null, bonuspath + '/backstage/machine/delBatch', {
				chks : chks.toString()
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					setLoad();
				});
			});
		});
	}
});

function check(id,typeId) {
	localStorage.setItem("machinesId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//localStorage.setItem("typeId",typeId);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['信息查看','background-color: #27A3D9;color:#fff'],
	  maxmin: true,
	  area: ['1000px', '550px'],
	  content: bonuspath+'/backstage/machine/openFrom'
	});
}
function receive(deviceCode) {
	localStorage.setItem("deviceCode",deviceCode);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具生命周期','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1300px', '550px'],
	  content: bonuspath+'/backstage/machine/machineLifeCycle'
	});
}

function setLoad(){
	$("#keyWord").val(keyWord);
	$("#type0").val(type0);
	$("type").val(type);
	$("#startTime").val(startTime);
	$("#endTime").val(endTime);
	$("#batchStatus option[value='" + batchStatus + "']").attr("selected","selected");
	getbaseList(init);
}

function edit(id){	
	cleanCode();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/machine/findCode',{id:id},function(data){
		setCode(data);   
	    JY.Model.edit("auDiv","修改",function(){
	    	if(JY.Validate.form("auForm")){
				var that =$(this);
				JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machine/updateCode',{id:id},function(data){
					that.dialog("close");
				    JY.Model.info(data.resMsg,function(){setLoad();});	
				});
			}	
	    });
	});
}

function setCode(data){
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='gpsCode']").val(JY.Object.notEmpty(l.gpsCode));
	$("#auForm input[name$='rfidEpc']").val(JY.Object.notEmpty(l.rfidEpc));
	
}

function cleanCode() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='gpsCode']").val('');
	$("#auForm input[name$='rfidEpc']").val('');
	hideRole();
}


function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='firstName']").val('0');// 上级资源
	$("#auForm input[name$='firstName']").val('');
	$("#auForm input[name$='model']").val('0');// 上级资源
	$("#auForm input[name$='model']").val('');
	$("#auForm input[name$='deviceCode']").val('');
	$("#auForm input[name$='buyPrice']").val('');
	$("#auForm input[name$='propertyDepartment']").val('');
	$("#auForm input[name$='useDepartment']").val('');
	$("#auForm input[name$='serviceLife']").val('');
	$("#auForm input[name$='remark']").val('');
	hideRole();
}

function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function search() {
	$("#searchBtn").trigger("click");
}

function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/machine/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				setLoad();
			});
		});
	});
}

function getMachineStatus(){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machine/getMachineStatus',null, function(data) {
		var l = data.obj.list;
		var str='<option value="0">请选择</option>';
			str+='<option value="0"></option>';
		for(var i=0;i<l.length;i++){
			str+='<option value='+l[i].id+'>'+l[i].batchStatus+'</option>';
		}
		$("#batchStatus").append(str);
	});
}

function setMaTypeForm(){
	var maTypeId = localStorage.getItem("maTypeId");
	var maTypeName = localStorage.getItem("maTypeName");
	var isCount = localStorage.getItem("isCount");
	if(isCount == '1'){
		$("#deviceCode").css("display","none");
	}else if(isCount == '0'){
		$("#deviceCode").css("display","");
	}
	$("#maTypeId").val(maTypeId);
	$("#maTypeName").val(maTypeName);
	$("#maModelId").val(0);
	$("#maModelName").val("");
}

function maTypeTree(){
	localStorage.setItem("maTypeId","");
	localStorage.setItem("maTypeName","");
	localStorage.setItem("maTypeTreeName",$("#maTypeName").val());
	layer.open({
		type: 2,
		title:['机具类型','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/machineType/maTypeTree'
	});
}

function setMaModelForm(){
	var maModelId = localStorage.getItem("maModelId");
	var maModelName = localStorage.getItem("maModelName");
	$("#maModelId").val(maModelId);
	$("#maModelName").val(maModelName);
}

function maModelTree(){
	var maTypeId = $("#maTypeId").val();
	if(maTypeId == "0"){
		JY.Model.info("请选择机具类型！");
	}else{
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
	}
}

function insert(){
	cleanForm();
	JY.Model.edit("auDivs", "新增", function() {
		var deviceCode = $('#deviceCode').val();
		var propertyDepartment = $('#propertyDepartment').val();
		var useDepartment = $('#useDepartment').val();
		var serviceLife = $('#serviceLife').val();
		var isCount = localStorage.getItem("isCount");
		
		if(isCount == "0"){
			if(deviceCode == '' || deviceCode == null){
				layer.msg('请输入设备编码');
				return false;
			}
		}
		if(propertyDepartment == '' || propertyDepartment == null){
			layer.msg('请输入产权部门');
			return false;
		}
		if(useDepartment == '' || useDepartment == null){
			layer.msg('请输入使用部门');
			return false;
		}
		if(serviceLife == '' || serviceLife == null){
			layer.msg('请输入使用年限');
			return false;
		}
		if (JY.Validate.form("auForms")) {
			var that = $(this);
			JY.Ajax.doRequest("auForms", bonuspath + '/backstage/machine/add', {deviceCode:deviceCode}, function(data) {
				that.dialog("close");
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		}
	});
}


function filePreview(id,fileType){
	localStorage.setItem("id",id);
	localStorage.setItem("fileType",fileType);
	
	layer.open({
	  type: 2,
	  title:["技术材料",'background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['800px', '600px'],
	  content: bonuspath+'/backstage/machine/fileView'
	});
}


function uploadProcedures(id,type){//改造手续上传
	localStorage.setItem("machineId",id);
	localStorage.setItem("fileType",type);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['改造手续上传','background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machine/proceduresLoadPage'
	});
}
function uploadMaterials(id,type){//技术材料上传
	localStorage.setItem("machineId",id);
	localStorage.setItem("fileType",type);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['技术材料上传','background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machine/materialsLoadPage'
	});
}

function uploadImg(id,type){ //图片上传||合格证上传
	localStorage.setItem("machineId",id);
	localStorage.setItem("fileType",type);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['合格证上传','background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machine/imgLoadPage'
	});
}
function uploadOpmanual(id){ //图片上传||操作手册上传
	localStorage.setItem("id",id);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['操作手册上传','background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machine/OpmanualLoadPage'
	});
}
function upload(){ //图片上传||操作手册上传
	var type = $("#type").val();
	localStorage.setItem("type",type);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['操作手册上传','background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/machine/submitLoad'
	});
}


function importExcel(){
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['设备入库模板excel上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['500px', '400px'],
	  content: bonuspath+'/backstage/machine/uploadExcel'
	});
}




function queryImg(id,fileType){//查看图片
	localStorage.setItem("id",id);//设备id
	localStorage.setItem("fileType",fileType);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['查看','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1200px', '400px'],
	  content: bonuspath+'/backstage/machine/queryImgPage'
	});
}
function fileView(id,optUrl,optName){//查看图片
	localStorage.setItem("id",id);//设备id
	localStorage.setItem("optUrl",optUrl);//设备id
	localStorage.setItem("optName",optName);//设备id
	//iframe层-父子操作
//	layer.open({
//	  type: 2,
//	  title:['查看','background-color: #27A3D9;color:#fff'],
//	  shadeClose:true,
//	  shade:false,
//	  maxmin: true,
//	  area: ['880px', '600px'],
//	  content: bonuspath+'/backstage/machine/queryOptImgPage'
//	});
	layer.open({
		  type: 2,
		  title:["技术材料",'background-color: #27A3D9;color:#fff'],
		  //shadeClose:true,
		  //shade:false,
		  maxmin: true,
		  area: ['800px', '600px'],
		  content: bonuspath+'/backstage/machine/fileOptView'
		});

}
function downProcedures(proceduresFilePath, proceduresFileName){//改造手续下载
	var s = proceduresFilePath.replaceAll("@","/");
	var suffix = s.split(".")[1];
    var l =bonuspath + '/backstage/machine/downFile1?headerUrl='+s;
    download(l, proceduresFileName);
}



function downImg(filePath, fileName,fileType){//图片下载
	var fileTypes = fileType.split(",");
	var filePaths = filePath.split(",");
	var fileNames = fileName.split(",");
	var photoName;
	var photoPath;
	for(var i = 0;i < fileTypes.length;i++){
		if(fileTypes[i] == "1"){
			photoPath = filePaths[i].replaceAll("@","/");
			photoName = fileNames[i];
		}
	}
	//window.open(bonuspath+photoPath);
//	window.open(bonuspath+"/optInfo/b4ab4e5c-5a0c-44f9-b2e4-1610783444e8.doc");
  var l =bonuspath + '/backstage/machine/downFile?headerUrl='+photoPath;
    download(l, photoName);
}
function download(url, filename) {
	getBlob(url, function (blob) {
		saveAs(blob, filename);
	});
};
function getBlob(url, cb) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.responseType = 'blob';
	xhr.onload = function () {
		if (xhr.status === 200) {
			cb(xhr.response);
		}
	};
	xhr.send();
}
function saveAs(blob, filename) {
	if (window.navigator.msSaveOrOpenBlob) {
		navigator.msSaveBlob(blob, filename);
	} else {
		var link = document.createElement('a');
		var body = document.querySelector('body');
		link.href = window.URL.createObjectURL(blob);
		link.download = filename;
		link.style.display = 'none';
		body.appendChild(link);
		link.click();
		body.removeChild(link);
		window.URL.revokeObjectURL(link.href);
	};
}

var Base64 = {
	    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
	    encode: function (e) {
	        var t = "";
	        var n, r, i, s, o, u, a;
	        var f = 0;
	        e = Base64._utf8_encode(e);
	        while (f < e.length) {
	            n = e.charCodeAt(f++);
	            r = e.charCodeAt(f++);
	            i = e.charCodeAt(f++);
	            s = n >> 2;
	            o = (n & 3) << 4 | r >> 4;
	            u = (r & 15) << 2 | i >> 6;
	            a = i & 63;
	            if (isNaN(r)) {
	                u = a = 64
	            } else if (isNaN(i)) {
	                a = 64
	            }
	            t = t + this._keyStr.charAt(s) + this._keyStr.charAt(o) + this._keyStr.charAt(u) + this._keyStr.charAt(a)
	        }
	        return t
	    },
	    decode: function (e) {
	        var t = "";
	        var n, r, i;
	        var s, o, u, a;
	        var f = 0;
	        e = e.replace(/[^A-Za-z0-9+/=]/g, "");
	        while (f < e.length) {
	            s = this._keyStr.indexOf(e.charAt(f++));
	            o = this._keyStr.indexOf(e.charAt(f++));
	            u = this._keyStr.indexOf(e.charAt(f++));
	            a = this._keyStr.indexOf(e.charAt(f++));
	            n = s << 2 | o >> 4;
	            r = (o & 15) << 4 | u >> 2;
	            i = (u & 3) << 6 | a;
	            t = t + String.fromCharCode(n);
	            if (u != 64) {
	                t = t + String.fromCharCode(r)
	            }
	            if (a != 64) {
	                t = t + String.fromCharCode(i)
	            }
	        }
	        t = Base64._utf8_decode(t);
	        return t
	    },
	    _utf8_encode: function (e) {
	        e = e.replace(/rn/g, "n");
	        var t = "";
	        for (var n = 0; n < e.length; n++) {
	            var r = e.charCodeAt(n);
	            if (r < 128) {
	                t += String.fromCharCode(r)
	            } else if (r > 127 && r < 2048) {
	                t += String.fromCharCode(r >> 6 | 192);
	                t += String.fromCharCode(r & 63 | 128)
	            } else {
	                t += String.fromCharCode(r >> 12 | 224);
	                t += String.fromCharCode(r >> 6 & 63 | 128);
	                t += String.fromCharCode(r & 63 | 128)
	            }
	        }
	        return t
	    },
	    _utf8_decode: function (e) {
	        var t = "";
	        var n = 0;
	        var r = c1 = c2 = 0;
	        while (n < e.length) {
	            r = e.charCodeAt(n);
	            if (r < 128) {
	                t += String.fromCharCode(r);
	                n++
	            } else if (r > 191 && r < 224) {
	                c2 = e.charCodeAt(n + 1);
	                t += String.fromCharCode((r & 31) << 6 | c2 & 63);
	                n += 2
	            } else {
	                c2 = e.charCodeAt(n + 1);
	                c3 = e.charCodeAt(n + 2);
	                t += String.fromCharCode((r & 15) << 12 | (c2 & 63) << 6 | c3 & 63);
	                n += 3
	            }
	        }
	        return t
	    }
	}
