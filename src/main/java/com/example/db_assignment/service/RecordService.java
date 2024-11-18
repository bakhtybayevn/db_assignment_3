package com.example.db_assignment.service;

import com.example.db_assignment.entity.Record;
import com.example.db_assignment.repository.RecordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Record getRecordById(Integer id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found for id: " + id));
    }

    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    public void deleteRecord(Integer id) {
        recordRepository.deleteById(id);
    }

    public List<Map<String, Object>> getTopCountriesByTotalPatients() {
        Pageable pageable = PageRequest.of(0, 2); // Get top 2 countries
        List<Object[]> results = recordRepository.findTopCountriesByTotalPatients(pageable);

        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> record = new HashMap<>();
            record.put("country", row[0]);
            record.put("total_patients", row[1]);
            response.add(record);
        }
        return response;
    }

    public Long getTotalCovid19Patients() {
        Long totalPatients = recordRepository.findTotalCovid19Patients();
        return totalPatients != null ? totalPatients : 0;
    }
}
