package com.help.server.util;

import javax.print.attribute.standard.PrinterInfo;
import java.util.Random;

//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.TypeReference;
//import com.help.server.domain.tables.Da_Show_Corp_City;
//import com.help.server.domain.tables.Da_show_waybill_province;
//
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.util.StringUtils;
//
//import java.text.DecimalFormat;
//import java.util.*;
//
///**
// * Created by houlongbin on 2016/11/11.
// */
public class CommonUtil {

    public static String getRandNumPhone(int length){

        final int maxNum = 10;
        int i; //生成的随机数
        int count = 0; //生成的随机数的长度
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();

        while (count < length) {
            //生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); //生成的数最大为1-62

            if ((i >= 0) && (i < str.length)) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();

    }
    public static String genRandomOrder(String userId) {

        final int maxNum = 20;
        int i; //生成的随机数
        int count = 0; //生成的随机数的长度
        char[] str = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z', '-', '.',
        };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();

        while (count < 20 - userId.length() - 1) {
            //生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); //生成的数最大为1-66

            if ((i >= 0) && (i < str.length)) {
                pwd.append(str[i]);
                count++;
            }
        }

        String token = userId +  pwd.toString().toUpperCase();
        return token;
    }
    public static String genRandomOrderEX(String helpType,String userId) {

        final int maxNum = 20;
        int i; //生成的随机数
        int count = 0; //生成的随机数的长度
        char[] str = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z', '-', '.',
        };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();

        while (count < 10 - userId.length() - 1) {
            //生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); //生成的数最大为1-66

            if ((i >= 0) && (i < str.length)) {
                pwd.append(str[i]);
                count++;
            }
        }

        String token = helpType + userId +  pwd.toString().toUpperCase();
        return token;
    }
    public static void main(String[] args) {
        String sss = genRandomOrderEX("S","20");
        System.out.println(sss);
    }
}

