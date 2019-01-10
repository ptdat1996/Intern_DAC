package com.dac.demo.processor;

import com.dac.demo.model.Customer;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CustomerImportProcessor implements ItemProcessor<Customer, Customer> {

    private final Logger log = LoggerFactory.getLogger(CustomerImportProcessor.class);

    @Override
    public Customer process(Customer customer) throws Exception {
        if(customer.getName() == null){
            return null;
        }
        String newName = WordUtils.capitalizeFully(customer.getName());
        log.info("Convert " + customer.getName() + " to " + newName);
        customer.setName(newName);
        return customer;
    }
}
