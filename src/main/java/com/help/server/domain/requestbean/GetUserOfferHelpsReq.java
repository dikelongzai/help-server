package com.help.server.domain.requestbean;

/**
 * Created by hou on 2017/1/24.
 */
public class GetUserOfferHelpsReq {

    private String sign;
    private long st;
    private String account;

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getSt() {
        return st;
    }

    public void setSt(long st) {
        this.st = st;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }



    public int getWallet_type() {
        return wallet_type;
    }

    public void setWallet_type(int wallet_type) {
        this.wallet_type = wallet_type;
    }

    public int getHelp_status() {
        return help_status;
    }

    public void setHelp_status(int help_status) {
        this.help_status = help_status;
    }

    private  long uid;
    private int order_type;
    private  int wallet_type;
    private  int help_status;

}
