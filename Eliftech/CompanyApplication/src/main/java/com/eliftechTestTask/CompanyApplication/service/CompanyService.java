package com.eliftechTestTask.CompanyApplication.service;

import com.eliftechTestTask.CompanyApplication.DTOs.CompanyDTO;
import com.eliftechTestTask.CompanyApplication.models.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> findAllForTree();
    List<CompanyDTO> findAll();
    void add(Company company);
    void edit(Company company);
    void delete(int id);
    CompanyDTO findById(int id);
//    List<CompanyDTO> findAllChild(int id);

}
