$(function() {
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
function setUnitForm(){
	var unitId = localStorage.getItem("unitId");
	var unitName = localStorage.getItem("unitName");
	$("#unitId").val(unitId);
	$("#unitName").val(unitName);
}

function setProjectForm(){
	var projectId = localStorage.getItem("projectId");
	var projectName = localStorage.getItem("projectName");
	$("#projectId").val(projectId);
	$("#projectName").val(projectName);
	getAgreementNum();

}

function unitTree(){
	localStorage.setItem("isOpen","1");
	localStorage.setItem("unitId","");
	localStorage.setItem("unitName","");
	localStorage.setItem("unitTreeName",$("#unitName").val());
	layer.open({
		type: 2,
		title:['结算单位','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/company/unitTree'
	});
}
function projectTree(){
	var unitId = $("#unitId").val();
	if(unitId == 0){
		JY.Model.info("请选择单位");
	}else{
		localStorage.setItem("unitId",unitId);
		localStorage.setItem("projectId","");
		localStorage.setItem("projectName","");
		localStorage.setItem("projectTreeName",$("#projectName").val());
		layer.open({
			type: 2,
			title:['工程名称','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['400px', '400px'],
			content: bonuspath+'/backstage/project/projectTree'
		});
	}
}

function getAgreementNum(){
	var unitId = $("#unitId").val();
	var projectId = $("#projectId").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/findAgreementCode',
			{leaseCompany:unitId,projectName:projectId}, function(data) {
				//alert(JSON.stringify(data));
				var l = data.resMsg;
				if(l == null){
					$("#agreementCode").val("尚未签订协议");
				}else{
					$("#agreementCode").val(l);
				}
			}
	);
}

function getbaseList(init) {
	if(init == 2){
		$("#unitName").val("");
		$("#projectName").val("");
		$("#agreementCode").val("");
	}
	var agreementCode= $("#agreementCode").val();
	if (init == 1)
		$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax
			.doRequest(
					"baseForm",
					bonuspath + '/backstage/settlement/findByPage',
					{agreementCode:agreementCode},
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
								html += "<td style='vertical-align:middle;' class='center'>"
										+ JY.Object.notEmpty(l.agreementCode)
										+ "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"
										+ JY.Object.notEmpty(l.projectName)
										+ "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"
										+ JY.Object.notEmpty(l.leaseName)
										+ "</td>";
								
								if (l.isSettlement == 1) {
									html += "<td style='vertical-align:middle;' class='center'>未结算</td>";
								} else {
									html += "<td style='vertical-align:middle;' class='center'>已结算</td>";
								}
								html += rowFunction(l.id,l.projectName);
								//html += rowFunctionly(l.id);
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,
									pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='8' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
	
	
}

function rowFunction(id,projectName) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	//h += "<a href='#' title='结算协议书' onclick='agreement(&apos;"+ id + "&apos;)' class='aBtnNoTD' ><i class='icon-file-alt color-blue bigger-140'></i></a>";
	h += "<a href='#' title='结算明细' onclick='details(&apos;"+ id+ "&apos;,&apos;" + projectName + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-blue bigger-140'></i></a>";
//	h += "<a href='#' title='维修是否完成' onclick='finishRepair(&apos;"+ id+ "&apos;)' class='aBtnNoTD' ><i class='icon-question color-p bigger-140'></i></a>";
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
//	h += "<li><a href='#' title='结算协议书' onclick='agreement(&apos;"+ id + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-blue bigger-140'></i></a></li>";
	h += "<li><a href='#' title='结算明细' onclick='details(&apos;" + id + "&apos;,&apos;" + projectName + "&apos;)' class='aBtnNoTD' ><i class='icon-file-alt color-blue bigger-140'></i></a></li>";
//	h += "<li><a href='#' title='维修是否完成' onclick='finishRepair(&apos;"+ id + "&apos;)' class='aBtnNoTD' ><i class='icon-question color-p bigger-140'></i></a></li>";
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}

function rowFunctionly(id){
	agreemently(id);
}

function agreemently(id){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royalty/findly', {
		id : id
	}, function(data) {
		var companyName = data.obj.companyName;
     	var projectName = data.obj.projectName;
		var settlementDate = data.obj.settlementDate;
		findCodely(id,companyName,projectName,settlementDate);
	});
}


function agreement(id){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlement/find', {
		id : id
	}, function(data) {
		var companyName = data.obj.leaseName;
     	var projectName = data.obj.projectName;
		var settlementDate = data.obj.settlementDate;
		findCode(id,companyName,projectName,settlementDate);
	});
}

