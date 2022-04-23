package me.sathish.witrafficalerts.corealert.controller;

import me.sathish.witrafficalerts.corealert.bean.TrafficAlert;
import me.sathish.witrafficalerts.corealert.config.TrafficAlertUrlConfiguration;
import me.sathish.witrafficalerts.corealert.exceptions.WITrafficAlertException;
import me.sathish.witrafficalerts.corealert.service.TrafficAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TrafficAlertController {
    private final Environment environment;
    private final TrafficAlertUrlConfiguration trafficUrlConfiguration;
    private final TrafficAlertService trafficAlertService;
    Logger LOG = LoggerFactory.getLogger(TrafficAlertController.class);

    public TrafficAlertController(TrafficAlertUrlConfiguration trafficUrlConfiguration, TrafficAlertService trafficAlertService, Environment environment) {
        this.trafficUrlConfiguration = trafficUrlConfiguration;
        this.trafficAlertService = trafficAlertService;
        this.environment = environment;
    }

    @RequestMapping("/alerts")
    public List<TrafficAlert> retrieveTrafficAlerts() {
        try {
            return trafficAlertService.parseURL(trafficUrlConfiguration.getAlertsURL());
        } catch (WITrafficAlertException e) {
            LOG.error("Exception for getting data" + e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }
}
