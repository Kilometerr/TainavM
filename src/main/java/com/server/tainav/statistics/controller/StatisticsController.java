package com.server.tainav.statistics.controller;

import com.server.tainav.statistics.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    HealthCheckService healthCheckService;

    @GetMapping("/life")
    private boolean healthCheck() {
        return healthCheckService.checkHealth(); //todo expand functionality to return statistics about baisc/extended health of app
    }
}
