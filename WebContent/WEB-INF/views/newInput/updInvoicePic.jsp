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
.img-container {
	width: 293px;
	height: 150px;
	background: #F2F2F2;
	margin-bottom: 35px;
	overflow: hidden;
	border: 1px solid #000;
}

.img-container>img {
	width: 293px;
	height: 150px;
}
#myFormId{
	margin-left: 20%;
}
</style>
<body>
	
	<fieldset>
	<legend>发票上传</legend>
	<h2>只能上传单张10M以下的 PNG、JPG、GIF 格式的图片</h2>
	<form id="myFormId" method="post" action="${bonuspath}/backstage/new/uploadPhoto" 
    	target="frmright" enctype="multipart/form-data" >
	   	选择文件:<input class="img-btn" type="file" id="drivingLicence" name="drivingLicence">
	    <input style="display: none;" name="maModelId" id="maModelId" type="text" />
	    <input style="display: none;" name="taskId" id="taskId" type="text" />
	    <div class="img-container"></div>
	    <input type="submit" value="上传"/>  
        <input type="button" value="取消" onclick="closeUpload()"/>
	</form>
	</fieldset>
	
<script type="text/javascript">
		var maModelId = localStorage.getItem("updIeId");
		var taskId = localStorage.getItem("taskId");
		var bonuspath = localStorage.getItem("bonuspath");
		$("#maModelId").val(maModelId);
		$("#taskId").val(taskId);
		$(function(){
		    /** 验证文件是否导入成功  */  
		    $("#myFormId").ajaxForm(function(data){  
		    	var obj = $.parseJSON(data);
		        if(obj.res=='1' || obj.res==1){  
		            layer.alert("提交成功！");
		            var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index); //疯狂模式，关闭所有层
					window.parent.location.reload(); //刷新父页面
		        }  
		    });       
		});
		
		function previewImg(fileInput, imgDiv) {
			if(window.FileReader) { //支持FileReader的时候
				var reader = new FileReader();
				reader.readAsDataURL(fileInput.files[0]);
				reader.onload = function(evt) {
					imgDiv.innerHTML = "\<img src=" + evt.target.result + "\>";
				}
			} else { //兼容ie9-
				imgDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + fileInput.value + '\)\';"></div>';
			}
		}
		
		function selectImg(fileInputs, imgDivs) {
			var checkImg = new RegExp("(.jpg$)|(.png$)|(.bmp$)|(.jpeg$)", "i");
			var i = 0;
			for(; i < fileInputs.length && i < imgDivs.length; i++) {
				(function(i) { //立即执行函数；保存i
					fileInputs[i].onchange = function() {
						if(checkImg.test(fileInputs[i].value)) {
							previewImg(this, imgDivs[i]);
						} else {
							layer.msg("只支持上传.jpg .png .bmp .jpeg;你的选择有误");
							$("#drivingLicence").val("");
						}
					};
				})(i);
			}
		}
		/* 为IE6 IE7 IE8增加document.getElementsByClassName函数 */
		/MSIE\s*(\d+)/i.test(navigator.userAgent);
		var isIE = parseInt(RegExp.$1 ? RegExp.$1 : 0);
		if(isIE > 0 && isIE < 9) {
			document.getElementsByClassName = function(cls) {
				var els = this.getElementsByTagName('*');
				var ell = els.length;
				var elements = [];
				for(var n = 0; n < ell; n++) {
					var oCls = els[n].className || '';
					if(oCls.indexOf(cls) < 0) continue;
					oCls = oCls.split(/\s+/);
					var oCll = oCls.length;
					for(var j = 0; j < oCll; j++) {
						if(cls == oCls[j]) {
							elements.push(els[n]);
							break;
						}
					}
				}
				return elements;
			}
		}
		var fileInputs = document.getElementsByClassName("img-btn"); //文件选择按钮
		var imgDivs = document.getElementsByClassName("img-container"); //图片容器
		selectImg(fileInputs, imgDivs);
		
		function closeUpload(){
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index); //疯狂模式，关闭所有层
		}
</script>
</body>
</html>