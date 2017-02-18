package com.help.server.schedu;

import com.help.server.controller.appcontroller.AppServerController;
import com.help.server.domain.HelpTasksMapper;
import com.help.server.domain.tables.Dynamic_Award;
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

    private long getUserTileID(int nsize){
        long title_id =1;
        List<Dynamic_Award> dynamicAwardList = helpTasksMapper.findDynmicRules();
        for (int i=0;i<dynamicAwardList.size();i++){
            Dynamic_Award dynamicAward = dynamicAwardList.get(i);
            if(nsize>dynamicAward.getTeam_num()){
                title_id = dynamicAward.getUser_title_id();
                break;
            }
        }
        return title_id;
    }

    @Scheduled(cron="0 0 8-10 * * *")
    public void Userlevel_Cal() {

        long nMemberCount = helpTasksMapper.getUserMemberCount();
        List<User_MemberInfo> list = helpTasksMapper.getUserMemberInfoList(1);
        for (int i =0;i<list.size();i++){
            User_MemberInfo user_memberInfo = list.get(i);
            List<User_MemberInfo> userMemberlist = helpTasksMapper.getUserMemberInfo(user_memberInfo.getUser_phone());
            int nsize = userMemberlist.size()-1;
            log.info("nsizexxx" +nsize);
            int title_id = (int)getUserTileID(nsize);
            if(title_id > user_memberInfo.getTitle_id()){
                helpTasksMapper.updateUserTitleId(title_id,user_memberInfo.getUser_id());
                log.info("title_id:"+title_id+ "user_id:" +user_memberInfo.getUser_id());
            }
        }
    }
}
