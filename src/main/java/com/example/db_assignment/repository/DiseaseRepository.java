package com.example.db_assignment.repository;

import com.example.db_assignment.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, String> {
    @Query("SELECT d FROM Disease d JOIN Discover dis ON d.diseaseCode = dis.diseaseCode " +
            "WHERE d.pathogen = 'Bacteria' AND dis.firstEncDate < :date")
    List<Disease> findDiseasesByPathogenBefore2020(@Param("date") LocalDateTime date);

}
