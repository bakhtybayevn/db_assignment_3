package com.example.db_assignment.entity;

import jakarta.persistence.*;

@Entity
public class Users {

    @Id
    @Column(length = 60)
    private String email;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String surname;

    private Integer salary;

    @Column(length = 20, nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "cname", nullable = false)
    private Country country;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                ", country=" + (country != null ? country.getCname() : "null") +
                '}';
    }
}
