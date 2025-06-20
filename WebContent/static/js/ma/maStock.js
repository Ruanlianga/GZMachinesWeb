$(function() {
	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	$('#export').on('click',function(e) {
		e.preventDefault();
		var deviceName = $("#deviceName").val();
		var deviceModel=$("#deviceModel").val();
		window.location.href=bonuspath+"/backstage/maStock/export?deviceName="+deviceName+"&deviceModel="+deviceModel;  
	});
});

function getbaseList(init) {
	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/maStock/findByPage',null,
		function(data) {
//		alert("data="+JSON.stringify(data));
			$("#baseTable tbody").empty();
			var obj = data.obj;
			var list = obj.list;
			var results = list;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
			var html = "";
			if (results != null && results.length > 0) {
				var leng = (pageNum - 1) * pageSize;
				for (var i = 0; i < results.length-1; i++) {
					var l = results[i];
					if(i == 0){
						var ll = results[results.length - 1];
						html += "<tr>";
						html += "<td style='vertical-align:middle;' class='center'>" + (i) + "</td>";
						html += "<td colspan='3' style='vertical-align:middle;' class='center'>合计</td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(parseInt(ll.stockNum)) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(parseInt(ll.leaseNum)) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(parseInt(ll.repairNum)) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(parseInt(ll.repairedNum)) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(parseInt(ll.scrapNum)) + "</td>";
						html += "<td style='vertical-align:middle;' class='center'>" + JY.Object.notEmpty(parseInt(ll.stockNums)) + "</td>";
						html += "</tr>";
					}
					html += "<tr>";
					html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + 1) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceName) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceModel) + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unit) + "</td>";
					var num=l.unit;
					var stockNum;
					var leaseNum;
					var repairNum;
					var repairedNum;
					var scrapNum;
					var lossNum;
					var stockNums;
					if(c[num]==undefined){
						stockNum=parseInt(l.stockNum);
						leaseNum=parseInt(l.leaseNum);
						repairNum=parseInt(l.repairNum);
						repairedNum=parseInt(l.repairedNum);
						scrapNum=parseInt(l.scrapNum);
						lossNum=parseInt(l.lossNum);
						stockNums=parseInt(l.stockNums);
					}else{
						stockNum =parseInt(l.stockNum);
						leaseNum =parseInt(l.leaseNum);
						repairNum =parseInt(l.repairNum);
						repairedNum =parseInt(l.repairedNum);
						scrapNum =parseInt(l.scrapNum);
						lossNum =parseInt(l.lossNum);
						stockNums =parseInt(l.stockNums);
					}
					html += "<td style='vertical-align:middle;' class='center'>"+ stockNum + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ leaseNum + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ repairNum + "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ repairedNum+ "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ scrapNum+ "</td>";
					html += "<td style='vertical-align:middle;' class='center'>"+ stockNums + "</td>";
					var isCount = l.isCount;
					if(isCount == "1" || isCount == 1){
						isCount = "是";
					}else{
						isCount = "否";
					}
					html += "<td style='vertical-align:middle;' class='center'>"+ isCount + "</td>";
					html += "</tr>";
				}
				$("#baseTable tbody").append(html);
				JY.Page.setPage("baseForm", "pageing", pageSize,pageNum,totalRecord, "getbaseList");
			} else {
				html += "<tr><td colspan='11' class='center'>没有相关数据</td></tr>";
				$("#baseTable tbody").append(html);
				$("#pageing ul").empty();// 清空分页
			}
			JY.Model.loadingClose();
		});
}
function exportData(){
	$("#baseForm").attr("onsubmit","return true;");
	$("#baseForm").attr("action",bonuspath +'/backstage/maStock/export');
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
}
function stockLoss(typeId,isBalance,isCount,deviceName,deviceModel){	
	if(isCount == 1 || isCount == "1"){
		layer.open({
		    content: '该种机具类型只计数，无详细信息',
		    btn: '我知道了'
		});
	}else{
		localStorage.setItem("typeId",typeId);
		localStorage.setItem("deviceName",deviceName);
		localStorage.setItem("deviceModel",deviceModel);
		localStorage.setItem("isBalance",isBalance);
		layer.open({
			type: 2,
			title:['详细信息','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['900px', '500px'],
			content: bonuspath+'/backstage/maStock/stockLoss'
		});
	}
}
function stockDetails(typeId,isCount,deviceName,deviceModel){	
	if(isCount == 1 || isCount == "1"){
		layer.open({
		    content: '该种机具类型只计数，无详细信息',
		    btn: '我知道了'
		});
	}else{
		localStorage.setItem("typeId",typeId);
		localStorage.setItem("deviceName",deviceName);
		localStorage.setItem("deviceModel",deviceModel);
		layer.open({
			type: 2,
			title:['详细信息','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['900px', '500px'],
			content: bonuspath+'/backstage/maStock/stockDetails'
		});
	}
}
function stock(typeId,maStatus,isCount,deviceName,deviceModel){	
//	alert("maStatus="+maStatus);
	if(isCount == 1 || isCount == "1"){
		layer.open({
		    content: '该种机具类型只计数，无详细信息',
		    btn: '我知道了'
		});
	}else{
		localStorage.setItem("typeId",typeId);
		localStorage.setItem("deviceName",deviceName);
		localStorage.setItem("deviceModel",deviceModel);
		localStorage.setItem("status",maStatus);
		layer.open({
			type: 2,
			title:['详细信息','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['900px', '500px'],
			content: bonuspath+'/backstage/maStock/details'
		});
	}
}

function search() {
	$("#searchBtn").trigger("click");
}
