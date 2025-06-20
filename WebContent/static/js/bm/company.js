$(function() {
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
	getCompany(0,0,"companyId");
	getCompanyType(0,0,"typeId");
	getbaseList(1);
//	$('#addBtn').on('click',function(e) {
//		
//});
	
});

function add(){
	// 通知浏览器不要执行与事件关联的默认动作
//	e.preventDefault();
	cleanForm();
	getCompany(0,0,"companyId1");
	getCompanyType(0,0,"typeId1");
	/*getOption(-1);*/
	JY.Model.edit("auDiv", "新增", function() {
		var companyId1 = $("#companyId1").val();
		var typeId1 = $("#typeId1").val();
		if(typeId1 == "0"){
			layer.msg("请选择单位类型");
			return false;
		}
		var phone = document.getElementById('phone').value;
		if(phone != "" && phone !=null){
			 if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(phone))){ 
			        layer.msg('手机号码有误，请重填');
			        return false; 
			    } 
		} 
		if (JY.Validate.form("auForm")) {
			var that = $(this);
			var unitName = $("#name").val();
			var companyId1 = $("#companyId1").val();
			getUnitName(unitName,companyId1,that);
		}
	});
}

//查询已存在单位名称
function getUnitName(name,companyId1,that){
	$.ajax({
		type: "post",
		url: bonuspath + "/backstage/company/getUnitName",
		dataType: "json",
		data: {
			name : name,
			companyId1:companyId1
		},
		success: function(data) {
			if(data.obj.length == "0"){
				JY.Ajax.doRequest("auForm", bonuspath+'/backstage/company/insert', null, function(data) {
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}else{
				JY.Model.info("单位名称重复，请核实准确！");
			}
				
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			alert("未连接到服务器，请检查网络！");
		}
	});
}

function getCompany(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/company/getCompany',null,function(data){
		var l = data.obj.list;
		var str='';
		str+='<option value="0">分公司选择</option>';
		for(var i=0;i<l.length;i++){
			if(num==0){
				str+='<option value='+l[i].id+'>'+l[i].name+'</option>';
			}else{
				if(num==l[i].id){
					str+='<option selected="selected" value='+l[i].id+'>'+l[i].name+'</option>';
				}else{
					str+='<option value='+l[i].id+'>'+l[i].name+'</option>';
				}
			}
		}
		$("#"+selectId).append(str);
	});	
}
function getCompanyType(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/company/getCompanyType',null,function(data){
		var l = data.obj.list;
		var str='<option value="0">单位类型选择</option>';
		for(var i=0;i<l.length;i++){
			if(num==0){
				str+='<option value='+l[i].id+'>'+l[i].name+'</option>';
			}else{
				if(num==l[i].id){
					str+='<option selected="selected" value='+l[i].id+'>'+l[i].name+'</option>';
				}else{
					str+='<option value='+l[i].id+'>'+l[i].name+'</option>';
				}
			}
		}
		$("#"+selectId).append(str);
	});	
}


function search(){
	getbaseList();
}

function getbaseList(role){
	JY.Model.loading();
	var companyId=$("#companyId").val();
	var typeId=$("#typeId").val();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/company/findByPage',
			{companyId:companyId,typeId:typeId},
			function(data){
		//alert(JSON.stringify(data));
		 $("#baseTable tbody").empty();
		 var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum,
				pageSize = list.pageSize,
				totalRecord = list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td style='vertical-align:middle;' class='center hidden-480'>"+(i+1)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center'>"+JY.Object.notEmpty(l.name)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center'>"+JY.Object.notEmpty(l.typeName)+"</td>";
            	
            		 html+="<td style='vertical-align:middle;' class='center '>"+JY.Object.notEmpty(l.unitTime)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center hidden-480' >"+JY.Object.notEmpty(l.materialClerk)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center '>"+JY.Object.notEmpty(l.manager)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center '>"+JY.Object.notEmpty(l.phone)+"</td>";
            		/* html+="<td style='vertical-align:middle;' class='center hidden-480'>"+JY.Object.notEmpty(numtoString(l.isDismiss))+"</td>";*/
            		 html+=rowFunction(l.id,l.companyId,l.companyName,l.typeId,l.typeName);
            		 html+="</tr>";
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
        	 }else{
        		html+="<tr><td colspan='8' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }
    	 JY.Model.loadingClose();
	 });
}

function numtoString(num){
	if(num==0){
		return '否';
	}else{
		return '是';
	}
}

function rowFunction(id,companyId,companyName,typeId,typeName){//\""+id+"\",\""+driverId+"\"
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	/*h+="<a href='#' title='查看' onclick='check(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";*/
	h+="<a href='#' title='修改' onclick='edit("+id+","+companyId+",\""+companyName+"\","+typeId+",\""+typeName+"\")' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h+="<a href='#' title='删除' onclick='del(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}
function check(accountId){
	/*cleanForm();
		JY.Ajax.doRequest(null,bonuspath +'/backstage/user/find',{id:accountId},function(data){
		    setForm(data);
		    JY.Model.check("auDiv");
	});*/
}

function del(id){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/role/findUserRole',{},function(data){
		var res = data.obj;
		if(res != 1){
			JY.Model.confirm("权限不够，无法删除！");
		}else{
			JY.Model.confirm("确认删除吗？",function(){	
			//	alert(id);
				JY.Ajax.doRequest(null,bonuspath +'/backstage/company/del',{id:id},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});
		}
		
	});
}
function edit(id,companyId,companyName,typeId,typeName){
	cleanForm();
		JY.Ajax.doRequest(null,bonuspath +'/backstage/company/find',{id:id},function(data){
			setForm(data);   
			getCompany(companyId,companyName,"companyId1");
			getCompanyType(typeId,typeName,"typeId1");
		    JY.Model.edit("auDiv","修改",function(){
		    	if(JY.Validate.form("auForm")){
					var that =$(this);
					var ist=$("#isDismiss").val();
					  var phone = document.getElementById('phone').value;
					  if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(phone))){ 
					        layer.msg('手机号码有误，请重填');
					        return false; 
					    }  
					//alert("ist="+ist);
					JY.Ajax.doRequest("auForm",bonuspath +'/backstage/company/update',{id:id},function(data){
					    that.dialog("close");
					    JY.Model.info(data.resMsg,function(){search();});	
					});
				}	
		    });
		});
}
function cleanForm(){
	
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='name']").val('');
	$("#auForm input[name$='materialClerk']").val('');
	$("#auForm input[name$='manager']").val('');
	$("#auForm input[name$='phone']").val('');
	/*$("#auForm input[name$='isDismiss']").val('');*/
/*	hideRole();*/
}
function setForm(data){
	var l=data.obj[0];
	JY.Tags.cleanForm("auForm");
	/*getOption(l.isDismiss);*/
	
	$("#auForm input[name$='name']").val(l.name);
	$("#auForm input[name$='materialClerk']").val(l.materialClerk);
	$("#auForm input[name$='manager']").val(l.manager);
	$("#auForm input[name$='phone']").val(l.phone);
	//$("#isDismiss option").val(l.isDismiss);
}
function getOption(num){
	$("#isDismiss").html("");
	var str ='';
	if(num==0){
		str+='<option selected="selected" value="0">否</option>';
		str+='<option value="1">是</option>'
	}else if(num==1){
		str+='<option value="0">否</option>';
		str+='<option selected="selected" value="1">是</option>'
	}else{
		str+='<option selected="selected" value="0">否</option>';
		str+='<option value="1">是</option>'
	}
	
		$("#isDismiss").append(str);
}


