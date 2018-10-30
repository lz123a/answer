package com.yt.controller;

import com.yt.domain.TeacherTime;
import com.yt.repository.TeacherTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommonController {

    @Autowired
    TeacherTimeRepository teacherTimeRepository;

    @RequestMapping("/teacherTime1")
    public Map<String,Object> teacherTime1(String teacherName){
        Map<String,Object> map = new HashMap<>();

        TeacherTime teacherTime = teacherTimeRepository.findById(1);
        map.put("success",true);
        map.put("message",teacherTime.getAnswerTime());
        System.out.println(teacherTime.getAnswerTime());
        return map;

    }

    @RequestMapping("/teacherTime")
    public Map<String,Object> teacherTime(String teacherName){
        Map<String,Object> map = new HashMap<>();

        List<TeacherTime> teacherTimeList = teacherTimeRepository.findByTeacher_Name(teacherName);
        map.put("success",true);
        map.put("message",teacherTimeList);

        return map;

    }

}
