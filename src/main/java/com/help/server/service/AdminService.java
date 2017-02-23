package com.help.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.tables.Offer_Help;
import com.help.server.domain.tables.OrderSetting;
import com.help.server.domain.tables.Orders;
import com.help.server.domain.tables.User_MemberInfo;
import com.help.server.util.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.help.server.util.SqlConstant.COMMON_SELECT_ALL;

/**
 * @author houlongbin
 */
@Service
public class AdminService {
    public static final Map<String, JSONObject> mapCuMathList = new ConcurrentHashMap();
    public static final Map<String, List<Orders>> hasMatchList = new ConcurrentHashMap();

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
    public String getSearchLeaveMessageSql(JSONObject param, int reply_type) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_BASE_LEAVING_MSG);
        sqlBuffer.append(" and reply_type=").append(reply_type).append(" ");
        //SQL_BASE_LEAVING_MSG
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
    public JSONObject getLeaveMsg(JSONObject param, int reply_type) throws Exception {
        String sql = getSearchLeaveMessageSql(param, reply_type);
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
     * 获取用户发单分页查询sql
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String getSearchOfferrSql(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_GET_OFFER_PAGE);
        if (!StringUtil.isEmpty(param.getString("user_phone"))) {
            sqlBuffer.append(" and user_phone like '%")
                    .append(param.getString("user_phone").trim()).append("%'");
        }
        if (!StringUtil.isEmpty(param.getString("st"))) {
            sqlBuffer.append(" and create_date>")
                    .append(DateUtil.getLongdate(param.getString("st") + CommonConstant.START_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("et"))) {
            sqlBuffer.append(" and create_date<=")
                    .append(DateUtil.getLongdate(param.getString("et") + CommonConstant.END_DAY));
        }
        //param={"et":"","help_status":"-1","help_type":"-1","is_admin":"-1","is_income":"-1","is_split":"-1","page":"1","st":"","user_phone":"","wallet_type":"-1"};result=
        if (!StringUtil.isEmpty(param.getString("help_status")) && !COMMON_SELECT_ALL.equals(param.getString("help_status"))) {
            sqlBuffer.append(" and help_status=").append(param.getString("help_status"));
        }
        if (!StringUtil.isEmpty(param.getString("is_admin")) && !COMMON_SELECT_ALL.equals(param.getString("is_admin"))) {
            sqlBuffer.append(" and is_admin=").append(param.getString("is_admin"));
        }
        if (!StringUtil.isEmpty(param.getString("is_income")) && !COMMON_SELECT_ALL.equals(param.getString("is_income"))) {
            sqlBuffer.append(" and is_income=").append(param.getString("is_income"));
        }
        if (!StringUtil.isEmpty(param.getString("is_split")) && !COMMON_SELECT_ALL.equals(param.getString("is_split"))) {
            sqlBuffer.append(" and is_split=").append(param.getString("is_split"));
        }
        if (!StringUtil.isEmpty(param.getString("help_type")) && !COMMON_SELECT_ALL.equals(param.getString("help_type"))) {
            sqlBuffer.append(" and help_type=").append(param.getString("help_type"));
        }
        if (!StringUtil.isEmpty(param.getString("wallet_type")) && !COMMON_SELECT_ALL.equals(param.getString("wallet_type"))) {
            sqlBuffer.append(" and wallet_type=").append(param.getString("wallet_type"));
        }
        sqlBuffer.append(SqlConstant.SQL_COMMON_DESC);
        log.info("getSearchOfferrSql sql=" + sqlBuffer.toString());
        return sqlBuffer.toString();
    }

    /**
     * 获取用户发单分页查询sql
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String getSearchOrderSql(JSONObject param) throws Exception {
        //{"cet":"2017-02-16","complaint_status":"1","cst":"2017-01-31","met":"2017-02-16","mst":"2017-02-02","order_type":"5","page":"1","pet":"2017-02-14","pst":"2017-02-08","withdrawals_phone":"153"};result={"data":[{"create_date":1487390599000,"help_order":"P5472fibb3i17j0fe486","help_status":1,"help_type":2,"is_admin":1,"is_income":1,"is_split":0,"money_num":1100,"user_id":7,"user_phone":"17709211685","wallet_type":1},{"create_date":1487390262849,"help_order":"P4930bjbji494c0b8a7f","help_status":1,"help_type":2,"is_admin":0,"is_income":1,"is_split":0,"money_num":600,"user_id":7,"user_phone":"17709211685","wallet_type":1},{"create_date":1487389771360,"help_order":"P482i4cig1181b01e7dg","help_status":1,"help_type":2,"is_admin":0,"is_income":1,"is_split":0,"money_num":600,"user_id":7,"user_phone":"17709211685","wallet_type":1},{"create_date":1487389752907,"help_order":"P468bg4h2g21b60h0720","help_status":1,"help_type":1,"is_admin":0,"is_income":0,"is_split":0,"money_num":1000,"user_id":7,"user_phone":"17709211685","wallet_type":0},{"create_date":1487389752894,"help_order":"P466cb61e6jaiea4393f","help_status":1,"help_type":2,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":7,"user_phone":"17709211685","wallet_type":2},{"create_date":1487385082685,"help_order":"P311f2808b21dj95g7hf","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":24,"user_phone":"15389290468","wallet_type":1},{"create_date":1487384911235,"help_order":"P3101j377j18671f6gh2","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":24,"user_phone":"15389290468","wallet_type":1},{"create_date":1487384899174,"help_order":"P309184b5ihd313b7gbj","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":10000,"user_id":24,"user_phone":"15389290468","wallet_type":1},{"create_date":1487379752057,"help_order":"P22696hf347c9d9b35h5","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":5,"user_phone":"15389290468","wallet_type":1},{"create_date":1487347842474,"help_order":"P1898851hf681426eijg","help_status":1,"help_type":1,"is_admin":0,"is_income":0,"is_split":0,"money_num":1000,"user_id":7,"user_phone":"17709211685","wallet_type":0}],"page":{"beginIndex":0,"count":1,"endIndex":10,"pageSize":10,"total":57,"totalRow":567}}
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_GET_ORDER_PAGE);
        if (!StringUtil.isEmpty(param.getString("recharge_phone"))) {
            sqlBuffer.append(" and recharge_phone like '%")
                    .append(param.getString("recharge_phone").trim()).append("%'");
        }
        if (!StringUtil.isEmpty(param.getString("withdrawals_phone"))) {
            sqlBuffer.append(" and withdrawals_phone like '%")
                    .append(param.getString("withdrawals_phone").trim()).append("%'");
        }
        if (!StringUtil.isEmpty(param.getString("mst"))) {
            sqlBuffer.append(" and match_date>")
                    .append(DateUtil.getLongdate(param.getString("mst") + CommonConstant.START_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("met"))) {
            sqlBuffer.append(" and match_date<=")
                    .append(DateUtil.getLongdate(param.getString("met") + CommonConstant.END_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("cst"))) {
            sqlBuffer.append(" and confirm_date>")
                    .append(DateUtil.getLongdate(param.getString("cst") + CommonConstant.START_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("cet"))) {
            sqlBuffer.append(" and confirm_date<=")
                    .append(DateUtil.getLongdate(param.getString("cet") + CommonConstant.END_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("pst"))) {
            sqlBuffer.append(" and payment_date>")
                    .append(DateUtil.getLongdate(param.getString("pst") + CommonConstant.START_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("pet"))) {
            sqlBuffer.append(" and payment_date<=")
                    .append(DateUtil.getLongdate(param.getString("pet") + CommonConstant.END_DAY));
        }
        //param={"et":"","help_status":"-1","help_type":"-1","is_admin":"-1","is_income":"-1","is_split":"-1","page":"1","st":"","user_phone":"","wallet_type":"-1"};result=
        if (!StringUtil.isEmpty(param.getString("complaint_status")) && !COMMON_SELECT_ALL.equals(param.getString("complaint_status"))) {
            sqlBuffer.append(" and complaint_status=").append(param.getString("complaint_status"));
        }
        if (!StringUtil.isEmpty(param.getString("order_type")) && !COMMON_SELECT_ALL.equals(param.getString("order_type"))) {
            sqlBuffer.append(" and order_type=").append(param.getString("order_type"));
        }
        sqlBuffer.append(SqlConstant.SQL_COMMON_DESC);
        log.info("getSearchOrderSql sql=" + sqlBuffer.toString());
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
     * 获取用户发单
     *
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject getPageOffer(JSONObject param) throws Exception {
        String sql = getSearchOfferrSql(param);
        int cpage = Integer.valueOf(param.getString("page"));
        return JdbcUtils.getInstatance().getPageBySql(sql, cpage);
    }

    /**
     * 获取订单
     *
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject getOrderOffer(JSONObject param) throws Exception {
        //{"cet":"2017-02-16","complaint_status":"1","cst":"2017-01-31","met":"2017-02-16","mst":"2017-02-02","order_type":"5","page":"1","pet":"2017-02-14","pst":"2017-02-08","withdrawals_phone":"153"};result={"data":[{"create_date":1487390599000,"help_order":"P5472fibb3i17j0fe486","help_status":1,"help_type":2,"is_admin":1,"is_income":1,"is_split":0,"money_num":1100,"user_id":7,"user_phone":"17709211685","wallet_type":1},{"create_date":1487390262849,"help_order":"P4930bjbji494c0b8a7f","help_status":1,"help_type":2,"is_admin":0,"is_income":1,"is_split":0,"money_num":600,"user_id":7,"user_phone":"17709211685","wallet_type":1},{"create_date":1487389771360,"help_order":"P482i4cig1181b01e7dg","help_status":1,"help_type":2,"is_admin":0,"is_income":1,"is_split":0,"money_num":600,"user_id":7,"user_phone":"17709211685","wallet_type":1},{"create_date":1487389752907,"help_order":"P468bg4h2g21b60h0720","help_status":1,"help_type":1,"is_admin":0,"is_income":0,"is_split":0,"money_num":1000,"user_id":7,"user_phone":"17709211685","wallet_type":0},{"create_date":1487389752894,"help_order":"P466cb61e6jaiea4393f","help_status":1,"help_type":2,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":7,"user_phone":"17709211685","wallet_type":2},{"create_date":1487385082685,"help_order":"P311f2808b21dj95g7hf","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":24,"user_phone":"15389290468","wallet_type":1},{"create_date":1487384911235,"help_order":"P3101j377j18671f6gh2","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":24,"user_phone":"15389290468","wallet_type":1},{"create_date":1487384899174,"help_order":"P309184b5ihd313b7gbj","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":10000,"user_id":24,"user_phone":"15389290468","wallet_type":1},{"create_date":1487379752057,"help_order":"P22696hf347c9d9b35h5","help_status":1,"help_type":1,"is_admin":0,"is_income":1,"is_split":0,"money_num":1000,"user_id":5,"user_phone":"15389290468","wallet_type":1},{"create_date":1487347842474,"help_order":"P1898851hf681426eijg","help_status":1,"help_type":1,"is_admin":0,"is_income":0,"is_split":0,"money_num":1000,"user_id":7,"user_phone":"17709211685","wallet_type":0}],"page":{"beginIndex":0,"count":1,"endIndex":10,"pageSize":10,"total":57,"totalRow":567}}
        String sql = getSearchOrderSql(param);
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
        JSONObject jsonReturn = new JSONObject();
        String et = DateUtil.addDate(-3);
        String st = DateUtil.addDate(-10);
        if (!StringUtil.isEmpty(param.getString("st"))) {
            st = param.getString("st").trim().substring(0, 10);
        }
        if (!StringUtil.isEmpty(param.getString("et"))) {
            et = param.getString("et").trim().substring(0, 10);
        }
        String sqlSell = "select \n" +
                "  date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) as day_time ,wallet_type,sum(money_num) as sum_money\n" +
                "from\n" +
                "  offer_help \n" +
                "where date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) >= '" + st + "' \n" +
                "  and DATE_FORMAT(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) <= '" + et + "' \n" +
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
        List<Map<String, Object>> listSell = JdbcUtils.getInstatance().findModeResult(sqlSell, null);
        log.info("getMatchList sell sql=" + sqlSell + "result=" + JSON.toJSONString(listSell));
        String sqlBuy = "  select \n" +
                "  date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) as day_time ,is_income,sum(money_num) as sum_money\n" +
                "from\n" +
                "  offer_help \n" +
                "where date_format(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) >= '" + st + "' \n" +
                "  and DATE_FORMAT(\n" +
                "    FROM_UNIXTIME(create_date / 1000),\n" +
                "    '%Y-%m-%d'\n" +
                "  ) <= '" + et + "' \n" +
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
        List<Map<String, Object>> listBuy = JdbcUtils.getInstatance().findModeResult(sqlBuy, null);
        log.info("getMatchList buy sql=" + sqlBuy + "result=" + JSON.toJSONString(listBuy));
//        jsonReturn.put("data", this.findModeResult(sqlPage, null));
        List<String> dateBet = DateUtil.getBetweenDates(st, et);
        JSONArray jsonArray = new JSONArray();
        for (String str : dateBet) {
            JSONObject json = new JSONObject();
            float sell_1 = 0;
            float sell_2 = 0;
            float buy_1 = 0;
            float buy_2 = 0;
            for (Map<String, Object> mapSell : listSell) {
                if (mapSell.get("day_time").toString().equals(str)) {
                    if (mapSell.containsKey("wallet_type")) {
                        //静态钱包提现
                        if (mapSell.get("wallet_type").toString().equals("1")) {
                            sell_1 = Float.valueOf(mapSell.get("sum_money").toString());
                        }
                        //动态钱包提现
                        if (mapSell.get("wallet_type").toString().equals("2")) {
                            sell_2 = Float.valueOf(mapSell.get("sum_money").toString());
                        }
                    }
                }

            }
            for (Map<String, Object> mapBuy : listBuy) {
                if (mapBuy.get("day_time").toString().equals(str)) {
                    //不是复投即正常买入单
                    if (mapBuy.get("is_income").toString().equals("1")) {
                        buy_1 = Float.valueOf(mapBuy.get("sum_money").toString());
                    }
                    //复投买入单
                    if (mapBuy.get("is_income").toString().equals("0")) {
                        buy_2 = Float.valueOf(mapBuy.get("sum_money").toString());
                    }
                }
            }
            json.put("day", str);
            json.put("sell_1", sell_1);
            json.put("sell_2", sell_2);
            json.put("buy_1", buy_1);
            json.put("buy_2", buy_2);
            jsonArray.add(json);

        }

        jsonReturn.put("data", jsonArray);
        log.info("getMatchList jsonReturn=" + jsonReturn.toJSONString());
        return jsonReturn;
    }

    public static synchronized void updateMachMap(String uuid, JSONObject jsonParm) {
        mapCuMathList.put(uuid, jsonParm);
    }

    public static synchronized void initMatchList(String uuid) {
        List<Orders> ordersList = new LinkedList<Orders>();
        hasMatchList.put(uuid, ordersList);
    }

    /**
     * 添加订单时再加一次判断防止发单是同一人
     *
     * @param uuid
     * @param orders
     */
    public static synchronized void addMatchOrder(String uuid, Orders orders) {
        if (orders.getWithdrawals_uid() != orders.getRecharge_uid()) {
            List<Orders> ordersList = hasMatchList.get(uuid);
            ordersList.add(orders);
            hasMatchList.put(uuid, ordersList);

        }

    }

    public static synchronized void saveMatchOrder(String uuid) {
        //持久化完成清空
        List<Orders> ordersList = hasMatchList.get(uuid);
        hasMatchList.remove(uuid);
    }

    public static boolean isContansOffer(String uuid, String help_order) {
        boolean isContains = false;
        List<Orders> ordersList = hasMatchList.get(uuid);
        for (Orders orders : ordersList) {
            if (orders.getRecharge_order().equals(help_order) || orders.getWithdrawals_order().equals("help_order")) {
                isContains = true;
                break;
            }
        }
        return isContains;
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
        /**
         *   apply_num_first` float(20,3) DEFAULT '5000.000' COMMENT '申请帮助第一次额度限制',
         `apply_num_lown` float(20,3) DEFAULT '500.000' COMMENT '申请帮助最小金额',
         `apply_num_high` float(20,3) DEFAULT '1000.000' COMMENT '申请帮助最高金额',
         `apply_num_times` int(20) DEFAULT '100' COMMENT '申请帮助金额必须是100 的倍数',
         `apply_num_term` int(4) DEFAULT '12' COMMENT '打款期限   12   小时内',
         `ask_num_lown` float(20,3) DEFAULT '500.000' COMMENT '请求帮助的最小金额',
         `ask_num_high` float(20,3) DEFAULT '1000.000' COMMENT '请求帮助的最大金额',
         `ask_num_times` int(4) DEFAULT '100' COMMENT '请求帮助必现是100的倍数',
         `ask_num_term` int(4) DEFAULT '12' COMMENT '请求帮助重新匹配  12  小时内'-->
         */
        if (!StringUtil.isEmpty(param.getString("apply_num_first"))) {
            sqlBuffer.append(",apply_num_first=").append(param.getString("apply_num_first"));
        }
        if (!StringUtil.isEmpty(param.getString("apply_num_lown"))) {
            sqlBuffer.append(",apply_num_lown=").append(param.getString("apply_num_lown"));
        }
        if (!StringUtil.isEmpty(param.getString("apply_num_high"))) {
            sqlBuffer.append(",apply_num_high=").append(param.getString("apply_num_high"));
        }
        if (!StringUtil.isEmpty(param.getString("apply_num_times"))) {
            sqlBuffer.append(",apply_num_times=").append(param.getString("apply_num_times"));
        }
        if (!StringUtil.isEmpty(param.getString("apply_num_term"))) {
            sqlBuffer.append(",apply_num_term=").append(param.getString("apply_num_term"));
        }
        if (!StringUtil.isEmpty(param.getString("ask_num_lown"))) {
            sqlBuffer.append(",ask_num_lown=").append(param.getString("ask_num_lown"));
        }
        if (!StringUtil.isEmpty(param.getString("ask_num_high"))) {
            sqlBuffer.append(",ask_num_high=").append(param.getString("ask_num_high"));
        }
        if (!StringUtil.isEmpty(param.getString("ask_num_times"))) {
            sqlBuffer.append(",ask_num_times=").append(param.getString("ask_num_times"));
        }
        if (!StringUtil.isEmpty(param.getString("ask_num_term"))) {
            sqlBuffer.append(",ask_num_term=").append(param.getString("ask_num_term"));
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
     * 修改用户信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public boolean updateUser(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE user_member SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000)");
        //{"direct_num":1,"four_generation":0.1,"id":7,"one_generation":0.2,"team_num":1,"three_generation":0.3,"two_generation":0.2,"user_title":"教授"}
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if ("ustatic_wallet".equals(entry.getKey()) || "udynamic_wallet".equals(entry.getKey())) {
                sqlBuffer.append(",").append(entry.getKey()).append("=").append(entry.getValue());

            } else {
                if (!entry.getKey().equals("user_id")) {
                    sqlBuffer.append(",").append(entry.getKey()).append("='").append(entry.getValue().toString()).append("'");
                }
            }
            //System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        sqlBuffer.append(" WHERE user_id=").append(param.getString("user_id"));
        log.info("updateUser sql=" + sqlBuffer.toString());
        return JdbcUtils.getInstatance().updateByPreparedStatement(sqlBuffer.toString(), null);
    }

    /**
     * 添加订单
     *
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject doAddOfferHelp(JSONObject param) throws Exception {
        //帮助类型 1申请帮助（出钱）,2请求帮助（提现）
        ResultStatusCode rs = new ResultStatusCode();
        int help_type = Integer.valueOf(param.getString("help_type"));
        int wallet_type = Integer.valueOf(param.getString("wallet_type"));
        long uid = Long.parseLong(param.getString("user_id"));
        long create_date = DateUtil.getLongdate(param.getString("create_date"));
        int money_num = Integer.valueOf(param.getString("money_num"));
        // param={"create_date":"2017-02-06 11:05:01","help_type":"0","money_num":"10000","user_id":"3","user_phone":"13759889278","wallet_type":"1"}
        if (help_type == 2) { //请求帮助
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
        offer_helps.setHelp_type(help_type);
        String ordernum = "";
        if (help_type == 1) {
            ordernum = CommonUtil.genRandomOrderEX("T", help_id + "");
        } else {
            ordernum = CommonUtil.genRandomOrderEX("S", help_id + "");
        }
        // String ordernum = CommonUtil.genRandomOrder(help_id + "");
        offer_helps.setHelp_order(ordernum);
        offer_helps.setMoney_num(money_num);
        offer_helps.setHelp_status(1);
        offer_helps.setPayment_type("0,1,2");
        offer_helps.setUser_id(uid);
        offer_helps.setUser_phone(param.getString("user_phone"));
        offer_helps.setWallet_type(wallet_type);
        offer_helps.setState('N');
        offer_helps.setStatus_confirmation(0);

        offer_helps.setIs_income(1);
        offer_helps.setIs_admin(1);
        appServerMappe.OfferHelp(offer_helps);
        log.info("doAddOfferHelp addOfferHelp success");
        if (help_type == 1) { //提供帮助
            appServerMappe.updateUserFrozen(uid, money_num);
            log.info("doAddOfferHelp updateUserFrozen success");
        }
        if (help_type == 2) { //请求帮助
            User_MemberInfo userMemberInfo = appServerMappe.getUserInfo(uid);
            //动态钱包
            if (wallet_type == 2) {
                appServerMappe.updateUserdynamic(uid, money_num);
                log.info("doAddOfferHelp updateUserFrozen success");
                //提供帮助
                offer_helps.setCreate_date(create_date);
                offer_helps.setLast_update(create_date);
                appServerMappe.id_generator(idname);
                help_id = appServerMappe.get_id_generator(idname);
                offer_helps.setHelp_id(help_id);
                ordernum = CommonUtil.genRandomOrder(help_id + "");
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
                appServerMappe.updateUserFrozen(uid, money_num);
                log.info("doAddOfferHelp  复投 success");
            } else {
                //从静态钱包提现，只减就可以，不到冻结里面去。
                appServerMappe.updateUserstatic(uid, money_num);
                //appServerMappe.updateUserFrozen(uid,money_num);
                log.info("doAddOfferHelp  updateUserdynamic success");
            }
        }


        return ResultStatusCode.OK.toJson();

    }

    /**
     * 订单匹配
     *
     * @param param
     * @return
     * @throws Exception
     */
    public JSONObject doMatchOffer(JSONObject param) throws Exception {
        long start = System.currentTimeMillis();
        //{"b1":[{"day":"2017-02-04","value":"800"},{"day":"2017-02-03","value":"600"}],"b2":[{"day":"2017-02-04","value":"500"},{"day":"2017-02-03","value":"700"}],"c1":[{"day":"2017-02-04","value":"100"},{"day":"2017-02-03","value":"900"}],"c2":[{"day":"2017-02-04","value":"1000"},{"day":"2017-02-03","value":"200"}]}
        JSONObject jsonReturn = new JSONObject();
        //第一步取当天订单原数据
        JSONObject jsonParams = new JSONObject();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String type = entry.getKey();
            JSONArray jsonArrayType = (JSONArray) entry.getValue();
            JSONArray jsonArray = new JSONArray();
            for (Object object : jsonArrayType) {
                JSONObject json = (JSONObject) object;
                if (json.containsKey("day")) {
                    json.put("offer", getOfferHelpByWhereSql(getWhereSql(type, json.getString("day"))));
                }
                json.put("is_over", false);//是否完成
                json.put("leave_value", Long.valueOf(json.getString("value")));//当日按照管理员所选未匹配的金额，初始值为所选值
                jsonArray.add(json);
            }
            jsonParams.put(type, jsonArray);
        }
        String uuid = UUID.randomUUID().toString();
        log.info("first=" + jsonParams.toJSONString());
        updateMachMap(uuid, jsonParams);
        initMatchList(uuid);
        jsonReturn = doMatchStep1(uuid);
        jsonReturn.put("msg", "OK");
        log.info("last=" + JSON.toJSONString(mapCuMathList));
        mapCuMathList.remove(uuid);
        hasMatchList.remove(uuid);
        //{"b1":[{"day":"2017-02-04","offer":[{"help_order":"P590d19i951id0hh71ca","lowerIds":[],"money_num":800,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P93bj6fei1022id284bc","lowerIds":[],"money_num":1500,"ruid":5,"user_id":9,"user_phone":"18192023651"},{"help_order":"P95jieig2h53i689cg87","lowerIds":[],"money_num":1600,"ruid":7,"user_id":18,"user_phone":"17792380563"}],"value":"800"},{"day":"2017-02-03","offer":[{"help_order":"P54gc72i00228c32jf7a","lowerIds":[],"money_num":200,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P989ac1ed4idi9cch597","lowerIds":[],"money_num":200,"ruid":7,"user_id":18,"user_phone":"17792380563"},{"help_order":"P55d0697b5949cg5bi10","lowerIds":[],"money_num":600,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P611gi76ji9i1bh78eg7","lowerIds":[],"money_num":600,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P568b61ehgfij3fc24e3","lowerIds":[],"money_num":800,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P37ed9hfhdb7fb9jb69i","lowerIds":[9,10],"money_num":1000,"ruid":0,"user_id":5,"user_phone":"15389290468"},{"help_order":"P383egiha6g1ece88eea","lowerIds":[17,18],"money_num":1000,"ruid":0,"user_id":7,"user_phone":"17709211685"}],"value":"600"}],"b2":[{"day":"2017-02-04","offer":[{"help_order":"P100bbf37i60j1fbj2ca","lowerIds":[],"money_num":300,"ruid":5,"user_id":10,"user_phone":"18192023650"},{"help_order":"P656113i95ai2gi9ie30","lowerIds":[],"money_num":900,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P67j6dc38f9cib9hfj6d","lowerIds":[],"money_num":900,"ruid":0,"user_id":3,"user_phone":"13759889278"}],"value":"500"},{"day":"2017-02-03","offer":[{"help_order":"P63b59fd49b7ef77f1f9","lowerIds":[],"money_num":500,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P912f5a347g2f6i01a3i","lowerIds":[17,18],"money_num":600,"ruid":0,"user_id":7,"user_phone":"17709211685"},{"help_order":"P978b54a570g9005fec9","lowerIds":[],"money_num":800,"ruid":5,"user_id":10,"user_phone":"18192023650"}],"value":"700"}],"c1":[{"day":"2017-02-04","offer":[{"help_order":"P573690gebgiic9e9ij4","lowerIds":[],"money_num":500,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P923jj842efjg1jbg5ii","lowerIds":[17,18],"money_num":500,"ruid":0,"user_id":7,"user_phone":"17709211685"}],"value":"100"},{"day":"2017-02-03","offer":[{"help_order":"P5250ii8e5jha3884fei","lowerIds":[],"money_num":100,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P945e32gf18iagb976i3","lowerIds":[],"money_num":500,"ruid":5,"user_id":10,"user_phone":"18192023650"},{"help_order":"P406g8i280de7j08ddia","lowerIds":[17,18],"money_num":1000,"ruid":0,"user_id":7,"user_phone":"17709211685"}],"value":"900"}],"c2":[{"day":"2017-02-04","offer":[{"help_order":"P991g55d11f7hdja3bi0","lowerIds":[],"money_num":300,"ruid":5,"user_id":10,"user_phone":"18192023650"},{"help_order":"P58g02c7c116ec11je3d","lowerIds":[],"money_num":800,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P6485d7g37adb71i29e2","lowerIds":[],"money_num":900,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P6612cffjd716i2a8iaj","lowerIds":[],"money_num":900,"ruid":0,"user_id":3,"user_phone":"13759889278"}],"value":"1000"},{"day":"2017-02-03","offer":[{"help_order":"P53cagcj0c8dehe9636b","lowerIds":[],"money_num":200,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P62j82a55igh7a71cdbd","lowerIds":[],"money_num":500,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P602cf771dg4cj8dbe73","lowerIds":[],"money_num":600,"ruid":0,"user_id":3,"user_phone":"13759889278"},{"help_order":"P90gf9i60e19affbceg9","lowerIds":[17,18],"money_num":600,"ruid":0,"user_id":7,"user_phone":"17709211685"},{"help_order":"P961fde0hf6g0eiea3e5","lowerIds":[],"money_num":800,"ruid":5,"user_id":10,"user_phone":"18192023650"}],"value":"200"}]}
        log.info("doMatchOffer cost=" + (System.currentTimeMillis() - start) + "ms;result=" + jsonReturn.toJSONString());
        return jsonReturn;

    }

    /**
     * 匹配订单第一步 正常买入和静态提现先做一次匹配 复投买入和动态提现做一次匹配由于这两个金额相对较小
     *
     * @return
     */
    public JSONObject doMatchStep1(String uuid) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Map<String, Integer> mapb = getMapB(uuid);
        log.info("buy map=" + JSON.toJSONString(mapb));
        Map<String, Integer> mapc = getMapS(uuid);
        log.info("sell map=" + JSON.toJSONString(mapc));
        //第一次全匹配
        doMatchEqual(mapb, mapc, uuid);
        //第一次全匹配生成订单数
        int ordersNum = hasMatchList.get(uuid).size();
        log.info(" doMatchEqual1 hasMatchList=" + ordersNum);
        //第一次拆分匹配
        List<Map<String, Integer>> list = matchEqualOfferLimit(mapb, mapc, uuid);
        ordersNum = hasMatchList.get(uuid).size() - ordersNum;
        log.info(" doMatchEqual1 matchEqualOfferLimit 1=" + ordersNum);
        mapb = list.get(0);
        mapc = list.get(1);
        //第二此全匹配
        doMatchEqual(mapb, mapc, uuid);
        ordersNum = hasMatchList.get(uuid).size() - ordersNum;
        log.info(" doMatchEqual1 doMatchEqual 2=" + ordersNum);
        log.info("buy map=" + JSON.toJSONString(mapb));
        log.info("sell map=" + JSON.toJSONString(mapc));
        //第二次拆分订单
        List<Map<String, Integer>> listresult = matchEqualOfferLimit(mapb, mapc, uuid);
        ordersNum = hasMatchList.get(uuid).size() - ordersNum;
        jsonObject.put("order_sum", hasMatchList.get(uuid).size());
        log.info(" doMatchEqual1 matchEqualOfferLimit 2=" + ordersNum);
        log.info("buy map=" + JSON.toJSONString(mapb));
        log.info("sell map=" + JSON.toJSONString(mapc));
        float sumnum = saveOrder(uuid);
        jsonObject.put("summoney", sumnum);
        log.info(" order sum money==" + sumnum);
        //进行拆分匹配 先权限验证 包含按金额全匹配
        //matchEqualOfferLimit(mapb, mapc, uuid);
        log.info("buy map=" + JSON.toJSONString(mapb));
        log.info("sell map=" + JSON.toJSONString(mapc));
        return jsonObject;
    }

    /**
     * 持久化订单
     *
     * @param uuid
     * @throws Exception
     */
    public float saveOrder(String uuid) throws Exception {
        List<Orders> list = hasMatchList.get(uuid);
        float sumnum = 0;
        for (Orders order : list) {
            sumnum = sumnum + order.getMoney_num();
            JdbcUtils.getInstatance().updateByPreparedStatement(getSaveOrderSql(order), null);
        }
        return sumnum;
    }

    /**
     * 获取持久化订单sql
     *
     * @param order
     * @return
     */
    public String getSaveOrderSql(Orders order) {
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO orders(create_date,last_update,state,order_id,order_num,order_type," +
                "recharge_order,recharge_phone,recharge_uid," +
                "withdrawals_order,withdrawals_phone,withdrawals_uid," +
                "money_num,complaint_status,match_date)select ");
        sql.append(order.getCreate_date()).append(",");
        sql.append(order.getLast_date()).append(",");
        sql.append("'").append(order.getState()).append("',");
        sql.append(order.getOrder_id()).append(",");
        sql.append("'").append(order.getOrder_num()).append("',");
        sql.append(order.getOrder_type()).append(",");
        sql.append("'").append(order.getRecharge_order()).append("',");
        sql.append("'").append(order.getRecharge_phone()).append("',");
        sql.append(order.getRecharge_uid()).append(",");
        sql.append("'").append(order.getWithdrawals_order()).append("',");
        sql.append("'").append(order.getWithdrawals_phone()).append("',");
        sql.append(order.getWithdrawals_uid()).append(",");
        sql.append(order.getMoney_num()).append(",");
        sql.append(order.getComplaint_status()).append(",");
        sql.append(order.getMatch_date());
        log.info("getSaveOrderSql sql=" + sql.toString());
        return sql.toString();


    }

    public JSONArray checkMatchAll(JSONArray jsonArray, String uuid) throws Exception {
        JSONArray jsonReurn = new JSONArray();
        //[{"forder":"b1_2017-02-03_P568b61ehgfij3fc24e3","num":800,"torder":"c2_2017-02-03_P961fde0hf6g0eiea3e5"},{"forder":"b2_2017-02-03_P912f5a347g2f6i01a3i","num":600,"torder":"c2_2017-02-03_P602cf771dg4cj8dbe73"},{"forder":"b1_2017-02-03_P611gi76ji9i1bh78eg7","num":600,"torder":"c2_2017-02-03_P90gf9i60e19affbceg9"},{"forder":"b1_2017-02-03_P989ac1ed4idi9cch597","num":200,"torder":"c2_2017-02-03_P53cagcj0c8dehe9636b"},{"forder":"b2_2017-02-03_P63b59fd49b7ef77f1f9","num":500,"torder":"c2_2017-02-03_P62j82a55igh7a71cdbd"},{"forder":"b2_2017-02-04_P656113i95ai2gi9ie30","num":900,"torder":"c2_2017-02-04_P6485d7g37adb71i29e2"},{"forder":"b1_2017-02-04_P590d19i951id0hh71ca","num":800,"torder":"c2_2017-02-04_P58g02c7c116ec11je3d"},{"forder":"b1_2017-02-03_P383egiha6g1ece88eea","num":1000,"torder":"c1_2017-02-03_P406g8i280de7j08ddia"},{"forder":"b2_2017-02-04_P100bbf37i60j1fbj2ca","num":300,"torder":"c2_2017-02-04_P991g55d11f7hdja3bi0"},{"forder":"b2_2017-02-04_P67j6dc38f9cib9hfj6d","num":900,"torder":"c2_2017-02-04_P6612cffjd716i2a8iaj"}]
        for (Object object : jsonArray) {
            JSONObject json = (JSONObject) object;
            //都检查通过
            if (checkOrder(json, uuid)) {
                doAddOrder(json, uuid);
            } else {
                jsonReurn.add(json);//未通过检查的等拆分完成后再做处理
            }

        }
        return jsonReurn;

    }

    /**
     * 第一步 生成订单 第2步更新 list 第三步 更新 map
     *
     * @param json
     * @param uuid
     */
    public void doAddOrder(JSONObject json, String uuid) throws Exception {

        String fOrderId = json.getString("forder");
        String tOrderId = json.getString("torder");
        Orders order = getOrder(json, uuid);

        addMatchOrder(uuid, order);
        doUpdateMapCuMathList(fOrderId, json.getIntValue("num"), uuid);
        doUpdateMapCuMathList(tOrderId, json.getIntValue("num"), uuid);

        log.info("doAddOrder param=" + json + JSON.toJSONString(hasMatchList));
    }

    public void doUpdateMapCuMathList(String OrderId, long num, String uuid) {
        String[] ftorderArr = OrderId.split("_");
        JSONArray jsonArray = mapCuMathList.get(uuid).getJSONArray(ftorderArr[0]);
        JSONArray jsonNew = new JSONArray();
        for (Object object : jsonArray) {
            JSONObject jsontmp = (JSONObject) object;
            if (ftorderArr[1].equals(jsontmp.getString("day"))) {
                long leaveValue = jsontmp.getLongValue("leave_value") - num;
                if (leaveValue == 0) {
                    jsontmp.put("is_over", true);//是否完成
                }
                jsontmp.put("leave_value", leaveValue);


            }
            jsonNew.add(jsontmp);

        }
        synchronized (mapCuMathList) {
            mapCuMathList.get(uuid).put(ftorderArr[0], jsonNew);
        }

    }

    /**
     * 更新源数据
     *
     * @param OrderId
     * @param uuid
     * @throws Exception
     */
    public void refreshMapCuMathList(String OrderId, String uuid) throws Exception {
        String[] ftorderArr = OrderId.split("_");
        JSONArray jsonArray = mapCuMathList.get(uuid).getJSONArray(ftorderArr[0]);
        JSONArray jsonNew = new JSONArray();
        for (Object object : jsonArray) {
            JSONObject jsontmp = (JSONObject) object;
            if (ftorderArr[1].equals(jsontmp.getString("day"))) {
                jsontmp.put("offer", getOfferHelpByWhereSql(getWhereSql(ftorderArr[0], ftorderArr[1])));
            }
            jsonNew.add(jsontmp);

        }
        synchronized (mapCuMathList) {
            mapCuMathList.get(uuid).put(ftorderArr[0], jsonNew);
        }

    }

    /**
     * 订单生成
     *
     * @param json
     * @param uuid
     * @return
     */
    public Orders getOrder(JSONObject json, String uuid) throws Exception {
        String fOrderId = json.getString("forder");
        String tOrderId = json.getString("torder");
        JSONObject fJson = getOfferByMID(fOrderId, uuid);
        JSONObject tJson = getOfferByMID(tOrderId, uuid);
        String idname = "order_id";
        appServerMappe.id_generator(idname);
        long order_id = appServerMappe.get_id_generator(idname);
        long start = System.currentTimeMillis();
        String ordernum = CommonUtil.genRandomOrderEX("D", order_id + "");
        Orders order = new Orders();
        order.setCreate_date(start);
        order.setLast_date(start);
        order.setOrder_type(3);
        order.setMoney_num(fJson.getFloatValue("money_num"));
        order.setState('N');
        order.setOrder_id(order_id);
        order.setMatch_date(start);
        order.setOrder_num(ordernum);
        order.setRecharge_order(fJson.getString("help_order"));
        order.setRecharge_phone(fJson.getString("user_phone"));
        order.setRecharge_uid(fJson.getLongValue("user_id"));
        order.setWithdrawals_order(tJson.getString("help_order"));
        order.setWithdrawals_phone(tJson.getString("user_phone"));
        order.setWithdrawals_uid(tJson.getLongValue("user_id"));
        //订单状态更新为已匹配
        updateOfferHelp(3, fJson.getString("help_order"));
        updateOfferHelp(3, tJson.getString("help_order"));
        return order;

    }

    public void updateOfferHelp(int status, String help_order) throws Exception {
        String sql = "update offer_help set help_status = 3 where help_order ='" + help_order + "'";
        log.info("updateOfferHelp sql=" + sql.toString());
        JdbcUtils.getInstatance().updateByPreparedStatement(sql, null);

    }

    /**
     * 订单生成
     *
     * @param json
     * @param uuid
     * @return
     */
    public Orders getOrderParamDetail(JSONObject json, String uuid) throws Exception {
        String idname = "order_id";
        appServerMappe.id_generator(idname);
        long order_id = appServerMappe.get_id_generator(idname);
        long start = System.currentTimeMillis();
        String ordernum = CommonUtil.genRandomOrderEX("D", order_id + "");
        Orders order = new Orders();

        order.setCreate_date(start);
        order.setLast_date(start);
        order.setOrder_type(3);
        order.setState('N');

        order.setOrder_id(order_id);
        order.setMatch_date(start);

        order.setOrder_num(ordernum);
        order.setMoney_num(json.getFloatValue("money_num"));
        order.setRecharge_order(json.getString("fhelp_order"));
        order.setRecharge_phone(json.getString("fuser_phone"));
        order.setRecharge_uid(json.getLongValue("fuser_id"));
        order.setWithdrawals_order(json.getString("thelp_order"));
        order.setWithdrawals_phone(json.getString("tuser_phone"));
        order.setWithdrawals_uid(json.getLongValue("tuser_id"));
        //订单状态更新为已匹配
        updateOfferHelp(3, json.getString("fhelp_order"));
        updateOfferHelp(3, json.getString("thelp_order"));
        return order;

    }

    public boolean checkOrder(JSONObject param, String uuid) throws Exception {
        boolean isTrue = false;
        //每天订单余额检测通过  再检测权限
        if (checkOfferForder(param.getString("forder"), param.getIntValue("num"), uuid) == 1 && checkOfferForder(param.getString("torder"), param.getIntValue("num"), uuid) == 1) {
            if (checklimits(param, uuid)) {
                isTrue = true;
            }
        }
        return isTrue;

    }

    //权限检查
    public boolean checklimits(JSONObject param, String uuid) {
        String fOrderId = param.getString("forder");
        String tOrderId = param.getString("torder");
        JSONObject fJson = getOfferByMID(fOrderId, uuid);
        JSONObject tJson = getOfferByMID(tOrderId, uuid);
        try {
            if (fJson.getIntValue("user_id") != tJson.getIntValue("user_id") && fJson.getIntValue("ruid") != tJson.getIntValue("user_id") &&
                    !fJson.getJSONArray("lowerIds").contains(tJson.getIntValue("user_id"))) {
                if (!isContansOffer(uuid, fJson.getString("help_order")) && !isContansOffer(uuid, tJson.getString("help_order"))) {
                    return true;
                } else {
                    log.info("isContansOffer false fhelp_order=" + fJson.getString("help_order") + ";thelp_order=" + tJson.getString("help_order") + ";matchlist=" + JSON.toJSONString(hasMatchList.get(uuid)));
                    return false;
                }


            } else {
                return false;
            }

        } catch (Exception e) {
            log.info("checklimits exception param=" + param.toString() + ";fJson=" + fJson.toJSONString() + ";tjson=" + tJson.toJSONString());
            return false;
        }
        //不能和自己成交不能和上级成交 不能和下级成交


    }

    /**
     * c1_2017-02-03_P5250ii8e5jha3884fei 格式获取最初的offer
     *
     * @param mid
     * @param uuid
     * @return
     */
    public JSONObject getOfferByMID(String mid, String uuid) {
        JSONObject jsonReturn = new JSONObject();
        String[] ftorderArr = mid.split("_");
        JSONArray jsonArray = mapCuMathList.get(uuid).getJSONArray(ftorderArr[0]);
        abc:
        for (Object object : jsonArray) {
            JSONObject json = (JSONObject) object;
            if (ftorderArr[1].equals(json.getString("day"))) {
                JSONArray offers = json.getJSONArray("offer");
                a:
                for (Object objectOffer : offers) {
                    JSONObject jsonOffer = (JSONObject) objectOffer;
                    if (jsonOffer.getString("help_order").equals(ftorderArr[2])) {
                        jsonReturn = jsonOffer;
                        break a;

                    }
                }
                if (!jsonReturn.isEmpty()) {
                    break abc;
                }

                // break;

            }

        }
        return jsonReturn;
    }

    /**
     * 当日余额检测
     *
     * @param ftOrderId
     * @param num
     * @param uuid
     * @return
     */
    public int checkOfferForder(String ftOrderId, int num, String uuid) {
        int reurnCode = 0;//code=0表示已经匹配完成 1表示可以匹配 2表示当天剩余值小于当前要匹配的值 即当前值需要拆分
        String[] ftorderArr = ftOrderId.split("_");
        JSONArray jsonArray = mapCuMathList.get(uuid).getJSONArray(ftorderArr[0]);
        for (Object object : jsonArray) {
            JSONObject json = (JSONObject) object;
            if (ftorderArr[1].equals(json.getString("day"))) {
                if (!json.getBooleanValue("is_over")) {
                    if (json.getInteger("leave_value") >= num) {
                        reurnCode = 1;
                    } else {
                        reurnCode = 2;
                    }
                }
                break;

            }

        }

        return reurnCode;

    }

    /**
     * 当日余额检测
     *
     * @param ftOrderId
     * @param num
     * @param uuid
     * @return
     */
    public int getLeave(String ftOrderId, int num, String uuid) {
        String[] ftorderArr = ftOrderId.split("_");
        JSONArray jsonArray = mapCuMathList.get(uuid).getJSONArray(ftorderArr[0]);
        for (Object object : jsonArray) {
            JSONObject json = (JSONObject) object;
            if (ftorderArr[1].equals(json.getString("day"))) {
                return json.getInteger("leave_value");
            }

        }

        return -1;

    }

    public Map<String, Integer> getMapB(String uuid) {
        Map<String, Integer> mapb = new HashMap<String, Integer>();
        JSONObject jsonParams = mapCuMathList.get(uuid);


        if (jsonParams.containsKey("b1")) {
            JSONArray jsonArrayb1 = jsonParams.getJSONArray("b1");
            if (jsonArrayb1 != null) {
                if (!jsonArrayb1.isEmpty()) {
                    for (Object object : jsonArrayb1) {
                        JSONObject json = (JSONObject) object;

                        JSONArray offersNotMatch = getHasNotMathList(uuid, json.getJSONArray("offer"));
                        for (Object offerObject : offersNotMatch) {
                            JSONObject jsonNotMatch = (JSONObject) offerObject;
                            String key = "b1" + "_" + json.getString("day") + "_" + jsonNotMatch.getString("help_order");
                            mapb.put(key, jsonNotMatch.getIntValue("money_num"));

                        }

                    }

                }
            }

        }
        if (jsonParams.containsKey("b2")) {
            JSONArray jsonArrayb2 = jsonParams.getJSONArray("b2");
            if (jsonArrayb2 != null) {
                if (!jsonArrayb2.isEmpty()) {
                    for (Object object : jsonArrayb2) {
                        JSONObject json = (JSONObject) object;
                        JSONArray offersNotMatch = getHasNotMathList(uuid, json.getJSONArray("offer"));
                        for (Object offerObject : offersNotMatch) {
                            JSONObject jsonNotMatch = (JSONObject) offerObject;
                            String key = "b2" + "_" + json.getString("day") + "_" + jsonNotMatch.getString("help_order");
                            mapb.put(key, jsonNotMatch.getIntValue("money_num"));

                        }

                    }
                }
            }

        }


        return mapb;

    }

    public Map<String, Integer> getMapS(String uuid) {
        Map<String, Integer> mapb = new HashMap<String, Integer>();
        JSONObject jsonParams = mapCuMathList.get(uuid);
        if (jsonParams.containsKey("c1")) {
            JSONArray jsonArrayb1 = jsonParams.getJSONArray("c1");
            if (jsonArrayb1 != null) {
                if (!jsonArrayb1.isEmpty()) {
                    for (Object object : jsonArrayb1) {
                        JSONObject json = (JSONObject) object;
                        JSONArray offersNotMatch = getHasNotMathList(uuid, json.getJSONArray("offer"));
                        for (Object offerObject : offersNotMatch) {
                            JSONObject jsonNotMatch = (JSONObject) offerObject;
                            String key = "c1" + "_" + json.getString("day") + "_" + jsonNotMatch.getString("help_order");
                            mapb.put(key, jsonNotMatch.getIntValue("money_num"));

                        }

                    }
                }
            }

        }
        if (jsonParams.containsKey("c1")) {
            JSONArray jsonArrayb2 = jsonParams.getJSONArray("c2");
            if (jsonArrayb2 != null) {
                if (!jsonArrayb2.isEmpty()) {
                    for (Object object : jsonArrayb2) {
                        JSONObject json = (JSONObject) object;
                        JSONArray offersNotMatch = getHasNotMathList(uuid, json.getJSONArray("offer"));
                        for (Object offerObject : offersNotMatch) {
                            JSONObject jsonNotMatch = (JSONObject) offerObject;
                            String key = "c2" + "_" + json.getString("day") + "_" + jsonNotMatch.getString("help_order");
                            mapb.put(key, jsonNotMatch.getIntValue("money_num"));
                        }

                    }
                }
            }
        }


        return mapb;

    }

    /**
     * 根据类型匹配
     *
     * @param uuid
     * @param type1
     * @param type2
     */
    public void doMathByType(String uuid, String type1, String type2) throws Exception {
        JSONObject jsonParams = mapCuMathList.get(uuid);
        JSONArray type1Arr = jsonParams.getJSONArray(type1);
        JSONArray type2Arr = jsonParams.getJSONArray(type2);
        for (Object object : type1Arr) {
            JSONObject json = (JSONObject) object;
            //当日匹配类型未完成
            if (!json.getBoolean("is_over")) {
                //获取当天未匹配的值
                long leave_value = json.getLongValue("leave_value");
                //获取所有为匹配订单订单
                JSONArray offersNotMatch = getHasNotMathList(uuid, json.getJSONArray("offer"));
                //未匹配订单为空 设置当天为匹配完成
                if (offersNotMatch.isEmpty()) {
                    json.put("is_over", true);
                } else {
                    //把未匹配订单的最小值拿出来和leave_value做一个比对
                    for (Object offerObject : offersNotMatch) {
                        JSONObject jsonNotMatch = (JSONObject) offerObject;
                        //进行最后一次匹配
                        if (jsonNotMatch.getLongValue("money_num") > leave_value) {
                            //拆分此订单
                            //  String order_num_new = splitOffer(jsonNotMatch.getString("help_order"), jsonNotMatch.getFloatValue("money_num"));
                            //优先把此订单匹配上
                            leave_value = 0;
                            json.put("is_over", true);
                            break;
                        } else {

                        }

                    }

                }

            }


        }

    }

    /**
     * 订单拆分 返回拆分订单的help_order
     *
     * @param help_order
     * @param money_num
     * @return
     * @throws Exception
     */
    public JSONObject splitOffer(String help_order, float money_num) throws Exception {
        JSONObject jsonSplitResult = new JSONObject();
        jsonSplitResult.put("lastOrder", help_order);
        Offer_Help offer_last = appServerMappe.getOfferHelpByHelpOrder(help_order);
        float lastMoney = offer_last.getMoney_num();
        long start = System.currentTimeMillis();
        //订单设置成为已完成
        // offer_last.setHelp_status(2);
        String sql = "UPDATE offer_help SET help_status=2,is_split=1,last_update=" + start + " where help_order='" + help_order + "'";
        JdbcUtils.getInstatance().updateByPreparedStatement(sql, null);
        log.info("splitOffer 订单设置成为已完成 sql=" + sql);
        long help_id = 0;
        String idname = "help_id";
        appServerMappe.id_generator(idname);
        help_id = appServerMappe.get_id_generator(idname);
        offer_last.setHelp_id(help_id);
        offer_last.setIs_split(1);
        offer_last.setLast_update(start);
        offer_last.setMoney_num(money_num);
        String ordernum = "";
        if (offer_last.getHelp_type() == 1) {
            ordernum = CommonUtil.genRandomOrderEX("T", help_id + "");
        } else {
            ordernum = CommonUtil.genRandomOrderEX("S", help_id + "");
        }
        // String ordernum = CommonUtil.genRandomOrder(help_id + "");
        offer_last.setHelp_order(ordernum);
        appServerMappe.OfferHelp(offer_last);
        jsonSplitResult.put("equelOrderNum", ordernum);
        appServerMappe.id_generator(idname);
        help_id = appServerMappe.get_id_generator(idname);
        offer_last.setHelp_id(help_id);
        offer_last.setIs_split(1);
        offer_last.setLast_update(start);
        float money_num_split = lastMoney - money_num;
        offer_last.setMoney_num(money_num_split);
        String ordernum1 = "";
        if (offer_last.getHelp_type() == 1) {
            ordernum1 = CommonUtil.genRandomOrderEX("T", help_id + "");
        } else {
            ordernum1 = CommonUtil.genRandomOrderEX("S", help_id + "");
        }
        offer_last.setHelp_order(ordernum1);
        appServerMappe.OfferHelp(offer_last);
        jsonSplitResult.put("money_num_split", money_num_split);
        jsonSplitResult.put("overplusOrderNum", ordernum1);
        jsonSplitResult.put("uid", offer_last.getUser_id());
        jsonSplitResult.put("uphone", offer_last.getUser_phone());
        log.info("splitOffer 订单已经拆分成两条 reuslt=" + jsonSplitResult.toJSONString());
        return jsonSplitResult;
    }

    public JSONArray getHasNotMathList(String uuid, JSONArray offersAll) {
        JSONArray returnJsonArr = new JSONArray();
        for (Object offerObject : offersAll) {
            JSONObject json = (JSONObject) offerObject;
            if (!isContansOffer(uuid, json.getString("help_order"))) {
                returnJsonArr.add(json);
            }
        }
        return returnJsonArr;
    }

    /**
     * 根据订单类型获取查询where条件
     *
     * @param type
     * @param day
     * @return
     */
    public String getWhereSql(String type, String day) {
        //b1 正常买入 b2 复投买入 c1 静态提现 c2 动态提现
        StringBuffer baseSql = new StringBuffer();
        baseSql.append(" where date_format(FROM_UNIXTIME(o.create_date / 1000),'%Y-%m-%d')='").append(day).append("' AND o.help_status=1 ");
        switch (type) {
            case "b1":
                baseSql.append(" and o.help_type=1 and o.is_income=1");
                break;
            case "b2":
                baseSql.append(" and o.help_type=1 and o.is_income=0");
                break;
            case "c1":
                baseSql.append(" and o.help_type=2 and o.wallet_type=1");
                break;
            case "c2":
                baseSql.append(" and o.help_type=2 and o.wallet_type=2");
                break;
        }
        return baseSql.toString();

    }

    /**
     * 根据查询where条件获取匹配订单所需的基础信息 o.help_order,o.user_id,o.user_phone,ruid及下级
     *
     * @param whereSql
     * @return
     * @throws Exception
     */
    public JSONArray getOfferHelpByWhereSql(String whereSql) throws Exception {
        JSONArray returnJson = new JSONArray();
        String sql = SqlConstant.SQL_GET_OFFER + whereSql + SqlConstant.SQL_GET_OFFER_DESC;
        List<Map<String, Object>> list = JdbcUtils.getInstatance().findModeResult(sql, null);
        for (Map<String, Object> mapTmp : list) {
            JSONObject json = JSON.parseObject(JSON.toJSONString(mapTmp));
            json.put("lowerIds", getLowerByUid(mapTmp.get("user_id").toString()));
            returnJson.add(json);
        }
        //  log.info("getOfferHelpByWhereSql sql=" + sql + ";result=" + returnJson.toJSONString());
        return returnJson;

    }

    /**
     * 获取某个用户的下级
     *
     * @param uid
     * @return
     * @throws Exception
     */
    public JSONArray getLowerByUid(String uid) throws Exception {
        JSONArray returnJson = new JSONArray();
        String sql = SqlConstant.SQL_GET_LOWER + uid;
        List<Map<String, Object>> list = JdbcUtils.getInstatance().findModeResult(sql, null);
        for (Map<String, Object> mapTmp : list) {
            if (mapTmp.containsKey("user_id")) {
                returnJson.add(Long.valueOf(mapTmp.get("user_id").toString()));

            }
        }
        // log.info("getLowerByUid sql=" + sql + ";result=" + returnJson.toJSONString());
        return returnJson;
    }

    /**
     * 权限匹配
     */
    public List<Map<String, Integer>> matchEqualOfferLimit(Map<String, Integer> mapb, Map<String, Integer> mapc, String uuid) throws Exception {
        List<Map<String, Integer>> matchLast = new LinkedList<Map<String, Integer>>();
        Map<String, Integer> mapbClone = new HashMap<String, Integer>();
        mapbClone.putAll(mapb);
        Map<String, Integer> mapcClone = new HashMap<String, Integer>();
        mapcClone.putAll(mapc);
        Iterator<Map.Entry<String, Integer>> iterb = mapb.entrySet().iterator();
        JSONArray jsonArray = new JSONArray();
        while (iterb.hasNext()) {
            boolean hasEqual = false;
            Map.Entry<String, Integer> meb = iterb.next();
            Iterator<Map.Entry<String, Integer>> iterc = mapc.entrySet().iterator();
            while (iterc.hasNext()) {
                Map.Entry<String, Integer> mec = iterc.next();
                int min = meb.getValue();
                if (meb.getValue() > mec.getValue()) {
                    min = mec.getValue();
                }
                JSONObject json = new JSONObject();
                json.put("forder", meb.getKey());
                json.put("torder", mec.getKey());
                json.put("num", mec.getValue());
                //权限检查
                if (checklimits(json, uuid)) {
                    int resultCheckOfferB = checkOfferForder(meb.getKey(), min, uuid);
                    int resultCheckOfferS = checkOfferForder(mec.getKey(), min, uuid);
                    //都大于0才能匹配
                    if (resultCheckOfferB > 0 && resultCheckOfferS > 0) {
                        JSONObject fJson = getOfferByMID(meb.getKey(), uuid);

                        if (fJson.isEmpty()) {
                            refreshMapCuMathList(meb.getKey(), uuid);
                            fJson = getOfferByMID(meb.getKey(), uuid);
                        }
                        JSONObject tJson = getOfferByMID(mec.getKey(), uuid);
                        if (tJson.isEmpty()) {
                            refreshMapCuMathList(mec.getKey(), uuid);
                            tJson = getOfferByMID(mec.getKey(), uuid);
                        }
                        JSONObject getOrderParam = new JSONObject();
                        String forder = meb.getKey();
                        String torder = mec.getKey();
                        getOrderParam.put("fuser_phone", fJson.getString("user_phone"));
                        getOrderParam.put("fuser_id", fJson.getString("user_id"));
                        getOrderParam.put("tuser_id", tJson.getString("user_id"));
                        getOrderParam.put("tuser_phone", tJson.getString("user_phone"));
                        if (resultCheckOfferB == 2 || resultCheckOfferS == 2) {
                            int bleave = getLeave(meb.getKey(), min, uuid);
                            int cleave = getLeave(mec.getKey(), min, uuid);
                            if (min > bleave) {
                                min = bleave;
                            }
                            if (min > cleave) {
                                min = cleave;
                            }
                        }
                        getOrderParam.put("money_num", min);
                        //b需要拆分
                        if (meb.getValue() > min) {
                            JSONObject jsonObject = splitOffer(meb.getKey().split("_")[2], (float) min);
                            forder = meb.getKey().split("_")[0] + "_" + meb.getKey().split("_")[1] + "_" + jsonObject.getString("equelOrderNum");
                            String newForder = meb.getKey().split("_")[0] + "_" + meb.getKey().split("_")[1] + "_" + jsonObject.getString("overplusOrderNum");
                            mapbClone.put(newForder, jsonObject.getIntValue("money_num_split"));
                            getOrderParam.put("fhelp_order", jsonObject.getString("equelOrderNum"));


                        }
                        //c需要拆分
                        if (mec.getValue() > min) {
                            JSONObject jsonObject = splitOffer(mec.getKey().split("_")[2], (float) min);
                            torder = mec.getKey().split("_")[0] + "_" + mec.getKey().split("_")[1] + "_" + jsonObject.getString("equelOrderNum");
                            getOrderParam.put("thelp_order", jsonObject.getString("equelOrderNum"));
                            String newForder = mec.getKey().split("_")[0] + "_" + mec.getKey().split("_")[1] + "_" + jsonObject.getString("overplusOrderNum");
                            mapcClone.put(newForder, jsonObject.getIntValue("money_num_split"));
                        }
                        if (!getOrderParam.containsKey("fhelp_order")) {
                            getOrderParam.put("fhelp_order", meb.getKey().split("_")[2]);
                        }
                        if (!getOrderParam.containsKey("thelp_order")) {
                            getOrderParam.put("thelp_order", mec.getKey().split("_")[2]);
                        }
                        //订单处理
                        Orders order = getOrderParamDetail(getOrderParam, uuid);
                        addMatchOrder(uuid, order);
                        doUpdateMapCuMathList(forder, min, uuid);
                        doUpdateMapCuMathList(torder, min, uuid);
                        mapcClone.remove(mec.getKey());
                        iterc.remove();
                        hasEqual = true;

                    }
                }
            }
            if (hasEqual) {
                mapbClone.remove(meb.getKey());
                iterb.remove();
            }

        }
        log.info("mapb=" + JSON.toJSONString(mapb) + "mapc=" + JSON.toJSONString(mapc) + "mapbClone=" + JSON.toJSONString(mapbClone) + "mapcClone=" + JSON.toJSONString(mapcClone));
        log.info("mapCuMathList=" + JSON.toJSONString(mapCuMathList));
        log.info("hasMatchList=" + JSON.toJSONString(hasMatchList));
        matchLast.add(mapbClone);
        matchLast.add(mapcClone);
        //doMatchEqual(mapbClone,mapcClone,uuid);
        System.out.println("matchLast=" + JSON.toJSONString(matchLast));
        return matchLast;
    }

    /**
     * 全匹配
     *
     * @param mapb
     * @param mapc
     * @param uuid
     * @throws Exception
     */
    public void doMatchEqual(Map<String, Integer> mapb, Map<String, Integer> mapc, String uuid) throws Exception {
        //拆分匹配完成再对剩余订单进行一次全匹配
        JSONArray jsonArrayMatchAll = MatchUtil.matchEqualOffer(mapb, mapc);
        JSONArray jsonUnmatch = checkMatchAll(jsonArrayMatchAll, uuid);
        for (Object object : jsonUnmatch) {
            JSONObject json = (JSONObject) object;
            mapb.put(json.getString("forder"), json.getIntValue("num"));
            mapc.put(json.getString("torder"), json.getIntValue("num"));
        }
        log.info("buy map=" + JSON.toJSONString(mapb));
        log.info("sell map=" + JSON.toJSONString(mapc));

    }

    /**
     * 第一步 生成订单 第2步更新 list 第三步 更新 map
     *
     * @param json
     * @param uuid
     */
    public void doAddOrderSplit(JSONObject json, String uuid) throws Exception {
        String fOrderId = json.getString("forder");
        String tOrderId = json.getString("torder");
        Orders order = getOrder(json, uuid);
        addMatchOrder(uuid, order);
        doUpdateMapCuMathList(fOrderId, json.getIntValue("num"), uuid);
        doUpdateMapCuMathList(tOrderId, json.getIntValue("num"), uuid);
        log.info("doAddOrder param=" + json + JSON.toJSONString(hasMatchList));
    }

    public static void main(String[] args) throws Exception {
        AdminService service = new AdminService();
        String param = "{\"b1\":[{\"day\":\"2017-02-04\",\"value\":\"2000\"},{\"day\":\"2017-02-03\",\"value\":\"2700\"}],\"b2\":[{\"day\":\"2017-02-04\",\"value\":\"2000\"},{\"day\":\"2017-02-03\",\"value\":\"1500\"}],\"c1\":[{\"day\":\"2017-02-04\",\"value\":\"1000\"},{\"day\":\"2017-02-03\",\"value\":\"1600\"}],\"c2\":[{\"day\":\"2017-02-04\",\"value\":\"2900\"},{\"day\":\"2017-02-03\",\"value\":\"2700\"}]}";
        service.doMatchOffer(JSONObject.parseObject(param));
        //service.doMatchOffer("P590d19i951id0hh71ca", 200);
    }


}
