package me.sathish.witrafficreader.controller;

import me.sathish.witrafficreader.bean.TrafficInformation;
import me.sathish.witrafficreader.config.TrafficUrlConfiguration;
import me.sathish.witrafficreader.service.TrafficUrlParserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
