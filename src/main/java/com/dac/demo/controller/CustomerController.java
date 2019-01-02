package com.dac.demo.controller;

import com.dac.demo.model.Customer;
import com.dac.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    final int passwordLength = 45;

    @GetMapping("/detail/{id}")
    public String view(@PathVariable("id") Integer id, ModelMap modelMap, HttpSession session) {
        if(session.getAttribute("role") == null || session.getAttribute("role") == "employee"){
            return "redirect:/employee/login";
        }
        modelMap.addAttribute("customer", customerService.findById(id).get());
        return "/detail";
    }

    @GetMapping("/add")
    public String insert(ModelMap modelMap,HttpSession session) {
        if(session.getAttribute("role") == null){
            return "redirect:/employee/login";
        }
        modelMap.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/add")
    public String insertSubmit(@ModelAttribute("customer") Customer customer,ModelMap modelMap) {

        Customer cus = null;
        if (customerService.findByUserName(customer.getUserName()) == null) {
            //user name not in database
            if(customer.getPassword().length() <= passwordLength){
                //password length <= 45
                cus = new Customer();
                cus.setUserName(customer.getUserName());
                cus.setPassword(customer.getPassword());
                cus.setFullName(customer.getFullName());
                cus.setEmail(customer.getEmail());
                cus.setDob(customer.getDob());
                customerService.save(cus);
                return "redirect:/employee/dashboard";
            }
            else {
                // password length > 45
                modelMap.put("message","Password less than 45 characters!");
                return  "/create";
            }
        }
        else {
            // user name in database
            modelMap.put("message","User name already exist, please choose another user name!");
            return "/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable("id") Integer id,HttpSession session) {
        if(session.getAttribute("role") != "admin"){
            return "redirect:/employee/login";
        }
        modelMap.addAttribute("customer", customerService.findById(id).get());
        return "/edit";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute("customer") Customer customer, ModelMap modelMap) {
        if(customer.getPassword().length() <= passwordLength) {
            Customer cus = customerService.findById(customer.getId()).get();
            cus.setPassword(customer.getPassword());
            cus.setFullName(customer.getFullName());
            cus.setEmail(customer.getEmail());
            cus.setDob(customer.getDob());
            customerService.save(cus);
            return "redirect:/employee/dashboard";
        }
        else {
            modelMap.put("message","Password less than 45 characters!");
            return "/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,HttpSession session) {
        if(session.getAttribute("role") != "admin"){
            return "redirect:/employee/login";
        }
        customerService.deleteById(id);
        return "redirect:/employee/dashboard";
    }
}
