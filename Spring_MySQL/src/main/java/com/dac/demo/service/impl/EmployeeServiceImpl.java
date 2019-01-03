package com.dac.demo.service.impl;

import com.dac.demo.model.Employee;
import com.dac.demo.repository.EmployeeRepository;
import com.dac.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee findByUserName(String userName) {
        return employeeRepository.findByUserName(userName);
    }
}
