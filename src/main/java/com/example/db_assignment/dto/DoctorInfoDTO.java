package com.example.db_assignment.dto;

public class DoctorInfoDTO {
    private String email;
    private String degree;
    private String name;
    private String surname;

    public DoctorInfoDTO(String email, String degree, String name, String surname) {
        this.email = email;
        this.degree = degree;
        this.name = name;
        this.surname = surname;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
