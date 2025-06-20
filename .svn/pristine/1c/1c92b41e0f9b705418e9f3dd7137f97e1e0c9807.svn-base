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
	getbaseList(1);
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
	var agreementCode= $("#agreementCode").val();
	if (init == 1)
		$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax
			.doRequest(
					"baseForm",
					bonuspath + '/backstage/settlementAudit/findByPage',
					{agreementCode:agreementCode},
					function(data) {
//						alert("data="+JSON.stringify(data));
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
									html += "<td style='vertical-align:middle;' class='center'>待审核</td>";
								} else {
									html += "<td style='vertical-align:middle;' class='center'>已审核</td>";
								}
								html += "<td style='vertical-align:middle;' class='center'>"
									+ JY.Object.notEmpty(l.settlementDate)
									+ "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"
									+ JY.Object.notEmpty(l.batch)
									+ "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"
									+ JY.Object.notEmpty(l.batchMoney)
									+ "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"
									+ JY.Object.notEmpty(l.deductionMoney)
									+ "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"
									+ JY.Object.notEmpty(l.remark)
									+ "</td>";
								html += rowFunction(l.agreementId,l.batch,l.isSettlement);
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,
									pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='11' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}

function rowFunction(agreementId,batch,isSettlement) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(isSettlement == 1 || isSettlement =='1'){
		h += "<a href='#' title='结算' onclick='settlementAudit(&apos;"+ agreementId+ "&apos;,&apos;"+ batch+ "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
		//h += "<a href='#' title='结算协议书' onclick='agreement(&apos;"+ id + "&apos;)' class='aBtnNoTD' ><i class='icon-file-alt color-blue bigger-140'></i></a>";
		//h += "<a href='#' title='结算明细' onclick='details(&apos;"+ id+ "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-blue bigger-140'></i></a>";
	}else{
		//h += "<a href='#' title='结算协议书' onclick='agreement(&apos;"+ id + "&apos;)' class='aBtnNoTD' ><i class='icon-file-alt color-blue bigger-140'></i></a>";
		//h += "<a href='#' title='结算明细' onclick='details(&apos;"+ id+ "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-blue bigger-140'></i></a>";
	}
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if(isSettlement == 1 || isSettlement =='1'){
		h += "<li><a href='#' title='结算' onclick='settlementAudit(&apos;"+ agreementId + "&apos;,&apos;"+ batch+ "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a></li>";
		//h += "<li><a href='#' title='结算协议书' onclick='agreement(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-blue bigger-140'></i></a></li>";
		//h += "<li><a href='#' title='结算明细' onclick='details(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-file-alt color-blue bigger-140'></i></a></li>";
	}else{
		//h += "<li><a href='#' title='结算协议书' onclick='agreement(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-list-alt color-blue bigger-140'></i></a></li>";
		//h += "<li><a href='#' title='结算明细' onclick='details(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-file-alt color-blue bigger-140'></i></a></li>";
	}
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}


function agreement(id){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royalty/find', {
		id : id
	}, function(data) {
		var companyName = data.obj.companyName;
     	var projectName = data.obj.projectName;
		var settlementDate = data.obj.settlementDate;
		findCode(id,companyName,projectName,settlementDate);
	});
}

function findCode(id,companyName,projectName,settlementDate){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royalty/findCode', {}, function(data) {
		var code = data.resMsg;
		getReturnMoney(id,projectName,companyName,settlementDate,code);
	});
}


function setForm(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal,scarpTotal) {
	$("#checkCodes").html(code);
	$("#workName").html(projectName);
	$("#companyName").html(JY.Object.notEmpty(companyName));
	$("#settlementDate").html(JY.Object.notEmpty(settlementDate));
	$(".leaseTotal").html("￥"+JY.Object.notEmpty(leaseTotal));
	$(".notYetTotal").html(JY.Object.notEmpty(notYetTotal));
	$(".returnTotal").html(JY.Object.notEmpty(returnTotal));
	var loseTotal =parseFloat(notYetTotal) + parseFloat(returnTotal);
	loseTotal = loseTotal.toFixed(2);
	$(".loseTotal").html("￥"+JY.Object.notEmpty(loseTotal));
//	$("#shuaiTotal").html(JY.Object.notEmpty(shuaiTotal));
	$(".repairTotal").html("￥"+JY.Object.notEmpty(repairTotal));
	$(".scarpTotal").html("￥"+JY.Object.notEmpty(scarpTotal));
	var total = parseFloat(leaseTotal) + parseFloat(repairTotal) + parseFloat(scarpTotal)+parseFloat(notYetTotal) + parseFloat(returnTotal);
	total = total.toFixed(2);
	$(".total").html("￥"+JY.Object.notEmpty(total));
	var dxTotal = DX(total);
	$(".dxTotal").html(JY.Object.notEmpty(dxTotal));
	JY.Model.check("auForm");
}

function getLeaseMoney(id,projectName,companyName,settlementDate,code,returnTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getLeaseList', {
		agreementId : id
	}, function(data) {
		var leaseTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			leaseTotal = res[res.length-1].leaseTotal;
		}
		getNotYetMoney(id,projectName,companyName,settlementDate,code,leaseTotal,returnTotal);
	});
}


function getNotYetMoney(id,projectName,companyName,settlementDate,code,leaseTotal,returnTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getNotYetMsg', {
		agreementId : id
	}, function(data) {
//		alert("data ="+ JSON.stringify(data));
		var notYetTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			notYetTotal = res[res.length-1].notYetTotal;
		}
		getRepairMoney(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal);
	});
}



function getReturnMoney(id,projectName,companyName,settlementDate,code){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getLossList', {
		agreementId : id
	}, function(data) {
		//alert("data ="+ JSON.stringify(data));
		var returnTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			returnTotal = res[res.length-1].returnTotal;
		}
		getLeaseMoney(id,projectName,companyName,settlementDate,code,returnTotal);
	});
}


function getRepairMoney(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getRepairList', {
		agreementId : id
	}, function(data) {
		var repairTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			repairTotal = res[res.length-1].repairTotal;
		}
		getScarpMoney(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal);
	});
}



function getScarpMoney(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/royaltyDetails/getScarpList', {
		agreementId : id
	}, function(data) {
		var scarpTotal = 0;
		var res = data.obj.list;
		if(res.length > 0){
			scarpTotal = res[res.length-1].scarpTotal;
		}
		setForm(id,projectName,companyName,settlementDate,code,leaseTotal,notYetTotal,returnTotal,repairTotal,scarpTotal);		
	});
}

function settlementAudit(agreementId,batch){
	layer.confirm('确定审核结算？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			JY.Ajax.doRequest(null, bonuspath + '/backstage/settlementAudit/settlementAudit', {agreementId : agreementId,batch:batch}, function(data) {
			/*	JY.Model.info(data.resMsg, function() {
					search();
				});*/
				layer.msg('审核成功', { time: 1000,icon: 1});
				search();
			});
		}, function(){
		  layer.msg('暂不结算', { time: 1000,icon: 1});
		});

}

function details(id){
	localStorage.setItem("agreementId",id);
	localStorage.setItem("type",2);
	var url = bonuspath+'/backstage/royaltyDetails/list';  
    window.location.href = url;
}

function search() {
	$("#searchBtn").trigger("click");
}

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
