var init = 1;
var keyWord = "";
var type0 = "";
var type = "";
var startTime = "";
var endTime = "";
var batchStatus = "0";
var map = new Map();

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
/*	$("#startTime").datetimepicker({
		format : 'yyyy-mm-dd'
	});
	$("#endTime").datetimepicker({
		format : 'yyyy-mm-dd'
	});*/

	
	$('#resetBtn').on('click', function(e) {
		$("#keyWord").val("");
		$("#type0").val("");
		$("#type").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#batchStatus").val("0");
		$("#batchStatus option[value='0']").attr("selected","true");
		init = 1;
		getbaseList(1);
	});
	

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
	 $("#type").val(localStorage.getItem("flowType"));
	keyWord = $("#keyWord").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	init = $(".pageNum").val();
	type = $("#type").val();
	
	$(".pageNum").val(init);
	if (index == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/disassembly/findByPageTwo',null,
		function(data) {
			$("#baseTable tbody").empty();
			var msg = data.resMsg;
			var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
			var html = "";
			console.log(results);
			if (results != null && results.length > 0) {
				var leng = (pageNum - 1) * pageSize;
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center hidden-480'>"
							+ (i + leng + 1) + "</td>";
					/*html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";*/
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.name) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.userName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.optTime) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.oldCode) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.newCode) + "</td>";
					
				
					
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
/**
 * 解绑
 * @returns
 */
function unBound(id,gisCode){
	JY.Model.loading();
	JY.Ajax.doRequest(null, bonuspath + '/gpsbinding/unBoundGps', {
		id : id,
		gisCode:gisCode
	}, function(data) {
		console.log(data);
		JY.Model.info(data.obj);
		getbaseList();
		JY.Model.loadingClose();
	});
	
}


/**
 * gisId绑定
 * @param id
 * @returns
 */

var results = new Array();

function inputOnBlur(id,deviceCode){
	var isRepert=false;
	var gisCode=$("#inNums"+id).val();
	console.log(gisCode);
	var obj = {};
	obj.id=id;
	obj.gisCode=gisCode;
	obj.deviceCode=deviceCode;
	if(gisCode==null || gisCode==''){
		for ( var i in results) {
			if(id==results[i].id ){
				results.splice(i,1);
			}
		}
	}
	//验证gisCode唯一性
	$.ajax({
		url: bonuspath + '/gpsbinding/getGisCodeBylist',
		data:{
			gisCode : gisCode
		},
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			console.log(data);
			if(data.obj==1){
				isRepert=true;
			}
		}
	})
	if(isRepert){
		JY.Model.info("gps编号'"+gisCode+"'已存在");
		return;
	}
	//对数据进行循环
	for ( var i in results) {
		if(gisCode==results[i].gisCode && id!=results[i].id ){
			isRepert=true;
			break;
		}
	}
	if(isRepert){
		JY.Model.info("gps编号'"+gisCode+"'填写重复");
		return;
	}
	//数据存储
	map.set(id,gisCode);
	if(gisCode!='' && gisCode!=null){
		results.push(obj);
	}
	
}

function saveNewData(){
	var array=new Array();
	if(results.length<1){
		return;
	}
	console.log(results);
	for ( var i in results) {
		var id=results[i].id
		var gisCode=results[i].gisCode;
		var  deviceCode=results[i].deviceCode;
		if(gisCode!=null && gisCode!=''){
			ids=id+"_"+gisCode+"_"+deviceCode;
			array.push(ids);
		}
	}
	var da=array.join("@");
	JY.Model.loading();
	//数据后台处理
	JY.Ajax.doRequest(null, bonuspath + '/gpsbinding/updateGisCode', {
		id : da,
	}, function(data) {
		console.log(data);
		JY.Model.info(data.obj);
		getbaseList();
		results = new Array();
		map = new Map();
		JY.Model.loadingClose();
	});
}

/**
 * gis 流水查询
 * @returns
 */
function gisFlowDetail(){
	
	
	
}








