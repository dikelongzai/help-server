package com.help.server.domain.responsebean;

/**
 * Created by hou on 2016/12/31.
 */
public class CommResp {
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

    private String code;
    private String msg;
}
