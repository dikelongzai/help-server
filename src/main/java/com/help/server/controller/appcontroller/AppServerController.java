
package com.help.server.controller.appcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.requestbean.*;
import com.help.server.domain.responsebean.*;
import com.help.server.domain.tables.Activate_Code;
import com.help.server.domain.tables.User_Member;
import com.help.server.domain.tables.User_MemberInfo;
import com.help.server.domain.tables.Validate_Code;
import com.help.server.service.AppService;
import com.help.server.util.Base64Util;
import com.help.server.util.CommonUtil;
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

    @RequestMapping(value = "10002", method = RequestMethod.GET)
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

    @RequestMapping(value = "10003", method = RequestMethod.GET)
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

    @RequestMapping(value = "10004", method = RequestMethod.GET)
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

    @RequestMapping(value = "10005", method = RequestMethod.GET)
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

    @RequestMapping(value = "10006", method = RequestMethod.GET)
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

    @RequestMapping(value = "10007", method = RequestMethod.GET)
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
        long fromuid = appServerMapper.getUserIDByaccount(username);
        int usable_code_num = appServerMapper.getUserCodeNum(fromuid);

        username = sendActivationcodeReq.getTaccount();
        long touid = appServerMapper.getUserIDByaccount(username);
        int nCount = appServerMapper.getUserCount(username);

        if (usable_code_num >= codeCount) { //可以分配
            appServerMapper.updateUserCodeNum_add(codeCount, fromuid);
            appServerMapper.updateUserCodeNum_dec(codeCount, touid);
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
            appServerMapper.insertValCode(activate_code);
            commResp.setCode(retCode);
            commResp.setMsg(retMsg);

        } else { //激活码不足，不能分配
            commResp.setCode("C0008");
            commResp.setMsg("激活码的数量不足！");
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }

    @RequestMapping(value = "10008", method = RequestMethod.GET)
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

    @RequestMapping(value = "10009", method = RequestMethod.GET)
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

    @RequestMapping(value = "10012", method = RequestMethod.GET)
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
}
