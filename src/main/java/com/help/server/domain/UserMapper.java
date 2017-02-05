package com.help.server.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

	/**
	 * 客户分布
	 * @return
	 */
	@Update("UPDATE user_member SET is_activate=#{is_activate} WHERE user_id=#{user_id}")
	public boolean  updateUserStatus(@Param("is_activate") int is_activate, @Param("user_id") long user_id);
	/**
	 * 客户分布
	 * @return
	 */
	@Update("UPDATE user_member SET is_activate=2 where is_activate=1")
	public boolean  approveAll();
}
