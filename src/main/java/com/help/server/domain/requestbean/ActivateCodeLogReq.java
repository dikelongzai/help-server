package com.help.server.domain.requestbean;

/**
 * Created by hou on 2017/1/21.
 */
public class ActivateCodeLogReq {

    private  String sign;
    private  long st;

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

    private String account;
    private  long uid;


}
