var reportCode = $("#reportCode").val();
var id = $("#id").val();
$(function () {
	JY.Ajax.doRequest(null, bonuspath + '/backstage/checkQrCode/find', {
		id : id
	}, function(data) {
		setForm(data);
	});
        
});

function setForm(data) {
	var l = data.obj;
	document.getElementById("reportCode2").innerText=l.reportCode;
	document.getElementById("unitName").innerText=l.unitName;
	document.getElementById("maType").innerText=l.maType;
	document.getElementById("maModel").innerText=l.maModel;
	document.getElementById("outCode").innerText=l.outCode;
	document.getElementById("vender").innerText=l.vender;
	document.getElementById("checkTime").innerText=l.checkTime;
	document.getElementById("checkUnit").innerText=l.checkUnit;
}