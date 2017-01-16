package com.help.server.domain.tables;

import java.sql.Timestamp;

/**
 * Created by dikelongzai 15399073387@163.com on 2017-01-02
 * .
 */
public class Rotate {
    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public long getNew_id() {
        return new_id;
    }

    public void setNew_id(long new_id) {
        this.new_id = new_id;
    }


    private long create_date;
    private long last_update;
    private String state;
    private long new_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;


    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    private String empty;

    public String getRotate_url() {
        return rotate_url;
    }

    public void setRotate_url(String rotate_url) {
        this.rotate_url = rotate_url;
    }

    private String rotate_url;

    public String getHelf_url() {
        return helf_url;
    }

    public void setHelf_url(String helf_url) {
        this.helf_url = helf_url;
    }

    private String helf_url;


}
