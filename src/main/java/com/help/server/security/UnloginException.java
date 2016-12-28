package com.help.server.security;

/**
 * Created by dikelongzai 15399073387@163.com on 2016-12-24
 * .
 */

public class UnloginException extends Exception
{
    private static final long serialVersionUID = 4826829570756949501L;
    private String requestUrl = "";

    public UnloginException()
    {
        super("可能您的帐户已在其他地方登录，或者是登录已过期。 ");
    }

    public UnloginException(String msg)
    {
        super(msg);
    }

    public UnloginException(String msg, String requestUrl)
    {
        super(msg);
        this.requestUrl = requestUrl;
    }

    public String getRequestUrl()
    {
        return this.requestUrl;
    }
}
