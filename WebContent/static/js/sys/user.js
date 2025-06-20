$(function () {
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 } 
	});
	//新加
	$('#addBtn').on('click', function(e) {
		//selectPostName();
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanForm();	
		loadOrgTree();
		JY.Model.edit("auDiv","新增",function(){
			 if(JY.Validate.form("auForm")){
				 var that =$(this);
				 JY.Ajax.doRequest("auForm",bonuspath +'/backstage/user/add',null,function(data){
				     that.dialog("close");      
				     JY.Model.info(data.resMsg,function(){search();});
				 });
			 }	
		});
	});
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){    
			chks.push($(this).val());    
		});     
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!"); 
		}else{
			JY.Model.confirm("确认要删除选中的数据吗?",function(){	
				JY.Ajax.doRequest(null,bonuspath +'/backstage/user/delBatch',{chks:chks.toString()},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});		
		}		
	});
});
function search(){
	$("#searchBtn").trigger("click");
}


function loadOrgTree(){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/orgTree',null,function(data){
		$.fn.zTree.init($("#orgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
	});
}
function emptyRole(){
	$("#orgName").prop("value","");
	$("#auForm input[name$='orgId']").prop("value","0");
}
var preisShow=false;//窗口是否显示
function showRole() {
	if(preisShow){
		hideRole();
	}else{
		var obj = $("#orgName");
		var offpos = $("#orgName").position();
		$("#orgContent").css({left:offpos.left+"px",top:offpos.top+obj.heigth+"px"}).slideDown("fast");	
		preisShow=true;
	}
}
function clickRole(e, treeId, treeNode) {
//	var check = (treeNode && !treeNode.isParent);
	var check = (treeNode);
	if(check){
		var zTree = $.fn.zTree.getZTreeObj("orgTree"),
		nodes = zTree.getSelectedNodes(),v ="",n ="",o="",p="";	
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";//获取name值
			n += nodes[i].id + ",";//获取id值
			o += nodes[i].other + ",";//获取自定义值
			var pathNodes=nodes[i].getPath();
			for(var y=0;y<pathNodes.length;y++){
				p+=pathNodes[y].name+"/";//获取path/name值
			}
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);	
		if (n.length > 0 ) n = n.substring(0, n.length-1);
		if (o.length > 0 ) o = o.substring(0, o.length-1);
		if (p.length > 0 ) p = p.substring(0, p.length-1);
		
		$("#orgName").val(p);
		$("#auForm input[name$='orgId']").prop("value",n);
		hideRole(n);
	}
}
function hideRole(n){
	$("#orgContent").fadeOut("fast");
	preisShow=false;
	loadCompanyInfo(n);
}
function loadCompanyInfo(n){
	var orgId = n;
	
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/findCompanyInfo',{orgId:orgId},function(data){
		console.log(JSON.stringify(data));
		if(data.obj.list!=null){
			var obj = data.obj.list;
			$("#companyId").val(obj.companyId);
			$("#companyName").val(obj.companyName);
		}
	
		
	});
}

function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/user/findByPage',null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td style='vertical-align:middle;' class='center'><label> <input type='checkbox' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td style='vertical-align:middle;' class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center'>"+JY.Object.notEmpty(l.loginName)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center hidden-480' >"+JY.Object.notEmpty(l.name)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center '>"+JY.Object.notEmpty(l.orgName)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center hidden-480'>"+JY.Object.notEmpty(l.postName)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center '>"+JY.Object.notEmpty(l.telphone)+"</td>";
            		 if(l.sex==1) html+="<td style='vertical-align:middle;' class='center hidden-480'><span class='label label-sm label-success'>男</span></td>";
            		 else             html+="<td style='vertical-align:middle;' class='center hidden-480'><span class='label label-sm arrowed-in'>女</span></td>";
            		 if(l.picUrl == null || l.picUrl == 'null' || l.picUrl == ''){
            			 html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'uploadImg("+l.id+")'>上传</a></td>";
            		 }else{
            			 var picUrl = l.picUrl.replaceAll(/\\/g,"@");
            			 html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick = 'queryImg(\""+picUrl+"\")'>查看</a><a href='javascript:void(0)' onclick = 'uploadImg("+l.id+")'>|上传</a></td>";
            		 }
            		 html+=rowFunction(l.id);
            		 html+="</tr>";
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='11' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }
    	 JY.Model.loadingClose();
	 });
}

function rowFunction(id){
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='密码重置' onclick='resetPwd(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-unlock-alt colo bigger-140'></i></a>";
	h+="<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="<a href='#' title='修改' onclick='edit(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h+="<a href='#' title='删除' onclick='del(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h+="</div>";
	h+="<div class='visible-xs visible-sm hidden-md hidden-lg'><div class='inline position-relative'>";
	h+="<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
	h+="<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
	h+="<li><a href='#' title='密码重置' onclick='resetPwd(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-unlock-alt colo bigger-140'></i></a></li>";
	h+="<li><a href='#' title='查看' onclick='check(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></li>";
	h+="<li><a href='#' title='修改' onclick='edit(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a></li>";
	h+="<li><a href='#' title='删除' onclick='del(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a></li>";
	h+="</ul></div></div>";		
	h+="</td>";
	return h;
}

