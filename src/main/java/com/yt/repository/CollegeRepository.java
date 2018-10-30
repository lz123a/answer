package com.yt.repository;

import com.yt.domain.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Integer>{
    public College findByName(String name);
    public College findById(int id);
}
