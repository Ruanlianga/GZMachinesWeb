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
<%
	String currentDate = DateTimeHelper.getNowDate();
%>
</head>

<body>

<script type="text/javascript">
findParts();
findPartsType();
function findParts(){
	$.ajax({
	      type: "POST",
	      url: bonuspath + "/backstage/parts/findByPage",
	      data: {},
	      dataType : "json",
	      success: function(data){
	    	  $("#repairParts").html("");
	  		var results = data.obj.list.results;
	  		var html="";
	  		if(results!=null&&results.length>0){
	  			for(var i=0;i<results.length;i++){
	  				var l= results[i];
	  				html+="<option value='"+l.partsId+"'>"+l.partsName+"</option>";
	  			}
	  			$("#repairParts").append(html);
	  		}else{
	  			html+="<option ></option>";;
	  			$("#repairParts").append(html);
	  		}
	      }
	   });
}

function findPartsType(){
	$.ajax({
	      type: "POST",
	      url: bonuspath + "/backstage/parts/findByPage",
	      data: {},
	      dataType : "json",
	      success: function(data){
	    	  $("#partsModel").html("");
	  		var results = data.obj.list.results;
	  		var html="";
	  		if(results!=null&&results.length>0){
	  			for(var i=0;i<results.length;i++){
	  				var l= results[i];
	  				html+="<option value='"+l.partsId+"'>"+l.partsType+"</option>";
	  			}
	  			$("#partsModel").append(html);
	  		}else{
	  			html+="<option ></option>";;
	  			$("#partsModel").append(html);
	  		}
	      }
	   });
}
</script>

	<form id="form" class="form-inline" method="POST" onsubmit="return false;">
	<table cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody>
			<tr style="display:none">
				<td colspan="2" class="ui-state-error"><input type="hidden" name="repairId" ></td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>承修单位：</td>
				<td class="DataTD">&nbsp;
				<input type="text" jyValidate="required"  maxlength="16" name="repairCompany" id="repairCompany" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>承修人：</td>
				<td class="DataTD">&nbsp;
				<input type="text" jyValidate="required"  maxlength="16" name="repairPerson" id="repairPerson" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>维修费（元）：</td>
				<td class="DataTD">&nbsp;
				<input type="text" jyValidate="required"  maxlength="16" name="repairMoney" id="repairMoney" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>维修内容：</td>
				<td class="DataTD">&nbsp;
				<input type="text" jyValidate="required"  maxlength="16" name="repairContent" id="repairContent" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>所需配件：</td>
				<td class="DataTD">&nbsp;
				<select id="repairParts" name="repairParts"></select>
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>配件型号：</td>
				<td class="DataTD">&nbsp;
					<select id="partsModel" name="partsModel"></select>
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>配件价格（元）：</td>
				<td class="DataTD">&nbsp;
				<input type="text" jyValidate="required"  maxlength="16" name="partsPrice" id="partsPrice" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
		</tbody>
	</table>
		
		<input class="btn btn-info" style="width: 120px;margin-left: 40%;" type="button" value="上传" onclick="writeInfo()" />  
	</form>
	
	<script type="text/javascript">
	
	var bonuspath = localStorage.getItem("repairpath");
	var deviceNum = localStorage.getItem("repairDNum");
	
	function writeInfo(){
		$.ajax({
		      type: "POST",
		      url: bonuspath + "/backstage/lease/repair/writeInfo",
		      data: {
		    	  deviceNum : deviceNum,
		    	  repairCompany : $("#repairCompany").val(),
		    	  repairPerson: $("#repairPerson").val(),
		    	  repairMoney: $("#repairMoney").val(),
		    	  repairContent: $("#repairContent").val(),
		    	  repairContent: $("#repairContent").val(),
		    	  repairParts: $("#repairParts").val(),
		    	  partsModel: $("#partsModel").val(),
		    	  partsPrice: $("#partsPrice").val()
		      },
		      dataType : "json",
		      success: function(data){
		    	  var res = JSON.stringify(data.res);
					if(res == 1 || res == '1'){
						layer.alert('信息添加完成', {
						    skin: 'layui-layer-lan'
						    ,closeBtn: 0
						    ,anim: 4 //动画类型
						  });
//						var index=parent.layer.getFrameIndex(window.name);
//						parent.layer.close(index); //疯狂模式，关闭所有层
						repairing();
					}
		      }
		   });
	}
	
	function repairing(){
		$.ajax({
		      type: "POST",
		      url: bonuspath + "/backstage/lease/repair/chooseInput",
		      data: {
		    	  deviceNum : deviceNum,
				status : 1
		      },
		      dataType : "json",
		      success: function(data){
//					var index=parent.layer.getFrameIndex(window.name);
//					parent.layer.close(index); //疯狂模式，关闭所有层
					window.parent.location.reload();
					parent.layer.close(index);
		      }
		   });
	}
	
	</script>
</body>
</html>