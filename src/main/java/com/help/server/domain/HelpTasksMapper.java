package com.help.server.domain;

import com.help.server.domain.tables.Dynamic_Award;
import com.help.server.domain.tables.User_MemberInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hou on 2017/2/16.
 */
@Mapper
public interface HelpTasksMapper {

    @Select("SELECT * FROM user_member WHERE FIND_IN_SET(user_phone,queryChildrenAreaInfo(#{userphone}))")
    public List<User_MemberInfo> getUserMemberInfo(@Param("userphone") String userphone);

    @Select("SELECT count(1) FROM user_member")
    public long getUserMemberCount();

    @Select("SELECT * FROM user_member where user_id >=#{uid} ORDER BY user_id limit 100")
    public List<User_MemberInfo> getUserMemberInfoList(long uid);

    @Select("select * from dynamic_award_rules where state<>'D' ORDER BY team_num")
    public List<Dynamic_Award> findDynmicRules();

    @Update("update user_member set title_id = #{titleid} where user_id = #{uid} ")
    public int updateUserTitleId(@Param("titleid") int titleid, @Param("uid") long uid);
}
