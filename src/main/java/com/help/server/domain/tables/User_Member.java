
package com.help.server.domain.tables;

import java.util.StringTokenizer;

/**
 * Created by houxianyong on 2016/12/29.
 */
public class User_Member {

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getIs_activate() {
        return is_activate;
    }

    public void setIs_activate(Integer is_activate) {
        this.is_activate = is_activate;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Integer getIs_freeze() {
        return is_freeze;
    }

    public void setIs_freeze(Integer is_freeze) {
        this.is_freeze = is_freeze;
    }

    public String getUser_head_url() {
        return user_head_url;
    }

    public void setUser_head_url(String user_head_url) {
        this.user_head_url = user_head_url;
    }

    public String getUser_bank_name() {
        return user_bank_name;
    }

    public void setUser_bank_name(String user_bank_name) {
        this.user_bank_name = user_bank_name;
    }

    public String getUser_bank_account() {
        return user_bank_account;
    }

    public void setUser_bank_account(String user_bank_account) {
        this.user_bank_account = user_bank_account;
    }

    public String getUser_payment() {
        return user_payment;
    }

    public void setUser_payment(String user_payment) {
        this.user_payment = user_payment;
    }

    public String getUser_weixin() {
        return user_weixin;
    }

    public void setUser_weixin(String user_weixin) {
        this.user_weixin = user_weixin;
    }

    public Float getUstatic_wallet() {
        return ustatic_wallet;
    }

    public void setUstatic_wallet(Float ustatic_wallet) {
        this.ustatic_wallet = ustatic_wallet;
    }

    public Float getUdynamic_wallet() {
        return udynamic_wallet;
    }

    public void setUdynamic_wallet(Float udynamic_wallet) {
        this.udynamic_wallet = udynamic_wallet;
    }

    public Float getUfrozen_wallet() {
        return ufrozen_wallet;
    }

    public void setUfrozen_wallet(Float ufrozen_wallet) {
        this.ufrozen_wallet = ufrozen_wallet;
    }

    public Integer getUsable_code_num() {
        return usable_code_num;
    }

    public void setUsable_code_num(Integer usable_code_num) {
        this.usable_code_num = usable_code_num;
    }

    public Integer getUsed_code_num() {
        return used_code_num;
    }

    public void setUsed_code_num(Integer used_code_num) {
        this.used_code_num = used_code_num;
    }

    public Integer getTitle_id() {
        return title_id;
    }

    public void setTitle_id(Integer title_id) {
        this.title_id = title_id;
    }

    //用户名
    private String user_name;
    //用户id
    private Long user_id;
    //是账号是否激活
    private Integer is_activate;
    //用户电话
    private String user_phone;
    //是否冻结
    private Integer is_freeze;
    //用户头像
    private String user_head_url;
    //用户银行名称和开开户行
    private String user_bank_name;
    //银行账号
    private String user_bank_account;
    //支付宝账号
    private  String user_payment;
    //微信账号
    private String user_weixin;
    //静态钱包金额
    private Float ustatic_wallet;
    //动台钱包金额
    private Float udynamic_wallet;
    //冻结钱包金额
    private Float ufrozen_wallet;
    //可用激活码的数量
    private  Integer usable_code_num;
    //已用激活码的数量
    private  Integer used_code_num;
    //个人等级id
    private Integer title_id;
}
