package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/5.
 */
public class NewsResp {

    private String code;
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

    public NewsInfo getData() {
        return data;
    }

    public void setData(NewsInfo data) {
        this.data = data;
    }

    private NewsInfo data;
}
