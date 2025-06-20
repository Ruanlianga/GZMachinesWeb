var id = localStorage.getItem("id");
var fileType =  localStorage.getItem("fileType");
$(function () {
	findPhotoList(id,fileType);
        
});


function  findPhotoList(id,fileType){
	$.ajax({
		type:"post",
		url:bonuspath + "/backstage/machine/findPhotoList",
		data:{
			id:id,
			fileType:fileType
		},
		dataType:"Json",
		success: function(data){
			var obj = data.obj;
			var list = obj.list;
			var html = "";
			if(list.length>0){
				for(var i = 0; i<list.length;i++){
					var fileName = list[i].fileName;
					var path =bonuspath +"/filePath"+ list[i].filePath;
					html +='<div style = "width:31%;height:80%;float:left;margin-left:1%;margin-right:1%">'
					html += '<img style="width:100%;height:100%;margin-top:2%;margin-right:1%;float:left"  data-original="' + path + '" src="' + path + '"  class="img" />';
					html +='<br/><span style="float:left;display:block;margin-left:20%">'+fileName+'</span>';
					html += '</div>'
				}
			}else{
				
				html += "<li style='color:black;float: left;border: none; font-size: 20px;height: 20%;position: absolute;\n" + "left: 315px;\n" + "top: 170px;'>没有相关数据</li>";
			       
			}
			$("#ulDiv").append(html);
		},
		error: function(XMLHttpRequest,textStatus, errorThrown){
			alert("未连接到服务器，请检查网络！"+textStatus);
		}
	});
	
}




// 加载接地图片
//function getHidePhotoList(data) {
//    $("#dowebok").empty();
//    var results = data;
//    if (results != null && results.length > 0) {
//        var html = "";
//        var s = results.replaceAll("@","/");
//        var l =bonuspath + '/backstage/machine/downFile?headerUrl='+s;
//        var path = l;
//      
//        html += "<li class='disPhotoLi'>";
//        html += '<img data-original="' + path + '" src="' + path + '"  class="img" />';
//        html += " </li>";
//        $("#dowebok").append(html);
//        /*var viewer = new Viewer(document.getElementById('dowebok'), {
//            url: 'data-original',
//            show: function () {
//                viewer.update();
//            }
//        });*/
//    } else {
//        var html = "<li style='color:black;float: left;border: none; font-size: 20px;height: 20%;position: absolute;\n" + "left: 315px;\n" + "top: 170px;'>没有相关数据</li>";
//        $("#dowebok").append(html);
//    }
//}