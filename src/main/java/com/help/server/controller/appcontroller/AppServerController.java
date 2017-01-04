
package com.help.server.controller.appcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.requestbean.GetUserInfoReq;
import com.help.server.domain.requestbean.RegisterInfoReq;
import com.help.server.domain.requestbean.UserLoginReq;
import com.help.server.domain.requestbean.sendValidateCodeReq;
import com.help.server.domain.responsebean.*;
import com.help.server.domain.tables.User_Member;
import com.help.server.domain.tables.User_MemberInfo;
import com.help.server.domain.tables.Validate_Code;
import com.help.server.service.AppService;
import com.help.server.util.Base64Util;
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
        User_MemberInfo user_member = appServerMapper.getUserInfo(userId);
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
        User_MemberInfo user_member = appServerMapper.getUserInfo(userId);

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
            User_MemberInfo user_member = appServerMapper.getUserInfo(userId);

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

    @RequestMapping(value = "10004", method = RequestMethod.GET)
    /**
     * 获取用户登录
     */
    @ResponseBody
    public JSONObject RegisterUser(@RequestParam(value="p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        RegisterInfoReq registerinfoReq = JSON.parseObject(msgBody, RegisterInfoReq.class);
        RegisterInfo  registerInfo = new RegisterInfo();
        RegisterInfoResp registerinfoResp = new RegisterInfoResp();
        long uid  =0;
        long referee_uid = 0;
        int ncount = appServerMapper.getUserCount(registerinfoReq.getTel());
        long nreferee =  appServerMapper.getUserCount(registerinfoReq.getInvite());
        if(nreferee!=0){
            referee_uid = appServerMapper.getUserIDByaccount(registerinfoReq.getInvite());
        }
        if(ncount != 0){
            registerinfoResp.setCode("C0005");
            registerinfoResp.setMsg("用户已经注册");
            registerInfo.setUid(uid);
            registerInfo.setStatus(1);
            registerinfoResp.setData(registerInfo);
        }else{
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

            if(nretcode ==1){
                registerinfoResp.setCode("C0000");
                registerinfoResp.setMsg("ok");
            }else{
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
    public JSONObject SendValidateCode(@RequestParam(value="p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        sendValidateCodeReq sendValidateReq = JSON.parseObject(msgBody, sendValidateCodeReq.class);

        CommResp commResp = new CommResp();
        String randcode = "8u732";
        long nCurTimer = System.currentTimeMillis();
        Validate_Code validate_code = new Validate_Code();
        validate_code.setUser_imei(sendValidateReq.getImei());
        validate_code.setUser_phone(sendValidateReq.getTel());
        validate_code.setCreate_date(nCurTimer);
        validate_code.setLast_update(nCurTimer);
        validate_code.setState('N');
        validate_code.setValidate_type(sendValidateReq.getType());
        validate_code.setValidate_code(randcode);
        long validate_id =0;
        String idname = "validate_id";
        appServerMapper.id_generator(idname);
        validate_id = appServerMapper.get_id_generator(idname);
        validate_code.setValidate_id(validate_id);
        //查询是否存在
        String userphone = sendValidateReq.getTel();
        int validatetype = sendValidateReq.getType();
        String code = randcode;
        int ncount = appServerMapper.getValCodeCount(userphone,validatetype);
        if(ncount == 0){
            //插入
            appServerMapper.insertValCode(validate_code);
        }else{
            appServerMapper.updateValCode(code,userphone,validatetype);
        }

        commResp.setCode("C0000");
        commResp.setMsg("OK");

        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        return jsonObject;
    }

}
