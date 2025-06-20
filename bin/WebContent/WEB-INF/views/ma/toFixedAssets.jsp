<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.bonus.core.DateTimeHelper"%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<link rel="stylesheet" href="${bonuspath}/static/plugins/jedate/skin/gray.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jedate/jedate.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<title>Insert title here</title>

</head>
<style>
html,body{
	width:100%;
	height:100%;
	margin: 0;
	padding: 0;
}

</style>
<body>
	<form id="form" class="form-inline" method="POST" onsubmit="return false;">
	<table cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody>
			<tr style="display:none">
				<td colspan="2" class="ui-state-error"><input type="hidden" name="batchId" ></td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>固定资产编号：</td>
				<td class="DataTD">&nbsp;
				<input type="text" jyValidate="required"  maxlength="16" name="fixedAssetsNum" id="fixedAssetsNum" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>ERP编号：</td>
				<td class="DataTD">&nbsp;
				<input type="text" jyValidate="required"  maxlength="16" name="erpNum" id="erpNum" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			
		</tbody>
	</table>
		
		<input class="btn btn-info" style="width: 120px;margin-left: 40%;" type="button" value="上传" onclick="writeInfo()" />  
	</form>
	
	<script type="text/javascript">
	var batchId = localStorage.getItem("fixedId");
	var bonuspath = localStorage.getItem("bonuspath");
	function writeInfo(){
		$.ajax({
		      type: "POST",
		      url: bonuspath + "/backstage/machine/update",
		      data: {
		    	  id : batchId,
		    	  isFixedAssets : 1,
		    	  assetNum : $("#fixedAssetsNum").val(),
		    	  erpNum: $("#erpNum").val()
		      },
		      dataType : "json",
		      success: function(data){
		    	  var res = JSON.stringify(data.res);
					if(res == 1 || res == '1'){
						layer.alert('资料填写完成', {
						    skin: 'layui-layer-lan'
						    ,closeBtn: 0
						    ,anim: 4 //动画类型
						  });
						window.parent.location.reload();
						parent.layer.close(index);
						/* var batchId = data.obj.id;
						var isFixedAssets = data.obj.isFixedAssets;
						var assetsNum = data.obj.fixedAssetsNum;
						var erpNum = data.obj.erpNum;
						findDeviceNum(batchId,isFixedAssets,fixedAssetsNum,erpNum); */
					}
		      }
		   });
	}
	
	function findDeviceNum(batchId,isFixedAssets,fixedAssetsNum,erpNum){
		$.ajax({
		      type: "POST",
		      url: bonuspath + "/backstage/machine/findDeviceByBatchId",
		      data: {
		    	  batchId : batchId
		      },
		      dataType : "json",
		      success: function(data){
		    	  var res = data.obj.list;
		    	  var deviceNum;
		    	  if(res != null || res.length>0){
		    		 for(var i = 0;i<res.length;i++){
		    			 deviceNum = res[i].deviceNum;
		    			 updateMachinesByFixed(deviceNum,isFixedAssets,fixedAssetsNum,erpNum)
		    		 } 
		    	  }
		    	  
		      }
		   });
	}
	
	function updateMachinesByFixed(deviceNum,isFixedAssets,fixedAssetsNum,erpNum){
		$.ajax({
		      type: "POST",
		      url: bonuspath + "/backstage/machines/updByFixed",
		      data: {
		    	  deviceNum : deviceNum,
		    	  isFixedAssets : isFixedAssets,
		    	  fixedAssetsNum : fixedAssetsNum,
		    	  erpNum: erpNum
		      },
		      dataType : "json",
		      success: function(data){
				var res = JSON.stringify(data.res);
				window.parent.location.reload();
				parent.layer.close(index);
		      }
		   });
	}
</script>
</body>
</html>