package com.help.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.tables.Offer_Help;
import com.help.server.domain.tables.OrderSetting;
import com.help.server.domain.tables.User_MemberInfo;
import com.help.server.util.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static com.help.server.util.SqlConstant.COMMON_SELECT_ALL;

/**
 * @author houlongbin
 */
@Service
public class AdminService {
    private Logger log = Logger.getLogger(AdminService.class);
    @Autowired
    private AppServerMapper appServerMappe;

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
     * 获取匹配列表
     *
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject getMatchList(JSONObject param) throws Exception {
        JSONObject jsonReturn=new JSONObject();
        String et=DateUtil.addDate(-3);
        String st=DateUtil.addDate(-10);
        if(!StringUtil.isEmpty(param.getString("st"))){
            st=param.getString("st").trim().substring(0, 10);
        }
        if(!StringUtil.isEmpty(param.getString("et"))){
            et=param.getString("et").trim().substring(0, 10);
        }
        String sqlSell="select \n" +
                "  date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) as day_time ,wallet_type,sum(money_num) as sum_money\n" +
                "from\n" +
                "  offer_help \n" +
                "where date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) >= '"+st+"' \n" +
                "  and DATE_FORMAT(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) <= '"+et+"' \n" +
                "  AND help_status=1 and help_type=2\n" +
                "  group by date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ),wallet_type\n" +
                "order by DATE_FORMAT(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) asc ";
        // 每日提现 按照钱包类型聚合查询总数
        List<Map<String, Object>> listSell=JdbcUtils.getInstatance().findModeResult(sqlSell,null);
        log.info("getMatchList sell sql=" +sqlSell+"result="+ JSON.toJSONString(listSell));
        String sqlBuy="  select \n" +
                "  date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) as day_time ,is_income,sum(money_num) as sum_money\n" +
                "from\n" +
                "  offer_help \n" +
                "where date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) >= '"+st+"' \n" +
                "  and DATE_FORMAT(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) <= '"+et+"' \n" +
                "  AND help_status=1 and help_type=1\n" +
                "  group by date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ),is_income\n" +
                "order by DATE_FORMAT(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) asc ";
        // -- 把买入的按照时间及是否是复投的聚合 1不是复投 0复投
        List<Map<String, Object>> listBuy=JdbcUtils.getInstatance().findModeResult(sqlBuy,null);
        log.info("getMatchList buy sql=" +sqlBuy+"result="+ JSON.toJSONString(listBuy));
//        jsonReturn.put("data", this.findModeResult(sqlPage, null));
        List<String> dateBet=DateUtil.getBetweenDates(st,et);
        JSONArray jsonArray=new JSONArray();
        for(String str:dateBet){
            JSONObject json=new JSONObject();
            float sell_1=0;
            float sell_2=0;
            float buy_1=0;
            float buy_2=0;
            for(Map<String, Object> mapSell:listSell){
                if(mapSell.get("day_time").toString().equals(str)){
                        if(mapSell.containsKey("wallet_type")){
                            //静态钱包提现
                            if(mapSell.get("wallet_type").toString().equals("1")){
                                sell_1=Float.valueOf(mapSell.get("sum_money").toString());
                            }
                            //动态钱包提现
                            if(mapSell.get("wallet_type").toString().equals("2")){
                                sell_2=Float.valueOf(mapSell.get("sum_money").toString());
                            }
                        }
                }

            }
            for(Map<String, Object> mapBuy:listBuy){
                if(mapBuy.get("day_time").toString().equals(str)){
                    //不是复投即正常买入单
                    if(mapBuy.get("is_income").toString().equals("1")){
                        buy_1=Float.valueOf(mapBuy.get("sum_money").toString());
                    }
                    //复投买入单
                    if(mapBuy.get("is_income").toString().equals("0")){
                        buy_2=Float.valueOf(mapBuy.get("sum_money").toString());
                    }
                }
            }
            json.put("day",str);
            json.put("sell_1",sell_1);
            json.put("sell_2",sell_2);
            json.put("buy_1",buy_1);
            json.put("buy_2",buy_2);
            jsonArray.add(json);

        }

        jsonReturn.put("data", jsonArray);
        log.info("getMatchList jsonReturn=" +jsonReturn.toJSONString());
        return jsonReturn;
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

    /**
     * 添加订单
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject doAddOfferHelp(JSONObject param)throws Exception {
        //帮助类型 1申请帮助（出钱）,2请求帮助（提现）
        ResultStatusCode rs=new ResultStatusCode();
        int help_type=Integer.valueOf(param.getString("help_type"));
        int wallet_type=Integer.valueOf(param.getString("wallet_type"));
        long uid=Long.parseLong(param.getString("user_id"));
        long create_date=DateUtil.getLongdate(param.getString("create_date"));
        int money_num=Integer.valueOf(param.getString("money_num"));
        // param={"create_date":"2017-02-06 11:05:01","help_type":"0","money_num":"10000","user_id":"3","user_phone":"13759889278","wallet_type":"1"}
        if(help_type==2) { //请求帮助
            User_MemberInfo userMemberInfo = appServerMappe.getUserInfo(uid);
            if (wallet_type == 2) {//动态钱包
                if (userMemberInfo.getUdynamic_wallet() < money_num) {
                    rs.setMessage("余额不足，不能发单！");
                    rs.setCode("C0015");
                    return rs.toJson();
                }
            }
            if (wallet_type == 1) {
                if (userMemberInfo.getUstatic_wallet() < money_num) {
                    rs.setMessage("余额不足，不能发单！");
                    rs.setCode("C0015");
                    return rs.toJson();
                }
            }
        }
        Offer_Help offer_helps = new Offer_Help();
        offer_helps.setCreate_date(create_date);
        offer_helps.setLast_update(create_date);
        //提供帮助 100 天加到冻结钱包
        //请求帮助 100 相应钱包减100，其中动态钱包时同时提供帮助生成订单同样金额订单。
        long help_id = 0;
        String idname = "help_id";
        appServerMappe.id_generator(idname);
        help_id = appServerMappe.get_id_generator(idname);
        offer_helps.setHelp_id(help_id);
        String ordernum = CommonUtil.genRandomOrder(help_id+"");
        offer_helps.setHelp_order(ordernum);
        offer_helps.setMoney_num(money_num);
        offer_helps.setHelp_status(1);
        offer_helps.setPayment_type("0,1,2");
        offer_helps.setUser_id(uid);
        offer_helps.setUser_phone(param.getString("user_phone"));
        offer_helps.setWallet_type(wallet_type);
        offer_helps.setState('N');
        offer_helps.setStatus_confirmation(0);
        offer_helps.setHelp_type(help_type);
        offer_helps.setIs_income(1);
        offer_helps.setIs_admin(1);
        appServerMappe.OfferHelp(offer_helps);
        log.info("doAddOfferHelp addOfferHelp success");
        if(help_type == 1){ //提供帮助
            appServerMappe.updateUserFrozen(uid,money_num);
            log.info("doAddOfferHelp updateUserFrozen success");
        }
        if(help_type ==2){ //请求帮助
            User_MemberInfo userMemberInfo = appServerMappe.getUserInfo(uid);
            //动态钱包
            if(wallet_type==2){
                appServerMappe.updateUserdynamic(uid,money_num);
                log.info("doAddOfferHelp updateUserFrozen success");
                //提供帮助
                offer_helps.setCreate_date(create_date);
                offer_helps.setLast_update(create_date);
                appServerMappe.id_generator(idname);
                help_id = appServerMappe.get_id_generator(idname);
                offer_helps.setHelp_id(help_id);
                ordernum = CommonUtil.genRandomOrder(help_id+"");
                offer_helps.setHelp_order(ordernum);
                offer_helps.setMoney_num(money_num);
                offer_helps.setHelp_status(1);
                offer_helps.setPayment_type("0,1,2");
                offer_helps.setUser_id(uid);
                offer_helps.setUser_phone(param.getString("user_phone"));
                offer_helps.setWallet_type(0);
                offer_helps.setState('N');
                offer_helps.setStatus_confirmation(0);
                offer_helps.setHelp_type(1);
                offer_helps.setIs_income(0);
                offer_helps.setIs_admin(1);
                appServerMappe.OfferHelp(offer_helps);
                appServerMappe.updateUserFrozen(uid,money_num);
                log.info("doAddOfferHelp  复投 success");
            }else{
                //从静态钱包提现，只减就可以，不到冻结里面去。
                appServerMappe.updateUserstatic(uid,money_num);
                //appServerMappe.updateUserFrozen(uid,money_num);
                log.info("doAddOfferHelp  updateUserdynamic success");
            }
        }


        return ResultStatusCode.OK.toJson();

    }


}
