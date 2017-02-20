package com.help.server.domain.responsebean;

/**
 * Created by houxianyong on 2017/2/20.
 */
public class GetUserInComeInfo {

    private String order_num;
    private float money_num;
    private float org_money_num;
    private long income_date;

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public float getMoney_num() {
        return money_num;
    }

    public void setMoney_num(float money_num) {
        this.money_num = money_num;
    }

    public float getOrg_money_num() {
        return org_money_num;
    }

    public void setOrg_money_num(float org_money_num) {
        this.org_money_num = org_money_num;
    }

    public long getIncome_date() {
        return income_date;
    }

    public void setIncome_date(long income_date) {
        this.income_date = income_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    private String user_name;
    private String user_phone;

}
