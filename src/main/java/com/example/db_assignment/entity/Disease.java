package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @Column(name = "disease_code", nullable = false, unique = true, length = 50)
    private String diseaseCode;

    @Column(nullable = false, length = 20)
    private String pathogen;

    @Column(nullable = false, length = 140)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER) // Change to EAGER
    @JoinColumn(name = "id", nullable = false)
    private DiseaseType diseaseType;


    public Disease() {}

    public Disease(String diseaseCode, String pathogen, String description, DiseaseType diseaseType) {
        this.diseaseCode = diseaseCode;
        this.pathogen = pathogen;
        this.description = description;
        this.diseaseType = diseaseType;
    }

    // Getters and Setters
    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    public String getPathogen() {
        return pathogen;
    }

    public void setPathogen(String pathogen) {
        this.pathogen = pathogen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiseaseType getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(DiseaseType diseaseType) {
        this.diseaseType = diseaseType;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "diseaseCode='" + diseaseCode + '\'' +
                ", pathogen='" + pathogen + '\'' +
                ", description='" + description + '\'' +
                ", diseaseType=" + (diseaseType != null ? diseaseType.getId() : "null") +
                '}';
    }
}
