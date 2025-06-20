layui.use('upload', function(){
	var code = localStorage.getItem("code");
    var $ = layui.jquery
        ,upload = layui.upload;
    	//普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: bonuspath + "/backstage/projectFinish/uploadImgSllt"
            ,accept:'file'
            ,exts: 'jpg|png|jpeg|pdf|docx|doc|xls|xlsx'
            ,size:50000
            ,data: {
            	code:code
            }
            ,auto: false  //取消自动上传
            ,bindAction: '#upload' //点击上传
            ,choose: function(obj){
            	obj.preview(function(index, file, result){
                    $('#demo1').val(file.name);
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败', {icon: 2, time: 2000});
                }else{
	 			var fileupload = $(".image");
                fileupload.attr("value",res.data.src);
                console.log(fileupload.attr("value"));
 				$('#demo1').val("");
				return layer.msg('上传成功', {icon: 1, time: 2000});
				}
                //上传成功
               
                /*window.parent.location.reload(); //刷新父页面*/
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
