package com.dac.demo.config;

import com.dac.demo.model.Customer;
import com.dac.demo.model.CustomerResult;
import com.dac.demo.processor.CustomerProcessor;
import com.dac.demo.reader.CustomerReader;
import com.dac.demo.writer.CustomerWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DatabaseToCSVConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabaseToCSVConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job exportJob(){
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
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    @Bean(destroyMethod = "")
    public ItemReader<Customer> reader(){
        return new CustomerReader().reader();
    }

    @Bean
    public CustomerProcessor processor(){
        return new CustomerProcessor();
    }

    @Bean(destroyMethod = "")
    public ItemWriter<CustomerResult> writer(){
        return new CustomerWriter().writer();
    }

}
