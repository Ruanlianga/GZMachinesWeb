<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%@include file="../webPortalCommonSet.jsp" %>

<title></title>

<style type="text/css">
	#baseInfo{
		/* border:1px solid; */
		text-align: center;
		width:96%;
		margin:0 2% 0 2%;
	}
	
	#baseInfo tr{
		height: 3em;
	}
	
	#baseInfo tr>input{
		width:96%;
	}
	#machineTable th{
		padding:5px;
		vertical-align:middle;
	}

	.inp{
		width:100%;
	}
	
	.inp2{
		width:68%;
	}
	
	#img{
		width:100%;
		height:150px;
	}
	
	#baseInfo td{
		border: solid 1px #d3d1d1;
	}
	
	.img{
		z-index:1;
		width:100px;
		height:100px;
		border:solid 1px black;
	}
	.img_div{
		display: inline-block;
		padding:10px;
	}
	.status{
	width:8%;
	position: absolute;
	z-index:999;
	top: 20%;
	left: 50%;
	opacity: 1;
}

.t{
	hidden:hidden;
}

.valid[readonly]{
		background-color: #ed9d9d !important;
		color: black !important;
	}
	
</style>
</head>
<body>
<div>
	<table  id="baseInfo" cellspacing="0" cellpadding="0" border="0" class="customTable">
		<tbody class="a">
			
			<tr style="height:1.5em;">
				<td colspan="8"></td>
			</tr>
			<tr>
				<td width="10%">
					<a href="#" title="过滤" onclick="unitTree();" class="lrspace3 aBtnNoTD" data-toggle="modal">选择单位&nbsp;
						<i class='icon-zoom-in color-p bigger-140' style="color:blue"></i>
					</a>
				</td>
				<td  width="15%">
				<input type="hidden" id="unitId" name="unitId" value="0" >
				<input type="text"   id="unitName" value="" />
			
				</td>
				
				<td width="10%">
					<a href="#" title="过滤" onclick="projectTree();" class="lrspace3 aBtnNoTD" data-toggle="modal">选择工程&nbsp;
						<i class='icon-zoom-in color-p bigger-140' style="color:blue"></i>
					</a>
				</td>
				<td width="15%"  style="text-align: left;">
			 	<input type="hidden" id="projectId" name="projectId"  value="">
				<input type="text" id="projectName" value="" />  
				
					 
				</td>
				
				 <td width="10%"><span>协议号:</span></td>
				<td width="15%"  style="text-align: left;">
				<input type="hidden" id="agreementId"  name="agreementId" value="">
					<input id="agreementCode" type="text" style="width:80%;" class="inp" disabled="disabled" value="">
				</td>
				
				
				
			</tr>
			
		
		
			
			
			<tr >
				<td width="10%"><span>结算人(租赁方):</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="sltMan" type="text"  class="inp" >
				</td>
				<td width="10%"><span>结算人联系电话:</span></td>
				<td width="15%"  style="text-align: left;">
					<input id="sltManPhone" type="text"  class="inp" >
				</td>
				<td width="10%"><span>备注:</span></td>
				<td width="15%" colspan="3" style="text-align: left;">
					<textarea id="remarks" rows="2" cols="" class="inp"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="8" width="13%">
				<div class="cl pd-5 bg-1 bk-gray" style="padding-top:5px;">
					<span class="l" style="width: 60%;text-align: left;" id="sForm">
						<input type="text"  id="keyWord" name="keyWord" placeholder="请输入搜索关键词...">
						<a class="btn btn-success radius" data-title="点击查询" onclick="" href="javascript:;" >
							查询
						</a>
					</span>
					<span class="r" style="width: 38%;text-align: right;">
					</span>
						<a class="btn btn-info radius" style="margin-left: 30%;" data-title="点击上传" onclick="uploadFile()" href="javascript:;" >
						上传
					</a>
					<a class="btn btn-danger radius" data-title="点击下载" onclick="downloadFile()" href="javascript:;" >
						下载
					</a>
				</div>
				
				</td>
			</tr>
			<tr>
				<td colspan="8">
				<form id="baseForm" class="form-inline" method="POST">
					<table id="machineTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:4%" class="center hidden-480">序号</th>
							<th style="width:8%" class="center">机具名称</th>
							<th style="width:8%" class="center">机具规格</th>
							<th style="width:7%" class="center">数量(单位)</th>
							<th style="width:9%" class="center">设备编码</th>
							<th style="width:7%" class="center">租赁单价</th>
							<th style="width:4%" class='center'>状态</th>
							<th style="width:8%" class="center">起租日期</th>
							<th style="width:8%" class='center'>退租日期</th>
							<th style="width:8%" class='center'>备注</th>
							<th style="width:8%" class='center'>操作</th>
							
							
						</tr>
					</thead>
					<tbody id="list">
						<tr><td colspan="14"  class="center" style='color:red;font-size:2em;' >请先选择需要完结的工程!!!</td></tr>
					</tbody>
					</table>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="col-sm-3">
	    <div class="dataTables_info customBtn" >
		   &emsp;<a href="#" title="导出" onclick="exportData();"   class="lrspace3" ><i class='icon-download color-blue bigger-220'></i></a> 
		</div>
	</div>
