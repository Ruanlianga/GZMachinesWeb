<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<div class="navbar-header pull-right" >
				<!-- 	<div class="navbar-header pull-right" role="navigation"> -->
					<ul class="nav ace-nav">
						<li class="userperLi">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img id="mainHeadpic" class="nav-user-photo" src="<c:choose>
									<c:when test="${!empty currentAccount.picUrl}">${bonuspath}/static/css/sys/images/user/hpic0.jpg</c:when> 
										<c:otherwise><c:url value="/static/css/sys/images/user/hpic0.jpg" /></c:otherwise>
										</c:choose>" />
								<span class="user-info">
									<small>欢迎光临,</small> 
									<span id="user-info-name ">
										<c:choose>
										<c:when test="${!empty currentAccount.name}">${currentAccount.name}</c:when>
										<c:when test="${!empty currentAccount.loginName}">${currentAccount.loginName}</c:when>
										<c:otherwise>用户</c:otherwise>
										</c:choose> 
									</span>									
								</span>
								<i class="icon-caret-down"></i>
							</a>
							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li><a href="javascript:void(0);" onclick="perData();" ><i class="icon-user"></i>个人设置</a></li>
								<li><a href="javascript:void(0);" onclick="perSetting();" ><i class="icon-cog"></i>系统设置</a></li>
								<li class="divider"></li>
								<li><a href="javascript:void(0);" onclick="logout();"><i class="icon-off"></i>退出</a></li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->