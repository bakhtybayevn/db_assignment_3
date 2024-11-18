package com.example.db_assignment.repository;

import com.example.db_assignment.dto.DoctorInfoDTO;
import com.example.db_assignment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    @Query("SELECT new com.example.db_assignment.dto.DoctorInfoDTO(d.email, d.degree, u.name, u.surname) " +
            "FROM Doctor d JOIN Users u ON d.email = u.email " +
            "WHERE d.email NOT IN (" +
            "SELECT s.doctor.email FROM Specialize s " +
            "WHERE s.diseaseType.description = 'Infectious Diseases')")
    List<DoctorInfoDTO> findDoctorsNotSpecializedInInfectiousDiseases();

    @Query("SELECT new com.example.db_assignment.dto.DoctorInfoDTO(d.email, d.degree, u.name, u.surname) " +
            "FROM Doctor d " +
            "JOIN Users u ON d.email = u.email " +
            "JOIN Specialize s ON s.doctor.email = d.email " +
            "GROUP BY d.email, d.degree, u.name, u.surname " +
            "HAVING COUNT(s.diseaseType.id) > 2")
    List<DoctorInfoDTO> findDoctorsSpecializedInMoreThanTwoDiseaseTypes();

    @Query("SELECT u.country.cname, AVG(u.salary) " +
            "FROM Doctor d " +
            "JOIN Users u ON d.email = u.email " +
            "JOIN Specialize s ON s.doctor.email = d.email " +
            "JOIN DiseaseType dt ON s.diseaseType.id = dt.id " +
            "WHERE dt.description = 'Virology' " +
            "GROUP BY u.country.cname")
    List<Object[]> findAverageSalaryOfVirologyDoctorsByCountry();
}