function findCode(id,companyName,projectName,settlementDate){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlement/findCode', {}, function(data) {
		var code = data.resMsg;
		getLeaseMoney(id,projectName,companyName,settlementDate,code);
	});
}



function findCodely(id,companyName,projectName,settlementDate){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlement/findCode', {}, function(data) {
		var code = data.resMsg;
		getReturnMoneyly(id,projectName,companyName,settlementDate,code);
	});
}

function setForm(id,projectName,companyName,settlementDate,code,leaseTotal,loseTotal,repairTotal,scrapTotal) {
	$("#checkCodes").html(code);
	$("#workName").html(projectName);
	$("#companyName").html(JY.Object.notEmpty(companyName));
	$("#settlementDate").html(JY.Object.notEmpty(settlementDate));
	$(".leaseTotal").html("￥"+JY.Object.notEmpty(leaseTotal));
	$(".notYetTotal").html(JY.Object.notEmpty(loseTotal));
	var loseTotal = parseFloat(loseTotal);
	loseTotal = loseTotal.toFixed(2);
	$(".loseTotal").html("￥"+JY.Object.notEmpty(loseTotal));
	var repairTotals = parseFloat(repairTotal);
	repairTotals = repairTotals.toFixed(2);
	$(".repairTotal").html("￥"+JY.Object.notEmpty(repairTotals));
	var scrapTotals = parseFloat(scrapTotal);
	scrapTotals = scrapTotals.toFixed(2);
	$(".scrapTotal").html("￥"+JY.Object.notEmpty(scrapTotals));
	var total = parseFloat(leaseTotal) + parseFloat(repairTotal) + parseFloat(scrapTotal)+parseFloat(loseTotal);
	total = total.toFixed(2);
	$(".total").html("￥"+JY.Object.notEmpty(total));
	var dxTotal = DX(total);
	$(".dxTotal").html(JY.Object.notEmpty(dxTotal));
	JY.Model.check("auForm");
}

function setFormly(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal,scrapTotal) {
	$("#checkCodes").html(code);
	$("#workName").html(projectName);
	$("#companyName").html(JY.Object.notEmpty(companyName));
	$("#settlementDate").html(JY.Object.notEmpty(settlementDate));
	$(".leaseTotal").html("￥"+JY.Object.notEmpty(leaseTotal));
	$(".notYetTotal").html(JY.Object.notEmpty(notYetTotal));
	var loseTotal =parseFloat(notYetTotal) + parseFloat(returnTotal);
	loseTotal = loseTotal.toFixed(2);
	$(".loseTotal").html("￥"+JY.Object.notEmpty(loseTotal));
	var repairTotal = parseFloat(repairTotal);
	repairTotal = repairTotal.toFixed(2);
	$(".repairTotal").html("￥"+JY.Object.notEmpty(repairTotal));
	var scrapTotal = parseFloat(scrapTotal);
	scrapTotal = scrapTotal.toFixed(2);
	$(".scrapTotal").html("￥"+JY.Object.notEmpty(scrapTotal));
	var total = parseFloat(leaseTotal) + parseFloat(repairTotal) + parseFloat(scarpTotal)+parseFloat(notYetTotal) + parseFloat(returnTotal);
	total = total.toFixed(2);
	$(".total").html("￥"+JY.Object.notEmpty(total));
	var dxTotal = DX(total);
	$(".dxTotal").html(JY.Object.notEmpty(dxTotal));
	//JY.Model.check("auForm");
}

function getLeaseMoney(id,projectName,companyName,settlementDate,code){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlementDetails/getLeaseList', {
		agreementId : id
	}, function(data) {
		var leaseTotal = 0.00;
		var res = data.obj.list;
		if(res.length > 0){
			leaseTotal = res[res.length-1].leaseTotal;
		}
		getLoseMoney(id,projectName,companyName,settlementDate,code,leaseTotal);
	});
}

function getLeaseMoneyly(id,projectName,companyName,settlementDate,code,returnTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getLeaseList', {
		agreementId : id
	}, function(data) {
		var leaseTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			leaseTotal = res[res.length-1].leaseTotal;
		}
		getNotYetMoneyly(id,projectName,companyName,settlementDate,code,leaseTotal,returnTotal);
	});
}
function getLoseMoney(id,projectName,companyName,settlementDate,code,leaseTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlementDetails/getLoseList', {
		agreementId : id
	}, function(data) {
//		alert("data ="+ JSON.stringify(data));
		var loseTotal = 0.00;
		var res = data.obj.list;
		if(res.length > 0){
			loseTotal = res[res.length-1].loseTotal;
		}
		getRepairMoney(id,projectName,companyName,settlementDate,code,leaseTotal,loseTotal);
	});
}

