package com.example.db_assignment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(DiscoverId.class) // Composite key class
@Table(name = "discover")
public class Discover {
    @Id
    @Column(name = "cname", nullable = false, insertable = false, updatable = false)
    private String cname;

    @Id
    @Column(name = "disease_code", nullable = false, insertable = false, updatable = false)
    private String diseaseCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cname", referencedColumnName = "cname", insertable = false, updatable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disease_code", referencedColumnName = "disease_code", insertable = false, updatable = false)
    private Disease disease;

    @Column(name = "first_enc_date", nullable = false)
    private LocalDateTime firstEncDate;

    public Discover() {}

    public Discover(String cname, String diseaseCode, Country country, Disease disease, LocalDateTime firstEncDate) {
        this.cname = cname;
        this.diseaseCode = diseaseCode;
        this.country = country;
        this.disease = disease;
        this.firstEncDate = firstEncDate;
    }

    // Getters and Setters
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
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

    public LocalDateTime getFirstEncDate() {
        return firstEncDate;
    }

    public void setFirstEncDate(LocalDateTime firstEncDate) {
        this.firstEncDate = firstEncDate;
    }
}
