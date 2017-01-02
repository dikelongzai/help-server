package com.help.server.domain.responsebean;

/**
 * Created by hou on 2016/12/31.
 */
public class GetUserWalletResp {

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

    public WalletInfoBean getData() {
        return data;
    }

    public void setData(WalletInfoBean data) {
        this.data = data;
    }

    private  String msg;
    private  WalletInfoBean data;
}
