package com.help.server.util;

/**
 * Created by dikelongzai 15399073387@163.com on 2016-12-24
 * .
 */
public class SqlConstant {
    /** 用户部分sql */
    public static final String SQL_CHECK_USER="select id,admin_name from user_admin where admin_name=? and admin_pwd=? ";
}
