package com.help.server.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.help.server.util.Base64Util;
import com.help.server.util.MD5Util;
import com.help.server.util.ResultStatusCode;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class AppHTTPBasicAuthorizeAttribute implements Filter {
    public static final String mdkey="b8958c5f08fc208ad2def6736f5242ce";
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ResultStatusCode resultStatusCode = checkHTTPBasicAuthorize(request);
        if (resultStatusCode != ResultStatusCode.OK)
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write(resultStatusCode.toJson().toJSONString());
            return;
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    private ResultStatusCode checkHTTPBasicAuthorize(ServletRequest request)
    {
        try
        {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String param=httpRequest.getParameter("p");
            if (param != null)
            {
                JSONObject json= JSON.parseObject(Base64Util.decode(param));
                long st=json.getLong("st");
                String authorCode= MD5Util.encode(st+mdkey);
                String sign=json.getString("sign");
                if(authorCode.equals(sign)){
                    return ResultStatusCode.OK;
                }

            }
            return ResultStatusCode.AUTHOR_ERROR;
        }
        catch(Exception ex)
        {
            return ResultStatusCode.SERVICE_ERROR;
        }

    }
}
