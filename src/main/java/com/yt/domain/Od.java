package com.yt.domain;

import javax.persistence.*;

@Entity
@Table(name = "od")
public class Od{
    @Id
    @GeneratedValue
    private int id;

    private String answerPlace;

    private int state;//0已提交未同意，1已同意未答疑，2已答疑

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherTime_id")
    private TeacherTime teacherTime;

    public Od(){

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerPlace() {
        return answerPlace;
    }

    public void setAnswerPlace(String answerPlace) {
        this.answerPlace = answerPlace;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TeacherTime getTeacherTime() {
        return teacherTime;
    }

    public void setTeacherTime(TeacherTime teacherTime) {
        this.teacherTime = teacherTime;
    }
}
