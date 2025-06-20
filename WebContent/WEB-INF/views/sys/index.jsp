<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
<%@include file="../baseset.jsp" %>
<script type="text/javascript" src="${bonuspath}/static/js/jquery/jquery.cookie.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="${bonuspath}/static/css/ace/font-awesome.min.css" />
<link rel="stylesheet" href="${bonuspath}/static/css/jquery/jquery-ui-1.10.3.full.min.css" type="text/css" />
<!--[if IE 7]>
	<link rel="stylesheet" href="${bonuspath}/static/css/ace/font-awesome-ie7.min.css" />
<![endif]-->
<link rel="stylesheet" href="${bonuspath}/static/css/bootstrap/bootstrap-responsive.min.css" />
<!-- ace styles -->
<link rel="stylesheet" href="${bonuspath}/static/css/ace/ace.min.css" /> 
<!--[if lte IE 8]>
	<link rel="stylesheet" href="${bonuspath}/static/css/ace/ace-ie.min.css" />
<![endif]-->
<link type="text/css" rel="stylesheet" href="${bonuspath}/static/css/ace/ace-rtl.min.css" />
<link type="text/css" rel="stylesheet" href="${bonuspath}/static/css/ace/ace-responsive.min.css" /> 
<link type="text/css" rel="stylesheet" href="${bonuspath}/static/css/ace/ace-skins.min.css" />

<link type="text/css" rel="stylesheet" href="${bonuspath}/static/plugins/tabs/css/tab-control.min.css" />
<link type="text/css" rel="stylesheet" href="${bonuspath}/static/css/sys/index.css" />
<link rel="stylesheet" href="${bonuspath}/static/js/theme/default/layer.css" />

</head>
<body id='indexBody'>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small>
						<i class="icon-leaf"></i>
						<img src="${bonuspath}/static/img/icon.png">
					</small>
				</a><!-- /.brand -->
			</div><!-- /.navbar-header -->
			<!-- 头部右边 -->
			<%@include file="headerRight.jsp" %>
				<!-- 头部右边 end-->
		</div><!-- /.container  -->
	</div>
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
		<div class="sidebar" id="sidebar">
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">	
					<button title="基础配置"  class="btn"             onclick="getMenu('1','y')"><i class="icon-desktop"></i></button>					
					<button title="智慧仓储"  class="btn btn-primary" onclick="getMenu('2','y')"><i class="icon-th"></i></button>		
					<button title="智慧工厂"  class="btn btn-success" onclick="getMenu('3','y')"><i class="icon-user"></i></button>					
					<button title="智慧办公"  class="btn btn-warning" onclick="getMenu('4','y')"><i class="icon-bitbucket"></i></button>
				</div>
				<!--左边 导航 小图标  -->
				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span title="基础配置" class="btn"              onclick="getMenu('1','y')"></span>
					<span title="智慧仓储" class="btn btn-primary"  onclick="getMenu('2','y')"></span>
					<span title="智慧工厂" class="btn btn-success"  onclick="getMenu('3','y')"></span>
					<span title="智慧办公" class="btn btn-warning"  onclick="getMenu('4','y')" ></span>
				</div>
				<!--左边 导航 小图标  end-->
			</div><!-- #sidebar-shortcuts -->
			<!--左边 导航 菜单 -->
			<ul class="nav nav-list" id="menu_li_id" ></ul><!-- /.nav-list -->
			<!--左边 导航 菜单  end-->
			<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
			</div>					
		</div>
		<!-- 主要界面 右边 -->
		<div class="main-content" id="maincontent" >
			<div class="page-content ">	
				<div class="tab-control">		
					<div  class="tab simple">
						<form>
							<input class="prev" type="button" /> 
							<input class="next" type="button" /> 
							<input class="find" type="button" />
						</form>
						<ul class="tabul"></ul>
					</div>
					<!-- 标签查找 -->
					<div class="tab-find hidden ">
						<form><input class="text" type="text" /></form>
						<ul></ul>
					</div>
					<!-- 主体 -->
					<div class="main" >
					</div>
				</div>	
			</div>
			<!-- /.main-content -->
			<!-- 设置栏 -->
			<%@include file="settings.jsp" %>
			<%@include file="../dialog.jsp" %>
				<!-- 个人设置栏end -->
				<!-- 主要界面 右边 end-->
				<!-- 右边设置栏 -->
				<!-- <div class="ace-settings-container" id="ace-settings-container"></div> -->
				<!-- /#ace-settings-container -->
		</div><!-- /.main-container-inner -->
			<!-- 重回最上按钮 -->
			<!-- <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a> -->
	</div><!-- /.main-container -->
</div>
<script src="${bonuspath}/static/js/ace/ace-extra.min.js"></script>
<script src="${bonuspath}/static/js/bootstrap/bootstrap.min.js"></script>
<script src="${bonuspath}/static/js/ace/typeahead-bs2.min.js"></script>
<script src="${bonuspath}/static/js/ace/ace-elements.min.js"></script>
<script src="${bonuspath}/static/js/ace/ace.min.js"></script>
<script src="${bonuspath}/static/js/jquery/jquery-ui-1.10.3.full.min.js"></script>
<script src="${bonuspath}/static/js/jquery/jquery.md5.js"></script>
<script src="${bonuspath}/static/plugins/tabs/js/tab-control.min.js"></script>
<script src="${bonuspath}/static/js/sys/main.js"></script>
<script src="${bonuspath}/static/js/sys/index.js"></script>
<script src="${bonuspath}/static/js/sys/index.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<script type="text/javascript">
	$(function(){
		openMenu("1","menu56","menu7","待办任务管理","/backstage/rm/taskRecord/show");
	})
</script>
</body>
</html>