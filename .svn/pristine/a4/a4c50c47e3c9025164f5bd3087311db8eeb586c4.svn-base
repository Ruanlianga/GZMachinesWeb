<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>

		<c:choose>
			<c:when test="${page.totalRecord > 0}">
			<c:forEach items="${page.results}" var="scrap" varStatus="index">
				<tr ondblclick="setDBChecked(${scrap.id})">
					<th style="width:5%;vertical-align: middle;" class="center" >
						<label>
							<input type="checkbox" name="ids" id="inp${scrap.id}" status="${scrap.status}" value="${scrap.id}" class="ace inp cb" >
							<span class="lbl">
							</span>
						</label>
					</th>
					<th class="center" style="vertical-align: middle;width:5%">${(page.pageNum-1)*page.pageSize+index.index+1}</th>
					<th class="center" style="vertical-align: middle;width:10%">${scrap.code}</th>
					<th class="center" style="vertical-align: middle;width:10%">${scrap.typeName}</th>
					<th class="center" style="vertical-align: middle;width:5%">${scrap.applyer}</th>
					<th class="center" style="vertical-align: middle;width:10%">${scrap.applyTime}</th>
					<th class="center" style="vertical-align: middle;width:10%">${scrap.applyRemark}</th>
					<th class="center" style="vertical-align: middle;width:5%">${scrap.auditor}</th>
					<th class="center" style="vertical-align: middle;width:10%">${scrap.auditTime}</th>
					<th class="center" style="vertical-align: middle;width:10%">${scrap.auditRemark}</th>
					
				<c:choose>
				     <c:when test="${scrap.status eq 0}">
				     <th  style="vertical-align: middle;width:10%" class="center">待审核</th>
				     </c:when>
				     <c:when test="${scrap.status eq 1}">
				       <th  style="vertical-align: middle;width:10%" class="center">审核通过</th>
				     </c:when>
				     <c:otherwise>
				     <th  style="vertical-align: middle;width:10%" class="center">审核驳回</th>
				     </c:otherwise>
				</c:choose>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10" class="center" >
					<%@include file="../paging.jsp" %>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr><td colspan='10' class='center'>没有相关数据</td></tr>
			</c:otherwise>
		</c:choose>		