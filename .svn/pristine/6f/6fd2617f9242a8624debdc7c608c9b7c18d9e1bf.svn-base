<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:choose>
	<c:when test="${page.totalRecord > 0}">
		<c:forEach items="${page.results}" var="ma" varStatus="index">
			<tr ondblclick="setChecked(${ma.id})">
				<th style="vertical-align:middle;" class="center">
					<label>
						<input type="checkbox" id="settle" name="settle" value="${ma.id}">
					</label>	
				</th> 
				<th style="vertical-align:middle;" class="center">${(page.pageNum-1)*page.pageSize+index.index+1}</th>
				<th style="vertical-align:middle;" class="center">
					${ma.cpyName}-${ma.cpyUnitName}
				</th>
				<th style="vertical-align:middle;" class="center">${ ma.project.name}</th>
				<th style="vertical-align:middle;" class="center">${ma.code }</th>
				<th style="vertical-align:middle;" class="center">
					<c:choose>
						<c:when test="${ma.isActive eq 1}">
							未结算
						</c:when>
						<c:otherwise>
							已结算
						</c:otherwise>
					</c:choose>
				</th>
				<th style="vertical-align:middle;" class="center">
					<c:choose>
						<c:when test="${ma.isActive eq 1}">
							<a href='#' title='结算' onclick='toSettle(${ma.id})' class='aBtnNoTD' ><i class='icon-ok color-p bigger-140'></i></a>
						</c:when>
						<c:otherwise>
						
						</c:otherwise>
					</c:choose>
					
				</th>
			</tr>
		</c:forEach>
		<tr><td colspan="11" class="center"><%@include file="../paging.jsp" %></td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr><td colspan='11' class='center'>没有相关数据</td></tr>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
function toSettle(id) {
	var data = {id:id};
	
	var indexMsg = layer.confirm("<h4 style='color:red'>您确定结算该工程吗?</h4>", {btn: ['确认','取消']},function(){
		layer.close(indexMsg);
	
	var idx = layer.msg('正在提交数据,请稍等...', {
		  icon: 16
		  ,shade: 0.01
		  ,time:'-1'
	});
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/projectSettlement/updateSettleSta', 
        data: JSON.stringify(data),
        dataType:"json",
        contentType:"application/json",      
        success:function(data) {
			layer.close(idx);
			console.log("data=",data)
			if(data.res == 1){
				showMsgAndReload(data.resMsg);
			}else{
				var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
					layer.close(indexMsg);
				});
			}
		},
		error:function(data){
			layer.close(idx);
			var indexMsg = layer.confirm('请求发送失败', {btn: ['关闭']},function(){
				layer.close(indexMsg);
			});
		}
	});
});	
	
}
function showMsgAndReload(msg){
	var indexMsg = layer.confirm("<h4 style='color:red;'>"+msg+"</h4>", {btn: ['关闭']},function(){
		layer.close(indexMsg);
		window.location.reload(); // 重新加载父页面
	});
}
</script>
  