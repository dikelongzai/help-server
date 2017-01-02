package com.help.server.domain.requestbean;

/**
 * Created by hou on 2017/1/1.10008
 */
public class GetUserTemaReq {

    private  String sign;
    private  String st;

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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private long uid;
    private  int level;
}
