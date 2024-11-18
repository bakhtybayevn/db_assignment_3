package com.example.db_assignment.entity;

import java.io.Serializable;
import java.util.Objects;

public class PatientDiseaseId implements Serializable {
    private String email;
    private String diseaseCode;

    public PatientDiseaseId() {}

    public PatientDiseaseId(String email, String diseaseCode) {
        this.email = email;
        this.diseaseCode = diseaseCode;
    }

    // Getters, Setters, equals(), and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDiseaseId that = (PatientDiseaseId) o;
        return Objects.equals(email, that.email) && Objects.equals(diseaseCode, that.diseaseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, diseaseCode);
    }
}
