package com.example.employeestorage.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer employeeId;

    @NotBlank(message = "Employee name must not be null or blank")
    private String employeeName;

    @NotBlank(message = "Employee surname must not be null or blank")
    private String employeeSurname;

    @Email
    private String employeeEmail;

    private String employeeAddress;

    private double employeeSalary;
    Integer companyId;

    private int isActive;

    private int isDeleted;

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public int getIsActive() {
        return isActive;
    }

    public int getIsDeleted() {
        return isDeleted;
    }
}
