<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
      //创建令牌
      String token = java.util.UUID.randomUUID().toString();
      //存在session中一份,以后做判断
      session.setAttribute("TOKEN_IN_SESSION", token);
    %>
		<input type="hidden" name="token" value="<%=token%>"/>
		<c:forEach items="${list}" var="list" varStatus="index">
			<tr >
				<th  style="vertical-align: middle;" class="center">
				  <label>
						<input type="checkbox" id="id"  onclick="onClickHander(this)" chooseInfo="chooseInfo"  <c:if test="${!empty list.checked}">checked="checked"</c:if> value="${list.maId}" class="ace inp cb" >
						<span class="lbl">
						</span>
					</label>
				</th>
				<input filed='typeId,val' class="inp" type='hidden' value='${list.typeId}'>
				<input filed='maId,val' class="inp" type='hidden' value='${list.maId}'>
				<input filed='maCode,val' class="inp" type='hidden' value='${list.maCode}'>
				<th valign="middle" class="center">${index.index+1}</th>
				<th style="vertical-align: middle;" class="center">${list.name}</th>
				<th style="vertical-align: middle;" class="center">${list.typeName}</th>
				<th style="vertical-align: middle;" class="center">${list.payPrice}</th>
				<th style="vertical-align: middle;" class="center">${list.isAssets}</th>
				<th style="vertical-align: middle;" class="center">${list.fixedAssetsCode}</th>
				<th style="vertical-align: middle;" class="center">${list.originNum}</th>
			    <th style="vertical-align: middle;" class="center">${list.maCode}</th>
				<th style="vertical-align: middle;" class="center">${list.status}</th>
				<th style="vertical-align: middle;" class="center">
					<input filed='scrapNum,val' class="inp" value='1'  readonly="readonly" type='text' style='width: 100%;' >
				</th>
				<th style="vertical-align: middle;" class="center">${list.explain}</th>
				<th style="vertical-align: middle;" class="center">
                   <input filed='remarks,val' class="inp" type='text' style='width: 100%;' >
                </th>
				<th style="vertical-align: middle;" class="center">${list.remark}</th>
			
			</tr>
		</c:forEach>
		<tr>
			<td colspan="10" class="center" >
				<%@include file="../paging.jsp" %>
			</td>
		</tr>
		
			