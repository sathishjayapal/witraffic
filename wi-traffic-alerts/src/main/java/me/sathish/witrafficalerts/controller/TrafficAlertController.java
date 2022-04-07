package me.sathish.witrafficalerts.controller;

import me.sathish.witrafficalerts.bean.TrafficAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class TrafficAlertController {
    @Autowired
    private Environment environment;

    @RequestMapping("/alerts")
    public TrafficAlert retrieveTrafficAlerts() {
        TrafficAlert trafficAlert = new TrafficAlert();
        trafficAlert.setPort(environment.getProperty("local.server.port"));
        trafficAlert.setEndTime(LocalDate.now().toString());
        trafficAlert.setHighImportance("High");
        String regions[] = {"Dane", "Milwaukee"};
        trafficAlert.setRegions(regions);
        trafficAlert.setSendNotification("Yes");
        return trafficAlert;
    }

}
