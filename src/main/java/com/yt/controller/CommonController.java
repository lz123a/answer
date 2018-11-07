package com.yt.controller;

import com.yt.domain.TeacherTime;
import com.yt.repository.TeacherTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    TeacherTimeRepository teacherTimeRepository;

    @RequestMapping("/app/teacherTime1")
    public @ResponseBody Map<String,Object> teacherTime1(String teacherName){
        Map<String,Object> map = new HashMap<>();

        TeacherTime teacherTime = teacherTimeRepository.findById(1);
        map.put("success",true);
        map.put("message",teacherTime.getAnswerTime());
        System.out.println(teacherTime.getAnswerTime());
        return map;

    }

    @RequestMapping("/app/teacherTime")
    public @ResponseBody
    Map<String,Object> teacherTime(String teacherName){
        Map<String,Object> map = new HashMap<>();

        List<TeacherTime> teacherTimeList = teacherTimeRepository.findByTeacher_Name(teacherName);
        map.put("success",true);
        map.put("message",teacherTimeList);

        return map;

    }


    @GetMapping("/User/Account/ChangePasswd")
    public String userChangerPasswd(){
        return "/User/Account/ChangePasswd";
    }
    @GetMapping("/User/StudentInfor/Letter")
    public String userLetter(){
        return "/User/StudentInfor/Letter";
    }
    @GetMapping("/User/StudentInfor/systemMsge")
    public String userSysMsge(){
        return "/User/StudentInfor/systemMsge";
    }
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
    @GetMapping(value ={ "index","Index"})
    public String stu(){

        return "index";
    }
    @GetMapping("/MyInfo/Objection")
    public String myinfoObjection(){
        return "/MyInfo/Objection";
    }
    @GetMapping("/MyInfo/ClassInfo")
    public String myinfoClassinfo(){
        return "/MyInfo/ClassInfo";
    }
    @GetMapping("/MyInfo/Index")
    public String myIndex(){
        return "/MyInfo/Index";
    }
    @GetMapping("/EducationCenter/Application")
    public String educationCenterApplication(){
        return "/EducationCenter/Application";
    }
    @GetMapping("/EducationCenter/Book")
    public String educationCenterBook(){
        return "/EducationCenter/Book";
    }
    @GetMapping("/EducationCenter/Score")
    public String educationCenter(){
        return "/EducationCenter/Score";
    }
    @GetMapping("/MyAccount/wdcw")
    public String myAccountwdcw(){
        return "/MyAccount/wdcw";
    }

}
