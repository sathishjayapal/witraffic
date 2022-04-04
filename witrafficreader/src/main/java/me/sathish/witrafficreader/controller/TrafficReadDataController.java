package me.sathish.witrafficreader.controller;

import me.sathish.witrafficreader.bean.TrafficInformation;
import me.sathish.witrafficreader.bean.WITrafficUrl;
import me.sathish.witrafficreader.config.TrafficUrlConfiguration;
import me.sathish.witrafficreader.service.TrafficUrlParserService;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TrafficReadDataController {
    private final TrafficUrlConfiguration trafficUrlConfiguration;
    private final TrafficUrlParserService trafficUrlParserService;

    public TrafficReadDataController(TrafficUrlConfiguration trafficUrlConfiguration, TrafficUrlParserService trafficUrlParserService) {
        this.trafficUrlConfiguration = trafficUrlConfiguration;
        this.trafficUrlParserService = trafficUrlParserService;
    }

    @RequestMapping("/trafficurls")
    public List<TrafficInformation> loadTrafficUrl() {
        return trafficUrlParserService.parseURL(trafficUrlConfiguration.getTrafficURL());
    }
}
