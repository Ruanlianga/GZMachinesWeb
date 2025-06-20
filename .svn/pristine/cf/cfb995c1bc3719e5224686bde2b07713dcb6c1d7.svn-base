$(function(){
	var qrcode = $("#qrcode").val();
	qrCodePage(qrcode);
	findInfoData(qrcode);
});

/*function qrCodePage(qrcode){
	$.ajax({
		url:bonuspath+'/backstage/machine/findByQrcode',
		data:{qrcode:qrcode},
		type:'post',
		dataType:'json',
		success:function(data){
			var l = data.obj;
			$("#qrPage").html("");
			var str='';

			var url = 'http://192.168.0.6:21029/GZMachinesWeb/maTypeFile/'+l.mainPara;
			//var url = 'http://112.31.212.128:1935/gz_imw/maTypeFile/'+l.mainPara;

			str+='<p><span>二维码编码：</span><span>'+nullToEmpty(qrcode)+'</span></p>';
			str+='<p><span>机具名称：</span><span>'+nullToEmpty(l.type0)+'</span></p>';
			str+='<p><span>规格型号：</span><span>'+nullToEmpty(l.type)+'</span></p>';
			str+='<p><span>设备编码：</span><span>'+nullToEmpty(l.deviceCode)+'</span></p>';
			str+='<p><span>生产厂家：</span><span>'+nullToEmpty(l.venderName)+'</span></p>';
			str+='<p><span>主要参数：</span><a href="'+url+'" download="'+l.mainPara+'">下载</a></p>';
			str+='<p><span>本次检修人员：</span><span>'+nullToEmpty(l.tOverhaulPersion)+'</span></p>';
			str+='<p><span>本次检修时间：</span><span>'+nullToEmpty(l.tOverhaulTime)+'</span></p>';
			str+='<p><span>下次检修时间：</span><span>'+nullToEmpty(l.nOverhaulTime)+'</span></p>';
			$("#qrPage").append(str);
			$("#qrPage").css("display","block");
		},
		error:function(dats){
		}
	});
}*/

function qrCodePage(qrcode){
	$.ajax({
		url:bonuspath+'/backstage/machine/findByQrcodePage',
		data:{qrcode:qrcode},
		type:'post',
		dataType:'json',
		success:function(data){
			var l = data.obj;
			if(l !=null){
				$("#type0").text(nullToEmpty(l.type0));
				$("#type").text(nullToEmpty(l.type));
				$("#deviceCode").text(nullToEmpty(l.deviceCode));
				$("#qrCode").text(nullToEmpty(l.qrcode));
				$("#remarks").text(nullToEmpty(l.remarks));
				$("#batchStatus").text(nullToEmpty(l.batchStatus));
				$("#buyTime").text(nullToEmpty(l.buyTime));
				$("#venderName").text(nullToEmpty(l.venderName));
				$("#buyCompany").text(nullToEmpty(l.buyCompany));
				
				if(l.filePath != null){
					var filePath="http://140.210.216.134:1935/gz_imw/acceptImg/"+l.filePath;
						//	var filePath="http://192.168.0.14:3980/GZMachinesWeb"+l.filePath;
					var html='<img src="'+filePath+'" style="width: 98%;height: 98%;margin: 1%">';
					$("#filePath").append(html);
				}else{
					$("#filePath").text("暂无图片");
				}
				
				if(l.optName !=null){
					var url="http://140.210.216.134:1935/gz_imw/acceptImg/"+l.optUrl;
						//var url="http://192.168.0.14:3980/GZMachinesWeb"+l.optUrl;
					var html='<a href="'+url+'" download="'+l.optName+'">'+l.optName+'</a>';
					$("#optName").append(html);
				}else{
					$("#optName").text("暂无文件");
				}
			}
		},
		error:function(dats){
		}
	});
}

function findInfoData(qrcode){
	$.ajax({
		url:bonuspath+'/backstage/machine/findInfoData',
		data:{qrcode:qrcode},
		type:'post',
		dataType:'json',
		success:function(data){
			var l = data.obj;
			if(l.length>0){
				var html="";
				$("#infoName").text(nullToEmpty(l[0].infoName));
				$("#time").text(nullToEmpty(l[0].time));
				for(var i=0;i<l.length;i++){
				//	var fileUrl="http://140.210.216.134:1935/gz_imw/acceptImg/"+l[i].fileUrl;
						var fileUrl="http://192.168.0.14:3980/GZMachinesWeb"+l[i].fileUrl;
					html+='<img src="'+fileUrl+'" style="width: 30.5%;height: 48%;margin: 1%">';
				}
				$("#infoFile").append(html);
			}else{
				$("#infoFile").text("暂无图片");
			}
		},
		error:function(dats){
		}
	});
}

function nullToEmpty(srt){
	if(srt == null){
		return "";
	}else{
		return srt;
	}
}
