package com.dac.demo.config;

import com.dac.demo.model.Customer;
import com.dac.demo.model.CustomerResult;
import com.dac.demo.processor.CustomerExportProcessor;
import com.dac.demo.reader.CustomerExportReader;
import com.dac.demo.writer.CustomerExportWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseToCSVConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabaseToCSVConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job exportCustomer(){
        return jobBuilderFactory.get("exportJob")
                .incrementer(new RunIdIncrementer())
                .flow(step())
                .end()
                .build();
    }
    @Bean
    public Step step(){
        return stepBuilderFactory.get("step")
                .<Customer, CustomerResult>chunk(10)
                .reader(readerDatabase())
                .processor(processorDatabase())
                .writer(writerCSV())
                .build();
    }
    @Bean(destroyMethod = "")
    public ItemReader<Customer> readerDatabase(){
        return new CustomerExportReader().reader();
    }

    @Bean
    public CustomerExportProcessor processorDatabase(){
        return new CustomerExportProcessor();
    }

    @Bean(destroyMethod = "")
    public ItemWriter<CustomerResult> writerCSV(){
        return new CustomerExportWriter().writer();
    }

}
