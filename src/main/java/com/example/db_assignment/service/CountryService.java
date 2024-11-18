package com.example.db_assignment.service;

import com.example.db_assignment.entity.Country;
import com.example.db_assignment.repository.CountryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryByCname(String cname) {
        return countryRepository.findById(cname)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountry(String cname) {
        countryRepository.deleteById(cname);
    }

    public boolean existsByCname(String cname) {
        return countryRepository.existsById(cname);
    }

    @Transactional
    public Country updateCname(String oldCname, String newCname) {
        // Fetch the entity to detach it
        Country country = countryRepository.findById(oldCname)
                .orElseThrow(() -> new RuntimeException("Country not found: " + oldCname));

        // Detach the entity
        entityManager.detach(country);

        // Update cname directly in the database
        countryRepository.updateCnameNative(oldCname, newCname);

        // Set the new cname and re-save the entity
        country.setCname(newCname);
        return countryRepository.save(country);
    }

}
