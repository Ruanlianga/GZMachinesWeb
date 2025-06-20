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
	height: 80%;
	
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
	var isFixedAssets = localStorage.getItem("isFixedAssets");
	var typeId = localStorage.getItem("typeId");
	
	findMachinesInfo();
	function findMachinesInfo(){
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machine/find",
			data: {
				id : machinesId,
				typeId : typeId
			},
			dataType: "json",
			success: function(data) {
				var l = data.obj;
				var machinesUrl = l.picUrl;
				var certificateFilePath = l.filePath;
				var machineNewUrl = l.machineNewUrl;
				if(machinesUrl!=null){
					machineNewUrl = machinesUrl;
				}
				
				var preUrl = bonuspath+'/filePath/machineImg/';
				if(machinesUrl!=null){
					machineNewUrl = machinesUrl;
					preUrl = bonuspath+'/filePath/newMachineImg/';
				}
				
				$("#main").html("");
				var str='';
				if((machineNewUrl == '' || machineNewUrl == null) && (certificateFilePath == '' || certificateFilePath == null)){
					str += '<div class="left">';
					str+='<div style="color:red;font-size:18px; height: 49.5%;">暂无机具图片</div>';
					str+='<div style="color:red;font-size:18px; height: 49.5%; border-top: 1px solid #808080;">暂无合格证标签图片</div>';
					str += '</div>';
				}else if((machineNewUrl == '' || machineNewUrl == null) && (certificateFilePath != '' && certificateFilePath != null)){
					var filePath = JY.Object.notEmpty(l.filePath);
					var path = l.filePath.replaceAll(/\\/g,"/");
					var certificateFilePath =bonuspath + '/backstage/machine/downFile?headerUrl='+path;
					str += '<div class="left">';
					str+='<div style="color:red;font-size:18px; height: 49.5%;">暂无机具图片</div>';
					str+='<div style="height: 49.5%; border-top: 1px solid #808080;"><img class="machinesImg" src=\"' + certificateFilePath + '\" /></div>';
					str += '</div>';
				}
				if((machineNewUrl != '' && machineNewUrl != null) && (certificateFilePath == '' || certificateFilePath == null)){
					str += '<div class="left">';
					str+='<div style="height: 49.5%;"><img class="machinesImg" src=\"'+preUrl+ machineNewUrl +'\" /></div>';
					str+='<div style="color:red;font-size:18px; height: 49.5%;">暂无合格证标签图片</div>';
					str += '</div>';
				}else if((machineNewUrl != '' && machineNewUrl != null) && (certificateFilePath != '' && certificateFilePath != null)){
					var filePath = JY.Object.notEmpty(l.filePath);
					var path = l.filePath.replaceAll(/\\/g,"/");
					var certificateFilePath =bonuspath + '/backstage/machine/downFile?headerUrl='+path;
					str += '<div class="left">';
					str+='<div style="height: 49.5%;"><img class="machinesImg" src=\"'+preUrl+ machineNewUrl +'\" /></div>';
					str+='<div style="height: 49.5%; border-top: 1px solid #808080;"><img class="machinesImg" src=\"' + certificateFilePath + '\" /></div>';
					str += '</div>';
				}
				str+='<div class="right"> <table class="ldetails">'+
				'<tr class="ltr"><td class="ltd">机具类型</td><td class="ldata">'+ JY.Object.notEmpty(l.type0) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">规格型号</td><td class="ldata">'+ JY.Object.notEmpty(l.type) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">生产厂家</td><td class="ldata">'+ JY.Object.notEmpty(l.venderName) +'</td></tr>';
				/* '<tr class="ltr"><td class="ltd">机具名称</td><td class="ldata">'+ JY.Object.notEmpty(l.type0) +'</td></tr>'; */
				str+='<tr class="ltr"><td class="ltd">出厂时间</td><td class="ldata">'+JY.Object.notEmpty(l.outFactortTime)+'</td></tr>';
				/* if(l.outFactortTime== '' || l.outFactortTime== null){
				str+='<tr class="ltr"><td class="ltd">出厂时间</td><td class="ldata"><input id="outFactortTime" value="'+JY.Object.notEmpty(l.outFactortTime)+'" type="text" /><span style="cursor: pointer;" onclick="addBuyTime(&apos;'+ machinesId +'&apos;)">提交</span></td></tr>';
				}else{
				str+='<tr class="ltr"><td class="ltd">出厂时间</td><td class="ldata">'+ JY.Object.notEmpty(l.outFactortTime) +'</td></tr>';
				} */
				str+='<tr class="ltr"><td class="ltd">出厂编号</td><td class="ldata">'+JY.Object.notEmpty(l.outFactortNum)+'</td></tr>';
				str+='<tr class="ltr"><td class="ltd">设备编码</td><td class="ldata">'+JY.Object.notEmpty(l.deviceCode)+'</td></tr>';
				str+='<tr class="ltr"><td class="ltd">固资编号</td><td class="ldata">'+JY.Object.notEmpty(l.assetNum)+'</td></tr>';
				str+='<tr class="ltr"><td class="ltd">购置价格</td><td class="ldata">'+JY.Object.notEmpty(l.buyPrice)+'</td></tr>';
				str+='<tr class="ltr"><td class="ltd">本次检验员</td><td class="ldata">'+ JY.Object.notEmpty(l.thisCheckMan) +'</td></tr>';
				str+='</table><div class="line"></div>';
				
				
				str+='<table class="rdetails">';
				str+='<tr class="ltr"><td class="ltd">本次检修时间</td><td class="ldata">'+JY.Object.notEmpty(l.thisCheckTime)+'</td></tr>';
				str+='<tr class="ltr"><td class="ltd">下次检修时间</td><td class="ldata">'+JY.Object.notEmpty(l.nextCheckTime)+'</td></tr>';
				str+='<tr class="ltr"><td class="ltd">出入库次数</td><td class="ldata">'+ JY.Object.notEmpty(l.outInNum) +'</td></tr>';
				str+='<tr class="ltr"><td class="ltd">产权部门</td><td class="ldata">'+ JY.Object.notEmpty(l.propertyDepartment) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">使用部门</td><td class="ldata">'+ JY.Object.notEmpty(l.useDepartment) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">使用年限</td><td class="ldata">'+ JY.Object.notEmpty(l.serviceLife) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">制单日期</td><td class="ldata">'+ JY.Object.notEmpty(l.makeOrderDate) +'</td></tr>'+
				/* '<tr class="ltr"><td class="ltd">主要参数</td><td class="ldata">'+ JY.Object.notEmpty(l.url) +'</td></tr>'+  */
				/* '<tr class="ltr"><td class="ltd">注意事项</td><td class="ldata">'+ JY.Object.notEmpty(l.notices) +'</td></tr>'+ */
				'<tr class="ltr"><td class="ltd">发票日期</td><td class="ldata">'+ JY.Object.notEmpty(l.invoiceDate) +'</td></tr>'+
				'<tr class="ltr"><td class="ltd">备注</td><td class="ldata">'+JY.Object.notEmpty(l.remarks)+'</td></tr>';
				$("#main").append(str);
				
				/* if(l.isFixedAssets == 0 || l.isFixedAssets =='0'){
					$("#assetNum").attr("readOnly","true");
					$("#assetNum").css("background","#ccc");
				} */
				layui.use('laydate', function(){
					var laydate = layui.laydate;
					  //日期时间选择器
					laydate.render({
						elem: '#thisCheckTime'
					});
					laydate.render({
						elem: '#nextCheckTime'
					});
					laydate.render({
						elem: '#outFactortTime'
					});
				});
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("未连接到服务器，请检查网络！");
			}
		});
	}
	
	function updSubmit(id){ //本次检修时间 、下次检修、备注
		var thisCheckTime = $("#thisCheckTime").val();
		var nextCheckTime = $("#nextCheckTime").val();
		var outFactortTime = $("#outFactortTime").val();
		var outFactortNum = $("#outFactortNum").val();
		var deviceCode = $("#deviceCode").val();
		var buyPrice = $("#buyPrice").val();
		
		var assetNum = $("#assetNum").val();
		var remarks = $("#remarks").val();
		$.ajax({
			type:"post",
			url:bonuspath +"/backstage/machine/update",
			data:{
				id : machinesId,
				thisCheckTime : thisCheckTime,
				nextCheckTime : nextCheckTime,
				outFactortTime : outFactortTime,
				outFactortNum : outFactortNum,
				deviceCode : deviceCode,
				buyPrice : buyPrice,
				assetNum : assetNum,
				remarks : remarks
			},
			success:function(data){
				var res = JSON.parse(data);
				layer.open({
				    content: res,
				    btn: '确定',
				    yes:function(index){
				    	var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index); //疯狂模式，关闭所有层
						window.parent.setLoad();
				    }
				});
			},
			error:function(XMLHttpRequest, textStatus,errorThrown){
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		})
		
	}
	
	
	
	
	
	
	function writeclerk(id){
		var clerk = $("#clerk").val();
		if(clerk.length > 0){
			$.ajax({
				type: "post",
				url: bonuspath + "/backstage/machine/update",
				data: {
					id : machinesId,
					flag:2,
					clerk : clerk
				},
				success: function(data) {
					var res = JSON.parse(data);
					var msg = res.resMsg;
					layer.open({
					    content: msg,
					    btn: '我知道了',
					    yes:function(index){
					    	var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index); //疯狂模式，关闭所有层
							window.parent.setLoad();
					    }
					});
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("未连接到服务器，请检查网络！"+textStatus);
				}
			});
		}else{
			layer.open({
			    content: "请填写设备编码！",
			    btn: '我知道了'
			});
		}
		
	}
	
	function writeCarefulMtter(id){
		var carefulMtter = $("#carefulMtter").val();
		$.ajax({
			type:"post",
			url:bonuspath +"/backstage/machine/update",
			data:{
				id : machinesId,
				flag:4,
				carefulMtter : carefulMtter
			},
			success:function(data){
				var res = JSON.parse(data);
				var msg = res.resMsg;
				layer.open({
				    content: msg,
				    btn: '我知道了',
				    yes:function(index){
				    	var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index); //疯狂模式，关闭所有层
						window.parent.setLoad();
				    }
				});
			},
			error:function(XMLHttpRequest, textStatus,errorThrown){
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		})
		
	}
	
	function writeMainPara(id){
		var mainPara = $("#mainPara").val();
		$.ajax({
			type:"post",
			url:bonuspath +"/backstage/machine/update",
			data:{
				id : machinesId,
				flag:5,
				mainPara : mainPara
			},
			success:function(data){
				var res = JSON.parse(data);
				var msg = res.resMsg;
				layer.open({
				    content: msg,
				    btn: '我知道了',
				    yes:function(index){
				    	var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index); //疯狂模式，关闭所有层
						window.parent.setLoad();
				    }
				});
			},
			error:function(XMLHttpRequest, textStatus,errorThrown){
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		})
		
	}
	
	
	
	
	
	function writeTime(id){
		var outFactortTime = $("#outFactortTime").val();
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machine/update",
			data: {
				id : machinesId,
				outFactortTime : outFactortTime
			},
			success: function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index); //疯狂模式，关闭所有层
				window.parent.setLoad();
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		});
	}
	$(function(){
		layui.use('laydate', function(){
			var laydate = layui.laydate;
			  //日期时间选择器
			laydate.render({
				elem: '#outFactortTime'
			});
			laydate.render({
				elem: '#outFactortTime',
				type: 'datetime'
			});
			laydate.render({
				elem: '#acceptTime',
				type: 'datetime'	
			});
		});
	});
</script>
	<div id="main" class="main">
	
	</div>
</body>
</html>