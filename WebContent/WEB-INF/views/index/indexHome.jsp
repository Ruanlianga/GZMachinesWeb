<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../baseset.jsp"%>


	<!-- 预加载关键资源 -->
	<link rel="stylesheet" href="${bonuspath}/static/css/index/indexHome.css" />
<%--	<script src="https://cdn.staticfile.org/echarts/5.4.3/echarts.min.js"></script>--%>
<%--	<link href="https://cdn.staticfile.org/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">--%>
<%--	<link href="https://cdn.staticfile.org/layui/2.7.6/css/layui.css" rel="stylesheet">--%>
<%--	<script src="https://cdn.staticfile.org/layui/2.7.6/layui.min.js"></script>--%>

	<!-- 预加载关键资源 -->
	<script src="${bonuspath}/static/js/index/echarts.min.js"></script>
	<link href="${bonuspath}/static/js/index/bootstrap.min.css" rel="stylesheet">
	<link href="${bonuspath}/static/js/index/layui.css" rel="stylesheet">
	<script src="${bonuspath}/static/js/index/layui.min.js"></script>
	<!-- 在head中添加 -->
<script src="${bonuspath}/static/js/lunar-calendar/lunar-calendar.min.js"></script>

<style>
	.equipment-types-block {
		cursor: pointer;
	}
	.equipment-types-block:hover {
		opacity: 0.9;
	}
	.equipment-types-container {
		padding: 15px;
	}
	.filter-section {
		margin-bottom: 15px;
		padding: 15px;
		background-color: #f8f8f8;
		border-radius: 4px;
	}
	.card-header {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 15px 20px;
		border-bottom: 1px solid #eee;
	}
	.more-link {
		font-size: 13px;
		color: #666;
		cursor: pointer;
		transition: all 0.3s;
	}
	.more-link:hover {
		color: #1E9FFF;
	}
	.tab-header {
		margin: 0;  /* 移除h4的默认margin */
	}
	.calendar-cell {
		padding: 2px;
		cursor: pointer;
	}
	.task-count {
		font-size: 12px;
		color: #666;
		display: flex;
		flex-direction: column;
		gap: 2px;
	}
	.task-count span {
		white-space: nowrap;
	}
	.quick-functions {
		background: #fff;
		border-radius: 8px;
		padding: 15px;
		margin-bottom: 20px;
		box-shadow: 0 2px 12px rgba(0,0,0,0.1);
	}
	
	.function-list {
		display: flex;
		flex-wrap: nowrap;
		gap: 15px;
		overflow-x: auto;
		padding: 10px 0;
	}
	
	.function-item {
		min-width: 100px;
		padding: 12px 15px;
		background: #f5f7fa;
		border-radius: 6px;
		text-align: center;
		cursor: pointer;
		transition: all 0.3s;
		white-space: nowrap;
	}
	
	.function-item:hover {
		background: #e6f7ff;
		color: #1890ff;
	}
	
	.function-item i {
		display: block;
		font-size: 24px;
		margin-bottom: 8px;
	}
	
	.function-item span {
		font-size: 14px;
	}

	.section-title-wrapper {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	.settings-btn {
		cursor: pointer;
		padding: 5px;
		color: #666;
		transition: all 0.3s;
	}
	
	.settings-btn:hover {
		color: #1890ff;
	}
	
	/* 菜单设置弹窗样式 */
	.menu-settings-dialog {
		padding: 20px;
	}
	
	.menu-settings-list {
		max-height: 500px;
		overflow-y: auto;
	}
	
	.menu-item {
		display: flex;
		align-items: center;
		padding: 10px;
		border-bottom: 1px solid #eee;
	}
	
	.menu-item:last-child {
		border-bottom: none;
	}
	
	.menu-item label {
		margin: 0;
		padding-left: 10px;
		cursor: pointer;
		display: flex;
		align-items: center;
		gap: 8px;
	}
	
	.dialog-footer {
		text-align: right;
		padding-top: 20px;
		border-top: 1px solid #eee;
	}
	
	.menu-item i {
		font-size: 16px;
		color: #666;
	}
	
	.menu-item input[type="checkbox"] {
		margin: 0;
		cursor: pointer;
	}

	.month-stat-value {
		display: flex;
		flex-wrap: wrap;
		gap: 10px;
		padding: 10px;
		background: #f8f9fa;
		border-radius: 4px;
		margin-bottom: 10px;
	}
	
	.stat-item {
		display: flex;
		align-items: center;
		gap: 5px;
		padding: 5px 10px;
		background: #fff;
		border-radius: 3px;
		box-shadow: 0 1px 2px rgba(0,0,0,0.05);
	}
	
	.stat-label {
		color: #666;
		font-size: 13px;
	}
	
	.stat-number {
		color: #1890ff;
		font-weight: bold;
		font-size: 14px;
	}
	title{
		font-size: 16px;
	}
</style>
</head>
<body>
	<div class="dashboard">
		<!-- 添加常用功能区域 -->
		<div class="container-fluid">
			<div class="quick-functions">
				<div class="section-title-wrapper">
					<div class="section-title">常用功能</div>
					<div class="settings-btn" onclick="showMenuSettingsDialog()">
						<i class="layui-icon layui-icon-set"></i>
					</div>
				</div>
				<div class="function-list" id="functionList">
					<!-- 功能按钮将通过JS动态添加 -->
				</div>
			</div>

				

			<!-- 差缺排名和预警信息 -->
			<div class="row">
				<div class="col-md-8">
					<div class="overview-card">
						<div class="card-header">
							<h4 class="tab-header" onclick="showProjectDetail()">差缺排名</h4>
							<a class="more-link" onclick="showProjectDetail()">查看更多 ></a>
						</div>
						<div id="rankingList" class="ranking-list">
							<table class="table">
								<thead>
									<tr>
										<th>排名</th>
										<th>工程名称</th>
										<th>领用量</th>
										<th>退回量</th>
										<th>差缺量</th>
									</tr>
								</thead>
								<tbody id="rankingBody">
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="overview-card" onclick="showWarningInfo()">
						<div class="section-title">预警信息</div>
						<div class="warning-grid">
							<div class="warning-item">
								<div class="warning-icon warning-stock"></div>
								<div class="warning-content">
									<div class="warning-left">
										<div class="warning-title">库存不足</div>
										<span  class="warning-desc">等于0</span>
									</div>
									<div class="warning-right">
										<span id="storageNum" class="warning-value">0</span>
									</div>
								</div>
							</div>
							<div class="warning-item">
								<div class="warning-icon warning-check"></div>
								<div class="warning-content">
									<div class="warning-left">
										<div class="warning-title">检验周期临近</div>
										<span class="warning-desc">1个月</span>
									</div>
									<div class="warning-right">
										<span id="checkTimeNum" class="warning-value">0</span>
									</div>
								</div>
							</div>
							<div class="warning-item">
								<div class="warning-icon warning-occupy"></div>
								<div class="warning-content">
									<div class="warning-left">
										<div class="warning-title">长期占用</div>
										<span class="warning-desc">领用超6个月未归还</span>
									</div>
									<div class="warning-right">
										<span id="useNum" class="warning-value">0</span>
									</div>
								</div>
							</div>
							<div class="warning-item">
								<div class="warning-icon warning-change"></div>
								<div class="warning-content">
									<div class="warning-left">
										<div class="warning-title">保有量变化</div>
										<span class="warning-desc">近30天</span>
									</div>
									<div class="warning-right">
										<span id="changeNum" class="warning-value">0</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 机具日历 -->
			<div class="overview-card">
				<h4>机具日历</h4>
				<div id="equipmentCalendarTableNum" class="month-overview">
					<div class="month-stat-item">
						<span class="month-stat-label">领料出库</span>
						<span class="month-stat-value">0</span>
					</div>
					<div class="month-stat-item">
						<span class="month-stat-label">退料接收</span>
						<span class="month-stat-value">0</span>
					</div>
					<div class="month-stat-item">
						<span class="month-stat-label">维修检验</span>
						<span class="month-stat-value">0</span>
					</div>
					<div class="month-stat-item">
						<span class="month-stat-label">机具报废</span>
						<span class="month-stat-value">0</span>
					</div>
					<div class="month-stat-item">
						<span class="month-stat-label">修试后入库</span>
						<span class="month-stat-value">0</span>
					</div>
					<div class="month-stat-item">
						<span class="month-stat-label">新购入库</span>
						<span class="month-stat-value">0</span>
					</div>
					<div class="month-stat-item">
						<span class="month-stat-label">库存盘点</span>
						<span class="month-stat-value">0</span>
					</div>
				</div>
				<div id="equipmentCalendar" class="calendar-container"></div>
			</div>
			
		<!-- 数据概览 -->
		<div class="overview-card">
			<div class="overview-title">数据概览</div>
			<div class="overview-blocks">
				<div class="data-block blue-bg equipment-types-block" onclick="showProjectDetail()">
					<h3 id="projectCount">0</h3>
					<p>工程数量</p>
				</div>
				<div class="data-block blue-bg equipment-types-block" onclick="showEquipmentTypes()">
					<h3 id="equipmentTypes">0</h3>
					<p>机具类别</p>
				</div>
				<div class="data-block blue-bg equipment-types-block" onclick="showEquipmentTypes()">
					<h3 id="totalCount">0</h3>
					<p>保有量</p>
				</div>
				<div class="data-block blue-bg equipment-types-block" onclick="showEquipmentTypes()">
					<h3 id="stockCount">0</h3>
					<p>库存量</p>
				</div>
				<div class="data-block blue-bg equipment-types-block" onclick="showEquipmentTypes()">
					<h3 id="inUseCount">0</h3>
					<p>在用量</p>
				</div>
				<div class="data-block blue-bg equipment-types-block" onclick="showEquipmentTypes()">
					<h3 id="repairCount">0</h3>
					<p>在修量</p>
				</div>
				<div class="data-block red-bg equipment-types-block" onclick="showEquipmentTypes()">
					<h3 id="scrappedCount">0</h3>
					<p>报废量</p>
				</div>
			</div>
		</div>

			<!-- 入库占比图表和待办事项 -->
			<div class="row">
				<div class="col-md-8">
					<div class="overview-card">
						<div class="section-title">入库占比&nbsp; &nbsp;&nbsp;  / &nbsp;&nbsp;&nbsp;机具类别</div>
						<div class="chart-grid">
							<div id="storageChart1" class="chart-item" onclick="showEquipmentTypes()"></div>
							<div id="storageChart2" class="chart-item" onclick="showEquipmentTypes()"></div>
							<div id="storageChart3" class="chart-item" onclick="showEquipmentTypes()"></div>
							<div id="storageChart4" class="chart-item" onclick="showEquipmentTypes()"></div>
							<div id="storageChart5" class="chart-item" onclick="showEquipmentTypes()"></div>
							<div id="storageChart6" class="chart-item" onclick="showEquipmentTypes()"></div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="overview-card">
						<div class="todo-header">
							<h4>待办事项</h4>
							<div class="todo-tabs">
								<span class="todo-tab active" data-type="all">全部</span>
								<span class="todo-tab" data-type="mine">我的</span>
							</div>
						</div>
						<div class="todo-list" onclick="showTodoList()">
							<div class="todo-item">
								<div class="todo-icon new"></div>
								<div class="todo-content">
									<div class="todo-title">新购机具</div>
									<span id ="newNum" class="todo-count">0</span>
								</div>
							</div>
							<div class="todo-item">
								<div class="todo-icon out"></div>
								<div class="todo-content">
									<div class="todo-title">领料出库</div>
									<span id ="leaseNum" class="todo-count">0</span>
								</div>
							</div>
							<div class="todo-item">
								<div class="todo-icon return"></div>
								<div class="todo-content">
									<div class="todo-title">退料申请</div>
									<span id ="backNum" class="todo-count">0</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			
		</div>
	</div>


	<script src="${bonuspath}/static/js/index/jquery.min.js"></script>
	<script src="${bonuspath}/static/js/index/layer.min.js"></script>
	<script src="${bonuspath}/static/js/index/bootstrap.bundle.min.js"></script>
	<script src="${bonuspath}/static/js/index/indexHome.js?v=1"></script>		
	<script src="${bonuspath}/static/js/sys/index.js"></script>
	<script src="${bonuspath}/static/js/ace/ace-extra.min.js"></script>
	<script src="${bonuspath}/static/js/bootstrap/bootstrap.min.js"></script>
	<script src="${bonuspath}/static/js/ace/typeahead-bs2.min.js"></script>
	<script src="${bonuspath}/static/js/ace/ace-elements.min.js"></script>
	<script src="${bonuspath}/static/js/ace/ace.min.js"></script>
	<script src="${bonuspath}/static/js/jquery/jquery-ui-1.10.3.full.min.js"></script>
	<script src="${bonuspath}/static/js/jquery/jquery.md5.js"></script>
	<script src="${bonuspath}/static/plugins/tabs/js/tab-control.min.js"></script>
	<script src="${bonuspath}/static/js/sys/main.js"></script>
	<script src="${bonuspath}/static/js/layer.js"></script>
	

	<script>
	// 将函数定义移到全局作用域
	window.loadMenuSettings = async function() {
		try {
			const data = await window.request(window.API.getAllMenus);
			if (!data || !Array.isArray(data)) {
				console.warn('菜单数据格式不正确:', data);
				return;
			}

			const menuList = document.getElementById('menuSettingsList');
			if (!menuList) {
				console.error('菜单列表容器不存在');
				return;
			}

			// 清空现有内容
			menuList.innerHTML = '';

			// 渲染菜单项
			data.forEach(menu => {
				const menuItem = document.createElement('div');
				menuItem.className = 'menu-item';
				menuItem.innerHTML = `
					<input type="checkbox" 
						   id="menu_${menu.rsId}" 
						   value="${menu.rsId}" 
						   ${menu.isCheck ? 'checked' : ''}>
					<label for="menu_${menu.rsId}">
						<i class="${menu.rsIcon || 'layui-icon-app'}"></i>
						${menu.rsName}
					</label>
				`;
				menuList.appendChild(menuItem);
			});

		} catch (error) {
			console.error('加载菜单设置失败:', error);
			layer.msg('加载菜单设置失败', {icon: 2});
		}
	}

	window.saveMenuSettings = async function() {
		try {
			const menuList = document.getElementById('menuSettingsList');
			if (!menuList) {
				console.error('菜单列表容器不存在');
				return;
			}

			// 获取所有选中的菜单ID
			const checkedMenus = Array.from(menuList.querySelectorAll('input[type="checkbox"]:checked'))
				.map(checkbox => checkbox.value);

			// 调用保存接口
			await window.request(window.API.saveMenuSettings, {
				menuIds: JSON.stringify(checkedMenus)
			});

			layer.msg('保存成功', {icon: 1});
			layer.closeAll('page');

			// 重新加载常用功能
			await window.initQuickFunctions();

		} catch (error) {
			console.error('保存菜单设置失败:', error);
			layer.msg('保存失败', {icon: 2});
		}
	}

	function showWarningInfo() {
		layer.open({
			type: 2,
			title: '预警信息',
			area: ['95%', '95%'],
			content: '${bonuspath}/backstage/indexHome/warning/info',
			btn: ['关闭'], // 添加关闭按钮
			yes: function(index, layero) {
				layer.close(index); // 关闭当前弹窗
			}
		});
	}
	
	function showEquipmentTypes() {
		layer.open({
			type: 2,
			title: '机具类别明细',
			area: ['1500px', '700px'],
			content: '${bonuspath}/backstage/indexHome/dataOverview/equipment/index',
			btn: ['关闭'], // 添加关闭按钮
			yes: function(index, layero) {
				layer.close(index); // 关闭当前弹窗
			}
		});
	}

	function showProjectDetail() {
		layer.open({
			type: 2,
			title: '工程详情',
			area: ['95%', '95%'],
			content: '${bonuspath}/backstage/indexHome/dataOverview/project/index',
			maxmin: false,
			btn: ['关闭'], // 添加关闭按钮
			yes: function(index, layero) {
				layer.close(index); // 关闭当前弹窗
			}
		});
	}

	function showTodoList() {
		layer.open({
			type: 2,
			title: '待办事项',
			area: ['95%', '95%'],
			content: '${bonuspath}/backstage/indexHome/todo/index',
			maxmin: false,
			btn: ['关闭'], // 添加关闭按钮
			yes: function(index, layero) {
				layer.close(index); // 关闭当前弹窗
			}
		});
	}

	function showMenuSettingsDialog() {
		console.log('显示功能设置弹窗');
		layer.open({
			type: 2,
			title: '常用功能设置',
			area: ['900px', '500px'],
			content: '${bonuspath}/backstage/indexHome/menuSettings/index',
			maxmin: false,
			scrollbar: false,
			btn: ['确定', '取消'],
			yes: function(index, layero) {
				// 获取iframe页面的window对象
				var iframeWin = window[layero.find('iframe')[0]['name']];
				// 调用iframe页面的保存方法
				iframeWin.saveSettings();
				// 关闭弹窗
				layer.close(index);
			},
			btn2: function(index, layero) {
				layer.close(index);
			},
			success: function() {
				console.log('弹窗打开成功，准备加载菜单设置');
			}
		});
	}

	</script>

	
</body>
</html>
