package com.example.db_assignment.repository;

import com.example.db_assignment.entity.Discover;
import com.example.db_assignment.entity.DiscoverId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscoverRepository extends JpaRepository<Discover, DiscoverId> {
    // Additional query methods if needed
}
