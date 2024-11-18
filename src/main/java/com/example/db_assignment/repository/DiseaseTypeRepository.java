package com.example.db_assignment.repository;

import com.example.db_assignment.entity.DiseaseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseTypeRepository extends JpaRepository<DiseaseType, Integer> {
}
