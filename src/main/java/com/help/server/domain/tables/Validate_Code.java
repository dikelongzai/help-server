
package com.help.server.domain.tables;

/**
 * Created by houxianyong on 2017/1/3.
 */
public class Validate_Code {

    public Validate_Code() {
    }

    public Validate_Code(String user_phone, String user_imei, int validate_type, String validate_code
            , long create_date, long last_update, char state, long validate_id) {

        this.create_date = create_date;
        this.last_update = last_update;
        this.state = state;
        this.validate_id = validate_id;
        this.user_phone = user_phone;
        this.user_imei = user_imei;
        this.validate_type = validate_type;
        this.validate_code = validate_code;
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

    public long getValidate_id() {
        return validate_id;
    }

    public void setValidate_id(long validate_id) {
        this.validate_id = validate_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_imei() {
        return user_imei;
    }

    public void setUser_imei(String user_imei) {
        this.user_imei = user_imei;
    }

    public int getValidate_type() {
        return validate_type;
    }

    public void setValidate_type(int validate_type) {
        this.validate_type = validate_type;
    }

    public String getValidate_code() {
        return validate_code;
    }

    public void setValidate_code(String validate_code) {
        this.validate_code = validate_code;
    }

    private  long create_date;
    private  long last_update;
    private  char state;
    private  long validate_id;
    private  String user_phone;
    private  String user_imei;
    private  int validate_type;
    private  String validate_code;

}
