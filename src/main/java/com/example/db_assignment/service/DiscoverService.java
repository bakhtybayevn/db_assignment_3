package com.example.db_assignment.service;

import com.example.db_assignment.entity.Discover;
import com.example.db_assignment.entity.DiscoverId;
import com.example.db_assignment.repository.DiscoverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoverService {
    private final DiscoverRepository discoverRepository;

    public DiscoverService(DiscoverRepository discoverRepository) {
        this.discoverRepository = discoverRepository;
    }

    public List<Discover> getAllDiscoveries() {
        return discoverRepository.findAll();
    }

    public Discover saveDiscover(Discover discover) {
        return discoverRepository.save(discover);
    }

    public void deleteDiscover(String cname, String diseaseCode) {
        DiscoverId discoverId = new DiscoverId(cname, diseaseCode);
        discoverRepository.deleteById(discoverId);
    }
}
