package com.help.server.domain.responsebean;

/**
 * Created by houxianyong on 2017/2/18.
 */
public class GetUserOfferOrderInfo {
    public long getMatch_date() {
        return match_date;
    }

    public void setMatch_date(long match_date) {
        this.match_date = match_date;
    }

    private  String order_num = "";
    private  String from_account ="";

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getFrom_account() {
        return from_account;
    }

    public String getFrom_uname() {
        return from_uname;
    }

    public void setFrom_uname(String from_uname) {
        this.from_uname = from_uname;
    }

    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }

    public float getIncome_money() {
        return income_money;
    }

    public void setIncome_money(float income_money) {
        this.income_money = income_money;
    }

    public long getUnfreeze_date() {
        return unfreeze_date;
    }

    public void setUnfreeze_date(long unfreeze_date) {
        this.unfreeze_date = unfreeze_date;
    }

    private  float income_money;
    private  long unfreeze_date;
    private  long match_date;
    private  String from_uname="";
}

