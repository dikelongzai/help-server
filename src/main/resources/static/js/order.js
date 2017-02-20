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
        var url = "/admin/getOrderList";
        var recharge_phone = $("#recharge_phone").val();
        var withdrawals_phone = $("#withdrawals_phone").val();
        var order_type = $("#order_type").val();
        var mst= $("#mst").val();
        var met= $("#met").val();
        var cst= $("#cst").val();
        var pst= $("#pst").val();
        var cet= $("#cet").val();
        var pet= $("#pet").val();
        var complaint_status= $("#complaint_status").val();



        var params = {
            recharge_phone: recharge_phone,
            withdrawals_phone: withdrawals_phone,
            order_type: order_type,
            mst: mst,
            met: met,
            cst: cst,
            cet: cet,
            pst: pst,
            pet: pet,
            complaint_status: complaint_status,
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
    getPageLiHtml: function (pageInfo) {
        var lihtml = '';
        //有前一页
        if (pageInfo.count >= 2) {
            lihtml += " <li class=\"paginItem\"><a href=\"javascript:leave.changePage(" + (pageInfo.count - 1) + ")\"><span class=\"pagepre\"></span></a></li>"
        }
        //大于6显示前五条 和最后一页
        if (pageInfo.total > 6) {
            for (var i = 1; i <= 5; i++) {
                if (i == pageInfo.count) {
                    lihtml += " <li class=\"paginItem current\"><a href=\"javascript:leave.changePage(" + i + ")\">" + i + "</a></li>";
                } else {
                    lihtml += " <li class=\"paginItem\"><a href=\"javascript:leave.changePage(" + i + ")\">" + i + "</a></li>";
                }
            }
            lihtml += " <li class=\"paginItem more\"><a href=\"javascript:;\">...</a></li>";
            if (pageInfo.count == pageInfo.total) {
                lihtml += " <li class=\"paginItem current\"><a href=\"javascript:leave.changePage(" + pageInfo.total + ")\">" + pageInfo.total + "</a></li>";
            } else {
                lihtml += " <li class=\"paginItem\"><a href=\"javascript:leave.changePage(" + pageInfo.total + ")\">" + pageInfo.total + "</a></li>";
            }

        } else {
            for (var i = 1; i <= pageInfo.total; i++) {
                if (i == pageInfo.count) {
                    lihtml += " <li class=\"paginItem current\"><a href=\"javascript:leave.changePage(" + i + ")\">" + i + "</a></li>";
                } else {
                    lihtml += " <li class=\"paginItem\"><a href=\"javascript:leave.changePage(" + i + ")\">" + i + "</a></li>";
                }
            }
        }

        //总页数大于当前页显示下一页图标
        if (pageInfo.total > pageInfo.count) {
            lihtml += " <li class=\"paginItem\"><a href=\"javascript:leave.changePage(" + (pageInfo.count + 1) + ")\"><span class=\"pagenxt\"></span></a></li>"
        }
        return lihtml;
    },
    getStrHtml: function (leaveInfo) {
       //{"data":[{"complaint_status":0,"confirm_date":0,"create_date":1487394333275,"from_date":"","id":132,"last_update":1487394333275,"match_date":1487394333275,"money_num":500,"order_id":679,"order_num":"P679hje2h161d9bhhjhd","order_type":0,"payment_date":0,"recharge_order":"P10235a2gdf50a812229","recharge_phone":"17792380563","recharge_uid":18,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P5923973di46f96861ec","withdrawals_phone":"17792380563","withdrawals_uid":18},{"complaint_status":0,"confirm_date":0,"create_date":1487394328686,"from_date":"","id":131,"last_update":1487394328686,"match_date":1487394328686,"money_num":1000,"order_id":678,"order_num":"P67880ejddc2ieb63fjb","order_type":0,"payment_date":0,"recharge_order":"P1019h315c089ae5h1ei","recharge_phone":"13759889278","recharge_uid":3,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P5904fg7b4c2ih19hd27","withdrawals_phone":"17792380563","withdrawals_uid":18},{"complaint_status":0,"confirm_date":0,"create_date":1487392736195,"from_date":"","id":130,"last_update":1487392736195,"match_date":1487392736195,"money_num":100,"order_id":677,"order_num":"P6773e9b2icbacg4fbf8","order_type":0,"payment_date":0,"recharge_order":"P58884c7ddgja2295386","recharge_phone":"18192023651","recharge_uid":9,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P5756f22c79jj50abh4g","withdrawals_phone":"13759889278","withdrawals_uid":3},{"complaint_status":0,"confirm_date":0,"create_date":1487390487557,"from_date":"","id":129,"last_update":1487390487557,"match_date":1487390487557,"money_num":100,"order_id":676,"order_num":"P676jf7c6cg86c043aia","order_type":0,"payment_date":0,"recharge_order":"P5868dff1dja9559i54b","recharge_phone":"18192023650","recharge_uid":10,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P5427cb564j9hi20j11a","withdrawals_phone":"17709211685","withdrawals_uid":7},{"complaint_status":0,"confirm_date":0,"create_date":1487390484686,"from_date":"","id":128,"last_update":1487390484686,"match_date":1487390484686,"money_num":200,"order_id":675,"order_num":"P675b7d244f531a97hfb","order_type":0,"payment_date":0,"recharge_order":"P584i073ffiddc9b74c7","recharge_phone":"18192023650","recharge_uid":10,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P540f151ccacc8770c05","withdrawals_phone":"17709211685","withdrawals_uid":7},{"complaint_status":0,"confirm_date":0,"create_date":1487390479943,"from_date":"","id":127,"last_update":1487390479943,"match_date":1487390479943,"money_num":100,"order_id":674,"order_num":"P67484174ja66ib12e37","order_type":0,"payment_date":0,"recharge_order":"P582e51h84b9h20cj06f","recharge_phone":"18192023650","recharge_uid":10,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P502b1hje4d81113e974","withdrawals_phone":"13759889278","withdrawals_uid":3},{"complaint_status":0,"confirm_date":0,"create_date":1487390477009,"from_date":"","id":126,"last_update":1487390477009,"match_date":1487390477009,"money_num":200,"order_id":673,"order_num":"P673a199a18j028ch533","order_type":0,"payment_date":0,"recharge_order":"P580f99cfa09a9i000c9","recharge_phone":"18192023650","recharge_uid":10,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P506h7i87b30c1eeac40","withdrawals_phone":"13759889278","withdrawals_uid":3},{"complaint_status":0,"confirm_date":0,"create_date":1487390472237,"from_date":"","id":125,"last_update":1487390472237,"match_date":1487390472237,"money_num":200,"order_id":672,"order_num":"P672bgc739hc0gfa3fb3","order_type":0,"payment_date":0,"recharge_order":"P5788g641b29536icb92","recharge_phone":"13759889278","recharge_uid":3,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P4969d4a79afec17a8dj","withdrawals_phone":"17709211685","withdrawals_uid":7},{"complaint_status":0,"confirm_date":0,"create_date":1487390469258,"from_date":"","id":124,"last_update":1487390469258,"match_date":1487390469258,"money_num":200,"order_id":671,"order_num":"P6715gb7dc8i358a8h22","order_type":0,"payment_date":0,"recharge_order":"P5763f97f3g3c5hcg78c","recharge_phone":"17709211685","recharge_uid":7,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P5245gedg57gdg9bhg53","withdrawals_phone":"13759889278","withdrawals_uid":3},{"complaint_status":0,"confirm_date":0,"create_date":1487390466364,"from_date":"","id":123,"last_update":1487390466364,"match_date":1487390466364,"money_num":100,"order_id":670,"order_num":"P670c917c32aa513d7i9","order_type":0,"payment_date":0,"recharge_order":"P399050875a5j6057c7d","recharge_phone":"18192023650","recharge_uid":10,"remittance_url":"","state":"N","to_date":"","withdrawals_order":"P57414d61714fgj507gj","withdrawals_phone":"13759889278","withdrawals_uid":3}],"page":{"beginIndex":0,"count":1,"endIndex":10,"pageSize":10,"total":14,"totalRow":132}}
        var html = "<tr>";
        //留言内容
        html += "<td>" + leaveInfo.order_num + "</td>";
        html += "<td>" + leaveInfo.recharge_phone + "</td>";
        html += "<td>" + leaveInfo.withdrawals_phone + "</td>";
        html += "<td>" + leaveInfo.money_num + "</td>";
       if(leaveInfo.order_type == 2){
              html += "<td>完成</td>";
         }else if(leaveInfo.order_type == 3){
             html += "<td>已匹配</td>";
         }else if(leaveInfo.order_type == 6){
             html += "<td>已打款未确认</td>";
         }else if(leaveInfo.order_type == 8){
             html += "<td>未收到款</td>";
         }
                 if (leaveInfo.complaint_status == 0) {
                     html += "<td>正常</td>";
                  }else if(leaveInfo.complaint_status == 1){
                       html += "<td>未打款</td>";
                  }else if(leaveInfo.complaint_status == 2){
                      html += "<td>虚假打款</td>";
                  }
        html += "<td>" + common.UnixToDate(leaveInfo.create_date, false) + "</td>";
        html += "<td>" + common.UnixToDate(leaveInfo.confirm_date, false) + "</td>";
          html += "<td>" + common.UnixToDate(leaveInfo.payment_date, false) + "</td>";
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