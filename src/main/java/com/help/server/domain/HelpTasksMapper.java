package com.help.server.domain;

import com.help.server.domain.tables.User_MemberInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hou on 2017/2/16.
 */
@Mapper
public interface HelpTasksMapper {

    @Select("SELECT * FROM user_member WHERE FIND_IN_SET(user_phone,queryChildrenAreaInfo(#{userphone}))")
    public List<User_MemberInfo> getAdminInfo(@Param("userphone") String userphone);
}
