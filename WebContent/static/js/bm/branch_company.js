$(function() {
	getbaseList(1);
	$('#addBtn').on('click',function(e) {
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		cleanForm();
		JY.Model.edit("auDiv", "新增", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath+'/backstage/branchCompany/insert', null, function(data) {
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

function getbaseList(init) {
	if (init == 1){
		$(".pageNum").val(1);
	}
	JY.Model.loading();
	JY.Ajax.doRequest( "baseForm",bonuspath + '/backstage/branchCompany/findByPage',null,function(data) {
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
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.name) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.code) + "</td>";
				html += rowFunction(l.id);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='13' class='center'>没有相关数据</td></tr>";
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
	h+="<a href='#' title='修改' onclick='edit(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h+="<a href='#' title='删除' onclick='del(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}

function del(id){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/role/findUserRole',{},function(data){
		var res = data.obj;
		//alert(res);
		if(res != 1){
			JY.Model.confirm("权限不够，无法删除！");
		}else{
			JY.Model.confirm("确认删除吗？",function(){	
				JY.Ajax.doRequest(null,bonuspath +'/backstage/branchCompany/delete',{id:id},function(data){
					JY.Model.info(data.resMsg,function(){getbaseList(1);});
				});
			});
		}
		
	});
}

function edit(id){
	cleanForm();
		JY.Ajax.doRequest(null,bonuspath +'/backstage/branchCompany/find',{id:id},function(data){
			setForm(data);   
		    JY.Model.edit("auDiv","修改",function(){
		    	if(JY.Validate.form("auForm")){
					var that =$(this);
					//alert("ist="+ist);
					JY.Ajax.doRequest("auForm",bonuspath +'/backstage/branchCompany/update',null,function(data){
					    that.dialog("close");
					    JY.Model.info(data.resMsg,function(){search();});	
					});
				}	
		    });
		});
}
function cleanForm(){
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('');
	$("#auForm input[name$='name']").val('');
	$("#auForm input[name$='code']").val('');
}

function setForm(data){
	var l = data.obj[0];
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='name']").val(l.name);
	$("#auForm input[name$='code']").val(l.code);
}

