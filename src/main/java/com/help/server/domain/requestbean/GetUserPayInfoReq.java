package com.help.server.domain.requestbean;

/**
 * Created by hou on 2017/1/21.
 */
public class GetUserPayInfoReq {

    private  String sign;

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

    private  long st;
    private  String account;


}
