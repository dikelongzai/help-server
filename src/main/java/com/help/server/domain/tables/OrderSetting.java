package com.help.server.domain.tables;

/**
 * Created by houlongbin on 2017/1/23.
 * 订单设置实体
 */
public class OrderSetting {
    private long create_date;
    private long last_update;
    private  long order_setting_id;
    private  Integer is_order_timer;
    private  long start_date;
    private  long end_date;
    private float order_max_money;
    private Integer match_date_min;
    private Integer match_date_max;
    private Integer  freezing_time;
    private Integer min_order_amount;
    private Integer  max_order_num;
    private Integer static_min_money;
    private Integer static_times_money;
    private Integer dynamic_min_money;
    private Integer dynamic_times_money;
    private Integer dynamic_max_money;
    private float dynamic_deduct_proportion;
    private float interest_not_paid;

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    public long getOrder_setting_id() {
        return order_setting_id;
    }

    public void setOrder_setting_id(long order_setting_id) {
        this.order_setting_id = order_setting_id;
    }

    public Integer getIs_order_timer() {
        return is_order_timer;
    }

    public void setIs_order_timer(Integer is_order_timer) {
        this.is_order_timer = is_order_timer;
    }

    public long getStart_date() {
        return start_date;
    }

    public void setStart_date(long start_date) {
        this.start_date = start_date;
    }

    public long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(long end_date) {
        this.end_date = end_date;
    }

    public float getOrder_max_money() {
        return order_max_money;
    }

    public void setOrder_max_money(float order_max_money) {
        this.order_max_money = order_max_money;
    }

    public Integer getMatch_date_min() {
        return match_date_min;
    }

    public void setMatch_date_min(Integer match_date_min) {
        this.match_date_min = match_date_min;
    }

    public Integer getMatch_date_max() {
        return match_date_max;
    }

    public void setMatch_date_max(Integer match_date_max) {
        this.match_date_max = match_date_max;
    }

    public Integer getFreezing_time() {
        return freezing_time;
    }

    public void setFreezing_time(Integer freezing_time) {
        this.freezing_time = freezing_time;
    }

    public Integer getMin_order_amount() {
        return min_order_amount;
    }

    public void setMin_order_amount(Integer min_order_amount) {
        this.min_order_amount = min_order_amount;
    }

    public Integer getMax_order_num() {
        return max_order_num;
    }

    public void setMax_order_num(Integer max_order_num) {
        this.max_order_num = max_order_num;
    }

    public Integer getStatic_min_money() {
        return static_min_money;
    }

    public void setStatic_min_money(Integer static_min_money) {
        this.static_min_money = static_min_money;
    }

    public Integer getStatic_times_money() {
        return static_times_money;
    }

    public void setStatic_times_money(Integer static_times_money) {
        this.static_times_money = static_times_money;
    }

    public Integer getDynamic_min_money() {
        return dynamic_min_money;
    }

    public void setDynamic_min_money(Integer dynamic_min_money) {
        this.dynamic_min_money = dynamic_min_money;
    }

    public Integer getDynamic_times_money() {
        return dynamic_times_money;
    }

    public void setDynamic_times_money(Integer dynamic_times_money) {
        this.dynamic_times_money = dynamic_times_money;
    }

    public Integer getDynamic_max_money() {
        return dynamic_max_money;
    }

    public void setDynamic_max_money(Integer dynamic_max_money) {
        this.dynamic_max_money = dynamic_max_money;
    }

    public float getDynamic_deduct_proportion() {
        return dynamic_deduct_proportion;
    }

    public void setDynamic_deduct_proportion(float dynamic_deduct_proportion) {
        this.dynamic_deduct_proportion = dynamic_deduct_proportion;
    }

    public float getInterest_not_paid() {
        return interest_not_paid;
    }

    public void setInterest_not_paid(float interest_not_paid) {
        this.interest_not_paid = interest_not_paid;
    }

    public float getInterest_paid() {
        return interest_paid;
    }

    public void setInterest_paid(float interest_paid) {
        this.interest_paid = interest_paid;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private float interest_paid;
    private String empty;
    private char state;
    private  long id;

}
