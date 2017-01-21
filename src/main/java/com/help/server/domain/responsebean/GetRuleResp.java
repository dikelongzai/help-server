package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/21.
 */
public class GetRuleResp {

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

    public GetRuleInfo getData() {
        return data;
    }

    public void setData(GetRuleInfo data) {
        this.data = data;
    }

    private  String msg;
    private  GetRuleInfo data;
}
