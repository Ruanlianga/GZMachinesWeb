<%@page import="com.bonus.core.DateTimeHelper"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
<%
 String endTime = DateTimeHelper.getNowDate();
%>
<link rel="stylesheet" href="${bonuspath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${bonuspath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${bonuspath}/static/plugins/jquery.jqprint-0.3.js"></script>
<script src="${bonuspath}/static/js/layer.js"></script>
<link rel="stylesheet" href="${bonuspath}/static/js/layui/css/layui.css" />
<script src="${bonuspath}/static/js/layui/layui.js"></script>
</head>
<style>
.center {
font-size: 14px;
color: black;
font-style: normal;
}
.title {
text-align:center;
font-size: 16px;
color: black;
font-style: normal;
}
</style>

<body>

<script type="text/javascript" >
        var idTmr;
        function  getExplorer() {
            var explorer = window.navigator.userAgent ;
        /*   //ie
            if (isIE || !!window.ActiveXObject || "ActiveXObject" in window) {
                return 'ie';
            } */
          //ie
            if (explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            } 
            //firefox
            else if (explorer.indexOf("Firefox") >= 0) {
                return 'Firefox';
            }
            //Chrome
            else if(explorer.indexOf("Chrome") >= 0){
                return 'Chrome';
            }
            //Operad
            else if(explorer.indexOf("Opera") >= 0){
                return 'Opera';
            }
            //Safari
            else if(explorer.indexOf("Safari") >= 0){
                return 'Safari';
            }
          //Netscape
            else if(explorer.indexOf("Netscape")>= 0) {
            	 return 'Netscape';
            }
        }
        function table2excel(tableid,name) {//整个表格拷贝到EXCEL中
            if(getExplorer()=='ie')
            {
                var curTbl = document.getElementById(tableid);
                var oXL = new ActiveXObject("Excel.Application");
                 
                //创建AX对象excel
                var oWB = oXL.Workbooks.Add();
                //获取workbook对象
                var xlsheet = oWB.Worksheets(1);
                //激活当前sheet
                var sel = document.body.createTextRange();
                sel.moveToElementText(curTbl);
                //把表格中的内容移到TextRange中
                sel.select();
                //全选TextRange中内容
                sel.execCommand("Copy");
                //复制TextRange中内容 
                xlsheet.Paste();
                //粘贴到活动的EXCEL中      
                oXL.Visible = true;
                //设置excel可见属性
 
                try {
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
                } catch (e) {
                    print("Nested catch caught " + e);
                } finally {
                    oWB.SaveAs(fname);
 
                    oWB.Close(savechanges = false);
                    //xls.visible = false;
                    oXL.Quit();
                    oXL = null;
                    //结束excel进程，退出完成
                    //window.setInterval("Cleanup();",1);
                    idTmr = window.setInterval("Cleanup();", 1);
 
                }
            }
            else
            {
                tableToExcel(tableid,name)
            }
        }
        function Cleanup() {
            window.clearInterval(idTmr);
            CollectGarbage();
        }
        var tableToExcel = (function() {
       		 var uri = 'data:application/vnd.ms-excel;base64,',
		 //格式化导出表格的样式
		 template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel"'+
		    'xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>'
		    +'<x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets>'
		    +'</x:ExcelWorkbook></xml><![endif]-->'+
		    ' <style type="text/css">'+
		    '.excelTable  {'+
		    'border-collapse:collapse;'+
		     ' border:thin solid #999; '+
		    '}'+
		    '   .excelTable  th {'+
		    '   border: thin solid #999;'+
		    '  padding:20px;'+
		    '  text-align: center;'+
		    '  border-top: thin solid #999;'+
		    ' background-color: #E6E6E6;'+
		    ' }'+
		    ' .excelTable  td{'+
		    ' border:thin solid #999;'+
		    '  padding:2px 5px;'+
		    '  text-align: center;'+
		    ' }</style>'+
		    '</head><body ><table class="excelTable">{table}</table></body></html>',  
                base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
                format = function(s, c) {
                    return s.replace(/{(\w+)}/g,
                    function(m, p) { return c[p]; }) }
                return function(table, name) {
		if(name.length == 0){name='导出Excel信息';}
                if (!table.nodeType) table = document.getElementById(table)
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
				//window.location.href = uri + base64(format(template, ctx))
				var downloadLink = document.createElement("A");
				downloadLink.href = uri + base64(format(template, ctx));
				downloadLink.download = name + '_' + formatTime(new Date(new Date().getTime()),'yyyy-mm-dd')+'.xls';
				downloadLink.target = '_blank';
				document.body.appendChild(downloadLink);
				downloadLink.click();
				document.body.removeChild(downloadLink);
              }
            })()
 
