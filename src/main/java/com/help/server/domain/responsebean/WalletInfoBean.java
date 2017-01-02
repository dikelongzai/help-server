package com.help.server.domain.responsebean;

/**
 * Created by hou on 2016/12/31.
 */
public class WalletInfoBean {

    public long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getDynamic() {
        return dynamic;
    }

    public void setDynamic(float dynamic) {
        this.dynamic = dynamic;
    }

    public float getUstatic() {
        return ustatic;
    }

    public void setUstatic(float ustatic) {
        this.ustatic = ustatic;
    }

    public float getFrozen() {
        return frozen;
    }

    public void setFrozen(float frozen) {
        this.frozen = frozen;
    }

    private  long    uid =0;
    private String account ="";
    private float dynamic=0;
    private float ustatic=0;
    private float frozen=0;

}
