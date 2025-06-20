<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>

<style type="text/css">
	.widget-toolbar a{
		vertical-align:middle;
	}
</style>

</head>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="widget-header widget-header-large" style="padding:10px;">
						<div class="widget-main customBtn" >	
							<span>关键字搜索：</span><input id="keyWord" type="text" name="keyWord" />
							<button class="btn btn-success btn-sm" onclick="getbaseList(1)" id="search" title="查询" type="button">查询</button>
						</div>
						<div class="widget-toolbar customBtn">
								<a  title="新增"  onclick="add()"  class="lrspace3" ><i class='icon-plus-sign color bigger-180'></i></a> 
								<a  title="查看"  onclick="view()"  class="lrspace3" ><i class='icon-info-sign color bigger-180'></i></a>
								<!-- <a  title="完成"  onclick="finish()"  class="lrspace3" ><i class='icon-ok-sign color bigger-180'></i></a> -->
							
							<a  title="打印" id="printBtns" onclick="print()"  class="lrspace3" ><i class='icon-print color-green bigger-180'></i></a>
							<a  title="刷新" id="searchBtn" onclick="getbaseList()" class="lrspace3" ><i class='icon-refresh bigger-180 orange'></i></a>	
						</div>
					</div>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:4%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:4%" class="center">序号</th>
							<th style="width:14%" class="center">编码</th>
							<th style="width:15%" class='center'>结算单位</th>
							<th style="width:14%" class='center'>结算工程</th>
							<th style="width:7%" class="center">基本租金</th>
							<th style="width:6%" class='center'>增加项</th>
							<th style="width:6%" class='center'>扣减项</th>
							<th style="width:7%" class="center">最终金额</th>
							<th style="width:6%" class='center'>结算人</th>
							<th style="width:10%" class='center'>结算日期</th>
							<th style="width:10%" class='center'>操作</th>
						</tr>
					</thead>
					<tbody>
					<!-- html传过来数据的添加位置-->
					</tbody>
				</table>
				
			</div>
		</div>
	</div>
	
	
<script type="text/javascript">

function uploadFile(id){  
	localStorage.setItem("id",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具文件上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['600px', '400px'],
	  content: bonuspath+'/backstage/projectSettlement/imgSllt'
	});
}



/* 
function uploadFile(id){
	localStorage.setItem("id",id);
	localStorage.setItem("bonuspath",bonuspath);
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['机具文件上传','background-color: #27A3D9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/projectSettlement/uploadFileUrl?id='+id
	});
} */
function downloadFile(id){
	window.location.href = bonuspath +"/backstage/projectSettlement/downloadDocument?id="+id; 
}

/**
 * @author js
 * @date 2020-06-24
 * @function 启动执行
 * @returns  
 */
$(function () {
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	getbaseList(1);
});

/**
 * @author js
 * @date 2020-06-26
 * @function 条件查询
 * @returns 
 */
function search(){
	$("#search").trigger("click")
}

/**
 * @author js
 * @date 2020-06-24
 * @function 初始化页面ajax请求 返回html页面
 * @returns  
 */
function getbaseList(init){
	var keyWord = $("#keyWord").val();
	if(!JY.Object.notNull(init) && JY.Object.notNull($("#pageNum").val())){
		init = $("#pageNum").val();
	}
	
	obj = {};
	var size = $("#pageSize").val();
	
	var data = {pageNum:init,pageSize:size};
	data.obj = getObjParam("baseForm");
	
	var idx = layer.msg('正在查询数据,请稍等...', {
		  icon: 16
		  ,shade: 0.01
		  ,time:'-1'
	});
	
	//接收数据
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/projectSettlement/findByPage?keyWord='+keyWord, 
        data: JSON.stringify(data), 
        //传过来的类型为html类型
        dataType:"html",
        contentType:"application/json",
        //传过来的html名称为data
        success:function(data){
        	//找到需要添加数据的位置
        	$("#baseTable tbody").html(data);
        	layer.close(idx);
		}
	})
}

/**
 * @author js
 * @date 2020-06-22
 * @function 创建任务弹窗
 * @returns
 */
function add(){
	var title = "添加结算单"; 
	i = layer.open({
		  type: 2,
		  title: title,
		  btn:['保存','取消'],
		  shade: [0],
		  area: ['90%', '95%'],
		  scrollbar: true,
		  anim: 2,
		  yes:function(index,layero){
			  var obj = $(layero).find("iframe")[0].contentWindow;
			  obj.submitApply();
		  },
		  content: [bonuspath +'/backstage/projectSettlement/addPage']
	});
}

/**
 * @author js
 * @date 2020-06-22
 * @function 创建任务弹窗
 * @returns
 */
function view(){
	if(validCheckboxCheckedNum(1)){
		var id = $(".cb:checkbox:checked").eq(0).val();
		var title = "工程结算详情";
		
		var chks =[];    
		$(".cb:checkbox:checked").each(function(){
			var obj = $(this).val();
			var result=obj.split(",");
			var id = result[0];
				chks.push(id);
		});
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!");
		}else{
			i = layer.open({
			  type: 2,
			  title: title,
			  btn:['取消'],
			  shade: [0],
			  area: ['90%', '90%'],
			  scrollbar: true,
			  anim: 2,
			  content: [bonuspath +'/backstage/projectSettlement/view?chks='+chks]
			}); 
		}
	}
}

/**
 * @author js
 * @date 2020-06-22
 * @function 创建任务弹窗
 * @returns
 */
function print(){
	if(validCheckboxCheckedNum(1)){
		var id = $(".cb:checkbox:checked").eq(0).val();
		var title = "打印工程结算详情";
		
		var chks =[];    
		$(".cb:checkbox:checked").each(function(){
			var obj = $(this).val();
			var result=obj.split(",");
			var id = result[0];
				chks.push(id);
		});
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!");
		}else{
			i = layer.open({
			  type: 2,
			  title: title,
			  btn:['打印','取消'],
			  shade: [0],
			  area: ['90%', '90%'],
			  scrollbar: true,
			  anim: 2,
			  yes:function(index,layero){
				  var obj = $(layero).find("iframe")[0].contentWindow;
				  obj.jqprinting1();
			  },
			  content: [bonuspath +'/backstage/projectSettlement/print?chks='+chks]
			}); 
		}
	}
}

/**
 * @author js
 * @date 2020-06-22
 * @function 创建任务弹窗
 * @returns
 */
function finish(){
	if(validCheckboxCheckedNum(1)){
		
		var ck = $(".cb:checkbox:checked:eq(0)");
		var id = ck.val();
		var staId = ck.attr("status");
		if(staId != 213){
			showMsg("<h4 style='color:red;'>该结算单,已完成!</h4>");
			return;
		}
		i = layer.open({
			  type: 2,
			  title: "完成结算单",
			  btn:['保存','取消'],
			  shade: [0],
			  area: ['50%', '60%'],
			  scrollbar: true,
			  anim: 2,
			  yes:function(index,layero){
				  
				  var obj = $(layero).find("iframe")[0].contentWindow;
				  obj.finishProjectSettlement();
			  },
			  content: [bonuspath +'/backstage/projectSettlement/toFinishProjectSettlement?id='+id]
		});
	}
}

</script>
</body>
</html>