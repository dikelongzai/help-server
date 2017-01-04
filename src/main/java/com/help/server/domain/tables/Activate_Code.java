package com.help.server.domain.tables;

/**
 * Created by houxianyong on 2017/1/4.
 */
public class Activate_Code {

    public Activate_Code(long create_date, long last_update,char state, long from_uid, long to_uid, int code_num, int is_from_admin) {
        this.state = state;
        this.from_uid = from_uid;
        this.to_uid = to_uid;
        this.code_num = code_num;
        this.is_from_admin = is_from_admin;
        this.create_date = create_date;
        this.last_update = last_update;
    }

    public Activate_Code() {
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public long getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(long from_uid) {
        this.from_uid = from_uid;
    }

    public long getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(long to_uid) {
        this.to_uid = to_uid;
    }

    public int getCode_num() {
        return code_num;
    }

    public void setCode_num(int code_num) {
        this.code_num = code_num;
    }

    public int getIs_from_admin() {
        return is_from_admin;
    }

    public void setIs_from_admin(int is_from_admin) {
        this.is_from_admin = is_from_admin;
    }

    private char state;
    private long from_uid;
    private long to_uid;
    private int code_num;
    private  int is_from_admin;
    private  long create_date;
    private  long last_update;
}

