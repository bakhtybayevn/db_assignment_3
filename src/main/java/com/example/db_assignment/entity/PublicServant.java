package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "public_servant")
public class PublicServant {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private Users user;

    @Column(name = "department", nullable = false)
    private String department;

    public PublicServant() {}

    public PublicServant(String email, Users user, String department) {
        this.email = email;
        this.user = user;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
