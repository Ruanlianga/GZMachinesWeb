<%@page import="javax.sound.midi.SysexMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="bonuspath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${bonuspath}/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/js/jquery/jquery-form.js"></script>
<%-- <script type="text/javascript" src="${bonuspath}/static/js/layui/layui.js"></script>
<script type="text/javascript" src="${bonuspath}/static/js/pm/machines.js"></script> --%>
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
.tableStyle{
	border:1px black double;
	width: 100%;
}

</style>
<body>
	<form id="myFormId" method="post" action="${bonuspath}/backstage/machines/uploadFile" 
    	target="frmright" enctype="multipart/form-data" >  
        <div class="box1" id="formContent" whiteBg="true">
			<table class="tableStyle" id="tableStyle" formMode="transparent">
          	<tr><td colspan="2"><h2>请上传要处理的文件，过程可能需要几分钟，请稍候片刻。</h2></td></tr>  
          	<tr><td id="message"></td></tr>  
            <tr><td style="padding-left: 30%;"><input name="file1" type="file" />
            <input style="display: none;" name="id" id="id" type="text" />
            </td>
            </tr> 
            <tr>
			<td colspan="2">
				<input type="submit" value="上传" />  
            	<input type="button" value="取消" onclick="closeUpload();"/>  
            </td>
            </tr>
         </table>    
        </div>  
    </form >
    <script type="text/javascript">
		var machinesId = localStorage.getItem("updDocId");
		var bonuspath = localStorage.getItem("bonuspath");
		$("#id").val(machinesId);
		
		function closeUpload(){
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index); //疯狂模式，关闭所有层
//			window.parent.location.reload(); //刷新父页面
		}
		
		$(function(){
		    /** 验证文件是否导入成功  */  
		    $("#myFormId").ajaxForm(function(data){  
		    	var obj = $.parseJSON(data);
		        if(obj.res=='1' || obj.res==1){  
		            alert("提交成功！");
		            var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index); //疯狂模式，关闭所有层
					window.parent.location.reload(); //刷新父页面
					findUrl(machinesId);
		        }  
		    });       
		});
		
		function findUrl(machinesId){
			$.ajax({
				type: "post",
				url: bonuspath + "/backstage/machines/findUrl",
				data: {
					machinesId : machinesId
				},
				success: function(data) {
					var data = $.parseJSON(data);
					var url = data.obj.url;
					toPdf(machinesId,url);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("未连接到服务器，请检查网络！"+textStatus);
				}
			});
		}
		
	function toPdf(machinesId,url){
		$.ajax({
			type: "post",
			url: bonuspath + "/backstage/machines/toPdf",
			data: {
				machinesId : machinesId,
				fileName : url
			},
			success: function(data) {
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("未连接到服务器，请检查网络！"+textStatus);
			}
		});
	}
		
</script>
</body>
</html>