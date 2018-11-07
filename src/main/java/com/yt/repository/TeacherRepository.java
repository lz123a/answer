package com.yt.repository;

import com.yt.domain.Student;
import com.yt.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TeacherRepository  extends JpaRepository<Teacher,Integer> {

    public Teacher findByName(String name);
}
