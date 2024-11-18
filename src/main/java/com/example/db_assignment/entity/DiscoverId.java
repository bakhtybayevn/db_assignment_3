package com.example.db_assignment.entity;

import java.io.Serializable;
import java.util.Objects;

public class DiscoverId implements Serializable {
    private String cname;
    private String diseaseCode;

    public DiscoverId() {}

    public DiscoverId(String cname, String diseaseCode) {
        this.cname = cname;
        this.diseaseCode = diseaseCode;
    }

    // Getters, Setters, equals(), and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscoverId that = (DiscoverId) o;
        return Objects.equals(cname, that.cname) && Objects.equals(diseaseCode, that.diseaseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cname, diseaseCode);
    }
}
