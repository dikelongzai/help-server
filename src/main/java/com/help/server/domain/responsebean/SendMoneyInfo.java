package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/2.
 */
public class SendMoneyInfo {

    private  String order_num;

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getPayment_st() {
        return payment_st;
    }

    public void setPayment_st(String payment_st) {
        this.payment_st = payment_st;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getFrom_tnamw() {
        return from_tnamw;
    }

    public void setFrom_tnamw(String from_tnamw) {
        this.from_tnamw = from_tnamw;
    }

    public String getFrom_account() {
        return from_account;
    }

    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }

    public String getPayment_url() {
        return payment_url;
    }

    public void setPayment_url(String payment_url) {
        this.payment_url = payment_url;
    }

    private  String payment_st;
    private  int type;
    private float money;
    private  String from_tnamw;
    private  String from_account;
    private  String payment_url;

}
