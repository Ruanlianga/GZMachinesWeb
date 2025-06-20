var element;
layui.use(['element'], function(){
	element = layui.element;
	// tab 切换事件
	element.on('tab(demo-filter-tab)', function(data){
		let auditStatus = $(this).attr('value');
		$('#auditStatus').val(auditStatus);
		search();
	});
});

$(function() {
	getbaseList(1);
});

function search(){
	getbaseList(1);
}

function reloadData() {
	getbaseList(1);
}

// 重置
function resetSearch(){
	$('#keyWord').val('');
	getbaseList(1);
}

function getbaseList(init) {
	if (init == 1){
		$(".pageNum").val(1);
	}
	JY.Model.loading();
	JY.Ajax.doRequest( "baseForm",bonuspath + '/backstage/planAudit/findByPage',null,function(data) {
		$("#baseTable tbody").empty();
		var obj = data.obj;
		var list = obj.list;
		var results = list.results;
		var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
		var html = "";
		if (results != null && results.length > 0) {
			var leng = (pageNum - 1) * pageSize;
			for (var i = 0; i < results.length; i++) {
				var l = results[i];
				html += "<tr>";
				html += "<td style='vertical-align:middle;' class='center hidden-480'>"
						+ (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.code) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.proName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.projectPart) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ getCheckStatus(l.statusType,l.status) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.creator) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.createTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
				html += rowFunction(l);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}

function rowFunction(obj){
	let content = getCheckStatus(obj.statusType,obj.status);
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if(!(content.indexOf('驳回') > -1 || content.indexOf('通过') > -1)){
		h+="<a href='#' title='审核' onclick='checkDetail(&apos;" + JSON.stringify(obj) + "&apos;)' class='aBtnNoTD' ><i class='icon-legal color-p bigger-140'></i></a>";	
	}
	h+="<a href='#' title='详情' onclick='detail(&apos;" + JSON.stringify(obj) + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}

// 导出
function exportData(){
	let params = {
			"keyWord":$("#keyWord").val(),
			"auditStatus":$("#auditStatus").val()
		}
    exportCommon(bonuspath + '/backstage/planAudit/export', 'POST', params,"需求计划申请");
}

// 机具需求计划申请详情
function detail(obj){
	var title = "详情"; 
	var layerIndex = layer.open({
		  type: 2,
		  title: title,
		  shade: [0],
		  area: ['90%', '90%'],
		  scrollbar: true,
		  move:false,
		  anim: 2,
		  yes:function(index,layero){
		  },
		  content: [bonuspath +'/backstage/planApplication/detail'],
		  success: function (layero, index) {
            let iframeWin = window["layui-layer-iframe" + layerIndex];
            iframeWin.setParams(obj);
	    },
	});
}

//机具需求计划申请审核详情
function checkDetail(obj){
	var title = "审核详情"; 
	var layerIndex = layer.open({
		  type: 2,
		  title: title,
		  shade: [0],
		  area: ['90%', '90%'],
		  scrollbar: true,
		  move:false,
		  anim: 2,
		  yes:function(index,layero){
		  },
		  content: [bonuspath +'/backstage/planAudit/checkDetail'],
		  success: function (layero, index) {
            let iframeWin = window["layui-layer-iframe" + layerIndex];
            iframeWin.setParams(obj);
	    },
	});
}

//审核状态
function getCheckStatus(statusType, status) {
	var company = "";
	if (statusType === '1') {
		return "<span style='color:#19BE6B;margin:0 5px 0 5px;font-size:16px;'>●</span>审核通过";
	} else if (statusType === '2') {
		company = "分公司";
	} else if (statusType === '3') {
		company = "项目管理中心";
	} else if (statusType === '4') {
		company = "机具公司";
	}
	if (status === '1') {
		return "<span style='color:#FF9900;margin:0 5px 0 5px;font-size:16px'>●</span>待" + company + "审核";
	} else if (status === '2') {
		return "<span style='color:#19BE6B;margin:0 5px 0 5px;font-size:16px;'>●</span>审核通过";
	} else if (status === '3') {
		return "<span style='color:#F56C6C;margin:0 5px 0 5px;font-size:16px'>●</span>"+company + "审核驳回";
	}
	return "<span style='color:#FF9900;margin:0 5px 0 5px;font-size:16px'>●</span>待审核";
}