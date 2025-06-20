var code =localStorage.getItem("code");
$(function(){
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
	getTypeName(0,0,"typeNameId");
	getDeviceModel(0,0,"deviceModel");
	getDeviceCode(0,0,"deviceCode");
	getCode(code,0,"code");
});

function reseData(startTime,endTime) {
   $("#keyWord").val(""); 
   $("#typeNameId").val("");
   $("#deviceModel").val("");
   $("#deviceCode").val("");
   $("#code").val(code);
   $("#startTime").val(startTime);
   $("#endTime").val(endTime);
   getbaseList();
}

function getbaseList(){
	$("#baseForm.pageNum").val();
	var c = document.getElementById("code").value;
	if(c != null &&  c !=''){
		code = c;
	}
	JY.Model.loading();
	//地址需更换,条件需更换
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/position/findOldGpsData',{code:code},function(data){
		 $("#baseTable tbody").empty();
			var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			/*var permitBtn = obj.permitBtn;*/
			var pageNum = list.pageNum,
				pageSize = list.pageSize,
				totalRecord = list.totalRecord;
        	 var html="";
    		 if(results!=null && results.length>0){
	         var leng = (pageNum - 1) * pageSize;
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td style='vertical-align: middle' class='center hidden-480'>"+(i + leng + 1)+"</td>";
					//基础数据,待填(部分字段需确认)
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.code)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.typeName)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.deviceModel)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.deviceCode)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.lon)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.lat)+"</td>";
	 				 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.upTime)+"</td>";
						var posTypeInfo = l.posType;
						if(posTypeInfo =="LBS" || posTypeInfo =="lbs"){
							posTypeInfo = "基站定位";
						}
						if(posTypeInfo =="GPS" || posTypeInfo =="gps"){
							posTypeInfo = "卫星定位"; 
						}
						if(posTypeInfo =="WIFI" || posTypeInfo =="wifi"){
							posTypeInfo = "WIFI定位";
						}
						if(posTypeInfo =="BEACON" || posTypeInfo =="beacon"){
							posTypeInfo = "蓝牙定位";
						}
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(posTypeInfo)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.address)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.useUnit)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.useTime)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.proName)+"</td>";
            		 html+="</tr>";		  
            	 }
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
        	 }else{
        		html+="<tr><td colspan='13' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
 	 
    	 JY.Model.loadingClose();
	 });
}

function getTypeName(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/position/getTypeName',null,function(data){
		var l = data.obj.list;
		var str='';
		str+='<option value="">-设备类型-</option>';
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
function getDeviceModel(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/position/getDeviceModel',null,function(data){
		var l = data.obj.list;
		var str='<option value="">-规格型号-</option>';
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
function getDeviceCode(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/position/getDeviceCode',null,function(data){
		var l = data.obj.list;
		var str='<option value="">-设备编码-</option>';
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

function getCode(num,name,selectId){
	$("#"+selectId).html("");
	JY.Ajax.doRequest(null,bonuspath +'/backstage/position/getCodes',null,function(data){
		var l = data.obj.list;
		var str='<option value="">-GPS编码-</option>';
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

function exportOldData(){
	var c = document.getElementById("code").value;
	if(c != null &&  c !=''){
		code = c;
	}
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/position/exportOldGPS?codes='+code);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}

