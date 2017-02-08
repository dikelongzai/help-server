
package com.help.server.domain.responsebean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hou on 2017/1/21.
 */
public class GetRuleResp {

    public ArrayList<GetDynamicRuleInfo> getD_data() {
        return d_data;
    }

    public void setD_data(ArrayList<GetDynamicRuleInfo> d_data) {
        this.d_data = d_data;
    }

    private String code;

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

    private String msg;
    private GetRuleInfo data;
    private ArrayList<GetDynamicRuleInfo> d_data;
}
