package com.server.tainav.statistics.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticsRepositoryImpl implements StatisticsRepository {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean checkConnection() {
        try {
            logger.debug("Attempting to check database connection");
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            logger.info("Database connection successful");
            return true;
        } catch (Exception e) {
            logger.error("Database connection failed", e);
            return false;
        }
    }
}
