var map;
var polygon2;
var point2s = [];
var drawingOverlay;
 var levels ="";
 var parentIds ="";
$(function() {
	// 初始化树
	getOrgTreeData("");
	initMap(); // 加载地图
});


function initMap() {
	creatMap();
	madeBoundary(); // 加载区域图
}

function creatMap(){
	map = new BMap.Map('map');
	//var poi = new BMap.Point(117.542207,33.133388);
	var poi = new BMap.Point(106.852748,26.647899);
	map.centerAndZoom(poi, 8.5);
	map.enableScrollWheelZoom();
	
	var styleOptions = {
		strokeColor: "red", //边线颜色。
		fillColor: "red", //填充颜色。当参数为空时，圆形将没有填充效果。
		strokeWeight: 3, //边线的宽度，以像素为单位。
		strokeOpacity: 0.8, //边线透明度，取值范围0 - 1。
		fillOpacity: 0.6, //填充的透明度，取值范围0 - 1。
		strokeStyle: 'solid' //边线的样式，solid或dashed。
	}
		//实例化鼠标绘制工具
	var drawingManager = new BMapLib.DrawingManager(map, {
		isOpen: false, //是否开启绘制模式
		enableDrawingTool: true, //是否显示工具栏
		drawingToolOptions: {
			anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
			offset: new BMap.Size(5, 5), //偏离值
			drawingModes : [BMAP_DRAWING_POLYLINE],
			scale: 0.8 //工具栏缩放比例
		},
		circleOptions: styleOptions, //圆的样式
		polylineOptions: styleOptions, //线的样式
		polygonOptions: styleOptions, //多边形的样式
		rectangleOptions: styleOptions //矩形的样式
	});

	findDevPos("");						//查询设备定位信息
	findFencePoints();					//查询围栏区域信息
	
	//添加鼠标绘制工具监听事件，用于获取绘制结果
	drawingManager.addEventListener('overlaycomplete', overlaycomplete);
	map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件  
	map.enableScrollWheelZoom();                            //启用滚轮放大缩小  
}

// 设置区域图start
function madeBoundary() {
	var datas = new Array("贵州-");
	var bdary = new BMap.Boundary();
	for (var i = 0; i < datas.length; i++) {
		getBoundary(datas[i], bdary);
	}
}

function getBoundary(data, bdary) {
	data = data.split("-");
	bdary.get(data[0], function(rs) { // 获取行政区域
		var count = rs.boundaries.length; // 行政区域的点有多少个
		var pointArray = [];
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {
				strokeWeight : 3,
				strokeColor : "#00D8FF", //#024E41 国网绿
				fillOpacity : 0,
				fillColor : "rgba(0, 0, 0, 0)"
			}); // 建立多边形覆盖物
			map.addOverlay(ply); // 添加覆盖物
			ply.disableMassClear(); // 此设置不会被移除
		}
	});
}
// 设置区域图end

