package com.help.server.domain.responsebean;

import java.util.ArrayList;

/**
 * Created by hou on 2017/1/5. 10018
 */
public class TurnChartResp {

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

    public ArrayList<TurnChartInfo> getData() {
        return data;
    }

    public void setData(ArrayList<TurnChartInfo> data) {
        this.data = data;
    }

    private  String msg;

    private ArrayList<TurnChartInfo> data;
}
