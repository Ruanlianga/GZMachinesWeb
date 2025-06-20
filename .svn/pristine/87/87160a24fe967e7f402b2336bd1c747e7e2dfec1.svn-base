$(function() {  
	
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
		laydate.render({
			elem: '#nowchecktime',
			max : minDate(),
		});
		laydate.render({
			elem: '#nextchecktime',
			min : minDate(), 
			trigger:'click'
		});
	
	});
	
	getbaseList(1);
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});

});
function minDate(){
    var now = new Date();
    return now.getFullYear()+"-" + (now.getMonth()+1) + "-" + now.getDate();
}
 

/**
 * @author 无畏
 * @date 2019-05-20
 * @function 直转单主页面的关键字,条件查询
 * @returns 
 */
function search(){
	$("#searchBtn").trigger("click")
}

/**
 * @author 无畏
 * @date 2019-04-26
 * @function 刷新直转单页面
 * @returns 
 */
function refreshPage(){
	var pageNum = $("#baseForm .pageNum").eq(0).val();
	getbaseList(pageNum);
}



/**
 * @author 无畏
 * @date 2019-04-27
 * @function 获取工程表单信息
 * @returns params对象
 */
function getParams(){
	var params = {};
	params.backAgreementCode = $("#backAgreementCode").val(); 
	params.collerAgreementCode = $("#collerAgreementCode").val();
	params.isTrue = 1;
	params.backerPhone = $("#backerPhone").val();
	params.collerPhone = $("#collerPhone").val();
	params.backerName = $("#backerName").val();
	params.collerName = $("#collerName").val();
	params.id = $("#directAssignId").val();
	params.imgPath = $("#imgPath").val();
	return params;
} 



/**
 * @author 无畏
 * @date 2019-04-27
 * @function 根据工程id 获取协议号
 * @returns 
 */
function getAgreementNum(f) {
	var unitId = $("#backUnitId").val();
	var projectId = $("#backProjectId").val();
	if(f == 2){
		unitId = $("#collerUnitId").val();
		projectId = $("#collerProjectId").val();
	}
	$.ajax({
		type:'POST',
		url:bonuspath+'/backstage/machineReceive/findAgreeCode',
		data:{leaseCompany : unitId,
			projectName : projectId},
		dataType:'json',
		async:false,
		success:function(data) {
			var l = data.resMsg;
			if (l == null) {
				$("#agreementCode").val("尚未签订协议");
			} else {
				if(f==1){
					$("#backAgreementCode").val(l);
					$("#subSearchBtn").attr("onclick","getMachineList(1)");
				}else{
					$("#collerAgreementCode").val(l);
				}
			}
		}
	});
}
/**
 * @author 无畏
 * @date 2019-04-24
 * @function 获得直转单首页列表
 * @returns 
 */
function getbaseList(init) {
	if (init == 1)
		$("#baseForm .pageNum").eq(0).val(1);
	JY.Model.loading();
	JY.Ajax.doRequest(
					"baseForm",
					bonuspath + '/backstage/checkWarning/findByPage',
					null,
					function(data) {
						$("#baseTable tbody").empty();
						var obj = data.obj;
						var results = obj.results;
						console.log(results);
						var permitBtn = obj.permitBtn;
						var pageNum = obj.pageNum, pageSize = obj.pageSize, totalRecord = obj.totalRecord;
						var html = "";
						var len;
						
						if (results != null && results.length > 0) {
							var	len = results.length;
							var leng = (pageNum - 1) * pageSize;
							for (var i = 0; i < len; i++){
								var l = results[i];
								html += "<tr ondblclick = 'setCheck("+l.id+")' style='color:"+l.level+";' >";
								html += "<td style='vertical-align:middle;' class='center hidden-480 checkoutfocus'>"
									+ (i + leng + 1) + "、<input type='checkbox' onclick='getfocus(&apos;"+l.deviceCode+"&apos;)' name='test' id='inp"+l.id+"' class='inp' value='"+l.id+"' />" 
								
								html += "<td style='vertical-align:middle;' class='center'><span title='"+JY.Object.notEmpty(l.agreementCode)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.agreementCode)+"</td>";
								html += "<td style='vertical-align:middle;' class='center'><span title='"+JY.Object.notEmpty(l.projectName)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.projectName)+"</td>";
								html += "<td style='vertical-align:middle;' class='center'><span title='"+JY.Object.notEmpty(l.unitName)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.unitName)+"</td>";
								html += "<td style='vertical-align:middle;' class='center'><span title='"+JY.Object.notEmpty(l.remarks)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.remarks)+"</td>";
								html += "<td style='vertical-align:middle;' class='center'><span title='"+JY.Object.notEmpty(l.maName)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.maName)+"</td>";
								html += "<td style='vertical-align:middle;' class='center'><span title='"+JY.Object.notEmpty(l.maType)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.maType)+"</td>";
								html += "<td style='vertical-align:middle;' class='center'><span title='"+JY.Object.notEmpty(l.deviceCode)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.deviceCode)+"</td>";
								html += "<td style='vertical-align:middle;background-color:#FF4500;' class='center'>";
								html += "<span style='background-color:#FF4500;color:#fff' title='"+JY.Object.notEmpty(l.nextCheckTime)+"' cursor:pointer;>"
										+ JY.Object.notEmpty(l.nextCheckTime) + "</span>";
								html += "</td>";
								html += rowFunction(l.id,l.deviceCode,l.nextCheckTime,l.thisCheckTime);
								html += "</tr>";
							}
							
							$("#baseTable tbody").append(html);
							
							JY.Page.setPage("baseForm", "pageing", pageSize,
									pageNum, totalRecord , "getbaseList");
						} else {
							html += "<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}

/**
 * @author 无畏
 * @date 2019-04-25
 * @function 首页列表操作按钮
 * @returns 
 */
function rowFunction(id,deviceCode,nextCheckTime,thisCheckTime){
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
		
	h+="<a href='#' title='修改' onclick='editbyid(&apos;"+id+"&apos;,&apos;"+nextCheckTime+"&apos;,&apos;"+thisCheckTime+"&apos;)' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
	//h += "<a href='#' title='通知' onclick='inform(&apos;"+id+"&apos;,&apos;"+deviceCode+"&apos;,&apos;"+nextCheckTime+"&apos;)'  class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}

function unitTree(){
	//localStorage.setItem("isOpen","0");
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
		content: bonuspath+'/backstage/company/unitTreePlus'
	});
}
function setUnitForm(){
	var unitId = localStorage.getItem("unitId");
	var unitName = localStorage.getItem("unitName");
	$("#unitId").val(unitId);
	$("#unitName").val(unitName);
}
function getAgreementNum(){
	var unitId = $("#unitId").val();
	var projectId = $("#projectId").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineReceive/findAgreeCode',
			{leaseCompany:unitId,projectName:projectId}, function(data) {
				var l = data.resMsg;
				if(l == null){
					$("#agreementCode").val("尚未签订协议");
				}else{
					$("#agreementCode").val(l);
				}
			}
	);
}
function setProjectForm(){
	var projectId = localStorage.getItem("projectId");
	var projectName = localStorage.getItem("projectName");
	$("#projectId").val(projectId);
	$("#projectName").val(projectName);
	getAgreementNum();
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

/**
 * @author 无畏
 * @date 2019-04-27
 * @function 删除相关的直转单
 * @returns
 */
function del(id){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,bonuspath +'/backstage/directAssign/delete',{id:id},function(data){
			JY.Model.info(data.resMsg,function(){
				refreshPage();});
		});
	});
}
   arr=[];
