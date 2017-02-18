
package com.help.server.controller.appcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.requestbean.*;
import com.help.server.domain.responsebean.*;
import com.help.server.domain.tables.*;
import com.help.server.service.AppService;
import com.help.server.service.FileUploadService;
import com.help.server.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    private final String retCode = "C0000";
    private final String retMsg = "ok";

    private static final Log log = LogFactory.getLog(AppServerController.class);
    @Autowired
    private AppService appService;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private AppServerMapper appServerMapper;

    @RequestMapping(value = "10001")

    /**
     * 获取用户信息
     */
    @ResponseBody
    public String getuserInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
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
            data.setImage_url(user_member.getUser_carded_url());
            data.setName(user_member.getUser_name());
            data.setStatus(user_member.getIs_activate());
            long titleid = user_member.getTitle_id();
            data.setTitle(appServerMapper.getTitleName(titleid));
            data.setId_num(user_member.getUser_carded());
            data.setInvite(user_member.getUser_referee_phone());
            data.setCode_num(user_member.getUsable_code_num());
            getUserInfoResp.setData(data);

        } else {
            getUserInfoResp.setCode("C0002");
            getUserInfoResp.setMsg("用户不存在！");
            getUserInfoResp.setData(data);
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserInfoResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }

        return retMsg;

    }

    @RequestMapping(value = "10002")
    /**
     * 获取用户钱包
     */
    @ResponseBody
    public String getuserWallet(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
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

        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10003")
    /**
     * 获取用户登录
     */
    @ResponseBody
    public String userLogIn(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
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
                data.setImage_url(user_member.getUser_carded_url());
                data.setName(user_member.getUser_name());
                data.setStatus(user_member.getIs_activate());
                long titleid = user_member.getTitle_id();
                data.setTitle(appServerMapper.getTitleName(titleid));
                data.setId_num(user_member.getUser_carded());
                data.setInvite(user_member.getUser_referee_phone());
                data.setCode_num(user_member.getUsable_code_num());
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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10004")
    /**
     * 获取用户登录
     */
    @ResponseBody
    public String RegisterUser(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
            regUserInfo.setTitle_id(1);
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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10005")
    /**
     * 发送验证码
     */
    @ResponseBody
    public String SendValidateCode(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
        //发送短信
        String content = SendSmsUtil.getSmsContent(randcode);
        SendSmsUtil.sendSms(userphone,content);

        commResp.setCode(retCode);
        commResp.setMsg(retMsg);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10006")
    /**
     * 验证验证码协议
     */
    @ResponseBody
    public String VerifyCode(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10007")
    /**
     * 验证验证码协议
     */
    @ResponseBody
    public String SendActivationcode(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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

        }
        long fromuid = appServerMapper.getUserIDByaccount(username);
        int usable_code_num = appServerMapper.getUserCodeNum(fromuid);

        username = sendActivationcodeReq.getTaccount();
        nUserCount = appServerMapper.getUserCount(username);
        if(nUserCount!=1){
            commResp.setCode("C10013");
            commResp.setMsg("用户信息不存在！");
            jsonObject = (JSONObject) JSON.toJSON(commResp);

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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10008")
/**
 * 验证验证码协议
 */
    @ResponseBody
    public String GetUserTema(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserTemaReq getUserTemaReq = JSON.parseObject(msgBody, GetUserTemaReq.class);
        GetUserTemaResp getUserTemaResp = new GetUserTemaResp();

        ArrayList <UserTeamInfo> userTeamInfoArrayList = new ArrayList<>();
        List<User_MemberInfo> userMemberInfoList= appServerMapper.getUserLevel(getUserTemaReq.getUid());
        int nLevel = getUserTemaReq.getLevel();
        if(nLevel == 1){
            if(userMemberInfoList!=null){
                for (int i =0;i<userMemberInfoList.size();i++){
                    User_MemberInfo user_memberInfo = userMemberInfoList.get(i);
                    UserTeamInfo userTeamInfo = new UserTeamInfo();
                    userTeamInfo.setAccount(user_memberInfo.getUser_phone());
                    userTeamInfo.setTname(user_memberInfo.getUser_name());
                    userTeamInfo.setUid(user_memberInfo.getUser_id());
                    userTeamInfo.setRt(user_memberInfo.getCreate_date());
                    userTeamInfo.setStatus(user_memberInfo.getIs_activate());
                    Offer_Help offerHelp = appServerMapper.getOfferHelpInfoByDesc(user_memberInfo.getUser_id());
                    int titleid = user_memberInfo.getTitle_id();
                    userTeamInfo.setTile_name(appServerMapper.getTitleName(titleid));
                    if(offerHelp!=null){
                        if(offerHelp.getHelp_type()==1){
                         userTeamInfo.setCsale(-(offerHelp.getMoney_num()));
                        }else{
                            userTeamInfo.setCsale(offerHelp.getMoney_num());
                        }

                    }else{
                        userTeamInfo.setCsale(0);
                    }

                    userTeamInfoArrayList.add(userTeamInfo);
                }
            }
        }else if(nLevel == 2){

            if(userMemberInfoList!=null){
                for (int i =0;i<userMemberInfoList.size();i++){
                    User_MemberInfo user_memberInfo = userMemberInfoList.get(i);
                    List<User_MemberInfo> dataTwoList= appServerMapper.getUserLevel(user_memberInfo.getUser_id());
                    for (int j =0;j<dataTwoList.size();j++){

                        User_MemberInfo dataInfo = dataTwoList.get(j);
                        UserTeamInfo userTeamInfo = new UserTeamInfo();
                        userTeamInfo.setAccount(dataInfo.getUser_phone());
                        userTeamInfo.setTname(dataInfo.getUser_name());
                        userTeamInfo.setUid(dataInfo.getUser_id());
                        userTeamInfo.setRt(dataInfo.getCreate_date());
                        userTeamInfo.setStatus(dataInfo.getIs_activate());
                        int titleid = user_memberInfo.getTitle_id();
                        userTeamInfo.setTile_name(appServerMapper.getTitleName(titleid));
                        Offer_Help offerHelp = appServerMapper.getOfferHelpInfoByDesc(user_memberInfo.getUser_id());
                        if(offerHelp!=null){
                            if(offerHelp.getHelp_type()==1){
                                userTeamInfo.setCsale(-(offerHelp.getMoney_num()));
                            }else{
                                userTeamInfo.setCsale(offerHelp.getMoney_num());
                            }

                        }else{
                            userTeamInfo.setCsale(0);
                        }
                        userTeamInfoArrayList.add(userTeamInfo);
                    }
                }
            }
        }else if(nLevel == 3){
            if(userMemberInfoList!=null){
                for (int i =0;i<userMemberInfoList.size();i++){
                    User_MemberInfo user_memberInfo = userMemberInfoList.get(i);
                    List<User_MemberInfo> dataTwoList= appServerMapper.getUserLevel(user_memberInfo.getUser_id());
                    for (int j =0;j<dataTwoList.size();j++){

                        User_MemberInfo dataTwoInfo = dataTwoList.get(j);
                        List<User_MemberInfo> datathreeList= appServerMapper.getUserLevel(dataTwoInfo.getUser_id());
                        for (int k=0;k<datathreeList.size();k++){
                            User_MemberInfo datathreeInfo = datathreeList.get(k);
                            UserTeamInfo userTeamInfo = new UserTeamInfo();
                            userTeamInfo.setAccount(datathreeInfo.getUser_phone());
                            userTeamInfo.setTname(datathreeInfo.getUser_name());
                            userTeamInfo.setUid(datathreeInfo.getUser_id());
                            userTeamInfo.setRt(datathreeInfo.getCreate_date());
                            userTeamInfo.setStatus(datathreeInfo.getIs_activate());
                            int titleid = user_memberInfo.getTitle_id();
                            userTeamInfo.setTile_name(appServerMapper.getTitleName(titleid));
                            Offer_Help offerHelp = appServerMapper.getOfferHelpInfoByDesc(user_memberInfo.getUser_id());
                            if(offerHelp!=null){
                                if(offerHelp.getHelp_type()==1){
                                    userTeamInfo.setCsale(-(offerHelp.getMoney_num()));
                                }else{
                                    userTeamInfo.setCsale(offerHelp.getMoney_num());
                                }

                            }else{
                                userTeamInfo.setCsale(0);
                            }
                            userTeamInfoArrayList.add(userTeamInfo);
                        }
                    }
                }
            }
        }else if(nLevel == 4){
            if(userMemberInfoList!=null){
                for (int i =0;i<userMemberInfoList.size();i++){
                    User_MemberInfo user_memberInfo = userMemberInfoList.get(i);
                    List<User_MemberInfo> dataTwoList= appServerMapper.getUserLevel(user_memberInfo.getUser_id());
                    for (int j =0;j<dataTwoList.size();j++){
                        User_MemberInfo dataTwoInfo = dataTwoList.get(j);
                        List<User_MemberInfo> datathreeList= appServerMapper.getUserLevel(dataTwoInfo.getUser_id());
                        for (int k=0;k<datathreeList.size();k++){
                            User_MemberInfo datathreeInfo = dataTwoList.get(k);
                            List<User_MemberInfo> datafourList= appServerMapper.getUserLevel(datathreeInfo.getUser_id());
                            for (int m=0;m<datathreeList.size();m++){
                                User_MemberInfo datafourInfo = datafourList.get(m);
                                UserTeamInfo userTeamInfo = new UserTeamInfo();
                                userTeamInfo.setAccount(datafourInfo.getUser_phone());
                                userTeamInfo.setTname(datafourInfo.getUser_name());
                                userTeamInfo.setUid(datafourInfo.getUser_id());
                                userTeamInfo.setRt(datafourInfo.getCreate_date());
                                userTeamInfo.setStatus(datafourInfo.getIs_activate());
                                int titleid = user_memberInfo.getTitle_id();
                                userTeamInfo.setTile_name(appServerMapper.getTitleName(titleid));
                                Offer_Help offerHelp = appServerMapper.getOfferHelpInfoByDesc(user_memberInfo.getUser_id());
                                if(offerHelp!=null){
                                    if(offerHelp.getHelp_type()==1){
                                        userTeamInfo.setCsale(-(offerHelp.getMoney_num()));
                                    }else{
                                        userTeamInfo.setCsale(offerHelp.getMoney_num());
                                    }

                                }else{
                                    userTeamInfo.setCsale(0);
                                }
                                userTeamInfoArrayList.add(userTeamInfo);
                            }
                        }
                    }
                }
            }
        }
        getUserTemaResp.setData(userTeamInfoArrayList);
        getUserTemaResp.setCode(retCode);
        getUserTemaResp.setMsg(retMsg);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserTemaResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10009")
    /**
     * 验证验证码协议
     */
    @ResponseBody
    public String UserActivate(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        CommResp commResp = new CommResp();
        ActivationUserReq activationUserReq = JSON.parseObject(msgBody, ActivationUserReq.class);
        String from_phone = activationUserReq.getFaccount();
        String to_phone = activationUserReq.getTaccount();
        int ncount = appServerMapper.getUserCount(to_phone);
        if (ncount > 0) {
            int usable_code_num = appServerMapper.getUserUsableCount(from_phone);
            if(usable_code_num>=1){
                appServerMapper.updateValUser(to_phone);
                appServerMapper.updateUserActiveNum_dec(1,from_phone);
                commResp.setCode(retCode);
                commResp.setMsg(retMsg);
            }else{
                commResp.setCode("C0017");
                commResp.setMsg("激活码数量小于1！");
            }

        } else {
            commResp.setCode("C0009");
            commResp.setMsg("手机号不是账号，不能激活！");
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10012")
    /**
     * 忘记密码
     */
    @ResponseBody
    public String forgetPwd(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
                forgetpwdInfo.setStatus(userinfo.getIs_activate());
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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;

    }
    @RequestMapping(value = "10023")
    /**
     * 已匹配成功订单
     */
    @ResponseBody
    public String GetMaterOrder(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetMateOrderReq getMateOrderReq = JSON.parseObject(msgBody, GetMateOrderReq.class);
        GetMateOrderResp getMateOrderResp = new GetMateOrderResp();

        getMateOrderResp.setCode(retCode);
        getMateOrderResp.setMsg(retMsg);
        getMateOrderResp.setCurrentTimer(System.currentTimeMillis());
        int ordertype = getMateOrderReq.getOrder_type();
        long rechargeuid = getMateOrderReq.getUid();
        List<Orders> orderList = appServerMapper.getOrderInfo(ordertype,rechargeuid);
        ArrayList<GetMaterOrderInfo> getMaterOrderList = new  ArrayList<GetMaterOrderInfo>();

        for(int i =0;i<orderList.size();i++){

            GetMaterOrderInfo getMaterOrderInfo = new GetMaterOrderInfo();
            Orders order = orderList.get(i);

            getMaterOrderInfo.setFrom_money(order.getMoney_num());
            getMaterOrderInfo.setTo_money(order.getMoney_num());
            getMaterOrderInfo.setConfirm_st(DateUtil.dateLongToString(order.getConfirm_date()));
            getMaterOrderInfo.setFrom_order_num(order.getRecharge_order());
            getMaterOrderInfo.setFrom_st(DateUtil.dateLongToString(order.getFrom_date()));
            getMaterOrderInfo.setOrder_num(order.getOrder_num());
            String userphone =order.getRecharge_phone();
            getMaterOrderInfo.setFrom_account(userphone);//
            String name = appServerMapper.getUserName(userphone);
            getMaterOrderInfo.setFrom_tname(name);
            getMaterOrderInfo.setMatch_st(DateUtil.dateLongToString(order.getMatch_date()));
            getMaterOrderInfo.setTo_order_num(order.getWithdrawals_order());
            Offer_Help offerHelp =  appServerMapper.getOfferHelpByHelpOrder(order.getWithdrawals_order());
            getMaterOrderInfo.setWallet_type(offerHelp.getWallet_type());
            userphone = order.getWithdrawals_phone();
            name = appServerMapper.getUserName(userphone);
            getMaterOrderInfo.setTo_tname(name);
            getMaterOrderInfo.setVoucher_url(order.getRemittance_url());
            getMaterOrderInfo.setTo_st(DateUtil.dateLongToString(order.getTo_date()));
            getMaterOrderInfo.setTo_account(userphone);
            getMaterOrderInfo.setOrder_type(order.getOrder_type());
            getMaterOrderList.add(getMaterOrderInfo);
        }
        getMateOrderResp.setData(getMaterOrderList);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getMateOrderResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10013")
    /**
     * 已匹配成功订单
     */
    @ResponseBody
    public String GetOrderByStatus(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
            byStatusGetOrderInfo.setFrom_account(order.getRecharge_phone());
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
            byStatusGetOrderInfo.setTo_tname(name);
            byStatusGetOrderInfo.setTo_st(DateUtil.dateLongToString(order.getTo_date()));
            byStatusGetOrderInfo.setTo_account(userphone);
            getMaterOrderList.add(byStatusGetOrderInfo);
        }
        byStatusGetOrderResp.setData(getMaterOrderList);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(byStatusGetOrderResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10014")
   /**
    * 接受和提供帮助订单
    */
    @ResponseBody
    public String AddOrderHelps(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        HelpsOrderReq helpsOrderReq = JSON.parseObject(msgBody, HelpsOrderReq.class);
        HelpsOrderResp helpsOrderResp = new HelpsOrderResp();
        GetRuleInfo getRuleInfo = appServerMapper.getRuleInfo();
        float money = helpsOrderReq.getMoney();
        //申请帮助，出钱
        if(helpsOrderReq.getHelp_type() ==1) {

            int times = (int) (money % getRuleInfo.getApply_num_times());
            if (money < getRuleInfo.getApply_num_lown() || money > getRuleInfo.getApply_num_high() || times != 0) {
                helpsOrderResp.setMsg("申请帮助的发单规则不正确，请重新发单！");
                helpsOrderResp.setCode("C0017");
                JSONObject jsonObject = (JSONObject) JSON.toJSON(helpsOrderResp);
                String retMsg = Base64Util.encode(jsonObject.toString());
                try {
                    retMsg = URLEncoder.encode(retMsg, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    log.error(e);
                }
                return retMsg;
            }
            int nCount = appServerMapper.getUserOfferHelpCount(helpsOrderReq.getUid());
            if (nCount == 0) {
                if (helpsOrderReq.getMoney() < getRuleInfo.getApply_num_first()) {
                    helpsOrderResp.setMsg("首次发单申请帮助金额大于" + getRuleInfo.getApply_num_first());
                    helpsOrderResp.setCode("C0016");
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(helpsOrderResp);
                    String retMsg = Base64Util.encode(jsonObject.toString());
                    try {
                        retMsg = URLEncoder.encode(retMsg, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        log.error(e);
                    }
                    return retMsg;
                }


            }
        }else {
            int times = (int)(money%getRuleInfo.getAsk_num_times());
            if(money<getRuleInfo.getAsk_num_lown()||money>getRuleInfo.getAsk_num_high()||times!=0){
                helpsOrderResp.setMsg("请求帮助的发单规则不正确，请重新发单！");
                helpsOrderResp.setCode("C0017");
                JSONObject jsonObject = (JSONObject) JSON.toJSON(helpsOrderResp);
                String retMsg = Base64Util.encode(jsonObject.toString());
                try {
                    retMsg = URLEncoder.encode(retMsg, "UTF-8");
                }catch (UnsupportedEncodingException e) {
                    log.error(e);
                }
                return retMsg;
            }
        }
        if(helpsOrderReq.getHelp_type() ==2) { //请求帮助
            User_MemberInfo userMemberInfo = appServerMapper.getUserInfo(helpsOrderReq.getUid());
            if (helpsOrderReq.getWallet_type() == 2) { //静态钱包
                if (userMemberInfo.getUdynamic_wallet() < helpsOrderReq.getMoney()) {
                    helpsOrderResp.setMsg("余额不足，不能发单！");
                    helpsOrderResp.setCode("C0015");
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(helpsOrderResp);
                    String retMsg = Base64Util.encode(jsonObject.toString());
                    try {
                        retMsg = URLEncoder.encode(retMsg, "UTF-8");
                    }catch (UnsupportedEncodingException e) {
                        log.error(e);
                    }
                    return retMsg;
                }
            }
            if (helpsOrderReq.getWallet_type() == 1) {

                if (userMemberInfo.getUstatic_wallet() < helpsOrderReq.getMoney()) {
                    helpsOrderResp.setMsg("余额不足，不能发单！");
                    helpsOrderResp.setCode("C0015");
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(helpsOrderResp);
                    String retMsg = Base64Util.encode(jsonObject.toString());
                    try {
                        retMsg = URLEncoder.encode(retMsg, "UTF-8");
                    }catch (UnsupportedEncodingException e) {
                        log.error(e);
                    }
                    return retMsg;
                }
            }
        }
        long ncurTimer = System.currentTimeMillis();
        Offer_Help offer_helps = new Offer_Help();
        offer_helps.setCreate_date(ncurTimer);
        offer_helps.setLast_update(ncurTimer);
        //提供帮助 100 天加到冻结钱包
       //请求帮助 100 相应钱包减100，其中动态钱包时同时提供帮助生成订单同样金额订单。
        long help_id = 0;
        String idname = "help_id";
        appServerMapper.id_generator(idname);
        help_id = appServerMapper.get_id_generator(idname);
        offer_helps.setHelp_id(help_id);
        String ordernum ="";
        if(helpsOrderReq.getHelp_type() ==1){
            ordernum = CommonUtil.genRandomOrderEX("T",help_id+"");
        }else{
            ordernum = CommonUtil.genRandomOrderEX("S",help_id+"");
        }

        offer_helps.setHelp_order(ordernum);
        offer_helps.setMoney_num(helpsOrderReq.getMoney());
        offer_helps.setHelp_status(1);
        offer_helps.setPayment_type("0,1,2");
        offer_helps.setUser_id(helpsOrderReq.getUid());
        offer_helps.setUser_phone(helpsOrderReq.getAccount());
        offer_helps.setWallet_type(helpsOrderReq.getWallet_type());
        offer_helps.setState('N');
        offer_helps.setStatus_confirmation(0);
        offer_helps.setHelp_type(helpsOrderReq.getHelp_type());
        offer_helps.setIs_income(1);
        appServerMapper.OfferHelp(offer_helps);
        if(helpsOrderReq.getHelp_type() == 1){ //提供帮助
            appServerMapper.updateUserFrozen(helpsOrderReq.getUid(),helpsOrderReq.getMoney());
        }
        if(helpsOrderReq.getHelp_type() ==2){ //请求帮助
            User_MemberInfo userMemberInfo = appServerMapper.getUserInfo(helpsOrderReq.getUid());
            if(helpsOrderReq.getWallet_type()==2){ //动态钱包
                appServerMapper.updateUserdynamic(helpsOrderReq.getUid(),helpsOrderReq.getMoney());
//提供帮助
                ncurTimer = System.currentTimeMillis();
                offer_helps.setCreate_date(ncurTimer);
                offer_helps.setLast_update(ncurTimer);
                appServerMapper.id_generator(idname);
                help_id = appServerMapper.get_id_generator(idname);
                offer_helps.setHelp_id(help_id);
                ordernum = CommonUtil.genRandomOrderEX("S",help_id+"");
                offer_helps.setHelp_order(ordernum);
                offer_helps.setMoney_num(helpsOrderReq.getMoney());
                offer_helps.setHelp_status(1);
                offer_helps.setPayment_type("0,1,2");
                offer_helps.setUser_id(helpsOrderReq.getUid());
                offer_helps.setUser_phone(helpsOrderReq.getAccount());
                offer_helps.setWallet_type(0);
                offer_helps.setState('N');
                offer_helps.setStatus_confirmation(0);
                offer_helps.setHelp_type(1);
                offer_helps.setIs_income(0);
                appServerMapper.OfferHelp(offer_helps);
                appServerMapper.updateUserFrozen(helpsOrderReq.getUid(),helpsOrderReq.getMoney());
            }else{ //静态钱包
                appServerMapper.updateUserstatic(helpsOrderReq.getUid(),helpsOrderReq.getMoney());
            }
        }

        helpsOrderResp.setMsg(retMsg);
        helpsOrderResp.setCode(retCode);
        HelpsOrderRespInfo helpsOrderRespInfo = new HelpsOrderRespInfo();
        helpsOrderRespInfo.setOrder_num(ordernum);
        helpsOrderResp.setData(helpsOrderRespInfo);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(helpsOrderResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10015")
    /**
     * 获取订单详情
     */
    @ResponseBody
    public String GetOrderInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }
    @RequestMapping(value = "10016")
    /**
     * 确认收款、确认未收到款
     */
    @ResponseBody
    public String ConfirmMoneyInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        ConfirmMoneyReq confirmMoneyReq = JSON.parseObject(msgBody, ConfirmMoneyReq.class);
        int complaintstatus =confirmMoneyReq.getType();
        String ordernum = confirmMoneyReq.getOrder_num();
        Orders orders = appServerMapper.getOrderInfoDetails(ordernum);
        if(complaintstatus==1){ //确认收款
            appServerMapper.updateOrderStatus(2,ordernum);
            appServerMapper.updateOfferHelp(2,orders.getRecharge_order());
            appServerMapper.updateOfferHelp(2,orders.getWithdrawals_order());
        }else{ //确认未收款
            appServerMapper.updateOrderStatus(8,ordernum);
            appServerMapper.updateOfferHelp(8,orders.getRecharge_order());
            appServerMapper.updateOfferHelp(8,orders.getWithdrawals_order());
        }
        CommResp commResp = new CommResp();
        commResp.setCode(retCode);
        commResp.setMsg(retMsg);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10017")
    /**
     * 获取公告信息
     */
    @ResponseBody
    public String GetNews(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;

    }

    @RequestMapping(value = "10018")
    /**
     * 获取轮播信息
     */
    @ResponseBody
    public String GetTurnChartInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10019")
    /**
     * 获取留言信息
     */
    @ResponseBody
    public String getLeavingMsgInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;

    }

    @RequestMapping(value = "10020")
    /**
     * 获取留言信息
     */
    @ResponseBody
    public String InsertLeavingMsg(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

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
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10021", method = RequestMethod.POST)

    @ResponseBody
    public String uploadfile(@RequestParam("file") MultipartFile file){

        String url = "";
        String fileName= null;
        try {
            fileName = fileUploadService.handleFileUpload(file).getFileName();
            url= CommonConstant.BASE_IMAGE_URL+fileName;
        } catch (Exception e) {
            log.error(e);
        }
        log.info("uploadfile="+url);
        FileUpLoadResp fileUpLoadResp = new FileUpLoadResp();
        fileUpLoadResp.setMsg(retMsg);
        fileUpLoadResp.setCode(retCode);
        fileUpLoadResp.setFile_url(url);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(fileUpLoadResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10022")

    @ResponseBody
    public String uploadPayInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request){

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        UploadPayInfoReq payInfoReq = JSON.parseObject(msgBody, UploadPayInfoReq.class);
        int ntype = payInfoReq.getType();
        if(ntype ==0){ //银行账号
            appServerMapper.updateUserBankInfo(payInfoReq.getBank(),payInfoReq.getSite(),payInfoReq.getName()
                    ,payInfoReq.getBankaccount(),payInfoReq.getUid());
        }else if(ntype==1){//支付宝
            appServerMapper.updateUserPayInfo(payInfoReq.getName()
                    ,payInfoReq.getBankaccount(),payInfoReq.getUid());
        }else if(ntype==2){
            appServerMapper.updateUserWinxinInfo(payInfoReq.getBankaccount(),payInfoReq.getUid());
        }
        CommResp commResp = new CommResp();
        commResp.setMsg(retMsg);
        commResp.setCode(retCode);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10024")

    @ResponseBody
    public String GetPayInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request){

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserPayInfoReq getUserPayInfoReq = JSON.parseObject(msgBody, GetUserPayInfoReq.class);
        UserPayInfo userPayInfo = appServerMapper.getUserPayInfo(getUserPayInfoReq.getAccount());

        GetUserPayInfoResp getUserPayInfoResp = new GetUserPayInfoResp();

        if(userPayInfo!=null){

            BankInfo bankInfo = new BankInfo();
            bankInfo.setBank(userPayInfo.getUser_bank());
            bankInfo.setAccount(userPayInfo.getUser_bank_account());
            bankInfo.setName(userPayInfo.getUser_bank_name());
            bankInfo.setSite(userPayInfo.getUser_bank_site());
            getUserPayInfoResp.setBank(bankInfo);

            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setName(userPayInfo.getUser_payment_name());
            paymentInfo.setAccount(userPayInfo.getUser_payment());
            getUserPayInfoResp.setPayment(paymentInfo);

            WeixinInfo weixinInfo = new WeixinInfo();
            weixinInfo.setAccount(userPayInfo.getUser_weixin());
            getUserPayInfoResp.setWeixin(weixinInfo);


            getUserPayInfoResp.setCode(retCode);
            getUserPayInfoResp.setMsg(retMsg);
        }else{
            getUserPayInfoResp.setCode("支付信息查询失败");
            getUserPayInfoResp.setCode("C0013");
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserPayInfoResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;

    }

    @RequestMapping(value = "10025")

    @ResponseBody
    public String UpLoadPayOrder(@RequestParam(value = "p") String inputStr, HttpServletRequest request){

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        UpLoadPayOrderReq upLoadPayOrderReq = JSON.parseObject(msgBody, UpLoadPayOrderReq.class);
        String ordernum = upLoadPayOrderReq.getSn();
        Orders orders = appServerMapper.getOrderInfoDetails(ordernum);
        long ncurrent = System.currentTimeMillis();
        CommResp commResp = new CommResp();
        try {
            appServerMapper.updateUserOrderInfo(ncurrent,upLoadPayOrderReq.getFile_url(),upLoadPayOrderReq.getSn());
            appServerMapper.updateOrderStatus(6,ordernum);
            appServerMapper.updateOfferHelp(6,orders.getRecharge_order());
            appServerMapper.updateOfferHelp(6,orders.getWithdrawals_order());
            commResp.setMsg(retMsg);
            commResp.setCode(retCode);
        }catch (Exception ex){
            commResp.setCode("数据库操作失败！");
            commResp.setCode("C0014");
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10026")

    @ResponseBody
    public String UserAuthInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request){

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        UserAuthReq userAuthReq = JSON.parseObject(msgBody, UserAuthReq.class);

        CommResp commResp = new CommResp();
        try {
            appServerMapper.updateUserAuthInfo(userAuthReq.getFile_url(),userAuthReq.getId_num()
                    ,userAuthReq.getName(),userAuthReq.getAccount());
            commResp.setMsg(retMsg);
            commResp.setCode(retCode);
        }catch (Exception ex){
            commResp.setCode("数据库操作失败！");
            commResp.setCode("C0014");
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10027")

    @ResponseBody
    public String GetActivateCodeLogInfo(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        ActivateCodeLogReq activateCodeLogReq = JSON.parseObject(msgBody, ActivateCodeLogReq.class);
        ActivateCodeLogResp activateCodeLogResp = new ActivateCodeLogResp();
        ArrayList<ActivateCodeLogInfo> activateCodeLogInfoList = new ArrayList<ActivateCodeLogInfo>();
        List<Activate_Code> activateCodeList = appServerMapper.getActivateInfo(activateCodeLogReq.getUid(),activateCodeLogReq.getUid());
        for (int i = 0; i < activateCodeList.size(); i++) {
            Activate_Code activate_code = activateCodeList.get(i);
            ActivateCodeLogInfo activateCodeLogInfo = new ActivateCodeLogInfo();
            activateCodeLogInfo.setDate(activate_code.getCreate_date());
            activateCodeLogInfo.setNum(activate_code.getCode_num());
            User_MemberInfo user_memberInfo = appServerMapper.getUserInfo(activate_code.getFrom_uid());
            activateCodeLogInfo.setFaccount(user_memberInfo.getUser_phone());
            activateCodeLogInfo.setName(user_memberInfo.getUser_name());
            user_memberInfo = appServerMapper.getUserInfo(activate_code.getTo_uid());
            activateCodeLogInfo.setTaccount(user_memberInfo.getUser_phone());
            activateCodeLogInfo.setTname(user_memberInfo.getUser_name());
            activateCodeLogInfoList.add(activateCodeLogInfo);
        }
        activateCodeLogResp.setData(activateCodeLogInfoList);
        activateCodeLogResp.setMsg(retMsg);
        activateCodeLogResp.setCode(retCode);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(activateCodeLogResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10028")

    @ResponseBody
    public String GetRuleSetting(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetRuleReq getRuleReq = JSON.parseObject(msgBody, GetRuleReq.class);
        GetRuleResp getRuleResp = new GetRuleResp();
        ArrayList<GetDynamicRuleInfo> d_data = new ArrayList<>();

        List<Dynamic_Award> dynamicAwardList = appServerMapper.findDynmicRules();
        for(int i =0;i<dynamicAwardList.size();i++){
            GetDynamicRuleInfo getDynamicRuleInfo = new GetDynamicRuleInfo();
            Dynamic_Award dynamicAward = dynamicAwardList.get(i);
            getDynamicRuleInfo.setD_limit(dynamicAward.getD_limit());
            getDynamicRuleInfo.setUser_title(dynamicAward.getUser_title());
            getDynamicRuleInfo.setUser_title_id(dynamicAward.getUser_title_id());
            d_data.add(getDynamicRuleInfo);
        }
        GetRuleInfo getRuleInfo = appServerMapper.getRuleInfo();
        getRuleResp.setData(getRuleInfo);
        getRuleResp.setD_data(d_data);
        getRuleResp.setCode(retCode);
        getRuleResp.setMsg(retMsg);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(getRuleResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }
    @RequestMapping(value = "10032")

    @ResponseBody
    public String ModifPwd(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        ModifPwdReq modifPwdReq = JSON.parseObject(msgBody, ModifPwdReq.class);
        CommResp commResp = new CommResp();
        int ncount = appServerMapper.checkUserOldPwd(modifPwdReq.getUid(),modifPwdReq.getPwd());
        if(ncount>0){
            appServerMapper.updateUserPWdByUid(modifPwdReq.getNewpwd(),modifPwdReq.getUid());
            commResp.setMsg(retMsg);
            commResp.setCode(retCode);
        }else{
            commResp.setMsg("旧密码校验失败！");
            commResp.setCode("C0014");
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(commResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

    @RequestMapping(value = "10029")

    @ResponseBody
    public String GetUserPayInfoBySn(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserPayInfoSnReq getUserPayInfoSnReq = JSON.parseObject(msgBody, GetUserPayInfoSnReq.class);
        Orders orders = appServerMapper.getOrderInfoDetails(getUserPayInfoSnReq.getSn());
        UserPayInfo userPayInfo = null;
        GetPayInfoBySnResp getPayInfoBySnResp = new GetPayInfoBySnResp();
        String account = "";
        if(orders.getRecharge_phone().equals(getUserPayInfoSnReq.getAccount())){
            account = orders.getWithdrawals_phone();
        }else{
            account = orders.getRecharge_phone();
        }
        userPayInfo = appServerMapper.getUserPayInfo(account);
        BankInfo bank = new BankInfo();
        bank.setName(userPayInfo.getUser_bank_name());
        bank.setAccount(userPayInfo.getUser_bank_account());
        bank.setBank(userPayInfo.getUser_bank());
        bank.setSite(userPayInfo.getUser_bank_site());
        getPayInfoBySnResp.setBank(bank);

        PaymentInfo payment = new PaymentInfo();
        payment.setAccount(userPayInfo.getUser_payment());
        payment.setName(userPayInfo.getUser_payment_name());
        getPayInfoBySnResp.setPayment(payment);

        WeixinInfo weixin = new WeixinInfo();
        weixin.setAccount(userPayInfo.getUser_weixin());

        LeaderInfo leader = new LeaderInfo();
        User_MemberInfo user_memberInfo = appServerMapper.getUserInfo(getUserPayInfoSnReq.getUid());
        String name = appServerMapper.getUserName(user_memberInfo.getUser_referee_phone());
        leader.setName(name);
        leader.setTel(user_memberInfo.getUser_referee_phone());
        getPayInfoBySnResp.setLeader(leader);
        getPayInfoBySnResp.setRemittance_url(orders.getRemittance_url());
        getPayInfoBySnResp.setMsg(retMsg);
        getPayInfoBySnResp.setCode(retCode);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(getPayInfoBySnResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }
    @RequestMapping(value = "10030")

    @ResponseBody
    public String GetUserOfferHelps(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserOfferHelpsReq getUserOfferHelpsReq = JSON.parseObject(msgBody, GetUserOfferHelpsReq.class);
        GetUserOfferHelpsResp getUserOfferHelpsResp = new GetUserOfferHelpsResp();
        List<Offer_Help> offerHelpList = null;
        if(getUserOfferHelpsReq.getWallet_type()==0&&getUserOfferHelpsReq.getHelp_status()!=0){
            offerHelpList = appServerMapper.getOfferHelpInfoByWall(getUserOfferHelpsReq.getUid()
                    ,getUserOfferHelpsReq.getOrder_type(),getUserOfferHelpsReq.getHelp_status());

        }else if(getUserOfferHelpsReq.getHelp_status()==0&&getUserOfferHelpsReq.getWallet_type()!=0){
            offerHelpList = appServerMapper.getOfferHelpInfoByHelpStatus(getUserOfferHelpsReq.getUid()
                    ,getUserOfferHelpsReq.getOrder_type(),getUserOfferHelpsReq.getWallet_type());

        }else if ((getUserOfferHelpsReq.getWallet_type()==0)&&(getUserOfferHelpsReq.getHelp_status()==0)){

            offerHelpList = appServerMapper.getOfferHelpInfoByHelpStatusAndWall(getUserOfferHelpsReq.getUid()
                    ,getUserOfferHelpsReq.getOrder_type());
        }else if(getUserOfferHelpsReq.getHelp_status() == 4){
            offerHelpList = appServerMapper.getOfferHelpInfoUn(getUserOfferHelpsReq.getUid()
                    ,getUserOfferHelpsReq.getOrder_type());
        } else{
            offerHelpList = appServerMapper.getOfferHelpInfo(getUserOfferHelpsReq.getUid()
                    ,getUserOfferHelpsReq.getOrder_type(),getUserOfferHelpsReq.getWallet_type(),getUserOfferHelpsReq.getHelp_status());
        }

        ArrayList<GetUserOfferHelpInfo> getUserOfferHelpInfos = new ArrayList<>();
        if(offerHelpList!=null){
            for (int i =0;i<offerHelpList.size();i++){
                Offer_Help offer_help = offerHelpList.get(i);
                GetUserOfferHelpInfo getUserOfferHelpInfo = new GetUserOfferHelpInfo();
                GetUserOfferOrderInfo data1 = new GetUserOfferOrderInfo();
                getUserOfferHelpInfo.setFrom_date(offer_help.getCreate_date());
                getUserOfferHelpInfo.setHelp_status(offer_help.getHelp_status());
                getUserOfferHelpInfo.setMoney(offer_help.getMoney_num());
                getUserOfferHelpInfo.setOrder_num(offer_help.getHelp_order());
                getUserOfferHelpInfo.setHelp_type(offer_help.getHelp_type());
                getUserOfferHelpInfo.setWallet_type(offer_help.getWallet_type());
                if(offer_help.getHelp_status()==2){ //已完成订单
                    if(offer_help.getHelp_type()==1){ //提供帮助
                        Orders orders = appServerMapper.getOrderInfoDetailsT(offer_help.getHelp_order());
                        data1.setFrom_account(orders.getWithdrawals_phone());
                        data1.setOrder_num(orders.getOrder_num());
                        data1.setUnfreeze_date(offer_help.getUnfreeze_date());
                        float inCome_money = (float) 300.00; //先整收益
                        data1.setIncome_money(inCome_money);
                    }else{ //申请帮助没有收益
                        Orders orders = appServerMapper.getOrderInfoDetailsS(offer_help.getHelp_order());
                        data1.setFrom_account(orders.getWithdrawals_phone());
                        data1.setOrder_num(orders.getOrder_num());
                    }

                }
                getUserOfferHelpInfo.setData1(data1);

                getUserOfferHelpInfos.add(getUserOfferHelpInfo);
            }
        }
        getUserOfferHelpsResp.setData(getUserOfferHelpInfos);
        getUserOfferHelpsResp.setCode(retCode);
        getUserOfferHelpsResp.setMsg(retMsg);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserOfferHelpsResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }


    @RequestMapping(value = "10033")

    @ResponseBody
    public String getUserCommonQuest(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {

        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        GetUserCommonQuestReq getUserCommonQuestReq = JSON.parseObject(msgBody, GetUserCommonQuestReq.class);
        GetUserCommonQuestResp getUserCommonQuestResp = new GetUserCommonQuestResp();

        ArrayList<GetUserCommonQuestInfo> data = new ArrayList<>();
        List<Leaving_Msg> leavingMsgList = appServerMapper.getLeavingMsg_Quest();
        for (int i =0;i<leavingMsgList.size();i++){
            GetUserCommonQuestInfo getUserCommonQuestInfo = new GetUserCommonQuestInfo();
            Leaving_Msg leaving_msg = leavingMsgList.get(i);
            getUserCommonQuestInfo.setContent(leaving_msg.getMsg_content());
            getUserCommonQuestInfo.setReply_content(leaving_msg.getReply_content());
            data.add(getUserCommonQuestInfo);
        }
        getUserCommonQuestResp.setData(data);
        getUserCommonQuestResp.setMsg(retMsg);
        getUserCommonQuestResp.setCode(retCode);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(getUserCommonQuestResp);
        String retMsg = Base64Util.encode(jsonObject.toString());
        try {
            retMsg = URLEncoder.encode(retMsg, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return retMsg;
    }

}
