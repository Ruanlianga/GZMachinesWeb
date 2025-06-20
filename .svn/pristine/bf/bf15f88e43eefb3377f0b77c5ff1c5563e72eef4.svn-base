<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<c:choose>
			<c:when test="${page.totalRecord > 0}">
			<c:forEach items="${page.results}" var="slt" varStatus="index">
				<tr ondblclick="setChecked(${slt.id})">
					<th style="width:5%" class="center">
						<label>
							<input type="checkbox" id="inp${slt.id}" status="${slt.status.id}" value="${slt.id}" class="ace inp cb" >
							<span class="lbl">
							</span>
						</label>
					</th>
					<th class="center">${(page.pageNum-1)*page.pageSize+index.index+1}</th>
					<th class="center">${slt.code}</th>
					<th class="center">${slt.unitName}</th>
					<th class="center">${slt.projectName}</th>
					<th class="center">${slt.creator}</th>
					<th class="center">${slt.createTime}</th>
					<%-- <th  class="center  " >
						<i onclick="uploadFile(${slt.id},${slt.code})"   title="文件上传" class='icon-circle-arrow-up color-blue  bigger-140'></i> 
					    <i onclick="downloadFile(${slt.id})" title="文件下载" class='icon-circle-arrow-down color-blue bigger-140'></i>    
 					 </th> --%>
					
				</tr>
			</c:forEach>
			<tr>
				<td colspan="12" class="center" >
					<%@include file="../paging.jsp" %>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr><td colspan='12' class='center'>没有相关数据</td></tr>
			</c:otherwise>
		</c:choose>		