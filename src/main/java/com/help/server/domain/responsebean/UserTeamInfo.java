package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/1.10008
 */
public class UserTeamInfo {

    private String tname;
    private String account;

    public String getTile_name() {
        return tile_name;
    }

    public void setTile_name(String tile_name) {
        this.tile_name = tile_name;
    }

    private String tile_name;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getRt() {
        return rt;
    }

    public void setRt(long rt) {
        this.rt = rt;
    }

    public float getCsale() {
        return csale;
    }

    public void setCsale(float csale) {
        this.csale = csale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    private long rt;
    private float csale;
    private int status;
    private  long uid;


}
