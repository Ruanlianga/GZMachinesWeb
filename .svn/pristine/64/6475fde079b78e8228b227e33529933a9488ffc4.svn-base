var init = 1;
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
	getProjectType(0,0,"typeId");
	getVolLever(0,0,"volId");
	
	getProvinces(0, 0);
	getCities(0,0)
	getDistricts(0,0)
	
	$('#addBtn').on('click',function(e) {
		// 通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		cleanForm();
		getCompany(0,0,"companyId1");
		getProjectType(0,0,"typeId1");
		getVolLever(0,0,"volId1");
		/*getOption(-1);*/
		JY.Model.edit("auDiv", "新增", function() {
			if (JY.Validate.form("auForm")) {
				var that = $(this);
				var projectName=document.getElementById('phone').value;
				var phone = document.getElementById('phone').value;
				 var telphone = document.getElementById('telphone').value;
				 var clerkPhone = document.getElementById('clerkPhone').value;
				 if(phone != "" && phone !=null){
					 if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(phone))){ 
					        layer.msg('手机号码有误，请重填');
					        return false; 
					    } 
				}
				  
				 if(telphone != "" && telphone !=null){
					 if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(telphone))){ 
					        layer.msg('联系方式有误，请重填');
					        return false; 
					    } 
				}
				 if(clerkPhone != "" && clerkPhone !=null){
					 if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(clerkPhone))){ 
					        layer.msg('材料员联系方式有误，请重填');
					        return false; 
					    } 
				}
				
				JY.Ajax.doRequest("auForm", bonuspath+'/backstage/project/insert', null, function(data){
					that.dialog("close");
					JY.Model.info(data.resMsg, function() {
						search();
					});
				});
			}
		});
	});
	// 初始化树
	getbaseList();
	
	$('#saveBtn').on('click', function(e) {
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){
			chks.push($(this).val());
		});
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!");
		}else{
			JY.Ajax.doRequest(null,bonuspath +'/backstage/user/updateUsers',{roleId:$("#roleId").val(),chks:chks.toString()},function(data){
				JY.Model.info(data.resMsg);
			});	
		}
	});
	
	// 选择省份触发
	$(document).on('change', '#provinceid', function() {
		var provinceId = $(this).val()
		if (provinceId) {
			$("#cityid").empty().append('<option value="">请选择城市</option>')
			$("#districtid").empty().append('<option value="">请选择区/县</option>')
			getCities(0, provinceId)
		} else {
			$("#cityid").empty().append('<option value="">请选择城市</option>')
			$("#districtid").empty().append('<option value="">请选择区/县</option>')
		}
	})


	$(document).on('change', '#cityid', function() {
		var cityId = $(this).val()
		if (cityId) {
			$("#districtid").empty().append('<option value="">请选择区/县</option>')
			getDistricts(0, cityId)
		} else {
			$("#districtid").empty().append('<option value="">请选择区/县</option>')
		}
	})
});


