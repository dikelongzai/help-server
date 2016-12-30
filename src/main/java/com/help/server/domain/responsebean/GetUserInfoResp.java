
package com.help.server.domain.responsebean;

public class GetUserInfoResp {

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

    public UserMberInfo getUserMberInfo() {
        return userMberInfo;
    }

    public void setUserMberInfo(UserMberInfo userMberInfo) {
        this.userMberInfo = userMberInfo;
    }

    private  String code;
    private  String msg;
    private  UserMberInfo userMberInfo;

}
