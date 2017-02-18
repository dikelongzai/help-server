package com.help.server.domain;

import com.help.server.domain.responsebean.GetRuleInfo;
import com.help.server.domain.tables.*;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from offer_help where help_type = 1 AND help_status = 1")
    public List<Offer_Help> getOfferCalUnMatch();

    @Select("select * from offer_help where help_type = 1 AND help_status <>2")
    public List<Offer_Help> getOfferCalMatch();
    // 获取规则表
    @Select("select * from order_setting where state<>'D'")
    public GetRuleInfo getTaskRuleInfo();

    //更新offer_help 时间为了计算利息
    @Update("update offer_help set last_update = #{lastupdate} where help_order = #{helporder}")
    public int updateLastOfferHelp(@Param("lastupdate") long lastupdate,@Param("helporder") String helporder);
    // 生成验证码
    @Insert("insert into income_calcul_log(create_date,last_update,income_id,income_type,user_id,money_num,org_money_num,helporder)" +
            " values(#{create_date}, #{last_update},#{income_id},#{income_type},#{user_id},#{money_num},#{org_money_num},#{helporder})")
    public int insertInComCalcul(Income_calcul_log inComCalcul);

}
