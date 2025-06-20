var form, layer;
let idParam;
function setParams(id) {
	idParam = id;
	layui.use([ 'form' ], function() {
		form = layui.form;
		layer = layui.layer;
		form.verify();
		form.on('submit(formData)', function(data) {
			submitApply(data);
		});
		form.on('radio(auditStatus)', function(data) {
			if (data.value === '2') { // 通过
				$('#auditRemarksLabel').empty().append('审核意见');
				$('#auditRemarks').removeAttr('lay-verify');
			} else if (data.value === '3') { // 不通过
				$('#auditRemarksLabel').empty().append(
						'<span class="required_icon">*</span>审核意见');
				$('#auditRemarks').attr('lay-verify', 'required');
			}
		});
		form.render();
	});
}

function saveData2() {
	$('#formSubmit').trigger('click')
}

// 提交
function submitApply(data) {
	let loadingMsg = layer.msg('正在提交保存,请稍等...', {
		icon : 16,
		shade : 0.01,
		time : '0'
	});
	console.log(JSON.stringify(data.field));
	data.field.applyId = idParam;
	$.ajax({
		url : bonuspath + '/backstage/planAudit/planAudit',
		type : 'POST',
		data : JSON.stringify(data.field),
		dataType : 'json',
		contentType : "application/json",
		beforeSend : function() {
			$('.save').addClass("layui-btn-disabled").attr("disabled", true);
			$('.cancel').addClass("layui-btn-disabled").attr("disabled", true);
		},
		success : function(result) {
			layer.close(loadingMsg); // 关闭提示层
			if (result.res === 1) {
				top.layer.msg(result.resMsg, {
					icon : 1
				});
				closePage(1);
			} else {
				var indexMsg = layer.confirm(result.resMsg, {
					btn : [ '关闭' ]
				}, function() {
					layer.close(indexMsg);
				});
				$('.save').removeClass("layui-btn-disabled").attr("disabled",
						false);
				$('.cancel').removeClass("layui-btn-disabled").attr("disabled",
						false);
			}
		},
		error : function(result) {
			layer.close(loadingMsg); // 关闭提示层
			layer.msg('服务异常，请稍后重试', {
				icon : 16,
				scrollbar : false,
				time : 2000
			});
			$('.save').removeClass("layui-btn-disabled")
					.attr("disabled", false);
			$('.cancel').removeClass("layui-btn-disabled").attr("disabled",
					false);
		}
	});
}

// 关闭页面
function closePage(type) {
	let index = parent.layer.getFrameIndex(window.name); // 先得到当前 iframe层的索引
	if (type == 1) {
		window.parent.closePage();
	}
	parent.layer.close(index); // 再执行关闭
}