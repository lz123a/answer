package com.yt.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Integer id;

    private String stu_id;

    private String name;

    private String phone;

    private String sex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clas_id")
    private Clas clas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "college_id")
    private College college;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<Od> orders;

    public Student(){

    }

    public List<Od> getOrders() {
        return orders;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setOrders(List<Od> orders) {
        this.orders = orders;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Clas getClas() {
        return clas;
    }

    public void setClas(Clas clas) {
        this.clas = clas;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
