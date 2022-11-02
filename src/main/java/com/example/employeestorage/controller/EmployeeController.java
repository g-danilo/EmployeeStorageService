package com.example.employeestorage.controller;

import com.example.employeestorage.model.Company;
import com.example.employeestorage.model.Employee;
import com.example.employeestorage.repository.CompanyRepository;
import com.example.employeestorage.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping(path="/employees")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping(path="/add")
    public String addNewEmployee(@RequestParam @NotBlank String employeeName, @RequestParam @NotBlank String employeeSurname,
                                 @RequestParam @Email String employeeEmail, @RequestParam String employeeAddress,
                                 @RequestParam double employeeSalary, @RequestParam Integer companyId,
                                 @RequestParam int isActive, @RequestParam int isDeleted) {
        Employee e = new Employee();
        e.setEmployeeName(employeeName);
        e.setEmployeeSurname(employeeSurname);
        e.setEmployeeEmail(employeeEmail);
        e.setEmployeeAddress(employeeAddress);
        e.setEmployeeSalary(employeeSalary);
        e.setCompanyId(companyId);
        e.setIsActive(isActive);
        e.setIsDeleted(isDeleted);
        employeeRepository.save(e);
        return "redirect:/employees/allActive/" + companyId;
    }

    @GetMapping(path="/all/{companyId}")
    public String getAllEmployees(@PathVariable int companyId, Model model) {
        Iterable<Employee> employees =  employeeRepository.findAllByCompanyId(companyId);
        Iterable<Company> companies = companyRepository.findByIsActiveAndIsDeleted(1, 0);
        model.addAttribute("employees", employees);
        model.addAttribute("companies", companies);
        model.addAttribute("companyName", companyRepository.findByCompanyId(companyId).getCompanyName());
        return "allEmployees";
    }

    @GetMapping(path="/allActive/{companyId}")
    public String getAllActiveEmployees(@PathVariable int companyId, Model model) {
        Iterable<Employee> employees = employeeRepository.findByCompanyIdAndIsActiveAndIsDeleted(companyId, 1, 0);
        Iterable<Company> companies = companyRepository.findByIsActiveAndIsDeleted(1, 0);
        model.addAttribute("employees", employees);
        model.addAttribute("companies", companies);
        model.addAttribute("companyName", companyRepository.findByCompanyId(companyId).getCompanyName());
        return "allEmployees";
    }

    @GetMapping(path="/{employeeId}")
    public String getEmployee(@PathVariable int employeeId, Model model) {
        Employee e = employeeRepository.findByEmployeeId(employeeId);
        model.addAttribute("employeeName", e.getEmployeeName());
        model.addAttribute("employeeSurname", e.getEmployeeSurname());
        model.addAttribute("employeeEmail", e.getEmployeeEmail());
        model.addAttribute("employeeAddress", e.getEmployeeAddress());
        model.addAttribute("employeeSalary", e.getEmployeeSalary());
        model.addAttribute("companyId", e.getCompanyId());
        model.addAttribute("isActive", e.getIsActive());
        model.addAttribute("isDeleted", e.getIsDeleted());
        Iterable<Company> c = companyRepository.findByIsActiveAndIsDeleted(1, 0);
        model.addAttribute("companies", c);
        return "employeeDetails";
    }

    @PutMapping(path="/{employeeId}")
    public String updateEmployee(@PathVariable int employeeId, @RequestParam @NotBlank String employeeName,
                                 @RequestParam @NotBlank String employeeSurname, @RequestParam @Email String employeeEmail,
                                 @RequestParam String employeeAddress, @RequestParam double employeeSalary,
                                 @RequestParam Integer companyId, @RequestParam int isActive, @RequestParam int isDeleted) {
        Employee e = employeeRepository.findByEmployeeId(employeeId);
        e.setEmployeeName(employeeName);
        e.setEmployeeSurname(employeeSurname);
        e.setEmployeeEmail(employeeEmail);
        e.setEmployeeAddress(employeeAddress);
        e.setEmployeeSalary(employeeSalary);
        e.setCompanyId(companyId);
        e.setIsActive(isActive);
        e.setIsDeleted(isDeleted);
        employeeRepository.save(e);
        return "redirect:/employees/allActive/" + companyId;
    }

    @DeleteMapping(path="/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        int companyId = employeeRepository.findByEmployeeId(employeeId).getCompanyId();
        employeeRepository.deleteById(employeeId);
        return "redirect:/employees/allActive/" + companyId;
    }

    @GetMapping(path="/avgSalary/{companyId}")
    public String getAvgSalary(@PathVariable int companyId, Model model) {
        double avgSalary = employeeRepository.getAvgSalary(companyId);
        model.addAttribute("avgSalary", avgSalary);
        return "averageSalary";
    }

    @PatchMapping(path="/{employeeId}")
    public String patchCompany(@PathVariable int employeeId) {
        Employee e = employeeRepository.findByEmployeeId(employeeId);
        int isDeleted = 1;
        if (e.getIsDeleted() == 1) {
            isDeleted = 0;
        }
        e.setIsDeleted(isDeleted);
        employeeRepository.save(e);
        int companyId = e.getCompanyId();
        return "redirect:/employees/allActive/" + companyId;
    }
}
