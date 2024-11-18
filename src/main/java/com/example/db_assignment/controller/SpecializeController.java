package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Doctor;
import com.example.db_assignment.entity.DiseaseType;
import com.example.db_assignment.entity.Specialize;
import com.example.db_assignment.service.DoctorService;
import com.example.db_assignment.service.DiseaseTypeService;
import com.example.db_assignment.service.SpecializeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/specializations")
public class SpecializeController {
    private final SpecializeService specializeService;
    private final DiseaseTypeService diseaseTypeService;
    private final DoctorService doctorService;

    public SpecializeController(SpecializeService specializeService, DiseaseTypeService diseaseTypeService, DoctorService doctorService) {
        this.specializeService = specializeService;
        this.diseaseTypeService = diseaseTypeService;
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Specialize> getAllSpecializations() {
        return specializeService.getAllSpecializations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialize> getSpecializationById(@PathVariable Integer id) {
        try {
            Specialize specialize = specializeService.getSpecializationById(id);
            return ResponseEntity.ok(specialize);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createSpecialization(@RequestBody Map<String, Object> payload) {
        try {
            Integer diseaseTypeId = (Integer) payload.get("disease_type_id");
            String doctorEmail = (String) payload.get("doctor_email");

            if (diseaseTypeId == null) {
                throw new IllegalArgumentException("Disease Type ID cannot be null");
            }

            if (doctorEmail == null || doctorEmail.trim().isEmpty()) {
                throw new IllegalArgumentException("Doctor email cannot be null or empty");
            }

            // Check if the DiseaseType exists
            DiseaseType diseaseType = diseaseTypeService.getDiseaseTypeById(diseaseTypeId);
            if (diseaseType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Disease Type not found"));
            }

            // Check if the Doctor exists
            Doctor doctor = doctorService.getDoctorByEmail(doctorEmail);
            if (doctor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Doctor not found"));
            }

            // Create and save the Specialization
            Specialize specialize = new Specialize(diseaseType, doctor);
            Specialize savedSpecialization = specializeService.saveSpecialization(specialize);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedSpecialization);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable Integer id) {
        try {
            specializeService.deleteSpecialization(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
