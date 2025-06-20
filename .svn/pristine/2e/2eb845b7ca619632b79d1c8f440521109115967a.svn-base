let form, table, laydate;
let tableIns;
let pageNum = 1; // 定义分页
let calendarDate = localStorage.getItem("calendarDate");
layui.use(["form", "table"], function() {
	form = layui.form;
	table = layui.table;
	$('#nowDate').html(calendarDate);
	initTable();
});

// 查询/重置
function queryTable(type) {
    if (type === 1) {
        reloadTable(1);
    } else if (type === 2) {
        $('#proName').val('');
        reloadTable(1);
    }
}

// 重载表格
function reloadTable(pageNum) {
    table.reload("currentTableId", {
        page: {
            curr: pageNum ? pageNum : 1,
        },
        where: {
            proName: $('#proName').val(),
			time: calendarDate
        },
    },
    );
}

// 初始化表格
function initTable() {
	tableIns = table.render({
		elem: "#currentTableId",
		id: 'currentTableId',
		height: "full-150",
		url: bonuspath + '/backstage/indexHome/getProAndNum',
		where: {
			proName: $('#proName').val(),
			time: calendarDate
		},
		request: {
			pageName: 'page',
			limitName: 'limit'
		},
		parseData: function(res) { // res 即为原始返回的数据
			return {
				"code": 0, // 解析接口状态
				"msg": '获取成功', // 解析提示文本
				"count": res.total, // 解析数据长度
				"data": res.list // 解析数据列表
			};
		},
		cols: [
			[
				{
					width: '5%',
					title: "序号",
					align: "center",
					templet: function(d) {
						return d.LAY_NUM;
					},
				},
				{
					field: "proName",
					width: '33%',
					title: "工程名称",
					unresize: true,
					align: "center",
					sort: true,
				},
				{
					field: "leaseNum",
					width: '8%',
					title: "领料出库",
					unresize: true,
					align: "center",
					sort: true,
					templet: function (d) {
                        return '<span style="color:#409eff;font-weight:bold;">'+d.leaseNum+'</span>';
                    },
				},
				{
					field: "backNum",
					width: '8%',
					title: "退料接收",
					unresize: true,
					align: "center",
					sort: true,
					templet: function (d) {
                        return '<span style="color:#409eff;font-weight:bold;">'+d.backNum+'</span>';
                    },
				},
				{
					field: "checkNum",
					width: '8%',
					title: "维修检验",
					unresize: true,
					align: "center",
					sort: true,
					templet: function (d) {
                        return '<span style="color:#409eff;font-weight:bold;">'+d.checkNum+'</span>';
                    },
				},
				{
					field: "scrapNum",
					width: '8%',
					title: "机具报废",
					unresize: true,
					align: "center",
					sort: true,
					templet: function (d) {
                        return '<span style="color:#409eff;font-weight:bold;">'+d.scrapNum+'</span>';
                    },
				},
				{
					field: "inputNum",
					width: '8%',
					title: "修饰入库",
					unresize: true,
					align: "center",
					sort: true,
					templet: function (d) {
                        return '<span style="color:#409eff;font-weight:bold;">'+d.inputNum+'</span>';
                    },
				},
				{
					field: "newNum",
					width: '8%',
					title: "新购入库",
					unresize: true,
					align: "center",
					sort: true,
					templet: function (d) {
                        return '<span style="color:#409eff;font-weight:bold;">'+d.newNum+'</span>';
                    },
				},
				{
					field: "bdNum",
					width: '8%',
					title: "库存盘点",
					unresize: true,
					align: "center",
					sort: true,
					templet: function (d) {
                        return '<span style="color:#409eff;font-weight:bold;">'+d.bdNum+'</span>';
                    },
				},
				{
					title: "操作",
					width: '6%',
					align: "center",
					unresize: true,
					templet: function(d) {
						let html = "";
						html += "<a onclick='openDeteil(" + JSON.stringify(d) + ")' style='color:blue;cursor: pointer;'>详情</a>";
						return html;
					},
				},
			],
		],
		limits: [10, 15, 20, 25, 50, 100],
		limit: 10,
		page: true,
		done: function(res, curr, count) {
			pageNum = tableIns.config.page.curr;
			table.resize("currentTableId");
		},
	});
}

/**日历详情*/
function openDeteil(obj) {
	localStorage.setItem("calendar_pro_id",obj.proId || '');
	parent.layer.open({
		type: 2,
		title: '机具日历详情',
		area: ['95%', '95%'],
		content: bonuspath + '/backstage/indexHome/calendar/index',
		maxmin: false,
		end:function(){
			localStorage.removeItem("calendar_pro_id");
		}
	});
}