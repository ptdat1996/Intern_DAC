package com.dac.demo.config;


import com.dac.demo.listener.JobCompletionNotificationListener;
import com.dac.demo.model.Customer;
import com.dac.demo.processor.CustomerImportProcessor;
import com.dac.demo.reader.CustomerReaderImportCSV;
import com.dac.demo.util.Constant;
import com.dac.demo.writer.CustomerImportWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importCustomer(JobCompletionNotificationListener listener){
        log.info("Begin job");
        return jobBuilderFactory.get("importCustomer")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepCustomer())
                .end()
                .build();
    }

    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new CustomerReaderImportCSV().readerCSV(Constant.CUSTOMER_DATA_IMPORT_FILE);
    }

    @Bean
    public CustomerImportProcessor processor() {
        return new CustomerImportProcessor();
    }

    @Bean
    public CustomerImportWriter writer() {
        return new CustomerImportWriter();
    }

    @Bean
    public Step stepCustomer() {
        log.info("Start step");
        return stepBuilderFactory.get("stepCustomer")
                .<Customer, Customer>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