function findDevPos(name){
	var index=name.indexOf("[");
	if(index>-1){
		name=name.substring(0,index);
	}
	var pts = [];
	var ptsInfo = [];
	$.ajax({
		type : "post",
		url : bonuspath + "/backstage/position/findBindPosition",
		data : {
			keyWord:name,
			deviceType:"2"
		},
		dataType : "json",
		success : function(data) {
			var result = data.obj;
			console.log("result",result);
			if (result != null && result != '') {
				for (var i = 0; i < result.length; i++) {
					var lat = result[i].lat;
					var lon = result[i].lon;
					var id = result[i].id;
					var jNum = result[i].jNum;
					var sNum = result[i].sNum;
					map.addOverlay(createMarker(id,lon, lat, result[i],jNum,sNum));
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}
//创建地图覆盖物start  
function createMarker(id,lon, lat, dev,jNum,sNum) { // 创建地图覆盖物
	var pt = new BMap.Point(lon, lat);
	var cent = "";
	var myIcon = getIcon(dev);
	var marker = new BMap.Marker(pt, {
		icon : myIcon
	});
	cent += "<div style='width: 100%;float: left;'>";
	cent += "<span style='color:#ffffff;font-size:16px;'>GPS编号：<strong>"
		+ JY.Object.notEmpty(dev.code) + "</strong></span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>设备编号：<strong>"
			+ JY.Object.notEmpty(dev.deviceCode) + "</strong></span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>设备电量： "
			+ JY.Object.notEmpty(dev.electQuantity) + "%" + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>经度： "
			+ JY.Object.notEmpty(dev.lon) + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>纬度： "
			+ JY.Object.notEmpty(dev.lat) + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>上传时间： " 
			+ JY.Object.notEmpty(dev.upTime) + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>设备类型： " + JY.Object.notEmpty(dev.typeName) + "</span><br />";
	var posTypeInfo = dev.posType;
	if(posTypeInfo =="LBS" || posTypeInfo =="lbs"){
		posTypeInfo = "基站定位";
	}
	if(posTypeInfo =="GPS" || posTypeInfo =="gps"){
		posTypeInfo = "卫星定位"; 
	}
	if(posTypeInfo =="WIFI" || posTypeInfo =="wifi"){
		posTypeInfo = "WIFI定位";
	}
	if(posTypeInfo =="BEACON" || posTypeInfo =="beacon"){
		posTypeInfo = "蓝牙定位";
	}
	cent += "<span style='color:#ffffff;font-size:16px;'>定位类型： "
		+ posTypeInfo + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>所属围栏：暂无</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>当前项目所在工程： "
		+ JY.Object.notEmpty(dev.proName) + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>领用单位： "
		+ JY.Object.notEmpty(dev.useUnit) + "</span><br />";
		/*cent +="<span>"+'历史定位：'+"</span>"+"<span style='color:#ffffff;font-size:16px;' onclick='gpsView("+ dev.code+","+dev.deviceCode+","+dev.typeName+")'>定位查看</span><br />";*/
	cent +="<span style='color:#ffffff;font-size:16px;'>"+'历史定位：'+"</span>"+"<img src = '../../static/img/gps.png' width='26px' height='26px' onclick='gpsView(\""+JY.Object.notEmpty(dev.code)+"\")' /><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>地址： "
		+ JY.Object.notEmpty(dev.address) + "</span><br />";
	cent += "</div>";
	addClickHandler(cent, marker);
	return marker;
}
/*function createMarker(id,lon, lat, dev,jNum,sNum) { // 创建地图覆盖物
	var info = isArea(lon, lat, dev.points);
	var pt = new BMap.Point(lon, lat);
	var cent = "";
	var myIcon = getIcon(dev);
	var marker = new BMap.Marker(pt, {
		icon : myIcon
	});
	cent += "<span style='color:#ffffff;font-size:16px;'><strong>"
		+ info + "</strong></span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>GPS编号：<strong>"
		+ JY.Object.notEmpty(dev.code) + "</strong></span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>设备编号：<strong>"
			+ JY.Object.notEmpty(dev.deviceCode) + "</strong></span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>设备电量： "
			+ JY.Object.notEmpty(dev.electQuantity) + "%" + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>经度： "
			+ JY.Object.notEmpty(dev.lon) + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>纬度： "
			+ JY.Object.notEmpty(dev.lat) + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>上传时间： " 
			+ JY.Object.notEmpty(dev.upTime) + "</span><br />";
	if(info=="设备不在区域内"){
		cent += "<span style='color:#ffffff;font-size:16px;'>设备数量： </span>" +
		"<span style='color:blue;font-size:16px;' onmouseover='findDeviceDetail(&apos;" + id + "&apos;,-1)'>"+ 1 + "</span><br />";
		cent += "<span style='color:#ffffff;font-size:16px;'>设备类型： " + JY.Object.notEmpty(dev.typeName) + "</span><br />";
	}else if(info=="设备在区域内"){
		cent += "<span style='color:#ffffff;font-size:16px;'>机动绞磨数量： </span>" +
		"<span style='color:blue;font-size:16px;' onmouseover='findDeviceDetail(-1,1)'>"+ JY.Object.notEmpty(jNum) + "</span><br />";
		cent += "<span style='color:#ffffff;font-size:16px;'>手扶机绞磨数量： </span>" +
		"<span style='color:blue;font-size:16px;' onmouseover='findDeviceDetail(-1,2)'>"+ JY.Object.notEmpty(sNum) + "</span><br />";
		cent += "<span style='color:#ffffff;font-size:16px;'>设备数量： </span>" +
		"<span style='color:blue;font-size:16px;' onmouseover='findDeviceDetail(-1,0)'>"+ JY.Object.notEmpty(parseInt(jNum) + parseInt(sNum)) + "</span><br />";
	}
	var posTypeInfo = dev.posType;
	if(posTypeInfo =="LBS" || posTypeInfo =="lbs"){
		posTypeInfo = "基站定位";
	}
	if(posTypeInfo =="GPS" || posTypeInfo =="gps"){
		posTypeInfo = "卫星定位"; 
	}
	if(posTypeInfo =="WIFI" || posTypeInfo =="wifi"){
		posTypeInfo = "WIFI定位";
	}
	if(posTypeInfo =="BEACON" || posTypeInfo =="beacon"){
		posTypeInfo = "蓝牙定位";
	}
	cent += "<span style='color:#ffffff;font-size:16px;'>定位类型： "
		+ posTypeInfo + "</span><br />";
	cent += "<span style='color:#ffffff;font-size:16px;'>所属围栏： "
		+ dev.fenceName + "</span><br />";
	cent += "<div style='width: 100%;height: 15%;float: left;'>";
	cent += "</div>";
	addClickHandler(cent, marker);
	return marker;
}*/

//历史定位数据查看
function gpsView(code){
	localStorage.setItem("code",code);
	layer.open({
		type : 2,
		title : ['历史定位', 'background-color: #27A3D9;color:#fff' ],
		shadeClose : true,
		shade : false,
		maxmin : true,
		area : [ '90%', '85%' ],
		content :bonuspath+'/backstage/position/historyGps'
	});
}

//点击重叠数字，弹窗展示设备信息明细
function findDeviceDetail(id,type){
	localStorage.setItem("id",id);
	localStorage.setItem("type",type);
	layer.open({
		type : 2,
		title : ['设备明细', 'background-color: #27A3D9;color:#fff' ],
		shadeClose : true,
		shade : false,
		maxmin : true,
		area : [ '1100px', '600px' ],
		content :bonuspath+'/backstage/index/map'
	});
}

//覆盖物点击事件start--机具
function addClickHandler(content, marker) {
		marker.addEventListener("onmouseover", function(e) {
		openDevInfo(content, e);
	});
}
// 覆盖物点击事件end

//创建信息窗口对象 start
function openDevInfo(content, e) { // 弹出窗口信息
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content, manageopts); // 创建信息窗口对象
	map.openInfoWindow(infoWindow, point); // 开启信息窗口
//	$("..BMap_pop div:nth-child(8) img")
//			.attr('src', "./libs/images/device.png");
}
// 创建信息窗口对象 end

//回调获得覆盖物信息
function overlaycomplete(e) {
	map.removeOverlay(drawingOverlay);
	drawingOverlay = e.overlay;
	map.addOverlay(drawingOverlay);
	var points = new Array();
	var arrps = new Array();
	var p=e.overlay.getPath();  
	for(var j = 0; j < p.length; j++){  
		var grid =p[j];  
		 var point = new BMap.Point(grid.lng,grid.lat);
		 var obj={};
		 obj.lon=grid.lng;
		 obj.lat=grid.lat;
		 points.push(point);
		 arrps.push(obj);
	}
	console.log("arrpss:",arrps);
	polygon2 = new BMap.Polygon(points,{strokeColor:"#f50704",fillColor:"#cfcfcf", strokeWeight:5, strokeOpacity:0,fillOpacity:0,});
	map.addOverlay(polygon2);
	console.log(points);
	addFence(arrps);
};

//围栏坐标存入数据库
function addFence(arrps){
	layer.prompt({title: '请输入围栏名称', formType: 2}, function(text, index){
		layer.close(index);
		$.ajax({
			type : "post",
			url : bonuspath + "/backstage/fence/insert",
			data : {
				points:JSON.stringify(arrps),
				name:text
			},
			dataType : "json",
			success : function(data) {
				layer.msg(data.resMsg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	});
}

//查询围栏坐标点信息
function findFencePoints(){
	$.ajax({
		type : "post",
		url : bonuspath + "/backstage/fence/findNoPage",
		data : {
			isActive : 1
		},
		dataType : "json",
		success : function(data) {
//			alert("data="+JSON.stringify(data))
			var res = data.obj.list;
			var points;
			for(var i=0;i<res.length;i++){
				points = res[i].points;
				var json=JSON.parse(points);
				var pointarray=new Array();
				for(var j=0;j<json.length;j++){
					var obj=json[j];
					var lon=obj.lon;
					var lat=obj.lat;
					var point = new BMap.Point(lon,lat);
					pointarray.push(point);
				}
				polygon2 = new BMap.Polygon(pointarray,{strokeColor:"#f50704",fillColor:"#cfcfcf", strokeWeight:5, strokeOpacity:0,fillOpacity:0,});
				map.addOverlay(polygon2);
			}
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}


//根据状态设置图标 start
function getIcon(dev) {
	var status = dev.status;
	var alarmType = dev.alarmType;
	var typeName = dev.typeName;
//	alert(dev.deviceCode+typeName+",状态="+status+",告警="+alarmType);
	var url;
		if(status == '5'){
			url = "../../libs/images/inLibj.png";
		}else{
			url = "../../libs/images/isUsej.png";
		}
	var icon = new BMap.Icon(url, new BMap.Size(35,35));
	return icon;
	
}


/*function getIcon(dev) {
	var status = dev.status;
	var alarmType = dev.alarmType;
	var typeName = dev.typeName;
//	alert(dev.deviceCode+typeName+",状态="+status+",告警="+alarmType);
	var url;
	if(typeName == "机动绞磨"){
		if(alarmType == '1'){
			url = "./libs/images/warningj.png";
		}else if(alarmType == '2'){
			url = "./libs/images/isNoticej.png";
		}else if(alarmType == '3'){
			url = "./libs/images/isNoticej.png";
		}else{
			if(status == '1'){
				url = "./libs/images/inLibj.png";
			}else{
				url = "./libs/images/isUsej.png";
			}
		}
	}else if(typeName == "手扶机绞磨"){
		if(alarmType == '1'){
			url = "./libs/images/warnings.png";
		}else if(alarmType == '2'){
			url = "./libs/images/isNotices.png";
		}else if(alarmType == '3'){
			url = "./libs/images/isNotices.png";
		}else{
			if(status == '1'){
				url = "./libs/images/inLibs.png";
			}else{
				url = "./libs/images/isUses.png";
			}
		}
	}
	
	var icon = new BMap.Icon(url, new BMap.Size(35,35));
	return icon;
	
}*/
// 根据状态设置图标 end

//管理人员信息窗口start
var manageopts = {
	width : 360, // 信息窗口宽度
	height : 320, // 信息窗口高度
	enableMessage : true
};
// 管理人员信息窗口end

// 获取当前时间start
function getNowFormatDate() {
	var date = new Date();
	var seperator = "-";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator + month + seperator
			+ strDate;

	return currentdate;
}

//判断坐标点是否在围栏区域内
function isArea(lon,lat,points){
	var info;
	var devPoint = new BMap.Point(lon, lat);
	var json=JSON.parse(points);
	var pointarray=new Array();
	for(var j=0;j<json.length;j++){
		var obj=json[j];
		var lons=obj.lon;
		var lats=obj.lat;
		var point = new BMap.Point(lons,lats);
		pointarray.push(point);
	}
	var fence = new BMap.Polygon(pointarray);
	
	if(BMapLib.GeoUtils.isPointInPolygon(devPoint,fence)){
		info = "设备在区域内";
	}else {
		info = "设备不在区域内";
	}
	return info;
}

// 定义树节点初始数据
var zNodes1 = [
	{ "id":0, "pId":-1,open:true,"name":"特殊设备",icon:bonuspath + "/static/css/sys/images/home.gif"},
	];


// 树的设置
var setting1 = {
	check : {
			enable : false,
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		async : {
			enable : true,
		},
	data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : 0
			}
		},
	callback : {
		onClick: onTreeClick,
		//点击前
		beforeClick:beforeClick,
		// 不允许拖拽
		beforeDrag : beforeDrag
	}
};
function beforeClick(treeId, treeNode){
	if(treeNode.id == 0){
		return false;
	}
	return true;
}

// 不允许拖拽
function beforeDrag(treeId, treeNodes) {
	return false;
}


// 获取数据初始化树
function getOrgTreeData(keyWord) {
	$.ajax({
		type : 'POST',
		url : bonuspath + '/backstage/position/getMainSpecialTree',
		data : {
			keyWord:keyWord
		},
		success : function(result) {
			var nodes = zNodes1.concat(result.obj.list);
			console.info(nodes);
			$.fn.zTree.init($("#modeInfoTree"), setting1, nodes);
		},
		error : function(e) {
			JY.Model.info(e.resMsg);
		},
		dataType : 'json'
	});
}

function refreshTree(){
	getOrgTreeData("");
}


// 获取树对象
function getTree() {
	return $.fn.zTree.getZTreeObj("modeInfoTree");
}

function onTreeClick(event, treeId, treeNode, clickFlag){
	console.info("name"+treeNode.name+",id:"+treeNode.id+",level:"+treeNode.LEVEL);
	remove_overlay(); // 移除覆盖物
	findDevPos(treeNode.name);
}

//清除地图覆盖物
function remove_overlay() {
	map.clearOverlays();
}

function search(){
	getbaseList();
}

function query(){

	 var ztreeObj = $.fn.zTree.getZTreeObj("modeInfoTree");
     var keyword=$("#keyWord").val();
     searchZtree(ztreeObj,keyword);
}

function reset(){
	$("#keyWord").val("");
	getOrgTreeData("");
}



function searchZtree(ztreeObj,ztreeInput){
	//ztree查询
  function filterFunc(node){
      var keyword=ztreeInput;
      //如果当前结点，或者其父结点可以找到，或者当前结点的子结点可以找到，则该结点不隐藏
      if(searchParent(keyword,node) || searchChildren(keyword,node.children)){
          return false;
      }
      return true;
  };

  //获取不符合条件的叶子结点
  var hiddenNodes=ztreeObj.getNodesByFilter(filterFunc);

  //隐藏不符合条件的叶子结点
  ztreeObj.hideNodes(hiddenNodes);
}


/**
 * 查找子结点，如果找到，返回true，否则返回false-----ztree查询时使用
 */
function searchChildren(keyword,children){
  if(children == null || children.length == 0){
      return false;
  }
  for(var i = 0;i < children.length;i++){
      var node = children[i];
      if(node.name.indexOf(keyword)!=-1){
          return true;
      }
      //递归查找子结点
      var result = searchChildren(keyword,node.children);
      if(result){
          return true;
      }
  }
  return false;
}

/**
 * 查找当前结点和父结点，如果找到，返回ture，否则返回false
 */
function searchParent(keyword,node){
    if(node == null){
        return false;
    }
    if(node.name.indexOf(keyword)!=-1){
        return true;
    }
    //递归查找父结点
    return searchParent(keyword,node.getParentNode());
}
