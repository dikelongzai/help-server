package com.help.server.domain.requestbean;

/**
 * Created by hou on 2017/1/21.
 */
public class GetUserPayInfoSnReq {

    private  String sign;

    public String getSign() {
        return sign;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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
    private  String sn;
    private  long uid;


}
