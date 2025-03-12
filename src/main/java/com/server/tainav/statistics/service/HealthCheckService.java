package com.server.tainav.statistics.service;

import com.server.tainav.statistics.exception.HealthCheckException;

public interface HealthCheckService {

    void checkHealth() throws HealthCheckException;
}
