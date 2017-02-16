package com.help.server.schedu;

import com.help.server.controller.appcontroller.AppServerController;
import com.help.server.domain.HelpTasksMapper;
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

    @Scheduled(cron="*/10 * * * * *")
    public void Userlevel_Cal() {

        //System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
