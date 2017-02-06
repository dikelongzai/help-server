package com.help.server.service;

import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.tables.OrderSetting;
import com.help.server.util.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.help.server.util.SqlConstant.COMMON_SELECT_ALL;

/**
 * @author houlongbin
 */
@Service
public class AdminService {
    private Logger log = Logger.getLogger(AdminService.class);

    /**
     * 获取分页查询sql
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String getSearchLeaveMessageSql(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_BASE_LEAVING_MSG);
        int status = Integer.valueOf(param.getString("status"));
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
     *
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
     * 获取激活码分页查询sql
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String getSearchCodeSql(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_BASE_CODE_MSG);
        if (!StringUtil.isEmpty(param.getString("user_name"))) {
            sqlBuffer.append(" and user_name like '%")
                    .append(param.getString("user_name").trim()).append("%'");
        }
        if (!StringUtil.isEmpty(param.getString("user_phone"))) {
            sqlBuffer.append(" and user_phone like '%")
                    .append(param.getString("user_phone").trim()).append("%'");
        }

        log.info("getSearchLeaveMessageSql sql=" + sqlBuffer.toString());
        return sqlBuffer.toString();
    }

    /**
     * 获取用户分页查询sql
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String getSearchUserSql(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_BASE_USER_MSG);
        if (!StringUtil.isEmpty(param.getString("user_name"))) {
            sqlBuffer.append(" and u.user_name like '%")
                    .append(param.getString("user_name").trim()).append("%'");
        }
        if (!StringUtil.isEmpty(param.getString("user_phone"))) {
            sqlBuffer.append(" and u.user_phone like '%")
                    .append(param.getString("user_phone").trim()).append("%'");
        }
        if (!StringUtil.isEmpty(param.getString("user_referee_phone"))) {
            sqlBuffer.append(" and u.user_referee_phone like '%")
                    .append(param.getString("user_referee_phone").trim()).append("%'");
        }
        if (!StringUtil.isEmpty(param.getString("title_id")) && !COMMON_SELECT_ALL.equals(param.getString("title_id"))) {
            sqlBuffer.append(" and u.title_id="
            )
                    .append(param.getString("title_id"));
        }
        if (!StringUtil.isEmpty(param.getString("is_activate")) && !COMMON_SELECT_ALL.equals(param.getString("is_activate"))) {
            sqlBuffer.append(" and u.is_activate="
            )
                    .append(param.getString("is_activate"));
        }
        log.info("getSearchUserSql sql=" + sqlBuffer.toString());
        return sqlBuffer.toString();
    }

    /**
     * 获取激活码分页信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject getPageCode(JSONObject param) throws Exception {
        String sql = getSearchCodeSql(param);
        int cpage = Integer.valueOf(param.getString("page"));
        return JdbcUtils.getInstatance().getPageBySql(sql, cpage);
    }

    /**
     * 获取用户分页信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject getPageUser(JSONObject param) throws Exception {
        String sql = getSearchUserSql(param);
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
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE order_Setting SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000)");
        if (!StringUtil.isEmpty(param.getString("start_date"))) {
            sqlBuffer.append(",start_date=").append(DateUtil.getDateFormatter2().parse(param.getString("start_date")).getTime());
        }
        if (!StringUtil.isEmpty(param.getString("end_date"))) {
            sqlBuffer.append(",end_date=").append(DateUtil.getDateFormatter2().parse(param.getString("end_date")).getTime());
        }
        sqlBuffer.append(",is_order_timer=").append(param.getString("is_order_timer"));
        if (!StringUtil.isEmpty(param.getString("dynamic_deduct_proportion"))) {
            sqlBuffer.append(",dynamic_deduct_proportion=").append(param.getString("dynamic_deduct_proportion"));
        }
        if (!StringUtil.isEmpty(param.getString("dynamic_max_money"))) {
            sqlBuffer.append(",dynamic_max_money=").append(param.getString("dynamic_max_money"));
        }
        if (!StringUtil.isEmpty(param.getString("dynamic_min_money"))) {
            sqlBuffer.append(",dynamic_min_money=").append(param.getString("dynamic_min_money"));
        }
        if (!StringUtil.isEmpty(param.getString("dynamic_times_money"))) {
            sqlBuffer.append(",dynamic_times_money=").append(param.getString("dynamic_times_money"));
        }
        if (!StringUtil.isEmpty(param.getString("freezing_time"))) {
            sqlBuffer.append(",freezing_time=").append(param.getString("freezing_time"));
        }
        if (!StringUtil.isEmpty(param.getString("interest_not_paid"))) {
            sqlBuffer.append(",interest_not_paid=").append(param.getString("interest_not_paid"));
        }
        if (!StringUtil.isEmpty(param.getString("interest_paid"))) {
            sqlBuffer.append(",interest_paid=").append(param.getString("interest_paid"));
        }
        if (!StringUtil.isEmpty(param.getString("match_date_max"))) {
            sqlBuffer.append(",match_date_max=").append(param.getString("match_date_max"));
        }
        if (!StringUtil.isEmpty(param.getString("match_date_min"))) {
            sqlBuffer.append(",match_date_min=").append(param.getString("match_date_min"));
        }
        if (!StringUtil.isEmpty(param.getString("max_order_num"))) {
            sqlBuffer.append(",max_order_num=").append(param.getString("max_order_num"));
        }
        if (!StringUtil.isEmpty(param.getString("min_order_amount"))) {
            sqlBuffer.append(",min_order_amount=").append(param.getString("min_order_amount"));
        }
        if (!StringUtil.isEmpty(param.getString("order_max_money"))) {
            sqlBuffer.append(",order_max_money=").append(param.getString("order_max_money"));
        }
        if (!StringUtil.isEmpty(param.getString("static_min_money"))) {
            sqlBuffer.append(",static_min_money=").append(param.getString("static_min_money"));
        }
        if (!StringUtil.isEmpty(param.getString("static_times_money"))) {
            sqlBuffer.append(",static_times_money=").append(param.getString("static_times_money"));
        }

        sqlBuffer.append(" WHERE id=").append(param.getString("id"));
        return JdbcUtils.getInstatance().updateByPreparedStatement(sqlBuffer.toString(), null);
    }

    /**
     * @param param
     * @return
     * @throws Exception
     */
    public boolean updateAward(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE dynamic_award_rules SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000)");
        //{"direct_num":1,"four_generation":0.1,"id":7,"one_generation":0.2,"team_num":1,"three_generation":0.3,"two_generation":0.2,"user_title":"教授"}
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if ("user_title".equals(entry.getKey())) {
                sqlBuffer.append(",").append(entry.getKey()).append("='").append(entry.getValue().toString()).append("'");
            } else {
                if (!entry.getKey().equals("id")) {
                    sqlBuffer.append(",").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
            //System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        sqlBuffer.append(" WHERE id=").append(param.getString("id"));
        log.info("updateAward sql=" + sqlBuffer.toString());
        return JdbcUtils.getInstatance().updateByPreparedStatement(sqlBuffer.toString(), null);
    }


}
