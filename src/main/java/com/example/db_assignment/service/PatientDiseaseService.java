package com.example.db_assignment.service;

import com.example.db_assignment.entity.PatientDisease;
import com.example.db_assignment.entity.PatientDiseaseId;
import com.example.db_assignment.repository.PatientDiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDiseaseService {
    private final PatientDiseaseRepository patientDiseaseRepository;

    public PatientDiseaseService(PatientDiseaseRepository patientDiseaseRepository) {
        this.patientDiseaseRepository = patientDiseaseRepository;
    }

    public List<PatientDisease> getAllPatientDiseases() {
        return patientDiseaseRepository.findAll();
    }

    public PatientDisease savePatientDisease(PatientDisease patientDisease) {
        return patientDiseaseRepository.save(patientDisease);
    }

    public void deletePatientDisease(String email, String diseaseCode) {
        PatientDiseaseId id = new PatientDiseaseId(email, diseaseCode);
        patientDiseaseRepository.deleteById(id);
    }
}
