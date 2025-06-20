var reportCode = localStorage.getItem("reportCode");
var id = localStorage.getItem("id");
$(function () {
	//需要修改
	var url="http://192.168.0.52:8080"+bonuspath + '/backstage/checkQrCode/showQRCodePage?reportCode='+reportCode+'&id='+id;
	var qrcode = new QRCode(  //实例化生成二维码
	    document.getElementById("qr"), {//二维码存放的div
	    width: 160, //设置宽高
	    height: 160,
	   }
	 
	  );
	 
	  //生成二维码
	  qrcode.makeCode(url);
	  
	  document.getElementById("qrData").innerText=reportCode;
        
});



function dwQrCode() {
	 var imgs = document.getElementById("qr").getElementsByTagName("img");
     var a = document.createElement('a');
     a.download = reportCode;
     a.href = imgs[0].src;
     document.body.appendChild(a);
     a.click();
     document.body.removeChild(a);
}
