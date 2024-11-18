package com.example.db_assignment.service;

import com.example.db_assignment.dto.DoctorInfoDTO;
import com.example.db_assignment.entity.Doctor;
import com.example.db_assignment.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorByEmail(String email) {
        return doctorRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found for email: " + email));
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(String email) {
        doctorRepository.deleteById(email);
    }

    public List<DoctorInfoDTO> getDoctorsNotSpecializedInInfectiousDiseases() {
        return doctorRepository.findDoctorsNotSpecializedInInfectiousDiseases();
    }

    public List<DoctorInfoDTO> getDoctorsSpecializedInMoreThanTwoDiseaseTypes() {
        return doctorRepository.findDoctorsSpecializedInMoreThanTwoDiseaseTypes();
    }

    public List<Map<String, Object>> getAverageSalaryOfVirologyDoctorsByCountry() {
        List<Object[]> results = doctorRepository.findAverageSalaryOfVirologyDoctorsByCountry();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> record = new HashMap<>();
            record.put("country", row[0]);
            record.put("averageSalary", row[1]);
            response.add(record);
        }
        return response;
    }
}
