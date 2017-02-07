package com.help.server.domain.tables;

import sun.dc.pr.PRError;

/**
 * Created by houxianyong on 2017/1/5.
 */
public class Offer_Help {

    private  long create_date;
    private  long last_update;
    private  char state;
    private  long help_id;
    private  String help_order;
    private  int help_type;
    private int is_income = 1;


    public int getIs_income() {
        return is_income;
    }

    public void setIs_income(int is_income) {
        this.is_income = is_income;
    }

    public Offer_Help() {

    }

    private  long user_id;
    private  String user_phone;
    private  String payment_type;
    private  int help_status;

    public Offer_Help(long create_date, long last_update, char state, long help_id, String help_order, int help_type, long user_id, String user_phone, String payment_type, int help_status, int status_confirmation, float money_num, int wallet_type,int is_income ) {

        this.create_date = create_date;
        this.last_update = last_update;
        this.state = state;
        this.help_id = help_id;
        this.help_order = help_order;
        this.help_type = help_type;
        this.user_id = user_id;
        this.user_phone = user_phone;
        this.payment_type = payment_type;
        this.help_status = help_status;
        this.status_confirmation = status_confirmation;
        this.money_num = money_num;
        this.wallet_type = wallet_type;
        this.is_income = is_income;
    }

    private  int status_confirmation;
    private  float money_num;
    private  int wallet_type;

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public long getHelp_id() {
        return help_id;
    }

    public void setHelp_id(long help_id) {
        this.help_id = help_id;
    }

    public String getHelp_order() {
        return help_order;
    }

    public void setHelp_order(String help_order) {
        this.help_order = help_order;
    }

    public int getHelp_type() {
        return help_type;
    }

    public void setHelp_type(int help_type) {
        this.help_type = help_type;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getHelp_status() {
        return help_status;
    }

    public void setHelp_status(int help_status) {
        this.help_status = help_status;
    }

    public int getStatus_confirmation() {
        return status_confirmation;
    }

    public void setStatus_confirmation(int status_confirmation) {
        this.status_confirmation = status_confirmation;
    }

    public float getMoney_num() {
        return money_num;
    }

    public void setMoney_num(float money_num) {
        this.money_num = money_num;
    }

    public int getWallet_type() {
        return wallet_type;
    }

    public void setWallet_type(int wallet_type) {
        this.wallet_type = wallet_type;
    }
}

