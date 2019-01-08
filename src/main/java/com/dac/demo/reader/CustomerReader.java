package com.dac.demo.reader;

import com.dac.demo.model.Customer;
import com.dac.demo.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class CustomerReader {

    private static final Logger log = LoggerFactory.getLogger(CustomerReader.class);

    @Autowired
    public DataSource dataSource;

    public ItemReader<Customer> reader(){
        JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource());
        reader.setSql(Constant.GET_CUSTOMER);
        reader.setRowMapper((rs,i)->{
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setDob(rs.getDate("date_of_birth").toLocalDate());
            return customer;
        });
        return reader;
    }

    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springbatch_advanced?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
}
