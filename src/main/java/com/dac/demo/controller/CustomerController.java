package com.dac.demo.controller;

import com.dac.demo.model.Customer;
import com.dac.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/add")
    public String insert(ModelMap modelMap){
        modelMap.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/add")
    public String insertSubmit(@ModelAttribute("customer") Customer customer){
        Customer cus = new Customer();
        cus.setUserName(customer.getUserName());
        cus.setPassword(customer.getPassword());
        cus.setFullName(customer.getFullName());
        cus.setEmail(customer.getEmail());
        cus.setDob(customer.getDob());

        customerService.save(cus);
        return "redirect:/employee/dashboard";
    }
}
