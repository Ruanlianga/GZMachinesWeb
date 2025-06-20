let idParam,objParam;
let details;
function setParams(obj) {
	objParam = JSON.parse(obj);
	idParam = objParam.id;
	layui.use([ 'form', 'layer', 'laydate' ], function() {
		form = layui.form;
		layer = layui.layer;
		laydate = layui.laydate;
	});
	setCheckStatus();
	getDetailById();
}

// 基本信息
function setPlanBasicTableInfo(obj){
	$('#proName').html(obj.proName);
	$('#projectPart').html(obj.projectPart);
	$('#projectContent').html(obj.projectContent);
	$('#needTime').html(obj.needTime);
	$('#remark').html(obj.remark);
}

// 查询
function search(){
	let keyWord = $('#keyWord').val();
	let keyWord2 = $('#keyWord2').val();
	if(!keyWord && !keyWord2){
		getbaseList(details);
	}else if(keyWord && !keyWord2){
		let dataList = details.filter(item => {
			console.log(item.type.indexOf(keyWord));
	        return item.type.indexOf(keyWord) > -1;
	    })
		getbaseList(dataList);
	}else if(!keyWord && keyWord2){
		let dataList = details.filter(item => {
			console.log(item.module.indexOf(keyWord2));
			return item.module.indexOf(keyWord2) > -1;
	    })
		getbaseList(dataList);
	}else if(keyWord && keyWord2){
		let dataList = details.filter(item => {
			console.log(item.module.indexOf(keyWord2));
			console.log(item.type.indexOf(keyWord));
			return item.type.indexOf(keyWord) > -1 && item.module.indexOf(keyWord2) > -1;;
	    })
		getbaseList(dataList);
	}
}

// 重置
function resetSearch(){
	$('#keyWord').val('');
	$('#keyWord2').val('');
	getbaseList(details);
}

function getbaseList(results) {
	var html = "";
	if(results && results.length > 0){
		for (var i = 0; i < results.length; i++) {
			var l = results[i];
			html += "<tr>";
			html += "<td style='vertical-align:middle;' class='center hidden-480'>"
					+ (i + 1) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.typeName) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.type) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.module) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.unit) + "</td>";
			html += "<td style='vertical-align:middle;color:#409EFF;' class='center'>"+ JY.Object.notEmpty(l.needNum) + "</td>";
			html += "<td style='vertical-align:middle;color:#409EFF;' class='center'>"+ JY.Object.notEmpty(l.times) + "</td>";
			html += "<td style='vertical-align:middle;' class='center'>"+ JY.Object.notEmpty(l.remarks) + "</td>";
			html += "</tr>";
		}
	}else{
		html += "<tr><td colspan='8' class='center'>没有相关数据</td></tr>";
	}
	$("#baseTable tbody").empty().append(html);
}

function rowFunction(id){
	var h="";
	h+="<td style='vertical-align:middle;' class='center'>";
	h+="<div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>";
	h+="<a href='#' title='详情' onclick='detail(&apos;" + id + "&apos;)' class='aBtnNoTD' ><i class='icon-zoom-in color-p bigger-140'></i></a>";
	h+="</div>";		
	h+="</td>";
	return h;
}

// 导出
function exportData(){
	let params = {
			"id":idParam
		}
    exportCommon(bonuspath + '/backstage/planApplication/exportDetail', 'POST', params,"机具明细");
}


