package me.sathish.witrafficreader.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("witraffic-url")
public class TrafficUrlConfiguration {
    private String trafficURL;
    private String keyForURL;
    public String getTrafficURL() {
        return trafficURL;
    }

    public String getKeyForURL() {
        return keyForURL;
    }

    public void setKeyForURL(String keyForURL) {
        this.keyForURL = keyForURL;
    }

    public void setTrafficURL(String trafficURL) {
        this.trafficURL = trafficURL;
    }
}
