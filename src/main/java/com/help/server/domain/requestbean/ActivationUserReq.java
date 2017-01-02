package com.help.server.domain.requestbean;

/**
 * Created by hou on 2017/1/1. 10009
 */
public class ActivationUserReq {
    private  String sign;
    private  String st;
    private  String faccount;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getFaccount() {
        return faccount;
    }

    public void setFaccount(String faccount) {
        this.faccount = faccount;
    }

    public String getTaccount() {
        return taccount;
    }

    public void setTaccount(String taccount) {
        this.taccount = taccount;
    }

    private  String taccount;

}
