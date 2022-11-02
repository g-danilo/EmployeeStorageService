package com.example.employeestorage.controller;

import com.example.employeestorage.model.Company;
import com.example.employeestorage.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping(path="/companies")
@Validated
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping(path="/add")
    public String addNewCompany(@RequestParam@NotBlank String companyName, @RequestParam int isActive, @RequestParam int isDeleted) {
        Company c = new Company();
        c.setCompanyName(companyName);
        c.setIsActive(isActive);
        c.setIsDeleted(isDeleted);
        companyRepository.save(c);
        return "redirect:/companies/allActive";
    }

    @GetMapping(path="/all")
    public String getAllCompanies(Model model) {
        Iterable<Company> companies =  companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "allCompanies";
    }

    @GetMapping(path="/allActive")
    public String getAllActiveCompanies(Model model) {
        Iterable<Company> companies = companyRepository.findByIsActiveAndIsDeleted(1, 0);
        model.addAttribute("companies", companies);
        return "allCompanies";
    }

    @GetMapping(path="/{companyId}")
    public String getCompany(@PathVariable int companyId, Model model) {
        Company c = companyRepository.findByCompanyId(companyId);
        model.addAttribute("companyName", c.getCompanyName());
        model.addAttribute("isActive", c.getIsActive());
        model.addAttribute("isDeleted", c.getIsDeleted());
        return "companyDetails";
    }

    @PutMapping(path="/{companyId}")
    public String updateCompany(@RequestParam int companyId, @RequestParam @NotBlank String companyName,
                                              @RequestParam int isActive, @RequestParam int isDeleted) {
        Company c = companyRepository.findByCompanyId(companyId);
        c.setCompanyName(companyName);
        c.setIsActive(isActive);
        c.setIsDeleted(isDeleted);
        companyRepository.save(c);
        return "redirect:/companies/allActive";
    }

    @DeleteMapping(path="/{companyId}")
    public String deleteCompany(@PathVariable int companyId) {
        companyRepository.deleteById(companyId);
        return "redirect:/companies/allActive";
    }

    @PatchMapping(path="/{companyId}")
    public String patchCompany(@PathVariable int companyId) {
        Company c = companyRepository.findByCompanyId(companyId);
        int isDeleted = 1;
        if (c.getIsDeleted() == 1) {
            isDeleted = 0;
        }
        c.setIsDeleted(isDeleted);
        companyRepository.save(c);
        return "redirect:/companies/allActive";
    }

}
