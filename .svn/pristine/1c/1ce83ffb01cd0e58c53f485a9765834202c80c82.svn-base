// 检查依赖是否加载
function checkDependencies() {
	const deps = {
		jQuery: typeof $ !== 'undefined',
		echarts: typeof echarts !== 'undefined',
		layui: typeof layui !== 'undefined',
		layer: typeof layer !== 'undefined'
	};
	
	const missing = Object.entries(deps)
		.filter(([, loaded]) => !loaded)
		.map(([name]) => name);
	
	if (missing.length > 0) {
		console.error('Missing dependencies:', missing.join(', '));
		return false;
	}
	return true;
}

// 性能监控对象
const performanceMonitor = {
	start: Date.now(),
	marks: new Map(),
	mark(name) {
		this.marks.set(name, Date.now() - this.start);
		console.log(`Performance mark: ${name} at ${this.marks.get(name)}ms`);
	}
};

// 定义API接口配置
window.API = {
	overview: "/backstage/indexHome/getPartOneData", // 数据概览接口地址
	storage: "/backstage/indexHome/getPartTwoData", // 入库占比接口
	rankList: "/backstage/indexHome/getPartThreeData", // 排名接口
	warning: "/backstage/indexHome/getPartFourData",
	todoList: "/backstage/indexHome/getPartFiveData",
	calendarList: "/backstage/indexHome/getPartSixData",
	baseURL: bonuspath, // API基础路径,
	quickFunctions: "/backstage/indexHome/getHomeResource", // 常用功能接口
	getAllMenus: "/backstage/indexHome/getResource", // 获取所有菜单
	saveMenuSettings: "/backstage/indexHome/saveResourse", // 保存菜单设置
};

// 封装Ajax请求方法
window.request = function(url, options = {}) {
	console.log('发起请求:', url, options);  // 添加日志
	return new Promise((resolve, reject) => {
		$.ajax({
			url: bonuspath + url,
			type: 'POST',
			data: options,
			success: function(response) {
				console.log('请求成功，原始响应:', response);  // 添加日志
				try {
					var data = JSON.parse(response);
					console.log('解析后的数据:', data);  // 添加日志
					if (data.res > 0) {
						resolve(data.obj);
					} else {
						reject(new Error(data.message || '请求失败'));
					}
				} catch (error) {
					console.error('解析响应数据失败:', error);
					reject(error);
				}
			},
			error: function(xhr, status, error) {
				console.error('请求失败:', {
					status: status,
					error: error,
					response: xhr.responseText
				});
				reject(error);
			}
		});
	});
}

// 将 loadMenuSettings 函数移到全局作用域
window.loadMenuSettings = async function() {
	console.log('开始加载菜单设置');
	try {
		console.log('准备请求菜单数据');
		const data = await window.request(window.API.getAllMenus);
		console.log('获取到的菜单数据:', data);
		
		// ... 其他代码
	} catch (error) {
		console.error('加载菜单设置失败:', error);
		console.error('错误详情:', error.stack);
		layer.msg('加载菜单设置失败', {icon: 2});
	}
}

