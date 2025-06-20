<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
		<c:if test="${page.totalRecord > 0}">
				<div class="row">
				<div class="col-sm-4">
					<div class="dataTables_info customBtn"></div>
				</div>
				<div class="col-sm-8">
					<!--设置分页位置-->
					<div id="pageing" class="dataTables_paginate paging_bootstrap">
						<ul class="pagination">	
						<c:choose>
							<c:when test="${(page.pageNum - 1) > 0}">
								<li class='prev'><a onclick='toPage(1)' href='#'>首页</a></li>
								<li class='prev'><a onclick='toPage(${page.pageNum - 1})' href='#'>上页</a></li>
							</c:when>
							<c:otherwise>
								<li class='prev disabled'><a href='##'>首页</a></li>
								<li class='prev disabled'><a href='##'>上页</a></li>
							</c:otherwise>	
						</c:choose>
						<c:if test="${page.pageNum - 1 > 0 }">
						<c:if test="${page.pageNum - 2 > 0 }">
						<li class='' ><a href='#' onclick='toPage(${page.pageNum - 2})' >..</a></li>
						</c:if>
						<li class='' ><a href='#' onclick='toPage(${page.pageNum - 1})' >${page.pageNum - 1}</a></li>
						</c:if>
						<li class='active' ><a href='#'>${page.pageNum}</a></li>
						<c:if test="${page.hasNPage}">
						<li class='' ><a href='#' onclick='toPage(${page.pageNum + 1})' >${page.pageNum + 1}</a></li>
						<c:if test="${page.hasNNPage}">
						<li class='' ><a href='#' onclick='toPage(${page.pageNum + 2})' >..</a></li>
						</c:if>
						</c:if>
						<c:choose>
							<c:when test="${page.hasNPage}">
								<li class='next'><a onclick='toPage(${page.pageNum+1})' href='#'>下页</a></li>
								<li class='next'><a onclick='toPage(${page.totalPage})' href='#'>尾页</a></li>
							</c:when>
							<c:otherwise>
								<li class='next disabled'><a href='##'>下页</a></li>
								<li class='next disabled'><a href='##'>尾页</a></li>
							</c:otherwise>
						</c:choose>
						<li class='next'><input onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)' id="inp" type='number' min='1' max='${fns:ceshi((page.totalRecord+page.pageSize-1),page.pageSize)}'  placeholder='页码' class='choseJPage' ></li>
						<li ><a class='btn btn-mini btn-success' onclick='jumpPage()' href='##'>跳转</a></li>
						<li class='disabled'>
							<select  onchange='setPageSize()' id='size' style='width:55px;float:left;' title='点击下拉选择，显示条数'>
							<option value='10'  <c:if test="${page.pageSize eq 10}">selected='selected'</c:if> >10</option>
							<option value='20' <c:if test="${page.pageSize eq 20}">selected='selected'</c:if> >20</option>
							<option value='30' <c:if test="${page.pageSize eq 30}">selected='selected'</c:if> >30</option>
						</li>
						</ul>
					</div>
					</div>
				</div>
				<input id="pageSize" type="hidden" value="${page.pageSize}" />
				<input id="pageNum" type="hidden" value="${page.pageNum}" />
				</c:if>