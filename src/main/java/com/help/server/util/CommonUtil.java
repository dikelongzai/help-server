package com.help.server.util;

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

        String token = "P"+userId +  pwd.toString().toLowerCase();
        return token;
    }
}
//    private static final Log log = LogFactory.getLog(CommonUtil.class);
//    /** 百度城市经纬度对应关系 */
//    static final String BAIDU_CITY="{\"citys\":[{\"name\":\"延寿\",\"value\":[128.331644,45.451897,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"临江\",\"value\":[126.918087,41.811979,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"嘉兴\",\"value\":[120.755486,30.746129,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"四平\",\"value\":[124.350398,43.16642,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"营口\",\"value\":[122.235418,40.667012,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"密云\",\"value\":[116.801346,40.35874,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"威海\",\"value\":[122.12042,37.513068,32],\"symbolSize\":3,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"杭州\",\"value\":[120.15507,30.274085,10],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"集安\",\"value\":[126.194031,41.125307,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"贵阳\",\"value\":[106.630154,26.647661,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"抚顺\",\"value\":[123.957208,41.880872,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"海门\",\"value\":[121.181615,31.871173,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"珠海\",\"value\":[113.576726,22.270715,9],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"河北\",\"value\":[114.475704,38.584854,-19],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"深圳\",\"value\":[114.057868,22.543099,14],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"黄浦\",\"value\":[121.484443,31.231763,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"蓬莱\",\"value\":[120.758848,37.810661,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"吉林\",\"value\":[126.549572,43.837883,-364],\"symbolSize\":14,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"甘肃\",\"value\":[103.826308,36.059421,-2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"龙井\",\"value\":[129.427066,42.766311,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"茂名\",\"value\":[110.925456,21.662999,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"丹东\",\"value\":[124.354707,40.0005,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"晋中\",\"value\":[112.752695,37.687024,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"浙江\",\"value\":[120.152792,30.267447,-2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"海城\",\"value\":[122.685217,40.882377,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"溆浦\",\"value\":[110.594921,27.908281,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"北京\",\"value\":[116.407526,39.90403,-14],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"铁岭\",\"value\":[123.726166,42.223769,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"大同\",\"value\":[113.61244,40.040295,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"金坛\",\"value\":[119.597897,31.723247,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"齐齐哈尔\",\"value\":[126.661669,45.742347,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"咸阳\",\"value\":[108.708991,34.329605,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"四川\",\"value\":[104.075931,30.651652,-5],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"福田\",\"value\":[114.055036,22.52153,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"盘锦\",\"value\":[122.070714,41.119997,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"中山\",\"value\":[113.392782,22.517646,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"福建\",\"value\":[119.295144,26.10078,-1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"泰顺\",\"value\":[119.717649,27.556884,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"宝山\",\"value\":[131.401589,46.577167,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"庆安\",\"value\":[127.507825,46.880102,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"海淀\",\"value\":[116.298056,39.959912,32],\"symbolSize\":3,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"大兴\",\"value\":[116.341395,39.726929,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"桦川\",\"value\":[130.719081,47.023001,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"惠州\",\"value\":[114.416196,23.111847,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"青岛\",\"value\":[120.38264,36.067082,52],\"symbolSize\":3,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"朝阳\",\"value\":[116.443108,39.92147,17],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"沈阳\",\"value\":[123.431475,41.805698,41],\"symbolSize\":3,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"菏泽\",\"value\":[115.480656,35.23375,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"南通\",\"value\":[120.894291,31.980172,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"南充\",\"value\":[106.110698,30.837793,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"双城\",\"value\":[126.312745,45.383263,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"南京\",\"value\":[118.796877,32.060255,17],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"新疆\",\"value\":[87.627704,43.793026,-2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"成都\",\"value\":[104.066541,30.572269,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"陕西\",\"value\":[108.954239,34.265472,-2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"黄岛\",\"value\":[120.04619,35.872664,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"温州\",\"value\":[120.699367,27.994267,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"石家庄\",\"value\":[114.51486,38.042307,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"邢台\",\"value\":[114.504844,37.070589,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"赣州\",\"value\":[114.93503,25.831829,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"义乌\",\"value\":[120.075058,29.306841,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"南昌\",\"value\":[115.858198,28.682892,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"闵行\",\"value\":[121.381709,31.112813,18],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"长宁\",\"value\":[121.424624,31.220367,7],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"道里\",\"value\":[126.616957,45.755777,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"乳山\",\"value\":[121.539765,36.919816,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"双流\",\"value\":[103.923648,30.574473,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"广州\",\"value\":[113.264435,23.129163,13],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"西城\",\"value\":[116.365868,39.912289,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"佳木斯\",\"value\":[130.318917,46.799923,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"皇姑\",\"value\":[123.44197,41.824796,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"榆树\",\"value\":[126.533146,44.840288,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"临汾\",\"value\":[111.518976,36.088005,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"上海\",\"value\":[121.473701,31.230416,44],\"symbolSize\":3,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"内蒙古\",\"value\":[111.765618,40.817498,-23],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"尚志\",\"value\":[128.009895,45.209586,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"湖里\",\"value\":[118.146769,24.512905,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"台州\",\"value\":[121.420757,28.656386,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"潍坊\",\"value\":[119.161756,36.706774,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"苏州\",\"value\":[120.585316,31.298886,14],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"房山\",\"value\":[116.143267,39.749144,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"即墨\",\"value\":[120.447128,36.389639,15],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"舒兰\",\"value\":[126.965607,44.406106,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"延吉\",\"value\":[129.508946,42.891255,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"三河\",\"value\":[117.078295,39.982718,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"大连\",\"value\":[121.614682,38.914003,40],\"symbolSize\":3,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"辉南\",\"value\":[126.046912,42.684993,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"无锡\",\"value\":[120.31191,31.49117,14],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"常州\",\"value\":[119.973987,31.810689,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"广西\",\"value\":[108.327546,22.815478,-1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"泉州\",\"value\":[118.675676,24.874132,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"昌平\",\"value\":[116.231204,40.22066,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"海阳\",\"value\":[121.158434,36.776378,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"郑州\",\"value\":[113.625368,34.7466,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"东城\",\"value\":[116.416357,39.928353,10],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"黄骅\",\"value\":[117.330048,38.371383,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"武侯\",\"value\":[104.04339,30.641982,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"鸡东\",\"value\":[131.12408,45.260412,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"龙口\",\"value\":[120.477813,37.646108,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"汤原\",\"value\":[129.905072,46.730706,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"湖北\",\"value\":[114.341862,30.546498,-4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"克拉玛依\",\"value\":[84.889207,45.579889,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"厦门\",\"value\":[118.089425,24.479834,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"哈尔滨\",\"value\":[126.534967,45.803775,8],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"秦皇岛\",\"value\":[119.600493,39.935385,7],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"江苏\",\"value\":[118.763232,32.061707,-1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"常熟\",\"value\":[120.752481,31.654376,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"烟台\",\"value\":[121.447935,37.463822,24],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"和平\",\"value\":[117.21451,39.116949,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"环翠\",\"value\":[122.123444,37.501991,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"宣武门外东大街\",\"value\":[116.378888,39.899332,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"张家港\",\"value\":[120.553284,31.870367,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"临安\",\"value\":[119.724733,30.233873,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"延安\",\"value\":[109.489727,36.585455,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"天津\",\"value\":[117.200983,39.084158,28],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"城阳\",\"value\":[120.39631,36.307064,15],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"石景山\",\"value\":[116.222982,39.906611,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"长沙\",\"value\":[112.938814,28.228209,5],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"安徽\",\"value\":[117.284923,31.861184,-1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"昆山\",\"value\":[120.980737,31.385598,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"徐汇\",\"value\":[121.436525,31.188523,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"东港\",\"value\":[124.152705,39.863008,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"廊坊\",\"value\":[116.683752,39.538047,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"鞍山\",\"value\":[122.994329,41.108647,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"海陵\",\"value\":[119.919425,32.491016,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"黑龙江\",\"value\":[126.661669,45.742347,-198],\"symbolSize\":8,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"西藏\",\"value\":[91.117212,29.646923,-1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"河南\",\"value\":[113.274379,34.445122,0],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"湖南\",\"value\":[112.98381,28.112444,-1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"佛山\",\"value\":[113.121416,23.021548,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"珲春\",\"value\":[130.366036,42.862821,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"扬州\",\"value\":[119.412966,32.39421,5],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"日照\",\"value\":[119.526888,35.416377,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"唐山\",\"value\":[118.180194,39.630867,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"同江\",\"value\":[132.510919,47.642707,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"荣成\",\"value\":[122.486658,37.16516,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"虎林\",\"value\":[132.93721,45.762686,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"武汉\",\"value\":[114.305393,30.593099,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"合肥\",\"value\":[117.227239,31.820587,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"荆州\",\"value\":[112.239741,30.335165,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"丰台\",\"value\":[116.287149,39.858427,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"山东\",\"value\":[117.020359,36.66853,-6],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"舟山\",\"value\":[122.207216,29.985295,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"连云港\",\"value\":[119.221611,34.596653,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"西安\",\"value\":[108.940175,34.341568,3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"济南\",\"value\":[117.12,36.651216,4],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"绵阳\",\"value\":[104.679114,31.46745,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"辽宁\",\"value\":[123.42944,41.835441,-58],\"symbolSize\":3,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"山西\",\"value\":[112.562398,37.873532,-3],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#58B3CC\"}}},{\"name\":\"呼和浩特\",\"value\":[111.749181,40.842585,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"河西\",\"value\":[117.223372,39.109563,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"兴和\",\"value\":[113.834173,40.872301,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"重庆\",\"value\":[106.551557,29.56301,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"胶州\",\"value\":[120.033382,36.26468,5],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"宁波\",\"value\":[121.550357,29.874557,10],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"滨海\",\"value\":[119.820831,33.990334,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"太原\",\"value\":[112.548879,37.87059,2],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"鸡西\",\"value\":[130.969333,45.295075,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"兰山\",\"value\":[118.347707,35.051729,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"阳泉\",\"value\":[113.580519,37.856972,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"勃利\",\"value\":[130.592171,45.755063,1],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}},{\"name\":\"长春\",\"value\":[125.323544,43.817072,8],\"symbolSize\":2,\"itemStyle\":{\"normal\":{\"color\":\"#F58158\"}}}]}";
//    static final JSONArray BAYDU_CITY_JSON=JSON.parseObject(BAIDU_CITY).getJSONArray("citys");
//    static final String POINT_E6="[{\"深圳\":[114.11819,22.55197]}{\"广州\":[113.26006,23.13399]}{\"东莞\":[113.75541,23.04873]}{\"惠州\":[114.42965,23.11749]}{\"潮州\":[116.61828,23.65934]}{\"茂名\":[110.91773,21.65274]}{\"清远\":[113.04748,23.68204]}{\"江门\":[113.10313,22.60173]}{\"阳江\":[111.97772,21.8606]}{\"肇庆\":[112.45882,23.05167]}{\"汕头\":[116.68803,23.35911]}{\"揭阳\":[116.35886,23.53794]}{\"佛山\":[113.11934,23.03751]}{\"珠海\":[113.57373,22.29112]}{\"钦州\":[108.6204,21.96307]}{\"贺州\":[111.54023,24.41308]}{\"贵港\":[109.59715,23.09139]}{\"玉林\":[110.14988,22.62932]}{\"崇左\":[107.35153,22.41841]}{\"南宁\":[108.33199,22.82527]}{\"防城港\":[108.34266,21.61852]}{\"桂林\":[110.2843,25.2771]}{\"梧州\":[111.30054,23.47352]}{\"柳州\":[109.40536,24.31427]}{\"贵阳\":[106.7139,26.58719]}{\"遵义\":[106.91718,27.69142]}{\"三亚\":[109.50617,18.25401]}{\"海口\":[110.35734,20.04501]}{\"昆明\":[102.71037,25.03736]}{\"大理\":[100.22252,25.59422]}{\"玉溪\":[102.54419,24.35733]}{\"曲靖\":[103.79677,25.50314]}{\"成都\":[104.07043,30.66431]}{\"内江\":[105.0553,29.58249]}{\"重庆\":[106.55355,29.56029]}{\"北京\":[116.40969,39.89945]}{\"天津\":[117.2001,39.14307]}{\"齐齐哈尔\":[123.9592,47.34192]}{\"乌兰察布\":[113.10717,41.03318]}{\"包头\":[109.83404,40.65486]}{\"银川\":[106.28956,38.47426]}{\"西宁\":[101.80335,36.62695]}{\"青岛\":[120.35483,36.10582]}{\"济南\":[116.97009,36.65319]}{\"烟台\":[121.38051,37.54736]}{\"济宁\":[116.58144,35.41508]}{\"潍坊\":[119.11343,36.71303]}{\"德州\":[116.29707,37.45018]}{\"东营\":[118.66868,37.43247]}{\"枣庄\":[117.56665,34.85406]}{\"太原\":[112.59966,37.87319]}{\"西安\":[108.9976,34.30017]}{\"渭南\":[109.50427,34.50081]}{\"宝鸡\":[107.14178,34.37353]}{\"石河子\":[86.07728,44.30433]}{\"乌鲁木齐\":[87.61988,43.80553]}{\"唐山\":[118.20599,39.63617]}{\"石家庄\":[114.52002,38.04502]}{\"邯郸\":[114.49076,36.61044]}{\"张家口\":[114.88464,40.81864]}{\"邢台\":[114.49854,37.07036]}{\"衡水\":[115.6864,37.7313]}{\"沧州\":[116.84271,38.30781]}{\"保定\":[115.49815,38.87262]}{\"承德\":[117.93583,40.96843]}{\"郑州\":[113.67769,34.76714]}{\"商丘\":[115.66306,34.44623]}{\"安阳\":[114.35347,36.09605]}{\"新乡\":[113.86393,35.3056]}{\"许昌\":[113.82251,34.02552]}{\"牡丹江\":[129.61041,44.57863]}{\"大庆\":[125.00504,46.59626]}{\"长春\":[125.32512,43.8929]}{\"兰州\":[103.77926,36.07967]}{\"沈阳\":[123.38035,41.81073]}{\"大连\":[121.62329,38.92425]}{\"盘锦\":[122.04954,41.19227]}{\"阜新\":[121.6496,42.01213]}{\"上海\":[121.4755,31.23385]}{\"武汉\":[114.28742,30.58965]}{\"荆州\":[112.2809,30.32097]}{\"鄂州\":[114.87261,30.40375]}{\"黄冈\":[114.86834,30.45047]}{\"荆门\":[112.19408,31.04024]}{\"宜昌\":[111.29069,30.70277]}{\"长沙\":[112.99369,28.19602]}{\"湘潭\":[112.9006,27.86387]}{\"怀化\":[109.96634,27.55401]}{\"郴州\":[113.03127,25.79673]}{\"岳阳\":[113.11599,29.37775]}{\"株洲\":[113.12818,27.83166]}{\"益阳\":[112.34484,28.58542]}{\"衡阳\":[112.60608,26.89977]}{\"常德\":[111.69352,29.03453]}{\"邵阳\":[111.47724,27.24231]}{\"合肥\":[117.29172,31.86646]}{\"蚌埠\":[117.38358,32.94222]}{\"六安\":[116.50035,31.75806]}{\"亳州\":[115.78067,33.87526]}{\"滁州\":[118.31417,32.30298]}{\"安庆\":[117.05042,30.52774]}{\"黄山\":[118.32944,29.72064]}{\"巢湖\":[117.86134,31.60151]}{\"马鞍山\":[122.99014,41.10736]}{\"马鞍山\":[118.4995,31.68128]}{\"莆田\":[119.00669,25.43473]}{\"龙岩\":[117.02706,25.09539]}{\"宁德\":[119.52011,26.66696]}{\"厦门\":[118.09924,24.46792]}{\"南平\":[118.17304,26.64459]}{\"福州\":[119.30491,26.07912]}{\"三明\":[117.63373,26.26706]}{\"泉州\":[118.59437,24.91486]}{\"南京\":[118.79349,32.05892]}{\"苏州\":[120.63331,31.31316]}{\"无锡\":[120.31118,31.58131]}{\"淮安\":[119.01645,33.59535]}{\"扬州\":[119.44767,32.39429]}{\"徐州\":[117.18011,34.2644]}{\"宿迁\":[118.29463,33.96095]}{\"南通\":[120.85978,32.02597]}{\"泰州\":[119.91498,32.49174]}{\"盐城\":[120.13363,33.38395]}{\"连云港\":[119.17806,34.601]}{\"常州\":[119.97573,31.79847]}{\"新余\":[114.91368,27.82167]}{\"赣州\":[114.93688,25.85251]}{\"鹰潭\":[117.02708,28.24294]}{\"南昌\":[115.91655,28.66246]}{\"吉安\":[114.98511,27.11408]}{\"杭州\":[120.1673,30.25748]}{\"温州\":[120.65878,28.02152]}{\"衢州\":[118.86995,28.93888]}{\"丽水\":[119.91244,28.4558]}{\"绍兴\":[120.58919,30.00989]}{\"宁波\":[121.56081,29.8801]}{\"金华\":[119.67453,29.11044]}{\"温州\":[120.65878,28.02152]}{\"台州\":[121.43664,28.68495]}]";
//    static final JSONArray FOR_US=JSON.parseArray(POINT_E6);
//    /**
//     * 根据城市名称获取echarts jsonarray
//     * @param city
//     * @return
//     */
//    public static JSONArray getBaiDuLanAndLo(String city){
//
//        JSONArray jsonArray=new JSONArray();
//        Iterator<Object> iterator=BAYDU_CITY_JSON.iterator();
//        while (iterator.hasNext()){
//            JSONObject json= (JSONObject)iterator.next();
//            if(json.getString("name").equals(city)){
//                jsonArray=json.getJSONArray("value");
//                if(jsonArray.size()>2){
//                    log.info("getBaiDuLanAndLo city="+city+" jsonArray="+jsonArray.toJSONString());
//                    jsonArray.remove(2);//删除最后一个
//
//                }
//                break;
//            }
//
//        }
//        return jsonArray;
//    }
//    public static void getHot(List<Da_show_waybill_province> list){
//        log.info("getEcharsAllDataJson getHot="+ JSON.toJSONString(list));
//    }
//
//    /**
//     * 获取每个城市ItemStyle随机填充颜色
//     * @return
//     */
//    public static JSONObject getItemStyle(){
//        //1到2的随机数
//        int romInt=(int)(1+Math.random()*(2-1+1));
//        JSONObject returnJson=new JSONObject();
//        JSONObject returnTmp=new JSONObject();
//        if(romInt==1){
//            returnTmp.put("color","#F58158");
//        }else{
//            returnTmp.put("color","#58B3CC");
//        }
//
//        returnJson.put("normal",returnTmp);
//        return returnJson;
//    }
//    /**
//     * 拼接百度echarts数据
//     * @param list
//     * @return
//     */
//    public static  JSONObject getEcharsAllDataJson( List<Da_show_waybill_province> list,List<Da_show_waybill_province> listFrom,List<Da_show_waybill_province> toFrom){
//        JSONObject jsonReturn=new JSONObject();
//        jsonReturn.put("citys",getBillBaiDuCitys(listFrom,toFrom));
//        JSONArray jsonMoveLines=new JSONArray();
//        //{"fromName":"黑龙江","toName":"胶州","coords":[[126.661669,45.742347],[120.033382,36.26468]]}
//        for(Da_show_waybill_province bean:list){
//            if(bean.getFrom_pro().equals("广东")){
//                bean.setFrom_pro("深圳");
//            }
//            if(bean.getTo_pro().equals("广东")){
//                bean.setFrom_pro("深圳");
//            }
//                JSONObject json=new JSONObject();
//                json.put("fromName",bean.getFrom_pro());
//                json.put("toName",bean.getTo_pro());
//                JSONArray coordsJson=new JSONArray();
//                JSONArray fromJson=getBaiDuLanAndLo(bean.getFrom_pro());
//                log.info("fromJson="+fromJson.toJSONString());
//                JSONArray toJson=getBaiDuLanAndLo(bean.getTo_pro());
//                log.info("toJson="+toJson.toJSONString());
//                coordsJson.add(fromJson.toArray());
//                coordsJson.add(toJson.toArray());
//                json.put("coords",coordsJson.toArray());
//                jsonMoveLines.add(json);
//                log.info("tmp="+json);
//
//
//        }
//        jsonReturn.put("moveLines",jsonMoveLines);
//        log.info("getEcharsAllDataJson retult="+jsonReturn.toJSONString());
//        return jsonReturn;
//
//    }
//
//    /**
//     * 获取 echarts 城市json
//     * @param listFrom
//     * @param toFrom
//     * @return
//     */
//    public static  JSONArray getBillBaiDuCitys(List<Da_show_waybill_province> listFrom,List<Da_show_waybill_province> toFrom){
//        JSONArray returnJson=new JSONArray();
//        List<String> list=new ArrayList<String>();
//        for(Da_show_waybill_province from:listFrom){
//            JSONObject json=new JSONObject();
//            json.put("name",from.getFrom_pro());
//            if(from.getFrom_pro().equals("广东")){
//                from.setFrom_pro("深圳");
//            }
//            JSONArray fromJson=getBaiDuLanAndLo(from.getFrom_pro());
//            int hot=getHotByNum(from.getNum());
//            fromJson.add(hot);
//            json.put("value",fromJson.toArray());
//            json.put("symbolSize",hot);
//            json.put("itemStyle",getItemStyle());
//            returnJson.add(json);
//            list.add(from.getFrom_pro());
//        }
//        for(Da_show_waybill_province from:toFrom){
//            if(from.getTo_pro().equals("广东")){
//                from.setTo_pro("广州");
//            }
//            if(!list.contains(from.getTo_pro())){
//                JSONObject json=new JSONObject();
//                json.put("name",from.getFrom_pro());
//
//                JSONArray fromJson=getBaiDuLanAndLo(from.getTo_pro());
//                int hot=getHotByNum(from.getNum());
//                fromJson.add(hot);
//                json.put("value",fromJson.toArray());
//                json.put("symbolSize",hot);
//                json.put("itemStyle",getItemStyle());
//                returnJson.add(json);
//                list.add(from.getTo_pro());
//            }
//
//        }
//
//        return returnJson;
//
//    }
//
//    /**
//     * 根据百度地图symbolSize和我们的数据比对除以500转换成symbolSize
//     * @param num
//     * @return
//     */
//    public static int getHotByNum(int num){
//        int numReturn=(num/500)+1;
//        if(numReturn>15){
//            numReturn=15;
//        }
//        return numReturn;
//
//    }
//
//
//
//    /**
//     * 经纬度处理防止地图标注重叠
//     *
//     * @param jsonArrayPoint
//     * @return
//     */
//    public static JSONArray getSubDel(JSONArray jsonArrayPoint, String city) {
//        Iterator<Object> iterator = jsonArrayPoint.iterator();
//        JSONArray reTurnJson = new JSONArray();
//        int i = 0;
//        while (iterator.hasNext()) {
//            if (city.equals("江苏")) {
//
//                Double doubleTmp = Double.valueOf(iterator.next().toString());
//
//
//                reTurnJson.add((doubleTmp + 0.5));
//
//            } else if (city.equals("安徽")) {
//
//                Double doubleTmp = Double.valueOf(iterator.next().toString());
//
//
//                if (i == 1) {
//
//                    reTurnJson.add((doubleTmp - 0.02));
//                } else {
//                    reTurnJson.add((doubleTmp));
//                }
//            } else if (city.equals("深圳")) {
//
//                Double doubleTmp = Double.valueOf(iterator.next().toString());
//
//
//                if (i == 1) {
//
//                    reTurnJson.add((doubleTmp + 2));
//                } else {
//                    reTurnJson.add((doubleTmp));
//                }
//            } else {
//                {
//                    Double doubleTmp = Double.valueOf(iterator.next().toString());
//                    if (i == 1) {
//                        reTurnJson.add((doubleTmp - getRondom()));
//                    } else {
//                        reTurnJson.add((doubleTmp - getRondom()));
//                    }
//                }
//            }
//
//            i++;
//        }
//
//        return reTurnJson;
//    }
//
//    public static  double getRondom(){
//        int romInt=(int)(1+Math.random()*(9-1+1));
//        return romInt/100;
//
//    }
//
//
//
//
//    /**
//     * 获取网店分布
//     * @return
//     */
//     public static JSONObject getForUs(){
//         JSONObject returnJson=new JSONObject();
//         JSONArray jsonArray=new JSONArray();
//
//         Iterator<Object> iterator=FOR_US.iterator();
//         while (iterator.hasNext()){
//             JSONObject tmp=new JSONObject();
//             JSONObject json= (JSONObject)iterator.next();
//             for (Map.Entry<String, Object> entry : json.entrySet()) {
//                 tmp.put("name",entry.getKey());
//
//                JSONArray jsonArr=JSON.parseArray(entry.getValue().toString());
//                 jsonArr.add(1);
//                 tmp.put("value",jsonArr.toArray());
//             }
//             jsonArray.add(tmp);
//
//
//         }
//
//         returnJson.put("point",jsonArray.toArray());
//         log.info("getForUs retult="+returnJson.toJSONString());
//         return returnJson;
//     }
//    /**
//     * 把输入地址中的省份去掉
//     *
//     * @param address
//     *            地址输入
//     * @return 不包含省份的地址
//     */
//    public static String removeProvince(String address) {
//        if (!StringUtils.isEmpty(address)) {
//            int index = address.indexOf("省");
//            address = address.substring(index + 1);
//        }
//        return address;
//    }
//
//    /**
//     * whether str is a number
//     * @param str
//     * @return
//     */
//    public static boolean isNumeric(String str){
//        return str.matches("-?[0-9]+.*[0-9]*");
//    }
//
//    /**
//     * change a number string to a percent
//     * @param str
//     * @return
//     */
//    public static String exchangePercent(String str){
//        if(CommonUtil.isNumeric(str)&&str.indexOf("%")==-1){
//            String sytle = "0%";
//            if(str.length()>4){
//                sytle = "0.0%";
//            }
//            DecimalFormat df = new DecimalFormat(sytle);
//            double dou =Double.parseDouble(str);
//            str = df.format(dou);
//        }
//        return str;
//    }
//
//}
