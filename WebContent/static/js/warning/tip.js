$(function(){
	getbaseList(1);
});
 
function getbaseList(init){
	if(init==1){
		$("#baseForm.pageNum").val(1);
	}
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/warningTip/findByPageTwo',null,function(data){
		 $("#bmTable tbody").empty();
        	 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=list.pageNum,
         	 pageSize=list.pageSize,
         	 totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td style='vertical-align: middle' class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.code)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.gpsCode)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.time)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.warnName)+"</td>";
            		 html+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
            		 html+="</div>";
            		 html+="</tr>";		 
            	 } 
        		 $("#bmTable tbody").append(html);
        		 JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='8' class='center'>没有相关数据</td></tr>";
        		$("#bmTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
 	 
	});
}
 