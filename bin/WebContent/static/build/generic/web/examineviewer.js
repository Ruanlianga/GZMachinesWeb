$(function(){
	getOneMessage();
});
	var id = localStorage.getItem("fileId");
	var uploadTime;
	var fileAuthor;
	var fileSource;
	var filephotoPath;
	var fileUpdatePath;
	var fileTransPath;
	var publishContent;
	var fileTypeName;
	var title;
function getOneMessage(){
	$.ajax({
		type:'post',
		url:bonuspath+'/backstage/filesing/findOne',
		data:{id:id},
		dataType:'json',
		success:function(data){
			var l =data.obj.list[0];
			fileTransPath=bonuspath+l.fileTransPath;
			title = l.title;
			uploadTime = l.uploadTime;
			fileSource = l.fileSource;
			fileAuthor = l.fileAuthor;
			fileUpdatePath = l.fileUpdatePath;
			publishContent= l.publishContent;
			fileTypeName = l.fileTypeName;
			$("#title").html(title);
			$("#uploadTime").html(uploadTime);
			$("#fileSource").html(fileSource);
			$("#fileAuthor").html(fileAuthor);
			$("#publishContent").html(publishContent);
		},
		error:function(data){
			
		}
	});
}