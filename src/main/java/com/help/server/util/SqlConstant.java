package com.help.server.util;

/**
 * Created by dikelongzai 15399073387@163.com on 2016-12-24
 * .
 */
public class SqlConstant {
    /** 用户部分sql */
    public static final String SQL_CHECK_USER="select id,admin_name from user_admin where admin_name=? and admin_pwd=? ";
    public  static final String SQL_BASE_LEAVING_MSG="SELECT * FROM leaving_msg WHERE  state<>'D' ";
    public  static final String SQL_BASE_CODE_MSG="SELECT user_id,user_name,user_phone,usable_code_num,used_code_num FROM user_member WHERE  1=1 ";
    public  static final String SQL_BASE_USER_MSG="SELECT u.user_id,u.user_name,u.create_date,u.user_phone,u.user_referee_phone,u.user_carded,u.ustatic_wallet,u.udynamic_wallet,u.ufrozen_wallet,u.is_activate,u.is_freeze,(SELECT user_title FROM `dynamic_award_rules` WHERE user_title_id=u.title_id)as user_title  FROM user_member u WHERE  1=1 ";
    public static  final String SQL_COMMON_DESC=" order by create_date desc ";
    public static  final String COMMON_SELECT_ALL="-1";
}
