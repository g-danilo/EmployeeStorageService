package com.example.employeestorage.repository;

import com.example.employeestorage.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Iterable<Company> findByIsActiveAndIsDeleted(int isActive, int isDeleted);

    Company findByCompanyId(int companyId);
}
