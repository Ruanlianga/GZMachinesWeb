<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>

<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>

<style type="text/css">
	.widget-toolbar a{
		vertical-align:middle;
	}
	 table tbody {
        display: block;
        height: 500px;
        overflow-y: scroll;
    }
  
    table thead,
    table tbody tr {
        display: table;
        width: 100%;
        table-layout: fixed; /**表格列的宽度由表格宽度决定，不由内容决定*/
        text-align: center;
    }
  
    tbody td {
        width: 50px;
    }
    table thead {
        width: calc( 100% - 1.2em);/*表头与表格垂直对齐*/
    }
</style>

</head>
<body style=" width:100%;height:100%;position: fixed;overflow:-Scroll;overflow-y:hidden">
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="widget-header widget-header-large" style="padding:10px;">
						<div class="widget-main customBtn" >	
							<span>关键字搜索：</span><input id="keyWord" type="text" name="keyWord" />
                   	
                   	<select id="status"  name="status"  class=" inp  ">
                       		 <option value="0">待审核</option>
                      
                        	<option value="1" >审核通过</option>
                        	
                        	<option value="2" >审核驳回</option>
                      
                   	</select>
							
							
							<button class="btn btn-success btn-sm" onclick="getbaseList(1)" id="search" title="查询" type="button">查询</button>
						</div>
						<div class="widget-toolbar customBtn">
					
						<a  title="审批" onclick="approval()"  class="lrspace3" ><i class='icon-legal color bigger-180'></i></a>
						
					    <a  title="查看" onclick="view()"  class="lrspace3" ><i class='icon-info-sign color bigger-180'></i></a>
						
						<a  title="刷新" id="searchBtn" onclick="getbaseList()" class="lrspace3" ><i class='icon-refresh bigger-180 orange'></i></a>	
						    
						</div>
					</div>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:5%" class="center">
								选择<!-- <label><input type="checkbox" class="ace" ><span class="lbl"></span></label> -->
							</th>
							<th style="width:5%" class="center">序号</th>
							<th style="width:10%" class="center">单号</th>
							<th style="width:10%" class="center">设备名称</th>
							<th style="width:5%" class="center">提交人</th>
							<th style="width:10%" class="center">提交时间</th>
							<th style="width:10%" class='center'>提交备注</th> 
							<th style="width:5%" class='center'>审核人</th>
							<th style="width:10%" class='center'>审核时间</th>
							<th style="width:10%" class='center'>审核备注</th>
							<th style="width:10%" class='center'>状态</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
			</div>
		</div>
	</div>

<script type="text/javascript">

/**
 * @author
 * @date
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


function setDBChecked(id){
	$("#baseTable tbody input:checkbox:checked").each(function(){
	
		    var oo = $(".cb:checkbox:checked").eq(0);
			var id = oo.val();
			
			$("#inp"+id).prop("checked", false);
	});
	
	if($("#inp"+id).is(':checked')){
		$("#inp"+id).prop("checked", false);
	}else{
		$("#inp"+id).prop("checked", true);
	}
	
   }
/**
 * @author 
 * @date 
 * @function 条件查询
 * @returns 
 */
function search(){
	$("#search").trigger("click")
}

/**
 * @author 
 * @date 
 * @function 初始化页面ajax请求 返回html页面
 * @returns  
 */
function getbaseList(init){
	obj = {};
	if(!JY.Object.notNull(init)){
		init = $("#pageNum").val();
	}
	var size = $("#pageSize").val();
	var data = {pageNum:init,pageSize:size};
	data.obj = getObjParam("baseForm");
	console.log(data)
	var idx = layer.msg('正在加载列表,请稍等...',{
		  icon: 16
		  ,shade: 0.01
		  ,time:'-1'
	});
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/scrapAudit/findByPage', 
        data: JSON.stringify(data),      
        dataType:"html",
        contentType:"application/json",
        success:function(data){
        	$("#baseTable tbody").html(data);
        	layer.close(idx);
		}
	})
}



function approval(){
	
	if(validCheckboxCheckedNum(1)){
		
		var oo = $(".cb:checkbox:checked").eq(0);
		var id = oo.val();
		var sta = oo.attr("status");
		
		if(!(sta == 0 || sta == '0')){
			var indexMsg = layer.confirm("<h4 style='color:red'>申请单当前不可修改!</h4>", {btn: ['关闭']},function(){
				layer.close(indexMsg);
    		});
			return;
		} 
		
		var btns = ['同意','驳回','取消'];
		var title = "审批报废";
		var opt = 'approval';
		i = layer.open({
			  type: 2,
			  title: title,
			  btn:btns,
			  shade: [0],
			  area: ['90%', '95%'],
			  scrollbar: true,
			  anim: 2,
			  yes:function(index,layero){
				  var obj = $(layero).find("iframe")[0].contentWindow;
				  obj.approvalApply(); 
			  },
			  btn2: function(index, layero){ 
				  var obj = $(layero).find("iframe")[0].contentWindow;
				  obj.rejectApply(); 
				  return false;
                  },
			  content: [bonuspath +'/backstage/scrapAudit/approvalApply?id='+id+"&opt="+opt]
		});
	}
}


function view(){
	
	if(validCheckboxCheckedNum(1)){
		 var oo = $(".cb:checkbox:checked").eq(0);
		var id = oo.val();
		var btns = ['关闭'];
		var title = "查看详情";
		
		i = layer.open({
			  type: 2,
			  title: title,
			  btn:btns,
			  shade: [0],
			  area: ['90%', '95%'],
			  scrollbar: true,
			  anim: 2,
			  content: [bonuspath +'/backstage/scrapAudit/approvalView?id='+id]
		});
	}
}


</script>
</body>
</html>