package com.server.tainav.statistics.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class StatisticsRepositoryTest {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Test
    public void testCheckConnection() {
        boolean isConnected = statisticsRepository.checkConnection();
        Assertions.assertTrue(isConnected, "Database connection should be successful");
    }
}
