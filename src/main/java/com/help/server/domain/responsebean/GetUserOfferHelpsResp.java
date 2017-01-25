package com.help.server.domain.responsebean;

import java.util.ArrayList;

/**
 * Created by hou on 2017/1/24.
 */
public class GetUserOfferHelpsResp {
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

    public ArrayList<GetUserOfferHelpInfo> getData() {
        return data;
    }

    public void setData(ArrayList<GetUserOfferHelpInfo> data) {
        this.data = data;
    }

    private String code;
    private String msg;
    private ArrayList<GetUserOfferHelpInfo> data;

}
