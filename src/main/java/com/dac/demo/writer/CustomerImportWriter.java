package com.dac.demo.writer;

import com.dac.demo.model.Customer;
import com.dac.demo.util.AbsWriter;
import com.dac.demo.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;


public class CustomerImportWriter extends AbsWriter<Customer> {
    private final Logger log = LoggerFactory.getLogger(CustomerImportWriter.class);

    @Autowired
    public DataSource dataSource;

    @Override
    public String getSQLString() {
        return Constant.INSERT_CUSTOMER;
    }

    @Override
    public void write(List<? extends Customer> list) throws Exception {
        log.info("Begin write");
        JdbcBatchItemWriter<Customer> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setDataSource(dataSource);
        writer.setSql(getSQLString());
        writer.afterPropertiesSet();
        writer.write(list);
        log.info("End write");
    }
}
