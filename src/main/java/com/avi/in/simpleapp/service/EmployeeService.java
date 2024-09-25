package com.avi.in.simpleapp.service;

import com.avi.in.simpleapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<Object[]> getEmployeesBySalary(BigDecimal salary) {
        try {
            return employeeRepository.getEmployeesBySalary(salary);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
