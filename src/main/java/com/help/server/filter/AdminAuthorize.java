package com.help.server.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.UserMapper;
import com.help.server.model.User;
import com.help.server.security.UnloginException;
import com.help.server.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class AdminAuthorize implements Filter {
    private static final Log log = LogFactory.getLog(AdminAuthorize.class);

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            if (httpRequest.getRequestURI().contains("logincheck")) {
                boolean checkUser=false;
                try {
                    checkUser=checkUser(httpRequest);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if (checkUser) {
                    res.sendRedirect("/admin/main");
                    //chain.doFilter(request, response);
                } else {
                    throw new UnloginException();
                }

            } else {

                ServletUtil.checkLogin(httpRequest);
                chain.doFilter(request, response);

            }
        } catch (UnloginException e) {
            res.sendRedirect("/login");
        }


    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    protected boolean checkUser(HttpServletRequest httpRequest) throws Exception {
        boolean chekUser = false;
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("pwd");
        if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password)) {
            log.info("check user username=" + username + ";pwd=" + password);
            List<Object> params = new ArrayList<Object>();
            params.add(username);
            params.add(StringUtil.getMD5(password).trim());
            List<Map<String, Object>> list = JdbcUtils.getInstatance().findModeResult(SqlConstant.SQL_CHECK_USER, params);
            if (list.size() > 0) {
                User user = new User();
                user.setId(Long.parseLong(list.get(0).get("id").toString()));
                user.setUsername(username);
                user.setIp(httpRequest.getRemoteAddr());
                HttpSession session = httpRequest.getSession();
                session.setAttribute("KEY_USER_BIND_SESSION", user);
                chekUser=true;
            }
        }
        return chekUser;
    }

}
