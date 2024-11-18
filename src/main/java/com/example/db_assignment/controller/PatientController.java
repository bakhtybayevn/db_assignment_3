package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Patient;
import com.example.db_assignment.entity.Users;
import com.example.db_assignment.service.PatientService;
import com.example.db_assignment.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;
    private final UsersService usersService;

    public PatientController(PatientService patientService, UsersService usersService) {
        this.patientService = patientService;
        this.usersService = usersService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email) {
        try {
            Patient patient = patientService.getPatientByEmail(email);
            return ResponseEntity.ok(patient);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody Map<String, String> payload) {
        try {
            String email = payload.get("email");
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }

            Users user = usersService.getUserByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "User not found for email: " + email));
            }

            Patient patient = new Patient(user);
            patient.setEmail(user.getEmail()); // Explicitly set the primary key

            Patient savedPatient = patientService.savePatient(patient);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }




    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletePatient(@PathVariable String email) {
        try {
            patientService.deletePatient(email);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
