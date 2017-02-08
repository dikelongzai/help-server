package com.help.server.domain.responsebean;

import java.lang.ref.PhantomReference;

/**
 * Created by houxianyong on 2017/2/8.
 */
public class GetDynamicRuleInfo {

    private long user_title_id;

    public long getUser_title_id() {
        return user_title_id;
    }

    public void setUser_title_id(long user_title_id) {
        this.user_title_id = user_title_id;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public float getD_limit() {
        return d_limit;
    }

    public void setD_limit(float d_limit) {
        this.d_limit = d_limit;
    }

    private String user_title;
    private float d_limit;
}
