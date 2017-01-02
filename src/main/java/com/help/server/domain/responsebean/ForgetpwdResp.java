package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/1.10012
 */
public class ForgetpwdResp {

    private String code;
    private String msg;

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

    public ForgetpwdInfo getData() {
        return data;
    }

    public void setData(ForgetpwdInfo data) {
        this.data = data;
    }

    private ForgetpwdInfo data;

}
