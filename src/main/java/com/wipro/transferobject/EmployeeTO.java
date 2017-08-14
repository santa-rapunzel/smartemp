package com.wipro.transferobject;

import java.math.BigDecimal;

public class EmployeeTO {

    private String empId;

    private NameTO name;

    private String department;

    private String designation;

    private BigDecimal salary;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public NameTO getName() {
        return name;
    }

    public void setName(NameTO name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
