package com.help.server.service;

import com.alibaba.fastjson.JSONObject;
import com.help.server.util.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author houlongbin
 */
@Service
public class AdminService {
    private Logger log = Logger.getLogger(AdminService.class);

    public String getSearchLeaveMessageSql(JSONObject param) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(SqlConstant.SQL_BASE_LEAVING_MSG);
        int status=Integer.valueOf(param.getString("status"));
        //{"et":"","page":"1","st":"","status":"0"}
        if (!StringUtil.isEmpty(param.getString("st"))) {
            sqlBuffer.append(" and create_date>")
                    .append(DateUtil.getLongdate(param.getString("st")+ CommonConstant.START_DAY));
        }
        if (!StringUtil.isEmpty(param.getString("et"))) {
            sqlBuffer.append(" and create_date<=")
                    .append(DateUtil.getLongdate(param.getString("et")+CommonConstant.END_DAY));
        }
        if(status>=0){
            sqlBuffer.append(" and is_reply=")
                    .append(status);
        }
        log.info("getSearchLeaveMessageSql sql="+sqlBuffer.toString());
        return sqlBuffer.toString();
    }
    public JSONObject getLeaveMsg(JSONObject param)throws Exception{
       String sql=getSearchLeaveMessageSql(param);
        int cpage=Integer.valueOf(param.getString("page"));
        return JdbcUtils.getInstatance().getPageBySql(sql,cpage);
    }


}
