package com.yt.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class TeacherTime {
    @Id
    private int id;

    private Date answerTime;

    private int state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacherTime")
    private List<Od> orders;

    public TeacherTime(){

    }

    public List<Od> getOrders() {
        return orders;
    }

    public void setOrders(List<Od> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    public Date getAnswerTime() {
        return answerTime;
    }
    @JsonBackReference
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
