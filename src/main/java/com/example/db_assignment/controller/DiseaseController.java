package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Disease;
import com.example.db_assignment.entity.DiseaseType;
import com.example.db_assignment.service.DiseaseService;
import com.example.db_assignment.service.DiseaseTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {
    private final DiseaseService diseaseService;
    private final DiseaseTypeService diseaseTypeService;

    public DiseaseController(DiseaseService diseaseService, DiseaseTypeService diseaseTypeService) {
        this.diseaseService = diseaseService;
        this.diseaseTypeService = diseaseTypeService;
    }

    @GetMapping
    public List<Disease> getAllDiseases() {
        return diseaseService.getAllDiseases();
    }

    @GetMapping("/{diseaseCode}")
    public ResponseEntity<Disease> getDiseaseByCode(@PathVariable String diseaseCode) {
        try {
            Disease disease = diseaseService.getDiseaseByCode(diseaseCode);
            return ResponseEntity.ok(disease);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createDisease(@RequestBody Map<String, Object> payload) {
        try {
            // Extract fields from the payload
            String diseaseCode = (String) payload.get("disease_code");
            String pathogen = (String) payload.get("pathogen");
            String description = (String) payload.get("description");
            Integer diseaseTypeId = (Integer) payload.get("disease_type_id");

            // Check if DiseaseType exists
            DiseaseType diseaseType;
            try {
                diseaseType = diseaseTypeService.getDiseaseTypeById(diseaseTypeId);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "DiseaseType with id " + diseaseTypeId + " not found"));
            }

            // Create and save the Disease entity
            Disease disease = new Disease(diseaseCode, pathogen, description, diseaseType);
            Disease savedDisease = diseaseService.saveDisease(disease);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedDisease);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to create disease: " + e.getMessage()));
        }
    }


    @PatchMapping("/{diseaseCode}")
    public ResponseEntity<Disease> patchDisease(@PathVariable String diseaseCode, @RequestBody Map<String, Object> updates) {
        Disease existingDisease = diseaseService.getDiseaseByCode(diseaseCode);
        if (existingDisease == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (updates.containsKey("pathogen")) {
            existingDisease.setPathogen((String) updates.get("pathogen"));
        }

        if (updates.containsKey("description")) {
            existingDisease.setDescription((String) updates.get("description"));
        }

        if (updates.containsKey("diseaseTypeId")) {
            Integer diseaseTypeId = (Integer) updates.get("diseaseTypeId");
            DiseaseType diseaseType = diseaseTypeService.getDiseaseTypeById(diseaseTypeId);
            existingDisease.setDiseaseType(diseaseType);
        }

        Disease updatedDisease = diseaseService.saveDisease(existingDisease);
        return ResponseEntity.ok(updatedDisease);
    }

    @DeleteMapping("/{diseaseCode}")
    public void deleteDisease(@PathVariable String diseaseCode) {
        diseaseService.deleteDisease(diseaseCode);
    }
}
