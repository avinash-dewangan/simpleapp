package com.avi.in.simpleapp.repository;

import com.avi.in.simpleapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Call stored procedure using @Procedure
    @Procedure(name = "GetEmployeesBySalary")
    List<Object[]> getEmployeesBySalary(@Param("input_salary") BigDecimal salary);

}
