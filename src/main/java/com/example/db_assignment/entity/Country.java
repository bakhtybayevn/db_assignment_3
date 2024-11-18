package com.example.db_assignment.entity;

import jakarta.persistence.*;
import java.math.BigInteger;

@Entity
public class Country {

    @Id
    @Column(length = 50)
    private String cname;

    @Column(nullable = false)
    private BigInteger population;

    // Getters and Setters
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public BigInteger getPopulation() {
        return population;
    }

    public void setPopulation(BigInteger population) {
        this.population = population;
    }
}

