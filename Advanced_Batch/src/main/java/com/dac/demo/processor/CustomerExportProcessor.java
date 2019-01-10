package com.dac.demo.processor;

import com.dac.demo.model.Customer;
import com.dac.demo.model.CustomerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Calendar;

public class CustomerExportProcessor implements ItemProcessor<Customer, CustomerResult> {

    private static final Logger log = LoggerFactory.getLogger(CustomerExportProcessor.class);

    @Override
    public CustomerResult process(Customer customer) throws Exception {
        CustomerResult customerResult = new CustomerResult();
        customerResult.setId(customer.getId());
        customerResult.setName(customer.getName());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int birthYear = customer.getDob().getYear();
        customerResult.setAge(currentYear - birthYear);
        log.info("Calculate age of Customer<" + customer.getId() + "> : " + (currentYear - birthYear));
        return customerResult;
    }
}
