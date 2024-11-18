package com.example.db_assignment.controller;

import com.example.db_assignment.entity.*;
import com.example.db_assignment.entity.Record;
import com.example.db_assignment.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService recordService;
    private final PublicServantService publicServantService;
    private final CountryService countryService;
    private final DiseaseService diseaseService;

    public RecordController(RecordService recordService, PublicServantService publicServantService,
                            CountryService countryService, DiseaseService diseaseService) {
        this.recordService = recordService;
        this.publicServantService = publicServantService;
        this.countryService = countryService;
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Integer id) {
        try {
            Record record = recordService.getRecordById(id);
            return ResponseEntity.ok(record);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createRecord(@RequestBody Map<String, Object> payload) {
        try {
            String email = (String) payload.get("email");
            String cname = (String) payload.get("cname");
            String diseaseCode = (String) payload.get("disease_code");
            Integer totalDeaths = (Integer) payload.get("total_deaths");
            Integer totalPatients = (Integer) payload.get("total_patients");

            if (email == null || cname == null || diseaseCode == null || totalDeaths == null || totalPatients == null) {
                throw new IllegalArgumentException("All fields are required");
            }

            PublicServant publicServant = publicServantService.getPublicServantByEmail(email);
            Country country = countryService.getCountryByCname(cname);
            Disease disease = diseaseService.getDiseaseByCode(diseaseCode);

            Record record = new Record(publicServant, country, disease, totalDeaths, totalPatients);
            Record savedRecord = recordService.saveRecord(record);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
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
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer id) {
        try {
            recordService.deleteRecord(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
