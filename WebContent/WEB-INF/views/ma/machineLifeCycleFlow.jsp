<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bonus.core.DateTimeHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="bonuspath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${bonuspath}/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/js/ace/jy.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<title>Insert title here</title>
<%
	String outFactortTime = DateTimeHelper.getNowDate();
%>
</head>
<style>
html,body{
	width:100%;
	height:100%;
	margin: 0;
	padding: 0;
}
.main{
	width: 99%;
	height: 99%;
	margin: 0;
	padding: 0;
}
.right{
	width: 100%;
	height: 95%;
	margin-top:2%;
	border: 1px solid grey;
	float: right;
}
.title{
	/* width: 18%; */
}
.scrap{
	width: 16%;
	height: 100%;
	float: left;
}
.delivery{
	margin-left: 2%;
	width: 16%;
	height: 100%;
	float: left;
}
.warehousing{
	width: 16%;
	height: 100%;
	float: left;
}
.repair{
	width: 16%;
	height: 100%;
	float: left;
}
.test{
	width: 16%;
	height: 100%;
	float: left;
}
.ltr{
	height: 10%;
}
.title{
	width:50px;
	font-weight: 600;
	font-size: 20px;
	margin-left: 10px;
}
.ltd{
	width:50px;
	color: #28A3DA;
	margin-left: 10px;
}
.line{
	width: 0.1%;
	height: 93%;
	background-color: gray;
	margin-top: 3%;
	float: left;
}

</style>



