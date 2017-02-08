package com.help.server.domain.tables;

/**
 * Created by hou on 2017/2/8.
 */
public class Dynamic_Award {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Long create_date) {
        this.create_date = create_date;
    }

    public Long getLast_update() {
        return last_update;
    }

    public void setLast_update(Long last_update) {
        this.last_update = last_update;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(Long dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public Integer getDirect_num() {
        return direct_num;
    }

    public void setDirect_num(Integer direct_num) {
        this.direct_num = direct_num;
    }

    public Integer getTeam_num() {
        return team_num;
    }

    public void setTeam_num(Integer team_num) {
        this.team_num = team_num;
    }

    public Float getOne_generation() {
        return one_generation;
    }

    public void setOne_generation(Float one_generation) {
        this.one_generation = one_generation;
    }

    public Float getTwo_generation() {
        return two_generation;
    }

    public void setTwo_generation(Float two_generation) {
        this.two_generation = two_generation;
    }

    public Float getThree_generation() {
        return three_generation;
    }

    public void setThree_generation(Float three_generation) {
        this.three_generation = three_generation;
    }

    public Float getFour_generation() {
        return four_generation;
    }

    public void setFour_generation(Float four_generation) {
        this.four_generation = four_generation;
    }

    public Long getUser_title_id() {
        return user_title_id;
    }

    public void setUser_title_id(Long user_title_id) {
        this.user_title_id = user_title_id;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }


    private Long id;
    private Long create_date;
    private  Long last_update;
    private String state;

    public Float getD_limit() {
        return d_limit;
    }

    public void setD_limit(Float d_limit) {
        this.d_limit = d_limit;
    }

    private Long dynamic_id;
    private Integer direct_num;
    private Integer team_num;
    private Float one_generation;
    private Float two_generation;
    private Float three_generation;
    private Float four_generation;
    private Long user_title_id;
    private String user_title;
    private Float d_limit;
}
