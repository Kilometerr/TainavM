package com.server.tainav.statistics.service;

import com.server.tainav.statistics.exception.HealthCheckException;
import org.springframework.stereotype.Service;

public interface HealthCheckService {

    void checkHealth() throws HealthCheckException;
}
