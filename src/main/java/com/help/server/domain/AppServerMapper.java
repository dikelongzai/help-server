
package com.help.server.domain;

 import com.help.server.domain.tables.Da_Show_Corp_City;
 import com.help.server.domain.tables.Da_show_waybill_province;
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
     * 用户登录
     * @return
     */
    @Select("select count(1)  from user_admin where admin_name = #{username} AND admin_pwd = #{pwd} limit 1 ")
    public int  checkUser(@Param("username") String username, @Param("pwd") String pwd);
}
