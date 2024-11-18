package com.example.db_assignment.repository;

import com.example.db_assignment.entity.PatientDisease;
import com.example.db_assignment.entity.PatientDiseaseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDiseaseRepository extends JpaRepository<PatientDisease, PatientDiseaseId> {
}
