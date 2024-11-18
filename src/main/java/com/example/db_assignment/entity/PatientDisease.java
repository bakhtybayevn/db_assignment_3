package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@IdClass(PatientDiseaseId.class)
@Table(name = "patient_disease")
public class PatientDisease {
    @Id
    @Column(name = "email", nullable = false, insertable = false, updatable = false)
    private String email;

    @Id
    @Column(name = "disease_code", nullable = false, insertable = false, updatable = false)
    private String diseaseCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disease_code", referencedColumnName = "disease_code", insertable = false, updatable = false)
    private Disease disease;

    public PatientDisease() {}

    public PatientDisease(Users user, Disease disease) {
        this.user = user;
        this.disease = disease;
        this.email = user.getEmail();
        this.diseaseCode = disease.getDiseaseCode();
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
