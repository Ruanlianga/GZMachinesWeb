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
	getbaseList(1);
	
	$('#download').on(
			'click',
			function(e) {
				var chks =[];    
				$('#baseTable input[name="ids"]:checked').each(function(){    
					chks.push($(this).val());    
				});     
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var keyWord=$("#keyWord").val();
				var isBind = $("#isBind").val();
				window.location.href=bonuspath +"/backstage/qrcode/downloadPhoto?startTime="+startTime+"&endTime="+endTime+"&keyWord="+keyWord+"&isBind="+isBind+"&chks="+chks;     
			});
});


function getbaseList(init) {
	if (init == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/qrcode/findByPage',
			null,function(data) {
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
						html+="<td style='vertical-align:middle;' class='center'><label> <input type='checkbox' name='ids' value='"+l.code+"' class='ace' /> <span class='lbl'></span></label></td>";
						html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.code) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maType) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.maModel) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.venderName) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.companyName) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.bindTime) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
						var isBind = l.isBind;
						if(isBind == 0){
							isBind = "未绑定";
						}else{
							isBind = "已绑定";
						}
						html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(isBind) + "</td>";
						html += "</tr>";
					}
					$("#baseTable tbody").append(html);
					JY.Page.setPage("baseForm", "pageing", pageSize,pageNum,totalRecord, "getbaseList");
				} else {
					html += "<tr><td colspan='11' class='center'>没有相关数据</td></tr>";
					$("#baseTable tbody").append(html);
					$("#pageing ul").empty();// 清空分页
				}
				JY.Model.loadingClose();
			}
	);
}

function rowFunction(id) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "</div>";
	h += "</td>";
	return h;
}

function add(){
	cleanForm();
	JY.Model.edit("auDiv", "新增", function() {
		var maModelId = $("#maModelId").val();
		var maVenderId = $("#maVenderId").val();
		if (JY.Validate.form("auForm")) {
			var nums = $("#nums").val();
			if(nums > 0){
				var that = $(this);
				JY.Model.loading("正在生成");
				JY.Ajax.doRequest("auForm", bonuspath + '/backstage/qrcode/insert', null, function(data) {
					that.dialog("close");
					JY.Model.loadingClose();
					JY.Model.info(data.resMsg, function() {getbaseList(1);});
				});
			}else{
				JY.Model.info("请重新输入！");
			}
		}
	});
}

function cleanForm(){
	$("#auForm input[name$='nums']").val("");
	localStorage.setItem("maTypeId","0");
	localStorage.setItem("maTypeName","");
	localStorage.setItem("maTypeTreeName","");
	localStorage.setItem("maModelId","0");
	localStorage.setItem("maModelName","");
	localStorage.setItem("maModelTreeName","");
	localStorage.setItem("maTypeTreeId","0");
	localStorage.setItem("maVenderId","0");
	localStorage.setItem("maVenderName","");
	localStorage.setItem("maVenderTreeName","");
	$("#maTypeId").val("0");
	$("#maTypeName").val("");
	$("#maModelId").val("0");
	$("#maModelName").val("");
	$("#maModelId").val("0");
	$("#maModelName").val("");
	$("#maVenderId").val("0");
	$("#maVenderName").val("");
}

function download(){
	var chks =[];    
	$('#baseTable input[name="ids"]:checked').each(function(){    
		chks.push($(this).val());    
	});     
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var keyWord=$("#keyWord").val();
	var isBind = $("#isBind").val();
	window.location.href=bonuspath +"/backstage/qrcode/downloadPhoto?startTime="+startTime+"&endTime="+endTime+"&keyWord="+keyWord+"&isBind="+isBind+"&chks="+chks;     
}

function produce(){
	window.location.href=bonuspath +"/backstage/qrcode/produceQRCode";     
}

function setMaTypeForm(){
	var maTypeId = localStorage.getItem("maTypeId");
	var maTypeName = localStorage.getItem("maTypeName");
	var isCount = localStorage.getItem("isCount");
	if(isCount == 1){
		layer.msg("请选择固定资产");
		return false;
	}
	$("#maTypeId").val(maTypeId);
	$("#maTypeName").val(maTypeName);
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

function setMaVenderForm(){
	var maVenderId = localStorage.getItem("maVenderId");
	var maVenderName = localStorage.getItem("maVenderName");
	$("#maVenderId").val(maVenderId);
	$("#maVenderName").val(maVenderName);
}

function maVenderTree(){
	localStorage.setItem("maVenderId","");
	localStorage.setItem("maVenderName","");
	localStorage.setItem("maVenderTreeName",$("#maVenderName").val());
	layer.open({
		type: 2,
		title:['机具厂家','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/vender/maVenderTree'
	});
}
