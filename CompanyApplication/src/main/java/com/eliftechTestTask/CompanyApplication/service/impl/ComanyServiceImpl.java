package com.eliftechTestTask.CompanyApplication.service.impl;

import com.eliftechTestTask.CompanyApplication.DTOs.CompanyDTO;
import com.eliftechTestTask.CompanyApplication.dao.CompanyDao;
import com.eliftechTestTask.CompanyApplication.models.Company;
import com.eliftechTestTask.CompanyApplication.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ComanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;

    @Override
    public List<CompanyDTO> findAllForTree() {

        List<CompanyDTO> companyDTOS = new ArrayList<>();

        List<Company> notNullOwnerId = companyDao.getNotNullOwnerId();

        List<Company> companyList = companyDao.findAll();

        for (Company company : notNullOwnerId) {
            companyList.remove(company);
        }

        for (Company company : companyList) {

            companyDTOS.add(new CompanyDTO(company.getId(), company.getCompanyName(), company.getEstimatedAnnualEarnings(), findAllChild(company.getId())));

        }

        return companyDTOS;
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> companyDTOS = new ArrayList<>();

        for (Company company : companyDao.findAll()) {
            companyDTOS.add(new CompanyDTO(company.getId(), company.getCompanyName(), company.getEstimatedAnnualEarnings()));
        }

        return companyDTOS;
    }

    @Override
    public void add(Company company) {

        companyDao.save(company);
    }

    @Override
    public void edit(Company company) {

        Company companyForEdit = companyDao.getOne(company.getId());

        companyForEdit.setCompanyName(company.getCompanyName());
        companyForEdit.setId(company.getId());
        companyForEdit.setEstimatedAnnualEarnings(company.getEstimatedAnnualEarnings());

        companyDao.save(companyForEdit);
    }

    @Override
    public void delete(int id) {

        for (CompanyDTO companyDTO : findAllForTree()) {
            if(companyDTO.getId() == id)
            {
                for (CompanyDTO dto : companyDTO.getChilds()) {
                    companyDao.deleteById(dto.getId());
                }
            }
        }

        companyDao.deleteById(id);
    }

    @Override
    public CompanyDTO findById(int id) {

        Company company = companyDao.getOne(id);


        return new CompanyDTO(company.getId(),company.getCompanyName(),company.getEstimatedAnnualEarnings());
    }

//    @Override
    private List<CompanyDTO> findAllChild(int id) {
        List<Company> companies = companyDao.findAllByOwnerID(id);

        List<CompanyDTO> companyDTOS = new ArrayList<>();

        for (Company company : companyDao.findAllByOwnerID(id)) {
            companyDTOS.add(new CompanyDTO(company.getId(), company.getCompanyName(), company.getEstimatedAnnualEarnings(), findAllChild(company.getId())));
        }

        return companyDTOS;
    }
}
