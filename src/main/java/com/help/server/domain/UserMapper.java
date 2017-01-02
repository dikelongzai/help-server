package com.help.server.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 客户分布，订单流向
 */
@Mapper
public interface UserMapper {
	/**
	 * 客户分布
	 * @return
     */
	@Select("select count(1)  from user_admin where admin_name = #{username} AND admin_pwd = #{pwd} limit 1 ")
	public int  checkUser(@Param("username") String username, @Param("pwd") String pwd);
}
