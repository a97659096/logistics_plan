package com.logistics.plan.domain.entity;

/**
 * 路线表
 * @author wsq
 */
public class RealLine {

    /**
     * 起点城市
     */
    private String postingCity;
    /**
     * 到达城市
     */
    private String reachCity;
    /**
     * 起点城市编号
     */
    private String postingCityCode;
    /**
     * 到达城市编号
     */
    private String reachCityCode;
    /**
     * 路线
     */
    private String lineData;


    public String getPostingCity() {
        return postingCity;
    }

    public void setPostingCity(String postingCity) {
        this.postingCity = postingCity;
    }

    public String getReachCity() {
        return reachCity;
    }

    public void setReachCity(String reachCity) {
        this.reachCity = reachCity;
    }

    public String getPostingCityCode() {
        return postingCityCode;
    }

    public void setPostingCityCode(String postingCityCode) {
        this.postingCityCode = postingCityCode;
    }

    public String getReachCityCode() {
        return reachCityCode;
    }

    public void setReachCityCode(String reachCityCode) {
        this.reachCityCode = reachCityCode;
    }

    public String getLineData() {
        return lineData;
    }

    public void setLineData(String lineData) {
        this.lineData = lineData;
    }
}
