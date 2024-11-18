package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "disease_type")
public class DiseaseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 140)
    private String description;

    public DiseaseType() {}

    public DiseaseType(String description) {
        this.description = description;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DiseaseType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
