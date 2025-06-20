var id = localStorage.getItem("id");
var fileType =  localStorage.getItem("fileType");
$(function () {
	findFileList(id,fileType);
});

function  findFileList(id,fileType){
	$.ajax({
		type:"post",
		url:bonuspath + "/backstage/machine/findFileList",
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
					var path ="/"+"filePath"+list[i].filePath;
					var fileName = list[i].fileName;
					html+="<div  onclick='viewPdf(\""+encodeURIComponent(path)+"\")'>";
					html += '<img style="width:120px;height:150px;margin-top:2%;margin-right:2%" src="'+bonuspath+'/static/img/pdf.png"/>';
					html +='<br/><span>'+fileName+'</span> ';
					html+='</div>'
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
function viewPdf(path){
	path = decodeURIComponent(path);
//	alert(decodeURIComponent(path));
//	alert(encodeURIComponent(path));
	window.open(bonuspath+path);
	
}

