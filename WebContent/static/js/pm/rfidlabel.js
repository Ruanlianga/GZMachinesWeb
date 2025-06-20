$(function() {
	getRfid();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});
	
});

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


function getRfid(){
	$("#deviceId").html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/rfidlabel/getRfid',null,function(data){
		var l = data.obj.list;
		var str='<option value="0">读写器选择</option>';
		for(var i=0;i<l.length;i++){
			str+='<option value='+l[i].productIp+'>'+l[i].productName+'</option>';
		}
		$("#deviceId").append(str);
	});	
}
function startInventory(){
	var deviceId = $("#deviceId").val();
	if(deviceId == 0){
		alert("请选择设备");
	}else{
		$("#tbody").html("");
		$("#button1").attr('disabled',true);
		$("#button2").attr('disabled',false);
		$("#button3").attr('disabled',true);
		$("#button4").attr('disabled',true);
		$("#button5").attr('disabled',true);
		JY.Ajax.doRequest(null,bonuspath +'/backstage/rfidlabel/startInventory',{devId:deviceId},function(data){
			console.log(data);
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});	
	}
	layer.alert('已经在盘点', {
		  skin: 'layui-layer-molv' //样式类名
		  ,closeBtn: 0
		});
	
}
function stopInventory(){
	var deviceId = $("#deviceId").val();
	if(deviceId == 0){
		alert("请选择设备");
	}else{
		$("#button1").attr('disabled',true);
		$("#button2").attr('disabled',true);
		$("#button3").attr('disabled',false);
		$("#button4").attr('disabled',true);
		$("#button5").attr('disabled',true);
		JY.Ajax.doRequest(null,bonuspath +'/backstage/rfidlabel/stopInventory',{devId:deviceId},function(data){
			console.log(data);
		});	
	}
	layer.alert('已经停止盘点', {
		  skin: 'layui-layer-molv' //样式类名
		  ,closeBtn: 0
		});
}
function getRfidLabels(init) {
	$("#button1").attr('disabled',false);
	$("#button2").attr('disabled',true);
	$("#button3").attr('disabled',true);
	$("#button4").attr('disabled',false);
	$("#button5").attr('disabled',false);
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/rfidlabel/findByPage',
				null,function(data) {
						$("#tbody").html("");
						var obj = data.obj;
						var results = obj.list;
						var html = "";
						if (results != null && results.length > 0) {
							for (var i = 0; i < results.length; i++) {
								var l = results[i];
								if(l != null){
									html += "<tr>";
									html += "<td class='center hidden-480'>" + (i + 1) + "</td>";
									html += "<td class='center'>" + JY.Object.notEmpty(l.rfidEpc) + "</td>";
									html += "<td class='center'>" + JY.Object.notEmpty(l.type0) + "</td>";
									html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.type) + "</td>";
									html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.deviceCode) + "</td>";
									if(l.batchStatus == '' || l.batchStatus == null){
										html += "<td style='vertical-align:middle;' class='center'></td>";
									}else if(l.batchStatus == 1 || l.batchStatus =='1'){
										html += "<td style='vertical-align:middle;' class='center'></td>";
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-default'>待通知</span></td>";
									}else if(l.batchStatus == 2 || l.batchStatus =='2'){
										html += "<td style='vertical-align:middle;' class='center'></td>";
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-primary'>待检验</span></td>";
									}else if(l.batchStatus == 3 || l.batchStatus =='3'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待打印</span></td>";
									}else if(l.batchStatus == 4 || l.batchStatus =='4'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待入库</span></td>";
									}else if(l.batchStatus == 5 || l.batchStatus =='5'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>已入库</span></td>";
									}else if(l.batchStatus == 6 || l.batchStatus =='6'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>已出库</span></td>";
									}else if(l.batchStatus == 7 || l.batchStatus =='7'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>已退料</span></td>";
									}else if(l.batchStatus == 8 || l.batchStatus =='8'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>修试后待入库</span></td>";
									}else if(l.batchStatus == 9 || l.batchStatus =='9'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待维修</span></td>";
									}else if(l.batchStatus == 10 || l.batchStatus =='10'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待报废</span></td>";
									}else if(l.batchStatus == 11 || l.batchStatus =='11'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>已报废</span></td>";
									}
									html += "</tr>";
								}
							}
							$("#baseTable tbody").append(html);
						} else {
							html += "<tr><td colspan='6' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
						}
						JY.Model.loadingClose();
					});
}


function updateInput(){
	$("#button1").attr('disabled',false);
	$("#button2").attr('disabled',true);
	$("#button3").attr('disabled',true);
	$("#button4").attr('disabled',false);
	$("#button5").attr('disabled',false);
	JY.Model.confirm("确认入库吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/rfidlabel/updateInput',null,function(data){
			JY.Model.info(data.resMsg,function(){getRfidLabels(1);});
		});
	});
}

function updateOut(){
	$("#button1").attr('disabled',false);
	$("#button2").attr('disabled',true);
	$("#button3").attr('disabled',true);
	$("#button4").attr('disabled',false);
	$("#button5").attr('disabled',false);
	JY.Model.confirm("确认出库吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/rfidlabel/updateOut',null,function(data){
			JY.Model.info(data.resMsg,function(){getRfidLabels(1);});
		});
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='epc']").val(l.epc);
		}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
		hideRole();
}

function hideRole() {
	$("#roleContent").fadeOut("fast");
	preisShow = false;
}

function search() {
	$("#searchBtn").trigger("click");
}