$(function() {
	if (!checkDependencies()) {
		console.error('必要的依赖项未加载');
		return;
	}

	// 全局变量声明
	const mockData = {
		overview: new Proxy({
			projectCount: 156,
			equipmentTypes: 45,
			totalCount: 3580,
			stockCount: 1245,
			inUseCount: 1890,
			repairCount: 328,
			scrappedCount: 117
		}, {
			get(target, prop) {
				return target[prop] || 0;
			}
		}),
		// 图数据
		chartData: {
			newStorage: { value: 0, total: 1000 },
			repairStorage: { value: 0, total: 800 },
			inventoryStorage: { value: 0, total: 600 },
			returnStorage: { value: 0, total: 500 },
			equipmentCount: { value: 0, total: 800 },
			deviceCount: { value: 0, total: 700 }
		},
		// 排名数据
		ranking: [
			{ rank: 1, projectName: "城市轨道交通工程", useCount: 245, returnCount: 180, diffCount: 65 },
			{ rank: 2, projectName: "高速公路扩建工程", useCount: 198, returnCount: 145, diffCount: 53 },
			{ rank: 3, projectName: "机场航站楼建设", useCount: 167, returnCount: 120, diffCount: 47 },
			{ rank: 4, projectName: "城市地下管廊工程", useCount: 156, returnCount: 112, diffCount: 44 },
			{ rank: 5, projectName: "铁路客运站改造", useCount: 145, returnCount: 105, diffCount: 40 },
			{ rank: 6, projectName: "港口码头扩建", useCount: 134, returnCount: 98, diffCount: 36 },
			{ rank: 7, projectName: "市政道路维修", useCount: 123, returnCount: 90, diffCount: 33 },
			{ rank: 8, projectName: "桥梁加固工程", useCount: 112, returnCount: 82, diffCount: 30 }
		],
		// 日历数据
		calendar: {
			'2024-12-01': { in: 15, out: 8, inStock: 358, outStock: 201 },
			'2024-12-02': { in: 12, out: 10, inStock: 360, outStock: 198 },
			'2024-12-03': { in: 10, out: 7, inStock: 253, outStock: 158 },
			'2024-12-04': { in: 15, out: 10, inStock: 258, outStock: 160 },
			'2024-12-05': { in: 13, out: 8, inStock: 263, outStock: 162 },
			'2024-12-06': { in: 16, out: 12, inStock: 267, outStock: 164 },
			'2024-12-07': { in: 11, out: 9, inStock: 269, outStock: 165 },
			'2024-12-08': { in: 18, out: 13, inStock: 274, outStock: 167 },
			'2024-03-09': { in: 12, out: 8, inStock: 278, outStock: 169 },
			'2024-03-10': { in: 9, out: 6, inStock: 281, outStock: 170 },
			'2024-03-11': { in: 17, out: 12, inStock: 286, outStock: 172 },
			'2024-03-12': { in: 14, out: 10, inStock: 290, outStock: 174 },
			'2024-03-13': { in: 16, out: 11, inStock: 295, outStock: 176 },
			'2024-03-14': { in: 13, out: 9, inStock: 299, outStock: 177 },
			'2024-03-15': { in: 19, out: 14, inStock: 304, outStock: 179 },
			'2024-03-16': { in: 15, out: 10, inStock: 309, outStock: 181 },
			'2024-03-17': { in: 11, out: 8, inStock: 312, outStock: 182 },
			'2024-03-18': { in: 18, out: 13, inStock: 317, outStock: 184 },
			'2024-03-19': { in: 14, out: 9, inStock: 322, outStock: 186 },
			'2024-03-20': { in: 16, out: 11, inStock: 327, outStock: 188 },
			'2024-03-21': { in: 13, out: 10, inStock: 330, outStock: 189 },
			'2024-03-22': { in: 17, out: 12, inStock: 335, outStock: 191 },
			'2024-03-23': { in: 15, out: 11, inStock: 339, outStock: 192 },
			'2024-03-24': { in: 12, out: 8, inStock: 343, outStock: 194 },
			'2024-03-25': { in: 18, out: 13, inStock: 348, outStock: 196 },
			'2024-03-26': { in: 14, out: 10, inStock: 352, outStock: 197 },
			'2024-03-27': { in: 16, out: 11, inStock: 357, outStock: 199 },
			'2024-03-28': { in: 13, out: 9, inStock: 361, outStock: 200 },
			'2024-12-28': { in: 13, out: 9, inStock: 361, outStock: 200 },
			'2024-12-29': { in: 19, out: 14, inStock: 366, outStock: 202 },
			'2024-12-30': { in: 15, out: 10, inStock: 371, outStock: 204 },
			'2024-12-31': { in: 17, out: 12, inStock: 376, outStock: 206 }
		}
	};

	// 初始化标志
	let isEchartsLoaded = false;
	let isDomReady = false;

	// 定义图表实例
	let charts = {
		chart1: null,
		chart2: null,
		chart3: null,
		chart4: null,
		chart5: null,
		chart6: null
	};

	// 检查并初始化
	function checkAndInit() {
		if (isEchartsLoaded && isDomReady) {
			initializeAll();
		}
	}

	// 监听ECharts加载
	function waitForEcharts() {
		if (typeof echarts !== 'undefined') {
			isEchartsLoaded = true;
			checkAndInit();
		} else {
			setTimeout(waitForEcharts, 100);
		}
	}

	// 统一初始化函数
	async function initializeAll() {
		try {
			console.log('开始初始化流程...');
			performanceMonitor.mark('Init Start');
			
			// 添加初始化常用功能
			console.log('0. 开始初始化常用功能...');
			try {
				await initQuickFunctions();
				console.log('常用功能初始化完成');
			} catch (error) {
				console.error('常用功能初始化失败:', error);
			}
			
			// 1. 更新数据概览
			console.log('1. 开始更新数据概览...');
			try {
				await updateOverview();
				console.log('数据概览更新完成');
			} catch (error) {
				console.error('数据概览更新失败:', error);
			}
			
			// 2. 初始化图表
			console.log('2. 开始初始化图表...');
			try {
				await initCharts();
				console.log('图表初始化完成');
			} catch (error) {
				console.error('图表初始化失败:', error);
			}
			
			// 3. 初始化预警信息
			console.log('3. 开始初始化预警信息...');
			try {
				await initWarning();
				console.log('预警信息初始化完成');
			} catch (error) {
				console.error('预警信息初始化失败:', error);
			}
			
			// 4. 更新排名列表
			console.log('4. 开始更新排名列表...');
			try {
				updateRankingList();
				console.log('排名列表更新完成');
			} catch (error) {
				console.error('排名列表更新失败:', error);
			}
			
			// 5. 启动排名自动滚动
			console.log('5. 开始启动排名自动滚动...');
			try {
				autoScrollRanking();
				console.log('排名自动滚动启动完成');
			} catch (error) {
				console.error('排名自动滚动启动失败:', error);
			}
			
			// 6. 获取待办事项
			console.log('6. 开始获取待办事项...');
			try {
				await fetchTodoList();
				console.log('待办事项获取完成');
			} catch (error) {
				console.error('待办事项获取失败:', error);
			}
			
			// 7. 初始化日历
			console.log('7. 开始初始化日历...');
			if ($('#equipmentCalendar').length) {
				try {
					// 直接初始化日历，不使用setTimeout
					await updateCalendarData();
					console.log('日历初始化完成');
				} catch (error) {
					console.error('日历初始化失败:', error);
				}
			} else {
				console.error('日历容器不存在');
			}

			// 绑定窗口调整事件
			console.log('8. 开始绑定窗口调整事件...');
			try {
				let resizeTimer;
				const handleResize = function() {
					clearTimeout(resizeTimer);
					resizeTimer = setTimeout(() => {
						Object.values(charts).forEach(chart => {
							chart && chart.resize();
						});
						if (calendarInstance && calendarInstance.resize) {
							calendarInstance.resize();
						}
					}, 100);
				};

				window.addEventListener('resize', handleResize);
				$(window).on('unload', function() {
					window.removeEventListener('resize', handleResize);
				});
				console.log('窗口调整事件绑定完成');
			} catch (error) {
				console.error('窗口调整事件绑定失败:', error);
			}

			performanceMonitor.mark('Init Complete');
			console.log('初始化流程完成');
		} catch (error) {
			console.error('初始化过程出错:', error);
			if (error.stack) {
				console.error('错误堆栈:', error.stack);
			}
			if (error.message) {
				console.error('错误信息:', error.message);
			}
		}
	}

	// 开始初始化流程
	isDomReady = true;
	waitForEcharts();

	// 建环形图用配置
	function createCircleChartOption(data, title, color) {
		return {
			title: {
				text: title,
				left: 'center',
				top: 'bottom',
				textStyle: {
					color: '#666',
					fontSize: 14,
					fontWeight: 'normal'
				}
			},
			tooltip: {
				trigger: 'item',
				formatter: function(params) {
					const percentage = ((params.value / data.total) * 100).toFixed(2);
					return `${title}: ${params.value} (${percentage}%)`;
				}
			},
			series: [{
				type: 'pie',
				radius: ['65%', '75%'],
				avoidLabelOverlap: false,
				label: {
					show: true,
					position: 'center',
					formatter: () => {
						const percentage = ((data.value / data.total) * 100).toFixed(2);
						return `${percentage}%\n${data.value}`;
					},
					fontSize: 16,
					fontWeight: 'bold'
				},
				emphasis: {
					label: {
						show: true,
						fontSize: 20,
						fontWeight: 'bold'
					}
				},
				data: [
					{
						value: data.value,
						name: title,
						itemStyle: { color: color }
					},
					{
						value: data.total,
						name: '总量',
						itemStyle: { color: '#f0f0f0' }
					}
				]
			}]
		};
	}

	// 更新排名列表
	function updateRankingList() {
		try {
			const rankingBody = document.getElementById('rankingBody');
			if (!rankingBody) {
				console.error('排名列表容器不存在');
				return;
			}
			// 清空现有内容
			rankingBody.innerHTML = '';
			
			
			fetchRankData();
			
		} catch (error) {
			console.error('更新排名列表时出错:', error);
		}
	}
	
	// 添加获取入库占比数据的方法
	async function fetchRankData() {
		try {
			const data =await request(API.rankList);
			console.log(data)
			
			// 创建并加载行
			data.forEach((item,index) => {
				const tr = document.createElement('tr');
				tr.className = 'ranking-item';
				
				tr.innerHTML = `
				<td><span class="rank-number">${index + 1}</span></td>
				<td><span class="project-name">${item.projectName}</span></td>
				<td><span class="count-number">${item.useCount}</span></td>
				<td><span class="count-number">${item.returnCount}</span></td>
				<td><span class="count-number">${item.diffCount}</span></td>
			`;
				
				rankingBody.appendChild(tr);
			});
			
		} catch (error) {
			console.error('获取差缺排名数据失败:', error);
		}
	}
	

	// 自动滚动排名列表
	function autoScrollRanking() {
		const rankingList = document.getElementById('rankingList');
		const tbody = rankingList.querySelector('tbody');
		let isScrolling = true;
		let scrollSpeed = 1; // 控制滚动速度
		
		// 克隆表格行用于无缝滚动
		const cloneRows = () => {
			const rows = tbody.querySelectorAll('tr:not(.clone-row)');
			// 清除之前的克隆行
			const existingClones = tbody.querySelectorAll('.clone-row');
			existingClones.forEach(clone => clone.remove());
			
			// 只有当有原始行时才进行克隆
			if (rows.length > 0) {
				rows.forEach(row => {
					const clone = row.cloneNode(true);
					clone.classList.add('clone-row');
					tbody.appendChild(clone);
				});
			}
		};
		
		// 初始化克隆
		cloneRows();
		
		// 滚动动画
		const scroll = () => {
			if (!isScrolling) return;
			
			const originalRows = tbody.querySelectorAll('tr:not(.clone-row)');
			const rowHeight = originalRows[0]?.offsetHeight || 0;
			const totalHeight = originalRows.length * rowHeight;
			
			// 增加滚动位置
			rankingList.scrollTop += scrollSpeed;
			
			// 当滚动到克隆行的末尾时重置
			if (rankingList.scrollTop >= totalHeight) {
				// 立即重置到顶部，实现无缝循环效果
				rankingList.scrollTop = 0;
			}
			
			requestAnimationFrame(scroll);
		};
		
		// 开始滚动
		if (tbody.querySelectorAll('tr:not(.clone-row)').length > 0) {
			// 确保初始滚动位置为0
			rankingList.scrollTop = 0;
			scroll();
		}
		
		// 鼠标悬停时暂停滚动
		rankingList.addEventListener('mouseenter', () => {
			isScrolling = false;
		});
		
		rankingList.addEventListener('mouseleave', () => {
			isScrolling = true;
			scroll();
		});
		
		// 监听窗口大小变化，重新计算克隆
		window.addEventListener('resize', () => {
			if (isScrolling) {
				// 保存当前滚动位置的相对百分比
				const scrollPercentage = rankingList.scrollTop / (tbody.clientHeight / 2);
				cloneRows();
				// 恢复相对滚动位置
				rankingList.scrollTop = scrollPercentage * (tbody.clientHeight / 2);
			}
		});
		
		// 定期重新克隆以保持最新数据
		setInterval(() => {
			if (isScrolling) {
				const currentScroll = rankingList.scrollTop;
				cloneRows();
				rankingList.scrollTop = currentScroll;
			}
		}, 5000); // 每5秒更新一次
	}

	// 更新数据概览
	async function updateOverview() {
		const data = await request(API.overview);
		console.log(data)
		const partOne = data[0].partOne;
		const updates = [];
		console.log(partOne)
		
		// 定义需要更新的字段
		const fields = [
			'projectCount',    // 工程数量
			'equipmentTypes',  // 机具类别
			'totalCount',      // 保有量
			'stockCount',      // 库存量
			'inUseCount',      // 在用量
			'repairCount',     // 在修量
			'scrappedCount'    // 报废量
		];

		// 批量更新数据
		fields.forEach(field => {
			const element = document.getElementById(field);
			if (element) {
				// animateNumber(element, partOne[field] || 0);
				
				const targetValue = partOne[field];
				updates.push({ element, targetValue });
			}
		});
		
		/*	for (let key in mockData.overview) {
				const element = document.getElementById(key);
				if (element) {
					const targetValue = mockData.overview[key];
					updates.push({ element, targetValue });
				}
			}*/

		// 批量更新数据
		requestAnimationFrame(() => {
			updates.forEach(({ element, targetValue }) => {
				let currentValue = 0;
				const step = targetValue / 30;
				const timer = setInterval(() => {
					currentValue += step;
					if (currentValue >= targetValue) {
						currentValue = targetValue;
						clearInterval(timer);
					}
					element.textContent = Math.floor(currentValue);
				}, 50);
			});
		});
	}
	
	
	// 更新数据概览
	async function initWarning() {
		const data =await request(API.warning);
		console.log(data)
		const partFour = data[0].partFour;
		
		console.log(partFour)
		
		// 定义需要更新的字段
		const fields = [
			'storageNum',    // 库存
			'checkNum',  // 检验
			'useNum',      // 占用
			'changeNum'      // 保有量
		];

		document.getElementById('storageNum').innerHTML = partFour.storageNum;
		document.getElementById('checkTimeNum').innerHTML = partFour.checkNum;
		document.getElementById('useNum').innerHTML = partFour.useNum;
		document.getElementById('changeNum').innerHTML = partFour.changeNum;
	}

	// 初始化日历组件
	let calendarInstance = null;

	// 更新日历数据
	async function updateCalendarData(time) {
		try {
			// 显示加载状态
			$('#equipmentCalendar').addClass('loading');
			
			// 准备请求参数
			const currentDate = new Date();
			const year = currentDate.getFullYear();
			const month = String(currentDate.getMonth() + 1).padStart(2, '0');
			const defaultTime = `${year}-${month}`;
			
			// 构造请求参数
			const params = {
				time: time ? time.time : defaultTime
			};
			
			const data = await request(API.calendarList, params);
			const calendarData = {};
			
			// 添加月度统计对象
			const monthStats = {
				leaseNum: 0,    // 领料出库总数
				backNum: 0,     // 退料接收总数
				checkNum: 0,    // 维修检验总数
				scrapNum: 0,    // 机具报废总数
				inputNum: 0,    // 修试入库总数
				newNum: 0,      // 新购入库总数
				bdNum: 0        // 库存盘点总数
			};

			data.forEach((rowData) => {
				if (!rowData || !rowData.date || !rowData.list || !Array.isArray(rowData.list)) {
					console.warn('日历行数据格式不正确:', rowData);
					return;
				}
				console.error(rowData);
				const date = rowData.date;
				const indexData = rowData.list[0] || {};
				console.error(rowData.proAndNum);
				// 累加月度统计数据，确保转换为数字进行计算
				monthStats.leaseNum += parseInt(indexData.leaseNum || 0);
				monthStats.backNum += parseInt(indexData.backNum || 0);
				monthStats.checkNum += parseInt(indexData.checkNum || 0);
				monthStats.scrapNum += parseInt(indexData.scrapNum || 0);
				monthStats.inputNum += parseInt(indexData.inputNum || 0);
				monthStats.newNum += parseInt(indexData.newNum || 0);
				monthStats.bdNum += parseInt(indexData.bdNum || 0);

				calendarData[date] = `
					<div class="date-info" title="${rowData.proAndNum}">
						<div class="date-header" data-date="${date}">
							<div class="date-number">${date.split('-')[2]}</div>
						</div>
						<div class="storage-data">
							${indexData.leaseNum > 0 ? `
								<div class="data-row in">
									<span>领料出库</span>
									<span class="data-value">${indexData.leaseNum}</span>
								</div>
							` : ''}
							${indexData.backNum > 0 ? `
								<div class="data-row out">
									<span>退料接收</span>
									<span class="data-value">${indexData.backNum}</span>
								</div>
							` : ''}
							${indexData.checkNum > 0 ? `
								<div class="data-row stock">
									<span>维修检验</span>
									<span class="data-value">${indexData.checkNum}</span>
								</div>
							` : ''}
							${indexData.scrapNum > 0 ? `
								<div class="data-row stock">
									<span>机具报废</span>
									<span class="data-value">${indexData.scrapNum}</span>
								</div>
							` : ''}
							${indexData.inputNum > 0 ? `
								<div class="data-row stock">
									<span>修试入库</span>
									<span class="data-value">${indexData.inputNum}</span>
								</div>
							` : ''}
							${indexData.newNum > 0 ? `
								<div class="data-row stock">
									<span>新购入库</span>
									<span class="data-value">${indexData.newNum}</span>
								</div>
							` : ''}
							${indexData.bdNum > 0 ? `
								<div class="data-row stock">
									<span>库存盘点</span>
									<span class="data-value">${indexData.bdNum}</span>
								</div>
							` : ''}
						</div>
					</div>
				`;
			});

			// 更新已有的统计值
			const $calendarTable = $('#equipmentCalendarTableNum');
			$calendarTable.find('.month-stat-item').each(function() {
				const $item = $(this);
				const label = $item.find('.month-stat-label').text().trim();
				let value = 0;
				
				// 根据标签文本匹配对应的统计值
				switch(label) {
					case '领料出库':
						value = monthStats.leaseNum;
						break;
					case '退料接收':
						value = monthStats.backNum;
						break;
					case '维修检验':
						value = monthStats.checkNum;
						break;
					case '机具报废':
						value = monthStats.scrapNum;
						break;
					case '修试后入库':
						value = monthStats.inputNum;
						break;
					case '新购入库':
						value = monthStats.newNum;
						break;
					case '库存盘点':
						value = monthStats.bdNum;
						break;
				}
				
				// 更新统计值
				$item.find('.month-stat-value').text(value);
			});

			// 确保layui已加载
			if (typeof layui === 'undefined') {
				console.error('Layui未加载');
				return;
			}

			// 初始化或更新日历
			layui.use(['laydate'], function() {
				const laydate = layui.laydate;
				
				// 如果已存在实例，先销毁
				if (calendarInstance) {
					try {
						// 移除旧的日历元素
						$('.layui-laydate').remove();
						calendarInstance = null;
					} catch (error) {
						console.error('销毁旧日历实例失败:', error);
					}
				}
				
				// 创建新实例
				const config = {
					elem: '#equipmentCalendar',
					type: 'date',
					theme: 'grid',
					position: 'static',
					calendar: true,
					weekStart: 1,
					format: 'yyyy-MM',
					showBottom: false,
					fullPanel: true,
					range: false,
					value: params.time,
					mark: calendarData,
					ready: function(date) {
						console.log('日历准备完成:', date);
						$('.layui-laydate-content').addClass('work-calendar');
						
						// 监听左右箭头点击事件
						$('.laydate-prev-m, .laydate-next-m, .laydate-prev-y, .laydate-next-y').off('click').on('click', function() {
							setTimeout(() => {
								const layDateContent = $('.layui-laydate-content');
								const currentHeader = $('.layui-laydate-header');
								
								// 获取当前显示的年月
								const yearText = currentHeader.find('.laydate-set-ym span').first().text().replace(/年/, '');
								const monthText = currentHeader.find('.laydate-set-ym span').last().text().replace(/月/, '');
								
								const year = parseInt(yearText);
								const month = parseInt(monthText);
								
								// 验证年月的有效性
								if (!isNaN(year) && !isNaN(month) && month >= 1 && month <= 12) {
									const formattedMonth = month.toString().padStart(2, '0');
									const currentDate = `${year}-${formattedMonth}`;
									
									console.log('切换到:', currentDate);
									
									// 防止重复触发
									const lastUpdate = layDateContent.data('lastUpdate');
									if (lastUpdate !== currentDate) {
										layDateContent.data('lastUpdate', currentDate);
										
										// 更新日历数据
										updateCalendarData({time: currentDate}).catch(error => {
											console.error('更新日历数据失败:', error);
										});
									}
								} else {
									console.warn('无效的年月值:', year, month);
								}
							}, 100); // 减少延迟时间
						});
					},
					change: function(value, date, endDate) {
						// 添加月份切换的处理
						console.log('日期变化:', value, date, endDate);
					},
					done: function(value, date, endDate) {
						// 获取选中日期的完整格式 yyyy-MM-dd
						const selectedDate = value + '-' + (date.date < 10 ? '0' + date.date : date.date);
						
						// 从 calendarData 中获取选中日期的数据
						const selectedData = calendarData[selectedDate];
						
						// 解析数据，提取各个值
						const dayData = {
							date: selectedDate,
							leaseNum: 0,
							backNum: 0,
							checkNum: 0,
							scrapNum: 0,
							inputNum: 0,
							newNum: 0,
							bdNum: 0
						};
						
						// 如果有数据，解析HTML字符串中的值
						if (selectedData) {
							const tempDiv = document.createElement('div');
							tempDiv.innerHTML = selectedData;
							
							// 获取所有数值
							const values = tempDiv.querySelectorAll('.data-value');
							values.forEach(value => {
								const text = value.previousElementSibling.textContent.trim();
								const num = parseInt(value.textContent);
								
								switch(text) {
									case '领料出库': dayData.leaseNum = num; break;
									case '退料接收': dayData.backNum = num; break;
									case '维修检验': dayData.checkNum = num; break;
									case '机具报废': dayData.scrapNum = num; break;
									case '修试入库': dayData.inputNum = num; break;
									case '新购入库': dayData.newNum = num; break;
									case '库存盘点': dayData.bdNum = num; break;
								}
							});
						}
						
						// 将数据存储到 localStorage
						localStorage.setItem('calendarDate', selectedDate);
						localStorage.setItem('calendarDayData', JSON.stringify(dayData));
						let layerIndex = layer.open({
							  type: 2,
							  title: "机具日历详情",
					//		  btn:['保存','取消'],
							  shade: [0],
							  area: ['80%', '80%'],
							  scrollbar: true,
							  move:false,
							  anim: 2,
							  yes:function(index,layero){
							  },
							  content: [bonuspath +'/backstage/indexHome/calendarDetail'],
							  success: function (layero, index) {
						    },
						});
						// 当用户点击日期时触发
						/*layer.open({
							type: 2,
							title: '机具日历详情',
							area: ['95%', '95%'],
							content: bonuspath + '/backstage/indexHome/calendar/index',
							maxmin: false
						});*/
					}
				};
				
				// 保存实例引用
				calendarInstance = laydate.render(config);
				console.log('日历实例创建完成:', calendarInstance);
			});
		} catch (error) {
			console.error('更新日历数据失败:', error);
			throw error;
		}
	}

	// 添加错误边界
	window.onerror = function(msg, url, line, col, error) {
		console.error('Global error:', {msg, url, line, col, error});
		return false;
	};

	// 待办事项切换
	$('.todo-tab').click(function() {
		$('.todo-tab').removeClass('active');
		$(this).addClass('active');
		
		const type = $(this).data('type');
		// 这里可以根据类型加载不同的数据
		if(type === 'mine') {
			fetchTodoList(type);
			
			// 加载我的待办
		} else {
			// 加载全部待办
			fetchTodoList(type);
		}
	});

	// 添加获取入库占比数据的方法
	async function fetchTodoList(type) {
		try {
			const data =await request(API.todoList,type);
			console.log(data[0])
			updateTodoList(data[0]);
		} catch (error) {
			console.error('获取待办数据失败:', error);
		}
	}
	
	function updateTodoList(data) {
		document.getElementById('newNum').innerHTML = data.newNum;
		document.getElementById('leaseNum').innerHTML = data.leaseNum;
		document.getElementById('backNum').innerHTML = data.backNum;
		document.getElementById('scrapNum').innerHTML = data.scrapNum;
		document.getElementById('bdNum').innerHTML = data.bdNum;
		document.getElementById('checkNum').innerHTML = data.checkNum;
	}

	// 初始化图表
	async function initCharts() {
		try {
			// 首先初始化所有图表实例
			const chartConfigs = [
				{ id: 'storageChart1', title: '新购入库', color: '#1890ff' },
				{ id: 'storageChart2', title: '修试后入库', color: '#36cfc9' },
				{ id: 'storageChart3', title: '盘点入库', color: '#73d13d' },
				{ id: 'storageChart4', title: '退料入库', color: '#ffc53d' },
				{ id: 'storageChart5', title: '机具数量', color: '#ff7a45' },
				{ id: 'storageChart6', title: '编码数量', color: '#722ed1' }
			];

			// 使用 requestAnimationFrame 批量初始化图表
			await new Promise(resolve => {
				requestAnimationFrame(() => {
					chartConfigs.forEach((config, index) => {
						const container = document.getElementById(config.id);
						if (container) {
							const chart = echarts.init(container);
							charts[`chart${index + 1}`] = chart;
							
							// 设置一个初始的空配置，避免白屏
							const emptyOption = createCircleChartOption(
								{value: 0, total: 100}, 
								config.title, 
								config.color
							);
							chart.setOption(emptyOption);
						}
					});
					resolve();
				});
			});

			// 获取并更新数据
			await fetchStorageData();
			
		} catch (error) {
			console.error('初始化图表时出错:', error);
			throw error;
		}
	}

	// 添加获取入库占比数据的方法
	async function fetchStorageData() {
		try {
			// 显示加载状态
			$('.chart-grid').addClass('loading');
			
			const data = await request(API.storage);
			console.log('获取到的存储数据:', data);
			
			if (!data || !data[0] || !data[0].partTwo) {
				console.warn('存储数据格式不正确');
				return;
			}

			const partTwo = data[0].partTwo;
			await updateStorageCharts(partTwo);
			
		} catch (error) {
			console.error('获取入库占比数据失败:', error);
			throw error;
		} finally {
			// 移除加载状态
			$('.chart-grid').removeClass('loading');
		}
	}

	// 添加更新图表的方法
	async function updateStorageCharts(data) {
		try {
			const chartConfigs = [
				{ key: 'newStorage', index: 1, title: '新购入库', color: '#1890ff' },
				{ key: 'repairStorage', index: 2, title: '修试后入库', color: '#36cfc9' },
				{ key: 'inventoryStorage', index: 3, title: '盘点入库', color: '#73d13d' },
				{ key: 'returnStorage', index: 4, title: '退料入库', color: '#ffc53d' },
				{ key: 'equipmentCount', index: 5, title: '机具数量', color: '#ff7a45' },
				{ key: 'deviceCount', index: 6, title: '编码数量', color: '#722ed1' }
			];

			for (const config of chartConfigs) {
				const chart = charts[`chart${config.index}`];
				if (!chart) {
					console.warn(`Chart ${config.index} not initialized`);
					continue;
				}

				const chartData = data[config.key] || { value: 0, total: 100 };
				const option = createCircleChartOption(chartData, config.title, config.color);
				
				try {
					chart.setOption(option);
				} catch (err) {
					console.error(`Error updating chart ${config.index}:`, err);
				}
			}
		} catch (error) {
			console.error('更新图表数据失败:', error);
			throw error;
		}
	}

	// 添加初始化常用功能的函数
	async function initQuickFunctions() {
		try {
			const data = await request(API.quickFunctions);
			if (!data || !Array.isArray(data)) {
				console.warn('常用功能数据格式不正确:', data);
				return;
			}

			const functionList = document.getElementById('functionList');
			if (!functionList) {
				console.error('功能列表容器不存在');
				return;
			}

			// 最多显示10个功能
			const functions = data.slice(0, 10);
			
			// 渲染功能按钮
			functions.forEach(func => {
				const functionItem = document.createElement('div');
				functionItem.className = 'function-item';
				
				// 修改点击事件处理
				functionItem.onclick = (e) => {
					e.preventDefault();
					if (func.rsUrl) {
						openMenuTwo(
							'1',
							func.rsId,
							func.parentId,
							func.rsName,
							bonuspath + func.rsUrl
						);
					}
				};

				// 修改 innerHTML 为字符串拼接
				const iconClass = func.rsIcon || 'layui-icon-app';
				functionItem.innerHTML = '<i class="' + iconClass + '"></i>' +
									   '<span>' + func.rsName + '</span>';
				
				functionList.appendChild(functionItem);
			});

		} catch (error) {
			console.error('初始化常用功能失败:', error);
			throw error;
		}
	}

	// 修改 openMenu 函数
	function openMenuTwo(type, id, parentId, menuName, resUrl) {
		console.log('Opening menu:', {type, id, parentId, menuName, resUrl});
		try {
			const parentWindow = window.parent || window.top;
			console.log('Parent window:', parentWindow);
			console.log('TabControlAppend exists:', typeof parentWindow.TabControlAppend === 'function');
			
			if (!parentWindow) {
				console.error('无法获取父窗口');
				return;
			}

			if (typeof parentWindow.TabControlAppend === 'function') {
				console.log('调用 TabControlAppend:', id, menuName, resUrl);
				parentWindow.TabControlAppend(id, menuName, resUrl);
			} else {
				console.error('父窗口中未找到 TabControlAppend 方法');
			}
		} catch (error) {
			console.error('打开菜单失败:', error);
			console.error('错误详情:', error.stack);
		}
	}


	// 添加保存菜单设置的函数
	async function saveMenuSettings() {
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
			await request(API.saveMenuSettings, {
				menuIds: JSON.stringify(checkedMenus)
			});

			layer.msg('保存成功', {icon: 1});
			layer.closeAll('page');

			// 重新加载常用功能
			await initQuickFunctions();

		} catch (error) {
			console.error('保存菜单设置失败:', error);
			layer.msg('保存失败', {icon: 2});
		}
	}

	function showMenuSettingsDialog() {
		console.log('打开菜单设置页面');
		layer.open({
			type: 2,
			title: '常用功能设置',
			area: ['800px', '600px'],
			content: bonuspath + '/backstage/indexHome/menuSettings',  // 新的后端页面路径
		});
	}

});