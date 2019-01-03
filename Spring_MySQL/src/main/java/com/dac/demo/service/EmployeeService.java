package com.dac.demo.service;


import com.dac.demo.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findByUserName(String userName);
}
