
$(function(){
	findMachineType();
})

function findMachineType(){
	$.ajax({
		type:"post",
		url:bonuspath + "/backstage/machine/findMachineType",
		data:{},
		dataType:"json",
		success: function(data){
			var obj = data.obj;
			var list = obj.list;
			var html = '<option value="">--请先选名称--</option>';
			if(list.length>0){
				for(var i = 0;i<list.length;i++){
					html += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
			}
			$("#typetId").append(html);
		},
		error: function(XMLHttpRequest,textStatus, errorThrown){
			alert("未连接到服务器，请检查网络！"+textStatus);
		}
	});
}


function findMachineTypeId(typeId){
	$("#typeParentId").empty();
	$.ajax({
		type:"post",
		url:bonuspath + "/backstage/machine/findMachineTypeId",
		data:{id:typeId},
		dataType:"json",
		success: function(data){
			var obj = data.obj;
			var list = obj.list;
			var html = '<option value="">--请先选名称--</option>';
			if(list.length>0){
				for(var i = 0;i<list.length;i++){
					html += '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}
			}
			$("#typeParentId").append(html);
		},
		error: function(XMLHttpRequest,textStatus, errorThrown){
			alert("未连接到服务器，请检查网络！"+textStatus);
		}
	});
}

function loadNameById(){
	var typeId = $("#typetId").val();
	findMachineTypeId(typeId);
}


layui.use('upload', function(){
    var $ = layui.jquery
        ,upload = layui.upload;
   
    
    	//普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: bonuspath + "/backstage/machine/submitUpload"
            ,exts:'pdf'
            ,size:50000
            ,data: {
            }
            ,auto: false  //取消自动上传
            ,bindAction: '#upload' //点击上传
            ,choose: function(obj){
            	obj.preview(function(index, file, result){
            		var name = file.name;
                    $('#demoText').text(name);
                });
            },
            before: function(obj){
                this.data={'typeParentId':$('#typeParentId').val()};//关键代码
                } 
            ,done: function(res){
            	var typeParentId=$('#typeParentId').val();
            	if(typeParentId == null || typeParentId == ""){
            		return layer.msg('请选择规格型号');
            	}
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
                var demoText = $('#demoText');
                demoText.html('<span style="color: #4cae4c;">上传成功</span>');

                var fileupload = $(".image");
                fileupload.attr("value",res.data.src);
                console.log(fileupload.attr("value"));
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



