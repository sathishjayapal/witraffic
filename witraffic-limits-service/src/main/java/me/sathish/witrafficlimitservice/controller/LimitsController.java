package me.sathish.witrafficlimitservice.controller;

import me.sathish.witrafficlimitservice.bean.Limits;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @RequestMapping("/limits")
    public Limits getLimits(){
        return new Limits();
    }
}
