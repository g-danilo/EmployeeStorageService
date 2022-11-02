package com.example.employeestorage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.employeestorage.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Iterable<Employee> findByCompanyIdAndIsActiveAndIsDeleted(int companyId, int isActive, int isDeleted);

    Iterable<Employee> findAllByCompanyId(int companyId);

    Employee findByEmployeeId(int employeeId);

    Iterable<Employee> findByCompanyId(int companyId);
    @Query(value = "SELECT coalesce(avg(employee_salary), 0.0) FROM employee" +
            " WHERE company_id = ?1 AND is_active = 1 AND is_deleted = 0", nativeQuery = true)
    Double getAvgSalary(int companyId);
}
