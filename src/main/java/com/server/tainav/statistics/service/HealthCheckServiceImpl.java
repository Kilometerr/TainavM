package com.server.tainav.statistics.service;

import com.server.tainav.statistics.exception.HealthCheckException;
import com.server.tainav.statistics.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public void checkHealth() throws HealthCheckException {
        boolean isConnected = statisticsRepository.checkConnection();
        if (!isConnected) {
            throw new HealthCheckException("Database connection failed");
        }
    }
}
