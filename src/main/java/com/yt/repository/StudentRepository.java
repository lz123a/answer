package com.yt.repository;

import com.yt.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    public Student findByName(String name);
    public Student findById(int id);
}
