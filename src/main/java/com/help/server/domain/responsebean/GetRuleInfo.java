package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/21.
 */
public class GetRuleInfo {

    private  long order_max_money;
    private long match_date_min;
    private long   match_date_max;
    private int freezing_time;

    public long getOrder_max_money() {
        return order_max_money;
    }

    public void setOrder_max_money(long order_max_money) {
        this.order_max_money = order_max_money;
    }

    public long getMatch_date_min() {
        return match_date_min;
    }

    public void setMatch_date_min(long match_date_min) {
        this.match_date_min = match_date_min;
    }

    public long getMatch_date_max() {
        return match_date_max;
    }

    public void setMatch_date_max(long match_date_max) {
        this.match_date_max = match_date_max;
    }

    public int getFreezing_time() {
        return freezing_time;
    }

    public void setFreezing_time(int freezing_time) {
        this.freezing_time = freezing_time;
    }

    public int getMin_order_amount() {
        return min_order_amount;
    }

    public void setMin_order_amount(int min_order_amount) {
        this.min_order_amount = min_order_amount;
    }

    public int getMax_order_num() {
        return max_order_num;
    }

    public void setMax_order_num(int max_order_num) {
        this.max_order_num = max_order_num;
    }

    public int getStatic_min_money() {
        return static_min_money;
    }

    public void setStatic_min_money(int static_min_money) {
        this.static_min_money = static_min_money;
    }

    public int getStatic_times_money() {
        return static_times_money;
    }

    public void setStatic_times_money(int static_times_money) {
        this.static_times_money = static_times_money;
    }

    public int getDynamic_min_money() {
        return dynamic_min_money;
    }

    public void setDynamic_min_money(int dynamic_min_money) {
        this.dynamic_min_money = dynamic_min_money;
    }

    public int getDynamic_times_mone() {
        return dynamic_times_mone;
    }

    public void setDynamic_times_mone(int dynamic_times_mone) {
        this.dynamic_times_mone = dynamic_times_mone;
    }

    public int getDynamic_max_money() {
        return dynamic_max_money;
    }

    public void setDynamic_max_money(int dynamic_max_money) {
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

    public int getIs_order_timer() {
        return is_order_timer;
    }

    public void setIs_order_timer(int is_order_timer) {
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

    private  int  min_order_amount;
    private  int max_order_num;
    private  int static_min_money;
    private  int static_times_money;
    private  int dynamic_min_money;
    private  int dynamic_times_mone;
    private  int dynamic_max_money;
    private  float   dynamic_deduct_proportion;
    private  float  interest_not_paid;
    private  float  interest_paid;
    private  int  is_order_timer;
    private  long start_date;
    private  long end_date;



}
