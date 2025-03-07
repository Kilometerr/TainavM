package com.server.tainav.statistics.service;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {
    @Override
    public boolean checkHealth() {
        return false;
    }
}
