package com.dac.demo.util;

public class Constant {

    public static final String GET_CUSTOMER = "SELECT id,name,date_of_birth FROM customers";

    public static final String INSERT_CUSTOMER = "INSERT INTO customers(name,date_of_birth) VALUES (:name, :dob)";

    public static final String UPDATE_CUSTOMER = "UPDATE customers SET name = :name, date_of_birth = :dob WHERE id = :id";

    public static final String CUSTOMER_DATA_IMPORT_FILE = "customer-data-import.csv";

    public static final String CUSTOMER_DATA_EXPORT_FILE = "customer-data-export.csv";
}
