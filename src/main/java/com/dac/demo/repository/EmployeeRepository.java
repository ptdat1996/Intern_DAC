package com.dac.demo.repository;

import com.dac.demo.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query(nativeQuery = true,name = "SELECT * FROM employees WHERE user_name = :userName")
    Employee findByUserName(@Param("userName") String userName);
}
