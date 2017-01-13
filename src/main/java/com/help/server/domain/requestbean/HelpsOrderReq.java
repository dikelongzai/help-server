package com.help.server.domain.requestbean;

/**
 * Created by hou on 2017/1/2. 10014
 */
public class HelpsOrderReq {
     private  String sign;
     private  long st;
     private  String account;

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

    private  long uid;
     private  int wallet_type;
    private  String os_type;
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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getWallet_type() {
        return wallet_type;
    }

    public void setWallet_type(int wallet_type) {
        this.wallet_type = wallet_type;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getHelp_type() {
        return help_type;
    }

    public void setHelp_type(int help_type) {
        this.help_type = help_type;
    }

    private  float money;
     private  int help_type;

}
