package com.help.server.domain.responsebean;

import java.util.ArrayList;

/**
 * Created by houxianyong on 2017/2/20.
 */
public class GetUserInComeResp {

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<GetUserInComeInfo> getData() {
        return data;
    }

    public void setData(ArrayList<GetUserInComeInfo> data) {
        this.data = data;
    }

    private ArrayList<GetUserInComeInfo> data;
}
