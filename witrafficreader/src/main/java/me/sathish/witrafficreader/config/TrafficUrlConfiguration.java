package me.sathish.witrafficreader.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("witraffic-url")
public class TrafficUrlConfiguration {
    private String trafficURL;
    private String alertsURL;
    private String winterRoadConditionsURL;

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
