package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/1.10012
 */
public class ForgetpwdInfo {

    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

}
