package com.help.server.domain.responsebean;

/**
 * Created by hou on 2017/1/24.
 */
public class GetUserOfferHelpInfo {

    private String order_num;
    private long money;

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getFrom_date() {
        return from_date;
    }

    public void setFrom_date(long from_date) {
        this.from_date = from_date;
    }

    public int getWallet_type() {
        return wallet_type;
    }

    public void setWallet_type(int wallet_type) {
        this.wallet_type = wallet_type;
    }

    public int getHelp_status() {
        return help_status;
    }

    public void setHelp_status(int help_status) {
        this.help_status = help_status;
    }

    public int getHelp_type() {
        return help_type;
    }

    public void setHelp_type(int help_type) {
        this.help_type = help_type;
    }

    private long from_date;
    private int wallet_type;
    private int help_status;
    private int help_type;
}