function uploadImg(id){ //图片上传
	localStorage.setItem("userId",id);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['合格证上传','background-color: #27A3D9;color:#fff'],
	  //shadeClose:true,
	  //shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/user/imgLoadPage'
	});
}

function queryImg(filePath){//查看图片
	localStorage.setItem("userPath",filePath);//保存合格证图片路径
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['查看','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['610px', '400px'],
	  content: bonuspath+'/backstage/user/queryImgPage'
	});
}

function check(accountId){
	cleanForm();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/orgTree',null,function(data){
		$.fn.zTree.init($("#orgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
		JY.Ajax.doRequest(null,bonuspath +'/backstage/user/find',{id:accountId},function(data){
			setForm(data);
		    JY.Model.check("auDiv");
		});
	});
}

function del(accountId){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/user/del',{id:accountId},function(data){
			JY.Model.info(data.resMsg,function(){search();});
		});
	});
}
function edit(accountId){
	cleanForm();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/orgTree',null,function(data){
		$.fn.zTree.init($("#orgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
		JY.Ajax.doRequest(null,bonuspath +'/backstage/user/find',{id:accountId},function(data){
		    setForm(data);
		   // selectPostName(data.obj.postId);
		    JY.Model.edit("auDiv","修改",function(){
		    	if(JY.Validate.form("auForm")){
					var that =$(this);
					JY.Ajax.doRequest("auForm",bonuspath +'/backstage/user/update',null,function(data){
					    that.dialog("close");
					    JY.Model.info(data.resMsg,function(){search();});	
					});
				}	
		    });
		});
	});
}
function resetPwd(accountId){
	$("#resetPwdFrom input[name$='id']").val(accountId);//类型
	$("#resetPwdFrom input[name$='pwd']").val('');//类型
	$("#resetPwdDiv").removeClass('hide').dialog({resizable: false,modal:true,title:"<div class='widget-header'><h4 class='smaller'>密码重置</h4></div>",title_html: true,
		buttons: [
		  {  
			 html: "<i class='icon-ok bigger-110'></i>&nbsp;保存","class" : "btn btn-primary btn-xs",
			 click: function() {
				 if(JY.Validate.form("resetPwdFrom")){
					 var that =$(this);
					 JY.Ajax.doRequest("resetPwdFrom",bonuspath +'/backstage/user/resetPwd',null,function(data){
						 that.dialog("close");
			        	JY.Model.info(data.resMsg,function(){search();});		
					 });
				 }		
			}
		  },
		   {
			 html: "<i class='icon-remove bigger-110'></i>&nbsp;取消","class":"btn btn-xs",
			 click: function() {
				$(this).dialog("close");
			 }
		   }
		]
	});
}
function cleanForm(){
	JY.Tags.cleanForm("auForm");
	$('#sex1').prop('checked',true);
	$("#auForm input[name$='orgId']").val('0');//上级资源
	$("#auForm input[name$='loginName']").prop("disabled",false); 
	hideRole();
}
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='id']").val(l.id);
	if(l.sex == "1") {
		$('#sex1').prop('checked',true);
	} 
	if(l.sex == "0") {
		$('#sex0').prop('checked',true);
	}
	
	$("#auForm input[name$='loginName']").val(JY.Object.notEmpty(l.loginName));
	$("#auForm input[name$='loginName']").prop("disabled",true); 
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
	$("#auForm input[name$='mail']").val(JY.Object.notEmpty(l.mail));
	$("#auForm input[name$='postName']").val(JY.Object.notEmpty(l.postName));
	$("#auForm input[name$='telphone']").val(JY.Object.notEmpty(l.telphone));
	$("#auForm input[name$='postDuty']").val(JY.Object.notEmpty(l.postDuty));
	$("#auForm input[name$='officeAddress']").val(JY.Object.notEmpty(l.officeAddress));
	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	var nodes = treeObj.getNodesByParam("id",l.orgId);
	if(nodes.length>0){
		treeObj.selectNode(nodes[0]);
		clickRole(null,null,nodes[0]);
	}
}

function selectPostName(id){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/post/findPost',null,function(data){
		$("#postId").html("");
		var obj = data.obj;
		var results = obj;
		var html="";
		if(results!=null&&results.length>0){
			for(var i=0;i<results.length;i++){
				var l= results[i];
				 html+="<option value='" + l.id + "'>" + l.name + "</option>";
			}
			$("#postId").append(html);
			$("#postId option[value='" + id + "']").attr("selected","selected");
		}else{
			html+="<option ></option>";;
			$("#postId").append(html);
		}
	});
}

function tips(){
	layer.open({
		  type: 2,
		  title:['提示','background-color: #27A3D9;color:#fff'],
		  shadeClose:true,
		  shade:false,
		  maxmin: true,
		  area: ['400px', '250px'],
		  content: bonuspath+'/backstage/user/tips'
		});
}
