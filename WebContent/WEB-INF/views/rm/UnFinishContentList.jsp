<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:choose>
	<c:when test="${page.totalRecord > 0}">
		<c:forEach items="${page.results}" var="ma" varStatus="index">
			<tr ondblclick="setChecked(${ma.id})">
				<th style="vertical-align:middle;" class="center">${(page.pageNum-1)*page.pageSize+index.index+1}</th>
				<%-- <th style="vertical-align:middle;" class="center">
					${ma.unitName}
				</th>
				<th style="vertical-align:middle;" class="center">
					${ma.projectName}
				</th>
				<th style="vertical-align:middle;" class="center">
					${ma.number}
				</th> --%>
				<th style="vertical-align:middle;" class="center">
					${ma.processName}-${ma.definitionName}
				</th>
				<th style="vertical-align:middle;" class="center">${ma.operationTime}</th>
				<%-- <th style="vertical-align:middle;" class="center">
					<c:choose>
						<c:when test="${ma.definitionId == 1}">
							管理员
						</c:when>
						<c:when test="${ma.definitionId == 2}">
							<c:choose>
								<c:when test="${ma.isExamine2 == 0 && ma.isApproval2 == 0}">
									吴堃
								</c:when>
								<c:when test="${ma.isExamine2 == 1 && ma.isApproval2 == 0}">
									管理员
								</c:when>
								<c:when test="${ma.isExamine2 == 1 && ma.isApproval2 == 1}">
									${ma.responer2}
								</c:when>
								<c:otherwise>
									暂无人员
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 5}">
							管理员
						</c:when>
						<c:when test="${ma.definitionId == 6}">
							吴堃
						</c:when>
						<c:when test="${ma.definitionId == 7}">
							管理员
						</c:when>
						<c:when test="${ma.definitionId == 8}">
							<c:choose>
								<c:when test="${ma.responer8 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer8}
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 11}">
							<c:choose>
								<c:when test="${ma.responer8 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer8}
								</c:otherwise>
							</c:choose>
							${ma.responer11}
						</c:when>
						<c:when test="${ma.definitionId == 12}">
							<c:choose>
								<c:when test="${ma.checkStatus12 ==2 && ma.checkStatus12 == 3}">
									${ma.checker12}
								</c:when>
								<c:when test="${ma.checkStatus12 ==1}">
									管理员
								</c:when>
								<c:when test="${ma.checkStatus12 ==4}">
									<c:choose>
										<c:when test="${ma.isExamine12 == 0 && ma.isApproval12 == 0}">
											吴堃
										</c:when>
										<c:when test="${ma.isExamine12 == 1 && ma.isApproval12 == 0}">
											管理员
										</c:when>
										<c:when test="${ma.isExamine12 == 1 && ma.isApproval12 == 1}">
											${ma.responer12}
										</c:when>
										<c:otherwise>
											暂无人员
										</c:otherwise>
									</c:choose>
									
								</c:when>
								<c:otherwise>
									暂无人员
								</c:otherwise>
								
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 18}">
							<c:choose>
								<c:when test="${ma.responer18 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer18}
								</c:otherwise>
							</c:choose>
							
						</c:when>
						<c:when test="${ma.definitionId == 19}">
							<c:choose>
								<c:when test="${ma.responer19 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer19}
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 20}">
							<c:choose>
								<c:when test="${ma.responer20 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer20}
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 21}">
							<c:choose>
								<c:when test="${ma.responer21 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer21}
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 22}">
							<c:choose>
								<c:when test="${ma.responer22 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer22}
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 23}">
							<c:choose>
								<c:when test="${ma.responer23 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer23}
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 24}">
							<c:choose>
								<c:when test="${ma.responer24 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer24}
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${ma.definitionId == 25}">
							<c:choose>
								<c:when test="${ma.responer25 eq null}">
									暂无人员
								</c:when>
								<c:otherwise>
									${ma.responer25}
								</c:otherwise>
							</c:choose>
							
						</c:when>
						<c:otherwise>
							暂无人员
						</c:otherwise>
					</c:choose>
				
		
				</th> --%>
				<th style="vertical-align:middle;" class="center">${ma.number}</th>
				<th style="vertical-align:middle;" class="center">${ma.creatorName}</th>
				<th style="vertical-align:middle;color:red" class="center">
				<c:choose>
				<c:when test="${page.isFinish == 0}">
				任务未完成
				</c:when>
				<%-- <c:otherwise>
				任务已完成
			 </c:otherwise> --%>
				</c:choose>
				</th>
<%-- 				<th style="vertical-align:middle;" class="center"><a href='#' title='查看' onclick="edit(${ma.id} ,${ma.definitionId})" class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a></th>
 --%>			</tr>
		</c:forEach>
		<tr><td colspan="7" class="center"><%@include file="../paging.jsp" %></td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr><td colspan='7' class='center'>没有相关数据</td></tr>
	</c:otherwise>
</c:choose>
<script type="text/javascript">

</script>
  