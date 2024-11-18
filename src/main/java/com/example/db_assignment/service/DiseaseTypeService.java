package com.example.db_assignment.service;

import com.example.db_assignment.entity.DiseaseType;
import com.example.db_assignment.repository.DiseaseTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseTypeService {
    private final DiseaseTypeRepository diseaseTypeRepository;

    public DiseaseTypeService(DiseaseTypeRepository diseaseTypeRepository) {
        this.diseaseTypeRepository = diseaseTypeRepository;
    }

    public List<DiseaseType> getAllDiseaseTypes() {
        return diseaseTypeRepository.findAll();
    }

    public DiseaseType getDiseaseTypeById(Integer id) {
        return diseaseTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiseaseType not found for id: " + id));
    }

    public DiseaseType saveDiseaseType(DiseaseType diseaseType) {
        return diseaseTypeRepository.save(diseaseType);
    }

    public void deleteDiseaseType(Integer id) {
        diseaseTypeRepository.deleteById(id);
    }
}
