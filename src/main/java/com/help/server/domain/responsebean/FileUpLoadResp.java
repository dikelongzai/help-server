package com.help.server.domain.responsebean;

/**
 * Created by hou on 2016/12/31.
 */
public class FileUpLoadResp {

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
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

    private String code;
    private String msg;
    private String file_url;
}
