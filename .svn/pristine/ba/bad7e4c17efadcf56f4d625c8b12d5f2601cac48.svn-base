var id = localStorage.getItem("id");
$(function () {
	findOptPhotoList(id);
});


function  findOptPhotoList(id){
	$.ajax({
		type:"post",
		url:bonuspath + "/backstage/machine/findOptPhotoList",
		data:{
			id:id,
		},
		dataType:"Json",
		success: function(data){
			var obj = data.obj;
			var list = obj.list;
			var html = "";
			if(list.length>0){
				for(var i = 0; i<list.length;i++){
					var path =bonuspath+list[i].filePath;
					html += '<img style="width:400px;height:300px;margin-top:2%;margin-right:2%"  data-original="' + path + '" src="' + path + '"  class="img" />';
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