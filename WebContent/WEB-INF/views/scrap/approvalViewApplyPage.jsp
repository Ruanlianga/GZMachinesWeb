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
			<input type="hidden" value="${apply.id}" name="id" id ="detailsId" >
			
			<tr>
				<td width="15%"><span>申请人:</span>
				</td>
				<td width="35%" class="a">
					<input type="text" class="inp" disabled="disabled" value="${apply.applyer}"></td>
				</td>
				
			  <td width="15%"><span>申请备注:</span>
				</td>
				<td width="35%" class="a">
				<input type="text" class="inp" disabled="disabled" value="${apply.applyRemark}"></td>
				</td>
			</tr>
			
			<tr>
				<td width="15%"><span>单号:</span></td>
				<td width="35%" class="a">
				<input type="text" class="inp" disabled="disabled" value="${apply.code}"></td>
				</td>
				
				<td width="15%"><span>审核备注:</span></td>
				<td width="35%" class="a">
				<input type="text" class="inp" disabled="disabled"  value="${apply.auditRemark}"></td>
				</td>
			</tr>
			
			
			
			<tr>
			  <button class="btn btn-danger btn-sm" onclick="downFile()" id="search" title="下载附件"  style="width: 8%; margin-left: 10px;"  type="button">下载附件</button>
				<td colspan="6">
			
					<table id="machineTable" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:10%" class="center">物资名称</th>
							<th style="width:10%" class="center">物资型号</th>
							<th style="width:10%" class="center">物资编号</th>
							<th style="width:10%" class="center">报废数量</th>
							
						</tr>
					</thead>
					<tbody id="list">
						<c:forEach items="${apply.details}" var="detail" varStatus="index">
							<tr class='pa'>
								<input filed='id,val' type='hidden' value='${detail.id}' >
								<input filed='num,val' type='hidden' value='${detail.num}' >
								<input filed='typeId,val' type='hidden' value='${detail.typeId}' >
								<input filed='maId,val' type='hidden' value='${detail.maId}' >
								<th  class='center sort'>${index.index+1}</th>
								<th  class='center'>${detail.typeName}</th>
								<th  class='center'>${detail.modelName}</th>
								<th  class='center'>${detail.maCode}</th>
								<th  class='center'>${detail.num}</th>
								
								
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


function downFile(){
	var obj = paramConversionToObjOfForm("baseInfo");
		var id = obj.id;
		var btns = ['下载','关闭'];
		var title = "附件下载";
		var opt = 'download';
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
				  obj.downloadFile(); 
			  },
			  content: [bonuspath +'/backstage/scrapAudit/scrapFile?id='+id+"&opt="+opt]
		});
}


</script>
</body>
</html>