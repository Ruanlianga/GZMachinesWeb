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
				<input type="text" class="inp" name="auditRemark" value="${apply.code}"></td>
				</td>
			</tr>
			
			
			
			<tr>
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


/**
 * @author 
 * @date 2019-06-11
 * @function 批准结果
 * @returns
 */
function approvalApply(){
	var machines = [];
	var o={};
	var oo = {};
	
	if(JY.Validate.newForm("baseInfo")){
		
		var obj = paramConversionToObjOfForm("baseInfo");
	
		var size = $(".pa").size();
		if(size == 0){
			showMsg("您还没有选择任何需求,请先选择物资") ;
			return;
		}
		$(".pa").each(function(){
			o = paramConversionToObjOfForm2(this);
			machines.push(o);
		});
		
		obj.arr = machines;
		
		console.info("obj:",obj)
		
		var idx = layer.msg('正在提交保存,请稍等...', {
			  icon: 16
			  ,shade: 0.01
			  ,time:'-1'
		});
		
 	   	$.ajax({        
	        type:"POST",  
	        url:bonuspath +'/backstage/scrapAudit/approvalScrapApply', 
	        data: JSON.stringify(obj),
	        contentType:"application/json",
	        dataType:"json",
	        success:function(data){
	        	layer.close(idx);
	           	if(data.res == 1){
	           		parent.layer.msg("审核提交成功!", {icon: 1}, function () {
	                    window.parent.location.reload();//刷新父页面
	                });
				}else{
					var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
						layer.close(indexMsg);
					});
				}

			},
			error:function(data){
				layer.msg("数据加载失败!",{icon:2,time:2000})
				layer.close(idx);
			}
		}) ;  
	}
}

/**
 * @author 
 * @date 2019-06-11
 * @function 驳回结果
 * @returns
 */
function rejectApply(){
	var machines = [];
	var o={};
	var oo = {};
	
	if(JY.Validate.newForm("baseInfo")){
		
		$(".pa").each(function(){
			o = paramConversionToObjOfForm2(this);
			machines.push(o);
		});
		
		var obj = paramConversionToObjOfForm("baseInfo");
		obj.arr = machines;
		console.info("obj:",obj)
		
		var idx = layer.msg('正在提交保存,请稍等...', {
			  icon: 16
			  ,shade: 0.01
			  ,time:'-1'
		});
		
 	   	$.ajax({        
	        type:"POST",  
	        url:bonuspath +'/backstage/scrapAudit/rejectScrapApply', 
	        data: JSON.stringify(obj),
	        contentType:"application/json",
	        dataType:"json",
	        success:function(data){
	        	layer.close(idx);
	           	if(data.res == 1){
	           		parent.layer.msg("审核提交成功!", {icon: 1}, function () {
	                    window.parent.location.reload();//刷新父页面
	                });
				}else{
					var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
						layer.close(indexMsg);
					});
				}
			},
			error:function(data){
				layer.msg("数据加载失败!",{icon:2,time:2000})
				layer.close(idx);
			}
		})  
	}
}


</script>
</body>
</html>