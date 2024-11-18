package com.example.db_assignment.repository;

import com.example.db_assignment.entity.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    @Query("SELECT r.country.cname AS countryName, SUM(r.totalPatients) AS totalPatients " +
            "FROM Record r " +
            "GROUP BY r.country.cname " +
            "ORDER BY totalPatients DESC")
    List<Object[]> findTopCountriesByTotalPatients(Pageable pageable);

    @Query("SELECT SUM(r.totalPatients) " +
            "FROM Record r " +
            "JOIN r.disease d " +
            "WHERE d.diseaseCode = 'COVID-19'")
    Long findTotalCovid19Patients();
}
