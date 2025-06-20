<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>

		<c:choose>
			<c:when test="${page.totalRecord > 0}">
			<c:forEach items="${page.results}" var="task" varStatus="index">
				<tr ondblclick="setDBChecked(${task.id})">
					<th style="width:5%;vertical-align: middle;" class="center" >
						<label>
							<input type="checkbox" name="ids" id="inp${task.id}" value="${task.id}" class="ace inp cb" >
							<span class="lbl">
							</span>
						</label>
					</th>
					<th class="center" style="vertical-align: middle;width:5%">${(page.pageNum-1)*page.pageSize+index.index+1}</th>
					<th class="center" style="vertical-align: middle;width:10%">${task.code}</th>
					<th class="center" style="vertical-align: middle;width:10%">${task.maName}</th>
					<th class="center" style="vertical-align: middle;width:10%">${task.creator}</th>
					<th class="center" style="vertical-align: middle;width:15%">${task.createTime}</th>
					<th class="center" style="vertical-align: middle;width:10%">${task.applyRemark}</th>
					<th class="center" style="vertical-align: middle;width:5%">${task.isUploadFile}</th>
					<c:choose>
						<c:when test="${task.status eq '已审核'}">
				 		<th class="center" style="vertical-align: middle;width:15%">${task.status}</th>
				 		</c:when>
				        <c:when test="${task.status eq '待审核'}">
				 		<th class="center" style="vertical-align: middle;width:15%">${task.status}</th>
				 		</c:when>
				 		<c:otherwise>
				 			<th class="center" style="vertical-align: middle;width:15%"><a href='javascript:void(0)' onclick ='viewRemark("${task.id}")'>${task.status}</a></th>
				 		</c:otherwise>
				 	</c:choose>
					<th class="center" style="vertical-align: middle;width:15%">
						<a href="#" title="附件上传" onclick="uploadFile('${task.id}')" class="aBtnNoTD"><i class="icon-upload color-p bigger-140"></i></a>
						&nbsp;&nbsp;
						<a href="#" title="附件下传" onclick="view('${task.id}')" class="aBtnNoTD"><i class="icon-download color-p bigger-140"></i></a>
					</th>
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