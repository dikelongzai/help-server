package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/2.10015
 */
public class SendMoneyResp {
    private  String code;
    private  String msg;

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

    public SendMoneyInfo getData() {
        return data;
    }

    public void setData(SendMoneyInfo data) {
        this.data = data;
    }

    private  SendMoneyInfo data;

}
