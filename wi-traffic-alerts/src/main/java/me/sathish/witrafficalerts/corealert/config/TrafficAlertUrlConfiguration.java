package me.sathish.witrafficalerts.corealert.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("wi-traffic-alerts-url") // This name should match the application name and properties file in the config server.
public class TrafficAlertUrlConfiguration {
    private String alertsURL;

    public String getAlertsURL() {
        return alertsURL;
    }

    public void setAlertsURL(String alertsURL) {
        this.alertsURL = alertsURL;
    }
}
