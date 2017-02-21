package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/1.
 */
public class GetMaterOrderInfo {

    private  String from_account;
    private  String to_account;
    private String from_order_num;

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

    public long getFrom_st() {
        return from_st;
    }

    public void setFrom_st(long from_st) {
        this.from_st = from_st;
    }

    public long getTo_st() {
        return to_st;
    }

    public void setTo_st(long to_st) {
        this.to_st = to_st;
    }

    public long getMatch_st() {
        return match_st;
    }

    public void setMatch_st(long match_st) {
        this.match_st = match_st;
    }

    public long getConfirm_st() {
        return confirm_st;
    }

    public void setConfirm_st(long confirm_st) {
        this.confirm_st = confirm_st;
    }

    public String getVoucher_url() {
        return voucher_url;
    }

    public void setVoucher_url(String voucher_url) {
        this.voucher_url = voucher_url;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    private  String to_order_num;
    private  String from_tname;

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    private  String to_tname;
    private float from_money;
    private  float to_money;
    private  long from_st;
    private  long to_st;
    private  long match_st;
    private  long confirm_st;
    private  String voucher_url;

    public int getWallet_type() {
        return wallet_type;
    }

    public void setWallet_type(int wallet_type) {
        this.wallet_type = wallet_type;
    }

    private  int  order_type;
    private  String order_num;
    private  int wallet_type;

}
