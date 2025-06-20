<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
<style>
	#upload{
		width: 15%;
	    height: 33px;
	    background-color: #009688;
	    border: 0;
	    color: #fff;
	    margin-left: 41%;
    }
    #shangchuan{
       margin-top: 5%;
    }
  
</style>
</head>
<body>
    <!--************这里是上传图片的代码***************-->
    <!--************这里添加的隐藏的输入框，用来传递images的参数***************-->
     <div id=type>
     &nbsp;&nbsp;机具名称:<select  style="width:30%"  id="typetId" name="typetId" onchange="loadNameById()">
    </select>
    &nbsp;&nbsp;规格型号: <select style="width:30%" id="typeParentId" name="typeParentId">
    <option value="">--请先选名称--</option>
     </select>
     </div>
     <div class="layui-upload" id=shangchuan>
        &nbsp;&nbsp;选择文件:<button type="button" class="layui-btn" id="test1">上传文件</button>
        <div class="layui-upload-list">
            <p id="demoText"></p>
        </div>
    </div>
    <!--************上面里是上传图片的代码***************-->
<button  id="upload">上传</button>
<script src="${bonuspath}/static/js/ma/submitLoad.js?v=1"></script>		
</body>
</html>