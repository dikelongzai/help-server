package com.help.server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.model.User;
import com.help.server.security.UnloginException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class ServletUtil {
    private Logger log = Logger.getLogger(ServletUtil.class);

    public static JSONObject getAppRequestParameters(HttpServletRequest request,
                                                     String[] unexpectParams) {
        JSONObject res = new JSONObject();
        try {
            Map m = request.getParameterMap();
            Iterator iterator = m.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if ((unexpectParams != null)
                        && (StringUtil.exists(key, unexpectParams, false, false)))
                    continue;
                String[] val = (String[]) m.get(key);
                if ((val != null) && (val.length == 1))
                    res.put(key, val[0]);
                else
                    res.put(key, val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static JSONObject getAppRequestParameters(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        String param = request.getParameter("p");
        if (param != null) {
            res = JSON.parseObject(Base64Util.decode(param));
        }
        return res;
    }

    public static User checkLogin(HttpServletRequest request) throws UnloginException {
        User user = getUser(request);
        if ((user == null) ) {
            StringBuffer sb = request.getRequestURL();
            throw new UnloginException("用户尚未登录，或者是登录已过期。 ", sb != null ? sb.toString() : "");
        }
        return user;
    }
    public static User getUser(HttpServletRequest request)
    {
        User u = (User)request.getSession().getAttribute("KEY_USER_BIND_SESSION");
        return u;
    }
}
