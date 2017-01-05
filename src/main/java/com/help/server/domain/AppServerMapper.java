
package com.help.server.domain;

import com.help.server.domain.tables.Activate_Code;
import com.help.server.domain.tables.User_Member;
import com.help.server.domain.tables.User_MemberInfo;
import com.help.server.domain.tables.Validate_Code;
import org.apache.ibatis.annotations.*;

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
    public User_MemberInfo getUserInfo(@Param("userId") Long userId);

    /**
     * 获取用户钱包 10002
     * @return
     */
    @Select("select user_id  from user_member where user_phone = #{username}")
    public long  getUserIDByaccount(@Param("username") String username);

    @Select("select count(1)  from user_member where user_phone = #{username}")
    public int  getUserCount(@Param("username") String username);
    /**
     * 用户登录密码校验 1003
     * @return
     */
    @Select("select count(1)  from user_member where user_phone = #{username} AND user_login_pwd = #{pwd} limit 1 ")
    public int  checkUser(@Param("username") String username, @Param("pwd") String pwd);
    /**
     * 用户注册 1004
     * @return
     */
    @Insert("insert into User_Member(user_id,user_name,user_login_pwd,user_phone,user_referee_phone,referee_user_id" +
            ",create_date,last_update,state,title_id,usable_code_num,used_code_num) values(#{user_id}, #{user_name}" +
            ",#{user_login_pwd},#{user_phone},#{user_referee_phone},#{referee_user_id},#{create_date},#{last_update},#{state},#{title_id},#{usable_code_num},#{used_code_num})")
    public int registerUser(User_Member userMember);
    //生成id
    @Update("update id_generator set id_count = id_count +1 where id_name = #{idname}")
    public int id_generator(@Param("idname") String idname);

    //获取生成的id
    @Select("select id_count  from id_generator where id_name = #{idname}")
    public  long get_id_generator(@Param("idname") String idname);

    // 生成验证码
    @Insert("insert into Validate_Code(user_phone,user_imei,validate_type,validate_code,,create_date,last_update,state,validate_id) values(#{user_phone}, #{user_imei}" +
            ",#{validate_type},#{validate_code},#{create_date},#{last_update},#{create_date},#{validate_id})")
    public  int insertValCode(Validate_Code validate_code);

    //查询该手机号是否存在验证码
    @Select("select count(1)  from validate_code where user_phone = #{userphone} AND validate_type = #{validatetype}")
    public int  getValCodeCount(@Param("userphone") String userphone,@Param("validatetype") int validatetype);

    //生成id
    @Update("update validate_code set validate_code =  #{code} where user_phone = #{userphone} AND validate_type = #{validatetype} ")
    public int updateValCode(@Param("code") String code,@Param("userphone") String userphone,@Param("validatetype") int validatetype);

    //查询该手机号是否存在验证码
    @Select("select count(1)  from validate_code where user_phone = #{userphone} AND validate_type = #{validatetype} AND validate_code = #{code}")
    public int  getValCodeCountByCode(@Param("userphone") String userphone,@Param("validatetype") int validatetype,@Param("code") String code);

    // 查询用户还有多少激活码
    @Select("select usable_code_num  from user_member where user_id = #{uid} ")
    public int  getUserCodeNum(@Param("uid") long uid);

    // 查询更新用户的激活码--添加
    @Update("update user_member set usable_code_num = usable_code_num - #{codenum} where user_id = #{uid} ")
    public int  updateUserCodeNum_add(@Param("uid") int codenum,@Param("uid") long uid);

    // 查询更新用户的激活码--减少
    @Update("update user_member set usable_code_num = usable_code_num - #{codenum} where user_id = #{uid} ")
    public int  updateUserCodeNum_dec(@Param("codenum") int codenum,@Param("uid") long uid);

    // 生成验证码
    @Insert("insert into activate_code(create_date,last_update,state,from_uid,to_uid,code_num,is_from_admin) values(#{create_date}, #{last_update}" +
            ",#{state},#{from_uid},#{to_uid},#{code_num},#{is_from_admin})")
    public  int insertValCode(Activate_Code activate_code);

    // 查询更新用户的激活码--减少
    @Update("update user_member set is_activate = 1 where user_phone = #{to_phone} ")
    public int  updateValUser(@Param("to_phone") String to_phone);

    // 查询用户是否存在
    @Select("select count(1)  from user_member where user_phone = #{userphone} AND user_referee_phone =  #{user_referee_phone}")
    public int  getUserExit(@Param("userphone") String userphone,@Param("user_referee_phone") String user_referee_phone);

    // 验证码校验
    @Select("select count(1)  from validate_code where user_phone = #{userphone} AND validate_type =  #{validate_type} AND validate_code =  #{validate_code} AND validate_type =  #{validate_type}")
    public int  forgetValiCode(@Param("userphone") String userphone,@Param("user_referee_phone") String user_referee_phone,@Param("validate_code") String validate_code,@Param("validate_type") int validate_type);

    // 修改用户密码
    @Update("update user_member set user_login_pwd = #{user_login_pwd}  where user_phone = #{to_phone} ")
    public int  updateUserPWd(@Param("user_login_pwd") String user_login_pwd,@Param("to_phone") String to_phone);
}

