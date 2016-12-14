package com.help.server.domain.tables;

/**
 * Created by houlongbin on 2016/11/11.
 */
public class Da_Show_Corp_City {
    public String getCity() {
        return city;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    private String customer;

    public void setCity(String city) {
        this.city = city;
    }

    private String city;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;
}
