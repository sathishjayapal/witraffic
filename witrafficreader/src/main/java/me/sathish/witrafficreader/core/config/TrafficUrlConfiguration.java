package me.sathish.witrafficreader.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("witraffic-url")
public class TrafficUrlConfiguration {
    private String trafficURL;

    public String getTrafficURL() {
        return trafficURL;
    }

    public void setTrafficURL(String trafficURL) {
        this.trafficURL = trafficURL;
    }
}
