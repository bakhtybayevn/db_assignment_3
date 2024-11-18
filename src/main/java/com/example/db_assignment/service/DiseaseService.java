package com.example.db_assignment.service;

import com.example.db_assignment.entity.Disease;
import com.example.db_assignment.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    public Disease getDiseaseByCode(String diseaseCode) {
        return diseaseRepository.findById(diseaseCode)
                .orElseThrow(() -> new RuntimeException("Disease not found for code: " + diseaseCode));
    }

    public Disease saveDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    public void deleteDisease(String diseaseCode) {
        diseaseRepository.deleteById(diseaseCode);
    }

    public List<Disease> getBacterialDiseasesBefore2020() {
        LocalDateTime cutoffDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        return diseaseRepository.findDiseasesByPathogenBefore2020(cutoffDate);
    }
}
