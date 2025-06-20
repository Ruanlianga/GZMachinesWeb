var chatrCountTime='';
var someID =localStorage.getItem("someID");
var someType = localStorage.getItem("someType");
$(function(){
	chatrCountTime = localStorage.getItem("chatrCountTime");
	getChart();
});
function getChart(){
	var nums='';
	var Ave='';
	var lins=[];
	$.ajax({
		url:bonuspath+'/backstage/basics/findtrainsChartList',
		type:'post',
		data:{'keyWord':chatrCountTime,"someID":someID,"someType":someType},
		dataType:'json',
		async:false,
		success:function(data){
			var l = data.obj.list;
			Ave=data.resMsg;
			for(var j=0;j<24;j++){
				lins.push(Ave);
			}
			var myChart = echarts.init(document.getElementById('trains'));
			var  option  = {
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            crossStyle: {
				                color: '#999'
				            }
				        }
				    },				
				    toolbox: {
				        feature: {
				            dataView: {show: true, readOnly: false},
				            magicType: {show: true, type: ['line', 'bar']},
				            restore: {show: true},
				            saveAsImage: {show: true}
				        }
				    },
				    
				    legend: {
				        data:['电压','功率','额定功率']
				    },
				    
				    xAxis: [
				        {
				            type: 'category',
				            data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23'],
				            name:'时间/小时',
				            axisPointer: {
				                type: 'shadow'
				            }
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            name: '电压',
				            min: 0, 				       
				
				            axisLabel: {
				                formatter: '{value} V'
				            }
				        },
				        {
				            type: 'value',
				            name: '功率',
				            min: 0, 
				            axisLabel: {
				                formatter: '{value} W'
				            }
				        }
    
				    ],			    
				    series: [
				/*        {
				            name:'电流',
				            type:'bar',
				            data:[2.0,4.9,7.0, 23.2,25.6,176,135.6,162.2, 32.6, 20.0, 96.4, 73.3,43,43,43,43,83,3,83,63,3,3,33]
				        },*/
				        {
				            name:'电压',
				            type:'line',
				            data:[2.6, 115.9, 9.0, 26.4, 28.7, 70.7, 1175.6, 182.2, 48.7, 18.8, 6.0, 2.3,84,44,34,74,44,44,5,42,]
				        },
				        {
				            name:'功率',
				            type:'line',
				            yAxisIndex: 1,
				            data:l,
				      //      data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 220.3, 23.4, 23.0, 16.5, 12.0, 6.2,11,1,1,33,44,55,66,17,19]
				        },
				        {
				            name:'额定功率',
				            type:'line',
				            yAxisIndex: 1,
				            data:lins,
				      //      data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 220.3, 23.4, 23.0, 16.5, 12.0, 6.2,11,1,1,33,44,55,66,17,19]
				        } 
				    ] 
			}
			myChart.setOption(option); 
			
			
			
			//指定图表的配置项和数据
			/*var option = {
					title : {
				        text: '过载情况',
				        subtext: ''
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    toolbox: {
				        show : false,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value}'
				            }
				        }
				    ],
				    series : [
				        {
				            name:'功率',
				            type:'line',
				            data:l,
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                	 {type : 'average', name : '平均值'}
				                ]
				            }
				        },
				        {
				            name:'额定功率',
				            type:'line',
				            data:lins,
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                	 {type : 'average', name : '平均值'}
				                ]
				            }
				        },
				        
				    ]
				};
			//使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);*/
			
			
		},
		error:function(data){
			
		}
	});

}

