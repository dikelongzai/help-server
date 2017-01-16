var top ={
	init:function(){
//		$(".dateinput").datepicker({
//			dateFormat: 'yy-mm-dd',
//		});
//		this.bindEvent();
		this.initEvent();
	},

//	dataInit:[], appid:"",
//	type:"inc",
//
//	bindEvent:function(){
//		$("#app-center").click(function(){
//			$("#appList").toggle(50);
//		});
//
//		$('body').delegate('.ui-datepicker-calendar td a','click', function() {
//			var date = $(".de-calendar input").val();
//			if (actualTime.type == "inc") {
//				actualTime.getData(date, actualTime.appid, "inc", "notInit");
//			}else{
//				actualTime.getData(date, actualTime.appid, "act", "notInit");
//			}
//		});
//
//		$(".btn-group-body a").click(function(){
//			$(this).parent().find(".selected").removeClass("selected");
//			$(this).addClass("selected");
//			var dateNow = common.getDateStr("");
//			if ($(this).hasClass("inc")) {
//				actualTime.getData(dateNow, actualTime.appid, "inc", "init");
//				actualTime.type = "inc";
//			} else {
//				actualTime.getData(dateNow, actualTime.appid, "act", "init");
//				actualTime.type = "act";
//			}
//		});
//
//		$("#appUl").delegate("a", "click", function(){
//			var appid = $(this).attr("appid");
//			var path = window.location.href.split("appid");
//			window.location.href = path[0] + "appid=" + appid;
//		});
//
//		$(document).click(function(e){
//			e = e || window.event;
//			if (e.target.getAttribute('id') != 'app-center' && e.target.getAttribute('id') != 'app-list'
//				&& e.target.getAttribute('id') != 'downspan' && e.target.getAttribute('id') != 'downi') {
//				$("#appList").hide();
//			}
//		});
//
//	},

	initEvent:function(){
		this.getLoginInfo();
//		actualTime.appid = common.getQueryParam(window.location.href, "appid");
//		this.getIncAndAct();
//
//		var dateNow = common.getDateStr("");
//		common.setMenuAppid(actualTime.appid);
//		// 获取新增用户信息
//		this.getData(dateNow, actualTime.appid, "inc", "init");
	},

	getLoginInfo:function(){
		url = "/admin/getUser";
		$.myPostJSON(url, {}, function(data){
			if (data && data.msg == "OK") {
				$("#user").html(data.rtn_data.name);
			} else if (data.code == "C0001") {
				window.location.href ="/login";
			}
		}, false);
	},

//	getIncAndAct:function(){
//		url = "../../searchAddPeople.do";
//		var st = common.getDateStr("");
//		var params={
//			st:st,
//			et:st,
//			appid:actualTime.appid,
//		};
//
//		$.myPostJSON(url, params, function(data){
//			if (data && data.rtn_msg == "ok") {
//				$("#inc").html(data.rtn_data[0].count);
//				url = "../../searchActivePeople.do";
//				$.myPostJSON(url, params, function(data1){
//					if (data1 && data1.rtn_msg == "ok") {
//						$("#act").html(data1.rtn_data[0].count);
//					}
//				}, false);
//			}
//		}, false);
//	},

//	getData:function(date, appid, from, isInit){
//		var url = "../../searchActiveHourPeople.do";
//		if (from == "inc") {
//			url = "../../searchAddPeopleGroupHour.do";
//		}
//		var params={
//			st:date,
//			appid:actualTime.appid,
//		};
//
//		var myChart = echarts.init(document.getElementById('chart1'));
//		myChart.showLoading();
//		$.myPostJSON(url, params, function(data){
//			if (data && data.rtn_msg == "ok") {
//				var rtndata = actualTime.formatData(date, data.rtn_data, isInit);
//				actualTime.initChart1Data(rtndata, myChart);
//			} else {
//				myChart.hideLoading();
//			}
//		}, false);
//	},
//
//	formatData:function(date, data, isInit){
//		var rtnData = new Object();
//		rtnData.title = [];
//		rtnData.y1 = [];
//		rtnData.series = [];
//
//		if (isInit == "init") {
//			for (var i = 0; i < data.length; i++) {
//				rtnData.y1.push(data[i].count);
//			}
//
//			actualTime.dataInit = rtnData.y1;
//			var obj1 = this.formatObject("今日",rtnData.y1);
//
//			rtnData.series.push(obj1);
//			rtnData.title.push("今日");
//		} else {
//			rtnData.title1 = date;
//			rtnData.y2 = [];
//			for (var i = 0; i < data.length; i++) {
//				rtnData.y2.push(data[i].count);
//			}
//
//			var obj1 = this.formatObject("今日",actualTime.dataInit);
//			var obj2 = this.formatObject(date,rtnData.y2);
//
//			rtnData.series.push(obj1);
//			rtnData.series.push(obj2);
//			rtnData.title.push("今日");
//			rtnData.title.push(date);
//		}
//		return rtnData;
//	},
//
//	formatObject:function(date, data){
//		var obj = new Object();
//		obj.name = date;
//		obj.type = "line";
//		obj.data = data;
//
//		return obj;
//	},
//
//	initChart1Data:function(data, myChart){
//        // 指定图表的配置项和数据
//        var option = {
//		    tooltip: {
//		        trigger: 'axis'
//		    },
//		    grid: {
//		        left: '3%',
//		        right: '4%',
//		        bottom: '3%',
//		        containLabel: true
//		    },
//		    toolbox: {
//		        feature: {
//		        }
//		    },
//		    legend: {
//		        data:data.title
//		    },
//		    xAxis: {
//		        type: 'category',
//		        boundaryGap: false,
//		        data: ["00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00",
//		        	   "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"],
//		    },
//		    yAxis: {
//		        type: 'value'
//		    },
//		    series: data.series,
//        };
//
//        // 使用刚指定的配置项和数据显示图表。
//        myChart.hideLoading();
//        myChart.setOption(option);
//	},
};

$(function(){
	top.init();
});