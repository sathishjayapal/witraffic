package me.sathish.witrafficlimitservice.bean;

public class Limits {
    private String trafficURL;
    private String alertsURL;
    private String winterRoadConditionsURL;

    public Limits() {
    }

    public Limits(String trafficURL, String alertsURL, String winterRoadConditionsURL) {
        this.trafficURL = trafficURL;
        this.alertsURL = alertsURL;
        this.winterRoadConditionsURL = winterRoadConditionsURL;
    }

    public String getTrafficURL() {
        return trafficURL;
    }

    public void setTrafficURL(String trafficURL) {
        this.trafficURL = trafficURL;
    }

    public String getAlertsURL() {
        return alertsURL;
    }

    public void setAlertsURL(String alertsURL) {
        this.alertsURL = alertsURL;
    }

    public String getWinterRoadConditionsURL() {
        return winterRoadConditionsURL;
    }

    public void setWinterRoadConditionsURL(String winterRoadConditionsURL) {
        this.winterRoadConditionsURL = winterRoadConditionsURL;
    }
}
