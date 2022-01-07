package com.andile.gumada.employee.manager.repository;

import com.andile.gumada.employee.manager.model.CountryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryMasterRepository extends JpaRepository<CountryMaster, Integer> {

    CountryMaster findByPhoneCode(int countryCode);
}