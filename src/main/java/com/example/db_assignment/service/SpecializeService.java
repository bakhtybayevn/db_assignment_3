package com.example.db_assignment.service;

import com.example.db_assignment.entity.Specialize;
import com.example.db_assignment.repository.SpecializeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializeService {
    private final SpecializeRepository specializeRepository;

    public SpecializeService(SpecializeRepository specializeRepository) {
        this.specializeRepository = specializeRepository;
    }

    public List<Specialize> getAllSpecializations() {
        return specializeRepository.findAll();
    }

    public Specialize getSpecializationById(Integer id) {
        return specializeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found for id: " + id));
    }

    public Specialize saveSpecialization(Specialize specialize) {
        return specializeRepository.save(specialize);
    }

    public void deleteSpecialization(Integer id) {
        specializeRepository.deleteById(id);
    }
}
