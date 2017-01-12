package com.help.server.domain.responsebean;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

/**
 * Created by hou on 2017/1/7.
 */
public class LeavingMsgResp {

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

    public ArrayList<LeavingMsgInfo> getData() {
        return data;
    }

    public void setData(ArrayList<LeavingMsgInfo> data) {
        this.data = data;
    }

    private  String code;
    private  String msg;
    private ArrayList<LeavingMsgInfo> data;
}
