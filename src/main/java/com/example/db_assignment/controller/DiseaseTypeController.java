package com.example.db_assignment.controller;

import com.example.db_assignment.entity.DiseaseType;
import com.example.db_assignment.service.DiseaseTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/disease-types")
public class DiseaseTypeController {
    private final DiseaseTypeService diseaseTypeService;

    public DiseaseTypeController(DiseaseTypeService diseaseTypeService) {
        this.diseaseTypeService = diseaseTypeService;
    }

    @GetMapping
    public List<DiseaseType> getAllDiseaseTypes() {
        return diseaseTypeService.getAllDiseaseTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiseaseType> getDiseaseTypeById(@PathVariable Integer id) {
        try {
            DiseaseType diseaseType = diseaseTypeService.getDiseaseTypeById(id);
            return ResponseEntity.ok(diseaseType);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public DiseaseType createDiseaseType(@RequestBody DiseaseType diseaseType) {
        return diseaseTypeService.saveDiseaseType(diseaseType);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DiseaseType> patchDiseaseType(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        DiseaseType existingDiseaseType = diseaseTypeService.getDiseaseTypeById(id);
        if (existingDiseaseType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (updates.containsKey("description")) {
            Object descriptionValue = updates.get("description");
            if (descriptionValue instanceof String) {
                existingDiseaseType.setDescription((String) descriptionValue);
            } else {
                throw new IllegalArgumentException("Invalid value for description: " + descriptionValue);
            }
        }

        DiseaseType updatedDiseaseType = diseaseTypeService.saveDiseaseType(existingDiseaseType);
        return ResponseEntity.ok(updatedDiseaseType);
    }

    @DeleteMapping("/{id}")
    public void deleteDiseaseType(@PathVariable Integer id) {
        diseaseTypeService.deleteDiseaseType(id);
    }
}
