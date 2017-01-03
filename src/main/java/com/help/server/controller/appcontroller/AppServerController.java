package com.help.server.controller.appcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.requestbean.GetUserInfoReq;
import com.help.server.domain.requestbean.UserLoginReq;
import com.help.server.domain.responsebean.GetUserInfoResp;
import com.help.server.domain.responsebean.GetUserWalletResp;
import com.help.server.domain.responsebean.UserMberInfo;
import com.help.server.domain.responsebean.WalletInfoBean;
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

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
     * 获取用户信息
     */
    @ResponseBody
    public JSONObject getuserInfo(@RequestParam(value="p") String inputStr, HttpServletRequest request) {
        String inputInt =request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserInfoReq getUserInfoReq = JSON.parseObject(msgBody, GetUserInfoReq.class);
        long userId = Long.parseLong(getUserInfoReq.getUid());
        User_Member user_member = appServerMapper.getUserInfo(userId);
        GetUserInfoResp getUserInfoResp= new GetUserInfoResp();
        UserMberInfo data = new UserMberInfo();
        if(user_member!=null){
            getUserInfoResp.setCode("C0001");
            getUserInfoResp.setMsg("ok");

            data.setUid(String.valueOf(user_member.getUser_id()));
            data.setAccount(user_member.getUser_phone());
            data.setAccount_type(user_member.getIs_activate());
            data.setImage_url(user_member.getUser_head_url());
            data.setName(user_member.getUser_name());
            data.setStatus(user_member.getIs_freeze());
            data.setTitle(String.valueOf(user_member.getTitle_id()));
            getUserInfoResp.setData(data);


        }else{
           getUserInfoResp.setCode("C0002");
           getUserInfoResp.setMsg("用户不存在！");
           getUserInfoResp.setData(data);
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserInfoResp);
        return  jsonObject;

    }
    @RequestMapping(value = "10002", method = RequestMethod.GET)
	 /**
     * 获取用户钱包
     */
    @ResponseBody
   public JSONObject getuserWallet(@RequestParam(value="p") String inputStr, HttpServletRequest request) {
       String inputInt =request.getParameter("p");
       String msgBody = Base64Util.decode(inputInt);
       GetUserInfoReq getUserInfoReq = JSON.parseObject(msgBody, GetUserInfoReq.class);
       long userId = Long.parseLong(getUserInfoReq.getUid());
       User_Member user_member = appServerMapper.getUserInfo(userId);

       GetUserWalletResp getUserWalletResp = new GetUserWalletResp();
       WalletInfoBean data = new WalletInfoBean();
       if(user_member!=null){
           getUserWalletResp.setCode("C0001");
           getUserWalletResp.setMsg("ok");
           data.setAccount(user_member.getUser_phone());
           data.setDynamic(user_member.getUdynamic_wallet());
           data.setUstatic(user_member.getUstatic_wallet());
           data.setFrozen(user_member.getUfrozen_wallet());
           data.setUid(user_member.getUser_id());
           getUserWalletResp.setData(data);
       }else {
           getUserWalletResp.setCode("C0002");
           getUserWalletResp.setCode("用户不存在");
           getUserWalletResp.setData(data);

       }
       JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserWalletResp);
       return  jsonObject;
   }
    @RequestMapping(value = "10003", method = RequestMethod.GET)
    /**
     * 获取用户登录
     */
    @ResponseBody
    public JSONObject userLogIn(@RequestParam(value="p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        UserLoginReq userLoginfoReq = JSON.parseObject(msgBody, UserLoginReq.class);
        String username = userLoginfoReq.getAccount();
        String pwd = userLoginfoReq.getPwd();
        int isok = appServerMapper.checkUser(username, pwd);
        GetUserInfoResp getUserInfoResp = new GetUserInfoResp();
        UserMberInfo data = new UserMberInfo();
        if (isok == 1) { //登录成功
            long userId = appServerMapper.getUserIDByaccount(username);
            User_Member user_member = appServerMapper.getUserInfo(userId);

            getUserInfoResp.setCode("C0001");
            getUserInfoResp.setMsg("ok");
            if (user_member != null) {

                data.setUid(String.valueOf(user_member.getUser_id()));
                data.setAccount(user_member.getUser_phone());
                data.setAccount_type(user_member.getIs_activate());
                data.setImage_url(user_member.getUser_head_url());
                data.setName(user_member.getUser_name());
                data.setStatus(user_member.getIs_freeze());
                data.setTitle(String.valueOf(user_member.getTitle_id()));
                getUserInfoResp.setData(data);

            } else {
                getUserInfoResp.setData(data);
            }

        } else { //登录失败
            getUserInfoResp.setCode("C0003");
            getUserInfoResp.setMsg("登录失败,用户名或者密码错误！");
            getUserInfoResp.setData(data);
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserInfoResp);
        return jsonObject;
    }
//    /**
//     * 运单小时展示
//     */
//    @ResponseBody
//    public List<Da_show_waybill_province> waybillhour() {
//        System.out.println("-------------->"+JSON.toJSON(frieghtMapper.findWayBillHour()));
//        return frieghtMapper.findWayBillHour();
//    }
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
