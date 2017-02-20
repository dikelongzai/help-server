
package com.help.server.domain.requestbean;

/**
 * Created by houxianyong on 2017/2/20.
 */
public class GetUserInComeReq {

    public String sign;
    public long st;

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

    public int getIncome_type() {
        return income_type;
    }

    public void setIncome_type(int income_type) {
        this.income_type = income_type;
    }

    private String account;
    public long uid;
    private int income_type;
}