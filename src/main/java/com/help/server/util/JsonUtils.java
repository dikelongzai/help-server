package com.help.server.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created by houlongbin on 2017/2/13.
 */
public class JsonUtils {
    @SuppressWarnings("unchecked")
    public static Map<String,Object> json2Map(String json){
        return JSON.parseObject(json, Map.class);
    }

    public static String obj2JsonString(Object obj){
        return JSON.toJSONString(obj);
    }
}
