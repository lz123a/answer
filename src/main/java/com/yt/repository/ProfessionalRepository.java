package com.yt.repository;

import com.yt.domain.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Properties;

public interface ProfessionalRepository extends JpaRepository<Professional,Integer> {
    public Professional findByName(String name);
}
