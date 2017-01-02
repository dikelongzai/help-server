package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/1.10008
 */
public class UserTeamInfo {

    private String tname;
    private String account;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getCsale() {
        return csale;
    }

    public void setCsale(String csale) {
        this.csale = csale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    private String rt;
    private String csale;
    private int status;
    private  long uid;


}
