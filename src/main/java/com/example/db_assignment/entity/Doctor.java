package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private Users user;

    @Column(name = "degree", nullable = false)
    private String degree;

    public Doctor() {}

    public Doctor(String email, Users user, String degree) {
        this.email = email;
        this.user = user;
        this.degree = degree;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
