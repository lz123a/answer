package com.yt.controller;

import com.yt.domain.Od;
import com.yt.domain.Student;
import com.yt.domain.TeacherTime;
import com.yt.repository.OdRepository;
import com.yt.repository.StudentRepository;
import com.yt.repository.TeacherTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class StudentController {

    @Autowired
    OdRepository odRepository;
    @Autowired
    TeacherTimeRepository teacherTimeRepository;
    @Autowired
    StudentRepository studentRepository;




    @RequestMapping("/app/submitTime")
    public Map<String,Object> submitTime(String datetime,String name){
        Date date = new Date();
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(datetime);
        }catch (Exception e){
            e.printStackTrace();
        }
        TeacherTime teacherTime = teacherTimeRepository.findByAnswerTime(date);
        teacherTime.setState(1);
        teacherTimeRepository.save(teacherTime);
        Od od = new Od();
        od.setAnswerPlace("未定");
        od.setState(0);
        Student student = studentRepository.findByName(name);
        od.setStudent(student);
        od.setTeacherTime(teacherTime);
        odRepository.save(od);
        map.put("success",true);
        map.put("message","预约成功");
        return map;
    }

    @RequestMapping("/app/getTeacherTime")
    public Map<String,Object> getTeacherTime(String teacherName){
        Map<String,Object> map = new HashMap<>();
        List<TeacherTime> teacherTimeList = teacherTimeRepository.findByTeacher_NameAndState(teacherName,0);
        List<Date> timeList = new ArrayList<>();
        for (TeacherTime teacherTime : teacherTimeList) {
            timeList.add(teacherTime.getAnswerTime());
        }
        map.put("success",true);
        map.put("timeList",timeList);
        return map;
    }


    @RequestMapping("/app/studentAnswerTime")
    public Map<String,Object> studentAnswerTime(String studentName,int state){
        Map<String,Object> map = new HashMap<>();
        List<Od> odList;
        if(state==2){
            odList = odRepository.findByStudent_NameAndState(studentName,state);
        }else{
            odList = odRepository.findByStudent_NameAndStateLessThan(studentName,2);
        }

        System.out.println(studentName+state);
        if(odList.size()==0){
            map.put("success",false);
            map.put("message","没有符合条件的预约");
            return map;
        }
        List<String> answerPlace = new ArrayList<>();
        List<Date> answerTime = new ArrayList<>();
        List<String> teacherName = new ArrayList<>();
        for(Od od:odList){
            answerPlace.add(od.getAnswerPlace());
            answerTime.add(od.getTeacherTime().getAnswerTime());
            teacherName.add(od.getTeacherTime().getTeacher().getName());
        }
        map.put("success",true);
        map.put("answerPlace",answerPlace);
        map.put("answerTime",answerTime);
        map.put("teacherName",teacherName);
        return map;
    }

}
