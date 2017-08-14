package com.wipro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Employee;
import com.wipro.repository.EmployeeRepository;
import com.wipro.transferobject.EmployeeTO;
import com.wipro.transferobject.NameTO;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeTO create(EmployeeTO employee) {

        return copyToEmployee(employeeRepository.save(copyToEntity(employee)));
    }

    @Override
    public EmployeeTO update(EmployeeTO employee) {
        return copyToEmployee(employeeRepository.save(copyToEntity(employee)));
    }

    @Override
    public boolean delete(String empId) {
        employeeRepository.delete(empId);
        return true;
    }

    @Override
    public List<EmployeeTO> getAll() {
        List<EmployeeTO> resultList = new ArrayList<>();
        List<Employee> list = employeeRepository.findAll();
        for (Employee emp : list) {
            resultList.add(copyToEmployee(emp));
        }
        return resultList;
    }

    private EmployeeTO copyToEmployee(Employee emp) {
        EmployeeTO to = new EmployeeTO();
        NameTO name = new NameTO();
        name.setFirstName(emp.getFirstName());
        name.setLastName(emp.getLastName());
        to.setEmpId(emp.getId());
        to.setName(name);
        to.setDepartment(emp.getDepartment());
        to.setDesignation(emp.getDesignation());
        to.setSalary(emp.getSalary());
        return to;
    }

    private Employee copyToEntity(EmployeeTO to) {
        Employee emp = new Employee();
        emp.setId(to.getEmpId());
        emp.setFirstName(to.getName().getFirstName());
        emp.setLastName(to.getName().getLastName());
        emp.setDepartment(to.getDepartment());
        emp.setDesignation(to.getDesignation());
        emp.setSalary(to.getSalary());
        return emp;
    }

}
