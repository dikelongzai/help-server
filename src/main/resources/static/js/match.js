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
            leave.changeText();


        });
        $("input[name=sc]").click(function() {
            if ($(this).attr("checked")) {
                $("input[name=sc1]").attr("checked", "checked");
                $("input[name=sc2]").attr("checked", "checked");
            }else{
                $("input[name=sc1]").removeAttr("checked");
                $("input[name=sc2]").removeAttr("checked");
            }
            leave.changeText();

        });
        //开始匹配
       $("#match").click(function () {
        var url = "/admin/doMatch";
        var paramsObj=new Object();
        var b1valueArr=new Array();
        var b2valueArr=new Array();
        var c1valueArr=new Array();
        var c2valueArr=new Array();
         $("input[type=checkbox][name=bc1]:checked").each(function() {
                var tmpvalue=new Object();
                var cvalue=$(this).val();
                tmpvalue.day=cvalue;
                var id = "b1"+cvalue;
                var value=$("#"+id).val();
                tmpvalue.value=value;
                b1valueArr.push(tmpvalue);
           });
          $("input[type=checkbox][name=bc2]:checked").each(function() {
                           var tmpvalue=new Object();
                           var cvalue=$(this).val();
                           tmpvalue.day=cvalue;
                            var id = "b2"+cvalue;
                            var value=$("#"+id).val();
                           tmpvalue.value=value;
                           b2valueArr.push(tmpvalue);
           });
          $("input[type=checkbox][name=sc1]:checked").each(function() {
                           var tmpvalue=new Object();
                           var cvalue=$(this).val();
                           tmpvalue.day=cvalue;
                             var id = "s1"+cvalue;
                                                      var value=$("#"+id).val();
                           tmpvalue.value=value;
                           c1valueArr.push(tmpvalue);
           });
          $("input[type=checkbox][name=sc2]:checked").each(function() {
                           var tmpvalue=new Object();
                           var cvalue=$(this).val();
                           tmpvalue.day=cvalue;
                           var id = "s2"+cvalue;
                           var value=$("#"+id).val();
                           tmpvalue.value=value;
                           c2valueArr.push(tmpvalue);
          });
          if(b1valueArr.length>0){
            paramsObj.b1=b1valueArr;
          }
           if(b2valueArr.length>0){
             paramsObj.b2=b2valueArr;
           }
           if(c1valueArr.length>0){
              paramsObj.c1=c1valueArr;
            }
           if(c2valueArr.length>0){
                paramsObj.c2=c2valueArr;
           }
           var params ={
                param:JSON.stringify(paramsObj)
            };
                   $.myPostJSON(url, params, function (data) {


                   }, false);

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
        html += "<td><input type=\"checkbox\" name=\"bc1\" value=\""+leaveInfo.day+"\"/></td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>正常买入</td>";
        html += "<td>" + leaveInfo.buy_1 + "</td>";
        html += "<td><input name=\"b1"+leaveInfo.day+"\" id=\"b1"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.buy_1+"\" onchange=\"leave.changeText()\"/></td>";
        //空栏目区分
        html += "<td></td>";
        //静态提现部分
        html += "<td><input type=\"checkbox\" name=\"sc1\" value=\""+leaveInfo.day+"\"/></td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>静态提现</td>";
        html += "<td>" + leaveInfo.sell_1 + "</td>";
        html += "<td><input name=\"s1"+leaveInfo.day+"\" id=\"s1"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.sell_1+"\" onchange=\"leave.changeText()\"/></td>";
        html += "</tr>"
        html+="<tr>";
        //复投买入
        html += "<td><input type=\"checkbox\" name=\"bc2\" value=\""+leaveInfo.day+"\"/></td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>复投买入</td>";
        html += "<td>" + leaveInfo.buy_2 + "</td>";
        html += "<td><input name=\"b2"+leaveInfo.day+"\" id=\"b2"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.buy_2+"\" onchange=\"leave.changeText()\"/></td>";
        //空栏目区分
        html += "<td></td>";
        //动态提现部分
        html += "<td><input type=\"checkbox\" name=\"sc2\" value=\""+leaveInfo.day+"\"/> </td>";
        html += "<td>" + leaveInfo.day + "</td>";
        html += "<td>动态提现</td>";
        html += "<td>" + leaveInfo.sell_2 + "</td>";
        html += "<td><input name=\"s2"+leaveInfo.day+"\" id=\"s2"+leaveInfo.day+"\" type=\"text\" class=\"dfinput\" value=\""+leaveInfo.sell_2+"\" onchange=\"leave.changeText()\"/></td>";
        html += "</tr>"
        return html;
    },
    changeText: function () {
        var bvalue=0;
        var svalue=0;
        $("input[type=checkbox][name=bc1]:checked").each(function() {
            var cvalue=$(this).val();
            var id = "b1"+cvalue;
            var value=$("#"+id).val();
            bvalue+=parseInt(value);
        });
        $("input[type=checkbox][name=bc2]:checked").each(function() {
            var cvalue=$(this).val();
            var id = "b2"+cvalue;
            var value=$("#"+id).val();
            bvalue+=parseInt(value);
        });
        $("input[type=checkbox][name=sc1]:checked").each(function() {
            var cvalue=$(this).val();
            var id = "s1"+cvalue;
            var value=$("#"+id).val();
            svalue+=parseInt(value);
        });
        $("input[type=checkbox][name=sc2]:checked").each(function() {
            var cvalue=$(this).val();
            var id = "s2"+cvalue;
            var value=$("#"+id).val();
            svalue+=parseInt(value);
        });
        $('.pagin').html();
        var pageHtml = '';
        pageHtml += "<div class=\"message\">买入共<i class=\"blue\">" +bvalue+ "</i>卖出共&nbsp;<i class=\"blue\">" + svalue+"</i></div>"
        $('.pagin').html(pageHtml);
        // this.cpage = pageClick;
        // this.getIncAndAct();
    },
};

$(function () {
    leave.init();
});