package com.server.tainav.statistics.repository;

import org.springframework.data.repository.Repository;

public interface StatisticsRepository extends Repository<Object, Long> {
    boolean checkConnection();
}
