/**
 * 用户管理
 * @type {{cpage: number, init: leave.init, initEvent: leave.initEvent, bindEvent: leave.bindEvent, getIncAndAct: leave.getIncAndAct, getPageHtml: leave.getPageHtml, getPageLiHtml: leave.getPageLiHtml, getStrHtml: leave.getStrHtml, changePage: leave.changePage, delLeave: leave.delLeave}}
 */
var leave = {
    cpage: 1,
    init: function () {
        this.initEvent();
        this.bindEvent();
    },
    initEvent: function () {
        this.getIncAndAct();
    },
    bindEvent: function () {
        //直接点击查询按钮把目前的页数值设置为1 我们默认是修改了查询条件
        $("#searchBt").click(function () {
            leave.cpage = 1;
            leave.getIncAndAct();//执行查询
        });

    },

    getIncAndAct: function () {
        var tbody = '';
        var url = "/admin/getOfferList";
        var user_phone = $("#user_phone").val();
        var st = $("#st").val();
        var et = $("#et").val();
        var help_type= $("#help_type").val();
        var wallet_type= $("#wallet_type").val();
        var is_admin= $("#is_admin").val();
        var is_income= $("#is_income").val();
        var is_split= $("#is_split").val();
        var help_status= $("#help_status").val();  var help_type= $("#help_type").val();



        var params = {
            user_phone: user_phone,
            st: st,
            et: et,
            help_type: help_type,
            wallet_type: wallet_type,
            is_admin: is_admin,
            is_income: is_income,
            is_split: is_split,
            help_status: help_status,
            page: leave.cpage
        };
        $.myPostJSON(url, params, function (data) {
            if (data.data) {
                $('.tablelist tbody')[0].innerHTML = tbody;
                for (var i = 0; i < data.data.length; i++) {
                    tbody += leave.getStrHtml(data.data[i]);
                }
                $('.tablelist tbody')[0].innerHTML = tbody;
            }
            if (data.page) {
                $('.pagin').html();
                $('.pagin').html(leave.getPageHtml(data.page));

            }

        }, false);
    },
    getPageHtml: function (pageInfo) {
        var pageHtml = '';
        pageHtml += "<div class=\"message\">共<i class=\"blue\">" + pageInfo.totalRow + "</i>条记录，当前显示第&nbsp;<i class=\"blue\">" + pageInfo.count + "&nbsp;</i>页</div>";
        pageHtml += "<ul class=\"paginList\">";
        pageHtml += this.getPageLiHtml(pageInfo);
        pageHtml += "</ul>";
        return pageHtml;
    },
    getPageLiHtml:function(pageInfo){
        var lihtml='';
        //有前一页
        if(pageInfo.count>=2){
            lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+(pageInfo.count-1)+")\"><span class=\"pagepre\"></span></a></li>"
        }
        //总页数大于6显示前五条 和最后一页
        if(pageInfo.total>6){
            //当前页大于四
            if(pageInfo.count>=4){
                if(pageInfo.total-pageInfo.count<=2){
                    var pre3=pageInfo.count-3;
                    var pre4=pageInfo.count-4;
                    lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+pre4+")\">"+pre4+"</a></li>";
                    lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+pre3+")\">"+pre3+"</a></li>";
                }
                //取出前两页
                var pre2=pageInfo.count-2;
                lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+pre2+")\">"+pre2+"</a></li>";
                var pre1=pageInfo.count-1;
                lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+pre1+")\">"+pre1+"</a></li>";
                lihtml+=" <li class=\"paginItem current\"><a href=\"javascript:leave.changePage("+pageInfo.count+")\">"+pageInfo.count+"</a></li>";
                //取出后两页
                if(pageInfo.total-pageInfo.count>2){
                    var nex1=pageInfo.count+1;
                    var nex2=pageInfo.count+2;
                    lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+nex1+")\">"+nex1+"</a></li>";
                    lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+nex2+")\">"+nex2+"</a></li>";
                }
                if(pageInfo.total-pageInfo.count==2){
                    var nex1=pageInfo.count+1;
                    lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+nex1+")\">"+nex1+"</a></li>";
                }
                if(pageInfo.count!=pageInfo.total){//等于的情况在上面已经出现
                    lihtml+=" <li class=\"paginItem current\"><a href=\"javascript:leave.changePage("+pageInfo.total+")\">"+pageInfo.total+"</a></li>";
                }
                //小于四直接显示前5页
            }else{
                for(var i = 1;i<=5;i++){
                    if(i==pageInfo.count){
                        lihtml+=" <li class=\"paginItem current\"><a href=\"javascript:leave.changePage("+i+")\">"+i+"</a></li>";
                    }else{
                        lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+i+")\">"+i+"</a></li>";
                    }
                }
                lihtml+=" <li class=\"paginItem more\"><a href=\"javascript:;\">...</a></li>";
                if(pageInfo.count==pageInfo.total){
                    lihtml+=" <li class=\"paginItem current\"><a href=\"javascript:leave.changePage("+pageInfo.total+")\">"+pageInfo.total+"</a></li>";
                }else{
                    lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+pageInfo.total+")\">"+pageInfo.total+"</a></li>";
                }
            }


        }else{
            for(var i = 1;i<=pageInfo.total;i++){
                if(i==pageInfo.count){
                    lihtml+=" <li class=\"paginItem current\"><a href=\"javascript:leave.changePage("+i+")\">"+i+"</a></li>";
                }else{
                    lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+i+")\">"+i+"</a></li>";
                }
            }
        }

        //总页数大于当前页显示下一页图标
        if(pageInfo.total>pageInfo.count){
            lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+(pageInfo.count+1)+")\"><span class=\"pagenxt\"></span></a></li>"
        }
        return lihtml;
    },
    getStrHtml: function (leaveInfo) {
        //{"data":[{"help_order":"P221edi070hg8iceabf0","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":3,"user_phone":"13759889278","wallet_type":1},{"help_order":"P303d7j483h67ai06idf","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":3,"user_phone":"13759889278","wallet_type":1},{"help_order":"P4jh3365ji3i893gbbh6","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":3,"user_phone":"13759889278","wallet_type":1},{"help_order":"P53afg7gic3ggig1gf01","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":1001,"user_phone":"15389290468","wallet_type":1},{"help_order":"P61d443e5djb5dcff9cg","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":1001,"user_phone":"15389290468","wallet_type":1},{"help_order":"P75aghgad6f9132dd392","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":1001,"user_phone":"15389290468","wallet_type":1},{"help_order":"P82g516a5db96dcj19g8","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":1001,"user_phone":"15389290468","wallet_type":1},{"help_order":"P9ifa2d88eg7ej5h3hi2","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":1001,"user_phone":"15389290468","wallet_type":1},{"help_order":"P10cfe45c46gjejjjjf2","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":1001,"user_phone":"15389290468","wallet_type":1},{"help_order":"P1160c66ch1hee2c8j33","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":100,"user_id":1001,"user_phone":"15389290468","wallet_type":1}],"page":{"beginIndex":0,"count":1,"endIndex":10,"pageSize":10,"total":57,"totalRow":567}}
        var html = "<tr>";
        //留言内容
        html += "<td>" + leaveInfo.user_phone + "</td>";
        html += "<td>" + leaveInfo.money_num + "</td>";
        if (leaveInfo.help_status == 1) {
            html += "<td>未匹配</td>";
         }else if(leaveInfo.help_status == 2){
              html += "<td>完成</td>";
         }else if(leaveInfo.help_status == 3){
              html += "<td>已匹配</td>";
         }else if(leaveInfo.help_status == 5){
             html += "<td>未打款</td>";
         }else if(leaveInfo.help_status == 6){
             html += "<td>未确认</td>";
         }else if(leaveInfo.help_status == 7){
             html += "<td>冻结期</td>";
         }

    if (leaveInfo.help_type == 1) {
          html += "<td>提供帮助 </td>";
    }else {
          html += "<td>申请帮助</td>";
    }
       if (leaveInfo.wallet_type == 1) {
              html += "<td>静态钱包</td>";
        }else {
              html += "<td>动态钱包</td>";
        }
               if (leaveInfo.is_admin == 1) {
                      html += "<td>是</td>";
                }else {
                      html += "<td>否</td>";
                }
                if (leaveInfo.is_income == 0) {
                       html += "<td>是</td>";
                 }else {
                       html += "<td>否</td>";
                 }
                 if (leaveInfo.is_split == 1) {
                        html += "<td>已拆分</td>";
                  }else {
                        html += "<td>未拆分</td>";
                  }
        html += "<td>" + common.UnixToDate(leaveInfo.create_date, false) + "</td>";
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