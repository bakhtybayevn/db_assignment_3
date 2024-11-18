package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private PublicServant publicServant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cname", referencedColumnName = "cname", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disease_code", referencedColumnName = "disease_code", nullable = false)
    private Disease disease;

    @Column(name = "total_deaths", nullable = false)
    private Integer totalDeaths;

    @Column(name = "total_patients", nullable = false)
    private Integer totalPatients;

    public Record() {}

    public Record(PublicServant publicServant, Country country, Disease disease, Integer totalDeaths, Integer totalPatients) {
        this.publicServant = publicServant;
        this.country = country;
        this.disease = disease;
        this.totalDeaths = totalDeaths;
        this.totalPatients = totalPatients;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PublicServant getPublicServant() {
        return publicServant;
    }

    public void setPublicServant(PublicServant publicServant) {
        this.publicServant = publicServant;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(Integer totalPatients) {
        this.totalPatients = totalPatients;
    }
}
