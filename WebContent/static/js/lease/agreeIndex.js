$(function() {
	
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  //日期时间选择器
		laydate.render({
			elem: '#startTime'
		});
	});
	findCode();
});

//查询已签订协议记录
function getHisAgreen(){
	var url = bonuspath + '/backstage/agreement/geiHisAgreement';  
    window.location.href = url;
}

function findCode(){
	JY.Ajax.doRequest(null,bonuspath +'/backstage/agreement/findCode',{},function(data){
		var code = data.resMsg;
		if(code != null || code != ''){
			$("#code").val(code);
		}else{
			alert("请联系相关人员！");
		}
	});
}

function checkAgreement(){
	var leaseCompany = $("#unitId").val();
	var projectName = $("#projectId").val();
	JY.Ajax.doRequest(null,bonuspath +'/backstage/agreement/checkAgreement',{leaseCompany:leaseCompany,projectName:projectName},function(data){
		var res = data.resMsg;
		if(res == 1){
			layer.alert('该协议已存在，请勿重复创建!', {
			  skin: 'layui-layer-molv' //样式类名
			  ,closeBtn: 0
			});
		}else{
			sureAgreemnet();
		}
	});

}

function sureAgreemnet(){
	var code = $("#code").val();
	var leaseCompany = $("#unitId").val();
	var projectName = $("#projectId").val();
	var startTime = $("#startTime ").val();
	var leaseTerm = $("#leaseTerm ").val();
	var authorizingPerson = $("#authorizingPerson ").val(); 
	var authorizingPhone = $("#authorizingPhone ").val(); 
	var contractNumber = $("#contractNumber").val();
	var remark = $("#remark").val();
	var agreePicName = localStorage.getItem("agreePicName");
	var urlPath = localStorage.getItem("urlPath");
	var isBalance = 1;
	if(leaseCompany == 0 || projectName == 0){
		layer.msg("重新选择租赁单位与工程名称");
		return false;
	}
	if(startTime == ""){
		layer.msg("请选择开始时间");
		return false;
	}
	if(leaseTerm == ""){
		layer.msg("请选择租赁天数");
		return false;
	}
	if(authorizingPerson == ""){
		layer.msg("请输入授权人");
		return false;
	}
	if(authorizingPhone == ""){
		layer.msg("请输入联系方式");
		return false;
	}
	var reg = /^1[3456789]\d{9}$/;
    if(!reg.test(authorizingPhone)){
        layer.msg('请输入正确的电话号码');
        return false;
    }
	if(contractNumber == ""){
		layer.msg("请输入合同编号");
		return false;
	}
//		alert("agreePicName="+agreePicName);
	JY.Ajax.doRequest("auForm", bonuspath + '/backstage/agreement/add',
			{ 
				code : code,
				leaseCompany : leaseCompany,
				projectName : projectName,
				startTime : startTime,
				leaseTerm : leaseTerm,
				authorizingPerson : authorizingPerson,
				authorizingPhone : authorizingPhone,
				contractNumber : contractNumber,
				isBalance : isBalance,
				agreePicName : agreePicName,
				remark : remark,
				urlPath : urlPath
			}, function(data) {
						//墨绿深蓝风
						layer.alert('协议签订完成!', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						}, function(){
							rebackAgree();
						});

				});
				that.dialog("close");
				JY.Model.info(data.resMsg);
	}

//查询已签订协议记录
function rebackAgree(){
	var url = bonuspath + '/backstage/agreement/list';  
    window.location.href = url;
}

function setUnitForm(){
	var unitId = localStorage.getItem("unitId");
	var unitName = localStorage.getItem("unitName");
	$("#unitId").val(unitId);
	$("#unitName").val(unitName);
}

function setProjectForm(){
	var projectId = localStorage.getItem("projectId");
	var projectName = localStorage.getItem("projectName");
	$("#projectId").val(projectId);
	$("#projectName").val(projectName);
}

function unitTree(){
	localStorage.setItem("unitId","");
	localStorage.setItem("unitName","");
	localStorage.setItem("unitTreeName",$("#unitName").val());
	layer.open({
		type: 2,
		title:['租赁单位','background-color: #438EB9;color:#fff'],
		shadeClose:true,
		shade:false,
		maxmin: true,
		area: ['400px', '400px'],
		content: bonuspath+'/backstage/company/unitTree'
	});
}

function projectTree(){
	localStorage.setItem("unitId","");
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

//合同等附件上传、查看
function updAgreePic(){
	//iframe层-父子操作
	layer.open({
	  type: 2,
	  title:['附件上传','background-color: #438EB9;color:#fff'],
	  shadeClose:true,
	  shade:false,
	  maxmin: true,
	  area: ['800px', '430px'],
	  content: bonuspath+'/backstage/agreement/updAgreePic'
	});
}

