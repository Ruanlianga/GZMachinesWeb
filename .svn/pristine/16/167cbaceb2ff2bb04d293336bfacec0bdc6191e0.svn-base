var supId = localStorage.getItem("supId");
var maModelId = localStorage.getItem("maModelId");

$(function() {
//	agreementInfo();

	getbaseList();
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	

});



function getbaseList(init) {
	
	var keyWord = $("#keyWord").val();


	if (init == 1)
	$(".pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath + '/backstage/puttask/findBackCode',
					{
						supId: supId,
						maModelId: maModelId,
						keyWord: keyWord
					},
					function(data) {
		              //alert("data="+JSON.stringify(data));
						$("#baseTable tbody").empty();
						var obj = data.obj;
						var list = obj.list;
						var results = list.results;
						var permitBtn = obj.permitBtn;
						var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
						var html = "";
						if (results != null && results.length > 0) {
							var leng = (pageNum - 1) * pageSize;
							for (var i = 0; i < results.length; i++) {
								var l = results[i];
								html += "<tr>";
								html += "<td style='vertical-align:middle;' class='center hidden-480'>" + (i + leng + 1) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineName) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.machineModel) + "</td>";
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.deviceCode) + "</td>";
								if(l.machineStatus == '' || l.machineStatus == null){
									html += "<td style='vertical-align:middle;' class='center'></td>";
								}else if(l.machineStatus == 1 || l.machineStatus =='1'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-default'>待通知</span></td>";
								}else if(l.machineStatus == 2 || l.machineStatus =='2'){
									html += "<td style='vertical-align:middle;' class='center'></td>";
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-primary'>待采购检验</span></td>";
								}else if(l.machineStatus == 3 || l.machineStatus =='3'){
//									html += "<td style='vertical-align:middle;' class='center'><a href='javascript:void(0)' onclick='toQRCode(&apos;"+ l.id +"&apos;);'>打印</a></td>";
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待打印</span></td>";
								}else if(l.machineStatus == 4 || l.machineStatus =='4'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>待入库</span></td>";
								}else if(l.machineStatus == 5 || l.machineStatus =='5'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-success'>在库</span></td>";
								}else if(l.machineStatus == 6 || l.machineStatus =='6'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>在用</span></td>";	
								}else if(l.machineStatus == 7 || l.machineStatus =='7'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在修</span></td>";
								}else if(l.machineStatus == 8 || l.machineStatus =='8'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-info'>已检验</span></td>";
								}else if(l.machineStatus == 9 || l.machineStatus =='9'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>修试后待入库</span></td>";
								}else if(l.machineStatus == 10 || l.machineStatus =='10'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待报废</span></td>";	
								}else if(l.machineStatus == 11 || l.machineStatus =='11'){
									if(l.isScrap == 1 || l.isScrap == '1'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>已报废</span></td>";
									}else{
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-danger'>待报废入库</span></td>";
									}
								}else if(l.machineStatus == 12 || l.machineStatus =='12'){
									if(l.isScrap == 1 || l.isScrap =='1'){
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已封存</span></td>";
									}else{
										html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待封存入库</span></td>";
									}
								}else if(l.machineStatus == 13 || l.machineStatus =='13'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>在检</span></td>";
								}else if(l.machineStatus == 14 || l.machineStatus =='14'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>出库待审</span></td>";
								}else if(l.machineStatus == 15 || l.machineStatus =='15'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>出库待批准</span></td>";
								}else if(l.machineStatus == 16 || l.machineStatus =='16'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>待批准</span></td>";
								}else if(l.machineStatus == 17 || l.machineStatus =='17'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>退料检验</span></td>";
								}else if(l.machineStatus == 18 || l.machineStatus =='18'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>入库待审</span></td>";
								}else if(l.machineStatus == 19 || l.machineStatus =='19'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>维修合格</span></td>";
								}else if(l.machineStatus == 20 || l.machineStatus =='20'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>已拆分</span></td>";
								}else if(l.machineStatus == 21 || l.machineStatus =='21'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>组装</span></td>";
								}else if(l.machineStatus == 22 || l.machineStatus =='22'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>检验报废</span></td>";
								}else if(l.machineStatus == 23 || l.machineStatus =='23'){
									html += "<td style='vertical-align:middle;' class='center'><span class='label label-warning'>检验未通过</span></td>";
								}else{
									html += "<td style='vertical-align:middle;' class='center'></td>";
								}
								html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.putInTime) + "</td>";
								html += "</tr>";
							}
							$("#baseTable tbody").append(html);
							JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
						} else {
							html += "<tr><td colspan='13' class='center'>没有相关数据</td></tr>";
							$("#baseTable tbody").append(html);
							$("#pageing ul").empty();// 清空分页
						}
						JY.Model.loadingClose();
					});
}