function checkProName(that){
	var name = $("#name").val();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/project/find',{name:name},function(data){
	    var res = data.res;
	    if(res == 1){
	    	JY.Model.info("该工程名称已删除，请重新输入");
	    }
	});
}
function getCompany(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/company/getCompany',null,function(data){
//		/alert(JSON.stringify(data));
		var l = data.obj.list;
		var str='<option value="0">分公司选择</option>';
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

function getProjectType(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/project/getProjectType',null,function(data){
//		/alert(JSON.stringify(data));
		var l = data.obj.list;
		var str='<option value="0"> 工程类别选择</option>';
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

function getVolLever(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/project/getVolLever',null,function(data){
//		/alert(JSON.stringify(data));
		var l = data.obj.list;
		var str='<option value="0">电压等级选择</option>';
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


function getbaseList(role){
	var companyId =$("#companyId").val();
	var typeId =$("#typeId").val()
	var volId =$("#volId").val()
	var isBal =$("#isBal").val();
	init = $(".pageNum").val();
	$(".pageNum").val(init);
	if (role == 1)$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/project/findByPage',{
		companyId:companyId,typeId:typeId,volId:volId,isBal:isBal
	},function(data){
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
            		 html+="<td style='vertical-align:middle;' class='center'>"+JY.Object.notEmpty(l.companyName)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center'>"+JY.Object.notEmpty(l.typeName)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center'>"+JY.Object.notEmpty(l.projectTime)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center'>"+JY.Object.notEmpty(l.kilometer)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center hidden-480' >"+JY.Object.notEmpty(l.manager)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center '>"+JY.Object.notEmpty(l.telphone)+"</td>";
            		 html+="<td style='vertical-align:middle;' class='center hidden-480'>"+JY.Object.notEmpty(l.phone)+"</td>";
            		 html+=rowFunction(l.id,l.companyId,l.typeId,l.volId);
            		 html+="</tr>";
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
        	 }else{
        		html+="<tr><td colspan='9' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }
    	 JY.Model.loadingClose();
	 });
}

function getOption(num){
	$("#isBalanceEnd").html("");
	var str ='';
	if(num==1){
		str+='<option selected="selected" value="0">否</option>';
		str+='<option value="1">是</option>'
	}else if(num==0){
		str+='<option value="0">否</option>';
		str+='<option selected="selected" value="1">是</option>'
	}else{
		str+='<option selected="selected" value="0">否</option>';
		str+='<option value="1">是</option>'
	}
		$("#isBalanceEnd").append(str);
}

function rowFunction(id,companyId,typeId,volId){//"+id+","+companyId+",\""+companyName+"\",
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='查看' onclick='check("+id+","+companyId+",+"+typeId+",+"+volId+")' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="<a href='#' title='修改' onclick='edit("+id+","+companyId+",+"+typeId+",+"+volId+")' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	h+="<a href='#' title='删除' onclick='del(&apos;"+id+"&apos;)' class='aBtnNoTD' ><i class='icon-remove-sign col bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}
function check(id,companyId,typeId,volId){
	cleanForm();
	/*JY.Ajax.doRequest(null,bonuspath +'/backstage/user/orgTree',null,function(data){*/
		//$.fn.zTree.init($("#orgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
		JY.Ajax.doRequest(null,bonuspath +'/backstage/project/find',{id:id},function(data){
		    setForm(data);
		    getCompany(companyId,0,"companyId1");
			getProjectType(typeId,0,"typeId1");
			getVolLever(volId,0,"volId1");
			
			getProvinces(data.obj[0].provinceid,0);
			getCities(data.obj[0].cityid,data.obj[0].provinceid)
			getDistricts(data.obj[0].areaid,data.obj[0].cityid)
		    JY.Model.check("auDiv");
		});
	/*});*/
}

function del(accountId){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/role/findUserRole',{},function(data){
		var res = data.obj;
		if(res != '1' && res != '20' && res != '35'){
			JY.Model.confirm("权限不够，无法删除！");
		}else{
			JY.Model.confirm("确认删除吗？",function(){	
				JY.Ajax.doRequest(null,bonuspath +'/backstage/project/del',{id:accountId},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});
		}
		
	});
}

function edit(id,companyId,typeId,volId){
	cleanForm();
		JY.Ajax.doRequest(null,bonuspath +'/backstage/project/find',{id:id},function(data){
		    setForm(data);   
		    getCompany(companyId,0,"companyId1");
			getProjectType(typeId,0,"typeId1");
			getVolLever(volId,0,"volId1");
			
			
			getProvinces(data.obj[0].provinceid,0);
			getCities(data.obj[0].cityid,data.obj[0].provinceid)
			getDistricts(data.obj[0].areaid,data.obj[0].cityid)
			
		    JY.Model.edit("auDiv","修改",function(){
		    	if(JY.Validate.form("auForm")){
					var that =$(this);
					 var phone = document.getElementById('phone').value;
					 var telphone = document.getElementById('telphone').value;
					 var clerkPhone = document.getElementById('clerkPhone').value;
					 if(phone != "" && phone !=null){
						 if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(phone))){ 
						        layer.msg('手机号码有误，请重填');
						        return false; 
						    } 
					}
					  
					 if(telphone != "" && telphone !=null){
						 if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(telphone))){ 
						        layer.msg('联系方式有误，请重填');
						        return false; 
						    } 
					}
					 if(clerkPhone != "" && clerkPhone !=null){
						 if(!(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/.test(clerkPhone))){ 
						        layer.msg('材料员联系方式有误，请重填');
						        return false; 
						    } 
					}
					JY.Ajax.doRequest("auForm",bonuspath +'/backstage/project/update',{id:id},function(data){
					    that.dialog("close");
					    JY.Model.info(data.resMsg,function(){search();});	
					});
				}	
		    });
		});
}
function cleanForm(){
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='num']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='manager']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='nature']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='telphone']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='phone']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='fax']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='address']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='orgName1']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='company']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='materialClerk']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='kilometer']").val(JY.Object.notEmpty(''));
	$("#auForm input[name$='clerkPhone']").val(JY.Object.notEmpty(''));
	$("#isBalanceEnd").val(JY.Object.notEmpty('0'));
	$("#auForm input[name$='remarks']").val(JY.Object.notEmpty(''));
}

