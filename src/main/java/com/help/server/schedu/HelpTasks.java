package com.help.server.schedu;

import com.help.server.controller.appcontroller.AppServerController;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.HelpTasksMapper;
import com.help.server.domain.responsebean.GetRuleInfo;
import com.help.server.domain.tables.*;
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

    private long getUserTileID(int nTeamsize,int nzhiSize){
        long title_id =1;
        List<Dynamic_Award> dynamicAwardList = helpTasksMapper.findDynmicRules();
        for(Dynamic_Award info : dynamicAwardList){

            if(info.getTeam_num() <= nTeamsize&&info.getDirect_num()<=nzhiSize){
                title_id = info.getUser_title_id();
                continue;
            }
        }
        return title_id;
    }
    //计算每个用户的级别
    @Scheduled(cron="0 0 1 * * *")
    public void Userlevel_Cal() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.error("Userlevel_Cal:"+df.format(new Date()));// new Date()为获取当前系统时间

        long nMemberCount = helpTasksMapper.getUserMemberCount();
        long ncount = nMemberCount / 100;
        if(ncount>0){
            long ncurrent = 0;
            for (int i = 0; i <= ncount; i++) {
                long ndexcurrent = helpTasksMapper.getUserMemberLimit(ncurrent);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<User_MemberInfo> list = helpTasksMapper.getUserMemberInfoList(ncurrent);
                for (int j =0;j<list.size();j++){
                    User_MemberInfo user_memberInfo = list.get(j);
                    List<User_MemberInfo> userMemberlist = helpTasksMapper.getUserMemberInfo(user_memberInfo.getUser_phone());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int nDirect = helpTasksMapper.countDirectUser(user_memberInfo.getUser_phone());
                    int nsize = userMemberlist.size()-1;
                    int title_id = (int)getUserTileID(nsize,nDirect);
                    if(title_id > user_memberInfo.getTitle_id()){
                        helpTasksMapper.updateUserTitleId(title_id,user_memberInfo.getUser_id());
                        log.info("title_id:"+title_id+ "user_id:" +user_memberInfo.getUser_id());
                    }
                }
                ncurrent = ndexcurrent;
            }
        }else{
            List<User_MemberInfo> list = helpTasksMapper.getUserMemberInfoList(1);
            for (int i =0;i<list.size();i++){
                User_MemberInfo user_memberInfo = list.get(i);
                List<User_MemberInfo> userMemberlist = helpTasksMapper.getUserMemberInfo(user_memberInfo.getUser_phone());
                int nsize = userMemberlist.size()-1;
                int nDirect = helpTasksMapper.countDirectUser(user_memberInfo.getUser_phone());
                int title_id = (int)getUserTileID(nsize,nDirect);
                if(title_id > user_memberInfo.getTitle_id()){
                    helpTasksMapper.updateUserTitleId(title_id,user_memberInfo.getUser_id());
                    log.info("title_id:"+title_id+ "user_id:" +user_memberInfo.getUser_id());
                }
            }
        }

    }
    //计算收入第一级
    void calLeader_Income_one(User_MemberInfo user_memberInfo,User_MemberInfo user_memberInfo1,long nCurrentTimer, List<Dynamic_Award> dynamicAwardList,int generation){

        List<Offer_Help> offerHelpList = helpTasksMapper.getUserCompleOfferHelp(user_memberInfo1.getUser_id());
        float generation_one = 0;
        long nTitle_id = user_memberInfo.getTitle_id();
        for (int i =0;i<dynamicAwardList.size();i++){
            Dynamic_Award dynamicAward = dynamicAwardList.get(i);
            if(dynamicAward.getUser_title_id()==nTitle_id){
                if(generation ==0){
                    generation_one = dynamicAward.getOne_generation();
                }else if(generation ==1){
                    generation_one = dynamicAward.getTwo_generation();
                }else if(generation ==2){
                    generation_one = dynamicAward.getThree_generation();
                }else{
                    generation_one = dynamicAward.getFour_generation();
                }
                break;
            }
        }
        if(generation_one ==0.0){
            return;
        }
        for (int m =0;m<offerHelpList.size();m++){
            Offer_Help offerHelp= offerHelpList.get(m);
            Income_calcul_log incomeCalculLog = new Income_calcul_log();
            incomeCalculLog.setCreate_date(nCurrentTimer);
            incomeCalculLog.setLast_update(nCurrentTimer);
            incomeCalculLog.setHelporder(offerHelp.getHelp_order());
            String idname = "income_id";
            appServerMapper.id_generator(idname);
            incomeCalculLog.setIncome_id(appServerMapper.get_id_generator(idname));
            incomeCalculLog.setIncome_type(1);
            incomeCalculLog.setOrg_money_num(offerHelp.getMoney_num());

            float money = offerHelp.getMoney_num()*generation_one;
            incomeCalculLog.setMoney_num(money);
            incomeCalculLog.setUser_id(user_memberInfo.getUser_id());
            incomeCalculLog.setFuser_id(offerHelp.getUser_id());
            //添加到用户领导奖领导奖和更新
            helpTasksMapper.updateUserDynamic_Wallet(money,user_memberInfo.getUser_id());
            helpTasksMapper.updateOffer_help_income(offerHelp.getHelp_order());
            //插入收入表
            helpTasksMapper.insertInComCalcul(incomeCalculLog);
        }
    }
    //领导动态奖计算
    @Scheduled(cron="0 0 2 * * *")
    public void CalLeader_Money(){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.error("CalLeader_Money:"+df.format(new Date()));// new Date()为获取当前系统时间

        List<Dynamic_Award> dynamicAwardList = helpTasksMapper.findDynmicRules();

        long nMemberCount = helpTasksMapper.getUserMemberCount();
        long ncount = nMemberCount / 100;
        long nCurrentTimer = System.currentTimeMillis();
        if(ncount>0){
            long ncurrent = 0;
            for (int i = 0; i <= ncount; i++) {
                long ndexcurrent = helpTasksMapper.getUserMemberLimit(ncurrent);
                List<User_MemberInfo> list = helpTasksMapper.getUserMemberInfoList(ncurrent);
                for (int j =0;j<list.size();j++){
                    User_MemberInfo user_memberInfo = list.get(j);
                        List<User_MemberInfo> oneList= appServerMapper.getUserLevel(user_memberInfo.getUser_id());
                        for (int k =0;k<oneList.size();k++){//一级
                            User_MemberInfo user_memberInfo1 = oneList.get(k);
                            calLeader_Income_one(user_memberInfo,user_memberInfo1,nCurrentTimer,dynamicAwardList,0);
                            List<User_MemberInfo> twoList= appServerMapper.getUserLevel(user_memberInfo1.getUser_id());
                            for (int m =0;m<twoList.size();m++){ //二级
                                User_MemberInfo user_memberInfo2 = twoList.get(m);
                                calLeader_Income_one(user_memberInfo,user_memberInfo2,nCurrentTimer,dynamicAwardList,1);
                                List<User_MemberInfo> threeList= appServerMapper.getUserLevel(user_memberInfo2.getUser_id());
                                for (int n =0;n<threeList.size();n++){ //三级
                                    User_MemberInfo user_memberInfo3 = threeList.get(n);
                                    calLeader_Income_one(user_memberInfo,user_memberInfo3,nCurrentTimer,dynamicAwardList,2);
                                    List<User_MemberInfo> fourList= appServerMapper.getUserLevel(user_memberInfo3.getUser_id());
                                    for (int l =0;l<fourList.size();l++){ //�ļ�
                                        User_MemberInfo user_memberInfo4 = fourList.get(l);
                                        calLeader_Income_one(user_memberInfo,user_memberInfo4,nCurrentTimer,dynamicAwardList,3);
                                    }
                                }
                            }

                        }

                }
                ncurrent = ndexcurrent;
            }
        }else{
            List<User_MemberInfo> list = helpTasksMapper.getUserMemberInfoList(1);
            for(int i=0;i<list.size();i++){
                User_MemberInfo user_memberInfo = list.get(i);
                    List<User_MemberInfo> oneList= appServerMapper.getUserLevel(user_memberInfo.getUser_id());
                    for (int k =0;k<oneList.size();k++){//一级
                        User_MemberInfo user_memberInfo1 = oneList.get(k);
                        calLeader_Income_one(user_memberInfo,user_memberInfo1,nCurrentTimer,dynamicAwardList,0);
                        List<User_MemberInfo> twoList= appServerMapper.getUserLevel(user_memberInfo1.getUser_id());
                        for (int m =0;m<twoList.size();m++){ //二级
                            User_MemberInfo user_memberInfo2 = twoList.get(m);
                            calLeader_Income_one(user_memberInfo,user_memberInfo2,nCurrentTimer,dynamicAwardList,1);
                            List<User_MemberInfo> threeList= appServerMapper.getUserLevel(user_memberInfo2.getUser_id());
                            for (int n =0;n<threeList.size();n++){ //三级
                                User_MemberInfo user_memberInfo3 = threeList.get(n);
                                calLeader_Income_one(user_memberInfo,user_memberInfo3,nCurrentTimer,dynamicAwardList,2);
                                List<User_MemberInfo> fourList= appServerMapper.getUserLevel(user_memberInfo3.getUser_id());
                                for (int l =0;l<fourList.size();l++){ //�ļ�
                                    User_MemberInfo user_memberInfo4 = fourList.get(l);
                                    calLeader_Income_one(user_memberInfo,user_memberInfo4,nCurrentTimer,dynamicAwardList,3);
                                }
                            }
                        }

                    }
            }
        }
    }

    //冻结奖计算
    @Scheduled(cron="0 0/30 0-23 * * *")
    public void CalIncome_Money() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.error("CalIncome_Money:"+df.format(new Date()));// new Date()为获取当前系统时间
