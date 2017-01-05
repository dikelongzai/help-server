
package com.help.server.domain.tables;

/**
 * Created by houxianyong on 2016/12/29.
 */
public class User_Member {

    public User_Member() {
    }

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

    public String getUser_referee_phone() {
        return user_referee_phone;
    }

    public void setUser_referee_phone(String user_referee_phone) {
        this.user_referee_phone = user_referee_phone;
    }

    public long getReferee_user_id() {
        return referee_user_id;
    }

    public User_Member(Long user_id,String user_name, String user_login_pwd, String user_phone
            ,String user_referee_phone, long referee_user_id, long create_date, long last_update
            , char state, Integer title_id, Integer usable_code_num, Integer used_code_num,Integer is_activate) {
        this.user_name = user_name;
        this.user_login_pwd = user_login_pwd;
        this.user_id = user_id;
        this.user_referee_phone = user_referee_phone;
        this.referee_user_id = referee_user_id;
        this.is_activate = is_activate;
        this.user_phone = user_phone;
        this.usable_code_num = usable_code_num;
        this.used_code_num = used_code_num;
        this.title_id = title_id;
        this.create_date = create_date;
        this.last_update = last_update;
        this.state = state;
    }

    public String getUser_login_pwd() {
        return user_login_pwd;
    }

    public void setUser_login_pwd(String user_login_pwd) {
        this.user_login_pwd = user_login_pwd;
    }

    public void setReferee_user_id(long referee_user_id) {
        this.referee_user_id = referee_user_id;
    }
    //用户名
    private String user_name;
    //用户密码
    private  String user_login_pwd;
    //用户id
    private Long user_id;
    //推荐人信息
    private String user_referee_phone;
    private long referee_user_id;
    //是账号是否激活
    private Integer is_activate;
    //用户电话
    private String user_phone;
    //可用激活码的数量
    private  Integer usable_code_num =0;
    //已用激活码的数量
    private  Integer used_code_num =0;
    //个人等级id
    private Integer title_id = 0;

    private  long create_date;
    private  long last_update;
    private char state ='N';
}
