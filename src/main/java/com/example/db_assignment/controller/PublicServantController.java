package com.example.db_assignment.controller;

import com.example.db_assignment.entity.PublicServant;
import com.example.db_assignment.entity.Users;
import com.example.db_assignment.service.PublicServantService;
import com.example.db_assignment.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public-servants")
public class PublicServantController {
    private final PublicServantService publicServantService;
    private final UsersService usersService;

    public PublicServantController(PublicServantService publicServantService, UsersService usersService) {
        this.publicServantService = publicServantService;
        this.usersService = usersService;
    }

    @GetMapping
    public List<PublicServant> getAllPublicServants() {
        return publicServantService.getAllPublicServants();
    }

    @GetMapping("/{email}")
    public ResponseEntity<PublicServant> getPublicServantByEmail(@PathVariable String email) {
        try {
            PublicServant publicServant = publicServantService.getPublicServantByEmail(email);
            return ResponseEntity.ok(publicServant);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPublicServant(@RequestBody Map<String, String> payload) {
        try {
            String email = payload.get("email");
            String department = payload.get("department");

            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }

            if (department == null || department.trim().isEmpty()) {
                throw new IllegalArgumentException("Department cannot be null or empty");
            }

            // Check if the user exists
            Users user = usersService.getUserByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "User not found"));
            }

            // Ensure the email is explicitly set
            PublicServant publicServant = new PublicServant();
            publicServant.setEmail(email);
            publicServant.setUser(user);
            publicServant.setDepartment(department);

            // Save the PublicServant
            PublicServant savedPublicServant = publicServantService.savePublicServant(publicServant);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPublicServant);
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
    public ResponseEntity<Void> deletePublicServant(@PathVariable String email) {
        try {
            publicServantService.deletePublicServant(email);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
