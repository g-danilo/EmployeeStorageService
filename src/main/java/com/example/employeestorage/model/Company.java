package com.example.employeestorage.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Integer companyId;

    @NotBlank(message = "Company name must not be null or blank")
    private String companyName;

    private int isActive;

    private int isDeleted;

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getIsActive() {
        return isActive;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public Integer getCompanyId() {
        return companyId;
    }
}
