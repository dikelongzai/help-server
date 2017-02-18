package com.help.server.domain.tables;

/**
 * Created by houxianyong on 2017/2/18.
 */
public class Income_calcul_log {

    public Income_calcul_log(){

    }
    public Income_calcul_log(long create_date,long last_update,long income_id
            ,long income_type,long user_id,float money_num,float org_money_num,String helporder){
        this.create_date = create_date;
        this.last_update = last_update;
        this.income_id = income_id;
        this.income_type = income_type;
        this.user_id = user_id;
        this.money_num = money_num;
        this.org_money_num = org_money_num;
        this.helporder = helporder;

    }
    private long id;
    private long create_date;
    private long last_update;
    private long income_id;
    private long income_type;
    private long user_id;
    private float money_num;
    private float org_money_num;
    private String helporder;
    private String income_explain;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getIncome_id() {
        return income_id;
    }

    public void setIncome_id(long income_id) {
        this.income_id = income_id;
    }

    public long getIncome_type() {
        return income_type;
    }

    public void setIncome_type(long income_type) {
        this.income_type = income_type;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public float getMoney_num() {
        return money_num;
    }

    public void setMoney_num(float money_num) {
        this.money_num = money_num;
    }

    public float getOrg_money_num() {
        return org_money_num;
    }

    public void setOrg_money_num(float org_money_num) {
        this.org_money_num = org_money_num;
    }

    public String getHelporder() {
        return helporder;
    }

    public void setHelporder(String helporder) {
        this.helporder = helporder;
    }

    public String getIncome_explain() {
        return income_explain;
    }

    public void setIncome_explain(String income_explain) {
        this.income_explain = income_explain;
    }
}
