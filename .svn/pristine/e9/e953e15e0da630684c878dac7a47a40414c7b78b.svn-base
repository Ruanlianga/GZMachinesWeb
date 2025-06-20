$(function(){
	getbaseList(1);
	$('#warningExport').on('click',function(e) {
		e.preventDefault();
		$("#baseForm").attr("onsubmit","return true;");
		$("#baseForm").attr("action",bonuspath +'/backstage/warning/warningExport');
		$("#baseForm").attr("target","downloadFrame");//iframe的名字
		$("#baseForm").submit();
	});
});
 
function edit(id){
	cleanFrom();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/warning/find',{id:id},function(data){
		setForm(data);
		JY.Model.edit("auDiv","修改",function(){
			 if(JY.Validate.form("auAddForm")){
				 var that =$(this);
				 JY.Ajax.doRequest("auAddForm",bonuspath +'/backstage/warning/update',{id:id},function(data){
				     that.dialog("close");  
				     JY.Model.info(data.resMsg,function(){getbaseList(1);});
				 });
			 }	
		});
	});
}
//新加
 

 
function setForm(data){
	var l=data.obj;
$("#auAddForm input[name$='id']").val(l.id);
/*	$("#auAddForm input[name$='name']").val(l.name);
	$("#auAddForm input[name$='code']").val(l.code);
	$("#auAddForm input[name$='typeName']").val(l.typeName);*/
	$("#auAddForm input[name$='uploadCycle']").val(l.uploadCycle);

 
}
function cleanFrom(){
	$("#auAddForm input[name$='id']").val("");
/*	$("#auAddForm input[name$='name']").val("");
	$("#auAddForm input[name$='code']").val("");
	$("#auAddForm input[name$='typeName']").val("");*/
	$("#auAddForm input[name$='uploadCycle']").val("");
}
function getbaseList(init){
	if(init==1){
		$("#baseForm.pageNum").val(1);
	}
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",bonuspath +'/backstage/warning/findByPage',null,function(data){
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
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.name)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>"+JY.Object.notEmpty(l.uploadCycle)+"</td>";
            		 html+="<td style='vertical-align: middle' class='center'>";
            		 html+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
            		 html+="<a  title='修改' onclick='edit(\""+l.id+"\")' class='aBtnNoTD' ><i class='icon-edit color-blue bigger-140'></i></a>";
            		 html+="</td>";
            		 html+="</div>";
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