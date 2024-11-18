package com.example.db_assignment.controller;

import com.example.db_assignment.entity.Country;
import com.example.db_assignment.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{cname}")
    public Country getCountryByCname(@PathVariable String cname) {
        return countryService.getCountryByCname(cname);
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @PatchMapping("/{cname}")
    public ResponseEntity<Country> patchCountry(@PathVariable String cname, @RequestBody Map<String, Object> updates) {
        Country existingCountry = countryService.getCountryByCname(cname);
        if (existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        String newCname = (String) updates.getOrDefault("cname", null);

        if (updates.containsKey("population")) {
            Object populationValue = updates.get("population");
            if (populationValue instanceof Number) {
                existingCountry.setPopulation(BigInteger.valueOf(((Number) populationValue).longValue()));
            } else if (populationValue instanceof String) {
                existingCountry.setPopulation(new BigInteger((String) populationValue));
            } else {
                throw new IllegalArgumentException("Invalid value for population: " + populationValue);
            }
        }

        if (newCname != null) {
            existingCountry = countryService.updateCname(cname, newCname);
        }

        Country updatedCountry = countryService.saveCountry(existingCountry);

        return ResponseEntity.ok(updatedCountry);
    }

    @DeleteMapping("/{cname}")
    public void deleteCountry(@PathVariable String cname) {
        countryService.deleteCountry(cname);
    }
}
