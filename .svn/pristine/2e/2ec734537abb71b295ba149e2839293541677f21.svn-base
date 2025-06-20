$(function(){
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
		laydate.render({
			elem: '#startTime',type: 'month'
		});
		laydate.render({
			elem: '#endTime',type: 'month'
		});
		
	});
	getbaseList(1);
});
function getbaseList(role){
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/whingReport/findByPage',null,function(data){
		/*alert(JSON.stringify(data));*/
		 $("#baseTable tbody").empty();
		 var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum,
				pageSize = list.pageSize,
				totalRecord = list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.prentName)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.typeName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.counts)+"</td>";
            		 html+=rowFunction(l.id);
            		 html+="</tr>";
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm", "pageing", pageSize,pageNum, totalRecord, "getbaseList");
        	 }else{
        		html+="<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }
    	 JY.Model.loadingClose();
	 });
}

function rowFunction(id){
	var h="";
	h+="<td class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='查看' onclick='check("+id+")' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}






