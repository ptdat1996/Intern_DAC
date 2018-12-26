package com.dac.demo.controller;

import com.dac.demo.model.Employee;
import com.dac.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private EmployeeService employeeService;
    // go to login page
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam("userName")String userName,
                             @RequestParam("password") String password,
                             HttpSession session){
        Employee employee = employeeService.findByUserName(userName);
        if(employee != null){
            // found employee
            if(employee.getPassword().equals(password)){
                //password match
                return "/dashboard";
            }
            else {
                //password not match
                return "/error";
            }
        }
        else {
            // not found
            return "/error";
        }
    }
}
