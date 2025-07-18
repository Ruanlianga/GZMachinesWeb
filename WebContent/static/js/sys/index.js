$(function () {
	//加载皮肤设置
	loadSkin($("#userSkin").val());
	//加载菜单
	getMenu();
	//	warning();
	//TabControlAppend('shouye', '首页', '');
	//测试
	/*for(var i=1;i<18;i++){
		TabControlAppend('shouye'+i,'首页'+i, '');
	}*/
	//皮肤设置框-主题事件
	$('#skinSetting input[name=skin]').on('click', function(){
		var skin=$(this).val();
		$('body').removeClass().addClass(skin);
		loadSkin(skin);
	});	
	//加载个性化设置
	loadPro();
	//切换窄屏 
	$('#jy-settings-add-container').on('click', function(){
		var checked=this.checked;
		if(checked)$("#main-container").addClass("container");
		else       $("#main-container").removeClass("container");
	});
	//缩放菜单
	$('#jy-settings-sfMenu').on('click', function(){
		var checked=this.checked;
		if(checked) $("#sidebar").addClass("menu-min");
		else        $("#sidebar").removeClass("menu-min");
	});	
	//切换右菜单
	$('#jy-settings-rtl').on('click', function(){
		var checked=this.checked;
		if(checked) $("#indexBody").addClass("rtl");
		else        $("#indexBody").removeClass("rtl");
	});	
	//设置头像
	$('#headpicRecommend li').click(function(){
		$('#headpicRecommend li').removeClass("visiHot");
		$(this).addClass("visiHot");
		$("#headpicPreviewImg").attr('src',$(this).find('img').attr('src')); 
	});
});
function loadSkin(skin){
	$('body').removeClass().addClass(skin);
	var skinColor="";
	if(skin=="skin-0")skinColor="blue";
	else if(skin=="skin-1")skinColor="dark";
	else if(skin=="skin-2")skinColor="pink";
	else if(skin=="skin-3")skinColor="light-grey";		
	$('#skinSettingIcon1').removeClass().addClass(skinColor+" icon-github-alt bigger-110");
	$('#skinSettingIcon2').removeClass().addClass(skinColor+" icon-magnet bigger-110");
}
function loadPro(){
	var settings=$.cookie('JY.settings');
	if(typeof(settings) != "undefined"){
		var sjson = JSON.parse(settings);	
		//切换窄屏 
		if(typeof(sjson.narWinMenu) != "undefined" &&sjson.narWinMenu=="1"){
			$("#main-container").addClass("container");
			$("#jy-settings-add-container").prop("checked",true);			
		}else{
			$("#main-container").removeClass("container");	
			$("#jy-settings-add-container").prop("checked",false);
		}
		//缩放菜单
		if(typeof(sjson.sfMenu) != "undefined" &&sjson.sfMenu=="1"){
			$("#sidebar").addClass("menu-min");
			$("#jy-settings-sfMenu").prop("checked",true);		
		}else{
			$("#sidebar").removeClass("menu-min");	
			$("#jy-settings-sfMenu").prop("checked",false);		
		}
		//切换右菜单
		if(typeof(sjson.posMenu) != "undefined" &&sjson.posMenu=="1"){
			$("#indexBody").addClass("rtl");
			$("#jy-settings-rtl").prop("checked",true);	
		}else{
			$("#indexBody").removeClass("rtl");	
			$("#jy-settings-rtl").prop("checked",false);	
		}
	}else{
		var sjson={};
		$.cookie('JY.settings', JSON.stringify(sjson));
	}
}

