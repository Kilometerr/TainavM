package com.server.tainav.statistics.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testHealthCheckSuccessIntegration() throws Exception {
        // Given
        when(jdbcTemplate.queryForObject("SELECT 1", Integer.class)).thenReturn(1);

        // When & Then
        mockMvc.perform(get("/stats/life"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value("All systems operational"));
    }

    @Test
    public void testHealthCheckFailureIntegration() throws Exception {
        // Given
        when(jdbcTemplate.queryForObject("SELECT 1", Integer.class))
                .thenThrow(new DataAccessException("Database error") {
                });

        // When & Then
        mockMvc.perform(get("/stats/life"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value("Database connection failed"));
    }
}
