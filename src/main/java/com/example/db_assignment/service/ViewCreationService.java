package com.example.db_assignment.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ViewCreationService {

    private final JdbcTemplate jdbcTemplate;

    public ViewCreationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createPatientDiseaseView() {
        String sql = """
            CREATE OR REPLACE VIEW PatientDiseaseView AS
            SELECT 
                u.name AS patient_name,
                u.surname AS patient_surname,
                d.description AS disease_name
            FROM 
                Users u
            JOIN 
                Record r ON u.email = r.email
            JOIN 
                Disease d ON r.disease_code = d.disease_code
        """;
        jdbcTemplate.execute(sql);
    }

    public List<Object[]> getViewData(String viewName) {
        String sql = "SELECT * FROM " + viewName;
        return Collections.singletonList(jdbcTemplate.queryForList(sql).toArray());
    }

    public void createDiseaseCodeIndex() {
        String sql = "CREATE INDEX IF NOT EXISTS idx_disease_code ON Disease(disease_code)";
        jdbcTemplate.execute(sql);
    }
}
