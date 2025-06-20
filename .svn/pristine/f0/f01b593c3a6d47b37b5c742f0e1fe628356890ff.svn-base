<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:choose>
	<c:when test="${page.totalRecord > 0}">
		<c:forEach items="${page.results}" var="ma" varStatus="index">
			<tr ondblclick="setChecked(${ma.id})">
				
				<th style="vertical-align:middle;" class="center">${(page.pageNum-1)*page.pageSize+index.index+1}</th>
				<th style="vertical-align:middle;" class="center">
					${ma.projectName}
				</th>
				<th style="vertical-align:middle;" class="center">${ma.leaseCpy}-${ma.companyName}</th>
				<th style="vertical-align:middle;" class="center">暂无</th>
				<th style="vertical-align:middle;" class="center">暂无</th>
				<th style="vertical-align:middle;" class="center">暂无</th>
				<th style="vertical-align:middle;" class="center">${ma.parentTypeName}</th>
				<th style="vertical-align:middle;" class="center">
					${ma.typeName}
				</th>
				<th style="vertical-align:middle;" class="center">
					${ma.unit}
				</th>
				<th style="vertical-align:middle;" class="center">
					${ma.leaseDate}
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
</script>
  