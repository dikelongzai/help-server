package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/7.
 */
public class LeavingMsgInfo {

    private String content;
    private  int status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage_st() {
        return message_st;
    }

    public void setMessage_st(String message_st) {
        this.message_st = message_st;
    }

    public String getReply_st() {
        return reply_st;
    }

    public void setReply_st(String reply_st) {
        this.reply_st = reply_st;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    private  String message_st;
    private  String reply_st;
    private  String reply_content;

}
