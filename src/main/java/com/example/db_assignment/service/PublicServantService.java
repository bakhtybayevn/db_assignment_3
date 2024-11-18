package com.example.db_assignment.service;

import com.example.db_assignment.entity.PublicServant;
import com.example.db_assignment.repository.PublicServantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublicServantService {
    private final PublicServantRepository publicServantRepository;

    public PublicServantService(PublicServantRepository publicServantRepository) {
        this.publicServantRepository = publicServantRepository;
    }

    public List<PublicServant> getAllPublicServants() {
        return publicServantRepository.findAll();
    }

    public PublicServant getPublicServantByEmail(String email) {
        return publicServantRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Public Servant not found for email: " + email));
    }

    public PublicServant savePublicServant(PublicServant publicServant) {
        if (publicServant.getEmail() == null || publicServant.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return publicServantRepository.save(publicServant);
    }

    public void deletePublicServant(String email) {
        publicServantRepository.deleteById(email);
    }

    public List<Map<String, Object>> getDepartmentsWithCovid19CasesAcrossMultipleCountries() {
        List<Object[]> results = publicServantRepository.findDepartmentsWithCovid19CasesAcrossMultipleCountries();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> record = new HashMap<>();
            record.put("department", row[0]);
            record.put("employeeCount", row[1]);
            response.add(record);
        }
        return response;
    }

    @Transactional
    public int doubleSalaryForHighCovid19Cases() {
        return publicServantRepository.doubleSalaryForHighCovid19Cases();
    }
}
