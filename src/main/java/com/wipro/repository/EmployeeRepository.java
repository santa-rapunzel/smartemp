package com.wipro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Employee;

@Repository("employeeRepository")
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
