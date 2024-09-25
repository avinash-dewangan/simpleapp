package com.avi.in.simpleapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceJDBC {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getEmployeesBySalary(BigDecimal salary) {
        String sql = "CALL GetEmployeesBySalary(?)";

        // Execute the stored procedure with the salary parameter
        return jdbcTemplate.queryForList(sql, salary);
    }
}
