package com.help.server.domain.requestbean;

/**
 * Created by hou on 2016/12/31.
 */
public class UserLoginReq {

    private String sign;
    private  long st;
    private  String account;
    private  String pwd;
    private  String os_type;

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private  String version;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getSt() {
        return st;
    }

    public void setSt(long st) {
        this.st = st;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