function search() {
	getbaseList();
}
function setForm(data){
	//alert(JSON.stringify(data));
	var l=data.obj[0];
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
	$("#auForm input[name$='num']").val(JY.Object.notEmpty(l.num));
	$("#auForm input[name$='manager']").val(JY.Object.notEmpty(l.manager));
	$("#auForm input[name$='nature']").val(JY.Object.notEmpty(l.nature));
	$("#auForm input[name$='telphone']").val(JY.Object.notEmpty(l.telphone));
	$("#auForm input[name$='phone']").val(JY.Object.notEmpty(l.phone));
	$("#auForm input[name$='fax']").val(JY.Object.notEmpty(l.fax));
	$("#auForm input[name$='address']").val(JY.Object.notEmpty(l.address));
	$("#auForm input[name$='orgName1']").val(JY.Object.notEmpty(l.companyName));
	$("#auForm input[name$='company']").val(JY.Object.notEmpty(l.company));
	$("#auForm input[name$='materialClerk']").val(JY.Object.notEmpty(l.materialClerk));
	$("#auForm input[name$='kilometer']").val(JY.Object.notEmpty(l.kilometer));
	$("#auForm input[name$='clerkPhone']").val(JY.Object.notEmpty(l.clerkPhone));
	$("#auForm input[name$='detailed']").val(JY.Object.notEmpty(l.detailed)); 
	$("#isBalanceEnd").val(JY.Object.notEmpty(l.isBalanceEnd));
	$("#auForm input[name$='remarks']").val(JY.Object.notEmpty(l.remarks));
	/* getOption(l.isBalanceEnd);*/
}

//三级联动省
function getProvinces(num, name) {
	$("#provinceid").html("");
	JY.Ajax.doRequest(null, bonuspath + '/backstage/project/getProvinces',
			null, function(data) {
				var l = data.obj.list;
				var str = '<option value="0">省选择</option>';
				for (var i = 0; i < l.length; i++) {
					if (num == 0) {
						str += '<option value=' + l[i].id + '>' + l[i].name +'</option>';
					} else {
						if (num == l[i].id) {
							str += '<option selected="selected" value='+ l[i].id + '>' + l[i].name + '</option>';
						} else {
							str += '<option value=' + l[i].id + '>' + l[i].name + '</option>';
						}
					}
				}
				$("#provinceid").append(str);
			});
}

//三级联动市
function getCities(num, name) {
	$("#cityid").html("");
	JY.Ajax.doRequest(null, bonuspath + '/backstage/project/getCities', {
		provinceid : name
	}, function(data) {
		var l = data.obj.list;
		var str = '<option value="0">市选择</option>';
		for (var i = 0; i < l.length; i++) {
			if (num == 0) {
				str += '<option value=' + l[i].id + '>' + l[i].name + '</option>';
			} else {
				if (num == l[i].id) {
					str += '<option selected="selected" value=' + l[i].id + '>' + l[i].name + '</option>';
				} else {
					str += '<option value=' + l[i].id + '>' + l[i].name + '</option>';
				}
			}
		}
		$("#cityid").append(str);
	});
}

//三级联动区县
function getDistricts(num, name) {
	$("#areaid").html("");
	JY.Ajax.doRequest(null, bonuspath + '/backstage/project/getDistricts', {
		cityid : name
	}, function(data) {
		var l = data.obj.list;
		var str = '<option value="0">市选择</option>';
		for (var i = 0; i < l.length; i++) {
			if (num == 0) {
				str += '<option value=' + l[i].id + '>' + l[i].name + '</option>';
			} else {
				if (num == l[i].id) {
					str += '<option selected="selected" value=' + l[i].id + '>' + l[i].name + '</option>';
				} else {
					str += '<option value=' + l[i].id + '>' + l[i].name + '</option>';
				}
			}
		}
		$("#areaid").append(str);
	});
}