/**
 * 用户管理
 * @type {{cpage: number, init: leave.init, initEvent: leave.initEvent, bindEvent: leave.bindEvent, getIncAndAct: leave.getIncAndAct, getPageHtml: leave.getPageHtml, getPageLiHtml: leave.getPageLiHtml, getStrHtml: leave.getStrHtml, changePage: leave.changePage, delLeave: leave.delLeave}}
 */
var leave ={
    cpage:1,
	init:function(){
		this.initEvent();
		this.bindEvent();
	},
	initEvent:function(){
		this.getIncAndAct();
	},
	bindEvent:function(){
		 //直接点击查询按钮把目前的页数值设置为1 我们默认是修改了查询条件
    		$("#searchBt").click(function(){
    		  leave.cpage=1;
    		  leave.getIncAndAct();//执行查询
			});

	},

	getIncAndAct:function(){
	    var tbody='';
		var url = "/admin/getUserList";
		var user_phone = $("#user_phone").val();
		var user_name = $("#user_name").val();
		var user_referee_phone = $("#user_referee_phone").val();
		var is_activate = $("#is_activate").val();
		var title_id = $("#title_id").val();


		var params={
			user_phone:user_phone,
			user_name:user_name,
			user_referee_phone:user_referee_phone,
			is_activate:is_activate,
			title_id:title_id,
			page:leave.cpage
		};
		$.myPostJSON(url, params, function(data){
		  if(data.data){
		  	$('.tablelist tbody')[0].innerHTML=tbody;
				for (var i = 0; i < data.data.length; i++) {
				  tbody+=leave.getStrHtml(data.data[i]);
				}
				$('.tablelist tbody')[0].innerHTML=tbody;
		  }
		  if(data.page){
		   $('.pagin').html();
		    $('.pagin').html(leave.getPageHtml(data.page));

		  }

		}, false);
	},
	getPageHtml:function(pageInfo){
	var pageHtml='';
    pageHtml+="<div class=\"message\">共<i class=\"blue\">"+pageInfo.totalRow+"</i>条记录，当前显示第&nbsp;<i class=\"blue\">"+pageInfo.count+"&nbsp;</i>页</div>";
  	pageHtml+="<ul class=\"paginList\">";
  	pageHtml+=this.getPageLiHtml(pageInfo);
  	pageHtml+="</ul>";
    return pageHtml;
	},
    getPageLiHtml:function(pageInfo){
     var lihtml='';
     //有前一页
     if(pageInfo.count>=2){
		 lihtml+=" <li class=\"paginItem\"><a href=\"javascript:leave.changePage("+(pageInfo.count-1)+")\"><span class=\"pagepre\"></span></a></li>"
     }
     //大于6显示前五条 和最后一页
     if(pageInfo.total>6){
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
	getStrHtml:function(leaveInfo){
	//{"is_activate":"-1","page":"1","title_id":"-1","user_name":"","user_phone":"","user_referee_phone":""};result={"data":[{"create_date":1484206011142,"is_activate":0,"is_freeze":0,"udynamic_wallet":0,"ufrozen_wallet":0,"user_carded":"","user_id":3,"user_name":"测试","user_phone":"13759889278","user_referee_phone":"15388898890","user_title":"职员","ustatic_wallet":0},{"create_date":1484240141864,"is_activate":0,"is_freeze":0,"udynamic_wallet":0,"ufrozen_wallet":0,"user_carded":"","user_id":5,"user_name":"测试","user_phone":"15389290468","user_referee_phone":"15388898890","user_title":"组长","ustatic_wallet":0},{"create_date":1484750430428,"is_activate":0,"is_freeze":0,"udynamic_wallet":0,"ufrozen_wallet":0,"user_carded":"","user_id":7,"user_name":"肖振宇","user_phone":"17709211685","user_referee_phone":"18652981927","user_title":"职员","ustatic_wallet":0},{"create_date":1485240473713,"is_activate":0,"is_freeze":0,"udynamic_wallet":0,"ufrozen_wallet":0,"user_carded":"","user_id":8,"user_name":"","user_phone":"","user_referee_phone":"","user_title":"主任","ustatic_wallet":0}],"page":{"beginIndex":0,"count":1,"endIndex":4,"pageSize":10,"total":1,"totalRow":4}}
	  var html="<tr>";
	  //留言内容
	   html+="<td>"+leaveInfo.user_name+"</td>";
		html+="<td>"+leaveInfo.user_title+"</td>";
		html+="<td>"+leaveInfo.user_phone+"</td>";
		html+="<td>"+leaveInfo.user_referee_phone+"</td>";
		html+="<td>"+leaveInfo.udynamic_wallet+"</td>";
		html+="<td>"+leaveInfo.ustatic_wallet+"</td>";
		html+="<td>"+leaveInfo.ufrozen_wallet+"</td>";
		html+="<td>"+common.UnixToDate(leaveInfo.create_date,false)+"</td>";
		if(leaveInfo.is_activate==0){
			html+="<td>未激活</td><td><a href='/admin/userDetail/"+leaveInfo.user_id+"' class=\"tablelink\">详情</a></td>";
		}else if(leaveInfo.is_activate==1){
			html+="<td>未审批</td>";
			html+=" <td><a href='/admin/appro/"+leaveInfo.user_id+"' class=\"tablelink\">审批</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='/admin/userDetail/"+leaveInfo.user_id+"' class=\"tablelink\">详情</a></td>"
		}else if(leaveInfo.is_activate==2){
			html+="<td>已审批</td>";
			html+=" <td><a href='/admin/frozen/"+leaveInfo.user_id+"' class=\"tablelink\">冻结</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='/admin/userDetail/"+leaveInfo.user_id+"' class=\"tablelink\">详情</a></td>"
		}else if(leaveInfo.is_activate==3){
			html+="<td>冻结</td>";
			html+=" <td><a  href='/admin/appro/"+leaveInfo.user_id+"' class=\"tablelink\">解冻</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='/admin/userDetail/"+leaveInfo.user_id+"' class=\"tablelink\">详情</a></td>"
		}
		html+="</tr>"
      return html;
	},
	changePage:function(pageClick){
       this.cpage=pageClick;
       this.getIncAndAct();
	},
};

$(function(){
	leave.init();
});