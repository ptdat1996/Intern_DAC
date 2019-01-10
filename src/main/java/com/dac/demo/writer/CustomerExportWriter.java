package com.dac.demo.writer;

import com.dac.demo.model.CustomerResult;
import com.dac.demo.util.ResourceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

public class CustomerExportWriter {

    private final Logger log = LoggerFactory.getLogger(CustomerExportWriter.class);

    public FlatFileItemWriter<CustomerResult> writer(){
        log.info("Begin write");
        FlatFileItemWriter<CustomerResult> writer = new FlatFileItemWriter<>();
        try {
            writer.setResource(new FileUrlResource(ResourceConstant.CUSTOMER_DATA_EXPORT_FILE));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        writer.setAppendAllowed(true);
        DelimitedLineAggregator<CustomerResult> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
        BeanWrapperFieldExtractor<CustomerResult> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id","name","age"});
        aggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(aggregator);
        writer.setHeaderCallback(writer1 -> writer1.write("id,name,age"));
        return writer;
    }
}
