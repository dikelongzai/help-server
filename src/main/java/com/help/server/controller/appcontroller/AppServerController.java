package com.help.server.controller.appcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.FrieghtMapper;
import com.help.server.domain.requestbean.GetUserInfoReq;
import com.help.server.domain.responsebean.GetUserInfoResp;
import com.help.server.domain.tables.User_Member;
import com.help.server.service.AppService;
import com.help.server.util.Base64Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户分布
 * Created by houlongbin on 2016/11/11.
 *
 */
@Controller
@RequestMapping("appserver")
public class AppServerController{
    private static final Log log = LogFactory.getLog(AppServerController.class);
    @Autowired
    private AppService appService;
    @Autowired
    private AppServerMapper appServerMapper;

    @RequestMapping(value = "10001", method = RequestMethod.GET)
    /**
     * 运单流向
     */
    @ResponseBody
    public JSONObject getuserInfo(@RequestParam(value="p") String inputStr, HttpServletRequest request) {
        String inputInt =request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserInfoReq getUserInfoReq = JSON.parseObject(msgBody, GetUserInfoReq.class);
        long userId = Long.parseLong(getUserInfoReq.getUid());
        User_Member user_member = appServerMapper.getUserInfo(userId);

        GetUserInfoResp getUserInfoResp= new GetUserInfoResp();
        JSONObject jsonObject = new JSONObject();
        jsonObject.getJSONObject(inputInt);
        return  jsonObject;
       // List<Da_show_waybill_province> list=frieghtMapper.findTopNBill();
        //return CommonUtil.getEcharsAllDataJson(list,frieghtMapper.findFromHost(),frieghtMapper.findToHost());
    }
//    @RequestMapping("waybillhour")
//    /**
//     * 运单小时展示
//     */
//    @ResponseBody
//    public List<Da_show_waybill_province> waybillhour() {
//        System.out.println("-------------->"+JSON.toJSON(frieghtMapper.findWayBillHour()));
//        return frieghtMapper.findWayBillHour();
//    }
//    @RequestMapping(value = "GetGoodLine", method = RequestMethod.GET)
//    /**
//     * 货源
//     */
//    @ResponseBody
//    public JSONObject GetGoodLine(@RequestParam(value="name") String inputStr, HttpServletRequest request) {
//
//        String inputInt =request.getParameter("name");
//        return freightService.getGoodLine(inputInt);
//    }
//    @RequestMapping("GetTopGood")
//    /**
//     * 货源
//     */
//    @ResponseBody
//    public JSONObject GetTopGood() {
//        System.out.println("-------------->"+JSON.toJSON(frieghtMapper.GetTopGood()));
//        return CommonUtil.getTopGood(frieghtMapper.GetTopGood());
//    }
//
//    @RequestMapping("GetUs")
//    /**
//     * 货源
//     */
//    @ResponseBody
//    public JSONObject getUs(){
//        System.out.println("-------------->"+JSON.toJSON(frieghtMapper.GetTopGood()));
//        return CommonUtil.getForUs();
//    }


}
