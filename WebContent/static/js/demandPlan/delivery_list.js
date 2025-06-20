$(function() {
	getbaseList(1);
	$('#addBtn').on('click',function(e) {
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		JY.Model.edit("auDiv", "新增", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath+'/backstage/planApplication/insert', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
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
	JY.Ajax.doRequest( "baseForm",bonuspath + '/backstage/planApplication/findByPage',null,function(data) {
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
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.needTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.creator) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.createTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remark) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.status) + "</td>";
				html += rowFunction(l.id);
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

function rowFunction(id){
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='详情' onclick='detail(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}

// 导出
function exportData(){
	let params = {
			"keyWord":$("#keyWord").val()
		}
    exportCommon(bonuspath + '/backstage/planApplication/export', 'POST', params,"需求计划申请");
}

// 查询流程
function showProcess(){
	layer.open({
		  type: 2,
		  title:['流程说明','background-color: #27A3D9;color:#fff'],
		  shadeClose:true,
		  shade:false,
		  maxmin: false,
		  move:false,
		  area: ['1100px', '600px'],
		  content:bonuspath + '/backstage/planApplication/showProcess'
	});
}

// 需求计划申请
function addForm(){
	var title = "机具需求计划申请"; 
	i = layer.open({
		  id: "addForm",
		  type: 2,
		  title: title,
		  shade: [0],
		  area: ['90%', '90%'],
		  scrollbar: true,
		  move:false,
		  anim: 2,
		  content: [bonuspath +'/backstage/planApplication/addForm']
	});
}

// 机具需求计划申请详情
function detail(id){
	var title = "机具需求计划申请详情"; 
	var layerIndex = layer.open({
		  type: 2,
		  title: title,
//		  btn:['保存','取消'],
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
            iframeWin.setParams(id);
	    },
	});
}