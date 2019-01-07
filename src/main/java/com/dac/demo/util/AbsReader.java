package com.dac.demo.util;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;

import org.springframework.validation.BindException;

public abstract class AbsReader<T> implements FieldSetMapper<T> {

    public FlatFileItemReader<T> readerCSV(String path) {
        FlatFileItemReader<T> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource(path));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    private LineMapper<T> lineMapper() {
        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(getDelimiter());
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(getFieldsName());
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(this);
        return lineMapper;
    }

    @Override
    public T mapFieldSet(FieldSet fieldSet) throws BindException {
        return mapField(fieldSet);
    }

    protected abstract T mapField(FieldSet fieldSet);

    protected abstract String[] getFieldsName();

    protected abstract String getDelimiter();
}
