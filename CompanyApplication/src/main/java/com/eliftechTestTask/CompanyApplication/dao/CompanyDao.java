package com.eliftechTestTask.CompanyApplication.dao;

import com.eliftechTestTask.CompanyApplication.models.Company;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyDao extends JpaRepository<Company, Integer>{

    List<Company> findAllByOwnerID(int id);

    @Modifying
    @Transactional
    @Query("From Company C WHERE C.ownerID != 0")
    List<Company> getNotNullOwnerId();
}
