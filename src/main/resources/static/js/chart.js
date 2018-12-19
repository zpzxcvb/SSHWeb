/**
 * line
 */
var lineChart = function(id, title, name) {
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(id));
     // 指定图表的配置项和数据
    var option = {
        title: {
            text: title
        },
        tooltip: {
        	trigger:'axis'
        },
        legend: {
        },
        xAxis: {
        	type: 'category',
        	data: []
        },
        yAxis: {
        	type: 'value'
        },
        series: [
        	{
        		type: 'line',
        		name: name,
        		data: []
        	}
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    return myChart;
}

/**
 * bar
 */
var barChart = function(id, title, name) {
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(id));
     // 指定图表的配置项和数据
    var option = {
        title: {
            text: title
        },
        tooltip: {
        	trigger:'axis'
        },
        legend: {
        },
        xAxis: {
        	type: 'category',
        	data: []
        },
        yAxis: {
        	type: 'value'
        },
        series: [
        	{
        		type: 'bar',
        		name: name,
        		data: []
        	}
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    return myChart;
}

/**
 * bar
 */
var barChart2 = function(id, title, name) {
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(id));
     // 指定图表的配置项和数据
    var option = {
        title: {
            text: title
        },
        tooltip: {
//        	trigger:'axis'
        },
        legend: {
        },
        xAxis: {
        	type: 'category'
        },
        yAxis: {
        	type: 'value'
        },
        dataset: {},
        series: []
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    return myChart;
}
/**
 * pie
 */
var pieChart = function(id, title, name) {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(id));
	// 指定图表的配置项和数据
	var option = {
		title : {
			text : title,
			subtext : '纯属虚构',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : []
		},
		series : [ {
			name : '访问来源',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : []
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	return myChart;
}
