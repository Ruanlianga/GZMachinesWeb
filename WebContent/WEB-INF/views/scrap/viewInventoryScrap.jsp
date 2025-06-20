<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%@include file="../webPortalCommonSet.jsp" %>
<title></title>

<style type="text/css">

#baseInfo{
		text-align: center;
		width:96%;
		margin:0 2% 0 2%;
	}
	
	#baseInfo tr{
		height: 2.5em;
	}
	
	#baseInfo tr>input{
		width:96%;
	}

	.inp{
		width:80%;
	}
	
	.a{
		text-align: left;
	}
	
</style>
</head>
<body>
<div>
	<table  id="baseInfo" cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody class="a">
			<button class="btn btn-info btn-sm" onclick="uploadPic()" id="search" title="上传"  style="width: 6%;margin-left: 2%"  type="button">上传附件</button>
			<button class="btn btn-info btn-sm" onclick="openShowFiles()" id="showFiles" title="查看"  style="width: 6%;margin-left: 2%"  type="button">附件查看</button>

			<tr>
				<td colspan="7">
					<table id="machineTable" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:10%" class="center">设备名称</th>
							<th style="width:10%" class="center">规格型号</th>
							<th style="width:7%" class="center">计量单位</th>
							<th style="width:10%" class="center">设备编号</th>
							<th style="width:10%" class="center">报废数量</th>
							<th style="width:10%" class="center">备注</th>
						</tr>
					</thead>
					<tbody id="list">
					<c:forEach items="${list}" var="detail" varStatus="index">
						<tr class='pa'>
							<th class='center'>${index.index+1}</th>
							<th class='center'>${detail.maName}</th>
							<th class='center'>${detail.typeName}</th>
							<th class='center'>${detail.unit}</th>
							<th class='center'>${detail.maCode}</th>
							<th class='center'>${detail.num}</th>
							<th class='center'>${detail.remarks}</th>
						</tr>
					</c:forEach>
				</tbody>
				   
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</div>


<script type="text/javascript">
var id=localStorage.getItem("id");
var machines ={};//机具容器
var p; //父页面对象,多层关闭时使用

function uploadPic(){
	localStorage.setItem("id",id);
	var index = layer.open({
		  type: 2,
		  title: '附件上传',
		  shade: [0],
		  btn: ['关闭'], //按钮组
		  area: ['800px', '500px'],
		  anim: 2,
		  yes: function(index,layero){//layer.msg('yes');    //点击确定回调
			  	var obj = $(layero).find("iframe")[0].contentWindow;
		       // alert(obj)
		        var imgId =  localStorage.getItem("imgId");
		        //alert(imgId)
		       
			    layer.close(index);
		  },
		  content: [bonuspath +'/backstage/apply/fileUpload']
	});
}

// 附件查看
function openShowFiles(){
	localStorage.setItem("id",id);
	var index = layer.open({
		type: 2,
		title: '附件查看',
		shade: [0],
		btn: ['关闭'], //按钮组
		area: ['800px', '500px'],
		anim: 2,
		yes: function(index,layero){//layer.msg('yes');    //点击确定回调
			const obj = $(layero).find("iframe")[0].contentWindow;
			const imgId = localStorage.getItem("imgId");
			layer.close(index);
		},
		content: [bonuspath +'/backstage/apply/findFileList?id='+id]
	});
}


/**
 * @author 
 * @date 2020-04-22
 * @function 条件查询
 * @returns 
 */
function search(){
	$("#search").trigger("click")
}

/**
 * @author 
 * @date 2020-04-24
 * @function 启动执行
 * @returns  
 */
$(function () {
	$.fn.modal.Constructor.prototype.enforceFocus = function(){};
	$("#keyWord").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	p = window.parent;
});






</script>
</body>
</html>