/**
* 扩展String对象,添加查找字符串出现的次数
* @param (String) str 要测试的字符串
*/
String.prototype.findCount =function(str){
	return this.split(str).length - 1;
}
 
/**
* 复制字符串
* @param (String) str 要复制的字符串
* @param (String) num 要复制的次数
* @return (Number) 复制后的字符串
*/
function copy(str , num){
	var tmp = '';
	for(var i=0; i<num; i++){
		tmp += str;
	}
	return tmp;
}
 
/**
* 格式化时间字符串，支持Date对象
* @param (Number) time 要格式化的时间串，或者是一个Date对象
* @param (String) format 格式，如：yyyymmddhhiiss yyyy-mm-dd hh:ii:ss
* @return (String) 格式化后的时间串
*/
function formatTime(time /* Number */,format /* String */){
 var 
	y=format.findCount('y'),
	m=format.findCount('m'),
	d=format.findCount('d'),
	h=format.findCount('h'),
	i=format.findCount('i'),
	s=format.findCount('s');
	
	time=time || '';
	format = format || '';
	format = format.toLowerCase();
	if(time == '') {return time;}
	if(time.constructor == Date){
		var tmp='' + time.getFullYear() +
			('00' + (time.getMonth() + 1)).slice(-2) +	
			('00' + time.getDate()).slice(-2) +	
			('00' + time.getHours()).slice(-2) +	
			('00' + time.getMinutes()).slice(-2) +	
			('00' + time.getSeconds()).slice(-2);
		    time = tmp;	
	}
	/*
	if(time.length <format.length){
		alert('要格式化的时间串与转换格式不一致！');
		return false;
	}
	*/
 
	if(y > 0){
		format = format.replace(copy('y',y),time.substring(0,4).slice(-y));
	}
 
	if(m > 0){
		format = format.replace(copy('m',m),('00'+time.substring(4,2)).slice(-m));
	}
 
	if(d > 0){
		format = format.replace(copy('d',d),('00'+time.substring(6,2)).slice(-d));
	}
 
	if(h > 0){
		format = format.replace(copy('h',h),('00'+time.substring(8,2)).slice(-h));
	}
 
	if(i > 0){
		format = format.replace(copy('i',i),('00'+time.substring(10,2)).slice(-i));
	}
 
	if(s > 0){
		format = format.replace(copy('s',s),('00'+time.substring(12,2)).slice(-s));
	}
 
	return format;
}
</script>
 

<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
						
						   <button style="float: right;" id="hisAgreement"
								class="btn btn-danger  btn-sm" title="协议列表" type="button"
								onclick="backToAgreen()">返回</button>
							<button style="float: right; margin-right: 10px;" title="打印"
								class="btn btn-info btn-sm" onClick="jqprinting1()">打印</button>
						
						    &nbsp;&nbsp;
						       选择结算日期：
							<input  style="hight: 60%;"type="text" readonly="readonly" value="<%=endTime%>" class="input-large" id="endTime" name="endTime" >  
							<button title="结算申请" class="btn btn-info btn-sm"  onClick="findSettlementApply()">结算申请</button>
							&nbsp;选择批次：<select id="batch" name="batch"></select>	
						   <button title="扣减费用" class="btn btn-info btn-sm"  onClick="editDeduction()">扣减费用</button>
					     	<!-- <input style="float: right;margin-right:10px;"id="Button1" type="button" value="导出四项费用" onclick="javascript:table2excel('baseTable','四项费用明细')" class="btn btn-sm btn-danger"/> -->
					     	<button style="float: right;margin-right:10px;"type="button" class="btn btn-info btn-sm" onclick="exportScarp();">报废明细导出</button>
