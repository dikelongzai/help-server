package com.help.server.domain.responsebean;

import java.util.ArrayList;

/**
 * Created by hou on 2017/1/2. 10013
 */
public class ByStatusGetOrderResp {

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

    public ArrayList<ByStatusGetOrderInfo> getData() {
        return data;
    }

    public void setData(ArrayList<ByStatusGetOrderInfo> data) {
        this.data = data;
    }

    private String msg;
    private ArrayList<ByStatusGetOrderInfo> data;

}
