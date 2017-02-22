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
		var url = "/admin/getLeaveMessage";
		var st = $("#st").val();
		var et = $("#et").val();
		var selectValue= $(".select3").val();
		var params={
			st:st,
			et:et,
			status:selectValue,
			page:leave.cpage
		};
		$.myPostJSON(url, params, function(data){



		// {"data":[{"create_date":1484206020008,"id":1,"is_reply":1,"last_update":1484206020008,"leaving_id":2,"msg_content":"testContent","msg_date":1484206020008,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484207784440,"id":2,"is_reply":1,"last_update":1484207784440,"leaving_id":3,"msg_content":"testContent","msg_date":1484207784440,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484207938301,"id":3,"is_reply":1,"last_update":1484207938301,"leaving_id":4,"msg_content":"testContent","msg_date":1484207938301,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484240138388,"id":4,"is_reply":1,"last_update":1484240138388,"leaving_id":5,"msg_content":"testContent","msg_date":1484240138388,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484401179019,"id":5,"is_reply":1,"last_update":1484401179019,"leaving_id":6,"msg_content":"testContent","msg_date":1484401179019,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484401631335,"id":6,"is_reply":1,"last_update":1484401631335,"leaving_id":7,"msg_content":"testContent","msg_date":1484401631335,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484401704990,"id":7,"is_reply":1,"last_update":1484401704990,"leaving_id":8,"msg_content":"testContent","msg_date":1484401704990,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484401790451,"id":8,"is_reply":1,"last_update":1484401790451,"leaving_id":9,"msg_content":"testContent","msg_date":1484401790451,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484402797133,"id":9,"is_reply":1,"last_update":1484402797133,"leaving_id":10,"msg_content":"testContent","msg_date":1484402797133,"reply_content":"","reply_date":"","state":"N","user_id":0},{"create_date":1484402909842,"id":10,"is_reply":1,"last_update":1484402909842,"leaving_id":11,"msg_content":"testContent","msg_date":1484402909842,"reply_content":"","reply_date":"","state":"N","user_id":0}],"page":{"beginIndex":0,"count":1,"endIndex":10,"pageSize":10,"total":2,"totalRow":18}}
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
	getStrHtml:function(leaveInfo){
	  var html="<tr>";
	  //留言内容
	  html+="<td>"+leaveInfo.msg_content+"</td>";
	  if(leaveInfo.is_reply==1){
	    html+="<td>未回复</td>";
	  }else{
	    html+="<td>已回复</td>";
	  }
		html+="<td>"+leaveInfo.reply_content+"</td>";
      //留言时间
	   html+="<td>"+common.UnixToDate(leaveInfo.create_date,true)+"</td>";
	   	  if(leaveInfo.is_reply==2){
	   	    html+=" <td><a href='javascript:leave.delLeave("+leaveInfo.id+")' class='tablelink'> 删除</a></td>";
	   	  }else{
	   	    html+=" <td><a a href='/admin/editLeave/"+leaveInfo.id+"' class=\"tablelink\">回复</a> <a href='javascript:leave.delLeave("+leaveInfo.id+")' class='tablelink'> 删除</a></td>";
	   	  }
      html+="</tr>"
      return html;
	},
	changePage:function(pageClick){
       this.cpage=pageClick;
       this.getIncAndAct();
	},
	delLeave:function (leaveId) {
		var url = "/admin/delLeaveMessage";
		var params={
			leaveId:leaveId
		};
		$.myPostJSON(url, params, function(data){
			if(data&&data.msg=='OK'){
				location.reload();
			}

		}, false);

	},
};

$(function(){
	leave.init();
});