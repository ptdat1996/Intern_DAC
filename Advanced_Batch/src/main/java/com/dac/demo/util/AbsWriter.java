package com.dac.demo.util;

import org.springframework.batch.item.ItemWriter;

public abstract class AbsWriter<T> implements ItemWriter<T> {
    public abstract String getSQLString();
}