/////////////////////利息记录/////////////////////////////////////////////////////////
        GetRuleInfo getRuleInfo = helpTasksMapper.getTaskRuleInfo();
//未匹配
        List<Offer_Help> offer_helpListUnMatch = helpTasksMapper.getOfferCalUnMatch();
        for (int i = 0; i < offer_helpListUnMatch.size(); i++) {
            long nCurrentTimer = System.currentTimeMillis();
            Offer_Help offerHelp = offer_helpListUnMatch.get(i);
            long updateLong = offerHelp.getLast_update();

            long createdate = offerHelp.getCreate_date();
            int days = (int) ((nCurrentTimer - createdate) / 1000 / 60 / 60 / 24);
            long updateDate = createdate + days * 3600000 * 24;

            long nHour = (nCurrentTimer - updateLong);
            if (nHour >= (3600000 * 24)) {
                helpTasksMapper.updateLastOfferHelp(updateDate, offerHelp.getHelp_order());
                Income_calcul_log incomeCalculLog = new Income_calcul_log();
                incomeCalculLog.setCreate_date(nCurrentTimer);
                incomeCalculLog.setLast_update(nCurrentTimer);
                incomeCalculLog.setHelporder(offerHelp.getHelp_order());
                String idname = "income_id";
                appServerMapper.id_generator(idname);
                incomeCalculLog.setIncome_id(appServerMapper.get_id_generator(idname));
                incomeCalculLog.setIncome_type(2);
                incomeCalculLog.setOrg_money_num(offerHelp.getMoney_num());
                float money = offerHelp.getMoney_num() * getRuleInfo.getInterest_not_paid();
                incomeCalculLog.setMoney_num(money);
                incomeCalculLog.setUser_id(offerHelp.getUser_id());
                helpTasksMapper.insertInComCalcul(incomeCalculLog);
            }
        }
