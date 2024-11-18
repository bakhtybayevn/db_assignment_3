package com.example.db_assignment.repository;

import com.example.db_assignment.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CountryRepository extends JpaRepository<Country, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE country SET cname = :newCname WHERE cname = :oldCname", nativeQuery = true)
    void updateCnameNative(@Param("oldCname") String oldCname, @Param("newCname") String newCname);
}
