package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private Users user;

    // Constructors, getters, and setters
    public Patient() {}

    public Patient(Users user) {
        this.email = user.getEmail();
        this.user = user;
    }

    // Getters and setters
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
        this.email = user.getEmail(); // Synchronize email
    }

    @Override
    public String toString() {
        return "Patient{" +
                "email='" + email + '\'' +
                ", user=" + user +
                '}';
    }
}
