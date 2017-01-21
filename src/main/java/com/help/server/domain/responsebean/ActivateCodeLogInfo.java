package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/21.
 */
public class ActivateCodeLogInfo {

    private String faccount;
    private String name;
    private String num;
    private String date;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
