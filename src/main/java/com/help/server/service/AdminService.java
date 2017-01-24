package com.help.server.service;

import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.tables.OrderSetting;
import com.help.server.util.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author houlongbin
 */
@Service
public class AdminService {
    private Logger log = Logger.getLogger(AdminService.class);

    /**
     * 获取分页查询sql
     * @param param
     * @return
     * @throws Exception
     */
    public String getSearchLeaveMessageSql(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_BASE_LEAVING_MSG);
        int status = Integer.valueOf(param.getString("status"));
        //{"et":"","page":"1","st":"","status":"0"}
        if (!StringUtil.isEmpty(param.getString("st"))) {
            sqlBuffer.append(" and create_date>")
                    .append(DateUtil.getLongdate(param.getString("st") + CommonConstant.START_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("et"))) {
            sqlBuffer.append(" and create_date<=")
                    .append(DateUtil.getLongdate(param.getString("et") + CommonConstant.END_DAY));
        }
        if (status >= 0) {
            sqlBuffer.append(" and is_reply=")
                    .append(status);
        }
        log.info("getSearchLeaveMessageSql sql=" + sqlBuffer.toString());
        return sqlBuffer.toString();
    }

    /**
     * 获取分页信息
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject getLeaveMsg(JSONObject param) throws Exception {
        String sql = getSearchLeaveMessageSql(param);
        int cpage = Integer.valueOf(param.getString("page"));
        return JdbcUtils.getInstatance().getPageBySql(sql, cpage);
    }

    /**
     * 修改订单设置
     *
     * @param param
     * @return
     * @throws Exception
     */
    public boolean updateOrderRolue(JSONObject param) throws Exception {
        //{"create_date":"2017-01-12 11:33:27","dynamic_deduct_proportion":"0.0",
        // "dynamic_max_money":"30000","dynamic_min_money":"500",
        // "dynamic_times_money":"100","end_date":"2017-01-12 11:33:27","
        // freezing_time":"168","id":"1","interest_not_paid":"0.01","interest_paid":"0.01","is_order_timer":"0",
        // "match_date_max":"7","match_date_min":"3","max_order_num":"2",
        // "min_order_amount":"100","order_max_money":"100000.0","start_date":"2017-01-12 11:33:27",
        // "static_min_money":"500","static_times_money":"100"}
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE order_Setting SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000)");
        if(!StringUtil.isEmpty(param.getString("start_date"))){
            sqlBuffer.append(",start_date=").append(DateUtil.getDateFormatter2().parse(param.getString("start_date")).getTime());
        }
        if(!StringUtil.isEmpty(param.getString("end_date"))){
            sqlBuffer.append(",end_date=").append(DateUtil.getDateFormatter2().parse(param.getString("end_date")).getTime());
        }
        sqlBuffer.append(",is_order_timer=").append(param.getString("is_order_timer"));
        if(!StringUtil.isEmpty(param.getString("dynamic_deduct_proportion"))){
            sqlBuffer.append(",dynamic_deduct_proportion=").append(param.getString("dynamic_deduct_proportion"));
        }
        if(!StringUtil.isEmpty(param.getString("dynamic_max_money"))){
            sqlBuffer.append(",dynamic_max_money=").append(param.getString("dynamic_max_money"));
        }
        if(!StringUtil.isEmpty(param.getString("dynamic_min_money"))){
            sqlBuffer.append(",dynamic_min_money=").append(param.getString("dynamic_min_money"));
        }
        if(!StringUtil.isEmpty(param.getString("dynamic_times_money"))){
            sqlBuffer.append(",dynamic_times_money=").append(param.getString("dynamic_times_money"));
        }
        if(!StringUtil.isEmpty(param.getString("freezing_time"))){
            sqlBuffer.append(",freezing_time=").append(param.getString("freezing_time"));
        }
        if(!StringUtil.isEmpty(param.getString("interest_not_paid"))){
            sqlBuffer.append(",interest_not_paid=").append(param.getString("interest_not_paid"));
        }
        if(!StringUtil.isEmpty(param.getString("interest_paid"))){
            sqlBuffer.append(",interest_paid=").append(param.getString("interest_paid"));
        }
        if(!StringUtil.isEmpty(param.getString("match_date_max"))){
            sqlBuffer.append(",match_date_max=").append(param.getString("match_date_max"));
        }
        if(!StringUtil.isEmpty(param.getString("match_date_min"))){
            sqlBuffer.append(",match_date_min=").append(param.getString("match_date_min"));
        }
        if(!StringUtil.isEmpty(param.getString("max_order_num"))){
            sqlBuffer.append(",max_order_num=").append(param.getString("max_order_num"));
        }
        if(!StringUtil.isEmpty(param.getString("min_order_amount"))){
            sqlBuffer.append(",min_order_amount=").append(param.getString("min_order_amount"));
        }
        if(!StringUtil.isEmpty(param.getString("order_max_money"))){
            sqlBuffer.append(",order_max_money=").append(param.getString("order_max_money"));
        }
        if(!StringUtil.isEmpty(param.getString("static_min_money"))){
            sqlBuffer.append(",static_min_money=").append(param.getString("static_min_money"));
        }
        if(!StringUtil.isEmpty(param.getString("static_times_money"))){
            sqlBuffer.append(",static_times_money=").append(param.getString("static_times_money"));
        }

        sqlBuffer.append(" WHERE id=").append(param.getString("id"));
        return JdbcUtils.getInstatance().updateByPreparedStatement(sqlBuffer.toString(), null);
    }


}