</div>


<script type="text/javascript">
var agreementId = $("#agreementId").val();


var p; //父页面对象,多层关闭时使用


/**
 * @author 
 * @date 2020-06-22
 * @function 条件查询
 * @returns 
 */
function search(){
	$("#search").trigger("click")
}

/**
 * @author 
 * @date 2020-05-24
 * @function 启动执行
 * @returns  
 */
$(function () {
	$.fn.modal.Constructor.prototype.enforceFocus = function(){};

	$("#keyWord").keydown(function(e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
	p = window.parent;
});


/**
 * @author 
 * @date 2020-12-02
 * @function 提交申请审核结果
 * @returns
 */
function submitApply(){
	
	var machines = [];
	var o={};
	if(JY.Validate.newForm("baseInfo")){
		
		var obj = paramConversionToObjOfForm("baseInfo");
		console.info("obj1:",obj)
		var oo = {};
		var arr = [];
		
		$(".mas").each(function(){
			o = paramConversionToObjOfForm2(this);
			//console.log("qqq"+JSON.stringify(o));
			arr.push(o);
		
		});
		obj.arr = arr;
		console.info("obj2:",obj)
		
		var indexMsg = layer.confirm("<h4 style='color:red'>您确定保存该选择结果吗?</h4>", {btn: ['确认','取消']},function(){
				layer.close(indexMsg);
		
		
		var idx = layer.msg('正在提交保存,请稍等...', {
			  icon: 16
			  ,shade: 0.01
			  ,time:'-1'
		});
		

	 	   $.ajax({        
	        type:"POST",  
	        url:bonuspath +'/backstage/projectFinish/saveFinishInfo', 
	        data: JSON.stringify(obj),
	        contentType:"application/json",
	        dataType:"json",
	        success:function(data){
	        	
	        	layer.close(idx);
	        	if(data.res == 1){
	        		
					var i = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(i);
					parent.showMsgAndReload(data.resMsg);
				}else{
					var indexMsg = layer.confirm(data.resMsg, {btn: ['关闭']},function(){
						layer.close(indexMsg);
					});
				}

			},
			error:function(data){
				layer.msg("数据加载失败!",{icon:2,time:2000})
				layer.close(idx);
			}
		}) ; 
		}) ;
	}
}


function validNum(that,price){

	var newPrice = $(that).val();
   
	if(newPrice == '' || isNaN(newPrice)){
		
		$(that).val(price);
	}else{
		if(parseFloat(newPrice) < 0){
			$(that).val("0");
		}else{
		
			$(that).val(newPrice);
		
		}
	}

	
}


/**
 * @author 
 * @date 2020-12-3
 * @function 工程选择事件
 * @returns  
 */
function selectProject(){
	var agreementId = $("#agreementId").val();
	if(JY.Object.notNull(agreementId)){
		//alert(agreementId)
		findUnSltMaTypeList(agreementId);
	}else{
		$("#total").val(0);
		$("#list").html("<tr><td colspan='14'  class='center' style='color:red;font-size:2em;' >请先选择需要结算的工程!!!</td></tr>");
	}
}

/**
 * @author 
 * @date 2020-05-24
 * @function 初始化页面ajax请求 返回html页面
 * @returns  
 */
function findUnSltMaTypeList(agreementId){
	var abc = {};
	abc = getObjParam("sForm");
	abc.agreement = {id:agreementId};
	var idx = layer.msg('正在加载数据,请稍等...', {
		  icon: 16
		  ,shade: 0.01
		  ,time:'-1'
	});
	$.ajax({        
        type:"POST",  
        url:bonuspath +'/backstage/projectFinish/findUnSltMaTypeList', 
        data: JSON.stringify(abc),      
        dataType:"html",
        contentType:"application/json",
        success:function(data){
        	
        	$("#list").html(data);
        
        	layer.close(idx);
		}
	})
}





function setUnitForm(){
	var unitId = localStorage.getItem("unitId");
	var unitName = localStorage.getItem("unitName");
	$("#unitId").val(unitId);
	$("#unitName").val(unitName);
	
}

function unitTree(){
	localStorage.setItem("isOpen","1");
	localStorage.setItem("unitId","");
	localStorage.setItem("unitName","");
	localStorage.setItem("unitTreeName",$("#unitName").val());
	layer.open({
		type: 2,
		title:['选择结算单位','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/company/unitTreePlus'
	});
}

function setProjectForm(){
	var projectId = localStorage.getItem("projectId");
	var projectName = localStorage.getItem("projectName");
	$("#projectId").val(projectId);
	$("#projectName").val(projectName);
	getAgreementNum();

}

function projectTree(){
	var unitId = $("#unitId").val();
	if(unitId == 0){
		JY.Model.info("请选择单位");
	}else{
		localStorage.setItem("unitId",unitId);
		localStorage.setItem("projectId","");
		localStorage.setItem("projectName","");
		localStorage.setItem("projectTreeName",$("#projectName").val());
		layer.open({
			type: 2,
			title:['工程名称','background-color: #438EB9;color:#fff'],
			shadeClose:true,
			shade:false,
			maxmin: true,
			area: ['400px', '400px'],
			content: bonuspath+'/backstage/project/projectTree'
		});
	}
}

function getAgreementNum(){
 
	var unitId = $("#unitId").val();
	var projectId = $("#projectId").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/agreement/getAgreementCode',
			{companyId:unitId,projectId:projectId}, function(data) {
				//alert(JSON.stringify(data));
				var l = data.obj;
				if(l == null){
					$("#agreementCode").val("尚未签订协议");
				}else{
					$("#agreementCode").val(l.code);
					$("#agreementId").val(l.id);
					selectProject()
				}
			}
	);
}

function exportData(){
	var id = $("#agreementId").val();
	$("#baseForm").attr("action",bonuspath +'/backstage/projectSettlement/export?id='+id+"&iddl="+iddl);
	$("#baseForm").attr("target","downloadFrame");//iframe的名字
	$("#baseForm").submit();
	/* window.location.href=bonuspath+"/backstage/projectSettlement/export?agreementId"+agreementId;   */
}

/**
 * 上传工程完结文件
 */
function uploadFile(){
	var code = $("#agreementCode").val();
	if(code !=null){
		localStorage.setItem("code",code);
		localStorage.setItem("bonuspath",bonuspath);
		//iframe层-父子操作
		layer.open({
		  type: 2,
		  title:['工程完结上传','background-color: #27A3D9;color:#fff'],
		  shadeClose:true,
		  shade:false,
		  maxmin: true,
		  area: ['600px', '400px'],
		  content: bonuspath+'/backstage/projectFinish/uploadProFile'
		});
	}else{
		return;
	}
}

function downloadFile(){
	var code = $("#agreementCode").val();
	var projectName = $("#projectName").val();
	JY.Ajax.doRequest(null, bonuspath + '/backstage/projectFinish/downloadFile',
			{code:code,projectName:projectName}, function(data) {
			}
	);
	layer.msg("文件已下载至D:/files文件夹",{icon:1,time:2000})
}
</script>
</body>
</html>