<!-- 					     	<button style="float: right;margin-right:10px;"type="button" class="btn btn-info btn-sm" onclick="exportRepair();">维修明细导出</button> -->
					     	<button style="float: right;margin-right:10px;"type="button" class="btn btn-info btn-sm" onclick="exportNotYet();">丢失明细导出</button>
					     	<button style="float: right;margin-right:10px;"type="button" class="btn btn-info btn-sm" onclick="exportLease();">租赁明细导出</button>
						</div>				
					</div>
				</form>
				<hr>
			
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead></thead>
					<tbody id="aa"></tbody>
					<thead>
					<thead>
						 <tr><th  colspan="11" style="text-align:center;font-weight:800" id="projectName" class="title"> </th></tr>
						<tr><th  colspan="11" style="font-weight:800" class="center">租赁费用明细</th></tr>
						<tr>
							<th style="width:10%" class="center">设备名称</th>
							<th style="width:10%" class="center">设备规格</th>
							<th style="width:5%" class="center">单位</th>
							<th style="width:10%" class="center">租赁单价（元）</th>
							<th style="width:10%" class="center">租赁数量</th>
							<th style="width:8%" class="center">租赁日期</th>
							<th style="width:8%" class="center">归还数量</th>
							<th style="width:9%" class="center">归还日期</th>
							<th style="width:10%" class="center">停用天数（天）</th>
							<th style="width:10%" class="center">租赁天数（天）</th>
							<th style="width:10%" class="center">租赁费用（元）</th>
						</tr>
					</thead>
					<tbody id="leaseDetails"></tbody>
					
					<thead>
						<tr><th colspan="11" style="font-weight:800" class="center">丢失费用明细</th></tr>
						<tr>
							<th style="width:10%" class="center">设备名称</th>
							<th style="width:10%" class="center">设备规格</th>
							<th style="width:5%" class="center">单位</th>
							<th style="width:10%" class="center">丢失数量</th>
							<th style="width:10%" class="center">丢失费用（元）</th>
							<th style="width:8%" class="center"></th>
							<th style="width:8%" class="center"></th>
							<th style="width:9%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
						</tr>
					</thead>
					<tbody id="notYetDetails"></tbody>
					
				<!-- 	<thead>
						<tr><th colspan="11" style="font-weight:800" class="center">维修费用明细</th></tr>
						<tr>
							<th style="width:10%" class="center">设备名称</th>
							<th style="width:10%" class="center">设备规格</th>
							<th style="width:5%" class="center">单位</th>
							<th style="width:10%" class="center">维修数量</th>
							<th style="width:10%" class="center">维修费用（元）</th>
							<th style="width:8%" class="center"></th>
							<th style="width:8%" class="center"></th>
							<th style="width:9%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
						</tr>
					</thead>
					<tbody id="repairDetails"></tbody>
					 -->
					<!-- <thead>
						<tr><th colspan="11" style="font-weight:800" class="center">报废费用明细</th></tr>
						<tr>
						<th style="width:10%" class="center">设备名称</th>
							<th style="width:10%" class="center">设备规格</th>
							<th style="width:5%" class="center">单位</th>
							<th style="width:10%" class="center">报废数量</th>
							<th style="width:10%" class="center">报废费用（元）</th>
							<th style="width:8%" class="center"></th>
							<th style="width:8%" class="center"></th>
							<th style="width:9%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
						</tr>
					</thead>
					<tbody id="scarpDetails"></tbody>
				
			     <thead> -->
			     
			     	<thead>
						<tr><th colspan="11" style="font-weight:800" class="center">扣减费用明细</th></tr>
						<tr>
						<th style="width:10%" class="center">扣减费用（元）</th>
							<th style="width:10%" class="center">备注</th>
							<th style="width:5%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:8%" class="center"></th>
							<th style="width:8%" class="center"></th>
							<th style="width:9%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:10%" class="center"></th>
						</tr>
					</thead>
					<tbody id="deductionDetails"></tbody>
				
			     <thead>
			     <!-- <tr><th colspan="10" style="font-weight:800" class="center">四项费用合计</th></tr> -->
						<!-- <tr>
						<th style="width:14%" class="center"></th>
							<th style="width:14%" class="center"></th>
							<th style="width:5%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:11%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:6%" class="center"></th>
							<th style="width:10%" class="center"></th>
							<th style="width:9%" class="center"></th>
							<th style="width:11%" class="center"></th>
						</tr> 
					</thead>
					<tbody id="fourDetails"></tbody> -->
					
				</table>
				<button style="float: right;margin-right:10px;" title="确认结算"  class="btn btn-sm btn-primary"   onClick="settlementFun()">确认结算</button>
				<button style="float: right;margin-right:10px;" title="分批结算"  class="btn btn-sm btn-primary"   onClick="settlementBatch()">分批结算</button>
			<%-- 	<%@include file="not_finish_form.jsp" %>
				<%@include file="lease_price_form.jsp" %>
				<%@include file="back_machine_form.jsp" %> --%>
				<%@include file="deduction_form.jsp" %>
				<%@include file="../dialog.jsp" %>	
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function jqprinting1() {
			$("#baseTable").jqprint({ operaSupport: false });
		}
	</script>
	
	<script src="${bonuspath}/static/js/settlement/settlementDetails.js?v=1"></script>		
<br>

</body>
</html>

