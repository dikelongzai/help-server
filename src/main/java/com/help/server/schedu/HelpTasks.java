package com.help.server.schedu;

import com.help.server.controller.appcontroller.AppServerController;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.HelpTasksMapper;
import com.help.server.domain.responsebean.GetRuleInfo;
import com.help.server.domain.tables.Dynamic_Award;
import com.help.server.domain.tables.Income_calcul_log;
import com.help.server.domain.tables.Offer_Help;
import com.help.server.domain.tables.User_MemberInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by houlongbin on 2017/2/5.
 * "0 0 * * * *" = the top of every hour of every day.
 * "*10 * * * * *" = every ten seconds.
 * "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
 * "0 * 6,19 * * *" = 6:00 AM and 7:00 PM every day.
 * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
 * "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
 * "0 0 0 25 12 ?" = every Christmas Day at midnight
 */
@Component
public class HelpTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Log log = LogFactory.getLog(HelpTasks.class);
    @Autowired
    private HelpTasksMapper helpTasksMapper;
    @Autowired
    private AppServerMapper appServerMapper;

    private long getUserTileID(int nsize){
        long title_id =1;
        List<Dynamic_Award> dynamicAwardList = helpTasksMapper.findDynmicRules();
        for(Dynamic_Award info : dynamicAwardList){

            if(info.getTeam_num() < nsize){
                title_id = info.getUser_title_id();
                continue;
            }
        }

        return title_id;
    }
    //计算每个用户的级别
    @Scheduled(cron="0 0 1 * * *")
    public void Userlevel_Cal() {

        long nMemberCount = helpTasksMapper.getUserMemberCount();
        List<User_MemberInfo> list = helpTasksMapper.getUserMemberInfoList(1);
        for (int i =0;i<list.size();i++){
            User_MemberInfo user_memberInfo = list.get(i);
            List<User_MemberInfo> userMemberlist = helpTasksMapper.getUserMemberInfo(user_memberInfo.getUser_phone());
            int nsize = userMemberlist.size()-1;
            int title_id = (int)getUserTileID(nsize);
            if(title_id > user_memberInfo.getTitle_id()){
                helpTasksMapper.updateUserTitleId(title_id,user_memberInfo.getUser_id());
                log.info("title_id:"+title_id+ "user_id:" +user_memberInfo.getUser_id());
            }
        }
    }
    //领导动态奖计算
    @Scheduled(cron="0 0 2 * * *")
    public void CalLeader_Money(){

    }

//冻结奖计算

    @Scheduled(cron="0 0 3 * * *")
    public void CalIncome_Money(){

        GetRuleInfo getRuleInfo = helpTasksMapper.getTaskRuleInfo();
//未匹配
        List<Offer_Help> offer_helpListUnMatch = helpTasksMapper.getOfferCalUnMatch();
        for (int i =0;i<offer_helpListUnMatch.size();i++){
            long nCurrentTimer = System.currentTimeMillis();
            Offer_Help offerHelp = offer_helpListUnMatch.get(i);
            long updateLong = offerHelp.getLast_update();
            long nHour = (nCurrentTimer-updateLong)/3600000/24;
            if(nHour>1){
                helpTasksMapper.updateLastOfferHelp(nCurrentTimer,offerHelp.getHelp_order());
                Income_calcul_log incomeCalculLog = new Income_calcul_log();
                incomeCalculLog.setCreate_date(nCurrentTimer);
                incomeCalculLog.setLast_update(nCurrentTimer);
                incomeCalculLog.setHelporder(offerHelp.getHelp_order());
                String idname = "income_id";
                appServerMapper.id_generator(idname);
                incomeCalculLog.setIncome_id(appServerMapper.get_id_generator(idname));
                incomeCalculLog.setIncome_type(2);
                incomeCalculLog.setOrg_money_num(offerHelp.getMoney_num());
                float money = offerHelp.getMoney_num()*getRuleInfo.getInterest_not_paid();
                incomeCalculLog.setMoney_num(money);
                incomeCalculLog.setUser_id(offerHelp.getUser_id());
                helpTasksMapper.insertInComCalcul(incomeCalculLog);
            }
        }
//已匹配
        List<Offer_Help> offer_helpListMatch = helpTasksMapper.getOfferCalMatch();
        for (int i =0;i<offer_helpListMatch.size();i++){
            long nCurrentTimer = System.currentTimeMillis();
            Offer_Help offerHelp = offer_helpListMatch.get(i);
            long updateLong = offerHelp.getLast_update();
            long nHour = (nCurrentTimer-updateLong)/3600000/24;
            if(nHour>1){
                helpTasksMapper.updateLastOfferHelp(nCurrentTimer,offerHelp.getHelp_order());
                Income_calcul_log incomeCalculLog = new Income_calcul_log();
                incomeCalculLog.setCreate_date(nCurrentTimer);
                incomeCalculLog.setLast_update(nCurrentTimer);
                incomeCalculLog.setHelporder(offerHelp.getHelp_order());
                String idname = "income_id";
                appServerMapper.id_generator(idname);
                incomeCalculLog.setIncome_id(appServerMapper.get_id_generator(idname));
                incomeCalculLog.setIncome_type(2);
                incomeCalculLog.setOrg_money_num(offerHelp.getMoney_num());
                float money = offerHelp.getMoney_num()*getRuleInfo.getInterest_paid();
                incomeCalculLog.setMoney_num(money);
                incomeCalculLog.setUser_id(offerHelp.getUser_id());
                helpTasksMapper.insertInComCalcul(incomeCalculLog);
            }
        }
    }


}
