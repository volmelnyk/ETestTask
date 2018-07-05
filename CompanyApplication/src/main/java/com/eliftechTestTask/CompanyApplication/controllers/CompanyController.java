package com.eliftechTestTask.CompanyApplication.controllers;

import com.eliftechTestTask.CompanyApplication.DTOs.CompanyDTO;
import com.eliftechTestTask.CompanyApplication.models.Company;
import com.eliftechTestTask.CompanyApplication.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add-company")
    void addCompany(@RequestBody Company company) {
        companyService.add(company);
    }
    @PostMapping("/edit-company")
    void editCompany(@RequestBody Company company) {
        companyService.edit(company);
    }


    @GetMapping("/get-company/{id}")
    CompanyDTO addCompany(@PathVariable(name = "id") int id) {

        return companyService.findById(id);
    }

    @GetMapping("/get-all-companies-for-tree")
    List<CompanyDTO> getAllCompaniseForTree()
    {
       return  companyService.findAllForTree();
    }

    @GetMapping("/get-all-companies")
    List<CompanyDTO> getAllCompanise()
    {
        return  companyService.findAll();
    }

    @GetMapping("/delete/{id}")
    void deleteCompane(@PathVariable(name = "id") int id)
    {
        companyService.delete(id);
    }


}
