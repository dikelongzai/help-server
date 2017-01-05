package com.help.server.domain.responsebean;

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

    public TurnChartInfo getData() {
        return data;
    }

    public void setData(TurnChartInfo data) {
        this.data = data;
    }

    private  String msg;
    private  TurnChartInfo data;
}
