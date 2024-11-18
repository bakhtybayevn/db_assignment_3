package com.example.db_assignment.repository;

import com.example.db_assignment.entity.PublicServant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicServantRepository extends JpaRepository<PublicServant, String> {
    @Query("SELECT ps.department, COUNT(DISTINCT ps.email) " +
            "FROM PublicServant ps " +
            "JOIN Record r ON ps.email = r.publicServant.email " +
            "WHERE r.disease.diseaseCode = 'COVID-19' " +
            "GROUP BY ps.department " +
            "HAVING COUNT(DISTINCT r.country.cname) > 1")
    List<Object[]> findDepartmentsWithCovid19CasesAcrossMultipleCountries();

    @Modifying
    @Query("UPDATE Users u " +
            "SET salary = salary * 2 " +
            "WHERE u.email IN (" +
            "  SELECT ps.email " +
            "  FROM PublicServant ps " +
            "  JOIN Record r ON ps.email = r.publicServant.email " +
            "  WHERE r.disease.diseaseCode = 'COVID-19' " +
            "  GROUP BY ps.email " +
            "  HAVING SUM(r.totalPatients) > 3" +
            ")")
    int doubleSalaryForHighCovid19Cases();
}
