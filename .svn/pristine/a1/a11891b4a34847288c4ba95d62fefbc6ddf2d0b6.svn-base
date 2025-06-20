var init = 1;
var keyWord = "";
var typeName = "";
var type = "";
var startTime = "";
var endTime = "";
var batchStatus = "0";
var deviceType = "0 "
var map = new Map();
$(function() {

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

	
	$('#resetBtn').on('click', function(e) {
		$("#keyWord").val("");
		$("#typeName").val("");
		$("#type").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#batchStatus").val("0");
		$("#batchStatus option[value='0']").attr("selected","true");
		$("#deviceType option[value='0']").attr("selected","true");
		init = 1;
		getbaseList(1);
	});
	

});

function getbaseList(index) {
	keyWord = $("#keyWord").val();
	typeName = $("#typeName").val();
	type = $("type").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	batchStatus = $("#batchStatus").val();
	deviceType = $("#deviceType").val();
	init = $(".pageNum").val();
	$(".pageNum").val(init);
	if (index == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/gpsbinding/findGpsFlowPage',null,
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
					
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.gisCode) + "</td>";
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
					}else if(l.batchStatus == 20 || l.batchStatus =='20'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已拆分</span></td>";
					}else if(l.batchStatus == 21 || l.batchStatus =='21'){
						html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已组装</span></td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'></td>";
					}
				
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.optTime) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.userName) + "</td>";
					var gisType=l.gisType;
					if(gisType=='1'){
						gisType='绑定';
					}else{
						gisType='解绑';
					}
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(gisType) + "</td>";
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

function inputOnBlur(id){
	var isRepert=false;
	var gisCode=$("#inNums"+id).val();
	console.log(gisCode);
	var obj = {};
	obj.id=id;
	obj.gisCode=gisCode;
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
		JY.Model.info("gis编号'"+gisCode+"'已存在");
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
		JY.Model.info("gis编号'"+gisCode+"'填写重复");
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
	console.log(results);
	for ( var i in results) {
		var id=results[i].id
		var gisCode=results[i].gisCode;
		if(gisCode!=null && gisCode!=''){
			ids=id+"_"+gisCode;
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