function perSetting(){
	$("#perSetting").removeClass('hide').dialog({
		resizable:false,dialogClass:"title-no-close",modal:true,//设置为true，该dialog将会有遮罩层
		title: "<div class='widget-header'><h4 class='font-bold'>系统设置</h4></div>",title_html: true,
		show:{effect:"fade"},
		buttons: [
		  {  
			html: "<i class='icon-ok bigger-110'></i>&nbsp;确认","class" : "btn btn-primary btn-xs",
			click: function() {	
				var that =$(this);
				 JY.Ajax.doRequest("perSettingFrom",bonuspath +'/backstage/user/setSetting',null,function(data){
		        	$("#userSkin").val($("#perSettingFrom input:radio[name=skin]:checked").val());
		        	savePro();
		        	that.dialog("close");
		        	JY.Model.info(data.resMsg); 		 
				 }); 	
			}
		  },
		  {
			html: "<i class='icon-remove bigger-110'></i>&nbsp;取消","class" : "btn btn-xs",
			click: function() {		
				//取消恢复皮肤
				var skin=$("#userSkin").val();
				loadSkin(skin);	
				$("#skinSetting input:radio[name=skin][value='"+skin+"']").prop("checked",true);
				//取消恢复个性化
				loadPro();
				$(this).dialog("close");
			}
		  }
		]
	}); 
}
function savePro(){
	var checked=false;
	var sjson = JSON.parse($.cookie('JY.settings'));
	//切换窄屏 
	checked=$("#perSettingFrom input:checkbox[name=narWinMenu]:checked").val();
	if(checked)sjson.narWinMenu="1";
	else       sjson.narWinMenu="0";
	//缩放菜单
	checked=$("#perSettingFrom input:checkbox[name=sfMenu]:checked").val();
	if(checked)sjson.sfMenu="1";
	else       sjson.sfMenu="0";
	//切换右菜单
	checked=$("#perSettingFrom input:checkbox[name=posMenu]:checked").val();
	if(checked)sjson.posMenu="1";
	else       sjson.posMenu="0";
	//保存至cookie
	$.cookie('JY.settings', JSON.stringify(sjson));
}
function perData(){
 	//获取个人资料
	JY.Ajax.doRequest(null,bonuspath +'/backstage/user/getPerData',null,function(data){
		if(data.res==1){
       		var o=data.obj;
       		//加载头像
       		$('#headpicRecommend li').removeClass("visiHot");
       		$("#headpicPreviewImg").attr('src',JY.Object.notNull(o.picUrl)?(o.picUrl):(bonuspath+"/static/css/sys/images/ser/hpic0.jpg"));
       		//加载个人资料
       		$("#perDataloginName").empty().append(JY.Object.notEmpty(o.loginName));
       		$("#perDataName").val(JY.Object.notEmpty(o.name));
       		$("#perDataEmail").val(JY.Object.notEmpty(o.mail));
       		$("#perDataPhone").val(JY.Object.notEmpty(o.telphone));
       		//打开窗体
       		$("#perData").removeClass('hide').dialog({
       			resizable: false,dialogClass: "title-no-close",modal: true,//设置为true，该dialog将会有遮罩层
       			title: "<div class='widget-header'><h4 class='font-bold'>个人设置</h4></div>",title_html: true,
       			show:{effect:"fade"},
       			buttons: [
       			  {  
       				html: "<i class='icon-ok bigger-110'></i>&nbsp;确认","class" : "btn btn-primary btn-xs",
       				click: function() {	
       					var that =$(this);
       					if($("#avatarSetting").hasClass("active")){
       						var picUrl=$("#headpicPreviewImg").attr('src');
       						JY.Ajax.doRequest(null,bonuspath +'/backstage/account/setHeadpic',{picUrl:picUrl},function(data){
	    				       JY.Model.info(data.resMsg); 
	    			//    alert("currentAccount.picUrl"+currentAccount.picUrl);
	    				       $("#mainHeadpic").attr('src',picUrl);
	    				       that.dialog("close");
       						});
       					}else if($("#perfileSetting").hasClass("active")){
       						if(JY.Validate.form("perfileSettingFrom")){	
	       						JY.Ajax.doRequest("perfileSettingFrom",bonuspath +'/backstage/user/setPerData',null,function(data){
		       				        JY.Model.info(data.resMsg); 
		       				        $("#user-info-name").empty().append($("#perDataName").val());
		       				        that.dialog("close");
	       						});
       						}
       					}else if($("#pwSetting").hasClass("active")){
       						if(JY.Validate.form("pwSettingFrom")){
       							var opwd=$.md5($("#pwSettingFrom input[name='opwd']").val());
       							var npwd=$.md5($("#pwSettingFrom input[name='npwd']").val());
       							var qpwd=$.md5($("#pwSettingFrom input[name='qpwd']").val());
       							if(opwd!=npwd){
       								if(npwd==qpwd){	
       									JY.Ajax.doRequest(null,bonuspath +'/backstage/user/preResetPWD',{opwd:opwd,npwd:npwd,qpwd:qpwd},function(data){
			       				        	JY.Model.info(data.resMsg); 
			       				        	that.dialog("close");	
       									});
           							}else{
           								$("#pwSettingFrom input[name='npwd']").tips({side:1,msg : "您输入的密码与确认密码不一致！",bg :'#FF2D2D',time:1});
           							}
       							}else{
       								$("#pwSettingFrom input[name='npwd']").tips({side:1,msg : "您输入的密码与旧密码不能一致！",bg :'#FF2D2D',time:1});
       							}
       						}
       					}					
       				}
       			  },
       			  {
       				html: "<i class='icon-remove bigger-110'></i>&nbsp;取消","class" : "btn btn-xs",
       				click: function() {		
       					$(this).dialog("close");
       				}
       			  }
       			]
       		}); 
       	 }else{
       		 JY.Model.error(data.resMsg); 
       	 }   		
	});
}

//打开菜单
function openMenu(type,id,parentId,menuName,resUrl){

	console.log("type=",type,"-id=",id,"-parentId=",parentId,"-menuName=",menuName,"-resUrl=",resUrl);
	console.log(type,id,parentId,menuName,resUrl);
	if (menuName === "需求计划申请") {
		// localStorage.setItem("isDisable")
	}
	    if('1'!=type || "noset"==resUrl)return;
		else if('/'==resUrl.substring(0,1))TabControlAppend(id,menuName,bonuspath+resUrl+"?menu="+id);
		else TabControlAppend(id,menuName,resUrl);	
}
function getMenu(layer,ref){
	//console.log("layer=",layer+"ref=",ref);
	$("#menu_li_id").empty();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/menu/getMenu',{layer:layer,ref:ref},function(data){
    	$("#menu_li_id").html(data.obj);
	});
}
function logout(){
	JY.Model.confirm("确认要退出吗？",function(){window.location.href=bonuspath+"/system_logout";});	
}

function warning(){
	JY.Ajax.doRequest(null, bonuspath + '/backstage/machineType/findWarnModel', {keyWord:null}, function(data) {
		var res = data.obj.list.results;
		if(res != null){
			if(res.length > 0){
				localStorage.setItem("bonuspath", bonuspath);
				layer.open({
				  type: 2,
				  title: false,
				  closeBtn: 0, //不显示关闭按钮
				  shade: [0],
				  area: ['400px', '300px'],
				  offset: 'rb', //右下角弹出
				  time: 5000, //2秒后自动关闭
				  anim: 2,
				  content: [bonuspath +'/backstage/machineType/warnNotice', 'no'] //iframe的url，no代表不显示滚动条
				});
			}
		}
	});
}

