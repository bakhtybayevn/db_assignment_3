package com.example.db_assignment.service;

import com.example.db_assignment.entity.Patient;
import com.example.db_assignment.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Patient not found for email: " + email));
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Patient savePatient(Patient patient) {
        if (patient.getUser() == null) {
            throw new RuntimeException("User is required to create a Patient");
        }

        if (patient.getEmail() == null || !patient.getEmail().equals(patient.getUser().getEmail())) {
            patient.setEmail(patient.getUser().getEmail());
        }

        Patient existingPatient = patientRepository.findById(patient.getEmail()).orElse(null);
        if (existingPatient != null) {
            throw new RuntimeException("Patient already exists with email: " + patient.getEmail());
        }

        // Ensure both `Patient` and `Users` are managed by the persistence context
        entityManager.merge(patient.getUser());
        Patient savedPatient = entityManager.merge(patient);

        return savedPatient;
    }


    public void deletePatient(String email) {
        patientRepository.deleteById(email);
    }
}
