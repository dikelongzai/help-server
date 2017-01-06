package com.help.server.domain.tables;

/**
 * Created by houxianyong on 2017/1/6.
 */
public class Rotate_News {


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

    public long getRotate_id() {
        return rotate_id;
    }

    public void setRotate_id(long rotate_id) {
        this.rotate_id = rotate_id;
    }

    public String getRotate_url() {
        return rotate_url;
    }

    public void setRotate_url(String rotate_url) {
        this.rotate_url = rotate_url;
    }

    public String getHelf_url() {
        return helf_url;
    }

    public void setHelf_url(String helf_url) {
        this.helf_url = helf_url;
    }
    private  long create_date;
    private  long last_update;
    private  char state;
    private  long rotate_id;
    private  String rotate_url;
    private  String helf_url;

}

