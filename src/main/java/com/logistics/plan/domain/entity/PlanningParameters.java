package com.logistics.plan.domain.entity;


public class PlanningParameters {

    //装载邮件种类(1-标快|快包,2-标快,3-快包)
    private String load_mail_type;

    //处理中心出发时间
    private String center_tw1;

    //投递部最早访问时间
    private String node_tw1;

    //趟车最晚从投递部出发时间
    private String node_tw2;

    //燃油价格(元/升)
    private String fuel_price;

    //每辆车最多串行投递数量
    private String max_customer_num;

    //是否要求车辆处理中心
    private String is_return;

    //单个邮件卸车时长
    private String time_per_mail;

    //允许车辆在投递部的最大停留时长
    private String allow_waiting_time;

    //车辆最大工作时长
    private String total_working_time;

    //改进速率
    private String decrease_rate;

    //改进次数上限
    private String counter_limiter;

    //最大求解时长
    private String time_limit_seconds;

    public String getLoad_mail_type() {
        return load_mail_type;
    }

    public void setLoad_mail_type(String load_mail_type) {
        this.load_mail_type = load_mail_type;
    }

    public String getCenter_tw1() {
        return center_tw1;
    }

    public void setCenter_tw1(String center_tw1) {
        this.center_tw1 = center_tw1;
    }

    public String getNode_tw1() {
        return node_tw1;
    }

    public void setNode_tw1(String node_tw1) {
        this.node_tw1 = node_tw1;
    }

    public String getNode_tw2() {
        return node_tw2;
    }

    public void setNode_tw2(String node_tw2) {
        this.node_tw2 = node_tw2;
    }

    public String getFuel_price() {
        return fuel_price;
    }

    public void setFuel_price(String fuel_price) {
        this.fuel_price = fuel_price;
    }

    public String getMax_customer_num() {
        return max_customer_num;
    }

    public void setMax_customer_num(String max_customer_num) {
        this.max_customer_num = max_customer_num;
    }

    public String getIs_return() {
        return is_return;
    }

    public void setIs_return(String is_return) {
        this.is_return = is_return;
    }

    public String getTime_per_mail() {
        return time_per_mail;
    }

    public void setTime_per_mail(String time_per_mail) {
        this.time_per_mail = time_per_mail;
    }

    public String getAllow_waiting_time() {
        return allow_waiting_time;
    }

    public void setAllow_waiting_time(String allow_waiting_time) {
        this.allow_waiting_time = allow_waiting_time;
    }

    public String getTotal_working_time() {
        return total_working_time;
    }

    public void setTotal_working_time(String total_working_time) {
        this.total_working_time = total_working_time;
    }

    public String getDecrease_rate() {
        return decrease_rate;
    }

    public void setDecrease_rate(String decrease_rate) {
        this.decrease_rate = decrease_rate;
    }

    public String getCounter_limiter() {
        return counter_limiter;
    }

    public void setCounter_limiter(String counter_limiter) {
        this.counter_limiter = counter_limiter;
    }

    public String getTime_limit_seconds() {
        return time_limit_seconds;
    }

    public void setTime_limit_seconds(String time_limit_seconds) {
        this.time_limit_seconds = time_limit_seconds;
    }
}
