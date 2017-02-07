package com.help.server.domain.responsebean;

import java.util.ArrayList;

/**
 * Created by houxianyong on 2017/2/7.
 */
public class GetUserCommonQuestResp {

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

    public ArrayList<GetUserCommonQuestInfo> getData() {
        return data;
    }

    public void setData(ArrayList<GetUserCommonQuestInfo> data) {
        this.data = data;
    }

    private  String msg;
    private ArrayList<GetUserCommonQuestInfo> data;
}
