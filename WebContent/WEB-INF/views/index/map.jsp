<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>地图</title>
<style type="text/css">
 html,body{
 	width: 100%;
 	height: 100%;
 	margin: 0px;
 	padding: 0px;
 }
/** #modeInfoTree{
 	width:100%;
 	height: 100%;
	overflow: hidden;
	float:left;
	zoom:1;
	position:relative;
 }
 #allmap {
    width:80%;
	height: 100%;
	overflow: hidden;
	float:right;
	zoom:1;
	position:relative;
}
**/
#map{
	height:100%;
	-webkit-transition: all 0.5s ease-in-out;
	transition: all 0.5s ease-in-out;
}
 span{
 	color:black;
 }
 .anchorBL{
 display:none;
 }
 .BMap_noprint {
 display: block ;
 }
 .BMap_cpyCtrl{
 display:none ;
 }
 
.BMap_pop {
  background: #FF6666;
  opacity: 0.9;
}
     
  .BMap_bubble_title{  
      color:black;  
      font-size:13px;  
      font-weight: bold;  
      text-align:left;  
  }  
  .BMap_pop div:nth-child(1){  
      border-radius:7px 0 0 0; 
      background: #FF6666 !important ;
  }        
  .BMap_top,.BMap_center,.BMap_bottom {
      background: #FF6666 !important ;
  }
  .BMap_pop div:nth-child(3){  
      border-radius:0 7px 0 0;
      background:#FF6666;  
      /*background: #ABABAB;*/  
      width:23px;  
      width:0px;height;  
  }  
  .BMap_pop div:nth-child(3) div{  
      border-radius:7px;  
  }  
  .BMap_pop div:nth-child(5){  
      border-radius:0 0 0 7px;  
  }  
  .BMap_pop div:nth-child(5) div{  
      border-radius:7px;  
  }  
  .BMap_pop div:nth-child(7){  
      border-radius:0 0 7px 0 ;  
  }  
  .BMap_pop div:nth-child div(7){  
      border-radius:7px ;  
  }  
  .title{
  	cursor: pointer;
  }
  
  .listTable,.listTable2{
 		width: 100%;
 		height: 100%;
 	}
 	
 	td{
 		color: #fff;
 		cursor: pointer;
 	}
 	td:hover{
 		color: #ebf226;
 	}
 	.map_title{
 		border-radius: 5px;
 		width: 80%; 
 		height: 85px;
 		background-image: url('libs/images/mapbanner.png');
 	}
 	.map_title1{
 		width:12%;
 		height: 85px;
 		float: left; 
 		font-size: 20px;
 		text-align: center;
 	}
 	.map_title5{
 		width: 8%;
 		float: left;
 		height: 100%;
 		text-align: center;
 	}
 	.map_title3{
 		width: 10%;
 		float: left;
 		height: 100%;
 		text-align: center;
 		margin-left:2%
 	}
.list,.list2{
	display: none; 
	width: 11.8%;
	height: 200px;
	background-color: #1871A7; 
	position: absolute;
	margin-top: 7.2%;
	opacity: 0.7;
}
#up-map-div {
   position: fixed;
	z-index: 2;
	margin-top:33%;
	margin-left:93%;
	hover: background:red;
}

#map > div:nth-child(2) {
	display: none;
}
</style>	
<%@ include file="../baseset.jsp"%>
<%-- <link rel="stylesheet" type="text/css" href="${bonuspath}/static/css/sys/screen.css" /> --%>
<link rel="stylesheet" type="text/css" href="${bonuspath}/static/css/baiduMap/DrawingManager_min.css" />
<link rel="stylesheet" type="text/css" href="${bonuspath}/static/css/baiduMap/SearchInfoWindow_min.css" />
<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=cClgLBaLgGUdQDilX9dGvieL"></script> -->
<script type="text/javascript" src="${bonuspath}/static/js/baiduMap/apiv1.3.min.js"></script>
<script src="${bonuspath}/static/js/baiduMap/SearchInfoWindow_min.js"></script>
<script src="${bonuspath}/static/js/baiduMap/DrawingManager_min.js"></script>
<script src="${bonuspath}/static/js/baiduMap/GeoUtils_min.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<script src="${bonuspath}/static/js/jy.js"></script>
<script src="${bonuspath}/static/js/warning/warningTip.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.all.min.js"></script>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.exhide.min.js"></script>
</head>
	<body>
		<div style="width: 100%;height: 100%;">
			<div style="width: 100%;height: 100%;">
				<div id="up-map-div" title="告警"  style="background-color: white; "  >
			    	<a href="#" onclick="warning()"><img style="height: 111%;width: 99%;" 
				    src="${bonuspath}/static/css/sys/images/warn33.png"></a>
		        </div>
		        <div id="modeTree" style="width: 18%;height: 100%;float:left;backgroud:grey;overflow: auto;">
		        <div style="width: 98%;height: 6%;">
		        	<input id="keyWord" style="height:30px"/>
		        	<button type="button" onclick="query()">查询</button>
		        	<button type="button" onclick="reset()">重置</button>
		        </div>
		        <ul id="modeInfoTree" class="ztree modeInfoTree"></ul>
		    	</div>
		      	<div id="allmap" style="width: 82%;height: 100%;float:right">
					<div id="map"></div>
				</div>
			</div>
		</div>
		
	</body>
	
	
	
	<script src="${bonuspath}/static/js/baiduMap/map_Update.js"></script>
	

	
</html>