function getNotYetMoneyly(id,projectName,companyName,settlementDate,code,leaseTotal,returnTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getNotYetMsg', {
		agreementId : id
	}, function(data) {
//		alert("data="+JSON.stringify(data));
		var notYetTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			notYetTotal = res[res.length-1].notYetTotal;
		}
		getRepairMoneyly(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal);
	});
}

function getReturnMoneyly(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getLossList', {
		agreementId : id
	}, function(data) {
		var returnTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			returnTotal = res[res.length-1].returnTotal;
		}
		getLeaseMoneyly(id,projectName,companyName,settlementDate,code,returnTotal);
	});
}

function getRepairMoney(id,projectName,companyName,settlementDate,code,leaseTotal,loseTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlementDetails/getRepairList', {
		agreementId : id
	}, function(data) {
		var repairTotal = 0.00;
		var res = data.obj.list;
		if(res.length > 0){
			repairTotal = res[res.length-1].repairTotal;
		}
		getScrapMoney(id,projectName,companyName,settlementDate,code,leaseTotal,loseTotal,repairTotal);
	});
}

function getRepairMoneyly(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getRepairList', {
		agreementId : id
	}, function(data) {
		var repairTotal = 0.00;
		var res = data.obj.list;
		if(res.length > 0){
			repairTotal = res[res.length-1].repairTotal;
		}
		getScarpMoneyly(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal);
	});
}


function getScrapMoney(id,projectName,companyName,settlementDate,code,leaseTotal,loseTotal,repairTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/settlementDetails/getScrapList', {
		agreementId : id
	}, function(data) {
		var scrapTotal = 0.00;
		var res = data.obj.list;
		if(res.length > 0){
			scrapTotal = res[res.length-1].scrapTotal;
		}
		setForm(id,projectName,companyName,settlementDate,code,leaseTotal,loseTotal,repairTotal,scrapTotal);		
	});
}

function getScarpMoneyly(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getScarpList', {
		agreementId : id
	}, function(data) {
		var scarpTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			scarpTotal = res[res.length-1].scarpTotal;
		}
		setFormly(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal,scarpTotal);		
	});
}

function settlement(id){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royalty/find', {
		id : id
	}, function(data) {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/royalty/settlement', {id : id}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function details(id,projectName){
	localStorage.setItem("agreementId",id);
	localStorage.setItem("projectName",projectName);
	localStorage.setItem("type",1);
	var url = bonuspath+'/backstage/settlementDetails/list';  
    window.location.href = url;
}

function search() {
	$("#searchBtn").trigger("click");
}

function finishRepair(id){	
	
		localStorage.setItem("id",id);
//	alert(id);
		layer.open({
			type: 2,
			title:['详细信息','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['900px', '500px'],
			content: bonuspath+'/backstage/royalty/repairCodeDetails'
		});
	
}
/*
//查询维修任务是否都已完成
function finishRepair(id){
	$.ajax({
		type: "post",
		url: bonuspath + "/backstage/royaltyDetails/findRepairList",
		dataType: "json",
		data: {
			agreementId: id
		},
		success: function(data) {
			var list = data.obj.list;
			var agreementCode;
			var repairCode;
			var isSure;
			if(list.length > 0){
				for(var i = 0;i<list.length;i++){
					agreementCode = list[i].agreementCode;
					repairCode = list[i].repairCode;
					isSure = list[i].isSure;
					if(isSure == 0){
						repairCode = list[i].repairCode;
						break;
					}
				}
				if(isSure == 0){
					layer.alert("编号为"+repairCode + '的维修任务未完成,无法结算！', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					});
				}else{
					layer.alert('维修任务已全部完成,可以结算！', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					});
				}
				layer.alert('维修任务已全部完成,可以结算！', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("未连接到服务器，请检查网络！");
		}
	});
}
*/
function DX(n) {
//	alert(1);
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
        return "数据非法";
    var unit = "千百拾亿千百拾万千百拾元角分", str = "";
        n += "00";
    var p = n.indexOf('.');
    if (p >= 0)
        n = n.substring(0, p) + n.substr(p+1, 2);
        unit = unit.substr(unit.length - n.length);
    for (var i=0; i < n.length; i++)
        str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
}