// 操作记录
function setOperRecordInfo(list,obj){
	let creator = obj.creator; // 发起人
	let html = '';
	if(list && list.length > 0){
		let imgUrl = `${bonuspath}/static/img/user_head_icon.png`;
		let imgUrl2 = `${bonuspath}/static/img/time_icon.png`;
		$.each(list,function(index,item){
			let operData = ""; 
			let dept = '';
			let minutes = item.minutes;
			if(item.hours === 0 && item.minutes === 0){
				minutes = 1;
			}
			if(item.auditType === '0' || item.auditType === '1'){
				dept = '项目部';
			}else if(item.auditType === '2'){
				dept = '分公司';
			}else if(item.auditType === '3'){
				dept = '项管中心';
			}else if(item.auditType === '4'){
				dept = '机具公司';
			}
			// 操作流程
			if(item.auditType === '0'){
				operData = '发起申请';
			}else if(item.auditType === '1'){
				operData = '重新提交申请';
			}else if(item.auditType === '2' && item.auditStatus === '2'){
				operData = '审核确认通过，共耗时：'+item.hours+'小时'+minutes+'分钟  原因备注：'+item.auditRemarks+'';
			}else if(item.auditType === '2' && item.auditStatus === '3'){
				operData = '驳回-给发起人'+creator+'，共耗时：'+item.hours+'小时'+minutes+'分钟  原因备注：'+item.auditRemarks+'';
			}else if(item.auditType === '3' && item.auditStatus === '2'){
				operData = '审核确认通过，共耗时：'+item.hours+'小时'+minutes+'分钟  原因备注：'+item.auditRemarks+'';
			}else if(item.auditType === '3' && item.auditStatus === '3'){
				operData = '驳回-给发起人'+creator+'，共耗时：'+item.hours+'小时'+minutes+'分钟  原因备注：'+item.auditRemarks+'';
			}else if(item.auditType === '4' && item.auditStatus === '2'){
				operData = '完结-审核确认通过，共耗时：'+item.hours+'小时'+minutes+'分钟  原因备注：'+item.auditRemarks+'';
			}else if(item.auditType === '4' && item.auditStatus === '3'){
				operData = '驳回-给发起人'+creator+'，共耗时：'+item.hours+'小时'+minutes+'分钟  原因备注：'+item.auditRemarks+'';
			}
			html += '<div class="layui-timeline-item">' +
				'<i class="layui-icon layui-timeline-axis"></i>' +
				'<div class="layui-timeline-content layui-text">' +
					'<h3 class="layui-timeline-title">'+item.auditTime.substring(0,10)+'</h3>' + 
					'<div class="layui-panel">' + 
						'<div class="oper-info layout">' + 
							'<div class="layout">' +
								'<img src="'+imgUrl+'">' +
							'</div>' +
							'<div class="user-oper layout">' +
								'<div style="width: 100%">' +
									'<span>'+item.auditUser+'</span><span>（'+item.phone+'）</span><span>'+dept+'</span>' +
								'</div>' +
								'<div style="width: 100%">' +
									'<span>'+operData+'</span>' +
								'</div>' +
							'</div>' +
							'<div class="layout">' +
								'<img src="'+imgUrl2+'">' +
								'<span style="margin: 0 5px 0 5px;">'+item.auditTime+'</span>' +
							'</div>' +
						'</div>' +
					'</div>' +
				'</div>' +
			'</div>';
		})
	}
	$('.layui-timeline').empty().append(html);
}

// 设置计划编号/审核状态
function setCheckStatus() {
	$('#code').empty().html(objParam.code);
	$('#checkStatus').html(getCheckStatus(objParam.statusType,objParam.status));
}

// 审核状态
function getCheckStatus(statusType, status) {
	var company = "";
	if (statusType === '1') {
		return "<span style='color:#19BE6B;margin:0 5px 0 5px;font-size:16px;'>●</span>审核通过";
	} else if (statusType === '2') {
		company = "分公司";
	} else if (statusType === '3') {
		company = "项目管理中心";
	} else if (statusType === '4') {
		company = "机具公司";
	}
	if (status === '1') {
		return "<span style='color:#FF9900;margin:0 5px 0 5px;font-size:16px'>●</span>待" + company + "审核";
	} else if (status === '2') {
		return "<span style='color:#19BE6B;margin:0 5px 0 5px;font-size:16px;'>●</span>审核通过";
	} else if (status === '3') {
		return "<span style='color:#F56C6C;margin:0 5px 0 5px;font-size:16px'>●</span>"+company + "审核驳回";
	}
	return "<span style='color:#FF9900;margin:0 5px 0 5px;font-size:16px'>●</span>待审核";
}

// 详情
function getDetailById(){
	$.ajax({        
	    type:"POST",  
	    url:bonuspath +'/backstage/planApplication/getPlanDetails', 
	    data: {
	    	'id':idParam
	    },
	    dataType:"json",
	    success:function(result){
	    	console.error(result);
	    	if(result.res == 1){
	    		setPlanBasicTableInfo(result.obj);
	    		getbaseList(result.obj.details);
	    		setOperRecordInfo(result.obj.auditList,result.obj);
	    		details = result.obj.details;
			}
		},
		error:function(result){
			layer.msg("数据加载失败!",{icon:2,time:2000})
		}
	});
}

// 打印
function print(){
	Print('#body', {
        onStart: function () {
          console.log('onStart', new Date())
        },
        onEnd: function () {
          console.log('onEnd', new Date())
        }
      })
}