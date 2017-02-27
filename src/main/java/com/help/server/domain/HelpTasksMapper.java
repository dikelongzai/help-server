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

    @Select("SELECT * FROM user_member WHERE FIND_IN_SET(user_phone,queryChildrenAreaInfo(#{userphone})) AND is_activate =2")
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
    @Insert("insert into income_calcul_log(create_date,last_update,income_id,income_type,user_id,money_num,org_money_num,helporder,fuser_id)" +
            " values(#{create_date}, #{last_update},#{income_id},#{income_type},#{user_id},#{money_num},#{org_money_num},#{helporder},#{fuser_id})")
    public int insertInComCalcul(Income_calcul_log inComCalcul);

    @Select("select max(user_id) from (select user_id from user_member where user_id > #{ncurrent} and state <> 'D' order by user_id asc limit 100) a")
    public long getUserMemberLimit(long ncurrent);

    @Select("select * from offer_help where user_id = #{userid} AND help_type = 1 AND help_status =2 AND is_leader_income = 0")
    public List<Offer_Help> getUserCompleOfferHelp(long userid);

    // 查询更新用户的激活码--添加
    @Update("update user_member set udynamic_wallet = udynamic_wallet + #{udynamicwallet} where user_id = #{uid} ")
    public int updateUserDynamic_Wallet(@Param("udynamicwallet") float udynamicwallet, @Param("uid") long uid);

    // 查询更新用户的激活码--添加
    @Update("update offer_help set is_leader_income = 1 where help_order = #{helporder} ")
    public int updateOffer_help_income(@Param("helporder") String helporder);

    @Select("select * from orders where order_type = #{ordertype}")
    public List<Orders> getOrderInfoList(@Param("ordertype") int ordertype);

    @Select("select * from income_calcul_log where income_type = #{incometype} AND helporder = #{helporder} AND user_id = #{userid}")
    public List<Income_calcul_log> getInCome_calcul_log(@Param("incometype") int incometype,@Param("helporder") String helporder
            ,@Param("userid") long userid);

    //更新静态钱包
    @Update("update user_member set ustatic_wallet = ustatic_wallet + #{num} where user_id = #{userid}")
    public int updateUserstatic_Add(@Param("userid") long userid,@Param("num") float num);

    @Select("select count(1) from user_member where is_admin = #{isadmin}")
    public int getUserMember_Admin(@Param("isadmin") int isadmin);

    @Select("select * from user_member where is_admin = #{isadmin}")
    public List<User_MemberInfo> getUserMember_Admin_list(@Param("isadmin") int isadmin);

    @Update("update user_member set is_activate = 3 where user_id = #{uid} ")
    public int updateUserActivate(@Param("uid") long uid);
    @Delete("delete  from income_calcul_log where helporder = #{helporder}")
    public  void deleteInCome_log(@Param("helporder") String helporder);

    @Update("update user_member set ufrozen_wallet = ufrozen_wallet - #{num} where user_id = #{userid}")
    public int updateUserFrozen_task(@Param("userid") long userid,@Param("num") float num);

    @Select("SELECT SUM(money_num) FROM offer_help where help_status<>2 AND help_status<>8 AND help_type =1 AND state <> 'D' AND user_id = #{userid")
    public float sumFrozen_Money(@Param("userid") long userid); ////冻结奖计算

    @Update("update orders set order_type = #{ordertype},last_update = #{lastupdate},state = 'D'  where order_num = #{ordernum} ")
    public int updateOrderStatus_task(@Param("ordertype") int ordertype, @Param("lastupdate") long lastupdate,@Param("ordernum") String ordernum);
}




