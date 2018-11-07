package com.yt.repository;

import com.yt.domain.TeacherTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TeacherTimeRepository extends JpaRepository<TeacherTime,Integer> {

    public List<TeacherTime> findAll();
    public List<TeacherTime> findByTeacher_Name(String teacher_name);
    public TeacherTime findById(int id);
    public List<TeacherTime> findByTeacher_NameAndState(String teacher_name,int state);
    public TeacherTime findByAnswerTime(Date answer_time);
    public TeacherTime findByTeacher_NameAndAnswerTime(String name,Date time);

}
