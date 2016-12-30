
package com.help.server.domain;

import com.help.server.domain.tables.Da_Show_Corp_City;
import com.help.server.domain.tables.Da_show_waybill_province;
import com.help.server.domain.tables.User_Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * Created by houxianyong on 2016/12/28.
 */
@Mapper
public interface AppServerMapper {

    /**
     * 获取用户信息 10001 user_member
     * @return user_name,is_activate,user_phone
     */
    @Select("select user_name,user_id,is_activate,user_phone,is_freeze,user_head_url,user_bank_name,user_bank_account,user_payment,user_weixin,ustatic_wallet,udynamic_wallet,ufrozen_wallet,usable_code_num,used_code_num,title_id from user_member where user_id = #{userId} ")
    public User_Member getUserInfo(@Param("userId") Long userId);

    /**
     * 获取用户钱包 10002
     * @return
     */
    @Select("select count(1)  from user_admin where admin_name = #{username} AND admin_pwd = #{pwd} limit 1 ")
    public int  getUserWallet(@Param("username") String username, @Param("pwd") String pwd);

    /**
     * 用户登录密码校验 1003
     * @return
     */
    @Select("select count(1)  from user_admin where admin_name = #{username} AND admin_pwd = #{pwd} limit 1 ")
    public int  checkUser(@Param("username") String username, @Param("pwd") String pwd);
}
