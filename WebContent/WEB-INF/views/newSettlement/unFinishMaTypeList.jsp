<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
		<c:choose>
			<c:when test="${!empty list}">
			<c:forEach items="${list}" var="ma" varStatus="index">
			<!-- ondblclick="setChecked(${ma.id})" -->
			
				<tr class="mas" id="row${ma.id}" >
				
					<input type="hidden" filed="id,val" value="${ma.id}">
					
					<input type="hidden" filed="typeId,val" value="${ma.type.id}">
					
					<input type="hidden" filed="machineId,val" value="${ma.machine.id}">
					
					<input type="hidden" filed="startDate,val" value="${ma.startDate}">
					
					<input type="hidden" filed="num,val" value="${ma.num}">
					
					<input type="hidden" filed="price,val" value="${ma.price}"> 
					
					<input type="hidden" filed="endDate,val" value="${ma.endDate}">
					
					<input type="hidden" filed="backDate,val" value="${ma.backDate}">
					
					<input type="hidden" filed="status,val" value="${ma.status}">
					
					<th style="width:5%" class="center">
						<label>
							<input checked="checked" type="checkbox" onchange="checkStatusChange(this,${ma.type.isCount})" id="inp${ma.id}" value="${ma.id}" class="ace inp cb" >
							<span class="lbl">
							</span>
						</label>
					</th>
					<th class="center">${(page.pageNum-1)*page.pageSize+index.index+1}</th>
					<th class="center">${ma.type.parentName}</th>
					<th class="center">${ma.type.name}</th>
					<th class="center">${ma.num}${ma.type.unit}</th>
					<th class="center">${ma.machine.deviceCode}</th>
					<%-- <th class="center">${ma.price}</th> --%>
					  <th  class="center">
					<input filed="price,val"   onblur="validNum(this,${ma.price})" style="width:100%;" id="price${ma.id}" type="number" value="${ma.price}">
					</th>
					<th class="center">
						<c:choose>
							<c:when test="${ma.status eq 1}">
								在用
							</c:when>
							<c:otherwise>
								已退
							</c:otherwise>
						</c:choose>
					</th>
					<th class="center">${ma.startDate}</th>
					<th class="center">${fns:nullPlaceholder(ma.backDate,'---')}</th>
				    <th class="center"><input filed="remarks,val"   style="width:100%;" id="remark${ma.id}" type="text" value=""></th>
				    <th class="center"></th>
					
					
				</tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan='14' class='center' style="color:red;font-size:2em;">没有相关数据</td></tr>
			</c:otherwise>
		</c:choose>		