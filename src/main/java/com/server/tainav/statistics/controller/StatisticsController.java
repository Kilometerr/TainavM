package com.server.tainav.statistics.controller;

import com.server.tainav.statistics.controller.response.HealthResponse;
import com.server.tainav.statistics.exception.HealthCheckException;
import com.server.tainav.statistics.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    HealthCheckService healthCheckService;

    @GetMapping("/life")
    public ResponseEntity<?> checkHealth() {
        try {
            healthCheckService.checkHealth();
            return ResponseEntity.ok().body(new HealthResponse(true, "All systems operational"));
        } catch (HealthCheckException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new HealthResponse(false, e.getMessage()));
        }
    }
}
