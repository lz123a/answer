package com.yt.repository;

import com.yt.domain.Clas;
import com.yt.domain.Od;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasRepository extends JpaRepository<Clas,Integer> {
    public Clas findByName(String name);
}
