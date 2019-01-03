package com.dac.demo.controller;

import com.dac.demo.model.Employee;
import com.dac.demo.service.CustomerService;
import com.dac.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    // go to login page
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             HttpSession session,
                             ModelMap modelMap) {
        Employee employee = employeeService.findByUserName(userName);
        if (employee != null) {
            // found employee
            if (employee.getPassword().equals(password)) {
                //password match
                switch (employee.getRole()) {
                    case "Admin":
                        session.setAttribute("role", "admin");
                        break;
                    case "Manager":
                        session.setAttribute("role", "manager");
                        break;
                    case "Employee":
                        session.setAttribute("role", "employee");
                        break;
                    default:
                        break;
                }
                return "redirect:/employee/dashboard";
            } else {
                //password not match
                modelMap.put("message", "Wrong password!");
                return "/login";
            }
        } else {
            // not found
            modelMap.put("message", "Wrong username!");
            return "/login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(ModelMap modelMap) {
        modelMap.addAttribute("listCustomer", customerService.findAll());
        return "/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        if(session.getAttribute("role") != null){
            session.removeAttribute("role");
            return "redirect:/employee/login";
        }
        return "redirect:/employee/login";
    }
}
