package com.help.server.domain.responsebean;

import java.util.List;

/**
 * Created by hou on 2017/1/21.
 */
public class ActivateCodeLogResp {

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

    public List<ActivateCodeLogInfo> getData() {
        return data;
    }

    public void setData(List<ActivateCodeLogInfo> data) {
        this.data = data;
    }

    private  String msg;
    private List <ActivateCodeLogInfo> data;
}
