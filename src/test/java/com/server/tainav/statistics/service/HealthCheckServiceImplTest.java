package com.server.tainav.statistics.service;

import com.server.tainav.statistics.exception.HealthCheckException;
import com.server.tainav.statistics.repository.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = HealthCheckServiceImpl.class)
public class HealthCheckServiceImplTest {

    @MockitoBean
    private StatisticsRepository statisticsRepository;

    @Autowired
    private HealthCheckService healthCheckService;

    @Test
    public void testCheckHealthSuccess() throws Exception {
        // Given
        when(statisticsRepository.checkConnection()).thenReturn(true);

        // When & Then
        healthCheckService.checkHealth(); // Should not throw exception
    }

    @Test
    public void testCheckHealthFailure() throws Exception {
        // Given
        when(statisticsRepository.checkConnection()).thenReturn(false);

        // When & Then
        try {
            healthCheckService.checkHealth();
            fail("Expected HealthCheckException to be thrown");
        } catch (HealthCheckException e) {
            assertEquals("Database connection failed", e.getMessage());
        }
    }
}