<script type="text/javascript">
	var deviceCode = localStorage.getItem("deviceCode");
	var bonuspath = localStorage.getItem("bonuspath");
	findMachinesInfo();
	function findMachinesInfo(){
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machine/machineLifeCycle",
			data: {
				deviceCode : deviceCode
			},
			dataType: "json",
			success: function(data) {
				var pickingName,pickingOutTime,pickingProName,pickingUnitName;
				var materialReturnName,materialReturnOutTime,materialReturnProName,materialReturnUnitName;
				var repairName,repairOutTime,repairProName,repairUnitName;
				var overhaulName,overhaulOutTime,overhaulProName,overhaulUnitName;
				var warehousingName,warehousingOutTime,warehousingProName,warehousingUnitName;
				var scrapName,scrapOutTime,scrapProName,scrapUnitName;
				
				
				var l1 = data.listPicking.obj;
				var l2 = data.listMaterialReturn.obj;
				var l3 = data.listRepair.obj;
				var l4 = data.listOverhaul.obj;
				var l5 = data.listWarehousing.obj;
				var l6 = data.listScrap.obj;
				
				if(l1 != null){
					pickingName = JY.Object.notEmpty(l1.name);
					pickingOutTime = JY.Object.notEmpty(l1.outTime);
					pickingProName = JY.Object.notEmpty(l1.proName);
					pickingUnitName = JY.Object.notEmpty(l1.unitName);
				}else{
					pickingName = "";
					pickingOutTime = "";
					pickingProName = "";
					pickingUnitName = "";
				}
				if(l2 != null){
					materialReturnName = JY.Object.notEmpty(l2.name);
					materialReturnOutTime = JY.Object.notEmpty(l2.outTime);
					materialReturnProName = JY.Object.notEmpty(l2.proName);
					materialReturnUnitName = JY.Object.notEmpty(l2.unitName);
				}else{
					materialReturnName = "";
					materialReturnOutTime = "";
					materialReturnProName = "";
					materialReturnUnitName = "";
				}
				if(l3 != null){
					repairName = JY.Object.notEmpty(l3.name);
					repairOutTime = JY.Object.notEmpty(l3.outTime);
					repairProName = JY.Object.notEmpty(l3.proName);
					repairUnitName = JY.Object.notEmpty(l3.unitName);
				}else{
					repairName = "";
					repairOutTime = "";
					repairProName = "";
					repairUnitName = "";
				}
				if(l4 != null){
					overhaulName = JY.Object.notEmpty(l4.name);
					overhaulOutTime = JY.Object.notEmpty(l4.outTime);
					overhaulProName = JY.Object.notEmpty(l4.proName);
					overhaulUnitName = JY.Object.notEmpty(l4.unitName);
				}else{
					overhaulName = "";
					overhaulOutTime = "";
					overhaulProName = "";
					overhaulUnitName = "";
				}
				if(l5 != null){
					warehousingName = JY.Object.notEmpty(l5.name);
					warehousingOutTime = JY.Object.notEmpty(l5.outTime);
					warehousingProName = JY.Object.notEmpty(l5.proName);
					warehousingUnitName = JY.Object.notEmpty(l5.unitName);
				}else{
					warehousingName = "";
					warehousingOutTime = "";
					warehousingProName = "";
					warehousingUnitName = "";
				}
				if(l6 != null){
					scrapName = JY.Object.notEmpty(l6.name);
					scrapOutTime = JY.Object.notEmpty(l6.outTime);
					scrapProName = JY.Object.notEmpty(l6.proName);
					scrapUnitName = JY.Object.notEmpty(l6.unitName);
				}else{
					scrapName = "";
					scrapOutTime = "";
					scrapProName = "";
					scrapUnitName = "";
				}
				$("#main").html("");
				var str='';
				str+='<div class="right"> <table class="delivery">'+
				'<tr class="ltr"><td class="title">出库</td></tr>'+
				'<tr class="ltr"><td class="ltd">出库人</td><td class="ldata">'+ pickingName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">出库时间</td><td class="ldata">'+ pickingOutTime +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">项目名称</td><td class="ldata">'+ pickingProName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">单位名称</td><td class="ldata">'+ pickingUnitName +'</td></tr>'+
				'</table>';
				
				str+='</table><div class="line"></div>';
				
				str+='<table class="warehousing">'+
				'<tr class="ltr"><td class="title">退料</td></tr>'+
				'<tr class="ltr"><td class="ltd">退料人</td><td class="ldata">'+ materialReturnName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">退料时间</td><td class="ldata">'+ materialReturnOutTime +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">项目名称</td><td class="ldata">'+ materialReturnProName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">单位名称</td><td class="ldata">'+ materialReturnUnitName +'</td></tr>'+
				'</table>';
				
				str+='</table><div class="line"></div>';
				
				str+='<table class="repair">'+
				'<tr class="ltr"><td class="title">维修</td></tr>'+
				'<tr class="ltr"><td class="ltd">维修人</td><td class="ldata">'+ repairName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">维修时间</td><td class="ldata">'+ repairOutTime +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">项目名称</td><td class="ldata">'+ repairProName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">单位名称</td><td class="ldata">'+ repairUnitName +'</td></tr>'+
				'</table>';
				
				str+='</table><div class="line"></div>';
				
				str+='<table class="test">'+
				'<tr class="ltr"><td class="title">检验</td></tr>'+
				'<tr class="ltr"><td class="ltd">检验人</td><td class="ldata">'+ overhaulName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">检验时间</td><td class="ldata">'+ overhaulOutTime +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">项目名称</td><td class="ldata">'+ overhaulProName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">单位名称</td><td class="ldata">'+ overhaulUnitName +'</td></tr>'+
				'</table>'
				
				str+='</table><div class="line"></div>';
				
				str+='<table class="warehousing">'+
				'<tr class="ltr"><td class="title">入库</td></tr>'+
				'<tr class="ltr"><td class="ltd">入库人</td><td class="ldata">'+ warehousingName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">入库时间</td><td class="ldata">'+ warehousingOutTime +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">项目名称</td><td class="ldata">'+ warehousingProName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">单位名称</td><td class="ldata">'+ warehousingUnitName +'</td></tr>'+
				'</table>';
				str+='</table><div class="line"></div>';
				
				str+='<table class="scrap">'+
				'<tr class="ltr"><td class="title">报废</td></tr>'+
				'<tr class="ltr"><td class="ltd">报废人</td><td class="ldata">'+ scrapName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">报废时间</td><td class="ldata">'+ scrapOutTime +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">项目名称</td><td class="ldata">'+ scrapProName +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">单位名称</td><td class="ldata">'+ scrapUnitName +'</td></tr>';
				str+='</table></div>';
				
				$("#main").append(str);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("未连接到服务器，请检查网络！");
			}
		});
	}
function hisView(){
		localStorage.setItem("deviceCode",deviceCode);
		localStorage.setItem("bonuspath",bonuspath);
		//iframe层-父子操作s
		layer.open({
		  type: 2,
		  title:['机具历史记录','background-color: #27A3D9;color:#fff'],
		  shadeClose:true,
		  shade:false,
		  maxmin: true,
		  area: ['1200px', '450px'],
		  content: bonuspath+'/backstage/machine/findhistory'
		});
		
}
</script>


	<div id="main" class="main">
	</div>
</body>
</html>