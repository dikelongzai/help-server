package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/1.
 */
public class GetMaterOrderInfo {

    private  int from_type;
    private  int to_type;
    private String from_order_num;

    public int getFrom_type() {
        return from_type;
    }

    public void setFrom_type(int from_type) {
        this.from_type = from_type;
    }

    public int getTo_type() {
        return to_type;
    }

    public void setTo_type(int to_type) {
        this.to_type = to_type;
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

    public String getTo_tname() {
        return to_tname;
    }

    public void setTo_tname(String to_tname) {
        this.to_tname = to_tname;
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

    public String getVoucher_url() {
        return voucher_url;
    }

    public void setVoucher_url(String voucher_url) {
        this.voucher_url = voucher_url;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    private  String to_order_num;
    private  String from_tname;
    private  String to_tname;
    private float from_money;
    private  float to_money;
    private  String from_st;
    private  String to_st;
    private  String match_st;
    private  String confirm_st;
    private  String voucher_url;
    private  String  order_type;
}
