package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/2/7.
 */
public class GetUserCommonQuestInfo {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    private String reply_content;

}
