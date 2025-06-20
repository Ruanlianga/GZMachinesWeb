var id = localStorage.getItem("id");
var optUrl = localStorage.getItem("optUrl");
var fileName = localStorage.getItem("optName");
var html = "";
var path ="/"+"filePath"+ optUrl
if(optUrl==null || optUrl == "null" || optUrl=="" ){
	html += "<li style='color:black;float: left;border: none; font-size: 20px;height: 20%;position: absolute;\n" + "left: 315px;\n" + "top: 170px;'>没有相关数据</li>";
}else{
html+="<div  onclick='viewPdf(\""+encodeURIComponent(path)+"\")'>";
html += '<img style="width:120px;height:150px;margin-top:2%;margin-right:2%" src="'+bonuspath+'/static/img/pdf.png"/>';
html +='<br/><span>'+fileName+'</span> ';
html+='</div>'
}
$("#ulDiv").append(html);

function viewPdf(path){
	path = decodeURIComponent(path);
//	alert(decodeURIComponent(path));
//	alert(encodeURIComponent(path));
	window.open(bonuspath+path);
	
}

