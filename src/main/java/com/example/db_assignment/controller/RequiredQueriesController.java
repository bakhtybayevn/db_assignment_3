package com.example.db_assignment.controller;

import com.example.db_assignment.dto.DoctorInfoDTO;
import com.example.db_assignment.entity.Disease;
import com.example.db_assignment.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/queries")
public class RequiredQueriesController {
    private final DiseaseService diseaseService;
    private final DoctorService doctorService;
    private final PublicServantService publicServantService;
    private final UsersService usersService;
    private final RecordService recordService;
    private final ViewCreationService viewCreationService;

    public RequiredQueriesController(DiseaseService diseaseService, DoctorService doctorService, PublicServantService publicServantService, UsersService usersService, RecordService recordService, ViewCreationService viewCreationService) {
        this.diseaseService = diseaseService;
        this.doctorService = doctorService;
        this.publicServantService = publicServantService;
        this.usersService = usersService;
        this.recordService = recordService;
        this.viewCreationService = viewCreationService;
    }

    @GetMapping("/basic/1")
    public List<Disease> getDiseasesByPathogenBefore2020() {
        return diseaseService.getBacterialDiseasesBefore2020();
    }

    @GetMapping("/basic/2")
    public List<DoctorInfoDTO> getDoctorsNotSpecializedInInfectiousDiseases() {
        return doctorService.getDoctorsNotSpecializedInInfectiousDiseases();
    }

    @GetMapping("/basic/3")
    public List<DoctorInfoDTO> getDoctorsSpecializedInMoreThanTwoDiseaseTypes() {
        return doctorService.getDoctorsSpecializedInMoreThanTwoDiseaseTypes();
    }

    @GetMapping("/complex/1")
    public List<Map<String, Object>> getAverageSalaryOfVirologyDoctorsByCountry() {
        return doctorService.getAverageSalaryOfVirologyDoctorsByCountry();
    }

    @GetMapping("/complex/2")
    public List<Map<String, Object>> getDepartmentsWithCovid19CasesAcrossMultipleCountries() {
        return publicServantService.getDepartmentsWithCovid19CasesAcrossMultipleCountries();
    }

    @GetMapping("/modify/1")
    public String doubleSalaryForHighCovid19Cases() {
        int updatedCount = publicServantService.doubleSalaryForHighCovid19Cases();
        return updatedCount + " public servants had their salary doubled.";
    }

    @GetMapping("/modify/2")
    public String deleteUsersWithSpecificNames() {
        int deletedCount = usersService.deleteUsersByNameContaining("bek", "gul");
        return deletedCount + " users were deleted.";
    }

    @GetMapping("/additional/1")
    public List<Map<String, Object>> getTopCountriesByTotalPatients() {
        return recordService.getTopCountriesByTotalPatients();
    }

    @GetMapping("/derived/1")
    public Map<String, Object> getTotalCovid19Patients() {
        Long totalPatients = recordService.getTotalCovid19Patients();
        Map<String, Object> response = new HashMap<>();
        response.put("total_number_of_patients_suffered_COVID-19", totalPatients);
        return response;
    }

    @PostMapping("/create-view")
    public String createView() {
        viewCreationService.createPatientDiseaseView();
        return "View created successfully!";
    }

    @GetMapping("/view-data")
    public List<Object[]> getViewData() {
        return viewCreationService.getViewData("PatientDiseaseView");
    }

    @PostMapping("/create-index")
    public String createIndex() {
        viewCreationService.createDiseaseCodeIndex();
        return "Index created successfully!";
    }
}
