
package com.help.server.domain.responsebean;

public class GetUserInfoResp {
    public UserMberInfo getData() {
        return data;
    }

    public void setData(UserMberInfo data) {
        this.data = data;
    }

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

    private  String code;
    private  String msg;
    private  UserMberInfo data;

}
