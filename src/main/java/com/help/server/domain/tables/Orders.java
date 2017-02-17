package com.help.server.domain.tables;

import com.help.server.domain.responsebean.SendMoneyInfo;

/**
 * Created by houxianyong on 2017/1/5.
 */
public class   Orders {

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public long getLast_date() {
        return last_date;
    }

    public void setLast_date(long last_date) {
        this.last_date = last_date;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getRecharge_order() {
        return recharge_order;
    }

    public void setRecharge_order(String recharge_order) {
        this.recharge_order = recharge_order;
    }

    public String getRecharge_phone() {
        return recharge_phone;
    }

    public void setRecharge_phone(String recharge_phone) {
        this.recharge_phone = recharge_phone;
    }

    public long getRecharge_uid() {
        return recharge_uid;
    }

    public void setRecharge_uid(long recharge_uid) {
        this.recharge_uid = recharge_uid;
    }

    public String getWithdrawals_order() {
        return withdrawals_order;
    }

    public void setWithdrawals_order(String withdrawals_order) {
        this.withdrawals_order = withdrawals_order;
    }

    public String getWithdrawals_phone() {
        return withdrawals_phone;
    }

    public void setWithdrawals_phone(String withdrawals_phone) {
        this.withdrawals_phone = withdrawals_phone;
    }

    public long getWithdrawals_uid() {
        return withdrawals_uid;
    }

    public void setWithdrawals_uid(long withdrawals_uid) {
        this.withdrawals_uid = withdrawals_uid;
    }

    public float getMoney_num() {
        return money_num;
    }

    public void setMoney_num(float money_num) {
        this.money_num = money_num;
    }

    public int getComplaint_status() {
        return complaint_status;
    }

    public void setComplaint_status(int complaint_status) {
        this.complaint_status = complaint_status;
    }

    public String getRemittance_url() {
        return remittance_url;
    }

    public void setRemittance_url(String remittance_url) {
        this.remittance_url = remittance_url;
    }

    public long getFrom_date() {
        return from_date;
    }

    public void setFrom_date(long from_date) {
        this.from_date = from_date;
    }

    public long getTo_date() {
        return to_date;
    }

    public void setTo_date(long to_date) {
        this.to_date = to_date;
    }

    public long getMatch_date() {
        return match_date;
    }

    public void setMatch_date(long match_date) {
        this.match_date = match_date;
    }

    public long getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(long confirm_date) {
        this.confirm_date = confirm_date;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }
    public long getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(long payment_date) {
        this.payment_date = payment_date;
    }
    private long create_date;
    private  long last_date;
    private char state;
    private long order_id;
    private  int order_type;
    private  String recharge_order;
    private  String recharge_phone;
    private  long recharge_uid;
    private  String withdrawals_order;
    private  String withdrawals_phone;
    private  long withdrawals_uid;
    private  float  money_num;
    private  int complaint_status;
    private  String remittance_url;
    private  long from_date;
    private  long to_date;
    private  long match_date;
    private  long confirm_date;
    private  String order_num;
    private  long  payment_date;
}

