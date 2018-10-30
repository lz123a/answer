package com.yt.domain;

import sun.dc.pr.PRError;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity
public class Clas {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clas")
    private List<Student> students;

    public Clas(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
