package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "specialize")
public class Specialize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private DiseaseType diseaseType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private Doctor doctor;

    public Specialize() {}

    public Specialize(DiseaseType diseaseType, Doctor doctor) {
        this.diseaseType = diseaseType;
        this.doctor = doctor;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DiseaseType getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(DiseaseType diseaseType) {
        this.diseaseType = diseaseType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
