package com.eliftechTestTask.CompanyApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)

    private int id;

    private String companyName;

    private double estimatedAnnualEarnings;

    private int ownerID;

    public Company(String companyName, double estimatedAnnualEarnings) {
        this.companyName = companyName;
        this.estimatedAnnualEarnings = estimatedAnnualEarnings;
    }
}
