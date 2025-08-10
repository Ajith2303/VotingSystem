package com.example.votingsystem.repository;

import com.example.votingsystem.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen, Long> {
    Optional<Citizen> findByNameIgnoreCase(String name);
}
