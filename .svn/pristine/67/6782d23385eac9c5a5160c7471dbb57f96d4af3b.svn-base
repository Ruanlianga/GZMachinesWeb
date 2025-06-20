<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>


<title></title>

<style type="text/css">

	#baseInfo{
		/* border:1px solid; */
		text-align: center;
		width:96%;
		margin:0 2% 0 2%;
	}
	
	#baseInfo tr{
		height: 3em;
	}
	
	#baseInfo tr>input{
		width:96%;
	}
	
	#machineTable th{
		padding:5px;
		vertical-align:middle;
	}

	.inp{
		width:100%;
	}
	
	.inp2{
		width:68%;
	}
	
	#img{
		width:100%;
		height:150px;
	}
	
	.img{
		z-index:1;
		width:100px;
		height:100px;
		border:solid 1px black;
	}
	
	.img_div{
		display: inline-block;
		padding:10px;
	}
	
	.status{
		width:8%;
		position: absolute;
		z-index:999;
		top: 20%;
		left: 50%;
		opacity: 1;
	}

	.t{
		hidden:hidden;
	}

	.valid[readonly]{
		background-color: #ed9d9d !important;
		color: black !important;
	}
	.a>td{
		font-size: 1.5em;
	}
	
</style>
</head>
<body>
<div>
	<table  id="baseInfo" cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody class="a">
			<input type="hidden" value="${apply.id}" name="id" >
			<input type="hidden" value="${apply.url}" id = "url" >	
			
			<tr>
				<td colspan="6">
			
					<table id="baseTable" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width:5%" class="center">
								选择<!-- <label><input type="checkbox" class="ace" ><span class="lbl"></span></label> -->
							</th>
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:10%" class="center">文件名称</th>
							
						</tr>
					</thead>
					<tbody id="list">
						<c:forEach items="${apply.details}" var="detail" varStatus="index">
							<tr class='pa'>
								<th style="width:5%;vertical-align: middle;" class="center" >
									<label>
										<input type="checkbox" name="ids" id="inp${detail.id}" value="${detail.id}" fileName="${detail.fileName}" url="${detail.url}" class="ace inp cb" >
										<span class="lbl">
										</span>
									</label>
								</th>
								<th  class='center sort'>${index.index+1}</th>
								<th  class='center'>${detail.fileName}</th>	
								
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

$(function () {
	$("#baseForm").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
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

function downloadFile(){
	if(validCheckboxCheckedNum(10)){
		var urls = new Array();
		var names = new Array();
		var oos = $(".cb:checkbox:checked");
		for(var i=0;i<oos.length;i++){
			var oo = $(".cb:checkbox:checked").eq(i);		
			var url = oo.attr("url");
			var name = oo.attr("fileName");
			urls.push(url);
			names.push(name);
		}
		window.open(bonuspath +'/backstage/scrapAudit/exportZip?urls='+urls+"&fileNames="+names);
	}
	
}


</script>
</body>
</html>