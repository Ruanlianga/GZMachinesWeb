$(function() {
	getbaseList(1);
});
var repairOutNum = "";
function getbaseList(init) {
	repairOutNum = localStorage.getItem("repairOutNum");
	if (init == 1)
	$("#baseForms.pageNum").val(1);
	JY.Model.loading();
	$("#outNum").val(repairOutNum);
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/lease/repair/findDetails',null,function(data) {
		$("#baseTable tbody").empty();
		var obj = data.obj;
		var list = obj.list;
		var results = list.results;
		var permitBtn = obj.permitBtn;
		var pageNum = list.pageNum, 
			pageSize = list.pageSize, 
			totalRecord = list.totalRecord;
		var html = "";
		if (results != null && results.length > 0) {
			var leng = (pageNum - 1) * pageSize;
			for (var i = 0; i < results.length; i++) {
				var l = results[i];
				html += "<tr>";
				html += "<td class='center'>" +
							"<label>" +
								"<input type='checkbox' name='ids' value='"+ l.deviceNum + "' class='ace' />" +
								"<span class='lbl'></span>" +
							"</label>" +
						"</td>";
				html += "<td style='vertical-align:middle;' class='center hidden-480'>"+ (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.outNum) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machinesName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.model) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceNum) + "</td>";
				var status = l.status;
				console.info("status:"+status);
				switch (status) {
				case "0":
					status = "<span class='label label-danger'>待维修</span>";
					break;
				case "1":
					status = "<span class='label label-info'>维修中</span>";
					break;
				case "2":
					status = "<span class='label label-error'>待报废</span>";
					break;
				case "3":
					status = "<span class='label label-warning'>合格</span>";
					break;
				default:
					break;
				}
				html += "<td style='vertical-align:middle;' class='center'>" + status + "</td>";
				html += rowFunction(l.deviceNum);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='8' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}

function rowFunction(deviceNum) {
	var h = "";
	h += "<td class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h += "<a href='#' title='维修' onclick='repair(&apos;"+ deviceNum + "&apos;)' class='aBtnNoTD' ><i class='icon-cogs color-blue bigger-140'></i></a>";
	h += "<a href='#' title='入库' onclick='input(&apos;" + deviceNum + "&apos;)' class='aBtnNoTD' ><i class='icon-home col bigger-140'></i></a>";
	h += "<a href='#' title='报废' onclick='scrap(&apos;" + deviceNum + "&apos;)' class='aBtnNoTD' ><i class='icon-trash col bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h += "<li><a href='#' title='维修' onclick='repair(&apos;" + deviceNum + "&apos;)' class='aBtnNoTD' ><i class='icon-cogs color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='入库' onclick='input(&apos;" + deviceNum + "&apos;)' class='aBtnNoTD' ><i class='icon-home col bigger-140'></i></a></li>";
	h += "<li><a href='#' title='报废' onclick='scrap(&apos;" + deviceNum + "&apos;)' class='aBtnNoTD' ><i class='icon-trash col bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function search() {
	$("#searchBtn").trigger("click");
}

function repair(deviceNum){
	localStorage.setItem("repairDNum",deviceNum);
	localStorage.setItem("repairpath",bonuspath);
//	findParts();
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['维修信息填写','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '400px'],
	  content: bonuspath+'/backstage/lease/repair/write'
	});
}

function input(deviceNum){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/lease/repair/chooseInput',
		{
		deviceNum : deviceNum,
		status : 3
		},function(data){
			JY.Model.info(data.resMsg, function() {
				search();
			});
	});
	
}

function scrap(deviceNum){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/lease/repair/chooseInput',
			{
			deviceNum : deviceNum,
			status : 2
			},function(data){
				JY.Model.info(data.resMsg, function() {
					search();
				});
		});
	
}

function findTypeModel(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/machines/findModel',{},function(data){
		$("#model").html("");
		var results = data.obj;
		var html="";
		if(results!=null&&results.list.length>0){
			for(var i=0;i<results.list.length;i++){
				var l= results.list[i];
				html+="<option value='"+l.machinesId+"'>"+l.model+"</option>";
			}
			$("#model").append(html);
		}else{
			html+="<option ></option>";;
			$("#model").append(html);
		}
	});
	
}






