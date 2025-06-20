$(function() {
	findParentTypeList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
});

function findParentTypeList() {
	$.ajax({
		url:bonuspath+'/backstage/applyDetails/findParentTypeList',
		data:{},
		type:'post',
		dataType:'json',
		success:function(data){
			var obj = data.obj;
			var list=obj.list;
			var html='<option value="">请选择</option>';
			$("#type").append(html);
			if(list.length>0){
				for(var i=0;i<list.length;i++){
					html+='<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
			}
			$("#parentType").append(html);
			
		},
		error:function(dats){
		}
	});
}

function findTypeList() {
	$("#type").empty();
	var id=$("#parentType").val();
	$.ajax({
		url:bonuspath+'/backstage/applyDetails/findTypeList',
		data:{id:id},
		type:'post',
		dataType:'json',
		success:function(data){
			var obj = data.obj;
			var list=obj.list;
			var html='<option value="">请选择</option>';
			if(list.length>0){
				for(var i=0;i<list.length;i++){
					html+='<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
			}
			$("#type").append(html);
			
		},
		error:function(dats){
		}
	});
}
function getbaseList(init) {
	var keyWord = $("#keyWord").val();
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/apply/findByPage',
			{
				keyWord: keyWord
			},
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
				html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.code) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.mtName) + "</td>";
				
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.createTime) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.applyRemark) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.status) + "</td>";
//				html += rowFunction(l.id);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='7' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();// 清空分页
		}
		JY.Model.loadingClose();
	});
}

function rowFunction(isApproval,id,isActive) {
	var h = "";
	h += "<td style='vertical-align:middle;' class='center'>";
	h += "<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	if (isApproval == "" || isApproval == null || isActive ==0 ) {
	}else{
		h += "<a href='#' title='通知' onclick='check(&apos;" + isApproval + "&apos;,&apos;"+ id+ "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
	h += "</div>";
	h += "<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	if (isApproval == 1 || isApproval == '1' || isActive ==0) {
	}else{
		h += "<a href='#' title='通知' onclick='check(&apos;" + isApproval +"&apos;,&apos;"+ id + "&apos;)' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>";
	}
	h += "</ul></div></div>";
	h += "</td>";
	return h;
}


function search() {
	$("#searchBtn").trigger("click");
}

function addData() {
	layer.open({
	  type: 2,
	  title:['报废申请明细','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/apply/addApply'
	});
}

//附件图片上传、查看
function updAgreeListPic(id){
	localStorage.setItem("updAgreeId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['附件图片上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/agreement/updAgreeListPic'
	});
}


