package com.yt.controller;

import com.yt.domain.Clas;
import com.yt.domain.College;
import com.yt.domain.Student;
import com.yt.domain.User;
import com.yt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClasRepository clasRepository;
    @Autowired
    CollegeRepository collegeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ProfessionalRepository professionalRepository;

    @RequestMapping("/registPost")
    public  Map<String,Object> registPost(String name,String clas,String stu_id,String sex,String phone,String uid,String psword,String department,String professional){
        Map<String,Object> map = new HashMap<>();

        Student student = new Student();
        student.setClas(clasRepository.findByName(clas));
        student.setName(name);
        student.setPhone(phone);
        student.setCollege(collegeRepository.findById(1));
        student.setDepartment(departmentRepository.findByName(department));
        student.setStu_id(stu_id);
        student.setProfessional(professionalRepository.findByName(professional));
        student.setSex(sex);
        studentRepository.save(student);
        User user = new User();
        user.setIdentity(0);
        user.setUid(uid);
        user.setPsword(psword);
        user.setStudent(student);
        userRepository.save(user);
        map.put("success",true);
        map.put("message","注册成功");
        return map;
    }

    @RequestMapping("/rrr")
    public Clas rrr(String name){
        Clas clas = new Clas();
        clas.setName(name);
        return clasRepository.save(clas);
    }

    @RequestMapping("/loginPost")
    public Map<String,Object> loginPost(String uid,String psword,String identity){
        Map<String,Object> map = new HashMap<>();
        System.out.println(uid+"  "+psword+"  "+identity);
        User user = new User();
        user.setUid(uid);
        user.setPsword(psword);
        if(identity.equals("teacher")){
            user.setIdentity(1);
        }else{
            user.setIdentity(0);
        }
        User user1 = userRepository.findByUidAndPswordAndIdentity(uid,psword,user.getIdentity());
        if(user1==null){
            map.put("success",false);
            map.put("message","账号密码错误");
            return map;
        }
        map.put("success",true);
        map.put("message","正确");
        Student student = user1.getStudent();
        String name = student.getName();
        map.put("name",name);
        return map;
    }

}
