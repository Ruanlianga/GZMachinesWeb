$(function() {
//	agreementInfo();
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
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	
});



var preisShow = false;// 窗口是否显示
function showRole() {
	if (preisShow) {
		hideRole();
	} else {
		var obj = $("#workNames");
		var offpos = $("#workNames").position();
		$("#workContent").css({
			left : offpos.left + "px",
			top : offpos.top + obj.heith + "px"
		}).slideDown("fast");
		preisShow = true;
	}
}

function getbaseList(init) {
	var keyWord = $("#keyWord").val();
	cleanForm();
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/scrap/findByPage',
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
				html += "<tr ondblclick='setDBChecked(${scrap.id})'>";
				html += "<td style='vertical-align:middle;' class='center'><label><input type='checkbox' name='ids' id='inp"+l.id+"' personId='"+l.scrapPersonId+"'  value='"+l.id+"' class='ace inp cb' ><span class='lbl'></span></label></td>";
				html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.modelName) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.scrapNum) + "</td>";
				if(l.scrapPerson == "" || l.scrapPerson == null){
					l.scrapPerson = "尚未指派";
					html += "<td style='vertical-align:middle;cursor:pointer;' class='center hidden-480'><a href='#' onclick='user("+ l.id +")'>" + l.scrapPerson + "</a></td>";
				}else{
					if(l.isActive ==1 ){
						html += "<td style='vertical-align:middle;' class='center'><input id='choose"+l.id+"' style='text-align: center;' onclick='user("+ l.id +")' value='"+ JY.Object.notEmpty(l.scrapPerson) + "'/>" +
						"</td>";
					}else{
						html += "<td style='vertical-align:middle;' class='center'><input readonly='readonly' id='choose"+l.id+"' style='text-align: center;' value='"+ JY.Object.notEmpty(l.scrapPerson) + "'/>" +
						"</td>";
					}
					
				}
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.scrapChecker) + "</td>";
				html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.operationTime) + "</td>";
				html += rowFunction(l.scrapPersonId,l.id,l.isActive);
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

function user(id) {
	cleanUserForm();
	JY.Model.edit("userDiv", "指派报废人员", function() {
		var that = $(this);
		if (JY.Validate.form("userForm")) {
			var userId = $("#userId").val();
			JY.Ajax.doRequest(null, bonuspath+'/backstage/scrap/update', 
				{
					id:id,
					scrapPerson:userId
				}, 
				function(data) {
					JY.Model.info(data.resMsg, function() {
						that.dialog("close");
						$("#userId").val('');
						clearUserFrom();
						search();
					});
			});
		}
	});
}

function users() {
	var vals = checkValues();
	cleanUserForm();
	JY.Model.edit("userDiv", "指派报废人员", function() {
		var that = $(this);
		if (JY.Validate.form("userForm")) {
			var userId = $("#userId").val();
			JY.Ajax.doRequest(null, bonuspath+'/backstage/scrap/updatePersons', 
				{
					ids:vals,
					scrapPerson:userId
				}, 
				function(data) {
					JY.Model.info(data.resMsg, function() {
						that.dialog("close");
						$("#userId").val('');
						clearUserFrom();
						search();
					});
			});
		}
	});
}

function notice() {
	var vals = checkPersons();
	JY.Model.confirm("确认通知吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/scrap/isApprovals', {
			scrapPersonIds:vals
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				surePersons();
				search();
				getbaseList(1);
			});
		});
	});
}

function surePersons() {
	var vals = checkValues();
	$.ajax({        
		type:"POST",  
        url:bonuspath +'/backstage/scrap/surePeoples', 
        data:{ids:vals},      
        dataType:"json",
        success:function(data) {
		   
	    },
	    error:function(data){
	    	var indexMsg = layer.confirm('请求发送失败', {btn: ['关闭']},function(){
	    		layer.close(indexMsg);
	    	});
	    }
	});
}

function checkValues(){
	var vals = '';
	$('input[type=checkbox]:checked').each(function(){
		var val = $(this).val();	
		console.log("val=",val);
		vals += val+","; 
		console.log("vals=",vals);	
	})
	return vals;
}

function checkPersons(){
	var vals = '';
	$('input[type=checkbox]:checked').each(function(){
		var val = $(this).attr("personId");	
		console.log("val=",val);
		vals += val+","; 
		console.log("vals=",vals);	
	})
	return vals;
}

