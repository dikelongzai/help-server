package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/2. //10013
 */
public class ByStatusGetOrderInfo {

    private  String from_account;
    private  String to_account;

    public String getTo_tname() {
        return to_tname;
    }

    public void setTo_tname(String to_tname) {
        this.to_tname = to_tname;
    }

    public String getFrom_order_num() {
        return from_order_num;
    }

    public void setFrom_order_num(String from_order_num) {
        this.from_order_num = from_order_num;
    }

    public String getTo_order_num() {
        return to_order_num;
    }

    public void setTo_order_num(String to_order_num) {
        this.to_order_num = to_order_num;
    }

    public String getFrom_tname() {
        return from_tname;
    }

    public void setFrom_tname(String from_tname) {
        this.from_tname = from_tname;
    }

    public float getFrom_money() {
        return from_money;
    }

    public void setFrom_money(float from_money) {
        this.from_money = from_money;
    }

    public float getTo_money() {
        return to_money;
    }

    public void setTo_money(float to_money) {
        this.to_money = to_money;
    }

    public String getFrom_st() {
        return from_st;
    }

    public void setFrom_st(String from_st) {
        this.from_st = from_st;
    }

    public String getTo_st() {
        return to_st;
    }

    public String getFrom_account() {
        return from_account;
    }

    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }

    public String getTo_account() {
        return to_account;
    }

    public void setTo_account(String to_account) {
        this.to_account = to_account;
    }

    public void setTo_st(String to_st) {
        this.to_st = to_st;
    }

    public String getMatch_st() {
        return match_st;
    }

    public void setMatch_st(String match_st) {
        this.match_st = match_st;
    }

    public String getConfirm_st() {
        return confirm_st;
    }

    public void setConfirm_st(String confirm_st) {
        this.confirm_st = confirm_st;
    }

    private  String from_order_num;
    private  String to_order_num;
    private  String from_tname;
    private  float from_money;
    private  float to_money;
    private  String from_st;
    private  String to_st;
    private  String match_st;
    private String confirm_st;
    private  String to_tname;
}
