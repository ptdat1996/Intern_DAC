package com.dac.demo.processor;

import com.dac.demo.model.Customer;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class CustomerImportProcessor implements ItemProcessor<Customer,Customer> {
    private final Logger log = LoggerFactory.getLogger(CustomerImportProcessor.class);


    @Override
    public Customer process(Customer customer) throws Exception {
        log.info("Begin process");
        String newName = WordUtils.capitalizeFully(customer.getName());
        log.info("Convert " +customer.getName() + " to " + newName);
        Customer cus = new Customer();
        cus.setName(newName);
        cus.setDob(customer.getDob());

        log.info("End process");
        return cus;
    }
}
