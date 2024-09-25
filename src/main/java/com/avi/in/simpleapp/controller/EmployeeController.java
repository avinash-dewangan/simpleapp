package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/salary/{amount}")
    public List<Object[]> getEmployeesBySalary(@PathVariable BigDecimal amount) {
        return employeeService.getEmployeesBySalary(amount);
    }
}