//已匹配
        List<Offer_Help> offer_helpListMatch = helpTasksMapper.getOfferCalMatch();
        for (int i = 0; i < offer_helpListMatch.size(); i++) {
            long nCurrentTimer = System.currentTimeMillis();
            Offer_Help offerHelp = offer_helpListMatch.get(i);
            long updateLong = offerHelp.getLast_update();

            long createdate = offerHelp.getCreate_date();
            int days = (int) ((nCurrentTimer - createdate) / 1000 / 60 / 60 / 24);
            long updateDate = createdate + days * 3600000 * 24;

            long nHour = (nCurrentTimer - updateLong);
            if (nHour >= (3600000 * 24)) {
                helpTasksMapper.updateLastOfferHelp(updateDate, offerHelp.getHelp_order());
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
/////////////////////冻结期结束 ，/////////////////////////////////////////////////////////
        // 冻结期
        List<Orders> orderInfoList = helpTasksMapper.getOrderInfoList(7);
        long nCurrentTimer = System.currentTimeMillis();
        for (int i = 0; i < orderInfoList.size(); i++) {
            Orders orders = orderInfoList.get(i);
            long nTimerBetw = nCurrentTimer - orders.getConfirm_date();
            if (nTimerBetw > (getRuleInfo.getFreezing_time() * 3600000)) { //订单冻结期，结束，订单完成
                appServerMapper.updateOrderStatus(2, nCurrentTimer, orders.getOrder_num());
                appServerMapper.updateOfferHelp(2, nCurrentTimer, orders.getRecharge_order());
                appServerMapper.updateOfferHelp(2, nCurrentTimer, orders.getWithdrawals_order());
                long recharge_uid = appServerMapper.getUserIDByaccount(orders.getRecharge_phone());
                float fCountMoney = 0;
                List<Income_calcul_log> calculLogList = helpTasksMapper.getInCome_calcul_log(2, orders.getRecharge_order(), recharge_uid);
                for (int j =0;j<calculLogList.size();j++){
                    Income_calcul_log incomeCalculLog = calculLogList.get(j);
                    fCountMoney = fCountMoney+incomeCalculLog.getMoney_num();
                }
                // 抽成 计算，计算后，添加到管理员账户
                double desc = orders.getMoney_num() * getRuleInfo.getDynamic_deduct_proportion();
                int nZAdmin = helpTasksMapper.getUserMember_Admin(2);
                if(nZAdmin>0){ //添加到主管理员账户
                    List<User_MemberInfo> list = helpTasksMapper.getUserMember_Admin_list(2);
                    if(list.size()>0){
                        User_MemberInfo userMemberInfo = list.get(0);
                        helpTasksMapper.updateUserstatic_Add(userMemberInfo.getUser_id(),(float)desc);
                        log.info("添加到主管理员账户"+recharge_uid +"useradmin: "+userMemberInfo.getUser_id()+"money:"+desc);
                    }
                }else{
                    int nCAdmin = helpTasksMapper.getUserMember_Admin(1);
                    List<User_MemberInfo> list = helpTasksMapper.getUserMember_Admin_list(1);
                    if(list.size()>0){
                        User_MemberInfo userMemberInfo = list.get(0);
                        helpTasksMapper.updateUserstatic_Add(userMemberInfo.getUser_id(),(float)desc);
                        log.info("添加到管理员账户"+recharge_uid +"useradmin: "+userMemberInfo.getUser_id()+"money:"+desc);
                    }
                }
             //冻结钱包+利息 =
                log.info("冻结期：updateUserFrozen_task:"+recharge_uid +"getMoney_num: "+orders.getMoney_num());
                helpTasksMapper.updateUserFrozen_task(recharge_uid,orders.getMoney_num());
                log.info("冻结期：updateUserstatic_Add_uid:"+recharge_uid +"getMoney_num: "+(orders.getMoney_num()+fCountMoney-(float) desc));
                helpTasksMapper.updateUserstatic_Add(recharge_uid,(orders.getMoney_num()+fCountMoney-(float) desc));
                float ufrozen = helpTasksMapper.sumFrozen_Money(orders.getRecharge_uid());
                User_MemberInfo userMemberInfo = appServerMapper.getUserInfo(orders.getRecharge_uid());
                if(ufrozen!= userMemberInfo.getUfrozen_wallet()){
                    log.info("冻结期ufrozen："+ufrozen +"User_MemberInfo: "+userMemberInfo.getUfrozen_wallet());
                }
            }
        }
///////////////////////////匹配后超期未打款///////////////////////////////////////////////
        nCurrentTimer = System.currentTimeMillis();
        // 打款方冻结帐户，单子状态超期。冻结钱包扣除对应单子的金额
        List<Orders> InfoList = helpTasksMapper.getOrderInfoList(3);
        for (int i = 0; i < InfoList.size(); i++) {
            Orders orders = InfoList.get(i);
            long match_date = orders.getMatch_date();
            long nbetwon = nCurrentTimer-match_date;
            if(nbetwon>(3600000*getRuleInfo.getApply_num_term())) { //超期未打款
                helpTasksMapper.updateOrderStatus_task(8,nCurrentTimer,orders.getOrder_num());
                appServerMapper.updateOfferHelp(8,nCurrentTimer,orders.getRecharge_order());
                log.info("匹配超期未打款："+orders.getRecharge_uid() +"getMoney_num: "+orders.getMoney_num());
                helpTasksMapper.updateUserFrozen_task(orders.getRecharge_uid(),orders.getMoney_num());
                helpTasksMapper.deleteInCome_log(orders.getRecharge_order());
                helpTasksMapper.updateUserActivate(orders.getRecharge_uid());
                appServerMapper.updateOfferHelp(1,nCurrentTimer,orders.getWithdrawals_order());
                float ufrozen = helpTasksMapper.sumFrozen_Money(orders.getRecharge_uid());
                User_MemberInfo userMemberInfo = appServerMapper.getUserInfo(orders.getRecharge_uid());
                if(ufrozen!= userMemberInfo.getUfrozen_wallet()){
                    log.info("匹配超期未打款ufrozen："+ufrozen +"User_MemberInfo: "+userMemberInfo.getUfrozen_wallet());
                }

            }
        }
        //已打款但超期未确认收款，冻结双方账号。扣除打款方冻结钱包对应金额，收款方已经扣过静态钱包了。
        /////////////////////////////////打款后未确认////////////////////////////////////////////////////////
        List<Orders> InfoListorder = helpTasksMapper.getOrderInfoList(6);
        for (int i = 0; i < InfoListorder.size(); i++) {
            Orders orders = InfoListorder.get(i);
            long pay_date = orders.getPayment_date();
            long nbetwon = nCurrentTimer-pay_date;
            if(nbetwon>(3600000*getRuleInfo.getAsk_num_term())) { //确认未打款
                helpTasksMapper.updateUserActivate(orders.getRecharge_uid());
                helpTasksMapper.updateUserActivate(orders.getWithdrawals_uid());
                helpTasksMapper.updateOrderStatus_task(8,nCurrentTimer,orders.getOrder_num());
                appServerMapper.updateOfferHelp(8,nCurrentTimer,orders.getRecharge_order());
                log.info("打款超期确认："+orders.getRecharge_uid() +"getMoney_num: "+orders.getMoney_num());
                helpTasksMapper.updateUserFrozen_task(orders.getRecharge_uid(),orders.getMoney_num());
                helpTasksMapper.deleteInCome_log(orders.getRecharge_order());
                appServerMapper.updateOfferHelp(8,nCurrentTimer,orders.getWithdrawals_order());

                float ufrozen = helpTasksMapper.sumFrozen_Money(orders.getRecharge_uid());
                User_MemberInfo userMemberInfo = appServerMapper.getUserInfo(orders.getRecharge_uid());
                if(ufrozen!= userMemberInfo.getUfrozen_wallet()){
                    log.info("打款超期确认ufrozen："+ufrozen +"User_MemberInfo: "+userMemberInfo.getUfrozen_wallet());
                }

            }
        }
    }


}