function getfocus(deviceCode){
	var a=0;
	   for(var i in arr) {
	      if([arr[i]]==deviceCode){
	    	  arr.splice(jQuery.inArray('deviceCode',arr),1);
	    	  a=1;
	      }
	  }
	   if(a==0){
		   arr. push(deviceCode);
	   }
    $("#content").val("各位同事你们好：设备编码为"+arr+"请注意下次检修时间即将到期")
}


function selectAll(){
	
}

/**
 * @author 无畏
 * @date 2019-04-30
 * @function 增加tr整行的双击选择事件
 * @returns  
 */
function setCheck(idNum){
	if($("#inp"+idNum).is(':checked')){
		$("#inp"+idNum).prop("checked", false);
	}else{
		$("#inp"+idNum).prop("checked", true);
	}
}

/**
 * @author 无畏
 * @date 2019-04-28
 * @function 阻止冒泡事件
 * @returns  
 */
function stopEvent(e){
	if (e && e.stopPropagation){
		e.stopPropagation();
	}else{
		window.event.cancelBubble = true;
	}
}

/**
 * @author 无畏
 * @date 2019-05-06
 * @function 回调函数 上传图片页面调用显示图片
 * @returns  
 */
function callBack(fileName){
	fileName = fileName.replace(/\"/g, "");
	$("#imgPath").val(fileName);
	var path=bonuspath+"/directImg/"+fileName;
	$("#img").attr("src",path);
	$("#img").attr("ondblclick","bigPic()");
}


function isNotNull(str){
	if(str ==undefined || str ==null || str =='null' ||str ==''|| str =='undefined'){
		return false;
	}
	return true;
}
  check_val=[];
  //一键修复
 function alter(){
	 //$(".FormData1").show();
	 check_val.splice(0,check_val.length);
	 obj=document.getElementsByName("test");
	 for(k in obj){
		 if(obj[k].checked)
			 check_val.push(obj[k].value);
	 }
	 if(check_val.length==0){
		 alert("请选择要修改的序号")
		 return;
	 }else{
		  open();
	 }
} 
 //通知
/* function inform(id,deviceCode,nextCheckTime){
	 $("#hiddenid").val(id);
	 noticeContent = "各位同事您好，下次的检修时间是:" +nextCheckTime+"；设备编号为:"+deviceCode;
	 $("#content").val(noticeContent);
	 JY.Model.edit("auDiv1","通知",function(){
	    	if(JY.Validate.form("auForm1")){
	    		 var that =$(this);
				JY.Ajax.doRequest("auForm1",bonuspath +'/backstage/checkWarning/sendNotice',null,function(data){
					that.dialog("close");
				    JY.Model.info(data.resMsg,function(){
				     
				    });	
				});
			}	
	    }); 
 
 }*/
 //单独的修改
 function editbyid(id,nextCheckTime,thisCheckTime){
	 //$(".FormData1").hide();
	 check_val.splice(0,check_val.length);
	 $("#nowchecktime").val(thisCheckTime);
	 $("#nextchecktime").val(nextCheckTime);
	 check_val.push(id);
	 open();
 }
 function open(){
	 JY.Model.edit("auDiv","修改检修时间",function(){
	    	if(JY.Validate.form("auForm")){
				var that =$(this);
				JY.Ajax.doRequest("auForm",bonuspath +'/backstage/checkWarning/updatearray',{check_val:check_val},function(data){
					//alert("data="+JSON.stringify(data));
					that.dialog("close");
				    JY.Model.info(data.resMsg,function(){
				    	setLoad();});	
				});
			}	
	    });
 }
 function setLoad(){
		$("#nowchecktime").val();
		$("#nextchecktime").val();
		 window.location.reload();
	 
	}
 
	 
 
 
