package com.help.server.domain.responsebean;

import java.util.ArrayList;

/**
 * Created by hou on 2017/1/1. 10008
 */
public class GetUserTemaResp {

    private  String code;

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

    public ArrayList<UserTeamInfo> getData() {
        return data;
    }

    public void setData(ArrayList<UserTeamInfo> data) {
        this.data = data;
    }

    private  String msg;
    private ArrayList <UserTeamInfo> data;
}
