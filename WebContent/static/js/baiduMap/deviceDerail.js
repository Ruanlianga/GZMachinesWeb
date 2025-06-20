$(function(){
	getbaseList(1);
});
var id =localStorage.getItem("id");
var type =localStorage.getItem("type");
function getbaseList(init){
	if(init==1){
		$("#baseForm.pageNum").val(1);
	}
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/position/findDeviceDetail',{id:id,status:type},function(data){
		 $("#bmTable tbody").empty();
        	 var obj=data.obj;
        	 var list=obj.list; 
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=obj.pageNum,
         	 pageSize=list.pageSize,
         	 totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td style='vertical-align: middle' class='center hidden-480'>"+(i+1)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.deviceCode)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.typeName)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.deviceModel)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.code)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.lon)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.lat)+"</td>";
            		 html+="</tr>";		  
            	 }
        		 $("#bmTable tbody").append(html);
        		 JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='7' class='center'>没有相关数据</td></tr>";
        		$("#bmTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
 	 
    	 JY.Model.loadingClose();
	 });
}