<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<script src="${bonuspath}/static/js/ace/select2.full.js"></script>

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
	<input type="hidden" name="images" class="image">
   <label class="layui-form-label ">选择文件:</label>
	<div class="layui-upload">
        <button type="button" class="layui-btn" id="test1">上传文件</button>
        <div class="layui-upload-list">
            <img class="layui-upload-img" id="demo1">
            <p id="demoText"></p>
        </div>
    </div>
	<button  id="upload">上传</button>
	<script type="text/javascript">
			
		layui.use('upload', function(){
			var id = localStorage.getItem("id");
		    var $ = layui.jquery
		        ,upload = layui.upload;
		    	//普通图片上传
		        var uploadInst = upload.render({
		            elem: '#test1'
		            ,url: bonuspath + "/backstage/vender/uploadVender"
		            ,accept:'file'
		            ,size:50000
		            ,exts: 'jpg|png|gif|bmp|jpeg|pdf|docx|doc'
		            ,data: {
		            	id: id
		            }
		            ,auto: false  //取消自动上传
		            ,bindAction: '#upload' //点击上传
		            ,choose: function(obj){
		            	obj.preview(function(index, file, result){
		            		var name = file.name;
		            		if(name.indexOf("jpg") != -1 || name.indexOf("png") != -1 || name.indexOf("bmp") != -1 || name.indexOf("jpeg") != -1 || name.indexOf("gif") != -1){
		            			$('#demo1').attr('src', result);
		            			$('#demoText').text('');
		            		}else{
		            			$('#demoText').text(name);
		            			$('#demo1').attr('src', '');
		            		}
		                });
		            }
		            ,done: function(res){
		            	var fileName = res.obj;
		            	localStorage.setItem("agreePicName",fileName);
		                //如果上传失败
		                if(res.code > 0){
		                    return layer.msg('上传失败');
		                }
		                //上传成功
		                var demoText = $('#demoText');
		                parent.layer.msg("上传成功");
		
		                var fileupload = $(".image");
		                //fileupload.attr("value",res.data.src);
		                var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index); //疯狂模式，关闭所有层
						window.parent.location.reload(); //刷新父页面
						
		                
		            }
		            ,error: function(){
		                //演示失败状态，并实现重传
		                var demoText = $('#demoText');
		                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
		                demoText.find('.demo-reload').on('click', function(){
		                    uploadInst.upload();
		                });
		            }
		        });
		});
			
	</script>
</body>
</html>