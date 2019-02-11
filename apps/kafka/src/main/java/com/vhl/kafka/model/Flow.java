/*
 * Creation : 24 janv. 2019
 */
package com.vhl.kafka.model;

public class Flow {

    public Flow() {
    };

    public Flow(Integer id, Integer lat, Integer lon, Integer alert, Boolean status) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.alert = alert;
        this.status = status;
    };

    private Integer id;
    private Integer lat;
    private Integer lon;
    private Integer alert;
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public Integer getAlert() {
        return alert;
    }

    public void setAlert(Integer alert) {
        this.alert = alert;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String toString() {
        String str = "ID : " + id + " status : " + status;
        return str;
    }

}
