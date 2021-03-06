package com.help.server.util;

/**
 * Created by dikelongzai 15399073387@163.com on 2016-12-24
 * .
 */
public class SqlConstant {
    /** 用户部分sql */
    public static final String SQL_CHECK_USER="select id,admin_name from user_admin where admin_name=? and admin_pwd=? ";
    public  static final String SQL_BASE_LEAVING_MSG="SELECT l.*,(select user_phone from user_member where user_id=l.user_id) as user_phone FROM leaving_msg l  WHERE  l.state<>'D' ";
    public  static final String SQL_BASE_CODE_MSG="SELECT user_id,user_name,user_phone,usable_code_num,used_code_num FROM user_member WHERE  1=1 ";
    public  static final String SQL_BASE_USER_MSG="SELECT u.is_admin,u.user_id,u.user_name,u.create_date,u.user_phone,u.user_referee_phone,u.user_carded,u.ustatic_wallet,u.udynamic_wallet,u.ufrozen_wallet,u.is_activate,(SELECT user_title FROM `dynamic_award_rules` WHERE user_title_id=u.title_id)as user_title  FROM user_member u WHERE  1=1 ";
    public static  final String SQL_COMMON_DESC=" order by create_date desc ";
    public  static final String SQL_GET_OFFER=" select o.help_order,o.user_id,o.money_num,o.user_phone,(select referee_user_id from `user_member` where user_id=o.user_id) as ruid from offer_help o  ";
    public static final String SQL_GET_OFFER_DESC=" ORDER BY  o.money_num ASC";
    //获取下级
    public static final String SQL_GET_LOWER="SELECT user_id FROM `user_member` WHERE referee_user_id=";
    public static  final String COMMON_SELECT_ALL="-1";


    public  static final String SQL_GET_OFFER_PAGE=" select create_date,help_order,user_id,money_num,user_phone,help_type,help_status,wallet_type,is_admin,is_income,is_split from offer_help WHERE  1=1  ";
    public  static final String SQL_GET_ORDER_PAGE=" select * from orders WHERE  1=1  ";
}
