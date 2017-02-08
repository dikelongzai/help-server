package com.help.server.domain;

import com.help.server.domain.tables.User_MemberInfo;
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

	/**
	 * 客户分布
	 * @return
	 */
	@Update("UPDATE user_member SET is_admin=1 WHERE user_id=#{user_id}")
	public boolean  updateUserRoleAdmin( @Param("user_id") long user_id);
	/**
	 * 获取用户信息 10001 user_member
	 *
	 * @return user_name, is_activate, user_phone
	 */
	@Select("select create_date,user_name,user_qq,user_id,user_carded_url,is_activate,user_phone,user_head_url,user_bank_name,user_bank_account,user_payment,user_weixin,ustatic_wallet,udynamic_wallet,ufrozen_wallet,usable_code_num,used_code_num,title_id,user_referee_phone,user_carded,referee_user_id from user_member where is_admin>0 ")
	public List<User_MemberInfo> getAdminInfo();
}
