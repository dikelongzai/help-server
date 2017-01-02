package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/2. 10014
 */
public class HelpsOrderResp {

    private  String code;
    private String msg;
    private HelpsOrderRespInfo data;

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

    public HelpsOrderRespInfo getData() {
        return data;
    }

    public void setData(HelpsOrderRespInfo data) {
        this.data = data;
    }
}
