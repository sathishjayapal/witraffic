package me.sathish.witrafficreader.core.controller;

import me.sathish.witrafficreader.core.bean.TrafficInformation;
import me.sathish.witrafficreader.core.config.TrafficUrlConfiguration;
import me.sathish.witrafficreader.core.service.TrafficUrlParserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin("*")
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
        System.out.println("The traffic URL is "+trafficUrlConfiguration.getTrafficURL());
        return trafficUrlParserService.parseURL(trafficUrlConfiguration.getTrafficURL(),trafficUrlConfiguration.getKeyForURL());
    }
}
