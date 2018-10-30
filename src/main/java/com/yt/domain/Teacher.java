package com.yt.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Teacher {
    @Id
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
    private List<TeacherTime> teacherTimes;

    public Teacher(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeacherTime> getTeacherTimes() {
        return teacherTimes;
    }

    public void setTeacherTimes(List<TeacherTime> teacherTimes) {
        this.teacherTimes = teacherTimes;
    }
}
