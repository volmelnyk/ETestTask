package com.eliftechTestTask.CompanyApplication.DTOs;

import com.eliftechTestTask.CompanyApplication.models.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class CompanyDTO {

    private int id;
    private String companyName;
    private double estimatedAnnualEarnings;
    private double estimatedAnnualEarningsForChilds;
    private List<CompanyDTO> childs = new ArrayList<>();


    public CompanyDTO() {

    }

    public CompanyDTO(int id, String companyName, double estimatedAnnualEarnings) {
        this.id = id;
        this.companyName = companyName;
        this.estimatedAnnualEarnings = estimatedAnnualEarnings;
    }

    public CompanyDTO(int id, String companyName, double estimatedAnnualEarnings, List<CompanyDTO> list) {


        this.id = id;
        this.companyName = companyName;
        this.estimatedAnnualEarnings = estimatedAnnualEarnings;
        this.estimatedAnnualEarningsForChilds = estimatedAnnualEarnings;
        this.childs = list;
        estimatedAnnualEarningsForChilds();
//        this.estimatedAnnualEarnings = estimatedAnnualEarnings;
    }


    private void estimatedAnnualEarningsForChilds()
    {
        for (CompanyDTO child : this.childs) {

            this.estimatedAnnualEarningsForChilds+=child.estimatedAnnualEarningsForChilds;
        }
    }
}
