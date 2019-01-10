package com.dac.demo.reader;

import com.dac.demo.model.Customer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class CustomerImportReaderCSV implements FieldSetMapper<Customer> {

    private final Logger log = LoggerFactory.getLogger(CustomerImportReaderCSV.class);

    private final String datePattern = "dd-MM-yyyy";

    private final int nameLength = 45;

    public FlatFileItemReader<Customer> readerCSV(String path) {
        FlatFileItemReader<Customer> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource(path));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(getDelimiter());
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(getFieldsName());
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(this);
        return lineMapper;
    }

    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        log.info("FieldSet:{} ", fieldSet);
        Customer customer = null;
        if (fieldSet == null){
            return null;
        }
        if(!isNameValid(fieldSet.readString("name"))){
            return new Customer();
        }
        if(!isDateValid(fieldSet.readString("dob").trim())){
            return new Customer();
        }
        customer = new Customer();
        customer.setName(fieldSet.readString("name"));
        customer.setDob(convertToDate(fieldSet.readString("dob").trim()));
        return customer;
    }

//    private Customer mapField(FieldSet fieldSet) {
//        log.info("FieldSet:{} ", fieldSet);
//        Customer customer;
//        if (fieldSet == null){
//            return null;
//        }
//        if(isNameValid(fieldSet.readString("name"))){
//            fieldSet = null;
//        }
//        customer = new Customer();
//        customer.setName(fieldSet.readString("name"));
//        customer.setDob(convertToDate(fieldSet.readString("dob").trim()));
//        return customer;
//    }


    private String[] getFieldsName() {
        return new String[]{"name", "dob"};
    }


    private String getDelimiter() {
        return DelimitedLineTokenizer.DELIMITER_COMMA;
    }

    private LocalDate convertToDate(String input) {
        String strSplit[] = input.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(strSplit[2]), Integer.parseInt(strSplit[1]), Integer.parseInt(strSplit[0]));
        return date;
    }

    private boolean isDateValid(String input){
        if(input.isEmpty()){
            log.error("Date is empty");
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
        try{
            Date date = dateFormat.parse(input);
        }
        catch (ParseException e){
            log.error("Invalid date format");
            return false;
        }
        return true;
    }

    private boolean isNameValid(String name){
        if(name.isEmpty()){
            log.error("Name is empty");
            return false;
        }
        if(name.length() > nameLength){
            log.error("Name less than {} characters", nameLength);
            return false;
        }
        return true;
    }
}
