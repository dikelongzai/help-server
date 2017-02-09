package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/22.
 */
public class GetPayInfoBySnResp {

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public LeaderInfo getLeader() {
        return leader;
    }

    public void setLeader(LeaderInfo leader) {
        this.leader = leader;
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

    public String getRemittance_url() {
        return remittance_url;
    }

    public void setRemittance_url(String remittance_url) {
        this.remittance_url = remittance_url;
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
    private LeaderInfo leader;
    private  String remittance_url;

}