function userTree(){
	cleanUserForm();
	localStorage.setItem("userId","");
	localStorage.setItem("userName","");
	localStorage.setItem("userTreeName",$("#userName").val());
	layer.open({
		type: 2,
		title:['检验人员','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/user/userTree'
	});
}
function clearUserFrom(){
	var userId = localStorage.getItem("userIds");
	var userName = localStorage.getItem("userName");
	$("#userId").val("");
	$("#userName").val("");
}

function setUserForm(){
	var userId = localStorage.getItem("userIds");
	var userName = localStorage.getItem("userName");
	$("#userId").val(userId);
	$("#userName").val(userName);
}

function cleanUserForm(){
	$("#userId").val("");
	$("#userName").val("");
}
// 批量删除
$('#delBatchBtn').on('click', function(e) {
	// 通知浏览器不要执行与事件关联的默认动作
	e.preventDefault();
	var chks = [];
	$('#baseTable input[name="ids"]:checked').each(function() {
		chks.push($(this).val());
	});
	if (chks.length == 0) {
		JY.Model.info("您没有选择任何内容!");
	} else {
		JY.Model.confirm("确认要删除选中的数据吗?", function() {
			JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/delBatch', {
				chks : chks.toString()
			}, function(data) {
				JY.Model.info(data.resMsg, function() {
					search();
				});
			});
		});
	}
});

function check(isApproval,id) {
	console.log("isApproval=",isApproval);
	console.log("id=",id);
	JY.Model.confirm("确认通知吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/scrap/isApproval', {
			scrapPersonId:isApproval
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				clearClickMethod(id);
				search();
				getbaseList(1);
			});
		});
	});
}

function clearClickMethod(id) {
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/scrap/surePeople', 
        data:{id:id},      
        dataType:"json",
        success:function(data) {
		   
		  },
		  error:function(data){
		   var indexMsg = layer.confirm('请求发送失败', {btn: ['关闭']},function(){
		    layer.close(indexMsg);
		   });
		  }
		 });
		}

function setForm(data) {
	var l = data.obj;
	$("#auForm input[name$='id']").val(l.id);
	$("#auForm input[name$='typeName']").val(JY.Object.notEmpty(l.typeName));
	$("#auForm input[name$='modelName']").val(JY.Object.notEmpty(l.modelName));
	$("#auForm input[name$='scrapNum']").val(JY.Object.notEmpty(l.scrapNum));
	$("#auForm input[name$='scrapPerson']").val(JY.Object.notEmpty(l.scrapPerson));
	$("#auForm input[name$='scrapChecker']").val(JY.Object.notEmpty(l.scrapChecker));
	$("#auForm input[name$='operationTime']").val(JY.Object.notEmpty(l.operationTime));
}

function cleanForm() {
	JY.Tags.isValid("auForm", "1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='id']").val('0');// 上级资源
	$("#auForm input[name$='typeName']").val('');
	$("#auForm input[name$='modelName']").val('');
	$("#auForm input[name$='scrapNum']").val('');
	$("#auForm input[name$='scrapPerson']").val('');
	$("#auForm input[name$='scrapChecker']").val('');
	$("#auForm input[name$='operationTime']").val('');
}

function search() {
	$("#searchBtn").trigger("click");
}

function edit(id) {
	console.info("id="+id);
	cleanForm();
	console.info("id="+id);
	JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/find', {
		id : id
	}, function(data) {
		setForm(data);
		JY.Model.edit("auDiv", "修改", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				JY.Ajax.doRequest("auForm", bonuspath
						+ '/backstage/agreement/update', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
}

function del(id) {
	JY.Model.confirm("确认删除吗？", function() {
		JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/del', {
			id : id
		}, function(data) {
			JY.Model.info(data.resMsg, function() {
				search();
			});
		});
	});
}

function details(id){
	localStorage.setItem("taskId",id);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['协议明细','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['1000px', '600px'],
	  content:bonuspath + '/backstage/agreementDetails/list'
	});

}

function findBranchOffice(){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/company/getCompany',{},function(data){
		$("#branchOffice").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#branchOffice").append(html);
		}else{
			html+="<option ></option>";;
			$("#branchOffice").append(html);
		}
	});
}

function findUnit(companyId){
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/company/getUnit',{companyId:companyId},function(data){
		$("#leaseCompany").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#leaseCompany").append(html);
		}else{
			html+="<option ></option>";;
			$("#leaseCompany").append(html);
		}
	});
	
}

function findProjectType(companyId){
	localStorage.setItem("companyId",companyId);
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/project/getProjectType',{},function(data){
		$("#projectType").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#projectType").append(html);
		}else{
			html+="<option ></option>";;
			$("#projectType").append(html);
		}
	});
	
}

function findProjectName(typeId){
	var companyId = localStorage.getItem("companyId");
	JY.Ajax.doRequest("auForm",bonuspath +'/backstage/project/getProjectName',{companyId : companyId,typeId:typeId},function(data){
		$("#projectName").html("");
		var results = data.obj.list;
		html="<option value='0'>请选择</option>";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				html+="<option value='"+l.id+"'>"+l.name+"</option>";
			}
			$("#projectName").append(html);
		}else{
			html+="<option ></option>";;
			$("#projectName").append(html);
		}
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

function readAgreePic(id){
	localStorage.setItem("readAgreeId",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
		type: 2,
		title:['附件图片查看','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['800px', '430px'],
		content: bonuspath+'/backstage/agreement/readAgreePic'
	});
}


