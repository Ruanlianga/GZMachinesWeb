$(function(){
	getbaseList(1);
	$('#export').on('click',function(e) {
		e.preventDefault();
		$("#auAddForm").attr("onsubmit","return true;");
		$("#auAddForm").attr("action",bonuspath +'/backstage/warningTip/export');
		$("#auAddForm").attr("target","downloadFrame");//iframe的名字
		$("#auAddForm").submit();
	});
});
 
function warning(){
	layer.open({
		type : 2,
		title : ['', 'background-color: #27A3D9;color:#fff' ],
		shadeClose : true,
		shade : false,
		maxmin : true,
		area : [ '1100px', '600px' ],
		content :bonuspath+'/backstage/warningTip/tip2'
	});
}

function getbaseList(init){
	if(init==1){
		$("#baseForm.pageNum").val(1);
	}
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/warningTip/findByPage',null,function(data){
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
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.typeName)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.code)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.warnName)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.lat)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.lon)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.time)+"</td>";
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
 