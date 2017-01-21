package com.help.server.domain.tables;

/**
 * Created by hou on 2017/1/21.
 */
public class UserPayInfo {

   private String user_bank;
   private String user_bank_site;

    public String getUser_bank() {
        return user_bank;
    }

    public void setUser_bank(String user_bank) {
        this.user_bank = user_bank;
    }

    public String getUser_bank_site() {
        return user_bank_site;
    }

    public void setUser_bank_site(String user_bank_site) {
        this.user_bank_site = user_bank_site;
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

    public String getUser_payment_name() {
        return user_payment_name;
    }

    public void setUser_payment_name(String user_payment_name) {
        this.user_payment_name = user_payment_name;
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

    private String user_bank_name;
   private String user_bank_account;
   private String user_payment_name;
   private String user_payment;
   private String user_weixin;
}
