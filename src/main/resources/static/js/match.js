/**
 * 用户管理
 * @type {{cpage: number, init: leave.init, initEvent: leave.initEvent, bindEvent: leave.bindEvent, getIncAndAct: leave.getIncAndAct, getPageHtml: leave.getPageHtml, getPageLiHtml: leave.getPageLiHtml, getStrHtml: leave.getStrHtml, changePage: leave.changePage, delLeave: leave.delLeave}}
 */
var leave = {
    init: function () {
        this.initEvent();
        this.bindEvent();
    },
    initEvent: function () {
        this.getIncAndAct();
    },
    bindEvent: function () {
        //直接点击查询按钮把目前的页数值设置为1 我们默认是修改了查询条件
        $("#search").click(function () {
            leave.getIncAndAct();//执行查询
        });
        $("input[name=bc]").click(function() {
            if ($(this).attr("checked")) {
                $("input[name=bc1]").attr("checked", "checked");
                $("input[name=bc2]").attr("checked", "checked");
            }else{
                $("input[name=bc1]").removeAttr("checked");
                $("input[name=bc2]").removeAttr("checked");
            }

        });
        $("input[name=sc]").click(function() {
            if ($(this).attr("checked")) {
                $("input[name=sc1]").attr("checked", "checked");
                $("input[name=sc2]").attr("checked", "checked");
            }else{
                $("input[name=sc1]").removeAttr("checked");
                $("input[name=sc2]").removeAttr("checked");
            }

        });

    },

    getIncAndAct: function () {
        var tbody = '';
        var url = "/admin/getMatchList";
        var st = $("#st").val();
        var et = $("#et").val();
        var params = {
            st: st,
            et: et,
        };
        $.myPostJSON(url, params, function (data) {
            //{"data":[{"buy_1":800,"buy_2":0,"day":"2017-02-04","sell_1":500,"sell_2":800},{"buy_1":3600,"buy_2":0,"day":"2017-02-03","sell_1":1100,"sell_2":200}]}
            if (data.data) {
                $('.tablelist tbody')[0].innerHTML = tbody;
                for (var i = 0; i < data.data.length; i++) {
                    tbody += leave.getStrHtml(data.data[i]);
                }
                $('.tablelist tbody')[0].innerHTML = tbody;
            }

        }, false);
    },
    getStrHtml: function (leaveInfo) {
        /**
         * $('input:checkbox').each(function() {
        if ($(this).attr('checked') ==true) {
                alert($(this).val());
        }
            });
         * @type {string}
         */
        //{"data":[{"buy_1":800,"buy_2":0,"day":"2017-02-04","sell_1":500,"sell_2":800},{"buy_1":3600,"buy_2":0,"day":"2017-02-03","sell_1":1100,"sell_2":200}]}
        var html = "<tr>";
        //正常买入部分
        html += "<td><input type=\"checkbox\" name=\"bc1\"/></td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>正常买入</td>";
        html += "<td>" + leaveInfo.buy_1 + "</td>";
        html += "<td><input name=\"b1"+leaveInfo.day+"\" id=\"b1"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.buy_1+"\"/></td>";
        //空栏目区分
        html += "<td></td>";
        //静态提现部分
        html += "<td><input type=\"checkbox\" name=\"sc1\"/></td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>静态提现</td>";
        html += "<td>" + leaveInfo.sell_1 + "</td>";
        html += "<td><input name=\"s1"+leaveInfo.day+"\" id=\"s1"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.sell_1+"\"/></td>";
        html += "</tr>"
        html+="<tr>";
        //复投买入
        html += "<td><input type=\"checkbox\" name=\"bc2\"/></td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>复投买入</td>";
        html += "<td>" + leaveInfo.buy_2 + "</td>";
        html += "<td><input name=\"b2"+leaveInfo.day+"\" id=\"b2"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.buy_2+"\"/></td>";
        //空栏目区分
        html += "<td></td>";
        //动态提现部分
        html += "<td><input type=\"checkbox\" name=\"sc2\"/></td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>动态提现</td>";
        html += "<td>" + leaveInfo.sell_2 + "</td>";
        html += "<td><input name=\"s2"+leaveInfo.day+"\" id=\"s2"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.sell_2+"\"/></td>";
        html += "</tr>"
        return html;
    },
    changePage: function (pageClick) {
        this.cpage = pageClick;
        this.getIncAndAct();
    },
};

$(function () {
    leave.init();
});