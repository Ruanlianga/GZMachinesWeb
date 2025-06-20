$(function() {
	parentId = localStorage.getItem("parentId");
	parentName = localStorage.getItem("parentName");
	getbaseList(1);
	$('#addBtn').on('click',function(e) {
		e.preventDefault();
		cleanForm();
		$("#pName").val(parentName);
		JY.Model.edit("auDiv", "新增", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath + '/backstage/machineType/insert', {parentId:parentId,level:4}, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						getbaseList(1);
					});
				});
			}
		});
	});
});

var parentId;
var parentName;

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	$("#parentId").val(parentId);
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/machineType/findDetails',null,
		function(data) {
			$("#baseTable tbody").empty();
			var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			if(results != null){
				var isCount = list.results[0].isCount; //是否计数 0、不计数（固资）  1、计数（非固资）
			}else{
				isCount = "";
			}
			localStorage.setItem("isCount", isCount); 
			
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
			var html = "";
			if (results != null && results.length > 0) {
				var leng = (pageNum - 1) * pageSize;
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.companyName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.parentName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.name) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.modelNum) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.nums) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.unit) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.leasePrice) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.payPrice) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.buyPrice) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.operationMan) + "</td>";
					var fileUrl = "是";
					if(l.fileUrl == null || l.fileUrl == ''){
						fileUrl = "否"
					}
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(fileUrl) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(l.remark) + "</td>";
					html += rowFunction(l.id);
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

function rowFunction(id) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h += "<a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h += "<a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	//h += "<a href='#' title='上传' onclick='uploadMaType(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-arrow-up bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='查看' onclick='check(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	//h += "<li><a href='#' title='上传' onclick='uploadMaType(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-arrow-up bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function check(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/findModel', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.check("auDiv");
	});
}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[id$='pName']").val(JY.Object.notEmpty(l.parentName));
	$("#auForm input[id$='name']").val(JY.Object.notEmpty(l.name));
	$("#auForm input[id$='nums']").val(JY.Object.notEmpty(l.nums));
	
	$("#auForm input[id$='unit']").val(JY.Object.notEmpty(l.unit));
	$("#auForm input[id$='ratedLoad']").val(JY.Object.notEmpty(l.ratedLoad));
	$("#auForm input[id$='testLoad']").val(JY.Object.notEmpty(l.testLoad));
	$("#auForm input[id$='holdingTime']").val(JY.Object.notEmpty(l.holdingTime));
	$("#auForm input[id$='leasePrice']").val(JY.Object.notEmpty(l.leasePrice));
	$("#auForm input[id$='payPrice']").val(JY.Object.notEmpty(l.payPrice));
	$("#auForm input[id$='buyPrice']").val(JY.Object.notEmpty(l.buyPrice));
	if(l.isTest == "1") {
		$('#isTest1').prop('checked',true);
	} 
	if(l.isTest == "0") {
		$('#isTest0').prop('checked',true);
	}
	if(l.isCount == "1") {
		$('#isCount1').prop('checked',true);
	} 
	if(l.isTest == "0") {
		$('#isCount0').prop('checked',true);
	}
	$("#auForm input[id$='keeper']").val(JY.Object.notEmpty(l.keeper));
	$("#auForm input[id$='keeperId']").val(JY.Object.notEmpty(l.keeperId));
	$("#auForm input[id$='warnNum']").val(JY.Object.notEmpty(l.warnNum));
	if(l.rfidPower !="" && l.rfidPower != null){
		document.getElementById("rfidPower").value=l.rfidPower;
	}else{
		document.getElementById("rfidPower").value="";
	}
}

function cleanForm() {
	$("#auForm input[name$='id']").val("");
	$("#auForm input[id$='pName']").val("");
	$("#auForm input[id$='name']").val("");
	$("#auForm input[id$='nums']").val("0");

	$("#auForm input[id$='unit']").val("");
	$("#auForm input[id$='ratedLoad']").val("");
	$("#auForm input[id$='testLoad']").val("");
	$("#auForm input[id$='holdingTime']").val("");
	$("#auForm input[id$='leasePrice']").val("");
	$("#auForm input[id$='payPrice']").val("");
	$("#auForm input[id$='buyPrice']").val("");
	var isCount = localStorage.getItem("isCount");  //是否计数 0、不计数（固资）  1、计数（非固资）
	if(isCount == "1"){
		$("#isCount1").attr("checked","checked");
	}else if(isCount == "0"){
		$("#isCount0").attr("checked","checked");
	}
	$("#auForm input[id$='keeper']").val("");
	$("#auForm input[id$='keeperId']").val("");
	$("#auForm input[id$='warnNum']").val("");
	document.getElementById("rfidPower").value="";
}

function edit(id) {
	cleanForm();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/findModel', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
		//alert(1);
				if (JY.Validate.form("auForm")) {
					var that = $(this);
					JY.Ajax.doRequest("auForm", bonuspath + '/backstage/machineType/updateModel', null, function(data) {
						that.dialog("close");
						JY.Model.info(data.resMsg, function() {
							getbaseList(1);
						});
					});
				}
			
		});
	});
}

function del(id) {
	JY.Model.confirm("请联系相关人员", function(){
		JY.Model.confirm("确认删除吗？", function() {
			JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/delete', {
				id : id,
				level : 4
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					getbaseList(1);
				});
			});
		});
	});
}

function uploadMaType(id){
	localStorage.setItem("uploadMaTypeId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具类型文件上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['400px', '300px'],
	  content: bonuspath+'/backstage/machineType/uploadFileUrl'
	});
}

function keeperTree(){
	localStorage.setItem("keeperIds","");
	localStorage.setItem("keeperNames","");
	layer.open({
		type: 2,
		title:['库管员列表','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/user/keeperTree'
	});
}

function setKeeperForm(){
	var keeperIds = localStorage.getItem("keeperIds");
	var keeperNames = localStorage.getItem("keeperNames");
	$("#keeper").val(keeperNames);
	$("#keeperId").val(keeperIds);
}
