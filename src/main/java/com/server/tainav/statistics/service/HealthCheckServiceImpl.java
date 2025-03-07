package com.server.tainav.statistics.service;

import com.server.tainav.statistics.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public boolean checkHealth() {
        return statisticsRepository.checkConnection();
    }
}
