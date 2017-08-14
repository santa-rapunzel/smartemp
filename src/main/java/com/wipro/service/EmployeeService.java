package com.wipro.service;

import java.util.List;

import com.wipro.transferobject.EmployeeTO;

public interface EmployeeService {

    public EmployeeTO create(EmployeeTO employee);

    public EmployeeTO update(EmployeeTO employee);

    public boolean delete(String empId);

    public List<EmployeeTO> getAll();

}
