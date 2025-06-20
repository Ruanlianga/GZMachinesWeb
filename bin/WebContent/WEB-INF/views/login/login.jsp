<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>智能机具系统</title>
<c:set var="bonuspath" value="${pageContext.request.contextPath}" />
<script>
	var bonuspath = '${bonuspath}';
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
 <script src="${bonuspath}/static/js/jquery/jquery-2.0.3.min.js"></script> 
<!--提示组件end-->
<link rel="icon" href="${bonuspath}/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${bonuspath}/favicon.ico" type="image/x-icon" />
<link rel="bookmark" href="${bonuspath}/favicon.ico" type="image/x-icon" />

 <link rel="stylesheet"
	href="${bonuspath}/static/css/bootstrap/bootstrap1.min.css" /> 
<link href="${bonuspath}/static/css/sys/images/iconfont/style.css" type="text/css" rel="stylesheet">

<link rel="stylesheet"
	href="${bonuspath}/static/css/jquery/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="${bonuspath}/static/css/sys/login.css" />
<script src="${bonuspath}/static/js/jquery/jquery-ui-1.10.3.full.min.js"></script>
<script src="${bonuspath}/static/js/jquery/jquery.md5.js"></script>
<script  type="text/javascript"src="${bonuspath}/static/js/sys/login.js"></script>
<style>
/* html{
height: 100%; width: 100%;
} */


input:-webkit-autofill {-webkit-box-shadow: 0 0 0px 1000px #053F68 inset;}
body{color:#fff; font-family:"微软雅黑"; font-size:14px;  width: 100%; height: 100%;}
	.wrap1{position:absolute; top:0; right:0; bottom:0; left:0; margin:auto }/*把整个屏幕真正撑开--而且能自己实现居中*/
	.main_content{background:url(images/main_bg.png) repeat; margin-left:auto; margin-right:auto; text-align:left; float:none; border-radius:8px;}
	.form-group{position:relative;}
	.login_btn{display:block; background:#159997 ; color:#fff; font-size:15px; width:100%; line-height:50px; border-radius:3px; border:none; }
	.login_input{width:80%; border:1px solid #159997; border-radius:8px;outline:none;  border:1.5px  solid; border-color:#098CBA;  line-height:45px; padding:2px 5px 2px 30px; background:none;}
	.login_input2{width:80%; border:1px solid #159997; border-radius:8px;outline:none;   line-height:50px; padding:2px 5px 2px 30px; background:none;}
		.login_input1{width:100%; border:1px solid #159997; border-radius:1px; line-height:0px; padding:1px 1px 1px 3px; background:none;}
	.icon_font{position:absolute; bottom:15px; left:55px; font-size:18px; color:white ;}
	.font16{font-size:46px;}
	.mg-t20{margin-top:30px;}
		.mg-t25{margin-top:40px;}
	.mg-t30{margin-top:5px;}
	@media (min-width:200px){.pd-xs-20{padding:0px;}}
	@media (min-width:768px){.pd-sm-50{padding:0px;}}
	#grad {
	  background: -webkit-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Safari 5.1 - 6.0 */
	  background: -o-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Opera 11.1 - 12.0 */
	  background: -moz-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Firefox 3.6 - 15 */
	  background: linear-gradient(#4990c1, #52a3d2, #6186a3); /* 标准的语法 */ 
     </style>
</head>

<body class=""     style="font-family: '微软雅黑';  background-color:#1588BB; background:url(${bonuspath}/static/css/sys/images/login/bg.png); no-repeat; background-size:100%  100%;   " >


<div  class="container  wrap1"  style="width:500px;  height:450px; ">
<div      style=" height: 80px; background-color: #002636;  border-radius: 15px "><p  class=" text-center  font16"   style="top:30px; color:#258CC3; ">黔送固定资产管理系统</p></div>
<div  class="mg-t30"   style="height:350px; background-color: #053F68;  border-radius: 15px; " >
<form action=""  onsubmit="return false">
<div  class="form-group "   style="text-align: center;">
   <i class="icon-user icon_font"  style="text-align: center;"></i>
    <input   class="login_input  mg-t25"   required="required"    type="text"  style=""   onclick="clean();"    value="" id="accountNameId" name="accountName"      placeholder="请输入用户名" />
</div>
<p  style="position: absolute;  left: 15%; color:red;" id="usererror"></p>
<div  class="form-group "   style="text-align: center;">
   <i class="icon-lock icon_font"  style="text-align: center;"></i>
    <input   class="login_input  mg-t20"   required="required"     type="password"   onclick="clean();"        id="passwordId" name="password"      placeholder="请输入密码" />
 </div>
 <p  style="position: absolute;  left: 15%; color:red;" id="passworderror"></p>
 <div  class="form-group "   style="text-align: center;">
<!--    <i class="icon-user icon_font"  style="text-align: center;"></i> -->
    <input   class="login_input2  mg-t20  "   id="loginBtn"    required="required"    type="button"  style="color:white; background-color:#098CBA;  text-align:center; font-size: 25px;"  id="accountNameId" name="accountName"  value="登   录   "     placeholder="" />
 </div> 
</form>

</div>
</div>



</body>
</html>
