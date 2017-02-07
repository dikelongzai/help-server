package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/21.
 */
public class ActivateCodeLogInfo {

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    private String faccount;

    public String getTaccount() {
        return taccount;
    }

    public void setTaccount(String taccount) {
        this.taccount = taccount;
    }

    private String taccount;
    private String name;
    private int num;
    private long date;
    private String tname;

    public String getFaccount() {
        return faccount;
    }

    public void setFaccount(String faccount) {
        this.faccount = faccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
