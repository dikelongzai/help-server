package com.help.server.domain.tables;

/**
 * Created by hou on 2017/1/7.
 */
public class Leaving_Msg {


    private  long create_date;

    public Leaving_Msg() {
    }

    private  long last_update;
    private  long leaving_id;

    public Leaving_Msg(long create_date, long last_update, char state, long leaving_id, long user_id, String msg_content, int is_reply, long msg_date) {
        this.create_date = create_date;
        this.last_update = last_update;
        this.leaving_id = leaving_id;
        this.user_id = user_id;
        this.msg_content = msg_content;
        this.is_reply = is_reply;
        this.msg_date = msg_date;
        this.state = state;
    }

    private  long user_id;



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

    public long getLeaving_id() {
        return leaving_id;
    }

    public void setLeaving_id(long leaving_id) {
        this.leaving_id = leaving_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public int getIs_reply() {
        return is_reply;
    }

    public void setIs_reply(int is_reply) {
        this.is_reply = is_reply;
    }

    public long getMsg_date() {
        return msg_date;
    }

    public void setMsg_date(long msg_date) {
        this.msg_date = msg_date;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public long getReply_date() {
        return reply_date;
    }

    public void setReply_date(long reply_date) {
        this.reply_date = reply_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private  String msg_content;
    private  String reply_content;
    private  int is_reply;
    private  long msg_date;
    private  long reply_date;
    private  long id;
    private  char state;
}
