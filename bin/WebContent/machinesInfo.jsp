<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Insert title here</title>
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
.left{
	width: 31.5%;
	height: 100%;
	border: 1px solid grey;
	margin: 0 0.5%;
	float: left;
}
.right{
	width: 67%;
	height: 95%;
	margin-top:2%;
	border: 1px solid grey;
	float: right;
}
.machinesImg{
	width: 90%;
	height: 50%;
	margin: 50% 5%;
	padding: 0;
}
.ldetails{
	width: 49%;
	height: 100%;
	float: left;
}
.rdetails{
	width: 49%;
	height: 100%;
	float: right;
}
.ltr{
	height: 10%;
}
.ltd{
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
<body>
<script type="text/javascript">
	var machinesId = localStorage.getItem("machinesId");
	var bonuspath = localStorage.getItem("bonuspath");
	findMachinesInfo();
	function findMachinesInfo(){
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machines/find",
			data: {
				machinesId : machinesId
			},
			dataType: "json",
			success: function(data) {
				var l = data.obj;
				var machinesUrl = l.machinesUrl;
				$("#main").html("");
				var str='';
				if(machinesUrl == '' || machinesUrl == null){
					str+='<div class="left" style="color:red;font-size:18px;">暂无机具图片</div>';
				}else{
					str+='<div class="left"><img class="machinesImg" src=\"'+bonuspath+'/machinesImg/'+ machinesUrl +'\" /></div>';
				}
				str+='<div class="right"> <table class="ldetails">'+
				'<tr class="ltr"><td class="ltd">机具名称</td><td class="ldata">'+ JY.Object.notEmpty(l.machinesName) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">机具类型</td><td class="ldata">'+ JY.Object.notEmpty(l.machinesType) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">生产厂家</td><td class="ldata">'+ JY.Object.notEmpty(l.manufacturer) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">机具型号</td><td class="ldata">'+ JY.Object.notEmpty(l.model) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">出厂时间</td><td class="ldata">'+ JY.Object.notEmpty(l.delyveryTime) +'</td></tr>';
				if(l.outNum == null || l.outNum == 'null'){
					str+='<tr class="ltr"><td class="ltd">出厂编号</td><td class="ldata"><input id="outNum" type="text" /><span onclick="writeNum(&apos;'+ machinesId +'&apos;)">提交</span></td></tr>';
				}else{
					str+='<tr class="ltr"><td class="ltd">出厂编号</td><td class="ldata">'+ JY.Object.notEmpty(l.outNum) +'</td></tr>';
				}
				str+='<tr class="ltr"><td class="ltd">设备编码</td><td class="ldata">'+ JY.Object.notEmpty(l.deviceNum) +'</td></tr>';
				if(l.deviceStatus == "1" || l.deviceStatus == 1){
					str += '<tr class="ltr"><td class="ltd">设备状态</td><td class="ldata">完好</td></tr>';
				}else if(l.deviceStatus == "0" || l.deviceStatus == 0){
					str += '<tr class="ltr"><td class="ltd">设备状态</td><td class="ldata">损坏</td></tr>';
				}
				str+='</table><div class="line"></div>';
				str+='<table class="rdetails">'+
				'<tr class="ltr"><td class="ltd">资产编号</td><td class="ldata">'+ JY.Object.notEmpty(l.assetNum) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">本次检修人员</td><td class="ldata">'+ JY.Object.notEmpty(l.thisServiceman) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">本次检修时间</td><td class="ldata">'+ JY.Object.notEmpty(l.thisServicetime) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">下次检修时间</td><td class="ldata">'+ JY.Object.notEmpty(l.nextServicetime) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">出入库次数</td><td class="ldata">'+ JY.Object.notEmpty(l.cycleNum) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">主要参数</td><td class="ldata">'+ JY.Object.notEmpty(l.url) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">注意事项</td><td class="ldata">'+ JY.Object.notEmpty(l.notices) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">备注</td><td class="ldata">'+ JY.Object.notEmpty(l.notes) +'</td></tr>'+
				'</table> </div>'
				$("#main").append(str);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("未连接到服务器，请检查网络！");
			}
		});
	}
	
	function writeNum(machinesId){
		var outNum = $("#outNum").val()
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machines/updateOutNum",
			data: {
				machinesId : machinesId,
				outNum : outNum
			},
			success: function(data) {
				//alert("data="+JSON.stringify(data));
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index); //疯狂模式，关闭所有层
				window.parent.location.reload(); //刷新父页面
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		});
	}
</script>
	<div id="main" class="main">
	
	</div>
</body>
</html>