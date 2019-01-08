package com.dac.demo.reader;

import com.dac.demo.model.Customer;
import com.dac.demo.util.AbsReader;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.FieldSet;

import java.time.LocalDate;


public class CustomerImportReaderCSV extends AbsReader<Customer> {

    private final Logger log = LoggerFactory.getLogger(CustomerImportReaderCSV.class);


    @Override
    protected Customer mapField(FieldSet fieldSet) {
        log.info("FieldSet:{} ", fieldSet);
        if (fieldSet == null)
            return null;
        Customer customer = new Customer();
        customer.setName(fieldSet.readString("name"));
        customer.setDob(convertToDate(fieldSet.readString("dob")));
        return customer;
    }

    @Override
    protected String[] getFieldsName() {
        return new String[]{"name", "dob"};
    }

    @Override
    protected String getDelimiter() {
        return DelimitedLineTokenizer.DELIMITER_COMMA;
    }

    private LocalDate convertToDate(String input) {
        String strSplit[] = input.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(strSplit[2]), Integer.parseInt(strSplit[1]), Integer.parseInt(strSplit[0]));
        return date;
    }
}
