package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Doctor;
import com.example.db_assignment.entity.Users;
import com.example.db_assignment.service.DoctorService;
import com.example.db_assignment.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final UsersService usersService;

    public DoctorController(DoctorService doctorService, UsersService usersService) {
        this.doctorService = doctorService;
        this.usersService = usersService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email) {
        try {
            Doctor doctor = doctorService.getDoctorByEmail(email);
            return ResponseEntity.ok(doctor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createDoctor(@RequestBody Map<String, String> payload) {
        try {
            String email = payload.get("email");
            String degree = payload.get("degree");

            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }

            if (degree == null || degree.trim().isEmpty()) {
                throw new IllegalArgumentException("Degree cannot be null or empty");
            }

            // Check if the user exists
            Users user = usersService.getUserByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "User not found"));
            }

            // Create and save the Doctor entity
            Doctor doctor = new Doctor(email, user, degree);
            Doctor savedDoctor = doctorService.saveDoctor(doctor);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred"));
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String email) {
        try {
            doctorService.deleteDoctor(email);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
