var common ={

	getDateStr:function(date){
		var mydate = new Date();
		if (date != "" && date != null && date != undefined) {
			mydate = new Date(date);
		}
		var year = mydate.getFullYear();
		var month = mydate.getMonth() + 1;
		var day = mydate.getDate();
		if (month < 10) {
			month = "0" + month;
		}

		if (day < 10) {
			day = "0" + day;
		}

		return year + "-" + month + "-" + day;
	},

	getDateByStr:function(str){
		var date;
		if (str == "" || str == null || str == undefined) {
			return str;
		} else {
			var dataArr = str.split("-");
			var yyyy = dataArr[0];
			var mm = dataArr[1] - 1;
			var dd = dataArr[2];
			date = new Date(yyyy, mm, dd);
			return date;
		}
	},

	getQueryParam:function(href, name){
		var myLocation=href;
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = myLocation.substr(myLocation.indexOf("\?")+1).match(reg);
		if (r!=null) return unescape(r[2]); return '';
	},

   jsonSort:function(array, field, reverse) {
		if(array.length < 2 || !field || typeof array[0] !== "object") return array;
		if(typeof array[0][field] === "number") {
			array.sort(function(x, y) { return x[field] - y[field]});
		}
		if(typeof array[0][field] === "string") {
			array.sort(function(x, y) { return x[field].localeCompare(y[field])});
		}
		if(reverse) {
			array.reverse();
		}
		return array;
    },
};

$.extend({
	myPostJSON: function( url, data, callback, asycn ,callbackError) {
		if ( jQuery.isFunction( data ) ) {
			callback = data;
			data = {};
		};

		if(asycn == undefined) {
			asycn = true;
		};

		return jQuery.ajax({
			type: "POST",
			url: url,
			data: data,
			success: callback,
			async:asycn,
			dataType: "json",
			error:callbackError
		});
	}
});
