package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Country;
import com.example.db_assignment.entity.Disease;
import com.example.db_assignment.entity.Discover;
import com.example.db_assignment.service.CountryService;
import com.example.db_assignment.service.DiseaseService;
import com.example.db_assignment.service.DiscoverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discover")
public class DiscoverController {
    private final DiscoverService discoverService;
    private final CountryService countryService;
    private final DiseaseService diseaseService;

    public DiscoverController(DiscoverService discoverService, CountryService countryService, DiseaseService diseaseService) {
        this.discoverService = discoverService;
        this.countryService = countryService;
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public List<Discover> getAllDiscoveries() {
        return discoverService.getAllDiscoveries();
    }

    @PostMapping
    public ResponseEntity<?> createDiscover(@RequestBody Map<String, Object> payload) {
        try {
            String cname = (String) payload.get("cname");
            String diseaseCode = (String) payload.get("disease_code");
            String firstEncDateStr = (String) payload.get("first_enc_date");

            LocalDateTime firstEncDate = LocalDateTime.parse(firstEncDateStr);

            Country country = countryService.getCountryByCname(cname);
            Disease disease = diseaseService.getDiseaseByCode(diseaseCode);

            Discover discover = new Discover(cname, diseaseCode, country, disease, firstEncDate);
            Discover savedDiscover = discoverService.saveDiscover(discover);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedDiscover);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDiscover(@RequestParam String cname, @RequestParam String diseaseCode) {
        try {
            discoverService.deleteDiscover(cname, diseaseCode);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
