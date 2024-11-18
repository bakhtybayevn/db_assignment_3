package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Disease;
import com.example.db_assignment.entity.PatientDisease;
import com.example.db_assignment.entity.Users;
import com.example.db_assignment.service.DiseaseService;
import com.example.db_assignment.service.PatientDiseaseService;
import com.example.db_assignment.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patient-diseases")
public class PatientDiseaseController {
    private final PatientDiseaseService patientDiseaseService;
    private final UsersService usersService;
    private final DiseaseService diseaseService;

    public PatientDiseaseController(PatientDiseaseService patientDiseaseService, UsersService usersService, DiseaseService diseaseService) {
        this.patientDiseaseService = patientDiseaseService;
        this.usersService = usersService;
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public List<PatientDisease> getAllPatientDiseases() {
        return patientDiseaseService.getAllPatientDiseases();
    }

    @PostMapping
    public ResponseEntity<?> createPatientDisease(@RequestBody Map<String, String> payload) {
        try {
            String email = payload.get("email");
            String diseaseCode = payload.get("disease_code");

            Users user = usersService.getUserByEmail(email);
            Disease disease = diseaseService.getDiseaseByCode(diseaseCode);

            PatientDisease patientDisease = new PatientDisease(user, disease);
            PatientDisease savedPatientDisease = patientDiseaseService.savePatientDisease(patientDisease);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatientDisease);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePatientDisease(@RequestParam String email, @RequestParam String diseaseCode) {
        try {
            patientDiseaseService.deletePatientDisease(email, diseaseCode);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
