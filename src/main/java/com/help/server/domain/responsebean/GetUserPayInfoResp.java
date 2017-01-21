package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/21.
 */
public class GetUserPayInfoResp {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BankInfo getBank() {
        return bank;
    }

    public void setBank(BankInfo bank) {
        this.bank = bank;
    }

    public PaymentInfo getPayment() {
        return payment;
    }

    public void setPayment(PaymentInfo payment) {
        this.payment = payment;
    }

    public WeixinInfo getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinInfo weixin) {
        this.weixin = weixin;
    }

    private BankInfo bank;
    private PaymentInfo payment;
    private WeixinInfo weixin;

}
