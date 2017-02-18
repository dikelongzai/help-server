package com.help.server.domain.responsebean;

import java.util.ArrayList;

/**
 * Created by hou on 2017/1/1.10023
 */
public class GetMateOrderResp {

    public long getCurrentTimer() {
        return currentTimer;
    }

    public void setCurrentTimer(long currentTimer) {
        this.currentTimer = currentTimer;
    }

    private String code;
    private String msg;
    private  long currentTimer;
    private ArrayList<GetMaterOrderInfo> data;

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

    public ArrayList<GetMaterOrderInfo> getData() {
        return data;
    }

    public void setData(ArrayList<GetMaterOrderInfo> data) {
        this.data = data;
    }
}
