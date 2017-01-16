
package com.help.server.controller.appcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.requestbean.*;
import com.help.server.domain.responsebean.*;
import com.help.server.domain.tables.*;
import com.help.server.service.AppService;
import com.help.server.util.Base64Util;
import com.help.server.util.CommonUtil;
import com.help.server.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户分布
 * Created by houlongbin on 2016/11/11.
 *
 */
@Controller
@RequestMapping("appserver")
public class AppServerController {

    private final String retCode = "C000";
    private final String retMsg = "ok";

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
    public JSONObject getuserInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserInfoReq getUserInfoReq = JSON.parseObject(msgBody, GetUserInfoReq.class);
        long userId = Long.parseLong(getUserInfoReq.getUid());
        User_MemberInfo user_member = appServerMapper.getUserInfo(userId);
        GetUserInfoResp getUserInfoResp = new GetUserInfoResp();
        UserMberInfo data = new UserMberInfo();
        if (user_member != null) {
            getUserInfoResp.setCode(retCode);
            getUserInfoResp.setMsg(retMsg);

            data.setUid(String.valueOf(user_member.getUser_id()));
            data.setAccount(user_member.getUser_phone());
            data.setAccount_type(user_member.getIs_activate());
            data.setImage_url(user_member.getUser_head_url());
            data.setName(user_member.getUser_name());
            data.setStatus(user_member.getIs_freeze());
            data.setTitle(String.valueOf(user_member.getTitle_id()));
            getUserInfoResp.setData(data);


        } else {
            getUserInfoResp.setCode("C0002");
            getUserInfoResp.setMsg("用户不存在！");
            getUserInfoResp.setData(data);
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserInfoResp);
        return jsonObject;

    }

    @RequestMapping(value = "10002")
    /**
     * 获取用户钱包
     */
    @ResponseBody
    public JSONObject getuserWallet(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserInfoReq getUserInfoReq = JSON.parseObject(msgBody, GetUserInfoReq.class);
        long userId = Long.parseLong(getUserInfoReq.getUid());
        User_MemberInfo user_member = appServerMapper.getUserInfo(userId);

        GetUserWalletResp getUserWalletResp = new GetUserWalletResp();
        WalletInfoBean data = new WalletInfoBean();
        if (user_member != null) {
            getUserWalletResp.setCode(retCode);
            getUserWalletResp.setMsg(retMsg);
            data.setAccount(user_member.getUser_phone());
            data.setDynamic(user_member.getUdynamic_wallet());
            data.setUstatic(user_member.getUstatic_wallet());
            data.setFrozen(user_member.getUfrozen_wallet());
            data.setUid(user_member.getUser_id());
            getUserWalletResp.setData(data);
        } else {
            getUserWalletResp.setCode("C0002");
            getUserWalletResp.setCode("用户不存在");
            getUserWalletResp.setData(data);

        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserWalletResp);
        return jsonObject;
    }

    @RequestMapping(value = "10003")
    /**
     * 获取用户登录
     */
    @ResponseBody
    public JSONObject userLogIn(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
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
            User_MemberInfo user_member = appServerMapper.getUserInfo(userId);

            getUserInfoResp.setCode(retCode);
            getUserInfoResp.setMsg(retMsg);
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

    @RequestMapping(value = "10004")
    /**
     * 获取用户登录
     */
    @ResponseBody
    public JSONObject RegisterUser(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        RegisterInfoReq registerinfoReq = JSON.parseObject(msgBody, RegisterInfoReq.class);
        RegisterInfo registerInfo = new RegisterInfo();
        RegisterInfoResp registerinfoResp = new RegisterInfoResp();
        long uid = 0;
        long referee_uid = 0;
        int ncount = appServerMapper.getUserCount(registerinfoReq.getTel());
        long nreferee = appServerMapper.getUserCount(registerinfoReq.getInvite());
        if (nreferee != 0) {
            referee_uid = appServerMapper.getUserIDByaccount(registerinfoReq.getInvite());
        }
        if (ncount != 0) {
            registerinfoResp.setCode("C0005");
            registerinfoResp.setMsg("用户已经注册");
            registerInfo.setUid(uid);
            registerInfo.setStatus(1);
            registerinfoResp.setData(registerInfo);
        } else {
            // 查找推荐人id
            //查找手机号是否注册。
            String idname = "user_id";
            appServerMapper.id_generator(idname);
            uid = appServerMapper.get_id_generator(idname);
            long curtimer = System.currentTimeMillis();
            User_Member regUserInfo = new User_Member();
            regUserInfo.setCreate_date(curtimer);
            regUserInfo.setLast_update(curtimer);
            regUserInfo.setState('N');
            regUserInfo.setIs_activate(0);
            regUserInfo.setUsable_code_num(0);
            regUserInfo.setUsed_code_num(0);
            regUserInfo.setTitle_id(0);
            regUserInfo.setUser_name(registerinfoReq.getTname());
            regUserInfo.setUser_login_pwd(registerinfoReq.getPwd());
            regUserInfo.setUser_phone(registerinfoReq.getTel());
            regUserInfo.setUser_referee_phone(registerinfoReq.getInvite());
            regUserInfo.setUser_id(uid);
            regUserInfo.setReferee_user_id(referee_uid);

            int nretcode = appServerMapper.registerUser(regUserInfo);

            if (nretcode == 1) {
                registerinfoResp.setCode(retCode);
                registerinfoResp.setMsg(retMsg);
            } else {
                registerinfoResp.setCode("C0006");
                registerinfoResp.setMsg("数据库操作失败！");
            }
            registerInfo.setUid(uid);
            registerInfo.setStatus(1);
            registerinfoResp.setData(registerInfo);
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(registerinfoResp);
        return jsonObject;
    }

    @RequestMapping(value = "10005")
    /**
     * 发送验证码
     */
    @ResponseBody
    public JSONObject SendValidateCode(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        sendValidateCodeReq sendValidateReq = JSON.parseObject(msgBody, sendValidateCodeReq.class);

        CommResp commResp = new CommResp();
        String randcode = CommonUtil.getRandNumPhone(4);
        long nCurTimer = System.currentTimeMillis();
        Validate_Code validate_code = new Validate_Code();
        validate_code.setUser_imei(sendValidateReq.getImei());
        validate_code.setUser_phone(sendValidateReq.getTel());
        validate_code.setCreate_date(nCurTimer);
        validate_code.setLast_update(nCurTimer);
        validate_code.setState('N');
        validate_code.setValidate_type(sendValidateReq.getType());
        validate_code.setValidate_code(randcode);
        long validate_id = 0;
        String idname = "validate_id";
        appServerMapper.id_generator(idname);
        validate_id = appServerMapper.get_id_generator(idname);
        validate_code.setValidate_id(validate_id);
        //查询是否存在
        String userphone = sendValidateReq.getTel();
        int validatetype = sendValidateReq.getType();
        String code = randcode;
        int ncount = appServerMapper.getValCodeCount(userphone, validatetype);
        if (ncount == 0) {
            //插入
            appServerMapper.insertValCode(validate_code);
        } else {
            appServerMapper.updateValCode(code, userphone, validatetype);
        }

        commResp.setCode(retCode);
        commResp.setMsg(retMsg);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }

    @RequestMapping(value = "10006")
    /**
     * 验证验证码协议
     */
    @ResponseBody
    public JSONObject VerifyCode(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        ValidateCodeReq validateCodeReq = JSON.parseObject(msgBody, ValidateCodeReq.class);
        CommResp commResp = new CommResp();

        String userphone = validateCodeReq.getTel();
        int validatetype = validateCodeReq.getType();
        String code = validateCodeReq.getVer_code();
        int nCount = appServerMapper.getValCodeCountByCode(userphone, validatetype, code);
        if (nCount > 0) {
            commResp.setCode(retCode);
            commResp.setMsg("OK");
        } else {
            commResp.setCode("C0007");
            commResp.setMsg("验证码校验失败！");
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }

    @RequestMapping(value = "10007")
    /**
     * 验证验证码协议
     */
    @ResponseBody
    public JSONObject SendActivationcode(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        SendActivationcodeReq sendActivationcodeReq = JSON.parseObject(msgBody, SendActivationcodeReq.class);
        CommResp commResp = new CommResp();

        int codeCount = sendActivationcodeReq.getCount();
        String username = sendActivationcodeReq.getFaccount();
        int nUserCount = appServerMapper.getUserCount(username);
        JSONObject jsonObject =null;
        if(nUserCount!=1){
            commResp.setCode("C10013");
            commResp.setMsg("用户信息不存在！");
            jsonObject = (JSONObject) JSON.toJSON(commResp);
            return jsonObject;
        }
        long fromuid = appServerMapper.getUserIDByaccount(username);
        int usable_code_num = appServerMapper.getUserCodeNum(fromuid);

        username = sendActivationcodeReq.getTaccount();
        nUserCount = appServerMapper.getUserCount(username);
        if(nUserCount!=1){
            commResp.setCode("C10013");
            commResp.setMsg("用户信息不存在！");
            jsonObject = (JSONObject) JSON.toJSON(commResp);
            return jsonObject;
        }
        long touid = appServerMapper.getUserIDByaccount(username);
        int nCount = appServerMapper.getUserCount(username);

        if (usable_code_num >= codeCount) { //可以分配
            appServerMapper.updateUserCodeNum_dec(codeCount, fromuid);
            appServerMapper.updateUserCodeNum_add(codeCount, touid);
            //交易激活码校验记录
            long ncurTimer = System.currentTimeMillis();
            Activate_Code activate_code = new Activate_Code();
            activate_code.setState('N');
            activate_code.setLast_update(ncurTimer);
            activate_code.setCreate_date(ncurTimer);
            activate_code.setCode_num(codeCount);
            activate_code.setFrom_uid(fromuid);
            activate_code.setTo_uid(touid);
            activate_code.setIs_from_admin(0);
            appServerMapper.insertActivateCode(activate_code);
            commResp.setCode(retCode);
            commResp.setMsg(retMsg);

        } else { //激活码不足，不能分配
            commResp.setCode("C0008");
            commResp.setMsg("激活码的数量不足！");
        }

        jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }

    @RequestMapping(value = "10008")
    /**
     * 验证验证码协议
     */
    @ResponseBody
    public JSONObject GetUserTema(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserTemaReq getUserTemaReq = JSON.parseObject(msgBody, GetUserTemaReq.class);

        GetUserTemaResp getUserTemaResp = new GetUserTemaResp();
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserTemaResp);
        return jsonObject;
    }

    @RequestMapping(value = "10009")
    /**
     * 验证验证码协议
     */
    @ResponseBody
    public JSONObject UserActivate(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        CommResp commResp = new CommResp();
        ActivationUserReq activationUserReq = JSON.parseObject(msgBody, ActivationUserReq.class);
        String from_phone = activationUserReq.getFaccount();
        String to_phone = activationUserReq.getTaccount();
        int ncount = appServerMapper.getUserCount(to_phone);
        if (ncount > 0) {
            appServerMapper.updateValUser(to_phone);
            commResp.setCode(retCode);
            commResp.setMsg(retMsg);
        } else {
            commResp.setCode("C0009");
            commResp.setMsg("手机号不是账号，不能激活！");
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }

    @RequestMapping(value = "10012")
    /**
     * 忘记密码
     */
    @ResponseBody
    public JSONObject forgetPwd(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        ForgetpwdReq forgetpwdReq = JSON.parseObject(msgBody, ForgetpwdReq.class);
        ForgetpwdResp forgetpwdResp = new ForgetpwdResp();
        ForgetpwdInfo forgetpwdInfo = new ForgetpwdInfo();

        String userphone = forgetpwdReq.getAccount();
        String user_referee_phone = forgetpwdReq.getInvite();
        String user_login_pwd = forgetpwdReq.getPwd();

        int nCount = appServerMapper.getUserExit(userphone,user_referee_phone);
        if(nCount>0){
            int nforget = appServerMapper.forgetValiCode(userphone,user_referee_phone,forgetpwdReq.getVer_code(),2);
            if(nforget>0){
                appServerMapper.updateUserPWd(user_login_pwd,userphone);
                long userId = appServerMapper.getUserIDByaccount(userphone);
                User_MemberInfo userinfo = appServerMapper.getUserInfo(userId);
                forgetpwdInfo.setUid(userId);
                if(userinfo.getIs_activate()==0){ //如果没有激活
                    forgetpwdInfo.setStatus(1);
                }else{
                    if(userinfo.getIs_freeze()==1){
                        forgetpwdInfo.setStatus(2);
                    }else{
                        forgetpwdInfo.setStatus(0);
                    }
                }
                forgetpwdResp.setData(forgetpwdInfo);
                forgetpwdResp.setCode(retCode);
                forgetpwdResp.setMsg(retMsg);
            }else{
                forgetpwdResp.setCode("C0011");
                forgetpwdResp.setMsg("验证码校验错误！");
                forgetpwdInfo.setUid(0);
                forgetpwdInfo.setStatus(0);
                forgetpwdResp.setData(forgetpwdInfo);
            }
        }else{
            forgetpwdResp.setCode("C0010");
            forgetpwdResp.setMsg("用户不存在，请重新输入！");
            forgetpwdInfo.setUid(0);
            forgetpwdInfo.setStatus(0);
            forgetpwdResp.setData(forgetpwdInfo);
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(forgetpwdResp);
        return jsonObject;

    }
    @RequestMapping(value = "10023")
    /**
     * 已匹配成功订单
     */
    @ResponseBody
    public JSONObject GetMaterOrder(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetMateOrderReq getMateOrderReq = JSON.parseObject(msgBody, GetMateOrderReq.class);
        GetMateOrderResp getMateOrderResp = new GetMateOrderResp();

        getMateOrderResp.setCode(retCode);
        getMateOrderResp.setMsg(retMsg);
        int ordertype = getMateOrderReq.getOrder_type();
        long rechargeuid = getMateOrderReq.getUid();
        List<Orders> orderList = appServerMapper.getOrderInfo(ordertype,rechargeuid);
        ArrayList<GetMaterOrderInfo> getMaterOrderList = new  ArrayList<GetMaterOrderInfo>();

        for(int i =0;i<orderList.size();i++){

            GetMaterOrderInfo getMaterOrderInfo = new GetMaterOrderInfo();
            Orders order = orderList.get(i);
            getMaterOrderInfo.setTo_type(2);
            getMaterOrderInfo.setFrom_money(order.getMoney_num());
            getMaterOrderInfo.setTo_money(order.getMoney_num());
            getMaterOrderInfo.setConfirm_st(DateUtil.dateLongToString(order.getConfirm_date()));
            getMaterOrderInfo.setFrom_order_num(order.getRecharge_order());
            getMaterOrderInfo.setFrom_st(DateUtil.dateLongToString(order.getFrom_date()));
            String userphone =order.getRecharge_phone();
            String name = appServerMapper.getUserName(userphone);
            getMaterOrderInfo.setFrom_tname(name);
            getMaterOrderInfo.setMatch_st(DateUtil.dateLongToString(order.getMatch_date()));
            getMaterOrderInfo.setTo_order_num(order.getWithdrawals_order());
            userphone = order.getWithdrawals_phone();
            name = appServerMapper.getUserName(userphone);
            getMaterOrderInfo.setTo_tname(name);
            getMaterOrderInfo.setVoucher_url(order.getRemittance_url());
            getMaterOrderInfo.setTo_st(DateUtil.dateLongToString(order.getTo_date()));
            getMaterOrderInfo.setFrom_type(1);
            getMaterOrderInfo.setOrder_type(order.getOrder_type());
            getMaterOrderList.add(getMaterOrderInfo);
        }
        getMateOrderResp.setData(getMaterOrderList);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getMateOrderResp);
        return jsonObject;
    }

    @RequestMapping(value = "10013")
    /**
     * 已匹配成功订单
     */
    @ResponseBody
    public JSONObject GetOrderByStatus(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        ByStatusGetOrderReq byStatusGetOrderReq = JSON.parseObject(msgBody, ByStatusGetOrderReq.class);
        ByStatusGetOrderResp byStatusGetOrderResp = new ByStatusGetOrderResp();

        byStatusGetOrderResp.setCode(retCode);
        byStatusGetOrderResp.setMsg(retMsg);
        int ordertype = byStatusGetOrderReq.getOrder_type();
        long rechargeuid = byStatusGetOrderReq.getUid();
        List<Orders> orderList = appServerMapper.getOrderInfo(ordertype,rechargeuid);
        ArrayList<ByStatusGetOrderInfo> getMaterOrderList = new  ArrayList<ByStatusGetOrderInfo>();

        for(int i =0;i<orderList.size();i++){

            ByStatusGetOrderInfo byStatusGetOrderInfo = new ByStatusGetOrderInfo();
            Orders order = orderList.get(i);
            byStatusGetOrderInfo.setTo_type(2);
            byStatusGetOrderInfo.setFrom_money(order.getMoney_num());
            byStatusGetOrderInfo.setTo_money(order.getMoney_num());
            byStatusGetOrderInfo.setConfirm_st(DateUtil.dateLongToString(order.getConfirm_date()));
            byStatusGetOrderInfo.setFrom_order_num(order.getRecharge_order());
            byStatusGetOrderInfo.setFrom_st(DateUtil.dateLongToString(order.getFrom_date()));
            String userphone =order.getRecharge_phone();
            String name = appServerMapper.getUserName(userphone);
            byStatusGetOrderInfo.setFrom_tname(name);
            byStatusGetOrderInfo.setMatch_st(DateUtil.dateLongToString(order.getMatch_date()));
            byStatusGetOrderInfo.setTo_order_num(order.getWithdrawals_order());
            userphone = order.getWithdrawals_phone();
            name = appServerMapper.getUserName(userphone);
            byStatusGetOrderInfo.setTo_st(DateUtil.dateLongToString(order.getTo_date()));
            byStatusGetOrderInfo.setFrom_type(1);
            getMaterOrderList.add(byStatusGetOrderInfo);
        }
        byStatusGetOrderResp.setData(getMaterOrderList);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(byStatusGetOrderResp);
        return jsonObject;
    }

    @RequestMapping(value = "10014")
    /**
     * 接受和提供帮助订单
     */
    @ResponseBody
    public JSONObject AddOrderHelps(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        HelpsOrderReq helpsOrderReq = JSON.parseObject(msgBody, HelpsOrderReq.class);
        HelpsOrderResp helpsOrderResp = new HelpsOrderResp();
        long ncurTimer = System.currentTimeMillis();

        Offer_Help offer_helps = new Offer_Help();
        offer_helps.setCreate_date(ncurTimer);
        offer_helps.setLast_update(ncurTimer);

        long help_id = 0;
        String idname = "help_id";
        appServerMapper.id_generator(idname);
        help_id = appServerMapper.get_id_generator(idname);
        offer_helps.setHelp_id(help_id);
        String ordernum = CommonUtil.genRandomOrder(help_id+"");
        offer_helps.setHelp_order(ordernum);
        offer_helps.setMoney_num(helpsOrderReq.getMoney());
        offer_helps.setHelp_status(0);
        offer_helps.setPayment_type("0,1,2");
        offer_helps.setUser_id(helpsOrderReq.getUid());
        offer_helps.setUser_phone(helpsOrderReq.getAccount());
        offer_helps.setWallet_type(helpsOrderReq.getWallet_type());
        offer_helps.setState('N');
        offer_helps.setStatus_confirmation(0);
        offer_helps.setHelp_type(helpsOrderReq.getHelp_type());
        appServerMapper.OfferHelp(offer_helps);
        helpsOrderResp.setMsg(retMsg);
        helpsOrderResp.setCode(retCode);
        HelpsOrderRespInfo helpsOrderRespInfo = new HelpsOrderRespInfo();
        helpsOrderRespInfo.setOrder_num(ordernum);
        helpsOrderResp.setData(helpsOrderRespInfo);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(helpsOrderResp);
        return jsonObject;
    }
    @RequestMapping(value = "10015")
    /**
     * 获取订单详情
     */
    @ResponseBody
    public JSONObject GetOrderInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        SendMoneyReq sendMoneyReq = JSON.parseObject(msgBody, SendMoneyReq.class);
        String ordernum = sendMoneyReq.getOrder_num();
        SendMoneyResp sendMoneyResp = new SendMoneyResp();
        SendMoneyInfo sendMoneyInfo = new SendMoneyInfo();
        //获取订单详情
        Orders order = appServerMapper.getOrderInfoDetails(ordernum);
        if(order!= null){
            sendMoneyResp.setMsg(retMsg);
            sendMoneyResp.setCode(retCode);

            sendMoneyInfo.setOrder_num(order.getOrder_num());
            sendMoneyInfo.setPayment_st(DateUtil.dateLongToString(order.getPayment_date()));
            sendMoneyInfo.setFrom_account(order.getRecharge_phone());
            String userphone =order.getRecharge_phone();
            String name = appServerMapper.getUserName(userphone);
            sendMoneyInfo.setFrom_tnamw(name);
            sendMoneyInfo.setMoney(order.getMoney_num());
            sendMoneyInfo.setType(order.getOrder_type());
            sendMoneyInfo.setPayment_url(order.getRemittance_url());
            sendMoneyResp.setData(sendMoneyInfo);

        }else{
            sendMoneyResp.setCode("C0010");
            sendMoneyResp.setMsg("订单不存在！");
            sendMoneyResp.setData(sendMoneyInfo);
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(sendMoneyResp);
        return jsonObject;
    }
    @RequestMapping(value = "10016")
    /**
     * 确认收款、确认未收到款
     */
    @ResponseBody
    public JSONObject ConfirmMoneyInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        ConfirmMoneyReq confirmMoneyReq = JSON.parseObject(msgBody, ConfirmMoneyReq.class);
        int complaintstatus =confirmMoneyReq.getType();
        String ordernum = confirmMoneyReq.getOrder_num();
        appServerMapper.updateOrderStatus(complaintstatus,ordernum);
        CommResp commResp = new CommResp();
        commResp.setCode(retCode);
        commResp.setMsg(retMsg);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }

    @RequestMapping(value = "10017")
    /**
     * 获取公告信息
     */
    @ResponseBody
    public JSONObject GetNews(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        NewsReq newsReq = JSON.parseObject(msgBody, NewsReq.class);
        NewsResp newsResp = new NewsResp();

        int type = newsReq.getType();
        int nCountNew =  appServerMapper.getNewsCount(type);
        News news = null;
        if(nCountNew>0){
            news = appServerMapper.getNews(type);
            newsResp.setCode(retCode);
            newsResp.setMsg(retMsg);
            NewsInfo data = new NewsInfo();
            data.setContent(news.getNew_content());
            newsResp.setData(data);
        }else{
            newsResp.setCode("C0011");
            newsResp.setMsg("没有系统公告！");
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(newsResp);
        return jsonObject;

    }

    @RequestMapping(value = "10018")
    /**
     * 获取轮播信息
     */
    @ResponseBody
    public JSONObject GetTurnChartInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        NewsReq newsReq = JSON.parseObject(msgBody, NewsReq.class);
        TurnChartResp chartResp = new TurnChartResp();
        ArrayList<TurnChartInfo> data = new ArrayList<TurnChartInfo>();

        List<Rotate_News> rotate_newsList =  appServerMapper.getRotateNews();
        Rotate_News rotate_news = null;
        for (int i =0;i<rotate_newsList.size();i++){
            rotate_news = rotate_newsList.get(i);
            TurnChartInfo turnChartInfo = new TurnChartInfo();
            turnChartInfo.setHelf(rotate_news.getHelf_url());
            turnChartInfo.setImage_url(rotate_news.getRotate_url());
            data.add(turnChartInfo);
        }
        chartResp.setData(data);
        chartResp.setCode(retCode);
        chartResp.setMsg(retMsg);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(chartResp);
        return jsonObject;
    }

    @RequestMapping(value = "10019")
    /**
     * 获取留言信息
     */
    @ResponseBody
    public JSONObject getLeavingMsgInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        LeavingMsgReq leavingMsgReq = JSON.parseObject(msgBody, LeavingMsgReq.class);
        LeavingMsgResp leavingMsgResp = new LeavingMsgResp();

        ArrayList<LeavingMsgInfo> leavingMsgInfoArrayList = new ArrayList<LeavingMsgInfo>();
        long uid = leavingMsgReq.getUid();
        int ncount = appServerMapper.getLeavingMsgCount(uid);
        if(ncount>0){
             List<Leaving_Msg> leavingMsgList = appServerMapper.getLeavingMsg(uid);
             for (int i =0;i<leavingMsgList.size();i++){
                 Leaving_Msg leaving_msg = leavingMsgList.get(i);

                 LeavingMsgInfo leavingMsgInfo = new LeavingMsgInfo();
                 leavingMsgInfo.setContent(leaving_msg.getMsg_content());
                 leavingMsgInfo.setMessage_st(DateUtil.dateLongToString(leaving_msg.getMsg_date()));
                 int is_reply = leaving_msg.getIs_reply();
                 leavingMsgInfo.setStatus(is_reply);
                 if(is_reply ==2){
                     leavingMsgInfo.setReply_content(leaving_msg.getReply_content());
                     leavingMsgInfo.setReply_st(DateUtil.dateLongToString(leaving_msg.getReply_date()));
                 }else{
                     leavingMsgInfo.setReply_content("");
                     leavingMsgInfo.setReply_st("");
                 }

                 leavingMsgInfoArrayList.add(leavingMsgInfo);

             }
            leavingMsgResp.setCode(retCode);
            leavingMsgResp.setMsg(retMsg);
            leavingMsgResp.setData(leavingMsgInfoArrayList);

        }else{
            leavingMsgResp.setMsg("没有留言！");
            leavingMsgResp.setCode("C0012");
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(leavingMsgResp);
        return jsonObject;

    }

    @RequestMapping(value = "10020")
    /**
     * 获取留言信息
     */
    @ResponseBody
    public JSONObject InsertLeavingMsg(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        SendLeavingMsgReq leavingMsgReq = JSON.parseObject(msgBody, SendLeavingMsgReq.class);

        long ncurrent = System.currentTimeMillis();

        Leaving_Msg leaving_msg = new Leaving_Msg();

        leaving_msg.setCreate_date(ncurrent);
        leaving_msg.setLast_update(ncurrent);
        leaving_msg.setMsg_date(ncurrent);
        leaving_msg.setIs_reply(1);
        leaving_msg.setMsg_content(leavingMsgReq.getContent());
        leaving_msg.setUser_id(leavingMsgReq.getUid());
        leaving_msg.setState('N');

        long leaving_id = 0;
        String leaving_name = "leaving_id";
        appServerMapper.id_generator(leaving_name);
        leaving_id = appServerMapper.get_id_generator(leaving_name);
        leaving_msg.setLeaving_id(leaving_id);

        appServerMapper.InsertLeavingMsg(leaving_msg);

        CommResp commResp = new CommResp();
        commResp.setCode(retCode);
        commResp.setMsg(retMsg);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }



    }
